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
package org.kuali.coeus.common.protocol.impl.note;

import org.kuali.coeus.common.protocol.framework.note.DeleteProtocolNotepadRule;

/**
 * Implementation of {@link org.kuali.coeus.common.protocol.framework.note.DeleteProtocolNotepadRule DeleteProtocolNotepadRule}.
 * @see org.kuali.coeus.common.protocol.framework.note.DeleteProtocolNotepadRule for details
 */
public class DeleteProtocolNotepadRuleImpl implements DeleteProtocolNotepadRule {

    @Override
    public boolean processDeleteProtocolNotepadRules(DeleteProtocolNotepadEvent event) {
        //TODO: Might have to do some more authorizing here...
        return true;
    }
}
