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
package org.kuali.coeus.sys.framework.validation;

import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;


import java.util.*;

/**
 * This class provides error reporting capabilities.
 * <p>
 * This logic was taken from
 * {@link org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase ResearchDocumentRuleBase} so that classes don't have to
 * subclass ResearchDocumentRuleBase to report an error using these convenient methods.
 * </p>
 */

public interface ErrorReporter {

    /**
     * Wrapper around global errorMap.put call, to allow better logging.
     *
     * @param propertyName
     * @param errorKey
     * @param errorParams
     */
    public void reportError(String propertyName, String errorKey, String... errorParams);
    
    /**
     * Adds an audit error to the
     * {@link GlobalVariables#getAuditErrorMap() GlobalVariables.getAuditErrorMap()}.
     * 
     * @param error the error to add.
     * @param errorKey the error map key
     * @param clusterLabel the cluster label
     * @param clusterCategory the cluster category
     * @throws IllegalArgumentException if error, errorKey, clusterLabel, or clusterCategory are null or
     * if errorKey, clusterLabel, or clusterCategory are whitespace
     */
    public void reportAuditError(AuditError error, String errorKey, String clusterLabel, String clusterCategory);

    public void reportSoftError(String propertyName, String errorKey, String... errorParams);
    
    @SuppressWarnings("unchecked")
    public Map<String, Collection<SoftError>> getSoftErrors();

    
    /**
     * Wrapper around global errorMap.put call, to allow better logging.
     * 
     * @param propertyName
     * @param errorKey
     * @param errorParams
     */
    public void reportWarning(String propertyName, String errorKey, String... errorParams);

   
    /**
     * Does the property have any errors in the message map?
     * @param propertyName
     * @return
     */
    public boolean propertyHasErrorReported(String propertyName);
    
    /**
     * Removed the errors in the message map for the property.
     * @param propertyName
     */
    public void removeErrors(String propertyName);

}
