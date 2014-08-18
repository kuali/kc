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
package org.kuali.kra.institutionalproposal.proposallog;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.web.struts.form.InquiryForm;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.krad.service.KeyValuesService;

import java.util.*;


public class ProposalLogStatusValuesFinder extends FormViewAwareUifKeyValuesFinderBase {

    /**
     * Returns valid status choices based on the current ProposalLog's Type.  Choices are selected as follows:<br />
     * Temporary:
     * <ul>
     * <li>Temporary</li>
     * <li>Void</li>
     * </ul>
     * Permanent (and Disclosure):
     * <ul>
     * <li>Pending</li>
     * <li>Void</li>
     * </ul>
     * When Disclosure type is implemented Permanent and Disclosure may need to be separated.
     * 
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> retval = new ArrayList<KeyValue>(); 
        KeyValuesService keyValuesService = (KeyValuesService) KcServiceLocator.getService("keyValuesService");
        Collection<ProposalLogStatus> statuses = keyValuesService.findAll(ProposalLogStatus.class);
        Set<String> validStatuses = new HashSet<String>();
        Object form = getFormOrView();
        retval.add(new ConcreteKeyValue("", "select"));
        boolean filterResults = true;
        if (form instanceof LookupForm || form instanceof InquiryForm) {
            filterResults = false;
        }
        else {
            ProposalLog proposalLog = (ProposalLog)(((KualiMaintenanceForm)form).getDocument().getNoteTarget());
            
            if (proposalLog == null || proposalLog.getProposalLogType() == null) {
                filterResults = false;
            }
            else if (StringUtils.equalsIgnoreCase(proposalLog.getProposalLogType().getProposalLogTypeCode(), ProposalLogUtils.getProposalLogTemporaryTypeCode())) {
                // valid user choices are Temporary and Void
                validStatuses.add(ProposalLogUtils.getProposalLogTemporaryStatusCode());
                validStatuses.add(ProposalLogUtils.getProposalLogVoidStatusCode());
            }
            else {
                // valid user choices are Pending and Void
                validStatuses.add(ProposalLogUtils.getProposalLogPendingStatusCode());
                validStatuses.add(ProposalLogUtils.getProposalLogVoidStatusCode());
            }
            // when Disclosure Type is implemented, the above else might need to be separated into 2 different options
        }
        for (ProposalLogStatus status : statuses) {
            if (!filterResults || validStatuses.contains(status.getProposalLogStatusCode())) {
                retval.add(new ConcreteKeyValue(status.getProposalLogStatusCode(), status.getDescription()));
            }
        }
        return retval;
    }
}
