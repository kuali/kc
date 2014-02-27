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
package org.kuali.kra.protocol.noteattachment;

import java.util.List;

public interface ProtocolNotepadService {

    /**
     * Populate the updateUserFullName transient field in each ProtocolNotepadBase object in the list param. 
     * list.
     * @param protocolNotepads The list of ProtocolNotepadBase objects you wish to populate the updateUserFullName field on.
     */
    void setProtocolNotepadUpdateUsersName(List<ProtocolNotepadBase> protocolNotepads);
    
}
