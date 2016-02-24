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
package org.kuali.coeus.common.framework.custom;

import org.kuali.coeus.common.impl.custom.CustomDataRule;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

/**
 * This class processes audit rules (warnings) for various KcTransactionalDocument extensions
 */
public class KcDocumentBaseAuditRule implements DocumentAuditRule {

    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;

        if (document instanceof KcTransactionalDocumentBase) {
            valid = new CustomDataRule().processRules(new AuditCustomDataEvent((KcTransactionalDocumentBase)document));
        }

        return valid;
    }

}
