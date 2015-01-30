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
package org.kuali.kra.irb.actions;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.controller.AbstractHoldingPageAction;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class IrbHoldingPageAction extends AbstractHoldingPageAction {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        ActionForward forward = super.execute(mapping, form, request, response);
        
        Object documentId = GlobalVariables.getUserSession().retrieveObject(Constants.HOLDING_PAGE_DOCUMENT_ID);
        Document document = getByDocumentHeaderId(documentId.toString());
        if (isDocumentPostprocessingComplete(document)) {
            String backLocation = (String) GlobalVariables.getUserSession().retrieveObject(Constants.HOLDING_PAGE_RETURN_LOCATION);
            GlobalVariables.getUserSession().removeObject(Constants.HOLDING_PAGE_RETURN_LOCATION);
            GlobalVariables.getUserSession().removeObject(Constants.HOLDING_PAGE_DOCUMENT_ID);

            forward = new ActionForward(backLocation, true);
        }
        
        return forward;
    }
}
