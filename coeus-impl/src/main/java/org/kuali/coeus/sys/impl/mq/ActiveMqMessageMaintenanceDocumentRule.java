package org.kuali.coeus.sys.impl.mq;

import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.rules.MaintenanceDocumentRuleBase;

public class ActiveMqMessageMaintenanceDocumentRule extends MaintenanceDocumentRuleBase {

    @Override
    protected boolean primaryKeyCheck(MaintenanceDocument document) {
        return true;
    }
}
