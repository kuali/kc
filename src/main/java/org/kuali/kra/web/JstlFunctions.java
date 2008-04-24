/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.web;

import java.util.List;
import java.util.Map;

import org.kuali.core.lookup.keyvalues.KeyValuesFinder;
import org.apache.commons.beanutils.PropertyUtils;

import static java.lang.Class.forName;
import static org.apache.commons.beanutils.PropertyUtils.setProperty;
import static org.kuali.kra.logging.FormattedLogger.*;

/**
 * Full of static methods for JSTL function access.
 * 
 * @author $Author: lprzybyl $
 * @version $Revision: 1.6 $
 */
public class JstlFunctions {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(JstlFunctions.class);

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
                    <c:forEach items="${krafn:getOptionList('org.kuali.kra.proposaldevelopment.lookup.keyvalue.ProposalPersonRoleValuesFinder', paramMap)}" var="option">
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
     * @param params mapped parameters to make it simpler for <code>{@link PropertyUtils#setProperty(Object,String,Object)}</code>
     * @return List of <code>{@link KeyLabelPair}</code> instances
     */
    // START SNIPPET: jstlFunctions#getOptionList
    public static List getOptionList(String valuesFinderClassName, Map params) {
        return setupValuesFinder(valuesFinderClassName, (Map<String, String>) params).getKeyValues();
    }
    // END SNIPPET: jstlFunctions#getOptionList
    
    /**
     * Initiates the values finder by its <code>valuesFinderClassName</code>. First locates the class in the class path. Then, 
     * creates an instance of it. A <code>{@link Map}</code> of key/values <code>{@link String}</code> instances a is used
     * to set properties on the values finder instance. Uses the apache <code>{@link PropertyUtils}</code> class to set properties
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
     * @see PropertyUtils#setProperty(Object, String, Object)
     */
    // START SNIPPET: jstlFunctions#setupValuesFinder
    private static KeyValuesFinder setupValuesFinder(String valuesFinderClassName, Map<String, String> params) {
        KeyValuesFinder retval = null;
        LOG.info("In setupValuesFinder");
        try {
            retval = (KeyValuesFinder) forName(valuesFinderClassName).newInstance();                        
        }
        catch (ClassNotFoundException cnfe) {
            warn("Could not find valuesFinder class %s in %s", valuesFinderClassName , buildTraceMessage(cnfe));
        }
        catch (InstantiationException e) {
            warn("Could not instantiate valuesFinder class %s in %s", valuesFinderClassName , buildTraceMessage(e));
        }
        catch (IllegalAccessException e) {
            warn("Could not instantiate valuesFinder class %s in %s", valuesFinderClassName , buildTraceMessage(e));
        }
        
        info("Setting params %s", params);
        
        if (retval != null && params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                try {
                    setProperty(retval, entry.getKey(), entry.getValue());
                }
                catch (Exception e) {
                    LOG.warn("Could not set property " +  entry.getKey() + " in " + buildTraceMessage(e));
                    e.printStackTrace();
                }
            }
        }

        return retval;
    }
    // END SNIPPET: jstlFunctions#setupValuesFinder
    
    private Object buildPropertyParameter(Object obj, String propertyName) throws Exception {
        return PropertyUtils.getPropertyDescriptor(obj, propertyName).getWriteMethod().getParameterTypes()[0];
    }

    /**
     * Get the stack trace from a <code>{@link Throwable}</code> and create a log message from it for tracing purposes
     * 
     * @param thrownObj 
     * @return String log message
     */
    private static String buildTraceMessage(Throwable thrownObj) {
        return thrownObj.getStackTrace()[0].getClassName() + "#" 
            + thrownObj.getStackTrace()[0].getMethodName() + ":" 
                + thrownObj.getStackTrace()[0].getLineNumber() + " " + thrownObj.getClass().getSimpleName() + "\n" + thrownObj.getMessage();
    }
}
