/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeMembershipBase;
import org.kuali.kra.protocol.ProtocolBase;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public interface ResearchAreasServiceBase {
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
     * This method checks that neither the given research area nor any of its descendants are referenced by 
     * any committee, committee member or protocol (current or past). 
     * This method should be called and checked to return true before calling the method to delete a research area (and its descendants).  
     * @param researchAreaCode
     * @return
     */
    boolean checkResearchAreaAndDescendantsNotReferenced(String researchAreaCode);
    
    
    /**
     * This method will return some current version of committee, committee member or protocol that references either the given research area or one of its (active) descendants.
     * It ignores past versions. If the given research area is not referenced by any current committee, committee member or protocol, 
     * then the method returns <code>ResearchAreaCurrentReferencerHolder.NO_REFERENCER</code>.   
     * This method should be called and its return value should be checked to ensure that there are no current referencers before calling the method to 
     * deactivate a research area (and its descendants).  
     * @param researchAreaCode
     * @return
     */
    ResearchAreaCurrentReferencerHolderBase getAnyCurrentReferencerForResearchAreaOrDescendant(String researchAreaCode);
    
    
    /**
     * This method will return the instance of a current ProtocolBase BO, saved in the db, that references the research area with the given code.
     * If no such saved protocol instance exists in the db, then this method returns null.
     * @param researchAreaCode
     * @return
     */
    ProtocolBase getCurrentProtocolReferencingResearchArea(String researchAreaCode);
    
    /**
     * This method will return the instance of a current CommitteeBase BO, saved in the db, that references the research area with the given code.
     * If no such saved committee instance exists in the db, then this method returns null.
     * @param researchAreaCode
     * @return
     */
    CommitteeBase getCurrentCommitteeReferencingResearchArea(String researchAreaCode);
    
    /**
     * This method will return the instance of a current CommitteeMembershipBase BO, saved in the db, that references the research area with the given code.
     * If no such saved committee membership instance exists in the db, then this method returns null.
     * @param researchAreaCode
     * @return
     */
    CommitteeMembershipBase getCurrentCommitteeMembershipReferencingResearchArea(String researchAreaCode);
    
    /**
     * This method will deactivate the research area that has the given <code>researchAreaCode</code>, and will also 
     * recursively deactivate all of its descendant research areas as well.
     * @param researchAreaCode
     */
    public void deactivateResearchAreaAndDescendants(String researchAreaCode) throws Exception;
    
    
    /**
     * This method will delete the research area that has the given <code>researchAreaCode</code> and will also 
     * recursively delete all of its descendant research areas as well.
     * @param researchAreaCode
     */
    public void deleteResearchAreaAndDescendants(String researchAreaCode) throws Exception;

    /**
     * 
     * This method is updates the research area based on the raChangeXML.
     * @param raChangeXML : XML formatted changes that were being performed on questionnaire page.
     */
    void saveResearchAreas(String raChangeXML) throws ParserConfigurationException, IOException, SAXException;

}
