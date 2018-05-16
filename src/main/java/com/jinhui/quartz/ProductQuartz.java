package com.jinhui.quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.jinhui.model.Products;
import com.jinhui.service.products.ProductsService;
import com.jinhui.util.FormatReturn;
/**
 * 产品相关定时任务
 * @author wsc
 *
 */
@Controller
public class ProductQuartz {
	private static Logger logger = LoggerFactory.getLogger(ProductQuartz.class);
	@Autowired
	private ProductsService productsService;
	
	/**
	 * 产品起息日当天自动将产品状态更新为“收益中”
	 */
	public void statusToBenifit(){
		logger.info("--------------------start 产品起息日当天自动将产品状态更新为“收益中” -----------------------");
		List<Products> productList = productsService.getProductsForIncomeQuartz();
		logger.info("产品总个数： ---------------  " + productList.size() + "  --------------------");
		for(int i=0;i<productList.size();i++){
			logger.info("产品简称： " + productList.get(i).getId()+"__"+productList.get(i).getShortName()+
					"__"+productList.get(i).getStatus()+"__"+productList.get(i).getBeginIncomeDate());
			//当前产品已到起息日
			if(isOverDate(transferDate(productList.get(i).getBeginIncomeDate()))){
				Products product = new Products();
				product.setId(productList.get(i).getId());
				product.setStatus(3);  //收益中
				int flag = productsService.updateProductStatusForQuartz(product);
			    logger.info("状态更新结果" + JSON.toJSONString(FormatReturn.format(flag)));	
			}
		}
		logger.info("--------------------end 产品起息日当天自动将产品状态更新为“收益中” -----------------------"+"\n\n\n");
	}
	
	
	/**
	 * 产品到期日当天自动将产品状态更新为“已兑付”
	 */
	public void statusToAlreadyHonor(){
		logger.info("--------------------start 产品到期日当天自动将产品状态更新为“已兑付” -----------------------");
		List<Products> productList = productsService.getProductsForOverQuartz();
		logger.info("产品总个数： ---------------  " + productList.size() + "  --------------------");
		for(int i=0;i<productList.size();i++){
			logger.info("产品简称： " + productList.get(i).getId()+"__"+productList.get(i).getShortName()+
					"__"+productList.get(i).getStatus()+"__"+productList.get(i).getExpiredDate());
			//当前产品已到到期日
			if(isOverDate(transferDate(productList.get(i).getExpiredDate()))){
				Products product = new Products();
				product.setId(productList.get(i).getId());
				product.setStatus(4);  //已兑付
				int flag = productsService.updateProductStatusForQuartz(product);
			    logger.info("状态更新结果" + JSON.toJSONString(FormatReturn.format(flag)));	
			}
		}
		logger.info("--------------------end 产品到期日当天自动将产品状态更新为“已兑付” -----------------------"+"\n\n\n");
	}
	
	/**
	 * 销售结束日当天15:00自动将产品状态更新为“销售结束”
	 */
	public void statusToSaleOver(){
		logger.info("--------------------start 销售结束日当天15:00自动将产品状态更新为“销售结束”” -----------------------");
		List<Products> productList = productsService.getProductsForOpenQuartz();
		logger.info("产品总个数： ---------------  " + productList.size() + "  --------------------");
		for(int i=0;i<productList.size();i++){
			logger.info("产品简称： " + productList.get(i).getId()+"__"+productList.get(i).getShortName()+
					"__"+productList.get(i).getStatus()+"__"+productList.get(i).getEndSaleDate());
			//当前产品已到销售结束日
			if(isOverDate(transferDate(productList.get(i).getEndSaleDate()))){
				Products product = new Products();
				product.setId(productList.get(i).getId());
				product.setStatus(2);  //销售结束
				int flag = productsService.updateProductStatusForQuartz(product);
			    logger.info("状态更新结果" + JSON.toJSONString(FormatReturn.format(flag)));	
			}
		}
		logger.info("--------------------end 销售结束日当天15:00自动将产品状态更新为“销售结束” -----------------------"+"\n\n\n");
	}
	
	/**
	 * 当天是否超过inDate时间
	 * @param inDate
	 * @return
	 */
	public static boolean isOverDate(int inDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
		String strNow = sdf.format(new Date());
		if(Integer.parseInt(strNow) >= inDate){
			return true;
		}else{
			return false;
		}
	}
	
	public static int transferDate(String inDate){
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sft = new SimpleDateFormat("yyyyMMdd");
		Date  date = null;
		try {
			date = sf.parse(inDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Integer.parseInt(sft.format(date));
	}
	
}
