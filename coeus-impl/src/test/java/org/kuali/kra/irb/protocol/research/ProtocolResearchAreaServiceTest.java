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
package org.kuali.kra.irb.protocol.research;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ResearchArea;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ProtocolResearchAreaServiceTest {
    
    private ResearchArea bo1;
    private ResearchArea bo2;
    private ResearchArea bo3;
    private List<ResearchArea> listOfResearchArea;
    private Protocol protocol;
    private ProtocolResearchAreaService service;
    
    @Before
    public void setUp() throws Exception {
        bo1 = new ResearchArea();
        bo1.setResearchAreaCode("1");
        bo1.setDescription("Test1");
        
        bo2 = new ResearchArea();
        bo2.setResearchAreaCode("2");
        bo2.setDescription("Test2");
     
        listOfResearchArea = new ArrayList<ResearchArea>();
        listOfResearchArea.add(bo1);
        listOfResearchArea.add(bo2);
        
        protocol = new Protocol(){
            @Override
            public void refreshReferenceObject(String referenceObjectName) {}

            
        };
        protocol.setProtocolId(1L);
        
        service = new ProtocolResearchAreaServiceImpl();
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testAddProtocolResearchArea() throws Exception {
                   
        service.addProtocolResearchArea(protocol, (List)listOfResearchArea);
        //Protocol must have 2 objects in ProtocolResearchArea
        assertEquals(2, protocol.getProtocolResearchAreas().size());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testAddProtocolResearchAreaForDuplicate() throws Exception {
                   
        service.addProtocolResearchArea(protocol, (List)listOfResearchArea);
        //Protocol must have 2 objects in ProtocolResearchArea
        assertEquals(2, protocol.getProtocolResearchAreas().size());
        
        bo3 = new ResearchArea();
        bo3.setResearchAreaCode("3");
        bo3.setDescription("Test3");
        listOfResearchArea.add(bo3);
        
        //Duplicate insert test
        service.addProtocolResearchArea(protocol, (List)listOfResearchArea);
        //Size must be 3, only newer object is added to list
        assertEquals(3, protocol.getProtocolResearchAreas().size());
    }
    
}
