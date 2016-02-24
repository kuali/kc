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
