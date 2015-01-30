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
