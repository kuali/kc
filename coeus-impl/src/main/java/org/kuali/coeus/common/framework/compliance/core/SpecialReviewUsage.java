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
package org.kuali.coeus.common.framework.compliance.core;

import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.core.api.mo.common.active.MutableInactivatable;


/**
 * Defines the mapping between a Special Review Type and a module to allow per-module customization of certain Special Review Types.
 */
public class SpecialReviewUsage extends KcPersistableBusinessObjectBase implements MutableInactivatable {

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
