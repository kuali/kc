/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.web;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.util.GlobalVariables;

@SuppressWarnings("deprecation")
public class ErrorUtils {

    /**
     * Copies audit errors from audit error map to message map. Arguments are optional and can both be null.
     * @param auditClusterCategory optional cluster category string if errors should be restricted. If null all audit clusters are included
     * @param errorkey optional errorkey to use instead of the one included in the audit error. This is to display the errors even if the property
     * is not included in the current page
     * @return true if any audit clusters are processed. 
     */
    public static boolean copyAuditErrorsToPage(String auditClusterCategory, String errorkey) {
        boolean auditClusterFound = false;
        Iterator<String> iter = KNSGlobalVariables.getAuditErrorMap().keySet().iterator();
        while (iter.hasNext()) {
           String errorKey = (String) iter.next();
            AuditCluster auditCluster = (AuditCluster)KNSGlobalVariables.getAuditErrorMap().get(errorKey);
            if(auditClusterCategory == null || StringUtils.equalsIgnoreCase(auditCluster.getCategory(), auditClusterCategory)){
                auditClusterFound = true;
                for (Object error : auditCluster.getAuditErrorList()) {
                    AuditError auditError = (AuditError)error;
                    GlobalVariables.getMessageMap().putError(errorKey == null ? auditError.getErrorKey() : errorKey, 
                            auditError.getMessageKey(), auditError.getParams());
                }
            }
        }
        return auditClusterFound;
    }
}
