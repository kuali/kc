<%--
 Copyright 2005-2010 The Kuali Foundation
 
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
<%@ page import="org.kuali.rice.kns.exception.AuthorizationException"%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp" %>

<c:set var="parameters"
       value="<%=request.getAttribute(\"org.kuali.kra.web.struts.action.AuthorizationExceptionAction\")%>" />

<c:if test="${not empty parameters}">
	<c:set var="message" value="${parameters.message}" />
</c:if>

<kul:page showDocumentInfo="false"
	      headerTitle="Authorization Exception"
	      docTitle="Authorization Exception Report"
	      transactionalDocument="false"
	      htmlFormAction="authorizationExceptionReport"
	      defaultMethodToCall="notify"
	      errorKey="*">

	<html:hidden property="message" write="false" value="${message}" />

    <div class="topblurb">
		<div align="center">
			<table cellpadding="10" cellspacing="0" border="0" class="container2">
				<tr>
					<td>
						<div align="left" valign="top">
							<strong>Error Message</strong>
						</div>
					</td>
					<td align="left">
						<div align="left">
							<font color="red">${message}</font>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td align="left">
						<div>
							<input type="image" name="cancel" value="true" class="tinybutton"
								   src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif"
								   class="globalbuttons" title="close" alt="Close">
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
</kul:page>