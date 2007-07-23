package org.kuali.kra.bo;

import java.sql.Timestamp;

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.core.service.DateTimeService;

public abstract class KraPersistableBusinessObjectBase extends PersistableBusinessObjectBase {

	private String updateUser;
	private Timestamp updateTimestamp;

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
        Timestamp updateTimestamp = ((DateTimeService)KraServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME)).getCurrentTimestamp();

        setUpdateTimestamp(updateTimestamp);
        // Since the UPDATE_USER column is only VACHAR(8), we need to truncate this string
        setUpdateUser(GlobalVariables.getUserSession().getLoggedInUserNetworkId().substring(0, 8));
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
}
