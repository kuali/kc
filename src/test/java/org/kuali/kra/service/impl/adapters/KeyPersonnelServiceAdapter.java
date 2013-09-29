package org.kuali.kra.service.impl.adapters;

import org.kuali.kra.budget.personnel.PersonRolodex;
import org.kuali.kra.proposaldevelopment.bo.InvestigatorCreditType;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;

import java.util.Collection;
import java.util.Map;

/**
 * 
 */
public class KeyPersonnelServiceAdapter implements KeyPersonnelService {
    @Override
    public void populateDocument(ProposalDevelopmentDocument document) {
        
    }
    @Override
    public void populateProposalPerson(ProposalPerson person, ProposalDevelopmentDocument document) {
        
    }
    @Override
    public Collection<InvestigatorCreditType> getInvestigatorCreditTypes() {
        return null;  
    }
    @Override
    public void addUnitToPerson(ProposalPerson person, ProposalPersonUnit unit) {
        
    }
    @Override
    public Map calculateCreditSplitTotals(ProposalDevelopmentDocument document) {
        return null;  
    }

    @Override
    public ProposalPersonUnit createProposalPersonUnit(String unitId, ProposalPerson person) {
        return null;  
    }

    @Override
    public boolean isPrincipalInvestigator(ProposalPerson person) {
        return false;  
    }
    @Override
    public boolean isCoInvestigator(ProposalPerson person) {
        return false;  
    }
    @Override
    public boolean isKeyPerson(ProposalPerson person) {
        return false;  
    }
    @Override
    public boolean isInvestigator(ProposalPerson person) {
        return false;  
    }
    @Override
    public boolean hasPrincipalInvestigator(ProposalDevelopmentDocument document) {
        return false;  
    }
    @Override
    public boolean isCreditSplitEnabled() {
        return false;  
    }

    @Override
    public boolean isRoleReadOnly(ProposalPersonRole role) {
        return false;  
    }
    @Override
    public String getPrincipalInvestigatorRoleDescription(ProposalDevelopmentDocument document) {
        return null;  
    }
    @Override
    public void assignLeadUnit(ProposalPerson person, String unitNumber) {
        
    }

    @Override
    public Map<String, String> loadKeyPersonnelRoleDescriptions(boolean sponsorIsNih) {
        return null;  
    }
    @Override
    public String getPersonnelRoleDesc(PersonRolodex person) {
        return null;
    }
}
