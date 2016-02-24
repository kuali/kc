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
