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
package org.kuali.kra.subaward.web.struts.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.subaward.SubAwardForm;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardCloseout;
import org.kuali.kra.subaward.bo.SubAwardContact;
import org.kuali.kra.subaward.bo.SubAwardForms;
import org.kuali.kra.subaward.bo.SubAwardFundingSource;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.kra.subaward.subawardrule.SubAwardDocumentRule;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class is using as SubAwardHomeAction ...
 */
public class SubAwardHomeAction extends SubAwardAction{

    private static final String SUBAWARD_VERSION_EDITPENDING_PROMPT_KEY = "message.subaward.version.editpending.prompt";
    private static final String PENDING = "PENDING";

    @Override
    public ActionForward execute(ActionMapping mapping,
    ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward =
        super.execute(mapping, form, request, response);
        SubAwardForm subAwardForm = (SubAwardForm) form;
        List<SubAwardForms> subAwardList = new ArrayList<SubAwardForms>();
        Collection<SubAwardForms> subAwardForms = (Collection<SubAwardForms>) KcServiceLocator.getService(BusinessObjectService.class).findAll(SubAwardForms.class);
        for(SubAwardForms subAwardFormValues : subAwardForms){
        if(subAwardFormValues.getTemplateTypeCode().equals(2)){
            subAwardList.add(subAwardFormValues);
        }
        }
        subAwardForm.getSubAward().setSubAwardForms(subAwardList);
        return actionForward;
    }

    @Override
    public ActionForward save(ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;

        ActionForward forward = super.save(mapping, form, request, response);
       return forward;
    }
    @SuppressWarnings("unchecked")
    @Override
    public ActionForward refresh(ActionMapping mapping, ActionForm form,
    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.refresh(mapping, form, request, response);
        SubAwardForm subAwardMultiLookupForm = (SubAwardForm)form;
        String lookupResultsBOClassName =
        request.getParameter(KRADConstants.LOOKUP_RESULTS_BO_CLASS_NAME);
        String lookupResultsSequenceNumber =
        request.getParameter(KRADConstants.LOOKUP_RESULTS_SEQUENCE_NUMBER);
        subAwardMultiLookupForm.setLookupResultsBOClassName(
        lookupResultsBOClassName);
        subAwardMultiLookupForm.
        setLookupResultsSequenceNumber(lookupResultsSequenceNumber);
        SubAward subAward = subAwardMultiLookupForm.
        getSubAwardDocument().getSubAward();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is used to handle the edit button
     * action on an ACTIVE Subaward.
     *  If no Pending version exists for the same
     * subawardCode, a new Subaward version is created.
     * If a Pending version exists,
     * the user is prompted as to whether she would
     * like to edit the Pending version. Answering Yes results in that Pending
     * version SubAwardDocument to be opened. Answering No
     * simply returns the user to the ACTIVE document screen
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editOrVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = ((SubAwardForm)form);
        SubAwardDocument subAwardDocument = subAwardForm.getSubAwardDocument();
        SubAward subaward = subAwardDocument.getSubAward();
        ActionForward forward;

        if (subaward.getVersionHistory().getStatusForOjb().equals(PENDING)) {
            response.sendRedirect(buildForwardUrl(subAwardForm.getDocId()));
            return null;
        }

        VersionHistory foundPending = findPendingVersion(subaward);
        if (foundPending != null) {
            Object question = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
            if (question == null) {
                forward = showPromptForEditingPendingVersion(mapping, form, request, response);
            } else {
                forward = processPromptForEditingPendingVersionResponse(mapping, request, response, subAwardForm, foundPending);
            }
        } else {
            forward = createAndSaveNewSubAwardVersion(response, subAwardForm, subAwardDocument, subaward);
        }

        return forward;
    }
    
    /**
     * This method find pending subaward versions.
     * @param subaward
     * @return VersionHistory
     */
    private VersionHistory findPendingVersion(SubAward subaward) {
        List<VersionHistory> histories = getVersionHistoryService().loadVersionHistory(SubAward.class, subaward.getSubAwardCode());
        VersionHistory foundPending = null;
        for (VersionHistory history: histories) {
            if (history.getStatus() == VersionStatus.PENDING && subaward.getSequenceNumber() < history.getSequenceOwnerSequenceNumber()) {
                foundPending = history;
                break;
            }
        }
        return foundPending;
    }
    
    /**
     * This method shows prompt for editing pending subaward version.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    private ActionForward showPromptForEditingPendingVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return this.performQuestionWithoutInput(mapping, form, request, response, "EDIT_OR_VERSION_QUESTION_ID",
                    getResources(request).getMessage(SUBAWARD_VERSION_EDITPENDING_PROMPT_KEY),
                    KRADConstants.CONFIRMATION_QUESTION,
                    KRADConstants.MAPPING_CANCEL, "");
    }
    
    /**
     * This method process the edit pending version prompt.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws WorkflowException,IOException
     */
    private ActionForward processPromptForEditingPendingVersionResponse(ActionMapping mapping, HttpServletRequest request,
            HttpServletResponse response, SubAwardForm subAwardForm, 
            VersionHistory foundPending) throws WorkflowException, 
                                                IOException {
        ActionForward forward;
        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
        if (ConfirmationQuestion.NO.equals(buttonClicked)) {
            forward = mapping.findForward(Constants.MAPPING_SUBAWARD_PAGE);            
        } else {
            initializeFormWithSubAward(subAwardForm, (SubAward) foundPending.getSequenceOwner());
            response.sendRedirect(buildForwardUrl(subAwardForm.getSubAwardDocument().getDocumentNumber()));
            forward = null;
        }
        return forward;
    }
    
