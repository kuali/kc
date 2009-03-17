/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.lookup;
/**
 * 
 * There are so many constants needed in lookup.  Created this class for now.
 * reloacate these constants to where they belong later when we have a definite direction.
 */
public interface ProtocolLookupConstants {
    
    public static class Key {
        public static final String FUNDING_SOURCE_SPONSOR = "fundingSource1";
        public static final String FUNDING_SOURCE_UNIT = "fundingSource2";
        public static final String FUNDING_SOURCE_OTHER = "fundingSource3";
        public static final String FUNDING_SOURCE_PROPOSAL = "fundingSource4";
        public static final String FUNDING_SOURCE_INSTITUTE_PROPOSALE = "fundingSource5";
        public static final String FUNDING_SOURCE_AWARD = "fundingSource6";
        public static final String EMPLOYEE_PERSON = "personIdY";
        public static final String ROLODEX_PERSON = "personIdN";
        public static final String EMPLOYEE_INVESTIGATOR = "principalInvestigatorIdY";
        public static final String ROLODEX_INVESTIGATOR = "principalInvestigatorIdN";
    }
    public static class Property {

        public static final String ROLODEX_ID = "rolodexId";
        public static final String UNIT_NUMBER = "unitNumber";
        public static final String PROPOSAL_NUMBER = "proposalNumber";
        public static final String AWARD_NUMBER = "awardNumber";
        public static final String PERSON_ID = "personId";
        public static final String SPONSOR_CODE = "sponsorCode";
        public static final String PRINCIPAL_INVESTIGATOR_ID = "principalInvestigatorId";
        public static final String FUNDING_SOURCE = "fundingSource";
        public static final String FUNDING_SOURCE_TYPE_CODE = "fundingSourceTypeCode";
        public static final String RESEARCH_AREA_CODE = "researchAreaCode";
        public static final String PROTOCOL_ID = "protocolId";
        public static final String PERSON_EMPLOYEE_INDICATOR = "personEmployeeIndicator";
        public static final String INVESTIGATOR_EMPLOYEE_INDICATOR = "investigatorEmployeeIndicator";
        public static final String PERFORMING_ORGANIZATION_ID = "performingOrganizationId";
        public static final String PROTOCOL_PERSON_ROLE_ID = "protocolPersonRoleId";
    }
}
