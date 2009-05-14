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
package org.kuali.kra.irb.personnel;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.PersonTraining;
import org.kuali.rice.kns.service.BusinessObjectService;


public class ProtocolPersonTrainingServiceImpl implements ProtocolPersonTrainingService {
    
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProtocolPersonTrainingServiceImpl.class);
    private BusinessObjectService businessObjectService;
    private static final String PERSON_ID_FIELD = "personId";
    
    
    /**
     * @see org.kuali.kra.irb.personnel.ProtocolPersonTrainingService#isPersonTrained(java.lang.String)
     */
    public void updatePersonTrained(List<ProtocolPerson> protocolPersons) {
        for(ProtocolPerson protocolPerson : protocolPersons) {
            setTrainedFlag(protocolPerson);
        }
    }
    
    /**
     * @see org.kuali.kra.irb.personnel.ProtocolPersonTrainingService#setTrainedFlag(org.kuali.kra.irb.personnel.ProtocolPerson)
     */
    public void setTrainedFlag(ProtocolPerson protocolPerson) {
        if (StringUtils.isNotEmpty(protocolPerson.getPersonId())) {
            Map<String, Object> matchingKeys = new HashMap<String, Object>();
            matchingKeys.put(PERSON_ID_FIELD, protocolPerson.getPersonId());
            Collection<PersonTraining> personTrainings = getBusinessObjectService().findMatching(PersonTraining.class, matchingKeys);
            protocolPerson.setTrained(personTrainings.size() > 0 ? true : false);
        }
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
