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
package org.kuali.coeus.common.permissions.impl.lookup.keyvalue;

import org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsForm;
import org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsHelperBase;
import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * The RolesValueFinder is responsible for building the list of
 * role names that will be displayed in the drop-down menu on
 * the Permissions tab web page.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RolesValuesFinder extends FormViewAwareUifKeyValuesFinderBase {
    
    /**
     * This Value Finder is different from a typical one.  This is
     * because it must display different roles based upon the document
     * type, e.g. the roles for Proposal Development and Protocol are
     * completely different.  By itself, this Value Finder cannot determine
     * what roles to display.  To resolve this problem, the Value Finder
     * delegates the building of the list of role names to the Permissions
     * Helper.  Each Form that supports the Permissions page has a
     * PermissionsHelper.  Since each Form, and its PermissionsHelper, is
     * specific to a document type, it can properly build the list
     * of role names.
     * 
     */
    @Override
    public List<KeyValue> getKeyValues() {
        Object form = getFormOrView();
        
        if (form instanceof PermissionsForm) {
            PermissionsForm tabSupport = (PermissionsForm) form;
            PermissionsHelperBase helper = tabSupport.getPermissionsHelper();
            return helper.getRoleSelection();
        }
        
        /* Need to return an empty array list as returning null causes problems with optionsFinder in DD, even if the field is not used, 
        such as in PermissionUser.xml in  COI Administrator Actions */
        return new ArrayList<KeyValue>();
    }
}
