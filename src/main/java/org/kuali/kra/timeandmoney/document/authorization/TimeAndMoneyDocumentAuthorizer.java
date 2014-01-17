/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.timeandmoney.document.authorization;

import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.document.authorization.TransactionalDocumentAuthorizerBase;

import java.util.Map;

/**
 * This class is the Time and Money Document Authorizer.  It determines the edit modes and
 * document actions for all time and money documents.
 */
public class TimeAndMoneyDocumentAuthorizer extends TransactionalDocumentAuthorizerBase {
    
    public static final String CREATE_TIME_AND_MONEY_PERMISSION = "Create Time And Money Document";
     
    @Override
    protected void addRoleQualification(
            Object primaryBusinessObjectOrDocument,
            Map<String, String> attributes) {
        super.addRoleQualification(primaryBusinessObjectOrDocument, attributes);
        TimeAndMoneyDocument timeAndMoneyDocument = (TimeAndMoneyDocument) primaryBusinessObjectOrDocument;
        if (timeAndMoneyDocument.getAward() != null 
                && timeAndMoneyDocument.getAward().getLeadUnit() != null) {
            attributes.put(KcKimAttributes.UNIT_NUMBER, timeAndMoneyDocument.getAward().getLeadUnit().getUnitNumber());
        } else {
            attributes.put(KcKimAttributes.UNIT_NUMBER, "*");
        }
    }
    
    public boolean hasCreatePermission(TimeAndMoneyDocument timeAndMoneyDocument, Person user) {
        boolean retVal = hasPermission(timeAndMoneyDocument, user, CREATE_TIME_AND_MONEY_PERMISSION);
        return retVal;
    }
    
    private boolean hasPermission(TimeAndMoneyDocument timeAndMoneyDocument, Person user, String permissionName) {
        return this.isAuthorized(timeAndMoneyDocument, "KC-T", permissionName, user.getPrincipalId());
    }
}
