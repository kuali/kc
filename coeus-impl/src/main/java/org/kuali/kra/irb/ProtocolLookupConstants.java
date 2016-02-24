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
        public static final String PROTOCOL_PERSON_ID = "protocolPersonId";
    }
}
