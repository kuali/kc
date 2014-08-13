/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.sys.framework.validation;

import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;


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
     * {@link KNSGlobalVariables#getAuditErrorMap() KNSGlobalVariables.getAuditErrorMap()}.
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
