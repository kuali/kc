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
package org.kuali.kra.irb.actions.history;


import java.util.Date;

import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.rice.krad.document.Document;

/**
 * Encapsulates the history filter event on the Protocol Actions panel.
 */
public class ProtocolHistoryFilterDatesEvent extends KraDocumentEventBaseExtension {
    
    private Date startDate;
    private Date endDate;

    /**
     * Constructs a ProtocolHistoryFilterDatesEvent.
     * @param document
     * @param startDate
     * @param endDate
     */
    public ProtocolHistoryFilterDatesEvent(Document document, Date startDate, Date endDate) {
        super("Filter protocol history", "", document);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.rule.event.KraDocumentEventBaseExtension#getRule()
     */
    @Override
    @SuppressWarnings("unchecked")
    public BusinessRuleInterface getRule() {
        return new ProtocolHistoryFilterDatesRule();
    }

}