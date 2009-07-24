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

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="textAreaFieldName" value="document.developmentProposalList[0].mailDescription" />
<c:set var="action" value="proposalDevelopmentProposal" />

<kul:tab tabTitle="Delivery Info" defaultOpen="false" tabErrorKey="document.developmentProposalList[0].mailBy*,document.developmentProposalList[0].mailType*,document.developmentProposalList[0].mailAccountNumber*,document.developmentProposalList[0].mailingAddressId*,document.developmentProposalList[0].mailDescription*,document.developmentProposalList[0].numberOfCopies*">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Delivery Info</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.proposaldevelopment.bo.MailBy" altText="help"/></span>
        </h3>
        <table cellpadding=0 cellspacing=0 summary="">
             <tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.mailBy}" /></div></th>
                <td align="left" valign="middle">
                	<kra:kraControlAttribute property="document.developmentProposalList[0].mailBy" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.mailBy}" />
				</td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.mailType}"/></div></th>
                <td>
                	<kra:kraControlAttribute property="document.developmentProposalList[0].mailType" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.mailType}" />
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.mailAccountNumber}"/></div></th>
                <td>                	
                  <kul:htmlControlAttribute property="document.developmentProposalList[0].mailAccountNumber" attributeEntry="${proposalDevelopmentAttributes.mailAccountNumber}" />
				</td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.numberOfCopies}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].numberOfCopies" attributeEntry="${proposalDevelopmentAttributes.numberOfCopies}" />
                </td>
                </tr>
                <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.mailingAddressId}"/></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].mailingAddressId" attributeEntry="${proposalDevelopmentAttributes.mailingAddressId}" />
                    <c:if test="${!empty KualiForm.document.developmentProposalList[0].rolodex.lastName}" >
                    <c:out value="${KualiForm.document.developmentProposalList[0].rolodex.lastName}, ${KualiForm.document.developmentProposalList[0].rolodex.firstName}"/>
                    </c:if>    
                          
					<c:choose>
					<c:when test="${!empty KualiForm.document.developmentProposalList[0].rolodex.addressLine1 || !empty KualiForm.document.developmentProposalList[0].rolodex.addressLine2 || !empty KualiForm.document.developmentProposalList[0].rolodex.addressLine3 ||!empty KualiForm.document.developmentProposalList[0].rolodex.city ||!empty KualiForm.document.developmentProposalList[0].rolodex.state}">  
						<c:set var="mailingInfo" value="" />
					</c:when>
					<c:otherwise>
						<c:set var="mailingInfo" value="(select)" />
					</c:otherwise>
					</c:choose>
					<c:if test="${empty readOnly or readOnly != true}" >  
						${mailingInfo}
						<kul:lookup boClassName="org.kuali.kra.bo.Rolodex" fieldConversions="rolodexId:document.developmentProposalList[0].mailingAddressId,firstName:document.developmentProposalList[0].rolodex.firstName,lastName:document.developmentProposalList[0].rolodex.lastName,organization:document.developmentProposalList[0].rolodex.organization,addressLine1:document.developmentProposalList[0].rolodex.addressLine1,addressLine2:document.developmentProposalList[0].rolodex.addressLine2,addressLine3:document.developmentProposalList[0].rolodex.addressLine3,city:document.developmentProposalList[0].rolodex.city,state:document.developmentProposalList[0].rolodex.state" anchor="${currentTabIndex}"/><br>
	                    <c:if test="${!empty KualiForm.document.developmentProposalList[0].rolodex.firstName}" >
	                    <span id="mailingFirstName">${KualiForm.document.developmentProposalList[0].rolodex.firstName}</span>&nbsp;
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.developmentProposalList[0].rolodex.middleName}" >
	                    <span id="mailingMiddleName">${KualiForm.document.developmentProposalList[0].rolodex.middleName}</span>&nbsp;
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.developmentProposalList[0].rolodex.lastName}" >
	                    <span id="mailingLastName">${KualiForm.document.developmentProposalList[0].rolodex.lastName}</span><br/>
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.developmentProposalList[0].rolodex.organization}" >
	                    <span id="mailingOrganization">${KualiForm.document.developmentProposalList[0].rolodex.organization}</span><br/>
	                    </c:if>
	                    <c:if test="${!empty KualiForm.document.developmentProposalList[0].rolodex.addressLine1}" >
	                    <span id="mailingAddressLine1"><c:out value="${KualiForm.document.developmentProposalList[0].rolodex.addressLine1}"/></span><br/>
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.developmentProposalList[0].rolodex.addressLine2}" >
	                    <span id="mailingAddressLine2"><c:out value="${KualiForm.document.developmentProposalList[0].rolodex.addressLine2}"/></span><br/>
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.developmentProposalList[0].rolodex.addressLine3}" >
	                    <span id="mailingAddressLine3"><c:out value="${KualiForm.document.developmentProposalList[0].rolodex.addressLine3}"/></span><br/>
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.developmentProposalList[0].rolodex.city}" >
	                    <span id="mailingCity"><c:out value="${KualiForm.document.developmentProposalList[0].rolodex.city}"/></span><br/>
	                    </c:if>                      
	                    <c:if test="${!empty KualiForm.document.developmentProposalList[0].rolodex.state}" >
	                    <span id="mailingState"><c:out value="${KualiForm.document.developmentProposalList[0].rolodex.state}"/></span><br/>
	                    </c:if>
	                    <div align="right"> 
	                         <html:image property="methodToCall.clearMailingNameAddress" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-clear1.gif" title="Clear Fields" alt="Clear Fields" styleClass="tinybutton"/>
	                    </div>
                    </c:if>
                </td>
                
                 <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.mailDescription}"  /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].mailDescription" attributeEntry="${proposalDevelopmentAttributes.mailDescription}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${proposalDevelopmentAttributes.mailDescription.label}" />
                </td>
            </tr>
        </table>
    </div>
</kul:tab>
