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
package org.kuali.coeus.propdev.impl.budget.core;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.lock.ProposalBudgetLockService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.impl.lock.PessimisticLockConstants;
import org.kuali.rice.core.api.util.RiceConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.uif.util.LifecycleElement;
import org.kuali.rice.krad.uif.view.FormView;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

public class ProposalBudgetView extends FormView {

    private static final String KC_ERROR_TRANSACTIONAL_LOCKED = "kc.error.transactional.locked.budget";
    private static final String ERROR_TRANSACTIONAL_LOCKED = "error.transactional.locked.budget";

    private ParameterService parameterService;
    private ProposalBudgetLockService proposalBudgetLockService;

    @Override
    public void performFinalize(Object model, LifecycleElement parent) {
        super.performFinalize(model, parent);
        generatePessimisticLockMessages((ProposalBudgetForm) model);
        ((ProposalBudgetForm) model).setCanEditView(!this.getReadOnly());
    }

    protected void generatePessimisticLockMessages(ProposalBudgetForm form) {
        Document document = form.getBudget().getDevelopmentProposal().getDocument();
        Person user = GlobalVariables.getUserSession().getPerson();
        int budgetVersion = form.getBudget().getBudgetVersionNumber();
        for (PessimisticLock lock : document.getPessimisticLocks()) {
            if (!lock.isOwnedByUser(user) && getProposalBudgetLockService().doesBudgetVersionMatchDescriptor(lock.getLockDescriptor(), budgetVersion)) {
                String lockDescriptor = StringUtils.defaultIfBlank(lock.getLockDescriptor(), "full");
                String lockOwner = lock.getOwnedByUser().getName();
                String lockTime = RiceConstants.getDefaultTimeFormat().format(lock.getGeneratedTimestamp());
                String lockDate = RiceConstants.getDefaultDateFormat().format(lock.getGeneratedTimestamp());

                if (!getParameterService().getParameterValueAsBoolean("KC-GEN", "All", PessimisticLockConstants.ALLOW_CLEAR_PESSIMISTIC_LOCK_PARM)) {
                    GlobalVariables.getMessageMap().putError(KRADConstants.GLOBAL_ERRORS,
                            ERROR_TRANSACTIONAL_LOCKED, lockDescriptor, lockOwner, lockTime, lockDate);
                } else {
                    GlobalVariables.getMessageMap().putError(KRADConstants.GLOBAL_ERRORS,
                            KC_ERROR_TRANSACTIONAL_LOCKED, lockDescriptor, lockOwner, lockTime, lockDate, lock.getId().toString());
                }
            }
        }
    }

    public ParameterService getParameterService() {
        if (parameterService == null) {
            parameterService = KcServiceLocator.getService(ParameterService.class);
        }

        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    public ProposalBudgetLockService getProposalBudgetLockService() {
        if (proposalBudgetLockService == null) {
            proposalBudgetLockService = KcServiceLocator.getService(ProposalBudgetLockService.class);
        }
        return proposalBudgetLockService;
    }

    public void setProposalBudgetLockService(ProposalBudgetLockService proposalBudgetLockService) {
        this.proposalBudgetLockService = proposalBudgetLockService;
    }
}
