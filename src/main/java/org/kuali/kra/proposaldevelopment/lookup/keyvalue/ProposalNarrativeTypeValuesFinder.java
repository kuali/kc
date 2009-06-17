/*
 * Copyright 2006-2009 The Kuali Foundation
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

import static org.kuali.kra.infrastructure.Constants.PARAMETER_COMPONENT_DOCUMENT;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.rice.kns.lookup.keyvalues.PersistableBusinessObjectValuesFinder;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

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
                    LOG.debug("Not removing narrative type " + narrativeType.getDescription());
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
        BusinessObjectService boService = KNSServiceLocator.getBusinessObjectService();
        KualiConfigurationService configurationService = KNSServiceLocator.getKualiConfigurationService();
        
        String proposalNarrativeTypeGroup = configurationService.getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, PARAMETER_COMPONENT_DOCUMENT, Constants.PROPOSAL_NARRATIVE_TYPE_GROUP);
        Map<String,String> queryMap = new HashMap<String,String>();
        queryMap.put("narrativeTypeGroup", proposalNarrativeTypeGroup);
        queryMap.put("systemGenerated", "N");
        //return boService.findMatchingOrderBy(getBusinessObjectClass(), queryMap, "narrativeTypeCode", true);
        return boService.findMatching(getBusinessObjectClass(), queryMap);
    }

    protected ProposalDevelopmentDocument getDocumentFromForm() {
        return ((ProposalDevelopmentForm) GlobalVariables.getKualiForm()).getDocument();
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
