package com.jinhui.util;

import java.text.ParseException;

import org.apache.poi.ss.usermodel.Row;

import com.jinhui.model.AssetsDetialInfo;

public interface ExcelRowToObj<T> {

	public T excelRowToObj(Row row)throws ParseException;
	
	public T excelRowToObjAbs(Row row)throws ParseException;
}
