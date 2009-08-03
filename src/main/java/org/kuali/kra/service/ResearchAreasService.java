/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.service;


public interface ResearchAreasService {
    /**
     * 
     * This method to get children research area codes of 'reresearchAreaCode'
     * 
     * @param researchAreaCode
     * @return
     */
    public String getSubResearchAreasForTreeView(String researchAreaCode);

    /**
     * 
     * This method is check whetehr the new research area code exist in DB
     * @param researchAreaCode : new research area code
     * @param researchAreas : list of research area codes that are being removed, but has not been removed from DB yet.
     * @return
     */
    public boolean isResearchAreaExist(String researchAreaCode, String researchAreas);

    /**
     * 
     * This method is call cal dao to run a list of sqlscripts.
     * @param sqlScripts : a sequence of sql scripts that were being performed on questionnaire page.
     */
    public void saveResearchAreas(String sqlScripts);

}
