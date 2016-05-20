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
import org.kuali.coeus.award.dto.AwardPersonDto;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.document.Document;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class AwardDocumentControllerTest extends KcIntegrationTestBase {

    @Test
    public void testAwardCreationGoodJson() throws IOException, IntrospectionException, IllegalAccessException, InvocationTargetException, WorkflowException {
        // POST
        String jsonInString = getCorrectJson();
        ObjectMapper mapper = new ObjectMapper();

        AwardDto awardDto = mapper.readValue(jsonInString, AwardDto.class);
        AwardDto newAwardDto = getDocumentController().createAward(awardDto);
        Assert.assertTrue(newAwardDto.getPrimeSponsorCode() == null);
        Assert.assertTrue(newAwardDto.getUnitNumber().equalsIgnoreCase("000001"));
        Assert.assertTrue(newAwardDto.getSponsorCode().equalsIgnoreCase("000340"));
        Assert.assertTrue(newAwardDto.getAccountNumber().equalsIgnoreCase("123456"));
        Calendar cal = Calendar.getInstance();
        Assert.assertTrue(newAwardDto.getAwardEffectiveDate().compareTo(getDate(2008, 2, 11)) == 0);
        Assert.assertTrue(newAwardDto.getAwardExecutionDate().compareTo(getDate(2008, cal.MARCH, 11)) == 0);
        Assert.assertTrue(newAwardDto.getBeginDate().compareTo(getDate(2008, cal.MARCH, 11)) == 0);
        Assert.assertTrue(newAwardDto.getNoticeDate().compareTo(getDate(2008, cal.MARCH, 11)) == 0);
        Assert.assertTrue(newAwardDto.getProjectEndDate().compareTo(getDate(2008, cal.MARCH, 11)) == 0);
        Assert.assertTrue(newAwardDto.getSponsorAwardNumber() == null);
        Assert.assertTrue(newAwardDto.getAccountTypeCode().toString().equalsIgnoreCase("1"));
        Assert.assertTrue(newAwardDto.getActivityTypeCode().toString().equalsIgnoreCase("1"));
        Assert.assertTrue(newAwardDto.getCfdaNumber().equalsIgnoreCase("00.000"));
        Assert.assertTrue(newAwardDto.getMethodOfPaymentCode().equalsIgnoreCase("1"));
        Assert.assertTrue(newAwardDto.getTitle().equalsIgnoreCase("APPLICATION OF MECHANICAL VIBRATION TO ENHANCE ORTHODONTIC TOOTH MOVEMENT"));
        Assert.assertTrue(newAwardDto.getBasisOfPaymentCode().equalsIgnoreCase("1"));
        Assert.assertTrue(newAwardDto.getAwardTransactionTypeCode().toString().equalsIgnoreCase("1"));
        Assert.assertTrue(newAwardDto.getLeadUnitNumber().equalsIgnoreCase("000001"));
        Assert.assertTrue(newAwardDto.getProjectPersons().size() == 3);
        Assert.assertTrue(newAwardDto.getProjectPersons().get(0).getPersonId().equalsIgnoreCase("10000000018"));
        Assert.assertTrue(newAwardDto.getProjectPersons().get(0).getRoleCode().equalsIgnoreCase("PI"));
        Assert.assertTrue(newAwardDto.getProjectPersons().get(1).getPersonId().equalsIgnoreCase("10000000017"));
        Assert.assertTrue(newAwardDto.getProjectPersons().get(1).getRoleCode().equalsIgnoreCase("COI"));
        Assert.assertTrue(newAwardDto.getProjectPersons().get(2).getPersonId().equalsIgnoreCase("10000000030"));
        Assert.assertTrue(newAwardDto.getProjectPersons().get(2).getRoleCode().equalsIgnoreCase("KP"));
        Assert.assertTrue(newAwardDto.getProjectPersons().get(2).getKeyPersonRole().equalsIgnoreCase("Postdoc"));
        Assert.assertTrue(newAwardDto.getAwardCustomDataList().size() == 2);
        Assert.assertTrue(newAwardDto.getAwardCustomDataList().get(0).getCustomAttributeId().toString().equalsIgnoreCase("1"));
        Assert.assertTrue(newAwardDto.getAwardCustomDataList().get(0).getValue().toString().equalsIgnoreCase("2"));
        Assert.assertTrue(newAwardDto.getAwardCustomDataList().get(1).getCustomAttributeId().toString().equalsIgnoreCase("4"));
        Assert.assertTrue(newAwardDto.getAwardCustomDataList().get(1).getValue().toString().equalsIgnoreCase("2"));
        Assert.assertTrue(newAwardDto.getAwardSponsorTerms().size() == 9);
        ArrayList<Long> termIds = new ArrayList<>();
        termIds.add(307L); termIds.add(308L); termIds.add(309L); termIds.add(310L);
        termIds.add(311L); termIds.add(312L); termIds.add(313L); termIds.add(314L);
        termIds.add(314L); termIds.add(315L);
        List<Long> sponsorTermIds = newAwardDto.getAwardSponsorTerms().stream().map(p -> p.getSponsorTermId()).collect(Collectors.toList());
        sponsorTermIds.removeAll(termIds);
        Assert.assertTrue(newAwardDto.getAwardReportTerms().size() == 2);
        Assert.assertTrue(sponsorTermIds.size() == 0);

        // GET PERSONS
        Assert.assertTrue(getDocumentController().getAwardPersons(Long.parseLong(newAwardDto.getDocNbr())).size() == 3);
        final Long awardContactId = newAwardDto.getProjectPersons().get(1).getAwardContactId();
        // DELETE PERSONS
        getDocumentController().deletePerson(Long.parseLong(newAwardDto.getDocNbr()), awardContactId);
        Assert.assertTrue(getDocumentController().getAwardPersons(Long.parseLong(newAwardDto.getDocNbr())).size() == 2);

        // DELETE AWARD
        getDocumentController().deleteAward(Long.parseLong(newAwardDto.getDocNbr()));
        AwardDto fetchedAwardDto = getDocumentController().getAward(Long.parseLong(newAwardDto.getDocNbr()));
        System.out.println("Status is " + fetchedAwardDto.getStatusCode());
        Assert.assertTrue(fetchedAwardDto.getDocStatus().equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_CANCEL_LABEL));

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testWrongDocumentError() throws IOException, IntrospectionException, IllegalAccessException, InvocationTargetException, WorkflowException {
       class AwardDocumentControllerMock extends AwardDocumentController {
           @Override
           protected Document getDocument(Long documentNumber) {
              return new ProtocolDocument();
           }
       }
        AwardDocumentControllerMock mockController = new AwardDocumentControllerMock();
        AwardDocument document = mockController.getAwardDocument(12L);

    }

    @Test
    public void testRightDocumentError() throws IOException, IntrospectionException, IllegalAccessException, InvocationTargetException, WorkflowException {
        class AwardDocumentControllerMock extends AwardDocumentController {
            @Override
            protected Document getDocument(Long documentNumber) {
                return new AwardDocument();
            }
        }
        AwardDocumentControllerMock mockController = new AwardDocumentControllerMock();
        AwardDocument document = mockController.getAwardDocument(12L);
        Assert.assertTrue(document != null);

    }

    @Test
    public void testAwardCreationNoCollections() throws IOException, IntrospectionException, IllegalAccessException, InvocationTargetException, WorkflowException {
        String json = getJsonWithoutCollections();
        ObjectMapper mapper = new ObjectMapper();
        AwardDto awardDto = mapper.readValue(json, AwardDto.class);

        AwardDto newAwardDto = getDocumentController().createAward(awardDto);
        Assert.assertTrue(newAwardDto.getPrimeSponsorCode() == null);
        Assert.assertTrue(newAwardDto.getUnitNumber().equalsIgnoreCase("000001"));
        Assert.assertTrue(newAwardDto.getSponsorCode().equalsIgnoreCase("000340"));
        Assert.assertTrue(newAwardDto.getAccountNumber().equalsIgnoreCase("123456"));
        Calendar cal = Calendar.getInstance();
        Assert.assertTrue(newAwardDto.getAwardEffectiveDate().compareTo(getDate(2008, 2, 11)) == 0);
        Assert.assertTrue(newAwardDto.getAwardExecutionDate().compareTo(getDate(2008, cal.MARCH, 11)) == 0);
        Assert.assertTrue(newAwardDto.getBeginDate().compareTo(getDate(2008, cal.MARCH, 11)) == 0);
        Assert.assertTrue(newAwardDto.getNoticeDate().compareTo(getDate(2008, cal.MARCH, 11)) == 0);
        Assert.assertTrue(newAwardDto.getProjectEndDate().compareTo(getDate(2008, cal.MARCH, 11)) == 0);
        Assert.assertTrue(newAwardDto.getSponsorAwardNumber() == null);
        Assert.assertTrue(newAwardDto.getAccountTypeCode().toString().equalsIgnoreCase("1"));
        Assert.assertTrue(newAwardDto.getActivityTypeCode().toString().equalsIgnoreCase("1"));
        Assert.assertTrue(newAwardDto.getCfdaNumber().equalsIgnoreCase("00.000"));
        Assert.assertTrue(newAwardDto.getMethodOfPaymentCode().equalsIgnoreCase("1"));
        Assert.assertTrue(newAwardDto.getTitle().equalsIgnoreCase("APPLICATION OF MECHANICAL VIBRATION TO ENHANCE ORTHODONTIC TOOTH MOVEMENT"));
        Assert.assertTrue(newAwardDto.getBasisOfPaymentCode().equalsIgnoreCase("1"));
        Assert.assertTrue(newAwardDto.getAwardTransactionTypeCode().toString().equalsIgnoreCase("1"));
        Assert.assertTrue(newAwardDto.getLeadUnitNumber().equalsIgnoreCase("000001"));
        Assert.assertTrue(newAwardDto.getProjectPersons().size() == 0);
        Assert.assertTrue(newAwardDto.getAwardCustomDataList().size() == 0);
        Assert.assertTrue(newAwardDto.getAwardSponsorTerms().size() == 0);
        Assert.assertTrue(newAwardDto.getAwardReportTerms().size() == 0);

        // GET award
        AwardDto savedAward = getDocumentController().getAward(Long.parseLong(newAwardDto.getDocNbr()));
        Assert.assertTrue(savedAward.getProjectPersons().size() == 0);

        String personJson = getPersonsJson();
        mapper = new ObjectMapper();
        List<AwardPersonDto> persons = mapper.readValue(personJson, mapper.getTypeFactory().constructCollectionType(List.class, AwardPersonDto.class) );

        // POST persons
        getDocumentController().addAwardPersons(persons, Long.parseLong(newAwardDto.getDocNbr()));
        persons = getDocumentController().getAwardPersons(Long.parseLong(newAwardDto.getDocNbr()));
        Assert.assertTrue(persons.size() == 3);


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

    public AwardDocumentController getDocumentController() throws IntrospectionException {
        return KcServiceLocator.getService(AwardDocumentController.class);
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
                "         ]\n" +
                "   }";
    }
}
