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
package org.kuali.kra.coi.personfinancialentity;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.common.framework.print.util.PrintingUtils;
import org.kuali.coeus.common.framework.print.watermark.WatermarkService;
import org.kuali.coeus.common.api.rolodex.RolodexContract;
import org.kuali.coeus.common.api.rolodex.RolodexService;
import org.kuali.coeus.common.api.sponsor.SponsorContract;
import org.kuali.coeus.common.api.sponsor.SponsorService;
import org.kuali.coeus.common.notification.impl.service.KcNotificationService;
import org.kuali.coeus.sys.framework.controller.DocHandlerService;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.disclosure.CoiDisclosureService;
import org.kuali.kra.coi.notesandattachments.attachments.FinancialEntityAttachment;
import org.kuali.kra.coi.notification.CoiNotification;
import org.kuali.kra.coi.notification.FinancialEntityNotificationContext;
import org.kuali.kra.coi.notification.FinancialEntityNotificationRenderer;
import org.kuali.kra.coi.service.CoiPrintingService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.ken.util.NotificationConstants;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.action.KualiAction;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.krad.util.GlobalVariables;

import javax.mail.internet.HeaderTokenizer;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.replace;
import static org.kuali.rice.krad.util.KRADConstants.*;

/**
 * 
 * This class is the struts action for financial entity maintenance
 */
@SuppressWarnings("deprecation")
public class FinancialEntityAction extends KualiAction {

    protected static final String INACTIVATE_ENTITY = "inactive";
    protected static final String ACTIVATE_ENTITY = "active";
    protected static final String CONFIRM_YES_CANCEL_FE = "confirmCancelFinancialEntity";
    protected static final String CONFIRM_NO_CANCEL_FE = "declineCancelFinancialEntity";

    private KcNotificationService kcNotificationService;
    
    /**
     * 
     * This method is called when user open the financial entity maintenance page
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward management(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ((FinancialEntityForm) form).getFinancialEntityHelper().initiate();
        // if this starts from FE link.  clean any coiDocId resicure
 //       ((FinancialEntityForm) form).setCoiDocId(null);
        return mapping.findForward("management");
    }

    /**
     * 
     * This method is to forward to 'New Financial Entity' page
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editNew(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ((FinancialEntityForm) form).getFinancialEntityHelper().initiate();
        return mapping.findForward("editNew");
    }

    /**
     * 
     * This method is to forward to 'My Financial Entities' page.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        ((FinancialEntityForm) form).getFinancialEntityHelper().initiate();
        return mapping.findForward("editList");
    }

    /*
     * Utility method to save financial entity when 'submit' is clicked.  also, retrieve the new list of active/inactive financial entities
     */
    protected void saveFinancialEntity(ActionForm form, PersonFinIntDisclosure personFinIntDisclosure) {
        getBusinessObjectService().save(personFinIntDisclosure);
        ((FinancialEntityForm) form).getFinancialEntityHelper().setActiveFinancialEntities(getFinancialEntities(true));
        ((FinancialEntityForm) form).getFinancialEntityHelper().setInactiveFinancialEntities(getFinancialEntities(false));
        recordSubmitActionSuccess("Financial Entity save ");
        
    }
    
    /**
     * 
     * This method for 'close' button action.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        final StrutsConfirmation confirm = buildParameterizedConfirmationQuestion(mapping, form, request, response, 
                CONFIRM_YES_CANCEL_FE, KeyConstants.MESSAGE_CANCEL_FE);
        return confirm(confirm, CONFIRM_YES_CANCEL_FE, CONFIRM_NO_CANCEL_FE);
    }


    /*
     * check if financial is valid for save
     */
    protected boolean isValidToSave(PersonFinIntDisclosure personFinIntDisclosure, String errorPath) {

        // TODO : may need to add save event rule
        GlobalVariables.getMessageMap().addToErrorPath(errorPath);
        //getDictionaryValidationService().validateBusinessObjectsRecursively(personFinIntDisclosure, 2);
        getDictionaryValidationService().validateBusinessObject(personFinIntDisclosure);
        GlobalVariables.getMessageMap().removeFromErrorPath(errorPath);
        GlobalVariables.getMessageMap().addToErrorPath(errorPath + ".finEntityContactInfos[0]");
        //getDictionaryValidationService().validateBusinessObjectsRecursively(personFinIntDisclosure, 2);
        getDictionaryValidationService().validateBusinessObject(personFinIntDisclosure.getFinEntityContactInfos().get(0));
        GlobalVariables.getMessageMap().removeFromErrorPath(errorPath + ".finEntityContactInfos[0]");

        boolean isValid = GlobalVariables.getMessageMap().hasNoErrors();
        isValid &= checkRule(new SaveFinancialEntityEvent(errorPath,personFinIntDisclosure));
        return isValid;

    }

