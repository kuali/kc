/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.coeus.sys.framework.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.model.AuditableForm;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.event.DocumentAuditEvent;
import org.kuali.rice.krad.service.KualiRuleService;

import java.util.Iterator;

/**
 * Helper class for Audit Actions.
 */
public final class AuditActionHelper {
    
    private final KualiRuleService ruleService; 
    
    public enum ValidationState {
        ERROR, WARNING, OK;
    }    
    
    /**
     * ctor that sets the used services.
     */
    public AuditActionHelper() {
        this(KcServiceLocator.getService(KualiRuleService.class));
    }
    
    /**
     * ctor that sets the used services from what is passed in.
     * @param ruleService the rule service
     * @throws NullPointerException if ruleService is null
     */
    AuditActionHelper(final KualiRuleService ruleService) {
        
        if (ruleService == null) {
            throw new NullPointerException("the ruleService is null");
        }
        this.ruleService = ruleService;
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
    public <T extends KualiDocumentFormBase & AuditableForm> ActionForward setAuditMode(
            final ActionMapping mapping, final T form, final boolean audit) {

        if (mapping == null) {
            throw new NullPointerException("the mapping is null");
        }
        
        if (form == null) {
            throw new NullPointerException("the form is null");
        }

        form.setAuditActivated(audit);     
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * This method will apply audit rules if audit mode is enabled.
     * @param form the action form
     * @param <T> the action form type
     * @return true if audit passed
     * @throws NullPointerException if the form is null
     */
    public <T extends KualiDocumentFormBase & AuditableForm> boolean auditConditionally(final T form) {
        return this.auditDocumentFromForm(form, false);
    }
    
    /**
     * This method will apply audit rules disregarding whether audit is enabled.
     * @param form the action form
     * @param <T> the action form type
     * @return true if audit passed
     * @throws NullPointerException if the form is null
     */
    public <T extends KualiDocumentFormBase & AuditableForm> boolean auditUnconditionally(final T form) {
        return this.auditDocumentFromForm(form, true);
    }
    
    /**
     * This method will always apply audit rules on a document.
     * @param document the document
     * @return true if audit passed
     * @throws NullPointerException if the document is null
     */
    public boolean auditUnconditionally(final Document document) {
        if (document == null) {
            throw new NullPointerException("the document is null");
        }
        
        return this.ruleService.applyRules(new DocumentAuditEvent(document));
    }
    
    /**
     * This method will apply audit rules when audit is enabled or if alwaysApplyAudit is true.
     * @param form the action form
     * @param alwaysApplyAudit whether to always apply audit
     * @param <T> the action form type
     * @return true if audit passed
     * @throws NullPointerException if the form is null
     */
    private <T extends KualiDocumentFormBase & AuditableForm> boolean auditDocumentFromForm(final T form, final boolean alwaysApplyAudit) {
        
        if (form == null) {
            throw new NullPointerException("the form is null");
        }
        
        if (form.isAuditActivated() || alwaysApplyAudit) {
            return this.auditUnconditionally(form.getDocument());
        }
        return true;
    }
    
    /**
     * Runs validation conditionally or otherwise depending on the unconditionally param
     * and then if the validation returns false, checks the audit clusters for any error
     * clusters. Then depending returns OK, WARNING or ERROR.
     * @param <T>
     * @param form
     * @param unconditionally
     * @return
     */
    public <T extends KualiDocumentFormBase & AuditableForm> ValidationState isValidSubmission(final T form, boolean unconditionally) {
        ValidationState result = ValidationState.OK;
        boolean auditPassed;
        if (unconditionally) {
            auditPassed = auditUnconditionally(form);
        } else {
            auditPassed = auditConditionally(form);
        }
        if (!auditPassed) {
            result = ValidationState.WARNING;
            for (Iterator iter = KNSGlobalVariables.getAuditErrorMap().keySet().iterator(); iter.hasNext();) {
                AuditCluster auditCluster = (AuditCluster)KNSGlobalVariables.getAuditErrorMap().get(iter.next());
                if (!StringUtils.equalsIgnoreCase(auditCluster.getCategory(), Constants.AUDIT_WARNINGS)) {
                    result = ValidationState.ERROR;
                    break;
                }
            }
        }
        return result;
    }    
}
