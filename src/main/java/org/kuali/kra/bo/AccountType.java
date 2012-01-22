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
package org.kuali.kra.bo;


/**
 * Account Type business object
 * 
 * @author Kuali Coeus development team (kc.dev@kuali.org)
 */
public class AccountType extends KraPersistableBusinessObjectBase {

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
