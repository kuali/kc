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
package org.kuali.coeus.common.impl.motd;

import org.apache.commons.collections4.ListUtils;
import org.kuali.coeus.common.framework.motd.MessageOfTheDay;
import org.kuali.coeus.common.framework.motd.MessageOfTheDayService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("messageOfTheDayService")
public class MessageOfTheDayServiceImpl implements MessageOfTheDayService {

    private static final String DISPLAY_ORDER = "displayOrder";
    private static final String ACTIVE = "active";

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Override
    public List<MessageOfTheDay> getMessagesOfTheDay() {
        final List<MessageOfTheDay> results = new ArrayList<MessageOfTheDay>( businessObjectService.findMatchingOrderBy(MessageOfTheDay.class,
                Collections.singletonMap(ACTIVE, "Y"),
                DISPLAY_ORDER, 
                true ));
        
        return ListUtils.emptyIfNull(results);
    }

    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    public void setBusinessObjectService( BusinessObjectService businessObjectService ) {
        this.businessObjectService = businessObjectService;
    }
    
}
