package com.inter.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.inter.dao.DetailInfoDao;
import com.inter.service.DetailInfoService;

@Service
public class DetailInfoServiceImpl implements DetailInfoService {

	@Autowired
	private DetailInfoDao detailInfoDao;

	public void setDetailInfoDao(DetailInfoDao detailInfoDao) {
		this.detailInfoDao = detailInfoDao;
	}

	/*
	 * { result_code : int IS_DST : bool URL : string DETAIL : [{title : string
	 * content : string }] }
	 */
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

				// 상품분류
				Map<String, Object> tempMap = new HashMap<String, Object>();
				tempMap.put("title", "商品分类");
				tempMap.put("content",
						detailInfo.get("SVC_CD") + "->" + detailInfo.get("SVC_CD2") + "->" + detailInfo.get("SVC_CD3"));

				detailInfoList.add(tempMap);

				// 생산 회사
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "生产公司");
				tempMap.put("content", detailInfo.get("BIZ_NAME"));
				detailInfoList.add(tempMap);

				// 상품 명칭
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "商品名称");
				tempMap.put("content", detailInfo.get("SVC_NM"));
				detailInfoList.add(tempMap);

				// 국가와 지역 번호
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "国家和地区码");
				tempMap.put("content", detailInfo.get("NATION_CODE"));
				detailInfoList.add(tempMap);

				// 행정구번호
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "行政区域码");
				tempMap.put("content", detailInfo.get("AREA_CODE_LU") + "->" + detailInfo.get("AREA_CODE_DAO") + "->"
						+ detailInfo.get("AREA_CODE_JIE"));
				detailInfoList.add(tempMap);

				// 관리 주요 번호
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "管理主体码");
				tempMap.put("content", detailInfo.get("MANAGER_CODE"));
				detailInfoList.add(tempMap);

				// 공장 코드 번호
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "厂商代码");
				tempMap.put("content", detailInfo.get("FACTORY_CODE"));
				detailInfoList.add(tempMap);

				// 상품 코드 번호 (SKU)
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "商品代码");
				tempMap.put("content", detailInfo.get("PRODUCT_NUM"));
				detailInfoList.add(tempMap);

				// 생산 시간
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "生产时间");
				tempMap.put("content", detailInfo.get("PRODUCT_MAKE_DATE"));
				detailInfoList.add(tempMap);

				// 증량
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "净含量");
				tempMap.put("content", detailInfo.get("PRODUCT_WEIGHT"));
				detailInfoList.add(tempMap);

				// 단품 번호
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "单件代码 ");
				tempMap.put("content", detailInfo.get("SKU_CODE_SEQUENCE"));
				detailInfoList.add(tempMap);

				// 원산지
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "产地");
				tempMap.put("content", detailInfo.get("PRODUCT_ORIGIN"));
				detailInfoList.add(tempMap);

				// 로트 번호
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "批次号");
				tempMap.put("content", detailInfo.get("LOT_NO"));
				detailInfoList.add(tempMap);

				// 검사 번호
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "检测编号");
				tempMap.put("content", detailInfo.get("PRODUCT_TRICKY_NUM"));
				detailInfoList.add(tempMap);

				// 검사 시간
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "检测时间");
				tempMap.put("content", detailInfo.get("PRODUCT_TRICKY_DTM"));
				detailInfoList.add(tempMap);

				// 검사자 성명
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "检测者姓名");
				tempMap.put("content", detailInfo.get("PRODUCT_INSPECTOR_INFO"));
				detailInfoList.add(tempMap);

				// 공장 출고 시간
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "出库（厂）时间");
				tempMap.put("content", detailInfo.get("PRODUCT_RELEASE_DTM"));
				detailInfoList.add(tempMap);

				// 상품 보증 기간
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "产品保质日期");
				tempMap.put("content", detailInfo.get("GUARANTEE_DT"));
				detailInfoList.add(tempMap);

				// 용도
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "用途");
				tempMap.put("content", detailInfo.get("PURPOSE"));
				detailInfoList.add(tempMap);

				// 회사 홈페이지
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "公司网址");
				tempMap.put("content", detailInfo.get("HOMEPAGE_ADDR"));
				detailInfoList.add(tempMap);

				// 회사 메일주소
				tempMap = new HashMap<String, Object>();
				tempMap.put("title", "公司邮箱");
				tempMap.put("content", detailInfo.get("CORP_EMAIL"));
				detailInfoList.add(tempMap);

				// 회사 전화번호
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
				
				result.put("result_code", 200);
				
			} catch (EmptyResultDataAccessException e) {
				result.put("result_code", 404);
			}

		} else {
			result.put("result_code", 403);
		}

		return result;
	}

}
