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
package org.kuali.coeus.common.impl.keyword;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.keyword.AbstractScienceKeyword;
import org.kuali.coeus.common.framework.keyword.KeywordsManager;
import org.kuali.coeus.common.framework.keyword.KeywordsService;
import org.kuali.coeus.common.framework.keyword.ScienceKeyword;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.model.MultiLookupForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.lookup.LookupResultsService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * This class is the implementation of KeywordsService to handle the requests from keywords panel in general
 */
@SuppressWarnings("unchecked")
@Component("keywordsService")
public class KeywordsServiceImpl implements KeywordsService {
	
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(KeywordsServiceImpl.class);

    @Autowired
    @Qualifier("lookupResultsService")
    private LookupResultsService lookupResultsService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Override
    public void addKeyword(KeywordsManager document, ScienceKeyword scienceKeyword) {
        if (!isDuplicateKeyword(scienceKeyword.getCode(), document.getKeywords())) {
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
     * Method to add keywords into keywords list associated with particular BO/Document
     * @see org.kuali.coeus.common.framework.keyword.KeywordsService#addKeywords(org.kuali.coeus.common.framework.keyword.KeywordsManager, org.kuali.coeus.sys.framework.model.MultiLookupForm)
     */
    @Deprecated
    public void addKeywords(KeywordsManager document, MultiLookupForm multiLookUpForm) {
        try{
            // check to see if we are coming back from a lookup
            if (Constants.MULTIPLE_VALUE.equals(multiLookUpForm.getRefreshCaller())) {
                // Multivalue lookup. Note that the multivalue keyword lookup results are returned persisted to avoid using session.
                // Since URLs have a max length of 2000 chars, field conversions can not be done.
                String lookupResultsSequenceNumber = multiLookUpForm.getLookupResultsSequenceNumber();//implement MultiLookupFormSupport
                if (StringUtils.isNotBlank(lookupResultsSequenceNumber)) {
                    Class lookupResultsBOClass = Class.forName(multiLookUpForm.getLookupResultsBOClassName());
                    Collection<PersistableBusinessObject> rawValues = lookupResultsService
                    		.retrieveSelectedResultBOs(lookupResultsSequenceNumber, lookupResultsBOClass, globalVariableService.getUserSession().getPrincipalId());
                    if (lookupResultsBOClass.isAssignableFrom(ScienceKeyword.class)) {
                        for (Iterator iter = rawValues.iterator(); iter.hasNext();) {
                            ScienceKeyword scienceKeyword = (ScienceKeyword) iter.next();
                            addKeyword(document, scienceKeyword);
                        }
                    }
                }
            }   
        }catch(Exception ex){
            LOG.error(ex.getMessage(), ex);
        }
        
    }

    public LookupResultsService getLookupResultsService() {
        return lookupResultsService;
    }

    public void setLookupResultsService(LookupResultsService lookupResultsService) {
        this.lookupResultsService = lookupResultsService;
    }

    public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }
}
