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
package org.kuali.kra.protocol;

/**
 * The ProtocolBase Version Service.
 */
public interface ProtocolVersionService {

    /**
     * Create a new version of a protocol document. 
     * @param protocolDocument the protocol document to version
     * @return the new versioned protocol document
     * @throws Exception
     */
    public ProtocolDocumentBase versionProtocolDocument(ProtocolDocumentBase protocolDocument) throws Exception;
    
    /**
     * Get a particular version of a protocol.
     * @param protocolNumber
     * @param sequenceNumber
     * @return
     */
    public ProtocolBase getProtocolVersion(String protocolNumber, Integer sequenceNumber);
}
