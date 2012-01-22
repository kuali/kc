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
package org.kuali.kra.irb.actions.amendrenew;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

@SuppressWarnings("serial")
public class ProtocolModule extends KraPersistableBusinessObjectBase {

    public static final String GENERAL_INFO = "001";

    public static final String PROTOCOL_PERSONNEL = "002";

    public static final String AREAS_OF_RESEARCH = "004";

    public static final String SUBJECTS = "006";

    public static final String SPECIAL_REVIEW = "007";

    public static final String ADD_MODIFY_ATTACHMENTS = "008";

    public static final String PROTOCOL_REFERENCES = "016";

    public static final String PROTOCOL_ORGANIZATIONS = "017";

    public static final String OTHERS = "023";

    public static final String FUNDING_SOURCE = "024";

    public static final String PROTOCOL_PERMISSIONS = "025";

    public static final String QUESTIONNAIRE = "026";

    private String protocolModuleCode;

    private String description;

    public ProtocolModule() {
    }

    public String getProtocolModuleCode() {
        return protocolModuleCode;
    }

    public void setProtocolModuleCode(String protocolModuleCode) {
        this.protocolModuleCode = protocolModuleCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
