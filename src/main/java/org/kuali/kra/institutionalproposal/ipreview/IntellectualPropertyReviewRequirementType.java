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
package org.kuali.kra.institutionalproposal.ipreview;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public class IntellectualPropertyReviewRequirementType extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private String intellectualPropertyReviewRequirementTypeCode;

    private String description;

    public IntellectualPropertyReviewRequirementType() {
    }

    public String getIntellectualPropertyReviewRequirementTypeCode() {
        return intellectualPropertyReviewRequirementTypeCode;
    }

    public void setIntellectualPropertyReviewRequirementTypeCode(String intellectualPropertyReviewRequirementTypeCode) {
        this.intellectualPropertyReviewRequirementTypeCode = intellectualPropertyReviewRequirementTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((intellectualPropertyReviewRequirementTypeCode == null) ? 0 : intellectualPropertyReviewRequirementTypeCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        IntellectualPropertyReviewRequirementType other = (IntellectualPropertyReviewRequirementType) obj;
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (intellectualPropertyReviewRequirementTypeCode == null) {
            if (other.intellectualPropertyReviewRequirementTypeCode != null) {
                return false;
            }
        } else if (!intellectualPropertyReviewRequirementTypeCode.equals(other.intellectualPropertyReviewRequirementTypeCode)) {
            return false;
        }
        return true;
    }
}
