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
package org.kuali.coeus.common.committee.impl.lookup.keyvalue;

import org.kuali.coeus.common.committee.impl.bo.CommitteeDecisionMotionType;
import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.KeyValuesService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Returns all possible values for the CommitteeBase Decision Motion dropdown box.
 */
public class CommitteeDecisionMotionValuesFinder extends FormViewAwareUifKeyValuesFinderBase {
    

    private static final long serialVersionUID = 3729912964388818340L;
    
    private KeyValuesService keyValuesService;

    @Override
    public List<KeyValue> getKeyValues() {
        Collection<CommitteeDecisionMotionType> motionTypes = getKeyValuesService().findAll(CommitteeDecisionMotionType.class);
        
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        
        for (CommitteeDecisionMotionType motionType : motionTypes) {
            keyValues.add(new ConcreteKeyValue(motionType.getMotionTypeCode(), motionType.getDescription()));
        }
        
        return keyValues;
    }
    
    /**
     * 
     * This method returns an instance of CommitteeService.
     * @return KeyValuesService
     */
    public KeyValuesService getKeyValuesService() {
        if (this.keyValuesService == null) {
            this.keyValuesService = KcServiceLocator.getService(KeyValuesService.class);
        }
        return this.keyValuesService;
    }
}
