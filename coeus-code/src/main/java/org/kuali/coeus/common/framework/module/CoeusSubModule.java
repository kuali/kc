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
package org.kuali.coeus.common.framework.module;


import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class CoeusSubModule extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    public static final String AMENDMENT_RENEWAL = "1";

    public static final String PROTOCOL_SUBMISSION = "2";

    public static final String ZERO_SUBMODULE = "0";

    public static final String PROPOSAL_S2S_SUBMODULE = "2";

    public static final String PROPOSAL_PERSON_CERTIFICATION = "3";
    
    public static final String COI_ANNUAL_DISCL_SUBMODULE = "1";
    public static final String COI_UPDATE_MASTER_DISCL_SUBMODULE = "2";
    public static final String COI_EVENT_AWARD_DISCL_SUBMODULE = "3";
    public static final String COI_EVENT_PROPOSAL_DISCL_SUBMODULE = "4";
    public static final String COI_EVENT_IRB_PROTOCOL_DISCL_SUBMODULE = "5";
    public static final String COI_EVENT_IACUC_PROTOCOL_DISCL_SUBMODULE = "6";
    public static final String COI_EVENT_TRAVEL_DISCL_SUBMODULE = "7";
    public static final String COI_SCREENING_SUBMODULE = "8";

    public static final String AMENDMENT = "4";

    public static final String RENEWAL = "3";
    
    public static final String CONTINUATION = "5";

    private Integer coeusSubModuleId;

    private String moduleCode;

    private String subModuleCode;

    private String description;

    private CoeusModule coeusModule;
    
    private boolean requireUniqueQuestionnareUsage;

    public CoeusSubModule() {
    }

    public Integer getCoeusSubModuleId() {
        return coeusSubModuleId;
    }

    public void setCoeusSubModuleId(Integer coeusSubModuleId) {
        this.coeusSubModuleId = coeusSubModuleId;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public String getSubModuleCode() {
        return subModuleCode;
    }

    public void setSubModuleCode(String subModuleCode) {
        this.subModuleCode = subModuleCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CoeusModule getCoeusModule() {
        return coeusModule;
    }

    public void setCoeusModule(CoeusModule coeusModule) {
        this.coeusModule = coeusModule;
    }

    public boolean isRequireUniqueQuestionnareUsage() {
        return requireUniqueQuestionnareUsage;
    }

    public void setRequireUniqueQuestionnareUsage(boolean requireUniqueQuestionnareUsage) {
        this.requireUniqueQuestionnareUsage = requireUniqueQuestionnareUsage;
    }
}
