/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.impl.krms;


import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.coeus.common.framework.krms.KcKrmsFactBuilderService;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.core.api.util.xml.XmlHelper;
import org.kuali.rice.kew.rule.xmlrouting.XPathHelper;
import org.kuali.rice.krms.api.engine.Facts;
import org.kuali.rice.krms.api.repository.category.CategoryDefinition;
import org.kuali.rice.krms.api.repository.term.TermRepositoryService;
import org.kuali.rice.krms.api.repository.term.TermSpecificationDefinition;
import org.w3c.dom.Document;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
/**
 * 
 * This is an abstract helper class to add facts for an object which has members defined with proper annotation.
 * FactBuilderServices can extend this class and use addObjectMembersAsFacts while implementing <code>addFacts</code> method.
 */
public abstract class KcKrmsFactBuilderServiceHelper implements KcKrmsFactBuilderService {
    
    protected final Log LOG = LogFactory.getLog(KcKrmsFactBuilderServiceHelper.class);
    
    protected String getElementValue(String docContent, String xpathExpression) {
        try {
            Document document = XmlHelper.trimXml(new ByteArrayInputStream(docContent.getBytes()));

            XPath xpath = XPathHelper.newXPath();
            String value = (String) xpath.evaluate(xpathExpression, document, XPathConstants.STRING);

            return value;

        } catch (Exception e) {
            throw new RiceRuntimeException();
        }
    }

    /**
     * 
     * This method gets all terms from the <code>factsObject</code>and add it to the KRMS FactsBuilder 
     * @param factsBuilder
     * @param factsObject
     * @param contextId
     * @param factTermNameSpace
     */
    public void addObjectMembersAsFacts(Facts.Builder factsBuilder, Object factsObject,String contextId, String factTermNS) {
        TermRepositoryService termRepositoryService = KcServiceLocator.getService("termRepositoryService");
        List<TermSpecificationDefinition> termSpecs=(List<TermSpecificationDefinition>) termRepositoryService.findAllTermSpecificationsByContextId(contextId);
        for (TermSpecificationDefinition termSpecificationDefinition : termSpecs) {
            
            if(isPropertyType(termSpecificationDefinition)){
                String termNS = termSpecificationDefinition.getNamespace();
                if(termNS.equals(factTermNS)){
                    String factKey = termSpecificationDefinition.getName();
                    if(factsObject!=null){
                        Class factsClazz = factsObject.getClass();
                        PropertyDescriptor propDescriptor = null;
                        try {
                            propDescriptor = PropertyUtils.getPropertyDescriptor(factsObject, factKey);
                            if(propDescriptor!=null){
                                Object propertyValue = null;
                                Method readMethod = propDescriptor.getReadMethod();
                                if(readMethod!=null){
                                    propertyValue = propDescriptor.getReadMethod().invoke(factsObject);
                                }
                                if(propertyValue!=null && propertyValue.getClass().isAssignableFrom(ScaleTwoDecimal.class)){
                                    propertyValue = ((ScaleTwoDecimal)propertyValue).bigDecimalValue();
                                }
                                factsBuilder.addFact(factKey, propertyValue);
                            }
                        }catch (IllegalArgumentException e) {
                            LOG.error("KRMS Fact for " + factKey + " has not been added to fact builder", e);
                        }catch (IllegalAccessException e) {
                            LOG.error("KRMS Fact for " + factKey + " has not been added to fact builder", e);
                        }catch (InvocationTargetException e) {
                            LOG.error("KRMS Fact for " + factKey + " has not been added to fact builder", e);
                        }catch (NoSuchMethodException e) {
                            LOG.error("KRMS Fact for " + factKey + " has not been added to fact builder", e);
                        }
                    }else{
                        factsBuilder.addFact(factKey, null);
                    }
                }
            }
        }
    }

    private boolean isPropertyType(TermSpecificationDefinition termSpecificationDefinition) {
        List<CategoryDefinition> catgories = termSpecificationDefinition.getCategories();
        for (CategoryDefinition categoryDefinition : catgories) {
            if(categoryDefinition.getName().equals(KcKrmsConstants.KRMS_PROPERTY_TYPE_NM))
                return true;
        }
        return false;
    }

}