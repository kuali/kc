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
package org.kuali.coeus.coi.framework;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Map;

public class ProjectPerson implements Serializable, ProjectMetadata {

    @Size(min = 1, max = 20)
    @NotNull
    private String sourceSystem;

    @Size(min = 1, max = 50)
    @NotNull
    private String sourceIdentifier;

    @NotNull
    @Size(min = 1, max = 40)
    private String personId;

    @NotNull
    @Size(min = 1, max = 20)
    private String sourcePersonType;

    @NotNull
    @Size(min = 1, max = 5)
    private String roleCode;

    private Map<String, String> metadata;

    public ProjectPerson() {
        super();
    }

    public ProjectPerson(String sourceSystem, String sourceIdentifier, String personId, String sourcePersonType, String roleCode, Map<String, String> metadata) {
        this.sourceSystem = sourceSystem;
        this.sourceIdentifier = sourceIdentifier;
        this.personId = personId;
        this.sourcePersonType = sourcePersonType;
        this.roleCode = roleCode;
        this.metadata = metadata;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getSourceIdentifier() {
        return sourceIdentifier;
    }

    public void setSourceIdentifier(String sourceIdentifier) {
        this.sourceIdentifier = sourceIdentifier;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getSourcePersonType() {
        return sourcePersonType;
    }

    public void setSourcePersonType(String sourcePersonType) {
        this.sourcePersonType = sourcePersonType;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Override
    public Map<String, String> getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "ProjectPerson{" +
                "sourceSystem='" + sourceSystem + '\'' +
                ", sourceIdentifier='" + sourceIdentifier + '\'' +
                ", personId='" + personId + '\'' +
                ", sourcePersonType='" + sourcePersonType + '\'' +
                ", roleCode='" + roleCode + '\'' +
                ", metadata=" + metadata +
                '}';
    }
}
