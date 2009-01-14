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
package org.kuali.kra.irb.service.impl;

import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolLocation;
import org.kuali.kra.irb.service.ProtocolLocationService;
import org.kuali.kra.service.OrganizationService;


public class ProtocolLocationServiceImpl implements ProtocolLocationService {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolLocationServiceImpl.class);
    
    /**
     * @see org.kuali.kra.irb.service.ProtocolLocationService#addProtocolLocation(org.kuali.kra.irb.bo.Protocol, org.kuali.kra.irb.bo.ProtocolLocation)
     */
    public void addProtocolLocation(Protocol protocol, ProtocolLocation protocolLocation) {
        
        //TODO Framework problem of 2 saves
        protocolLocation.setProtocolNumber("0");
        protocolLocation.setSequenceNumber(0);
        protocolLocation.refreshReferenceObject("protocolOrganizationType");
        protocolLocation.refreshReferenceObject("organization");
        protocolLocation.setRolodexId(protocolLocation.getOrganization().getContactAddressId());
        protocolLocation.refreshReferenceObject("rolodex");
        protocol.getProtocolLocations().add(protocolLocation);
    }

    /**
     * @see org.kuali.kra.irb.service.ProtocolLocationService#addDefaultProtocolLocation(org.kuali.kra.irb.bo.Protocol)
     */
    public void addDefaultProtocolLocation(Protocol protocol) {
        if(protocol.getProtocolLocations().size() == 0) {
            ProtocolLocation protocolLocation = new ProtocolLocation();
            protocolLocation.setProtocolNumber("0");
            protocolLocation.setSequenceNumber(0);
            Organization organization = getOrganization(Constants.DEFAULT_PROTOCOL_ORGANIZATION_ID);
            protocolLocation.setOrganization(organization);
            protocolLocation.setOrganizationId(organization.getOrganizationId());
            protocolLocation.setRolodexId(organization.getContactAddressId());
            protocolLocation.setProtocolOrganizationTypeCode(Constants.DEFAULT_PROTOCOL_ORGANIZATION_TYPE_CODE);
            protocolLocation.refreshReferenceObject("protocolOrganizationType");
            protocolLocation.refreshReferenceObject("rolodex");
            protocol.getProtocolLocations().add(protocolLocation);
        }
    }
    
    /**
     * @see org.kuali.kra.irb.service.ProtocolLocationService#deleteProtocolLocation(org.kuali.kra.irb.bo.Protocol, int)
     */
    public void deleteProtocolLocation(Protocol protocol, int lineNumber) {

        protocol.getProtocolLocations().remove(lineNumber);  

    }
   
    /**
     * @see org.kuali.kra.irb.service.ProtocolLocationService#clearProtocolLocation(org.kuali.kra.irb.bo.Protocol, int)
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
        OrganizationService organizationService = KraServiceLocator.getService(OrganizationService.class);
        return organizationService.getOrganization(organizationId);
    }
    
}
