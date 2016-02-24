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
