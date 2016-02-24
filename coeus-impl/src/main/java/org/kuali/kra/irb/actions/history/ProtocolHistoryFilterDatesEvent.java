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
package org.kuali.kra.irb.actions.history;


import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.rice.krad.document.Document;

import java.util.Date;

/**
 * Encapsulates the history filter event on the Protocol Actions panel.
 */
public class ProtocolHistoryFilterDatesEvent extends KcDocumentEventBaseExtension {
    
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

    @Override
    @SuppressWarnings("unchecked")
    public KcBusinessRule getRule() {
        return new ProtocolHistoryFilterDatesRule();
    }

}
