package com.hx.acc.common.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 */
public class Page<T> implements Serializable {

	private static final long serialVersionUID = -2053800594583879853L;

	/** 内容 */
	private final List<T> content = new ArrayList<T>();

	/** 总记录数 */
	private final long total;

	/** 分页信息 */
	private final Pageable pageable;

	/** 中间段页码数 */
	private final int segmentCount;

	/** 是否存在上一页 */
	private final boolean hasPrevious;

	/** 是否存在下一页 */
	private final boolean hasNext;

	/** 是否为首页 */
	private final boolean isFirst;

	/** 是否为末页 */
	private final boolean isLast;

	/** 上一页页码 */
	private final int previousPageNumber;

	/** 下一页页码 */
	private final int nextPageNumber;

	/** 首页页码 */
	private final int firstPageNumber;

	/** 末页页码 */
	private final int lastPageNumber;

	/** 中间段页码 */
	private final List<Integer> segment = new ArrayList<Integer>();

	/**
	 * 初始化一个新创建的Page对象
	 */
	public Page() {
		this.total = 0L;
		this.pageable = new Pageable();
		this.segmentCount = 0;
		this.hasPrevious = false;
		this.hasNext = false;
		this.isFirst = false;
		this.isLast = false;
		this.previousPageNumber = 0;
		this.nextPageNumber = 0;
		this.firstPageNumber = 0;
		this.lastPageNumber = 0;
	}

	/**
	 * @param content
	 *            内容
	 * @param total
	 *            总记录数
	 * @param pageable
	 *            分页信息
	 */
	public Page(List<T> content, long total, Pageable pageable) {
		this.content.addAll(content);
		this.total = total;
		this.pageable = pageable;
		
		Integer pageNumber = this.getPageNumber();
		Integer totalPages = this.getTotalPages();
		Integer segmentCount = this.getSegmentCount();

		if (pageNumber == null || pageNumber < 1) {
			pageNumber = 1;
		}
		if (totalPages == null || totalPages < 1) {
			totalPages = 1;
		}
		if (segmentCount == null || segmentCount < 1) {
			segmentCount = 10;
		}
		boolean hasPrevious = pageNumber > 1;
		boolean hasNext = pageNumber < totalPages;
		boolean isFirst = pageNumber == 1;
		boolean isLast = pageNumber.equals(totalPages);
		int previousPageNumber = pageNumber - 1;
		int nextPageNumber = pageNumber + 1;
		int firstPageNumber = 1;
		int lastPageNumber = totalPages;
		int startSegmentPageNumber = pageNumber - (int) Math.floor((segmentCount - 1) / 2D);//向下取整
		int endSegmentPageNumber = pageNumber + (int) Math.ceil((segmentCount - 1) / 2D);//向上取整
		if(pageNumber<6){
			endSegmentPageNumber = 10;
		}
		if(pageNumber>(totalPages-6)){
			startSegmentPageNumber = totalPages-9;
		}
		if (startSegmentPageNumber < 1) {
			startSegmentPageNumber = 1;
		}
		if (endSegmentPageNumber > totalPages) {
			endSegmentPageNumber = totalPages;
		}
		for (int i = startSegmentPageNumber; i <= endSegmentPageNumber; i++) {
			this.segment.add(i);
		}
		this.segmentCount = segmentCount;
		this.hasPrevious = hasPrevious;
		this.hasNext = hasNext;
		this.isFirst = isFirst;
		this.isLast = isLast;
		this.previousPageNumber = previousPageNumber;
		this.nextPageNumber = nextPageNumber;
		this.firstPageNumber = firstPageNumber;
		this.lastPageNumber = lastPageNumber;
	}

	/**
	 * 获取页码
	 * 
	 * @return 页码
	 */
	public int getPageNumber() {
		return pageable.getPageNumber();
	}

	/**
	 * 获取每页记录数
	 * 
	 * @return 每页记录数
	 */
	public int getPageSize() {
		return pageable.getPageSize();
	}

	/**
	 * 获取搜索属性
	 * 
	 * @return 搜索属性
	 */
	public String getSearchProperty() {
		return pageable.getSearchProperty();
	}

	/**
	 * 获取搜索值
	 * 
	 * @return 搜索值
	 */
	public String getSearchValue() {
		return pageable.getSearchValue();
	}

	/**
	 * 获取排序属性
	 * 
	 * @return 排序属性
	 */
	public String getOrderProperty() {
		return pageable.getOrderProperty();
	}

	/**
	 * 获取总页数
	 * 
	 * @return 总页数
	 */
	public int getTotalPages() {
		return (int) Math.ceil((double) getTotal() / (double) getPageSize());
	}

	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */
	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content){
		this.content.addAll(content);
	}
	/**
	 * 获取总记录数
	 * 
	 * @return 总记录数
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * 获取分页信息
	 * 
	 * @return 分页信息
	 */
	public Pageable getPageable() {
		return pageable;
	}

	public int getSegmentCount() {
		return segmentCount;
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public boolean getIsFirst() {
		return isFirst;
	}

	public boolean getIsLast() {
		return isLast;
	}

	public int getPreviousPageNumber() {
		return previousPageNumber;
	}

	public int getNextPageNumber() {
		return nextPageNumber;
	}

	public int getFirstPageNumber() {
		return firstPageNumber;
	}

	public int getLastPageNumber() {
		return lastPageNumber;
	}

	public List<Integer> getSegment() {
		return segment;
	}
}