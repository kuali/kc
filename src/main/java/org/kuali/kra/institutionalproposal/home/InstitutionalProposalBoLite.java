package org.kuali.kra.institutionalproposal.home;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.proposaldevelopment.bo.ActivityType;
import org.kuali.kra.proposaldevelopment.bo.ProposalType;
import org.kuali.rice.core.api.util.type.KualiDecimal;
import org.kuali.rice.krad.service.BusinessObjectService;

public class InstitutionalProposalBoLite extends KraPersistableBusinessObjectBase {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1097135668497003235L;

	private Long proposalId;
	
	private List<InstitutionalProposalPerson> projectPersons;

	private String unitNumber;
	private Unit leadUnit;
	
	private String sponsorCode;
	private String sponsorName;
	private Sponsor sponsor;
	
	private Date requestedStartDateInitial;
    private Date requestedStartDateTotal;
    private Date requestedEndDateInitial;
    private Date requestedEndDateTotal;
	
	private String proposalNumber;
    
	private Integer sequenceNumber;
    
	private Integer proposalTypeCode;
    private ProposalType proposalType;
    
	private String activityTypeCode;
    private ActivityType activityType;
    
	private String title;
	
    private KualiDecimal totalDirectCostInitial;
    private KualiDecimal totalDirectCostTotal;
    private KualiDecimal totalIndirectCostInitial;
    private KualiDecimal totalIndirectCostTotal;
    
    private Integer statusCode;
    
