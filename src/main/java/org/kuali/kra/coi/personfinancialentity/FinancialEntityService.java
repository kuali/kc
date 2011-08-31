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
}
