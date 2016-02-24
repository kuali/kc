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
package org.kuali.kra.irb;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.rice.krad.document.Document;

/**
 * 
 * This class
 */
public abstract class ProtocolEventBase<Z extends KcBusinessRule> extends KcDocumentEventBaseExtension {

    /**
     * Enum helps identify type of error to respond.
     */
    public enum ErrorType {
        HARDERROR, SOFTERROR
    };

    private ErrorType type;

    public ProtocolEventBase(String description, String errorPathPrefix, Document document, ErrorType type) {
        super(description, errorPathPrefix, document);
        this.type = type;
    }

    /**
     * This method should return CommitteeScheduleEvent.event.
     * 
     * @return
     */
    public ErrorType getType() {
        return this.type;
    }

}
