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
package org.kuali.coeus.instprop.impl.api;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.kuali.coeus.common.api.document.service.CommonApiService;
import org.kuali.coeus.common.budget.framework.core.category.BudgetCategory;
import org.kuali.coeus.instprop.impl.api.dto.InstitutionalProposalDto;
import org.kuali.coeus.instprop.impl.api.dto.IpPersonDto;
import org.kuali.coeus.instprop.impl.api.service.InstitutionalProposalApiService;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLogger;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLoggerDao;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLoggerFactory;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.impl.controller.rest.audit.RestAuditLoggerFactoryImpl;
import org.kuali.coeus.sys.impl.controller.rest.audit.RestAuditLoggerImpl;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kew.routeheader.service.RouteHeaderService;
import org.kuali.rice.krad.service.DocumentService;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


public class InstitutionalProposalDocumentControllerTest extends KcIntegrationTestBase{

    @Test
    public void testCreateProposalGoodJson() throws IOException, IntrospectionException, IllegalAccessException, InvocationTargetException, WorkflowException {

        // POST
        String jsonInString = getCorrectJson();
        ObjectMapper mapper = new ObjectMapper();

        InstitutionalProposalDto ipDto = mapper.readValue(jsonInString, InstitutionalProposalDto.class);
        String documentId = getDocumentController().createInstitutionalProposal(ipDto, true);
        InstitutionalProposalDocument document = (InstitutionalProposalDocument) getDocumentController().getCommonApiService().getDocumentFromDocId(Long.parseLong(documentId));
        final InstitutionalProposal institutionalProposal = document.getInstitutionalProposal();
        Assert.assertTrue(institutionalProposal.getProjectPersons().size() == 2);
        Assert.assertTrue(institutionalProposal.getProjectPersons().get(0).isPrincipalInvestigator());
        Assert.assertTrue(institutionalProposal.getProjectPersons().get(1).isCoInvestigator());
        Assert.assertTrue(institutionalProposal.getProjectPersons().get(0).getRolodexId() == 186);
        Assert.assertTrue(institutionalProposal.getProjectPersons().get(1).getRolodexId() == 180);
        Assert.assertTrue(institutionalProposal.getCustomDataList().size() == 2);
        Assert.assertTrue(institutionalProposal.getCustomDataList().get(0).getCustomAttributeId() == 1);
        Assert.assertTrue(institutionalProposal.getCustomDataList().get(1).getCustomAttributeId() == 2);
        Assert.assertTrue(institutionalProposal.getProposalNumber() != null);
        Calendar cal = Calendar.getInstance();
        Assert.assertTrue(institutionalProposal.getRequestedStartDateInitial().compareTo(getDate(2008, cal.JUNE, 1)) == 0);
        Assert.assertTrue(institutionalProposal.getRequestedStartDateTotal().compareTo(getDate(2008, cal.JUNE, 1)) == 0);
        Assert.assertTrue(institutionalProposal.getRequestedEndDateInitial().compareTo(getDate(2009, cal.MAY, 31)) == 0);
        Assert.assertTrue(institutionalProposal.getRequestedEndDateTotal().compareTo(getDate(2009, cal.MAY, 31)) == 0);
        Assert.assertTrue(institutionalProposal.getCreateTimestamp().compareTo(getDate(2008, cal.MARCH, 11)) == 0);

        // GET
        InstitutionalProposalDto ipFetched = getDocumentController().getInstitutionalProposal(Long.parseLong(documentId));
        Assert.assertTrue(ipFetched.getProjectPersons().size() == 2);
        Assert.assertTrue(ipFetched.getProjectPersons().get(0).getRoleCode() == ContactRole.PI_CODE);
        Assert.assertTrue(ipFetched.getProjectPersons().get(0).getRolodexId() == 186);
        Assert.assertTrue(ipFetched.getProjectPersons().get(1).getRolodexId() == 180);
        Assert.assertTrue(ipFetched.getInstitutionalProposalCustomDataList().size() == 2);
        Assert.assertTrue(ipFetched.getInstitutionalProposalCustomDataList().get(0).getCustomAttributeId() == 1);
        Assert.assertTrue(ipFetched.getInstitutionalProposalCustomDataList().get(1).getCustomAttributeId() == 2);
        Assert.assertTrue(ipFetched.getProposalNumber() != null);
        Assert.assertTrue(ipFetched.getDocStatus().equalsIgnoreCase("SAVED"));

        // ROUTE
        try {
            getDocumentController().routeDocument(Long.parseLong(documentId));
        }
        catch(Exception e) {
            //Graduate student count
            Assert.assertTrue(UnprocessableEntityException.class.isAssignableFrom(e.getClass()));
        }

        // DELETE
        getDocumentController().deleteInstitutionalProposal(Long.parseLong(documentId));
        InstitutionalProposalDto deletedIpDto = getDocumentController().getInstitutionalProposal(Long.parseLong(documentId));
        Assert.assertTrue(deletedIpDto.getDocStatus().equalsIgnoreCase("CANCELED"));

    }

