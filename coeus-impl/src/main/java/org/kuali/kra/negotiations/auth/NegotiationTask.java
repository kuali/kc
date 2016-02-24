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
package org.kuali.kra.negotiations.auth;

import org.kuali.coeus.common.framework.auth.task.Task;
import org.kuali.kra.infrastructure.TaskGroupName;
import org.kuali.kra.negotiations.document.NegotiationDocument;

/**
 * 
 * This class manages the attributes needed for a negotiation task.
 */
public class NegotiationTask extends Task {
    
    private NegotiationDocument negotiationDocument;

    /**
     * 
     * Constructs a NegotiationTask.java.
     * @param taskName
     * @param negotiation
     */
    public NegotiationTask(String taskName, NegotiationDocument negotiationDocument) {
        super(TaskGroupName.NEGOTIATION, taskName);
        this.negotiationDocument = negotiationDocument;
    }
    
    public NegotiationDocument getNegotiationDocument() {
        return negotiationDocument;
    }

    public void setNegotiationDocument(NegotiationDocument negotiationDocument) {
        this.negotiationDocument = negotiationDocument;
    }    
}
