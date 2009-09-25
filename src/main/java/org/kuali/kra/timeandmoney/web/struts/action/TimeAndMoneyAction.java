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

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.accesslayer.LookupException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistributionBean;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.AwardDirectFandADistributionService;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.TimeAndMoneyForm;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
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
    
    private static final String PENDING_VIEW = "1";
    private static final String ACTIVE_VIEW = "0";
    private static final String PENDING_TRANSACTIONS_ATTRIBUTE_NAME = "pendingTransactions";
    private static final String ZERO = "0";
    BusinessObjectService businessObjectService;
    private AwardDirectFandADistributionBean awardDirectFandADistributionBean;
    
    public TimeAndMoneyAction(){
        awardDirectFandADistributionBean = new AwardDirectFandADistributionBean();
    }
    
    /**
     * @see org.kuali.kra.web.struts.action.KraTransactionalDocumentActionBase#save(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        forward = super.save(mapping, form, request, response);
        ActivePendingTransactionsService aptService = getActivePendingTransactionsService();
        List<AwardAmountInfo> awardAmountInfoObjects = new ArrayList<AwardAmountInfo>();
        for(Entry<String, AwardHierarchyNode> awardHierarchyNode : timeAndMoneyDocument.getAwardHierarchyNodes().entrySet()){
            Award award = aptService.getActiveAwardVersion(awardHierarchyNode.getValue().getAwardNumber());
            AwardAmountInfo aai = aptService.fetchAwardAmountInfoWithHighestTransactionId(award.getAwardAmountInfos());
            
            int index = findAwardHierarchyNodeIndex(awardHierarchyNode);
            
            if(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate()!=null){
                aai.setFinalExpirationDate(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getFinalExpirationDate());    
            }
            if(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getCurrentFundEffectiveDate()!=null){
                aai.setCurrentFundEffectiveDate(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getCurrentFundEffectiveDate());    
            }
            if(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getObligationExpirationDate()!=null){
                aai.setObligationExpirationDate(timeAndMoneyForm.getAwardHierarchyNodeItems().get(index).getObligationExpirationDate());    
            }
            awardAmountInfoObjects.add(aai);
        }
        getBusinessObjectService().save(awardAmountInfoObjects);
        getBusinessObjectService().save(timeAndMoneyDocument.getAward());        
        
        return forward;
    }

    /*
     * This method...
     */
    private int findAwardHierarchyNodeIndex(Entry<String, AwardHierarchyNode> awardHierarchyNode) {
        String reverseAwardNumber = StringUtils.reverse(awardHierarchyNode.getValue().getAwardNumber());
        String i= StringUtils.substring(reverseAwardNumber, 0, StringUtils.indexOf(reverseAwardNumber, ZERO));
        int index = Integer.parseInt(StringUtils.reverse(i));
        return index;
    }
        
    /**
     * 
     * This method refreshes the view depending on various view optins like either active or pending view or dates only, totals and 
     * distributed/distributable. 
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward refreshView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument doc = timeAndMoneyForm.getTimeAndMoneyDocument();
        if(StringUtils.equalsIgnoreCase(timeAndMoneyForm.getCurrentOrPendingView(),PENDING_VIEW)){
            
            Map<String, AwardAmountTransaction> awardAmountTransactionItems = new HashMap<String, AwardAmountTransaction>();
            List<Award> awardItems = new ArrayList<Award>();
            List<TransactionDetail> transactionDetailItems = new ArrayList<TransactionDetail>();
            
            updateDocumentFromSession(doc);
            
            ActivePendingTransactionsService service = KraServiceLocator.getService(ActivePendingTransactionsService.class);
            service.processTransactions(doc, doc.getNewAwardAmountTransaction(), awardAmountTransactionItems, awardItems, transactionDetailItems);
            GlobalVariables.getUserSession().addObject(GlobalVariables.getUserSession().getKualiSessionId(), doc);
            doc.refreshReferenceObject(PENDING_TRANSACTIONS_ATTRIBUTE_NAME);
        }else if(StringUtils.equalsIgnoreCase(timeAndMoneyForm.getCurrentOrPendingView(),ACTIVE_VIEW)){
            timeAndMoneyForm.setOrder(new ArrayList<String>());
            doc.setAwardHierarchyItems(getAwardHierarchyService().getAwardHierarchy(doc.getRootAwardNumber(), timeAndMoneyForm.getOrder()));
            getAwardHierarchyService().populateAwardHierarchyNodes(doc.getAwardHierarchyItems(), doc.getAwardHierarchyNodes());
            GlobalVariables.getUserSession().addObject(GlobalVariables.getUserSession().getKualiSessionId(), doc);
        }
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    /*
     * This method...
     * @param doc
     */
    private void updateDocumentFromSession(TimeAndMoneyDocument doc) {
        if(doc.getAwardHierarchyNodes()==null || doc.getAwardHierarchyNodes().size()==0){
            if(GlobalVariables.getUserSession().retrieveObject(GlobalVariables.getUserSession().getKualiSessionId())!=null){
                TimeAndMoneyDocument document = (TimeAndMoneyDocument)GlobalVariables.getUserSession().retrieveObject(GlobalVariables.getUserSession().getKualiSessionId());
                doc.setAwardHierarchyItems(document.getAwardHierarchyItems());
                doc.setAwardHierarchyNodes(document.getAwardHierarchyNodes());
            }else{
                throw new RuntimeException("Can't Retrieve Time And Money Document from Session");
            }
        }
    }
    /**
     * 
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#docHandler(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        
        ActionForward forward = handleDocument(mapping, form, request, response, timeAndMoneyForm);
        timeAndMoneyForm.initializeFormOrDocumentBasedOnCommand();        
        String rootAwardNumber = timeAndMoneyForm.getTimeAndMoneyDocument().getRootAwardNumber();
                
        timeAndMoneyDocument.setAwardHierarchyItems(getAwardHierarchyService().getAwardHierarchy(rootAwardNumber, timeAndMoneyForm.getOrder()));
        timeAndMoneyDocument.setAwardNumber(rootAwardNumber);
        
        getAwardHierarchyService().populateAwardHierarchyNodes(timeAndMoneyDocument.getAwardHierarchyItems(), timeAndMoneyDocument.getAwardHierarchyNodes());
        
        GlobalVariables.getUserSession().addObject(GlobalVariables.getUserSession().getKualiSessionId(), timeAndMoneyDocument);
        
        populateOtherPanels(timeAndMoneyForm.getTransactionBean().getNewAwardAmountTransaction(), timeAndMoneyForm, rootAwardNumber);
        
        return forward;
    }
        
    /*
     * This method retrieves AwardHierarchyService
     */
    protected AwardHierarchyService getAwardHierarchyService(){        
        return (AwardHierarchyService) KraServiceLocator.getService(AwardHierarchyService.class);
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
    
    /**
     * 
     * This method adds a new transaction.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addTransaction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ((TimeAndMoneyForm) form).getTransactionBean().addPendingTransactionItem();
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    
    /**
     * 
     * This method adds a deletes a transaction.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteTransaction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ((TimeAndMoneyForm) form).getTransactionBean().deletePendingTransactionItem(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    
    /**
     * 
     * This method switches the award for history, summary and Action Summary panels.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward switchAward(ActionMapping mapping, ActionForm form , HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm)form;
        String goToAwardNumber = timeAndMoneyForm.getGoToAwardNumber();
        
        populateOtherPanels(timeAndMoneyForm.getTransactionBean().getNewAwardAmountTransaction(), timeAndMoneyForm, goToAwardNumber);
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /*
     * This method populates Summary, Action Summary and History panels for selected award.
     *  
     * @param timeAndMoneyForm
     * @param timeAndMoneyForm
     * @param goToAwardNumber
     * @throws LookupException
     * @throws SQLException
     */
    private void populateOtherPanels(AwardAmountTransaction newAwardAmountTransaction, TimeAndMoneyForm timeAndMoneyForm, String goToAwardNumber)
            throws LookupException, SQLException {
        Award award = getActiveAwardVersion(goToAwardNumber);
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        timeAndMoneyDocument.setAwardNumber(award.getAwardNumber());
        timeAndMoneyDocument.setAward(award);
        if(isNewAward(timeAndMoneyForm) && !(timeAndMoneyDocument.getAward().getBeginDate() == null)){
            AwardDirectFandADistributionService awardDirectFandADistributionService = getAwardDirectFandADistributionService();
            timeAndMoneyForm.getTimeAndMoneyDocument().getAward().setAwardDirectFandADistributions
                                (awardDirectFandADistributionService.
                                        generateDefaultAwardDirectFandADistributionPeriods(timeAndMoneyForm.getTimeAndMoneyDocument().getAward()));
        }
        
        
        TimeAndMoneyHistoryService tamhs = KraServiceLocator.getService(TimeAndMoneyHistoryService.class);
        
        tamhs.getTimeAndMoneyHistory(timeAndMoneyDocument.getAwardNumber(), timeAndMoneyDocument.getTimeAndMoneyHistory(), timeAndMoneyForm.getColumnSpan());
        
        TimeAndMoneyActionSummaryService tamass = KraServiceLocator.getService(TimeAndMoneyActionSummaryService.class);
        tamass.populateActionSummary(timeAndMoneyDocument.getTimeAndMoneyActionSummaryItems(), timeAndMoneyDocument.getAwardNumber());
        
        timeAndMoneyDocument.setNewAwardAmountTransaction(newAwardAmountTransaction);
    }
    
    /**
     * This method tests if the award is new by checking the size of AwardDirectFandADistributions on the Award.
     * @param awardForm
     * @return
     */
    public boolean isNewAward(TimeAndMoneyForm timeAndMoneyForm) {
        return timeAndMoneyForm.getTimeAndMoneyDocument().getAward().getAwardDirectFandADistributions().size() == 0;
    }
    
    /**
     * 
     * This method is a helper method to retrieve AwardSponsorTermService.
     * @return
     */
    protected AwardDirectFandADistributionService getAwardDirectFandADistributionService() {
        return KraServiceLocator.getService(AwardDirectFandADistributionService.class);
    }

    /*
     * This method retrieves an active award version.
     * 
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
            award = ((List<Award>)businessObjectService.findMatching(Award.class, getHashMapToFindActiveAward(goToAwardNumber))).get(0);              
        }
        return award;
    }
    
    private Map<String, String> getHashMapToFindActiveAward(String goToAwardNumber) {
        Map<String, String> map = new HashMap<String,String>();
        map.put("awardNumber", goToAwardNumber);
        return map;
    }

    /*
     * Retrieves an ActivePendingTransactionsService.
     */
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
    
    /**
     * 
     * This method adds a new AwardDirectFandADistribution to the list.  
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addAwardDirectFandADistribution(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        awardDirectFandADistributionBean.addAwardDirectFandADistribution(((TimeAndMoneyForm) form).getAwardDirectFandADistributionBean());    
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method removes an AwardDirectFandADistribution from the list. 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteAwardDirectFandADistribution(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        timeAndMoneyForm.getTimeAndMoneyDocument().getAward().getAwardDirectFandADistributions().remove(getLineToDelete(request));
        awardDirectFandADistributionBean.updateBudgetPeriodsAfterDelete(timeAndMoneyForm.getTimeAndMoneyDocument().getAward().getAwardDirectFandADistributions());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to recalculate the Total amounts in the Direct F and A Distribution panel.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return mapping forward
     * @throws Exception
     */
    public ActionForward recalculateDirectFandADistributionTotals(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        return businessObjectService;
    }

}
