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
package org.kuali.kra.iacuc.personnel;


import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocolAction;
import org.kuali.kra.iacuc.noteattachment.IacucProtocolAttachmentPersonnel;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentPersonnelBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentService;
import org.kuali.kra.protocol.personnel.AddProtocolUnitEvent;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonRoleBase;
import org.kuali.kra.protocol.personnel.ProtocolUnitBase;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


public class IacucProtocolPersonnelAction extends IacucProtocolAction {


    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    private static final String CONFIRM_YES_DELETE_ATTACHMENT_PERSONNEL = "confirmDeleteAttachmentPersonnel";
    private static final String CONFIRM_NO_DELETE = "";


    private ProtocolAttachmentService protocolAttachmentService;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        getProtocolPersonnelService().selectProtocolUnit(getProtocolPersons(form));
        ((ProtocolFormBase)form).getPersonnelHelper().prepareView();
        ((ProtocolFormBase) form).refreshDisclosureProjectStatuses();
        return actionForward;
    }

    /**
     * This method is linked to ProtocolPersonnelService to perform the action - Add ProtocolBase Person. 
     * Method is called in protocolAddPersonnelSection.tag 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addProtocolPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        IacucProtocolPerson newProtocolPerson = (IacucProtocolPerson)protocolForm.getPersonnelHelper().getNewProtocolPerson();
        ProtocolBase protocol = protocolForm.getProtocolDocument().getProtocol();

        // check any business rules
        boolean rulePassed = applyRules(new AddIacucProtocolPersonnelEvent(Constants.EMPTY_STRING, protocolForm.getProtocolDocument(),
            newProtocolPerson));
        if (rulePassed) {
            getProtocolPersonnelService().addProtocolPerson(protocol, newProtocolPerson);
            //If we are adding a new principal investigator, make sure we update the person id
            if (StringUtils.equals(newProtocolPerson.getProtocolPersonRoleId(), ProtocolPersonRoleBase.ROLE_PRINCIPAL_INVESTIGATOR)) {
                protocolForm.getProtocolHelper().setPersonId(newProtocolPerson.getPersonId());
            }
            protocolForm.getPersonnelHelper().setNewProtocolPerson(new IacucProtocolPerson());
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is linked to ProtocolPersonnelService to perform the action - Delete ProtocolBase Person.
     * Method is called in ProtocolPersonnel.jsp
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProtocolPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        ProtocolDocumentBase protocolDocument = protocolForm.getProtocolDocument();

        boolean rulePassed =  true; 

        if(rulePassed) {
            getProtocolPersonnelService().deleteProtocolPerson(protocolDocument.getProtocol());
        }
        return mapping.findForward(Constants.MAPPING_BASIC );
    }

    /**
     * This method is to clear existing protocol person.
     * Method is called in protocolAddPersonnelSection.tag
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward clearProtocolPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        protocolForm.getPersonnelHelper().setNewProtocolPerson(new IacucProtocolPerson());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    
    /**
     * Method called when adding an attachment to a person.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws Exception if there is a problem executing the request.
     */
    public ActionForward addPersonnelAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        ProtocolDocumentBase protocolDocument = protocolForm.getProtocolDocument();
        int selectedPersonIndex = getSelectedPersonIndex(request, protocolDocument);
        ProtocolPersonBase protocolPerson = protocolDocument.getProtocol().getProtocolPerson(selectedPersonIndex);
        ProtocolAttachmentPersonnelBase newAttachmentPersonnel = protocolForm.getPersonnelHelper().getNewProtocolAttachmentPersonnels().get(selectedPersonIndex);
        newAttachmentPersonnel.setPersonId(protocolPerson.getProtocolPersonId());
        newAttachmentPersonnel.setProtocolNumber(protocolPerson.getProtocolNumber());
        
        boolean rulePassed =  applyRules(new AddIacucProtocolAttachmentPersonnelEvent(Constants.EMPTY_STRING, protocolForm.getProtocolDocument(), 
                newAttachmentPersonnel, selectedPersonIndex));

        if (rulePassed) {
            getProtocolPersonnelService().addProtocolPersonAttachment(protocolDocument.getProtocol(), newAttachmentPersonnel, selectedPersonIndex);
            protocolForm.getPersonnelHelper().getNewProtocolAttachmentPersonnels().set(selectedPersonIndex, new IacucProtocolAttachmentPersonnel());
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Method called when viewing an attachment personnel.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws Exception if there is a problem executing the request.
     */
    public ActionForward viewPersonnelAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        ProtocolDocumentBase protocolDocument = protocolForm.getProtocolDocument();
        ProtocolPersonBase protocolPerson = protocolDocument.getProtocol().getProtocolPerson(getSelectedPersonIndex(request, protocolDocument));
        ProtocolAttachmentBase attachment = protocolPerson.getAttachmentPersonnels().get(getSelectedLine(request));
        return printAttachmentProtocol(mapping, protocolForm, response, attachment);
    }

    /*
     * This is to view attachment if attachment is selected in print panel.
     */
    private ActionForward printAttachmentProtocol(ActionMapping mapping, ProtocolFormBase form, HttpServletResponse response, ProtocolAttachmentBase attachment) throws Exception {

        if (attachment == null) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        final AttachmentFile file = attachment.getFile();
       
        this.streamToResponse(file.getData(), getValidHeaderString(file.getName()), getValidHeaderString(file.getType()), response);
        return RESPONSE_ALREADY_HANDLED;
    }

    /**
     * Method called when deleting an attachment from a person.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deletePersonnelAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        ProtocolDocumentBase protocolDocument = ((ProtocolFormBase) form).getProtocolDocument();
        ProtocolPersonBase protocolPerson = protocolDocument.getProtocol().getProtocolPerson(getSelectedPersonIndex(request, protocolDocument));
        ProtocolAttachmentPersonnelBase attachment = protocolPerson.getAttachmentPersonnels().get(getSelectedLine(request));

        final StrutsConfirmation confirm 
        = buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_YES_DELETE_ATTACHMENT_PERSONNEL, 
                KeyConstants.QUESTION_DELETE_ATTACHMENT_CONFIRMATION, attachment.getAttachmentDescription(), attachment.getFile().getName());
        
        return confirm(confirm, CONFIRM_YES_DELETE_ATTACHMENT_PERSONNEL, CONFIRM_NO_DELETE);
    }
    
    /**
     * Method called when confirming the deletion an attachment personnel.
     * 
     * @param mapping the action mapping
     * @param form the form.
     * @param request the request.
     * @param response the response.
     * @return an action forward.
     * @throws Exception if there is a problem executing the request.
     */
    public ActionForward confirmDeleteAttachmentPersonnel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolDocumentBase protocolDocument = ((ProtocolFormBase) form).getProtocolDocument();
        ProtocolPersonBase protocolPerson = protocolDocument.getProtocol().getProtocolPerson(getSelectedPersonIndex(request, protocolDocument));
        ProtocolAttachmentPersonnelBase attachment = protocolPerson.getAttachmentPersonnels().get(getSelectedLine(request));

        if (attachment.getFileId() != null && !getProtocolAttachmentService().isSharedFile(attachment)) {
            ((ProtocolFormBase) form).getNotesAttachmentsHelper().getFilesToDelete().add(attachment.getFile());
        }
        protocolPerson.getAttachmentPersonnels().remove(getSelectedLine(request));

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    


    /**
     * This method is linked to ProtocolPersonnelService to perform the action
     * Add ProtocolUnitBase to Person.
     * Method is called in personUnitsSection.tag
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addProtocolPersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        ProtocolDocumentBase protocolDocument = protocolForm.getProtocolDocument();
        int selectedPersonIndex = getSelectedPersonIndex(request, protocolDocument);

        ProtocolPersonBase protocolPerson = protocolDocument.getProtocol().getProtocolPerson(selectedPersonIndex);
        
        ProtocolUnitBase newProtocolPersonUnit = protocolForm.getPersonnelHelper().getNewProtocolPersonUnits().get(selectedPersonIndex);
        boolean rulePassed = applyRules(new AddProtocolUnitEvent(Constants.EMPTY_STRING, protocolForm.getProtocolDocument(), 
                newProtocolPersonUnit, selectedPersonIndex));

        if (rulePassed) {
            getProtocolPersonnelService().addProtocolPersonUnit(protocolForm.getPersonnelHelper().getNewProtocolPersonUnits(), protocolPerson, selectedPersonIndex);
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is linked to ProtocolPersonnelService to perform the action.
     * Delete ProtocolUnitBase from Person unit list.
     * Method is called in personUnitsSection.tag
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProtocolPersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        ProtocolDocumentBase protocolDocument = protocolForm.getProtocolDocument();
        int selectedPersonIndex = getSelectedPersonIndex(request, protocolDocument);
        getProtocolPersonnelService().deleteProtocolPersonUnit(protocolDocument.getProtocol(), selectedPersonIndex, getSelectedLine(request));

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is to update protocol person details view based on modified person role. 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateProtocolPersonView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        ProtocolDocumentBase protocolDocument = protocolForm.getProtocolDocument();
        int selectedPersonIndex = getSelectedPersonIndex(request, protocolDocument);
        getProtocolPersonnelService().switchInvestigatorCoInvestigatorRole(protocolDocument.getProtocol().getProtocolPersons());
        getProtocolPersonnelService().syncPersonRoleAndUnit(protocolDocument.getProtocol().getProtocolPerson(selectedPersonIndex));
        getProtocolPersonnelService().syncPersonRoleAndAffiliation(protocolDocument.getProtocol().getProtocolPerson(selectedPersonIndex));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is to get selected person index.
     * Each person data is displayed in individual panel.
     * Person index is required to identify the person to perform an action.
     * @param request
     * @param document
     * @return
     */
    protected int getSelectedPersonIndex(HttpServletRequest request, ProtocolDocumentBase document) {
        int selectedPersonIndex = -1;
        String parameterName = (String) request.getAttribute(KRADConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            selectedPersonIndex = Integer.parseInt(StringUtils.substringBetween(parameterName, "protocolPersons[", "]."));
        }
        return selectedPersonIndex;
    }
    
    /**
     * This method is to get list of protocol persons
     * @param form
     * @return
     */
    private List<ProtocolPersonBase> getProtocolPersons(ActionForm form) {
        return ((ProtocolFormBase) form).getProtocolDocument().getProtocol().getProtocolPersons();
    }

    @Override
    public void preSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.preSave(mapping, form, request, response);
        
        ProtocolFormBase protocolForm = (ProtocolFormBase) form;
        ProtocolBase protocol = protocolForm.getProtocolDocument().getProtocol();
        
        for (ProtocolPersonBase protocolPerson : protocol.getProtocolPersons()) {
            String personComparator = (protocolPerson.getPersonId() != null) ? protocolPerson.getPersonId() : protocolPerson.getRolodexId().toString(); 
            if (protocolPerson.isPrincipalInvestigator() && !personComparator.equals(protocol.getPrincipalInvestigatorId())) {
                // reset PI from cached getter
                protocol.setPrincipalInvestigatorId(null);

                // Assign the PI the APPROVER role if PI has a personId (for doc cancel).
                if (protocolPerson.getPersonId() != null && getProtocolPersonnelService().shouldPrincipalInvestigatorBeAddedToWorkflow()) {
                    KcAuthorizationService kraAuthService = getKraAuthorizationService();
                    kraAuthService.addDocumentLevelRole(protocolPerson.getPersonId(), RoleConstants.IACUC_PROTOCOL_APPROVER, protocol);
                    protocolForm.resetUserPermissionStates();
                }
            }
            else if (!protocolPerson.isPrincipalInvestigator() &&
                    (!StringUtils.equals(protocolPerson.getPersonId(),protocol.getPrincipalInvestigatorId()))) {
               
                if (protocolPerson.getPersonId() != null) {
                    // Assign the Other Role To Viewer the AGGREGATOR role.
                    KcAuthorizationService kraAuthService = KcServiceLocator.getService(KcAuthorizationService.class);
                    kraAuthService.addDocumentLevelRole(protocolPerson.getPersonId(), RoleConstants.IACUC_PROTOCOL_VIEWER, protocol);
                    protocolForm.resetUserPermissionStates();
                }
            }
        }

        Map keyMap = new HashMap();
        keyMap.put("protocolNumber", protocol.getProtocolNumber());
        keyMap.put("sequenceNumber", protocol.getSequenceNumber());
 
        Collection<IacucProtocolAttachmentPersonnel> attachments = getBusinessObjectService().findMatching(IacucProtocolAttachmentPersonnel.class, keyMap);
        List<AttachmentFile> filesToDelete = new ArrayList<AttachmentFile>();
        List<Long> attachmentIds = new ArrayList<Long>();
        for (ProtocolAttachmentPersonnelBase attachment : protocol.getAttachmentPersonnels()) {
            if (attachment.getId() != null) {
                attachmentIds.add(attachment.getId());
            }
        }
        for (ProtocolAttachmentPersonnelBase attachment : attachments) {
            if (!attachmentIds.contains(attachment.getId()) && !getProtocolAttachmentService().isSharedFile(attachment)) {
                filesToDelete.add(attachment.getFile());
            }
        }
        protocolForm.getNotesAttachmentsHelper().setFilesToDelete(filesToDelete);
    }

    
    private ProtocolAttachmentService getProtocolAttachmentService() {
        if (protocolAttachmentService == null) {
            protocolAttachmentService = KcServiceLocator.getService("iacucProtocolAttachmentService");
        }
        return protocolAttachmentService;
    }
    
    @Override
    public void postSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.postSave(mapping, form, request, response);
        if (!((ProtocolFormBase) form).getNotesAttachmentsHelper().getFilesToDelete().isEmpty()) {
            getBusinessObjectService().delete(((ProtocolFormBase) form).getNotesAttachmentsHelper().getFilesToDelete());
            ((ProtocolFormBase) form).getNotesAttachmentsHelper().getFilesToDelete().clear();
        }
    }
}
