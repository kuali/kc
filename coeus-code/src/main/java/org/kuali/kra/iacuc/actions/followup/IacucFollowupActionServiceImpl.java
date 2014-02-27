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
package org.kuali.kra.iacuc.actions.followup;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.submit.IacucValidProtocolActionAction;
import org.kuali.kra.protocol.actions.followup.FollowupActionServiceImplBase;


public class IacucFollowupActionServiceImpl extends FollowupActionServiceImplBase<IacucValidProtocolActionAction> implements IacucFollowupActionService {
 
    
    private static final Log LOG = LogFactory.getLog(IacucFollowupActionServiceImpl.class);

    @Override
    protected Class<IacucValidProtocolActionAction> getValidProtocolActionActionClassHook() {
        return IacucValidProtocolActionAction.class;
    }

    @Override
    protected String getProtocolActionTypeCodeForRecordCommitteeDecisionHook() {
        return IacucProtocolActionType.RECORD_COMMITTEE_DECISION;
    }

    @Override
    protected Log getLogHook() {
        return IacucFollowupActionServiceImpl.LOG;
    }
    

}
