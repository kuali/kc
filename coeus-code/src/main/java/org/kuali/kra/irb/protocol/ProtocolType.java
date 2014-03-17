/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.irb.protocol;

import org.kuali.kra.protocol.protocol.ProtocolTypeBase;

public class ProtocolType extends ProtocolTypeBase {


    private static final long serialVersionUID = 5222672499618671313L;

    private String protocolTypeCode;

    private String description;
    
    private boolean globalFlag;

    public ProtocolType() {
    }

    public String getProtocolTypeCode() {
        return protocolTypeCode;
    }

    public void setProtocolTypeCode(String protocolTypeCode) {
        this.protocolTypeCode = protocolTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public boolean isGlobalFlag() {
		return globalFlag;
	}

	public void setGlobalFlag(boolean globalFlag) {
		this.globalFlag = globalFlag;
	}
}