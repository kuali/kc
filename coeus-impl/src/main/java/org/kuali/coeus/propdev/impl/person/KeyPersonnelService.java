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
package org.kuali.coeus.propdev.impl.person;

import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.common.framework.type.InvestigatorCreditType;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalCreditSplitListDto;
import org.kuali.coeus.propdev.impl.person.creditsplit.ProposalUnitCreditSplit;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * A Service for persisted modifications of Key Personnel related business objects
 *
 * @see org.kuali.coeus.propdev.impl.person.ProposalPerson
 * @author $Author: gmcgrego $
 * @version $Revision: 1.11 $
 */
public interface KeyPersonnelService {
    /**
     * Part of a complete breakfast, it has everything you need to populate Key Personnel into a <code>{@link ProposalDevelopmentDocument}</code>
     * 
     * @param document
     */
    public void populateDocument(ProposalDevelopmentDocument document);

    /**
     * Part of a complete breakfast, it has everything you need to populate Key Personnel
     * 
     * @param person
     * @param document
     */
    public void populateProposalPerson(ProposalPerson person, ProposalDevelopmentDocument document);
    
    /**
     * Create a <code>{@link Collection}</code> from all the ACTIVE <code>{@link InvestigatorCreditTypes}</code>
     * stored persistently.
     * 
     * @return a <code>{@link Collection}</code> of <code>{@link InvestigatorCreditType}</code> instances.
     */
    public Collection<InvestigatorCreditType> getInvestigatorCreditTypes();

    /**
     * Add a <code>{@link ProposalPersonUnit}</code> instance to a <code>{@link ProposalPerson}</code>. 
     * 
     * @param person
     * @param unit
     */
    public void addUnitToPerson(ProposalPerson person, ProposalPersonUnit unit);

    /**
     * Everytime something changes that will effect credit split values, this gets called to generate a graph of the
     * new data.
     *
     * @param document
     * @return Map
     */
    public Map calculateCreditSplitTotals(ProposalDevelopmentDocument document);

    /**
     * Uses a <code>{@link Unit}</code> obtained from the <code>{@link Unit}</code> lookup
     * to create a <code>{@link ProposalPersonUnit}</code> instance.
     *
     * @param unitId
     * @return ProposalPersonUnit
     */
    public ProposalPersonUnit createProposalPersonUnit(String unitId, ProposalPerson person);        

    /**
     * Checks if the application-level configuration parameter for credit splits is enabled. This allows credit splits to be used within the application
     * 
     * 
     * @return true if credit split enabled configuration parameter is on
     * @see org.kuali.coeus.propdev.impl.person.creditsplit.CreditSplit
     */
    public boolean isCreditSplitEnabled();
    
    /**
     * Assigns the lead unit of the proposal to the given principal investigator
     *
     * @param document
     * @param person Principal 
     */
    
    public void assignLeadUnit(ProposalPerson person, String unitNumber);
    
    public void addProposalPerson(ProposalPerson proposalPerson, ProposalDevelopmentDocument document);
    
    public boolean isValidHomeUnit(ProposalPerson person, String unitId);

    public List<ProposalCreditSplitListDto> createCreditSplitListItems(List<ProposalPerson> investigators);

    public List<ProposalUnitCreditSplit> createCreditSplits(ProposalPersonUnit unit);

    public void populateCreditSplit(ProposalDevelopmentDocument document);

    }
