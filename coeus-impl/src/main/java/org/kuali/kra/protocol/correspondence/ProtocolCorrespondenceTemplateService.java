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

import java.util.List;

/**
 * The ProtocolBase Correspondence Template Service provides a set of methods for
 * working with protocol correspondence templates.
 */
public interface ProtocolCorrespondenceTemplateService {

    /**
     * 
     * This method adds a default ProtocolCorrespondenceTemplateBase.
     * @param correspondenceType - the protocol correspondence type to which the template is to be added.
     * @param correspondenceTemplate - the protocol correspondence template to be added.
     * @throws Exception 
     */
    void addDefaultProtocolCorrespondenceTemplate(ProtocolCorrespondenceTypeBase correspondenceType, 
            ProtocolCorrespondenceTemplateBase correspondenceTemplate) throws Exception;
    
    /**
     * 
     * This method adds a committee specific ProtocolCorrespondenceTemplateBase.
     * @param correspondenceType - the protocol correspondence type to which the template is to be added.
     * @param correspondenceTemplate - the protocol correspondence template to be added.
     * @throws Exception 
     */
    void addCommitteeProtocolCorrespondenceTemplate(ProtocolCorrespondenceTypeBase correspondenceType, 
            ProtocolCorrespondenceTemplateBase correspondenceTemplate) throws Exception;
    
    /**
     * 
     * This method saves the correspondence templates.
     * @param protocolCorrespondenceTypes - the list of protocolCorrespondenceTypes with templates to be saved to the database..
     * @param deletedBos - the list of protocolCorrespondenceTemplates that are to be deleted from the database.
     */
    void saveProtocolCorrespondenceTemplates(List<ProtocolCorrespondenceTypeBase> protocolCorrespondenceTypes, 
            List<ProtocolCorrespondenceTemplateBase> deletedBos);
    
    /**
     * 
     * This method is to get the correspondence template for the protoCorrespondenceType and the committeeId specified.
     * If it is not found for this committee, then retrieve from 'DEFAULT'
     * @param committeeId
     * @param protoCorrespTypeCode
     * @return
     */
    ProtocolCorrespondenceTemplateBase getProtocolCorrespondenceTemplate (String committeeId, String protoCorrespTypeCode);

}
