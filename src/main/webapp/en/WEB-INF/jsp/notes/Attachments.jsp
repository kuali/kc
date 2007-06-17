<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

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