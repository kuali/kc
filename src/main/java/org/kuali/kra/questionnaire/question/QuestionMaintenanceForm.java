/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.questionnaire.question;

import javax.servlet.http.HttpServletRequest;

import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.util.ActionFormUtilMap;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;

public class QuestionMaintenanceForm extends KualiMaintenanceForm {

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
     * override this for view bootstrap data.  A new doc is initiated in this case.
     * this will make the document header looks 'Final'.
     * @see org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase#populateHeaderFields(org.kuali.rice.kew.api.WorkflowDocument)
     */
    @Override
    public void populateHeaderFields(WorkflowDocument workflowDocument) {
        
        super.populateHeaderFields(workflowDocument);
//
//        // readOnly is changing several times during load.  so better with this approach
        if (this.isReadOnly() && workflowDocument.isInitiated()) {
//            getDocInfo().get(1).setDisplayValue("FINAL");
//            getDocInfo().get(2).setLookupAware(false);
//            getDocInfo().get(2).setDisplayValue("admin");
//            getDocInfo().get(3).setDisplayValue("00:00 AM 01/01/2010");
//
            getDocInfo().clear();
        }
    }

}
