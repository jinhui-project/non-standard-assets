package com.jinhui.service.upLoad.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhui.mapper.upload.FileinfosMapper;
import com.jinhui.model.Fileinfos;
import com.jinhui.service.upLoad.FileinfosService;
/**
 * <p>Title:FileinfosServiceImpl</p>
 * <p>Description:文件信息表服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 金汇金融有限公司</p>
 * @author 谷一帅- 75423426@qq.com
 * @version v1.0 2016-12-20
 */
@Service
public class FileinfosServiceImpl implements FileinfosService  {
	@Autowired
	private FileinfosMapper fileinfosMapper;

	public Long saveFileinfosInfo(Fileinfos fileinfos) throws Exception {	
		return fileinfosMapper.saveFileinfosInfo(fileinfos);
	}
	
	public List<Fileinfos> queryPageList(Fileinfos fileinfos,int pageIndex, int pageSize){	
		int begin = pageIndex * pageSize;
		fileinfos.setOffset(begin);
		fileinfos.setLimit(pageSize);
		return fileinfosMapper.queryPageList(fileinfos);
	}
	public int queryPageCount(Fileinfos fileinfos) {	
		return fileinfosMapper.queryPageCount(fileinfos);
	}
	
	public Fileinfos queryFileinfos(long fid){
		return fileinfosMapper.queryEntity(fid);
	}
	public void delFileinfosId(Long id){
		fileinfosMapper.delId(id);
	}


	public void updateFileinfos(Fileinfos fileinfos) throws Exception {
		fileinfosMapper.updateNotNullFileinfos(fileinfos);
	}

	@Override
	public List<Fileinfos> queryList(List<Long> list) {
		return fileinfosMapper.queryList(list);
	}
	
	
	
	public List<Fileinfos> queryTemplateFilePage(Fileinfos fileinfos,int pageIndex, int pageSize){	
		int begin = pageIndex * pageSize;
		fileinfos.setOffset(begin);
		fileinfos.setLimit(pageSize);
		return fileinfosMapper.queryTemplateFilePage(fileinfos);
	}
	
	public int queryTemplateFilePageCount(Fileinfos fileinfos) {	
		return fileinfosMapper.queryTemplateFilePageCount(fileinfos);
	}

}