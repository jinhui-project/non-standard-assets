package com.jinhui.service.organization.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jinhui.mapper.organization.OrganizationMapper;
import com.jinhui.mapper.upload.FileinfosMapper;
import com.jinhui.model.Fileinfos;
import com.jinhui.model.Organization;
import com.jinhui.service.organization.OrganizationService;
import com.jinhui.service.upLoad.FileinfosService;
import com.jinhui.util.UpLoadUtil;
import com.jinhui.util.UtilTool;

/**
 * 机构信息Service
 * 
 * @author jinhui
 *
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
	@Autowired
	private OrganizationMapper organizationMapper;

	@Autowired
	private FileinfosMapper fileinfosMapper;

	@Autowired
	private FileinfosService fileinfosService;

	@Value("${uploadDelPath}")
	private String uploadDelPath;

	Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);
	/**
	 * 文件开头地址
	 */
	@Value("${uploadPath}")
	private String uploadPath;

	/**
	 * 机构信息分页
	 */
	public List<Organization> queryOrganizationPage(
			Organization organizationint, int pageIndex, int pageSize) {
		List<Organization> listReturn = new ArrayList<Organization>();
		int begin = pageIndex * pageSize;
		organizationint.setOffset(begin);
		organizationint.setLimit(pageSize);
		List<Organization> list = organizationMapper
				.queryOrganizationPage(organizationint);
		if (null != list && list.size() > 0) {
			for (Organization o : list) {
				List<Fileinfos> busiLicenceFileList = new ArrayList<Fileinfos>();
				List<Fileinfos> certificateFileList = new ArrayList<Fileinfos>();
				if (null != o.getBusiLicence()
						&& !"".equals(o.getBusiLicence())) {
					String[] arrBusiLicence = o.getBusiLicence().split(",");
					for (String tempBusiLicence : arrBusiLicence) {
						Fileinfos b = fileinfosMapper.queryEntity(Long
								.valueOf(tempBusiLicence));
						if (null != b) {
							b.setUploadTimeStr(UtilTool.stampToDate(b
									.getUploadTime()));
							b.setEditTimeStr(UtilTool.stampToDate(b
									.getEditTime()));
						}
						busiLicenceFileList.add(b);
					}
				}
				o.setBusiLicenceFile(busiLicenceFileList);

				if (null != o.getCertificate()
						&& !"".equals(o.getCertificate())) {
					String[] arrCertificate = o.getCertificate().split(",");
					for (String tempCertificate : arrCertificate) {
						Fileinfos c = fileinfosMapper.queryEntity(Long
								.valueOf(tempCertificate));
						if (null != c) {
							c.setUploadTimeStr(UtilTool.stampToDate(c
									.getUploadTime()));
							c.setEditTimeStr(UtilTool.stampToDate(c
									.getEditTime()));
						}
						certificateFileList.add(c);
					}
				}
				listReturn.add(o);
			}

		}
		return listReturn;
	}

	/**
	 * 查询总数
	 * 
	 * @param organization
	 * @return
	 * @throws Exception
	 */
	public Integer queryOrganizationCount(Organization organization) {
		return organizationMapper.queryOrganizationCount(organization);

	}

	/**
	 * 机构信息详情
	 * 
	 */
	public Organization queryOrganizationDetail(Integer gid) {
		Organization o = organizationMapper.queryOrganizationDetail(gid);
		List<Fileinfos> busiLicenceFileList = new ArrayList<Fileinfos>();
		List<Fileinfos> certificateFileList = new ArrayList<Fileinfos>();
		List<Fileinfos> platBussReportFileList = new ArrayList<Fileinfos>();
		if (null != o.getBusiLicence() && !"".equals(o.getBusiLicence())) {
			String[] arrBusiLicence = o.getBusiLicence().split(",");
			for (String tempBusiLicence : arrBusiLicence) {
				Fileinfos b = fileinfosMapper.queryEntity(Long
						.valueOf(tempBusiLicence));
				if (null != b) {
					b.setUploadTimeStr(UtilTool.stampToDate(b.getUploadTime()));
					b.setEditTimeStr(UtilTool.stampToDate(b.getEditTime()));
				}
				busiLicenceFileList.add(b);
			}
		}
		o.setBusiLicenceFile(busiLicenceFileList);

		if (null != o.getCertificate() && !"".equals(o.getCertificate())) {
			String[] arrCertificate = o.getCertificate().split(",");
			for (String tempCertificate : arrCertificate) {
				Fileinfos c = fileinfosMapper.queryEntity(Long
						.valueOf(tempCertificate));
				if (null != c) {
					c.setUploadTimeStr(UtilTool.stampToDate(c.getUploadTime()));
					c.setEditTimeStr(UtilTool.stampToDate(c.getEditTime()));
				}
				certificateFileList.add(c);
			}
		}
		o.setCertificateFile(certificateFileList);
		if(null != o.getPlatBussReport() && !"".equals(o.getPlatBussReport())){
			String[] platBussReportFileArr = o.getPlatBussReport().split(",");
			for(String platBussReportFiletemp :platBussReportFileArr){
				Fileinfos c = fileinfosMapper.queryEntity(Long.parseLong(platBussReportFiletemp));
				if (null != c) {
					c.setUploadTimeStr(UtilTool.stampToDate(c.getUploadTime()));
					c.setEditTimeStr(UtilTool.stampToDate(c.getEditTime()));
				}
				platBussReportFileList.add(c);
			}
			o.setPlatBussReportFile(platBussReportFileList);
		}
		
		
		return o;
	}

	/**
	 * 机构信息---添加
	 * 
	 * @param organization
	 * @param busiLicence
	 *            营业执照路径
	 * @param certificate
	 *            资质附件路径
	 * @throws Exception
	 */
	// public void insert(Organization organization,
	// List<MultipartFile> busiLicence, List<MultipartFile> certificate,
	// HttpServletRequest httpsRequest) throws Exception {
	public void insert(Organization organization) throws Exception {
		String powerStr = "000";
		// 备案机构
		String recordType = "0";
		// 资产方类型
		String assetsType = "0";
		// 资金方类型：0未指定1三方2互金平台3私募4银行 */
		String fundType = "0";

		if (null != organization.getRecordType()
				&& organization.getRecordType() != 0) {
			recordType = "1";
		}
		if (null != organization.getAssetsType()
				&& organization.getAssetsType() != 0) {
			assetsType = "1";
		}
		if (null != organization.getFundType()
				&& organization.getFundType() != 0) {
			fundType = "1";
		}
		powerStr = recordType + assetsType + fundType;
		organization.setPower(powerStr);
		organization.setCreateTime(UtilTool.dateConLon());

		// StringBuffer sbBusiLicenceId = new StringBuffer();
		// StringBuffer sbCertificateId = new StringBuffer();
		// for (MultipartFile mf : busiLicence) {
		// Map<String, String> busiLicenceMap = new HashMap<String, String>();
		// Fileinfos busiLicenceFileinfos = new Fileinfos();
		// UpLoadUtil utBusiLicence = new UpLoadUtil();
		// busiLicenceMap = utBusiLicence.upLoadMap(mf,
		// ConstantEntity.FILE_IMG, uploadPath);
		// busiLicenceFileinfos.setFilePath(busiLicenceMap.get("file_path"));
		// busiLicenceFileinfos.setFileName(busiLicenceMap.get("file_name"));
		// busiLicenceFileinfos.setFileType(busiLicenceMap.get("file_type"));
		// busiLicenceFileinfos.setUploadTime(UtilTool.dateConLon());
		//
		// fileinfosMapper.saveFileinfosInfo(busiLicenceFileinfos);
		// sbBusiLicenceId.append(busiLicenceFileinfos.getId()).append(",");
		// }
		// organization.setBusiLicence(sbBusiLicenceId + "");
		//
		// for (MultipartFile mf2 : certificate) {
		// Map<String, String> certificateMap = new HashMap<String, String>();
		// Fileinfos certificateFileinfos = new Fileinfos();
		// UpLoadUtil utCertificate = new UpLoadUtil();
		// certificateMap = utCertificate.upLoadMap(mf2,
		// ConstantEntity.FILE_IMG, uploadPath);
		// certificateFileinfos.setFilePath(certificateMap.get("file_path"));
		// certificateFileinfos.setFileName(certificateMap.get("file_name"));
		// certificateFileinfos.setFileType(certificateMap.get("file_type"));
		// certificateFileinfos.setUploadTime(UtilTool.dateConLon());
		//
		// fileinfosMapper.saveFileinfosInfo(certificateFileinfos);
		// sbCertificateId.append(certificateFileinfos.getId()).append(",");
		// }
		//
		// organization.setCertificate(sbCertificateId + "");

		organizationMapper.insert(organization);
	}

	public List<Organization> queryOrganizationAll(Organization organizationint) {
		List<Organization> listReturn = new ArrayList<Organization>();
		List<Organization> list = organizationMapper
				.queryOrganizationAll(organizationint);
		if (null != list && list.size() > 0) {
			for (Organization o : list) {
				List<Fileinfos> busiLicenceFileList = new ArrayList<Fileinfos>();
				List<Fileinfos> certificateFileList = new ArrayList<Fileinfos>();
				if (null != o.getBusiLicence()
						&& !"".equals(o.getBusiLicence())) {
					String[] arrBusiLicence = o.getBusiLicence().split(",");
					for (String tempBusiLicence : arrBusiLicence) {
						Fileinfos b = fileinfosMapper.queryEntity(Long
								.valueOf(tempBusiLicence));
						if (null != b) {
							b.setUploadTimeStr(UtilTool.stampToDate(b
									.getUploadTime()));
							b.setEditTimeStr(UtilTool.stampToDate(b
									.getEditTime()));
						}
						busiLicenceFileList.add(b);
					}
				}
				o.setBusiLicenceFile(busiLicenceFileList);

				if (null != o.getCertificate()
						&& !"".equals(o.getCertificate())) {
					String[] arrCertificate = o.getCertificate().split(",");
					for (String tempCertificate : arrCertificate) {
						Fileinfos c = fileinfosMapper.queryEntity(Long
								.valueOf(tempCertificate));
						if (null != c) {
							c.setUploadTimeStr(UtilTool.stampToDate(c
									.getUploadTime()));
							c.setEditTimeStr(UtilTool.stampToDate(c
									.getEditTime()));
						}
						certificateFileList.add(c);
					}
				}
				listReturn.add(o);
			}

		}
		return listReturn;
	}

	/**
	 * 更新
	 */
	// public void updateNotNullOrganization(Organization organization,
	// List<MultipartFile> busiLicence, List<MultipartFile> certificate)
	// throws Exception {

	public void updateNotNullOrganization(Organization organization)
			throws Exception {
		// Organization o =
		// organizationMapper.queryOrganizationDetail(Integer.valueOf(organization.getId()
		// + ""));
		//
		// StringBuffer sbBusiLicenceId = new StringBuffer();
		// StringBuffer sbCertificateId = new StringBuffer();
		//
		// for (MultipartFile mf : busiLicence) {
		// Map<String, String> busiLicenceMap = new HashMap<String, String>();
		// Fileinfos busiLicenceFileinfos = new Fileinfos();
		// UpLoadUtil utBusiLicence = new UpLoadUtil();
		// busiLicenceMap = utBusiLicence.upLoadMap(mf,ConstantEntity.FILE_IMG,
		// uploadPath);
		// busiLicenceFileinfos.setFilePath(busiLicenceMap.get("file_path"));
		// busiLicenceFileinfos.setFileName(busiLicenceMap.get("file_name"));
		// busiLicenceFileinfos.setFileType(busiLicenceMap.get("file_type"));
		// busiLicenceFileinfos.setUploadTime(UtilTool.dateConLon());
		// busiLicenceFileinfos.setEditTime(UtilTool.dateConLon());
		//
		// fileinfosMapper.saveFileinfosInfo(busiLicenceFileinfos);
		// sbBusiLicenceId.append(busiLicenceFileinfos.getId()).append(",");
		// }
		// organization.setBusiLicence(organization.getBusiLicence() + ","+
		// sbBusiLicenceId);
		//
		// String[] tempBL = organization.getBusiLicence().split(",");
		// String[] tempDel = o.getBusiLicence().split(",");
		// List<String> listBL = Arrays.asList(tempBL);// 需要保存的
		// List<String> listDelId = Arrays.asList(tempDel);// 数据库查询出来的
		// // 判断 需要删除的文件并删除
		// getUncontain(listBL, listDelId,"busiLicence");
		//
		// for (MultipartFile mf2 : certificate) {
		// Map<String, String> certificateMap = new HashMap<String, String>();
		// Fileinfos certificateFileinfos = new Fileinfos();
		// UpLoadUtil utCertificate = new UpLoadUtil();
		// certificateMap = utCertificate.upLoadMap(mf2,
		// ConstantEntity.FILE_IMG, uploadPath);
		// certificateFileinfos.setFilePath(certificateMap.get("file_path"));
		// certificateFileinfos.setFileName(certificateMap.get("file_name"));
		// certificateFileinfos.setFileType(certificateMap.get("file_type"));
		// certificateFileinfos.setUploadTime(UtilTool.dateConLon());
		// certificateFileinfos.setEditTime(UtilTool.dateConLon());
		// fileinfosMapper.saveFileinfosInfo(certificateFileinfos);
		// sbCertificateId.append(certificateFileinfos.getId()).append(",");
		// }
		// String[] tempCF = organization.getCertificate().split(",");
		// String[] tempDelCF = o.getCertificate().split(",");
		// List<String> listCF = Arrays.asList(tempCF);// 需要保存的
		// List<String> listDelIdCF = Arrays.asList(tempDelCF);// 数据库查询出来的
		// // 判断 需要删除的文件并删除
		// getUncontain(listCF, listDelIdCF,"certificate");
		//
		// organization.setCertificate(organization.getCertificate()+","+sbCertificateId);

		String powerStr = "";
		// 备案机构
		String recordType = "0";
		// 资产方类型
		String assetsType = "0";
		// 资金方类型：0未指定1三方2互金平台3私募4银行 */
		String fundType = "0";

		if (null != organization.getRecordType()
				&& organization.getRecordType() != 0) {
			recordType = "1";
		}
		if (null != organization.getAssetsType()
				&& organization.getAssetsType() != 0) {
			assetsType = "1";
		}
		if (null != organization.getFundType()
				&& organization.getFundType() != 0) {
			fundType = "1";
		}
		powerStr = recordType + assetsType + fundType;
		organization.setPower(powerStr);
		organization.setCreateTime(UtilTool.dateConLon());

		organizationMapper.updateNotNullOrganization(organization);
	}

	/**
	 * 
	 * @param list1
	 *            需要保存的
	 * @param list2
	 *            数据库查询出来的
	 */
	public void getUncontain(List<String> list1, List<String> list2, String str) {
		logger.info(str
				+ "======================================================【删除文件开始】");
		for (String delId : list2) {
			if (!list1.contains(delId)) {
				System.out.println("要删除文件的是id主键为：==>" + delId);
				logger.info("要删除文件的是id主键为：==>" + delId);
				Fileinfos f = fileinfosService.queryFileinfos(Long
						.valueOf(delId));
				if (null == f) {
					logger.info("文件不存在!id主键为：==>" + delId);
				} else {
					UpLoadUtil u = new UpLoadUtil();
					try {
						boolean flag = u.deleteUpLoad(f.getFilePath(), 0,
								uploadDelPath);
						if (flag) {
							fileinfosService
									.delFileinfosId(Long.valueOf(delId));
							logger.info("文件删除成功!");
						} else {
							logger.info("文件删除失败!");
						}

					} catch (Exception e) {
						logger.error("【文件删除】deleteFile" + e);

					}
				}
			}
		}
		logger.info(str
				+ "======================================================【删除文件结束】");
	}
}
