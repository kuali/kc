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
package org.kuali.kra.s2s.formmapping;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.s2s.bo.S2sUserAttachedForm;
import org.kuali.kra.s2s.generator.S2SGeneratorNotFoundException;
import org.kuali.kra.s2s.util.S2SConstants;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;




/**
 * This class is used to bind the S2SFormBinding.xml and get the information about the namespace , mainclass , stylesheet and
 * package name.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */

public class FormMappingLoader {

    private static Map<String, FormMappingInfo> bindings;
    private static Map<Integer, List<String>> sortedNameSpaces;
    private static final String BINDING_FILE_NAME = "/S2SFormBinding.xml";
    private static final String BINDING_FILE_NAME_V2="/org/kuali/kra/s2s/s2sform/S2SFormBinding-V2.xml";
    private static final String BINDING_FILE_NAME_BACKPORT="/org/kuali/kra/s2s/backport/S2SFormBinding-Backport.xml";
    private static final String NAMESPACE = "namespace";
    private static final String MAIN_CLASS = "mainClass";
    private static final String STYLE_SHEET = "stylesheet";
    private static final String PKG_NAME = "pkgName";
    private static final String FORM_NAME = "formName";
    private static final String TAG_FORM = "Form";
    private static final String SORT_INDEX = "sortIndex";
    private static final int DEFAULT_SORT_INDEX = 1000;
    private static final Log LOG = LogFactory.getLog(FormMappingLoader.class);

    
    /**
     * 
     * This method is used to get the Form Information based on the given schema
     * 
     * @param nameSpace {@link String} namespace URL of the form
     * @return {@link formMappingInfo}containing the namespace information
     * @throws S2SGeneratorNotFoundException
     * 
     */
    public FormMappingInfo getFormInfo(String namespace) {
        return getFormInfo(null,namespace);
    }
    
    public FormMappingInfo getFormInfo(String proposalNumber,String namespace) {
        getBindings();
        FormMappingInfo formMappingInfo = bindings.get(namespace);
        if (formMappingInfo != null) {
            return formMappingInfo;
        }else if(proposalNumber!=null){
            return getUserAttachedForm(proposalNumber,namespace);
        }else{
            return null;
        }
    }

    private FormMappingInfo getUserAttachedForm(String proposalNumber,String namespace) {
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("proposalNumber", proposalNumber);
        fieldMap.put("namespace", namespace);
        List<S2sUserAttachedForm> formList = (List<S2sUserAttachedForm>) KraServiceLocator.getService(BusinessObjectService.class).
                                                    findMatching(S2sUserAttachedForm.class,fieldMap);
        if(!formList.isEmpty()){
            S2sUserAttachedForm userAttachedForm=formList.get(0);
            FormMappingInfo mappingInfo = new FormMappingInfo();
            mappingInfo.setFormName(userAttachedForm.getFormName());
            mappingInfo.setMainClass("org.kuali.kra.s2s.generator.impl.UserAttachedFormGenerator");
            mappingInfo.setNameSpace(namespace);
            mappingInfo.setSortIndex(999);
            mappingInfo.setStyleSheet(createStylesheetName(namespace));
            mappingInfo.setUserAttachedForm(true);
            return mappingInfo;
        }else return null;
        
    }

    private String createStylesheetName(String namespace) {
        String[] tokens = namespace.split("/");
        String formname = tokens[tokens.length-1];
        String templateName = "org/kuali/kra/s2s/stylesheet/"+formname+".xsl";
        return templateName;
    }

    /**
     * 
     * This method is used to bind FormMappingLoader class
     * 
     * @return {@link Map}
     */

    public Map<String, FormMappingInfo> getBindings() {
        if (bindings == null) {
            synchronized (FormMappingLoader.class) {
            	bindings = new Hashtable<String, FormMappingInfo>();
                sortedNameSpaces = new TreeMap<Integer, List<String>>();
                loadBindings(BINDING_FILE_NAME);
                if((new FormMappingLoader().getClass().getResourceAsStream(BINDING_FILE_NAME_V2))!=null)
                loadBindings(BINDING_FILE_NAME_V2);
                if((new FormMappingLoader().getClass().getResourceAsStream(BINDING_FILE_NAME_BACKPORT))!=null)
                    loadBindings(BINDING_FILE_NAME_BACKPORT);
            }
        }
        return bindings;
    }


    /**
     * 
     * This method is used to load the bindings from S2SFormBinding.xml file
     */
    private void loadBindings(String BindingFile) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document = null;
        DocumentBuilder builder;
        Integer defaultSortIndex = Integer.valueOf(DEFAULT_SORT_INDEX);
        if(!sortedNameSpaces.containsKey(defaultSortIndex))
        	sortedNameSpaces.put(defaultSortIndex, new ArrayList<String>());
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new FormMappingLoader().getClass().getResourceAsStream(BindingFile));
        }
        catch (ParserConfigurationException e) {
            LOG.error(S2SConstants.ERROR_MESSAGE, e);
            return;
        }
        catch (SAXException e) {
            LOG.error(S2SConstants.ERROR_MESSAGE, e);
            return;
        }
        catch (IOException e) {
            LOG.error(S2SConstants.ERROR_MESSAGE, e);
            return;
        }
        Integer sortedIndex;
        List<String> nameSpaceList;
        NodeList formList = document.getElementsByTagName(TAG_FORM);
        for (int index = 0; index < formList.getLength(); index++) {
            Element formNode = (Element) formList.item(index);
            FormMappingInfo formInfo = new FormMappingInfo();
            formInfo.setNameSpace(formNode.getAttribute(NAMESPACE).trim());
            formInfo.setFormName(formNode.getElementsByTagName(FORM_NAME).item(0).getTextContent().trim());
            formInfo.setMainClass(formNode.getElementsByTagName(MAIN_CLASS).item(0).getTextContent().trim());
            formInfo.setUserAttachedForm(false);
            formInfo.setStyleSheet(formNode.getElementsByTagName(STYLE_SHEET).item(0).getTextContent().trim());
//            formInfo.setPkgName(formNode.getElementsByTagName(PKG_NAME).item(0).getTextContent().trim());

            NodeList sortIndexNodesList = formNode.getElementsByTagName(SORT_INDEX);
            if (sortIndexNodesList.getLength() > 0) {
                formInfo.setSortIndex(Integer.valueOf(sortIndexNodesList.item(0).getTextContent().trim()).intValue());
                sortedIndex = Integer.valueOf(formInfo.getSortIndex());

                if (sortedNameSpaces.get(sortedIndex) != null) {
                    nameSpaceList = sortedNameSpaces.get(sortedIndex);
                }
                else {
                    nameSpaceList = new ArrayList<String>();
                }
                nameSpaceList.add(formInfo.getNameSpace());
                sortedNameSpaces.put(sortedIndex, nameSpaceList);
            }
            else {
                formInfo.setSortIndex(DEFAULT_SORT_INDEX);
                nameSpaceList = sortedNameSpaces.get(defaultSortIndex);
                nameSpaceList.add(formInfo.getNameSpace());
                sortedNameSpaces.put(defaultSortIndex, nameSpaceList);
            }
            bindings.put(formInfo.getNameSpace(), formInfo);
        }
    }

    /**
     * Gets the sortedNameSpaces attribute.
     * 
     * @return Returns the sortedNameSpaces.
     */
    public Map<Integer, List<String>> getSortedNameSpaces() {
        return sortedNameSpaces;
    }

}
