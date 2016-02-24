/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.kra.institutionalproposal.proposallog;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.web.struts.form.KualiMaintenanceForm;
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
        if (form instanceof KualiMaintenanceForm) {
        	ProposalLog proposalLog = (ProposalLog)(((KualiMaintenanceForm)form).getDocument().getNoteTarget());
            
            if (proposalLog == null || proposalLog.getProposalLogType() == null) {
                filterResults = false;
            } else if (StringUtils.equalsIgnoreCase(proposalLog.getProposalLogType().getProposalLogTypeCode(), ProposalLogUtils.getProposalLogTemporaryTypeCode())) {
                // valid user choices are Temporary and Void
                validStatuses.add(ProposalLogUtils.getProposalLogTemporaryStatusCode());
                validStatuses.add(ProposalLogUtils.getProposalLogVoidStatusCode());
            } else {
                // valid user choices are Pending and Void
                validStatuses.add(ProposalLogUtils.getProposalLogPendingStatusCode());
                validStatuses.add(ProposalLogUtils.getProposalLogVoidStatusCode());
            }
            // when Disclosure Type is implemented, the above else might need to be separated into 2 different options
        } else {
        	filterResults = false;
        }       
        for (ProposalLogStatus status : statuses) {
            if (!filterResults || validStatuses.contains(status.getProposalLogStatusCode())) {
                retval.add(new ConcreteKeyValue(status.getProposalLogStatusCode(), status.getDescription()));
            }
        }
        return retval;
    }
}
