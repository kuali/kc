package org.kuali.coeus.sys.framework.view;

import org.apache.commons.lang.StringUtils;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.impl.lock.PessimisticLockConstants;
import org.kuali.rice.core.api.util.RiceConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.kuali.rice.krad.uif.view.TransactionalDocumentView;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.web.form.TransactionalDocumentFormBase;

import java.util.List;

public class KcTransactionalDocumentView extends TransactionalDocumentView {

    private static final String KC_ERROR_TRANSACTIONAL_LOCKED = "kc.error.transactional.locked";

    private ParameterService parameterService;

    /**
     * This is here so that every view doesn't need to remember to include this script.
     */
    @Override
    public List<String> getAdditionalScriptFiles() {
        List<String> files = super.getAdditionalScriptFiles();
        if (!files.contains("scripts/sys/pessimisticLock.js")){
            files.add("scripts/sys/pessimisticLock.js");
            setAdditionalScriptFiles(files);
        }
        return files;
    }

    @Override
    protected void generatePessimisticLockMessages(TransactionalDocumentFormBase form) {
        if (!getParameterService().getParameterValueAsBoolean("KC-GEN", "All", PessimisticLockConstants.ALLOW_CLEAR_PESSIMISTIC_LOCK_PARM)) {
            super.generatePessimisticLockMessages(form);
        } else {
            Document document = form.getDocument();
            Person user = GlobalVariables.getUserSession().getPerson();

            for (PessimisticLock lock : document.getPessimisticLocks()) {
                if (!lock.isOwnedByUser(user)) {
                    String lockDescriptor = StringUtils.defaultIfBlank(lock.getLockDescriptor(), "full");
                    String lockOwner = lock.getOwnedByUser().getName();
                    String lockTime = RiceConstants.getDefaultTimeFormat().format(lock.getGeneratedTimestamp());
                    String lockDate = RiceConstants.getDefaultDateFormat().format(lock.getGeneratedTimestamp());

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
}
