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
    private String mainClass;
    private String formName;
    private String stylesheet;
    private String pkgname;
    private int sortIndex;
    
    private static final String KEY_NAMESPACE = "nameSpace";
    private static final String KEY_MAIN_CLASS = "mainClass";
    private static final String KEY_FORM_NAME = "formName";
    private static final String KEY_STYLE_SHEET = "stylesheet";
    private static final String KEY_PACKAGE_NAME = "pkgname";


    /** Creates a new instance of FormMappingInfo */
    public FormMappingInfo() {
    }

    /**
     * Getter for property MainClass.
     * 
     * @return Value of property MainClass.
     */
    public String getMainClass() {
        return mainClass;
    }

    /**
     * Setter for property MainClass.
     * 
     * @param MainClass New value of property MainClass.
     */
    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }


    /**
     * Getter for property nameSpace.
     * 
     * @return Value of property nameSpace.
     */
    public String getNameSpace() {
        return nameSpace;
    }

    /**
     * Setter for property nameSpace.
     * 
     * @param nameSpace New value of property nameSpace.
     */
    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    /**
     * Getter for property formName.
     * 
     * @return Value of property formName.
     */
    public String getFormName() {
        return formName;
    }

    /**
     * Setter for property formName.
     * 
     * @param formName New value of property formName.
     */
    public void setFormName(String formName) {
        this.formName = formName;
    }

    /**
     * Getter for property StyleSheet.
     * 
     * @return Value of property StyleSheet.
     */
    public String getStyleSheet() {
        return stylesheet;
    }

    /**
     * Setter for property StyleSheet.
     * 
     * @param StyleSheet New value of property StyleSheet.
     */
    public void setStyleSheet(String stylesheet) {
        this.stylesheet = stylesheet;
    }

    /**
     * Getter for property beanName.
     * 
     * @return Value of property beanName.
     */
    public String getPkgName() {
        return pkgname;
    }

    /**
     * Setter for property beanName.
     * 
     * @param beanName New value of property beanName.
     */

    public void setPkgName(String pkgname) {
        this.pkgname = pkgname;
    }

    public String toString() {
        Map<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put(KEY_NAMESPACE, getNameSpace());
        hashMap.put(KEY_MAIN_CLASS, getMainClass());
        hashMap.put(KEY_FORM_NAME, getFormName());
        hashMap.put(KEY_STYLE_SHEET, getStyleSheet());
        hashMap.put(KEY_PACKAGE_NAME, getPkgName());
        return hashMap.toString();
    }

    /**
     * Gets the stylesheet attribute.
     * 
     * @return Returns the stylesheet.
     */
    public String getStylesheet() {
        return stylesheet;
    }

    /**
     * Sets the stylesheet attribute value.
     * 
     * @param stylesheet The stylesheet to set.
     */
    public void setStylesheet(String stylesheet) {
        this.stylesheet = stylesheet;
    }

    /**
     * Gets the pkgname attribute.
     * 
     * @return Returns the pkgname.
     */
    public String getPkgname() {
        return pkgname;
    }

    /**
     * Sets the pkgname attribute value.
     * 
     * @param pkgname The pkgname to set.
     */
    public void setPkgname(String pkgname) {
        this.pkgname = pkgname;
    }

    /**
     * Gets the sortIndex attribute.
     * 
     * @return Returns the sortIndex.
     */
    public int getSortIndex() {
        return sortIndex;
    }

    /**
     * Sets the sortIndex attribute value.
     * 
     * @param sortIndex The sortIndex to set.
     */
    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

}
