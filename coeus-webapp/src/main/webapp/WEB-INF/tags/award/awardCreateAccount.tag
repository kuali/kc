<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
   - 
   - This program is free software: you can redistribute it and/or modify
   - it under the terms of the GNU Affero General Public License as
   - published by the Free Software Foundation, either version 3 of the
   - License, or (at your option) any later version.
   - 
   - This program is distributed in the hope that it will be useful,
   - but WITHOUT ANY WARRANTY; without even the implied warranty of
   - MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   - GNU Affero General Public License for more details.
   - 
   - You should have received a copy of the GNU Affero General Public License
   - along with this program.  If not, see <http://www.gnu.org/licenses/>.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="awardCreateAccountAttributes" value="${DataDictionary.Award.attributes}" />
<kul:tab tabTitle="Create Account" defaultOpen="${param.command eq 'displayDocSearchView' ? true : false}" tabErrorKey="error.award.createAccount.*" >
	<div class="tab-container" align="left">
    	<h3> 
    		<span class="subhead-left">Create Account</span>
        </h3>        
		              
		<c:choose>
			<c:when test = "${KualiForm.document.award.financialAccountDocumentNumber == null}" >
		              
		               <table cellpadding=0 cellspacing=0 summary="">
        				<tr>
							<th>
								<div align="center">
									<kul:htmlAttributeLabel attributeEntry="${awardCreateAccountAttributes.accountNumber}" />
								</div>
							
							</th>
							<th>
							<div align="center"	>
									Actions
							</div>
						</th>
						</tr>
						<tr>
							<td class="infoline">
							
								<div align="center">				
										<kul:htmlControlAttribute property="document.award.accountNumber"  attributeEntry="${awardCreateAccountAttributes.accountNumber}" readOnly="false"/>								
								</div>
							</td>
							<td class="infoline">
								<div align="center">
						   			<html:image property="methodToCall.createAccount" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-createAccount.gif" title="create award account" alt="create award account" styleClass="tinybutton"/>
								</div>
							</td>
						</tr>
						</table>
		    </c:when>
			<c:otherwise>
				<table cellpadding=0 cellspacing=0 summary="">
        				<tr>
							<th><div align="center">
									<kul:htmlAttributeLabel attributeEntry="${awardCreateAccountAttributes.financialAccountDocumentNumber}" />
								</div>
							</th>
							<th>
							<div align="center"	>
							Account creation date
							</div>
							</th>
						</tr>
						<tr>
							<td class="infoline">
								<div align="center">
									<c:out value="${KualiForm.document.award.financialAccountDocumentNumber}" />
								</div>
							</td>
							<td class="infoline">
								<div align="center">
								<fmt:formatDate value="${KualiForm.document.award.financialAccountCreationDate}" pattern="MM/dd/yyyy" />
								</div>
							</td>
						</tr>
				</table>
			</c:otherwise>
        </c:choose>
	</div>
</kul:tab>

