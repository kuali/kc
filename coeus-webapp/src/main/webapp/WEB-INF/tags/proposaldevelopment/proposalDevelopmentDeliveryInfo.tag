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

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="textAreaFieldName" value="document.developmentProposalList[0].mailDescription" />
<c:set var="action" value="proposalDevelopmentProposal" />

<kul:tab tabTitle="Delivery Info" defaultOpen="false" tabErrorKey="document.developmentProposalList[0].mailBy*,document.developmentProposalList[0].mailType*,document.developmentProposalList[0].mailAccountNumber*,document.developmentProposalList[0].mailingAddressId*,document.developmentProposalList[0].mailDescription*,document.developmentProposalList[0].numberOfCopies*">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Delivery Info</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.coeus.common.proposal.framework.mail.MailBy" altText="help"/></span>
        </h3>
        <table cellpadding=0 cellspacing=0 summary="">
             <tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.mailBy}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].mailBy" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.mailBy}" />
				</td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.mailType}"/></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].mailType" readOnly="${readOnly}" attributeEntry="${proposalDevelopmentAttributes.mailType}" />
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
                    <c:if test="${!readOnly}">
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].mailingAddressId" attributeEntry="${proposalDevelopmentAttributes.mailingAddressId}" />
                    <c:if test="${!empty KualiForm.document.developmentProposalList[0].rolodex.lastName}" >
                    <c:out value="${KualiForm.document.developmentProposalList[0].rolodex.lastName}, ${KualiForm.document.developmentProposalList[0].rolodex.firstName}"/>
                    </c:if>
                    </c:if>    
                          
					<c:choose>
					<c:when test="${!empty KualiForm.document.developmentProposalList[0].rolodex.addressLine1 || !empty KualiForm.document.developmentProposalList[0].rolodex.addressLine2 || !empty KualiForm.document.developmentProposalList[0].rolodex.addressLine3 ||!empty KualiForm.document.developmentProposalList[0].rolodex.city ||!empty KualiForm.document.developmentProposalList[0].rolodex.state}">  
						<c:set var="mailingInfo" value="" />
					</c:when>
					<c:otherwise>
						<c:set var="mailingInfo" value="(select)" />
					</c:otherwise>
					</c:choose>
					<c:if test="${empty readOnly or !readOnly}" >  
						${mailingInfo}
			        </c:if>
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
                        <c:if test="${!empty KualiForm.document.developmentProposalList[0].rolodex.postalCode}" >
                        <span id="mailingPostalCode"><c:out value="${KualiForm.document.developmentProposalList[0].rolodex.postalCode}"/></span><br/>
                        </c:if>
	                    <div align="right" style="float: right;"> 
	                       <c:if test="${!readOnly}" >
							  <kul:lookup boClassName="org.kuali.coeus.common.framework.rolodex.Rolodex" fieldConversions="rolodexId:document.developmentProposalList[0].mailingAddressId,firstName:document.developmentProposalList[0].rolodex.firstName,lastName:document.developmentProposalList[0].rolodex.lastName,organization:document.developmentProposalList[0].rolodex.organization,addressLine1:document.developmentProposalList[0].rolodex.addressLine1,addressLine2:document.developmentProposalList[0].rolodex.addressLine2,addressLine3:document.developmentProposalList[0].rolodex.addressLine3,city:document.developmentProposalList[0].rolodex.city,state:document.developmentProposalList[0].rolodex.state" anchor="${currentTabIndex}"/>
	                          <c:if test="${!empty KualiForm.document.developmentProposalList[0].rolodex.organization}" >
	                             <html:image property="methodToCall.clearMailingNameAddress" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-clear1.gif" title="Clear Fields" alt="Clear Fields" styleClass="tinybutton"/>
	                          </c:if>
	                       </c:if>
	                    </div>
                </td>
                
                 <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.mailDescription}"  /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.developmentProposalList[0].mailDescription" attributeEntry="${proposalDevelopmentAttributes.mailDescription}" />
                </td>
            </tr>
        </table>
    </div>
</kul:tab>
