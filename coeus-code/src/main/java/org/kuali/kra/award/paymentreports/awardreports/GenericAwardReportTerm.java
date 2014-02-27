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
package org.kuali.kra.award.paymentreports.awardreports;

import java.sql.Date;

public interface GenericAwardReportTerm {
    /**
     * Gets the reportClassCode attribute. 
     * @return Returns the reportClassCode.
     */
    String getReportClassCode();
    
    /**
     * Gets the reportCode attribute. 
     * @return Returns the reportCode.
     */
    String getReportCode();

    /**
     * Gets the frequencyCode attribute. 
     * @return Returns the frequencyCode.
     */
    String getFrequencyCode();
    
    /**
     * Gets the frequencyBaseCode attribute. 
     * @return Returns the frequencyBaseCode.
     */
    String getFrequencyBaseCode();

    /**
     * Gets the ospDistributionCode attribute. 
     * @return Returns the ospDistributionCode.
     */
    String getOspDistributionCode();
    
    /**
     * Gets the dueDate attribute. 
     * @return Returns the dueDate.
     */
    Date getDueDate();
    
    boolean equalsInitialFields(GenericAwardReportTerm awardReportTerm);
}
