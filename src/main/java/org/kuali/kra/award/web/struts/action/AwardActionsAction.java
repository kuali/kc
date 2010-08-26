/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.award.web.struts.action;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.AwardNumberService;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyTempObject;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.award.printing.AwardPrintParameters;
import org.kuali.kra.award.printing.AwardPrintType;
import org.kuali.kra.award.printing.service.AwardPrintingService;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.rice.core.util.RiceConstants;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;

/**
 * 
 * This class represents the Struts Action for Award Actions page(AwardActions.jsp)
 */
public class AwardActionsAction extends AwardAction implements AuditModeAction {
    
    private static final String ZERO = "0";
    private static final String NEW_CHILD_SELECTED_AWARD_OPTION = "c";
    private static final String NEW_CHILD_COPY_FROM_PARENT_OPTION = "b";
    private static final String ERROR_CANCEL_PENDING_PROPOSALS = "error.cancel.fundingproposal.pendingVersion";
    public static final String NEW_CHILD_NEW_OPTION = "a";
    public static final String AWARD_COPY_NEW_OPTION = "a";
    public static final String AWARD_COPY_CHILD_OF_OPTION = "b";

    @Override
    protected void validateLookupInquiryFullParameter(HttpServletRequest request, ActionForm form, String fullParameter) {
        if(fullParameter.startsWith("methodToCall.performLookup.(!!org.kuali.kra.award.home.Award!!).(((awardNumber:awardHierarchyTempObject")) {
            return;
        } else {
            super.validateLookupInquiryFullParameter(request,form,fullParameter);
        }
    }

