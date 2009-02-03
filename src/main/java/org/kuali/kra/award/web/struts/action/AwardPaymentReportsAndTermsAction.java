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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.award.bo.AwardReportTerm;
import org.kuali.kra.award.bo.AwardReportTermRecipient;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.rule.event.AddAwardReportTermEvent;
import org.kuali.kra.award.rule.event.AddAwardReportTermRecipientEvent;
import org.kuali.kra.award.web.struts.form.AwardForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.AwardReportsService;
import org.kuali.rice.kns.util.KNSConstants;

/**
 * 
 * This class represents the Struts Action for Payments, 
 * Reports & Terms page(AwardPaymentsReportsAndTerms.jsp)
 */
public class AwardPaymentReportsAndTermsAction extends AwardAction {
    private static final String ROLODEX = "rolodex";
    private static final String PERIOD = ".";
    private static final int HARDCODED_ROLODEX_ID = 20083;
    private ApprovedEquipmentActionHelper approvedEquipmentActionHelper;
    
    public AwardPaymentReportsAndTermsAction() {
        init();
    }
    
    public ActionForward addApprovedEquipmentItem(ActionMapping mapping, ActionForm form, 
                                                    HttpServletRequest request, HttpServletResponse response) 
                                                    throws Exception {
        approvedEquipmentActionHelper.addApprovedEquipmentItem(((AwardForm)form).getApprovedEquipmentFormHelper());
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
           
    /**
     * 
     * This method gets called upon clicking of refresh pulldown menu buttons on the screen
     * to populate the drop down menus afresh based on other parameters.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward refreshPulldownOptions(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * Upon the return from look up on <code>Rolodex</code> (Organization) field, this method gets
     * executed; We need to display Organization name on the screen; thus we have to call
     * OJB's refreshReferenceObject method on every awardReportTermRecipient object.
     *  
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#refresh(
     * org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, 
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward refresh(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {        
        super.refresh(mapping, form, request, response);
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = (AwardDocument) awardForm.getAwardDocument();
        for(AwardReportTermRecipient awardReportTermRecipient : awardForm.getNewAwardReportTermRecipient()){
            awardReportTermRecipient.refreshReferenceObject(ROLODEX);
        }
        for(AwardReportTerm awardReportTerm : awardDocument.getAward().getAwardReportTerms()){
            for(AwardReportTermRecipient awardReportTermRecipient : awardReportTerm.getAwardReportTermRecipients()){
                awardReportTermRecipient.refreshReferenceObject(ROLODEX);
            }
        }
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * 
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#reload(
     * org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, 
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward reload(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        AwardForm awardForm = (AwardForm) form;
        
        ActionForward actionForward = super.reload(mapping, form, request, response);
        
        AwardReportsService awardReportsService = KraServiceLocator.getService(AwardReportsService.class);
        awardReportsService.doPreparations(awardForm);
        
        return actionForward;        
    }
    
    /**
     * 
     * This method adds a new AwardReportTerm object to the list of AwardReportTerm objects
     * inside Award.
     * For every added AwardReportTerm object; we are adding an empty AwardReportTerm object to
     * AwardForm.newAwardReportTermRecipients list - for recipients to be added.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addAwardReportTerm(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument= (AwardDocument) awardForm.getAwardDocument();
        
        awardForm.setAwardReportTermPanelNumber(new Integer(getReportClassCodeIndex(request)).toString());
        
        AwardReportTerm newAwardReportTerm = 
            awardForm.getNewAwardReportTerm().get(getReportClassCodeIndex(request));
        
        if(getKualiRuleService().applyRules(new AddAwardReportTermEvent(Constants.EMPTY_STRING,
                awardForm.getAwardDocument(), newAwardReportTerm))){
            newAwardReportTerm.setReportClassCode(getReportClass(request));
            awardDocument.getAward().setAwardReportTerms(addAwardReportTermToAward(
                    awardDocument.getAward(),newAwardReportTerm));            
            awardForm.getNewAwardReportTerm().set(
                    getReportClassCodeIndex(request),new AwardReportTerm());            
            awardForm.getNewAwardReportTermRecipient().add(new AwardReportTermRecipient());
        }
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * 
     * This method adds the newAwardReportTerm to the list of <code>AwardReportTerm</code> objects.
     * @param award
     * @param newAwardReportTerm
     * @param reportClass
     * @return
     */
    protected List<AwardReportTerm> addAwardReportTermToAward(
            Award award, AwardReportTerm newAwardReportTerm){        
        newAwardReportTerm.setAward(award);
        award.getAwardReportTerms().add(newAwardReportTerm);
        return award.getAwardReportTerms();
    }
    
    /**
     * 
     * This method deletes a AwardReportTerm from the list of AwardReportTerm objects.
     * This method also calls another method which fetches a list of recipients that need to be deleted;
     * since all the recipients associated with the AwardReportTerm also need to be deleted and 
     * then all those objects are deleted.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteAwardReportTerm(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        
        awardDocument.getAward().getAwardReportTerms().remove(getLineToDelete(request));
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * 
     * This method gets the newAwardReportTerm object from the form, evaluates the rules and adds it
     * to the list of AwardReportTerm objects.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addRecipient(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        
        awardForm.setAwardReportTermPanelNumber(new Integer(getAwardReportTermIndex(request)).toString());
        
        AwardReportTermRecipient newAwardReportTermRecipient = awardForm.getNewAwardReportTermRecipient().
                                                get(getAwardReportTermIndex(request));
        
        if(newAwardReportTermRecipient.getContactId()!=null){
            populateContactTypeAndRolodex(newAwardReportTermRecipient);
        }else if(newAwardReportTermRecipient.getRolodexId()!=null){
            newAwardReportTermRecipient.setContactTypeCode(Constants.CONTACT_TYPE_OTHER);
        }
        
        if(getKualiRuleService().applyRules(new AddAwardReportTermRecipientEvent(Constants.EMPTY_STRING,
                awardDocument, newAwardReportTermRecipient))){
            awardDocument.getAward().getAwardReportTerms().get(getAwardReportTermIndex(request)).
                    getAwardReportTermRecipients().add(newAwardReportTermRecipient);
            awardForm.getNewAwardReportTermRecipient().set(getAwardReportTermIndex(request), 
                                                            new AwardReportTermRecipient());
        }
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * Currently this method sets the rolodex id to a constant value 
     * and sets the contactTypeCode to 1 of the 6 values listed in the switch case.
     * These values correspond to the hard coded values finder - ContactTypeValuesFinder.
     * 
     * @param newAwardReportTermRecipient
     */
    //TODO: this method should be refactored once the contact functionality is complete
    protected void populateContactTypeAndRolodex(
            AwardReportTermRecipient newAwardReportTermRecipient){
        
        newAwardReportTermRecipient.setRolodexId(HARDCODED_ROLODEX_ID);
        switch(newAwardReportTermRecipient.getContactId().intValue()){
            case 1:
                newAwardReportTermRecipient.setContactTypeCode("6");
                break;
            case 2:
                newAwardReportTermRecipient.setContactTypeCode("5");
                break;
            case 3:
                newAwardReportTermRecipient.setContactTypeCode("4");
                break;
            case 4:
                newAwardReportTermRecipient.setContactTypeCode("3");
                break;
            case 5:
                newAwardReportTermRecipient.setContactTypeCode("2");
                break;
            case 6:
                newAwardReportTermRecipient.setContactTypeCode("9");
                break;
            default:                
                break;
        }
    }
    
    /**
     * 
     * This method deletes a recipient.
     * It reads the recipientsSize from the request; it deletes a AwardReportTerm object from the list
     * if the size is more than 1, otherwise it sets the contactTypeCode, rolodexId and numberOfCopies
     * to null.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteRecipient(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        awardDocument.getAward().getAwardReportTerms().get(getAwardReportTermIndex(request)).
            getAwardReportTermRecipients().remove(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * 
     * This method reads the reportClass from the request.
     * @param request
     * @return
     */
    protected String getReportClass(HttpServletRequest request) {
        int reportClass = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String reportClassString = StringUtils.substringBetween(parameterName, ".reportClass", PERIOD);
            reportClass = Integer.parseInt(reportClassString);
        }

        return new Integer(reportClass).toString();
    }
    
