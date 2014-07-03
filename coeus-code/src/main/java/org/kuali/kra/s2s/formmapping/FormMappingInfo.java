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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * This class is used for setting and getting the values which are binded in the S2SFormBinding.xml file.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class FormMappingInfo{
    private String nameSpace;
    private String generatorName;
    private String formName;
    private String stylesheet;
    private int sortIndex;
    private Boolean userAttachedForm=false;
    
    private static final String KEY_NAMESPACE = "nameSpace";
    private static final String KEY_MAIN_CLASS = "generatorName";
    private static final String KEY_FORM_NAME = "formName";
    private static final String KEY_STYLE_SHEET = "stylesheet";

    public String getGeneratorName() {
        return generatorName;
    }

    public void setGeneratorName(String generatorName) {
        this.generatorName = generatorName;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getStyleSheet() {
        return stylesheet;
    }

    public void setStyleSheet(String stylesheet) {
        this.stylesheet = stylesheet;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

    public Boolean getUserAttachedForm() {
        return userAttachedForm;
    }

    public void setUserAttachedForm(Boolean userAttachedForm) {
        this.userAttachedForm = userAttachedForm;
    }

    public String toString() {
        Map<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put(KEY_NAMESPACE, getNameSpace());
        hashMap.put(KEY_MAIN_CLASS, getGeneratorName());
        hashMap.put(KEY_FORM_NAME, getFormName());
        hashMap.put(KEY_STYLE_SHEET, getStyleSheet());
        return hashMap.toString();
    }
}
