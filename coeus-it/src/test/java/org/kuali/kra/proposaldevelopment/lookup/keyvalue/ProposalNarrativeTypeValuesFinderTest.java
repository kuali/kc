/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.attachment.ProposalNarrativeTypeValuesFinder;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeType;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProposalNarrativeTypeValuesFinderTest extends KcIntegrationTestBase {

    private NarrativeType bioNarrativeType;
    private ProposalDevelopmentDocument document;
    private ProposalNarrativeTypeValuesFinder finder;
    private NarrativeType otherNarrativeType;
    private NarrativeType proposalNarrativeType;
    private List<KeyValue> allNarrativeTypesAsKeyValues;
    private Collection<NarrativeType> allNarrativeTypes;
    
    @Before
    public void setUp() throws Exception {
        initNarrativeTypes();
        initDocument();
        finder = getFinder();
    }
    
    @After
    public void tearDown() throws Exception {
        document = null;
        otherNarrativeType = null;
    }

    @Test
    public void testFindingNarrativeValues() throws Exception {
        document.getDevelopmentProposal().getNarratives().add(createNarrative(proposalNarrativeType));
        Assert.assertEquals(3, finder.getFilteredKeyValues(copyMasterNarrativeTypeList(),document.getDevelopmentProposal(),"").size());
        Assert.assertEquals(4, finder.getFilteredKeyValues(copyMasterNarrativeTypeList(),document.getDevelopmentProposal(),createNarrative(proposalNarrativeType).getNarrativeTypeCode()).size());
        document.getDevelopmentProposal().getNarratives().clear();
        
        document.getDevelopmentProposal().getNarratives().add(createNarrative(bioNarrativeType));
        Assert.assertEquals(3, finder.getFilteredKeyValues(copyMasterNarrativeTypeList(),document.getDevelopmentProposal(),"").size());
        Assert.assertEquals(4, finder.getFilteredKeyValues(copyMasterNarrativeTypeList(),document.getDevelopmentProposal(),createNarrative(bioNarrativeType).getNarrativeTypeCode()).size());
        document.getDevelopmentProposal().getNarratives().clear();
        
        document.getDevelopmentProposal().getNarratives().add(createNarrative(otherNarrativeType));
        Assert.assertEquals(4, finder.getFilteredKeyValues(copyMasterNarrativeTypeList(),document.getDevelopmentProposal(),"").size());
        document.getDevelopmentProposal().getNarratives().clear();
    }

    private ArrayList<NarrativeType> copyMasterNarrativeTypeList() {
        return new ArrayList<NarrativeType>(allNarrativeTypes);
    }

    private Narrative createNarrative(NarrativeType narrativeType) {
        Narrative narrative = new MockNarrative();
        narrative.setNarrativeType(narrativeType);
        narrative.setNarrativeTypeCode(narrativeType.getCode());
        
        return narrative;
    }

    private NarrativeType createNarrativeType(String narrativeTypeCode, String narrativeDescription, boolean allowMultiple) {
        NarrativeType narrativeType = new NarrativeType();
        narrativeType.setCode(narrativeTypeCode);
        narrativeType.setDescription(narrativeDescription);
        narrativeType.setAllowMultiple(allowMultiple);
        allNarrativeTypes.add(narrativeType);
        allNarrativeTypesAsKeyValues.add(new ConcreteKeyValue(narrativeTypeCode ,narrativeDescription));
        
        return narrativeType;
    }

    private ProposalNarrativeTypeValuesFinder getFinder() {
        ProposalNarrativeTypeValuesFinder finder = new MockProposalNarrativeTypeValuesFinder();

        return finder;
    }
    
    private void initDocument() {
        document = new ProposalDevelopmentDocument();
    }

    private void initNarrativeTypes() {
        allNarrativeTypes = new ArrayList<NarrativeType>();
        allNarrativeTypesAsKeyValues = new ArrayList<KeyValue>();
        proposalNarrativeType = createNarrativeType("1", "Proposal", false);
        bioNarrativeType = createNarrativeType("2", "Bio Sketch", false);
        otherNarrativeType = createNarrativeType("3", "Other", true);        
    }
    
    // Mock out methods that rely on infrastructure services
    @SuppressWarnings("serial")
    private class MockNarrative extends Narrative {


        
    }
    
    // Mock out methods that rely on infrastructure services 
    private class MockProposalNarrativeTypeValuesFinder extends ProposalNarrativeTypeValuesFinder {

        @Override
        public Collection<NarrativeType> loadAllNarrativeTypes(DevelopmentProposal developmentProposal) {
            return allNarrativeTypes;
        }
        
    }
}
