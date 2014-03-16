/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.framework.org.type;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORGANIZATION_TYPE_LIST")
public class OrganizationTypeList extends KcPersistableBusinessObjectBase {

    @Id
    @Column(name = "ORGANIZATION_TYPE_CODE")
    private Integer organizationTypeCode;

    @Column(name = "DESCRIPTION")
    private String description;

    public OrganizationTypeList() {
        super();
    }

    public Integer getOrganizationTypeCode() {
        return organizationTypeCode;
    }

    public void setOrganizationTypeCode(Integer organizationTypeCode) {
        this.organizationTypeCode = organizationTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
