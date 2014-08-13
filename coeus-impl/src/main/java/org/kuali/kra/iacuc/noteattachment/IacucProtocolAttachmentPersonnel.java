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
package org.kuali.kra.iacuc.noteattachment;

import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentPersonnelBase;

public class IacucProtocolAttachmentPersonnel extends ProtocolAttachmentPersonnelBase {

    private static final long serialVersionUID = 5529222336473432436L;
    
    private static final String GROUP_CODE = "2";

    public IacucProtocolAttachmentPersonnel(ProtocolBase protocol) {
        super(protocol);
    }
    
    public IacucProtocolAttachmentPersonnel() {
        super();
    }
    
    @Override
    public String getGroupCode() {
        return GROUP_CODE;
    }

    @Override
    public String getAttachmentDescription() {
        return "Personnel Attachment";
    }

}
