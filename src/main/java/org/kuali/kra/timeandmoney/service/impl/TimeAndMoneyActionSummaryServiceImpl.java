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
package org.kuali.kra.timeandmoney.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.accesslayer.LookupException;
import org.kuali.kra.timeandmoney.dao.TimeAndMoneyDao;
import org.kuali.kra.timeandmoney.history.TimeAndMoneyActionSummary;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyActionSummaryService;
import org.kuali.rice.kns.dao.impl.PlatformAwareDaoBaseOjb;
import org.kuali.rice.kns.service.BusinessObjectService;

public class TimeAndMoneyActionSummaryServiceImpl extends PlatformAwareDaoBaseOjb implements TimeAndMoneyActionSummaryService {
    
    private BusinessObjectService businessObjectService;
    private TimeAndMoneyDao timeAndMoneyDao;

    public void populateActionSummary(List<TimeAndMoneyActionSummary> timeAndMoneyActionSummaryItems, String documentNumber, String awardNumber, Integer sequenceNumber) throws LookupException, SQLException {
        
        timeAndMoneyDao.runScripts(timeAndMoneyActionSummaryItems, awardNumber);
        
    }

    /**
     * Gets the businessObjectService attribute. 
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    /**
     * Gets the timeAndMoneyDao attribute. 
     * @return Returns the timeAndMoneyDao.
     */
    public TimeAndMoneyDao getTimeAndMoneyDao() {
        return timeAndMoneyDao;
    }

    /**
     * Sets the timeAndMoneyDao attribute value.
     * @param timeAndMoneyDao The timeAndMoneyDao to set.
     */
    public void setTimeAndMoneyDao(TimeAndMoneyDao timeAndMoneyDao) {
        this.timeAndMoneyDao = timeAndMoneyDao;
    }

}
