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

public interface ProtocolCorrespondenceTemplateService {

    /**
     * 
     * This method add a ProtocolCorrespondenceTemplate.
     * @param correspondenceType - the protocol correspondence type to which the template is to be added.
     * @param correspondenceTemplate - the protocol correspondence template to be added.
     */
    void addProtocolCorrespondenceTemplate(ProtocolCorrespondenceType correspondenceType, ProtocolCorrespondenceTemplate correspondenceTemplate);
    
    /**
     * 
     * This method saves the correspondence templates.
     * @param protocolCorrespondenceTypes - the list of protocolCorrespondenceTypes with templates to be saved.
     */
    void saveProtocolCorrespondenceTemplates(List<ProtocolCorrespondenceType> protocolCorrespondenceTypes);
}
