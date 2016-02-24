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
package org.kuali.kra.protocol.actions.submit;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * A ProtocolBase Review Type refers to the type of review that an
 * IRB Committee will perform, e.g. Full, Expedited, Exempt, etc.
 */
@SuppressWarnings("serial")
public class ProtocolReviewTypeBase extends KcPersistableBusinessObjectBase {

    private String reviewTypeCode;

    private String description;

    private boolean globalFlag;


    public ProtocolReviewTypeBase() {
    }

    public String getReviewTypeCode() {
        return reviewTypeCode;
    }

    public void setReviewTypeCode(String reviewTypeCode) {
        this.reviewTypeCode = reviewTypeCode;
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
