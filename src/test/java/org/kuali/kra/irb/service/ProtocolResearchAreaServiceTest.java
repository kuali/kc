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
package org.kuali.kra.irb.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.irb.bo.Protocol;
import org.kuali.kra.irb.service.impl.ProtocolResearchAreaServiceImpl;
import org.kuali.rice.kns.bo.PersistableBusinessObject;


public class ProtocolResearchAreaServiceTest {
    
    private ResearchArea bo1;
    private ResearchArea bo2;
    private ResearchArea bo3;
    private List<PersistableBusinessObject> listOfResearchArea;
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
     
        listOfResearchArea = new ArrayList<PersistableBusinessObject>();
        listOfResearchArea.add(bo1);
        listOfResearchArea.add(bo2);
        
        protocol = new Protocol();
        protocol.setProtocolId(1L);
        
        service = new ProtocolResearchAreaServiceImpl();
    }
    
    @Test
    public void testAddProtocolResearchArea() throws Exception {
                   
        service.addProtocolResearchArea(protocol, listOfResearchArea);
        //Protocol must have 2 objects in ProtocolResearchArea
        assertEquals(2, protocol.getProtocolResearchAreas().size());
    }
    
    @Test
    public void testAddProtocolResearchAreaForDuplicate() throws Exception {
                   
        service.addProtocolResearchArea(protocol, listOfResearchArea);
        //Protocol must have 2 objects in ProtocolResearchArea
        assertEquals(2, protocol.getProtocolResearchAreas().size());
        
        bo3 = new ResearchArea();
        bo3.setResearchAreaCode("3");
        bo3.setDescription("Test3");
        listOfResearchArea.add(bo3);
        
        //Duplicate insert test
        service.addProtocolResearchArea(protocol, listOfResearchArea);
        //Size must be 3, only newer object is added to list
        assertEquals(3, protocol.getProtocolResearchAreas().size());
    }
    
}
