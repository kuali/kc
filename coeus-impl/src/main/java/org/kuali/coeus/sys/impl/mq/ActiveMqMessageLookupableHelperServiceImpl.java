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
package org.kuali.coeus.sys.impl.mq;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.lookup.KcKualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Properties;

@Component("activeMqMessageLookupableHelperService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ActiveMqMessageLookupableHelperServiceImpl extends KcKualiLookupableHelperServiceImpl {

    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        final List<HtmlData> htmlDataList = super.getCustomActionUrls(businessObject, pkNames);
        final AnchorHtmlData resendLink = new AnchorHtmlData();
        resendLink.setDisplayText("Resend Message");
        resendLink.setHref(getCustomActionUrlHref(businessObject, "resend", pkNames));
        htmlDataList.add(resendLink);
        return htmlDataList;
    }

    protected String getCustomActionUrlHref(BusinessObject businessObject, String methodToCall, List pkNames) {
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, methodToCall);
        parameters.put(KRADConstants.BUSINESS_OBJECT_CLASS_ATTRIBUTE, businessObject.getClass().getName());
        parameters.putAll(getParametersFromPrimaryKey(businessObject, pkNames));
        if (StringUtils.isNotBlank(getReturnLocation())) {
            parameters.put(KRADConstants.RETURN_LOCATION_PARAMETER, getReturnLocation());
        }
        return UrlFactory.parameterizeUrl("../activeMqMessageMaintenance.do", parameters);
    }
}
