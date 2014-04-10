/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.rule;

import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.rice.krad.bo.BusinessObject;

/**
 * Interface for rule implementations to go from where an action changes a <code>{@link ProposalPerson}</code>
 */
public interface ChangeKeyPersonRule extends org.kuali.rice.krad.rules.rule.BusinessRule {

    /**
     * To process Change event business rules for <code>{@link ProposalPerson}</code> instances. Any <code>{@link BusinessObject}</code>
     * can change the state of a <code>{@link ProposalPerson}</code> instance. This <code>{@link BusinessObject}</code> instance is considered
     *  to be the <code>source</code> of the event.
     * 
     * @param proposalPerson
     * @param source the event source acting on the rule
     * @return boolean pass or fail
     */
    public boolean processChangeKeyPersonBusinessRules(ProposalPerson proposalPerson, BusinessObject source,int index);

}
