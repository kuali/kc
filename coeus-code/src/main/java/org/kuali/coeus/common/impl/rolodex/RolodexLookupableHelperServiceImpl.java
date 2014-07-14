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
package org.kuali.coeus.common.impl.rolodex;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.KRADConstants;

public class RolodexLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl{

    private final Log LOG = LogFactory.getLog(RolodexLookupableHelperServiceImpl.class);

    private static final String IS_SPONSOR_ADDRESS = "isSponsorAddress";
    private static final String SPONSOR_NAME = "sponsor.sponsorName";

	public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
		List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
		if(businessObject instanceof Rolodex) {
			Rolodex rolodex = (Rolodex)businessObject;
			if(rolodex.getSponsorAddressFlag()) {
		        if (allowsMaintenanceNewOrCopyAction()) {
		        	
		            htmlDataList.add(getUrlData(businessObject, KRADConstants.MAINTENANCE_COPY_METHOD_TO_CALL, pkNames));
		        }
			}
			else 
				htmlDataList = super.getCustomActionUrls(businessObject, pkNames);
			
		}
		return htmlDataList;
	}
}

