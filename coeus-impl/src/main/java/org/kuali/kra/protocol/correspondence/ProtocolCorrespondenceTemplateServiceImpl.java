/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.protocol.correspondence;

import org.apache.struts.upload.FormFile;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.service.BusinessObjectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class implements the ProtocolCorrespondenceTemplateService.
 */
public abstract class ProtocolCorrespondenceTemplateServiceImpl implements ProtocolCorrespondenceTemplateService {

    BusinessObjectService businessObjectService;

    public void addDefaultProtocolCorrespondenceTemplate(ProtocolCorrespondenceTypeBase correspondenceType, 
        ProtocolCorrespondenceTemplateBase correspondenceTemplate) throws Exception {
        correspondenceTemplate.setCommitteeId(Constants.DEFAULT_CORRESPONDENCE_TEMPLATE);
        addProtocolCorrespondenceTemplate(correspondenceType, correspondenceTemplate);
    }
    
    public void addCommitteeProtocolCorrespondenceTemplate(ProtocolCorrespondenceTypeBase correspondenceType, 
            ProtocolCorrespondenceTemplateBase correspondenceTemplate) throws Exception {
        addProtocolCorrespondenceTemplate(correspondenceType, correspondenceTemplate);
    }

    protected void addProtocolCorrespondenceTemplate(ProtocolCorrespondenceTypeBase correspondenceType, 
            ProtocolCorrespondenceTemplateBase correspondenceTemplate) throws Exception {
        correspondenceTemplate.setProtoCorrespTypeCode(correspondenceType.getProtoCorrespTypeCode());

        FormFile templateFile = correspondenceTemplate.getTemplateFile();
        correspondenceTemplate.setFileName(templateFile.getFileName());
        correspondenceTemplate.setCorrespondenceTemplate(templateFile.getFileData());
        
        correspondenceType.getProtocolCorrespondenceTemplates().add(correspondenceTemplate);
    }
    
    public void saveProtocolCorrespondenceTemplates(List<ProtocolCorrespondenceTypeBase> protocolCorrespondenceTypes, 
            List<ProtocolCorrespondenceTemplateBase> deletedBos) {
        if (!deletedBos.isEmpty()) {
            businessObjectService.delete(deletedBos);
        }

        for (ProtocolCorrespondenceTypeBase protocolCorrespondenceType : protocolCorrespondenceTypes) {
            businessObjectService.save(protocolCorrespondenceType);
        }
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public ProtocolCorrespondenceTemplateBase getProtocolCorrespondenceTemplate (String committeeId, String protoCorrespTypeCode) {
        Map fieldValues = new HashMap();
        fieldValues.put("committeeId", committeeId);
        fieldValues.put("protoCorrespTypeCode", protoCorrespTypeCode);
        ProtocolCorrespondenceTemplateBase protocolCorrespondenceTemplate = null;        
        List<ProtocolCorrespondenceTemplateBase> templates = (List<ProtocolCorrespondenceTemplateBase>)businessObjectService.findMatching(getProtocolCorrespondenceTemplateBOClassHook(), fieldValues);
        if (templates.isEmpty()) {
            fieldValues.put("committeeId", "DEFAULT");
            templates = (List<ProtocolCorrespondenceTemplateBase>)businessObjectService.findMatching(getProtocolCorrespondenceTemplateBOClassHook(), fieldValues);
            if (!templates.isEmpty()) {
                protocolCorrespondenceTemplate = templates.get(0);
            }
        } else {
            protocolCorrespondenceTemplate = templates.get(0);            
        }
        return protocolCorrespondenceTemplate;
    }

    
    protected abstract Class<? extends ProtocolCorrespondenceTemplateBase> getProtocolCorrespondenceTemplateBOClassHook();

}