<%--
 Copyright 2005-2008 The Kuali Foundation
 
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

<kul:page showDocumentInfo="false" headerTitle="Kuali Help" docTitle=""
	transactionalDocument="false" htmlFormAction="help">
	<table width="100%" border=0 cellspacing=0 cellpadding=0>
		<c:if test="${! empty KualiForm.helpLabel }">
			<tr>
				<td width="10%"></td>
				<td><b><font size="+1">${KualiForm.helpLabel}</font></b></td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
		</c:if>
		<tr>
			<td width="10%"></td>
			<td>${KualiForm.helpDescription}</td>
		</tr>
	</table>
	<br />
	<br />
	<div style="width: 10%; float: left;">&nbsp;</div>
	<div style="width: 90%;">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<c:if test="${! empty KualiForm.helpRequired }">
			<tr>
				<th style="text-align: left; width: 10em;">Required?</th>
				<td>${KualiForm.helpRequired}</td>
			</tr>
		</c:if>
		<c:if test="${! empty KualiForm.validationPatternName }">
			<tr>
				<th style="text-align: left; width: 10em;">Data Type:</th>
				<td>${KualiForm.validationPatternName}</td>
			</tr>
		</c:if>
		<c:if test="${! empty KualiForm.helpMaxLength }">
			<tr>
				<th style="text-align: left; width: 10em;">Maximum Length:</th>
				<td>${KualiForm.helpMaxLength}</td>
			</tr>
		</c:if>
	</table>
	</div>
	<br>
	<br>
	<div align="center"><a href="javascript:window.close();"
		title="close this window"><img src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif" alt="close this window"></a></div>
</kul:page>
