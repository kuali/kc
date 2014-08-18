/*
 * Copyright 2005-2014 The Kuali Foundation
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
