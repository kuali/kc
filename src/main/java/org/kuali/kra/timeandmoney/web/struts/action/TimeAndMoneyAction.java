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
package org.kuali.kra.timeandmoney.web.struts.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.TimeAndMoneyForm;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.KNSConstants;

public class TimeAndMoneyAction extends KraTransactionalDocumentActionBase {
    
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form
            , HttpServletRequest request, HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;        
        ActionForward forward = handleDocument(mapping, form, request, response, timeAndMoneyForm);
        timeAndMoneyForm.initializeFormOrDocumentBasedOnCommand();        
        timeAndMoneyForm.getTimeAndMoneyDocument().setAwardHierarchyItems(getAwardHierarchyService().getAwardHierarchy(timeAndMoneyForm.getTimeAndMoneyDocument().getAwardNumber()));
        
        setupHierachyNodes(timeAndMoneyForm.getTimeAndMoneyDocument());
        
        return forward;
    }
    
    public AwardHierarchyService getAwardHierarchyService(){        
        return (AwardHierarchyService) KraServiceLocator.getService(AwardHierarchyService.class);
    }
    
    protected void setupHierachyNodes(TimeAndMoneyDocument timeAndMoneyDocument){
        AwardHierarchyNode awardHierarchyNode;
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String, String> fieldValues = new HashMap<String, String>();
        
        for(Entry<String, AwardHierarchy> awardHierarchy:timeAndMoneyDocument.getAwardHierarchyItems().entrySet()){
            awardHierarchyNode = new AwardHierarchyNode();
            awardHierarchyNode.setAwardNumber(awardHierarchy.getValue().getAwardNumber());
            awardHierarchyNode.setParentAwardNumber(awardHierarchy.getValue().getParentAwardNumber());
            awardHierarchyNode.setRootAwardNumber(awardHierarchy.getValue().getRootAwardNumber());
            
            fieldValues.put("awardNumber", awardHierarchy.getValue().getAwardNumber());
            List<Award> awardList = (List<Award>) businessObjectService.findMatching(Award.class, fieldValues);
            awardHierarchyNode.setFinalExpirationDate(awardList.get(0).getProjectEndDate());
            awardHierarchyNode.setLeadUnitName(awardList.get(0).getUnitName());
            awardHierarchyNode.setPrincipalInvestigatorName(awardList.get(0).getPrincipalInvestigatorName());
            awardHierarchyNode.setObliDistributableAmount(awardList.get(0).getAwardAmountInfos().get(0).getObliDistributableAmount());
            awardHierarchyNode.setAmountObligatedToDate(awardList.get(0).getAwardAmountInfos().get(0).getAmountObligatedToDate());
            awardHierarchyNode.setAnticipatedTotalAmount(awardList.get(0).getAwardAmountInfos().get(0).getAnticipatedTotalAmount());
            awardHierarchyNode.setAntDistributableAmount(awardList.get(0).getAwardAmountInfos().get(0).getAntDistributableAmount());
            timeAndMoneyDocument.getAwardHierarchyNodes().put(awardHierarchyNode.getAwardNumber(), awardHierarchyNode);
        }
    }
    
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param awardForm
     * @return
     * @throws Exception
     */
    public ActionForward handleDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response, TimeAndMoneyForm timeAndMoneyForm) throws Exception {
        String command = timeAndMoneyForm.getCommand();
        ActionForward forward;        
        if (KEWConstants.ACTIONLIST_INLINE_COMMAND.equals(command)) {
            String docIdRequestParameter = request.getParameter(KNSConstants.PARAMETER_DOC_ID);
            Document retrievedDocument = getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
            timeAndMoneyForm.setDocument(retrievedDocument);
            request.setAttribute(KNSConstants.PARAMETER_DOC_ID, docIdRequestParameter);
            ActionForward baseForward = mapping.findForward(Constants.MAPPING_COPY_PROPOSAL_PAGE);
            forward = new ActionForward(buildForwardStringForActionListCommand(
                    baseForward.getPath(),docIdRequestParameter));  
        } else {
        forward = super.docHandler(mapping, form, request, response);
        }
        
        return forward;
    }
    
    public ActionForward addTransaction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ((TimeAndMoneyForm) form).getTransactionBean().addPendingTransactionItem();
        return mapping.findForward("basic");        
    }
    
    public ActionForward deleteTransaction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ((TimeAndMoneyForm) form).getTransactionBean().deletePendingTransactionItem(getLineToDelete(request));
        return mapping.findForward("basic");        
    }
    
    public ActionForward approveTransactions(ActionMapping mapping, ActionForm form , HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        
        getActivePendingTransactionsService().approveTransactions();
        
        return mapping.findForward("basic");
    }
    
    protected ActivePendingTransactionsService getActivePendingTransactionsService(){
        return (ActivePendingTransactionsService) KraServiceLocator.getService(ActivePendingTransactionsService.class);
    }
    
    /**
     * 
     * This method builds the string for the ActionForward 
     * @param forwardPath
     * @param docIdRequestParameter
     * @return
     */
    public String buildForwardStringForActionListCommand(String forwardPath, String docIdRequestParameter){
        StringBuilder sb = new StringBuilder();
        sb.append(forwardPath);
        sb.append("?");
        sb.append(KNSConstants.PARAMETER_DOC_ID);
        sb.append("=");
        sb.append(docIdRequestParameter);
        return sb.toString();
    }    
    
    
    public ActionForward addTransaction(){
        return null;
    }
    
    public ActionForward deleteTransaction(){
        return null;
    }
    
    public ActionForward submit(){
        return null;
    }

}
