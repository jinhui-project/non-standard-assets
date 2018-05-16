package com.jinhui.quartz;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import com.jinhui.model.Assets;
import com.jinhui.model.abs.AssetPackage;
import com.jinhui.service.abs.BackPaymentService;
import com.jinhui.service.abs.InvoiceService;
import com.jinhui.service.assets.AssetsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.jinhui.model.abs.PeAccountsReceivable;
import com.jinhui.model.abs.PeBackpaymentRecord;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ABS资产 应收账款相关定时任务
 * @author wsc
 *
 */
@Controller
@RequestMapping("/ABS")
public class ABSAssetsQuartz {
	private static Logger logger = LoggerFactory.getLogger(ABSAssetsQuartz.class);
	@Autowired
	AssetsService assetsService;
	
	@Autowired
	BackPaymentService BackPaymentService;
	
	@Autowired
	InvoiceService InvoiceService;
	BigDecimal backpayBalance = new BigDecimal("0");

	
	/**
	 * 回款
	 */
	@RequestMapping("/backPayment")
	public void backPayment(){
		long start = System.currentTimeMillis();
		//处理逾期的基础资产
		overdue();

		logger.info("--------------------start 回款开始 -----------------------");
		List<PeBackpaymentRecord> backpayRecordList = BackPaymentService.selectBackpayRecordList();
		for(PeBackpaymentRecord backpayRecord:backpayRecordList){
			backpayBalance = backpayRecord.getBackpayBalance();
			//查询“到期兑付”状态的资产包
			List<Assets> assetsList = assetsService.getABSAssetsToPay();
		    logger.info("资产包个数： ---------------  " + assetsList.size() + "  --------------------");
		    //有“到期兑付”的资产包，先处理“到期兑付”资产包中的资产，再兑付基础资产
		    if(assetsList.size() > 0){

				List<PeAccountsReceivable>  peAccountsReceivableList = InvoiceService.selectAssetByAids(assetsList,backpayRecord.getReceivableDebtor(),backpayRecord.getOriginalHolder());

				//资产包中的基础资产回款处理
				backpayBalance = handle(backpayBalance,peAccountsReceivableList,backpayRecord);

				if(backpayBalance.doubleValue() > 0){
					//直接兑付基础资产
					handleBaseAssets(backpayBalance,backpayRecord);
				}
		    }else{
                //没有需要兑付的资产包, 直接兑付基础资产
				handleBaseAssets(backpayBalance,backpayRecord);
		    }
		}
		long end = System.currentTimeMillis();
		logger.info("处理花费时间:"+(end-start));
		logger.info("--------------------end 回款结束 -----------------------"+"\n\n\n");
	}

