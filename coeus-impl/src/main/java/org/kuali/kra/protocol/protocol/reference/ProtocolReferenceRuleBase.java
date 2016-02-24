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
package org.kuali.kra.protocol.protocol.reference;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;

/**
 * This class is implementation of <code>AddProtocolReferenceRule</code> interface. Impl makes sure necessary rules are satisfied 
 * before object can be used.
 */
public abstract class ProtocolReferenceRuleBase extends KcTransactionalDocumentRuleBase implements AddProtocolReferenceRule {

    @Override
    public boolean processAddProtocolReferenceBusinessRules(AddProtocolReferenceEventBase addProtocolReferenceEvent) {
        
        boolean rulePassed = true;
        
        if (null == addProtocolReferenceEvent.getProtocolReferenceBean().getProtocolReferenceTypeCode()) {
            rulePassed = false;
            reportError("newProtocolReferenceBean"+".protocolReferenceTypeCode", KeyConstants.ERROR_PROTOCOLREFERENCE_PROTOCOLREFERENCETYPECODE, "Type");
        }
         
        if (StringUtils.isBlank(addProtocolReferenceEvent.getProtocolReferenceBean().getReferenceKey())) {
            rulePassed = false;
            reportError("newProtocolReferenceBean"+".referenceKey", KeyConstants.ERROR_PROTOCOLREFERENCE_PROTOCOLREFERENCEKEY, "Other Identifier");
        }
        
        if (!validateDate(addProtocolReferenceEvent.getProtocolReferenceBean().getApplicationDate())){
            rulePassed = false;
            reportError("newProtocolReferenceBean"+".applicationDate", KeyConstants.ERROR_PROTOCOLREFERENCE_INVALID_DATE, "Application Date");
        }
        
        if (!validateDate(addProtocolReferenceEvent.getProtocolReferenceBean().getApprovalDate())){
            rulePassed = false;
            reportError("newProtocolReferenceBean"+".approvalDate", KeyConstants.ERROR_PROTOCOLREFERENCE_INVALID_DATE, "Approval Date");
        }
            
        return rulePassed;
    }
    
    private boolean validateDate(String stringDate) {
        try {
            if (!StringUtils.isBlank(stringDate)) {
                Date date = new Date(DateFormat.getDateInstance(DateFormat.SHORT).parse(stringDate).getTime());
            }
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

}
