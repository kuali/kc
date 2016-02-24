/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.award.contacts;

import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class finds Project Roles for an Award contact 
 */
public abstract class AwardContactsProjectRoleValuesFinder extends FormViewAwareUifKeyValuesFinderBase {
    
    private static final String EMPTY_STR = "";
    private static final String KEY_VALUES_SERVICE_NAME = "keyValuesService";

    @Override
    public List<KeyValue> getKeyValues() {
        return buildKeyValues(getKeyValuesService().findAll(getRoleType()));
    }


    protected KeyValuesService getKeyValuesService() {
        return (KeyValuesService) KcServiceLocator.getService(KEY_VALUES_SERVICE_NAME);
    }

    protected abstract Class<? extends ContactRole> getRoleType();
    
    /**
     * Build the pairs.
     */
    protected List<KeyValue> buildKeyValues(Collection<? extends ContactRole> contactRoles) {
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        addEmptyKeyValuePair(keyValues);
        for (ContactRole role : contactRoles) {
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
