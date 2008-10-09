package org.kuali.kra.bo;

import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.IdClass;

import java.util.LinkedHashMap;

@IdClass(org.kuali.kra.bo.id.OrganizationAuditId.class)
@Entity
@Table(name="ORGANIZATION_AUDIT")
public class OrganizationAudit extends KraPersistableBusinessObjectBase {

	@Id
	@Column(name="FISCAL_YEAR")
	private String fiscalYear;
	
	@Id
	@Column(name="ORGANIZATION_ID")
	private String organizationId;
	
	@Column(name="AUDIT_ACCEPTED")
	private boolean auditAccepted;
	
	@Column(name="AUDIT_COMMENT")
	private String auditComment;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="ORGANIZATION_ID", insertable=false, updatable=false)
	private Organization organization;

	public OrganizationAudit(){
		super();
	}

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public boolean getAuditAccepted() {
		return auditAccepted;
	}

	public void setAuditAccepted(boolean auditAccepted) {
		this.auditAccepted = auditAccepted;
	}

	public String getAuditComment() {
		return auditComment;
	}

	public void setAuditComment(String auditComment) {
		this.auditComment = auditComment;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("fiscalYear", getFiscalYear());
		hashMap.put("organizationId", getOrganizationId());
		hashMap.put("auditAccepted", getAuditAccepted());
		hashMap.put("auditComment", getAuditComment());
		return hashMap;
	}

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}

