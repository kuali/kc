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
package org.kuali.kra.award.lookup;

import java.util.List;
import java.util.Properties;

import org.kuali.kra.award.home.AwardTemplate;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;

public class AwardTemplateLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    
  /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 7169266970079382877L;

  protected String getDocumentTypeName() {
      return "org.kuali.kra.award.home.AwardTemplate";
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
