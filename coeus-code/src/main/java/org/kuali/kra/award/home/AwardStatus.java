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
package org.kuali.kra.award.home;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * 
 * This class represents AwardStatus BO
 */
public class AwardStatus extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = 7554440681643595144L;

    private String statusCode;

    private String description;

    public AwardStatus() {
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
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
        result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
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
        if (getClass() != obj.getClass()) return false;
        AwardStatus other = (AwardStatus) obj;
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (statusCode == null) {
            if (other.statusCode != null) {
                return false;
            }
        } else if (!statusCode.equals(other.statusCode)) {
            return false;
        }
        return true;
    }
}
