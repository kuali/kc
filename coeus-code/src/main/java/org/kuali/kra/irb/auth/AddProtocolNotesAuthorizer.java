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
package org.kuali.kra.irb.auth;

import org.kuali.kra.irb.actions.amendrenew.ProtocolModule;

/**
 * The Add Protocol Notes Authorizer determines if a user has the right
 * to create protocol notes
 */
public class AddProtocolNotesAuthorizer extends ModifyAmendmentAuthorizer {

    protected AddProtocolNotesAuthorizer() {
        super(ProtocolModule.ADD_MODIFY_ATTACHMENTS);
    }

}
