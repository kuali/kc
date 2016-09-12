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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.award.api.AwardController;
import org.kuali.coeus.award.dto.AwardDto;
import org.kuali.coeus.coi.framework.Project;
import org.kuali.coeus.coi.framework.ProjectRetrievalService;
import org.kuali.coeus.common.api.document.service.CommonApiService;
import org.kuali.coeus.irb.api.IrbProtocolDocumentController;
import org.kuali.coeus.irb.api.dto.IrbProtocolDto;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.document.Document;

import javax.jms.ObjectMessage;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ProjectPushIrbTest extends ProjectPushTestBase {

    private ProjectRetrievalService irbProjectRetrievalService;
    final String IRB_PROJECT_RETRIEVAL_SERVICE = "irbProjectRetrievalService";
    private ProtocolDocument protocolDocument;

    @Before
    public void setUp() throws IOException, IntrospectionException, IllegalAccessException, InvocationTargetException, WorkflowException {
        IrbProtocolDto protocolDto = createProtocol();
        protocolDocument = (ProtocolDocument) getCommonApiService().getDocumentFromDocId(Long.parseLong(protocolDto.getDocNbr()));
    }

    private CommonApiService getCommonApiService() {
        return KcServiceLocator.getService(CommonApiService.class);
    }

    @Test
	public void test() throws Exception {
        final Project awardProject = getProjectRetrievalService().retrieveProject(getDocumentIdentifier());
        if(isCoiEnabled()) {
            ObjectMessage message = getMessageFromProject(awardProject);
            getRestMessageConsumer().onMessage(message);
        }
    }

    public IrbProtocolDto createProtocol() throws IOException, WorkflowException, IllegalAccessException, IntrospectionException {
        String jsonInString = getIrbJson();
        ObjectMapper mapper = new ObjectMapper();

        IrbProtocolDto irbDto = mapper.readValue(jsonInString, IrbProtocolDto.class);
        IrbProtocolDto irbProtocolDto = getDocumentController().createProtocol(irbDto);
        Assert.assertTrue(irbProtocolDto.getTitle().equalsIgnoreCase("protocol1"));
        Assert.assertTrue(irbProtocolDto.getReferenceNumber1().equalsIgnoreCase("HR-2775"));
        Assert.assertTrue(irbProtocolDto.getProtocolTypeCode().equalsIgnoreCase("2"));
        Assert.assertTrue(irbProtocolDto.getProtocolPersons().size() == 3);
        Assert.assertTrue(irbProtocolDto.getDocNbr() != null);
        Assert.assertTrue(irbProtocolDto.getDocNbr() != "1756575F");
        Assert.assertTrue(irbProtocolDto.getLeadUnitNumber().equalsIgnoreCase("000001"));

        Assert.assertTrue(irbProtocolDto.getProtocolPersons().get(0).getPersonId().equalsIgnoreCase("10000000018"));
        Assert.assertTrue(irbProtocolDto.getProtocolPersons().get(0).getProtocolPersonRoleId().equalsIgnoreCase(ContactRole.PI_CODE));
        Assert.assertTrue(irbProtocolDto.getProtocolPersons().get(0).getAffiliationTypeCode().toString().equalsIgnoreCase("1"));

        Assert.assertTrue(irbProtocolDto.getProtocolPersons().get(1).getPersonId() == null);
        Assert.assertTrue(irbProtocolDto.getProtocolPersons().get(1).getRolodexId().toString().equalsIgnoreCase("186"));
        Assert.assertTrue(irbProtocolDto.getProtocolPersons().get(1).getProtocolPersonRoleId().equalsIgnoreCase(ContactRole.COI_CODE));
        Assert.assertTrue(irbProtocolDto.getProtocolPersons().get(1).getAffiliationTypeCode().toString().equalsIgnoreCase("5"));

        Assert.assertTrue(irbProtocolDto.getProtocolPersons().get(2).getPersonId().equalsIgnoreCase("10000000030"));
        Assert.assertTrue(irbProtocolDto.getProtocolPersons().get(2).getProtocolPersonRoleId().equalsIgnoreCase("SP"));
        Assert.assertTrue(irbProtocolDto.getProtocolPersons().get(2).getAffiliationTypeCode().toString().equalsIgnoreCase("4"));
        return irbProtocolDto;
    }

    public IrbProtocolDocumentController getDocumentController() throws IntrospectionException {
        IrbProtocolDocumentController controller = KcServiceLocator.getService(IrbProtocolDocumentController.class);
        return controller;
    }

    public String getIrbJson() {
        String jsonString = "{\n" +
                "    \"title\":\"protocol1\",\n" +
                "    \"referenceNumber1\":\"HR-2775\",\n" +
                "    \"protocolTypeCode\":\"2\",\n" +
                "    \"docNbr\":\"1756575F\",\n" +
                "    \"protocolPersons\": [\n" +
                "         {\n" +
                "        \"personId\" : \"10000000018\",\n" +
                "        \"protocolPersonRoleId\": \"PI\",\n" +
                "        \"affiliationTypeCode\":\"1\"\n" +
                "         },\n" +
                "         {\n" +
                "        \"rolodexId\" : \"186\",\n" +
                "        \"protocolPersonRoleId\": \"COI\",\n" +
                "        \"affiliationTypeCode\":\"5\"\n" +
                "         },\n" +
                "         {\n" +
                "        \"personId\" : \"10000000030\",\n" +
                "        \"protocolPersonRoleId\": \"SP\",\n" +
                "        \"affiliationTypeCode\":\"4\"\n" +
                "         }\n" +
                "    ]\n" +
                "}";
        return jsonString;
    }

    @Override
    public String getDocumentIdentifier() {
        return protocolDocument.getProtocol().getProtocolNumber();
    }

    public ProjectRetrievalService getProjectRetrievalService() {
        if (irbProjectRetrievalService == null) {
            irbProjectRetrievalService = KcServiceLocator.getService(IRB_PROJECT_RETRIEVAL_SERVICE);
        }
        return irbProjectRetrievalService;
    }

}