    private void initializeFormWithSubAward(SubAwardForm subAwardForm, SubAward subAward) throws WorkflowException {
        reinitializeSubAwardForm(subAwardForm, findDocumentForSubAward(subAward));
    }
    
    private SubAwardDocument findDocumentForSubAward(SubAward subAward) throws WorkflowException {
        SubAwardDocument document = (SubAwardDocument) getDocumentService().getByDocumentHeaderId(subAward.getSubAwardDocument().getDocumentNumber());
        document.setSubAward(subAward);
        return document;
    }

    /**.
     * This method is for createAndSaveNewSubAwardVersion
     * @param response the Response
     * @param subAwardForm the SubAwardForm
     * @param subAwardDocument the SubAwrdDocument
     * @param subAward the SubAward
     * @return ActionForward
     * @throws Exception
     */
    private ActionForward createAndSaveNewSubAwardVersion(HttpServletResponse response, SubAwardForm subAwardForm,
       SubAwardDocument subAwardDocument, SubAward subAward) throws Exception {
       subAwardForm.getSubAwardDocument().getSubAward().setNewVersion(true);
       SubAwardDocument newSubAwardDocument = getSubAwardService().createNewSubAwardVersion(subAwardForm.getSubAwardDocument());
       getDocumentService().saveDocument(newSubAwardDocument);
       getSubAwardService().updateSubAwardSequenceStatus(newSubAwardDocument.getSubAward(), VersionStatus.PENDING);
       getVersionHistoryService().updateVersionHistory(newSubAwardDocument.getSubAward(), VersionStatus.PENDING, 
               GlobalVariables.getUserSession().getPrincipalName());
        reinitializeSubAwardForm(subAwardForm, newSubAwardDocument);
        return new ActionForward(buildForwardUrl(newSubAwardDocument.getDocumentNumber()), true);
    }

    /**.
     * This method prepares the SubAwardForm with
     * the document found via the SubAward lookup
     * Because the helper beans may have preserved a different
     *  SubAwardForm, we need to reset these too
     * @param subAwardForm the SubAwardForm
     * @param document the SubAwardDocument
     * @throws WorkflowException
     */
    private void reinitializeSubAwardForm(SubAwardForm subAwardForm,
    SubAwardDocument document) throws WorkflowException {
   subAwardForm.populateHeaderFields(document.getDocumentHeader().
   getWorkflowDocument());
        subAwardForm.setDocument(document);
        subAwardForm.initialize();
    }

    /**.
     * This method is for addingFundingSource
     * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward addFundingSource(
    ActionMapping mapping, ActionForm form, HttpServletRequest request,
    HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAward subAward = subAwardForm.getSubAwardDocument().getSubAward();
        SubAwardFundingSource fundingSources=
        subAwardForm.getNewSubAwardFundingSource();

        if (new SubAwardDocumentRule().
        processAddSubAwardFundingSourceBusinessRules(
        fundingSources, subAward)) {
            addFundingSourceToSubAward(
            subAwardForm.getSubAwardDocument().getSubAward(), fundingSources);
            subAwardForm.setNewSubAwardFundingSource(
            new SubAwardFundingSource());
        }
        return mapping.findForward(Constants.MAPPING_SUBAWARD_PAGE);
    }

    /**.
     * This method is for addingFundingSourceToSubAward
     * @param fundingSources
     * @param subAward the SubAward
     * @param fundingSources the SubAwardFundingSource
     * @return boolean
     */
    boolean addFundingSourceToSubAward(
    SubAward subAward, SubAwardFundingSource fundingSources) {
        if (subAward.getSubAwardCode() == null) {
            String subAwardCode = getSubAwardService().getNextSubAwardCode();
            subAward.setSubAwardCode(subAwardCode);
        }
        fundingSources.setSubAward(subAward);
        return subAward.getSubAwardFundingSourceList().add(fundingSources);
    }

