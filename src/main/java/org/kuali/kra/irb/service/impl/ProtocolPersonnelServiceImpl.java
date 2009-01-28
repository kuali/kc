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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.bo.PersonTraining;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.bo.ProtocolPerson;
import org.kuali.kra.irb.bo.ProtocolUnit;
import org.kuali.kra.irb.service.ProtocolPersonnelService;


public class ProtocolPersonnelServiceImpl implements ProtocolPersonnelService {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolPersonnelServiceImpl.class);
    private BusinessObjectService businessObjectService;
    
    /**
     * @see org.kuali.kra.irb.service.ProtocolPersonnelService#addProtocolPerson(org.kuali.kra.irb.bo.Protocol, org.kuali.kra.irb.bo.ProtocolPerson)
     */
    public void addProtocolPerson(Protocol protocol, ProtocolPerson protocolPerson) {
        
        //TODO - How to handle protocol number and sequence number
        protocolPerson.setProtocolNumber("0");
        protocolPerson.setSequenceNumber(0);
        protocolPerson.setProtocolId(protocol.getProtocolId());
        //Refresh Person or Rolodex
        if(!StringUtils.isBlank(protocolPerson.getPersonId())) {
            protocolPerson.refreshReferenceObject("person");
        }else {
            protocolPerson.refreshReferenceObject("rolodex");
        }
        protocolPerson.refreshReferenceObject("protocolPersonRole");
        protocol.getProtocolPersons().add(protocolPerson);
    }

    /**
     * @see org.kuali.kra.irb.service.ProtocolPersonnelService#deleteProtocolPerson(org.kuali.kra.irb.bo.Protocol, int)
     */
    public void deleteProtocolPerson(Protocol protocol) {
        List<ProtocolPerson> deletedPersons = new ArrayList<ProtocolPerson>();
        for(ProtocolPerson protocolPerson : protocol.getProtocolPersons()) {
            if(protocolPerson.isDelete()) {
                deletedPersons.add(protocolPerson);
            }
        }
        protocol.getProtocolPersons().removeAll(deletedPersons);
    }
    
    /**
     * @see org.kuali.kra.irb.service.ProtocolPersonnelService#addProtocolPersonUnit(org.kuali.kra.irb.bo.Protocol, org.kuali.kra.irb.bo.ProtocolPerson)
     */
    public void addProtocolPersonUnit(List<ProtocolUnit> protocolPersonUnits, ProtocolPerson protocolPerson, int selectedPersonIndex) {
        ProtocolUnit newProtocolPersonUnit = protocolPersonUnits.get(selectedPersonIndex);
        newProtocolPersonUnit.setProtocolNumber(protocolPerson.getProtocolNumber());
        newProtocolPersonUnit.setProtocolPersonId(protocolPerson.getProtocolPersonId());

        //TODO - How to handle protocol number and sequence number
        newProtocolPersonUnit.setProtocolNumber("0");
        newProtocolPersonUnit.setSequenceNumber(0);
        
        newProtocolPersonUnit.refreshReferenceObject("unit");
        protocolPerson.addProtocolUnit(newProtocolPersonUnit);

        protocolPersonUnits.remove(selectedPersonIndex);
        protocolPersonUnits.add(selectedPersonIndex,new ProtocolUnit());
        
    }
    
    /**
     * @see org.kuali.kra.irb.service.ProtocolPersonnelService#deleteProtocolPersonUnit(java.util.List, org.kuali.kra.irb.bo.ProtocolPerson, int)
     */
    public void deleteProtocolPersonUnit(Protocol protocol, ProtocolPerson protocolPerson, int selectedPersonIndex, int lineNumber) {
        ProtocolPerson selectedPerson =  protocol.getProtocolPerson(selectedPersonIndex);
        ProtocolUnit protocolUnit = selectedPerson.getProtocolUnit(lineNumber);
        selectedPerson.removeProtocolUnit(protocolUnit);
    }
    
    /**
     * @see org.kuali.kra.irb.service.ProtocolPersonnelService#isPersonTrained(java.lang.String)
     */
    public boolean isPersonTrained(String personId) {
        boolean isTrained = false;
        if (StringUtils.isNotEmpty(personId)) {
            Map<String, Object> matchingKeys = new HashMap<String, Object>();
            matchingKeys.put("personId", personId);
            Collection<PersonTraining> personTrainings = businessObjectService.findMatching(PersonTraining.class, matchingKeys);
            if(personTrainings.size() > 0) {
                isTrained = true;
            }
        }
        return isTrained;
    }
    
    /**
     * Gets the businessObjectService attribute.
     * 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * 
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
