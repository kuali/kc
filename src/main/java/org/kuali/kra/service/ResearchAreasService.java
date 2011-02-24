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
package org.kuali.kra.service;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public interface ResearchAreasService {
    /**
     * 
     * This method to get children research area codes of 'researchAreaCode'.
     * 
     * @param researchAreaCode
     * @param activeOnly - if true show only active research areas
     * @return
     */
    String getSubResearchAreasForTreeView(String researchAreaCode, boolean activeOnly);

    /**
     * 
     * This method is check whether the new research area code exist in DB.
     * @param researchAreaCode : new research area code
     * @param researchAreas : list of research area codes that are being removed, but has not been removed from DB yet.
     * @return
     */
    boolean isResearchAreaExist(String researchAreaCode, String researchAreas);

    /**
     * 
     * This method is updates the research area based on the raChangeXML.
     * @param raChangeXML : XML formatted changes that were being performed on questionnaire page.
     */
    void saveResearchAreas(String raChangeXML) throws ParserConfigurationException, IOException, SAXException;

}
