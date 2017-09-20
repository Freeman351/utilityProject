package com.freeman.j2ee.service;

import java.io.Serializable;

public interface IBaseEntity extends Serializable{
	public static final long UNSAVED_OBJECT_ID = 0L;
	public static final long FIRST_VERSION_NUMBER = 1L;

	public Long getId();

	public void setId(Long id);

	public Serializable getSerializableObjectId();

	public void setSerializableObjectId(Serializable anObjectId);
	public Integer getIdx() ;

	public void setIdx(Integer idx);
	public boolean isNew();
}
