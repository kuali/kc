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
package org.kuali.kra.s2s.formmapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.kuali.kra.s2s.generator.S2SGeneratorNotFoundException;
import org.kuali.kra.s2s.util.S2SConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


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
    private static final String NAMESPACE = "namespace";
    private static final String MAIN_CLASS = "mainClass";
    private static final String STYLE_SHEET = "stylesheet";
    private static final String PKG_NAME = "pkgName";
    private static final String FORM_NAME = "formName";
    private static final String TAG_FORM = "Form";
    private static final String SORT_INDEX = "sortIndex";
    private static final int DEFAULT_SORT_INDEX = 1000;
    private static final Logger LOG = Logger.getLogger(FormMappingLoader.class);

    /**
     * 
     * This method is used to get the Form Information based on the given schema
     * 
     * @param nameSpace {@link String} namespace URL of the form
     * @return {@link formMappingInfo}containing the namespace information
     * @throws S2SGeneratorNotFoundException
     * 
     */
    public FormMappingInfo getFormInfo(String nameSpace) throws S2SGeneratorNotFoundException {
        getBindings();
        FormMappingInfo formMappingInfo = bindings.get(nameSpace);
        if (formMappingInfo != null) {
            return formMappingInfo;
        }
        else {
            throw new S2SGeneratorNotFoundException("Form not found");
        }
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
                loadBindings();
            }
        }
        return bindings;
    }


    /**
     * 
     * This method is used to load the bindings from S2SFormBinding.xml file
     */
    private void loadBindings() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document = null;
        DocumentBuilder builder;
        Integer defaultSortIndex = Integer.valueOf(DEFAULT_SORT_INDEX);
        bindings = new Hashtable<String, FormMappingInfo>();
        sortedNameSpaces = new TreeMap<Integer, List<String>>();
        sortedNameSpaces.put(defaultSortIndex, new ArrayList<String>());

        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(new FormMappingLoader().getClass().getResourceAsStream(BINDING_FILE_NAME));
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
            formInfo.setStyleSheet(formNode.getElementsByTagName(STYLE_SHEET).item(0).getTextContent().trim());
            formInfo.setPkgName(formNode.getElementsByTagName(PKG_NAME).item(0).getTextContent().trim());

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
