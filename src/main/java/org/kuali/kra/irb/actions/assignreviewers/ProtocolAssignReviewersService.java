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
package org.kuali.kra.irb.actions.assignreviewers;

import java.util.List;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.submit.ProtocolReviewerBean;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;

/**
 * Responsible for assigning reviewers to a protocol.
 */
public interface ProtocolAssignReviewersService {

    /**
     * Get the current submission which may be pending or already submitted to the IRB committee.
     * @param protocol
     * @return the submission or null if there is none
     */
    public ProtocolSubmission getCurrentSubmission(Protocol protocol);
    
    /**
     * Assigns the reviewers to the protocol.
     * @param protocol the protocol
     * @param reviewerBeans the list of reviewers
     */
    public void assignReviewers(Protocol protocol, List<ProtocolReviewerBean> reviewerBeans);
}
