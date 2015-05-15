/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.sys.framework.lookup;

import org.kuali.rice.krad.bo.BusinessObject;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 
 * This class is to set the allowsMaintenanceEditAction to false, so the 'edit' action
 * will not be created in search result list.  currently Budget5categorymapping &amp; validceratetype are
 * using this as lookupable.
 */
@Component("noEditLookupableHelperService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NoEditLookupableHelperServiceImpl extends KcKualiLookupableHelperServiceImpl {

    @Override
    protected boolean allowsMaintenanceEditAction(BusinessObject businessObject) {
        return false;
    }

}
