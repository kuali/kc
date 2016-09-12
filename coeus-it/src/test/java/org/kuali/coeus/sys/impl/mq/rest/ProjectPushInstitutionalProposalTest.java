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
import org.kuali.coeus.common.api.document.service.CommonApiService;
import org.kuali.coeus.instprop.impl.api.InstitutionalProposalDocumentController;
import org.kuali.coeus.instprop.impl.api.dto.InstitutionalProposalDto;
import org.kuali.coeus.instprop.impl.api.dto.IpPersonDto;
import org.kuali.coeus.instprop.impl.api.service.InstitutionalProposalApiService;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLogger;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLoggerDao;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLoggerFactory;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.impl.controller.rest.audit.RestAuditLoggerFactoryImpl;
import org.kuali.coeus.sys.impl.controller.rest.audit.RestAuditLoggerImpl;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.krad.service.DocumentService;

import javax.jms.ObjectMessage;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ProjectPushInstitutionalProposalTest extends ProjectPushTestBase {

    public static final String INST_PROP_PROJECT_RETRIEVAL_SERVICE = "instPropProjectRetrievalService";
    InstitutionalProposalDocument ipDocument;
    InstitutionalProposal institutionalProposal;
    private ProjectRetrievalService instPropProjectRetrievalService;

    @Before
    public void setUp() throws IOException, IntrospectionException, IllegalAccessException, InvocationTargetException, WorkflowException {
        String jsonInString = getIpJson();
        ObjectMapper mapper = new ObjectMapper();

        InstitutionalProposalDto ipDto = mapper.readValue(jsonInString, InstitutionalProposalDto.class);
        String documentId = getDocumentController().createInstitutionalProposal(ipDto, true);
        ipDocument = (InstitutionalProposalDocument) getDocumentController().getCommonApiService().getDocumentFromDocId(Long.parseLong(documentId));
        institutionalProposal = ipDocument.getInstitutionalProposal();
    }

    @Test
	public void test() throws Exception {
        final Project awardProject = getProjectRetrievalService().retrieveProject(getDocumentIdentifier());
        if(isCoiEnabled()) {
            ObjectMessage message = getMessageFromProject(awardProject);
            getRestMessageConsumer().onMessage(message);
        }
    }

    public String getGoodPersonsJson() {
        return "      [\n" +
                "         {\n" +
                "            \"rolodexId\":\"186\",\n" +
                "            \"roleCode\":\"PI\"\n" +
                "         },\n" +
                "         {\n" +
                "            \"rolodexId\":\"180\",\n" +
                "            \"roleCode\":\"COI\"\n" +
                "         }\n" +
                "      ]\n";
    }

    protected String getIpJson() {
            return "{\n" +
                    "      \"primeSponsorCode\":null,\n" +
                    "      \"proposalTypeCode\":\"1\",\n" +
                    "      \"createTimestamp\":\"3/11/2008\",\n" +
                    "      \"unitNumber\":\"000001\",\n" +
                    "      \"title\":\"Characterizing Sensorimotor Control and Its Deficits in Persons with MS\",\n" +
                    "      \"documentDescription\":\"Characterizing Sensorimotor Control and Its Deficits in Persons with MS\",\n" +
                    "      \"sponsorCode\":\"001067\",\n" +
                    "      \"activityTypeCode\":\"1\",\n" +
                    "      \"requestedStartDateInitial\":\"6/1/2008\",\n" +
                    "      \"requestedStartDateTotal\":\"6/1/2008\",\n" +
                    "      \"requestedEndDateInitial\":\"5/31/2009\",\n" +
                    "      \"requestedEndDateTotal\":\"5/31/2009\",\n" +
                    "      \"totalDirectCostInitial\":\"40000\",\n" +
                    "      \"totalDirectCostTotal\":\"40000\",\n" +
                    "      \"totalIndirectCostInitial\":\"4000\",\n" +
                    "      \"totalIndirectCostTotal\":\"4000\",\n" +
                    "      \"statusCode\":\"5\",\n" +
                    "      \"persons\":[\n" +
                    "         {\n" +
                    "            \"rolodexId\":\"186\",\n" +
                    "            \"roleCode\":\"PI\"\n" +
                    "         },\n" +
                    "         {\n" +
                    "            \"rolodexId\":\"180\",\n" +
                    "            \"roleCode\":\"COI\"\n" +
                    "         }\n" +
                    "      ],\n" +
                    "      \"customData\":[\n" +
                    "         {\n" +
                    "            \"customAttributeId\":\"1\",\n" +
                    "            \"value\":\"0\"\n" +
                    "         },\n" +
                    "         {\n" +
                    "            \"customAttributeId\":\"2\",\n" +
                    "            \"value\":\"0\"\n" +
                    "         }\n" +
                    "      ],\n" +
                    "      \"ipReviewActivityIndicator\":\"A\"\n" +
                    "   }";
    }

    @Override
    public String getDocumentIdentifier() {
        return institutionalProposal.getProposalNumber();
    }

    public ProjectRetrievalService getProjectRetrievalService() {
        if (instPropProjectRetrievalService == null) {
            instPropProjectRetrievalService = KcServiceLocator.getService(INST_PROP_PROJECT_RETRIEVAL_SERVICE);
        }
        return instPropProjectRetrievalService;
    }

    public InstitutionalProposalDocumentController getDocumentController() throws IntrospectionException {
        InstitutionalProposalDocumentController ipDocumentController = new InstitutionalProposalDocumentController() {

            @Override
            public RestAuditLoggerFactory getRestAuditLoggerFactory() {
                return new RestAuditLoggerTest();
            }

            @Override
            public RouteHeaderService getRouteHeaderService() {
                return KcServiceLocator.getService(RouteHeaderService.class);
            }

            @Override
            public InstitutionalProposalApiService getInstitutionalProposalApiService() {
                return KcServiceLocator.getService(InstitutionalProposalApiService.class);
            }

            @Override
            public CommonApiService getCommonApiService() {
                return KcServiceLocator.getService(CommonApiService.class);
            }

            @Override
            public DocumentService getDocumentService() {
                return KcServiceLocator.getService(DocumentService.class);
            }

        };
        return ipDocumentController;
    }

    class RestAuditLoggerTest extends RestAuditLoggerFactoryImpl {
        @Override
        public RestAuditLogger getNewAuditLogger(Class<?> dataObjectClass, List<String> propertiesToLog) {
            return new RestAuditLoggerImplTest("admin",
                    dataObjectClass, propertiesToLog, null);
        }
    }

    public class RestAuditLoggerImplTest extends RestAuditLoggerImpl {
        public RestAuditLoggerImplTest(String username, Class<?> dataObjectClass, List<String> propertiesToTrack,
                                       RestAuditLoggerDao restAuditLoggerDao) {
            super(username, dataObjectClass, propertiesToTrack, restAuditLoggerDao, null);
        }

        @Override
        public void addModifiedItem(Object currentItem, Object updatedItem) {
        }

        @Override
        public void saveAuditLog() {}

        @Override
        public void addNewItem(Object newItem) {}

        @Override
        public void addDeletedItem(Object deletedItem) {}

    }

}
