package org.kuali.kra.bo;

import java.sql.Date;
import java.util.LinkedHashMap;

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
	private String fedralEmployerId;
	private String humanSubAssurance;
	private Date incorporatedDate;
	private String incorporatedIn;
	private String indirectCostRateAgreement;
	private String irsTaxExcemption;
	private String massEmployeeClaim;
	private String massTaxExcemptNum;
	private String nsfInstitutionalCode;
	private Integer numberOfEmployees;
	private Integer onrResidentRep;
	private String organizationName;
	private String phsAcount;
	private Date scienceMisconductComplDate;
	private String telexNumber;
	private String vendorCode;
	private Rolodex rolodex;

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

	public String getFedralEmployerId() {
		return fedralEmployerId;
	}

	public void setFedralEmployerId(String fedralEmployerId) {
		this.fedralEmployerId = fedralEmployerId;
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

	public String getIrsTaxExcemption() {
		return irsTaxExcemption;
	}

	public void setIrsTaxExcemption(String irsTaxExcemption) {
		this.irsTaxExcemption = irsTaxExcemption;
	}

	public String getMassEmployeeClaim() {
		return massEmployeeClaim;
	}

	public void setMassEmployeeClaim(String massEmployeeClaim) {
		this.massEmployeeClaim = massEmployeeClaim;
	}

	public String getMassTaxExcemptNum() {
		return massTaxExcemptNum;
	}

	public void setMassTaxExcemptNum(String massTaxExcemptNum) {
		this.massTaxExcemptNum = massTaxExcemptNum;
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

	public String getPhsAcount() {
		return phsAcount;
	}

	public void setPhsAcount(String phsAcount) {
		this.phsAcount = phsAcount;
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
		hashMap.put("fedralEmployerId", getFedralEmployerId());
		hashMap.put("humanSubAssurance", getHumanSubAssurance());
		hashMap.put("incorporatedDate", getIncorporatedDate());
		hashMap.put("incorporatedIn", getIncorporatedIn());
		hashMap.put("indirectCostRateAgreement", getIndirectCostRateAgreement());
		hashMap.put("irsTaxExcemption", getIrsTaxExcemption());
		hashMap.put("massEmployeeClaim", getMassEmployeeClaim());
		hashMap.put("massTaxExcemptNum", getMassTaxExcemptNum());
		hashMap.put("nsfInstitutionalCode", getNsfInstitutionalCode());
		hashMap.put("numberOfEmployees", getNumberOfEmployees());
		hashMap.put("onrResidentRep", getOnrResidentRep());
		hashMap.put("organizationName", getOrganizationName());
		hashMap.put("phsAcount", getPhsAcount());
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
}
