/*
 * Copyright 2005-2014 The Kuali Foundation
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
import org.apache.ojb.broker.PersistenceBrokerException;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.bo.PersistableBusinessObjectExtension;
import org.kuali.rice.krad.data.jpa.DisableVersioning;
import org.kuali.rice.krad.service.PersistenceStructureService;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.List;

@DisableVersioning
@MappedSuperclass
public abstract class KcPersistableBusinessObjectBase extends PersistableBusinessObjectBase implements KcDataObject {

    @Column(name = "UPDATE_USER")
    private String updateUser;

    @Column(name = "UPDATE_TIMESTAMP")
    private Timestamp updateTimestamp;

    @Transient
    private boolean updateUserSet;

    @Transient
    private transient PersistableBusinessObjectExtension temporaryExtension;

    @Transient
    private transient KcDataObjectService kcDataObjectService;

    @Transient
    private transient PersistenceStructureService persistenceStructureService;

    @Override
    protected void prePersist() {
        getKcDataObjectService().initVersionNumberForPersist(this);
        getKcDataObjectService().initUpdateFieldsForPersist(this);
        getKcDataObjectService().initObjectIdForPersist(this);

        super.prePersist();

        if (extension != null) {
            temporaryExtension = extension;
            extension = null;
        }
    }

    @Override
    protected void postPersist() {
        if (temporaryExtension != null) {
            @SuppressWarnings("unchecked")
            final List<String> fieldNames = getPersistenceStructureService().listPrimaryKeyFieldNames(getClass());
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
        getKcDataObjectService().initObjectIdForUpdate(this);
        getKcDataObjectService().initVersionNumberForUpdate(this);
        getKcDataObjectService().initUpdateFieldsForUpdate(this);

        super.preUpdate();
    }

    @Override
    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }

    @Override
    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    @Override
    public String getUpdateUser() {
        return updateUser;
    }

    @Override
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public boolean isUpdateUserSet() {
        return updateUserSet;
    }

    @Override
    public void setUpdateUserSet(boolean updateUserSet) {
        this.updateUserSet = updateUserSet;
    }

    KcDataObjectService getKcDataObjectService() {
        if (this.kcDataObjectService == null) {
            this.kcDataObjectService = KcServiceLocator.getService(KcDataObjectService.class);
        }
        return this.kcDataObjectService;
    }

    PersistenceStructureService getPersistenceStructureService() {
        if (this.persistenceStructureService == null) {
            this.persistenceStructureService = KcServiceLocator.getService(PersistenceStructureService.class);
        }
        return this.persistenceStructureService;
    }

    void setKcDataObjectService(KcDataObjectService kcDataObjectService) {
        this.kcDataObjectService = kcDataObjectService;
    }

    void setPersistenceStructureService(PersistenceStructureService persistenceStructureService) {
        this.persistenceStructureService = persistenceStructureService;
    }
}
