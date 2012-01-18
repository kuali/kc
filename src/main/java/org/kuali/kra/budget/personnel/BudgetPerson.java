/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.budget.personnel;

import java.sql.Date;
import java.util.LinkedHashMap;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.core.BudgetAssociate;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.proposaldevelopment.hierarchy.HierarchyMaintainable;
import org.kuali.kra.proposaldevelopment.hierarchy.HierarchyMaintainable;

/**
 * BudgetPerson business object
 */
public class BudgetPerson extends BudgetAssociate implements HierarchyMaintainable {
	
    private static final long serialVersionUID = 1L;
    
    private Date effectiveDate;
	private String jobCode;
	private JobCode jobCodeRef;
	private String jobTitle;
	private Boolean nonEmployeeFlag;
	private String personId;
    private Integer rolodexId;
    private String tbnId;
	private String appointmentTypeCode;
	private BudgetDecimal calculationBase;
	private String personName;
	private AppointmentType appointmentType;
	private Integer personSequenceNumber;
	private Rolodex rolodex;
    private String role;
    private Date salaryAnniversaryDate;
    
    private transient KcPersonService kcPersonService;

    private String hierarchyProposalNumber;
    private boolean hiddenInHierarchy;

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
    
    public BudgetPerson() {
        super();
    }
    
    public BudgetPerson(KcPerson person) {
        super();
        this.personId = person.getPersonId();
        this.personName = person.getFullName();
        this.salaryAnniversaryDate = person.getExtendedAttributes().getSalaryAnniversaryDate();
        this.nonEmployeeFlag = false;
    }
    
    public BudgetPerson(Rolodex rolodex) {
        super();
        this.rolodexId = rolodex.getRolodexId();
        this.personName = rolodex.getFirstName() + " " + rolodex.getLastName();
        this.nonEmployeeFlag = true;
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
	    if (this.jobCode == null || !this.jobCode.equals(jobCode)){
	       this.jobCode = jobCode;
	       refreshJobTitle();
	    }
	}

	public Boolean getNonEmployeeFlag() {
		return nonEmployeeFlag;
	}

	public void setNonEmployeeFlag(Boolean nonEmployeeFlag) {
		this.nonEmployeeFlag = nonEmployeeFlag;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public AppointmentType getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(AppointmentType appointmentType) {
		this.appointmentType = appointmentType;
	}

	public BudgetDecimal getCalculationBase() {
		return calculationBase;
	}

	public void setCalculationBase(BudgetDecimal calculationBase) {
		this.calculationBase = calculationBase;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}


	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
        LinkedHashMap<String, Object> hashMap = super.toStringMapper();
		hashMap.put("effectiveDate", getEffectiveDate());
		hashMap.put("jobCode", getJobCode());
		hashMap.put("nonEmployeeFlag", getNonEmployeeFlag());
		hashMap.put("personId", getPersonId());
		hashMap.put("appointmentTypeCode", getAppointmentTypeCode());
		hashMap.put("calculationBase", getCalculationBase());
		hashMap.put("personName", getPersonName());
		return hashMap;
	}

    /**
     * Gets the appointmentTypeCode attribute. 
     * @return Returns the appointmentTypeCode.
     */
    public String getAppointmentTypeCode() {
        return appointmentTypeCode;
    }

    /**
     * Sets the appointmentTypeCode attribute value.
     * @param appointmentTypeCode The appointmentTypeCode to set.
     */
    public void setAppointmentTypeCode(String appointmentTypeCode) {
        this.appointmentTypeCode = appointmentTypeCode;
    }
    
    

    /**
     * Gets the rolodexId attribute. 
     * @return Returns the rolodexId.
     */
    public Integer getRolodexId() {
        return rolodexId;
    }

    /**
     * Sets the rolodexId attribute value.
     * @param rolodexId The rolodexId to set.
     */
    public void setRolodexId(Integer rolodexId) {
        this.rolodexId = rolodexId;
    }

    /**
     * Gets the person attribute. 
     * @return Returns the person.
     */
    public KcPerson getPerson() {
        return getKcPersonService().getKcPersonByPersonId(personId);
    }
    
