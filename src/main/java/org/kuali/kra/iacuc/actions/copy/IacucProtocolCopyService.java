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
package org.kuali.kra.iacuc.actions.copy;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.protocol.actions.copy.ProtocolCopyService;

public interface IacucProtocolCopyService extends ProtocolCopyService<IacucProtocolDocument>{

    /**
     * This method is to copy iacuc specific data list  from source protocol to destination protocol
     * @param srcProtocol
     * @param destProtocol
     */
    public void copyIacucProtocolModules(IacucProtocol srcProtocol, IacucProtocol destProtocol);
    
    /**
     * This method is to copy source threers data to destination protocol
     * @param srcProtocol
     * @param destProtocol
     */
    public void copyProtocolThreers(IacucProtocol srcProtocol, IacucProtocol destProtocol);
    
    /**
     * This method is to copy source species and groups to destination protocol
     * Here new protocol species id will be generated for all new records.
     * We need to set the old reference id in protocol species.
     * There is a link in procedure to protocol species id. So when we copy procedure data, we can 
     * replace old protocol species id with new one.
     * @param srcProtocol
     * @param destProtocol
     */
    public void copyProtocolSpeciesAndGroups(IacucProtocol srcProtocol, IacucProtocol destProtocol);
    
    /**
     * This method is to copy source procedures to destination protocol
     * We need to replace the old protocol species id with the new one which was handled
     * through copyProtocolSpeciesAndGroups
     * @param srcProtocol
     * @param destProtocol
     */
    public void copyProtocolProcedures(IacucProtocol srcProtocol, IacucProtocol destProtocol);
    
    /**
     * This method is to copy source exceptions to destination protocol
     * @param srcProtocol
     * @param destProtocol
     */
    public void copyProtocolExceptions(IacucProtocol srcProtocol, IacucProtocol destProtocol);
    
    
}