    /** {@inheritDoc} */
    public ActionForward activate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return new AuditActionHelper().setAuditMode(mapping, (AwardForm) form, true);
    }

    /** {@inheritDoc} */
    public ActionForward deactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return new AuditActionHelper().setAuditMode(mapping, (AwardForm) form, false);
    }
    
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        ActionForward forward = super.reload(mapping, form, request, response);
        super.populateAwardHierarchy(form);
        
        return forward;
    }
    
    /**
     * 
     * This method corresponds copy award action on Award Hierarchy UI. Depending on various options selected appropriate helper methods get called.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward copyAward(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        AwardForm awardForm = (AwardForm)form;
        String awardNumber = getAwardNumber(request);
        String reversedAwardNumber = StringUtils.reverse(awardNumber);                
        int index = Integer.parseInt(StringUtils.substring(reversedAwardNumber, 0,reversedAwardNumber.indexOf(ZERO)));
        ActionForward forward = null;
        AwardHierarchy newRootNode = null;
        
        if(awardForm.getAwardHierarchyTempObjects().get(index).getCopyAwardRadio()!=null){
            String radio = awardForm.getAwardHierarchyTempObjects().get(index).getCopyAwardRadio();
            Boolean copyDescendants = awardForm.getAwardHierarchyTempObjects().get(index).getCopyDescendants();
            AwardHierarchy targetNode = findTargetNode(request, awardForm);            
            if(StringUtils.equalsIgnoreCase(radio, AWARD_COPY_NEW_OPTION)){
                if(copyDescendants!=null && copyDescendants){
                    newRootNode = awardForm.getAwardHierarchyBean().copyAwardAndAllDescendantsAsNewHierarchy(targetNode.getAwardNumber());
                }else{
                    newRootNode = awardForm.getAwardHierarchyBean().copyAwardAsNewHierarchy(targetNode.getAwardNumber());
                }
                forward = prepareToForwardToNewChildAward(mapping, awardForm, targetNode, newRootNode);    
            }else if(StringUtils.equalsIgnoreCase(radio, AWARD_COPY_CHILD_OF_OPTION)){
                String awardNumberOfNodeToBeParent = awardForm.getAwardHierarchyTempObjects().get(index).getCopyAwardPanelTargetAward();
                if(!StringUtils.isEmpty(awardNumberOfNodeToBeParent)) {
                    if(copyDescendants!=null && copyDescendants){    
                        if(!StringUtils.isEmpty(awardNumberOfNodeToBeParent)) {
                            newRootNode = awardForm.getAwardHierarchyBean().copyAwardAndDescendantsAsChildOfAnotherAward(targetNode.getAwardNumber(), awardNumberOfNodeToBeParent);
                        }
                    }else{
                        newRootNode = awardForm.getAwardHierarchyBean().copyAwardAsChildOfAnotherAward(targetNode.getAwardNumber(), awardNumberOfNodeToBeParent);
                    }    
                    forward = prepareToForwardToNewChildAward(mapping, awardForm, targetNode, newRootNode); 
                }else{
                    GlobalVariables.getMessageMap().putError("awardHierarchyTempObject[" + index + "].copyAwardPanelTargetAward", KeyConstants.ERROR_COPY_AWARD_CHILDOF_AWARD_NOT_SELECTED, awardNumber);
                    awardForm.getFundingProposalBean().setAllAwardsForAwardNumber(null);
                    forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);    
                }
            }
        }else{  
            GlobalVariables.getMessageMap().putError("awardHierarchyTempObject[" + index + "].copyAwardPanelTargetAward", KeyConstants.ERROR_COPY_AWARD_NO_OPTION_SELECTED, awardNumber);
            forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
        return forward;
    }
    
    /**
     * 
     * This method corresponds to the Create New Child behavior on Award Hierarchy JQuery UI. It calls various helper methods based on the options 
     * selected in the UI.
     *  
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm)form;
        String awardNumber = getAwardNumber(request);
        String reversedAwardNumber = StringUtils.reverse(awardNumber);
        int index = Integer.parseInt(StringUtils.substring(reversedAwardNumber, 0,reversedAwardNumber.indexOf(ZERO)));
        ActionForward forward = null;
        
        if(awardForm.getAwardHierarchyTempObjects().get(index).getCreateNewChildRadio()!=null){
            AwardHierarchy targetNode = findTargetNode(request, awardForm);
            String radio = awardForm.getAwardHierarchyTempObjects().get(index).getCreateNewChildRadio();
            if(StringUtils.equalsIgnoreCase(radio, NEW_CHILD_NEW_OPTION)){
                AwardHierarchy newChildNode = awardForm.getAwardHierarchyBean().createNewChildAward(targetNode.getAwardNumber());                
                forward = prepareToForwardToNewChildAward(mapping, awardForm, targetNode, newChildNode);
            }else if(StringUtils.equalsIgnoreCase(radio, NEW_CHILD_COPY_FROM_PARENT_OPTION)){
                AwardHierarchy newChildNode = awardForm.getAwardHierarchyBean().createNewAwardBasedOnParent(targetNode.getAwardNumber());
                forward = prepareToForwardToNewChildAward(mapping, awardForm, targetNode, newChildNode);
            }else if(StringUtils.equalsIgnoreCase(radio, NEW_CHILD_SELECTED_AWARD_OPTION)){
                String awardNumberOfNodeToCopyFrom = awardForm.getAwardHierarchyTempObjects().get(index).getNewChildPanelTargetAward();
                if(StringUtils.isEmpty(awardNumberOfNodeToCopyFrom)) {
                    GlobalVariables.getMessageMap().putError("awardHierarchyTempObject[" + index + "].newChildPanelTargetAward", KeyConstants.ERROR_CREATE_NEW_CHILD_OTHER_AWARD_NOT_SELECTED, awardNumber);
                    forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
                }else{
                    AwardHierarchy newChildNode = awardForm.getAwardHierarchyBean().createNewChildAwardBasedOnAnotherAwardInHierarchy(
                            awardNumberOfNodeToCopyFrom, targetNode.getAwardNumber());
                    forward = prepareToForwardToNewChildAward(mapping, awardForm, targetNode, newChildNode);    
                }               
            }
        }else{
            GlobalVariables.getMessageMap().putError("awardHierarchyTempObject[" + index + "].newChildPanelTargetAward", KeyConstants.ERROR_CREATE_NEW_CHILD_NO_OPTION_SELECTED, awardNumber);
            forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
        return forward;
        
    }
    
    /**
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward createANewChildAward(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardHierarchy targetNode = findTargetNode(request, awardForm);
        AwardHierarchy newChildNode = awardForm.getAwardHierarchyBean().createNewChildAward(targetNode.getAwardNumber());
        return prepareToForwardToNewChildAward(mapping, awardForm, targetNode, newChildNode);
    }

    /**
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward createANewChildAwardBasedOnParent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardHierarchy targetNode = findTargetNode(request, awardForm);
        AwardHierarchy newChildNode = awardForm.getAwardHierarchyBean().createNewAwardBasedOnParent(targetNode.getAwardNumber());
        return prepareToForwardToNewChildAward(mapping, awardForm, targetNode, newChildNode);
    }

    public ActionForward createANewChildAwardBasedOnAnotherAwardInHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String awardNumberOfNodeToCopyFrom = getHierarchyTargetAwardNumber(request);
        if(StringUtils.isEmpty(awardNumberOfNodeToCopyFrom)) {
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
        AwardForm awardForm = (AwardForm) form;
        AwardHierarchy targetNode = findTargetNode(request, awardForm);
        AwardHierarchy newChildNode = awardForm.getAwardHierarchyBean().createNewChildAwardBasedOnAnotherAwardInHierarchy(awardNumberOfNodeToCopyFrom,
                                                                                                                            targetNode.getAwardNumber());
        return prepareToForwardToNewChildAward(mapping, awardForm, targetNode, newChildNode);
    }

    public ActionForward copyAwardAsANewHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardHierarchy targetNode = findTargetNode(request, awardForm);
        AwardHierarchy newRootNode = awardForm.getAwardHierarchyBean().copyAwardAsNewHierarchy(targetNode.getAwardNumber());
        return prepareToForwardToNewChildAward(mapping, awardForm, targetNode, newRootNode);
    }

    public ActionForward copyAwardAsANewHierarchyWithDescendants(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardHierarchy targetNode = findTargetNode(request, awardForm);
        AwardHierarchy newRootNode = awardForm.getAwardHierarchyBean().copyAwardAndAllDescendantsAsNewHierarchy(targetNode.getAwardNumber());
        return prepareToForwardToNewChildAward(mapping, awardForm, targetNode, newRootNode);
    }
    
    public ActionForward copyAwardAsChildOfAnotherAward(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardHierarchy targetNode = findTargetNode(request, awardForm);
        String awardNumberOfNodeToBeParent = getHierarchyTargetAwardNumber(request);
        awardForm.getAwardHierarchyBean().copyAwardAsChildOfAnotherAward(targetNode.getAwardNumber(), awardNumberOfNodeToBeParent);
        populateAwardHierarchy(awardForm);
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward copyAwardAndDescendantsAsChildOfAnotherAward(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardHierarchy targetNode = findTargetNode(request, awardForm);
        String awardNumberOfNodeToBeParent = getHierarchyTargetAwardNumber(request);
        if(!StringUtils.isEmpty(awardNumberOfNodeToBeParent)) {
            awardForm.getAwardHierarchyBean().copyAwardAndDescendantsAsChildOfAnotherAward(targetNode.getAwardNumber(), awardNumberOfNodeToBeParent);
        }
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward copyAwardAsAChildInCurrentHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardHierarchy targetNode = findTargetNode(request, awardForm);
        String awardNumberOfNodeToBeParent = getHierarchyTargetAwardNumber(request);
        awardForm.getAwardHierarchyBean().copyAwardAsChildOfAnAwardInCurrentHierarchy(targetNode.getAwardNumber(), awardNumberOfNodeToBeParent);
        populateAwardHierarchy(awardForm);
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward copyAwardAsAChildInCurrentHierarchyWithDescendants(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardHierarchy targetNode = findTargetNode(request, awardForm);
        String awardNumberOfNodeToBeParent = getHierarchyTargetAwardNumber(request);
        awardForm.getAwardHierarchyBean().copyAwardAndDescendantsAsChildOfAnAwardInCurrentHierarchy(targetNode.getAwardNumber(), awardNumberOfNodeToBeParent);
        populateAwardHierarchy(awardForm);
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward copyAwardAsAChildOfAwardInAnotherHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardHierarchy targetNode = findTargetNode(request, awardForm);
        String awardNumberOfNodeToBeParent = getHierarchyTargetAwardNumber(request);
        awardForm.getAwardHierarchyBean().copyAwardAsChildOfAnAwardInAnotherHierarchy(targetNode.getAwardNumber(), awardNumberOfNodeToBeParent);
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward copyAwardAsAChildOfAwardInAnotherHierarchyWithDescendants(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardHierarchy targetNode = findTargetNode(request, awardForm);
        String awardNumberOfNodeToBeParent = getHierarchyTargetAwardNumber(request);
        if(!StringUtils.isEmpty(awardNumberOfNodeToBeParent)) {
            awardForm.getAwardHierarchyBean().copyAwardAndDescendantsAsChildOfAnAwardInAnotherHierarchy(targetNode.getAwardNumber(), awardNumberOfNodeToBeParent);
        }
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }    

    public ActionForward selectAllAwardPrintNoticeItems(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm)form;
        awardForm.getAwardPrintNotice().selectAllItems();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward deselectAllAwardPrintNoticeItems(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm)form;
        awardForm.getAwardPrintNotice().deselectAllItems();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward printNotice(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AwardForm awardForm = (AwardForm) form;
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put(AwardPrintParameters.ADDRESS_LIST
                .getAwardPrintParameter(), awardForm.getAwardPrintNotice()
                .getSponsorContacts());
        reportParameters.put(AwardPrintParameters.FOREIGN_TRAVEL
                .getAwardPrintParameter(), awardForm.getAwardPrintNotice()
                .getForeignTravel());
        reportParameters.put(AwardPrintParameters.REPORTING
                .getAwardPrintParameter(), awardForm.getAwardPrintNotice()
                .getReports());
        reportParameters.put(AwardPrintParameters.CLOSEOUT
                .getAwardPrintParameter(), awardForm.getAwardPrintNotice()
                .getCloseout());
        reportParameters.put(AwardPrintParameters.FUNDING_SUMMARY
                .getAwardPrintParameter(), awardForm.getAwardPrintNotice()
                .getFundingSummary());
        reportParameters.put(AwardPrintParameters.SPECIAL_REVIEW
                .getAwardPrintParameter(), awardForm.getAwardPrintNotice()
                .getSpecialReview());
        reportParameters.put(AwardPrintParameters.COMMENTS
                .getAwardPrintParameter(), awardForm.getAwardPrintNotice()
                .getComments());
        reportParameters.put(AwardPrintParameters.HIERARCHY_INFO
                .getAwardPrintParameter(), awardForm.getAwardPrintNotice()
                .getHierarchy());
        reportParameters.put(AwardPrintParameters.SUBCONTRACT
                .getAwardPrintParameter(), awardForm.getAwardPrintNotice()
                .getSubAward());
        reportParameters.put(AwardPrintParameters.COST_SHARING
                .getAwardPrintParameter(), awardForm.getAwardPrintNotice()
                .getCostShare());
        reportParameters.put(AwardPrintParameters.KEYWORDS
                .getAwardPrintParameter(), awardForm.getAwardPrintNotice()
                .getKeywords());
        reportParameters.put(AwardPrintParameters.TECHNICAL_REPORTING
                .getAwardPrintParameter(), awardForm.getAwardPrintNotice()
                .getTechnicalReports());
        reportParameters.put(AwardPrintParameters.EQUIPMENT
                .getAwardPrintParameter(), awardForm.getAwardPrintNotice()
                .getEquipment());
        reportParameters.put(AwardPrintParameters.OTHER_DATA
                .getAwardPrintParameter(), awardForm.getAwardPrintNotice()
                .getOtherData());
        reportParameters.put(AwardPrintParameters.TERMS
                .getAwardPrintParameter(), awardForm.getAwardPrintNotice()
                .getTerms());
        reportParameters.put(AwardPrintParameters.FA_COST
                .getAwardPrintParameter(), awardForm.getAwardPrintNotice()
                .getFaRates());
        reportParameters.put(AwardPrintParameters.PAYMENT
                .getAwardPrintParameter(), awardForm.getAwardPrintNotice()
                .getPayment());
        reportParameters.put(AwardPrintParameters.FLOW_THRU
                .getAwardPrintParameter(), awardForm.getAwardPrintNotice()
                .getFlowThru());
        reportParameters.put(AwardPrintParameters.PROPOSAL_DUE
                .getAwardPrintParameter(), false);
        //awardForm.getAwardPrintNotice().getProposalsDue());
        
        reportParameters.put(AwardPrintParameters.SIGNATURE_REQUIRED
                .getAwardPrintParameter(), awardForm.getAwardPrintNotice()
                .getRequireSignature());
        AwardPrintingService awardPrintService = KraServiceLocator
                .getService(AwardPrintingService.class);
        AwardDocument awardDocument = awardForm.getAwardDocument();
        Award award = awardDocument.getAward();
        AttachmentDataSource dataStream = awardPrintService.printAwardReport(
                award,
                AwardPrintType.AWARD_NOTICE_REPORT.getAwardPrintType(),
                reportParameters);
        streamToResponse(dataStream, response);
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward printChangeReport(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put(AwardPrintParameters.SIGNATURE_REQUIRED
                .getAwardPrintParameter(), awardForm
                .getAwardPrintChangeReport().getRequireSignature());
        reportParameters.put(AwardPrintParameters.SEQUENCE_NUMBER
                .getAwardPrintParameter(), awardForm
                .getAwardPrintChangeReport().getAwardVersion());
        reportParameters.put(AwardPrintParameters.TRANSACTION_ID
                .getAwardPrintParameter(), awardForm
                .getAwardPrintChangeReport().getTransactionId());
        AwardPrintingService awardPrintService = KraServiceLocator
                .getService(AwardPrintingService.class);
        AttachmentDataSource dataStream = awardPrintService.printAwardReport(
                awardForm.getAwardDocument().getAward(), AwardPrintType.AWARD_DELTA_REPORT
                        .getAwardPrintType(), reportParameters);
        streamToResponse(dataStream, response);
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward printHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm)form;
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        AwardPrintingService awardPrintService = KraServiceLocator
                .getService(AwardPrintingService.class);
        AttachmentDataSource dataStream = awardPrintService.printAwardReport(
                awardForm.getAwardDocument().getAward(),
                AwardPrintType.AWARD_BUDGET_HIERARCHY.getAwardPrintType(),
                reportParameters);
        streamToResponse(dataStream, response);
        return null;
    }

    public ActionForward printHierarchyModification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm)form;
        //TODO: Add printing service call here
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward printBudget(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
//        Map<String, Object> reportParameters = new HashMap<String, Object>();
//        AwardPrintingService awardPrintService = KraServiceLocator
//                .getService(AwardPrintingService.class);
//        AttachmentDataSource dataStream = awardPrintService.printAwardReport(
//                awardForm.getAwardDocument(), AwardPrintType.AWARD_TEMPLATE
//                        .getAwardPrintType(), reportParameters);
//        streamToResponse(dataStream, response);
//        return null;
        //TODO: Add printing service call here
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward printTimeMoneyHistory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        AwardPrintingService awardPrintService = KraServiceLocator
                .getService(AwardPrintingService.class);
        AttachmentDataSource dataStream = awardPrintService.printAwardReport(
                awardForm.getAwardDocument().getAward(),
                AwardPrintType.MONEY_AND_END_DATES_HISTORY.getAwardPrintType(),
                reportParameters);
        streamToResponse(dataStream, response);
        return null;
    }

    public ActionForward printTransactionDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put(AwardPrintParameters.HIGHEST_TRANSACTION_ID_INDEX
                .getAwardPrintParameter(), awardForm.getIndexOfAwardAmountInfoForDisplay());
        AwardPrintingService awardPrintService = KraServiceLocator
                .getService(AwardPrintingService.class);
        AttachmentDataSource dataStream = awardPrintService.printAwardReport(
                awardForm.getAwardDocument().getAward(),
                AwardPrintType.AWARD_BUDGET_HISTORY_TRANSACTION
                        .getAwardPrintType(), reportParameters);
        streamToResponse(dataStream, response);
        return null;
    }

    public AwardNumberService getAwardNumberService(){
        return KraServiceLocator.getService(AwardNumberService.class);
    }

    protected String getAwardNumber(HttpServletRequest request) {
        String awardNumber = "";
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            awardNumber = StringUtils.substringBetween(parameterName, ".awardNumber", ".");
        }

        return awardNumber;
    }

    private int getActiveHierarchyObjectIndex(HttpServletRequest request) throws Exception {
        Enumeration<String> lookupParameters = request.getParameterNames();
        int index = -1;
        while(lookupParameters.hasMoreElements()) {
            String temp = lookupParameters.nextElement();
            if(temp.startsWith("awardHierarchyTempObject[")) {
                index = temp.indexOf("awardHierarchyTempObject[") + 25;
                temp = temp.substring(index, index+1);
                index = Integer.parseInt(temp);
                break;
            }
        }
        
        return index;
    }
    
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
        AwardForm awardForm = (AwardForm)form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        int activeHierarchyObjectIndex = getActiveHierarchyObjectIndex(request);
        int loopIndex = 0;
        Award currentAward = awardDocument.getAward();
        
        for(AwardHierarchyTempObject temp: awardForm.getAwardHierarchyTempObjects()){ 
            List<String> order = new ArrayList<String>();
            
            if(loopIndex == activeHierarchyObjectIndex-1) {
                temp.setAwardNumber2(null);
                temp.setAwardNumber(null);
            }
            
            if(StringUtils.isNotBlank(temp.getAwardNumber1())){
                Map<String,AwardHierarchyNode> awardHierarchyNodes = new HashMap<String, AwardHierarchyNode>();
                Map<String,AwardHierarchy> awardHierarchyItems = awardForm.getAwardHierarchyBean().getAwardHierarchy(temp.getAwardNumber1(), order);
                getAwardHierarchyService().populateAwardHierarchyNodes(awardHierarchyItems, awardHierarchyNodes, currentAward.getAwardNumber(), currentAward.getSequenceNumber().toString());
                StringBuilder sb = new StringBuilder();
                for(String str:order){
                    sb.append(awardHierarchyNodes.get(str).getAwardNumber());
                    sb.append(KNSConstants.BLANK_SPACE).append("%3A");
                }
                temp.setSelectBox1(sb.toString());
                request.setAttribute("selectedAwardNumber", temp.getAwardNumber()); 
            }

            if(StringUtils.isNotBlank(temp.getAwardNumber2())){
                order = new ArrayList<String>();
                Map<String,AwardHierarchyNode> awardHierarchyNodes = new HashMap<String, AwardHierarchyNode>();
                Map<String,AwardHierarchy> awardHierarchyItems = getAwardHierarchyService().getAwardHierarchy(temp.getAwardNumber2(), order);
                getAwardHierarchyService().populateAwardHierarchyNodes(awardHierarchyItems, awardHierarchyNodes, currentAward.getAwardNumber(), currentAward.getSequenceNumber().toString());
                StringBuilder sb = new StringBuilder();
                for(String str:order){
                    AwardHierarchyNode tempAwardNode = awardHierarchyNodes.get(str);
                    if(tempAwardNode.isAwardDocumentFinalStatus()) {
                        sb.append(tempAwardNode.getAwardNumber());
                        sb.append(KNSConstants.BLANK_SPACE).append("%3A");    
                    }
                }
                temp.setSelectBox2(sb.toString());
                request.setAttribute("selectedAwardNumber", temp.getAwardNumber()); 
            }
            loopIndex++;
        }

        return super.refresh(mapping, form, request, response);
    }

    private AwardHierarchy findTargetNode(HttpServletRequest request, AwardForm awardForm) {
        return awardForm.getAwardHierarchyBean().getRootNode().findNodeInHierarchy(getAwardNumber(request));
    }

    private ActionForward prepareToForwardToNewChildAward(ActionMapping mapping, AwardForm awardForm, AwardHierarchy targetNode,
                                                            AwardHierarchy newNodeToView) throws WorkflowException {
        ActionForward forward;
        if(newNodeToView != null) {
            awardForm.setCommand(KEWConstants.INITIATE_COMMAND);
            createDocument(awardForm);
            Award newChildAward = newNodeToView.getAward();
            if(!newNodeToView.isRootNode()) {
                setMultipleNodeHierarchyOnAwardFormTrue(awardForm);  
            }
            awardForm.getAwardDocument().setAward(newChildAward);
            awardForm.getAwardHierarchyBean().recordTargetNodeState(targetNode);
            awardForm.getFundingProposalBean().setAllAwardsForAwardNumber(null);
            forward = mapping.findForward(Constants.MAPPING_AWARD_HOME_PAGE);
        } else {
            forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
        return forward;
    }
    
    /**
     * Since a child award will always be part of a multiple award hierarchy, we need to set the boolean to true so that the anticipated
     * and obligated totals on Details & Dates tab will be uneditable on initial creation.  After the initial save of document
     * this is handled in the docHandler and home methods of AwardAction.
     * @param awardForm
     */
    private void setMultipleNodeHierarchyOnAwardFormTrue(AwardForm awardForm) {
         awardForm.setAwardInMultipleNodeHierarchy(true);
    }

    private String getHierarchyTargetAwardNumber(HttpServletRequest request) {
        return request.getParameter("awardNumberInputTemp");
    }

    @Override
    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        /*
         * We need to ensure the user didn't create a pending version of the linked proposal,
         * which, when processed, will overwrite any de-linking caused by the canceling of this Award version.
         */
        Set<String> linkedPendingProposals = getLinkedPendingProposals(((AwardForm) form).getAwardDocument().getAward());
        if (!linkedPendingProposals.isEmpty()) {
            String proposalNumbers = StringUtils.join(linkedPendingProposals, ", ");
            GlobalVariables.getMessageMap().putError("noKey", 
                    ERROR_CANCEL_PENDING_PROPOSALS, 
                    proposalNumbers);
            return mapping.findForward(RiceConstants.MAPPING_BASIC);
        }
        
        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        // this should probably be moved into a private instance variable
        // logic for cancel question
        if (question == null) {
            // ask question if not already asked
            return this.performQuestionWithoutInput(mapping, form, request, response, KNSConstants.DOCUMENT_CANCEL_QUESTION, getKualiConfigurationService().getPropertyString("document.question.cancel.text"), KNSConstants.CONFIRMATION_QUESTION, KNSConstants.MAPPING_CANCEL, "");
        }
        else {
            Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
            if ((KNSConstants.DOCUMENT_CANCEL_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked)) {
                // if no button clicked just reload the doc
                return mapping.findForward(RiceConstants.MAPPING_BASIC);
            }
            // else go to cancel logic below
        }

        KualiDocumentFormBase kualiDocumentFormBase = (KualiDocumentFormBase) form;
        doProcessingAfterPost( kualiDocumentFormBase, request );
        getDocumentService().cancelDocument(kualiDocumentFormBase.getDocument(), kualiDocumentFormBase.getAnnotation());
        
        //add all award amount info objects to previous award version and save.
