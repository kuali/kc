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
package org.kuali.kra.timeandmoney.web.struts.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.ojb.broker.accesslayer.LookupException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.kuali.coeus.award.finance.timeAndMoney.TimeAndMoneyPosts;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.sys.framework.controller.KcHoldingPageConstants;
import org.kuali.coeus.sys.framework.controller.KcTransactionalDocumentActionBase;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.award.AwardAmountInfoService;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.paymentreports.awardreports.reporting.service.ReportTrackingService;
import org.kuali.kra.award.timeandmoney.AwardDirectFandADistribution;
import org.kuali.kra.award.version.service.AwardVersionService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.timeandmoney.TimeAndMoneyForm;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.kra.timeandmoney.history.TransactionDetail;
import org.kuali.kra.timeandmoney.service.*;
import org.kuali.kra.timeandmoney.transactions.AwardAmountTransaction;
import org.kuali.kra.timeandmoney.transactions.PendingTransaction;
import org.kuali.kra.timeandmoney.transactions.TransactionRuleImpl;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.authorization.AuthorizationConstants;
import org.kuali.rice.kns.document.authorization.DocumentAuthorizer;
import org.kuali.rice.kns.document.authorization.DocumentPresentationController;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.util.Map.Entry;

public class TimeAndMoneyAction extends KcTransactionalDocumentActionBase {

    private static final String INVALID_AWARD_NUMBER_ERROR = "error.timeandmoney.invalidawardnumber";
	private static final String GO_TO_AWARD_NUMBER_FIELD_NAME = "goToAwardNumber";
	private static final String TIME_AND_MONEY_SUMMARY_AND_HISTORY_MAPPING = "timeAndMoneySummaryAndHistory";
	private static final String TIME_AND_MONEY_MAPPING = "timeAndMoney";
    public static final String AWARD_NUMBER = "awardNumber";
    public static final String TIME_AND_MONEY_DOCUMENT = "TimeAndMoneyDocument";
    public static final String DIRECT_INDIRECT_ENABLED = "1";

