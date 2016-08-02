package org.kuali.coeus.dc.updateuser;


import java.util.Date;

public class LastActionInfo {
    private String documentId;
    private String principalName;
    private Date date;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LastActionInfo)) return false;

        LastActionInfo that = (LastActionInfo) o;

        if (documentId != null ? !documentId.equals(that.documentId) : that.documentId != null) return false;
        if (principalName != null ? !principalName.equals(that.principalName) : that.principalName != null)
            return false;
        return date != null ? date.equals(that.date) : that.date == null;

    }

    @Override
    public int hashCode() {
        int result = documentId != null ? documentId.hashCode() : 0;
        result = 31 * result + (principalName != null ? principalName.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LastActionInfo{" +
                "documentId='" + documentId + '\'' +
                ", principalName='" + principalName + '\'' +
                ", date=" + date +
                '}';
    }
}
