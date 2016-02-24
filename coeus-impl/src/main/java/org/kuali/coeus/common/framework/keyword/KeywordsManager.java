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


import java.util.List;

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
