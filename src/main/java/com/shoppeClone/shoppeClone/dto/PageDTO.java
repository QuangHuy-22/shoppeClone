package com.shoppeClone.shoppeClone.dto;

import java.util.ArrayList;
import java.util.List;

public class PageDTO<T> {

private Integer page;
	
	private Integer limit;
	
	private Long totalItems;
	
	private List<T> data = new ArrayList<>();

	public PageDTO(Integer page, Integer limit, Long totalItems, List<T> data) {
		this.page = page;
		this.limit = limit;
		this.totalItems = totalItems;
		this.data = data;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
}