    /**
     * 
     * This method reads the reportClassCodeIndex from the request.
     * It is specified in the tag file and is used for showing the validation errors while adding
     * a new AwardReportTerm object.
     * @param request
     * @return
     */
    protected int getReportClassCodeIndex(HttpServletRequest request) {
        int reportClassIndex = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String reportClassIndexString = StringUtils.substringBetween(
                    parameterName, ".reportClassIndex", PERIOD);
            reportClassIndex = Integer.parseInt(reportClassIndexString);
        }

        return reportClassIndex;
    }
    
    /**
     * 
     * This method reads the recipientIndex from the request.
     * It is specified in the tag file and is used for showing the validation errors while adding
     * a new AwardReportTerm object as a recipient. 
     * @param request
     * @return
     */
    protected int getAwardReportTermIndex(HttpServletRequest request) {
        int awardReportTermIndex = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String awardReportTermIndexString = StringUtils.substringBetween(parameterName, 
                                                                                ".awardReportTerm", PERIOD);
            awardReportTermIndex= Integer.parseInt(awardReportTermIndexString);
        }

        return awardReportTermIndex;
    }
    
    /**
     * 
     * This method reads the recipient size from the request.
     * @param request
     * @return
     */    
    protected int getRecipientSize(HttpServletRequest request) {
        int recipientSize = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String recipientSizeString = StringUtils.substringBetween(parameterName, 
                                                                        ".recipientSize", PERIOD);
            recipientSize = Integer.parseInt(recipientSizeString);
        }        
        return recipientSize;
    }

    protected void init() {
        approvedEquipmentActionHelper = new ApprovedEquipmentActionHelper(this);
    }
}