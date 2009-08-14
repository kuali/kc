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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.accesslayer.LookupException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.TimeAndMoneyForm;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.service.ActivePendingTransactionsService;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyActionSummaryService;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyHistoryService;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;

public class TimeAndMoneyAction extends KraTransactionalDocumentActionBase {
    
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;        
        ActionForward forward = handleDocument(mapping, form, request, response, timeAndMoneyForm);
        timeAndMoneyForm.initializeFormOrDocumentBasedOnCommand();        
        String rootAwardNumber = timeAndMoneyForm.getTimeAndMoneyDocument().getRootAwardNumber();
        List<String> order = new ArrayList<String>();        
        timeAndMoneyForm.getTimeAndMoneyDocument().setAwardHierarchyItems(getAwardHierarchyService().getAwardHierarchy(rootAwardNumber, order));
        timeAndMoneyForm.getTimeAndMoneyDocument().setAwardNumber(rootAwardNumber);
        timeAndMoneyForm.setOrder(order);
        setupHierachyNodes(timeAndMoneyForm.getTimeAndMoneyDocument());
        populateOtherPanels(timeAndMoneyForm.getTransactionBean().getNewAwardAmountTransaction(), timeAndMoneyForm.getTimeAndMoneyDocument(), rootAwardNumber);
        return forward;
    }
    
    public AwardHierarchyService getAwardHierarchyService(){        
        return (AwardHierarchyService) KraServiceLocator.getService(AwardHierarchyService.class);
    }
    
    protected void setupHierachyNodes(TimeAndMoneyDocument timeAndMoneyDocument){
        AwardHierarchyNode awardHierarchyNode;
        ActivePendingTransactionsService aptService = KraServiceLocator.getService(ActivePendingTransactionsService.class);
        
        for(Entry<String, AwardHierarchy> awardHierarchy:timeAndMoneyDocument.getAwardHierarchyItems().entrySet()){
            awardHierarchyNode = new AwardHierarchyNode();
            awardHierarchyNode.setAwardNumber(awardHierarchy.getValue().getAwardNumber());
            awardHierarchyNode.setParentAwardNumber(awardHierarchy.getValue().getParentAwardNumber());
            awardHierarchyNode.setRootAwardNumber(awardHierarchy.getValue().getRootAwardNumber());
            
            Award award = aptService.getActiveAwardVersion(awardHierarchy.getValue().getAwardNumber());
            AwardAmountInfo awardAmountInfo = aptService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());            
            
            awardHierarchyNode.setFinalExpirationDate(award.getProjectEndDate());
            awardHierarchyNode.setLeadUnitName(award.getUnitName());
            awardHierarchyNode.setPrincipalInvestigatorName(award.getPrincipalInvestigatorName());
            awardHierarchyNode.setObliDistributableAmount(awardAmountInfo.getObliDistributableAmount());
            awardHierarchyNode.setAmountObligatedToDate(awardAmountInfo.getAmountObligatedToDate());
            awardHierarchyNode.setAnticipatedTotalAmount(awardAmountInfo.getAnticipatedTotalAmount());
            awardHierarchyNode.setAntDistributableAmount(awardAmountInfo.getAntDistributableAmount());
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
        
        
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm)GlobalVariables.getKualiForm();
        getActivePendingTransactionsService().approveTransactions(timeAndMoneyForm.getTimeAndMoneyDocument(), timeAndMoneyForm.getTransactionBean().getNewAwardAmountTransaction());
        
        return mapping.findForward("basic");
    }
    
    public ActionForward switchAward(ActionMapping mapping, ActionForm form , HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm)form;
        TimeAndMoneyDocument doc = timeAndMoneyForm.getTimeAndMoneyDocument();
        String goToAwardNumber = timeAndMoneyForm.getGoToAwardNumber();
        
        populateOtherPanels(timeAndMoneyForm.getTransactionBean().getNewAwardAmountTransaction(), doc, goToAwardNumber);
        
        return mapping.findForward("basic");
    }

    /**
     * This method...
     * @param timeAndMoneyForm
     * @param doc
     * @param goToAwardNumber
     * @throws LookupException
     * @throws SQLException
     */
    private void populateOtherPanels(AwardAmountTransaction newAwardAmountTransaction, TimeAndMoneyDocument doc, String goToAwardNumber)
            throws LookupException, SQLException {
        Award award = getActiveAwardVersion(goToAwardNumber);
        
        doc.setAwardNumber(award.getAwardNumber());
        doc.setAward(award);
        
        TimeAndMoneyHistoryService tamhs = KraServiceLocator.getService(TimeAndMoneyHistoryService.class);
        
        tamhs.getTimeAndMoneyHistory(doc.getAwardNumber(), doc.getTimeAndMoneyHistory());
        
        TimeAndMoneyActionSummaryService tamass = KraServiceLocator.getService(TimeAndMoneyActionSummaryService.class);
        tamass.populateActionSummary(doc.getTimeAndMoneyActionSummaryItems(), doc.getAwardNumber());
        
        doc.setNewAwardAmountTransaction(newAwardAmountTransaction);
    }

    /**
     * This method...
     * @param doc
     * @param goToAwardNumber
     */
    private Award getActiveAwardVersion(String goToAwardNumber) {
        VersionHistoryService vhs = KraServiceLocator.getService(VersionHistoryService.class);  
        VersionHistory vh = vhs.findActiveVersion(Award.class, goToAwardNumber);
        Award award = null;
        
        if(vh!=null){
            award = (Award) vh.getSequenceOwner();
        }else{
            BusinessObjectService businessObjectService =  KraServiceLocator.getService(BusinessObjectService.class);
            award = ((List<Award>)businessObjectService.findMatching(Award.class, getHashMap(goToAwardNumber))).get(0);              
        }
        return award;
    }
    
    private Map<String, String> getHashMap(String goToAwardNumber) {
        Map<String, String> map = new HashMap<String,String>();
        map.put("awardNumber", goToAwardNumber);
        return map;
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
