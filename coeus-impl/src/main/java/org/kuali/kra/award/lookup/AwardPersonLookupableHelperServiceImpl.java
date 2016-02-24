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
package org.kuali.kra.award.lookup;

import org.kuali.kra.award.dao.AwardPersonDao;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.krad.bo.BusinessObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AwardPersonLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {
    private static final long serialVersionUID = 3716323161734123416L;

    private transient AwardPersonDao awardPersonDao;
    
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, @SuppressWarnings("unchecked") List pkNames) {
        return new ArrayList<HtmlData>();
    }
    
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        return awardPersonDao.getAwardPersons(fieldValues);
    }
    
    @Override
    protected String getHtmlAction() {
        return "awardPaymentReportsAndTerms.do";
    }
    
    @Override
    protected String getDocumentTypeName() {
        return "AwardDocument";
    }
    
    @Override
    protected String getKeyFieldName() {
        return "awardContactId";
    }

    /**
     * @param awardPersonDao
     */
    public void setAwardPersonDao(AwardPersonDao awardPersonDao) {
        this.awardPersonDao = awardPersonDao;
    }
}
