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
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.coeus.sys.framework.validation.Auditable;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.event.DocumentAuditEvent;
import org.kuali.rice.krad.service.KualiRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * Helper class for Audit Actions.
 */
@Component("auditHelper")
public class AuditHelperImpl implements AuditHelper {

    @Autowired
    @Qualifier("kualiRuleService")
    private KualiRuleService ruleService;

    public <T extends KualiDocumentFormBase & Auditable> ActionForward setAuditMode(
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

    public <T extends Auditable> boolean auditConditionally(final T form) {
        return this.auditDocumentFromForm(form, false);
    }

    public <T extends Auditable> boolean auditUnconditionally(final T form) {
        return this.auditDocumentFromForm(form, true);
    }

    public boolean auditUnconditionally(final Document document) {
        if (document == null) {
            throw new NullPointerException("the document is null");
        }

        return this.ruleService.applyRules(new DocumentAuditEvent(document));
    }

    private <T extends Auditable> boolean auditDocumentFromForm(final T form, final boolean alwaysApplyAudit) {

        if (form == null) {
            throw new NullPointerException("the form is null");
        }

        if (form.isAuditActivated() || alwaysApplyAudit) {
            return this.auditUnconditionally(form.getDocument());
        }
        return true;
    }

    public <T extends Auditable> AuditHelper.ValidationState isValidSubmission(final T form, boolean unconditionally) {
        AuditHelper.ValidationState result = AuditHelper.ValidationState.OK;
        boolean auditPassed;
        if (unconditionally) {
            auditPassed = auditUnconditionally(form);
        } else {
            auditPassed = auditConditionally(form);
        }
        if (!auditPassed) {
            result = AuditHelper.ValidationState.WARNING;
            for (Iterator iter = GlobalVariables.getAuditErrorMap().keySet().iterator(); iter.hasNext();) {
                AuditCluster auditCluster = (AuditCluster)GlobalVariables.getAuditErrorMap().get(iter.next());
                if (!StringUtils.equalsIgnoreCase(auditCluster.getCategory(), Constants.AUDIT_WARNINGS)) {
                    result = AuditHelper.ValidationState.ERROR;
                    break;
                }
            }
        }
        return result;
    }

    public KualiRuleService getRuleService() {
        return ruleService;
    }

    public void setRuleService(KualiRuleService kualiRuleService) {
        this.ruleService = kualiRuleService;
    }
}
