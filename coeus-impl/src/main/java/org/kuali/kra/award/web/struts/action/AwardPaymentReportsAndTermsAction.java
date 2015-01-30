/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.award.web.struts.action;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.sponsor.term.SponsorTerm;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.kra.award.AwardDocumentRule;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncPendingChangeBean;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardSponsorTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTracking;
import org.kuali.kra.award.paymentreports.awardreports.reporting.ReportTrackingBean;
import org.kuali.kra.award.paymentreports.closeout.AwardCloseoutService;
import org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentSchedule;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.service.PersistenceService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;

/**
 * 
 * This class represents the Struts Action for Payments, 
 * Reports & Terms page(AwardPaymentsReportsAndTerms.jsp)
 */
public class AwardPaymentReportsAndTermsAction extends AwardAction {
    private static final Log LOG = LogFactory.getLog( AwardPaymentReportsAndTermsAction.class );
    private static final String ROLODEX = "rolodex";
    private static final String PERIOD = ".";    
    private static final String AWARD_REPORT_TERM_PROPERTY = "awardReportTermItems";
    private SponsorTermActionHelper sponsorTermActionHelper;
    
    public AwardPaymentReportsAndTermsAction() {
        sponsorTermActionHelper = new SponsorTermActionHelper();
    }
    
    /**
     * Clear rolodex entry from recipients
     * 
     */
    public ActionForward clearRolodex(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) 
    throws Exception {
        ((AwardForm)form).getAwardReportsBean().getNewAwardReportTermRecipient(getAwardReportTermIndex(request)).setRolodexId(null);
        ((AwardForm)form).getAwardReportsBean().getNewAwardReportTermRecipient(getAwardReportTermIndex(request)).setRolodex(null);
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }

