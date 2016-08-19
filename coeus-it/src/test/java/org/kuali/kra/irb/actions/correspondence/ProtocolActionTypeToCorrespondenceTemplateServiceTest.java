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
package org.kuali.kra.irb.actions.correspondence;


import org.apache.struts.upload.FormFile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.test.MockFormFile;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplate;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplateService;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceType;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;
public class ProtocolActionTypeToCorrespondenceTemplateServiceTest extends KcIntegrationTestBase {
    
    ProtocolActionTypeToCorrespondenceTemplateService service;
    @Before
    public void setUp() throws Exception {
        service = KcServiceLocator.getService(ProtocolActionTypeToCorrespondenceTemplateService.class);
        Collection<ProtocolCorrespondenceType> protocolCorrespondenceTypes = KcServiceLocator.getService(BusinessObjectService.class).findAll(ProtocolCorrespondenceType.class);
        for (ProtocolCorrespondenceType type : protocolCorrespondenceTypes) {
            if ("Protocol Submission Report #1".equals(type.getDescription())) {
                ProtocolCorrespondenceTemplate template = new ProtocolCorrespondenceTemplate();
                template.setFileName("test.xml");
                template.setProtoCorrespTypeCode(type.getProtoCorrespTypeCode());

                template.setTemplateFile(createFormFile());
                KcServiceLocator.getService(ProtocolCorrespondenceTemplateService.class).addDefaultProtocolCorrespondenceTemplate(type, template);
                KcServiceLocator.getService(BusinessObjectService.class).save(template);
            } else if ("Protocol Submission Report #2".equals(type.getDescription())) {
                ProtocolCorrespondenceTemplate template = new ProtocolCorrespondenceTemplate();
                template.setFileName("test2.xml");
                template.setProtoCorrespTypeCode(type.getProtoCorrespTypeCode());

                template.setTemplateFile(createFormFile());
                KcServiceLocator.getService(ProtocolCorrespondenceTemplateService.class).addDefaultProtocolCorrespondenceTemplate(type, template);
                KcServiceLocator.getService(BusinessObjectService.class).save(template);
            }
        }
    }

    private FormFile createFormFile() throws Exception {
        MockFormFile formFile = new MockFormFile();
        formFile.setFileData(new byte[] {'a', 'b', 'c', 'd'});
        formFile.setFileSize(formFile.getFileData().length);
        formFile.setContentType("xml");
        formFile.setFileName("test.xml");
        return formFile;
    }

    @After
    public void tearDown() throws Exception {
        service = null;
    }
    
    @Test
    public void testGetTemplatesByProtocolAction1(){
        List<ProtocolCorrespondenceTemplate> result = (List)service.getTemplatesByProtocolAction(ProtocolActionType.SUBMIT_TO_IRB);
        assertTrue(result.size() == 2);
    }
    
    @Test
    public void testGetTemplatesByProtocolAction2(){
        List<ProtocolCorrespondenceTemplate> result = (List)service.getTemplatesByProtocolAction(ProtocolActionType.ADMINISTRATIVE_CORRECTION);
        assertTrue(result.size() == 0);
    }
    
}