	/**
	 * 替换已回款的基础资产(应收账款)
	 */
	@RequestMapping("/replace")
	public void replaceBaseAssets(){
		long start = System.currentTimeMillis();
		supplyBaseAssets();
	
		logger.info("--------------------start 替换资产包中已回款的基础资产开始 -----------------------");
		//资产包中已回款需要被替换的基础资产
		List<PeAccountsReceivable> havePayedList = InvoiceService.selectHavePayed();
		//可以用来替换的基础资产
		List<PeAccountsReceivable> couldReplaceList = InvoiceService.selectCouldReplace();

		for(PeAccountsReceivable havePayed : havePayedList){
			logger.info("------------- "+ havePayed.toString());
			    AssetPackage ap = assetsService.queryAssetBaseAmount(havePayed.getAid());
			    //基础资产总金额小于预警规模，需要补充基础资产
                if(ap.getPackageAmount().subtract(ap.getWarnScale()).subtract(havePayed.getBillAmount()).doubleValue() < 0 ){
					BigDecimal amount = havePayed.getBillAmount();
					BigDecimal couldBalance = new BigDecimal(0);
					Iterator<PeAccountsReceivable> itr = couldReplaceList.iterator();
					StringBuffer baseAssetIds = new StringBuffer();
					while(itr.hasNext()){
						if(ap.getPackageAmount().subtract(ap.getWarnScale()).subtract(havePayed.getBillAmount()).add(couldBalance).doubleValue() < 0 ) {
							PeAccountsReceivable couldReplace = itr.next();
							couldBalance = couldBalance.add(couldReplace.getBillBalance());
							baseAssetIds.append("," + couldReplace.getId());
							if (amount.subtract(couldReplace.getBillBalance()).doubleValue() > 0) {
								InvoiceService.toReplace(couldReplace.getId(), havePayed.getAid());
								amount = amount.subtract(couldReplace.getBillBalance());
								itr.remove();
							} else if (amount.subtract(couldReplace.getBillBalance()).doubleValue() == 0) {
								InvoiceService.toReplace(couldReplace.getId(), havePayed.getAid());
								InvoiceService.beReplaced(havePayed.getId());
								itr.remove();
								break;
							} else {
								InvoiceService.toReplace(couldReplace.getId(), havePayed.getAid());
								InvoiceService.beReplaced(havePayed.getId());
								itr.remove();
								break;
							}
						}
					}
					if(!"".equals(baseAssetIds.toString())){
						//保存资产包中基础资产自动被替换记录
						assetsService.saveAssetChangeHisBatch(String.valueOf(havePayed.getAid()),String.valueOf(havePayed.getId()),"4");
						//保存资产包中基础资产自动替换记录
						assetsService.saveAssetChangeHisBatch(String.valueOf(havePayed.getAid()),baseAssetIds.substring(1),"5");

					}
				}else{
					//基础资产总金额大于等于预警规模，直接移除基础资产
					InvoiceService.beReplaced(havePayed.getId());

					//保存资产包中基础资产自动被替换记录
					if("2".equals(havePayed.getStatus()) || "5".equals(havePayed.getStatus())){
						//已收或逾期后回收状态的资产记录为9"已回款移除"
						assetsService.saveAssetChangeHisBatch(String.valueOf(havePayed.getAid()),String.valueOf(havePayed.getId()),"9");
					}

				}
		}
		long end = System.currentTimeMillis();
		logger.info("处理花费时间:"+(end-start));
		logger.info("--------------------end 替换资产包中已回款基础资产结束 -----------------------");
	}

	//兑付基础资产
	private BigDecimal handleBaseAssets(BigDecimal backpayBalance,PeBackpaymentRecord backpayRecord){
		List<PeAccountsReceivable>  peAccountsReceivableList = InvoiceService.selectAssetByDebtor(backpayRecord.getReceivableDebtor(),backpayRecord.getOriginalHolder());
		//回款处理
		return handle(backpayBalance,peAccountsReceivableList,backpayRecord);
	}

	private BigDecimal handle(BigDecimal backpayBalance, List<PeAccountsReceivable> peAccountsReceivableList,PeBackpaymentRecord backpayRecord){
		for(PeAccountsReceivable peAccountsReceivable:peAccountsReceivableList){
			logger.info("id= "+peAccountsReceivable.getId()+" "+peAccountsReceivable.getDebtorName() );
			BigDecimal paybackAmount = new BigDecimal(0);
			if(backpayBalance.subtract(peAccountsReceivable.getBillBalance()).doubleValue() > 0){
				BackPaymentService.partialMatch(backpayRecord.getId(),peAccountsReceivable.getBillBalance());
				InvoiceService.perfectPayment(peAccountsReceivable.getId(),backpayRecord.getId());
				paybackAmount = peAccountsReceivable.getBillBalance();
				backpayBalance = backpayBalance.subtract(peAccountsReceivable.getBillBalance());
				//记录回款的资金变动记录
				assetsService.saveFundChangeHisSelective(peAccountsReceivable,backpayRecord,paybackAmount,"1");

			}else if(backpayBalance.subtract(peAccountsReceivable.getBillBalance()).doubleValue() == 0){
				BackPaymentService.perfectMatch(backpayRecord.getId());
				InvoiceService.perfectPayment(peAccountsReceivable.getId(),backpayRecord.getId());
				paybackAmount = peAccountsReceivable.getBillBalance();
				backpayBalance = backpayBalance.subtract(peAccountsReceivable.getBillBalance());
				//记录回款的资金变动记录
				assetsService.saveFundChangeHisSelective(peAccountsReceivable,backpayRecord,paybackAmount,"1");
				break;
			}else{
				BackPaymentService.perfectMatch(backpayRecord.getId());
				InvoiceService.partialPayment(peAccountsReceivable.getId(), backpayBalance,backpayRecord.getId());
				paybackAmount = backpayBalance;
				backpayBalance = backpayBalance.subtract(peAccountsReceivable.getBillBalance());
				//记录回款的资金变动记录
				assetsService.saveFundChangeHisSelective(peAccountsReceivable,backpayRecord,paybackAmount,"1");
				break;
			}

		}
		return backpayBalance;
	}

