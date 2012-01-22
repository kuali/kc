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
package org.kuali.kra.committee.rule.event;

import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.rice.krad.rules.rule.event.KualiDocumentEvent;

/**
*
* This interface addresses the lookup of the <code>{@link CommitteeMembership}</code> of an event.
*
* @author Kuali Research Administration Team (kc.dev@kuali.org)
*/
public interface CommitteeMembershipEvent extends KualiDocumentEvent {
    /**
     * 
     * Get the <code>{@link CommitteeMembership}</code> of this event.
     * 
     * @return <code>CommitteeMembership</code>
     */
    CommitteeMembership getCommitteeMembership();
}
