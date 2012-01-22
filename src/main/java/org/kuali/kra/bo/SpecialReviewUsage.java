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
package org.kuali.kra.bo;


/**
 * Defines the mapping between a Special Review Type and a module to allow per-module customization of certain Special Review Types.
 */
public class SpecialReviewUsage extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1123437346869395158L;

    private Long specialReviewUsageId;

    private String specialReviewTypeCode;

    private String moduleCode;

    private boolean global;

    private boolean active;

    private SpecialReviewType specialReviewType;

    private CoeusModule coeusModule;

    public Long getSpecialReviewUsageId() {
        return specialReviewUsageId;
    }

    public void setSpecialReviewUsageId(Long specialReviewUsageId) {
        this.specialReviewUsageId = specialReviewUsageId;
    }

    public String getSpecialReviewTypeCode() {
        return specialReviewTypeCode;
    }

    public void setSpecialReviewTypeCode(String specialReviewTypeCode) {
        this.specialReviewTypeCode = specialReviewTypeCode;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public SpecialReviewType getSpecialReviewType() {
        return specialReviewType;
    }

    public void setSpecialReviewType(SpecialReviewType specialReviewType) {
        this.specialReviewType = specialReviewType;
    }

    public CoeusModule getCoeusModule() {
        return coeusModule;
    }

    public void setCoeusModule(CoeusModule coeusModule) {
        this.coeusModule = coeusModule;
    }
}
