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
package org.kuali.kra.protocol.auth;

import org.kuali.kra.protocol.Protocol;

public class ContinuationAuthorizer extends ProtocolAuthorizer {

    @Override
    public boolean isAuthorized(String userId, ProtocolTask task) {
        return false;
    }

    protected final boolean isAmendmentOrRenewalOrContinuation(Protocol protocol) {
        return protocol.getProtocolNumber() != null &&
               (protocol.getProtocolNumber().contains("A") ||
                       protocol.getProtocolNumber().contains("C") ||
                       protocol.getProtocolNumber().contains("R"));
    }

}
