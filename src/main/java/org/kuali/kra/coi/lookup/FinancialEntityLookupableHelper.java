/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.coi.lookup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.QueryFactory;
import org.apache.ojb.broker.query.ReportQueryByCriteria;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.negotiations.bo.NegotiationAssociationType;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.UrlFactory;

public class FinancialEntityLookupableHelper extends KraLookupableHelperServiceImpl{

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 59748796825429286L;

    @SuppressWarnings("unchecked")
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        super.setBackLocationDocFormKey(fieldValues);
  
        List<PersonFinIntDisclosure> financialEntities = (List<PersonFinIntDisclosure>) super.getSearchResultsUnbounded(fieldValues);
        List<PersonFinIntDisclosure> filtered = new ArrayList<PersonFinIntDisclosure>();
        for (PersonFinIntDisclosure financialEntity : financialEntities) {
            if (financialEntity.isCurrentFlag()) {
                filtered.add(financialEntity);
            }
        }
        return filtered;
    }
   
    
    @Override
    protected void addEditHtmlData(List<HtmlData> htmlDataList, BusinessObject businessObject) {     
        htmlDataList.add(getEditLink(businessObject));    
    }
    
    /**
     * This method returns the edit link
     * @param businessObject
     * @return
     */
    protected AnchorHtmlData getEditLink(BusinessObject businessObject) {
        PersonFinIntDisclosure financialEntity = (PersonFinIntDisclosure) businessObject;
        Properties parameters = new Properties();
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("edit");
        parameters.put(KNSConstants.DISPATCH_REQUEST_PARAMETER, "editFinancialEntityFromLookup");
        parameters.put(getKeyFieldName(), financialEntity.getPersonFinIntDisclosureId()+"");
        String href  = UrlFactory.parameterizeUrl("../" + getHtmlAction(), parameters);
        htmlData.setHref(href);
        return htmlData;
    }
    
    @Override
    protected String getDocumentTypeName() {
        return "FinancialEntityDocument";
    }

    @Override
    protected String getHtmlAction() {
        return "financialEntityEditList.do";
    }

    @Override
    protected String getKeyFieldName() {
        return "personFinIntDisclosureId";
    }

   

}
