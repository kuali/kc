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
package org.kuali.kra.irb.actions.correspondence;

import org.kuali.kra.irb.Protocol;

/**|
 * 
 * This class will be implemented by the protocol action services.
 */
public interface ActionCorrespondenceGenerationService {
    /**
     * 
     * This method will call the ProtocolGenerateCorrespondenceService, the ProtocolXMLStreamService(needs to be dummied up), and pass 
     * those returns to the print service, and attach the generated PDF to the Protocol.
     * @param protocol a Protocol object.
     */
    void generateCorrespondenceDocumentAndAttach(Protocol protocol);
}
