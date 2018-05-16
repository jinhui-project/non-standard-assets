package com.jinhui.util;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;



public class ExcelImportUtil2<T> {

	public static final DecimalFormat df = new DecimalFormat("0");
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public void parseExcel(CommonsMultipartFile excelFile, HttpServletRequest request, List<T> datas,
			ExcelRowToObj<T> rowToObj) {
		String updatePath = request.getServletContext().getRealPath("upload");
		String fileName = excelFile.getFileItem().getName();

		File file = new File(updatePath, fileName);
		if (!file.exists()) {
			file.mkdirs();
		}
		FileInputStream fileInputStream = null;
		try {
			// 保存临时文件
			excelFile.transferTo(file);
			Workbook workbook = null;

			try {
				fileInputStream = new FileInputStream(file);
				workbook = new HSSFWorkbook(fileInputStream);
			} catch (Exception e) {
				fileInputStream = new FileInputStream(file);
				workbook = new XSSFWorkbook(fileInputStream);
			}
			//资产类型1城投类,2上市公司资产,3供应链资产,4私募ABS产品,5收益权转让产品
			//读取sheet			
			//获取sheet的个数
			for (int s = 0; s < workbook.getNumberOfSheets();s ++){
				Sheet sheet = workbook.getSheetAt(s);
				String assetType = sheet.getSheetName();//获取sheet名称知道是哪类资产类型
				if(assetType.equals(AssetsConstant.stateInvestmentAsset)){  //城投类
					// 读取数据,从第二行开始，第一列项目名称，第二列资产类型
					for (int i = 1; i <= sheet.getLastRowNum(); i++) {
						if (!StringUtils.isEmpty(getCellValue(sheet.getRow(i).getCell(0)))) {
							T t= (T) rowToObj.excelRowToObj(sheet.getRow(i));
							datas.add(t);
						}
					}
				}
				if(assetType.equals(AssetsConstant.ipoAsset)){  //上市公司资产
					// 读取数据,从第二行开始，第一列项目名称，第二列资产类型
					for (int i = 1; i <= sheet.getLastRowNum(); i++) {
						if (!StringUtils.isEmpty(getCellValue(sheet.getRow(i).getCell(0)))) {
							T t= (T) rowToObj.excelRowToObj(sheet.getRow(i));
							datas.add(t);
						}
					}
				}
				if(assetType.equals(AssetsConstant.supplyChainAsset)){  //供应链资产
					// 读取数据,从第二行开始，第一列项目名称，第二列资产类型
					for (int i = 1; i <= sheet.getLastRowNum(); i++) {
						if (!StringUtils.isEmpty(getCellValue(sheet.getRow(i).getCell(0)))) {
							T t= (T) rowToObj.excelRowToObj(sheet.getRow(i));
							datas.add(t);
						}
					}
				}
				if(assetType.equals(AssetsConstant.peAbsAsset)){  //私募ABS产品
					// 读取数据,从第二行开始，第一列项目名称，第二列资产类型
					for (int i = 1; i <= sheet.getLastRowNum(); i++) {
						if (!StringUtils.isEmpty(getCellValue(sheet.getRow(i).getCell(0)))) {
							T t= (T) rowToObj.excelRowToObjAbs(sheet.getRow(i));
							datas.add(t);
						}
					}
				}
				if(assetType.equals(AssetsConstant.usufructTransferAsset)){  //收益权转让产品  
					// 读取数据,从第二行开始，第一列项目名称，第二列资产类型
					for (int i = 1; i <= sheet.getLastRowNum(); i++) {
						if (!StringUtils.isEmpty(getCellValue(sheet.getRow(i).getCell(0)))) {
							T t= (T) rowToObj.excelRowToObj(sheet.getRow(i));
							datas.add(t);
						}
					}
				}
			}
			
						
			if (null != fileInputStream) {
				fileInputStream.close();
			}
		} catch (Exception e) {
			//throw new MmoException(Status.SERVICE_FAIL, "excel文件错误", e);
		} finally {
			file.delete();
		}
	}


	public static String getCellValue(Cell cell) {

		Object value = null;
		if (null == cell) {
			return null;
		}
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			value = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				value = sdf.format(cell.getDateCellValue());
			} else {
				value = df.format(cell.getNumericCellValue());
			}
			break;
		default:
			value = cell.getStringCellValue();
			break;
		}
		return String.valueOf(value);
	}
}
