package org.kuali.coeus.sys.framework.model;


import org.kuali.coeus.sys.api.model.GloballyUnique;
import org.kuali.coeus.sys.api.model.RecordedUpdate;
import org.kuali.coeus.sys.api.model.Versioned;

import java.sql.Timestamp;

/**
 * Kuali Coeus Data Objects have certain fields in common which are contained within this interface.
 */
public interface KcDataObject extends GloballyUnique, Versioned, RecordedUpdate {

    int UPDATE_USER_LENGTH = 60;

    void setObjectId(String objectId);

    void setVersionNumber(Long versionNumber);

    @Override
    Timestamp getUpdateTimestamp();

    void setUpdateTimestamp(Timestamp updateTimestamp);

    void setUpdateUser(String updateUser);

    boolean isUpdateUserSet();

    void setUpdateUserSet(boolean updateUserSet);
}