    private AwardVersionService awardVersionService;
    private ActivePendingTransactionsService activePendingTransactionsService;
    private TimeAndMoneyVersionService timeAndMoneyVersionService;
    private KcWorkflowService kcWorkflowService;
    private ReportTrackingService reportTrackingService;
    private AwardHierarchyService awardHierarchyService;
    private AwardAmountInfoService awardAmountInfoService;
    private TimeAndMoneyHistoryService timeAndMoneyHistoryService;
    private TimeAndMoneyActionSummaryService timeAndMoneyActionSummaryService;
    private GlobalVariableService globalVariableService;
    private DataObjectService dataObjectService;
    private TimeAndMoneyExistenceService timeAndMoneyExistenceService;
    private TimeAndMoneyService timeAndMoneyService;

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        final List<AwardHierarchyNode> awardHierarchyNodeItems = timeAndMoneyForm.getAwardHierarchyNodeItems();
        getTimeAndMoneyService().captureDateChangeTransactions(timeAndMoneyDocument, awardHierarchyNodeItems);
        captureSingleNodeMoneyTransactions(mapping, form, request, response);
        return super.save(mapping, form, request, response);
    }

    public void captureSingleNodeMoneyTransactions(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                                   HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        List<TransactionDetail> moneyTransactionDetailItems = new ArrayList<>();
        getTimeAndMoneyService().updateAwardAmountTransactions(timeAndMoneyDocument);
        // Capture amount changes in hierarchy view
        if (timeAndMoneyDocument.getAwardHierarchyNodes().size() == 1 && !timeAndMoneyForm.getDisableCurrentValues()) {
            for(Map.Entry<String, AwardHierarchyNode> awardHierarchyNode : timeAndMoneyDocument.getAwardHierarchyNodes().entrySet()){
                List<AwardHierarchyNode> awardHierarchyNodeItems = timeAndMoneyForm.getAwardHierarchyNodeItems();
                boolean refreshNeeded = getTimeAndMoneyService().captureMoneyChanges(awardHierarchyNodeItems, timeAndMoneyDocument, moneyTransactionDetailItems, awardHierarchyNode);
                if (refreshNeeded) {
                    timeAndMoneyForm.setToPendingView();
                    refreshView(mapping, timeAndMoneyForm, request, response);
                }
            }
        }
    }


    protected GlobalVariableService getGlobalVariableService() {
        if (globalVariableService == null) {
            globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }
        return globalVariableService;
    }

    protected DataObjectService getDataObjectService() {
        if (dataObjectService == null) {
            dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        }
        return dataObjectService;
    }

    protected void addPostEntry(Long awardId, String awardNumber, String documentNumber) {
        TimeAndMoneyPosts timeAndMoneyPosts = new TimeAndMoneyPosts();
        timeAndMoneyPosts.setAwardId(awardId);
        timeAndMoneyPosts.setDocumentNumber(documentNumber);
        String awardFamily = awardNumber.substring(0, StringUtils.indexOf(awardNumber, "-"));
        timeAndMoneyPosts.setAwardFamily(awardFamily);
        getDataObjectService().save(timeAndMoneyPosts);
    }

    public ActionForward postTimeAndMoney(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        final TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        final Award award = timeAndMoneyDocument.getAward();
        addPostEntry(award.getAwardId(), award.getAwardNumber(), timeAndMoneyDocument.getDocumentNumber());
        getGlobalVariableService().getMessageMap().putInfo(KeyConstants.TM_INFORMATION_POSTED, KeyConstants.TM_INFORMATION_POSTED);
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    /*
     * override route to call save before we route.
     */
    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward;
        save(mapping, form, request, response);
        actionForward = super.route(mapping, form, request, response);

        return doRoutingTasks(mapping, (TimeAndMoneyForm) form, actionForward);
    }

    protected ActionForward doRoutingTasks(ActionMapping mapping, TimeAndMoneyForm timeAndMoneyForm, ActionForward actionForward) throws ParseException {
        saveReportTrackingItems(timeAndMoneyForm);
        String routeHeaderId = timeAndMoneyForm.getDocument().getDocumentNumber();

        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_AWARD_TIME_AND_MONEY_PAGE, TIME_AND_MONEY_DOCUMENT);
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionRedirect holdingPageForward = new ActionRedirect(mapping.findForward(KcHoldingPageConstants.MAPPING_HOLDING_PAGE));
        holdingPageForward.addParameter(KcHoldingPageConstants.HOLDING_PAGE_DOCUMENT_ID, routeHeaderId);
        return routeToHoldingPage(basicForward, actionForward, holdingPageForward, returnLocation, routeHeaderId);
    }

    protected void saveReportTrackingItems(TimeAndMoneyForm timeAndMoneyForm) throws ParseException {
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        Award award = timeAndMoneyDocument.getAward();
        getReportTrackingService().generateReportTrackingAndSave(award, true);
    }
        
    /**
     * override to call save before we blanket approve.
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#blanketApprove(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward blanketApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward actionForward;
        save(mapping, form, request, response);
        actionForward = super.blanketApprove(mapping, form, request, response);

        return doRoutingTasks(mapping, (TimeAndMoneyForm) form, actionForward);
    }



    /**
     * must remove all award amount infos corresponding to this document.  Date changes create and add new Award Amount Info.  Pending Transactions
     * do not create new Award Amount Info until the document is routed or blanket approved.
     * @see org.kuali.rice.kns.web.struts.action.KualiDocumentActionBase#cancel(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward;
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        //remove all Award Amount Infos from all affected awards.
        Map<String, AwardHierarchy> awardHierarchyItems = timeAndMoneyDocument.getAwardHierarchyItems();
        for (Map.Entry<String, AwardHierarchy> awardHierarchyEntry : awardHierarchyItems.entrySet()) {
            AwardHierarchy awardHierarchy = awardHierarchyEntry.getValue();
            Award award = getAwardVersionService().getWorkingAwardVersion(awardHierarchy.getAwardNumber());
            List<AwardAmountInfo> deleteCollection = new ArrayList<>();
            for (AwardAmountInfo awardAmountInfo : award.getAwardAmountInfos()) {
                if(!(awardAmountInfo.getTimeAndMoneyDocumentNumber() == null)) {
                    if(awardAmountInfo.getTimeAndMoneyDocumentNumber().equals(timeAndMoneyDocument.getDocumentNumber())) {
                        deleteCollection.add(awardAmountInfo);
                    }
                }
            }
            getBusinessObjectService().delete(deleteCollection);
            deleteCollection.clear();
        }
        timeAndMoneyDocument.setDocumentStatus(VersionStatus.CANCELED.toString());
        getBusinessObjectService().save(timeAndMoneyDocument);
        actionForward = super.cancel(mapping, form, request, response);   
        
        return actionForward;
    }

    /**
     * 
     * This method refreshes the view depending on various view options like either active or pending view or dates only, totals and 
     * distributed/distributable.
     */
    public ActionForward refreshView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument doc = timeAndMoneyForm.getTimeAndMoneyDocument();
        //perform this logic if pending view
        if(StringUtils.equalsIgnoreCase(timeAndMoneyForm.getCurrentOrPendingView(), TimeAndMoneyForm.PENDING)){
            
            Map<String, AwardAmountTransaction> awardAmountTransactionItems = new HashMap<>();
            List<Award> awardItems = new ArrayList<>();
            List<TransactionDetail> transactionDetailItems = new ArrayList<>();
            
            updateDocumentFromSession(doc);

            //added refreshFlag boolean to service method. If doing a refresh, we don't want to reset the processed flag.  Only when T&M doc is routed for approval.
            getActivePendingTransactionsService().processTransactions(doc, doc.getAwardAmountTransactions().get(0), awardAmountTransactionItems, awardItems, transactionDetailItems, true);
            GlobalVariables.getUserSession().addObject(GlobalVariables.getUserSession().getKualiSessionId()+Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION, doc);
        //perform this logic if active view
        } else if(StringUtils.equalsIgnoreCase(timeAndMoneyForm.getCurrentOrPendingView(), TimeAndMoneyForm.CURRENT)){
            timeAndMoneyForm.setOrder(new ArrayList<String>());
            
            Award tmpAward = getCurrentAward(doc);
            doc.setAwardHierarchyItems(getAwardHierarchyService().getAwardHierarchy(doc.getRootAwardNumber(), timeAndMoneyForm.getOrder()));
            if(tmpAward != null) {
                getAwardHierarchyService().populateAwardHierarchyNodesForTandMDoc(doc.getAwardHierarchyItems(), doc.getAwardHierarchyNodes(), tmpAward.getAwardNumber(), tmpAward.getSequenceNumber().toString(), timeAndMoneyForm.getTimeAndMoneyDocument());
            } else {
                getAwardHierarchyService().populateAwardHierarchyNodesForTandMDoc(doc.getAwardHierarchyItems(), doc.getAwardHierarchyNodes(), null, null, timeAndMoneyForm.getTimeAndMoneyDocument());
            }
            GlobalVariables.getUserSession().addObject(GlobalVariables.getUserSession().getKualiSessionId()+Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION, doc);
        }
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    private void updateDocumentFromSession(TimeAndMoneyDocument doc) {
        if(doc.getAwardHierarchyNodes()==null || doc.getAwardHierarchyNodes().size()==0){
            if(GlobalVariables.getUserSession().retrieveObject(GlobalVariables.getUserSession().getKualiSessionId()+Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION)!=null){
                TimeAndMoneyDocument document = (TimeAndMoneyDocument)GlobalVariables.getUserSession().retrieveObject(GlobalVariables.getUserSession().getKualiSessionId()+Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION);
                doc.setAwardHierarchyItems(document.getAwardHierarchyItems());
                doc.setAwardHierarchyNodes(document.getAwardHierarchyNodes());
            }else {
                throw new RuntimeException("Can't Retrieve Time And Money Document from Session");
            }
        }
    }
    
    private Award getCurrentAward(TimeAndMoneyDocument timeAndMoneyDocument) {
        Award tmpAward = timeAndMoneyDocument.getAward();
        if(tmpAward == null) {
            tmpAward = getAwardVersionService().getWorkingAwardVersion(timeAndMoneyDocument.getAwardNumber());
        }
        
        return tmpAward;
    }

    @Override
    public ActionForward docHandler(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();

        ActionForward forward = handleDocument(mapping, form, request, response, timeAndMoneyForm);

        timeAndMoneyForm.initializeFormOrDocumentBasedOnCommand();        
        String rootAwardNumber = timeAndMoneyForm.getTimeAndMoneyDocument().getRootAwardNumber();
                
        timeAndMoneyDocument.setAwardHierarchyItems(getAwardHierarchyService().getAwardHierarchy(rootAwardNumber, timeAndMoneyForm.getOrder()));
        timeAndMoneyDocument.setAwardNumber(rootAwardNumber);  
        
        Award tmpAward = getCurrentAward(timeAndMoneyDocument);
        if(tmpAward != null) {
            getAwardHierarchyService().populateAwardHierarchyNodesForTandMDoc(timeAndMoneyDocument.getAwardHierarchyItems(), timeAndMoneyDocument.getAwardHierarchyNodes(), tmpAward.getAwardNumber(), tmpAward.getSequenceNumber().toString(), timeAndMoneyForm.getTimeAndMoneyDocument());
        } else {
            getAwardHierarchyService().populateAwardHierarchyNodesForTandMDoc(timeAndMoneyDocument.getAwardHierarchyItems(), timeAndMoneyDocument.getAwardHierarchyNodes(), null, null, timeAndMoneyForm.getTimeAndMoneyDocument());
        }
        //initialize award for summary display to current version of root award
        timeAndMoneyForm.setAwardForSummaryPanelDisplay(tmpAward);
        
        getAwardHierarchyService().createNodeMapsOnFormForSummaryPanel(timeAndMoneyDocument.getAwardHierarchyNodes(), timeAndMoneyForm.getPreviousNodeMap(),
                timeAndMoneyForm.getNextNodeMap());

        GlobalVariables.getUserSession().addObject(GlobalVariables.getUserSession().getKualiSessionId()+Constants.TIME_AND_MONEY_DOCUMENT_STRING_FOR_SESSION, timeAndMoneyDocument);
        
        populateOtherPanels(timeAndMoneyForm.getTransactionBean().getNewAwardAmountTransaction(), timeAndMoneyForm, rootAwardNumber);

        return forward;
    }

    public ActionForward handleDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response, TimeAndMoneyForm timeAndMoneyForm) throws Exception {
        String command = timeAndMoneyForm.getCommand();
        ActionForward forward = null;        
        if (KewApiConstants.ACTIONLIST_INLINE_COMMAND.equals(command)) {
            String docIdRequestParameter = request.getParameter(KRADConstants.PARAMETER_DOC_ID);
            Document retrievedDocument = getDocumentService().getByDocumentHeaderId(docIdRequestParameter);
            timeAndMoneyForm.setDocument(retrievedDocument);
            request.setAttribute(KRADConstants.PARAMETER_DOC_ID, docIdRequestParameter);
            ActionForward baseForward = mapping.findForward(Constants.MAPPING_COPY_PROPOSAL_PAGE);
            forward = new ActionForward(buildForwardStringForActionListCommand(
                    baseForward.getPath(),docIdRequestParameter));  
        } else if (Constants.MAPPING_AWARD_TIME_AND_MONEY_PAGE.equals(command)) {
            loadDocument(timeAndMoneyForm);
        }else {
            forward = super.docHandler(mapping, form, request, response);
        }
        
        if (Constants.MAPPING_AWARD_TIME_AND_MONEY_PAGE.equals(command)) {
            forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
        
        return forward;
    }
    
    public ActionForward addTransaction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        final List<AwardHierarchyNode> awardHierarchyNodeItems = timeAndMoneyForm.getAwardHierarchyNodeItems();
        getTimeAndMoneyService().captureDateChangeTransactions(timeAndMoneyDocument, awardHierarchyNodeItems);
        if (timeAndMoneyForm.getTransactionBean().addPendingTransactionItem()) {
            timeAndMoneyForm.setToPendingView();
            refreshView(mapping, form, request, response);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    

    public ActionForward deleteTransaction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        final List<AwardHierarchyNode> awardHierarchyNodeItems = timeAndMoneyForm.getAwardHierarchyNodeItems();
        getTimeAndMoneyService().captureDateChangeTransactions(timeAndMoneyDocument, awardHierarchyNodeItems);
        ((TimeAndMoneyForm) form).getTransactionBean().deletePendingTransactionItem(getLineToDelete(request));
        refreshView(mapping, form, request, response);
        return mapping.findForward(Constants.MAPPING_BASIC);        
    }
    
    /**
     * This method switches the award for history, summary and Action Summary panels.
     */
    public ActionForward switchAward(ActionMapping mapping, ActionForm form , HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm)form;
        String goToAwardNumber = timeAndMoneyForm.getGoToAwardNumber();
        
        populateOtherPanels(timeAndMoneyForm.getTransactionBean().getNewAwardAmountTransaction(), timeAndMoneyForm, goToAwardNumber);
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward timeAndMoneySummaryAndHistory(ActionMapping mapping, ActionForm form , HttpServletRequest request, HttpServletResponse response) throws Exception {
    	TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
    	TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
    	String awardNumber = null;
    	if (StringUtils.isBlank(timeAndMoneyForm.getGoToAwardNumber())) {
    		awardNumber = timeAndMoneyDocument.getAwardNumber();
    	} else {
    		Award award = getAwardVersionService().getWorkingAwardVersion(timeAndMoneyForm.getGoToAwardNumber());
	        if (award == null) {
	            GlobalVariables.getMessageMap().putError(GO_TO_AWARD_NUMBER_FIELD_NAME, INVALID_AWARD_NUMBER_ERROR, timeAndMoneyForm.getGoToAwardNumber());
	            return mapping.findForward(Constants.MAPPING_BASIC);
	        }
	        awardNumber = award.getAwardNumber();
    	}

        timeAndMoneyDocument.setAwardVersionHistoryList(getTimeAndMoneyHistoryService().buildTimeAndMoneyHistoryObjects(awardNumber, true));
        timeAndMoneyDocument.setTimeAndMoneyActionSummaryItems(getTimeAndMoneyActionSummaryService().populateActionSummary(awardNumber));

    	return mapping.findForward(TIME_AND_MONEY_SUMMARY_AND_HISTORY_MAPPING);
    }

    /*
     * This method populates Summary, Action Summary and History panels for selected award.
     */
    private void populateOtherPanels(AwardAmountTransaction newAwardAmountTransaction, TimeAndMoneyForm timeAndMoneyForm, String goToAwardNumber)
            throws LookupException, SQLException, WorkflowException {
        Award award = getAwardVersionService().getWorkingAwardVersion(goToAwardNumber);
        if (award == null) {
            GlobalVariables.getMessageMap().putError(GO_TO_AWARD_NUMBER_FIELD_NAME, INVALID_AWARD_NUMBER_ERROR, goToAwardNumber);
            return;
        }
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        timeAndMoneyDocument.setAwardNumber(award.getAwardNumber());
        timeAndMoneyDocument.setAward(award);
        timeAndMoneyDocument.setNewAwardAmountTransaction(newAwardAmountTransaction);
    }
    
    /**
     * This method tests if the award is new by checking the size of AwardDirectFandADistributions on the Award.
     */
    public boolean isNewAward(TimeAndMoneyForm timeAndMoneyForm) {
        return timeAndMoneyForm.getTimeAndMoneyDocument().getAward().getAwardDirectFandADistributions().size() == 0;
    }

    /**
     * 
     * This method builds the string for the ActionForward
     */
    public String buildForwardStringForActionListCommand(String forwardPath, String docIdRequestParameter){
        StringBuilder sb = new StringBuilder();
        sb.append(forwardPath);
        sb.append("?");
        sb.append(KRADConstants.PARAMETER_DOC_ID);
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
     */
    public ActionForward addAwardDirectFandADistribution(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        final List<AwardHierarchyNode> awardHierarchyNodeItems = timeAndMoneyForm.getAwardHierarchyNodeItems();
        getTimeAndMoneyService().captureDateChangeTransactions(timeAndMoneyDocument, awardHierarchyNodeItems);
        timeAndMoneyForm.getAwardDirectFandADistributionBean()
                    .addAwardDirectFandADistribution(((TimeAndMoneyForm) form).getAwardDirectFandADistributionBean());    
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method removes an AwardDirectFandADistribution from the list.
     */
    public ActionForward deleteAwardDirectFandADistribution(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        final List<AwardHierarchyNode> awardHierarchyNodeItems = timeAndMoneyForm.getAwardHierarchyNodeItems();
        getTimeAndMoneyService().captureDateChangeTransactions(timeAndMoneyDocument, awardHierarchyNodeItems);
        AwardDirectFandADistribution awardDirectFandADistribution = timeAndMoneyForm.getTimeAndMoneyDocument().getAward().getAwardDirectFandADistributions().get(getLineToDelete(request));
        timeAndMoneyForm.getTimeAndMoneyDocument().getAward().getAwardDirectFandADistributions().remove(getLineToDelete(request));
        getBusinessObjectService().delete(awardDirectFandADistribution);
        timeAndMoneyForm.getAwardDirectFandADistributionBean().updateBudgetPeriodsAfterDelete(timeAndMoneyForm.getTimeAndMoneyDocument().getAward().getAwardDirectFandADistributions());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is used to recalculate the Total amounts in the Direct F and A Distribution panel.
     */
    public ActionForward timeAndMoney(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       
        return mapping.findForward(TIME_AND_MONEY_MAPPING);
    }
    
    /**
     * This method is used to recalculate the Total amounts in the Direct F and A Distribution panel.
     */
    public ActionForward recalculateDirectFandADistributionTotals(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method retrieves the awardDocument from the session and redirects the user to the appropriate Award.
     */
    public ActionForward returnToAward(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        TimeAndMoneyDocument timeAndMoneyDocument = timeAndMoneyForm.getTimeAndMoneyDocument();
        if(!getKcWorkflowService().isInWorkflow(timeAndMoneyDocument)){
            this.save(mapping, form, request, response);
        }
        String awardDocumentNumber = (String) GlobalVariables.getUserSession().retrieveObject(Constants.AWARD_DOCUMENT_STRING_FOR_SESSION + "-" + timeAndMoneyDocument.getDocumentNumber());
        if (StringUtils.isBlank(awardDocumentNumber)) {
            awardDocumentNumber = timeAndMoneyDocument.getAward().getAwardDocument().getDocumentNumber();
        }
        //reload document to make sure we have a valid workflow document
        AwardDocument awardDocument = (AwardDocument) getDocumentService().getByDocumentHeaderId(awardDocumentNumber);       
        String routeHeaderId = awardDocument.getDocumentHeader().getWorkflowDocument().getDocumentId();
        GlobalVariables.getUserSession().removeObject(Constants.AWARD_DOCUMENT_STRING_FOR_SESSION + "-" + timeAndMoneyDocument.getDocumentNumber());

        String forward = buildForwardUrl(routeHeaderId);
        return new ActionForward(forward, true);
    }
    
    public boolean isDirectIndirectViewEnabled() {
        String directIndirectEnabledValue = getParameterService().getParameterValueAsString(Constants.PARAMETER_MODULE_AWARD, ParameterConstants.DOCUMENT_COMPONENT, "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST");
        return DIRECT_INDIRECT_ENABLED.equals(directIndirectEnabledValue);
    }

    public ActionForward goToNextAward(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        return goToAward(mapping, timeAndMoneyForm, timeAndMoneyForm.getNextNodeMap().get(timeAndMoneyForm.getAwardForSummaryPanelDisplay().getAwardNumber()));

    }
        
    public ActionForward goToPreviousAward(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm) form;
        return goToAward(mapping, timeAndMoneyForm, timeAndMoneyForm.getPreviousNodeMap().get(timeAndMoneyForm.getAwardForSummaryPanelDisplay().getAwardNumber()));
    }

    private ActionForward goToAward(ActionMapping mapping, TimeAndMoneyForm timeAndMoneyForm, String awardNumber) throws Exception {
        Award awardForSummary = ((List<Award>)getBusinessObjectService().findMatching(Award.class, Collections.singletonMap(AWARD_NUMBER, awardNumber))).get(0);
        timeAndMoneyForm.setAwardForSummaryPanelDisplay(awardForSummary);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward editOrVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        TimeAndMoneyForm timeAndMoneyForm = (TimeAndMoneyForm)form;
        TimeAndMoneyDocument doc = timeAndMoneyForm.getTimeAndMoneyDocument();
        String rootAwardNumber = doc.getRootAwardNumber();
        TimeAndMoneyDocument finalTandM = getTimeAndMoneyVersionService().findOpenedTimeAndMoney(rootAwardNumber);
        if (finalTandM == null) {
        	return mapping.findForward(Constants.MAPPING_BASIC);
        } else {
	        String routeHeaderId = finalTandM.getDocumentHeader().getWorkflowDocument().getDocumentId();
	        String returnAwardDocId = (String) GlobalVariables.getUserSession().retrieveObject(Constants.AWARD_DOCUMENT_STRING_FOR_SESSION + "-" + doc.getDocumentNumber());
	        GlobalVariables.getUserSession().addObject(Constants.AWARD_DOCUMENT_STRING_FOR_SESSION + "-" + routeHeaderId, returnAwardDocId);
	        String forwardString = buildForwardUrl(routeHeaderId);
	        return new ActionForward(forwardString, true);
        }
    }

    @Override
    // Overriding this because KraTransactionalDocumentActionBase assumes the authorizer is of type KcDocumentAuthorizerBase
    protected void populateAuthorizationFields(KualiDocumentFormBase formBase) {
        if (formBase.isFormDocumentInitialized()) {
            Document document = formBase.getDocument();
            Person user = GlobalVariables.getUserSession().getPerson();
            DocumentPresentationController documentPresentationController = KNSServiceLocator.getDocumentHelperService().getDocumentPresentationController(document);
            DocumentAuthorizer documentAuthorizer = getDocumentHelperService().getDocumentAuthorizer(document);
            Set<String> documentActions =  documentPresentationController.getDocumentActions(document);
            documentActions = documentAuthorizer.getDocumentActions(document, user, documentActions);

            if (getDataDictionaryService().getDataDictionary().getDocumentEntry(document.getClass().getName()).getUsePessimisticLocking()) {
                documentActions = getPessimisticLockService().getDocumentActions(document, user, documentActions);
            }
            
            Set<String> editModes = new HashSet<>();
            if (!documentAuthorizer.canOpen(document, user)) {
                editModes.add(AuthorizationConstants.EditMode.UNVIEWABLE);
            } else if (documentActions.contains(KRADConstants.KUALI_ACTION_CAN_EDIT)) {
                editModes.add(AuthorizationConstants.EditMode.FULL_ENTRY);
            } else {
                editModes.add(AuthorizationConstants.EditMode.VIEW_ONLY);
            }
            Map editMode = this.convertSetToMap(editModes);
            if (getDataDictionaryService().getDataDictionary().getDocumentEntry(document.getClass().getName()).getUsePessimisticLocking()) {
                editMode = getPessimisticLockService().establishLocks(document, editMode, user);
            }
            
            // We don't want to use KNS way to determine can edit document overview
            // It should be the same as can edit
            if (editMode.containsKey(AuthorizationConstants.EditMode.FULL_ENTRY)) {
                if (!documentActions.contains(KRADConstants.KUALI_ACTION_CAN_EDIT_DOCUMENT_OVERVIEW)) {
                    documentActions.add(KRADConstants.KUALI_ACTION_CAN_EDIT_DOCUMENT_OVERVIEW);
                }
            } else {
                if (documentActions.contains(KRADConstants.KUALI_ACTION_CAN_EDIT_DOCUMENT_OVERVIEW)) {
                    documentActions.remove(KRADConstants.KUALI_ACTION_CAN_EDIT_DOCUMENT_OVERVIEW);
                }
            }
            //copy action is meaningless in T&M.
            if (documentActions.contains((KRADConstants.KUALI_ACTION_CAN_COPY))) {
                documentActions.remove(KRADConstants.KUALI_ACTION_CAN_COPY);
            }
            formBase.setDocumentActions(convertSetToMap(documentActions));
            formBase.setEditingMode(editMode);
        }
        
    }

    public TimeAndMoneyVersionService getTimeAndMoneyVersionService() {
        if (timeAndMoneyVersionService == null) {
            timeAndMoneyVersionService = KcServiceLocator.getService(TimeAndMoneyVersionService.class);
        }
        return timeAndMoneyVersionService;
    }

    public void setTimeAndMoneyVersionService(TimeAndMoneyVersionService timeAndMoneyVersionService) {
        this.timeAndMoneyVersionService = timeAndMoneyVersionService;
    }

    public AwardVersionService getAwardVersionService() {
        if(awardVersionService == null) {
            awardVersionService = KcServiceLocator.getService(AwardVersionService.class);
        }
        return awardVersionService;
    }

    protected ActivePendingTransactionsService getActivePendingTransactionsService(){
        if (activePendingTransactionsService == null) {
            activePendingTransactionsService = KcServiceLocator.getService(ActivePendingTransactionsService.class);
        }
        return activePendingTransactionsService;
    }

    protected KcWorkflowService getKcWorkflowService() {
        if (kcWorkflowService == null) {
            kcWorkflowService = KcServiceLocator.getService(KcWorkflowService.class);
        }
        return kcWorkflowService;
    }

    public ReportTrackingService getReportTrackingService() {
        if (reportTrackingService == null) {
            reportTrackingService = KcServiceLocator.getService(ReportTrackingService.class);
        }
        return reportTrackingService;
    }

    protected AwardHierarchyService getAwardHierarchyService(){
        if (awardHierarchyService == null) {
            awardHierarchyService = KcServiceLocator.getService(AwardHierarchyService.class);
        }
        return awardHierarchyService;
    }

    public AwardAmountInfoService getAwardAmountInfoService() {
        if (awardAmountInfoService == null) {
            awardAmountInfoService = KcServiceLocator.getService(AwardAmountInfoService.class);
        }
        return awardAmountInfoService;
    }

    public TimeAndMoneyHistoryService getTimeAndMoneyHistoryService() {
        if (timeAndMoneyHistoryService == null) {
            timeAndMoneyHistoryService = KcServiceLocator.getService(TimeAndMoneyHistoryService.class);
        }
        return timeAndMoneyHistoryService;
    }

    public TimeAndMoneyActionSummaryService getTimeAndMoneyActionSummaryService() {
        if (timeAndMoneyActionSummaryService == null) {
            timeAndMoneyActionSummaryService = KcServiceLocator.getService(TimeAndMoneyActionSummaryService.class);
        }
        return timeAndMoneyActionSummaryService;
    }

    protected TimeAndMoneyExistenceService getTimeAndMoneyExistenceService() {
        if (timeAndMoneyExistenceService == null) {
            timeAndMoneyExistenceService = KcServiceLocator.getService(TimeAndMoneyExistenceService.class);
        }
        return timeAndMoneyExistenceService;
    }

    protected TimeAndMoneyService getTimeAndMoneyService() {
        if (timeAndMoneyService == null) {
            timeAndMoneyService = KcServiceLocator.getService(TimeAndMoneyService.class);
        }
        return timeAndMoneyService;    }
}

