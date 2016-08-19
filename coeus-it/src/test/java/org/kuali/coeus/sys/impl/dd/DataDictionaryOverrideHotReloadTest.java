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
package org.kuali.coeus.sys.impl.dd;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.dd.DataDictionaryOverride;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.test.MockFormFile;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.coreservice.api.parameter.Parameter;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.maintenance.KualiMaintainableImpl;
import org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.datadictionary.AttributeDefinition;
import org.kuali.rice.krad.datadictionary.DataDictionary;
import org.kuali.rice.krad.exception.ValidationException;
import org.kuali.rice.krad.maintenance.MaintainableImpl;
import org.kuali.rice.krad.service.DataDictionaryService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.uif.container.PageGroupBase;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;


import java.util.Collections;
import java.util.UUID;
import java.util.function.Consumer;

public class DataDictionaryOverrideHotReloadTest  extends KcIntegrationTestBase {

    private ParameterService parameterService;
    private DataDictionaryService dataDictionaryService;
    private MaintenanceDocumentDictionaryService maintenanceDocumentDictionaryService;
    private DocumentService documentService;

    @Before
    public void setUpServices() {
        parameterService = KcServiceLocator.getService(ParameterService.class);
        dataDictionaryService = KcServiceLocator.getService(DataDictionaryService.class);
        maintenanceDocumentDictionaryService = KcServiceLocator.getService(MaintenanceDocumentDictionaryService.class);
        documentService = KcServiceLocator.getService(DocumentService.class);
    }

    @Test
    public void test_valid_file_krad() throws Exception {
        setOverrideParameter("Y");

        final String beanOverrideXml = "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "\txmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:p=\"http://www.springframework.org/schema/p\"\n" +
                "\txsi:schemaLocation=\"http://www.springframework.org/schema/beans\n" +
                "                    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd\">\n" +
                "\t<bean id=\"PropDev-InitiatePage\" parent=\"PropDev-InitiatePage-parentBean\" p:header.headerText=\"Create Sausage\"/>\n" +
                "</beans>";


        final Consumer<DataDictionary> preOverrideAssert = (dataDictionary) ->
                Assert.assertEquals("Create Proposal", ((PageGroupBase) dataDictionaryService.getDataDictionary().getDictionaryBean("PropDev-InitiatePage")).getHeader().getHeaderText());
        final Consumer<DataDictionary> postOverrideAssert = (dataDictionary) ->
                Assert.assertEquals("Create Sausage", ((PageGroupBase) dataDictionaryService.getDataDictionary().getDictionaryBean("PropDev-InitiatePage")).getHeader().getHeaderText());

        test_valid_file(beanOverrideXml, true, preOverrideAssert, postOverrideAssert);
    }

    @Test
    public void test_valid_file_kns() throws Exception {
        setOverrideParameter("Y");

        final String beanOverrideXml = "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "\txmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:p=\"http://www.springframework.org/schema/p\"\n" +
                "\txsi:schemaLocation=\"http://www.springframework.org/schema/beans\n" +
                "                    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd\">\n" +
                "\t<bean id=\"Unit-unitNumber\" parent=\"Unit-unitNumber-parentBean\" p:label=\"A Thing\"/>\n" +
                "</beans>";


        final Consumer<DataDictionary> preOverrideAssert = (dataDictionary) ->
                Assert.assertEquals("Unit Number", ((AttributeDefinition) dataDictionaryService.getDataDictionary().getDictionaryBean("Unit-unitNumber")).getLabel());
        final Consumer<DataDictionary> postOverrideAssert = (dataDictionary) ->
                Assert.assertEquals("A Thing",((AttributeDefinition) dataDictionaryService.getDataDictionary().getDictionaryBean("Unit-unitNumber")).getLabel());

        test_valid_file(beanOverrideXml, true, preOverrideAssert, postOverrideAssert);
    }


