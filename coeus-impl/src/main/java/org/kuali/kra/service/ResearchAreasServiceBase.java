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
