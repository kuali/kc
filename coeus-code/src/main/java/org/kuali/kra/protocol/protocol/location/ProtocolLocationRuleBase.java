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
package org.kuali.kra.protocol.protocol.location;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.org.OrganizationService;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.ProtocolDocumentBase;

public abstract class ProtocolLocationRuleBase extends KcTransactionalDocumentRuleBase implements AddProtocolLocationRule {

    private static final String ERROR_PROPERTY_ORGANIZATION_ID = "protocolHelper.newProtocolLocation.organizationId"; 
    private static final String ERROR_PROPERTY_ORGANIZATION_TYPE_CODE = "protocolHelper.newProtocolLocation.protocolOrganizationTypeCode"; 
    
    /**
     * Don't allow protocol location with an invalid organization id, organization type code, duplicate organization id
     * @see org.kuali.kra.protocol.protocol.location.AddProtocolLocationRule#processAddProtocolLocationBusinessRules(org.kuali.kra.protocol.protocol.location.AddProtocolLocationEvent)
     */
    public boolean processAddProtocolLocationBusinessRules(AddProtocolLocationEvent addProtocolLocationEvent) {
        boolean isValid = true;
        /**
         * check organization id 
         */
        String organizationId = addProtocolLocationEvent.getProtocolLocation().getOrganizationId();
        if (StringUtils.isBlank(organizationId)) {
            isValid = false;
            reportError(ERROR_PROPERTY_ORGANIZATION_ID, KeyConstants.ERROR_PROTOCOL_LOCATION_ORGANIZATION_ID_NOT_FOUND);
        }
        else if (isInvalidOrganizationId(organizationId)) {
            isValid = false;
            reportError(ERROR_PROPERTY_ORGANIZATION_ID, KeyConstants.ERROR_PROTOCOL_LOCATION_ORGANIZATION_ID_INVALID);
        }
        else if (isDuplicate((ProtocolDocumentBase)addProtocolLocationEvent.getDocument(), organizationId)) {
            isValid = false;
            reportError(ERROR_PROPERTY_ORGANIZATION_ID, KeyConstants.ERROR_PROTOCOL_LOCATION_ORGANIZATION_ID_DUPLICATE);
        }

        /**
         * check organization type code 
         */
        String organizationTypeCode = addProtocolLocationEvent.getProtocolLocation().getProtocolOrganizationTypeCode();
        if (StringUtils.isBlank(organizationTypeCode)) {
            isValid = false;
            reportError(ERROR_PROPERTY_ORGANIZATION_TYPE_CODE, KeyConstants.ERROR_PROTOCOL_LOCATION_ORGANIZATION_TYPE_CODE_NOT_FOUND);
        }
        return isValid;
    }
    
 
    /**
     * Check if organization id is invalid.  
     * Connect OrganizationService to fetch organization name for input organization id
     * If a valid name is returned, organizationId is valid; otherwise it is invalid.
     * 
     * @param organizationId
     * @return true if invalid; false if valid
     */
    private boolean isInvalidOrganizationId(String organizationId) {
        boolean organizationIdInvalid = false;
        OrganizationService organizationService = KcServiceLocator.getService(OrganizationService.class);
        Organization organization = organizationService.getOrganization(organizationId);
        if(organization == null) {
            organizationIdInvalid = true;
        }
        return organizationIdInvalid;
    }

    /**
     * Check if new organization id already exists in the list.
     * 
     * @param document - protocol document
     * @param organizationId
     * @return true if it is a duplicate; otherwise false
     */
    private boolean isDuplicate(ProtocolDocumentBase document, String organizationId) {
        for (ProtocolLocationBase protocolLocation : document.getProtocol().getProtocolLocations()) {
            if (StringUtils.equalsIgnoreCase(protocolLocation.getOrganizationId(), organizationId)) {
                return true;
            }
        }
        return false;        
    }

}
