/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.lookup;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.dao.RolodexDao;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.BeanPropertyComparator;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.transaction.annotation.Transactional;

/**
 * Lookup wrapper class to modify lookup results of <code>{@link NonOrganizationalRolodex}</code> lookups
 * 
 */
@Transactional
public class NonOrganizationalRolodexLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3536764919498823536L;
    private transient RolodexDao rolodexDao;

    /**
     * @see org.kuali.core.lookup.KualiLookupableHelperServiceImpl#getSearchResults(java.util.Map)
     */
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
