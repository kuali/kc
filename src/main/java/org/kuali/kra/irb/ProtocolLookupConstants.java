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
package org.kuali.kra.irb;
/**
 * 
 * There are so many constants needed in lookup.  Created this class for now.
 * TODO : reloacate these constants to where they belong later when we have a definite direction for constants.
 */
public interface ProtocolLookupConstants {
    
    /**
     * 
     * This class contains the property constants
     */
    public static class Property {

        public static final String ROLODEX_ID = "rolodexId";
        public static final String UNIT_NUMBER = "unitNumber";
        public static final String PERSON_ID = "personId";
        public static final String KEY_PERSON = "keyPerson";
        public static final String INVESTIGATOR = "investigator";
        public static final String FUNDING_SOURCE = "fundingSource";
        public static final String FUNDING_SOURCE_TYPE_CODE = "fundingSourceTypeCode";
        public static final String RESEARCH_AREA_CODE = "researchAreaCode";
        public static final String PROTOCOL_ID = "protocolId";
        public static final String PROTOCOL_TYPE_CODE = "protocolTypeCode";
        public static final String PROTOCOL_STATUS_CODE = "protocolStatusCode";
        public static final String PERFORMING_ORGANIZATION_ID = "performingOrganizationId";
        public static final String PROTOCOL_PERSON_ROLE_ID = "protocolPersonRoleId";
        public static final String PROTOCOL_ORGANIZATION_TYPE_CODE = "protocolOrganizationTypeCode";
        public static final String PERFORMING_ORGANIZATION_CODE = "1";
        public static final String PERSON_NAME = "personName";
        public static final String ORGANIZATION_ID = "organizationId";
        public static final String LEAD_UNIT_NUMBER = "leadUnitNumber";
    }
}
