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


import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

/**
 * 
 * This is an abstract class for holding common properties of ScienceKeyword
 * 
 */
public abstract class AbstractScienceKeyword extends KcPersistableBusinessObjectBase {

    private String scienceKeywordCode;

    private String scienceKeywordDescription;

    private ScienceKeyword scienceKeyword;

    private Boolean selectKeyword = false;

    /**
     * Gets the scienceKeywordCode attribute. 
     * @return Returns the scienceKeywordCode.
     */
    public String getScienceKeywordCode() {
        return scienceKeywordCode;
    }

    /**
     * Sets the scienceKeywordCode attribute value.
     * @param scienceKeywordCode
     */
    public void setScienceKeywordCode(String scienceKeywordCode) {
        this.scienceKeywordCode = scienceKeywordCode;
    }

    /**
     * Gets the scienceKeyword attribute. 
     * @return Returns the scienceKeyword.
     */
    public ScienceKeyword getScienceKeyword() {
        return scienceKeyword;
    }

    /**
     * Sets the scienceKeyword attribute value.
     * @param scienceKeyword The scienceKeyword to set.
     */
    public void setScienceKeyword(ScienceKeyword scienceKeyword) {
        this.scienceKeyword = scienceKeyword;
    }

    /**
     * Gets the selectKeyword attribute. 
     * @return Returns the selectKeyword.
     */
    public Boolean getSelectKeyword() {
        return selectKeyword;
    }

    /**
     * Sets the selectKeyword attribute value.
     * @param selectKeyword The selectKeyword to set.
     */
    public void setSelectKeyword(Boolean selectKeyword) {
        this.selectKeyword = selectKeyword;
    }

    /**
     * Gets the scienceKeywordDescription attribute. 
     * @return Returns the scienceKeywordDescription.
     */
    public String getScienceKeywordDescription() {
        return scienceKeywordDescription;
    }

    /**
     * Sets the scienceKeywordDescription attribute value.
     * @param scienceKeywordDescription The scienceKeywordDescription to set.
     */
    public void setScienceKeywordDescription(String scienceKeywordDescription) {
        this.scienceKeywordDescription = scienceKeywordDescription;
    }
}
