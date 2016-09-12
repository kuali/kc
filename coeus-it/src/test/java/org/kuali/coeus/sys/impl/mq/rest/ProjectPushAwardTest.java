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
package org.kuali.coeus.sys.impl.mq.rest;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.award.api.AwardController;
import org.kuali.coeus.award.dto.AwardDto;
import org.kuali.coeus.coi.framework.Project;
import org.kuali.coeus.coi.framework.ProjectRetrievalService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;

import javax.jms.ObjectMessage;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ProjectPushAwardTest extends ProjectPushTestBase {

    private static final String AWARD_PROJECT_RETRIEVAL_SERVICE = "awardProjectRetrievalService";
    AwardDocument awardDocument;
    private ProjectRetrievalService awardProjectRetrievalService;

    @Before
    public void setUp() throws IOException, IntrospectionException, IllegalAccessException, InvocationTargetException, WorkflowException {
        String jsonInString = getAwardJson();
        ObjectMapper mapper = new ObjectMapper();

        AwardDto awardDto = mapper.readValue(jsonInString, AwardDto.class);
        AwardDto newAwardDto = getAwardController().createAward(awardDto);
        awardDocument = getAwardController().getAwardDocumentById(newAwardDto.getAwardId());
    }

    @Test
	public void test() throws Exception {
        final Project awardProject = getProjectRetrievalService().retrieveProject(getDocumentIdentifier());
        if(isCoiEnabled()) {
            ObjectMessage message = getMessageFromProject(awardProject);
            getRestMessageConsumer().onMessage(message);
        }
    }

    public AwardController getAwardController() throws IntrospectionException {
        return KcServiceLocator.getService(AwardController.class);
    }

    public String getAwardJson() {
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

    @Override
    public String getDocumentIdentifier() {
        return awardDocument.getAward().getAwardNumber();
    }

    public ProjectRetrievalService getProjectRetrievalService() {
        if (awardProjectRetrievalService == null) {
            awardProjectRetrievalService = KcServiceLocator.getService(AWARD_PROJECT_RETRIEVAL_SERVICE);
        }
        return awardProjectRetrievalService;
    }

}
