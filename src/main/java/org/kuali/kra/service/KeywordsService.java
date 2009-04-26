/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.service;

import org.kuali.kra.bo.ScienceKeyword;
import org.kuali.kra.document.KeywordsManager;
import org.kuali.kra.web.struts.form.MultiLookupFormBase;

/**
 * This service is to handle the requests from Keywords panel
 */
public interface KeywordsService<T> {
    /**
     * 
     * This method is check whether the keyword panel needs to be enabled or not
     * @param moduleCode
     * @return
     */
    public Boolean hasKeywordsPanelEnabledForModule(String moduleCode);
    /**
     * 
     * This method is to add keyword to the document or BO.
     * @param document or BO
     * @param scienceKeyWord
     */
    public void addKeyword( KeywordsManager<T> document,ScienceKeyword scienceKeyWord);
    
    /**
     * 
     * This method is to delete all selected keywords from the keywords list.
     * @param document or BO
     */
    public void deleteKeyword(KeywordsManager<T> document);
    
    public void addKeywords(KeywordsManager<T> document,MultiLookupFormBase multiLookUpForm);
    
}
