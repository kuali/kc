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
        Assert.assertEquals(3, finder.getFilteredKeyValues(copyMasterNarrativeTypeList(),document.getDevelopmentProposal(),new Narrative()).size());
        Assert.assertEquals(4, finder.getFilteredKeyValues(copyMasterNarrativeTypeList(),document.getDevelopmentProposal(),createNarrative(proposalNarrativeType)).size());
        document.getDevelopmentProposal().getNarratives().clear();
        
        document.getDevelopmentProposal().getNarratives().add(createNarrative(bioNarrativeType));
        Assert.assertEquals(3, finder.getFilteredKeyValues(copyMasterNarrativeTypeList(),document.getDevelopmentProposal(),new Narrative()).size());
        Assert.assertEquals(4, finder.getFilteredKeyValues(copyMasterNarrativeTypeList(),document.getDevelopmentProposal(),createNarrative(bioNarrativeType)).size());
        document.getDevelopmentProposal().getNarratives().clear();
        
        document.getDevelopmentProposal().getNarratives().add(createNarrative(otherNarrativeType));
        Assert.assertEquals(4, finder.getFilteredKeyValues(copyMasterNarrativeTypeList(),document.getDevelopmentProposal(),new Narrative()).size());
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
