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
package org.kuali.coeus.common.framework.person.attr;

import org.kuali.coeus.common.api.person.attr.DegreeTypeContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.persistence.*;

/**
 * Class representation of the Degree Type Business Object
 *
 * $Id: DegreeType.java,v 1.2 2008-07-23 19:16:44 gmcgrego Exp $
 */
@Entity
@Table(name = "DEGREE_TYPE")
public class DegreeType extends KcPersistableBusinessObjectBase implements DegreeTypeContract {

    @Id
    @Column(name = "DEGREE_CODE")
    private String code;

    @Transient
    private Integer degreeLevel;

    @Column(name = "DESCRIPTION")
    private String description;

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDegreeLevel() {
        return this.degreeLevel;
    }

    public void setDegreeLevel(Integer argDegreeLevel) {
        this.degreeLevel = argDegreeLevel;
    }
}
