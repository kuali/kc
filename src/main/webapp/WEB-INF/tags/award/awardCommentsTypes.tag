<%--
 Copyright 2006-2008 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/award/awardTldHeader.jsp"%>
<%@ attribute name="index" description="Index" required="true" %>
<%@ attribute name="commentTypeDescription" description="Comment Type Description" required="true" %>
<c:set var="action" value="awardNotesAndAttachments" />

<c:set var="commentAttributes" value="${DataDictionary.AwardComment.attributes}" />
<c:set var="commentMethodName" value="${fn:replace(commentTypeDescription,' ','')}"/>

	<kra:innerTab parentTab="Comments" defaultOpen="false" tabTitle="${commentTypeDescription}" tabErrorKey="" >
		<table>
        	<th width="100" align="right" scope="row"><div align="center">Add:</div></th>
        	<td class="infoline">
            	 <div align="left">
            	  	 <kul:htmlControlAttribute property="${docAward}.award${commentMethodName}.comments" attributeEntry="${commentAttributes.comments}"/>
            	  	 <kra:expandedTextArea textAreaFieldName="${docAward}.awardList[0].award${commentMethodName}.comments" action="${action}" textAreaLabel="${commentAttributes.comments.label}" />
            	 </div>
            </td>
        </table
  	</kra:innerTab>	