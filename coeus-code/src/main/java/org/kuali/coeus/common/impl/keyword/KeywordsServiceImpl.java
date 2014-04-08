/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.impl.keyword;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.keyword.AbstractScienceKeyword;
import org.kuali.coeus.common.framework.keyword.KeywordsManager;
import org.kuali.coeus.common.framework.keyword.KeywordsService;
import org.kuali.coeus.common.framework.keyword.ScienceKeyword;
import org.kuali.coeus.sys.framework.model.MultiLookupForm;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * This class is the implementation of KeywordsService to handle the requests from keywords panel in general
 */
@SuppressWarnings("unchecked")
public class KeywordsServiceImpl implements KeywordsService {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(KeywordsServiceImpl.class);

    @Override
    public void addKeyword(KeywordsManager document, ScienceKeyword scienceKeyword) {
        if (!isDuplicateKeyword(scienceKeyword.getScienceKeywordCode(), document.getKeywords())) {
            document.addKeyword(scienceKeyword);
        }
    }

    @Override
    public void deleteKeyword(KeywordsManager keywordsDocument) {
        List<AbstractScienceKeyword> scienceKeywords = keywordsDocument.getKeywords();
        for (int i = scienceKeywords.size()-1; i >=0 ; i--) {
            AbstractScienceKeyword abstractScienceKeyword = scienceKeywords.get(i);
            if(abstractScienceKeyword.getSelectKeyword()){
                scienceKeywords.remove(abstractScienceKeyword);
            }
        }
    }

    /**
     * 
     * This method to check whether there is any duplicate objects before adding to the list
     * @param newScienceKeywordCode
     * @param keywords
     * @return
     */
    protected boolean isDuplicateKeyword(String newScienceKeywordCode, List<AbstractScienceKeyword> keywords) {
        for (AbstractScienceKeyword abstractScienceKeyword : keywords) {
            if(newScienceKeywordCode.equals(abstractScienceKeyword.getScienceKeywordCode())){
                return true;
            }
        }
        return false;
    }

    /**
     * Method to add keywords into keywards list associated with particular BO/Document
     * @see org.kuali.coeus.common.framework.keyword.KeywordsService#addKeywords(org.kuali.coeus.common.framework.keyword.KeywordsManager, org.kuali.coeus.sys.framework.model.MultiLookupForm)
     */
    public void addKeywords(KeywordsManager document, MultiLookupForm multiLookUpForm) {
        try{
            // check to see if we are coming back from a lookup
            if (Constants.MULTIPLE_VALUE.equals(multiLookUpForm.getRefreshCaller())) {
                // Multivalue lookup. Note that the multivalue keyword lookup results are returned persisted to avoid using session.
                // Since URLs have a max length of 2000 chars, field conversions can not be done.
                String lookupResultsSequenceNumber = multiLookUpForm.getLookupResultsSequenceNumber();//implement MultiLookupFormSupport
                if (StringUtils.isNotBlank(lookupResultsSequenceNumber)) {
                    Class lookupResultsBOClass = Class.forName(multiLookUpForm.getLookupResultsBOClassName());
                    Collection<PersistableBusinessObject> rawValues = KNSServiceLocator.getLookupResultsService()
                    .retrieveSelectedResultBOs(lookupResultsSequenceNumber, lookupResultsBOClass, GlobalVariables.getUserSession().getPrincipalId());
                    if (lookupResultsBOClass.isAssignableFrom(ScienceKeyword.class)) {
                        KeywordsService keywordsService = KcServiceLocator.getService(KeywordsService.class);//move this to separate method and give protected access
                        for (Iterator iter = rawValues.iterator(); iter.hasNext();) {
                            ScienceKeyword scienceKeyword = (ScienceKeyword) iter.next();
                            keywordsService.addKeyword(document, scienceKeyword);
                        }
                    }
                }
            }   
        }catch(Exception ex){
            LOG.error(ex.getMessage(), ex);
        }
        
    }
}
