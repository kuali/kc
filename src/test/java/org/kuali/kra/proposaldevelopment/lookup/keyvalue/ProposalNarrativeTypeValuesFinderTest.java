/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

public class ProposalNarrativeTypeValuesFinderTest {

    private static final String QUICKSTART = "quickstart";
    private static final String NO = "N";
    private static final String YES = "Y";
    private NarrativeType bioNarrativeType;
    private ProposalDevelopmentDocument document;
    private ProposalNarrativeTypeValuesFinder finder;
    private NarrativeType otherNarrativeType;
    private NarrativeType proposalNarrativeType;
    private List<KeyLabelPair> allNarrativeTypesAsKeyLabelPairs;
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
        document.getNarratives().add(createNarrative(proposalNarrativeType));
        Assert.assertEquals(2, finder.getFilteredKeyValues(copyMasterNarrativeTypeList()).size());
        document.getNarratives().clear();
        
        document.getNarratives().add(createNarrative(bioNarrativeType));
        Assert.assertEquals(2, finder.getFilteredKeyValues(copyMasterNarrativeTypeList()).size());
        document.getNarratives().clear();
        
        document.getNarratives().add(createNarrative(otherNarrativeType));
        Assert.assertEquals(3, finder.getFilteredKeyValues(copyMasterNarrativeTypeList()).size());
        document.getNarratives().clear();
    }

    private ArrayList<NarrativeType> copyMasterNarrativeTypeList() {
        return new ArrayList<NarrativeType>(allNarrativeTypes);
    }

    private Narrative createNarrative(NarrativeType narrativeType) {
        Narrative narrative = new MockNarrative();
        narrative.setNarrativeType(narrativeType);
        narrative.setNarrativeTypeCode(narrativeType.getNarrativeTypeCode());
        
        return narrative;
    }

    private NarrativeType createNarrativeType(String narrativeTypeCode, String narrativeDescription, boolean allowMultiple) {
        NarrativeType narrativeType = new NarrativeType();
        narrativeType.setNarrativeTypeCode(narrativeTypeCode);
        narrativeType.setDescription(narrativeDescription);
        narrativeType.setAllowMultiple(allowMultiple ? YES : NO);
        allNarrativeTypes.add(narrativeType);
        allNarrativeTypesAsKeyLabelPairs.add(new KeyLabelPair(narrativeTypeCode ,narrativeDescription));
        
        return narrativeType;
    }

    private ProposalNarrativeTypeValuesFinder getFinder() {
        ProposalNarrativeTypeValuesFinder finder = new MockProposalNarrativeTypeValuesFinder();
        finder.setBusinessObjectClass(NarrativeType.class);
        finder.setIncludeKeyInDescription(false);
        finder.setKeyAttributeName("narrativeTypeCode");
        finder.setLabelAttributeName("description");
        
        return finder;
    }
    
    private void initDocument() {
        document = new ProposalDevelopmentDocument();
    }

    private void initNarrativeTypes() {
        allNarrativeTypes = new ArrayList<NarrativeType>();
        allNarrativeTypesAsKeyLabelPairs = new ArrayList<KeyLabelPair>();
        proposalNarrativeType = createNarrativeType("1", "Proposal", false);
        bioNarrativeType = createNarrativeType("2", "Bio Sketch", false);
        otherNarrativeType = createNarrativeType("3", "Other", true);        
    }
    
    // Mock out methods that rely on infrastructure services
    @SuppressWarnings("serial")
    private class MockNarrative extends Narrative {

        @Override
        protected String findLoggedInUserPersonId() {
            return QUICKSTART;
        }
        
    }
    
    // Mock out methods that rely on infrastructure services 
    private class MockProposalNarrativeTypeValuesFinder extends ProposalNarrativeTypeValuesFinder {
 
        @Override
        protected ProposalDevelopmentDocument getDocumentFromForm() {
            return document;
        }

        @Override
        public Collection<NarrativeType> loadAllNarrativeTypes() {
            return allNarrativeTypes;
        }
        
    }
}
