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
package org.kuali.kra.irb.personnel;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.common.framework.attachment.AttachmentFile;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolAction;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentService;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentPersonnelBase;
import org.kuali.kra.protocol.personnel.AddProtocolUnitEvent;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The ProtocolPersonnelAction corresponds to the Personnel tab (web page).  It is
 * responsible for handling all user requests from that tab (web page).
 */
public class ProtocolPersonnelAction extends ProtocolAction {
    

    private static final ActionForward RESPONSE_ALREADY_HANDLED = null;
    private static final String CONFIRM_YES_DELETE_ATTACHMENT_PERSONNEL = "confirmDeleteAttachmentPersonnel";
    private static final String CONFIRM_NO_DELETE = "";


    private ProtocolAttachmentService protocolAttachmentService;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        getProtocolPersonnelService().selectProtocolUnit(getProtocolPersons(form));
        ((ProtocolForm)form).getPersonnelHelper().prepareView();
        
        return actionForward;
    }

    /**
     * This method is linked to ProtocolPersonnelService to perform the action - Add Protocol Person. 
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
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolPerson newProtocolPerson = (ProtocolPerson) protocolForm.getPersonnelHelper().getNewProtocolPerson();
        Protocol protocol = (Protocol) protocolForm.getProtocolDocument().getProtocol();

        // check any business rules
        boolean rulePassed = applyRules(new AddProtocolPersonnelEvent(Constants.EMPTY_STRING, (ProtocolDocument) protocolForm.getProtocolDocument(), newProtocolPerson));
        if (rulePassed) {
            getProtocolPersonnelService().addProtocolPerson(protocol, newProtocolPerson);
            //If we are adding a new principal investigator, make sure we update the person id
            if (StringUtils.equals(newProtocolPerson.getProtocolPersonRoleId(), ProtocolPersonRole.ROLE_PRINCIPAL_INVESTIGATOR)) {
                protocolForm.getProtocolHelper().setPersonId(newProtocolPerson.getPersonId());
            }
            protocolForm.getPersonnelHelper().setNewProtocolPerson(new ProtocolPerson());
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * This method is linked to ProtocolPersonnelService to perform the action - Delete Protocol Person.
     * Method is called in ProtocolPersonnel.jsp
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProtocolPerson(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = (ProtocolDocument) protocolForm.getProtocolDocument();
        getProtocolPersonnelService().deleteProtocolPerson(protocolDocument.getProtocol());
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
        ProtocolForm protocolForm = (ProtocolForm) form;
        protocolForm.getPersonnelHelper().setNewProtocolPerson(new ProtocolPerson());
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
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = (ProtocolDocument) protocolForm.getProtocolDocument();
        int selectedPersonIndex = getSelectedPersonIndex(request, protocolDocument);
        ProtocolPerson protocolPerson = (ProtocolPerson) protocolDocument.getProtocol().getProtocolPerson(selectedPersonIndex);
        ProtocolAttachmentPersonnel newAttachmentPersonnel = (ProtocolAttachmentPersonnel) protocolForm.getPersonnelHelper().getNewProtocolAttachmentPersonnels().get(selectedPersonIndex);
        newAttachmentPersonnel.setPersonId(protocolPerson.getProtocolPersonId());
        newAttachmentPersonnel.setProtocolNumber(protocolPerson.getProtocolNumber());
        
        boolean rulePassed =  applyRules(new AddProtocolAttachmentPersonnelEvent(Constants.EMPTY_STRING, (ProtocolDocument) protocolForm.getProtocolDocument(), 
                newAttachmentPersonnel, selectedPersonIndex));

        if (rulePassed) {
            getProtocolPersonnelService().addProtocolPersonAttachment(protocolDocument.getProtocol(), newAttachmentPersonnel, selectedPersonIndex);
            protocolForm.getPersonnelHelper().getNewProtocolAttachmentPersonnels().set(selectedPersonIndex, new ProtocolAttachmentPersonnel());
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
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = (ProtocolDocument) protocolForm.getProtocolDocument();
        ProtocolPerson protocolPerson = (ProtocolPerson) protocolDocument.getProtocol().getProtocolPerson(getSelectedPersonIndex(request, protocolDocument));
        ProtocolAttachmentBase attachment = protocolPerson.getAttachmentPersonnels().get(getSelectedLine(request));
        return printAttachmentProtocol(mapping, protocolForm, response, attachment);
    }

    /*
     * This is to view attachment if attachment is selected in print panel.
     */
    private ActionForward printAttachmentProtocol(ActionMapping mapping, ProtocolForm form, HttpServletResponse response, ProtocolAttachmentBase attachment) throws Exception {

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
        ProtocolDocument protocolDocument = (ProtocolDocument) ((ProtocolForm) form).getProtocolDocument();
        ProtocolPerson protocolPerson = (ProtocolPerson) protocolDocument.getProtocol().getProtocolPerson(getSelectedPersonIndex(request, protocolDocument));
        ProtocolAttachmentPersonnel attachment = (ProtocolAttachmentPersonnel) protocolPerson.getAttachmentPersonnels().get(getSelectedLine(request));

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
        ProtocolDocument protocolDocument = (ProtocolDocument) ((ProtocolForm) form).getProtocolDocument();
        ProtocolPerson protocolPerson = (ProtocolPerson) protocolDocument.getProtocol().getProtocolPerson(getSelectedPersonIndex(request, protocolDocument));
        ProtocolAttachmentPersonnel attachment = (ProtocolAttachmentPersonnel) protocolPerson.getAttachmentPersonnels().get(getSelectedLine(request));

        if (attachment.getFileId() != null && !getProtocolAttachmentService().isSharedFile(attachment)) {
            ((ProtocolForm) form).getNotesAttachmentsHelper().getFilesToDelete().add(attachment.getFile());
        }
        protocolPerson.getAttachmentPersonnels().remove(getSelectedLine(request));

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    


    /**
     * This method is linked to ProtocolPersonnelService to perform the action
     * Add ProtocolUnit to Person.
     * Method is called in personUnitsSection.tag
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addProtocolPersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = (ProtocolDocument) protocolForm.getProtocolDocument();
        int selectedPersonIndex = getSelectedPersonIndex(request, protocolDocument);

        ProtocolPerson protocolPerson = (ProtocolPerson) protocolDocument.getProtocol().getProtocolPerson(selectedPersonIndex);
        
        ProtocolUnit newProtocolPersonUnit = (ProtocolUnit) protocolForm.getPersonnelHelper().getNewProtocolPersonUnits().get(selectedPersonIndex);
        boolean rulePassed = applyRules(new AddProtocolUnitEvent(Constants.EMPTY_STRING, protocolForm.getProtocolDocument(), newProtocolPersonUnit, selectedPersonIndex));
        if (rulePassed) {
            getProtocolPersonnelService().addProtocolPersonUnit(protocolForm.getPersonnelHelper().getNewProtocolPersonUnits(), protocolPerson, selectedPersonIndex);
        }

        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method is linked to ProtocolPersonnelService to perform the action.
     * Delete ProtocolUnit from Person unit list.
     * Method is called in personUnitsSection.tag
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteProtocolPersonUnit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = (ProtocolDocument) protocolForm.getProtocolDocument();
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
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = (ProtocolDocument) protocolForm.getProtocolDocument();
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
    protected int getSelectedPersonIndex(HttpServletRequest request, ProtocolDocument document) {
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
        return ((ProtocolForm) form).getProtocolDocument().getProtocol().getProtocolPersons();
    }

    @Override
    public void preSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.preSave(mapping, form, request, response);
        
        ProtocolForm protocolForm = (ProtocolForm) form;
        Protocol protocol = (Protocol) protocolForm.getProtocolDocument().getProtocol();
        
        for (ProtocolPersonBase protocolPerson : protocol.getProtocolPersons()) {
            String personComparator = (protocolPerson.getPersonId() != null) ? protocolPerson.getPersonId() : protocolPerson.getRolodexId().toString(); 
            if (protocolPerson.isPrincipalInvestigator() && !personComparator.equals(protocol.getPrincipalInvestigatorId())) {
                // reset PI from cached getter
                protocol.setPrincipalInvestigatorId(null);

                // Assign the PI the APPROVER role if PI has a personId (for doc cancel).
                if (protocolPerson.getPersonId() != null) {
                    KcAuthorizationService kraAuthService = KcServiceLocator.getService(KcAuthorizationService.class);
                    kraAuthService.addDocumentLevelRole(protocolPerson.getPersonId(), RoleConstants.PROTOCOL_APPROVER, protocol);
                    protocolForm.resetUserPermissionStates();
                    
                }
            }
            else if (!protocolPerson.isPrincipalInvestigator() &&
                    (!StringUtils.equals(protocolPerson.getPersonId(),protocol.getPrincipalInvestigatorId()))) {
               
                if (protocolPerson.getPersonId() != null) {
                    // Assign the Other Role To Viewer the AGGREGATOR role.
                    KcAuthorizationService kraAuthService = KcServiceLocator.getService(KcAuthorizationService.class);
                    kraAuthService.addDocumentLevelRole(protocolPerson.getPersonId(), RoleConstants.PROTOCOL_VIEWER, protocol);
                    protocolForm.resetUserPermissionStates();
                }
            }
            
            // we need to rebuild the user states if affiliations have been modified
            if(protocolPerson.isAffiliationTypeCodeChanged()) {
                protocolForm.resetUserPermissionStates();
            }
        }

        Map keyMap = new HashMap();
        keyMap.put("protocolNumber", protocol.getProtocolNumber());
        keyMap.put("sequenceNumber", protocol.getSequenceNumber());
 
        List<ProtocolAttachmentPersonnel> attachments = (List<ProtocolAttachmentPersonnel>)getBusinessObjectService().findMatching(ProtocolAttachmentPersonnel.class, keyMap);
        List<AttachmentFile> filesToDelete = new ArrayList<AttachmentFile>();
        List<Long> attachmentIds = new ArrayList<Long>();
        for (ProtocolAttachmentPersonnelBase attachment : protocol.getAttachmentPersonnels()) {
            if (attachment.getId() != null) {
                attachmentIds.add(attachment.getId());
            }
        }
        for (ProtocolAttachmentPersonnel attachment : attachments) {
            if (!attachmentIds.contains(attachment.getId()) && !getProtocolAttachmentService().isSharedFile(attachment)) {
                filesToDelete.add(attachment.getFile());
            }
        }        
         
        protocolForm.getNotesAttachmentsHelper().setFilesToDelete(filesToDelete);
        
    }

    
    private ProtocolAttachmentService getProtocolAttachmentService() {
        if (protocolAttachmentService == null) {
            protocolAttachmentService = KcServiceLocator.getService(ProtocolAttachmentService.class);
        }
        return protocolAttachmentService;
    }
    
    @Override
    public void postSave(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        super.postSave(mapping, form, request, response);
        if (!((ProtocolForm) form).getNotesAttachmentsHelper().getFilesToDelete().isEmpty()) {
            getBusinessObjectService().delete(((ProtocolForm) form).getNotesAttachmentsHelper().getFilesToDelete());
            ((ProtocolForm) form).getNotesAttachmentsHelper().getFilesToDelete().clear();
        }
        
    }
}
