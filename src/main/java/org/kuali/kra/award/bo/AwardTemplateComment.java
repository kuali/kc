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

import org.kuali.kra.bo.CommentType;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import java.util.LinkedHashMap;

public class AwardTemplateComment extends KraPersistableBusinessObjectBase { 
	
	/**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4617051298910760741L;
    private Integer templateCommentsId; 
//	private Integer templateCode; 
	private String commentTypeCode; 
	private Boolean checklistPrintFlag; 
	private String comment; 
	
	private CommentType commentType; 
	private AwardTemplate template; 
	
	public AwardTemplateComment() { 

	} 
	
	public Integer getTemplateCommentsId() {
		return templateCommentsId;
	}

	public void setTemplateCommentsId(Integer templateCommentsId) {
		this.templateCommentsId = templateCommentsId;
	}

//	public Integer getTemplateCode() {
//		return templateCode;
//	}
//
//	public void setTemplateCode(Integer templateCode) {
//		this.templateCode = templateCode;
//	}

	public String getCommentTypeCode() {
		return commentTypeCode;
	}

	public void setCommentTypeCode(String commentTypeCode) {
		this.commentTypeCode = commentTypeCode;
	}

	public Boolean getChecklistPrintFlag() {
		return checklistPrintFlag;
	}

	public void setChecklistPrintFlag(Boolean checklistPrintFlag) {
		this.checklistPrintFlag = checklistPrintFlag;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comments) {
		this.comment = comments;
	}

	public CommentType getCommentType() {
		return commentType;
	}

	public void setCommentType(CommentType commentType) {
		this.commentType = commentType;
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
		LinkedHashMap hashMap = new LinkedHashMap();
		hashMap.put("templateCommentsId", getTemplateCommentsId());
//		hashMap.put("templateCode", getTemplateCode());
		hashMap.put("commentTypeCode", getCommentTypeCode());
		hashMap.put("checklistPrintFlag", getChecklistPrintFlag());
		hashMap.put("comments", getComment());
		return hashMap;
	}
	
}