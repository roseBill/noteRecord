package com.hx.acc.payment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


public class PaymentController {

	private static Logger logger = Logger.getLogger(PaymentController.class);
	
	private final String lendFilename = "任务.xls";


	
	/** 
	 * @Description 放款查询导出报表222
	 * @param 参数 
	 * @return ModelAndView 返回类型  
	 */  
	@RequestMapping(value = "/loan/exportExl")
	public ModelAndView exportExl(HttpServletRequest request,Pageable pageable,ModelMap model){
		
		logger.info("=====放款任务查询导出数据start=====");
		doSetPageable(pageable,false);
		
		Page<Payment> page = null;
		ModelAndView modelAndView = null;
		try {
			page = paymentService.findExportPaymentList(pageable);
			modelAndView = getModelAndView(page.getContent(), model,lendFilename,false);
		} catch (Exception e) {
			String errorMsg = "BeginDateStr::"+pageable.getBeginDateStr()+";"+
					"EndDateStr::"+pageable.getEndDateStr()+";"+
					"NoticeStatus::"+pageable.getNoticeStatus()+";"+
					"PayType::"+pageable.getPayType()+";"+
					"UserName::"+pageable.getUserName()+";"+
					"PageNumber::"+pageable.getPageNumber()+";"+
					"PageSize::"+pageable.getPageSize()+";";
							
			logger.error("manualOperationExportExl"+errorMsg, e);
		}
		
		logger.info("=====放款任务查询导出数据end=====");		
		
		return modelAndView;
	}
	

	
	/** 
	 * @Description 拼接字段，形成excel
	 * @param 参数 
	 * @return ModelAndView 返回类型  
	 */  
	private ModelAndView getModelAndView(List<Payment> list,ModelMap model,String filename,boolean isOperation){
		
		if(list == null){
			logger.error("PaymentController getModelAndView : list is null");
			return null;
		}
		if(model == null){
			logger.error("PaymentController getModelAndView : model is null");
			return null;
		}
		
		if(StringUtils.isEmpty(filename)){
			logger.error("PaymentController getModelAndView : filename is null");
			return null;
		}
		
		ModelAndView modelAndView = null;
				
		//文件格式
		String[] formats = new String[]{
				"@","@","@","@","@",
				"@","@","@","@","@",
				"@","@","@","@","#,##0.00",
				"#,##0.00","#,##0.00","#,##0.00","#,##0.00","#,##0.00",
				"#,##0.00","#,##0.00","#,##0.00","#,##0.00","#,##0.00",
				"#,##0.00","#,##0.00","#,##0.00","#,##0.00","@",
				"@","#,##0.00","@","@","@",
				"@"};
				
		//属性
		String[] properties = new String[]{
				"noticeNo","contractNo","companyName","customerName","productType",
				"repaymentMethod","loanPeriod","settleAgainLoan","xxxx2","gpsInstallStatus",
				"payTypeStr","payee","bank","accountNo","audiAmount",
				"audiProductRate","interest","processingFee","collateralRegistFee","gpsFee",
				"parkingFee","keyDeductFee","peccancyFeeHolding","insuranceFeeHolding","annualVerificationHolding",
				"temporaryStorageFee","provincialDeposit","urgentFee","otherFee","otherFeeMemo",
				"totleAmount","amount","baopanTime","payTime","noticeStatus","remark"
		};
		
		String[] titles = null;	
		
		if(isOperation){//是否人工操作界面导出
			titles = new String[]{
					"流水号","流水号","流水号","流水号","流水号"

				};
		}else {//放款查询界面
			titles = new String[]{
					流水号","流水号","流水号","流水号","流水号"
				};
		}
		
		
		//固定宽度
		Integer[] widths = new Integer[]{
				3000,6000,3000,3000,3000, 
				3000,3000,3000,6000,4000, 
				3000,3000,3000,6000,3000, 
				3000,3000,3000,3000,3000, 
				3000,3000,3000,3000,3000, 
				3000,3000,3000,3000,3000, 
				3000,3000,6000,6000,3000,
				6000};
						
		//类型转换
		DateConverter dateConverter = new DateConverter();
		dateConverter.setPattern("yyyy-MM-dd");
		
		DateConverter dateDetailConverter = new DateConverter();
		dateDetailConverter.setPattern("yyyy-MM-dd HH:mm:ss");
		
		BigDecimalConverter decimal = new BigDecimalConverter();
		decimal.setPattern("#,##0.00");
		
		DoubleConverter doubleConverter = new DoubleConverter();
		doubleConverter.setPattern("#.#");
		
		Converter[] converters = new Converter[]{
				decimal,decimal,decimal,decimal,decimal,
				decimal,decimal,decimal,decimal,decimal,
				decimal,decimal,decimal,decimal,decimal,
				
				decimal,decimal,decimal,decimal,decimal,
				decimal,decimal,decimal,decimal,decimal,
				decimal,decimal,decimal,decimal,decimal,
				decimal,decimal,dateDetailConverter,dateDetailConverter,decimal,decimal
				};
		
		modelAndView = new ModelAndView(new CashSearchExcelView(filename, null, properties, titles,formats, widths, converters, list, null), model);
		
		
		return modelAndView;
	}
	
	
	private void doSetPageable(Pageable pageable,boolean isOperate) {
		if(pageable == null){
			logger.error("pageable is null");
			return;
		}
		String beginDate = "";
		String endDate = "";
		//开始日期
		if(pageable.getBeginDate()!=null){
			beginDate = DateUtils.format(pageable.getBeginDate(), DateUtils.SHOW_DATE_FORMAT);//yyyy-MM-dd
		}else{//为空时设置今天
			//beginDate =  DateUtils.format(DateUtils.currentDateBegin(), DateUtils.SHOW_DATE_FORMAT);//yyyy-MM-dd
		}
		pageable.setBeginDateStr(beginDate);
		//结束日期
		if(pageable.getEndDate()!=null){
			endDate = DateUtils.format(pageable.getEndDate(),DateUtils.SHOW_DATE_FORMAT);//yyyy-MM-dd
		}else {//为空时设置今天
			//endDate = DateUtils.format(DateUtils.currentDateEnd(),DateUtils.SHOW_DATE_FORMAT);//yyyy-MM-dd
		}
		pageable.setEndDateStr(endDate);
		
		doSetPayType(pageable,isOperate);
		doSetNoticeStatus(pageable,isOperate);
		
	}
	
	
	
	
}
