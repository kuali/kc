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
package org.kuali.kra.timeandmoney.service;

public interface AwardFnaDistributionService {
    
    /**
     * 
     * This method returns true if the Award F and A Distribution panel equality validation should be displayed as a warning.
     * @return
     */
    boolean displayAwardFAndADistributionEqualityValidationAsWarning();
    
    /**
     * 
     * This method returns true if the Award F and A Distribution panel equality validation should be displayed as an error.
     * @return
     */
    boolean displayAwardFAndADistributionEqualityValidationAsError();
    
    /**
     * 
     * This method returns true if the F and A Distribution Equality validation should NOT be run.
     * @return
     */
    boolean disableFAndADistributionEqualityValidation();
}
