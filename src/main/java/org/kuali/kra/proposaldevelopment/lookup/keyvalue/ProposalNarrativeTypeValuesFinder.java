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
import static org.kuali.kra.infrastructure.Constants.PARAMETER_COMPONENT_DOCUMENT;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT;
import static org.kuali.kra.infrastructure.Constants.PROPOSAL_NARRATIVE_TYPE_GROUP;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.lookup.keyvalues.PersistableBusinessObjectValuesFinder;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.lookup.keyvalue.KeyValueFinderService;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;

/**
 * Finds the available set of supported Narrative Types.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author KRADEV team
 */
public class ProposalNarrativeTypeValuesFinder extends PersistableBusinessObjectValuesFinder {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalNarrativeTypeValuesFinder.class);
    
    private Map<String, String> getQueryMap() {
        Map<String,String> queryMap = new HashMap<String,String>();
        queryMap.put("narrativeTypeGroup", getService(KualiConfigurationService.class).getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, PARAMETER_COMPONENT_DOCUMENT, PROPOSAL_NARRATIVE_TYPE_GROUP));
        queryMap.put("systemGenerated", "N");
        return queryMap;
    }
    
    /**
     * Constructs the list of Proposal Narrative Types. The list populates
     * from NARRATIVE_TYPE database table via the "KeyValueFinderService".
     * 
     * @return the list of &lt;key, value&gt; pairs of Narrative types.  The first entry
     * is always &lt;"", "select:"&gt;.
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinderService#getKeyValues()
     */
    public List<KeyLabelPair> getKeyValues() {
        List<KeyLabelPair> retval = super.getKeyValues();
        ProposalDevelopmentDocument document = ((ProposalDevelopmentForm) getKualiForm()).getProposalDevelopmentDocument();
        List<KeyLabelPair> forRemoval = new ArrayList<KeyLabelPair>();
        
        for (KeyLabelPair pair : retval) {
            if (existsInDocument(document, (String) pair.getKey())) {
                forRemoval.add(pair);
            }
            else {
                LOG.info("Not removing narrative type " + pair.getLabel());
            }
        }
        
        // Remove any KeyLabelPair instances for narratives with these type codes already in the document
        retval.removeAll(forRemoval);
        
        return retval;
    }
    
    /**
     * Used to compare which Narrative Types already exist in the document.
     * 
     * @param document
     * @param narrativeTypeCode <code>{@link NarrativeType}</code> code
     * @return true if the narrative type is in the document and false otherwise
     */
    private boolean existsInDocument(ProposalDevelopmentDocument document, String narrativeTypeCode) {
        for (Narrative narrative : document.getNarratives()) {
            if (narrative.getNarrativeTypeCode().equals(narrativeTypeCode)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Locate within Spring the <code>{@link KeyValueFinderService}</code> singleton
     * 
     * @return KeyValueFinderService
     */
    private KeyValueFinderService getKeyValueFinderService() {
        return getService(KeyValueFinderService.class);
    }
    
    /**
     * Locate within Spring the <code>{@link BusinessObjectSedvice}</code> singleton
     * 
     * @return BusinessObjectService
     */
    private BusinessObjectService getBusinessObjectService() {
        return getService(BusinessObjectService.class);
    }

}
