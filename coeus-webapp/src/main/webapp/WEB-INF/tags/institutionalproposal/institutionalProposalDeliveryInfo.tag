<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
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

<c:set var="institutionalProposalAttributes" value="${DataDictionary.InstitutionalProposal.attributes}" />
<c:set var="institutionalProposalCommentAttributes" value="${DataDictionary.InstitutionalProposalComment.attributes}" />
<c:set var="textAreaFieldName" value="document.institutionalProposal.mailDescription" />
<c:set var="action" value="institutionalProposalHome" />

<kul:tab tabTitle="Delivery Info" defaultOpen="false" tabErrorKey="document.institutionalProposalList[0].rolodexId,document.institutionalProposal.rolodexId">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Delivery Info</span>
      		<span class="subhead-right"><kul:help parameterNamespace="KC-IP" parameterDetailType="Document" parameterName="deliveryInfo1HelpUrl" altText="help"/></span>
          </h3>
        <table cellpadding=0 cellspacing=0 summary="">
             <tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.mailBy}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.institutionalProposal.mailBy" readOnly="${readOnly}" attributeEntry="${institutionalProposalAttributes.mailBy}" />
				</td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.mailType}"/></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.institutionalProposal.mailType" readOnly="${readOnly}" attributeEntry="${institutionalProposalAttributes.mailType}" />
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
                          
					<c:choose>
					<c:when test="${!empty KualiForm.document.institutionalProposal.rolodex.addressLine1 || !empty KualiForm.document.institutionalProposal.rolodex.addressLine2 || !empty KualiForm.document.institutionalProposal.rolodex.addressLine3 ||!empty KualiForm.document.institutionalProposal.rolodex.city ||!empty KualiForm.document.institutionalProposal.rolodex.state}">  
						<c:set var="mailingInfo" value="" />
					</c:when>
					<c:otherwise>
						<c:set var="mailingInfo" value="(select)" />
					</c:otherwise>
					</c:choose>
					<c:if test="${empty readOnly or !readOnly}" >  
						<c:out value="${mailingInfo}"/>
	                </c:if>
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
	                    	<span id="mailingCityStateZipLine">
	                    		<c:out value="${KualiForm.document.institutionalProposal.rolodex.city}"/>
	                    		<c:if test="${!empty KualiForm.document.institutionalProposal.rolodex.state}" >
	                    			&nbsp;<c:out value="${KualiForm.document.institutionalProposal.rolodex.state}"/>
	                    			<c:if test="${!empty KualiForm.document.institutionalProposal.rolodex.postalCode}" >
	                    				&nbsp;<c:out value="${KualiForm.document.institutionalProposal.rolodex.postalCode}"/>
	                    			</c:if>
	                    		</c:if>
	                    	</span>
	                    </c:if>                      
	                    <div align="right" style="float: right;"> 
	                       <c:if test="${!readOnly}" >
	                          <kul:lookup boClassName="org.kuali.coeus.common.framework.rolodex.Rolodex" fieldConversions="rolodexId:document.institutionalProposal.rolodexId" anchor="${currentTabIndex}"/>
	                          <c:if test="${!empty KualiForm.document.institutionalProposal.rolodex.organization}" >
	                             <html:image property="methodToCall.clearMailingNameAddress" src="${ConfigProperties.kr.externalizable.images.url}tinybutton-clear1.gif" title="Clear Fields" alt="Clear Fields" styleClass="tinybutton"/>
	                          </c:if>
	                       </c:if>
	                    </div>
                </td>
                <th><div align="right"></th>
                <td><div align="right"></td>
                <%--
                 <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${institutionalProposalAttributes.mailDescription}"  /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.institutionalProposal.mailDescription" attributeEntry="${institutionalProposalAttributes.mailDescription}" />
                </td>  --%>
            </tr>
        </table>
    </div>
    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left">Delivery Comments</span>
        </h3>
        <table>
            <th width="100" align="right" scope="row"><div align="center">Add:</div></th>
            <td class="infoline">
                <div align="left">
                    <kul:htmlControlAttribute property="document.institutionalProposalList[0].deliveryComment.comments" attributeEntry="${institutionalProposalCommentAttributes.comments}"/>
                </div>
            </td>
        </table>
    </div>
</kul:tab>
