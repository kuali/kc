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

    @Override
    protected void logEvent() {
        LOG.info("Logging AwardSponsorTermRuleEvent");
    }

    @Override
    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return AwardSponsorTermRule.class;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((AwardSponsorTermRule)rule).processAddSponsorTermBusinessRules(this);
    }

}
