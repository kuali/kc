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
package org.kuali.kra.iacuc.actions.modifysubmission;

import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.actions.IacucActionHelper;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeService;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.List;

/**
 * Finds the available set of dates where a protocol can be scheduled
 * for a review by a committee.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@SuppressWarnings("deprecation")
public class IacucProtocolModifySubmissionScheduleValuesFinder extends FormViewAwareUifKeyValuesFinderBase {
    

    private static final long serialVersionUID = 951593069011237294L;

    /**
     * @return the list of &lt;key, value&gt; pairs of committees.  The first entry
     * is always &lt;"", "select:"&gt;.
     *
     */
    @Override
    public List<KeyValue> getKeyValues() {
        return getCommitteeService().getAvailableCommitteeDates(getCommitteeId());
    }
    
    /**
     * Get the Committee Service.
     * @return the Committee Service
     */
    private CommitteeServiceBase getCommitteeService() {
        return KcServiceLocator.getService(IacucCommitteeService.class);
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
        if (form instanceof IacucProtocolForm) {
            IacucProtocolForm protocolForm = (IacucProtocolForm) form;
            committeeId = ((IacucActionHelper) protocolForm.getActionHelper()).getIacucProtocolModifySubmissionBean().getCommitteeId();
        }
        return committeeId;
    }
}
