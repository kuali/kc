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
package org.kuali.kra.irb.actions.expeditedapprove;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.approve.ProtocolApproveEvent;

/**
 * Encapsulates the event that the IRB Administrator approves a protocol.
 */
public class ProtocolExpeditedApproveEvent extends ProtocolApproveEvent {
        
    /**
     * Constructs a ProtocolExpeditedApproveEvent.
     * @param document the document to validate
     * @param protocolApproveBean the bean that keeps the comments and dates
     */
    public ProtocolExpeditedApproveEvent(ProtocolDocument document, ProtocolExpeditedApproveBean protocolApproveBean) {
        super(document, protocolApproveBean);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public KcBusinessRule getRule() {
        return new ProtocolExpeditedApproveRule();
    }
    
}
