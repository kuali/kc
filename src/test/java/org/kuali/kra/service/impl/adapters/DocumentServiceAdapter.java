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
package org.kuali.kra.service.impl.adapters;

import java.util.List;

import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.bo.AdHocRouteRecipient;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.DocumentService;

public class DocumentServiceAdapter implements DocumentService {

    @Override
    public boolean saveDocumentNotes(Document document) {
        return false;
    }

    @Override
    public void sendNoteRouteNotification(Document document, Note note, Person sender) throws WorkflowException {
    }

    public Document acknowledgeDocument(Document document, String annotation, List adHocRecipients) throws WorkflowException {
        return null;
    }

    public boolean addNoteToDocument(Document document, Note note) {
        return false;
    }

    public Document approveDocument(Document document, String annotation, List adHocRoutingRecipients) throws WorkflowException {
        return null;
    }

    public Document blanketApproveDocument(Document document, String annotation, List adHocRecipients) throws WorkflowException {
        return null;
    }

    public Document cancelDocument(Document document, String annotation) throws WorkflowException {
        return null;
    }

    public Document clearDocumentFyi(Document document, List adHocRecipients) throws WorkflowException {
        return null;
    }

    @Override
    public Note createNoteFromDocument(Document document, String text) {
        return null;
    }

    public Document disapproveDocument(Document document, String annotation) throws Exception {
        return null;
    }

    public boolean documentExists(String documentHeaderId) {
        return false;
    }

    public Document getByDocumentHeaderId(String documentHeaderId) throws WorkflowException {
        return null;
    }

    public Document getByDocumentHeaderIdSessionless(String documentHeaderId) throws WorkflowException {
        return null;
    }

    public List getDocumentsByListOfDocumentHeaderIds(Class clazz, List documentHeaderIds) throws WorkflowException {
        return null;
    }

    public Document getNewDocument(String documentTypeName) throws WorkflowException {
        return null;
    }

    public Document getNewDocument(Class documentClass) throws WorkflowException {
        return null;
    }

    public PersistableBusinessObject getNoteParent(Document document, Note newNote) {
        return null;
    }

    public void prepareWorkflowDocument(Document document) throws WorkflowException {

    }

    public Document routeDocument(Document document, String annotation, List adHocRoutingRecipients) throws WorkflowException {
        return null;
    }

    public Document saveDocument(Document document) throws WorkflowException {
        return null;
    }

    public Document saveDocument(Document document, Class kualiDocumentEventClass) throws WorkflowException {
        return null;
    }

    public void sendAdHocRequests(Document document, String annotation, List<AdHocRouteRecipient> adHocRecipients) throws WorkflowException {

    }

    public Document superUserApproveDocument(Document document, String annotation) throws WorkflowException {
        return null;
    }

    public Document superUserCancelDocument(Document document, String annotation) throws WorkflowException {
        return null;
    }

    public Document superUserDisapproveDocument(Document document, String annotation) throws WorkflowException {
        return null;
    }

    @Override
    public Document updateDocument(Document document) {
        return null;
    }


}