    @Test
    public void testCreateProposalPersonsEndpoint() throws IOException, IntrospectionException, IllegalAccessException, InvocationTargetException, WorkflowException {
        String jsonInString = getCorrectJson();
        ObjectMapper mapper = new ObjectMapper();
        InstitutionalProposalDto ipDto = mapper.readValue(jsonInString, InstitutionalProposalDto.class);

        ipDto.setProjectPersons(null);
        ipDto.setInstitutionalProposalCustomDataList(null);
        String documentId = getDocumentController().createInstitutionalProposal(ipDto, true);
        InstitutionalProposalDocument document = (InstitutionalProposalDocument) getDocumentController().getCommonApiService().getDocumentFromDocId(Long.parseLong(documentId));
        final InstitutionalProposal institutionalProposal = document.getInstitutionalProposal();
        Assert.assertTrue(institutionalProposal.getProjectPersons().size() == 0);
        Assert.assertTrue(institutionalProposal.getCustomDataList().size() == 0);
        Assert.assertTrue(institutionalProposal.getProposalNumber() != null);
        Calendar cal = Calendar.getInstance();
        Assert.assertTrue(institutionalProposal.getRequestedStartDateInitial().compareTo(getDate(2008, cal.JUNE, 1)) == 0);
        Assert.assertTrue(institutionalProposal.getRequestedStartDateTotal().compareTo(getDate(2008, cal.JUNE, 1)) == 0);
        Assert.assertTrue(institutionalProposal.getRequestedEndDateInitial().compareTo(getDate(2009, cal.MAY, 31)) == 0);
        Assert.assertTrue(institutionalProposal.getRequestedEndDateTotal().compareTo(getDate(2009, cal.MAY, 31)) == 0);
        Assert.assertTrue(institutionalProposal.getCreateTimestamp().compareTo(getDate(2008, cal.MARCH, 11)) == 0);

        // POST
        String personJson = getGoodPersonsJson();
        ObjectMapper personMapper = new ObjectMapper();
        List<IpPersonDto> persons = personMapper.readValue(personJson, personMapper.getTypeFactory().constructCollectionType(List.class, IpPersonDto.class) );
        getDocumentController().addProposalPersons(persons, Long.parseLong(documentId));

        //GET
        List<IpPersonDto> personsDto = getDocumentController().getAllProposalPersons(Long.parseLong(documentId));
        Assert.assertTrue(personsDto.size() == 2);
        Assert.assertTrue(personsDto.get(0).getRoleCode().equalsIgnoreCase(ContactRole.PI_CODE));
        Assert.assertTrue(personsDto.get(0).getRolodexId().toString().equalsIgnoreCase("186"));
        Assert.assertTrue(personsDto.get(1).getRoleCode().equalsIgnoreCase(ContactRole.COI_CODE));
        Assert.assertTrue(personsDto.get(1).getRolodexId().toString().equalsIgnoreCase("180"));

        Long personId = personsDto.get(1).getInstitutionalProposalContactId();
        getDocumentController().deleteProposalPerson(Long.parseLong(documentId), personId);
        personsDto = getDocumentController().getAllProposalPersons(Long.parseLong(documentId));

        Assert.assertTrue(personsDto.size() == 1);

    }

    @Test(expected = UnprocessableEntityException.class)
    public void testCreateProposalMissingData() throws IOException, IntrospectionException, IllegalAccessException, InvocationTargetException, WorkflowException {
        String jsonInString = getBadJson();
        ObjectMapper mapper = new ObjectMapper();

        InstitutionalProposalDto ipDto = mapper.readValue(jsonInString, InstitutionalProposalDto.class);
        getDocumentController().createInstitutionalProposal(ipDto, true);
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

    public String getBadJson() {
        return "{\n" +
                "      \"primeSponsorCode\":null,\n" +
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

    public String getCorrectJson() {
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

    public Date getDate(int year, int month, int day) {
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



}
