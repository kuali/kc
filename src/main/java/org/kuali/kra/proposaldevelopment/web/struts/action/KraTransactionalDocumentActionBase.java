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
package org.kuali.kra.proposaldevelopment.web.struts.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.Constants;
import org.kuali.core.service.DataDictionaryService;
import org.kuali.core.web.struts.action.KualiTransactionalDocumentActionBase;
import org.kuali.kra.infrastructure.KraServiceLocator;

public class KraTransactionalDocumentActionBase extends KualiTransactionalDocumentActionBase {
    private static final Log LOG = LogFactory.getLog(KraTransactionalDocumentActionBase.class);
    public ActionForward updateTextArea(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
        
        // parse out the important strings from our methodToCall parameter
        String fullParameter = (String) request.getAttribute(Constants.METHOD_TO_CALL_ATTRIBUTE);

        // parse textfieldname:htmlformaction
        String parameterFields = StringUtils.substringBetween(fullParameter, Constants.METHOD_TO_CALL_PARM2_LEFT_DEL, Constants.METHOD_TO_CALL_PARM2_RIGHT_DEL);
        if ( LOG.isDebugEnabled() ) {
            LOG.debug( "fullParameter: " + fullParameter );
            LOG.debug( "parameterFields: " + parameterFields );
        }
        String[] keyValue = null;
        if (StringUtils.isNotBlank(parameterFields)) {
            String[] textAreaParams = parameterFields.split(Constants.FIELD_CONVERSIONS_SEPERATOR);
            if ( LOG.isDebugEnabled() ) {
                LOG.debug( "lookupParams: " + textAreaParams );
            }
            for (int i = 0; i < textAreaParams.length; i++) {
                keyValue = textAreaParams[i].split(Constants.FIELD_CONVERSION_PAIR_SEPERATOR);

                if ( LOG.isDebugEnabled() ) {
                    LOG.debug( "keyValue[0]: " + keyValue[0] );
                    LOG.debug( "keyValue[1]: " + keyValue[1] );
                }
            }
        }
        request.setAttribute(org.kuali.kra.infrastructure.Constants.TEXT_AREA_FIELD_NAME, keyValue[0]);
        request.setAttribute(org.kuali.kra.infrastructure.Constants.HTML_FORM_ACTION,keyValue[1]);
        request.setAttribute(org.kuali.kra.infrastructure.Constants.TEXT_AREA_FIELD_LABEL,keyValue[2]);
        return mapping.findForward("updateTextArea");

    }
    public ActionForward postTextAreaToParent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("basic");
    }

    
}
