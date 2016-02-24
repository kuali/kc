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
package org.kuali.coeus.common.committee.impl.lookup.keyvalue;

import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.List;

/**
 * Finds the available set of dates where a protocol can be scheduled
 * for a review by a committee.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public abstract class CommitteeScheduleValuesFinderBase extends FormViewAwareUifKeyValuesFinderBase {
    

    private static final long serialVersionUID = 1914558119859827942L;

    /**
     * @return the list of &lt;key, value&gt; pairs of committees.  The first entry
     * is always &lt;"", "select:"&gt;.
     */
    @Override
    public List<KeyValue> getKeyValues() {
        return getCommitteeService().getAvailableCommitteeDates(getCommitteeId());
    }
    
    /**
     * Get the CommitteeBase Service.
     * @return the CommitteeBase Service
     */
    private CommitteeServiceBase getCommitteeService() {
        return KcServiceLocator.getService(getCommitteeServiceClassHook());
    }

    protected abstract Class<? extends CommitteeServiceBase> getCommitteeServiceClassHook();

    /**
     * Get the committee id.  Currently we are only concerned with
     * scheduling protocols.  The committee id is found in via the ProtocolFormBase.
     * Keep in mind that the user selects the committee via a drop-down and
     * thus the selected committee id is placed into the form.
     * @return
     */
    private String getCommitteeId() {
        String committeeId = "";
        Object formOrView = getFormOrView();
        if (formOrView instanceof ProtocolFormBase) {
            ProtocolFormBase protocolForm = (ProtocolFormBase) formOrView;
            committeeId = protocolForm.getActionHelper().getProtocolSubmitAction().getCommitteeId();
        }
        return committeeId;
    }
}
