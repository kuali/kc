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
<html>
  <head>
    <title>All Notes Report</title>
    <link href="css/screen.css" rel="stylesheet" type="text/css">
    <style type="text/css">
    <!--
      body {
        background-color: #e4e4e4;
      }
    -->
    </style>

    <script language="JavaScript" src="scripts/en-common.js"></script>
    <script language="JavaScript" src="scripts/notes-common.js"></script>
  </head>

  <body>

    <html-el:form action="Note.do" enctype="multipart/form-data">
    <html-el:hidden name="NoteForm" property="methodToCall" />
    <html-el:hidden name="NoteForm" property="showEdit" />
    <html-el:hidden name="NoteForm" property="showAdd" />
    <html-el:hidden name="NoteForm" property="noteIdNumber" />
    <html-el:hidden name="NoteForm" property="docId" />
    <html-el:hidden name="NoteForm" property="sortNotes" />
    <html-el:hidden name="NoteForm" property="sortOrder" />
    <html-el:hidden name="NoteForm" property="note.noteId" />
    <html-el:hidden name="NoteForm" property="note.routeHeaderId" />
    <html-el:hidden name="NoteForm" property="note.noteAuthorWorkflowId" />
    <html-el:hidden name="NoteForm" property="note.noteCreateLongDate" />
    <html-el:hidden name="NoteForm" property="note.lockVerNbr" />
    <html-el:hidden name="NoteForm" property="attachmentTarget" />

          <table width="100%" border=0 cellspacing=0 cellpadding=0>
  		<tr>
    		<td><jsp:include page="../WorkflowMessages.jsp" flush="true" /></td>
  		</tr>
	</table>

          <table width="100%" border=0 cellspacing=0 cellpadding=0>
            <c:if test="${NoteForm.showAdd}">
              <tr>
                <td colspan="4" class="headercell5" scope="col">Create Note </td>
              </tr>
              <tr class="thnormal2">
                <th scope="col">Author</th>
                <th scope="col">Date</th>
                <th scope="col">Note</th>
                <th scope="col">
                  <div align="center">Action</div>
                </th>
              </tr>
              <tr valign="top">
                <td nowrap class="datacell"><c:out value="${NoteForm.currentUserName}" />&nbsp;</td>
                <td class="datacell1"><c:out value="${NoteForm.currentDate}" />&nbsp;</td>
                <td class="datacell1">
                  <c:choose>
                    <c:when test="${NoteForm.showEdit == 'yes'}">
                      <html-el:textarea property="addText" cols="60" rows="3" disabled="true"/><br>
                      <html-el:hidden name="NoteForm" property="addText" />
						<c:set var="allowEdit" value="false" scope="request" />
					  <c:if test="${NoteForm.showAttachments}">
	                      <c:set var="attachmentNote" value="${currentNote}" scope="request"/>
						  <jsp:include page="Attachments.jsp"/>
					  </c:if>
                    </c:when>
                    <c:otherwise>
                      <html-el:textarea property="addText" cols="60" rows="3"/><br>
                      <c:set var="allowEdit" value="true" scope="request" />
                      <c:if test="${!empty NoteForm.attachmentTarget}">
                      	<c:set var="attachmentTarget" value="${NoteForm.attachmentTarget}" scope="request"/>
                      </c:if>
                      <c:if test="${NoteForm.showAttachments}">
	                      <c:set var="attachmentNote" value="${currentNote}" scope="request"/>
						  <jsp:include page="Attachments.jsp"/>
					  </c:if>
                    </c:otherwise>
                  </c:choose>
                </td>
                <td class="datacell1">
                  <c:choose>
                    <c:when test="${NoteForm.showEdit == 'yes'}">
                      <div align="center">
                        <img src="images/tinybutton-save-disable.gif" width="45" height="15" hspace="3" vspace="3">
                      </div>
                    </c:when>
                    <c:otherwise>
                      <div align="center">
  	                    <img src="images/tinybutton-save.gif" width="45" height="15" hspace="3" vspace="3" border="0" onclick="setMethod('save'); document.forms[0].submit();" />
                      </div>
                    </c:otherwise>
                  </c:choose>
                </td>
              </tr>
            </c:if>


            <c:choose>
              <c:when test="${NoteForm.numberOfNotes > 0}">
                <tr>
                  <td colspan="4" class="headercell5" scope="col">View Notes</td>
                </tr>
                <tr class="thnormal2">
                  <th scope="col">Author</th>
                  <th nowrap scope="col">
                    <a href="javascript: document.forms[0].elements['methodToCall'].value = 'sort'; document.forms[0].elements['sortNotes'].value = 'true'; document.forms[0].submit();">Date</a> <img src="images/arrow-expcol-down.gif" width="9" height="5">
                  </th>
                  <th scope="col">Note</th>
                  <th scope="col">
                    <div align="center">Action</div>
                  </th>
                </tr>
                <c:forEach var="currentNote" items="${NoteForm.noteList}" varStatus="status">
                  <tr valign="top">
                    <td nowrap class="datacell1"><c:out value="${currentNote.noteAuthorFullName}" />&nbsp;</td>
                    <td class="datacell1"><c:out value="${currentNote.formattedCreateDate}" /><br><div nowrap><c:out value="${currentNote.formattedCreateTime}" /><div>&nbsp;</td>
                    <td class="datacell1">
                      <c:choose>
                        <c:when test="${currentNote.editingNote && currentNote.authorizedToEdit}">
                          <html-el:textarea property="note.noteText" cols="60" rows="3" />&nbsp;
                          <br>
                          <c:if test="${NoteForm.showAttachments}">
							  <c:set var="allowEdit" value="true" scope="request" />
	                          <c:set var="attachmentNote" value="${currentNote}" scope="request"/>
							  <jsp:include page="Attachments.jsp"/>
						  </c:if>
                        </c:when>
                        <c:otherwise>
                        <c:set var="allowEdit" value="false" scope="request" />
                          <c:out value="${currentNote.noteText}" />&nbsp;
                          <c:if test="${NoteForm.showAttachments}">
	                          <c:set var="attachmentNote" value="${currentNote}" scope="request"/>
							  <jsp:include page="Attachments.jsp"/>
					 	  </c:if>
                        </c:otherwise>
                      </c:choose>
                    </td>
                    <td nowrap class="datacell1">
                      <c:choose>
                        <c:when test="${currentNote.editingNote && currentNote.authorizedToEdit}">
                          <div align="center">
  	                        <img src="images/tinybutton-save.gif" width="40" height="15" hspace="3" vspace="3" border="0" onclick="setNoteId('save', '<c:out value="${currentNote.noteId}"/>'); document.forms[0].submit();" />
  	                        <img src="images/tinybutton-cancel.gif" width="40" height="15" hspace="3" vspace="3" border="0" onclick="setMethod('cancel'); document.forms[0].submit();" />
                          </div>
                        </c:when>
                        <c:otherwise>
                          <c:choose>
                            <c:when test="${NoteForm.showEdit != 'yes' && currentNote.authorizedToEdit}">
                              <div align="center">
                                <img src="images/tinybutton-edit1.gif" width="40" height="15" hspace="3" vspace="3" border="0" onclick="setNoteId('edit', '<c:out value="${currentNote.noteId}"/>'); document.forms[0].submit();" />
                                <img src="images/tinybutton-delete1.gif" width="40" height="15" vspace="3" onclick="setNoteId('delete', '<c:out value="${currentNote.noteId}"/>'); document.forms[0].submit();" />
                              </div>
                            </c:when>
                            <c:otherwise>
                              <div align="center">
                                <img src="images/tinybutton-edit1-disable.gif" width="40" height="15" hspace="3" vspace="3">
                                <img src="images/tinybutton-delete1-disabled.gif" width="40" height="15" vspace="3">
                              </div>
                            </c:otherwise>
                          </c:choose>&nbsp;
                        </c:otherwise>
                      </c:choose>
                    </td>
                  </tr>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <c:if test="${! NoteForm.showAdd}">
                  <tr>
                    <td>
                      <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
	                    <tbody>
                          <tr>
                            <td align="center" valign="middle" class="spacercell">
                              <div align="center"><br><br><br><br><br><br>
                                <p>No notes available </p>
                                <c:if test="${NoteForm.authorizedToAdd}">
                                  <p>&nbsp;</p>
                                  <p>
                                    <img src="images/tinybutton-addnote.gif" width="66" height="15" hspace="3" vspace="3" border="0" onclick="setMethod('add'); document.forms[0].submit();" />
                                  </p>
                                </c:if>
                              </div>
                            </td>
                          </tr>
                        </tbody>
                      </table>
                    </td>
                  </tr>
                </c:if>
              </c:otherwise>
            </c:choose>
          </table>
    </html-el:form>

  </body>

</html>







