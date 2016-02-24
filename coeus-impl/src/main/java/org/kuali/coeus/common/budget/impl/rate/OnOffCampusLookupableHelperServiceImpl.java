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
package org.kuali.coeus.common.budget.impl.rate;

import org.kuali.coeus.sys.framework.lookup.KcKualiLookupableHelperServiceImpl;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.bo.BusinessObject;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("onOffCampusLookupableHelperService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OnOffCampusLookupableHelperServiceImpl extends KcKualiLookupableHelperServiceImpl {

    /**
     * 
     * This is for onoffcampusflag.  It's boolean, but saved as 'N/F'.  So, it caused trouble for lookup 
     */
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        if (fieldValues.get(Constants.ON_OFF_CAMPUS_FLAG).equalsIgnoreCase("Y")) {
             fieldValues.put(Constants.ON_OFF_CAMPUS_FLAG, "N");
        } else if (fieldValues.get(Constants.ON_OFF_CAMPUS_FLAG).equalsIgnoreCase("N")) {
             fieldValues.put(Constants.ON_OFF_CAMPUS_FLAG, "F");
        }
        return super.getSearchResults(fieldValues);
    }

}
