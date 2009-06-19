/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.actions.amendrenew;

import org.kuali.kra.irb.ProtocolDocument;

/**
 * Protocol Amendment/Renewal Service.
 */
public interface ProtocolAmendRenewService {

    /**
     * Create an amendment.  An amendment is simply a copy of a protocol with
     * specific sections (modules) designated for modification.
     * @param protocolDocument the protocol document to create an amendment from
     * @param amendmentBean the amendment info (which modules to be amended)
     * @return the amendment's document number
     * @throws Exception
     */
    public String createAmendment(ProtocolDocument protocolDocument, ProtocolAmendmentBean amendmentBean) throws Exception;

    /**
     * Create a Renewal without an Amendment.
     * @param protocolDocument the protocol document to renew
     * @return the renewal's document number
     * @throws Exception
     */
    public String createRenewal(ProtocolDocument protocolDocument) throws Exception;
    
    /**
     * Create a Renewal with an Amendment.
     * @param protocolDocument the protocol document to renew/amend
     * @param amendmentBean the amendment info (which modules to be amended)
     * @return the renewal's document number
     * @throws Exception
     */
    public String createRenewalWithAmendment(ProtocolDocument protocolDocument, ProtocolAmendmentBean amendmentBean) throws Exception;
}
