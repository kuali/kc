/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

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

    public Boolean checkPropertyValueForAnyPreviousVersion(SequenceOwner<?> currentVersion, String property, String comparison) {
        if (currentVersion != null && property != null && comparison != null) {
            // I can't believe there isn't a way to retrieve a "documentBoNumber" equivalent from the SequenceOwner interface...
            Object versionNumber = getPropertyValue(currentVersion, currentVersion.getVersionNameField());
            if (versionNumber == null) {
                return false;
            }

            for (VersionHistory pastVersion : getVersionHistoryService().loadVersionHistory(currentVersion.getClass(), versionNumber.toString())) {
                if (VersionStatus.ARCHIVED == pastVersion.getStatus() || VersionStatus.ACTIVE == pastVersion.getStatus()) {
                    Object propertyValue = getPropertyValue(pastVersion.getSequenceOwner(), property);
                    if (propertyValue == null || currentVersion.getSequenceNumber().equals(pastVersion.getSequenceOwnerSequenceNumber())) {
                        continue;
                    }
                    if (propertyValueEqualsString(property, propertyValue, comparison)) {
                        return true;
                    }
                }
            }
        }
        return false;
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
        }catch (IllegalArgumentException e) {
            LOG.error("Could not find property " + property, e);
        }catch (IllegalAccessException e) {
            LOG.error("Could not find property " + property, e);
        }catch (InvocationTargetException e) {
            LOG.error("Could not find property " + property, e);
        }catch (NoSuchMethodException e) {
            LOG.error("Could not find property " + property, e);
        }
        return propertyValue;
    }

    protected Boolean propertyValueEqualsString(String propertyName, Object propertyValue, String comparison) {
        try {
            if (propertyValue instanceof Integer) {
                if (propertyValue.equals(Integer.valueOf(comparison))) {
                    return true;
                }
            }
            if (propertyValue instanceof Long) {
                if (propertyValue.equals(Long.valueOf(comparison))) {
                    return true;
                }
            }
            if (propertyValue instanceof Double) {
                if (propertyValue.equals(Double.valueOf(comparison))) {
                    return true;
                }
            }
            if (propertyValue instanceof BigDecimal) {
                if (propertyValue.equals(new BigDecimal(comparison))) {
                    return true;
                }
            }
        }
        catch (NumberFormatException e) {
            LOG.error("Value " + comparison + " cannot be numerically compared to property " + propertyName, e);
        }
        if (propertyValue instanceof Boolean) {
            if (propertyValue.equals(Boolean.valueOf(comparison))) {
                return true;
            }
        }
        return comparison.equals(propertyValue.toString());
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
        if (previousVersion == null) {
            return false;
        }

        Object currentPropertyValue = getPropertyValue(currentVersion, property);
        Object previousPropertyValue = getPropertyValue(previousVersion, property);

        if (currentPropertyValue == null && previousPropertyValue != null || currentPropertyValue != null && previousPropertyValue == null) {
            return true;
        }
        else if (currentPropertyValue == null && previousPropertyValue == null) {
            return false;
        }
        return !currentPropertyValue.equals(previousPropertyValue);
    }

    protected SequenceOwner<?> getLastActiveVersion(SequenceOwner<?> currentVersion) {
        SequenceOwner<?> highestActiveVersion = null;
        if (currentVersion != null) {
            // I can't believe there isn't a way to retrieve a "documentBoNumber" equivalent from the SequenceOwner interface...
            Object versionNumber = getPropertyValue(currentVersion, currentVersion.getVersionNameField());
            if (versionNumber != null) {
                for (VersionHistory pastVersion : getVersionHistoryService().loadVersionHistory(currentVersion.getClass(), versionNumber.toString())) {
                    if (pastVersion.getSequenceOwnerSequenceNumber() != currentVersion.getSequenceNumber()) {
                        if ((VersionStatus.ARCHIVED == pastVersion.getStatus() || VersionStatus.ACTIVE == pastVersion.getStatus()) &&
                                (highestActiveVersion == null || pastVersion.getSequenceOwnerSequenceNumber() > highestActiveVersion.getSequenceNumber())) {
                            highestActiveVersion = pastVersion.getSequenceOwner();
                        }
                    }
                }
            }
        }
        return highestActiveVersion;
    }

    public Boolean doSponsorAndPrimeSponsorMatch(Sponsorable grantsBo) {
        String sponsorCode = grantsBo.getSponsorCode();
        // There should REALLY be an interface for this...
        String primeSponsorCode = null;
        try {
            PropertyDescriptor propDescriptor = PropertyUtils.getPropertyDescriptor(grantsBo, "primeSponsorCode");
            if (propDescriptor != null){
                Method readMethod = propDescriptor.getReadMethod();
                if (readMethod != null){
                    primeSponsorCode = (String) propDescriptor.getReadMethod().invoke(grantsBo);
                }
            }
        }catch (Exception e) {
            LOG.error("Could not read Prime Sponsor code from BO", e);
        }

        if (sponsorCode != null && primeSponsorCode != null && sponsorCode.equals(primeSponsorCode)) {
            return true;
        }
        return false;
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
