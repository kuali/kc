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
package org.kuali.kra.irb.protocol;

import org.kuali.kra.irb.document.ProtocolDocument;

/**
 * 
 * This class is the API for protocol service. 
 */
public interface ProtocolProtocolService {

    /**
     * 
     * This method is to load protocol data for edit.  It is primarily called by 'edit' in protocol lookup.
     * @param document
     * @param protocolNumber
     */
    void loadProtocolForEdit(ProtocolDocument document, String protocolNumber);
}
