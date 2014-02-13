/*
 * Copyright 2005-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.sys.framework.model;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kim.api.identity.IdentityService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.bo.PersistableBusinessObjectExtension;
import org.kuali.rice.krad.data.jpa.DisableVersioning;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.List;

@DisableVersioning
@MappedSuperclass
public abstract class KcPersistableBusinessObjectBase extends PersistableBusinessObjectBase {

    protected static final int UPDATE_USER_LENGTH = 60;

    @Transient
    private transient IdentityService identityService;

    @Column(name = "UPDATE_USER")
    private String updateUser;

    @Column(name = "UPDATE_TIMESTAMP")
    private Timestamp updateTimestamp;

    @Transient
    private boolean updateUserSet;

    @Transient
    private transient PersistableBusinessObjectExtension temporaryExtension;

    @Override
    protected void prePersist() {
        super.prePersist();
        this.setVersionNumber(new Long(0));
        setUpdateFields();
        if (extension != null) {
            temporaryExtension = extension;
            extension = null;
        }
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.rice.krad.bo.PersistableBusinessObjectBase#afterInsert(org.apache.ojb.broker.PersistenceBroker)
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void postPersist() {
        if (temporaryExtension != null) {
            List<String> fieldNames = KNSServiceLocator.getPersistenceStructureService().listPrimaryKeyFieldNames(getClass());
            try {
                for (String fieldName : fieldNames) {
                    try {
                        Method thisGetter = PropertyUtils.getReadMethod(PropertyUtils.getPropertyDescriptor(this, fieldName));
                        Method extensionSetter = PropertyUtils.getWriteMethod(PropertyUtils.getPropertyDescriptor(temporaryExtension, fieldName));
                        extensionSetter.invoke(temporaryExtension, thisGetter.invoke(this));
                    } catch (NoSuchMethodException nsme) {
                        throw new PersistenceBrokerException("Could not find accessor for " + fieldName + " in an extension object", nsme);
                    } catch (InvocationTargetException ite) {
                        throw new PersistenceBrokerException("Could not invoke accessor for " + fieldName + " on an extension object", ite);
                    } catch (IllegalAccessException iae) {
                        throw new PersistenceBrokerException("Illegal access when invoking " + fieldName + " accessor on an extension object", iae);
                    }
                }
            } finally {
                extension = temporaryExtension;
                temporaryExtension = null;
            }
        }
    }

    @Override
    protected void preUpdate() {
        super.preUpdate();
        // Optimistic Locking has been disabled so adding null check and setting version number to 0                                                                         
        // If we ever turn Optimistic Locking back on, we need to remove this code                                                                         
        if (this.getVersionNumber() == null) {
            this.setVersionNumber(new Long(0));
        }
        setUpdateFields();
    }

    /**
     * Set updateTimestamp and updateUser prior to persistence
     */
    private void setUpdateFields() {
        if (!isUpdateUserSet()) {
            String principalName = GlobalVariables.getUserSession().getPrincipalName();
            String lastPrincipalId = (String) GlobalVariables.getUserSession().retrieveObject(Constants.LAST_ACTION_PRINCIPAL_ID);
            if (StringUtils.isNotBlank(lastPrincipalId)) {
                principalName = getIdentityService().getPrincipal(lastPrincipalId).getPrincipalName();
            }

            setUpdateUser(principalName);
        }
        setUpdateTimestamp(((DateTimeService) KcServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME)).getCurrentTimestamp());
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

    /**
     * Sets the update user, making sure it is not the system user and truncating the name so it will fit.
     *
     * @param updateUser the user who updated this object
     */
    public void setUpdateUser(String updateUser) {
        if (!KRADConstants.SYSTEM_USER.equals(updateUser)) {
            this.updateUser = StringUtils.substring(updateUser, 0, UPDATE_USER_LENGTH);
        }
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

    /**
     * Looks up and returns the IdentityService.
     * @return the identity service.
     */
    private IdentityService getIdentityService() {
        if (this.identityService == null) {
            this.identityService = KcServiceLocator.getService(IdentityService.class);
        }
        return this.identityService;
    }
}
