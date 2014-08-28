package org.kuali.coeus.common.framework.auth.perm;

public interface DocumentLevelPermissionable extends Permissionable {

    /**
     * This will return the workflow document number.
     * @return the document number
     */
    String getDocumentNumber();
}
