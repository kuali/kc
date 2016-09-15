package org.kuali.coeus.award.api;

import junit.framework.Assert;
import org.codehaus.jackson.map.ObjectMapper;
import org.kuali.coeus.award.dto.AwardDto;
import org.kuali.coeus.award.finance.timeAndMoney.api.TimeAndMoneyController;
import org.kuali.coeus.award.finance.timeAndMoney.dto.TimeAndMoneyDto;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonCreditSplit;
import org.kuali.kra.award.contacts.AwardPersonUnitCreditSplit;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AwardControllerTestBase extends KcIntegrationTestBase {
    public Award testAwardTMVersion2(ObjectMapper mapper, Award award1, String tm1DocumentNumber) throws Exception {
        AwardPerson person;
        String awardString2 = getAwardVersion2();
        AwardDto award2Dto = mapper.readValue(awardString2, AwardDto.class);
        AwardDto award2ResultDto = getAwardController().versionAward(award2Dto, award1.getAwardId());
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
        return award2;
    }


    public String getAwardVersion1() {
        return "{\n" +
                "\"unitNumber\":\"000001\",\n" +
                "\"sponsorCode\":\"000340\",\n" +
                "\"statusCode\":\"5\",\n" +
                "\"accountNumber\":\"74493\",\n" +
                "\"awardEffectiveDate\":\"07/01/2008\",\n" +
                "\"awardExecutionDate\":\"11/01/2008\",\n" +
                "\"beginDate\":\"07/01/2008\",\n" +
                "\"projectEndDate\":\"12/31/2011\",\n" +
                "\"obligationStartDate\":\"07/01/2008\",\n" +
                "\"obligationEndDate\":\"06/30/2009\",\n" +
                "\"sponsorAwardNumber\":\"7-08-JF-25\",\n" +
                "\"awardTypeCode\":\"5\",\n" +
                "\"accountTypeCode\":\"5\",\n" +
                "\"activityTypeCode\":\"1\",\n" +
                "\"cfdaNumber\":\"\",\n" +
                "\"methodOfPaymentCode\":\"1\",\n" +
                "\"title\":\"ANovelInterdisciplinaryApproachtoCharacterizeandMinimizeVascularChangesContributingtoRestenosisafterStentinginType2DiabetesMellitus\",\n" +
                "\"basisOfPaymentCode\":\"1\",\n" +
                "\"awardTransactionTypeCode\":\"10\",\n" +
                "\"noticeDate\":\"07/16/2008\",\n" +
                "\"leadUnitNumber\":\"281\",\n" +
                "\"projectPersons\":[\n" +
                "{\n" +
                "\"personId\":\"10000000018\",\n" +
                "\"roleCode\":\"PI\"\n" +
                "}\n" +
                "],\n" +
                "\"awardCustomDataList\":[\n" +
                "{\n" +
                "\"customAttributeId\":\"1\",\n" +
                "\"value\":\"08-193\"\n" +
                "},\n" +
                "{\n" +
                "\"customAttributeId\":\"4\",\n" +
                "\"value\":\"37\"\n" +
                "}\n" +
                "],\n" +
                "\"awardSponsorTerms\":[\n" +
                "{\n" +
                "\"sponsorTermId\":\"307\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"308\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"309\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"310\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"311\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"312\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"313\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"314\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"315\"\n" +
                "}\n" +
                "],\n" +
                "\"awardReportTerms\":[\n" +
                "{\n" +
                "\"reportClassCode\":\"1\",\n" +
                "\"reportCode\":\"33\",\n" +
                "\"frequencyCode\":\"7\",\n" +
                "\"frequencyBaseCode\":\"3\",\n" +
                "\"ospDistributionCode\":\"1\",\n" +
                "\"dueDate\":\"8/1/2009\"\n" +
                "},\n" +
                "{\n" +
                "\"reportClassCode\":\"3\",\n" +
                "\"reportCode\":\"7\",\n" +
                "\"frequencyCode\":\"6\",\n" +
                "\"frequencyBaseCode\":\"2\",\n" +
                "\"ospDistributionCode\":\"1\",\n" +
                "\"dueDate\":\"8/1/2010\"\n" +
                "}\n" +
                "],\n" +
                "\"awardSponsorContacts\":[\n" +
                "{\n" +
                "\"rolodexId\":\"123\",\n" +
                "\"roleCode\":\"8\"\n" +
                "}\n" +
                "]\n" +
                "}";
    }

    public String getTMVersion1() {
        return "{\n" +
                "    \"awardId\":\"3844\",\n" +
                "   \"transactionDetails\":[\n" +
                "      {\n" +
                "         \"comments\":\"NEW\",\n" +
                "         \"sourceAwardNumber\":\"000000-00000\",\n" +
                "         \"destinationAwardNumber\":\"000090-00001\",\n" +
                "         \"obligatedDirectAmount\":\"121781\",\n" +
                "         \"obligatedIndirectAmount\":\"15105\",\n" +
                "         \"anticipatedDirectAmount\":\"332831\",\n" +
                "         \"anticipatedIndirectAmount\":\"39988\"\n" +
                "      }\n" +
                "   ],\n" +
                "   \"awardAmountTransactions\":[\n" +
                "      {\n" +
                "         \"transactionTypeCode\":\"10\",\n" +
                "         \"noticeDate\":\"07/16/2008\"\n" +
                "      }\n" +
                "   ]\n" +
                "}";
    }

    public String getTimeAndMoneyNewVersion() {
        return "{\n" +
                "    \"transactionDetails\": [\n" +
                "    {\n" +
                "      \"comments\": \"test\",\n" +
                "      \"sourceAwardNumber\": \"000000-00000\",\n" +
                "      \"destinationAwardNumber\": \"000536-00001\",\n" +
                "      \"transactionDetailType\": \"PRIMARY\",\n" +
                "      \"obligatedDirectAmount\": 500,\n" +
                "      \"obligatedIndirectAmount\": 500,\n" +
                "      \"anticipatedDirectAmount\": 500,\n" +
                "      \"anticipatedIndirectAmount\": 500\n" +
                "    }],\n" +
                "    \"awardAmountTransactions\" : [\n" +
                "        {\n" +
                "            \"transactionTypeCode\":\"9\"\n" +
                "        }\n" +
                "    ]\n" +
                "   }";
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
                "      \"obligatedDirectAmount\": 500,\n" +
                "      \"obligatedIndirectAmount\": 500,\n" +
                "      \"anticipatedDirectAmount\": 500,\n" +
                "      \"anticipatedIndirectAmount\": 500\n" +
                "    }],\n" +
                "\"awardAmountTransactions\" : [\n" +
                "        {\n" +
                "            \"transactionTypeCode\":\"1\"\n" +
                "        }\n" +
                "    ]" +
                "\n" +
                "   }";
    }

    public String getawardJsonRequiredForTimeAndMoney() {
        return "{\n" +
                "      \"primeSponsorCode\":\"000340\",\n" +
                "      \"unitNumber\":\"000001\",\n" +
                "      \"sponsorCode\":\"000340\",\n" +
                "      \"statusCode\":\"1\",\n" +
                "      \"accountNumber\":\"123456\",\n" +
                "      \"anticipatedTotalDirect\": \"100.05\",\n" +
                "      \"anticipatedTotalIndirect\":\"100\",\n" +
                "      \"obligatedTotalDirect\":\"100.05\",\n" +
                "      \"obligatedTotalIndirect\":\"100\",\n" +
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

    public String getPersonsJson() {
        return "[\n" +
                "         {\n" +
                "        \"personId\" : \"10000000018\",\n" +
                "        \"roleCode\": \"PI\"\n" +
                "         },\n" +
                "         {\n" +
                "        \"personId\" : \"10000000017\",\n" +
                "        \"roleCode\": \"COI\"\n" +
                "         },\n" +
                "         {\n" +
                "        \"personId\" : \"10000000030\",\n" +
                "        \"roleCode\": \"KP\",\n" +
                "        \"keyPersonRole\": \"Postdoc\"\n" +
                "         }\n" +
                "    ]";
    }

    protected String getTMVersion2() {
        return "{\n" +
                "    \"awardId\":\"3844\",\n" +
                "   \"transactionDetails\":[\n" +
                "      {\n" +
                "         \"comments\":\"SUP\",\n" +
                "         \"sourceAwardNumber\":\"000000-00000\",\n" +
                "         \"destinationAwardNumber\":\"000090-00001\",\n" +
                "         \"obligatedDirectAmount\":\"101140\",\n" +
                "         \"obligatedIndirectAmount\":\"11559\",\n" +
                "         \"anticipatedDirectAmount\":\"0\",\n" +
                "         \"anticipatedIndirectAmount\":\"0\"\n" +
                "      }\n" +
                "   ],\n" +
                "   \"awardAmountTransactions\":[\n" +
                "      {\n" +
                "         \"transactionTypeCode\":\"13\",\n" +
                "         \"noticeDate\":\"09/17/2009\"\n" +
                "      }\n" +
                "   ]\n" +
                "}";
    }

    public String getAwardVersion2() {
        return "{\n" +
                "\"unitNumber\":\"000001\",\n" +
                "\"sponsorCode\":\"000340\",\n" +
                "\"statusCode\":\"5\",\n" +
                "\"accountNumber\":\"74493\",\n" +
                "\"awardEffectiveDate\":\"07/01/2008\",\n" +
                "\"awardExecutionDate\":\"12/01/2008\",\n" +
                "\"beginDate\":\"07/01/2008\",\n" +
                "\"projectEndDate\":\"12/31/2011\",\n" +
                "\"obligationStartDate\":\"07/01/2009\",\n" +
                "\"obligationEndDate\":\"06/30/2010\",\n" +
                "\"sponsorAwardNumber\":\"7-08-JF-25\",\n" +
                "\"awardTypeCode\":\"5\",\n" +
                "\"accountTypeCode\":\"5\",\n" +
                "\"activityTypeCode\":\"1\",\n" +
                "\"cfdaNumber\":\"\",\n" +
                "\"methodOfPaymentCode\":\"1\",\n" +
                "\"title\":\"ANovelInterdisciplinaryApproachtoCharacterizeandMinimizeVascularChangesContributingtoRestenosisafterStentinginType2DiabetesMellitus\",\n" +
                "\"basisOfPaymentCode\":\"1\",\n" +
                "\"awardTransactionTypeCode\":\"13\",\n" +
                "\"noticeDate\":\"09/17/2009\",\n" +
                "\"leadUnitNumber\":\"000001\",\n" +
                "\"projectPersons\":[\n" +
                "{\n" +
                "\"personId\":\"10000000018\",\n" +
                "\"roleCode\":\"PI\"\n" +
                "}\n" +
                "],\n" +
                "\"awardCustomDataList\":[\n" +
                "{\n" +
                "\"customAttributeId\":\"1\",\n" +
                "\"value\":\"10-115\"\n" +
                "},\n" +
                "{\n" +
                "\"customAttributeId\":\"4\",\n" +
                "\"value\":\"37\"\n" +
                "}\n" +
                "],\n" +
                "\"awardSponsorTerms\":[\n" +
                "{\n" +
                "\"sponsorTermId\":\"307\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"308\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"309\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"310\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"311\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"312\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"313\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"314\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"315\"\n" +
                "}\n" +
                "],\n" +
                "\"awardReportTerms\":[\n" +
                "{\n" +
                "\"reportClassCode\":\"1\",\n" +
                "\"reportCode\":\"33\",\n" +
                "\"frequencyCode\":\"7\",\n" +
                "\"frequencyBaseCode\":\"3\",\n" +
                "\"ospDistributionCode\":\"1\",\n" +
                "\"dueDate\":\"8/1/2009\"\n" +
                "},\n" +
                "{\n" +
                "\"reportClassCode\":\"3\",\n" +
                "\"reportCode\":\"7\",\n" +
                "\"frequencyCode\":\"6\",\n" +
                "\"frequencyBaseCode\":\"2\",\n" +
                "\"ospDistributionCode\":\"1\",\n" +
                "\"dueDate\":\"8/1/2010\"\n" +
                "}\n" +
                "],\n" +
                "\"awardSponsorContacts\":[\n" +
                "{\n" +
                "\"rolodexId\":\"123\",\n" +
                "\"roleCode\":\"8\"\n" +
                "}\n" +
                "]\n" +
                "}";
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

    public TimeAndMoneyController getTimeAndMoneyController() throws IntrospectionException {
        return KcServiceLocator.getService(TimeAndMoneyController.class);
    }

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

    public void testAwardTMVersion3(ObjectMapper mapper, String tm1DocumentNumber, Award award2) throws Exception {
        AwardPerson person;
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
        String tmDocNumber3 = getTimeAndMoneyController().versionTimeAndMoney(timeAndMoney3Dto, tm1DocumentNumber);
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

    protected String getTMVersion3() {
        return "{\n" +
                "    \"awardId\":\"3844\",\n" +
                "   \"transactionDetails\":[\n" +
                "      {\n" +
                "         \"comments\":\"SUP\",\n" +
                "         \"sourceAwardNumber\":\"000000-00000\",\n" +
                "         \"destinationAwardNumber\":\"000090-00001\",\n" +
                "         \"obligatedDirectAmount\":\"109910\",\n" +
                "         \"obligatedIndirectAmount\":\"13324\",\n" +
                "         \"anticipatedDirectAmount\":\"0\",\n" +
                "         \"anticipatedIndirectAmount\":\"0\"\n" +
                "      }\n" +
                "   ],\n" +
                "   \"awardAmountTransactions\":[\n" +
                "      {\n" +
                "         \"transactionTypeCode\":\"13\",\n" +
                "         \"noticeDate\":\"11/02/2010\"\n" +
                "      }\n" +
                "   ]\n" +
                "}";
    }

    protected String getAwardVersion3() {
        return "{\n" +
                "\"unitNumber\":\"000001\",\n" +
                "\"sponsorCode\":\"000340\",\n" +
                "\"statusCode\":\"5\",\n" +
                "\"accountNumber\":\"74493\",\n" +
                "\"awardEffectiveDate\":\"07/01/2008\",\n" +
                "\"awardExecutionDate\":\"07/01/2008\",\n" +
                "\"beginDate\":\"07/01/2008\",\n" +
                "\"projectEndDate\":\"12/31/2011\",\n" +
                "\"obligationStartDate\":\"07/01/2010\",\n" +
                "\"obligationEndDate\":\"06/30/2011\",\n" +
                "\"sponsorAwardNumber\":\"7-08-JF-25\",\n" +
                "\"awardTypeCode\":\"5\",\n" +
                "\"accountTypeCode\":\"5\",\n" +
                "\"activityTypeCode\":\"2\",\n" +
                "\"cfdaNumber\":\"\",\n" +
                "\"methodOfPaymentCode\":\"1\",\n" +
                "\"title\":\"ANovelInterdisciplinaryApproachtoCharacterizeandMinimizeVascularChangesContributingtoRestenosisafterStentinginType2DiabetesMellitus\",\n" +
                "\"basisOfPaymentCode\":\"1\",\n" +
                "\"awardTransactionTypeCode\":\"13\",\n" +
                "\"noticeDate\":\"11/02/2010\",\n" +
                "\"leadUnitNumber\":\"000001\",\n" +
                "\"projectPersons\":[\n" +
                "{\n" +
                "\"personId\":\"10000000018\",\n" +
                "\"roleCode\":\"PI\"\n" +
                "}\n" +
                "],\n" +
                "\"awardCustomDataList\":[\n" +
                "{\n" +
                "\"customAttributeId\":\"1\",\n" +
                "\"value\":\"10-115\"\n" +
                "},\n" +
                "{\n" +
                "\"customAttributeId\":\"4\",\n" +
                "\"value\":\"37\"\n" +
                "}\n" +
                "],\n" +
                "\"awardSponsorTerms\":[\n" +
                "{\n" +
                "\"sponsorTermId\":\"307\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"308\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"309\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"310\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"311\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"312\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"313\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"314\"\n" +
                "},\n" +
                "{\n" +
                "\"sponsorTermId\":\"315\"\n" +
                "}\n" +
                "],\n" +
                "\"awardReportTerms\":[\n" +
                "{\n" +
                "\"reportClassCode\":\"1\",\n" +
                "\"reportCode\":\"33\",\n" +
                "\"frequencyCode\":\"7\",\n" +
                "\"frequencyBaseCode\":\"3\",\n" +
                "\"ospDistributionCode\":\"1\",\n" +
                "\"dueDate\":\"8/1/2009\"\n" +
                "},\n" +
                "{\n" +
                "\"reportClassCode\":\"3\",\n" +
                "\"reportCode\":\"7\",\n" +
                "\"frequencyCode\":\"6\",\n" +
                "\"frequencyBaseCode\":\"2\",\n" +
                "\"ospDistributionCode\":\"1\",\n" +
                "\"dueDate\":\"8/1/2010\"\n" +
                "}\n" +
                "],\n" +
                "\"awardSponsorContacts\":[\n" +
                "{\n" +
                "\"rolodexId\":\"123\",\n" +
                "\"roleCode\":\"8\"\n" +
                "}\n" +
                "]\n" +
                "}\n";
    }




    public String getJsonWithoutCollections() {
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
                "      \"projectEndDate\":\"3/11/2008\",\n" +
                "      \"sponsorAwardNumber\":null,\n" +
                "      \"accountTypeCode\":\"1\",\n" +
                "      \"activityTypeCode\":\"1\",\n" +
                "      \"cfdaNumber\":\"00.000\",\n" +
                "      \"methodOfPaymentCode\":\"1\",\n" +
                "      \"title\":\"APPLICATION OF MECHANICAL VIBRATION TO ENHANCE ORTHODONTIC TOOTH MOVEMENT\",\n" +
                "      \"basisOfPaymentCode\":\"1\",\n" +
                "      \"awardTransactionTypeCode\":\"1\",\n" +
                "      \"noticeDate\":\"3/11/2008\",\n" +
                "      \"leadUnitNumber\": null\n" +
                "   }";
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
                "      \"projectEndDate\":\"3/11/2008\",\n" +
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
                "         },\n" +
                "         {\n" +
                "        \"personId\" : \"10000000017\",\n" +
                "        \"roleCode\": \"COI\"\n" +
                "         },\n" +
                "         {\n" +
                "        \"personId\" : \"10000000030\",\n" +
                "        \"roleCode\": \"KP\",\n" +
                "        \"keyPersonRole\": \"Postdoc\"\n" +
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

    public String getCorrectAwardJsonDifferentNoticeDate() {
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
                "      \"projectEndDate\":\"3/11/2008\",\n" +
                "      \"sponsorAwardNumber\":null,\n" +
                "      \"accountTypeCode\":\"1\",\n" +
                "      \"activityTypeCode\":\"1\",\n" +
                "      \"cfdaNumber\":\"00.000\",\n" +
                "      \"methodOfPaymentCode\":\"1\",\n" +
                "      \"title\":\"APPLICATION OF MECHANICAL VIBRATION TO ENHANCE ORTHODONTIC TOOTH MOVEMENT\",\n" +
                "      \"basisOfPaymentCode\":\"1\",\n" +
                "      \"awardTransactionTypeCode\":\"1\",\n" +
                "      \"noticeDate\":\"3/5/2015\",\n" +
                "      \"leadUnitNumber\": null,\n" +
                "      \"projectPersons\": [\n" +
                "         {\n" +
                "        \"personId\" : \"10000000018\",\n" +
                "        \"roleCode\": \"PI\"\n" +
                "         },\n" +
                "         {\n" +
                "        \"personId\" : \"10000000017\",\n" +
                "        \"roleCode\": \"COI\"\n" +
                "         },\n" +
                "         {\n" +
                "        \"personId\" : \"10000000030\",\n" +
                "        \"roleCode\": \"KP\",\n" +
                "        \"keyPersonRole\": \"Postdoc\"\n" +
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

    public String getAwardOnePerson() {
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
                "      \"projectEndDate\":\"3/11/2008\",\n" +
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
}
