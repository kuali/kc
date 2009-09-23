<%--
 Copyright 2005-2007 The Kuali Foundation
 
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
<%@ include file="tldHeader.jsp"%>

<kul:page showDocumentInfo="false"
	headerTitle="${QuestionPromptForm.title}" docTitle=""
	transactionalDocument="false" htmlFormAction="questionPrompt"
	errorKey="*">
	<html:hidden property="formKey" write="false" />
	<html:hidden property="backLocation" write="false" />
	<html:hidden property="caller" write="false" />
	<html:hidden property="questionIndex" write="false" />
	<html:hidden property="formKey" write="false" />
	<html:hidden property="context" write="false" />
	<html:hidden property="questionAnchor" write="false" />
	<html:hidden property="methodToCallPath" write="false" />
	<html:hidden property="docNum" write="false" />
	<br>
	  <div align="center">
	    ${QuestionPromptForm.questionText}
	  </div>
	<br>
	<br>
	<c:if test="${QuestionPromptForm.showReasonField}">
		<div class="topblurb">
		<div align=center>
		<table cellpadding=0 class="container2">
			<tr>
				<td>
				<div align=left><font color="red">*</font>&nbsp;&nbsp;Please enter
				the reason below:</div>
				</td>
			</tr>
			<tr>
				<td>
				<div align=center><html:textarea property="reason" tabindex="0"
					rows="4" cols="60" /></div>
				</td>
			</tr>
		</table>
		</div>
		</div>
		<br>
	</c:if>
	<div class="topblurb">
	<div align=center>
	<table cellpadding=0 class="container2">
		<tr>
			<td>
			<div id="globalbuttons"><c:forEach
				items="${QuestionPromptForm.buttons}" var="button"
				varStatus="status">
				<html:image
					property="methodToCall.processAnswer.button${status.index}" 
					src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_${button}.gif" styleClass="confirm" />
			</c:forEach></div>
			</td>
		</tr>
	</table>
	</div>
	</div>
</kul:page>
