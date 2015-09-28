package org.kuali.coeus.coi.framework;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ProjectPerson implements Serializable {

    @NotNull
    @Size(min = 1, max = 40)
    private String personId;

    @NotNull
    @Size(min = 1, max = 5)
    private String roleCode;

    public ProjectPerson() {
        super();
    }

    public ProjectPerson(String personId, String roleCode) {
        this.personId = personId;
        this.roleCode = roleCode;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}
