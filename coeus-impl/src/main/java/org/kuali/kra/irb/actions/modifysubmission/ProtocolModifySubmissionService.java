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
package org.kuali.kra.irb.actions.modifysubmission;

import org.kuali.kra.irb.ProtocolDocument;

/**
 * 
 * This class defines the functions required to modify a protocol submission.
 */
public interface ProtocolModifySubmissionService {
    
    /**
     * 
     * This method updates the protocol submission and persists the changes.
     * @param protocolDocument
     * @param bean
     * @throws Exception
     */
    void modifySubmission(ProtocolDocument protocolDocument, ProtocolModifySubmissionBean bean) throws Exception;

}
