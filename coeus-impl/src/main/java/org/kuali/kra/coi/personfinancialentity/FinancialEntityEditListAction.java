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

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.version.VersionException;
import org.kuali.kra.coi.CoiActionType;
import org.kuali.kra.coi.notesandattachments.attachments.FinancialEntityAttachment;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class for the struts action of 'my financial entities' page.
 */
public class FinancialEntityEditListAction extends FinancialEntityAction{
    private static final String DEACTIVATE_ENTITY_QUESTION="DeactivateEntity";
    // TODO : db column is '2000', but coeus shows 1000 limit; so just follow coeus message.
    private static final String DEACTIVATE_ENTITY_REASON_MAXLENGTH = "1000";
    private static final String PROCESS_STATUS_FINAL = "F";

    /**
     * 
     * This method is for editing 'active' financial entity
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editActiveFinancialEntity(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        FinancialEntityHelper financialEntityHelper = ((FinancialEntityForm) form).getFinancialEntityHelper();
        financialEntityHelper.setEditType(ACTIVATE_ENTITY);
        editFinancialEntity(form, request);
       return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method is for editing 'inactive' financial entity
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward editInactiveFinancialEntity(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        FinancialEntityHelper financialEntityHelper = ((FinancialEntityForm) form).getFinancialEntityHelper();
        financialEntityHelper.setEditType(INACTIVATE_ENTITY);
        editFinancialEntity(form, request);
       return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method is the share components of 'edit' both active and inactive FE
     * @param form
     * @param request
     * @throws Exception
     */
    private void editFinancialEntity(ActionForm form, HttpServletRequest request) throws Exception {
        FinancialEntityHelper financialEntityHelper = ((FinancialEntityForm) form).getFinancialEntityHelper();
        int entityIndex = -1;
        if (request.getParameter("coiDocId") != null ) {
            // patch if request from coi disclosure FE
            financialEntityHelper.setActiveFinancialEntities(getFinancialEntities(true));
            entityIndex = findMatchedIndex(financialEntityHelper.getActiveFinancialEntities(), financialEntityHelper.getEditCoiEntityId());
        } else {
            entityIndex = getSelectedLine(request);
        }
        PersonFinIntDisclosure personFinIntDisclosure = getFinancialEntities(form).get(entityIndex);
        financialEntityHelper.setEditEntityIndex(entityIndex);
        financialEntityHelper.setEditRelationDetails(getFinancialEntityService().getFinancialEntityDataMatrixForEdit(personFinIntDisclosure.getPerFinIntDisclDetails()));
        financialEntityHelper.setFinEntityAttachmentList(FinancialEntityAttachment.copyAttachmentList(personFinIntDisclosure.getFinEntityAttachments()));
        financialEntityHelper.resetPrevSponsorCode();
    }

    /*
     * This method is for coi edit.  coi FE list is not the same as ActiveFE, so need to match the FEDisclosureID
     */
    private int findMatchedIndex(List<PersonFinIntDisclosure> financialEntities, String entityId) {
        int i = 0;
        for (PersonFinIntDisclosure financialEntity : financialEntities) {
            if (StringUtils.equals(financialEntity.getPersonFinIntDisclosureId().toString(), entityId)) {
                return i;
            }
            i++;
        }
        return -1;
    }
    public ActionForward editFinancialEntityFromLookup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String id = request.getParameter("personFinIntDisclosureId");
        FinancialEntityHelper financialEntityHelper = ((FinancialEntityForm) form).getFinancialEntityHelper();
        List<PersonFinIntDisclosure> activePersonFinIntDisclosures = getActiveFinancialEntities();
        List<PersonFinIntDisclosure> inactivePersonFinIntDisclosures = getInactiveFinancialEntities();

