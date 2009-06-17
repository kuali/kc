/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.document;

import java.util.List;

import org.kuali.kra.bo.ScienceKeyword;

/**
 * This interface has all the methods to process ScienceKeywords within a BO or Document.
 * Any BO or Document which handles Keywords should implement this interface.
 * @param <T>
 */
public interface KeywordsManager<T> {//KeywordsManager
    /**
     * 
     * This method is to get the list of Keywords from a Document or BO
     * @return
     */
    public List<T> getKeywords();
    /**
     * 
     * This method is add a keyword to the list in a Document or BO
     * @param scienceKeyword
     */
    public void addKeyword(ScienceKeyword scienceKeyword);
    /**
     * 
     * This method is to get the Keyword from the keywords list of a Document or BO
     * @param index
     * @return
     */
    public T getKeyword(int index);
}
