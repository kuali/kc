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
package org.kuali.kra.committee.bo;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * Defines the type of motion (approve, disapprove, etc.) that the committee voted to enact.
 */
@SuppressWarnings("serial")
public class CommitteeDecisionMotionType extends KraPersistableBusinessObjectBase {

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

    /**
     * Constructs a CommitteeDecisionMotionType.
     */
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
