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
package org.kuali.coeus.s2sgen.impl.generate;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.propdev.api.s2s.UserAttachedFormService;
import org.kuali.coeus.s2sgen.api.generate.FormMappingInfo;
import org.kuali.coeus.s2sgen.api.generate.FormMappingService;
import org.kuali.coeus.s2sgen.impl.generate.support.UserAttachedFormGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
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
@Component("formMappingService")
public class FormMappingServiceImpl implements FormMappingService {

    private static Map<String, FormMappingInfo> bindings;
    private static Map<Integer, List<String>> sortedNameSpaces;
    private static final String NAMESPACE = "namespace";
    private static final String GENERATOR_NAME = "generatorName";
    private static final String STYLE_SHEET = "stylesheet";
    private static final String FORM_NAME = "formName";
    private static final String TAG_FORM = "Form";
    private static final String SORT_INDEX = "sortIndex";
    private static final int DEFAULT_SORT_INDEX = 1000;
    private static final Log LOG = LogFactory.getLog(FormMappingServiceImpl.class);
    private static final String ERROR_MESSAGE = "Exception Occurred";
    private static final String BASE_PACKAGE_PATH = "org/kuali/coeus/s2sgen/impl/generate";

    @Autowired
    @Qualifier("userAttachedFormService")
    private UserAttachedFormService userAttachedFormService;

    @Value("classpath:" + BASE_PACKAGE_PATH + "/S2SFormBinding.xml")
    private Resource bindingFile;

    /**
     * 
     * This method is used to get the Form Information based on the given schema
     * 
     * @param namespace {@link String} namespace URL of the form
     * @return {@link FormMappingInfo}containing the namespace information
     */
    @Override
    public FormMappingInfo getFormInfo(String namespace) {

        if (StringUtils.isBlank(namespace)) {
            throw new IllegalArgumentException("namespace is blank");
        }

        return getFormInfo(namespace, null);
    }

    @Override
    public FormMappingInfo getFormInfo(String namespace, String proposalNumber) {

        if (StringUtils.isBlank(namespace)) {
            throw new IllegalArgumentException("proposalNumber is blank");
        }

        if (StringUtils.isBlank(namespace)) {
            throw new IllegalArgumentException("namespace is blank");
        }

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

    protected FormMappingInfo getUserAttachedForm(String namespace, String proposalNumber) {
        final String formName = getUserAttachedFormService().findFormNameByProposalNumberAndNamespace(proposalNumber, namespace);

        if (formName != null) {
            FormMappingInfo mappingInfo = new FormMappingInfo();
            mappingInfo.setFormName(formName);
            mappingInfo.setGeneratorName(UserAttachedFormGenerator.class.getName());
            mappingInfo.setNameSpace(namespace);
            mappingInfo.setSortIndex(999);
            mappingInfo.setStyleSheet(createStylesheetName(namespace));
            mappingInfo.setUserAttachedForm(true);
            return mappingInfo;
        }else return null;
        
    }

    protected String createStylesheetName(String namespace) {
        String[] tokens = namespace.split("/");
        String formname = tokens[tokens.length-1];
        String templateName = BASE_PACKAGE_PATH + "/support/"+formname+".xsl";
        return templateName;
    }

    /**
     * 
     * This method is used to bind FormMappingLoader class
     * 
     * @return {@link Map}
     */
    @Override
    public Map<String, FormMappingInfo> getBindings() {
        if (bindings == null) {
            synchronized (FormMappingServiceImpl.class) {
            	bindings = new Hashtable<String, FormMappingInfo>();
                sortedNameSpaces = new TreeMap<Integer, List<String>>();
                loadBindings(bindingFile);
            }
        }
        return bindings == null ? Collections.<String, FormMappingInfo>emptyMap() : new HashMap<String, FormMappingInfo>(bindings);
    }


    /**
     * 
     * This method is used to load the bindings from S2SFormBinding.xml file
     */
    protected void loadBindings(Resource bindingResource) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document = null;
        DocumentBuilder builder;
        Integer defaultSortIndex = Integer.valueOf(DEFAULT_SORT_INDEX);
        if(!sortedNameSpaces.containsKey(defaultSortIndex))
        	sortedNameSpaces.put(defaultSortIndex, new ArrayList<String>());
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(bindingResource.getInputStream());
        }
        catch (ParserConfigurationException|SAXException|IOException e) {
            LOG.error(ERROR_MESSAGE, e);
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
            formInfo.setGeneratorName(formNode.getElementsByTagName(GENERATOR_NAME).item(0).getTextContent().trim());
            formInfo.setUserAttachedForm(false);
            formInfo.setStyleSheet(formNode.getElementsByTagName(STYLE_SHEET).item(0).getTextContent().trim());

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

    @Override
    public Map<Integer, List<String>> getSortedNameSpaces() {
        return sortedNameSpaces == null ? Collections.<Integer, List<String>>emptyMap() : new HashMap<Integer, List<String>>(sortedNameSpaces);
    }

    public UserAttachedFormService getUserAttachedFormService() {
        return userAttachedFormService;
    }

    public void setUserAttachedFormService(UserAttachedFormService userAttachedFormService) {
        this.userAttachedFormService = userAttachedFormService;
    }

    public Resource getBindingFile() {
        return bindingFile;
    }

    public void setBindingFile(Resource bindingFile) {
        this.bindingFile = bindingFile;
    }
}
