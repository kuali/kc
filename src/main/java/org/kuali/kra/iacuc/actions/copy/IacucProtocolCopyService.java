/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.iacuc.actions.copy;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.protocol.actions.copy.ProtocolCopyService;

public interface IacucProtocolCopyService extends ProtocolCopyService<IacucProtocolDocument>{

    /**
     * This method is to copy source threers data to destination protocol
     * @param srcProtocol
     * @param destProtocol
     */
    public void copyProtocolThreers(IacucProtocol srcProtocol, IacucProtocol destProtocol);

    /**
     * This method is to merge species during amendment and renewal with amendment.
     * A deep copy alone is causing issue at this point since there is reference in 
     * procedure panel for these protocol species. So when removed from the list is causing
     * referential constraint violation. This is a work around for now until we figure out
     * a change to handle protocol species and procedures. Here PK is retained and other informations
     * are merged for existing species and any new species are added while species removed in 
     * amendment are removed from original.
     * @param srcProtocol
     * @param destProtocol
     */
    public void mergeProtocolSpeciesAndGroups(IacucProtocol srcProtocol, IacucProtocol destProtocol);
    
    /**
     * This method is to merge protocol procedures. We need to set reference for the new
     * protocol species since it we create a new version.
     * @param srcProtocol
     * @param destProtocol
     */
    public void mergeProtocolProcedures(IacucProtocol srcProtocol, IacucProtocol destProtocol);

    /**
     * This method is to copy source exceptions to destination protocol
     * @param srcProtocol
     * @param destProtocol
     */
    public void copyProtocolExceptions(IacucProtocol srcProtocol, IacucProtocol destProtocol);
    
    
}
