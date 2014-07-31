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


import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.document.Document;




/**
 * Helper class for Audit Actions.
 */

public interface AuditHelper {

    public enum ValidationState {
        ERROR, WARNING, OK;
    }

    /**
     * 
     * This method sets the audit mode on the passed in {@link KualiDocumentFormBase KualiDocumentFormBase}
     * and then returns the correct {@link ActionForward} 
     * @param mapping the action mapping
     * @param form the action form
     * @param audit what to set the audit mode to
     * @param <T> the action form type
     * @return the action forward
     * @throws NullPointerException if the mapping or form is null
     */
    public <T extends KualiDocumentFormBase & Auditable> ActionForward setAuditMode(
            final ActionMapping mapping, final T form, final boolean audit);
    
    /**
     * This method will apply audit rules if audit mode is enabled.
     * @param form the action form
     * @param <T> the action form type
     * @return true if audit passed
     * @throws NullPointerException if the form is null
     */
    public <T extends Auditable> boolean auditConditionally(final T form);
    
    /**
     * This method will apply audit rules disregarding whether audit is enabled.
     * @param form the action form
     * @param <T> the action form type
     * @return true if audit passed
     * @throws NullPointerException if the form is null
     */
    public <T extends Auditable> boolean auditUnconditionally(final T form);
    
    /**
     * This method will always apply audit rules on a document.
     * @param document the document
     * @return true if audit passed
     * @throws NullPointerException if the document is null
     */
    public boolean auditUnconditionally(final Document document);
    
    /**
     * Runs validation conditionally or otherwise depending on the unconditionally param
     * and then if the validation returns false, checks the audit clusters for any error
     * clusters. Then depending returns OK, WARNING or ERROR.
     * @param <T>
     * @param form
     * @param unconditionally
     * @return
     */
    public <T extends Auditable> ValidationState isValidSubmission(final T form, boolean unconditionally);

}
