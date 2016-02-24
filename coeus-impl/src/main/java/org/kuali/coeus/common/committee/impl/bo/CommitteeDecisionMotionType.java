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
package org.kuali.coeus.common.committee.impl.bo;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * Defines the type of motion (approve, disapprove, etc.) that the committee voted to enact.
 */
@SuppressWarnings("serial")
public class CommitteeDecisionMotionType extends KcPersistableBusinessObjectBase {

    /**
     * The committee decision to approve the protocol.
     */
    public static final String APPROVE = "1";

    /**
     * The committee decision to disapprove the protocol.
     */
    public static final String DISAPPROVE = "2";

    /**
     * The committee decision to return the protocol to the PI with minor revisions requested.
     */
    public static final String SPECIFIC_MINOR_REVISIONS = "3";

    /**
     * The committee decision to return the protocol to the PI with substantive revisions requested.
     */
    public static final String SUBSTANTIVE_REVISIONS_REQUIRED = "4";

    private String motionTypeCode;

    private String description;


    public CommitteeDecisionMotionType() {
    }

    public String getMotionTypeCode() {
        return motionTypeCode;
    }

    public void setMotionTypeCode(String motionTypeCode) {
        this.motionTypeCode = motionTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
