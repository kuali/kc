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
package org.kuali.coeus.propdev.impl.s2s;

import org.kuali.coeus.propdev.api.s2s.S2sUserAttachedFormAttFileContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class S2sUserAttachedFormAttFile extends KcPersistableBusinessObjectBase implements S2sUserAttachedFormAttFileContract { 

    private static final long serialVersionUID = 435752267453958277L;
    private Long id; 
    private Long s2sUserAttachedFormAttId; 
    private byte[] attachment; 
    
    public S2sUserAttachedFormAttFile() { 

    }

    public Long getS2sUserAttachedFormAttId() {
        return s2sUserAttachedFormAttId;
    }

    public void setS2sUserAttachedFormAttId(Long s2sUserAttachedFormAttId) {
        this.s2sUserAttachedFormAttId = s2sUserAttachedFormAttId;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	} 
    
}