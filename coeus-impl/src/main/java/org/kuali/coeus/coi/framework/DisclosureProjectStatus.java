package org.kuali.coeus.coi.framework;

import javax.validation.constraints.Pattern;

public class DisclosureProjectStatus {
    @Pattern(regexp = "[a-zA-Z0-9]+")
    String userId;
    @Pattern(regexp = "[a-zA-Z ]+")
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
