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
package org.kuali.kra.iacuc.auth;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.actions.table.IacucProtocolTableService;

public abstract class IacucProtocolTableAuthorizerBase extends IacucProtocolAuthorizer {
    
    IacucProtocolTableService iacucProtocolTableService;
    
    public IacucProtocolTableService getIacucProtocolTableService() {
        return iacucProtocolTableService;
    }

    public void setIacucProtocolTableService(IacucProtocolTableService iacucProtocolTableService) {
        this.iacucProtocolTableService = iacucProtocolTableService;
    }

    protected boolean checkIfSubmissionCanBeBumped(IacucProtocol protocol) {
        boolean retVal = false;
        IacucProtocolSubmission submission = (IacucProtocolSubmission) protocol.getProtocolSubmission();
        if( (null != submission) && (null != getIacucProtocolTableService().getNextScheduleForCommittee(submission.getCommitteeSchedule())) ) {
            retVal = true;
        }
        return retVal;
    }

}
