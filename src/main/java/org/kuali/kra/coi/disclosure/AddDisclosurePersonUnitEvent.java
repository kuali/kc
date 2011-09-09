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
package org.kuali.kra.coi.disclosure;

import java.util.List;

import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.committee.document.CommitteeDocument;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.reviewcomments.ProtocolAddReviewAttachmentRule;
import org.kuali.kra.irb.onlinereview.ProtocolReviewAttachment;
import org.kuali.kra.meeting.MeetingAddMinuteRule;
import org.kuali.kra.meeting.MeetingEventBase;
import org.kuali.kra.meeting.MeetingEventBase.ErrorType;
import org.kuali.kra.meeting.MeetingHelper;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rule.event.KraDocumentEventBaseExtension;
import org.kuali.rice.kns.document.Document;

public class AddDisclosurePersonUnitEvent  extends KraDocumentEventBaseExtension {
    
    private String propertyName;
    private DisclosurePersonUnit disclosurePersonUnit;
    private List<DisclosurePersonUnit> disclosurePersonUnits;
    /**
     * Constructs a ProtocolAddReviewAttachmentEvent.
     * 
     * @param document The document to validate
     * @param propertyName The error path property prefix
     * @param reviewAttachment The added Reviewer Attachment
     */
    public AddDisclosurePersonUnitEvent(String propertyName, DisclosurePersonUnit disclosurePersonUnit, List<DisclosurePersonUnit> disclosurePersonUnits) {
        super("Add financial entity unit", "", null);
        this.propertyName = propertyName;
        this.disclosurePersonUnit = disclosurePersonUnit;
        this.disclosurePersonUnits = disclosurePersonUnits;
        
    }
        
    public String getPropertyName() {
        return propertyName;
    }
    
 
    @Override
    @SuppressWarnings("unchecked")
    public BusinessRuleInterface getRule() {
        return new AddDisclosurePersonUnitRule();
    }

    public DisclosurePersonUnit getDisclosurePersonUnit() {
        return disclosurePersonUnit;
    }

    public void setDisclosurePersonUnit(DisclosurePersonUnit disclosurePersonUnit) {
        this.disclosurePersonUnit = disclosurePersonUnit;
    }

    public List<DisclosurePersonUnit> getDisclosurePersonUnits() {
        return disclosurePersonUnits;
    }

    public void setDisclosurePersonUnits(List<DisclosurePersonUnit> disclosurePersonUnits) {
        this.disclosurePersonUnits = disclosurePersonUnits;
    }


}
