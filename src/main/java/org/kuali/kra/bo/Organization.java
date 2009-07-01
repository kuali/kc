/*
 * Copyright 2006-2009 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
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
package org.kuali.kra.bo;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.rice.kns.util.TypedArrayList;

public class Organization extends KraPersistableBusinessObjectBase {
	private String organizationId;
	private String address;
	private String agencySymbol;
	private String animalWelfareAssurance;
	private String cableAddress;
	private String cageNumber;
	private Integer cognizantAuditor;
	private String comGovEntityCode;
	private String congressionalDistrict;
	private Integer contactAddressId;
	private String county;
	private String dodacNumber;
	private String dunsNumber;
	private String dunsPlusFourNumber;
	private String federalEmployerId;
	private String humanSubAssurance;
	private Date incorporatedDate;
	private String incorporatedIn;
	private String indirectCostRateAgreement;
	private String irsTaxExemption;
	private String stateEmployeeClaim;
	private String stateTaxExemptNum;
	private String nsfInstitutionalCode;
	private Integer numberOfEmployees;
	private Integer onrResidentRep;
	private String organizationName;
	private String phsAccount;
	private Date scienceMisconductComplDate;
	private String telexNumber;
	private String vendorCode;
	private String fedralEmployerId;
	
	private Rolodex rolodex;
    private List<OrganizationYnq> organizationYnqs;
    private List<OrganizationType> organizationTypes;
    private List<OrganizationIndirectcost> organizationIdcs;
    private List<OrganizationAudit> organizationAudits;
    
    public Organization() {
        super();
        organizationYnqs = new TypedArrayList(OrganizationYnq.class);        
        organizationTypes = new TypedArrayList(OrganizationType.class);        
        organizationIdcs = new TypedArrayList(OrganizationIndirectcost.class);        
        organizationAudits = new TypedArrayList(OrganizationAudit.class);        
    }

    
	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAgencySymbol() {
		return agencySymbol;
	}

	public void setAgencySymbol(String agencySymbol) {
		this.agencySymbol = agencySymbol;
	}

	public String getAnimalWelfareAssurance() {
		return animalWelfareAssurance;
	}

	public void setAnimalWelfareAssurance(String animalWelfareAssurance) {
		this.animalWelfareAssurance = animalWelfareAssurance;
	}

	public String getCableAddress() {
		return cableAddress;
	}

	public void setCableAddress(String cableAddress) {
		this.cableAddress = cableAddress;
	}

	public String getCageNumber() {
		return cageNumber;
	}

	public void setCageNumber(String cageNumber) {
		this.cageNumber = cageNumber;
	}

	public Integer getCognizantAuditor() {
		return cognizantAuditor;
	}

	public void setCognizantAuditor(Integer cognizantAuditor) {
		this.cognizantAuditor = cognizantAuditor;
	}

	public String getComGovEntityCode() {
		return comGovEntityCode;
	}

	public void setComGovEntityCode(String comGovEntityCode) {
		this.comGovEntityCode = comGovEntityCode;
	}

	public String getCongressionalDistrict() {
		return congressionalDistrict;
	}

	public void setCongressionalDistrict(String congressionalDistrict) {
		this.congressionalDistrict = congressionalDistrict;
	}

	public Integer getContactAddressId() {
		return contactAddressId;
	}

	public void setContactAddressId(Integer contactAddressId) {
		this.contactAddressId = contactAddressId;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getDodacNumber() {
		return dodacNumber;
	}

	public void setDodacNumber(String dodacNumber) {
		this.dodacNumber = dodacNumber;
	}

	public String getDunsNumber() {
		return dunsNumber;
	}

	public void setDunsNumber(String dunsNumber) {
		this.dunsNumber = dunsNumber;
	}

	public String getDunsPlusFourNumber() {
		return dunsPlusFourNumber;
	}

	public void setDunsPlusFourNumber(String dunsPlusFourNumber) {
		this.dunsPlusFourNumber = dunsPlusFourNumber;
	}

	public String getFederalEmployerId() {
		return federalEmployerId;
	}

	public void setFederalEmployerId(String federalEmployerId) {
		this.federalEmployerId = federalEmployerId;
	}

	public String getHumanSubAssurance() {
		return humanSubAssurance;
	}

	public void setHumanSubAssurance(String humanSubAssurance) {
		this.humanSubAssurance = humanSubAssurance;
	}

	public Date getIncorporatedDate() {
		return incorporatedDate;
	}

	public void setIncorporatedDate(Date incorporatedDate) {
		this.incorporatedDate = incorporatedDate;
	}

	public String getIncorporatedIn() {
		return incorporatedIn;
	}

	public void setIncorporatedIn(String incorporatedIn) {
		this.incorporatedIn = incorporatedIn;
	}

	public String getIndirectCostRateAgreement() {
		return indirectCostRateAgreement;
	}

	public void setIndirectCostRateAgreement(String indirectCostRateAgreement) {
		this.indirectCostRateAgreement = indirectCostRateAgreement;
	}

	public String getIrsTaxExemption() {
		return irsTaxExemption;
	}

	public void setIrsTaxExemption(String irsTaxExemption) {
		this.irsTaxExemption = irsTaxExemption;
	}

	public String getStateEmployeeClaim() {
		return stateEmployeeClaim;
	}

	public void setStateEmployeeClaim(String stateEmployeeClaim) {
		this.stateEmployeeClaim = stateEmployeeClaim;
	}

	public String getStateTaxExemptNum() {
		return stateTaxExemptNum;
	}

	public void setStateTaxExemptNum(String stateTaxExemptNum) {
		this.stateTaxExemptNum = stateTaxExemptNum;
	}

	public String getNsfInstitutionalCode() {
		return nsfInstitutionalCode;
	}

	public void setNsfInstitutionalCode(String nsfInstitutionalCode) {
		this.nsfInstitutionalCode = nsfInstitutionalCode;
	}

	public Integer getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public void setNumberOfEmployees(Integer numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}

	public Integer getOnrResidentRep() {
		return onrResidentRep;
	}

	public void setOnrResidentRep(Integer onrResidentRep) {
		this.onrResidentRep = onrResidentRep;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getPhsAccount() {
		return phsAccount;
	}

	public void setPhsAccount(String phsAccount) {
		this.phsAccount = phsAccount;
	}

	public Date getScienceMisconductComplDate() {
		return scienceMisconductComplDate;
	}

	public void setScienceMisconductComplDate(Date scienceMisconductComplDate) {
		this.scienceMisconductComplDate = scienceMisconductComplDate;
	}

	public String getTelexNumber() {
		return telexNumber;
	}

	public void setTelexNumber(String telexNumber) {
		this.telexNumber = telexNumber;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}


	@Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("organizationId", getOrganizationId());
		hashMap.put("address", getAddress());
		hashMap.put("agencySymbol", getAgencySymbol());
		hashMap.put("animalWelfareAssurance", getAnimalWelfareAssurance());
		hashMap.put("cableAddress", getCableAddress());
		hashMap.put("cageNumber", getCageNumber());
		hashMap.put("cognizantAuditor", getCognizantAuditor());
		hashMap.put("comGovEntityCode", getComGovEntityCode());
		hashMap.put("congressionalDistrict", getCongressionalDistrict());
		hashMap.put("contactAddressId", getContactAddressId());
		hashMap.put("county", getCounty());
		hashMap.put("dodacNumber", getDodacNumber());
		hashMap.put("dunsNumber", getDunsNumber());
		hashMap.put("dunsPlusFourNumber", getDunsPlusFourNumber());
		hashMap.put("federalEmployerId", getFederalEmployerId());
		hashMap.put("humanSubAssurance", getHumanSubAssurance());
		hashMap.put("incorporatedDate", getIncorporatedDate());
		hashMap.put("incorporatedIn", getIncorporatedIn());
		hashMap.put("indirectCostRateAgreement", getIndirectCostRateAgreement());
		hashMap.put("irsTaxExemption", getIrsTaxExemption());
		hashMap.put("stateEmployeeClaim", getStateEmployeeClaim());
		hashMap.put("stateTaxExemptNum", getStateTaxExemptNum());
		hashMap.put("nsfInstitutionalCode", getNsfInstitutionalCode());
		hashMap.put("numberOfEmployees", getNumberOfEmployees());
		hashMap.put("onrResidentRep", getOnrResidentRep());
		hashMap.put("organizationName", getOrganizationName());
		hashMap.put("phsAccount", getPhsAccount());
		hashMap.put("scienceMisconductComplDate", getScienceMisconductComplDate());
		hashMap.put("telexNumber", getTelexNumber());
		hashMap.put("vendorCode", getVendorCode());
		return hashMap;
	}

    public Rolodex getRolodex() {
        return rolodex;
    }

    public void setRolodex(Rolodex rolodex) {
        this.rolodex = rolodex;
    }

    public List<OrganizationYnq> getOrganizationYnqs() {
        return organizationYnqs;
    }

    public void setOrganizationYnqs(List<OrganizationYnq> organizationYnqs) {
        this.organizationYnqs = organizationYnqs;
    }

    public OrganizationYnq getOrganizationYnq(int index) {
        while (getOrganizationYnqs().size() <= index) {
            getOrganizationYnqs().add(new OrganizationYnq());
        }
        
        return (OrganizationYnq)getOrganizationYnqs().get(index);
    }

    public List<OrganizationType> getOrganizationTypes() {
        return organizationTypes;
    }

    public void setOrganizationTypes(List<OrganizationType> organizationTypes) {
        this.organizationTypes = organizationTypes;
    }

    public OrganizationType getOrganizationType(int index) {
        while (getOrganizationTypes().size() <= index) {
            getOrganizationTypes().add(new OrganizationType());
        }
        
        return (OrganizationType)getOrganizationTypes().get(index);
    }

    public List<OrganizationIndirectcost> getOrganizationIdcs() {
        return organizationIdcs;
    }

    public void setOrganizationIdcs(List<OrganizationIndirectcost> organizationIdcs) {
        this.organizationIdcs = organizationIdcs;
    }

    public OrganizationIndirectcost getOrganizationIdc(int index) {
        while (getOrganizationIdcs().size() <= index) {
            getOrganizationIdcs().add(new OrganizationIndirectcost());
        }
        
        return (OrganizationIndirectcost)getOrganizationIdcs().get(index);
    }

    public List<OrganizationAudit> getOrganizationAudits() {
        return organizationAudits;
    }

    public void setOrganizationAudits(List<OrganizationAudit> organizationAudits) {
        this.organizationAudits = organizationAudits;
    }
    
    public OrganizationAudit getOrganizationAudit(int index) {
        while (getOrganizationAudits().size() <= index) {
            getOrganizationAudits().add(new OrganizationAudit());
        }
        
        return (OrganizationAudit)getOrganizationAudits().get(index);
    }


    public String getFedralEmployerId() {
        return federalEmployerId;
    }


    public void setFedralEmployerId(String federalEmployerId) {
        this.federalEmployerId = federalEmployerId;        
    }


}
