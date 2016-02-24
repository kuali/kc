/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.sys.framework.model;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.bo.PersistableBusinessObjectExtension;
import org.kuali.rice.krad.data.jpa.DisableVersioning;
import org.kuali.rice.krad.service.PersistenceStructureService;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import java.lang.reflect.Field;
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
    
    @Override
    public String toString() {
        class BusinessObjectToStringBuilder extends ReflectionToStringBuilder {
            private BusinessObjectToStringBuilder(Object object) {
                super(object);
            }

            public boolean accept(Field field) {
                if (String.class.isAssignableFrom(field.getType())
                		|| ClassUtils.isPrimitiveOrWrapper(field.getType())) {
                	return true;
                } else {
                	return false;
                }
            }
        };
        ReflectionToStringBuilder toStringBuilder = new BusinessObjectToStringBuilder(this);
        return toStringBuilder.toString();
    }    
}
