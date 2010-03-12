<%--
 Copyright 2007 The Kuali Foundation
 
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
<%@ attribute name="documentTypeName" required="true" description="The name of the document type displayed by the page rendering this tag; used to lookup the document type level help icon rendered on this page." %>
<%@ attribute name="categories" required="true" description="The categories of audit error clusters this tag should render errors for." %>

<div align="right">
	<kul:help documentTypeName="${documentTypeName}" pageName="${RiceConstants.AUDIT_MODE_HEADER_TAB}" altText="page help"/>
</div>
      
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="t3" summary="">
	<tbody id="">
		<tr>
			<td><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="12" height="12" class="tl3" id=""></td>
			<td align="right"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="12" height="12" class="tr3" id=""></td>
		</tr>
	</tbody>
</table>
      
<div id="workarea">
	<div class="tab-container"  align="center">
			<h3>Audit Mode</h3>
		<table cellpadding=0 cellspacing="0"  summary="">
			<tr>
				<td>
					<div class="floaters">
						<p>You can activate an audit check to determine any errors or incomplete information. </p>
						<p align="center">
							<c:choose>
								<c:when test="${KualiForm.auditActivated}"><html:image property="methodToCall.deactivate" src="${ConfigProperties.externalizable.images.url}tinybutton-deacaudit.gif" title="Deactivate audit mode" alt="Deactivate audit mode" styleClass="tinybutton" /></c:when>
								<c:otherwise><html:image property="methodToCall.activate" src="${ConfigProperties.externalizable.images.url}tinybutton-activaudt.gif" title="Activate audit mode" alt="Activate audit mode" styleClass="tinybutton" /></c:otherwise>
							</c:choose>
						</p>
					</div>
				</td>
			</tr>
		</table>
		<c:if test="${KualiForm.auditActivated}">
			<table cellpadding="0" cellspacing="0" summary="">
			<c:forEach items="${fn:split(categories,',')}" var="category">
				<kul:auditSet category="${category}" />
			</c:forEach>			
			</table>
		</c:if>
	</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="b3" summary="">
		<tr>
			<td align="left" class="footer"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="12" height="14" class="bl3" id=""></td>
			<td align="right" class="footer-right"><img src="${ConfigProperties.kr.externalizable.images.url}pixel_clear.gif" alt="" width="12" height="14" class="br3" id=""></td>
		</tr>
	</table>
</div>
<div class="globalbuttons"> </div>
