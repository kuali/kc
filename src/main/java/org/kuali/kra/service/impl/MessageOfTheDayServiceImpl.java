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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.bo.MessageOfTheDay;
import org.kuali.kra.service.MessageOfTheDayService;
import org.kuali.kra.service.ServiceHelper;
import org.kuali.rice.krad.service.BusinessObjectService;

public class MessageOfTheDayServiceImpl implements MessageOfTheDayService {

    private transient BusinessObjectService businessObjectService;
    private static String DISPLAY_ORDER = "displayOrder";
    private static String ACTIVE = "active";
    
    
    /**
     * @see org.kuali.kra.service.MessageOfTheDayService#getMessagesOfTheDay()
     */
    @SuppressWarnings("unchecked")
    public List<MessageOfTheDay> getMessagesOfTheDay() {
        List<MessageOfTheDay> results = new ArrayList<MessageOfTheDay>( businessObjectService.findMatchingOrderBy(MessageOfTheDay.class,
                ServiceHelper.getInstance().buildCriteriaMap(ACTIVE, "Y"),
                DISPLAY_ORDER, 
                true ));
        
        return results;
    }

    
    
    @SuppressWarnings("unused")
    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    @SuppressWarnings("unused")
    public void setBusinessObjectService( BusinessObjectService businessObjectService ) {
        this.businessObjectService = businessObjectService;
    }
    
}
