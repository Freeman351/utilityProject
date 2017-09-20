package com.freeman.model;

public class UserLabel extends BaseEntity {

	private Long userId;
	private User user;
	private Long labelId;
	private Label label;
	private Long parentUserLabelId;
	private Long rootUserLabelId;

	public UserLabel() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getLabelId() {
		return labelId;
	}

	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public Long getParentUserLabelId() {
		return parentUserLabelId;
	}

	public void setParentUserLabelId(Long parentUserLabelId) {
		this.parentUserLabelId = parentUserLabelId;
	}

	public Long getRootUserLabelId() {
		return rootUserLabelId;
	}

	public void setRootUserLabelId(Long rootUserLabelId) {
		this.rootUserLabelId = rootUserLabelId;
	}

}
