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
package org.kuali.kra.common.permissions.lookup.keyvalue;

import java.util.List;

import org.kuali.kra.common.permissions.web.struts.form.PermissionsForm;
import org.kuali.kra.common.permissions.web.struts.form.PermissionsHelperBase;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;

/**
 * The RolesValueFinder is responsible for building the list of
 * role names that will be displayed in the drop-down menu on
 * the Permissions tab web page.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class RolesValuesFinder extends KeyValuesBase {
    
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
     * @see org.kuali.core.lookup.keyvalues.KeyValuesFinder#getKeyValues()
     */
    public List<KeyValue> getKeyValues() {
        KualiForm form = KNSGlobalVariables.getKualiForm();
        
        if (form instanceof PermissionsForm) {
            PermissionsForm tabSupport = (PermissionsForm) form;
            PermissionsHelperBase helper = tabSupport.getPermissionsHelper();
            return helper.getRoleSelection();
        }
        
        return null;
    }
}
