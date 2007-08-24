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
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="textAreaFieldName" value="document.mailDescription" />
<c:set var="action" value="proposalDevelopmentProposal" />

<kul:tab tabTitle="Delivery Info" defaultOpen="true" tabErrorKey="document.mailBy*,document.mailType*,document.mailAccountNumber*,document.mailingAddressId*,document.mailDescription*">
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Delivery Info</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="fillMeIn" altText="help"/></span>
        </div>
        
        <table cellpadding=0 cellspacing=0 summary="">
             <tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.mailBy}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.mailBy" attributeEntry="${proposalDevelopmentAttributes.mailBy}" />
				</td>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.mailType}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.mailType" attributeEntry="${proposalDevelopmentAttributes.mailType}" />
                </td>
            </tr>
        	<tr>
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.mailAccountNumber}" /></div></th>
                <td>                	
                  <kul:htmlControlAttribute property="document.mailAccountNumber" attributeEntry="${proposalDevelopmentAttributes.mailAccountNumber}" />
				</td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.numberOfCopies}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.numberOfCopies" attributeEntry="${proposalDevelopmentAttributes.numberOfCopies}" />
                </td>
                </tr>
                <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.mailingAddressId}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.mailingAddressId" attributeEntry="${proposalDevelopmentAttributes.mailingAddressId}" />
                    <c:if test="${!empty KualiForm.document.rolodex.lastName}" >
                    <c:out value="${KualiForm.document.rolodex.lastName}, ${KualiForm.document.rolodex.firstName}"/>
                    </c:if>           
                 <kul:lookup boClassName="org.kuali.kra.bo.Rolodex" 
                    fieldConversions="rolodexId:document.mailingAddressId,firstName:document.rolodex.firstName,lastName:document.rolodex.lastName,addressLine1:document.rolodex.addressLine1,addressLine2:document.rolodex.addressLine2,addressLine3:document.rolodex.addressLine3,city:document.rolodex.city,state:document.rolodex.state" /> <br>

                    <c:if test="${!empty KualiForm.document.rolodex.addressLine1}" >
                      
                    <c:out value="${KualiForm.document.rolodex.addressLine1}"/><br>
                    </c:if>                      
                    <c:if test="${!empty KualiForm.document.rolodex.addressLine2}" >
                    <c:out value="${KualiForm.document.rolodex.addressLine2}"/><br>
                    </c:if>                      
                    <c:if test="${!empty KualiForm.document.rolodex.addressLine3}" >
                    <c:out value="${KualiForm.document.rolodex.addressLine3}"/><br>
                    </c:if>                      
                    <c:if test="${!empty KualiForm.document.rolodex.city}" >
                    <c:out value="${KualiForm.document.rolodex.city}"/><br>
                    </c:if>                      
                    <c:if test="${!empty KualiForm.document.rolodex.state}" >
                    <c:out value="${KualiForm.document.rolodex.state}"/><br>
                    </c:if>
                </td>
            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${proposalDevelopmentAttributes.mailDescription}" /></div></th>
                <td colspan="3" align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.mailDescription" attributeEntry="${proposalDevelopmentAttributes.mailDescription}" />
                    <html:image property="methodToCall.updateTextArea.((#${textAreaFieldName}:${action}:${proposalDevelopmentAttributes.mailDescription.label}#))" src='${ConfigProperties.kra.externalizable.images.url}pencil_add.png' onclick="javascript: textAreaPop(document.getElementById('${textAreaFieldName}').value,'${textAreaFieldName}','proposalDevelopment','${proposalDevelopmentAttributes.mailDescription.label}');return false"/>
                </td>
            </tr>
        </table>
    </div>
</kul:tab>
