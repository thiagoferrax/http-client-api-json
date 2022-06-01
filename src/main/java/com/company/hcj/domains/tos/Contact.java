package com.company.hcj.domains.tos;

import java.util.Date;
import java.util.Map;

public class Contact {
	private int id;
	private Map<String, String> properties;
	private Date createdAt;
	private Date updatedAt;
	private boolean archived;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", properties=" + properties + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", archived=" + archived + "]";
	}

}
