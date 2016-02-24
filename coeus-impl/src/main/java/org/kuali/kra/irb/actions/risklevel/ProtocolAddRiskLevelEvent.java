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
package org.kuali.kra.irb.actions.risklevel;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.irb.ProtocolDocument;

/**
 * Encapsulates a validation event for a Protocol Risk Level add action.
 */
public class ProtocolAddRiskLevelEvent extends KcDocumentEventBaseExtension {
    
    private String propertyName;
    private ProtocolRiskLevel riskLevel;

    /**
     * Constructs a ProtocolAddRiskLevelEvent.
     * 
     * @param document The document to validate
     * @param propertyName The error path property prefix
     * @param riskLevel The added Risk Level
     */
    public ProtocolAddRiskLevelEvent(ProtocolDocument document, String propertyName, ProtocolRiskLevel riskLevel) {
        super("Enter risk level", "", document);
        this.propertyName = propertyName;
        this.riskLevel = riskLevel;
    }
    
    public ProtocolDocument getProtocolDocument() {
        return (ProtocolDocument) getDocument();
    }
    
    public String getPropertyName() {
        return propertyName;
    }
    
    public ProtocolRiskLevel getProtocolRiskLevel() {
        return riskLevel;
    }

    @Override
    @SuppressWarnings("unchecked")
    public KcBusinessRule getRule() {
        return new ProtocolAddRiskLevelRule();
    }

}
