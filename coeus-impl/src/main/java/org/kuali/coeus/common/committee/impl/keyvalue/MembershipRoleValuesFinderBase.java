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
package org.kuali.coeus.common.committee.impl.keyvalue;

import org.kuali.coeus.common.committee.impl.bo.MembershipRole;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MembershipRoleValuesFinderBase extends UifKeyValuesFinderBase {


    private static final long serialVersionUID = -7492852270320003877L;

    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyLabels = new ArrayList<KeyValue>();
        keyLabels.add(new ConcreteKeyValue("", "select"));

        Map<String, String> criteria = new HashMap<String, String>();
        
        criteria.put("committeeTypeCode", getCommitteeTypeCodeHook());

        List<? extends MembershipRole> roles = (List<? extends MembershipRole>) getBusinessObjectService().findMatching(MembershipRole.class, criteria);

        for(MembershipRole role : roles) {
            keyLabels.add(new ConcreteKeyValue(role.getMembershipRoleCode(), role.getDescription()));
        }
        return keyLabels;
    }
    
    protected abstract String getCommitteeTypeCodeHook();

    protected BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }
    
}
