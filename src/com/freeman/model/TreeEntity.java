package com.freeman.model;

import java.util.List;

public class TreeEntity {

	private Long id;
	private Long parentId;
	private Long rootId;
	private String name;
	private List<TreeEntity> children;
	private int level;

	public TreeEntity() {
	}

	public TreeEntity(Long id, Long parentId, Long rootId, String name) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.rootId = rootId;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getRootId() {
		return rootId;
	}

	public void setRootId(Long rootId) {
		this.rootId = rootId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TreeEntity> getChildren() {
		return children;
	}

	public void setChildren(List<TreeEntity> children) {
		this.children = children;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
