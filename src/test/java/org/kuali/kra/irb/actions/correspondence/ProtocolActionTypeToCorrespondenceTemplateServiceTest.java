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
package org.kuali.kra.irb.actions.correspondence;


import java.util.Collection;
import java.util.List;

import org.apache.struts.upload.FormFile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplateService;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceType;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.rice.kns.service.BusinessObjectService;

public class ProtocolActionTypeToCorrespondenceTemplateServiceTest extends KcUnitTestBase {
    
    ProtocolActionTypeToCorrespondenceTemplateService service;
    @Before
    public void setUp() throws Exception {
        super.setUp();
        service = KraServiceLocator.getService(ProtocolActionTypeToCorrespondenceTemplateService.class);
        Collection<ProtocolCorrespondenceType> protocolCorrespondenceTypes = KraServiceLocator.getService(BusinessObjectService.class).findAll(ProtocolCorrespondenceType.class);
        for (ProtocolCorrespondenceType type : protocolCorrespondenceTypes) {
            if ("Protocol Submission Report #1".equals(type.getDescription())) {
                ProtocolCorrespondenceTemplate template = new ProtocolCorrespondenceTemplate();
                template.setFileName("test.xml");
                template.setProtoCorrespTypeCode(type.getProtoCorrespTypeCode());
                FormFile file = new FormFileMock();
                template.setTemplateFile(file);
                KraServiceLocator.getService(ProtocolCorrespondenceTemplateService.class).addDefaultProtocolCorrespondenceTemplate(type, template);
                KraServiceLocator.getService(BusinessObjectService.class).save(template);
            } else if ("Protocol Submission Report #2".equals(type.getDescription())) {
                ProtocolCorrespondenceTemplate template = new ProtocolCorrespondenceTemplate();
                template.setFileName("test2.xml");
                template.setProtoCorrespTypeCode(type.getProtoCorrespTypeCode());
                FormFile file = new FormFileMock();
                template.setTemplateFile(file);
                KraServiceLocator.getService(ProtocolCorrespondenceTemplateService.class).addDefaultProtocolCorrespondenceTemplate(type, template);
                KraServiceLocator.getService(BusinessObjectService.class).save(template);               
            }
        }
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        service = null;
    }
    
    @Test
    public void testGetTemplatesByProtocolAction1(){
        List<ProtocolCorrespondenceTemplate> result = service.getTemplatesByProtocolAction(ProtocolActionType.SUBMIT_TO_IRB);
        assertTrue(result.size() == 2);
    }
    
    @Test
    public void testGetTemplatesByProtocolAction2(){
        List<ProtocolCorrespondenceTemplate> result = service.getTemplatesByProtocolAction(ProtocolActionType.ADMINISTRATIVE_CORRECTION);
        assertTrue(result.size() == 0);
    }
    
    @Test
    public void testGetTemplatesByProtocolAction3(){
        boolean didError = false;
        try {
        List<ProtocolCorrespondenceTemplate> result = service.getTemplatesByProtocolAction("foobar");
        } catch (IllegalArgumentException ae) {
            didError = true;
        }
        assertTrue(didError);
    }

}
