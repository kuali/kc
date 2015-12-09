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
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeType;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.component.BindingInfo;
import org.kuali.rice.krad.uif.field.InputField;
import org.kuali.rice.krad.uif.field.InputFieldBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProposalNarrativeTypeValuesFinderTest extends KcIntegrationTestBase {

    private NarrativeType bioNarrativeType;
    private ProposalDevelopmentDocument document;
    private ProposalNarrativeTypeValuesFinder finder;
    private NarrativeType otherNarrativeType;
    private NarrativeType firstAlphabeticallyNarrativeType;
    private NarrativeType proposalNarrativeType;
    private List<KeyValue> allNarrativeTypesAsKeyValues;
    private Collection<NarrativeType> allNarrativeTypes;
    private Boolean alphabetizeCollection = false;
    
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
        Assert.assertEquals(4, finder.getFilteredKeyValues(copyMasterNarrativeTypeList(),document.getDevelopmentProposal(),"").size());
        Assert.assertEquals(5, finder.getFilteredKeyValues(copyMasterNarrativeTypeList(),document.getDevelopmentProposal(),createNarrative(proposalNarrativeType).getNarrativeTypeCode()).size());
        document.getDevelopmentProposal().getNarratives().clear();
        
        document.getDevelopmentProposal().getNarratives().add(createNarrative(bioNarrativeType));
        Assert.assertEquals(4, finder.getFilteredKeyValues(copyMasterNarrativeTypeList(),document.getDevelopmentProposal(),"").size());
        Assert.assertEquals(5, finder.getFilteredKeyValues(copyMasterNarrativeTypeList(),document.getDevelopmentProposal(),createNarrative(bioNarrativeType).getNarrativeTypeCode()).size());
        document.getDevelopmentProposal().getNarratives().clear();
        
        document.getDevelopmentProposal().getNarratives().add(createNarrative(otherNarrativeType));
        Assert.assertEquals(5, finder.getFilteredKeyValues(copyMasterNarrativeTypeList(),document.getDevelopmentProposal(),"").size());
        document.getDevelopmentProposal().getNarratives().clear();
    }
    
    @Test
    public void testFindingNarrativeValues_alphabetize() throws Exception {
    	this.alphabetizeCollection = true;
        document.getDevelopmentProposal().getNarratives().add(createNarrative(proposalNarrativeType));
        ProposalDevelopmentDocumentForm form = new ProposalDevelopmentDocumentForm();
        form.setDocument(document);
        List<KeyValue> values = finder.getKeyValues(form);
        Assert.assertEquals(4, values.size());
        Assert.assertEquals(firstAlphabeticallyNarrativeType.getCode(), values.get(1).getKey());
        Assert.assertEquals(firstAlphabeticallyNarrativeType.getDescription(), values.get(1).getValue());
        
        InputField typeField = new InputFieldBase();
        typeField.setBindingInfo(new BindingInfo());
        typeField.getBindingInfo().setBindingPath("document.developmentProposal.narratives[0].narrativeTypeCode");
        values = finder.getKeyValues(form, typeField);
        Assert.assertEquals(5, values.size());
        Assert.assertEquals(firstAlphabeticallyNarrativeType.getCode(), values.get(1).getKey());
        Assert.assertEquals(firstAlphabeticallyNarrativeType.getDescription(), values.get(1).getValue());
    }


    private ArrayList<NarrativeType> copyMasterNarrativeTypeList() {
        return new ArrayList<NarrativeType>(allNarrativeTypes);
    }

    private Narrative createNarrative(NarrativeType narrativeType) {
        Narrative narrative = new Narrative();
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
        firstAlphabeticallyNarrativeType = createNarrativeType("4", "AAA1", false);
    }
    
    // Mock out methods that rely on infrastructure services 
    private class MockProposalNarrativeTypeValuesFinder extends ProposalNarrativeTypeValuesFinder {

        @Override
        public Collection<NarrativeType> loadAllNarrativeTypes(DevelopmentProposal developmentProposal) {
            return allNarrativeTypes;
        }
        
        @Override
        protected Boolean shouldAlphabetizeNarrativeTypes() {
        	return alphabetizeCollection;
        }
        
        @Override
        protected NarrativeType getNarrativeType(String narrativeTypeCode) {
        	return proposalNarrativeType;
        }
        
    }
}
