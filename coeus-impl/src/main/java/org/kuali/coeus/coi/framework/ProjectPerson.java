package org.kuali.coeus.coi.framework;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ProjectPerson implements Serializable {

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

    public ProjectPerson() {
        super();
    }

    public ProjectPerson(String sourceSystem, String sourceIdentifier, String personId, String sourcePersonType, String roleCode) {
        this.sourceSystem = sourceSystem;
        this.sourceIdentifier = sourceIdentifier;
        this.personId = personId;
        this.sourcePersonType = sourcePersonType;
        this.roleCode = roleCode;
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
    public String toString() {
        return "ProjectPerson{" +
                "sourceSystem='" + sourceSystem + '\'' +
                ", sourceIdentifier='" + sourceIdentifier + '\'' +
                ", personId='" + personId + '\'' +
                ", sourcePersonType='" + sourcePersonType + '\'' +
                ", roleCode='" + roleCode + '\'' +
                '}';
    }
}
