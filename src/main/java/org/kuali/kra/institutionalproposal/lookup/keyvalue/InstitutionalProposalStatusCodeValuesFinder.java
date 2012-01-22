/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.institutionalproposal.lookup.keyvalue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.ProposalStatus;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.KeyValuesService;

/**
 * Builds a key/value pair of available Institutional Proposal Status Codes.
 */
public class InstitutionalProposalStatusCodeValuesFinder extends KeyValuesBase {

    /**
     * If this is being called from the InstitutionalProposalForm, lookup all proposal status's except for Funded.  
     * This is the result of a rule that disallows user to change an Institutional Proposal to funded.  
     * The only way an Institutional Proposal can be funded is if it is associated with an Award, and this can 
     * only be done from the Award module.  If the lookup is from elsewhere, Funded should be included.
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("unchecked")
    public List getKeyValues() {
        Collection<ProposalStatus> proposalStatusList = getKeyValuesService().findAll(ProposalStatus.class);
        
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        
        boolean includeFunded = true;
        KualiForm form = KNSGlobalVariables.getKualiForm();
        if (form instanceof InstitutionalProposalForm) {
            includeFunded = false;
        }
        
        for (ProposalStatus proposalStatus : proposalStatusList) {
            if (!proposalStatus.getDescription().equals("Funded") || includeFunded) {
                keyValues.add(new ConcreteKeyValue(proposalStatus.getProposalStatusCode().toString()
                        ,proposalStatus.getDescription()));    
            }
        }        
                
        return keyValues;
    }
    
    /**
     * 
     * This is a wrapper method for the retrieval of KeyValuesService.
     * 
     * @return
     */
    protected KeyValuesService getKeyValuesService() {
        return KraServiceLocator.getService(KeyValuesService.class);
    }

}
