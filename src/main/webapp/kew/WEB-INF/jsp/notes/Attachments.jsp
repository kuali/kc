<%--
 Copyright 2007-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl2.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>
	<br>
	<c:forEach var="attachment" items="${attachmentNote.attachments}" varStatus="status">
		<c:out value="${attachment.fileName}"/>
		<c:if test="${allowEdit}">
			<a href='Note.do?methodToCall=deleteAttachment&note.noteId=<c:out value="${attachmentNote.noteId}" />'>delete</a>
		</c:if>
		<a href='attachment?attachmentId=<c:out value="${attachment.attachmentId}"/>'
			<c:if test="${!empty attachmentTarget}">
				target='<c:out value="${attachmentTarget}"/>'
			</c:if>
		>download</a>
		<br>
	</c:forEach>
	<!--  note.noteId -->
	<c:if test="${allowEdit && empty attachmentNote.attachments }">
		Attachment:  <html-el:file styleClass="dataCell" property="file"/>
	</c:if>
