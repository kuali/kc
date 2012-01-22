/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.correspondence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.upload.FormFile;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;


public class ProtocolCorrespondenceTemplateServiceTest {
    private static final String  COMMITTEE_ID = "commid";
    private static final String  AGENDA_TYPE = "9";
    private static final String  AGENDA_FILE_NAME = "agenda.xml";

    Mockery context = new JUnit4Mockery();
    FormFile mockedFile = null;

    @Before
    public void setUp() throws Exception {
        mockedFile = this.context.mock(FormFile.class);
    }
    
    @After
    public void tearDown() throws Exception {
        mockedFile = null;
    }
    
    @Test
    public void addDefaultProtocolCorrespondenceTemplateTest() throws Exception {
        simulateValidMockedFileBehavior();

        ProtocolCorrespondenceType correspondenceType = new ProtocolCorrespondenceType();
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = new ProtocolCorrespondenceTemplate();
        newCorrespondenceTemplate.setProtoCorrespTypeCode("5");
        newCorrespondenceTemplate.setTemplateFile(mockedFile);

        getProtocolCorrespondenceTemplateService().addDefaultProtocolCorrespondenceTemplate(correspondenceType, newCorrespondenceTemplate);

        assertEquals(newCorrespondenceTemplate.getProtoCorrespTypeCode(), correspondenceType.getDefaultProtocolCorrespondenceTemplate().getProtoCorrespTypeCode());
        assertEquals("DEFAULT", correspondenceType.getDefaultProtocolCorrespondenceTemplate().getCommitteeId());
        assertEquals(newCorrespondenceTemplate.getFileName(), correspondenceType.getDefaultProtocolCorrespondenceTemplate().getFileName());
        assertTrue(correspondenceType.getCommitteeProtocolCorrespondenceTemplates().isEmpty());
    }
    
    @Test
    public void addCommitteeProtocolCorrespondenceTemplateTest() throws Exception {
        simulateValidMockedFileBehavior();
        
        ProtocolCorrespondenceType correspondenceType = new ProtocolCorrespondenceType();
        ProtocolCorrespondenceTemplate newCorrespondenceTemplate = new ProtocolCorrespondenceTemplate();
        newCorrespondenceTemplate.setProtoCorrespTypeCode("5");
        newCorrespondenceTemplate.setCommitteeId("Committee1");
        newCorrespondenceTemplate.setTemplateFile(mockedFile);

        getProtocolCorrespondenceTemplateService().addCommitteeProtocolCorrespondenceTemplate(correspondenceType, newCorrespondenceTemplate);

        assertEquals(1, correspondenceType.getCommitteeProtocolCorrespondenceTemplates().size());
        assertEquals(newCorrespondenceTemplate.getProtoCorrespTypeCode(), correspondenceType.getCommitteeProtocolCorrespondenceTemplates().get(0).getProtoCorrespTypeCode());
        assertEquals(newCorrespondenceTemplate.getCommitteeId(), correspondenceType.getCommitteeProtocolCorrespondenceTemplates().get(0).getCommitteeId());
        assertEquals(newCorrespondenceTemplate.getFileName(), correspondenceType.getCommitteeProtocolCorrespondenceTemplates().get(0).getFileName());
        assertTrue(correspondenceType.getDefaultProtocolCorrespondenceTemplate() == null);
    }
    
    /**
     * This method is to get the protocol correspondence template service.
     * @return ProtocolCorrespondenceTemplateService
     */
    private ProtocolCorrespondenceTemplateService getProtocolCorrespondenceTemplateService() {
        return (ProtocolCorrespondenceTemplateService) KraServiceLocator.getService("protocolCorrespondenceTemplateService");
    }

    private void simulateValidMockedFileBehavior() throws IOException {
        this.context.checking(new Expectations() {{
            allowing(mockedFile).getFileName();
            will(returnValue("template.xml"));
            
            allowing(mockedFile).getFileData();
            will(returnValue(new byte[] { (byte) 1, (byte) 2, (byte) 3 }));
        }});
    }

    @SuppressWarnings("serial")
    private ProtocolCorrespondenceType createCorrespondenceType() {
        ProtocolCorrespondenceType correspondenceType = new ProtocolCorrespondenceType();
        ProtocolCorrespondenceTemplate correspondenceTemplate = new ProtocolCorrespondenceTemplate() {
            @Override
            public int compareTo(ProtocolCorrespondenceTemplate arg) {
                return this.getCommitteeId().compareTo(arg.getCommitteeId());
            }
        };
        correspondenceTemplate.setProtoCorrespTypeCode("5");
        correspondenceTemplate.setCommitteeId(Constants.DEFAULT_CORRESPONDENCE_TEMPLATE);
        correspondenceTemplate.setFileName("defaultTemplate.xml");
        correspondenceType.getProtocolCorrespondenceTemplates().add(correspondenceTemplate);

        correspondenceTemplate = new ProtocolCorrespondenceTemplate(){
            @Override
            public int compareTo(ProtocolCorrespondenceTemplate arg) {
                return this.getCommitteeId().compareTo(arg.getCommitteeId());
            }
        };
        correspondenceTemplate.setProtoCorrespTypeCode("5");
        correspondenceTemplate.setCommitteeId("Committee1");
        correspondenceTemplate.setFileName("committee1Template.xml");
        correspondenceType.getProtocolCorrespondenceTemplates().add(correspondenceTemplate);

        correspondenceTemplate = new ProtocolCorrespondenceTemplate(){
            @Override
            public int compareTo(ProtocolCorrespondenceTemplate arg) {
                return this.getCommitteeId().compareTo(arg.getCommitteeId());
            }
        };
        correspondenceTemplate.setProtoCorrespTypeCode("5");
        correspondenceTemplate.setCommitteeId("Committee2");
        correspondenceTemplate.setFileName("committee2Template.xml");
        correspondenceType.getProtocolCorrespondenceTemplates().add(correspondenceTemplate);
        
        return correspondenceType;
    }

    @Test
    public void testGetProtocolCorrespondenceTemplate() throws Exception {
        ProtocolCorrespondenceTemplateServiceImpl templateService = new ProtocolCorrespondenceTemplateServiceImpl();
        final BusinessObjectService businessObjectService = context.mock(BusinessObjectService.class);
        ProtocolCorrespondenceTemplate template = new ProtocolCorrespondenceTemplate();
        template.setCommitteeId(COMMITTEE_ID);
        template.setProtoCorrespTypeCode(AGENDA_TYPE);
        template.setFileName(AGENDA_FILE_NAME);
        final List<ProtocolCorrespondenceTemplate> templates = new ArrayList<ProtocolCorrespondenceTemplate>();
        templates.add(template);
        context.checking(new Expectations() {
            {
                Map fieldValues = new HashMap();
                fieldValues.put("committeeId", COMMITTEE_ID);
                fieldValues.put("protoCorrespTypeCode", AGENDA_TYPE);
                one(businessObjectService).findMatching(ProtocolCorrespondenceTemplate.class, fieldValues);
                will(returnValue(templates));


            }
        });
        templateService.setBusinessObjectService(businessObjectService);
        ProtocolCorrespondenceTemplate protocolTemplate = templateService.getProtocolCorrespondenceTemplate(COMMITTEE_ID, AGENDA_TYPE);
        Assert.assertEquals(protocolTemplate.getCommitteeId(), COMMITTEE_ID);
        Assert.assertEquals(protocolTemplate.getProtoCorrespTypeCode(), AGENDA_TYPE);
        Assert.assertEquals(protocolTemplate.getFileName(), AGENDA_FILE_NAME);

    }


}
