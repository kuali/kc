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
package org.kuali.kra.iacuc.onlinereview.authorization;

import org.kuali.kra.authorization.Task;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.protocol.ProtocolOnlineReviewDocument;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReview;

public class IacucProtocolOnlineReviewTask extends Task {
    private ProtocolOnlineReview protocolOnlineReview;
    private ProtocolOnlineReviewDocument protocolOnlineReviewDocument;
    
    /**
     * Constructs a ProtocolOnlineReviewTask.
     * @param taskName the name of the task
     * @param protocolOnlineReview the Protocol
     */
    public IacucProtocolOnlineReviewTask(String taskName, ProtocolOnlineReview protocolOnlineReview) {
        super(TaskGroupName.IACUC_PROTOCOL_ONLINEREVIEW, taskName);
        this.protocolOnlineReview = protocolOnlineReview;
    }
    
    public IacucProtocolOnlineReviewTask(String taskName, ProtocolOnlineReviewDocument protocolOnlineReviewDocument) {
        super(TaskGroupName.IACUC_PROTOCOL_ONLINEREVIEW, taskName);
        this.protocolOnlineReview = protocolOnlineReviewDocument.getProtocolOnlineReview();
        this.protocolOnlineReviewDocument = protocolOnlineReviewDocument;
    }
    
    public IacucProtocolOnlineReviewTask(String taskName, ProtocolOnlineReview protocolOnlineReview, String genericTaskName) {
        super(TaskGroupName.IACUC_PROTOCOL_ONLINEREVIEW, taskName, genericTaskName);
        this.protocolOnlineReview = protocolOnlineReview;
    }

    public IacucProtocolOnlineReviewTask(String taskName, ProtocolOnlineReviewDocument protocolOnlineReviewDocument, String genericTaskName) {
        super(TaskGroupName.IACUC_PROTOCOL_ONLINEREVIEW, taskName, genericTaskName);
        this.protocolOnlineReview = protocolOnlineReviewDocument.getProtocolOnlineReview();
        this.protocolOnlineReviewDocument = protocolOnlineReviewDocument;
    }

    /**
     * Get the ProtocolOnlineReview.
     * @return the ProtocolOnlineReview
     */
    public ProtocolOnlineReview getProtocolOnlineReview() {
        return protocolOnlineReview;
    }

    /**
     * Gets the protocolOnlineReviewDocument attribute. 
     * @return Returns the protocolOnlineReviewDocument.
     */
    public ProtocolOnlineReviewDocument getProtocolOnlineReviewDocument() {
        return protocolOnlineReviewDocument;
    }

}
