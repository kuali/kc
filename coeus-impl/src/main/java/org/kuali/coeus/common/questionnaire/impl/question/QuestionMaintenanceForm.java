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
package org.kuali.coeus.common.questionnaire.impl.question;

import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kns.util.ActionFormUtilMap;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;

import javax.servlet.http.HttpServletRequest;

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
