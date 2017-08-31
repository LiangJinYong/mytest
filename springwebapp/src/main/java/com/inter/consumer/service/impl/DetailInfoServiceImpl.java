package com.inter.consumer.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.inter.consumer.dao.DetailInfoDao;
import com.inter.consumer.service.DetailInfoService;

@Service
public class DetailInfoServiceImpl implements DetailInfoService {

	@Autowired
	private DetailInfoDao detailInfoDao;

	public void setDetailInfoDao(DetailInfoDao detailInfoDao) {
		this.detailInfoDao = detailInfoDao;
	}

	public Map<String, Object> detailInfo(HttpServletRequest request) {

		String sequence = request.getParameter("SEQUENCE");
		String generation = request.getParameter("GENERATION");
		String step = request.getParameter("STEP");

		String token = request.getHeader("token");

		int appUserCount = detailInfoDao.queryAppUserCount(token);

		Map<String, Object> result = new HashMap<String, Object>();

		if (appUserCount > 0) {
			try {
				Map<String, Object> detailInfo = detailInfoDao.queryDetailInfo(sequence, generation, step);

				List<Map<String, Object>> detailInfoList = new ArrayList<Map<String, Object>>();

				boolean expireFlag = true;
				
				Map<String, Object> tempMap = new HashMap<String, Object>();
				tempMap.put("title", "商品分类");
				
				String svcCd = (String) detailInfo.get("SVC_CD");
				String svcCd2 = (String) detailInfo.get("SVC_CD2");
				String svcCd3 = (String) detailInfo.get("SVC_CD3");
				
				String svcCdName = "";
				String svcCd2Name = "";
				String svcCd3Name = "";
				
				List<Map<String, Object>> svcCdNameList = detailInfoDao.querySvcCdName(svcCd);
				if (svcCdNameList.size() > 0) {
					svcCdName = (String) svcCdNameList.get(0).get("COMM_CD_VAL_NM");
				}
				
				List<Map<String, Object>> svcCd2NameList = detailInfoDao.querySvcCdName(svcCd2);
				if (svcCd2NameList.size() > 0) {
					svcCd2Name = (String) svcCd2NameList.get(0).get("COMM_CD_VAL_NM");
				}
				
				List<Map<String, Object>> svcCd3NameList = detailInfoDao.querySvcCdName(svcCd3);
				if (svcCd3NameList.size() > 0) {
					svcCd3Name = (String) svcCd3NameList.get(0).get("COMM_CD_VAL_NM");
				}
				
				tempMap.put("content",
						svcCdName + " -> " + svcCd2Name + " -> " + svcCd3Name);

				detailInfoList.add(tempMap);

				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "生产公司");
				tempMap.put("content", detailInfo.get("BIZ_NAME"));
				detailInfoList.add(tempMap);

				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "商品名称");
				tempMap.put("content", detailInfo.get("SVC_NM"));
				detailInfoList.add(tempMap);

				/*
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "国家和地区码");
				tempMap.put("content", detailInfo.get("NATION_CODE"));
				detailInfoList.add(tempMap);

				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "行政区域码");
				tempMap.put("content", detailInfo.get("AREA_CODE_LU") + "->" + detailInfo.get("AREA_CODE_DAO") + "->"
						+ detailInfo.get("AREA_CODE_JIE"));
				detailInfoList.add(tempMap);

				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "管理主体码");
				tempMap.put("content", detailInfo.get("MANAGER_CODE"));
				detailInfoList.add(tempMap);
				*/

				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "厂商代码");
				tempMap.put("content", detailInfo.get("FACTORY_CODE"));
				detailInfoList.add(tempMap);

				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "商品代码");
				tempMap.put("content", detailInfo.get("PRODUCT_NUM"));
				detailInfoList.add(tempMap);

				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "生产时间");
				tempMap.put("content", detailInfo.get("PRODUCT_MAKE_DATE"));
				detailInfoList.add(tempMap);

				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "净含量");
				tempMap.put("content", detailInfo.get("PRODUCT_WEIGHT"));
				detailInfoList.add(tempMap);

				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "单件代码 ");
				tempMap.put("content", detailInfo.get("SKU_CODE_SEQUENCE"));
				detailInfoList.add(tempMap);

				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "产地");
				tempMap.put("content", detailInfo.get("PRODUCT_ORIGIN"));
				detailInfoList.add(tempMap);

				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "生产品许可证编号");
				tempMap.put("content", detailInfo.get("LOT_NO"));
				detailInfoList.add(tempMap);
				
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "产品执行标准号");
				tempMap.put("content", detailInfo.get("PRODUCT_STANDARD_NUM"));
				detailInfoList.add(tempMap);

				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "检测编号");
				tempMap.put("content", detailInfo.get("PRODUCT_TRICKY_NUM"));
				detailInfoList.add(tempMap);

				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "检测时间");
				tempMap.put("content", detailInfo.get("PRODUCT_TRICKY_DTM"));
				detailInfoList.add(tempMap);

				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "检测者姓名");
				tempMap.put("content", detailInfo.get("PRODUCT_INSPECTOR_INFO"));
				detailInfoList.add(tempMap);

				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "出库（厂）时间");
				tempMap.put("content", detailInfo.get("PRODUCT_RELEASE_DTM"));
				detailInfoList.add(tempMap);

				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "产品保质日期");
				tempMap.put("content", detailInfo.get("GUARANTEE_DT"));
				detailInfoList.add(tempMap);
				
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "保质期剩余时间");
				
				DateFormat df = new SimpleDateFormat("yyyyMMdd");
				df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));

				String codeExpireDateStr = (String) detailInfo.get("CODE_EXPIRE_DT");
				Date codeExpireDate = df.parse(codeExpireDateStr);
				
				Date today = new Date();
				
				String expireMsg;
				if (codeExpireDate.after(today)) {
					int diff = (int) ((codeExpireDate.getTime() - today.getTime()) / (24*60*60*1000));
					expireMsg = diff + "日";
				} else {
					expireFlag = false;
					expireMsg = "已过保质期";	
				}
				
				tempMap.put("content", expireMsg);
				detailInfoList.add(tempMap);

				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "用途");
				tempMap.put("content", detailInfo.get("PURPOSE"));
				detailInfoList.add(tempMap);

				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "公司网址");
				tempMap.put("content", detailInfo.get("HOMEPAGE_ADDR"));
				detailInfoList.add(tempMap);

				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "公司邮箱");
				tempMap.put("content", detailInfo.get("CORP_EMAIL"));
				detailInfoList.add(tempMap);

				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "公司电话");
				tempMap.put("content", detailInfo.get("CORP_PHONE"));
				detailInfoList.add(tempMap);

				for (int i = 1; i <= 20; i++) {
					String productVal = (String) detailInfo.get("PRODUCT_VAL" + i);
					if (detailInfo.get("PRODUCT_VAL" + i) != null && !"".equals(productVal.trim())) {
						String[] productValArr = productVal.split("^");
						
						tempMap = new HashMap<String, Object>();
						tempMap.put("title", productValArr[0]);
						tempMap.put("content", productValArr[1]);
						
						detailInfoList.add(tempMap);
					}
				}
				result.put("DETAIL", detailInfoList);
				
				result.put("IS_DST", (Integer)detailInfo.get("IS_DST") == 1);
				
				result.put("URL", detailInfo.get("PRODUCT_URL"));
				
				result.put("EXPIRE_FLAG", expireFlag);
				
				result.put("result_code", 200);
				
			} catch (EmptyResultDataAccessException e) {
				result.put("result_code", 404);
			} catch (ParseException e) {
				result.put("result_code", 404);
			}

		} else {
			result.put("result_code", 403);
		}

		return result;
	}

}
