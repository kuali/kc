<%--
 Copyright 2007 The Kuali Foundation.
 
 Licensed under the Educational Community License, Version 1.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="categories" value="Errors,Warnings,Grant.Gov Errors" />

<kul:tabTop tabTitle="Data Validation" defaultOpen="false"  
            tabErrorKey="document.audit*">
	<div class="tab-container"  align="center">
		<div class="h2-container"> 
			<span class="subhead-left"> <h2>Data Validation</h2> </span>
		</div>
		<table cellpadding=0 cellspacing="0"  summary="">
			<tr>
				<td>
					<div class="floaters">
                    <p>You can activate a Validation check to determine any errors or incomplete information. The following Validations types will be determined:</p>
                    <ul>
                      <li>errors that prevent submission into routing</li>
                      <li>warnings that serve as alerts to  possible data issues but will not prevent submission into routing</li>
                      <li>errors that prevent submission to grants.gov</li>
                    </ul>
						<p align="center">
							<c:choose>
								<c:when test="${KualiForm.auditActivated}"><html:image property="methodToCall.deactivate" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-validateoff.gif" styleClass="tinybutton" alt="turn off validation"/></c:when>
								<c:otherwise><html:image property="methodToCall.activate" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-validateon.gif" styleClass="tinybutton" alt="activate validation"/></c:otherwise>
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
</kul:tabTop>
