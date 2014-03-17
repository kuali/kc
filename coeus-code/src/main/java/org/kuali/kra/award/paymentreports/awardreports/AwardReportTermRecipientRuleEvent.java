/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award.paymentreports.awardreports;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This the AwardPaymentScheduleRuleEvent
 */
public class AwardReportTermRecipientRuleEvent extends KcDocumentEventBase {
    private static final Log LOG = LogFactory.getLog(AwardReportTermRecipientRuleEvent.class);
    
    private Award award;
    private AwardReportTerm parentAwardReportTerm;
    private AwardReportTermRecipient awardReportTermRecipientItem;
    
    public AwardReportTermRecipientRuleEvent(String errorPathPrefix, 
                                            AwardDocument awardDocument,
                                            Award award,
                                            AwardReportTerm parentAwardReportTerm,
                                            AwardReportTermRecipient awardReportTermRecipientItem) {
        super("Payment Schedule item", errorPathPrefix, awardDocument);
        this.award = award;
        this.awardReportTermRecipientItem = awardReportTermRecipientItem;
        this.parentAwardReportTerm = parentAwardReportTerm;
    }

    /**
     * Convenience method to return an Award
     * @return
     */
    public Award getAward() {
        return award;
    }
    
    /**
     * Convenience method to return an AwardDocument
     * @return
     */
    public AwardDocument getAwardDocument() {
        return (AwardDocument) getDocument();
    }
    
    /**
     * This method returns the payment schedule item for validation
     * @return
     */
    public AwardReportTermRecipient getAwardReportTermRecipientItemForValidation() {
        return awardReportTermRecipientItem;
    }   

    @Override
    protected void logEvent() {
        LOG.info("Logging AwardPaymentScheduleRuleEvent");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return AwardReportTermRecipientRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardReportTermRecipientRule)rule).processAwardReportTermRecipientBusinessRules(this);
    }

    /**
     * Gets the parentAwardReportTerm attribute. 
     * @return Returns the parentAwardReportTerm.
     */
    public AwardReportTerm getParentAwardReportTerm() {
        return parentAwardReportTerm;
    }

    /**
     * Sets the parentAwardReportTerm attribute value.
     * @param parentAwardReportTerm The parentAwardReportTerm to set.
     */
    public void setParentAwardReportTerm(AwardReportTerm parentAwardReportTerm) {
        this.parentAwardReportTerm = parentAwardReportTerm;
    }   
}
