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

public class IntellectualPropertyReviewResultType extends KraPersistableBusinessObjectBase {

    private static final long serialVersionUID = 1L;

    private String intellectualPropertyReviewResultTypeCode;

    private String description;

    public IntellectualPropertyReviewResultType() {
    }

    public String getIntellectualPropertyReviewResultTypeCode() {
        return intellectualPropertyReviewResultTypeCode;
    }

    public void setIntellectualPropertyReviewResultTypeCode(String intellectualPropertyReviewResultTypeCode) {
        this.intellectualPropertyReviewResultTypeCode = intellectualPropertyReviewResultTypeCode;
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
        result = prime * result + ((intellectualPropertyReviewResultTypeCode == null) ? 0 : intellectualPropertyReviewResultTypeCode.hashCode());
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
        IntellectualPropertyReviewResultType other = (IntellectualPropertyReviewResultType) obj;
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (intellectualPropertyReviewResultTypeCode == null) {
            if (other.intellectualPropertyReviewResultTypeCode != null) {
                return false;
            }
        } else if (!intellectualPropertyReviewResultTypeCode.equals(other.intellectualPropertyReviewResultTypeCode)) {
            return false;
        }
        return true;
    }
}
