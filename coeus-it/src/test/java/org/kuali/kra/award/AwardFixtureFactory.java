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

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Factory class for Award test fixtures
 *
 * During the course of normal development and maintenance, developers should add their BO fixture creation methods here. Then any other developer can use the
 * methods to quickly create fixtures they know can meet the validation rules needed to save the BO.
 *
 */
public class AwardFixtureFactory {
    public static final String UNIVERSITY_UNIT_NUMBER = "000001";
    private static final String YES_FLAG = "Y";

    /**
     * This method creates a sample Award with all required fields populated. Designed to be used in tests, it meets the general purpose of creating any sample,
     * savable Award without having to worry that not all required fields have been populated.
     *
     * Developers: As required field definitions change, you should keep this method updated.
     *
     * @return
     */
    public static Award createAwardFixture() {
        Award award = new Award();
        award.setAwardNumber(Award.DEFAULT_AWARD_NUMBER);
        award.setActivityTypeCode("1");
        award.setApprovedEquipmentIndicator(YES_FLAG);
        award.setApprovedForeignTripIndicator(YES_FLAG);
        award.setAwardTransactionTypeCode(1);
        award.setAwardTypeCode(1);
        award.setCostSharingIndicator(YES_FLAG);
        award.setIdcIndicator(YES_FLAG);
        award.setPaymentScheduleIndicator(YES_FLAG);
        award.setProjectEndDate(new Date(new GregorianCalendar(2010, Calendar.DECEMBER, 31).getTimeInMillis()));
        award.setScienceCodeIndicator(YES_FLAG);
        award.setSequenceNumber(1);
        award.setSpecialReviewIndicator(YES_FLAG);
        award.setStatusCode(1);
        award.setSponsorCode("000162");
        award.setSubContractIndicator(YES_FLAG);
        award.setTitle("Sample Award Title");
        award.setTransferSponsorIndicator(YES_FLAG);
        award.setUnitNumber(UNIVERSITY_UNIT_NUMBER);
        return award;
    }
}
