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
package org.kuali.kra.protocol.correspondence;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This class is a work around to access values of a sub list with EL.
 * e.g. the EL "replaceCorrespondenceTemplates[${status.index}].[${status2.index}].templateFile
 * does not work to access List<List<ProtocolCorrespondenceTemplateBase>> replaceCorrespondenceTemplates.
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