	//检测基础资产规模小于预警规模的资产包，补充基础资产
	@RequestMapping("/supply")
    private void supplyBaseAssets(){
		logger.info("--------------------start 检测基础资产规模小于预警规模的资产包，补充基础资产开始 -----------------------");

		//查询未到兑付状态且基础资产规模小于预警规模的资产包列表
		List<AssetPackage> assetPackageList = assetsService.queryAssetPackageList();
		for(AssetPackage assetPackage : assetPackageList){
			BigDecimal packageAmount = assetPackage.getPackageAmount();
			if(assetPackage.getWarnScale().subtract(packageAmount).doubleValue() > 0){
				//可以用来替换的基础资产
				List<PeAccountsReceivable> couldReplaceList = InvoiceService.selectCouldReplace();
				StringBuffer baseAssetIds = new StringBuffer();
				for(PeAccountsReceivable par : couldReplaceList){
					if(assetPackage.getWarnScale().subtract(packageAmount).doubleValue() > 0){
						//补充基础资产
						InvoiceService.toReplace(par.getId(),Integer.parseInt(assetPackage.getId()));
						packageAmount = packageAmount.add(par.getBillBalance());
						baseAssetIds.append(","+par.getId());
					}
				}

				//保存资产包中基础资产自动补充记录
                if(!"".equals(baseAssetIds.toString())){
                    assetsService.saveAssetChangeHisBatch(assetPackage.getId(),baseAssetIds.substring(1),"6");
				}
			}

			if(assetPackage.getWarnScale().subtract(packageAmount).doubleValue() <= 0){
				//资产状态由警告修改为正常
				assetsService.normalAssetPackage(Integer.parseInt(assetPackage.getId()));
			}
		}
		logger.info("--------------------start 检测基础资产规模小于预警规模的资产包，补充基础资产结束 -----------------------");
	}

	/**
	 * 逾期
	 */
	@RequestMapping("/overdue")
	public  void overdue() {
		/**
		 * 资产包中逾期的基础资产
		 */
		List<PeAccountsReceivable> parList = InvoiceService.selectOverdueBaseAssets();
		for(PeAccountsReceivable par: parList){
			//逾期的基础资产将状态改为“逾期”
			InvoiceService.overdue(par.getId());
			if(!"2".equals(par.getWarnStatus())){
				InvoiceService.overdueToRemove(par.getId());
				int count = assetsService.selectHaveSaveRecord(par.getAid());
				if(count == 0){
					//保存资产包中基础资产逾期记录
					/*assetsService.saveAssetChangeHisBatch(par.getAid().toString(),par.getId().toString(),"8");*/

					//逾期状态的资产记录为11“逾期移除”
					assetsService.saveAssetChangeHisBatch(par.getAid().toString(),par.getId().toString(),"11");
				}
			}

		}
	}

	/**
	 * 到资产包“预计到期日”，自动将资产warn_status更新为“兑付”状态2
	 */
	@RequestMapping("/readyToExpire")
	public  void readyToExpire() {
		logger.info("--------------------start 准备到期开始 -----------------------");

        assetsService.expiredAssetPackage();

		logger.info("--------------------start 准备到期结束 -----------------------");

	}

}
