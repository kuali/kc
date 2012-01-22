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
package org.kuali.kra.institutionalproposal.contacts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.KeyValuesService;

/**
 * This class finds Project Roles for an Institutional Proposal contact
 */
public abstract class InstitutionalProposalContactsProjectRoleValuesFinder extends KeyValuesBase {

    private static final String EMPTY_STR = "";
    private static final String KEY_VALUES_SERVICE_NAME = "keyValuesService";

    /**
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @SuppressWarnings("unchecked")
    public List getKeyValues() {
        return buildKeyValues(getKeyValuesService().findAll(getRoleType()));
    }

    /**
     * @return
     */
    protected KeyValuesService getKeyValuesService() {
        return (KeyValuesService) KraServiceLocator.getService(KEY_VALUES_SERVICE_NAME);
    }

    protected abstract Class<? extends ContactRole> getRoleType();
    
    /*
     * Build the pairs
     */
    private List<KeyValue> buildKeyValues(Collection<? extends ContactRole> contactRoles) {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        addEmptyKeyValuePair(keyValues);
        for (ContactRole role: contactRoles) {
            keyValues.add(new ConcreteKeyValue(role.getRoleCode(), role.getRoleDescription()));
        }
        return keyValues;
    }

    /**
     * This method adds an empty role in the selection
     * @param keyValues
     */
    protected void addEmptyKeyValuePair(List<KeyValue> keyValues) {
        keyValues.add(new ConcreteKeyValue(EMPTY_STR, EMPTY_STR));
    }

}
