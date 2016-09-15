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
import org.kuali.coeus.award.dto.AwardDto;
import org.kuali.coeus.award.finance.timeAndMoney.api.TimeAndMoneyController;
import org.kuali.coeus.award.finance.timeAndMoney.dto.TimeAndMoneyDto;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.awardhierarchy.AwardHierarchyService;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;

import java.beans.IntrospectionException;
import java.util.Calendar;

public class AwardHierarchyControllerTest extends AwardControllerTestBase {

    @Test
    public void testAwardHierarchy() throws Exception {
        // POST
        String parentAwardJson = getAwardVersion1();
        ObjectMapper mapper = new ObjectMapper();

        AwardDto awardDto = mapper.readValue(parentAwardJson, AwardDto.class);
        AwardDto parentAwardDto = getAwardController().createAward(awardDto);
        AwardDocument parentDocument = getAwardController().getAwardDocumentById(parentAwardDto.getAwardId());
        Award parentAward = parentDocument.getAward();
        AwardPerson person = parentAward.getProjectPerson(0);

        setupCreditSplits(person);
        getAwardController().submitDocument(parentAward.getAwardId());

        AwardDto childAwardDto = mapper.readValue(getAwardVersion1(), AwardDto.class);
        AwardDto createdChildDto = getAwardHierarchyController().createChild(childAwardDto, parentAwardDto.getAwardId());
        AwardDocument childDocument = getAwardController().getAwardDocumentById(createdChildDto.getAwardId());
        Award childAward = childDocument.getAward();

        AwardHierarchy hierarchy = getAwardHierarchyService().loadAwardHierarchy(createdChildDto.getAwardNumber());
        Assert.assertNotNull(hierarchy);
        Assert.assertTrue(hierarchy.getRootAwardNumber().equalsIgnoreCase(parentAwardDto.getAwardNumber()));
        Assert.assertTrue(hierarchy.getParentAwardNumber().equalsIgnoreCase(parentAwardDto.getAwardNumber()));
        Assert.assertTrue(hierarchy.getOriginatingAwardNumber().equalsIgnoreCase(parentAwardDto.getAwardNumber()));
        Assert.assertTrue(hierarchy.getAwardNumber().equalsIgnoreCase(createdChildDto.getAwardNumber()));
        Assert.assertTrue(parentAward.getNoticeDate().compareTo(getDate(2008, 6, 16)) == 0);
        Assert.assertTrue(childAward.getNoticeDate().compareTo(getDate(2008, 6, 16)) == 0);

        setupCreditSplits(childAward.getProjectPerson(0));
        getAwardController().submitDocument(childAward.getAwardId());

        String timeAndMoneyString = getTMVersion1();
        timeAndMoneyString = timeAndMoneyString.replace("awardId\":\"3844\"", "awardId\":\"" +  childAward.getAwardId() + "\"");
        timeAndMoneyString = timeAndMoneyString.replace("\"destinationAwardNumber\":\"000090-00001\"", "\"destinationAwardNumber\":\"" +  childAward.getAwardNumber() + "\"");
        TimeAndMoneyDto timeAndMoneyDto = mapper.readValue(timeAndMoneyString, TimeAndMoneyDto.class);
        String tm1DocumentNumber = getTimeAndMoneyController().createTimeAndMoneyDocument(timeAndMoneyDto);
        getTimeAndMoneyController().submitDocument(tm1DocumentNumber);

        Assert.assertTrue(childAward.getObligatedTotalDirect().compareTo(new ScaleTwoDecimal(121781L)) == 0);
        Assert.assertTrue(childAward.getObligatedTotalIndirect().compareTo(new ScaleTwoDecimal(15105L)) == 0);
        Assert.assertTrue(childAward.getAnticipatedTotalDirect().compareTo(new ScaleTwoDecimal(332831L)) == 0);
        Assert.assertTrue(childAward.getAnticipatedTotalIndirect().compareTo(new ScaleTwoDecimal(39988L)) == 0);

        Assert.assertTrue(childAward.getAwardEffectiveDate().compareTo(getDate(2008, 6, 1)) == 0);
        Assert.assertTrue(childAward.getNoticeDate().compareTo(getDate(2008, 6, 16)) == 0);
        Assert.assertTrue(childAward.getProjectEndDate().compareTo(getDate(2011, 11, 31)) == 0);
        Assert.assertTrue(childAward.getObligationExpirationDate().compareTo(getDate(2009, 5, 30)) == 0);
        Assert.assertTrue(childAward.getAwardAmountInfo().getCurrentFundEffectiveDate().compareTo(getDate(2008, 6, 01)) == 0);
        Assert.assertTrue(childAward.getAwardExecutionDate().compareTo(getDate(2008, 10, 1)) == 0);

        String awardString2 = getAwardVersion2();
        AwardDto award2Dto = mapper.readValue(awardString2, AwardDto.class);
        AwardDto award2ResultDto = getAwardController().versionAward(award2Dto, childAward.getAwardId());
        Long award2Id = award2ResultDto.getAwardId();
        AwardDocument awardDocument2 = getAwardController().getAwardDocumentById(award2Id);
        Award award2 = awardDocument2.getAward();
        person = awardDocument2.getAward().getProjectPerson(0);
        setupCreditSplits(person);
        getAwardController().submitDocument(award2.getAwardId());

        String timeAndMoneyString2 = getTMVersion2();
        timeAndMoneyString2 = timeAndMoneyString2.replace("awardId\":\"3844\"", "awardId\":\"" +  award2.getAwardId() + "\"");
        timeAndMoneyString2 = timeAndMoneyString2.replace("\"destinationAwardNumber\":\"000090-00001\"", "\"destinationAwardNumber\":\"" +  award2.getAwardNumber() + "\"");
        TimeAndMoneyDto timeAndMoney2Dto = mapper.readValue(timeAndMoneyString2, TimeAndMoneyDto.class);
        String tmDocNumber2 = getTimeAndMoneyController().versionTimeAndMoney(timeAndMoney2Dto, tm1DocumentNumber);
        getTimeAndMoneyController().submitDocument(tmDocNumber2);

        Assert.assertTrue(award2.getObligatedTotalDirect().compareTo(new ScaleTwoDecimal(222921L)) == 0);
        Assert.assertTrue(award2.getObligatedTotalIndirect().compareTo(new ScaleTwoDecimal(26664L)) == 0);
        Assert.assertTrue(award2.getAnticipatedTotalDirect().compareTo(new ScaleTwoDecimal(332831L)) == 0);
        Assert.assertTrue(award2.getAnticipatedTotalIndirect().compareTo(new ScaleTwoDecimal(39988L)) == 0);

        Assert.assertTrue(award2.getAwardEffectiveDate().compareTo(getDate(2008, 6, 1)) == 0);
        Assert.assertTrue(award2.getNoticeDate().compareTo(getDate(2009, 8, 17)) == 0);
        Assert.assertTrue(award2.getProjectEndDate().compareTo(getDate(2011, 11, 31)) == 0);
        Assert.assertTrue(award2.getObligationExpirationDate().compareTo(getDate(2010, 5, 30)) == 0);
        Assert.assertTrue(award2.getAwardAmountInfo().getCurrentFundEffectiveDate().compareTo(getDate(2009, 6, 01)) == 0);
        Assert.assertTrue(award2.getAwardExecutionDate().compareTo(getDate(2008, 11, 1)) == 0);

        String awardString3 = getAwardVersion3();
        AwardDto award3Dto = mapper.readValue(awardString3, AwardDto.class);
        AwardDto awardVersion3Dto = getAwardController().versionAward(award3Dto, award2.getAwardId());
        Long award3Id = awardVersion3Dto.getAwardId();
        AwardDocument awardDocument3 = getAwardController().getAwardDocumentById(award3Id);
        Award award3 = awardDocument3.getAward();
        person = awardDocument3.getAward().getProjectPerson(0);
        setupCreditSplits(person);
        getAwardController().submitDocument(award3.getAwardId());

        String timeAndMoneyString3 = getTMVersion3();
        timeAndMoneyString3 = timeAndMoneyString3.replace("awardId\":\"3844\"", "awardId\":\"" +  award3.getAwardId() + "\"");
        timeAndMoneyString3 = timeAndMoneyString3.replace("\"destinationAwardNumber\":\"000090-00001\"", "\"destinationAwardNumber\":\"" +  award3.getAwardNumber() + "\"");
        TimeAndMoneyDto timeAndMoney3Dto = mapper.readValue(timeAndMoneyString3, TimeAndMoneyDto.class);
        String tmDocNumber3 = getTimeAndMoneyController().versionTimeAndMoney(timeAndMoney3Dto, tmDocNumber2);
        getTimeAndMoneyController().submitDocument(tmDocNumber3);

        Assert.assertTrue(award3.getObligatedTotalDirect().compareTo(new ScaleTwoDecimal(332831L)) == 0);
        Assert.assertTrue(award3.getObligatedTotalIndirect().compareTo(new ScaleTwoDecimal(39988L)) == 0);
        Assert.assertTrue(award3.getAnticipatedTotalDirect().compareTo(new ScaleTwoDecimal(332831L)) == 0);
        Assert.assertTrue(award3.getAnticipatedTotalIndirect().compareTo(new ScaleTwoDecimal(39988L)) == 0);

        Assert.assertTrue(award3.getAwardEffectiveDate().compareTo(getDate(2008, 6, 1)) == 0);
        Assert.assertTrue(award3.getNoticeDate().compareTo(getDate(2010, 10, 2)) == 0);
        Assert.assertTrue(award3.getProjectEndDate().compareTo(getDate(2011, 11, 31)) == 0);
        Assert.assertTrue(award3.getObligationExpirationDate().compareTo(getDate(2011, 5, 30)) == 0);
        Assert.assertTrue(award3.getAwardAmountInfo().getCurrentFundEffectiveDate().compareTo(getDate(2010, 6, 01)) == 0);
        Assert.assertTrue(award3.getAwardExecutionDate().compareTo(getDate(2008, 6, 1)) == 0);


    }

