/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol.onlinereview.authorization;

import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;

/**
 * A ProtocolBase Online Review Task is a task that performs an action against a
 * ProtocolBase Online Review.  To assist authorization, the ProtocolOnlineReviewBase is available.
 */
public final class ProtocolOnlineReviewTask extends Task {
    
    private ProtocolOnlineReviewBase protocolOnlineReview;
    private ProtocolOnlineReviewDocumentBase protocolOnlineReviewDocument;
    
    /**
     * Constructs a ProtocolOnlineReviewTask.
     * @param taskName the name of the task
     * @param protocolOnlineReview the ProtocolBase
     */
    public ProtocolOnlineReviewTask(String taskName, ProtocolOnlineReviewBase protocolOnlineReview) {
        super(TaskGroupName.PROTOCOL_ONLINEREVIEW, taskName);
        this.protocolOnlineReview = protocolOnlineReview;
    }
    
    public ProtocolOnlineReviewTask(String taskName, ProtocolOnlineReviewDocumentBase protocolOnlineReviewDocument) {
        super(TaskGroupName.PROTOCOL_ONLINEREVIEW, taskName);
        this.protocolOnlineReview = protocolOnlineReviewDocument.getProtocolOnlineReview();
        this.protocolOnlineReviewDocument = protocolOnlineReviewDocument;
    }
    
    public ProtocolOnlineReviewTask(String taskName, ProtocolOnlineReviewBase protocolOnlineReview, String genericTaskName) {
        super(TaskGroupName.PROTOCOL_ONLINEREVIEW, taskName, genericTaskName);
        this.protocolOnlineReview = protocolOnlineReview;
    }

    public ProtocolOnlineReviewTask(String taskName, ProtocolOnlineReviewDocumentBase protocolOnlineReviewDocument, String genericTaskName) {
        super(TaskGroupName.PROTOCOL_ONLINEREVIEW, taskName, genericTaskName);
        this.protocolOnlineReview = protocolOnlineReviewDocument.getProtocolOnlineReview();
        this.protocolOnlineReviewDocument = protocolOnlineReviewDocument;
    }

    /**
     * Get the ProtocolOnlineReviewBase.
     * @return the ProtocolOnlineReviewBase
     */
    public ProtocolOnlineReviewBase getProtocolOnlineReview() {
        return protocolOnlineReview;
    }

    /**
     * Gets the protocolOnlineReviewDocument attribute. 
     * @return Returns the protocolOnlineReviewDocument.
     */
    public ProtocolOnlineReviewDocumentBase getProtocolOnlineReviewDocument() {
        return protocolOnlineReviewDocument;
    }

}    
