package com.assignment.diet.response.vo;

import java.util.List;

public class BatchResponse {

	private String parent;
	private List<BatchResponseVO> children;

	public BatchResponse(String parent, List<BatchResponseVO> children) {
		this.parent = parent;
		this.children = children;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public List<BatchResponseVO> getChildren() {
		return children;
	}

	public void setChildren(List<BatchResponseVO> children) {
		this.children = children;
	}

}
