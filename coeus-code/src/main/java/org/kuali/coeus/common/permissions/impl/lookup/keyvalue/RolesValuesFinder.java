/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.permissions.impl.lookup.keyvalue;

import org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsForm;
import org.kuali.coeus.common.permissions.impl.web.struts.form.PermissionsHelperBase;
import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.rice.core.api.util.KeyValue;

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
        
        return null;
    }
}
