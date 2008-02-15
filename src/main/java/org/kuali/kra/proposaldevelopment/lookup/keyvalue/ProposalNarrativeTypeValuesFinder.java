/*
 * Copyright 2007 The Kuali Foundation.
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

import static org.kuali.core.util.GlobalVariables.getKualiForm;

import gov.grants.apply.system.Global_V1_0.YesNoType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.core.lookup.keyvalues.PersistableBusinessObjectValuesFinder;
import org.kuali.core.service.KeyValuesService;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.rice.KNSServiceLocator;

/**
 * Finds the available set of supported Narrative Types.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author KRADEV team
 * 
 * Rewrite of class to implement filterable KeyLabelPair list.
 * 
 * A complete rewrite had to be done because super class was not editable, but the patterns used 
 * here should be incorporated into super class. This class would then be refactored to again rely
 * more on super class behavior. Super class rewrite should include isolating behaviors relying on 
 * infrastructure into protected methods to allow unit tests to override with mocks and stub out 
 * database and service lookups. 
 */
public class ProposalNarrativeTypeValuesFinder extends PersistableBusinessObjectValuesFinder {
    private static final String NO = "N";
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalNarrativeTypeValuesFinder.class);
    
    @Override
    public List<KeyLabelPair> getKeyValues() {
        Collection<NarrativeType> allNarrativeTypes = loadAllNarrativeTypes();
        return getFilteredKeyValues(allNarrativeTypes);
    }
    
    public List<KeyLabelPair> getFilteredKeyValues(Collection<NarrativeType> allNarrativeTypes) {
        Collection<NarrativeType> filteredCollection = filterCollection(allNarrativeTypes);
        return buildKeyLabelPairsCollection(filteredCollection);
        
    }

    protected Collection<NarrativeType> filterCollection(Collection<NarrativeType> narrativeTypes) {
        ProposalDevelopmentDocument document = getDocumentFromForm();
        Collection<NarrativeType> forRemoval = new ArrayList<NarrativeType>();        
        for (NarrativeType narrativeType : narrativeTypes) {
            for (Narrative narrative : document.getNarratives()) {
                if (filterCondition(narrative.getNarrativeType(), narrativeType)) {
                    forRemoval.add(narrativeType);
                } else {
                    LOG.info("Not removing narrative type " + narrativeType.getDescription());
                }
            }            
        }
        
        // Remove any instances for with these type codes already in the document
        narrativeTypes.removeAll(forRemoval);
        
        return narrativeTypes;
    }

    private List<KeyLabelPair> buildKeyLabelPairsCollection(Collection<NarrativeType> narrativeTypes) {
        List<KeyLabelPair> keyLabelPairs = new ArrayList<KeyLabelPair>();
        for (NarrativeType narrativeType : narrativeTypes) {
            String key = narrativeType.getNarrativeTypeCode();
            String label = narrativeType.getDescription();
            keyLabelPairs.add(new KeyLabelPair(key, label));
        }
        return keyLabelPairs;
    }
    
    @SuppressWarnings("unchecked")
    protected Collection<NarrativeType> loadAllNarrativeTypes() {
        KeyValuesService boService = KNSServiceLocator.getKeyValuesService();
        return (Collection<NarrativeType>) boService.findAll(getBusinessObjectClass());
    }

    protected ProposalDevelopmentDocument getDocumentFromForm() {
        return ((ProposalDevelopmentForm) getKualiForm()).getProposalDevelopmentDocument();
    }
    
    /**
     * Used to compare which Narrative Types already exist in the document.
     * 
     * @param document
     * @param narrativeTypeCode <code>{@link NarrativeType}</code> code
     * @return true if the narrative type is in the document and false otherwise
     */
    protected boolean filterCondition(NarrativeType documentNarrativeType, NarrativeType narrativeType) {
        return documentNarrativeType.getNarrativeTypeCode().equals(narrativeType.getNarrativeTypeCode()) && multiplesNotAllowed(narrativeType);
    }

    private boolean multiplesNotAllowed(NarrativeType narrativeType) {
        return narrativeType.getAllowMultiple().equals(NO);
    }
}
