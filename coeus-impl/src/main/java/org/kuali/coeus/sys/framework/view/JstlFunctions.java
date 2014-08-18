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
package org.kuali.coeus.sys.framework.view;

import org.apache.commons.beanutils.BeanUtils;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.krad.keyvalues.KeyValuesFinder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * Full of static methods for JSTL function access.
 * 
 * @author $Author: gmcgrego $
 * @version $Revision: 1.7 $
 */
public final class JstlFunctions {
    private static final String SETTING_PARAMS_PROLOG = "Setting params ";
    private static final String PROPERTY_SETTING_EXC_PROLOG = "Could not set property ";
    private static final String VALUES_FINDER_CLASS_EXC_PROLOG = "Could not find valuesFinder class ";
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(JstlFunctions.class);

    private static KcAttachmentService KC_ATTACHMENT_SERVICE;

    private JstlFunctions() {}
    
    /**
     * Returns a list of key/value pairs for displaying in an HTML option for a select list. This is a customized approach to retrieving
     * key/value data from database based on criteria specified in the <code>params {@link Map}</code><br/>
     * <br/>
     * Here is an example of how the code is used from a JSP:<br/>
     * <code>
     * <jsp:useBean id="paramMap" class="java.util.HashMap"/>
                    <c:set target="${paramMap}" property="forAddedPerson" value="true" />
                    <kul:checkErrors keyMatch="${proposalPerson}.proposalPersonRoleId" auditMatch="${proposalPerson}.proposalPersonRoleId"/>  
                    <c:set var="roleStyle" value=""/>
                    <c:if test="${hasErrors==true}">
                        <c:set var="roleStyle" value="background-color:#FFD5D5"/>
                    </c:if>
                    <html:select property="${proposalPerson}.proposalPersonRoleId" tabindex="0" style="${roleStyle}">
                    <c:forEach items="${krafn:getOptionList('org.kuali.coeus.propdev.impl.person.ProposalPersonRoleValuesFinder', paramMap)}" var="option">
                    <c:choose>
                        <c:when test="${KualiForm.document.proposalPersons[personIndex].proposalPersonRoleId == option.key}">
                        <option value="${option.key}" selected>${option.label}</option>
                        </c:when>
                        <c:otherwise>
                        <option value="${option.key}">${option.label}</option>
                        </c:otherwise>
                    </c:choose>
                    </c:forEach>
                    </html:select>
       </code>
     * 
     * 
     * @param valuesFinderClassName
     * @param params mapped parameters
     * @return List of key values
     */
    @SuppressWarnings("unchecked")
    public static List getOptionList(String valuesFinderClassName, Map params) {
        return setupValuesFinder(valuesFinderClassName, (Map<String, Object>) params).getKeyValues();
    }

    /**
     * Returns the BigDecimal value wrapped inside the given ScaleTwoDecimal in order to get correct type coercion for Jetty.
     * Here is an example of how the code is used in a JSP/tag file:
     * <code>
     * <c:set var="cumTotalCost" value="${cumTotalCost + krafn:getBigDecimal(budgetPeriodObj.totalCost)}" />
     * </code>
     */
    public static BigDecimal getBigDecimal(ScaleTwoDecimal scaleTwoDecimal) {
        return scaleTwoDecimal.bigDecimalValue();
    }

    public static String getIconPath(String type) {
        return getKcAttachmentService().getFileTypeIcon(type);
    }

    /**
     * Returns the float value of a given ScaleTwoDecimal.
     * Here is an example of how the code is used in a JSP/tag file:
     * <code>
     * <c:set var="cumTotalCost" value="${cumTotalCost + krafn:getFloatValue(budgetPeriodObj.totalCost)}" />
     * </code>
     */
    public static float getFloatValue(ScaleTwoDecimal scaleTwoDecimal) {
        return scaleTwoDecimal.floatValue();
    }
    
    /**
     * Initiates the values finder by its <code>valuesFinderClassName</code>. First locates the class in the class path. Then, 
     * creates an instance of it. A <code>{@link Map}</code> of key/values <code>{@link String}</code> instances a is used
     * to set properties on the values finder instance. Uses the apache <code>{@link BeanUtils}</code> class to set properties
     * by the name of the key in the <code>{@link Map}</code>.<br/>
     * <br/>
     * Basically, a new values finder is created. the <code>params</code> parameter is a <code>{@link Map}</code> of arbitrary values
     * mapped to properties of the values finder class.<br/>
     * <br/>
     * Since this is so flexible and the ambiguity of properties referenced in the <code>{@link Map}</code>, a number of exceptions are caught
     * if a property cannot be set or if the values finder cannot be instantiated. All of these exceptions are handled within the method. None
     * of these exceptions are thrown back.
     * 
     * 
     * @param valuesFinderClassName
     * @param params
     * @return KeyValuesFinder
     * @see BeanUtils#setProperty(Object, String, Object)
     */
    private static KeyValuesFinder setupValuesFinder(String valuesFinderClassName, Map<String, Object> params) {
        KeyValuesFinder retval = getKeyFinder(valuesFinderClassName);
        
        if(LOG.isDebugEnabled()) {
            LOG.debug(SETTING_PARAMS_PROLOG + params);
        }
        
        addParametersToFinder(params, retval);

        return retval;
    }

    private static void addParametersToFinder(Map<String, Object> params, KeyValuesFinder finder) {
        if (finder != null && params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                try {
                    BeanUtils.setProperty(finder, entry.getKey(), entry.getValue());
                } catch (Exception e) {
                    warn(PROPERTY_SETTING_EXC_PROLOG + entry.getKey(), e);
                }
            }
        }
    }

    private static KeyValuesFinder getKeyFinder(String valuesFinderClassName) {
        KeyValuesFinder retval = null;
        try {
            retval = (KeyValuesFinder) Class.forName(valuesFinderClassName).newInstance();
        } catch (ClassNotFoundException|InstantiationException|IllegalAccessException e) {
            warn(VALUES_FINDER_CLASS_EXC_PROLOG + valuesFinderClassName, e);
        }
        return retval;
    }

    private static void warn(String message, Exception e) {
        if (LOG.isWarnEnabled()) {
            LOG.warn(message, e);
        }
    }

    public static KcAttachmentService getKcAttachmentService() {
        if (KC_ATTACHMENT_SERVICE == null) {
            KC_ATTACHMENT_SERVICE = KcServiceLocator.getService(KcAttachmentService.class);
        }

        return KC_ATTACHMENT_SERVICE;
    }

    public static void setKcAttachmentService(KcAttachmentService KcAttachmentService) {
        KC_ATTACHMENT_SERVICE = KcAttachmentService;
    }
}
