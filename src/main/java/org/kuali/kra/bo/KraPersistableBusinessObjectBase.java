package org.kuali.kra.bo;

import java.sql.Timestamp;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.bo.user.AuthenticationUserId;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.exceptions.UserNotFoundException;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.KNSServiceLocator;
import org.kuali.core.service.DateTimeService;
import org.kuali.core.service.UniversalUserService;

public abstract class KraPersistableBusinessObjectBase extends PersistableBusinessObjectBase {

    private String updateUser;
    private Timestamp updateTimestamp;
    private boolean updateUserSet;

    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#beforeInsert()
     */
    @Override
    public void beforeInsert(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        super.beforeInsert(persistenceBroker);
        setUpdateFields();
    }

    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#beforeInsert()
     */
    @Override
    public void beforeUpdate(PersistenceBroker persistenceBroker) throws PersistenceBrokerException {
        super.beforeUpdate(persistenceBroker);
        setUpdateFields();
    }

    /**
     * Set updateTimestamp and updateUser prior to persistence
     */
    private void setUpdateFields() {
        if (!isUpdateUserSet()) {
            String updateUser = GlobalVariables.getUserSession().getLoggedInUserNetworkId();

            // Since the UPDATE_USER column is only VACHAR(8), we need to truncate this string if it's longer than 8 characters
            if (updateUser.length() > 8) {
                updateUser = updateUser.substring(0, 8);
            }

            setUpdateUser(updateUser);
        }
        setUpdateTimestamp(((DateTimeService)KraServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME)).getCurrentTimestamp());
    }

    public Timestamp getUpdateTimestamp() {
    	return updateTimestamp;
    }
    public void setUpdateTimestamp(Timestamp updateTimestamp) {
    	this.updateTimestamp = updateTimestamp;
    }
    public String getUpdateUser() {
    	return updateUser;
    }
    public void setUpdateUser(String updateUser) {
    	this.updateUser = updateUser;
    }

    /**
     * Gets the updateUserSet attribute.
     * @return Returns the updateUserSet.
     */
    public boolean isUpdateUserSet() {
        return updateUserSet;
    }

    /**
     * Sets the updateUserSet attribute value.
     * @param updateUserSet The updateUserSet to set.
     */
    public void setUpdateUserSet(boolean updateUserSet) {
        this.updateUserSet = updateUserSet;
    }
    
    //Helper methods
    // TODO : Is ok for sharing here??

    public String getAuthorPersonName(){
        UniversalUser user=null;
        try {
            user = KNSServiceLocator.getBean(UniversalUserService.class).getUniversalUser(new AuthenticationUserId(getUpdateUser()));
        }
        catch (UserNotFoundException unfe) {
        }
        if (ObjectUtils.isNull(user)) {
            return "Person not found";
        }
        else {
            return user.getPersonName();
        }
    }


}
