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

import static org.apache.commons.beanutils.PropertyUtils.getProperty;
import static org.kuali.core.util.GlobalVariables.getKualiForm;
import static org.kuali.core.util.ObjectUtils.equalByKeys;
import static org.kuali.kra.infrastructure.Constants.PROPOSAL_NARRATIVE_TYPE_GROUP;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_COMPONENT_DOCUMENT;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.lookup.keyvalues.PersistableBusinessObjectValuesFinder;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.lookup.keyvalue.ExtendedPersistableBusinessObjectValuesFinder;
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
        String proposalNarrativeTypeGroup = getService(KualiConfigurationService.class).getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, PARAMETER_COMPONENT_DOCUMENT, PROPOSAL_NARRATIVE_TYPE_GROUP);
        Map<String,String> queryMap = new HashMap<String,String>();
        queryMap.put("narrativeTypeGroup", proposalNarrativeTypeGroup);
        queryMap.put("systemGenerated", "N");

        ProposalDevelopmentDocument document = ((ProposalDevelopmentForm) getKualiForm()).getProposalDevelopmentDocument();
        
        try {
            for (NarrativeType ntype : (Collection<NarrativeType>) getBusinessObjectService().findMatching(NarrativeType.class,queryMap)) {
                if (!existsInDocument(document, ntype)) {
                    Object key = getProperty(ntype, getKeyAttributeName());
                    String label = (String)getProperty(ntype, getLabelAttributeName());
                    if (isIncludeKeyInDescription()) {
                        label = key + " - " + label;
                    }
                    retval.add(new KeyLabelPair(key, label));
                }
            }
        } catch (IllegalAccessException e) {
            LOG.debug(e.getMessage(), e);
            LOG.error(e.getMessage());
            throw new RuntimeException("IllegalAccessException occurred while trying to build keyValues List. businessObjectClass: " + getBusinessObjectClass() + "; keyAttributeName: " + getKeyAttributeName() + "; labelAttributeName: " + getLabelAttributeName() + "; includeKeyInDescription: " + isIncludeKeyInDescription(), e);
        } catch (InvocationTargetException e) {
            LOG.debug(e.getMessage(), e);
            LOG.error(e.getMessage());
            throw new RuntimeException("InvocationTargetException occurred while trying to build keyValues List. businessObjectClass: " + getBusinessObjectClass() + "; keyAttributeName: " + getKeyAttributeName() + "; labelAttributeName: " + getLabelAttributeName() + "; includeKeyInDescription: " + isIncludeKeyInDescription(), e);
        } catch (NoSuchMethodException e) {
            LOG.debug(e.getMessage(), e);
            LOG.error(e.getMessage());
            throw new RuntimeException("NoSuchMethodException occurred while trying to build keyValues List. businessObjectClass: " + getBusinessObjectClass() + "; keyAttributeName: " + getKeyAttributeName() + "; labelAttributeName: " + getLabelAttributeName() + "; includeKeyInDescription: " + isIncludeKeyInDescription(), e);
        }
        return retval;
    }
    
    private boolean existsInDocument(ProposalDevelopmentDocument document, NarrativeType nt) {
        for (Narrative narrative : document.getNarratives()) {
            if (narrative.getNarrativeTypeCode().equals(nt.getNarrativeTypeCode())) {
                return true;
            }
        }
        
        return false;
    }
    
    private KeyValueFinderService getKeyValueFinderService() {
        return getService(KeyValueFinderService.class);
    }
    
    private BusinessObjectService getBusinessObjectService() {
        return getService(BusinessObjectService.class);
    }

}
