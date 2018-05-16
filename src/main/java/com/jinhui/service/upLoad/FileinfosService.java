package com.jinhui.service.upLoad;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinhui.model.AgreementTemplate;
import com.jinhui.model.Fileinfos;
/**
 * <p>Title:FileinfosService</p>
 * <p>Description:文件信息表服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 金汇金融有限公司</p>
 * @author 谷一帅- 75423426@qq.com
 * @version v1.0 2016-12-20
 */

public interface FileinfosService {
	
	
	Long saveFileinfosInfo(Fileinfos fileinfos)throws Exception;
	
	List<Fileinfos>  queryPageList(Fileinfos fileinfos,int pageIndex, int pageSize);
	
	int queryPageCount(Fileinfos fileinfos) ;
	
	Fileinfos queryFileinfos(long fid);
	
	void delFileinfosId(Long id);

	void updateFileinfos(Fileinfos fileinfos)throws Exception;
	
	
	List<Fileinfos> queryList(List<Long> list);
	
	
	List<Fileinfos>  queryTemplateFilePage(Fileinfos fileinfos,int pageIndex, int pageSize);
	int queryTemplateFilePageCount(Fileinfos fileinfos);
	
}