    public Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}

	public String getProposalNumber() {
		return proposalNumber;
	}

	public void setProposalNumber(String proposalNumber) {
		this.proposalNumber = proposalNumber;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public Unit getLeadUnit() {
		return leadUnit;
	}

	public void setLeadUnit(Unit leadUnit) {
		this.leadUnit = leadUnit;
	}
	
	public String getSponsorCode() {
		return sponsorCode;
	}

	public void setSponsorCode(String sponsorCode) {
		this.sponsorCode = sponsorCode;
	}

	public Sponsor getSponsor() {
		return sponsor;
	}

	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	public String getSponsorName() {
        Sponsor tempSponsor = getSponsor();
        sponsorName = tempSponsor != null ? tempSponsor.getSponsorName() : null;
        return sponsorName;
    }
	
	public Date getRequestedStartDateInitial() {
		return requestedStartDateInitial;
	}

	public void setRequestedStartDateInitial(Date requestedStartDateInitial) {
		this.requestedStartDateInitial = requestedStartDateInitial;
	}

	public Date getRequestedStartDateTotal() {
		return requestedStartDateTotal;
	}

	public void setRequestedStartDateTotal(Date requestedStartDateTotal) {
		this.requestedStartDateTotal = requestedStartDateTotal;
	}

	public Date getRequestedEndDateInitial() {
		return requestedEndDateInitial;
	}

	public void setRequestedEndDateInitial(Date requestedEndDateInitial) {
		this.requestedEndDateInitial = requestedEndDateInitial;
	}

	public Date getRequestedEndDateTotal() {
		return requestedEndDateTotal;
	}

	public void setRequestedEndDateTotal(Date requestedEndDateTotal) {
		this.requestedEndDateTotal = requestedEndDateTotal;
	}
	
	public KualiDecimal getTotalDirectCostInitial() {
		return totalDirectCostInitial;
	}

	public void setTotalDirectCostInitial(KualiDecimal totalDirectCostInitial) {
		this.totalDirectCostInitial = totalDirectCostInitial;
	}

	public KualiDecimal getTotalDirectCostTotal() {
		return totalDirectCostTotal;
	}

	public void setTotalDirectCostTotal(KualiDecimal totalDirectCostTotal) {
		this.totalDirectCostTotal = totalDirectCostTotal;
	}

	public KualiDecimal getTotalIndirectCostInitial() {
		return totalIndirectCostInitial;
	}

	public void setTotalIndirectCostInitial(KualiDecimal totalIndirectCostInitial) {
		this.totalIndirectCostInitial = totalIndirectCostInitial;
	}

	public KualiDecimal getTotalIndirectCostTotal() {
		return totalIndirectCostTotal;
	}

	public void setTotalIndirectCostTotal(KualiDecimal totalIndirectCostTotal) {
		this.totalIndirectCostTotal = totalIndirectCostTotal;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public KualiDecimal getTotalInitialCost() {
        KualiDecimal returnValue = new KualiDecimal(0);
        returnValue = returnValue.add(totalDirectCostInitial);
        returnValue = returnValue.add(totalIndirectCostInitial);
        return returnValue;
    }
	
	public KualiDecimal getTotalCost() {
        KualiDecimal returnValue = new KualiDecimal(0);
        returnValue = returnValue.add(totalDirectCostTotal);
        returnValue = returnValue.add(totalIndirectCostTotal);
        return returnValue;
    }

	public InstitutionalProposalPerson getPrincipalInvestigator() {
        for (InstitutionalProposalPerson proposalPerson : this.getProjectPersons()) {
            if (proposalPerson.isPrincipalInvestigator()) {
                return proposalPerson;
            }
        }
        return null;
    }

	/**
     * Gets the projectPersons attribute. 
     * @return Returns the projectPersons.
     */
    public List<InstitutionalProposalPerson> getProjectPersons() {   
        return projectPersons; 
    }
	
	public Integer getProposalTypeCode() {
		return proposalTypeCode;
	}

	public void setProposalTypeCode(Integer proposalTypeCode) {
		this.proposalTypeCode = proposalTypeCode;
	}
	
	public ProposalType getProposalTypeFromCode() {
        if (proposalType == null) {
            if (proposalTypeCode != null) {
                Map<String, Object> identifiers = new HashMap<String, Object>();
                identifiers.put("proposalTypeCode", proposalTypeCode);
                proposalType = (ProposalType) getBusinessObjectService().findByPrimaryKey(ProposalType.class, identifiers);
            }
        }
        return proposalType;
    }
	
	public ProposalType getProposalType() {
        if (proposalType == null && proposalTypeCode != null) {
            this.refreshReferenceObject("proposalType");
        }
        return proposalType;
    }
	
	public String getActivityTypeCode() {
		return activityTypeCode;
	}

	public void setActivityTypeCode(String activityTypeCode) {
		this.activityTypeCode = activityTypeCode;
	}
	
	public ActivityType getActivityType() {
        if (activityType == null && activityTypeCode != null) {
            this.refreshReferenceObject("activityType");
        }
        return activityType;
    }
	
	public ActivityType getActivityTypeFromCode() {
        if (activityType == null) {
            if (activityTypeCode != null) {
                Map<String, Object> identifiers = new HashMap<String, Object>();
                identifiers.put("activityTypeCode", activityTypeCode);
                activityType = (ActivityType) getBusinessObjectService().findByPrimaryKey(ActivityType.class, identifiers);
            }
        }
        return activityType;
    }
	
	public void setProjectPersons(List<InstitutionalProposalPerson> projectPersons) {
		this.projectPersons = projectPersons;
	}

	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}

	public void setProposalType(ProposalType proposalType) {
		this.proposalType = proposalType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}
	
	public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
	
	protected BusinessObjectService getBusinessObjectService() {
        return (BusinessObjectService) KraServiceLocator.getService(BusinessObjectService.class);
    }
	
	protected InstitutionalProposalService getInstitutionalProposalService() {
		return (InstitutionalProposalService) KraServiceLocator.getService(InstitutionalProposalService.class);
	}
	
	public InstitutionalProposal getInstitutionalProposal() {
		// If we need the full IP, we can lazy load it and retrieve it.
		return getInstitutionalProposalService().getInstitutionalProposal(this.getProposalId().toString());
	}
	
	public InstitutionalProposalDocument getInstitutionalProposalDocument() {
		return this.getInstitutionalProposal().getInstitutionalProposalDocument();
	}
    
	
}
