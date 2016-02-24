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
package org.kuali.kra.coi.disclosure;

import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.kra.coi.DisclosureReporterUnit;

import java.util.List;

public class SaveDisclosureReporterUnitEvent  extends KcDocumentEventBaseExtension {
    
    private String propertyName;
    private List<? extends DisclosureReporterUnit> disclosureReporterUnits;
    /**
     * Constructs a ProtocolAddReviewAttachmentEvent.
     * 
     * @param document The document to validate
     * @param propertyName The error path property prefix
     * @param reviewAttachment The added Reviewer Attachment
     */
    public SaveDisclosureReporterUnitEvent(String propertyName, List<? extends DisclosureReporterUnit> disclosureReporterUnits) {
        super("Save reporter unit", "", null);
        this.propertyName = propertyName;
        this.disclosureReporterUnits = disclosureReporterUnits;
    }
        
    public String getPropertyName() {
        return propertyName;
    }
    
 
    @Override
    @SuppressWarnings("unchecked")
    public KcBusinessRule getRule() {
        return new SaveDisclosureReporterUnitRule();
    }

    public List<? extends DisclosureReporterUnit> getDisclosureReporterUnits() {
        return disclosureReporterUnits;
    }

    public void setDisclosureReporterUnits(List<? extends DisclosureReporterUnit> disclosureReporterUnits) {
        this.disclosureReporterUnits = disclosureReporterUnits;
    }



}
