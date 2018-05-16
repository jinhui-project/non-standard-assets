package com.jinhui.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Row;

import com.jinhui.model.AssetsDetialInfo;

public class ExcelToAssetsDetialInfoImpl implements  ExcelRowToObj<AssetsDetialInfo>{

	@Override
	public AssetsDetialInfo excelRowToObj(Row row) throws ParseException {
		AssetsDetialInfo assetsDetialInfo = new AssetsDetialInfo();
		assetsDetialInfo.setName(ExcelImportUtil2.getCellValue(row.getCell(0)));
		return assetsDetialInfo;
	}
	
	@Override
	public AssetsDetialInfo excelRowToObjAbs(Row row) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		DecimalFormat f = new DecimalFormat("0,000.00");	    
		AssetsDetialInfo assetsDetialInfo = new AssetsDetialInfo();
		assetsDetialInfo.setName(ExcelImportUtil2.getCellValue(row.getCell(0)));
		assetsDetialInfo.setAssetType(Integer.parseInt(ExcelImportUtil2.getCellValue(row.getCell(1))));
		//assetsDetialInfo.setIssueDate(sdf.parse(ExcelImportUtil2.getCellValue(row.getCell(2))));
		assetsDetialInfo.setBorrower(ExcelImportUtil2.getCellValue(row.getCell(3)));
		assetsDetialInfo.setAssetMgr(ExcelImportUtil2.getCellValue(row.getCell(4)));
		assetsDetialInfo.setSetTrench(Integer.parseInt(ExcelImportUtil2.getCellValue(row.getCell(5))));
		assetsDetialInfo.setSeniorPercent(ExcelImportUtil2.getCellValue(row.getCell(6)));
		assetsDetialInfo.setSeniorRating(Integer.parseInt(ExcelImportUtil2.getCellValue(row.getCell(7))));
		
		Double dscale = Double.parseDouble(f.parseObject(ExcelImportUtil2.getCellValue(row.getCell(8))).toString());
        BigDecimal bigscale = new BigDecimal(dscale);
        assetsDetialInfo.setScale(bigscale);
        
        assetsDetialInfo.setTenor(Integer.parseInt(ExcelImportUtil2.getCellValue(row.getCell(9))));
        assetsDetialInfo.setAssetSubType(Integer.parseInt(ExcelImportUtil2.getCellValue(row.getCell(10))));
		return assetsDetialInfo;
	}
  
}