        financialEntityHelper.setActiveFinancialEntities(activePersonFinIntDisclosures);
        financialEntityHelper.setInactiveFinancialEntities(inactivePersonFinIntDisclosures);
        PersonFinIntDisclosure currentEntity = getFinancialEntity(id);
        financialEntityHelper.setEditEntityIndex(getEntityIndex(currentEntity));
        financialEntityHelper.setEditRelationDetails(getFinancialEntityService().
                getFinancialEntityDataMatrixForEdit(
                    currentEntity.getPerFinIntDisclDetails()));
        if (StringUtils.equals(currentEntity.getStatusCode() + "", FinIntEntityStatus.INACTIVE)) {
            financialEntityHelper.setEditType(INACTIVATE_ENTITY);
        } else {
            financialEntityHelper.setEditType(ACTIVATE_ENTITY);
        }
        return mapping.findForward(Constants.MAPPING_COI_EDIT_LIST); 
    }
  
    protected int getEntityIndex(PersonFinIntDisclosure currentEntity) {
        int entityIndex = 0;
        List<PersonFinIntDisclosure> financialEntities = getActiveFinancialEntities();
        if (StringUtils.equals(currentEntity.getStatusCode() + "", FinIntEntityStatus.INACTIVE)) {
            financialEntities = getInactiveFinancialEntities();
        }
        for (int i = 0; i < financialEntities.size(); i++) {
            PersonFinIntDisclosure fe = financialEntities.get(i);
            if (StringUtils.equals(currentEntity.getPersonFinIntDisclosureId()+"", fe.getPersonFinIntDisclosureId() + "")) {
                entityIndex = i;
            }
        }
        return entityIndex;
    }
    
    protected PersonFinIntDisclosure getFinancialEntity(String entityId) {
        Map<String, String> criteria = new HashMap<String, String>();
        criteria.put("personFinIntDisclosureId", entityId);
        PersonFinIntDisclosure value = (PersonFinIntDisclosure) getBusinessObjectService().findByPrimaryKey(PersonFinIntDisclosure.class, criteria);
        return value;
    }
    
    protected List<PersonFinIntDisclosure> getActiveFinancialEntities() {
        return getFinancialEntityService().getFinancialEntities(GlobalVariables.getUserSession().getPrincipalId(), true);
    }
    
    protected List<PersonFinIntDisclosure> getInactiveFinancialEntities() {
        return getFinancialEntityService().getFinancialEntities(GlobalVariables.getUserSession().getPrincipalId(), false);
    }
    
    /*
     * get active or inactive FE list based on the type selected
     */
    private List<PersonFinIntDisclosure> getFinancialEntities(ActionForm form) {
        
        FinancialEntityHelper financialEntityHelper = ((FinancialEntityForm) form).getFinancialEntityHelper();
        if (StringUtils.equals(ACTIVATE_ENTITY, financialEntityHelper.getEditType())) {
            return ((FinancialEntityForm) form).getFinancialEntityHelper()
                .getActiveFinancialEntities();
        } else {
            return ((FinancialEntityForm) form).getFinancialEntityHelper()
                    .getInactiveFinancialEntities();
        }

    }
    
    public ActionForward showFinancialEntityHistory(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        FinancialEntityHelper financialEntityHelper = ((FinancialEntityForm) form).getFinancialEntityHelper();
        Integer entityIndex = Integer.parseInt(request.getParameter("index"));
        financialEntityHelper.setEditEntityIndex(entityIndex);
        String status = request.getParameter("status");
        PersonFinIntDisclosure currentPersonFinIntDisclosure;
        if (StringUtils.equalsIgnoreCase(status, "activecoi")) {
            // this is a patch to retrieve coi disclosure history 
            currentPersonFinIntDisclosure = getFinancialEntity(entityIndex.toString());
            currentPersonFinIntDisclosure.setVersions(getFinancialEntityService().getFinDisclosureVersions(currentPersonFinIntDisclosure.getEntityNumber()));
        } else {
            if (StringUtils.equalsIgnoreCase(status, Constants.FINANCIAL_ENTITY_STATUS_INACTIVE)) {
                currentPersonFinIntDisclosure = ((FinancialEntityForm) form).getFinancialEntityHelper()
                        .getInactiveFinancialEntities().get(entityIndex);
            } else {
                currentPersonFinIntDisclosure = ((FinancialEntityForm) form).getFinancialEntityHelper()
                        .getActiveFinancialEntities().get(entityIndex);
                List<PersonFinIntDisclosure> versionList = currentPersonFinIntDisclosure.getVersions();
                List<PersonFinIntDisclosure> activeFinancialIntDisclosure = new ArrayList<PersonFinIntDisclosure>();
                
                for (PersonFinIntDisclosure personFinIntDisclosure : versionList) {
                    if (personFinIntDisclosure.getProcessStatus().equals(PROCESS_STATUS_FINAL)) {
                        activeFinancialIntDisclosure.add(personFinIntDisclosure);
                    }
                }
                currentPersonFinIntDisclosure.setVersions(activeFinancialIntDisclosure);
            }
        }
        financialEntityHelper.setVersions(currentPersonFinIntDisclosure);
        return mapping.findForward("history");
    }
    
    /**
     * This method displays the financial entity summary.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward viewFinancialEntity(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        FinancialEntityHelper financialEntityHelper = ((FinancialEntityForm) form).getFinancialEntityHelper();      
        FinancialEntitySummaryHelper summaryHelper = ((FinancialEntityForm) form).getFinancialEntitySummaryHelper();
        Integer entityIndex = Integer.parseInt(request.getParameter("index"));
        financialEntityHelper.setEditEntityIndex(entityIndex);
        String status = request.getParameter("status");
        
        int currentVersionNumber;
        PersonFinIntDisclosure currentFinancialEntity;
        if (StringUtils.equalsIgnoreCase(status, "activecoi")) {
            // this is a patch to retrieve coi disclosure view
            currentFinancialEntity = getFinancialEntity(entityIndex.toString());
            currentFinancialEntity.setVersions(getFinancialEntityService().getFinDisclosureVersions(currentFinancialEntity.getEntityNumber()));
            currentVersionNumber = currentFinancialEntity.getVersions().size();
            status = ACTIVATE_ENTITY;
            // This is coming from coi, user may not be reporter, so user FE's personid to retrieve FEs for reporter
            ((FinancialEntityForm) form).getFinancialEntityHelper().setActiveFinancialEntities(getFinancialEntities(currentFinancialEntity.getPersonId(), true));
            ((FinancialEntityForm) form).getFinancialEntityHelper().setInactiveFinancialEntities(getFinancialEntities(currentFinancialEntity.getPersonId(), false));
        } else if (StringUtils.equalsIgnoreCase(status, Constants.FINANCIAL_ENTITY_STATUS_INACTIVE)) {
            currentFinancialEntity = ((FinancialEntityForm) form).getFinancialEntityHelper().getInactiveFinancialEntities().get(entityIndex);
            currentVersionNumber = currentFinancialEntity.getVersions().size();
        } else {
            currentFinancialEntity = ((FinancialEntityForm) form).getFinancialEntityHelper().getActiveFinancialEntities().get(entityIndex);
            List<PersonFinIntDisclosure> versionList = currentFinancialEntity.getVersions();
            List<PersonFinIntDisclosure> activeFinancialIntDisclosure = new ArrayList<PersonFinIntDisclosure>();
            
            for(PersonFinIntDisclosure personFinIntDisclosure: versionList ){
                if(personFinIntDisclosure.getProcessStatus().equals(PROCESS_STATUS_FINAL)){
                	activeFinancialIntDisclosure.add(personFinIntDisclosure);
                }
            }
            currentFinancialEntity.setVersions(activeFinancialIntDisclosure);
            currentVersionNumber = currentFinancialEntity.getVersions().size();
            
        }
        summaryHelper.setEntityStatus(status);
        summaryHelper.setSummaryDetails(currentVersionNumber, currentFinancialEntity.getEntityNumber(), status);
        return mapping.findForward("viewEntity");
    }
    
    /**
     * This method shows the previous and next versions of the financial entity
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward previousNextVersion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        int currentVersionNumber = Integer.parseInt(request.getParameter("versionNumber"));
        String entityNumber = request.getParameter("entityNumber");
        FinancialEntitySummaryHelper summaryHelper = ((FinancialEntityForm) form).getFinancialEntitySummaryHelper();
        String status = summaryHelper.getEntityStatus();
        summaryHelper.setSummaryDetails(currentVersionNumber, entityNumber, status);
        return mapping.findForward("viewEntity");

    }
    
    /**
     * 
     * This method to inactive the selected financial entity
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward inactivateFinancialEntity(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        int entityIndex = getSelectedLine(request);
        Object question = request.getParameter(KRADConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KRADConstants.QUESTION_CLICKED_BUTTON);
        String reason = request.getParameter(KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME);
        String callerString = String.format("inactivateFinancialEntity.line%s.anchor%s", entityIndex, 0);
        if (question == null) {
            return this.performQuestionWithInput(mapping, form, request, response, DEACTIVATE_ENTITY_QUESTION,
                    "Are you sure you want to deactivate this financial entity ?", KRADConstants.CONFIRMATION_QUESTION,
                    callerString, "");
        }
        else if ((DEACTIVATE_ENTITY_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked)) {
            // nothing to do.
        } else {
            if (StringUtils.isBlank(reason) || reason.length() > 1000) {
                if (reason == null) {
                    reason = ""; // Prevents null pointer exception in performQuestion
                }
                return this.performQuestionWithInputAgainBecauseOfErrors(mapping, form, request, response,
                        DEACTIVATE_ENTITY_QUESTION, "Are you sure you want to deactivate this financial entity ?",
                        KRADConstants.CONFIRMATION_QUESTION, callerString, "", reason,
                        KeyConstants.ERROR_DEACTIVATE_FINANCIAL_ENTITY_REASON_REQUIRED, KRADConstants.QUESTION_REASON_ATTRIBUTE_NAME,
                        DEACTIVATE_ENTITY_REASON_MAXLENGTH);
            } else {

                PersonFinIntDisclosure personFinIntDisclosure = ((FinancialEntityForm) form).getFinancialEntityHelper().getActiveFinancialEntities().get(entityIndex);
                ((FinancialEntityForm) form).getFinancialEntityHelper().setEditRelationDetails(getFinancialEntityService().getFinancialEntityDataMatrixForEdit(personFinIntDisclosure.getPerFinIntDisclDetails()));
                ((FinancialEntityForm) form).getFinancialEntityHelper().setFinEntityAttachmentList(getFinancialEntityService().retrieveFinancialEntityAttachmentsFor(personFinIntDisclosure.getPersonFinIntDisclosureId()));
                versionFinancialEntity(form, personFinIntDisclosure,2, reason);
            }
        }
        ((FinancialEntityForm) form).getFinancialEntityHelper().setEditEntityIndex(-1);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * 
     * This method to active the selected financial entity
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward activateFinancialEntity(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        int entityIndex = getSelectedLine(request);
        FinancialEntityHelper financialEntityHelper = ((FinancialEntityForm) form).getFinancialEntityHelper();
        PersonFinIntDisclosure personFinIntDisclosure = financialEntityHelper.getInactiveFinancialEntities().get(entityIndex);
        financialEntityHelper.setEditRelationDetails(getFinancialEntityService().getFinancialEntityDataMatrixForEdit(personFinIntDisclosure.getPerFinIntDisclDetails()));
        financialEntityHelper.setFinEntityAttachmentList(getFinancialEntityService().retrieveFinancialEntityAttachmentsFor(personFinIntDisclosure.getPersonFinIntDisclosureId()));
        versionFinancialEntity(form, personFinIntDisclosure,1, Constants.EMPTY_STRING);
        financialEntityHelper.setEditEntityIndex(-1);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /*
     * to version person financial entity and save.
     */
    private PersonFinIntDisclosure versionFinancialEntity(ActionForm form, PersonFinIntDisclosure personFinIntDisclosure, Integer statusCode, String statusDesc) throws VersionException {
        FinancialEntityHelper financialEntityHelper = ((FinancialEntityForm) form).getFinancialEntityHelper();
        PersonFinIntDisclosure newVersionDisclosure = getFinancialEntityService().versionPersonFinintDisclosure(personFinIntDisclosure, 
                                                               financialEntityHelper.getEditRelationDetails(), 
                                                               financialEntityHelper.getFinEntityAttachmentList());

        newVersionDisclosure.setStatusCode(statusCode);
        newVersionDisclosure.setStatusDescription(statusDesc);
        // the auto-retrieve is true. If it is not refresh here, then after save, the status code return to '1'
        // same refresh for 'activate'
        newVersionDisclosure.refreshReferenceObject("finIntEntityStatus");
        saveFinancialEntity(form, newVersionDisclosure);
        return newVersionDisclosure;
        
    }
    
    /**
     * 
     * This method to handle the submit action for edited financial entity
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward submit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FinancialEntityForm financialEntityForm = (FinancialEntityForm) form;
        FinancialEntityHelper financialEntityHelper = financialEntityForm.getFinancialEntityHelper();

        int entityIndex = getSelectedLine(request);
        PersonFinIntDisclosure personFinIntDisclosure = getFinancialEntities(form).get(entityIndex);

        if (isValidToSave(personFinIntDisclosure, getErrorPropertyPrefix(form, entityIndex))) {
            if (StringUtils.equals(PROCESS_STATUS_FINAL, personFinIntDisclosure.getProcessStatus())) {
                PersonFinIntDisclosure newFinIntDisclosure = versionFinancialEntity(form, personFinIntDisclosure,
                        StringUtils.equals(ACTIVATE_ENTITY, financialEntityHelper.getEditType()) ? 1 : 2, Constants.EMPTY_STRING);
                resetEditEntityIndex(form, newFinIntDisclosure.getPersonFinIntDisclosureId());
                // send notification to admins that FE has been modified
                sendNotificationAndPersist(CoiActionType.FE_MODIFIED_EVENT, "Financial Entity Modified", personFinIntDisclosure);
            }
            else {
                personFinIntDisclosure.setProcessStatus(PROCESS_STATUS_FINAL);
                resetFinEntityDet(financialEntityHelper, personFinIntDisclosure);
                personFinIntDisclosure.setFinEntityAttachments(financialEntityHelper.getFinEntityAttachmentList());
                saveFinancialEntity(form, personFinIntDisclosure);
                // send notification to admins that FE has been created
                sendNotificationAndPersist(CoiActionType.FE_CREATED_EVENT, "Financial Entity Created", personFinIntDisclosure);
            }
            if (StringUtils.isNotBlank(financialEntityForm.getCoiDocId())) {
                String forward = buildForwardUrl(financialEntityForm.getCoiDocId());
                financialEntityForm.setCoiDocId(null);
                financialEntityForm.getFinancialEntityHelper().setReporterId(null);
                return new ActionForward(forward, true);
            }
            /*
             * initiate again or the edit button disappears and the tab is open.
             */
            financialEntityHelper.initiate();
        }
       
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /*
     * get proper error prefix for active and inactive FE
     */
    private String getErrorPropertyPrefix(ActionForm form, int entityIndex) {
        
        FinancialEntityHelper financialEntityHelper = ((FinancialEntityForm) form).getFinancialEntityHelper();
        if (StringUtils.equals(ACTIVATE_ENTITY, financialEntityHelper.getEditType())) {
            return "financialEntityHelper.activeFinancialEntities[" + entityIndex + "]";
        } else {
            return "financialEntityHelper.inactiveFinancialEntities[" + entityIndex + "]";
        }

    }

    /**
     * 
     * This method is to save the editing financial entity.  process_status is 'S'
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FinancialEntityHelper financialEntityHelper = ((FinancialEntityForm) form).getFinancialEntityHelper();

        int entityIndex = getSelectedLine(request);
        PersonFinIntDisclosure personFinIntDisclosure = getFinancialEntities(form).get(entityIndex);

        if (isValidToSave(personFinIntDisclosure, getErrorPropertyPrefix(form, entityIndex))) {
            if (StringUtils.equals(PROCESS_STATUS_FINAL, personFinIntDisclosure.getProcessStatus())) {
                PersonFinIntDisclosure newVersionDisclosure = getFinancialEntityService().versionPersonFinintDisclosure(
                        personFinIntDisclosure, financialEntityHelper.getEditRelationDetails(), financialEntityHelper.getFinEntityAttachmentList());
                newVersionDisclosure.setProcessStatus("S");
                newVersionDisclosure.setStatusDescription(Constants.EMPTY_STRING);
                saveFinancialEntity(form, newVersionDisclosure);
                resetEditEntityIndex(form, newVersionDisclosure.getPersonFinIntDisclosureId());
            }
            else {
                personFinIntDisclosure.setFinEntityAttachments(financialEntityHelper.getFinEntityAttachmentList());
                resetFinEntityDet(financialEntityHelper, personFinIntDisclosure);
                saveFinancialEntity(form, personFinIntDisclosure);
            }
            // saveFinancialEntity(form, personFinIntDisclosure);
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /*
     * reload FE details from ui fields.  also delete the old details; so the new one can be saved properly
     */
    private void resetFinEntityDet(FinancialEntityHelper financialEntityHelper, PersonFinIntDisclosure personFinIntDisclosure) {
        if (CollectionUtils.isNotEmpty(personFinIntDisclosure.getPerFinIntDisclDetails())) {
            getBusinessObjectService().delete(personFinIntDisclosure.getPerFinIntDisclDetails());
        }
        personFinIntDisclosure.setPerFinIntDisclDetails(getFinancialEntityService().getFinDisclosureDetails(
                financialEntityHelper.getEditRelationDetails(), personFinIntDisclosure.getEntityNumber(),
                personFinIntDisclosure.getSequenceNumber()));
        
    }

    /*
     * after versioned, the retrieved list may not be exactly the same as before version.
     * so, this is just to make sure it is fine.
     */
    private void resetEditEntityIndex(ActionForm form, Long personFinIntDisclosureId) {
        FinancialEntityHelper financialEntityHelper = ((FinancialEntityForm) form).getFinancialEntityHelper();
        int i = 0;
        for (PersonFinIntDisclosure personFinIntDisclosure : getFinancialEntities(form)) {
            if (personFinIntDisclosure.getPersonFinIntDisclosureId().equals(personFinIntDisclosureId)) {
                financialEntityHelper.setEditEntityIndex(i);
                break;
            }
            i++;
        }
    }
    
    public ActionForward addNewFinancialEntityAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ((FinancialEntityForm) form).getFinancialEntityHelper().addNewFinancialEntityAttachment();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward deleteFinancialEntityAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        FinancialEntityForm financialEntityForm = (FinancialEntityForm) form;
        int selectedLine = getSelectedLine(request);
        financialEntityForm.getFinancialEntityHelper().removeNewFinancialEntityAttachment(selectedLine);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /*
     * for new FE and user cancels, go back to main page
     */
    @Override
    public ActionForward whereToGoAfterCancel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return mapping.findForward(KRADConstants.MAPPING_PORTAL);
    }

}
