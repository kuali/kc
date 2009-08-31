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
package org.kuali.kra.irb;

/**
 * The Protocol Version Service.
 */
public interface ProtocolVersionService {

    /**
     * Create a new version of a protocol document.  Does not save the document to
     * the database.
     * @param protocolDocument the protocol document to version
     * @return the new versioned protocol document (unsaved)
     * @throws Exception
     */
    public ProtocolDocument versionProtocolDocument(ProtocolDocument protocolDocument) throws Exception;
}