//        AwardForm awardForm = (AwardForm) form;
//        AwardDocument awardDocument = (AwardDocument) awardForm.getDocument();
//        Award award  = awardDocument.getAward();
//        Award activeAward = getActiveAwardVersion(award.getAwardNumber());
//        activeAward.setAwardAmountInfos(award.getAwardAmountInfos());
//        //reinitialize the collection so the cancelled doc can be viewed.
//        award.initializeAwardAmountInfoObjects();
//        
//        getBusinessObjectService().save(award);
//        getBusinessObjectService().save(activeAward);

        return returnToSender(request, mapping, kualiDocumentFormBase);
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
     * Find pending proposal versions linked to this award version.
     */
    private Set<String> getLinkedPendingProposals(Award award) {
        Set<String> linkedPendingProposals = new HashSet<String>();
        for (AwardFundingProposal awardFundingProposal : award.getFundingProposals()) {
            String proposalNumber = awardFundingProposal.getProposal().getProposalNumber();
            InstitutionalProposal pendingVersion = getInstitutionalProposalService().getPendingInstitutionalProposalVersion(proposalNumber);
            if (pendingVersion != null && pendingVersion.isFundedByAward(award.getAwardNumber(), award.getSequenceNumber())) {
                linkedPendingProposals.add(proposalNumber);
            }
        }
        return linkedPendingProposals;
    }
    
    protected InstitutionalProposalService getInstitutionalProposalService() {
        return KraServiceLocator.getService(InstitutionalProposalService.class);
    }
    
}
