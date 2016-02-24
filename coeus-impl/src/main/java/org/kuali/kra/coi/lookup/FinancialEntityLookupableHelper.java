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
package org.kuali.kra.coi.lookup;

import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.kra.coi.personfinancialentity.PersonFinIntDisclosure;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class FinancialEntityLookupableHelper extends KraLookupableHelperServiceImpl{


    private static final long serialVersionUID = 59748796825429286L;

    @SuppressWarnings("unchecked")
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        super.setBackLocationDocFormKey(fieldValues);
  
        List<PersonFinIntDisclosure> financialEntities = (List<PersonFinIntDisclosure>) super.getSearchResultsUnbounded(fieldValues);
        List<PersonFinIntDisclosure> filtered = CollectionUtils.createCorrectImplementationForCollection(financialEntities);
        String principalId = GlobalVariables.getUserSession().getPrincipalId();
        filtered.addAll(financialEntities.stream().filter(financialEntity -> financialEntity.isCurrentFlag() && financialEntity.getPersonId().equals(principalId)).collect(Collectors.toList()));
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
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, "editFinancialEntityFromLookup");
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
