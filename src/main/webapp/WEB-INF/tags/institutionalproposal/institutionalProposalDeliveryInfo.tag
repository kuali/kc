<%--
 Copyright 2006-2008 The Kuali Foundation
 
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

<c:set var="institutionalProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />
<c:set var="textAreaFieldName" value="document.institutionalProposal.mailDescription" />
<c:set var="action" value="institutionalProposalHome" />

<kul:tab tabTitle="Delivery Info" defaultOpen="false" tabErrorKey="">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Delivery Info</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.proposaldevelopment.bo.MailBy" altText="help"/></span>
        </h3>
        <table cellpadding=0 cellspacing=0 summary="">
             <tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.mailBy}" /></div></th>
                <td align="left" valign="middle">
                	<kra:kraControlAttribute property="document.institutionalProposal.mailBy" readOnly="${readOnly}" attributeEntry="${institutionalProposalAttributes.mailBy}" />
				</td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.mailType}"/></div></th>
                <td>
                	<kra:kraControlAttribute property="document.institutionalProposal.mailType" readOnly="${readOnly}" attributeEntry="${institutionalProposalAttributes.mailType}" />
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.mailAccountNumber}"/></div></th>
                <td>                	
                  <kul:htmlControlAttribute property="document.institutionalProposal.mailAccountNumber" attributeEntry="${institutionalProposalAttributes.mailAccountNumber}" />
				</td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.numberOfCopies}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.institutionalProposal.numberOfCopies" attributeEntry="${institutionalProposalAttributes.numberOfCopies}" />
                </td>
                </tr>
                <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.rolodexId}"/></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.institutionalProposal.rolodexId" attributeEntry="${institutionalProposalAttributes.rolodexId}" />
                    <c:if test="${!empty KualiForm.document.institutionalProposal.rolodex.lastName}" >
                    <c:out value="${KualiForm.document.institutionalProposal.rolodex.lastName}, ${KualiForm.document.institutionalProposal.rolodex.firstName}"/>
                    </c:if>    
                          
					<c:choose>
					<c:when test="${!empty KualiForm.document.institutionalProposal.rolodex.addressLine1 || !empty KualiForm.document.institutionalProposal.rolodex.addressLine2 || !empty KualiForm.document.institutionalProposal.rolodex.addressLine3 ||!empty KualiForm.document.institutionalProposal.rolodex.city ||!empty KualiForm.document.institutionalProposal.rolodex.state}">  
						<c:set var="mailingInfo" value="" />
					</c:when>
					<c:otherwise>
						<c:set var="mailingInfo" value="(select)" />
					</c:otherwise>
					</c:choose>
					<c:if test="${empty readOnly or readOnly != true}" >  
						${mailingInfo}
						<kul:lookup boClassName="org.kuali.kra.bo.Rolodex" fieldConversions="rolodexId:document.institutionalProposal.mailingAddressId,firstName:document.institutionalProposal.rolodex.firstName,lastName:document.institutionalProposal.rolodex.lastName,organization:document.institutionalProposal.rolodex.organization,addressLine1:document.institutionalProposal.rolodex.addressLine1,addressLine2:document.institutionalProposal.rolodex.addressLine2,addressLine3:document.institutionalProposal.rolodex.addressLine3,city:document.institutionalProposal.rolodex.city,state:document.institutionalProposal.rolodex.state" anchor="${currentTabIndex}"/><br>
	                    <c:if test="${!empty KualiForm.document.institutionalProposal.rolodex.firstName}" >
	                    <span id="mailingFirstName">${KualiForm.document.institutionalProposal.rolodex.firstName}</span>&nbsp;
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.institutionalProposal.rolodex.middleName}" >
	                    <span id="mailingMiddleName">${KualiForm.document.institutionalProposal.rolodex.middleName}</span>&nbsp;
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.institutionalProposal.rolodex.lastName}" >
	                    <span id="mailingLastName">${KualiForm.document.institutionalProposal.rolodex.lastName}</span><br/>
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.institutionalProposal.rolodex.organization}" >
	                    <span id="mailingOrganization">${KualiForm.document.institutionalProposal.rolodex.organization}</span><br/>
	                    </c:if>
	                    <c:if test="${!empty KualiForm.document.institutionalProposal.rolodex.addressLine1}" >
	                    <span id="mailingAddressLine1"><c:out value="${KualiForm.document.institutionalProposal.rolodex.addressLine1}"/></span><br/>
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.institutionalProposal.rolodex.addressLine2}" >
	                    <span id="mailingAddressLine2"><c:out value="${KualiForm.document.institutionalProposal.rolodex.addressLine2}"/></span><br/>
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.institutionalProposal.rolodex.addressLine3}" >
	                    <span id="mailingAddressLine3"><c:out value="${KualiForm.document.institutionalProposal.rolodex.addressLine3}"/></span><br/>
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.institutionalProposal.rolodex.city}" >
	                    <span id="mailingCity"><c:out value="${KualiForm.document.institutionalProposal.rolodex.city}"/></span><br/>
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.institutionalProposal.rolodex.state}" >
	                    <span id="mailingState"><c:out value="${KualiForm.document.institutionalProposal.rolodex.state}"/></span><br/>
	                    </c:if>
	                    <div align="right"> 
	                         <html:image property="methodToCall.clearMailingNameAddress" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-clear1.gif" title="Clear Fields" alt="Clear Fields" styleClass="tinybutton"/>
	                    </div>
                    </c:if>
                </td>
                
                 <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.mailDescription}"  /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.institutionalProposal.mailDescription" attributeEntry="${institutionalProposalAttributes.mailDescription}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${institutionalProposalAttributes.mailDescription.label}" />
                </td>
            </tr>
        </table>
    </div>
</kul:tab>
