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
package org.kuali.kra.irb.noteattachment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.MessageMap;
import org.kuali.rice.kns.util.TypedArrayList;

/**
 * Adaptor class to provide ProtocolPersonnellAttachment actions from the ProtocolNoteAndAttachmentAction
 * class to the ProtocolPersonnelAction class.
 */
public class ProtocolPersonnelAttachmentAdapter {
    
    private static final String OLD_PROPERTY_PREFIX = "notesAttachmentsHelper.newAttachmentPersonnel.";
    private static final String NEW_PROPERTY_PREFIX_1 = "personnelHelper.newProtocolAttachmentPersonnels[";
    private static final String NEW_PROPERTY_PREFIX_2 = "].";
    private static final String TYPE_CODE = "typeCode";
    private static final String DESCRIPTION = "description";
    private static final String NEW_FILE = "newFile";

    /**
     * Add a protocol personnel attachment to a person by using the addAttachmentPersonnel action from ProtocolNoteAndAttachmentAction.
     * 
     * @param protocolForm
     * @param protocolPerson
     * @param selectedPersonIndex
     * @param newAttachmentPersonnel
     */
    public ActionForward addProtocolAttachmentPersonnel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        // initialize helper for foreign action
        ProtocolForm protocolForm = (ProtocolForm) form;
        ProtocolDocument protocolDocument = protocolForm.getDocument();
        int selectedPersonIndex = getSelectedPersonIndex(request, protocolDocument);
        ProtocolPerson protocolPerson = protocolDocument.getProtocol().getProtocolPerson(selectedPersonIndex);
        ProtocolAttachmentPersonnel newAttachmentPersonnel = protocolForm.getPersonnelHelper().getNewProtocolAttachmentPersonnels().get(selectedPersonIndex);
        newAttachmentPersonnel.setPersonId(protocolPerson.getProtocolPersonId());
        newAttachmentPersonnel.setProtocolNumber(protocolPerson.getProtocolNumber());
        protocolForm.getNotesAttachmentsHelper().setNewAttachmentPersonnel(newAttachmentPersonnel);

        // call foreign action
        ProtocolNoteAndAttachmentAction action = new ProtocolNoteAndAttachmentAction();
        ActionForward actionForward = action.addAttachmentPersonnel(mapping, form, request, response);
        
        // on successful add populate screen
        if (protocolPerson.getAttachmentPersonnels().contains(newAttachmentPersonnel)) {
            protocolForm.getPersonnelHelper().getNewProtocolAttachmentPersonnels().set(selectedPersonIndex, new ProtocolAttachmentPersonnel());
        }
        
        rewriteMessages(selectedPersonIndex);
        
        return actionForward;

    }
    
    /**
     * This method is to get selected person index.
     * Each person data is displayed in individual panel.
     * Person index is required to identify the person to perform an action.
     * @param request
     * @param document
     * @return
     */
    private int getSelectedPersonIndex(HttpServletRequest request, ProtocolDocument document) {
        int selectedPersonIndex = -1;
        String parameterName = (String) request.getAttribute(KNSConstants.METHOD_TO_CALL_ATTRIBUTE);
        if (StringUtils.isNotBlank(parameterName)) {
            selectedPersonIndex = Integer.parseInt(StringUtils.substringBetween(parameterName, "protocolPersons[", "]."));
        }
        return selectedPersonIndex;
    }

    /**
     * This method rewrites the error messages of ProtocolNoteAndAttachmentAction.
     */
    private void rewriteMessages(int selectedPersonIndex) {
        MessageMap messageMap = GlobalVariables.getMessageMap();
        
        replaceMessageProperty(messageMap, selectedPersonIndex, TYPE_CODE);
        replaceMessageProperty(messageMap, selectedPersonIndex, DESCRIPTION);
        replaceMessageProperty(messageMap, selectedPersonIndex, NEW_FILE);
    }
    
    /**
     * This method replaces the property of the error, warning and info messages.
     * @param messageMap
     * @param oldProperty
     * @param newProperty
     */
    private void replaceMessageProperty(MessageMap messageMap, int index, String propertyField) {
        String oldProperty = OLD_PROPERTY_PREFIX + propertyField;
        String newProperty = NEW_PROPERTY_PREFIX_1 + index + NEW_PROPERTY_PREFIX_2 + propertyField;
        
        TypedArrayList errorMessages = messageMap.getErrorMessagesForProperty(oldProperty);
        if ((errorMessages != null) && !errorMessages.isEmpty()) {
            messageMap.removeAllErrorMessagesForProperty(oldProperty);
            for (Object errorMessage : errorMessages) {
                messageMap.putError(newProperty, ((ErrorMessage) errorMessage).getErrorKey(), ((ErrorMessage) errorMessage).getMessageParameters());
            }
        }

        TypedArrayList warningMessages = messageMap.getWarningMessagesForProperty(oldProperty);
        if ((warningMessages != null) && !warningMessages.isEmpty()) {
            messageMap.removeAllWarningMessagesForProperty(oldProperty);
            for (Object errorMessage : warningMessages) {
                messageMap.putWarning(newProperty, ((ErrorMessage) errorMessage).getErrorKey(), ((ErrorMessage) errorMessage).getMessageParameters());
            }
        }

        TypedArrayList infoMessages = messageMap.getInfoMessagesForProperty(oldProperty);
        if ((infoMessages != null) && !infoMessages.isEmpty()) {
            messageMap.removeAllInfoMessagesForProperty(oldProperty);
            for (Object errorMessage : infoMessages) {
                messageMap.putInfo(newProperty, ((ErrorMessage) errorMessage).getErrorKey(), ((ErrorMessage) errorMessage).getMessageParameters());
            }
        }
    }
}
