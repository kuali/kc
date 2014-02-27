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
package org.kuali.kra.irb.noteattachment;

import org.kuali.kra.irb.Protocol;
import org.kuali.kra.protocol.noteattachment.ProtocolNotepadBase;

/**
 * The Protocol Notepad class.
 */
public class ProtocolNotepad extends ProtocolNotepadBase {

    private static final long serialVersionUID = -294125058992878907L;
    
     /**
      * empty ctor to satisfy JavaBean convention.
      */
     public ProtocolNotepad() {
         super();
     }
    
     /**
      * Convenience ctor to add the protocol as an owner.
      * 
      * <p>
      * This ctor does not validate any of the properties.
      * </p>
      * 
      * @param protocol the protocol.
      */
     public ProtocolNotepad(final Protocol protocol) {
         super(protocol);
     }
}
