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
package org.kuali.kra.protocol.correspondence;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ProtocolCorrespondenceTypeBase extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = -4725522827463252054L;
    public static final String APPROVAL_LETTER = "1";
    public static final String WITHDRAWAL_NOTICE = "16";
    public static final String GRANT_EXEMPTION_NOTICE = "17";
    public static final String CLOSURE_NOTICE = "26";
    public static final String ABANDON_NOTICE = "28";
    public static final String NOTICE_OF_DEFERRAL = "3";
    public static final String SRR_LETTER = "4";
    public static final String SMR_LETTER = "6";
    public static final String EXPEDITED_APPROVAL_LETTER = "5";
    public static final String SUSPENSION_NOTICE = "7";
    public static final String TERMINATION_NOTICE = "8";

    private String protoCorrespTypeCode;

    private String description;

    private String moduleId;
    
    private List<ProtocolCorrespondenceTemplateBase> protocolCorrespondenceTemplates;

    public ProtocolCorrespondenceTypeBase() {
        setProtocolCorrespondenceTemplates(new ArrayList<ProtocolCorrespondenceTemplateBase>());
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

    public List<ProtocolCorrespondenceTemplateBase> getProtocolCorrespondenceTemplates() {
        return protocolCorrespondenceTemplates;
    }

    /**
     * 
     * This method returns the default correspondence template.
     * @return default protocol correspondence template
     */
    public ProtocolCorrespondenceTemplateBase getDefaultProtocolCorrespondenceTemplate() {
        for (ProtocolCorrespondenceTemplateBase template : this.protocolCorrespondenceTemplates) {
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
    public List<ProtocolCorrespondenceTemplateBase> getCommitteeProtocolCorrespondenceTemplates() {
        List<ProtocolCorrespondenceTemplateBase> templates = new ArrayList<ProtocolCorrespondenceTemplateBase>();
        for (ProtocolCorrespondenceTemplateBase template : this.protocolCorrespondenceTemplates) {
            if (!StringUtils.equals(template.getCommitteeId(), Constants.DEFAULT_CORRESPONDENCE_TEMPLATE)) {
                templates.add(template);
            }
        }
        Collections.sort(templates);
        return templates;
    }

    /**
     * This method returns the correspondence templates which are specific to given committee.
     * @param committeeId
     * @return list of protocol correspondence templates
     */
    public List<ProtocolCorrespondenceTemplateBase> getCommitteeProtocolCorrespondenceTemplates(String committeeId) {
        List<ProtocolCorrespondenceTemplateBase> templates = new ArrayList<ProtocolCorrespondenceTemplateBase>();
        for (ProtocolCorrespondenceTemplateBase template : this.protocolCorrespondenceTemplates) {
            if (StringUtils.equals(template.getCommitteeId(), committeeId)) {
                templates.add(template);
            }
        }
        Collections.sort(templates);
        return templates;
    }
    
    public void setProtocolCorrespondenceTemplates(List<ProtocolCorrespondenceTemplateBase> protocolCorrespondenceTemplates) {
        this.protocolCorrespondenceTemplates = protocolCorrespondenceTemplates;
    }

}
