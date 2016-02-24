/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
