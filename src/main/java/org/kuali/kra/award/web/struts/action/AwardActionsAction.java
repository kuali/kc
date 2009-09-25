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
import org.kuali.kra.award.awardhierarchy.AwardHierarchyBean;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.AwardHierarchyUIService;
import org.kuali.kra.timeandmoney.AwardHierarchyNode;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;

/**
 * 
 * This class represents the Struts Action for Award Actions page(AwardActions.jsp)
 */
public class AwardActionsAction extends AwardAction implements AuditModeAction {    
    
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
    
    public ActionForward copy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        AwardForm awardForm = (AwardForm)form;
        String awardNumber = getAwardNumber(request);
        String reversedAwardNumber = StringUtils.reverse(awardNumber);
        String index = StringUtils.substring(reversedAwardNumber, 0,reversedAwardNumber.indexOf("0"));
        ActionForward forward = null;        
        if(awardForm.getAwardHierarchyTempOjbect().get(Integer.parseInt(index)).getCopyAwardRadio()!=null){
            String radio = awardForm.getAwardHierarchyTempOjbect().get(Integer.parseInt(index)).getCreateNewChildRadio();
            Boolean copyDescendants = awardForm.getAwardHierarchyTempOjbect().get(Integer.parseInt(index)).getCopyDescendants();
            if(StringUtils.equalsIgnoreCase(radio, "a")){
                if(copyDescendants){
                    forward = this.copyAwardAsANewHierarchyWithDescendants(mapping, form, request, response);
                }else{
                    forward = this.copyAwardAsANewHierarchy(mapping, form, request, response);    
                }
            }else if(StringUtils.equalsIgnoreCase(radio, "b")){
                String copyAwardPanelAward = awardForm.getAwardHierarchyTempOjbect().get(Integer.parseInt(index)).getCopyAwardPanelTargetAward();
                if(StringUtils.equalsIgnoreCase(StringUtils.substring(copyAwardPanelAward, 0, 6),StringUtils.substring(awardNumber, 0, 6))){
                    if(copyDescendants){
                        forward = this.copyAwardAsAChildInCurrentHierarchyWithDescendants(mapping, form, request, response);
                    }else{
                        forward = this.copyAwardAsAChildInCurrentHierarchy(mapping, form, request, response);    
                    }
                }else{
                    if(copyDescendants){
                        forward = this.copyAwardAsAChildOfAwardInAnotherHierarchyWithDescendants(mapping, form, request, response);
                    }else{
                        forward = this.copyAwardAsAChildOfAwardInAnotherHierarchy(mapping, form, request, response);    
                    }
                }
                forward = createANewChildAwardBasedOnParent(mapping, form, request, response);
            }
        }
        return forward;
    }
    
    
    public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm)form;
        String awardNumber = getAwardNumber(request);
        String reversedAwardNumber = StringUtils.reverse(awardNumber);
        String index = StringUtils.substring(reversedAwardNumber, 0,reversedAwardNumber.indexOf("0"));
        ActionForward forward = null;
        if(awardForm.getAwardHierarchyTempOjbect().get(Integer.parseInt(index)).getCreateNewChildRadio()!=null){
            String radio = awardForm.getAwardHierarchyTempOjbect().get(Integer.parseInt(index)).getCreateNewChildRadio();
            if(StringUtils.equalsIgnoreCase(radio, "a")){
                forward = createANewChildAward(mapping, form, request, response);
            }else if(StringUtils.equalsIgnoreCase(radio, "b")){
                forward = createANewChildAwardBasedOnParent(mapping, form, request, response);
            }else if(StringUtils.equalsIgnoreCase(radio, "c")){
                forward = createANewChildAwardBasedOnAnotherAwardInHierarchy(mapping, form, request, response);
            }
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


    private AwardHierarchyUIService getAwardHierarchyUIService(){
        return KraServiceLocator.getService(AwardHierarchyUIService.class);
    }

    private ActionForward prepareToForwardToNewChildAward(ActionMapping mapping, AwardForm awardForm, AwardHierarchy targetNode,
                                                            AwardHierarchy newNodeToView) throws WorkflowException {
        ActionForward forward;
        if(newNodeToView != null) {
            awardForm.setCommand(KEWConstants.INITIATE_COMMAND);
            createDocument(awardForm);
            Award newChildAward = newNodeToView.getAward();
            awardForm.getAwardDocument().setAward(newChildAward);
            awardForm.getAwardHierarchyBean().recordTargetNodeState(targetNode);
            forward = mapping.findForward(Constants.MAPPING_AWARD_HOME_PAGE);
        } else {
            forward = mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
        return forward;
    }

    private String getHierarchyTargetAwardNumber(HttpServletRequest request) {
        return request.getParameter("awardNumberInputTemp");
    }
}
