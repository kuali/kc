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

<c:set var="awardCreateAwardAttributes" value="${DataDictionary.Award.attributes}" />
<kul:tab tabTitle="Create Financial Award" defaultOpen="${param.command eq 'displayDocSearchView' ? true : false}" tabErrorKey="error.award.createFinancialAward.*" >
	<div class="tab-container" align="left">
    	<h3> 
    		<span class="subhead-left">Create Financial Award</span>
        </h3>        
		              
		<c:choose>
			<c:when test = "${KualiForm.document.award.financialAwardDocumentNumber == null}" >
		              
		               <table cellpadding=0 cellspacing=0 summary="">
        				<tr>
							<th>
							<div align="center"	>
									Actions
							</div>
						</th>
						</tr>
						<tr>
							<td class="infoline">
								<div align="center">
						   			<html:image property="methodToCall.createFinancialAward" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-createAccount.gif" title="create financial award" alt="create financial award" styleClass="tinybutton"/>
								</div>
							</td>
						</tr>
						</table>
		    </c:when>
			<c:otherwise>
				<table cellpadding=0 cellspacing=0 summary="">
        				<tr>
							<th><div align="center">
									<kul:htmlAttributeLabel attributeEntry="${awardCreateAwardAttributes.financialAwardDocumentNumber}" />
								</div>
							</th>
							<th>
							<div align="center"	>
							Financial award creation date
							</div>
							</th>
						</tr>
						<tr>
							<td class="infoline">
								<div align="center">
									<c:out value="${KualiForm.document.award.financialAwardDocumentNumber}" />
								</div>
							</td>
							<td class="infoline">
								<div align="center">
								<fmt:formatDate value="${KualiForm.document.award.financialAwardCreationDate}" pattern="MM/dd/yyyy" />
								</div>
							</td>
						</tr>
				</table>
			</c:otherwise>
        </c:choose>
	</div>
</kul:tab>

