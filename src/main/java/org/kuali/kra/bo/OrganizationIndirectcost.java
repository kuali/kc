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

import java.sql.Date;
import java.util.LinkedHashMap;

import org.kuali.kra.budget.RateDecimal;

@IdClass(org.kuali.kra.bo.id.OrganizationIndirectcostId.class)
@Entity
@Table(name="ORGANIZATION_IDC")
public class OrganizationIndirectcost extends KraPersistableBusinessObjectBase {

	@Id
	@Column(name="IDC_NUMBER")
	private Integer idcNumber;
	@Id
	@Column(name="ORGANIZATION_ID")
	private String organizationId;
	@Column(name="APPLICABLE_IDC_RATE")
	private RateDecimal applicableIndirectcostRate;
	@Column(name="END_DATE")
	private Date endDate;
	@Column(name="IDC_COMMENT")
	private String idcComment;
	@Column(name="IDC_RATE_TYPE_CODE")
	private Integer idcRateTypeCode;
	@Column(name="REQUESTED_DATE")
	private Date requestedDate;
	@Column(name="START_DATE")
	private Date startDate;
    @ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="ORGANIZATION_ID", insertable=false, updatable=false)
	private Organization organization;

	public OrganizationIndirectcost(){
		super();
	}

	public Integer getIdcNumber() {
		return idcNumber;
	}

	public void setIdcNumber(Integer idcNumber) {
		this.idcNumber = idcNumber;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public RateDecimal getApplicableIndirectcostRate() {
		return applicableIndirectcostRate;
	}

	public void setApplicableIndirectcostRate(RateDecimal applicableIndirectcostRate) {
		this.applicableIndirectcostRate = applicableIndirectcostRate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getIdcComment() {
		return idcComment;
	}

	public void setIdcComment(String idcComment) {
		this.idcComment = idcComment;
	}

	public Integer getIdcRateTypeCode() {
		return idcRateTypeCode;
	}

	public void setIdcRateTypeCode(Integer idcRateTypeCode) {
		this.idcRateTypeCode = idcRateTypeCode;
	}

	public Date getRequestedDate() {
		return requestedDate;
	}

	public void setRequestedDate(Date requestedDate) {
		this.requestedDate = requestedDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("idcNumber", getIdcNumber());
		hashMap.put("organizationId", getOrganizationId());
		hashMap.put("applicableIndirectcostRate", getApplicableIndirectcostRate());
		hashMap.put("endDate", getEndDate());
		hashMap.put("idcComment", getIdcComment());
		hashMap.put("idcRateTypeCode", getIdcRateTypeCode());
		hashMap.put("requestedDate", getRequestedDate());
		hashMap.put("startDate", getStartDate());
		return hashMap;
	}

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}

