/*
 * Copyright 2006-2008 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.award.bo;

import java.util.LinkedHashMap;

public class AwardTemplateComment extends AwardCommentBase { 
	
	/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4617051298910760741L;
    private Integer templateCommentsId; 
	private AwardTemplate template; 
	
	public AwardTemplateComment() { 

	} 
	
	public Integer getTemplateCommentsId() {
		return templateCommentsId;
	}

	public void setTemplateCommentsId(Integer templateCommentsId) {
		this.templateCommentsId = templateCommentsId;
	}

	public AwardTemplate getTemplate() {
		return template;
	}

	public void setTemplate(AwardTemplate template) {
		this.template = template;
	}

	@SuppressWarnings("unchecked")
    @Override 
	protected LinkedHashMap toStringMapper() {
		LinkedHashMap hashMap = super.toStringMapper();
		hashMap.put("templateCommentsId", getTemplateCommentsId());
		return hashMap;
	}
	
}