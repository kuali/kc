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
package org.kuali.kra.iacuc.actions.assignCmt;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.rice.krad.util.GlobalVariables;

public class IacucProtocolAssignCmtRuleImpl extends KcTransactionalDocumentRuleBase implements IacucProtocolAssignCmtRule {

    public boolean processAssignToCommittee(ProtocolDocumentBase document, IacucProtocolAssignCmtBean actionBean) {
        boolean valid = true;
        if (StringUtils.isBlank(actionBean.getCommitteeId())) {
            valid = false;
            GlobalVariables.getMessageMap().putError(Constants.PROTOCOL_ASSIGN_CMT_SCHED_ACTION_PROPERTY_KEY + ".committeeId", 
                                                   KeyConstants.ERROR_PROTOCOL_COMMITTEE_NOT_SELECTED);
        }
        return valid;
    }


}