    /**.
     * This method is deleteFundingSource
      * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward deleteFundingSource(ActionMapping mapping,
    ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAwardDocument subAwardDocument = subAwardForm.getSubAwardDocument();
        int selectedLineNumber = getSelectedLine(request);
        SubAwardFundingSource  subAwardFundingSource =
        subAwardDocument.getSubAward().getSubAwardFundingSourceList().
        get(selectedLineNumber);

        subAwardDocument.getSubAward().
        getSubAwardFundingSourceList().remove(selectedLineNumber);
//      this.getBusinessObjectService().delete(subAwardFundingSource); // let save() do this
        return mapping.findForward(Constants.MAPPING_SUBAWARD_PAGE);
    }

    /**
     * This method is for adding contacts
     * This method is for @throws Exception...
     * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward addContacts(ActionMapping mapping,
    ActionForm form, HttpServletRequest request,
        HttpServletResponse response) throws Exception {

        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAwardContact subAwardContact = subAwardForm.getNewSubAwardContact();
        SubAward subAward = subAwardForm.getSubAwardDocument().getSubAward();

        if (new SubAwardDocumentRule().
        processAddSubAwardContactBusinessRules(
        subAwardContact, subAward)) {
            addContactsToSubAward(subAwardForm.
            getSubAwardDocument().getSubAward(), subAwardContact);
            subAwardForm.setNewSubAwardContact(new SubAwardContact());
        }
        return mapping.findForward(Constants.MAPPING_SUBAWARD_PAGE);
    }

    /**.
     * This method is for addingContactsToSubAward
     * @param subAward the SubAward
     * @param subAwardContact the SubAwardContact
     * @return boolean
     */
    boolean addContactsToSubAward(SubAward subAward,
    SubAwardContact subAwardContact) {
        if (subAward.getSubAwardCode() == null) {
            String subAwardCode = getSubAwardService().getNextSubAwardCode();
            subAward.setSubAwardCode(subAwardCode);
        }
        subAwardContact.setSubAward(subAward);
        return subAward.getSubAwardContactsList().add(subAwardContact);
    }

    /**
     * This method is for deleteContact
    * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward deleteContact(ActionMapping mapping,
    ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAwardDocument subAwardDocument = subAwardForm.getSubAwardDocument();
        int selectedLineNumber = getSelectedLine(request);
        SubAwardContact subAwardContact =
        subAwardDocument.getSubAward().
        getSubAwardContactsList().get(selectedLineNumber);
        subAwardDocument.getSubAward().
        getSubAwardContactsList().remove(selectedLineNumber);
//      this.getBusinessObjectService().delete(subAwardContact); // let save() do this
        return mapping.findForward(Constants.MAPPING_SUBAWARD_PAGE);
    }

    /**.
     * This method is for addCloseouts
     * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward addCloseouts(ActionMapping mapping,
    ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAwardCloseout subAwardCloseout =
        subAwardForm.getNewSubAwardCloseout();

        if (new SubAwardDocumentRule().
        processAddSubAwardCloseoutBusinessRules(subAwardCloseout)) {
            if (subAwardCloseout.getDateFollowup() == null) {
                subAwardCloseout.setDateFollowup(
                this.getSubAwardService().getCalculatedFollowupDate(
                subAwardCloseout.getDateRequested()));
            }
            addCloseoutToSubAward(subAwardForm.
            getSubAwardDocument().getSubAward(), subAwardCloseout);
            subAwardForm.setNewSubAwardCloseout(new SubAwardCloseout());
        }
        return mapping.findForward(Constants.MAPPING_SUBAWARD_PAGE);
    }

    /**.
     * This method is for addCloseoutToSubAward
     * @param subAward the SubAward
     * @param subAwardCloseout the SubAwardCloseout
     * @return boolean
     */
    boolean addCloseoutToSubAward(SubAward subAward,
    SubAwardCloseout subAwardCloseout) {
        if (subAward.getSubAwardCode() == null) {
            String subAwardCode = getSubAwardService().getNextSubAwardCode();
            subAward.setSubAwardCode(subAwardCode);
        }
        subAwardCloseout.setSubAward(subAward);
        return subAward.getSubAwardCloseoutList().add(subAwardCloseout);
    }
   /**.
 * This method is for deleteCloseout
    * @param mapping the ActionMapping
     * @param form the ActionForm
     * @param request the Request
     * @param response the Response
     * @return ActionForward
     * @throws Exception
 */
public ActionForward deleteCloseout(ActionMapping mapping,
		ActionForm form, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
        SubAwardForm subAwardForm = (SubAwardForm) form;
        SubAwardDocument subAwardDocument = subAwardForm.getSubAwardDocument();
        int selectedLineNumber = getSelectedLine(request);
        SubAwardCloseout subAwardCloseout =  subAwardDocument.
        getSubAward().getSubAwardCloseoutList().get(selectedLineNumber);
        subAwardDocument.getSubAward().
        getSubAwardCloseoutList().remove(selectedLineNumber);
//        this.getBusinessObjectService().delete(subAwardCloseout); // let save() do this
        return mapping.findForward(Constants.MAPPING_SUBAWARD_PAGE);
    }

public ActionForward selectAllSubAwardPrintNoticeItems(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    SubAwardForm subAwardForm = (SubAwardForm)form;
    subAwardForm.getSubAwardPrintAgreement().selectAllItems();
    return mapping.findForward(Constants.MAPPING_SUBAWARD_PAGE);
}

public ActionForward deselectAllSubAwardPrintNoticeItems(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    SubAwardForm subAwardForm = (SubAwardForm)form;
    subAwardForm.getSubAwardPrintAgreement().deselectAllItems();
    return mapping.findForward(Constants.MAPPING_SUBAWARD_PAGE);
}

}
