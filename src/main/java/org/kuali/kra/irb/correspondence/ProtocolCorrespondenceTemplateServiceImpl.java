/*
 * Copyright 2006-2010 The Kuali Foundation
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

import java.util.List;

import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * 
 * This class implements the ProtocolCorrespondenceTemplateService.
 */
public class ProtocolCorrespondenceTemplateServiceImpl implements ProtocolCorrespondenceTemplateService {

    BusinessObjectService businessObjectService;

    /**
     * 
     * @see org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplateService#addProtocolCorrespondenceTemplate()
     */
    public void addProtocolCorrespondenceTemplate(ProtocolCorrespondenceType correspondenceType, ProtocolCorrespondenceTemplate correspondenceTemplate) {
        correspondenceTemplate.setProtoCorrespTypeCode(correspondenceType.getProtoCorrespTypeCode());
        if (correspondenceTemplate.getCommitteeId().equals("1")) {
            correspondenceTemplate.setFileName("sample1.add");
        } else {
            correspondenceTemplate.setFileName("sample2.add");
        }
        correspondenceType.getProtocolCorrespondenceTemplates().add(correspondenceTemplate);
    }
    
    /**
     * 
     * @see org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplateService#deleteProtocolCorrespondenceTemplate(org.kuali.kra.irb.correspondence.ProtocolCorrespondenceType, int)
     */
    public void deleteProtocolCorrespondenceTemplate(ProtocolCorrespondenceType correspondenceType, int index) {
        correspondenceType.getProtocolCorrespondenceTemplates().remove(index);	
    }
    
    /**
     * 
     * @see org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplateService#saveProtocolCorrespondenceTemplates(java.util.List)
     */
    public void saveProtocolCorrespondenceTemplates(List<ProtocolCorrespondenceType> protocolCorrespondenceTypes) {
        for (ProtocolCorrespondenceType protocolCorrespondenceType : protocolCorrespondenceTypes) {
            businessObjectService.save(protocolCorrespondenceType);
        }
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
}
