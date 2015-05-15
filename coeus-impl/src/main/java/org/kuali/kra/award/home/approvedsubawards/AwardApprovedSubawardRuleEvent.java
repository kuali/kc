/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.award.home.approvedsubawards;

import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.krad.rules.rule.BusinessRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class AwardApprovedSubawardRuleEvent extends KcDocumentEventBase {
    
    private AwardApprovedSubaward awardApprovedSubaward;
    private List<AwardApprovedSubaward> awardApprovedSubawards;
    


    public AwardApprovedSubawardRuleEvent(String errorPathPrefix, 
                                           AwardDocument awardDocument,
                                           AwardApprovedSubaward awardApprovedSubaward,
                                           List<AwardApprovedSubaward> awardApprovedSubawards) {
        super("ApprovedSubaward", errorPathPrefix, awardDocument);
        this.awardApprovedSubaward = awardApprovedSubaward;
        this.awardApprovedSubawards = new ArrayList<>(awardApprovedSubawards);
    }
    

    public AwardDocument getAwardDocument() {
        return (AwardDocument) getDocument();
    }
    

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

    public List<AwardApprovedSubaward> getAwardApprovedSubawards() {
        
        Collections.sort(awardApprovedSubawards, new SubAwardComparator());
        return awardApprovedSubawards;
    }

    
    @Override
    protected void logEvent() {


    }

    @Override
    public Class getRuleInterfaceClass() {

        return null;
    }

    @Override
    public boolean invokeRuleMethod(BusinessRule rule) {

        return false;
    }

}
