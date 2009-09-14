/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.maintenance;

import javax.servlet.http.HttpServletRequest;

import org.kuali.rice.kns.util.ActionFormUtilMap;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;

public class KraMaintenanceForm extends KualiMaintenanceForm {

    private static final long serialVersionUID = -627714142076110160L;

    /**
     * This method disables the caching of drop down lists.  
     * Question maintenance has a drop down list whose value depends on another drop down list.  With caching enabled the
     * drop down list will always be empty.  Disabling caching will reload the drop down list whenever the page is posted.
     * 
     * @see org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm#populate(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public void populate(HttpServletRequest request) {
        super.populate(request);
        if (getActionFormUtilMap() != null && getActionFormUtilMap() instanceof ActionFormUtilMap) {
            ((ActionFormUtilMap) getActionFormUtilMap()).setCacheValueFinderResults(false);
        }       
    }
    
   /**
    * Rice does additional checks that are not compatible with the use of expanded text area.  These checks are disabled here.
    * 
    * @see org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm#shouldPropertyBePopulatedInForm(java.lang.String, javax.servlet.http.HttpServletRequest)
    */
    @Override
    public boolean shouldPropertyBePopulatedInForm(String requestParameterName, HttpServletRequest request) {
        return true;
    }

}
