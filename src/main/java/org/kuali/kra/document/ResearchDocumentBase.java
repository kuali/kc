/*
 * Copyright 2007 The Kuali Foundation.
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.document;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.kuali.core.document.TransactionalDocumentBase;
import org.kuali.core.service.DateTimeService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;

public class ResearchDocumentBase extends TransactionalDocumentBase {

    private String updateUser;
    private Timestamp updateTimestamp;
    private List<DocumentNextvalue> documentNextvalues;

    public ResearchDocumentBase() {
        super();
        documentNextvalues = new ArrayList<DocumentNextvalue>();
    }

    @Override
    public void prepareForSave() {
        super.prepareForSave();
        String updateUser = GlobalVariables.getUserSession().getLoggedInUserNetworkId();

        // Since the UPDATE_USER column is only VACHAR(8), we need to truncate this string if it's longer than 8 characters
        if (updateUser.length() > 8) {
            updateUser = updateUser.substring(0, 8);
        }

        setUpdateTimestamp(((DateTimeService)KraServiceLocator.getService(Constants.DATE_TIME_SERVICE_NAME)).getCurrentTimestamp());
        setUpdateUser(updateUser);
        //setProposalNextvalues(documentNextvalues);
    }

    public Timestamp getUpdateTimestamp() {
        return updateTimestamp;
    }
    public void setUpdateTimestamp(Timestamp updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public String getUpdateUser() {
        return updateUser;
    }
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public void setDocumentNextvalues(List<DocumentNextvalue> documentNextvalues) {
        this.documentNextvalues = documentNextvalues;
    }

    public List<DocumentNextvalue> getDocumentNextvalues() {
        return documentNextvalues;
    }

    public Integer getDocumentNextValue(String propertyName) {
        Integer propNextValue = 1;
        // search for property and get the latest number - increment for next call
        for(Iterator iter = documentNextvalues.iterator(); iter.hasNext();) {
            DocumentNextvalue documentNextvalue = (DocumentNextvalue)iter.next();
            if(documentNextvalue.getPropertyName().equalsIgnoreCase(propertyName)) {
                propNextValue = documentNextvalue.getNextValue();
                documentNextvalue.setNextValue(propNextValue + 1);
            }
        }
        // property does not exist - set initial value and increment for next call
        if(propNextValue == 1) {
            DocumentNextvalue documentNextvalue = new DocumentNextvalue();
            documentNextvalue.setNextValue(propNextValue + 1);
            documentNextvalue.setPropertyName(propertyName);
            
            documentNextvalues.add(documentNextvalue);
        }
        setDocumentNextvalues(documentNextvalues);
        return propNextValue;
    }

    // TODO : this is for the attachment that save attachment only when click 'add
    public DocumentNextvalue getDocumentNextvalueBo(String propertyName) {
        for(Iterator iter = documentNextvalues.iterator(); iter.hasNext();) {
            DocumentNextvalue documentNextvalue = (DocumentNextvalue)iter.next();
            if(documentNextvalue.getPropertyName().equalsIgnoreCase(propertyName)) {
                return documentNextvalue;
            }
        }
        // following should not happen because it already got the next value for this property before calling this for updating
        DocumentNextvalue documentNextvalue = new DocumentNextvalue();
        documentNextvalue.setNextValue(1);
        documentNextvalue.setPropertyName(propertyName);
        return documentNextvalue;
    } 
    
}
