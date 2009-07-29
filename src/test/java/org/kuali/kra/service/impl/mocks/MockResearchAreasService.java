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
package org.kuali.kra.service.impl.mocks;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.service.ResearchAreasService;

public class MockResearchAreasService implements ResearchAreasService {
    
    private List<ResearchArea> researchAreas = new ArrayList<ResearchArea>();

    public String getAscendantList(String researchAreaCode) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getInitialResearchAreasList() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getSubResearchAreasForTreeView(String researchAreaCode) {
        // TODO Auto-generated method stub
        return null;
    }

    public void addResearchAreas(String researchAreaCode, String parentResearchAreaCode, boolean hasChildrenFlag) {
        ResearchArea researchArea = new ResearchArea();
        researchArea.setResearchAreaCode(researchAreaCode);
        researchArea.setParentResearchAreaCode(parentResearchAreaCode);
        researchArea.setHasChildrenFlag(hasChildrenFlag);
        researchArea.setDescription("research area "+researchAreaCode);
        researchAreas.add(researchArea);
    }
    public boolean isResearchAreaExist(String researchAreaCode, String researchAreas) {
        // TODO Auto-generated method stub
        return false;
    }

    public void saveResearchAreas(String sqlScripts) {
        // TODO Auto-generated method stub
        
    }

}
