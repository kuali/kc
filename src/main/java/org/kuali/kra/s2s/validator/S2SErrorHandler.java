/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.s2s.validator;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.DOMBuilder;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.util.AuditError;

public class S2SErrorHandler {
    private static Map<String, AuditError> auditErrorMap;

    public static AuditError getError(String key) {
        if (auditErrorMap == null) {
            loadErrors();
        }
        AuditError error = auditErrorMap.get(key);
        AuditError defaultError = new AuditError(Constants.NO_FIELD, key + " is not valid", Constants.GRANTS_GOV_PAGE + "."
                + Constants.GRANTS_GOV_PANEL_ANCHOR);
        return error == null ? defaultError : error;
    }

    private static void loadErrors() {
        InputStream stream = null;
        try {
            stream = new S2SErrorHandler().getClass().getResourceAsStream("/S2SErrorMessages.xml");
            org.w3c.dom.Document errorsDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
            Document document = new DOMBuilder().build(errorsDocument);
            Element root = document.getRootElement();

            auditErrorMap = new HashMap<String, AuditError>();
            for (Iterator errorsElementIt = root.getChildren("Error").iterator(); errorsElementIt.hasNext();) {
                Element errorElement = (Element) errorsElementIt.next();
                String errorKey = errorElement.getChildTextTrim("ErrorKey");
                String messageKey = errorElement.getChildTextTrim("MessageKey");
                String errorMessage = errorElement.getChildTextTrim("Message");
                String errorFixLink = errorElement.getChildTextTrim("FixLink");
                errorFixLink = errorFixLink == null || errorFixLink.equals("") ? Constants.GRANTS_GOV_PAGE + "."
                        + Constants.GRANTS_GOV_PANEL_ANCHOR : errorFixLink;
                AuditError s2sError = new AuditError(errorKey == null ? Constants.NO_FIELD : errorKey, errorMessage, errorFixLink);
                auditErrorMap.put(messageKey, s2sError);
            }
            //            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
