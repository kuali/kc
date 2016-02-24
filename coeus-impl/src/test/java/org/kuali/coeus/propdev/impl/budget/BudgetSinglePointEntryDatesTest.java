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
package org.kuali.coeus.propdev.impl.budget;

import junit.framework.Assert;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.personnel.BudgetPersonnelDetails;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class BudgetSinglePointEntryDatesTest {

    @Test
    public void peopleInGroupWithSameDates() throws ParseException {
        BudgetLineItem lineItem = new BudgetLineItem();
        Calendar cal = Calendar.getInstance();
        lineItem.setStartDate(getDate(2015, cal.JULY, 1));
        lineItem.setEndDate(getDate(2015, cal.NOVEMBER, 1));

        BudgetPersonnelDetails detail1 = new BudgetPersonnelDetails();
        detail1.setStartDate(getDate(2015, cal.JANUARY, 1));
        detail1.setEndDate(getDate(2015, cal.FEBRUARY, 1));

        BudgetPersonnelDetails detail2 = new BudgetPersonnelDetails();
        detail2.setStartDate(getDate(2015, cal.JANUARY, 1));
        detail2.setEndDate(getDate(2015, cal.FEBRUARY, 1));

        lineItem.getBudgetPersonnelDetailsList().add(detail1);
        lineItem.getBudgetPersonnelDetailsList().add(detail2);

        ProposalBudgetForm form = new ProposalBudgetForm();
        Date startDate = form.getLineItemStartDate(lineItem);
        Date endDate = form.getLineItemEndDate(lineItem);

        Assert.assertTrue(startDate.compareTo(getDate(2015, cal.JANUARY, 1)) == 0);
        Assert.assertTrue(endDate.compareTo(getDate(2015, cal.FEBRUARY, 1)) == 0);

    }

    @Test
    public void peopleInGroupWithDifferentStartDates() throws ParseException {
        BudgetLineItem lineItem = new BudgetLineItem();
        Calendar cal = Calendar.getInstance();
        lineItem.setStartDate(getDate(2015, cal.JULY, 1));
        lineItem.setEndDate(getDate(2015, cal.NOVEMBER, 1));

        BudgetPersonnelDetails detail1 = new BudgetPersonnelDetails();
        detail1.setStartDate(getDate(2015, cal.JANUARY, 1));
        detail1.setEndDate(getDate(2015, cal.MARCH, 1));

        BudgetPersonnelDetails detail2 = new BudgetPersonnelDetails();
        detail2.setStartDate(getDate(2015, cal.JANUARY, 2));
        detail2.setEndDate(getDate(2015, cal.MARCH, 1));

        lineItem.getBudgetPersonnelDetailsList().add(detail1);
        lineItem.getBudgetPersonnelDetailsList().add(detail2);

        ProposalBudgetForm form = new ProposalBudgetForm();
        Date startDate = form.getLineItemStartDate(lineItem);
        Date endDate = form.getLineItemEndDate(lineItem);

        Assert.assertTrue(startDate.compareTo(getDate(2015, cal.JULY, 1)) == 0);
        Assert.assertTrue(endDate.compareTo(getDate(2015, cal.MARCH, 1)) == 0);

    }

    @Test
    public void peopleInGroupWithDifferentEndDates() throws ParseException {
        BudgetLineItem lineItem = new BudgetLineItem();
        Calendar cal = Calendar.getInstance();
        lineItem.setStartDate(getDate(2015, cal.JULY, 1));
        lineItem.setEndDate(getDate(2015, cal.NOVEMBER, 1));

        BudgetPersonnelDetails detail1 = new BudgetPersonnelDetails();
        detail1.setStartDate(getDate(2015, cal.JANUARY, 1));
        detail1.setEndDate(getDate(2015, cal.MARCH, 1));

        BudgetPersonnelDetails detail2 = new BudgetPersonnelDetails();
        detail2.setStartDate(getDate(2015, cal.JANUARY, 1));
        detail2.setEndDate(getDate(2015, cal.MARCH, 2));

        lineItem.getBudgetPersonnelDetailsList().add(detail1);
        lineItem.getBudgetPersonnelDetailsList().add(detail2);

        ProposalBudgetForm form = new ProposalBudgetForm();
        Date startDate = form.getLineItemStartDate(lineItem);
        Date endDate = form.getLineItemEndDate(lineItem);

        Assert.assertTrue(startDate.compareTo(getDate(2015, cal.JANUARY, 1)) == 0);
        Assert.assertTrue(endDate.compareTo(getDate(2015, cal.NOVEMBER, 1)) == 0);

    }

    @Test
    public void peopleInGroupWithDifferentStartAndEndDates() throws ParseException {
        BudgetLineItem lineItem = new BudgetLineItem();
        Calendar cal = Calendar.getInstance();
        lineItem.setStartDate(getDate(2015, cal.JULY, 1));
        lineItem.setEndDate(getDate(2015, cal.NOVEMBER, 1));

        BudgetPersonnelDetails detail1 = new BudgetPersonnelDetails();
        detail1.setStartDate(getDate(2015, cal.JANUARY, 1));
        detail1.setEndDate(getDate(2015, cal.MARCH, 1));

        BudgetPersonnelDetails detail2 = new BudgetPersonnelDetails();
        detail2.setStartDate(getDate(2015, cal.JANUARY, 2));
        detail2.setEndDate(getDate(2015, cal.MARCH, 2));

        lineItem.getBudgetPersonnelDetailsList().add(detail1);
        lineItem.getBudgetPersonnelDetailsList().add(detail2);

        ProposalBudgetForm form = new ProposalBudgetForm();
        Date startDate = form.getLineItemStartDate(lineItem);
        Date endDate = form.getLineItemEndDate(lineItem);

        Assert.assertTrue(startDate.compareTo(getDate(2015, cal.JULY, 1)) == 0);
        Assert.assertTrue(endDate.compareTo(getDate(2015, cal.NOVEMBER, 1)) == 0);

    }

    @Test
    public void peopleInGroupWithNullStartAndEndDates() throws ParseException {
        BudgetLineItem lineItem = new BudgetLineItem();
        Calendar cal = Calendar.getInstance();
        lineItem.setStartDate(getDate(2015, cal.JULY, 1));
        lineItem.setEndDate(getDate(2015, cal.NOVEMBER, 1));

        BudgetPersonnelDetails detail1 = new BudgetPersonnelDetails();
        detail1.setStartDate(null);
        detail1.setEndDate(null);

        BudgetPersonnelDetails detail2 = new BudgetPersonnelDetails();
        detail2.setStartDate(null);
        detail2.setEndDate(null);

        lineItem.getBudgetPersonnelDetailsList().add(detail1);
        lineItem.getBudgetPersonnelDetailsList().add(detail2);

        ProposalBudgetForm form = new ProposalBudgetForm();
        Date startDate = form.getLineItemStartDate(lineItem);
        Date endDate = form.getLineItemEndDate(lineItem);

        Assert.assertTrue(getDate(2015, cal.JULY, 1).compareTo(startDate) == 0);
        Assert.assertTrue(getDate(2015, cal.NOVEMBER, 1).compareTo(endDate) == 0);

    }

    @Test
    public void testNonPersonnelLineItems() throws ParseException {
        BudgetLineItem lineItem = new BudgetLineItem();
        Calendar cal = Calendar.getInstance();
        lineItem.setStartDate(getDate(2015, cal.JULY, 1));
        lineItem.setEndDate(getDate(2015, cal.NOVEMBER, 1));

        ProposalBudgetForm form = new ProposalBudgetForm();
        Date startDate = form.getLineItemStartDate(lineItem);
        Date endDate = form.getLineItemEndDate(lineItem);

        Assert.assertTrue(getDate(2015, cal.JULY, 1).compareTo(startDate) == 0);
        Assert.assertTrue(getDate(2015, cal.NOVEMBER, 1).compareTo(endDate) == 0);

    }

    private java.sql.Date getDate(Integer year, Integer month, Integer day) {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.YEAR, year);
        cal.set(cal.MONTH, month);
        cal.set(cal.DATE, day);
        cal.set(cal.HOUR_OF_DAY, 0);
        cal.set(cal.MINUTE, 0);
        cal.set(cal.SECOND, 0);
        cal.set(cal.MILLISECOND, 0);
        return new java.sql.Date(cal.getTime().getTime());
    }
}
