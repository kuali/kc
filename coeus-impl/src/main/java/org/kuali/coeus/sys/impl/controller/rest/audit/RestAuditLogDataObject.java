package org.kuali.coeus.sys.impl.controller.rest.audit;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

@Entity
@Table(name = "REST_AUDIT_LOG")
public class RestAuditLogDataObject {

    @PortableSequenceGenerator(name = "SEQ_REST_AUDIT_LOG_ID")
    @GeneratedValue(generator = "SEQ_REST_AUDIT_LOG_ID")
	@Id
	@Column(name ="ID")
    private Long id;
    
    @Column(name = "USERNAME")
    private String username;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIMESTAMP")
    private Date date;
    
    @Column(name = "CLASS_NAME")
    private String className;
    
    @Column(name = "ADDED_JSON")
    private String added;
    
    @Column(name = "MODIFIED_JSON")
    private String modified;
    
    @Column(name = "DELETED_JSON")
    private String deleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getAdded() {
		return added;
	}

	public void setAdded(String added) {
		this.added = added;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	
	
}