    public ActionForward addPaymentScheduleItem(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        ((AwardForm) form).getPaymentScheduleBean().addPaymentScheduleItem();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward deletePaymentScheduleItem(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) 
                                            throws Exception {
            (((AwardForm) form).getPaymentScheduleBean()).deletePaymentScheduleItem(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward generatePaymentSchedules(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        
        (((AwardForm) form).getPaymentScheduleBean()).generatePaymentSchedules();
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward addApprovedEquipmentItem(ActionMapping mapping, ActionForm form, 
                                                    HttpServletRequest request, HttpServletResponse response) 
                                                    throws Exception {
        ((AwardForm) form).getApprovedEquipmentBean().addApprovedEquipmentItem();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * This method adds a foreign travel trip
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addApprovedForeignTravel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                                                                                                throws Exception {
        ((AwardForm) form).getApprovedForeignTravelBean().addApprovedForeignTravel();
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    
    
    
    
    
    /**
     * This method forces recalculation
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward recalculateSpecialApprovalTotals(ActionMapping mapping, ActionForm form, 
                                                    HttpServletRequest request, HttpServletResponse response) 
                                                    throws Exception {
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
           
    /**
     * This method triggers recalculation of the award payment schedule total
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 
     * @throws Exception
     */
    public ActionForward recalculateCostShareTotal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
       
        return mapping.findForward(Constants.MAPPING_BASIC);
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
     * executed; We need to display Organization name on the screen;
     * 
     * For this first we are adding all the AwardReportTermRecipient objects and the string "Rolodex"
     * to two lists and passing it to PersistenceService which then refreshes the Rolodex object in 
     * all of AwardReportTermRecipient objects in 1 single transation.
     *  
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#refresh(
     * org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, 
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("unchecked")
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {        
        super.refresh(mapping, form, request, response);
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = (AwardDocument) awardForm.getAwardDocument();
        
        awardForm.getApprovedForeignTravelBean().refreshTravelers();
        
        refreshAwardReportTermRecipients(awardForm, awardDocument);
        
        String lookupResultsBOClassName = request.getParameter(KRADConstants.LOOKUP_RESULTS_BO_CLASS_NAME);
        String lookupResultsSequenceNumber = request.getParameter(KRADConstants.LOOKUP_RESULTS_SEQUENCE_NUMBER);
        awardForm.setLookupResultsBOClassName(lookupResultsBOClassName);
        awardForm.setLookupResultsSequenceNumber(lookupResultsSequenceNumber);        
        List<AwardSyncPendingChangeBean> pendingChanges = new ArrayList<AwardSyncPendingChangeBean>();
        try{
            // check to see if we are coming back from a lookup
            if (Constants.MULTIPLE_VALUE.equals(awardForm.getRefreshCaller())) {
                // Multivalue lookup. Note that the multivalue keyword lookup results are returned persisted to avoid using session.
                // Since URLs have a max length of 2000 chars, field conversions can not be done.
                String thisLookupResultsSequenceNumber = awardForm.getLookupResultsSequenceNumber();//implement MultiLookupFormSupport
                if (StringUtils.isNotBlank(thisLookupResultsSequenceNumber)) {
                    Class lookupResultsBOClass = Class.forName(awardForm.getLookupResultsBOClassName());
                    Collection<PersistableBusinessObject> rawValues = KNSServiceLocator.getLookupResultsService()
                        .retrieveSelectedResultBOs(thisLookupResultsSequenceNumber, lookupResultsBOClass, GlobalVariables.getUserSession().getPrincipalId());
                    if (lookupResultsBOClass.isAssignableFrom(SponsorTerm.class)) {
                        for (Iterator iter = rawValues.iterator(); iter.hasNext();) {
                            SponsorTerm sponsorTerm = (SponsorTerm) iter.next();
                            AwardSponsorTerm awardSponsorTerm = 
                                sponsorTermActionHelper.addSponsorTermFromMutiValueLookup(((AwardForm) form).getSponsorTermFormHelper(), sponsorTerm, request);
                            if (awardSponsorTerm != null) {
                                pendingChanges.add(new AwardSyncPendingChangeBean(AwardSyncType.ADD_SYNC, awardSponsorTerm, "awardSponsorTerms")); 
                            }
                        }
                    }
                }
            }   
        }catch(Exception ex){
            LOG.error("exception in refresh", ex);
        }
        if (!pendingChanges.isEmpty()) {
            return this.confirmSyncAction(mapping, form, request, response, pendingChanges,
                    mapping.findForward(Constants.MAPPING_AWARD_BASIC));
        } else {
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);            
        }
    }
    
    /**
     * 
     * @see org.kuali.core.web.struts.action.KualiDocumentActionBase#reload(
     * org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, 
     * javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @SuppressWarnings("all")
    public ActionForward reload(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        AwardForm awardForm = (AwardForm) form;
        
        ActionForward actionForward = super.reload(mapping, form, request, response);
        
        setReportsAndTermsOnAwardForm(awardForm);
        
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
        if(new AwardDocumentRule().processAwardReportTermSaveRules((AwardForm) form)) {
            AwardReportTerm newReport = 
                    ((AwardForm) form).getAwardReportsBean().addAwardReportTermItem(getReportClass(request), getReportClassCodeIndex(request));
            if (newReport != null) {
                ActionForward confirmSynch = this.confirmSyncAction(mapping, form, request, response, AwardSyncType.ADD_SYNC, newReport, AWARD_REPORT_TERM_PROPERTY, null, 
                        mapping.findForward(Constants.MAPPING_AWARD_BASIC));
                ((AwardForm) form).getReportTrackingBeans().add(new ReportTrackingBean());
                return confirmSynch;
            }
            else {
                return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
            }
        }
        else {
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
    }
    
    /**
     * 
     * This method deletes a AwardReportTerm from the list of AwardReportTerm objects.
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
        AwardForm awardForm =(AwardForm) form;
        AwardReportTerm newReport = 
            (awardForm.getAwardReportsBean()).deleteAwardReportTermItem(getLineToDelete(request));
        ActionForward af = this.confirmSyncAction(mapping, form, request, response, AwardSyncType.DELETE_SYNC, newReport, 
                AWARD_REPORT_TERM_PROPERTY, null, mapping.findForward(Constants.MAPPING_AWARD_BASIC));
        List<ReportTracking> reportTrackings = this.getReportTrackingService().getReportTacking(newReport);
        awardForm.getReportTrackingsToDelete().addAll(reportTrackings);
        return af;
    }
    
    public ActionForward deleteReportTrackingRecord(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm =(AwardForm) form;
        int reportTrackingRecordToDelete = getLineToDelete(request);
        int awardReportTermRecord = getSelectedAwardTermRecord(request);
        AwardReportTerm art = awardForm.getAwardDocument().getAward().getAwardReportTermItems().get(awardReportTermRecord);
        ReportTracking rt = art.getReportTrackings().get(reportTrackingRecordToDelete);
        art.getReportTrackings().remove(reportTrackingRecordToDelete);
        awardForm.getReportTrackingsToDelete().add(rt);
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    protected int getSelectedAwardTermRecord(HttpServletRequest request) {
        int selectedLine = -1;
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String lineNumber = StringUtils.substringBetween(parameterName, ".awardReportTermItems", ".");
            selectedLine = Integer.parseInt(lineNumber);
        }

        return selectedLine;
    }
    
    /**
     * 
     * This method adds an AwardCloseout Item.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addAwardCloseout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        ((AwardForm) form).getAwardCloseoutBean().addAwardCloseoutItem();
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * 
     * This method deletes an AwardCloseout Item.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteAwardCloseout(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
                                                throws Exception {
        
        (((AwardForm) form).getAwardCloseoutBean()).deleteAwardCloseoutItem(getLineToDelete(request));
        
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    
       
    public ActionForward deleteApprovedEquipmentItem(ActionMapping mapping, ActionForm form, 
                                        HttpServletRequest request, HttpServletResponse response) 
                                                                        throws Exception {
            (((AwardForm) form).getApprovedEquipmentBean()).deleteApprovedEquipmentItem(getLineToDelete(request));
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward deleteApprovedForeignTravelTrip(ActionMapping mapping, ActionForm form, 
                                                        HttpServletRequest request, HttpServletResponse response) 
                                                        throws Exception {
        (((AwardForm) form).getApprovedForeignTravelBean()).deleteApprovedForeignTravelTrip(getLineToDelete(request));
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
        
        AwardReportTermRecipient newRecipient = 
            ((AwardForm) form).getAwardReportsBean().addAwardReportTermRecipientItem(getAwardReportTermIndex(request));
        if (newRecipient != null) {
            return this.confirmSyncAction(mapping, form, request, response, AwardSyncType.ADD_SYNC, newRecipient, AWARD_REPORT_TERM_PROPERTY, null, 
                    mapping.findForward(Constants.MAPPING_AWARD_BASIC));
        } else {
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
    }
    
    /**
     * 
     * This method deletes a recipient.
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
        
        AwardReportTermRecipient recipient = 
            (((AwardForm) form).getAwardReportsBean()).deleteAwardReportTermRecipientItem(getAwardReportTermIndex(request), getLineToDelete(request));
        return this.confirmSyncAction(mapping, form, request, response, AwardSyncType.DELETE_SYNC, recipient, 
                AWARD_REPORT_TERM_PROPERTY, null, mapping.findForward(Constants.MAPPING_AWARD_BASIC));
    }
    
//    /**
//     * 
//     * This method clears the rolodex (Organization/Name) selection.
//     * 
//     * @param mapping
//     * @param form
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    public ActionForward clearRolodex(ActionMapping mapping, ActionForm form,
//            HttpServletRequest request, HttpServletResponse response) throws Exception {
//        AwardForm awardForm = (AwardForm) form;
//        AwardDocument awardDocument = awardForm.getAwardDocument();
//        
//        if(clearRolodexRequestIsNotForAddLine(getLineToDelete(request))){
//            clearRolodexIdField(awardDocument.getAward().getAwardReportTermItems().get(getAwardReportTermIndex(
//                    request)).getAwardReportTermRecipients().get(getLineToDelete(request)));
//        }else{
//            clearRolodexIdField(awardForm.getAwardReportsBean().getNewAwardReportTermRecipients().get(getAwardReportTermIndex(
//                    request)));
//        }
//        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
//    }
//    
//    /**
//     * 
//     * This is a helper method to determine whether the clearRolodex request is coming from a
//     * add line or already added line.
//     * @param lineToDelete
//     * @return
//     */
//    protected boolean clearRolodexRequestIsNotForAddLine(int lineToDelete){
//        return lineToDelete!=-1;
//    }
//    
//    /**
//     * 
//     * This method sets the rolodexId field to null in AwardReportTermRecipient BO
//     * 
//     * @param awardReportTermRecipient
//     */
//    protected void clearRolodexIdField(AwardReportTermRecipient awardReportTermRecipient){
//        awardReportTermRecipient.setRolodexId(null);
//        awardReportTermRecipient.setRolodex(null);
//    }
    /**
     * 
     * This method reads the reportClass from the request.
     * @param request
     * @return
     */
    protected String getReportClass(HttpServletRequest request) {
        int reportClass = -1;
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
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
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
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
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            String awardReportTermIndexString = StringUtils.substringBetween(parameterName, ".awardReportTerm", PERIOD);
            awardReportTermIndex= Integer.parseInt(awardReportTermIndexString);
        }

        return awardReportTermIndex;
    }
    
    /**
     * 
     * This method adds a new AwardSponsorTerm object to the list of AwardSponosorTerm objects
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
    public ActionForward addAwardSponsorTerm(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardSponsorTerm newSponsorTerm = sponsorTermActionHelper.addSponsorTerm(((AwardForm) form).getSponsorTermFormHelper(), request);
        if (newSponsorTerm != null) {
            return confirmSyncAction(mapping, form, request, response, AwardSyncType.ADD_SYNC, newSponsorTerm, "awardSponsorTerms", null, mapping.findForward(Constants.MAPPING_AWARD_BASIC));       
        } else {
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }        
    }
    
    /**
     * 
     * This method deletes a AwardSponsorTerms from the list of AwardSponsorTerms objects.
     * 
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteAwardSponsorTerm(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        int lineToDelete = getLineToDelete(request);
        AwardSponsorTerm awardSponsorTerm = awardDocument.getAward().getAwardSponsorTerms().get(lineToDelete);
        awardDocument.getAward().getAwardSponsorTerms().remove(lineToDelete);       
        return confirmSyncAction(mapping, form, request, response, AwardSyncType.DELETE_SYNC, awardSponsorTerm, "awardSponsorTerms", null, mapping.findForward(Constants.MAPPING_AWARD_BASIC));
    }
    
    /**
     * 
     * @see org.kuali.kra.award.web.struts.action.AwardAction#save(org.apache.struts.action.ActionMapping,
     *               org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        AwardDocument awardDocument = awardForm.getAwardDocument();
        
        if (awardForm.getAwardDocument().getAward().getApprovedEquipmentItems() == null || awardForm.getAwardDocument().getAward().getApprovedEquipmentItems().isEmpty()) {
            awardForm.getAwardDocument().getAward().setApprovedEquipmentIndicator(Constants.NO_FLAG);
        } else {
            awardForm.getAwardDocument().getAward().setApprovedEquipmentIndicator(Constants.YES_FLAG);
        }
        if (awardForm.getAwardDocument().getAward().getApprovedForeignTravelTrips() == null || awardForm.getAwardDocument().getAward().getApprovedForeignTravelTrips().isEmpty()) {
            awardForm.getAwardDocument().getAward().setApprovedForeignTripIndicator(Constants.NO_FLAG);
        } else {
            awardForm.getAwardDocument().getAward().setApprovedForeignTripIndicator(Constants.YES_FLAG);
        }
        if (awardForm.getAwardDocument().getAward().getPaymentScheduleItems() == null || awardForm.getAwardDocument().getAward().getPaymentScheduleItems().isEmpty()) {
            awardForm.getAwardDocument().getAward().setPaymentScheduleIndicator(Constants.NO_FLAG);
        } else {
            awardForm.getAwardDocument().getAward().setPaymentScheduleIndicator(Constants.YES_FLAG);
        }
        
        getAwardCloseoutService().updateCloseoutDueDatesBeforeSave(awardDocument.getAward());
        if (new AwardDocumentRule().processAwardReportTermBusinessRules(awardDocument) && new AwardDocumentRule().processAwardReportTermSaveRules(awardForm)) {
            
            /**
             * process AwardPaymentSchedule, if they have been updated, update the last update user, and last update date fields.
             */
            String lastUpdateUser = GlobalVariables.getUserSession().getPerson().getName();
            Timestamp lastUpdateDate = new Timestamp(new java.util.Date().getTime());
            
            for (AwardPaymentSchedule item : awardDocument.getAward().getPaymentScheduleItems()) {
                Map primaryKey = new HashMap();
                primaryKey.put("AWARD_PAYMENT_SCHEDULE_ID", item.getAwardPaymentScheduleId());
                AwardPaymentSchedule dbItem = (AwardPaymentSchedule) this.getBusinessObjectService().findByPrimaryKey(AwardPaymentSchedule.class, primaryKey);
                if (dbItem == null || item.checkForUpdates(dbItem)) {
                    item.setLastUpdateTimestamp(lastUpdateDate);
                    item.setLastUpdateUser(lastUpdateUser);
                }
            }
            
            ActionForward forward = super.save(mapping, form, request, response);
            if (!awardForm.getReportTrackingsToDelete().isEmpty()) {
                this.getBusinessObjectService().delete(awardForm.getReportTrackingsToDelete());
                awardForm.setReportTrackingsToDelete(new ArrayList<ReportTracking>());
            }
            return forward;
        } else {
            return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
        }
    }
    
    protected AwardCloseoutService getAwardCloseoutService(){
        return KcServiceLocator.getService(AwardCloseoutService.class);
    }
    
    /**
     * 
     * This is a wrapper method for the retrieval of PersistenceService.
     * 
     * @return
     */
    protected PersistenceService getPersistenceService(){
        return KcServiceLocator.getService(PersistenceService.class);
    }
    
    /**
     * 
     * This method adds all the AwardReportTermRecipient objects in the Award and does a retrieves the related Rolodex object for all of them in single 
     * transaction.
     * 
     * @param awardForm
     * @param awardDocument
     */
    private void refreshAwardReportTermRecipients(AwardForm awardForm, AwardDocument awardDocument) {
        List<AwardReportTermRecipient> persistableObjects = new ArrayList<AwardReportTermRecipient>();
        List<String> referenceObjectNames = new ArrayList<String>();
        
        for(AwardReportTermRecipient awardReportTermRecipient : awardForm.getAwardReportsBean().getNewAwardReportTermRecipients()){
            persistableObjects.add(awardReportTermRecipient);
            referenceObjectNames.add(ROLODEX);            
        }
        
        for(AwardReportTerm awardReportTerm : awardDocument.getAward().getAwardReportTermItems()){
            for(AwardReportTermRecipient awardReportTermRecipient : awardReportTerm.getAwardReportTermRecipients()){
                persistableObjects.add(awardReportTermRecipient);
                referenceObjectNames.add(ROLODEX);
            }
        }
        
        if(persistableObjects.size()>0 && referenceObjectNames.size()>0 ){
            getPersistenceService().retrieveReferenceObjects(persistableObjects, referenceObjectNames);
        }
    }
    
    /**
     * When the user syncs an existing sponsor term.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward syncSponsorTerm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        AwardForm awardForm = (AwardForm) form;
        Award award = awardForm.getAwardDocument().getAward();
        AwardSponsorTerm awardSponsorTerm = award.getAwardSponsorTerms().get(getSelectedLine(request));
        awardForm.getAwardSyncBean().addConfirmedPendingChange(AwardSyncType.ADD_SYNC, awardSponsorTerm, "awardSponsorTerms");
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * When the user syncs an existing report.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward syncAwardReportTerm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        AwardForm awardForm = (AwardForm) form;
        Award award = awardForm.getAwardDocument().getAward();
        AwardReportTerm awardReportTerm = award.getAwardReportTermItems().get(getSelectedLine(request));
        awardReportTerm.refresh();
        awardForm.getAwardSyncBean().addConfirmedPendingChange(AwardSyncType.ADD_SYNC, awardReportTerm, AWARD_REPORT_TERM_PROPERTY);
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    /**
     * When a user syncs an existing report recipient.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward syncRecipient(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        AwardForm awardForm = (AwardForm)form;
        Award award = awardForm.getAwardDocument().getAward();
        AwardReportTermRecipient recipient = 
            award.getAwardReportTermItems().get(getAwardReportTermIndex(request)).getAwardReportTermRecipients().get(getSelectedLine(request));
        awardForm.getAwardSyncBean().addConfirmedPendingChange(AwardSyncType.ADD_SYNC, recipient, AWARD_REPORT_TERM_PROPERTY);
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward regenerateReports(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        Award award = awardForm.getAwardDocument().getAward();
        this.getReportTrackingService().generateReportTrackingAndSave(award, true);
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward selectAllMultEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        int awardReportTermItemsIndex = getAwardReportTermItemsIndex(request);
        List<ReportTracking> reportTrackings = awardForm.getAwardDocument().getAward().getAwardReportTermItems().get(
                getAwardReportTermItemsIndex(request)).getReportTrackings();
        if (reportTrackings != null && !reportTrackings.isEmpty()) {
            getReportTrackingService().setReportTrackingListSelected(reportTrackings, true);
        } else {
            String fieldName = "methodToCall.selectAllMultEdit.AwardReportTermItemsIndex" + awardReportTermItemsIndex;
            KcServiceLocator.getService(ErrorReporter.class).reportError(fieldName, KeyConstants.ERROR_AWARD_REPORT_TERM_ITEM_NO_REPORT_TRACKING, "select");
        }
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward selectNoneMultiEdit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        int awardReportTermItemsIndex = getAwardReportTermItemsIndex(request);
        List<ReportTracking> reportTrackings = awardForm.getAwardDocument().getAward().getAwardReportTermItems().get(
                getAwardReportTermItemsIndex(request)).getReportTrackings();
        if (reportTrackings != null && !reportTrackings.isEmpty()) {
            getReportTrackingService().setReportTrackingListSelected(reportTrackings, false);
        } else {
            String fieldName = "methodToCall.selectNoneMultiEdit.AwardReportTermItemsIndex" + awardReportTermItemsIndex;
            KcServiceLocator.getService(ErrorReporter.class).reportError(fieldName, KeyConstants.ERROR_AWARD_REPORT_TERM_ITEM_NO_REPORT_TRACKING, "unselect");
        }
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    public ActionForward updateMultileReportTracking(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(new AwardDocumentRule().processAwardReportTermSaveRules((AwardForm) form)) {
        AwardForm awardForm = (AwardForm) form;
        int awardReportTermItemsIndex = getAwardReportTermItemsIndex(request);
        List<ReportTracking> reportTrackings = awardForm.getAwardDocument().getAward().getAwardReportTermItems().get(getAwardReportTermItemsIndex(request)).getReportTrackings();
        if (reportTrackings != null) {
        getReportTrackingService().updateMultipleReportTrackingRecords(reportTrackings, awardForm.getReportTrackingBeans().get(awardReportTermItemsIndex));
        getReportTrackingService().setReportTrackingListSelected(reportTrackings, false);
        }
        awardForm.buildReportTrackingBeans();
        }
        return mapping.findForward(Constants.MAPPING_AWARD_BASIC);
    }
    
    private int getAwardReportTermItemsIndex(HttpServletRequest request) {
        final String awardReportTermItemsIndexBase = "AwardReportTermItemsIndex";
        Map paramMap = request.getParameterMap();
        Iterator keys = paramMap.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next().toString();
            if (StringUtils.contains(key, awardReportTermItemsIndexBase)) {
                int startingSubStringIndex = StringUtils.indexOf(key, awardReportTermItemsIndexBase) + awardReportTermItemsIndexBase.length();
                int endingSubStringIndex = key.length() - 2;
                String intValSubstring = key.substring(startingSubStringIndex, endingSubStringIndex);
                return Integer.valueOf(intValSubstring);
            }
        }
        throw new IllegalArgumentException(awardReportTermItemsIndexBase + " was not found in the request, can't find the index.");
    }
    
}
