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
package org.kuali.kra.award.web.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.AwardNumberService;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyTempOjbect;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;

/**
 * 
 * This class represents the Struts Action for Award Actions page(AwardActions.jsp)
 */
public class AwardActionsAction extends AwardAction implements AuditModeAction {
    
    private static final String ZERO = "0";
    private static final String NEW_CHILD_SELECTED_AWARD_OPTION = "c";
    private static final String NEW_CHILD_COPY_FROM_PARENT_OPTION = "b";
    public static final String NEW_CHILD_NEW_OPTION = "a";
    public static final String AWARD_COPY_NEW_OPTION = "a";
    public static final String AWARD_COPY_CHILD_OF_OPTION = "b";
    
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
        
        if(awardForm.getAwardHierarchyTempOjbect().get(index).getCopyAwardRadio()!=null){
            String radio = awardForm.getAwardHierarchyTempOjbect().get(index).getCopyAwardRadio();
            Boolean copyDescendants = awardForm.getAwardHierarchyTempOjbect().get(index).getCopyDescendants();
            AwardHierarchy targetNode = findTargetNode(request, awardForm);            
            if(StringUtils.equalsIgnoreCase(radio, AWARD_COPY_NEW_OPTION)){
                if(copyDescendants!=null && copyDescendants){
                    AwardHierarchy newRootNode = awardForm.getAwardHierarchyBean().copyAwardAndAllDescendantsAsNewHierarchy(targetNode.getAwardNumber());
                    forward = prepareToForwardToNewChildAward(mapping, awardForm, targetNode, newRootNode);
                }else{
                    AwardHierarchy newRootNode = awardForm.getAwardHierarchyBean().copyAwardAsNewHierarchy(targetNode.getAwardNumber());
                    forward = prepareToForwardToNewChildAward(mapping, awardForm, targetNode, newRootNode);    
                }
            }else if(StringUtils.equalsIgnoreCase(radio, AWARD_COPY_CHILD_OF_OPTION)){
                String awardNumberOfNodeToBeParent = awardForm.getAwardHierarchyTempOjbect().get(index).getCopyAwardPanelTargetAward();
                if(!StringUtils.isEmpty(awardNumberOfNodeToBeParent)) {
                    if(copyDescendants!=null && copyDescendants){    
                        if(!StringUtils.isEmpty(awardNumberOfNodeToBeParent)) {
                            awardForm.getAwardHierarchyBean().copyAwardAndDescendantsAsChildOfAnotherAward(targetNode.getAwardNumber(), awardNumberOfNodeToBeParent);
                        }
                        forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
                    }else{
                        awardForm.getAwardHierarchyBean().copyAwardAsChildOfAnotherAward(targetNode.getAwardNumber(), awardNumberOfNodeToBeParent);
                        populateAwardHierarchy(awardForm);
                        forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
                    }    
                }else{
                    GlobalVariables.getMessageMap().putError("awardHierarchyTempOjbect[" + index + "].copyAwardPanelTargetAward", KeyConstants.ERROR_COPY_AWARD_CHILDOF_AWARD_NOT_SELECTED, awardNumber);
                    forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);    
                }
            }
        }else{
            GlobalVariables.getMessageMap().putError("awardHierarchyTempOjbect[" + index + "].copyAwardPanelTargetAward", KeyConstants.ERROR_COPY_AWARD_NO_OPTION_SELECTED, awardNumber);
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
        
        if(awardForm.getAwardHierarchyTempOjbect().get(index).getCreateNewChildRadio()!=null){
            AwardHierarchy targetNode = findTargetNode(request, awardForm);
            String radio = awardForm.getAwardHierarchyTempOjbect().get(index).getCreateNewChildRadio();
            if(StringUtils.equalsIgnoreCase(radio, NEW_CHILD_NEW_OPTION)){
                AwardHierarchy newChildNode = awardForm.getAwardHierarchyBean().createNewChildAward(targetNode.getAwardNumber());                
                forward = prepareToForwardToNewChildAward(mapping, awardForm, targetNode, newChildNode);
            }else if(StringUtils.equalsIgnoreCase(radio, NEW_CHILD_COPY_FROM_PARENT_OPTION)){
                AwardHierarchy newChildNode = awardForm.getAwardHierarchyBean().createNewAwardBasedOnParent(targetNode.getAwardNumber());
                forward = prepareToForwardToNewChildAward(mapping, awardForm, targetNode, newChildNode);
            }else if(StringUtils.equalsIgnoreCase(radio, NEW_CHILD_SELECTED_AWARD_OPTION)){
                String awardNumberOfNodeToCopyFrom = awardForm.getAwardHierarchyTempOjbect().get(index).getNewChildPanelTargetAward();
                if(StringUtils.isEmpty(awardNumberOfNodeToCopyFrom)) {
                    GlobalVariables.getMessageMap().putError("awardHierarchyTempOjbect[" + index + "].newChildPanelTargetAward", KeyConstants.ERROR_CREATE_NEW_CHILD_OTHER_AWARD_NOT_SELECTED, awardNumber);
                    forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
                }else{
                    AwardHierarchy newChildNode = awardForm.getAwardHierarchyBean().createNewChildAwardBasedOnAnotherAwardInHierarchy(
                            awardNumberOfNodeToCopyFrom, targetNode.getAwardNumber());
                    forward = prepareToForwardToNewChildAward(mapping, awardForm, targetNode, newChildNode);    
                }               
            }
        }else{
            GlobalVariables.getMessageMap().putError("awardHierarchyTempOjbect[" + index + "].newChildPanelTargetAward", KeyConstants.ERROR_CREATE_NEW_CHILD_NO_OPTION_SELECTED, awardNumber);
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

    public ActionForward printNotice(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm)form;
        //TODO: Add printing service call here
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward printChangeReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm)form;
        //TODO: Add printing service call here
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward printHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm)form;
        //TODO: Add printing service call here
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward printHierarchyModification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm)form;
        //TODO: Add printing service call here
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward printBudget(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm)form;
        //TODO: Add printing service call here
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward printTimeMoneyHistory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm)form;
        //TODO: Add printing service call here
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward printTransactionDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm)form;
        //TODO: Add printing service call here
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
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


    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{

        AwardForm awardForm = (AwardForm)form;
        for(AwardHierarchyTempOjbect temp: awardForm.getAwardHierarchyTempOjbect()){
            List<String> order = new ArrayList<String>();
            if(StringUtils.isNotBlank(temp.getAwardNumber1())){
                Map<String,AwardHierarchyNode> awardHierarchyNodes = new HashMap<String, AwardHierarchyNode>();
                Map<String,AwardHierarchy> awardHierarchyItems = awardForm.getAwardHierarchyBean().getAwardHierarchy(temp.getAwardNumber1(), order);
                getAwardHierarchyService().populateAwardHierarchyNodes(awardHierarchyItems, awardHierarchyNodes);
                StringBuilder sb = new StringBuilder();
                for(String str:order){
                    sb.append(awardHierarchyNodes.get(str).getAwardNumber());
                    sb.append(KNSConstants.BLANK_SPACE).append("%3A").append(KNSConstants.BLANK_SPACE).append(awardHierarchyNodes.get(str).getAccountNumber());
                    sb.append(KNSConstants.BLANK_SPACE).append(":").append(KNSConstants.BLANK_SPACE).append(awardHierarchyNodes.get(str).getPrincipalInvestigatorName());
                    sb.append(KNSConstants.BLANK_SPACE).append(":").append(KNSConstants.BLANK_SPACE).append(awardHierarchyNodes.get(str).getLeadUnitName());
                    sb.append("#");
                }
                temp.setSelectBox1(sb.toString());
            }

            if(StringUtils.isNotBlank(temp.getAwardNumber2())){
                Map<String,AwardHierarchyNode> awardHierarchyNodes = new HashMap<String, AwardHierarchyNode>();
                Map<String,AwardHierarchy> awardHierarchyItems = getAwardHierarchyService().getAwardHierarchy(temp.getAwardNumber2(), order);
                getAwardHierarchyService().populateAwardHierarchyNodes(awardHierarchyItems, awardHierarchyNodes);
                StringBuilder sb = new StringBuilder();
                for(String str:order){
                    sb.append(awardHierarchyNodes.get(str).getAwardNumber());
                    sb.append(KNSConstants.BLANK_SPACE).append("%3A").append(KNSConstants.BLANK_SPACE).append(awardHierarchyNodes.get(str).getAccountNumber());
                    sb.append(KNSConstants.BLANK_SPACE).append(":").append(KNSConstants.BLANK_SPACE).append(awardHierarchyNodes.get(str).getPrincipalInvestigatorName());
                    sb.append(KNSConstants.BLANK_SPACE).append(":").append(KNSConstants.BLANK_SPACE).append(awardHierarchyNodes.get(str).getLeadUnitName());
                    sb.append("#");
                }
                temp.setSelectBox2(sb.toString());
            }
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
            setMultipleNodeHierarchyOnAwardFormTrue(awardForm);
            awardForm.getAwardDocument().setAward(newChildAward);
            awardForm.getAwardHierarchyBean().recordTargetNodeState(targetNode);
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
}
