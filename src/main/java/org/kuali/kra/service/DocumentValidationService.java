/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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

import org.kuali.core.bo.PersistableBusinessObject;

public interface DocumentValidationService {
    
    /**
     * 
     * This method is to validate document recursively. 
     * TODO : this is a temporary service before rice fixes/enhances it.
     * @param businessObject
     * @param depth
     */
    public void validateDocumentRecursively(PersistableBusinessObject businessObject, int depth);

}
