package org.kuali.coeus.sys.framework.auth;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import java.sql.Timestamp;
import java.util.Calendar;

public class CoreImpersonation extends KcPersistableBusinessObjectBase {

    private Long id;
    private Timestamp loginTimestamp;
    private String impersonatedUserId;
    private String impersonatedByUserId;
    private String impersonationDisplayName;
    private String requestedResource;

    public CoreImpersonation(AuthUser authUser, String requestedResource) {
        this.impersonatedByUserId = authUser.getImpersonatedBy();
        this.impersonatedUserId = authUser.getUsername();
        this.impersonationDisplayName = authUser.getDisplayName();
        this.requestedResource = requestedResource;
        this.loginTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getLoginTimestamp() {
        return loginTimestamp;
    }

    public void setLoginTimestamp(Timestamp loginTimestamp) {
        this.loginTimestamp = loginTimestamp;
    }

    public String getImpersonatedUserId() {
        return impersonatedUserId;
    }

    public void setImpersonatedUserId(String impersonatedUserId) {
        this.impersonatedUserId = impersonatedUserId;
    }

    public String getImpersonatedByUserId() {
        return impersonatedByUserId;
    }

    public void setImpersonatedByUserId(String impersonatedByUserId) {
        this.impersonatedByUserId = impersonatedByUserId;
    }

    public String getImpersonationDisplayName() {
        return impersonationDisplayName;
    }

    public void setImpersonationDisplayName(String impersonationDisplayName) {
        this.impersonationDisplayName = impersonationDisplayName;
    }

    public String getRequestedResource() {
        return requestedResource;
    }

    public void setRequestedResource(String requestedResource) {
        this.requestedResource = requestedResource;
    }

}
