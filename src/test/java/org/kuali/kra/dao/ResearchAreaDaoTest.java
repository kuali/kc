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
package org.kuali.kra.dao;

import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.GlobalVariables;

public class ResearchAreaDaoTest extends KraTestBase {

    private String[] qScripts = { "insert R values('001','000001', 'N', 'top level node', sysdate, user)",
            "insert R values('001.1','001', 'N', 'child node', sysdate, user)", "", "" };
    private ResearchAreaDao raDao;
    private BusinessObjectService bos;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        raDao = getService(ResearchAreaDao.class);
        bos = getService(BusinessObjectService.class);
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }

    @After
    public void tearDown() throws Exception {
        GlobalVariables.setUserSession(null);
        raDao = null;
        bos = null;
        super.tearDown();
    }

    @Test
    public void testUpdateResearchArea() {
        qScripts[2] = "update R'description edited' where RESEARCH_AREA_CODE = '001'";
        qScripts[3] = "update R'child description edited' where RESEARCH_AREA_CODE = '001.1'";
        raDao.runScripts(qScripts);
        HashMap<String, String> keyMap = new HashMap<String, String>();
        keyMap.put("researchAreaCode", "001");
        List<ResearchArea> researchAreas = (List<ResearchArea>) bos.findMatching(ResearchArea.class, keyMap);
        assertTrue(researchAreas.size() == 1);
        assertEquals(researchAreas.get(0).getDescription(), "description edited");
        keyMap.put("researchAreaCode", "001.1");
        researchAreas = (List<ResearchArea>) bos.findMatching(ResearchArea.class, keyMap);
        assertTrue(researchAreas.size() == 1);
        assertEquals(researchAreas.get(0).getDescription(), "child description edited");

    }

    @Test
    public void testInsertDeleteResearchArea() {
        raDao.runScripts(qScripts);
        HashMap<String, String> keyMap = new HashMap<String, String>();
        keyMap.put("researchAreaCode", "001");
        List<ResearchArea> researchAreas = (List<ResearchArea>) bos.findMatching(ResearchArea.class, keyMap);
        assertTrue(researchAreas.size() == 1);
        keyMap.put("researchAreaCode", "001.1");
        researchAreas = (List<ResearchArea>) bos.findMatching(ResearchArea.class, keyMap);
        assertTrue(researchAreas.size() == 1);
        qScripts[0] = "delete R'001.1' and PARENT_RESEARCH_AREA_CODE = '001'";
        qScripts[1] = "delete R'001' and PARENT_RESEARCH_AREA_CODE = '000001'";
        raDao.runScripts(qScripts);
        keyMap = new HashMap<String, String>();
        keyMap.put("researchAreaCode", "001");
        researchAreas = (List<ResearchArea>) bos.findMatching(ResearchArea.class, keyMap);
        assertTrue(researchAreas.size() == 0);
        keyMap.put("researchAreaCode", "001.1");
        researchAreas = (List<ResearchArea>) bos.findMatching(ResearchArea.class, keyMap);
        assertTrue(researchAreas.size() == 0);

    }

    @Test
    public void testInsertRemoveResearchArea() {
        raDao.runScripts(qScripts);
        HashMap<String, String> keyMap = new HashMap<String, String>();
        keyMap.put("researchAreaCode", "001");
        List<ResearchArea> researchAreas = (List<ResearchArea>) bos.findMatching(ResearchArea.class, keyMap);
        assertTrue(researchAreas.size() == 1);
        keyMap.put("researchAreaCode", "001.1");
        researchAreas = (List<ResearchArea>) bos.findMatching(ResearchArea.class, keyMap);
        assertTrue(researchAreas.size() == 1);
        qScripts[0] = "remove((001))";
        qScripts[1] = "";
        raDao.runScripts(qScripts);
        keyMap = new HashMap<String, String>();
        keyMap.put("researchAreaCode", "001");
        researchAreas = (List<ResearchArea>) bos.findMatching(ResearchArea.class, keyMap);
        assertTrue(researchAreas.size() == 0);
        keyMap.put("researchAreaCode", "001.1");
        researchAreas = (List<ResearchArea>) bos.findMatching(ResearchArea.class, keyMap);
        assertTrue(researchAreas.size() == 0);

    }


}
