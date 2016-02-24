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
package org.kuali.coeus.common.framework.keyword;

import org.kuali.coeus.sys.framework.model.MultiLookupForm;

/**
 * This service is to handle the requests from Keywords panel
 */
public interface KeywordsService<T> {

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
    
    public void addKeywords(KeywordsManager<T> document,MultiLookupForm multiLookUpForm);
    
}
