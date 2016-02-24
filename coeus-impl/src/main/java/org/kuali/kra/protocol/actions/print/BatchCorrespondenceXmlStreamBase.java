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
package org.kuali.kra.protocol.actions.print;

import java.util.Map;

import org.apache.xmlbeans.XmlObject;
import org.kuali.coeus.common.framework.compliance.core.ComplianceConstants;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

public abstract class BatchCorrespondenceXmlStreamBase extends CorrespondenceXmlStreamBase {
	private RenewalReminderStreamBase renewalReminderStream;
	private CorrespondenceXmlStreamBase correspondenceXmlStream;
    private ParameterService parameterService;
	
    @Override
    public Map<String, XmlObject> generateXmlStream(KcPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
        String protocolCorrespTypeCode = (String)reportParameters.get(ComplianceConstants.PROTO_CORRESP_TYPE_CODE);
        String renewalReminderCorrespTypes = getRenewalReminderCorrespondenceTypesParamValues();
        if (renewalReminderCorrespTypes.contains(protocolCorrespTypeCode)) {
        	return getRenewalReminderStream().generateXmlStream(printableBusinessObject, reportParameters);
        }else {
        	return getCorrespondenceXmlStream().generateXmlStream(printableBusinessObject, reportParameters);
        }
    }
	
    public abstract String getRenewalReminderCorrespondenceTypesParamValues();
    
	public RenewalReminderStreamBase getRenewalReminderStream() {
		return renewalReminderStream;
	}
	
	public void setRenewalReminderStream(RenewalReminderStreamBase renewalReminderStream) {
		this.renewalReminderStream = renewalReminderStream;
	}
	
	public CorrespondenceXmlStreamBase getCorrespondenceXmlStream() {
		return correspondenceXmlStream;
	}

	public void setCorrespondenceXmlStream(CorrespondenceXmlStreamBase correspondenceXmlStream) {
		this.correspondenceXmlStream = correspondenceXmlStream;
	}

	public ParameterService getParameterService() {
		return parameterService;
	}

	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}

}
