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
package org.kuali.coeus.propdev.impl.core;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants;
import org.kuali.coeus.sys.framework.view.KcTransactionalDocumentView;
import org.kuali.coeus.sys.impl.lock.PessimisticLockConstants;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.RiceConstants;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.web.form.TransactionalDocumentFormBase;

public class ProposalDocumentView extends KcTransactionalDocumentView {
    private static final String KC_ERROR_TRANSACTIONAL_LOCKED = "kc.error.transactional.locked";
    public static final String FULL = "full";


    @Override
    protected void generatePessimisticLockMessages(TransactionalDocumentFormBase form) {
    	ProposalDevelopmentDocumentForm proposalDevelopmentDocumentForm = (ProposalDevelopmentDocumentForm)form;
    	if(!proposalDevelopmentDocumentForm.isViewOnly() || canSaveCertification(proposalDevelopmentDocumentForm)) {
            Document document = form.getDocument();
            String pageId = proposalDevelopmentDocumentForm.getPageId();
            Person user = GlobalVariables.getUserSession().getPerson();

            for (PessimisticLock lock : document.getPessimisticLocks()) {
                String lockRegion = lock.getLockDescriptor() != null ? StringUtils.split(lock.getLockDescriptor(),"-")[1] : null;
                if (!lock.isOwnedByUser(user)  && !StringUtils.equals(pageId,ProposalDevelopmentConstants.KradConstants.BUDGET_PAGE)
                        && !StringUtils.equals(lockRegion,KraAuthorizationConstants.LOCK_DESCRIPTOR_BUDGET)) {
                    String lockDescriptor = StringUtils.defaultIfBlank(lock.getLockDescriptor(), FULL);
                    String lockOwner = lock.getOwnedByUser().getName();
                    String lockTime = RiceConstants.getDefaultTimeFormat().format(lock.getGeneratedTimestamp());
                    String lockDate = RiceConstants.getDefaultDateFormat().format(lock.getGeneratedTimestamp());

                    if (!getParameterService().getParameterValueAsBoolean(Constants.KC_GENERIC_PARAMETER_NAMESPACE , Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, PessimisticLockConstants.ALLOW_CLEAR_PESSIMISTIC_LOCK_PARM)) {
                    GlobalVariables.getMessageMap().putError(KRADConstants.GLOBAL_ERRORS,
                            RiceKeyConstants.ERROR_TRANSACTIONAL_LOCKED, lockDescriptor, lockOwner, lockTime, lockDate);
                    } else {
                        GlobalVariables.getMessageMap().putError(KRADConstants.GLOBAL_ERRORS,
                                KC_ERROR_TRANSACTIONAL_LOCKED, lockDescriptor, lockOwner, lockTime, lockDate, lock.getId().toString());
                    }
                    removeSaveCertification(proposalDevelopmentDocumentForm);
                }
            }
    	}
    }
    
    protected boolean canSaveCertification(ProposalDevelopmentDocumentForm proposalDevelopmentDocumentForm) {
    	Map<String, Boolean> editModes = proposalDevelopmentDocumentForm.getEditModes();
    	return editModes.get(ProposalDevelopmentConstants.AuthConstants.CAN_SAVE_CERTIFICATION);
    }

    protected void removeSaveCertification(ProposalDevelopmentDocumentForm proposalDevelopmentDocumentForm) {
    	Map<String, Boolean> editModes = proposalDevelopmentDocumentForm.getEditModes();
    	editModes.remove(ProposalDevelopmentConstants.AuthConstants.CAN_SAVE_CERTIFICATION);
    }

}
