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
package org.kuali.kra.award.home.approvedsubawards;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class...
 */
public class AwardApprovedSubawardRuleEvent extends KraDocumentEventBase {

    private static final Log LOG = LogFactory.getLog(AwardApprovedSubawardRuleEvent.class);
    
    private AwardApprovedSubaward awardApprovedSubaward;
    private List<AwardApprovedSubaward> awardApprovedSubawards;
    

    /**
     * Constructs a AwardApprovedSubawardRuleEvent.java.
     * @param errorPathPrefix
     * @param awardDocument
     * @param awardApprovedSubaward
     * @param awardApprovedSubawards
     */
    public AwardApprovedSubawardRuleEvent(String errorPathPrefix, 
                                           AwardDocument awardDocument,
                                           AwardApprovedSubaward awardApprovedSubaward,
                                           List<AwardApprovedSubaward> awardApprovedSubawards) {
        super("ApprovedSubaward", errorPathPrefix, awardDocument);
        this.awardApprovedSubaward = awardApprovedSubaward;
        this.awardApprovedSubawards = awardApprovedSubawards;
    }
    
    /**
     * Convenience method to return an AwardDocument
     * @return
     */
    public AwardDocument getAwardDocument() {
        return (AwardDocument) getDocument();
    }
    
    /**
     * This method returns the equipment item for validation
     * @return
     */
    public AwardApprovedSubaward getApprovedSubaward() {
        return awardApprovedSubaward;
    }
    
    
    class SubAwardComparator implements Comparator
    {    
        public int compare(Object kv1, Object kv2 )
        {    
            try
            {
                String orgName1 = ((AwardApprovedSubaward)kv1).getOrganizationName();
                String orgName2 = ((AwardApprovedSubaward)kv2).getOrganizationName();
                if (orgName1 == null)
                {
                    orgName1 = "";
                }
                if (orgName2 == null)
                {
                    orgName2 = "";
                }
                return orgName1.compareTo(orgName2);  
            }
            catch (Exception e)
            {
                return 0;
            }
        }
        
    }
    
    /**
     * Gets the awardApprovedSubawards attribute. 
     * @return Returns the awardApprovedSubawards.
     */
    public List<AwardApprovedSubaward> getAwardApprovedSubawards() {
        
        Collections.sort(awardApprovedSubawards, new SubAwardComparator());
        return awardApprovedSubawards;
    }

    
    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#logEvent()
     */
    @Override
    protected void logEvent() {
        // TODO Auto-generated method stub

    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#getRuleInterfaceClass()
     */
    public Class getRuleInterfaceClass() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @see org.kuali.core.rule.event.KualiDocumentEvent#invokeRuleMethod(org.kuali.core.rule.BusinessRule)
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        // TODO Auto-generated method stub
        return false;
    }

}
