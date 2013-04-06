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
package org.kuali.kra.protocol.protocol.research;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.protocol.ProtocolBase;


public abstract class ProtocolResearchAreaServiceImplBase implements ProtocolResearchAreaService {

       
    /**
     * @see org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaService#addProtocolResearchArea(org.kuali.kra.protocol.ProtocolBase, org.kuali.kra.bo.ResearchArea)
     */
    public void addProtocolResearchArea(ProtocolBase protocol, Collection<ResearchAreaBase> selectedBOs) {
        for (ResearchAreaBase newResearchAreas : selectedBOs) {
            //New ResearchAreas added by user selection
            // ignore / drop duplicates
            if (!isDuplicateResearchAreas(newResearchAreas, protocol.getProtocolResearchAreas())) {
                //Add new ProtocolResearchAreas to list
                protocol.addProtocolResearchAreas(createInstanceOfProtocolResearchAreas(protocol, newResearchAreas));
            }
        }
    }
    
    /**
     * @see org.kuali.kra.protocol.protocol.research.ProtocolResearchAreaService#isEmptyProtocolResearchAreas(org.kuali.kra.protocol.ProtocolBase)
     */
    public boolean isEmptyProtocolResearchAreas(ProtocolBase protocol) {
        return CollectionUtils.isEmpty(protocol.getProtocolResearchAreas());
    }

    /**
     * This method is private helper method, to create instance of ProtocolResearchAreas and set appropriate values.
     * @param protocolDocument
     * @param researchAreas
     * @return
     */
    protected ProtocolResearchAreaBase createInstanceOfProtocolResearchAreas(ProtocolBase protocol, ResearchAreaBase researchAreas) {
        ProtocolResearchAreaBase protocolResearchAreas = getNewProtocolResearchAreaInstanceHook();
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
    
    protected abstract ProtocolResearchAreaBase getNewProtocolResearchAreaInstanceHook();
    
    
    /**
     * This method is private helper method, to restrict duplicate ProtocolResearchAreas insertion in list.
     * @param newResearchAreaCode
     * @param protocolResearchAreas
     * @return
     */
    protected boolean isDuplicateResearchAreas(ResearchAreaBase newResearchAreas, List<ProtocolResearchAreaBase> protocolResearchAreas) {
        for (ProtocolResearchAreaBase pra  : protocolResearchAreas) {    
            if (pra.getResearchAreas().equals(newResearchAreas)) {
                return true;
            }
        }
        return false;
    }    
}
