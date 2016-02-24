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
package org.kuali.kra.award;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.service.SequenceAccessorService;

import java.text.DecimalFormat;

public class AwardNumberServiceImpl implements AwardNumberService {

    private SequenceAccessorService sequenceAccessorService;
    
    public AwardNumberServiceImpl() {
    }
    /**
     * Set the Sequence Accessor Service.
     * @param sequenceAccessorService the Sequence Accessor Service
     */
    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }

    
    @Override
    public String getNextAwardNumber() {
        Long nextAwardNumber = sequenceAccessorService.getNextAvailableSequenceNumber(Constants.AWARD_SEQUENCE_AWARD_NUMBER, Award.class);
        
        // Use Coeus' xxxxxx-yyy format for compatibility
        DecimalFormat formatter = new DecimalFormat("000000");        
        return formatter.format(nextAwardNumber) + "-00001";
    }
}
