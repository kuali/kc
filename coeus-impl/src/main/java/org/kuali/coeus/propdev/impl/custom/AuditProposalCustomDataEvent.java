package org.kuali.coeus.propdev.impl.custom;

import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.AUDIT_ERRORS;
import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.SUPPLEMENTAL_PAGE_ID;
import static org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants.SUPPLEMENTAL_PAGE_NAME;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.AuditCustomDataEvent;
import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;

public class AuditProposalCustomDataEvent extends AuditCustomDataEvent {
	
	public AuditProposalCustomDataEvent(KcTransactionalDocumentBase document) {
		super(document);
	}

	@Override
	public void reportError(CustomAttribute customAttribute, String propertyName, String errorKey, String... errorParams) {
        String key = SUPPLEMENTAL_PAGE_NAME + "." +customAttribute.getGroupName();
        AuditCluster auditCluster = GlobalVariables.getAuditErrorMap().get(key);
        if (auditCluster == null) {
            List<AuditError> auditErrors = new ArrayList<AuditError>();
            auditCluster = new AuditCluster(key, auditErrors, AUDIT_ERRORS);
            GlobalVariables.getAuditErrorMap().put(key, auditCluster);
        }
        List<AuditError> auditErrors = auditCluster.getAuditErrorList();
        auditErrors.add(new AuditError(StringUtils.removePattern(customAttribute.getGroupName() + "_" + customAttribute.getLabel(), "([^0-9a-zA-Z\\-_])"),
                errorKey, SUPPLEMENTAL_PAGE_ID + "." + customAttribute.getGroupName().replace(" ","_"), errorParams));

    }
}
