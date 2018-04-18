package com.hx.acc.common.base;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 分页信息
 */
public class Pageable implements Serializable {

	private static final long serialVersionUID = -3930180379790344299L;

	/** 默认页码 */
	private static final int DEFAULT_PAGE_NUMBER = 1;

	/** 默认每页记录数 */
	private static final int DEFAULT_PAGE_SIZE = 10;

	/** 最大每页记录数 */
	private static final int MAX_PAGE_SIZE = 1000;

	/** 页码 */
	private int pageNumber = DEFAULT_PAGE_NUMBER;

	/** 每页记录数 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/** 搜索属性 */
	private String searchProperty;
	
	/**开始时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date beginDate;  
	
	/**开始日期字符串*/
	private String beginDateStr;  
	
	/**结束日期字符串*/
	private String endDateStr;
	
	/**开始时间*/
	private Long beginTime;
	
	/**结束时间*/
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	
	/**结束时间*/
	private Long endTime;
	
	/**用户名*/
	private String userName;

	/**合同号*/
	private String contractNo;
	
	/**付款类型*/
	private String payType;
	
	/**状态*/
	private String noticeStatus;
	
	/** 搜索值 */
	private String searchValue;

	/** 排序属性 */
	private String orderProperty;
	
	//关联查询根据用户ID，查询accountlog 记录
	private Integer userId;
	
	/**
	 * 初始化一个新创建的Pageable对象
	 */
	public Pageable() {
	}

	public Long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	

	
	public String getBeginDateStr() {
		return beginDateStr;
	}

	public void setBeginDateStr(String beginDateStr) {
		this.beginDateStr = beginDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	/**
	 * 初始化一个新创建的Pageable对象
	 * 
	 * @param pageNumber
	 *            页码
	 * @param pageSize
	 *            每页记录数
	 */
	public Pageable(Integer pageNumber, Integer pageSize) {
		if (pageNumber != null && pageNumber >= 1) {
			this.pageNumber = pageNumber;
		}
		if (pageSize != null && pageSize >= 1 && pageSize <= MAX_PAGE_SIZE) {
			this.pageSize = pageSize;
		}
	}
	
	

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获取页码
	 * 
	 * @return 页码
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * 设置页码
	 * 
	 * @param pageNumber
	 *            页码
	 */
	public void setPageNumber(int pageNumber) {
		if (pageNumber < 1) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		}
		this.pageNumber = pageNumber;
	}

	/**
	 * 获取每页记录数
	 * 
	 * @return 每页记录数
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页记录数
	 * 
	 * @param pageSize
	 *            每页记录数
	 */
	public void setPageSize(int pageSize) {
		if (pageSize < 1 || pageSize > MAX_PAGE_SIZE) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}

	/**
	 * 获取搜索属性
	 * 
	 * @return 搜索属性
	 */
	public String getSearchProperty() {
		return searchProperty;
	}

	/**
	 * 设置搜索属性
	 * 
	 * @param searchProperty
	 *            搜索属性
	 */
	public void setSearchProperty(String searchProperty) {
		this.searchProperty = searchProperty;
	}

	/**
	 * 获取搜索值
	 * 
	 * @return 搜索值
	 */
	public String getSearchValue() {
		return searchValue;
	}

	/**
	 * 设置搜索值
	 * 
	 * @param searchValue
	 *            搜索值
	 */
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	/**
	 * 获取排序属性
	 * 
	 * @return 排序属性
	 */
	public String getOrderProperty() {
		return orderProperty;
	}

	/**
	 * 设置排序属性
	 * 
	 * @param orderProperty
	 *            排序属性
	 */
	public void setOrderProperty(String orderProperty) {
		this.orderProperty = orderProperty;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Pageable other = (Pageable) obj;
		return new EqualsBuilder().append(getPageNumber(), other.getPageNumber()).append(getPageSize(), other.getPageSize()).append(getSearchProperty(), other.getSearchProperty()).append(getSearchValue(), other.getSearchValue()).append(getOrderProperty(), other.getOrderProperty()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(getPageNumber()).append(getPageSize()).append(getSearchProperty()).append(getSearchValue()).append(getOrderProperty()).toHashCode();
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	
	
	
}