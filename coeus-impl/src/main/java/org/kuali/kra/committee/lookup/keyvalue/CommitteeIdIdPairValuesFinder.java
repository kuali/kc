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
package org.kuali.kra.committee.lookup.keyvalue;

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.List;

public class CommitteeIdIdPairValuesFinder extends CommitteeIdValuesFinder {


    private static final long serialVersionUID = -1230239894490494267L;

    /**
     * This override will return the active committee <id, id> pairs list as key-labels. 
     * 
     * @see org.kuali.kra.committee.lookup.keyvalue.CommitteeIdValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {        
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        
        List<CommitteeBase> committees = this.getActiveCommittees();
        if (CollectionUtils.isNotEmpty(committees)) {
            for (CommitteeBase committee : committees) {
                keyValues.add(new ConcreteKeyValue(committee.getCommitteeId(), committee.getCommitteeId()));
            }
        }
        return keyValues;
    }
    

}
