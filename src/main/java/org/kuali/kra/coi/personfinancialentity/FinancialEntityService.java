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
package org.kuali.kra.coi.personfinancialentity;

import java.util.List;

import org.kuali.kra.service.VersionException;

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
     * @return
     * @throws VersionException
     */
    PersonFinIntDisclosure versionPersonFinintDisclosure(PersonFinIntDisclosure personFinIntDisclosure, List<FinEntityDataMatrixBean> newRelationDetails) throws VersionException;

    /**
     * get next entity number from DB sequence
     * This method...
     * @return
     */
    String getNextEntityNumber();
    
    List<PersonFinIntDisclosure> getFinDisclosureVersions(String entityNumber);
    
    PersonFinIntDisclosure getCurrentFinancialEntities(String entityNumber);
}
