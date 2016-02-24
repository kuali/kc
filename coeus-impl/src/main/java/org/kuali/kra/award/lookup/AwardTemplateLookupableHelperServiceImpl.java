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

import org.kuali.kra.award.home.AwardTemplate;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;

import java.util.List;
import java.util.Properties;

public class AwardTemplateLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {


    private static final long serialVersionUID = 7169266970079382877L;

  protected String getDocumentTypeName() {
      return AwardTemplate.class.getName();
  }
  protected String getHtmlAction() {
      return "maintenanceSponsorTemplate.do";
  }
  protected String getKeyFieldName() {
      return "templateCode";
  }

  protected AnchorHtmlData getPrintLink(AwardTemplate document) {
      AnchorHtmlData htmlData = new AnchorHtmlData();
      htmlData.setDisplayText("print");
      Properties parameters = new Properties();
      parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, "print");
      parameters.put(KRADConstants.BUSINESS_OBJECT_CLASS_ATTRIBUTE, getDocumentTypeName());
      parameters.put(KRADConstants.RETURN_LOCATION_PARAMETER, getReturnLocation());
      parameters.put(getKeyFieldName(), document.getTemplateCode().toString());
      String href  = UrlFactory.parameterizeUrl("../"+getHtmlAction(), parameters);
      
      htmlData.setHref(href);
      return htmlData;

  }
  

  @Override
  public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
      List<HtmlData> htmlDataList = super.getCustomActionUrls(businessObject, pkNames);
      AnchorHtmlData htmlData = getPrintLink((AwardTemplate) businessObject);
      htmlDataList.add(htmlData);
      return htmlDataList;
  }

}
