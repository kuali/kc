/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.award;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.award.commitments.AwardCostShareRuleEvent;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.AwardSponsorTerm;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * AwardSponsorTermRuleEvent class for rule processing.
 */
public class AwardSponsorTermRuleEvent extends KcDocumentEventBase {
    
    private static final Log LOG = LogFactory.getLog(AwardCostShareRuleEvent.class);
    private AwardSponsorTerm awardSponsorTerm;
    private String sponsorTermCode;
    private int sponsorTermTypeCode;

    /**
     * Constructs a AwardSponsorTermRuleEvent.
     * @param errorPathPrefix
     * @param awardDocument
     * @param awardSponsorTerm
     * @param sponsorTermCode the sponsor term code from the HTTP request
     * @param sponsorTermTypeCode the index of the subpanel within the terms panel
     */
    public AwardSponsorTermRuleEvent(String errorPathPrefix, 
                                           AwardDocument awardDocument,
                                           AwardSponsorTerm awardSponsorTerm,
                                           String sponsorTermCode,
                                           int sponsorTermTypeCode) {
        super("Cost Share", errorPathPrefix, awardDocument);
        this.awardSponsorTerm = awardSponsorTerm;
        this.sponsorTermCode = sponsorTermCode;
        this.sponsorTermTypeCode = sponsorTermTypeCode;
    }
    
    /**
     * Convenience method to return an AwardDocument
     * @return
     */
    public AwardDocument getAwardDocument() {
        return (AwardDocument) getDocument();
    }
    
    /**
     * This method returns the sponsor term for validation
     * @return
     */
    public AwardSponsorTerm getAwardSponsorTermForValidation() {
        return awardSponsorTerm;
    }
    
    public String getSponsorTermCode() {
        return sponsorTermCode;
    }

    public int getSponsorTermTypeCode() {
        return sponsorTermTypeCode;
    }

    /**
     * @see org.kuali.coeus.sys.framework.rule.KcDocumentEventBase#logEvent()
     */
    @Override
    protected void logEvent() {
        LOG.info("Logging AwardSponsorTermRuleEvent");
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return AwardSponsorTermRule.class;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardSponsorTermRule)rule).processAddSponsorTermBusinessRules(this);
    }

}
