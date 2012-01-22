/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.irb.correspondence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;

public class ProtocolCorrespondenceType extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = -4725522827463252054L;

    private String protoCorrespTypeCode;

    private String description;

    private String moduleId;

    private List<ProtocolCorrespondenceTemplate> protocolCorrespondenceTemplates;

    public ProtocolCorrespondenceType() {
        setProtocolCorrespondenceTemplates(new ArrayList<ProtocolCorrespondenceTemplate>());
    }

    public String getProtoCorrespTypeCode() {
        return protoCorrespTypeCode;
    }

    public void setProtoCorrespTypeCode(String protoCorrespTypeCode) {
        this.protoCorrespTypeCode = protoCorrespTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public List<ProtocolCorrespondenceTemplate> getProtocolCorrespondenceTemplates() {
        return protocolCorrespondenceTemplates;
    }

    /**
     * 
     * This method returns the default correspondence template.
     * @return default protocol correspondence template
     */
    public ProtocolCorrespondenceTemplate getDefaultProtocolCorrespondenceTemplate() {
        for (ProtocolCorrespondenceTemplate template : this.protocolCorrespondenceTemplates) {
            if (StringUtils.equals(template.getCommitteeId(), Constants.DEFAULT_CORRESPONDENCE_TEMPLATE)) {
                return template;
            }
        }
        return null;
    }

    /**
     * 
     * This method returns the correspondence templates which are specific to a committee.
     * @return list of protocol correspondence templates
     */
    public List<ProtocolCorrespondenceTemplate> getCommitteeProtocolCorrespondenceTemplates() {
        List<ProtocolCorrespondenceTemplate> templates = new ArrayList<ProtocolCorrespondenceTemplate>();
        for (ProtocolCorrespondenceTemplate template : this.protocolCorrespondenceTemplates) {
            if (!StringUtils.equals(template.getCommitteeId(), Constants.DEFAULT_CORRESPONDENCE_TEMPLATE)) {
                templates.add(template);
            }
        }
        Collections.sort(templates);
        return templates;
    }

    public void setProtocolCorrespondenceTemplates(List<ProtocolCorrespondenceTemplate> protocolCorrespondenceTemplates) {
        this.protocolCorrespondenceTemplates = protocolCorrespondenceTemplates;
    }
}
