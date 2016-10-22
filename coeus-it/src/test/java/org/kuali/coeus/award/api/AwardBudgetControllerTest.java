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
package org.kuali.coeus.award.api;

import junit.framework.Assert;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.kuali.coeus.award.dto.AwardBudgetExtDto;
import org.kuali.coeus.award.dto.AwardDto;
import org.kuali.coeus.award.dto.AwardPersonDto;
import org.kuali.coeus.award.finance.timeAndMoney.api.TimeAndMoneyController;
import org.kuali.coeus.award.finance.timeAndMoney.dto.TimeAndMoneyDto;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.budget.AwardBudgetExt;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonCreditSplit;
import org.kuali.kra.award.contacts.AwardPersonUnitCreditSplit;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

import javax.validation.constraints.AssertTrue;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AwardBudgetControllerTest extends KcIntegrationTestBase {


    public void setupCreditSplits(AwardPerson person) {
        List<AwardPersonCreditSplit> creditSplits = new ArrayList<>();

        AwardPersonCreditSplit awardCreditSplit0 = new AwardPersonCreditSplit();
        awardCreditSplit0.setCredit(new ScaleTwoDecimal(100));
        awardCreditSplit0.setInvCreditTypeCode("0");
        awardCreditSplit0.setAwardPerson(person);
        creditSplits.add(awardCreditSplit0);

        AwardPersonCreditSplit awardCreditSplit = new AwardPersonCreditSplit();
        awardCreditSplit.setCredit(new ScaleTwoDecimal(100));
        awardCreditSplit.setInvCreditTypeCode("1");
        awardCreditSplit.setAwardPerson(person);
        creditSplits.add(awardCreditSplit);

        AwardPersonCreditSplit awardCreditSplit2 = new AwardPersonCreditSplit();
        awardCreditSplit2.setCredit(new ScaleTwoDecimal(100));
        awardCreditSplit2.setInvCreditTypeCode("2");
        awardCreditSplit2.setAwardPerson(person);
        creditSplits.add(awardCreditSplit2);

        AwardPersonCreditSplit awardCreditSplit3 = new AwardPersonCreditSplit();
        awardCreditSplit3.setCredit(new ScaleTwoDecimal(100));
        awardCreditSplit3.setInvCreditTypeCode("3");
        awardCreditSplit3.setAwardPerson(person);
        creditSplits.add(awardCreditSplit3);
        person.setCreditSplits(creditSplits);

        List<AwardPersonUnitCreditSplit> unitCreditSplits = new ArrayList<>();


        AwardPersonUnitCreditSplit awardUnitCreditSplit8 = new AwardPersonUnitCreditSplit();
        awardUnitCreditSplit8.setCredit(new ScaleTwoDecimal(100));
        awardUnitCreditSplit8.setInvCreditTypeCode("0");
        unitCreditSplits.add(awardUnitCreditSplit8);

        AwardPersonUnitCreditSplit awardUnitCreditSplit5 = new AwardPersonUnitCreditSplit();
        awardUnitCreditSplit5.setCredit(new ScaleTwoDecimal(100));
        awardUnitCreditSplit5.setInvCreditTypeCode("1");
        unitCreditSplits.add(awardUnitCreditSplit5);

        AwardPersonUnitCreditSplit awardUnitCreditSplit6 = new AwardPersonUnitCreditSplit();
        awardUnitCreditSplit6.setCredit(new ScaleTwoDecimal(100));
        awardUnitCreditSplit6.setInvCreditTypeCode("2");
        unitCreditSplits.add(awardUnitCreditSplit6);

        AwardPersonUnitCreditSplit awardUnitCreditSplit7 = new AwardPersonUnitCreditSplit();
        awardUnitCreditSplit7.setCredit(new ScaleTwoDecimal(100));
        awardUnitCreditSplit7.setInvCreditTypeCode("3");
        unitCreditSplits.add(awardUnitCreditSplit7);

        person.getUnit(0).setCreditSplits(unitCreditSplits);
    }

    @Test
    public void testAwardBudget() throws Exception {
        // POST
        String awardJsonRequiredForTimeAndMoney = getawardJson();
        ObjectMapper mapper = new ObjectMapper();

        AwardDto awardDto = mapper.readValue(awardJsonRequiredForTimeAndMoney, AwardDto.class);
        AwardDto newAwardDto = getAwardController().createAward(awardDto);
        AwardDocument document = getAwardController().getAwardDocumentById(newAwardDto.getAwardId());
        AwardPerson person = document.getAward().getProjectPerson(0);
        setupCreditSplits(person);

        KcServiceLocator.getService(DocumentService.class).saveDocument(document);
        Assert.assertTrue(document.getAward().getAwardSequenceStatus().toString().equalsIgnoreCase("PENDING"));
        Long awardId = document.getAward().getAwardId();
        String awardNumber = document.getAward().getAwardNumber();

        Assert.assertTrue(document.getAward().getObligatedTotalDirect().compareTo(new ScaleTwoDecimal(0)) == 0);
        Assert.assertTrue(document.getAward().getObligatedTotalIndirect().compareTo(new ScaleTwoDecimal(0)) == 0);

        Assert.assertTrue(document.getAward().getAnticipatedTotalDirect().compareTo(new ScaleTwoDecimal(0)) == 0);
        Assert.assertTrue(document.getAward().getAnticipatedTotalIndirect().compareTo(new ScaleTwoDecimal(0)) == 0);

        String timeAndMoneyString = getFirsTimeAndMoney();
        timeAndMoneyString = timeAndMoneyString.replace("awardId\" : \"26965\"", "awardId\" : \"" +  awardId + "\"");
        timeAndMoneyString = timeAndMoneyString.replace("\"destinationAwardNumber\": \"000530-00001\"", "\"destinationAwardNumber\": \"" +  awardNumber + "\"");
        TimeAndMoneyDto timeAndMoneyDto = mapper.readValue(timeAndMoneyString, TimeAndMoneyDto.class);
        String documentNumber = getTimeAndMoneyController().createTimeAndMoneyDocument(timeAndMoneyDto);
        TimeAndMoneyDto newDocDto = getTimeAndMoneyController().submitDocument(documentNumber);

        Assert.assertTrue(document.getAward().getObligatedTotalDirect().compareTo(new ScaleTwoDecimal(10000)) == 0);
        Assert.assertTrue(document.getAward().getObligatedTotalIndirect().compareTo(new ScaleTwoDecimal(0)) == 0);

        Assert.assertTrue(document.getAward().getAnticipatedTotalDirect().compareTo(new ScaleTwoDecimal(10000)) == 0);
        Assert.assertTrue(document.getAward().getAnticipatedTotalIndirect().compareTo(new ScaleTwoDecimal(0)) == 0);

        AwardDocument awardDocument = getAwardController().getAwardDocumentById(newAwardDto.getAwardId());

        AwardBudgetExtDto awardBudgetExtDto = mapper.readValue(getBudgetJson(), AwardBudgetExtDto.class);
        AwardBudgetExtDto createdBudget = getAwardBudgetController().createBudget(newAwardDto.getAwardId(), awardBudgetExtDto);
        Assert.assertTrue(createdBudget.getBudgetPeriods().size() == 1);
        Assert.assertTrue(createdBudget.getBudgetPeriods().get(0).getBudgetLineItems().size() == 2);
        Assert.assertTrue(createdBudget.getBudgetPeriods().get(0).getTotalIndirectCost().compareTo(new ScaleTwoDecimal(2000)) == 0);
        Assert.assertTrue(createdBudget.getBudgetPeriods().get(0).getTotalFringeAmount().compareTo(new ScaleTwoDecimal(1000)) == 0);
        Assert.assertTrue(createdBudget.getBudgetPeriods().get(0).getBudgetLineItems().get(0).getCostElement().equalsIgnoreCase("400255"));
        Assert.assertTrue(createdBudget.getBudgetPeriods().get(0).getBudgetLineItems().get(0).getLineItemCost().compareTo(new ScaleTwoDecimal(6000)) == 0);
        Assert.assertTrue(createdBudget.getBudgetPeriods().get(0).getBudgetLineItems().get(1).getCostElement().equalsIgnoreCase("421818"));
        Assert.assertTrue(createdBudget.getBudgetPeriods().get(0).getBudgetLineItems().get(1).getLineItemCost().compareTo(new ScaleTwoDecimal(1000)) == 0);
        Assert.assertTrue(createdBudget.getBudgetPeriods().get(0).getBudgetLineItems().get(0).getCostElement().equalsIgnoreCase("400255"));
        Assert.assertTrue(createdBudget.getBudgetInitiator().equalsIgnoreCase("10000000001"));

        getAwardBudgetController().routeAwardBudget(createdBudget.getBudgetId());

        AwardBudgetExt awardBudgetExt = getBusinessObjectService().findBySinglePrimaryKey(AwardBudgetExt.class, createdBudget.getBudgetId());
        Document awardBudgetDocument = getDocumentService().getByDocumentHeaderId(awardBudgetExt.getDocumentNumber());
        getDocumentService().blanketApproveDocument(awardBudgetDocument, "", new ArrayList<>());
        AwardBudgetActionDto actionDto = mapper.readValue(getBudgetActionString(), AwardBudgetActionDto.class);
        getAwardBudgetController().changeBudgetStatus(createdBudget.getBudgetId(), actionDto);
        AwardBudgetExtDto awardBudget = getAwardBudgetController().getAwardBudget(createdBudget.getBudgetId().toString());
        Assert.assertTrue(awardBudget.getAwardBudgetStatusCode().equalsIgnoreCase(getPostedStatusCode()));
    }

    private String getPostedStatusCode() {
        return CoreFrameworkServiceLocator.getParameterService().getParameterValueAsString(AwardBudgetDocument.class, KeyConstants.AWARD_BUDGET_STATUS_POSTED);
    }

    public BusinessObjectService getBusinessObjectService() {
        return KcServiceLocator.getService(BusinessObjectService.class);
    }

    public DocumentService getDocumentService() {
        return KcServiceLocator.getService(DocumentService.class);
    }

    public String getBudgetActionString() {
        return "{\n" +
                "   \"actionToTake\" : \"post\"\n" +
                "  }";
    }
    public java.sql.Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set( cal.YEAR, year );
        cal.set( cal.MONTH, month);
        cal.set( cal.DATE, day);

        cal.set( cal.HOUR_OF_DAY, 0 );
        cal.set( cal.MINUTE, 0 );
        cal.set( cal.SECOND, 0 );
        cal.set( cal.MILLISECOND, 0 );

        return new java.sql.Date( cal.getTime().getTime() );
    }

    public AwardController getAwardController() throws IntrospectionException {
        return KcServiceLocator.getService(AwardController.class);
    }

    public AwardBudgetController getAwardBudgetController() throws IntrospectionException {
        return KcServiceLocator.getService(AwardBudgetController.class);
    }

    public TimeAndMoneyController getTimeAndMoneyController() throws IntrospectionException {
        return KcServiceLocator.getService(TimeAndMoneyController.class);
    }


    public String getFirsTimeAndMoney() {

        return "{\n" +
                "    \"awardId\" : \"26965\",\n" +
                "    \"transactionDetails\": [\n" +
                "    {\n" +
                "      \"comments\": \"test\",\n" +
                "      \"sourceAwardNumber\": \"000000-00000\",\n" +
                "      \"destinationAwardNumber\": \"000530-00001\",\n" +
                "      \"transactionDetailType\": \"PRIMARY\",\n" +
                "      \"obligatedDirectAmount\": 10000,\n" +
                "      \"obligatedIndirectAmount\": 0,\n" +
                "      \"anticipatedDirectAmount\": 10000,\n" +
                "      \"anticipatedIndirectAmount\": 0\n" +
                "    }],\n" +
               "\"awardAmountTransactions\" : [\n" +
                "        {\n" +
                "            \"transactionTypeCode\":\"1\"\n" +
                "        }\n" +
                "    ]" +
                "\n" +
                "   }";
    }

    public String getawardJson() {
        return "{\n" +
                "      \"primeSponsorCode\":\"000340\",\n" +
                "      \"unitNumber\":\"000001\",\n" +
                "      \"sponsorCode\":\"000340\",\n" +
                "      \"statusCode\":\"1\",\n" +
                "      \"accountNumber\":\"123456\",\n" +
                "      \"anticipatedTotalDirect\": \"0\",\n" +
                "      \"anticipatedTotalIndirect\":\"0\",\n" +
                "      \"obligatedTotalDirect\":\"0\",\n" +
                "      \"obligatedTotalIndirect\":\"0\",\n" +
                "      \"obligationStartDate\":\"01/11/2008\",\n" +
                "      \"obligationEndDate\":\"30/11/2008\",\n" +
                "      \"awardExecutionDate\":\"4/11/2008\",\n" +
                "      \"preAwardEffectiveDate\":\"3/11/2008\",\n" +
                "      \"beginDate\":\"3/11/2008\",\n" +
                "      \"awardEffectiveDate\":\"1/11/2008\",\n" +
                "      \"projectEndDate\":\"30/11/2008\",\n" +
                "      \"closeoutDate\":\"3/11/2008\",\n" +
                "      \"procurementPriorityCode\":\"1\",\n" +
                "      \"sponsorAwardNumber\":null,\n" +
                "      \"awardTypeCode\":\"5\",\n" +
                "      \"accountTypeCode\":\"1\",\n" +
                "      \"activityTypeCode\":\"1\",\n" +
                "      \"preAwardAuthorizedAmount\":\"100\",\n" +
                "      \"cfdaNumber\":\"00.000\",\n" +
                "      \"methodOfPaymentCode\":\"1\",\n" +
                "      \"title\":\"APPLICATION OF MECHANICAL VIBRATION TO ENHANCE ORTHODONTIC TOOTH MOVEMENT\",\n" +
                "      \"basisOfPaymentCode\":\"1\",\n" +
                "      \"awardTransactionTypeCode\":\"1\",\n" +
                "      \"noticeDate\":\"3/11/2008\",\n" +
                "      \"leadUnitNumber\": \"000001\",\n" +
                "      \"projectPersons\": [\n" +
                "         {\n" +
                "        \"personId\" : \"10000000018\",\n" +
                "        \"roleCode\": \"PI\"\n" +
                "         }" +
                "    ],\n" +
                "     \"awardCustomDataList\": [\n" +
                "        {\n" +
                "            \"customAttributeId\" : \"1\",\n" +
                "            \"value\" : \"2\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"customAttributeId\" : \"4\",\n" +
                "            \"value\" : \"2\"\n" +
                "        }\n" +
                "        ],\n" +
                "     \"awardSponsorTerms\" : [\n" +
                "        {\"sponsorTermId\":\"307\"}, {\"sponsorTermId\":\"308\"}, {\"sponsorTermId\":\"309\"},\n" +
                "         {\"sponsorTermId\":\"310\"}, {\"sponsorTermId\":\"311\"}, {\"sponsorTermId\":\"312\"},\n" +
                "         {\"sponsorTermId\":\"313\"}, {\"sponsorTermId\":\"314\"}, {\"sponsorTermId\":\"315\"}         ],\n" +
                "    \"awardReportTerms\" : [\n" +
                "         {\n" +
                "             \"reportClassCode\":\"1\",\n" +
                "             \"reportCode\":\"33\",\n" +
                "             \"frequencyCode\":\"7\",\n" +
                "             \"frequencyBaseCode\":\"3\",\n" +
                "             \"ospDistributionCode\":\"4\",\n" +
                "             \"dueDate\":\"3/11/2015\"\n" +
                "         }, \n" +
                "         {\n" +
                "             \"reportClassCode\":\"3\",\n" +
                "             \"reportCode\":\"7\",\n" +
                "             \"frequencyCode\":\"6\",\n" +
                "             \"frequencyBaseCode\":\"2\",\n" +
                "             \"ospDistributionCode\":\"4\",\n" +
                "            \"dueDate\":\"3/11/2015\"\n" +
                "         }\n" +
                "         ],\n" +
                "         \"awardSponsorContacts\" : [\n" +
                "             {\n" +
                "             \"rolodexId\" : \"132\",\n" +
                "             \"roleCode\" : \"1\"\n" +
                "             }\n" +
                "             ]\n" +
                "   }";
    }

    public String getBudgetJson() {
        return "{\n" +
                "    \"budgetInitiator\": \"10000000001\",\n" +
                "    \"awardBudgetStatusCode\": \"1\",\n" +
                "    \"awardBudgetTypeCode\": \"1\",\n" +
                "    \"description\": \"New\",\n" +
                "    \"onOffCampusFlag\": \"D\",\n" +
                "    \"endDate\": \"06/11/2010\",\n" +
                "    \"startDate\": \"01/11/2008\",\n" +
                "    \"ohRateClassCode\": \"20\",\n" +
                "    \"ohRateTypeCode\": \"1\",\n" +
                "    \"comments\": null,\n" +
                "    \"modularBudgetFlag\": false,\n" +
                "    \"urRateClassCode\": \"1\",\n" +
                "    \"applyLineItemRates\":false,\n" +
                "    \"budgetPeriods\": [\n" +
                "      {\n" +
                "        \"comments\": null,\n" +
                "        \"periodNumber\":\"1\",\n" +
                "        \"costSharingAmount\": 0,\n" +
                "        \"endDate\": \"06/11/2010\",\n" +
                "        \"startDate\": \"01/11/2008\",\n" +
                "        \"totalCost\": 0,\n" +
                "        \"totalIndirectCost\": 2000,\n" +
                "        \"totalFringeAmount\": 1000,\n" +
                "        \"budgetLineItems\": [\n" +
                "          {\n" +
                "            \"applyInRateFlag\": true,\n" +
                "            \"groupName\": \"\",\n" +
                "            \"endDate\": \"06/30/2009\",\n" +
                "            \"lineItemCost\": 1000,\n" +
                "            \"obligatedAmount\": 0,\n" +
                "            \"lineItemDescription\": null,\n" +
                "            \"onOffCampusFlag\": false,\n" +
                "            \"quantity\": 1,\n" +
                "            \"startDate\": \"01/11/2008\",\n" +
                "            \"formulatedCostElementFlag\": false,\n" +
                "            \"costElement\": \"421818\",\n" +
                "            \"budgetCategoryCode\": \"20\",\n" +
                "            \"budgetCategoryTypeCode\": \"E\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"applyInRateFlag\": true,\n" +
                "            \"groupName\": \"\",\n" +
                "            \"endDate\": \"06/11/2010\",\n" +
                "            \"lineItemCost\": 6000,\n" +
                "            \"obligatedAmount\": 0,\n" +
                "            \"lineItemDescription\": null,\n" +
                "            \"onOffCampusFlag\": false,\n" +
                "            \"quantity\": 1,\n" +
                "            \"startDate\": \"07/01/2008\",\n" +
                "            \"formulatedCostElementFlag\": false,\n" +
                "            \"costElement\": \"400255\",\n" +
                "            \"budgetCategoryCode\": \"26\",\n" +
                "            \"budgetCategoryTypeCode\": \"E\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    ],\n" +
                "    \"budgetPersons\": [\n" +
                "      {\n" +
                "        \"effectiveDate\": \"07/01/2008\",\n" +
                "        \"jobCode\": \"AA000\",\n" +
                "        \"nonEmployeeFlag\": false,\n" +
                "        \"personId\": \"10000000018\",\n" +
                "        \"appointmentTypeCode\": \"6\",\n" +
                "        \"calculationBase\": 0,\n" +
                "        \"personName\": \"ALAN MCAFEE\",\n" +
                "        \"salaryAnniversaryDate\": null\n" +
                "      }\n" +
                "    ]\n" +
                "  }";
    }

}
