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
package org.kuali.kra.subaward.web.struts.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.subaward.SubAwardForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**.
 *
 * This class represents the Struts Action for Medusa page(AwardMedusa.jsp)
 */
public class SubAwardMedusaAction extends SubAwardAction {

    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form
           , HttpServletRequest request, HttpServletResponse response)
        throws Exception { SubAwardForm subAwardForm = (SubAwardForm) form;
            if (subAwardForm.getDocument().getDocumentNumber() == null) {
                loadDocumentInForm(request, subAwardForm);
            }
            subAwardForm.getMedusaBean().setMedusaViewRadio("0");
            subAwardForm.getMedusaBean().setModuleName("subaward");
            subAwardForm.getMedusaBean().
            setModuleIdentifier(subAwardForm.getSubAwardDocument().
            getSubAward().getSubAwardId());
            subAwardForm.getMedusaBean().generateParentNodes();
            return mapping.findForward(Constants.MAPPING_AWARD_MEDUSA_PAGE); }
    /**.
     * This method is for refreshView
     * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws Exception
     * @return mapping
     */
    public ActionForward refreshView(ActionMapping mapping,
    	ActionForm form, HttpServletRequest request,
    	HttpServletResponse response) throws Exception {

        return mapping.findForward(Constants.MAPPING_AWARD_MEDUSA_PAGE);
    }
}
