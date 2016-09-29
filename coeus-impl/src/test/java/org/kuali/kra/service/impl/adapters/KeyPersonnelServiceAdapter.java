/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.service.impl.adapters;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelService;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalCreditSplitListDto;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalUnitCreditSplit;

import java.util.Collection;
import java.util.List;
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
    public boolean isCreditSplitEnabled() {
        return false;  
    }
    @Override
    public void assignLeadUnit(ProposalPerson person, String unitNumber) {
        
    }
    @Override
    public void addProposalPerson(ProposalPerson proposalPerson, ProposalDevelopmentDocument document) {
        
    }
    @Override
    public List<ProposalCreditSplitListDto> createCreditSplitListItems(ProposalDevelopmentDocument document) {
        return null;
    }
    @Override
    public List<ProposalUnitCreditSplit> createCreditSplits(ProposalPersonUnit unit){
        return null;
    }

    @Override
    public void populateCreditSplit(ProposalDevelopmentDocument document) {}
    
}
