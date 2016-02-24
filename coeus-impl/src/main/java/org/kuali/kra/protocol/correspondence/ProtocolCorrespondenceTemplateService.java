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
