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
package org.kuali.kra.common.permissions;

import java.util.List;
import java.util.Map;

/**
 * This class...
 */
public interface Permissionable {
    
    String PROPOSAL_KEY = "proposal";
    String AWARD_KEY = "award";
    String TIME_AND_MONEY_KEY = "timeandmoney";
    
    //these keys dont seem to be used now
    String AWARD_BUDGET_KEY = "awardbudget";
    String PROPOSAL_BUDGET_KEY = "proposalbudget";
    
    String PROTOCOL_KEY = "protocol";
    String COMMITTEE_KEY = "committee";
    String COMMITTEE_SCHEDULE_KEY="committeeSchedule";
    String PROTOCOL_ONLINE_REVIEW_KEY="protocolOnlineReview";
    
    String NEGOTIATION_KEY = "negotiation";
    String COI_DISCLOSURE_KEY = "coiDisclosure";
    
    String SPONSOR_HIREARCHY_KEY = "sponsorhirearchy";
    
    /**
     * 
     * This method returns the appropriate document number for implementing documents
     * For award it would be awardNumber and for PDD it would be proposal Number.
     * @return
     */
    String getDocumentNumberForPermission();
    
    /**
     * 
     * This method returns unique key for implementing document.
     * 
     * @return
     */
    String getDocumentKey();
    
    /**
     * 
     * This method gets all the role names for particular document. 
     * @return
     */
    List<String> getRoleNames();
    
    String getNamespace();
    
    String getLeadUnitNumber();
    
    String getDocumentRoleTypeCode();
    
    /**
     * Allows a permissionable to set additional qualified role attributes that may be needed by 
     * kim services to resolve the role members.
     * 
     * @param qualifiedRoleAttributes
     */
    void populateAdditionalQualifiedRoleAttributes( Map<String, String> qualifiedRoleAttributes );
    
    
    
}
