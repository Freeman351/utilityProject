package com.freeman.model;

import java.io.Serializable;

import com.freeman.j2ee.service.IBaseEntity;


@SuppressWarnings("serial")
public class BaseEntity implements IBaseEntity {
	private Long id = null;
	private Integer idx = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Serializable getSerializableObjectId() {
		return new Long(id);
	}

	public void setSerializableObjectId(Serializable anObjectId) {
		if (anObjectId instanceof Long)
			id = ((Long) anObjectId).longValue();
	}


	public boolean isNew() {
		return getId() == null;
	}

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idx == null) ? 0 : idx.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idx == null) {
			if (other.idx != null)
				return false;
		} else if (!idx.equals(other.idx))
			return false;
		return true;
	}

}
