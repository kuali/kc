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
package org.kuali.coeus.common.impl.krms;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.sponsor.Sponsorable;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.framework.version.history.VersionHistory;
import org.kuali.coeus.common.framework.version.history.VersionHistoryService;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;
import org.kuali.coeus.sys.api.model.ScaleThreeDecimal;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class KcKrmsJavaFunctionTermServiceBase {

    protected final Log LOG = LogFactory.getLog(KcKrmsJavaFunctionTermServiceBase.class);

    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String[] restrictedElements = { " ", "`", "@", "#", "!", "$", "%", "^", "&", "*", "(", ")", "[", "]", "{",
            "}", "|", "\\", "/", "?", "<", ">", ",", ";", ":", "'", "\"", "`", "+" };

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @Autowired
    @Qualifier("versionHistoryService")
    private VersionHistoryService versionHistoryService;

    public Boolean checkPropertyValueForAnyPreviousVersion(SequenceOwner<?> currentVersion, String property, String valueToCompare) {
        return currentVersion.getVersionNameFieldValue() == null ? false:
           getVersionHistories(currentVersion, currentVersion.getVersionNameFieldValue()).stream().filter(pastVersion ->
                    isArchivedOrActive(pastVersion)).collect(Collectors.toList()).
                    stream().anyMatch(pastVersion -> isPropertyValueMatches(currentVersion, property, valueToCompare, pastVersion));
    }

    protected boolean isPropertyValueMatches(SequenceOwner<?> currentVersion, String property, String valueToCompare, VersionHistory pastVersion) {
        Object propertyValue = getPropertyValue(pastVersion.getSequenceOwner(), property);
        return propertyValue != null && !currentVersion.getSequenceNumber().equals(pastVersion.getSequenceOwnerSequenceNumber()) &&
                propertyValueEqualsString(property, propertyValue, valueToCompare);
    }

    protected boolean isArchivedOrActive(VersionHistory pastVersion) {
        return VersionStatus.ARCHIVED == pastVersion.getStatus() || VersionStatus.ACTIVE == pastVersion.getStatus();
    }

    protected List<VersionHistory> getVersionHistories(SequenceOwner<?> currentVersion, String versionNumber) {
        return getVersionHistoryService().loadVersionHistory(currentVersion.getClass(), versionNumber);
    }

    protected Object getPropertyValue (Object propertyObject, String property) {
        Object propertyValue = null;
        try {
            PropertyDescriptor propDescriptor = PropertyUtils.getPropertyDescriptor(propertyObject, property);
            if (propDescriptor != null){
                Method readMethod = propDescriptor.getReadMethod();
                if (readMethod != null) {
                    propertyValue = propDescriptor.getReadMethod().invoke(propertyObject);
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Count not find property " + property + "on object" + propertyObject, e);
        }
        return propertyValue;
    }

    protected Boolean propertyValueEqualsString(String propertyName, Object propertyValue, String valueToCompare) {
        try {
            if (propertyValue instanceof Integer) {
                if (propertyValue.equals(Integer.valueOf(valueToCompare))) {
                    return true;
                }
            }
            if (propertyValue instanceof Long) {
                if (propertyValue.equals(Long.valueOf(valueToCompare))) {
                    return true;
                }
            }
            if (propertyValue instanceof Double) {
                if (propertyValue.equals(Double.valueOf(valueToCompare))) {
                    return true;
                }
            }
            if (propertyValue instanceof BigDecimal) {
                if (propertyValue.equals(new BigDecimal(valueToCompare))) {
                    return true;
                }
            }
            if (propertyValue instanceof ScaleTwoDecimal) {
                if (propertyValue.equals(new ScaleTwoDecimal(valueToCompare))) {
                    return true;
                }
            }
            if (propertyValue instanceof ScaleThreeDecimal) {
                if (propertyValue.equals(new ScaleThreeDecimal(valueToCompare))) {
                    return true;
                }
            }
        }
        catch (NumberFormatException e) {
            throw new RuntimeException("Value " + valueToCompare + " cannot be numerically compared to property " + propertyName, e);
        }
        if (propertyValue instanceof Boolean) {
            if (propertyValue.equals(Boolean.valueOf(valueToCompare))) {
                return true;
            }
        }
        return valueToCompare.equals(propertyValue.toString());
    }

    protected String[] buildArrayFromCommaList(String commaList) {
        String[] newArray = commaList.split(",");
        if (newArray.length == 0) {
            newArray = new String[] { commaList.trim() };
        }
        return newArray;
    }

    public Boolean hasPropertyChangedThisVersion(SequenceOwner<?> currentVersion, String property) {
        if (currentVersion == null || property == null) {
            return false;
        }
        SequenceOwner<?> previousVersion = getLastActiveVersion(currentVersion);
        Object currentPropertyValue = getPropertyValue(currentVersion, property);
        Object previousPropertyValue = getPropertyValue(previousVersion, property);
        return !Objects.equals(currentPropertyValue,previousPropertyValue);
    }

    protected SequenceOwner<?> getLastActiveVersion(SequenceOwner<?> currentVersion) {
        SequenceOwner<?> highestActiveVersion = null;
        if (currentVersion.getVersionNameFieldValue() != null) {
            for (VersionHistory pastVersion : getVersionHistories(currentVersion, currentVersion.getVersionNameFieldValue().toString())) {
                if (isHighestActiveVersion(highestActiveVersion, pastVersion, currentVersion)) {
                    highestActiveVersion = pastVersion.getSequenceOwner();
                }
            }
        }
        return highestActiveVersion;
    }

    private boolean isHighestActiveVersion(SequenceOwner<?> highestActiveVersion, VersionHistory pastVersion, SequenceOwner<?> currentVersion) {
        return (pastVersion.getSequenceOwnerSequenceNumber() != currentVersion.getSequenceNumber() && isArchivedOrActive(pastVersion)) &&
                (highestActiveVersion == null || pastVersion.getSequenceOwnerSequenceNumber() > highestActiveVersion.getSequenceNumber());
    }

    public Boolean doSponsorAndPrimeSponsorMatch(Sponsorable grantsBo) {
        return Objects.equals(grantsBo.getSponsorCode(), grantsBo.getPrimeSponsorCode());
    }

    /**
     * 
     * This method returns 'true' if 'stringToCheck' does not contain a special character, otherwise returns 'false'.
     */
    protected String specialCharacterRule(String stringToCheck) {
        for (String element : restrictedElements) {
            if (StringUtils.equalsIgnoreCase(element, stringToCheck)) {
                return FALSE;
            }
        }
        return TRUE;
    }

    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public VersionHistoryService getVersionHistoryService() {
        return versionHistoryService;
    }

    public void setVersionHistoryService(VersionHistoryService versionHistoryService) {
        this.versionHistoryService = versionHistoryService;
    }
}
