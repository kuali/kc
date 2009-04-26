/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.rule.event;

import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.rice.kns.document.Document;

/**
 * 
 * This class
 */
public abstract class ProtocolEventBase<Z extends BusinessRuleInterface> extends KraDocumentEventBaseExtension {

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
