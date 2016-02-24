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
package org.kuali.kra.irb.onlinereview.authorization;

import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.irb.ProtocolOnlineReviewDocument;
import org.kuali.kra.irb.onlinereview.ProtocolOnlineReview;

/**
 * A Protocol Online Review Task is a task that performs an action against a
 * Protocol Online Review.  To assist authorization, the ProtocolOnlineReview is available.
 */
public final class ProtocolOnlineReviewTask extends Task {
    
    private ProtocolOnlineReview protocolOnlineReview;
    private ProtocolOnlineReviewDocument protocolOnlineReviewDocument;
    
    /**
     * Constructs a ProtocolOnlineReviewTask.
     * @param taskName the name of the task
     * @param protocolOnlineReview the Protocol
     */
    public ProtocolOnlineReviewTask(String taskName, ProtocolOnlineReview protocolOnlineReview) {
        super(TaskGroupName.PROTOCOL_ONLINEREVIEW, taskName);
        this.protocolOnlineReview = protocolOnlineReview;
    }
    
    public ProtocolOnlineReviewTask(String taskName, ProtocolOnlineReviewDocument protocolOnlineReviewDocument) {
        super(TaskGroupName.PROTOCOL_ONLINEREVIEW, taskName);
        this.protocolOnlineReview = (ProtocolOnlineReview) protocolOnlineReviewDocument.getProtocolOnlineReview();
        this.protocolOnlineReviewDocument = protocolOnlineReviewDocument;
    }
    
    public ProtocolOnlineReviewTask(String taskName, ProtocolOnlineReview protocolOnlineReview, String genericTaskName) {
        super(TaskGroupName.PROTOCOL_ONLINEREVIEW, taskName, genericTaskName);
        this.protocolOnlineReview = protocolOnlineReview;
    }

    public ProtocolOnlineReviewTask(String taskName, ProtocolOnlineReviewDocument protocolOnlineReviewDocument, String genericTaskName) {
        super(TaskGroupName.PROTOCOL_ONLINEREVIEW, taskName, genericTaskName);
        this.protocolOnlineReview = (ProtocolOnlineReview) protocolOnlineReviewDocument.getProtocolOnlineReview();
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
