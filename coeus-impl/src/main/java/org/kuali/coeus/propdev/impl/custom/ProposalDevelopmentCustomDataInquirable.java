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
package org.kuali.coeus.propdev.impl.custom;

import org.kuali.coeus.common.framework.custom.arg.ArgValueLookup;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.inquiry.InquirableImpl;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("proposalDevelopmentCustomDataInquirable")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProposalDevelopmentCustomDataInquirable extends InquirableImpl {
    @Override
    public Object retrieveDataObject(Map<String, String> parameters) {
        Map<String,String> filteredParamters = new HashMap<String,String>();
        filteredParamters.put("value",parameters.get("value"));
        filteredParamters.put("argumentName",parameters.get("argumentName"));

        return getLegacyDataAdapter().findObjectBySearch(ArgValueLookup.class,parameters);

    }
}
