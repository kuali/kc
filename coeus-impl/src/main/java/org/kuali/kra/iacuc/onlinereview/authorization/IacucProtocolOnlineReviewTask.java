/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.iacuc.onlinereview.authorization;

import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocumentBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;

public class IacucProtocolOnlineReviewTask extends Task {
    private ProtocolOnlineReviewBase protocolOnlineReview;
    private ProtocolOnlineReviewDocumentBase protocolOnlineReviewDocument;
    
    /**
     * Constructs a ProtocolOnlineReviewTask.
     * @param taskName the name of the task
     * @param protocolOnlineReview the Protocol
     */
    public IacucProtocolOnlineReviewTask(String taskName, ProtocolOnlineReviewBase protocolOnlineReview) {
        super(TaskGroupName.IACUC_PROTOCOL_ONLINEREVIEW, taskName);
        this.protocolOnlineReview = protocolOnlineReview;
    }
    
    public IacucProtocolOnlineReviewTask(String taskName, ProtocolOnlineReviewDocumentBase protocolOnlineReviewDocument) {
        super(TaskGroupName.IACUC_PROTOCOL_ONLINEREVIEW, taskName);
        this.protocolOnlineReview = protocolOnlineReviewDocument.getProtocolOnlineReview();
        this.protocolOnlineReviewDocument = protocolOnlineReviewDocument;
    }
    
    public IacucProtocolOnlineReviewTask(String taskName, ProtocolOnlineReviewBase protocolOnlineReview, String genericTaskName) {
        super(TaskGroupName.IACUC_PROTOCOL_ONLINEREVIEW, taskName, genericTaskName);
        this.protocolOnlineReview = protocolOnlineReview;
    }

    public IacucProtocolOnlineReviewTask(String taskName, ProtocolOnlineReviewDocumentBase protocolOnlineReviewDocument, String genericTaskName) {
        super(TaskGroupName.IACUC_PROTOCOL_ONLINEREVIEW, taskName, genericTaskName);
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
