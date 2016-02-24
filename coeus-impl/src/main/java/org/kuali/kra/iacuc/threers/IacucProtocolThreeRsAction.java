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
package org.kuali.kra.iacuc.threers;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class IacucProtocolThreeRsAction extends IacucProtocolAction {


    /**
     * @see org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase#execute(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        ActionForward actionForward = super.execute(mapping, form, request, response);
        ((IacucProtocolForm)form).getIacucAlternateSearchHelper().prepareView();
        
        return actionForward;
    }
    
    public ActionForward addAlternateSearchDatabase (ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;
        IacucAlternateSearchHelper alternateSearchHelper = protocolForm.getIacucAlternateSearchHelper();
        IacucAlternateSearch altSearch = alternateSearchHelper.getNewAlternateSearch();
        List<String> newDatabases = alternateSearchHelper.getNewDatabases();
        
        if (applyRules(new AddAlternateSearchEvent(protocolForm.getProtocolDocument(), altSearch, newDatabases))) {
            getIacucAlternateSearchService().addAlternateSearch(((IacucProtocol)protocolForm.getIacucProtocolDocument().getProtocol()),
                     altSearch, newDatabases);       
            getDocumentService().saveDocument(protocolForm.getProtocolDocument());

            // clear entry since this one's good
            alternateSearchHelper.setNewAlternateSearch(new IacucAlternateSearch());
            alternateSearchHelper.setNewDatabases(new ArrayList<String>()); 
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    
    public ActionForward deleteAlternateSearch (ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        IacucProtocolForm protocolForm = (IacucProtocolForm) form;        
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        int index = getAlternateSearchIndexNumber(parameterName, "deleteAlternateSearch");
        
        getIacucAlternateSearchService().deleteAlternateSearch(((IacucProtocol)protocolForm.getIacucProtocolDocument().getProtocol()),
                index);
        getDocumentService().saveDocument(protocolForm.getProtocolDocument());

        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    
    private int getAlternateSearchIndexNumber(String parameterName, String actionMethodToCall) {
        int result = -1;
        if (StringUtils.isBlank(parameterName)||parameterName.indexOf("."+actionMethodToCall+".") == -1) {
            throw new IllegalArgumentException(
                    String.format("getAlternateSearchIndex expects a non-empty value for parameterName parameter, "+
                            "and it must contain as a substring the parameter actionMethodToCall. "+
                            "The passed values were (%s,%s)."
                            ,parameterName,actionMethodToCall)
                    );
        }
        String idxNmbr = StringUtils.substringBetween(parameterName, ".line.", ".anchor");
        result = Integer.parseInt(idxNmbr);
        return result;
    }
    
    private IacucAlternateSearchService getIacucAlternateSearchService() {
        return KcServiceLocator.getService(IacucAlternateSearchService.class);
    }
}
