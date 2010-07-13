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
package org.kuali.kra.irb.actions.expediteapproval;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.approve.ProtocolApproveBean;

/**
 * The Protocol Expedite Approval Service is used to grant an
 * expedited approval to a protocol.
 */
public interface ProtocolExpediteApprovalService {

    /**
     * Grant an expedited approval to a protocol that is
     * submitted to the IRB office.
     * @param protocol
     * @param expeditedApprovalBean
     * @throws Exception
     */
    public void grantExpeditedApproval(Protocol protocol, ProtocolApproveBean expeditedApprovalBean) throws Exception;
}
