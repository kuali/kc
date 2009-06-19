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
package org.kuali.kra.irb.protocol.location;

import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.service.OrganizationService;


public class ProtocolLocationServiceImpl implements ProtocolLocationService {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolLocationServiceImpl.class);
    private OrganizationService organizationService;
    private static final String REFERENCE_PROTOCOL_ORGANIZATION_TYPE = "protocolOrganizationType";
    private static final String REFERENCE_ORGANIZATION = "organization";
    private static final String REFERENCE_ROLODEX = "rolodex";
    private static final String PROTOCOL_NUMBER = "0";
    private static final Integer SEQUENCE_NUMBER = 0;
    
    
    /**
     * @see org.kuali.kra.irb.protocol.location.ProtocolLocationService#addProtocolLocation(org.kuali.kra.irb.Protocol, org.kuali.kra.irb.protocol.location.ProtocolLocation)
     */
    public void addProtocolLocation(Protocol protocol, ProtocolLocation protocolLocation) {
        
        //TODO Framework problem of 2 saves
        protocolLocation.setProtocolNumber(PROTOCOL_NUMBER);
        protocolLocation.setSequenceNumber(SEQUENCE_NUMBER);
        protocolLocation.refreshReferenceObject(REFERENCE_PROTOCOL_ORGANIZATION_TYPE);
        protocolLocation.refreshReferenceObject(REFERENCE_ORGANIZATION);
        protocolLocation.setRolodexId(protocolLocation.getOrganization().getContactAddressId());
        protocolLocation.refreshReferenceObject(REFERENCE_ROLODEX);
        protocol.getProtocolLocations().add(protocolLocation);
    }

    /**
     * @see org.kuali.kra.irb.protocol.location.ProtocolLocationService#addDefaultProtocolLocation(org.kuali.kra.irb.Protocol)
     */
    public void addDefaultProtocolLocation(Protocol protocol) {
        if(protocol.getProtocolLocations().size() == 0) {
            ProtocolLocation protocolLocation = new ProtocolLocation();
            protocolLocation.setProtocolNumber(PROTOCOL_NUMBER);
            protocolLocation.setSequenceNumber(SEQUENCE_NUMBER);
            Organization organization = getOrganization(Constants.DEFAULT_PROTOCOL_ORGANIZATION_ID);
            protocolLocation.setOrganization(organization);
            protocolLocation.setOrganizationId(organization.getOrganizationId());
            protocolLocation.setRolodexId(organization.getContactAddressId());
            protocolLocation.setProtocolOrganizationTypeCode(Constants.DEFAULT_PROTOCOL_ORGANIZATION_TYPE_CODE);
            protocolLocation.refreshReferenceObject(REFERENCE_PROTOCOL_ORGANIZATION_TYPE);
            protocolLocation.refreshReferenceObject(REFERENCE_ROLODEX);
            protocol.getProtocolLocations().add(protocolLocation);
        }
    }
       
    /**
     * @see org.kuali.kra.irb.protocol.location.ProtocolLocationService#clearProtocolLocation(org.kuali.kra.irb.Protocol, int)
     */
    public void clearProtocolLocationAddress(Protocol protocol, int lineNumber) {

        protocol.getProtocolLocations().get(lineNumber).setRolodexId(new Integer(0));  
        protocol.getProtocolLocations().get(lineNumber).setRolodex(new Rolodex());  

    }

    /**
     * This method is to get default organization.
     * Method is linked to organization service.
     * @return Organization
     */
    private Organization getOrganization(String organizationId) {
        return organizationService.getOrganization(organizationId);
    }
    
    /**
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setOrganizationService(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

}
