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
package org.kuali.coeus.common.impl.rolodex.nonorg;

import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex;
import org.kuali.coeus.common.impl.rolodex.RolodexDao;
import org.kuali.coeus.sys.framework.lookup.KcKualiLookupableHelperServiceImpl;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.BeanPropertyComparator;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Lookup wrapper class to modify lookup results of <code>{@link NonOrganizationalRolodex}</code> lookups
 * 
 */
@Transactional
@Component("nonOrganizationalRolodexHelperService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NonOrganizationalRolodexLookupableHelperServiceImpl extends KcKualiLookupableHelperServiceImpl {
    

    private static final long serialVersionUID = -3536764919498823536L;

    @Autowired
    @Qualifier("rolodexDao")
    private transient RolodexDao rolodexDao;

    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        boolean usePrimaryKeys = getLookupService().allPrimaryKeyValuesPresentAndNotWildcard(Rolodex.class, fieldValues);
        
        setBackLocation(fieldValues.get(KRADConstants.BACK_LOCATION));
        setDocFormKey(fieldValues.get(KRADConstants.DOC_FORM_KEY));
        setReferencesToRefresh(fieldValues.get(KRADConstants.REFERENCES_TO_REFRESH));
        
        List<?extends BusinessObject> searchResults = getRolodexDao().getNonOrganizationalRolodexResults(fieldValues, usePrimaryKeys);
        
        // sort list if default sort column given
        List defaultSortColumns = getDefaultSortColumns();
        if (defaultSortColumns.size() > 0) {
            Collections.sort(searchResults, new BeanPropertyComparator(getDefaultSortColumns(), true));
        }
        return searchResults;
    }

    public RolodexDao getRolodexDao() {
        return rolodexDao;
    }

    public void setRolodexDao(RolodexDao rolodexDao) {
        this.rolodexDao = rolodexDao;
    }
}
