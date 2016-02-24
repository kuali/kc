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
package org.kuali.kra.protocol.protocol.research;

import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.protocol.ProtocolBase;

import java.util.Collection;
import java.util.List;


public abstract class ProtocolResearchAreaServiceImplBase implements ProtocolResearchAreaService {

       
    @Override
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
