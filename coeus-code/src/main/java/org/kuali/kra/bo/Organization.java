/*
 * Copyright 2005-2013 The Kuali Foundation
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

import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.springframework.util.AutoPopulatingList;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "ORGANIZATION")
public class Organization extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 2010946634885248282L;

    @Id
    @Column(name = "ORGANIZATION_ID")
    private String organizationId;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "AGENCY_SYMBOL")
    private String agencySymbol;

    @Column(name = "ANIMAL_WELFARE_ASSURANCE")
    private String animalWelfareAssurance;

    @Column(name = "CABLE_ADDRESS")
    private String cableAddress;

    @Column(name = "CAGE_NUMBER")
    private String cageNumber;

    @Column(name = "COGNIZANT_AUDITOR")
    private Integer cognizantAuditor;

    @ManyToOne(targetEntity = Rolodex.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "COGNIZANT_AUDITOR", referencedColumnName = "ROLODEX_ID", insertable = false, updatable = false)
    private Rolodex cognizantAuditorRolodex;

    @Column(name = "COM_GOV_ENTITY_CODE")
    private String comGovEntityCode;

    @Column(name = "CONGRESSIONAL_DISTRICT")
    private String congressionalDistrict;

    @Column(name = "CONTACT_ADDRESS_ID")
    private Integer contactAddressId;

    @Column(name = "COUNTY")
    private String county;

    @Column(name = "DODAC_NUMBER")
    private String dodacNumber;

    @Column(name = "DUNS_NUMBER")
    private String dunsNumber;

    @Column(name = "DUNS_PLUS_FOUR_NUMBER")
    private String dunsPlusFourNumber;

    @Column(name = "FEDRAL_EMPLOYER_ID")
    private String federalEmployerId;

    @Column(name = "HUMAN_SUB_ASSURANCE")
    private String humanSubAssurance;

    @Column(name = "INCORPORATED_DATE")
    private Date incorporatedDate;

    @Column(name = "INCORPORATED_IN")
    private String incorporatedIn;

    @Column(name = "INDIRECT_COST_RATE_AGREEMENT")
    private String indirectCostRateAgreement;

    @Column(name = "IRS_TAX_EXCEMPTION")
    private String irsTaxExemption;

    @Column(name = "MASS_EMPLOYEE_CLAIM")
    private String stateEmployeeClaim;

    @Column(name = "MASS_TAX_EXCEMPT_NUM")
    private String stateTaxExemptNum;

    @Column(name = "NSF_INSTITUTIONAL_CODE")
    private String nsfInstitutionalCode;

    @Column(name = "NUMBER_OF_EMPLOYEES")
    private Integer numberOfEmployees;

    @Column(name = "ONR_RESIDENT_REP")
    private Integer onrResidentRep;

    @ManyToOne(targetEntity = Rolodex.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "ONR_RESIDENT_REP", referencedColumnName = "ROLODEX_ID", insertable = false, updatable = false)
    private Rolodex onrResidentRepRolodex;

    @Column(name = "ORGANIZATION_NAME")
    private String organizationName;

    @Column(name = "PHS_ACOUNT")
    private String phsAccount;

    @Column(name = "SCIENCE_MISCONDUCT_COMPL_DATE")
    private Date scienceMisconductComplDate;

    @Column(name = "TELEX_NUMBER")
    private String telexNumber;

    @Column(name = "VENDOR_CODE")
    private String vendorCode;

    @ManyToOne(targetEntity = Rolodex.class, cascade = { CascadeType.REFRESH })
    @JoinColumn(name = "CONTACT_ADDRESS_ID", referencedColumnName = "ROLODEX_ID", insertable = false, updatable = false)
    private Rolodex rolodex;

    @OneToMany(mappedBy = "organization")
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORGANIZATION_ID", insertable = false, updatable = false)
    private List<OrganizationYnq> organizationYnqs;

    @OneToMany(targetEntity = OrganizationType.class, fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST })
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORGANIZATION_ID", insertable = false, updatable = false)
    private List<OrganizationType> organizationTypes;

    @OneToMany(mappedBy = "organization")
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORGANIZATION_ID", insertable = false, updatable = false)
    private List<OrganizationIndirectcost> organizationIdcs;

    @OneToMany(mappedBy = "organization")
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ORGANIZATION_ID", insertable = false, updatable = false)
    private List<OrganizationAudit> organizationAudits;

    @SuppressWarnings("unchecked")
    public Organization() {
        super();
        organizationYnqs = new AutoPopulatingList<OrganizationYnq>(OrganizationYnq.class);
        organizationTypes = new AutoPopulatingList<OrganizationType>(OrganizationType.class);
        organizationIdcs = new AutoPopulatingList<OrganizationIndirectcost>(OrganizationIndirectcost.class);
        organizationAudits = new AutoPopulatingList<OrganizationAudit>(OrganizationAudit.class);
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
        return (OrganizationYnq) getOrganizationYnqs().get(index);
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
        return (OrganizationType) getOrganizationTypes().get(index);
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
        return (OrganizationIndirectcost) getOrganizationIdcs().get(index);
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
        return (OrganizationAudit) getOrganizationAudits().get(index);
    }

    public String getFedralEmployerId() {
        return federalEmployerId;
    }

    public void setFedralEmployerId(String federalEmployerId) {
        this.federalEmployerId = federalEmployerId;
    }

    public Rolodex getCognizantAuditorRolodex() {
        return cognizantAuditorRolodex;
    }

    public void setCognizantAuditorRolodex(Rolodex cognizantAuditorRolodex) {
        this.cognizantAuditorRolodex = cognizantAuditorRolodex;
    }

    public Rolodex getOnrResidentRepRolodex() {
        return onrResidentRepRolodex;
    }

    public void setOnrResidentRepRolodex(Rolodex onrResidentRepRolodex) {
        this.onrResidentRepRolodex = onrResidentRepRolodex;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.add(this.organizationTypes);
        return managedLists;
    }
}
