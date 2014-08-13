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
package org.kuali.kra.committee.lookup.keyvalue;

import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.List;

/**
 * Finds the available set of dates where a protocol can be scheduled
 * for a review by a committee.  This values finder is almost exactly 
 * the same as CommitteeScheduleValuesFinder, except that the committee
 * information comes from the AssignCmtSchedBean.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class CommitteeScheduleValuesFinder2 extends FormViewAwareUifKeyValuesFinderBase {
    
    /**
     * @return the list of &lt;key, value&gt; pairs of committees.  The first entry
     * is always &lt;"", "select:"&gt;.
     */
    public List<KeyValue> getKeyValues() {
        return getCommitteeService().getAvailableCommitteeDates(getCommitteeId());
    }
    
    /**
     * Get the Committee Service.
     * @return the Committee Service
     */
    private CommitteeService getCommitteeService() {
        return KcServiceLocator.getService(CommitteeService.class);
    }

    /**
     * Get the committee id.  Currently we are only concerned with
     * scheduling protocols.  The committee id is found in via the ProtocolForm.
     * Keep in mind that the user selects the committee via a drop-down and
     * thus the selected committee id is placed into the form.
     * @return
     */
    private String getCommitteeId() {
        String committeeId = "";
        Object form = getFormOrView();
        if (form instanceof ProtocolForm) {
            ProtocolForm protocolForm = (ProtocolForm) form;
            committeeId = protocolForm.getActionHelper().getAssignCmtSchedBean().getCommitteeId();
        }
        return committeeId;
    }
}