    private void test_valid_file(String beanOverrideXml, boolean active, Consumer<DataDictionary> preOverrideAssert, Consumer<DataDictionary> postOverrideAssert) throws Exception {
        setOverrideParameter("Y");

        MockFormFile formFile = new MockFormFile();
        formFile.setFileName("DDFile.xml");
        formFile.setContentType("text/xml");
        byte[] beanOverride = beanOverrideXml.getBytes();
        formFile.setFileSize(beanOverride.length);
        formFile.setFileData(beanOverride);

        final DataDictionaryOverride override = new DataDictionaryOverride();
        override.setActive(active);
        override.setFileName(formFile.getFileName());
        override.setContentType(formFile.getContentType());
        override.setAttachmentContent(formFile.getFileData());
        override.setOverrideBeansFile(formFile);

        override.setUpdateUser("admin");

        preOverrideAssert.accept(dataDictionaryService.getDataDictionary());

        MaintenanceDocumentBase document = newMaintDoc(override);
        document.getNewMaintainableObject().setMaintenanceAction(KRADConstants.MAINTENANCE_NEW_ACTION);
        document = (MaintenanceDocumentBase) documentService.routeDocument(document,null,null);

        postOverrideAssert.accept(dataDictionaryService.getDataDictionary());
    }

    @Test(expected = ValidationException.class)
    public void test_invalid_file() throws Exception {
        setOverrideParameter("Y");

        final String beanOverrideXml = "junk";


        final Consumer<DataDictionary> preOverrideAssert = (dataDictionary) -> Assert.assertFalse(GlobalVariables.getMessageMap().toString(), GlobalVariables.getMessageMap().hasErrors());
        final Consumer<DataDictionary> postOverrideAssert = (dataDictionary) -> {};

        test_valid_file(beanOverrideXml, true, preOverrideAssert, postOverrideAssert);
    }

    @Test
    public void test_not_applied_not_active() throws Exception {
        setOverrideParameter("Y");

        final String beanOverrideXml = "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "\txmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:p=\"http://www.springframework.org/schema/p\"\n" +
                "\txsi:schemaLocation=\"http://www.springframework.org/schema/beans\n" +
                "                    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd\">\n" +
                "\t<bean id=\"Unit-organizationId\" parent=\"Unit-organizationId-parentBean\" p:label=\"Foo bar\" />\n" +
                "</beans>";

        final Consumer<DataDictionary> preOverrideAssert, postOverrideAssert;
        preOverrideAssert = postOverrideAssert = (dataDictionary) ->
                Assert.assertEquals("Organization Id", ((AttributeDefinition) dataDictionaryService.getDataDictionary().getDictionaryBean("Unit-organizationId")).getLabel());


        test_valid_file(beanOverrideXml, false, preOverrideAssert, postOverrideAssert);
    }

    private void setOverrideParameter(String value) {
        final Parameter parameter = parameterService.getParameter("KC-SYS", "All", "DATA_DICTIONARY_RUNTIME_OVERRIDE");
        Parameter.Builder builder = Parameter.Builder.create(parameter);
        builder.setValue(value);
        parameterService.updateParameter(builder.build());
    }

    protected MaintenanceDocumentBase newMaintDoc(DataDictionaryOverride newBo) throws Exception {
        final String documentTypeName = maintenanceDocumentDictionaryService.getDocumentTypeName(newBo.getClass());
        final MaintenanceDocumentBase document;
        try {
            document = (MaintenanceDocumentBase) KRADServiceLocatorWeb.getDocumentService().getNewDocument(documentTypeName);
        }
        catch (WorkflowException e) {
            throw new RuntimeException(e);
        }

        document.getNewMaintainableObject().setGenerateDefaultValues(documentTypeName);
        document.getNewMaintainableObject().processAfterNew( document, Collections.emptyMap() );

        document.getDocumentHeader().setDocumentDescription("test");
        document.setOldMaintainableObject(getNewMaintainableImpl(null));
        document.setNewMaintainableObject(getNewMaintainableImpl(newBo));
        document.getNewMaintainableObject().setBoClass(newBo.getClass());
        document.setAttachmentPropertyName("overrideBeansFile");
        document.populateDocumentAttachment();
        document.getAttachment().setObjectId(UUID.randomUUID().toString());

        return document;
    }

    protected MaintainableImpl getNewMaintainableImpl(PersistableBusinessObject bo) {
        return (bo == null) ? new KualiMaintainableImpl() : new KualiMaintainableImpl(bo);
    }

}
