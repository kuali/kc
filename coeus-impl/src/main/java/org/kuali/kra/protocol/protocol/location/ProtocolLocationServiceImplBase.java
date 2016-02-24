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

import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.org.OrganizationService;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.protocol.ProtocolBase;


public abstract class ProtocolLocationServiceImplBase implements ProtocolLocationService {
    
    private OrganizationService organizationService;
    private static final String REFERENCE_PROTOCOL_ORGANIZATION_TYPE = "protocolOrganizationType";
    private static final String REFERENCE_ORGANIZATION = "organization";
    private static final String REFERENCE_ROLODEX = "rolodex";
    private static final String PROTOCOL_NUMBER = "0";
    private static final Integer SEQUENCE_NUMBER = 0;
    
    
    @Override
    public void addProtocolLocation(ProtocolBase protocol, ProtocolLocationBase protocolLocation) {
        
        //TODO Framework problem of 2 saves
        protocolLocation.setProtocolNumber(PROTOCOL_NUMBER);
        protocolLocation.setSequenceNumber(SEQUENCE_NUMBER);
        protocolLocation.refreshReferenceObject(REFERENCE_PROTOCOL_ORGANIZATION_TYPE);
        protocolLocation.refreshReferenceObject(REFERENCE_ORGANIZATION);
        protocolLocation.setRolodexId(protocolLocation.getOrganization().getContactAddressId());
        protocolLocation.refreshReferenceObject(REFERENCE_ROLODEX);
        protocol.getProtocolLocations().add(protocolLocation);
    }

    @Override
    public void addDefaultProtocolLocation(ProtocolBase protocol) {
        if(protocol.getProtocolLocations().size() == 0) {
            ProtocolLocationBase protocolLocation = getNewProtocolLocationInstanceHook();
            protocolLocation.setProtocolNumber(PROTOCOL_NUMBER);
            protocolLocation.setSequenceNumber(SEQUENCE_NUMBER);
            Organization organization = getOrganization(getDefaultProtocolOrganizationIdHook());
            protocolLocation.setOrganization(organization);
            protocolLocation.setOrganizationId(organization.getOrganizationId());
            protocolLocation.setRolodexId(organization.getContactAddressId());
            protocolLocation.setProtocolOrganizationTypeCode(getDefaultProtocolOrganizationTypeCodeHook());
            protocolLocation.refreshReferenceObject(REFERENCE_PROTOCOL_ORGANIZATION_TYPE);
            protocolLocation.refreshReferenceObject(REFERENCE_ROLODEX);
            protocol.getProtocolLocations().add(protocolLocation);
        }
    }
       
    protected abstract String getDefaultProtocolOrganizationIdHook();

    protected abstract String getDefaultProtocolOrganizationTypeCodeHook();

    protected abstract ProtocolLocationBase getNewProtocolLocationInstanceHook();

    @Override
    public void clearProtocolLocationAddress(ProtocolBase protocol, int lineNumber) {

        protocol.getProtocolLocations().get(lineNumber).setRolodexId(new Integer(0));  
        protocol.getProtocolLocations().get(lineNumber).setRolodex(new Rolodex());  

    }

    /**
     * This method is to get default organization.
     * Method is linked to organization service.
     * @return Organization
     */
    protected Organization getOrganization(String organizationId) {
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
