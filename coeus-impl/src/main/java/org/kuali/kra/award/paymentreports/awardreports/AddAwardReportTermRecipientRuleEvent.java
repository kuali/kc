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
package org.kuali.kra.award.paymentreports.awardreports;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class is for rule validation when adding new item
 */
public class AddAwardReportTermRecipientRuleEvent extends AwardReportTermRecipientRuleEvent {

    /**
     * 
     * Constructs a AddAwardReportTermRecipientRuleEvent.java.
     * @param errorPathPrefix
     * @param awardDocument
     * @param award
     * @param awardReportTermRecipientItem
     */
    public AddAwardReportTermRecipientRuleEvent(String errorPathPrefix, AwardDocument awardDocument, Award award, AwardReportTerm parentAwardReportTerm, AwardReportTermRecipient awardReportTermRecipientItem) {
        super(errorPathPrefix, awardDocument, award, parentAwardReportTerm, awardReportTermRecipientItem);
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardReportTermRecipientRule)rule).processAddAwardReportTermRecipientBusinessRules(this);
    }
}
