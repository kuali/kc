package org.kuali.kra.bo;

import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Table;
import javax.persistence.Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.kuali.core.util.TypedArrayList;

@Entity
@Table(name="ORGANIZATION")
public class Organization extends KraPersistableBusinessObjectBase {
	@Id
	@Column(name="ORGANIZATION_ID")
	private String organizationId;
	@Column(name="ADDRESS")
	private String address;
	@Column(name="AGENCY_SYMBOL")
	private String agencySymbol;
	@Column(name="ANIMAL_WELFARE_ASSURANCE")
	private String animalWelfareAssurance;
	@Column(name="CABLE_ADDRESS")
	private String cableAddress;
	@Column(name="CAGE_NUMBER")
	private String cageNumber;
	@Column(name="COGNIZANT_AUDITOR")
	private Integer cognizantAuditor;
	@Column(name="COM_GOV_ENTITY_CODE")
	private String comGovEntityCode;
	@Column(name="CONGRESSIONAL_DISTRICT")
	private String congressionalDistrict;
	@Column(name="CONTACT_ADDRESS_ID")
	private Integer contactAddressId;
	@Column(name="COUNTY")
	private String county;
	@Column(name="DODAC_NUMBER")
	private String dodacNumber;
	@Column(name="DUNS_NUMBER")
	private String dunsNumber;
	@Column(name="DUNS_PLUS_FOUR_NUMBER")
	private String dunsPlusFourNumber;
	@Column(name="FEDRAL_EMPLOYER_ID")
	private String federalEmployerId;
	@Column(name="HUMAN_SUB_ASSURANCE")
	private String humanSubAssurance;
	@Column(name="INCORPORATED_DATE")
	private Date incorporatedDate;
	@Column(name="INCORPORATED_IN")
	private String incorporatedIn;
	@Column(name="INDIRECT_COST_RATE_AGREEMENT")
	private String indirectCostRateAgreement;
	@Column(name="IRS_TAX_EXCEMPTION")
	private String irsTaxExemption;
	@Column(name="MASS_EMPLOYEE_CLAIM")
	private String stateEmployeeClaim;
	@Column(name="MASS_TAX_EXCEMPT_NUM")
	private String stateTaxExemptNum;
	@Column(name="NSF_INSTITUTIONAL_CODE")
	private String nsfInstitutionalCode;
	@Column(name="NUMBER_OF_EMPLOYEES")
	private Integer numberOfEmployees;
	@Column(name="ONR_RESIDENT_REP")
	private Integer onrResidentRep;
	@Column(name="ORGANIZATION_NAME")
	private String organizationName;
	@Column(name="PHS_ACOUNT")
	private String phsAccount;
	@Column(name="SCIENCE_MISCONDUCT_COMPL_DATE")
	private Date scienceMisconductComplDate;
	@Column(name="TELEX_NUMBER")
	private String telexNumber;
	@Column(name="VENDOR_CODE")
	private String vendorCode;
	
	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST})
	@JoinColumn(name="CONTACT_ADDRESS_ID", insertable=false, updatable=false)
	private Rolodex rolodex;
	
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.bo.OrganizationYnq.class, mappedBy="organization")
	private List<OrganizationYnq> organizationYnqs;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.bo.OrganizationType.class, mappedBy="organization")
	private List<OrganizationType> organizationTypes;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.bo.OrganizationIndirectcost.class, mappedBy="organization")
	private List<OrganizationIndirectcost> organizationIdcs;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, 
           targetEntity=org.kuali.kra.bo.OrganizationAudit.class, mappedBy="organization")
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


}

