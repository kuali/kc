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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * 
 * This class implements the ProtocolCorrespondenceTemplateService.
 */
public class ProtocolCorrespondenceTemplateServiceImpl implements ProtocolCorrespondenceTemplateService {

    BusinessObjectService businessObjectService;

    public void addDefaultProtocolCorrespondenceTemplate(ProtocolCorrespondenceType correspondenceType, 
        ProtocolCorrespondenceTemplate correspondenceTemplate) throws Exception {
        correspondenceTemplate.setCommitteeId(Constants.DEFAULT_CORRESPONDENCE_TEMPLATE);
        addProtocolCorrespondenceTemplate(correspondenceType, correspondenceTemplate);
    }
    
    public void addCommitteeProtocolCorrespondenceTemplate(ProtocolCorrespondenceType correspondenceType, 
            ProtocolCorrespondenceTemplate correspondenceTemplate) throws Exception {
        addProtocolCorrespondenceTemplate(correspondenceType, correspondenceTemplate);
    }

    protected void addProtocolCorrespondenceTemplate(ProtocolCorrespondenceType correspondenceType, 
            ProtocolCorrespondenceTemplate correspondenceTemplate) throws Exception {
        correspondenceTemplate.setProtoCorrespTypeCode(correspondenceType.getProtoCorrespTypeCode());

        FormFile templateFile = correspondenceTemplate.getTemplateFile();
        correspondenceTemplate.setFileName(templateFile.getFileName());
        correspondenceTemplate.setCorrespondenceTemplate(templateFile.getFileData());
        
        correspondenceType.getProtocolCorrespondenceTemplates().add(correspondenceTemplate);
    }
    
    public void saveProtocolCorrespondenceTemplates(List<ProtocolCorrespondenceType> protocolCorrespondenceTypes, 
            List<ProtocolCorrespondenceTemplate> deletedBos) {
        if (!deletedBos.isEmpty()) {
            businessObjectService.delete(deletedBos);
        }

        for (ProtocolCorrespondenceType protocolCorrespondenceType : protocolCorrespondenceTypes) {
            businessObjectService.save(protocolCorrespondenceType);
        }
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public ProtocolCorrespondenceTemplate getProtocolCorrespondenceTemplate (String committeeId, String protoCorrespTypeCode) {

        // TODO : ProtocolCorrespondenceTemplate is using 'committeeId' not the pk (id) of committee
        // is this ok ?
        Map fieldValues = new HashMap();
        fieldValues.put("committeeId", committeeId);
        fieldValues.put("protoCorrespTypeCode", protoCorrespTypeCode);
        ProtocolCorrespondenceTemplate protocolCorrespondenceTemplate = null;
        List<ProtocolCorrespondenceTemplate> templates = (List<ProtocolCorrespondenceTemplate>)businessObjectService.findMatching(ProtocolCorrespondenceTemplate.class, fieldValues);
        if (templates.isEmpty()) {
            fieldValues.put("committeeId", "DEFAULT");
            templates = (List<ProtocolCorrespondenceTemplate>)businessObjectService.findMatching(ProtocolCorrespondenceTemplate.class, fieldValues);
            if (!templates.isEmpty()) {
                protocolCorrespondenceTemplate = templates.get(0);
            }
        } else {
            protocolCorrespondenceTemplate = templates.get(0);            
        }
        return protocolCorrespondenceTemplate;
    }

}
