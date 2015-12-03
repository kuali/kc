package org.kuali.coeus.common.budget.impl.summary;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BudgetSummaryServiceImplTest {
    BudgetSummaryServiceImpl budgetSummaryService;

    public List<Date> createStartEndDates(String start, String end) throws Exception {
        return new ArrayList<Date>() {{
            add(0, createDateFromString(start));
            add(1, createDateFromString(end));
        }};

    }

    public Date createDateFromString(String date) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return new Date(dateFormat.parse(date).getTime());
    }

    @Before
    public void setup() {
        budgetSummaryService = new BudgetSummaryServiceImpl();
    }

    @Test
    public void getNewStartEndDates_gapNo_gapLeapNo_initPeriodLeapNo_currentPeriodLeapNo_Yearly() throws Exception {
        List<Date> dates = createStartEndDates("01/01/2015", "12/31/2015");
        List<Date> newDates = budgetSummaryService.getNewStartEndDates(dates, 0, 364, null, false, false);
        Assert.assertEquals("start date should be 01/01/2015", dates.get(0), newDates.get(0));
        Assert.assertEquals("end date should be 12/31/2015", dates.get(1), newDates.get(1));
    }

    @Test
    public void getNewStartEndDates_gapNo_gapLeapNo_initPeriodLeapNo_currentPeriodLeapYes_Yearly() throws Exception {
        List<Date> dates = createStartEndDates("01/01/2016", "12/31/2016");
        List<Date> newDates = budgetSummaryService.getNewStartEndDates(dates, 0, 364, null, false, false);
        Assert.assertEquals("start date should be 01/01/2016", dates.get(0), newDates.get(0));
        Assert.assertEquals("end date should be 12/31/2016", dates.get(1), newDates.get(1));
    }

    @Test
    public void getNewStartEndDates_gapNo_gapLeapNo_initPeriodLeapYes_currentPeriodLeapNo_Yearly() throws Exception {
        List<Date> dates = createStartEndDates("01/01/2015", "12/31/2015");
        List<Date> newDates = budgetSummaryService.getNewStartEndDates(dates, 0, 365, null, true, false);
        Assert.assertEquals("start date should be 01/01/2015", dates.get(0), newDates.get(0));
        Assert.assertEquals("end date should be 12/31/2015", dates.get(1), newDates.get(1));
    }

    @Test
    public void getNewStartEndDates_gapNo_gapLeapNo_initPeriodLeapYes_currentPeriodLeapYes_Yearly() throws Exception {
        List<Date> dates = createStartEndDates("01/01/2016", "12/31/2016");
        List<Date> newDates = budgetSummaryService.getNewStartEndDates(dates, 0, 365, null, true, false);
        Assert.assertEquals("start date should be 01/01/2016", dates.get(0), newDates.get(0));
        Assert.assertEquals("end date should be 12/31/2016", dates.get(1), newDates.get(1));
    }

    @Test
    public void getNewStartEndDates_gapYes_gapLeapNo_initPeriodLeapNo_currentPeriodLeapNo_Yearly() throws Exception {
        List<Date> dates = createStartEndDates("01/01/2015", "12/31/2015");
        List<Date> newDates = budgetSummaryService.getNewStartEndDates(dates, 19, 345, null, false, false);
        Assert.assertEquals("start date should be 01/20/2015", createDateFromString("01/20/2015"), newDates.get(0));
        Assert.assertEquals("end date should be 12/31/2015", dates.get(1), newDates.get(1));
    }

    @Test
    public void getNewStartEndDates_gapYes_gapLeapNo_initPeriodLeapNo_currentPeriodLeapYes_Yearly() throws Exception {
        List<Date> dates = createStartEndDates("01/01/2016", "12/31/2016");
        List<Date> newDates = budgetSummaryService.getNewStartEndDates(dates, 19, 345, null, false, false);
        Assert.assertEquals("start date should be 01/20/2016", createDateFromString("01/20/2016"), newDates.get(0));
        Assert.assertEquals("end date should be 12/31/2016", dates.get(1), newDates.get(1));
    }

    @Test
    public void getNewStartEndDates_gapYes_gapLeapNo_initPeriodLeapYes_currentPeriodLeapNo_Yearly() throws Exception {
        List<Date> dates = createStartEndDates("01/01/2015", "12/31/2015");
        List<Date> newDates = budgetSummaryService.getNewStartEndDates(dates, 19, 346, null, true, false);
        Assert.assertEquals("start date should be 01/20/2015", createDateFromString("01/20/2015"), newDates.get(0));
        Assert.assertEquals("end date should be 12/31/2015", dates.get(1), newDates.get(1));
    }

    @Test
    public void getNewStartEndDates_gapYes_gapLeapNo_initPeriodLeapYes_currentPeriodLeapYes_Yearly() throws Exception {
        List<Date> dates = createStartEndDates("01/01/2016", "12/31/2016");
        List<Date> newDates = budgetSummaryService.getNewStartEndDates(dates, 19, 346, null, true, false);
        Assert.assertEquals("start date should be 01/20/2016", createDateFromString("01/20/2016"), newDates.get(0));
        Assert.assertEquals("end date should be 12/31/2015", dates.get(1), newDates.get(1));
    }

    @Test
    public void getNewStartEndDates_gapYes_gapLeapYes_initPeriodLeapNo_currentPeriodLeapNo_Yearly() throws Exception {
        List<Date> dates = createStartEndDates("02/15/2015", "02/14/2016");
        List<Date> newDates = budgetSummaryService.getNewStartEndDates(dates, 19, 346, null, false, true);
        Assert.assertEquals("start date should be 03/06/2015", createDateFromString("03/06/2015"), newDates.get(0));
        Assert.assertEquals("end date should be 02/14/2016", dates.get(1), newDates.get(1));
    }

    @Test
    public void getNewStartEndDates_gapYes_gapLeapYes_initPeriodLeapNo_currentPeriodLeapYes_Yearly() throws Exception {
        List<Date> dates = createStartEndDates("02/15/2016", "02/14/2017");
        List<Date> newDates = budgetSummaryService.getNewStartEndDates(dates, 19, 346, null, false, true);
        Assert.assertEquals("start date should be 03/05/2016", createDateFromString("03/05/2016"), newDates.get(0));
        Assert.assertEquals("end date should be 02/14/2017", dates.get(1), newDates.get(1));
    }

    @Test
    public void getNewStartEndDates_gapNo_gapLeapNo_initPeriodLeapNo_currentPeriodLeapNo_BiYearly() throws Exception {
        List<Date> dates = createStartEndDates("01/01/2015", "06/30/2015");
        List<Date> newDates = budgetSummaryService.getNewStartEndDates(dates, 0, 180, null, false, false);
        Assert.assertEquals("start date should be 01/01/2015", dates.get(0), newDates.get(0));
        Assert.assertEquals("end date should be 06/30/2015", dates.get(1), newDates.get(1));
    }

}
