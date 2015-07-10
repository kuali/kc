/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.protocol.correspondence;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class is a work around to access values of a sub list with EL.
 * e.g. the EL "replaceCorrespondenceTemplates[${status.index}].[${status2.index}].templateFile
 * does not work to access List&lt;List&lt;ProtocolCorrespondenceTemplateBase&gt;&gt; replaceCorrespondenceTemplates.
 * To solve this we added this intermediate class and use the following EL statement:
 * "replaceCorrespondenceTemplates[${status.index}].list[${status2.index}].templateFile"
 */
public class ProtocolCorrespondenceTemplateList {

	private List<ProtocolCorrespondenceTemplateBase> list;
	
	public ProtocolCorrespondenceTemplateList() {
		list = new ArrayList<ProtocolCorrespondenceTemplateBase>();
	}

	public List<ProtocolCorrespondenceTemplateBase> getList() {
		return list;
	}

	public void setList(List<ProtocolCorrespondenceTemplateBase> list) {
		this.list = list;
	}
}
