/*
 * Copyright 2005-2010 The Kuali Foundation
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
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;


public class ProtocolPersonTrainingServiceImpl implements ProtocolPersonTrainingService {
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProtocolPersonTrainingServiceImpl.class);
    private static final String PERSON_ID_FIELD = "personId";
    private static final String ACTIVE_FLAG_FIELD = "active";
    private static final String FOLLOWUP_DATE_FIELD = "followupDate";
    private static final String IS_ACTIVE_VALUE = "Y";
    private BusinessObjectService businessObjectService;
    private DateTimeService dateTimeService;
    
    
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
        protocolPerson.setTrained(isTrained(protocolPerson.getPersonId()));
    }
    
    /**
     * This method verifies that the person is trained.
     * @param personId
     * @return true if the person is trained, false otherwise.
     */
    @SuppressWarnings("unchecked")
    private boolean isTrained(String personId) {
        if (StringUtils.isNotEmpty(personId)) {
            Map<String, Object> matchingKeys = new HashMap<String, Object>();
            matchingKeys.put(PERSON_ID_FIELD, personId);
            matchingKeys.put(ACTIVE_FLAG_FIELD, IS_ACTIVE_VALUE);
            Collection<PersonTraining> personTrainings = getBusinessObjectService().findMatchingOrderBy(PersonTraining.class, matchingKeys, FOLLOWUP_DATE_FIELD, false);
            for (PersonTraining personTraining : personTrainings) {
                if (getDateTimeService().getCurrentDate().before(personTraining.getFollowupDate())) {
                    return true;
                }
            }
        }
        return false;        
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
    
    /**
     * Gets the dateTimeService attribute.
     * 
     * @return Returns the dateTimeService.
     */
    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    /**
     * Sets the dateTimeService attribute value.
     * 
     * @param dateTimeService The dateTimeService to set.
     */
    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }
    
}
