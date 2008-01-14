/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DocumentService;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.notification.util.NotificationConstants;

import edu.iu.uis.eden.KEWServiceLocator;
import edu.iu.uis.eden.clientapp.IDocHandler;
import edu.iu.uis.eden.routeheader.DocumentRouteHeaderValue;
import edu.iu.uis.eden.routeheader.RouteHeaderService;

public class ProposalDevelopmentBudgetVersionsAction extends ProposalDevelopmentAction {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentBudgetVersionsAction.class);
    
    /**
     * Action called to create a new budget version.
     * 
     * @param mapping 
     * @param form
     * @param request
     * @param response
     * @return ActionForward instance for forwarding to the tab.
     */
    public ActionForward addBudgetVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pdDoc = pdForm.getProposalDevelopmentDocument();
        DocumentService docService = KraServiceLocator.getService(DocumentService.class);
        List<BudgetVersionOverview> budgetVersions = pdDoc.getBudgetVersionOverviews();
        Integer newBudgetVersion;
        if (!budgetVersions.isEmpty()) {
            newBudgetVersion = budgetVersions.get(budgetVersions.size() - 1).getBudgetVersionNumber() + 1;
        } else {
            newBudgetVersion = 1;
        }
        BudgetDocument budgetDocument = (BudgetDocument) docService.getNewDocument(BudgetDocument.class);
        budgetDocument.setProposalNumber(pdDoc.getProposalNumber());
        budgetDocument.setBudgetVersionNumber(newBudgetVersion);
        budgetDocument.getDocumentHeader().setFinancialDocumentDescription(pdForm.getNewBudgetVersionName());
        budgetDocument.setStartDate(pdDoc.getRequestedStartDateInitial());
        budgetDocument.setEndDate(pdDoc.getRequestedEndDateInitial());
        budgetDocument.setOhRateClassCode(1);
        budgetDocument.setUrRateClassCode(1);
        budgetDocument.setModularBudgetFlag("N");
        docService.saveDocument(budgetDocument);
        docService.routeDocument(budgetDocument, "Route to Final", new ArrayList());
        pdForm.getProposalDevelopmentDocument().addNewBudgetVersion(budgetDocument);
        RouteHeaderService rhSrv = (RouteHeaderService) KraServiceLocator.getService(KEWServiceLocator.DOC_ROUTE_HEADER_SRV);
        Long docId = budgetDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
        DocumentRouteHeaderValue routeHeader = rhSrv.getRouteHeader(docId);
        String forward = routeHeader.getDocumentType().getDocHandlerUrl();
        if (forward.indexOf("?") == -1) {
            forward += "?";
        } else {
            forward += "&";
        }
        forward += IDocHandler.ROUTEHEADER_ID_PARAMETER + "=" + docId;
        forward += "&" + IDocHandler.COMMAND_PARAMETER + "=" + NotificationConstants.NOTIFICATION_DETAIL_VIEWS.DOC_SEARCH_VIEW;
//        if (getUserSession(request).isBackdoorInUse()) {
//            forward += "&" + IDocHandler.BACKDOOR_ID_PARAMETER + "=" + getUserSession(request).getNetworkId();
//        }
        return new ActionForward(forward, true);
    }
    
    public ActionForward openBudgetVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pdDoc = pdForm.getProposalDevelopmentDocument();
        int lineToOpen = getSelectedLine(request);
        BudgetVersionOverview budgetToOpen = pdDoc.getBudgetVersionOverview(lineToOpen);
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
        Map pks = new HashMap();
        pks.put("proposalNumber", Integer.parseInt(budgetToOpen.getDocumentNumber()));
        pks.put("budgetVersionNumber", budgetToOpen.getBudgetVersionNumber());
        BudgetDocument budgetDocument = (BudgetDocument) boService.findByPrimaryKey(BudgetDocument.class, pks);
        RouteHeaderService rhSrv = (RouteHeaderService) KraServiceLocator.getService(KEWServiceLocator.DOC_ROUTE_HEADER_SRV);
        Long docId = budgetDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
        DocumentRouteHeaderValue routeHeader = rhSrv.getRouteHeader(docId);
        String forward = routeHeader.getDocumentType().getDocHandlerUrl();
        if (forward.indexOf("?") == -1) {
            forward += "?";
        } else {
            forward += "&";
        }
        forward += IDocHandler.ROUTEHEADER_ID_PARAMETER + "=" + docId;
        forward += "&" + IDocHandler.COMMAND_PARAMETER + "=" + NotificationConstants.NOTIFICATION_DETAIL_VIEWS.DOC_SEARCH_VIEW;
//        if (getUserSession(request).isBackdoorInUse()) {
//            forward += "&" + IDocHandler.BACKDOOR_ID_PARAMETER + "=" + getUserSession(request).getNetworkId();
//        }
        return new ActionForward(forward, true);
    }
}