    /**
     * 
     * This method is to process rule check.
     * @param event
     * @return
     */
    protected boolean checkRule(KcDocumentEventBaseExtension event) {
        return event.getRule().processRules(event);
    }

    private DictionaryValidationService getDictionaryValidationService() {
        return KNSServiceLocator.getKNSDictionaryValidationService();
    }

    protected BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }

    protected FinancialEntityService getFinancialEntityService() {
        return KcServiceLocator.getService(FinancialEntityService.class);
    }
    protected CoiDisclosureService getCoiDisclosureService() {
        return KcServiceLocator.getService(CoiDisclosureService.class);
    }

    protected SequenceAccessorService getSequenceAccessorService() {
        return KcServiceLocator.getService(SequenceAccessorService.class);
    }

    /*
     * utility method to get active/inactive financial entities.
     */
    protected List<PersonFinIntDisclosure> getFinancialEntities(boolean active) {
        return getFinancialEntities(GlobalVariables.getUserSession().getPrincipalId(), active);
    }
    
    protected List<PersonFinIntDisclosure> getFinancialEntities(String personId, boolean active) {
        return getFinancialEntityService().getFinancialEntities(personId, active);
    }
    
    /**
     * 
     * This method is for header message displaying
     * @param submitAction
     */
    protected void recordSubmitActionSuccess(String submitAction) {
        KNSGlobalVariables.getMessageList().add(KeyConstants.MESSAGE_FINANCIAL_ENTITY_ACTION_COMPLETE, submitAction);
    }

    /**
     * This is specifically for 'sponsor' lookup'  when return a value, the addresses fields will be overriden.
     * @see org.kuali.rice.kns.web.struts.action.KualiAction#refresh(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // TODO following is to handle populate entity address info after sponsor code change
        // need further refactoring
        ActionForward forward = super.refresh(mapping, form, request, response);
        FinancialEntityHelper financialEntityHelper = ((FinancialEntityForm) form).getFinancialEntityHelper();
        String refreshCaller = request.getParameter("refreshCaller"); // sponsorLookupable
        String sponsorCode = request.getParameter("financialEntityHelper.newPersonFinancialEntity.sponsorCode"); // sponsorLookupable
        boolean isEdit = false;
        if (StringUtils.isNotBlank(refreshCaller) && StringUtils.isBlank(sponsorCode) && financialEntityHelper.getEditEntityIndex() >= 0) {
            if (StringUtils.equals(ACTIVATE_ENTITY, financialEntityHelper.getEditType())) {
                sponsorCode = request.getParameter("financialEntityHelper.activeFinancialEntities["+financialEntityHelper.getEditEntityIndex()+"].sponsorCode");
            } else {
                sponsorCode = request.getParameter("financialEntityHelper.inactiveFinancialEntities["+financialEntityHelper.getEditEntityIndex()+"].sponsorCode");
            }
            isEdit = true;
        }
        if (StringUtils.isNotBlank(refreshCaller) && StringUtils.isNotBlank(sponsorCode)) {
            SponsorContract sponsor = KcServiceLocator.getService(SponsorService.class).getSponsor(sponsorCode);
            if (sponsor != null) {
                RolodexContract rolodex = KcServiceLocator.getService(RolodexService.class).getRolodex(sponsor.getRolodexId());

                FinancialEntityContactInfo contactInfo = financialEntityHelper.getNewPersonFinancialEntity().getFinEntityContactInfos().get(0);
                if (isEdit) {
                    if (StringUtils.equals(ACTIVATE_ENTITY, financialEntityHelper.getEditType())) {
                        financialEntityHelper.getActiveFinancialEntities().get(financialEntityHelper.getEditEntityIndex()).setEntityName(sponsor.getSponsorName());
                        contactInfo = financialEntityHelper.getActiveFinancialEntities().get(financialEntityHelper.getEditEntityIndex()).getFinEntityContactInfos().get(0);
                    } else {
                        financialEntityHelper.getInactiveFinancialEntities().get(financialEntityHelper.getEditEntityIndex()).setEntityName(sponsor.getSponsorName());
                        contactInfo = financialEntityHelper.getInactiveFinancialEntities().get(financialEntityHelper.getEditEntityIndex()).getFinEntityContactInfos().get(0);
                    }
                } else {
                    financialEntityHelper.getNewPersonFinancialEntity().setEntityName(sponsor.getSponsorName());
                }
                contactInfo.setAddressLine1(rolodex.getAddressLine1());
                contactInfo.setAddressLine2(rolodex.getAddressLine2());
                contactInfo.setAddressLine3(rolodex.getAddressLine3());
                contactInfo.setCity(rolodex.getCity());
                contactInfo.setState(rolodex.getState());
                contactInfo.setCountryCode(rolodex.getCountryCode());
                contactInfo.setPostalCode(rolodex.getPostalCode());
            }
        }
        return forward;
    }

    protected String buildForwardUrl(String routeHeaderId) {
        // TODO : this is a copy from KraTransactionalDocumentActionBase.
        // investigate if it can be shared
        DocHandlerService researchDocumentService = KcServiceLocator.getService(DocHandlerService.class);
        String forward = researchDocumentService.getDocHandlerUrl(routeHeaderId);
        if (forward.indexOf("?") == -1) {
            forward += "?";
        }
        else {
            forward += "&";
        }
        forward += KewApiConstants.DOCUMENT_ID_PARAMETER + "=" + routeHeaderId;
        forward += "&" + KewApiConstants.COMMAND_PARAMETER + "=" + NotificationConstants.NOTIFICATION_DETAIL_VIEWS.DOC_SEARCH_VIEW;
        if (GlobalVariables.getUserSession().isBackdoorInUse()) {
            forward += "&" + KewApiConstants.BACKDOOR_ID_PARAMETER + "=" + GlobalVariables.getUserSession().getPrincipalName();
        }
        return forward;
    }

    public ActionForward viewFinancialEntityAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        FinancialEntityForm financialEntityForm = (FinancialEntityForm) form;
        final int selection = this.getSelectedLine(request);
        FinancialEntityAttachment attachment = financialEntityForm.getFinancialEntityHelper().getFinEntityAttachmentList().get(selection);
        if (attachment == null) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        } else {
            final AttachmentFile file = attachment.getAttachmentFile();
            PrintingUtils.streamToResponse(file.getData(), getValidHeaderString(file.getName()),  getValidHeaderString(file.getType()), response);
            return null;  // response already handled
        }
    }

    public ActionForward viewFinancialEntityAttachmentFromSummary(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        final String selection = request.getParameter("linkId");
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("id", selection);
        List<AttachmentFile> files = (List<AttachmentFile>)getBusinessObjectService().findMatching(AttachmentFile.class, fieldValues);
        if (files.size() == 0) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        } else {
            AttachmentFile attachmentFile = files.get(0);
            PrintingUtils.streamToResponse(attachmentFile.getData(), getValidHeaderString(attachmentFile.getName()),  getValidHeaderString(attachmentFile.getType()), response);
            return null;  // response already handled
        }
    }

    protected CoiPrintingService getCoiPrintingService() {
        return  KcServiceLocator.getService(CoiPrintingService.class);
    }
    
    protected WatermarkService getWatermarkService() {
        return  KcServiceLocator.getService(WatermarkService.class);
    }

    protected static String getValidHeaderString(String s) {
        return MimeUtility.quote(s, HeaderTokenizer.MIME);
    }    


    /**
     * "borrowed" from KraTransactionalDocumentActionBase class
     */
    protected StrutsConfirmation buildParameterizedConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response, String questionId, String configurationId, String... params)
            throws Exception {
        StrutsConfirmation retval = new StrutsConfirmation();
        retval.setMapping(mapping);
        retval.setForm(form);
        retval.setRequest(request);
        retval.setResponse(response);
        retval.setQuestionId(questionId);
        retval.setQuestionType(CONFIRMATION_QUESTION);

        ConfigurationService kualiConfiguration = CoreApiServiceLocator.getKualiConfigurationService();
        String questionText = kualiConfiguration.getPropertyValueAsString(configurationId);

        for (int i = 0; i < params.length; i++) {
            questionText = replace(questionText, "{" + i + "}", params[i]);
        }
        retval.setQuestionText(questionText);

        return retval;
    }

    /**
     * "borrowed" from KraTransactionalDocumentActionBase class
     */
    public ActionForward confirm(StrutsConfirmation question, String yesMethodName, String noMethodName) throws Exception {
        // Figure out what the caller is. We want the direct caller of confirm()
        question.setCaller(((KualiForm) question.getForm()).getMethodToCall());

        if (question.hasQuestionInstAttributeName()) {
            Object buttonClicked = question.getRequest().getParameter(QUESTION_CLICKED_BUTTON);
            if (ConfirmationQuestion.YES.equals(buttonClicked) && isNotBlank(yesMethodName)) {
                return dispatchMethod(question.getMapping(), question.getForm(), question.getRequest(), question.getResponse(),
                        yesMethodName);
            }
            else if (isNotBlank(noMethodName)) {
                return dispatchMethod(question.getMapping(), question.getForm(), question.getRequest(), question.getResponse(),
                        noMethodName);
            }
        }
        else {
            return this.performQuestionWithoutInput(question, EMPTY_STRING);
        }

        return question.getMapping().findForward(Constants.MAPPING_BASIC);
    }

    /**
     * "borrowed" from KraTransactionalDocumentActionBase class
     */
    protected ActionForward performQuestionWithoutInput(StrutsConfirmation question, String context) throws Exception {
        return this.performQuestionWithoutInput(question.getMapping(), question.getForm(), question.getRequest(), question
                .getResponse(), question.getQuestionId(), question.getQuestionText(), question.getQuestionType(), question
                .getCaller(), context);
    }

    /*
     * if user answers "yes", then return to Disclosure if we came from there
     */
    public ActionForward confirmCancelFinancialEntity(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        FinancialEntityForm financialEntityForm = (FinancialEntityForm) form;
        FinancialEntityHelper financialEntityHelper = financialEntityForm.getFinancialEntityHelper();

        if (StringUtils.isNotBlank(financialEntityForm.getCoiDocId())) {
            String forward = buildForwardUrl(financialEntityForm.getCoiDocId());
            financialEntityForm.setCoiDocId(null);
            financialEntityForm.getFinancialEntityHelper().setReporterId(null);
            return new ActionForward(forward, true);
        }
        return whereToGoAfterCancel(mapping, form, request, response);
    }

    /*
     * if user answers "no", then stay where we are
     */
    public ActionForward declineCancelFinancialEntity(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                                                                                                                throws Exception {
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /*
     * if user cancels, where do we go
     */
    public ActionForward whereToGoAfterCancel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                                HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    protected void sendNotificationAndPersist(String feAction, String actionDescription, PersonFinIntDisclosure disclosure) {
        FinancialEntityNotificationContext context = new FinancialEntityNotificationContext(disclosure, 
            feAction, actionDescription, new FinancialEntityNotificationRenderer(disclosure));
        getKcNotificationService().sendNotificationAndPersist(context, new CoiNotification(), disclosure);
    }
    
    public KcNotificationService getKcNotificationService() {
        if (kcNotificationService == null) {
            kcNotificationService = KcServiceLocator.getService(KcNotificationService.class);
        }
        return kcNotificationService;
    }

    public void setKcNotificationService(KcNotificationService kcNotificationService) {
        this.kcNotificationService = kcNotificationService;
    }

}
