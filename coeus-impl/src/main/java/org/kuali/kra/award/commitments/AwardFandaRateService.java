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
package org.kuali.kra.award.commitments;

import org.kuali.kra.award.home.ValidRates;

import java.util.List;


/**
 * 
 * This is the AwardFandaRateService interface.
 */
public interface AwardFandaRateService {
    
    /**
     * 
     * This method returns the start and end dates for a given fiscal year.
     * The Start Date for a fiscal year would be 07/01/<fiscalYear-1> and 
     * End Date would be 06/30/<fiscalYear> 
     * @param fiscalYear
     * @return
     */
    public List<String> getStartAndEndDatesBasedOnFiscalYear(String fiscalYear);
    
    public List<ValidRates> getValidRates(AwardFandaRate awardFandaRate);
    
}
