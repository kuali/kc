/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.service;

import org.kuali.core.document.Document;

/**
 * The KRA Workflow Service is simply a set or re-usable functionality
 * to determine the workflow state of a document as well as who can
 * work on the document when it is in workflow.
 */
public interface KraWorkflowService {

    /**
     * Does the given user have the right to work on the document if
     * the document is in workflow?
     * @param username the username of the person
     * @param doc the document
     * @return true if the person has permission; otherwise false
     */
    public boolean hasWorkflowPermission(String username, Document doc);
    
    /**
     * Is the document currently enroute within workflow?
     * @param doc the document
     * @return true if enroute; otherwise false
     */
    public boolean isEnRoute(Document doc);
    
    /**
     * Is the document closed?  A closed document has been Approved,
     * Disapproved, Canceled, or encountered an Exception.
     * @param doc the document
     * @return true if closed; otherwise false
     */
    public boolean isClosed(Document doc);
    
    /**
     * Determine if the document has been submitted to workflow or not?
     * @param doc the document
     * @return true if in workflow; otherwise false
     */
    public boolean isInWorkflow(Document doc);
}
