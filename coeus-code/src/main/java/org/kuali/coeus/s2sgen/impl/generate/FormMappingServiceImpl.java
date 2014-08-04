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
import org.kuali.coeus.propdev.api.s2s.UserAttachedFormService;
import org.kuali.coeus.s2sgen.api.generate.FormMappingInfo;
import org.kuali.coeus.s2sgen.api.generate.FormMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Component("formMappingService")
public class FormMappingServiceImpl implements FormMappingService {

    private static final String BASE_PACKAGE_PATH = "org/kuali/coeus/s2sgen/impl/generate";

    private Map<String, FormMappingInfo> bindings = new ConcurrentHashMap<>();
    private Map<Integer, Set<String>> sortedNameSpaces = new ConcurrentHashMap<>();

    @Autowired
    @Qualifier("userAttachedFormService")
    private UserAttachedFormService userAttachedFormService;

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

        return bindings.get(namespace);
    }

    @Override
    public FormMappingInfo getFormInfo(String namespace, String proposalNumber) {

        if (StringUtils.isBlank(namespace)) {
            throw new IllegalArgumentException("proposalNumber is blank");
        }

        if (StringUtils.isBlank(namespace)) {
            throw new IllegalArgumentException("namespace is blank");
        }

        FormMappingInfo formMappingInfo = bindings.get(namespace);
        if (formMappingInfo != null) {
            return formMappingInfo;
        } else if (proposalNumber != null) {
            formMappingInfo =  getUserAttachedForm(proposalNumber, namespace);
            registerForm(formMappingInfo);
            return formMappingInfo;
        }

        return null;
    }

    protected FormMappingInfo getUserAttachedForm(String namespace, String proposalNumber) {
        final String formName = getUserAttachedFormService().findFormNameByProposalNumberAndNamespace(proposalNumber, namespace);

        if (formName != null) {
            FormMappingInfo mappingInfo = new FormMappingInfo();
            mappingInfo.setFormName(formName);
            mappingInfo.setGeneratorName("UserAttachedFormGenerator");
            mappingInfo.setNameSpace(namespace);
            mappingInfo.setSortIndex(999);
            mappingInfo.setStyleSheet(createStylesheetName(namespace));
            mappingInfo.setUserAttachedForm(true);
            return mappingInfo;
        } else {
            return null;
        }
        
    }

    protected String createStylesheetName(String namespace) {
        String[] tokens = namespace.split("/");
        String formname = tokens[tokens.length-1];
        return BASE_PACKAGE_PATH + "/support/"+formname+".xsl";
    }

    @Override
    public Map<String, FormMappingInfo> getBindings() {
        return bindings == null ? Collections.<String, FormMappingInfo>emptyMap() : new HashMap<>(bindings);
    }

    @Override
    public Map<Integer, Set<String>> getSortedNameSpaces() {
        return sortedNameSpaces == null ? Collections.<Integer, Set<String>>emptyMap() : new HashMap<>(sortedNameSpaces);
    }

    @Override
    public void registerForm(FormMappingInfo info) {
        bindings.put(info.getNameSpace(), info);

        Set<String> list = sortedNameSpaces.get(info.getSortIndex());
        if (list == null) {
            list = new HashSet<>();
            sortedNameSpaces.put(info.getSortIndex(), list);
        }

        list.add(info.getNameSpace());
    }

    public UserAttachedFormService getUserAttachedFormService() {
        return userAttachedFormService;
    }

    public void setUserAttachedFormService(UserAttachedFormService userAttachedFormService) {
        this.userAttachedFormService = userAttachedFormService;
    }
}
