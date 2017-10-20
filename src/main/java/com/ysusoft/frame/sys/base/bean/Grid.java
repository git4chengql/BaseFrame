package com.ysusoft.frame.sys.base.bean;

import java.util.List;

public class Grid<T> {

	private Long total;
	private List<T> rows;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
