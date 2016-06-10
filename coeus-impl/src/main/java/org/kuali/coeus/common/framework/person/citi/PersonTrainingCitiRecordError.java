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
package org.kuali.coeus.common.framework.person.citi;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class PersonTrainingCitiRecordError extends KcPersistableBusinessObjectBase {
    private Long id;
    private Long citiRecordId;
    private String message;

    public PersonTrainingCitiRecordError() {
        super();
    }

    public PersonTrainingCitiRecordError(String message) {
        this.setMessage(message);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCitiRecordId() {
        return citiRecordId;
    }

    public void setCitiRecordId(Long citiRecordId) {
        this.citiRecordId = citiRecordId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = StringUtils.substring(message, 0, 2000) ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonTrainingCitiRecordError)) return false;

        PersonTrainingCitiRecordError that = (PersonTrainingCitiRecordError) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (citiRecordId != null ? !citiRecordId.equals(that.citiRecordId) : that.citiRecordId != null) return false;
        return message != null ? message.equals(that.message) : that.message == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (citiRecordId != null ? citiRecordId.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
