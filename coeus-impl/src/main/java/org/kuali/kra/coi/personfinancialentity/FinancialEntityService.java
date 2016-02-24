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
package org.kuali.kra.coi.personfinancialentity;

import org.kuali.coeus.common.framework.version.VersionException;
import org.kuali.kra.coi.notesandattachments.attachments.FinancialEntityAttachment;

import java.util.List;

/**
 * 
 * This is an interface for declaration of methods related to financial entity maintenance
 */
public interface FinancialEntityService {
    
    /**
     * 
     * This method to get a list of active or inactive financial entities of this personid
     * only return the last version, ie, current one.
     * @param personId
     * @param active
     * @return
     */
    List<PersonFinIntDisclosure> getFinancialEntities(String personId, boolean active);
    
    /**
     * 
     * This method is to get the financial entity reporter.  If it does not exist, then it create on based on personId
     * @param personId
     * @return
     */
    FinancialEntityReporter getFinancialEntityReporter(String personId);
    
    /**
     * 
     * This method is to get all the active relationship types
     * @return
     */
    List<FinIntEntityRelType> getFinancialEntityRelationshipTypes();
    
    /**
     * 
     * This method is to set up the datamatrix bean, which will be used for UI
     * This bean is formed by datagroup/datamatrix/relationtype
     * @return
     */
    List<FinEntityDataMatrixBean> getFinancialEntityDataMatrix();
    
    /**
     * 
     * This method is to convert UI data matrix to fin disclosure detail
     * @param dataMatrixs
     * @param entityNumber
     * @param sequenceNumber
     * @return
     */
    List<PersonFinIntDisclDet> getFinDisclosureDetails(List<FinEntityDataMatrixBean> dataMatrixs, String entityNumber, Integer sequenceNumber);

    /**
     * 
     * This method is to get the datamatrix bean and then populate the existing data from person fin discl detail
     * @param disclosureDetails
     * @return
     */
    List<FinEntityDataMatrixBean> getFinancialEntityDataMatrixForEdit(List<PersonFinIntDisclDet> disclosureDetails);
    
    /**
     * 
     * This method to version person FE disclosure
     * @param personFinIntDisclosure
     * @param newRelationDetails
     * @param newFinancialEntityAttachments
     * @return
     * @throws VersionException
     */
    PersonFinIntDisclosure versionPersonFinintDisclosure(PersonFinIntDisclosure personFinIntDisclosure, 
                                                        List<FinEntityDataMatrixBean> newRelationDetails,
                                                        List<FinancialEntityAttachment> newFinancialEntityAttachments) throws VersionException;

    /**
     * get next entity number from DB sequence
     */
    String getNextEntityNumber();
    
    List<PersonFinIntDisclosure> getFinDisclosureVersions(String entityNumber);
    
    PersonFinIntDisclosure getCurrentFinancialEntities(String entityNumber);
    
    /**
     * 
     * This method returns the current list of FinancialEntityAttachment objects for the specified FE
     * and null when input parameter is null.
     * @param entityId
     * @return null when input parameter is null; otherwise list of FinancialEntityAttachment objects 
     */   
    public List<FinancialEntityAttachment> retrieveFinancialEntityAttachmentsFor(Long entityId);
}
