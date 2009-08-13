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
package org.kuali.kra.award.lookup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.dao.AwardPersonDao;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;

public class AwardPersonLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {
    private static final long serialVersionUID = 3716323161734123416L;

    private AwardPersonDao awardPersonDao;
    
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
