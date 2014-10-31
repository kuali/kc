/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.common.budget.framework.personnel;

import static org.kuali.rice.core.api.criteria.PredicateFactory.equal;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.budget.api.personnel.BudgetPersonContract;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.core.DateSortable;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.rolodex.PersonRolodex;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.sponsor.Sponsorable;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyMaintainable;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.persistence.ScaleTwoDecimalConverter;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;

/**
 * BudgetPerson business object
 */
@Entity
@Table(name = "BUDGET_PERSONS")
public class BudgetPerson extends KcPersistableBusinessObjectBase implements PersonRolodex, HierarchyMaintainable, DateSortable, BudgetPersonContract {

    private static final String BUDGET_PERSON_GROUP_OTHER = "Other Personnel";
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PERSON_SEQUENCE_NUMBER")
    private Integer personSequenceNumber;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "BUDGET_ID", referencedColumnName = "BUDGET_ID")
    private Budget budget;

    @Column(name = "BUDGET_ID", insertable = false, updatable = false)
    private Long budgetId;

    @Column(name = "EFFECTIVE_DATE")
    private Date effectiveDate;

    @Column(name = "JOB_CODE")
    private String jobCode;

    @Column(name = "NON_EMPLOYEE_FLAG")
    @Convert(converter = BooleanYNConverter.class)
    private Boolean nonEmployeeFlag;

    @Column(name = "PERSON_ID")
    private String personId;

    @Column(name = "ROLODEX_ID")
    private Integer rolodexId;

    @Column(name = "TBN_ID")
    private String tbnId;

    @Column(name = "APPOINTMENT_TYPE_CODE")
    private String appointmentTypeCode;

    @Column(name = "CALCULATION_BASE")
    @Convert(converter = ScaleTwoDecimalConverter.class)
    private ScaleTwoDecimal calculationBase;

    @Column(name = "PERSON_NAME")
    private String personName;

    @Column(name = "SALARY_ANNIVERSARY_DATE")
    private Date salaryAnniversaryDate;

    @Column(name = "HIERARCHY_PROPOSAL_NUMBER")
    private String hierarchyProposalNumber;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "HIERARCHY_PROPOSAL_NUMBER", referencedColumnName = "PROPOSAL_NUMBER", insertable = false, updatable = false)
    private DevelopmentProposal hierarchyProposal;

    @Column(name = "HIDE_IN_HIERARCHY")
    @Convert(converter = BooleanYNConverter.class)
    private boolean hiddenInHierarchy;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "JOB_CODE", referencedColumnName = "JOB_CODE", insertable = false, updatable = false)
    private JobCode jobCodeRef;

    @ManyToOne(cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "APPOINTMENT_TYPE_CODE", referencedColumnName = "APPOINTMENT_TYPE_CODE", insertable = false, updatable = false)
    private AppointmentType appointmentType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ROLODEX_ID", referencedColumnName = "ROLODEX_ID", insertable = false, updatable = false)
    private Rolodex rolodex;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "TBN_ID", referencedColumnName = "TBN_ID", insertable = false, updatable = false)
    private TbnPerson tbnPerson;

    @OneToMany(mappedBy="budgetPerson", orphanRemoval = true, cascade = { CascadeType.ALL })
    private List<BudgetPersonSalaryDetails> budgetPersonSalaryDetails;

    @Transient
    private String role;

    @Transient
    private boolean selected;

    @Transient
    private String userName;

    @Transient
    private String emailAddress;

    @Transient
    private Unit unit;

    @Transient
    private String organization;

    @Transient
    private String city;

    @Transient
    private PersonRolodex personRolodex;

    @Transient
    private String contactRoleCode;

    @Transient
    private transient KcPersonService kcPersonService;

    @Transient
    private transient BudgetPersonService budgetPersonService;

    @Transient
    private BudgetPersonSalaryDetails personSalaryDetails;
    
    @Transient
    private DataObjectService dataObjectService;

    public BudgetPerson() {
        super();
        budgetPersonSalaryDetails = new ArrayList<BudgetPersonSalaryDetails>();
        personSalaryDetails = new BudgetPersonSalaryDetails();
    }

    public BudgetPerson(KcPerson person) {
        super();
        this.personId = person.getPersonId();
        this.personName = person.getFullName();
        this.salaryAnniversaryDate = person.getExtendedAttributes().getSalaryAnniversaryDate();
        this.nonEmployeeFlag = false;
        this.userName = person.getUserName();
        this.emailAddress = person.getEmailAddress();
        this.unit = person.getUnit();
    }

    public BudgetPerson(Rolodex rolodex) {
        super();
        this.rolodexId = rolodex.getRolodexId();
        this.personName = rolodex.getFirstName() + " " + rolodex.getLastName();
        this.nonEmployeeFlag = true;
        this.organization = rolodex.getOrganization();
        this.emailAddress = rolodex.getEmailAddress();
        this.city = rolodex.getCity();
    }

    public BudgetPerson(TbnPerson tbn) {
        super();
        this.tbnId = tbn.getTbnId();
        this.personName = tbn.getPersonName();
        this.nonEmployeeFlag = true;
        this.jobCode = tbn.getJobCode();
    }

    public BudgetPerson(PersonRolodex proposalPerson) {
        super();
        if (proposalPerson.getPersonId() != null) {
            this.personId = proposalPerson.getPersonId();
            this.nonEmployeeFlag = false;
        } else {
            this.rolodexId = proposalPerson.getRolodexId();
            this.nonEmployeeFlag = true;
        }
        this.personName = proposalPerson.getFullName();
    }

    public List<BudgetPersonSalaryDetails> getBudgetPersonSalaryDetails() {
      List<BudgetPersonSalaryDetails> salaryDetails = new ArrayList<BudgetPersonSalaryDetails>();
      if (this.budgetPersonSalaryDetails == null || this.budgetPersonSalaryDetails.isEmpty()) {
          for (BudgetPeriod budgetPeriod : getBudget().getBudgetPeriods()) {
        	  BudgetPersonSalaryDetails budgetPersonSalaryDetails = new BudgetPersonSalaryDetails();
        	  budgetPersonSalaryDetails.setBudgetId(getBudgetId());
        	  budgetPersonSalaryDetails.setBudgetPeriod(budgetPeriod.getBudgetPeriod());
        	  budgetPersonSalaryDetails.setPersonId(getPersonId());
        	  budgetPersonSalaryDetails.setPersonSequenceNumber(getPersonSequenceNumber());
              salaryDetails.add(budgetPersonSalaryDetails);
          }
          this.budgetPersonSalaryDetails = salaryDetails;
      }
    	return budgetPersonSalaryDetails;
    }

    public void setBudgetPersonSalaryDetails(List<BudgetPersonSalaryDetails> budgetPersonSalaryDetails) {
        this.budgetPersonSalaryDetails = budgetPersonSalaryDetails;
    }

    @Override
    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    @Override
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /*
	 * Helper method for calculator. This is used for sorting and filtering after combining with rates purpose
	 */
    public Date getStartDate() {
        return effectiveDate;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        if (this.jobCode == null || !this.jobCode.equals(jobCode)) {
            this.jobCode = jobCode;
            refreshJobTitle();
        }
    }

    @Override
    public Boolean getNonEmployeeFlag() {
        return nonEmployeeFlag;
    }

    public void setNonEmployeeFlag(Boolean nonEmployeeFlag) {
        this.nonEmployeeFlag = nonEmployeeFlag;
    }

    @Override
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    @Override
    public ScaleTwoDecimal getCalculationBase() {
        return calculationBase;
    }

    public void setCalculationBase(ScaleTwoDecimal calculationBase) {
        this.calculationBase = calculationBase;
    }

    @Override
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getAppointmentTypeCode() {
        return appointmentTypeCode;
    }

    public void setAppointmentTypeCode(String appointmentTypeCode) {
        this.appointmentTypeCode = appointmentTypeCode;
    }

    @Override
    public Integer getRolodexId() {
        return rolodexId;
    }

    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    public KcPerson getPerson() {
        return getKcPersonService().getKcPersonByPersonId(personId);
    }

    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return this.kcPersonService;
    }

    public Rolodex getRoldex() {
        return rolodex;
    }

    public void setRoldex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }

    @Override
    public Integer getPersonSequenceNumber() {
        return personSequenceNumber;
    }

    public void setPersonSequenceNumber(Integer personSequenceNumber) {
        this.personSequenceNumber = personSequenceNumber;
    }

    @Override
    public String getTbnId() {
        return tbnId;
    }

    public void setTbnId(String tbnId) {
        this.tbnId = tbnId;
    }

    public Rolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }

    public String getRole() {
		if(getPersonRolodex() != null) {
			this.role = getPersonRolodex().getContactRole().getRoleDescription();
		}
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /**
     * This method determines if the given budgetPerson is the same person with the same job code & effective date
     * @param budgetPerson
     * @return boolean
     */
    public boolean isDuplicatePerson(BudgetPerson budgetPerson) {
        if (!StringUtils.equals(this.getJobCode(), budgetPerson.getJobCode()) || !ObjectUtils.equals(this.getEffectiveDate(), budgetPerson.getEffectiveDate())) {
            return false;
        }
        if (this.getNonEmployeeFlag() != null && this.getNonEmployeeFlag() && budgetPerson.getNonEmployeeFlag() != null && budgetPerson.getNonEmployeeFlag()) {
            if (this.getRolodexId() != null && budgetPerson.getRolodexId() != null) {
                return this.getRolodexId().equals(budgetPerson.getRolodexId());
            } else if (this.getTbnId() != null && budgetPerson.getTbnId() != null) {
                return this.getTbnId().equals(budgetPerson.getTbnId());
            }
            return false;
        } else if (this.getNonEmployeeFlag() != null && !this.getNonEmployeeFlag() && budgetPerson.getNonEmployeeFlag() != null && !budgetPerson.getNonEmployeeFlag()) {
            return this.getPersonId().equals(budgetPerson.getPersonId());
        }
        // else non-employee vs. employee  
        return false;
    }

    public boolean isSamePerson(BudgetPerson budgetPerson) {
        if (this.getNonEmployeeFlag() && budgetPerson.getNonEmployeeFlag()) {
            if (this.getRolodexId() != null && budgetPerson.getRolodexId() != null) {
                return this.getRolodexId().equals(budgetPerson.getRolodexId());
            } else if (this.getTbnId() != null && budgetPerson.getTbnId() != null) {
                return this.getTbnId().equals(budgetPerson.getTbnId());
            }
            return false;
        } else if (!this.getNonEmployeeFlag() && !budgetPerson.getNonEmployeeFlag()) {
            return this.getPersonId().equals(budgetPerson.getPersonId());
        }
        // else non-employee vs. employee  
        return false;
    }

    public String getPersonRolodexTbnId() {
        String rolodexPersonId = getRolodexId() == null ? getPersonId() : getRolodexId().toString();
        return rolodexPersonId == null ? getTbnId() : rolodexPersonId;
    }

    public String getJobTitle() {
        // Note, since we aren't persisting the jobTitle in the BudgetPersons table, we need to grab the title   
        // for each BudgetPerson.jobCode via svc call below.  
        getJobTitleFromJobCode();
        String ret = null;
        if (jobCodeRef != null) {
            ret = jobCodeRef.getJobTitle();
        }
        return ret;
    }

    public void setJobTitle(String jobTitle) {
        refreshJobTitle();
    }

    private void refreshJobTitle() {
        jobCodeRef = null;
        getJobTitleFromJobCode();
    }

    private void getJobTitleFromJobCode() {
        if (StringUtils.isNotBlank(getJobCode()) && (this.jobCodeRef == null || !StringUtils.isNotBlank(this.jobCodeRef.getJobTitle()))) {
            JobCodeService jcService = KcServiceLocator.getService(JobCodeService.class);
            this.jobCodeRef = jcService.findJobCodeRef(getJobCode());
        }
    }

    @Override
    public JobCode getJobCodeRef() {
        return jobCodeRef;
    }

    public void setJobCodeRef(JobCode jobCodeRef) {
        this.jobCodeRef = jobCodeRef;
    }

    @Override
    public void setHierarchyProposalNumber(String hierarchyProposalNumber) {
        this.hierarchyProposalNumber = hierarchyProposalNumber;
    }

    @Override
    public String getHierarchyProposalNumber() {
        return hierarchyProposalNumber;
    }

    @Override
    public boolean isHiddenInHierarchy() {
        return hiddenInHierarchy;
    }

    @Override
    public void setHiddenInHierarchy(boolean hiddenInHierarchy) {
        this.hiddenInHierarchy = hiddenInHierarchy;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBudgetId() == null) ? 0 : getBudgetId().hashCode());
        result = prime * result + ((personSequenceNumber == null) ? 0 : personSequenceNumber.hashCode());
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
        BudgetPerson other = (BudgetPerson) obj;
        if (getBudgetId() == null) {
            if (other.getBudgetId() != null)
                return false;
        } else if (!getBudgetId().equals(other.getBudgetId()))
            return false;
        if (personSequenceNumber == null) {
            if (other.personSequenceNumber != null)
                return false;
        } else if (!personSequenceNumber.equals(other.personSequenceNumber))
            return false;
        return true;
    }

    @Override
    public Date getSalaryAnniversaryDate() {
        return salaryAnniversaryDate;
    }

    public void setSalaryAnniversaryDate(Date salaryAnniversaryDate) {
        this.salaryAnniversaryDate = salaryAnniversaryDate;
    }

    @Override
    public Date getSortableDate() {
        return getEffectiveDate();
    }

    public void setPersonSalaryDetails(BudgetPersonSalaryDetails personSalaryDetails) {
        this.personSalaryDetails = personSalaryDetails;
    }

    public BudgetPersonSalaryDetails getPersonSalaryDetails() {
        return personSalaryDetails;
    }

    public TbnPerson getTbnPerson() {
        return tbnPerson;
    }

    public void setTbnPerson(TbnPerson tbnPerson) {
        this.tbnPerson = tbnPerson;
    }

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Budget getBudget() {
		if (budget == null) {
			//In an award, OJB may not have populated budget before some getters that call this function are used.
			QueryByCriteria builder = QueryByCriteria.Builder.fromPredicates(equal("budgetId", this.getBudgetId()));
	    	List<Budget> budgets = getDataObjectService().findMatching(Budget.class, builder).getResults();
	    	this.budget = budgets.get(0);
		}
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPersonGroup() {
		if (hierarchyProposal != null) {
			return "Proposal # " + hierarchyProposal.getProposalNumber() + " - Budget Version " + hierarchyProposal.getLastSyncedBudget().getBudgetVersionNumber();
		} else {
			return getPersonRolodex() != null ? getBudget().getParentDocumentGroupName() : BUDGET_PERSON_GROUP_OTHER;
		}
	}

	protected BudgetPersonService getBudgetPersonService() {
        if (this.budgetPersonService == null) {
            this.budgetPersonService = KcServiceLocator.getService(BudgetPersonService.class);
        }
        return this.budgetPersonService;
    }

	public String getContactRoleCode() {
		if(getPersonRolodex() != null) {
			this.contactRoleCode = getPersonRolodex().getContactRole().getRoleCode();
		}
		return contactRoleCode;
	}

	public void setContactRoleCode(String contactRoleCode) {
		this.contactRoleCode = contactRoleCode;
	}
	
	@PostLoad
	protected void syncPersonRoldex() {
		setPersonRolodex(getBudgetPersonService().getBudgetPersonRolodex(getBudget(), this));
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public PersonRolodex getPersonRolodex() {
		return personRolodex;
	}

	public void setPersonRolodex(PersonRolodex personRolodex) {
		this.personRolodex = personRolodex;
	}

	public DataObjectService getDataObjectService() {
		if (dataObjectService == null) {
			dataObjectService = KcServiceLocator.getService(DataObjectService.class);
		}
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}
	
	@Override
	public String getFullName() {
		return personName;
	}

	@Override
	public boolean isOtherSignificantContributorFlag() {
		return getPersonRolodex() != null ? getPersonRolodex().isOtherSignificantContributorFlag() : false;
	}

	@Override
	public String getProjectRole() {
		return getPersonRolodex() != null ? getPersonRolodex().getProjectRole() : null;
	}

	@Override
	public ContactRole getContactRole() {
		return getPersonRolodex() != null ? getPersonRolodex().getContactRole() : null;
	}

	@Override
	public Sponsorable getParent() {
		return getPersonRolodex() != null ? getPersonRolodex().getParent() : null;
	}

	@Override
	public String getInvestigatorRoleDescription() {
		return getPersonRolodex() != null ? getPersonRolodex().getInvestigatorRoleDescription() : null;
	}

	@Override
	public boolean isInvestigator() {
		return getPersonRolodex() != null ? getPersonRolodex().isInvestigator() : false;
	}

	@Override
	public boolean isPrincipalInvestigator() {
		return getPersonRolodex() != null ? getPersonRolodex().isPrincipalInvestigator() : false;
	}

	@Override
	public boolean isMultiplePi() {
		return getPersonRolodex() != null ? getPersonRolodex().isMultiplePi() : false;
	}

	@Override
	public String getLastName() {
		String lastName = null;
		if(getTbnId() == null) {
			lastName = getRolodexId() == null ? getKcPersonService().getKcPersonByPersonId(personId).getLastName() : getRoldex().getLastName();
		}
		return lastName;
	}

	@Override
	public Integer getOrdinalPosition() {
		return getPersonRolodex() != null ? getPersonRolodex().getOrdinalPosition() : getPersonSequenceNumber();
	}

	public DevelopmentProposal getHierarchyProposal() {
		return hierarchyProposal;
	}

	public void setHierarchyProposal(DevelopmentProposal hierarchyProposal) {
		this.hierarchyProposal = hierarchyProposal;
	}

}
