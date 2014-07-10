/*
 * Copyright 2005-2014 The Kuali Foundation.
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
package org.kuali.coeus.s2sgen.impl.validate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlValidationError;
import org.kuali.coeus.s2sgen.api.core.AuditError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * This class validates a XML document passed as XMLObject
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
@Component("s2SValidatorService")
public class S2SValidatorServiceImpl implements S2SValidatorService {

    Log LOG = LogFactory.getLog(S2SValidatorServiceImpl.class);

    @Autowired
    @Qualifier("s2SErrorHandlerService")
    private S2SErrorHandlerService s2SErrorHandlerService;

    /**
     * This method receives an XMLObject and validates it against its schema and returns the validation result. It also receives a
     * list in which upon validation failure, populates it with XPaths of the error nodes.
     * 
     * @param formObject XML document as {@link}XMLObject
     * @param errors List list of XPaths of the error nodes.
     * @return validation result true if valid false otherwise.
     * @see S2SValidatorService#validate(org.apache.xmlbeans.XmlObject, java.util.List)
     */
    public boolean validate(XmlObject formObject, List<AuditError> errors) {

        List<String> formErrors = new ArrayList<String>();
        boolean result = false;
        result = validateXml(formObject, formErrors);

        for (String validationError : formErrors) {
            errors.add(s2SErrorHandlerService.getError(GRANTS_GOV_PREFIX + validationError));
        }

        return result;
    }


    /**
     * 
     * This method receives an XMLObject and validates it against its schema and returns the validation result. It also receives a
     * list in which upon validation failure, populates it with XPaths of the error nodes
     * 
     * @param formObject XML document as {@link}XMLObject
     * @param errors List list of XPaths of the error nodes.
     * @return validation result true if valid false otherwise.
     */
    protected boolean validateXml(XmlObject formObject, List<String> errors) {
        XmlOptions validationOptions = new XmlOptions();
        ArrayList<XmlValidationError> validationErrors = new ArrayList<XmlValidationError>();
        validationOptions.setErrorListener(validationErrors);

        boolean isValid = formObject.validate(validationOptions);

        if (!isValid) {
            LOG.error("Errors occured during validation of XML from form generator" + validationErrors);
            Iterator<XmlValidationError> iter = validationErrors.iterator();
            while (iter.hasNext()) {
                XmlError error = iter.next();
                LOG.info("Validation error:" + error);
                Node node = error.getCursorLocation().getDomNode();
                errors.add(getXPath(node));
            }
            LOG.debug("Error XPaths:" + errors);
        }
        return isValid;
    }

    /**
     * 
     * This method receives a node, fetches its name, and recurses up to its parent node, until it reaches the document node, thus
     * creating the XPath of the node passed and returns it as String
     * 
     * @param node for which Document node has to found.
     * @return String which represents XPath of the node
     */
    protected String getXPath(Node node) {
        if (node==null || node.getNodeType() == Node.DOCUMENT_NODE) {
            return "";
        }
        else {
            return getXPath(node.getParentNode()) + "/" + node.getNodeName();
        }
    }

    public S2SErrorHandlerService getS2SErrorHandlerService() {
        return s2SErrorHandlerService;
    }

    public void setS2SErrorHandlerService(S2SErrorHandlerService s2SErrorHandlerService) {
        this.s2SErrorHandlerService = s2SErrorHandlerService;
    }
}
