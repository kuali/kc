/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.protocol;

import java.util.Collection;
import java.util.List;

import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.irb.Protocol;
import org.kuali.rice.kns.bo.PersistableBusinessObject;


public class ProtocolResearchAreaServiceImpl implements ProtocolResearchAreaService {

       
    /**
     * @see org.kuali.kra.irb.protocol.ProtocolResearchAreaService#addProtocolResearchArea(org.kuali.kra.irb.Protocol, org.kuali.kra.bo.ResearchArea)
     */
    public void addProtocolResearchArea(Protocol protocol, Collection<PersistableBusinessObject> selectedBOs) {
        for (PersistableBusinessObject bo : selectedBOs) {
            //New ResearchAreas added by user selection
            ResearchArea newResearchAreas = (ResearchArea) bo;
            // ignore / drop duplicates
            if (!isDuplicateResearchAreas(newResearchAreas, protocol.getProtocolResearchAreas())) {
                //Add new ProtocolResearchAreas to list
                protocol.addProtocolResearchAreas(createInstanceOfProtocolResearchAreas(protocol, newResearchAreas));
            }
        }
    }

    /**
     * This method is private helper method, to create instance of ProtocolResearchAreas and set appropriate values.
     * @param protocolDocument
     * @param researchAreas
     * @return
     */
    private ProtocolResearchArea createInstanceOfProtocolResearchAreas(Protocol protocol, ResearchArea researchAreas) {
        ProtocolResearchArea protocolResearchAreas = new ProtocolResearchArea();
        protocolResearchAreas.setProtocol(protocol);                            
        
        if(null != protocol.getProtocolNumber())
            protocolResearchAreas.setProtocolNumber(protocol.getProtocolNumber());
        else
            protocolResearchAreas.setProtocolNumber("0");
        
        if(null != protocol.getSequenceNumber())
            protocolResearchAreas.setSequenceNumber(protocol.getSequenceNumber());
        else
            protocolResearchAreas.setSequenceNumber(0);
        
        protocolResearchAreas.setResearchAreaCode(researchAreas.getResearchAreaCode());
        protocolResearchAreas.setResearchAreas(researchAreas);
        return protocolResearchAreas;
    }
    
    /**
     * This method is private helper method, to restrict duplicate ProtocolResearchAreas insertion in list.
     * @param newResearchAreaCode
     * @param protocolResearchAreas
     * @return
     */
    private boolean isDuplicateResearchAreas(ResearchArea newResearchAreas, List<ProtocolResearchArea> protocolResearchAreas) {
        for (ProtocolResearchArea pra  : protocolResearchAreas) {    
            if (pra.getResearchAreas().equals(newResearchAreas)) {
                return true;
            }
        }
        return false;
    }    
}
