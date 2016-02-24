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
