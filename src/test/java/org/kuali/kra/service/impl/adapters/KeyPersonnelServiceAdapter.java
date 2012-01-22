package org.kuali.kra.service.impl.adapters;

import java.util.Collection;
import java.util.Map;

import org.kuali.kra.budget.personnel.PersonRolodex;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * 
 */
public class KeyPersonnelServiceAdapter implements KeyPersonnelService {
    public void populateDocument(ProposalDevelopmentDocument document) {
        
    }

    public void populateProposalPerson(ProposalPerson person, ProposalDevelopmentDocument document) {
        
    }

    public Collection<InvestigatorCreditType> getInvestigatorCreditTypes() {
        return null;  
    }

    public void addUnitToPerson(ProposalPerson person, ProposalPersonUnit unit) {
        
    }

    public Map calculateCreditSplitTotals(ProposalDevelopmentDocument document) {
        return null;  
    }

    public BusinessObjectService getBusinessObjectService() {
        return null;  
    }

    public void setBusinessObjectService(BusinessObjectService boservice) {
        
    }

    public ProposalPersonUnit createProposalPersonUnit(String unitId, ProposalPerson person) {
        return null;  
    }

    public ProposalPerson createProposalPersonFromPersonId(String personId) {
        return null;  
    }

    public ProposalPerson createProposalPersonFromRolodexId(String rolodexId) {
        return null;  
    }

    public boolean isPrincipalInvestigator(ProposalPerson person) {
        return false;  
    }

    public boolean isCoInvestigator(ProposalPerson person) {
        return false;  
    }

    public boolean isKeyPerson(ProposalPerson person) {
        return false;  
    }

    public boolean isInvestigator(ProposalPerson person) {
        return false;  
    }

    public boolean hasPrincipalInvestigator(ProposalDevelopmentDocument document) {
        return false;  
    }

    public boolean isCreditSplitEnabled() {
        return false;  
    }

    public boolean isRoleReadOnly(String roleId) {
        return false;  
    }

    public boolean isRoleReadOnly(ProposalPersonRole role) {
        return false;  
    }

    public String getPrincipalInvestigatorRoleDescription(ProposalDevelopmentDocument document) {
        return null;  
    }

    public void assignLeadUnit(ProposalPerson person, String unitNumber) {
        
    }

    public boolean isSponsorNIH(ProposalDevelopmentDocument document) {
        return false;  
    }

    public Map<String, String> loadKeyPersonnelRoleDescriptions(boolean sponsorIsNih) {
        return null;  
    }

    public String getPersonnelRoleDesc(PersonRolodex person) {
        return null;
    }
}
