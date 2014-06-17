package org.kuali.coeus.common.api.org;

import org.kuali.coeus.common.api.org.audit.OrganizationAuditContract;
import org.kuali.coeus.common.api.org.type.OrganizationTypeContract;

import java.util.Date;
import java.util.List;

public interface OrganizationContract {

    String getOrganizationId();

    String getAddress();

    String getAgencySymbol();

    String getAnimalWelfareAssurance();

    String getCableAddress();

    String getCageNumber();

    Integer getCognizantAuditor();

    String getComGovEntityCode();

    String getCongressionalDistrict();

    Integer getContactAddressId();

    String getCounty();

    String getDodacNumber();

    String getDunsNumber();

    String getDunsPlusFourNumber();

    String getFederalEmployerId();

    String getHumanSubAssurance();

    Date getIncorporatedDate();

    String getIncorporatedIn();

    String getIndirectCostRateAgreement();

    String getIrsTaxExemption();

    String getStateEmployeeClaim();

    String getStateTaxExemptNum();

    String getNsfInstitutionalCode();

    Integer getNumberOfEmployees();

    Integer getOnrResidentRep();

    String getOrganizationName();

    String getPhsAccount();

    Date getScienceMisconductComplDate();

    String getTelexNumber();

    String getVendorCode();

    List<? extends OrganizationYnqContract> getOrganizationYnqs();

    List<? extends OrganizationTypeContract> getOrganizationTypes();

    List<? extends OrganizationIndirectcostContract> getOrganizationIdcs();

    List<? extends OrganizationAuditContract> getOrganizationAudits();
}
