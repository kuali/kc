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
