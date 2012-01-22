/*
 * Copyright 2005-2010 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
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
package org.kuali.kra.proposaldevelopment.bo;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

/**
 * The Proposal State represents the current state of a proposal.
 */
public class ProposalState extends KraPersistableBusinessObjectBase {

    public static final String IN_PROGRESS = "1";

    public static final String APPROVAL_PENDING = "2";

    public static final String APPROVAL_GRANTED = "3";

    public static final String APPROVAL_NOT_INITIATED_SUBMITTED = "4";

    public static final String APPROVAL_PENDING_SUBMITTED = "5";

    public static final String APPROVED_AND_SUBMITTED = "6";

    public static final String DISAPPROVED = "7";

    public static final String APPROVED_POST_SUBMISSION = "8";

    public static final String DISAPPROVED_POST_SUBMISSION = "9";

    public static final String CANCELED = "10";

    public static final String DOCUMENT_ERROR = "11";

    public static final String REVISIONS_REQUESTED = "12";

    public static final String APPROVED = "13";

    private String stateTypeCode;

    private String description;

    /**
	 * Get the State Type Code.
	 * @return the state type code
	 */
    public String getStateTypeCode() {
        return stateTypeCode;
    }

    /**
	 * Set the State Type Code
	 * @param stateTypeCode the new state type code
	 */
    public void setStateTypeCode(String stateTypeCode) {
        this.stateTypeCode = stateTypeCode;
    }

    /**
	 * Get the human-readable description of the status.
	 * @return the textual description
	 */
    public String getDescription() {
        return description;
    }

    /**
	 * Set the textual description.
	 * @param description the new description
	 */
    public void setDescription(String description) {
        this.description = description;
    }
}
