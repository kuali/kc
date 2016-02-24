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
package org.kuali.kra.bo;


import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * Account Type business object
 * 
 * @author Kuali Coeus development team (kc.dev@kuali.org)
 */
public class AccountType extends KcPersistableBusinessObjectBase {

    private static final long serialVersionUID = -8445736738959087012L;

    private Integer accountTypeCode;

    private String description;

    public AccountType() {
    }

    public Integer getAccountTypeCode() {
        return accountTypeCode;
    }

    public void setAccountTypeCode(Integer accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((accountTypeCode == null) ? 0 : accountTypeCode.hashCode());
        result = PRIME * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        final AccountType OTHER = (AccountType) obj;
        if (accountTypeCode == null) {
            if (OTHER.accountTypeCode != null) return false;
        } else if (!accountTypeCode.equals(OTHER.accountTypeCode)) return false;
        if (description == null) {
            if (OTHER.description != null) return false;
        } else if (!description.equals(OTHER.description)) return false;
        return true;
    }
}