    /**
     * Gets the KC Person Service.
     * @return KC Person Service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KraServiceLocator.getService(KcPersonService.class);
        }
        
        return this.kcPersonService;
    }

    /**
     * Gets the roldex attribute. 
     * @return Returns the roldex.
     */
    public Rolodex getRoldex() {
        return rolodex;
    }

    /**
     * Sets the rolodex attribute value.
     * @param rolodex The rolodex to set.
     */
    public void setRoldex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }

    /**
     * Gets the personSequenceNumber attribute. 
     * @return Returns the personSequenceNumber.
     */
    public Integer getPersonSequenceNumber() {
        return personSequenceNumber;
    }

    /**
     * Sets the personSequenceNumber attribute value.
     * @param personSequenceNumber The personSequenceNumber to set.
     */
    public void setPersonSequenceNumber(Integer personSequenceNumber) {
        this.personSequenceNumber = personSequenceNumber;
    }
    
    public String getTbnId() {
        return tbnId;
    }

    public void setTbnId(String tbnId) {
        this.tbnId = tbnId;
    }

    /**
     * Gets the rolodex attribute. 
     * @return Returns the rolodex.
     */
    public Rolodex getRolodex() {
        return rolodex;
    }

    /**
     * Sets the rolodex attribute value.
     * @param rolodex The rolodex to set.
     */
    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }

    public String getRole() {
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
        if (!StringUtils.equals(this.getJobCode(), budgetPerson.getJobCode())
                || !ObjectUtils.equals(this.getEffectiveDate(), budgetPerson.getEffectiveDate())) {
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
        String rolodexPersonId = getRolodexId()==null?getPersonId():getRolodexId().toString();
        return rolodexPersonId==null?getTbnId():rolodexPersonId;
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
        if (StringUtils.isNotBlank(getJobCode()) && 
                (this.jobCodeRef == null || !StringUtils.isNotBlank(this.jobCodeRef.getJobTitle())) ) { 
                JobCodeService jcService = KraServiceLocator.getService(JobCodeService.class);
                this.jobCodeRef = jcService.findJobCodeRef(getJobCode());
            }
    }

    public JobCode getJobCodeRef() {
        return jobCodeRef;
    }

    public void setJobCodeRef(JobCode jobCodeRef) {
        this.jobCodeRef = jobCodeRef;
    }

    /**
     * Sets the hierarchyProposalNumber attribute value.
     * @param hierarchyProposalNumber The hierarchyProposalNumber to set.
     */
    public void setHierarchyProposalNumber(String hierarchyProposalNumber) {
        this.hierarchyProposalNumber = hierarchyProposalNumber;
    }

    /**
     * Gets the hierarchyProposalNumber attribute. 
     * @return Returns the hierarchyProposalNumber.
     */
    public String getHierarchyProposalNumber() {
        return hierarchyProposalNumber;
    }

    /**
     * Gets the hiddenInHierarchy attribute. 
     * @return Returns the hiddenInHierarchy.
     */
    public boolean isHiddenInHierarchy() {
        return hiddenInHierarchy;
    }

    /**
     * Sets the hiddenInHierarchy attribute value.
     * @param hiddenInHierarchy The hiddenInHierarchy to set.
     */
    public void setHiddenInHierarchy(boolean hiddenInHierarchy) {
        this.hiddenInHierarchy = hiddenInHierarchy;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBudgetId() == null) ? 0 : getBudgetId().hashCode());
        result = prime * result + ((personSequenceNumber == null) ? 0 : personSequenceNumber.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        }
        else if (!getBudgetId().equals(other.getBudgetId()))
            return false;
        if (personSequenceNumber == null) {
            if (other.personSequenceNumber != null)
                return false;
        }
        else if (!personSequenceNumber.equals(other.personSequenceNumber))
            return false;
        return true;
    }

    /**
     * Gets the salaryAnniversaryDate attribute. 
     * @return Returns the salaryAnniversaryDate.
     */
    public Date getSalaryAnniversaryDate() {
        return salaryAnniversaryDate;
    }

    /**
     * Sets the salaryAnniversaryDate attribute value.
     * @param salaryAnniversaryDate The salaryAnniversaryDate to set.
     */
    public void setSalaryAnniversaryDate(Date salaryAnniversaryDate) {
        this.salaryAnniversaryDate = salaryAnniversaryDate;
    }
    
    
    
}