    public void setupCreditSplits(AwardPerson person) {
        super.setupCreditSplits(person);
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

    public String getCorrectJson() {
        return "{\n" +
                "      \"primeSponsorCode\":null,\n" +
                "      \"unitNumber\":\"000001\",\n" +
                "      \"sponsorCode\":\"000340\",\n" +
                "      \"statusCode\":\"1\",\n" +
                "      \"accountNumber\":\"123456\",\n" +
                "      \"awardEffectiveDate\":\"3/11/2008\",\n" +
                "      \"awardExecutionDate\":\"3/11/2008\",\n" +
                "      \"beginDate\":\"3/11/2008\",\n" +
                "      \"awardTypeCode\":\"5\",\n" +
                "      \"projectEndDate\":\"12/31/2011\",\n" +
                "      \"obligationStartDate\":\"07/01/2008\",\n" +
                "      \"obligationEndDate\":\"06/30/2009\",\n" +
                "      \"sponsorAwardNumber\":null,\n" +
                "      \"accountTypeCode\":\"1\",\n" +
                "      \"activityTypeCode\":\"1\",\n" +
                "      \"cfdaNumber\":\"00.000\",\n" +
                "      \"methodOfPaymentCode\":\"1\",\n" +
                "      \"title\":\"APPLICATION OF MECHANICAL VIBRATION TO ENHANCE ORTHODONTIC TOOTH MOVEMENT\",\n" +
                "      \"basisOfPaymentCode\":\"1\",\n" +
                "      \"awardTransactionTypeCode\":\"1\",\n" +
                "      \"noticeDate\":\"3/11/2008\",\n" +
                "      \"leadUnitNumber\": null,\n" +
                "      \"projectPersons\": [\n" +
                "         {\n" +
                "        \"personId\" : \"10000000018\",\n" +
                "        \"roleCode\": \"PI\"\n" +
                "         }\n" +
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
                "         {\"sponsorTermId\":\"307\"}, {\"sponsorTermId\":\"308\"}, {\"sponsorTermId\":\"309\"},\n" +
                "         {\"sponsorTermId\":\"310\"}, {\"sponsorTermId\":\"311\"}, {\"sponsorTermId\":\"312\"},\n" +
                "         {\"sponsorTermId\":\"313\"}, {\"sponsorTermId\":\"314\"}, {\"sponsorTermId\":\"315\"}\n" +
                "         ],\n" +
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
                "    \"awardSponsorContacts\" : [\n" +
                "             {\n" +
                "             \"rolodexId\" : \"132\",\n" +
                "             \"roleCode\" : \"1\"\n" +
                "             }\n" +
                "             ]" +
                "   }";


    }

    @Test
    public void testAwardHierarchyProcess2() throws Exception {
        // POST
        String parentAwardJson = getParentAward1Json();
        ObjectMapper mapper = new ObjectMapper();

        AwardDto awardDto = mapper.readValue(parentAwardJson, AwardDto.class);
        AwardDto parentAwardDto = getAwardController().createAward(awardDto);
        AwardDocument parentDocument = getAwardController().getAwardDocumentById(parentAwardDto.getAwardId());
        Award parentAward = parentDocument.getAward();
        AwardPerson person = parentAward.getProjectPerson(0);

        setupCreditSplits(person);
        getAwardController().submitDocument(parentAward.getAwardId());

        String timeAndMoneyString = getParentAward1TMJson();
        timeAndMoneyString = timeAndMoneyString.replace("awardId\":\"3844\"", "awardId\":\"" +  parentAward.getAwardId() + "\"");
        timeAndMoneyString = timeAndMoneyString.replace("\"destinationAwardNumber\":\"000090-00001\"", "\"destinationAwardNumber\":\"" +  parentAward.getAwardNumber() + "\"");
        TimeAndMoneyDto timeAndMoneyDto = mapper.readValue(timeAndMoneyString, TimeAndMoneyDto.class);
        String tm1DocumentNumber = getTimeAndMoneyController().createTimeAndMoneyDocument(timeAndMoneyDto);
        getTimeAndMoneyController().submitDocument(tm1DocumentNumber);
        TimeAndMoneyDto timeAndMoney1DtoRetrieved = getTimeAndMoneyController().getTimeAndMoneydocument(tm1DocumentNumber);
        Assert.assertTrue(timeAndMoney1DtoRetrieved.getTimeAndMoneyDocumentStatus().equalsIgnoreCase("FINAL"));

        String parentAwardVersion2 = getParentAwardVersion2();
        AwardDto parentAwardVersion2Dto = mapper.readValue(parentAwardVersion2, AwardDto.class);
        AwardDto parentAward2Dto = getAwardController().versionAward(parentAwardVersion2Dto, parentAward.getAwardId());
        Long parent2Id = parentAward2Dto.getAwardId();
        AwardDocument parentDocument2 = getAwardController().getAwardDocumentById(parent2Id);
        Award parentAward2 = parentDocument2.getAward();
        person = parentAward2.getProjectPerson(0);
        setupCreditSplits(person);
        getAwardController().submitDocument(parentAward2.getAwardId());


        String timeAndMoneyString2 = getParentAward2TMJson();
        timeAndMoneyString2 = timeAndMoneyString2.replace("awardId\":\"3844\"", "awardId\":\"" +  parentAward2.getAwardId() + "\"");
        timeAndMoneyString2 = timeAndMoneyString2.replace("\"destinationAwardNumber\":\"000090-00001\"", "\"destinationAwardNumber\":\"" +  parentAward2.getAwardNumber() + "\"");
        TimeAndMoneyDto timeAndMoneyDto2 = mapper.readValue(timeAndMoneyString2, TimeAndMoneyDto.class);
        String tm2DocumentNumber = getTimeAndMoneyController().versionTimeAndMoney(timeAndMoneyDto2, timeAndMoney1DtoRetrieved.getTimeAndMoneyDocumentNbr());
        getTimeAndMoneyController().submitDocument(tm2DocumentNumber);

        AwardDto childAwardVersion1Dto = mapper.readValue(getChildAwardVersion1(), AwardDto.class);
        AwardDto createdChildDto = getAwardHierarchyController().createChild(childAwardVersion1Dto, parentAwardDto.getAwardId());
        AwardDocument childDocument = getAwardController().getAwardDocumentById(createdChildDto.getAwardId());
        Award childAward = childDocument.getAward();

        AwardHierarchy hierarchy = getAwardHierarchyService().loadAwardHierarchy(createdChildDto.getAwardNumber());
        Assert.assertNotNull(hierarchy);
        Assert.assertTrue(hierarchy.getRootAwardNumber().equalsIgnoreCase(parentAwardDto.getAwardNumber()));
        Assert.assertTrue(hierarchy.getParentAwardNumber().equalsIgnoreCase(parentAwardDto.getAwardNumber()));
        Assert.assertTrue(hierarchy.getOriginatingAwardNumber().equalsIgnoreCase(parentAwardDto.getAwardNumber()));
        Assert.assertTrue(hierarchy.getAwardNumber().equalsIgnoreCase(createdChildDto.getAwardNumber()));

        Assert.assertTrue(parentAward2.getObligatedTotalDirect().compareTo(new ScaleTwoDecimal(359410L)) == 0);
        Assert.assertTrue(parentAward2.getObligatedTotalIndirect().compareTo(new ScaleTwoDecimal(181500)) == 0);
        Assert.assertTrue(parentAward2.getAnticipatedTotalDirect().compareTo(new ScaleTwoDecimal(359410L)) == 0);
        Assert.assertTrue(parentAward2.getAnticipatedTotalIndirect().compareTo(new ScaleTwoDecimal(181500L)) == 0);

        Assert.assertTrue(parentAward2.getAwardEffectiveDate().compareTo(getDate(2012, 7, 1)) == 0);
        Assert.assertTrue(parentAward2.getNoticeDate().compareTo(getDate(2007, 6, 1)) == 0);
        Assert.assertTrue(parentAward2.getProjectEndDate().compareTo(getDate(2017, 6, 31)) == 0);
        Assert.assertTrue(parentAward2.getObligationExpirationDate().compareTo(getDate(2015, 6, 31)) == 0);
        Assert.assertTrue(parentAward2.getAwardAmountInfo().getCurrentFundEffectiveDate().compareTo(getDate(2014, 7, 01)) == 0);
        Assert.assertTrue(parentAward2.getAwardExecutionDate().compareTo(getDate(2014, 3, 28)) == 0);

        setupCreditSplits(childAward.getProjectPerson(0));
        getAwardController().submitDocument(childAward.getAwardId());
    }

    public String getChildAwardVersion1() {
        return "{\n" +
                "      \"primeSponsorCode\":null,\n" +
                "      \"unitNumber\":\"000001\",\n" +
                "      \"sponsorCode\":\"000340\",\n" +
                "      \"statusCode\":\"1\",\n" +
                "      \"accountNumber\":\"71581\",\n" +
                "      \"awardEffectiveDate\":\"08/01/2012\",\n" +
                "      \"awardExecutionDate\":\"04/28/2014\",\n" +
                "      \"beginDate\":\"3/11/2008\",\n" +
                "      \"awardTypeCode\":\"5\",\n" +
                "      \"projectEndDate\":\"07/31/2017\",\n" +
                "      \"obligationStartDate\":\"08/01/2012\",\n" +
                "      \"obligationEndDate\":\"07/31/2015\",\n" +
                "      \"sponsorAwardNumber\":null,\n" +
                "      \"accountTypeCode\":\"5\",\n" +
                "      \"activityTypeCode\":\"1\",\n" +
                "      \"cfdaNumber\":\"47.074\",\n" +
                "      \"methodOfPaymentCode\":\"1\",\n" +
                "      \"title\":\"APPLICATION OF MECHANICAL VIBRATION TO ENHANCE ORTHODONTIC TOOTH MOVEMENT\",\n" +
                "      \"basisOfPaymentCode\":\"1\",\n" +
                "      \"awardTransactionTypeCode\":\"1\",\n" +
                "      \"noticeDate\":\"07/01/2007\",\n" +
                "      \"leadUnitNumber\": null,\n" +
                "      \"projectPersons\": [\n" +
                "         {\n" +
                "        \"personId\" : \"10000000018\",\n" +
                "        \"roleCode\": \"PI\"\n" +
                "         }\n" +
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
                "         {\"sponsorTermId\":\"307\"}, {\"sponsorTermId\":\"308\"}, {\"sponsorTermId\":\"309\"},\n" +
                "         {\"sponsorTermId\":\"310\"}, {\"sponsorTermId\":\"311\"}, {\"sponsorTermId\":\"312\"},\n" +
                "         {\"sponsorTermId\":\"313\"}, {\"sponsorTermId\":\"314\"}, {\"sponsorTermId\":\"315\"}\n" +
                "         ],\n" +
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
                "    \"awardSponsorContacts\" : [\n" +
                "             {\n" +
                "             \"rolodexId\" : \"132\",\n" +
                "             \"roleCode\" : \"1\"\n" +
                "             }\n" +
                "             ]" +
                "   }";
    }

    public String getParentAwardVersion2() {
        return "{\n" +
                "      \"primeSponsorCode\":null,\n" +
                "      \"unitNumber\":\"000001\",\n" +
                "      \"sponsorCode\":\"000340\",\n" +
                "      \"statusCode\":\"1\",\n" +
                "      \"accountNumber\":\"71581\",\n" +
                "      \"awardEffectiveDate\":\"08/01/2012\",\n" +
                "      \"awardExecutionDate\":\"04/28/2014\",\n" +
                "      \"beginDate\":\"3/11/2008\",\n" +
                "      \"awardTypeCode\":\"5\",\n" +
                "      \"projectEndDate\":\"07/31/2017\",\n" +
                "      \"obligationStartDate\":\"08/01/2014\",\n" +
                "      \"obligationEndDate\":\"07/31/2015\",\n" +
                "      \"sponsorAwardNumber\":null,\n" +
                "      \"accountTypeCode\":\"5\",\n" +
                "      \"activityTypeCode\":\"1\",\n" +
                "      \"cfdaNumber\":\"47.074\",\n" +
                "      \"methodOfPaymentCode\":\"1\",\n" +
                "      \"title\":\"APPLICATION OF MECHANICAL VIBRATION TO ENHANCE ORTHODONTIC TOOTH MOVEMENT\",\n" +
                "      \"basisOfPaymentCode\":\"1\",\n" +
                "      \"awardTransactionTypeCode\":\"1\",\n" +
                "      \"noticeDate\":\"07/01/2007\",\n" +
                "      \"leadUnitNumber\": null,\n" +
                "      \"projectPersons\": [\n" +
                "         {\n" +
                "        \"personId\" : \"10000000018\",\n" +
                "        \"roleCode\": \"PI\"\n" +
                "         }\n" +
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
                "         {\"sponsorTermId\":\"307\"}, {\"sponsorTermId\":\"308\"}, {\"sponsorTermId\":\"309\"},\n" +
                "         {\"sponsorTermId\":\"310\"}, {\"sponsorTermId\":\"311\"}, {\"sponsorTermId\":\"312\"},\n" +
                "         {\"sponsorTermId\":\"313\"}, {\"sponsorTermId\":\"314\"}, {\"sponsorTermId\":\"315\"}\n" +
                "         ],\n" +
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
                "    \"awardSponsorContacts\" : [\n" +
                "             {\n" +
                "             \"rolodexId\" : \"132\",\n" +
                "             \"roleCode\" : \"1\"\n" +
                "             }\n" +
                "             ]" +
                "   }";
    }

    public String getParentAward2TMJson() {
        return "{\n" +
                "    \"awardId\":\"3844\",\n" +
                "   \"transactionDetails\":[\n" +
                "      {\n" +
                "         \"comments\":\"NEW\",\n" +
                "         \"sourceAwardNumber\":\"000000-00000\",\n" +
                "         \"destinationAwardNumber\":\"000090-00001\",\n" +
                "         \"obligatedDirectAmount\":\"123363.00\",\n" +
                "         \"obligatedIndirectAmount\":\"62297.00\",\n" +
                "         \"anticipatedDirectAmount\":\"0.00\",\n" +
                "         \"anticipatedIndirectAmount\":\"0.00\"\n" +
                "      }\n" +
                "   ],\n" +
                "   \"awardAmountTransactions\":[\n" +
                "      {\n" +
                "         \"transactionTypeCode\":\"10\",\n" +
                "         \"noticeDate\":\"04/28/2014\"\n" +
                "      }\n" +
                "   ]\n" +
                "}";
    }

    public String getParentAward1TMJson() {
        return "{\n" +
                "    \"awardId\":\"3844\",\n" +
                "   \"transactionDetails\":[\n" +
                "      {\n" +
                "         \"comments\":\"NEW\",\n" +
                "         \"sourceAwardNumber\":\"000000-00000\",\n" +
                "         \"destinationAwardNumber\":\"000090-00001\",\n" +
                "         \"obligatedDirectAmount\":\"236047.00\",\n" +
                "         \"obligatedIndirectAmount\":\"119203.00\",\n" +
                "         \"anticipatedDirectAmount\":\"359410.00\",\n" +
                "         \"anticipatedIndirectAmount\":\"181500.00\"\n" +
                "      }\n" +
                "   ],\n" +
                "   \"awardAmountTransactions\":[\n" +
                "      {\n" +
                "         \"transactionTypeCode\":\"10\",\n" +
                "         \"noticeDate\":\"07/27/2012\"\n" +
                "      }\n" +
                "   ]\n" +
                "}";
    }

    public String getParentAward1Json() {
        return "{\n" +
                "      \"primeSponsorCode\":null,\n" +
                "      \"unitNumber\":\"000001\",\n" +
                "      \"sponsorCode\":\"000340\",\n" +
                "      \"statusCode\":\"1\",\n" +
                "      \"accountNumber\":\"71581\",\n" +
                "      \"awardEffectiveDate\":\"08/01/2012\",\n" +
                "      \"awardExecutionDate\":\"07/27/2012\",\n" +
                "      \"beginDate\":\"3/11/2008\",\n" +
                "      \"awardTypeCode\":\"5\",\n" +
                "      \"projectEndDate\":\"07/31/2017\",\n" +
                "      \"obligationStartDate\":\"08/01/2012\",\n" +
                "      \"obligationEndDate\":\"07/31/2014\",\n" +
                "      \"sponsorAwardNumber\":null,\n" +
                "      \"accountTypeCode\":\"5\",\n" +
                "      \"activityTypeCode\":\"1\",\n" +
                "      \"cfdaNumber\":\"47.074\",\n" +
                "      \"methodOfPaymentCode\":\"1\",\n" +
                "      \"title\":\"APPLICATION OF MECHANICAL VIBRATION TO ENHANCE ORTHODONTIC TOOTH MOVEMENT\",\n" +
                "      \"basisOfPaymentCode\":\"1\",\n" +
                "      \"awardTransactionTypeCode\":\"1\",\n" +
                "      \"noticeDate\":\"07/01/2007\",\n" +
                "      \"leadUnitNumber\": null,\n" +
                "      \"projectPersons\": [\n" +
                "         {\n" +
                "        \"personId\" : \"10000000018\",\n" +
                "        \"roleCode\": \"PI\"\n" +
                "         }\n" +
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
                "         {\"sponsorTermId\":\"307\"}, {\"sponsorTermId\":\"308\"}, {\"sponsorTermId\":\"309\"},\n" +
                "         {\"sponsorTermId\":\"310\"}, {\"sponsorTermId\":\"311\"}, {\"sponsorTermId\":\"312\"},\n" +
                "         {\"sponsorTermId\":\"313\"}, {\"sponsorTermId\":\"314\"}, {\"sponsorTermId\":\"315\"}\n" +
                "         ],\n" +
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
                "    \"awardSponsorContacts\" : [\n" +
                "             {\n" +
                "             \"rolodexId\" : \"132\",\n" +
                "             \"roleCode\" : \"1\"\n" +
                "             }\n" +
                "             ]" +
                "   }";


    }

    public AwardController getAwardController() throws IntrospectionException {
        return KcServiceLocator.getService(AwardController.class);
    }

    public AwardHierarchyController getAwardHierarchyController() throws IntrospectionException {
        return KcServiceLocator.getService(AwardHierarchyController.class);
    }

    public AwardHierarchyService getAwardHierarchyService() {
        return KcServiceLocator.getService(AwardHierarchyService.class);
    }

    public TimeAndMoneyController getTimeAndMoneyController() throws IntrospectionException {
        return KcServiceLocator.getService(TimeAndMoneyController.class);
    }

    }
