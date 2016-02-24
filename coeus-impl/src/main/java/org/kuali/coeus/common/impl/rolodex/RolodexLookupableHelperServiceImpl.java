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
package org.kuali.coeus.common.impl.rolodex;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.sys.framework.lookup.KcKualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.KRADConstants;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("rolodexLookupableHelperService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RolodexLookupableHelperServiceImpl extends KcKualiLookupableHelperServiceImpl {

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

