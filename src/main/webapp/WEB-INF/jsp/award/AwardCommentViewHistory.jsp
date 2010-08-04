<%--
Copyright 2005-2010 The Kuali Foundation

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
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="awardCommentTypeDescription" value="${KualiForm.awardCommentHistoryByType[0].commentType.description}" />

<kul:documentPage
    showDocumentInfo="true"
    htmlFormAction="awardNotesAndAttachments"
    documentTypeName="AwardDocument"
    renderMultipart="false"
    showTabButtons="false"
    auditCount="0"
    headerTabActive="actions">

<kul:tabTop tabTitle="${awardCommentTypeDescription} History" defaultOpen="true">

   <c:forEach var="awardComment" items="${KualiForm.awardCommentHistoryByType}" varStatus="awardCommentIndex">        
     <kul:innerTab parentTab="Comments" defaultOpen="true" tabTitle="${awardComment.updateTimestampDateString}" tabErrorKey="" >
		<table>
		<tr>
        	<th width="200" align="left" scope="row"><div align="left">Comments:</div></th>
        	<td>
            	 <div align="left">
            	 	${awardComment.comments}
            	  </div>	 
            </td>	     	 
    	</tr>
        </table>
  	</kul:innerTab>	
  </c:forEach>

</kul:tabTop>
<kul:panelFooter />

</kul:documentPage>