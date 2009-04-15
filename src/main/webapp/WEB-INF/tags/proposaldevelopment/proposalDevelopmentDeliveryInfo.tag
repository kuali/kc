<%--
 Copyright 2006-2009 The Kuali Foundation
 
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

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="textAreaFieldName" value="document.mailDescription" />
<c:set var="action" value="proposalDevelopmentProposal" />

<kul:tab tabTitle="Delivery Info" defaultOpen="false" tabErrorKey="document.mailBy*,document.mailType*,document.mailAccountNumber*,document.mailingAddressId*,document.mailDescription*,document.numberOfCopies*">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Delivery Info</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.proposaldevelopment.bo.MailBy" altText="help"/></span>
        </div>
        <table cellpadding=0 cellspacing=0 summary="">
             <tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.mailBy}" /></div></th>
                <td align="left" valign="middle">
                	<kra:kraControlAttribute property="document.mailBy" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.mailBy}" />
				</td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.mailType}"/></div></th>
                <td>
                	<kra:kraControlAttribute property="document.mailType" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.mailType}" />
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.mailAccountNumber}"/></div></th>
                <td>                	
                  <kul:htmlControlAttribute property="document.mailAccountNumber" attributeEntry="${proposalDevelopmentAttributes.mailAccountNumber}" />
				</td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.numberOfCopies}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.numberOfCopies" attributeEntry="${proposalDevelopmentAttributes.numberOfCopies}" />
                </td>
                </tr>
                <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.mailingAddressId}"/></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.mailingAddressId" attributeEntry="${proposalDevelopmentAttributes.mailingAddressId}" />
                    <c:if test="${!empty KualiForm.document.rolodex.lastName}" >
                    <c:out value="${KualiForm.document.rolodex.lastName}, ${KualiForm.document.rolodex.firstName}"/>
                    </c:if>    
                          
					<c:choose>
					<c:when test="${!empty KualiForm.document.rolodex.addressLine1 || !empty KualiForm.document.rolodex.addressLine2 || !empty KualiForm.document.rolodex.addressLine3 ||!empty KualiForm.document.rolodex.city ||!empty KualiForm.document.rolodex.state}">  
						<c:set var="mailingInfo" value="" />
					</c:when>
					<c:otherwise>
						<c:set var="mailingInfo" value="(select)" />
					</c:otherwise>
					</c:choose>
					<c:if test="${empty readOnly or readOnly != true}" >  
						${mailingInfo}
						<kul:lookup boClassName="org.kuali.kra.bo.Rolodex" fieldConversions="rolodexId:document.mailingAddressId,firstName:document.rolodex.firstName,lastName:document.rolodex.lastName,organization:document.rolodex.organization,addressLine1:document.rolodex.addressLine1,addressLine2:document.rolodex.addressLine2,addressLine3:document.rolodex.addressLine3,city:document.rolodex.city,state:document.rolodex.state" anchor="${currentTabIndex}"/><br>
	                    <c:if test="${!empty KualiForm.document.rolodex.firstName}" >
	                    <span id="mailingFirstName">${KualiForm.document.rolodex.firstName}</span>&nbsp;
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.rolodex.middleName}" >
	                    <span id="mailingMiddleName">${KualiForm.document.rolodex.middleName}</span>&nbsp;
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.rolodex.lastName}" >
	                    <span id="mailingLastName">${KualiForm.document.rolodex.lastName}</span><br/>
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.rolodex.organization}" >
	                    <span id="mailingOrganization">${KualiForm.document.rolodex.organization}</span><br/>
	                    </c:if>
	                    <c:if test="${!empty KualiForm.document.rolodex.addressLine1}" >
	                    <span id="mailingAddressLine1"><c:out value="${KualiForm.document.rolodex.addressLine1}"/></span><br/>
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.rolodex.addressLine2}" >
	                    <span id="mailingAddressLine2"><c:out value="${KualiForm.document.rolodex.addressLine2}"/></span><br/>
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.rolodex.addressLine3}" >
	                    <span id="mailingAddressLine3"><c:out value="${KualiForm.document.rolodex.addressLine3}"/></span><br/>
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.rolodex.city}" >
	                    <span id="mailingCity"><c:out value="${KualiForm.document.rolodex.city}"/></span><br/>
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.rolodex.state}" >
	                    <span id="mailingState"><c:out value="${KualiForm.document.rolodex.state}"/></span><br/>
	                    </c:if>
	                    <div align="right"> 
	                         <html:image property="methodToCall.clearMailingNameAddress" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-clear1.gif" title="Clear Fields" alt="Clear Fields" styleClass="tinybutton"/>
	                    </div>
                    </c:if>
                </td>
                
                 <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.mailDescription}"  /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.mailDescription" attributeEntry="${proposalDevelopmentAttributes.mailDescription}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${proposalDevelopmentAttributes.mailDescription.label}" />
                </td>
            </tr>
        </table>
    </div>
</kul:tab>
