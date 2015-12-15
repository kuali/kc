package org.kuali.coeus.sys.framework.controller.rest.audit;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestAuditLog {

	private String username;
	private Instant date;
	private String className;
	private List<Map<String, Object>> added = new ArrayList<>();
	private List<Map<String, Object>> modified = new ArrayList<>();
	private List<Map<String, Object>> deleted = new ArrayList<>();
	
	public RestAuditLog(String username, String className) {
		this.username = username;
		this.className = className;
	}
	
	public RestAuditLog(String username, Instant date, String className, List<Map<String, Object>> added,
			List<Map<String, Object>> modified, List<Map<String, Object>> deleted) {
		super();
		this.username = username;
		this.date = date;
		this.className = className;
		this.added = added;
		this.modified = modified;
		this.deleted = deleted;
	}
	
	public boolean hasChanges() {
		return !added.isEmpty() || !modified.isEmpty() || !deleted.isEmpty();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Instant getDate() {
		return date;
	}
	public void setDate(Instant date) {
		this.date = date;
	}
	public List<Map<String, Object>> getAdded() {
		return added;
	}
	public void setAdded(List<Map<String, Object>> added) {
		this.added = added;
	}
	public List<Map<String, Object>> getModified() {
		return modified;
	}
	public void setModified(List<Map<String, Object>> modified) {
		this.modified = modified;
	}
	public List<Map<String, Object>> getDeleted() {
		return deleted;
	}
	public void setDeleted(List<Map<String, Object>> deleted) {
		this.deleted = deleted;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	
}
