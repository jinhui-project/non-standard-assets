package com.jinhui.mapper.upload;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinhui.model.Fileinfos;

public interface FileinfosMapper {

	List<Fileinfos>  queryPageList(Fileinfos fileinfos);
	
	int queryPageCount(Fileinfos fileinfos);
	
	Long saveFileinfosInfo(@Param("record")Fileinfos fileinfos);
	
	List<Fileinfos> queryList(@Param("list")List<Long> list);
	
	Fileinfos queryEntity(@Param("id")Long id);
	
	void delId(@Param("id")Long id);
	
	void updateNotNullFileinfos(Fileinfos fileinfos);
	
	
	List<Fileinfos>  queryTemplateFilePage(Fileinfos fileinfos);
	
	int queryTemplateFilePageCount(Fileinfos fileinfos);
}
