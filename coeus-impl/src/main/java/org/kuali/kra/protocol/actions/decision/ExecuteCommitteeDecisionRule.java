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
package org.kuali.kra.protocol.actions.decision;

import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class defines the methods needed for committee decision rules.
 */
public interface ExecuteCommitteeDecisionRule<CD extends CommitteeDecision<?> > extends BusinessRule {
   
    /**
     * 
     * This method will check for valid user input and attach error message to fields as needed.
     * @param document the ProtocolDocumentBase
     * @param actionBean a CommitteeDecision bean
     * @return a boolean as to whether the user input is valid
     */
    boolean proccessCommitteeDecisionRule(ProtocolDocumentBase document, CD actionBean);

}
