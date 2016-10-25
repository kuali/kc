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
package org.kuali.coeus.sys.impl.validation;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.validation.ErrorReporter;
import org.kuali.coeus.sys.framework.validation.SoftError;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.UserSession;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("errorReporter")
public class ErrorReporterImpl implements ErrorReporter {

    private static final Log LOG = LogFactory.getLog(ErrorReporterImpl.class);

    public void reportError(String propertyName, String errorKey, String... errorParams) {
        GlobalVariables.getMessageMap().putError(propertyName, errorKey, errorParams);
        if (LOG.isDebugEnabled()) {
            LOG.debug("rule failure at " + ErrorReporterImpl.getMethodPath(1, 2));
        }
    }

    public void reportAuditError(AuditError error, String errorKey, String clusterLabel, String clusterCategory) {
        if (error == null || StringUtils.isBlank(errorKey)
                || StringUtils.isBlank(clusterLabel) || StringUtils.isBlank(clusterCategory)) {
            throw new IllegalArgumentException(new StringBuilder("null argument error: ")
                    .append(error)
                    .append(" errorkey: ")
                    .append(errorKey)
                    .append(" clusterLabel: ")
                    .append(clusterLabel)
                    .append(" clusterCategory: ")
                    .append(clusterCategory).toString());
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("rule failure at " + ErrorReporterImpl.getMethodPath(1, 2));
        }

        @SuppressWarnings("unchecked")
        final Map<String, AuditCluster> errorMap = GlobalVariables.getAuditErrorMap();

        AuditCluster cluster = errorMap.get(errorKey);

        if (cluster == null) {
            cluster = new AuditCluster(clusterLabel, new ArrayList<AuditError>(), clusterCategory);
            errorMap.put(errorKey, cluster);
        }

        @SuppressWarnings("unchecked")
        final Collection<AuditError> errors = cluster.getAuditErrorList();
        errors.add(error);
    }

    public void reportSoftError(String propertyName, String errorKey, String... errorParams) {
        addSoftError(propertyName, errorKey, errorParams);
        if (LOG.isDebugEnabled()) {
            LOG.debug("rule failure at " + ErrorReporterImpl.getMethodPath(1, 2));
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Collection<SoftError>> getSoftErrors() {
        UserSession session = GlobalVariables.getUserSession();
        Object o = session.retrieveObject(KeyConstants.SOFT_ERRORS_KEY);
        Map<String, Collection<SoftError>> softErrors =(Map<String, Collection<SoftError>>) o;
        if(softErrors == null) {
            softErrors = initializeSoftErrorMap();
        }
        return softErrors;
    }

    private void addSoftError(String propertyName, String errorKey, String[] errorParams) {
        Map<String, Collection<SoftError>> softMessageMap = getSoftErrors();
        Collection<SoftError> errorsForProperty = softMessageMap.get(propertyName);
        if(errorsForProperty == null) {
            errorsForProperty = new HashSet<SoftError>();
        }
        errorsForProperty.add(new SoftError(errorKey, errorParams));
        softMessageMap.put(propertyName, errorsForProperty);
    }

    private Map<String, Collection<SoftError>> initializeSoftErrorMap() {
        Map<String, Collection<SoftError>> softMessageMap = Collections.synchronizedMap(new HashMap<String, Collection<SoftError>>() {
            private static final long serialVersionUID = 709850431504932842L;

            @Override
            public Collection<SoftError> get(Object key) {
                return super.remove(key);
            }

        });
        GlobalVariables.getUserSession().addObject(KeyConstants.SOFT_ERRORS_KEY, softMessageMap);
        return softMessageMap;
    }

    public void reportWarning(String propertyName, String errorKey, String... errorParams) {
        GlobalVariables.getMessageMap().putWarning(propertyName, errorKey, errorParams);
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("rule warning at %s", ErrorReporterImpl.getMethodPath(1, 2)));
        }
    }

    public boolean propertyHasErrorReported(String propertyName) {
        boolean result = false;
        if( GlobalVariables.getMessageMap().getErrorMessagesForProperty(propertyName) != null) {
            result = GlobalVariables.getMessageMap().getErrorMessagesForProperty(propertyName).size() > 0;
        }
        return result;
    }

    public void removeErrors(String propertyName) {
        if(GlobalVariables.getMessageMap().getErrorMessagesForProperty(propertyName)!=null) {
            GlobalVariables.getMessageMap().getErrorMessagesForProperty(propertyName).clear();
        }
    }

    public static String getMethodPath(int fromLevel, int toLevel) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        //increase the levels to avoid including the method that called this.
        fromLevel = fromLevel + 1;
        toLevel = toLevel + 1;

        if (fromLevel <= 0) {
            throw new IllegalArgumentException("invalid fromLevel (" + fromLevel + " < 0)");
        }
        if (fromLevel > toLevel) {
            throw new IllegalArgumentException("invalid levels (fromLevel " + fromLevel + " > toLevel " + toLevel + ")");
        }
        if (toLevel >= stackTraceElements.length) {
            throw new IllegalArgumentException("invalid toLevel (" + toLevel + " >= " + stackTraceElements.length + ")");
        }

        StringBuffer result = new StringBuffer();
        int elementIndex = 0;
        for (StackTraceElement element : stackTraceElements){
            if (elementIndex >= fromLevel && elementIndex >= toLevel) {
                if (result.length() > 0) {
                    result.append(" from ");
                }
                result.append(element.getClassName()).append(".");
                result.append(element.getMethodName()).append("(");
                result.append(element.getFileName()).append(":");
                result.append(element.getLineNumber()).append(")");
            }
            elementIndex++;
        }
        return result.toString();

    }
}
