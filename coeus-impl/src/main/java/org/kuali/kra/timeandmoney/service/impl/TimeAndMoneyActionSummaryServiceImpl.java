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
package org.kuali.kra.timeandmoney.service.impl;

import org.apache.ojb.broker.accesslayer.LookupException;
import org.kuali.kra.timeandmoney.dao.TimeAndMoneyDao;
import org.kuali.kra.timeandmoney.history.TimeAndMoneyActionSummary;
import org.kuali.kra.timeandmoney.service.TimeAndMoneyActionSummaryService;
import org.kuali.rice.core.framework.persistence.ojb.dao.PlatformAwareDaoBaseOjb;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.sql.SQLException;
import java.util.List;

public class TimeAndMoneyActionSummaryServiceImpl extends PlatformAwareDaoBaseOjb implements TimeAndMoneyActionSummaryService {
    
    private BusinessObjectService businessObjectService;
    private TimeAndMoneyDao timeAndMoneyDao;

    public List<TimeAndMoneyActionSummary> populateActionSummary(String awardNumber) {
        return timeAndMoneyDao.buildTimeAndMoneyActionSummaryForAward(awardNumber);
        
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
