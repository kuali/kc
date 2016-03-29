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
package org.kuali.kra.irb.actions.print;

import org.kuali.coeus.common.framework.compliance.core.ComplianceConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.protocol.actions.print.BatchCorrespondenceXmlStreamBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;

public class IrbBatchCorrespondenceXmlStream extends BatchCorrespondenceXmlStreamBase {
	
	  public String getRenewalReminderCorrespondenceTypesParamValues() {
		  return getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_IRB, ParameterConstants.DOCUMENT_COMPONENT, ComplianceConstants.IRB_RENEWAL_REMINDER_CORRESP_TYPES);
	  }

}
