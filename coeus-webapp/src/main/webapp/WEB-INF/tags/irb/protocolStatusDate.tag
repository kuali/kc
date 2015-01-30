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

<c:set var="protocolAttributes" value="${DataDictionary.Protocol.attributes}" />
<c:set var="readOnly" value="${!(KualiForm.document.protocolList[0].correctionMode && !KualiForm.document.protocolList[0].amendment && !KualiForm.document.protocolList[0].renewal)}" />

<kul:tab tabTitle="Status & Dates" defaultOpen="false" tabErrorKey="" >
    <div class="tab-container" align="center">
		<h3>
		 	<span class="subhead-left">Status &amp; Dates</span>
		 	<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.actions.ProtocolStatus" altText="help"/></span>
		</h3>
    
    	<table id="status-dates-table" cellpadding=0 cellspacing=0 summary="">
    	 	<tr>
    		 	<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.protocolNumber}"/></div></th>
    	       	<td width="20%">
    	       	   <c:choose>
    	       	       <c:when test="${empty KualiForm.document.protocolList[0].protocolNumber}">
                            Generated on Save
                        </c:when>
                        <c:otherwise>
                            <kul:htmlControlAttribute property="document.protocolList[0].protocolNumber" attributeEntry="${protocolAttributes.protocolNumber}" readOnly="true" />
                            <html:hidden property="document.protocolList[0].protocolNumber" />
                        </c:otherwise>
    	       	   </c:choose>
    	       	</td>
    		 	<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.protocolStatusCode}"  /></div></th>
    	        <td width="20%">${KualiForm.document.protocolList[0].protocolStatus.description}&nbsp;</td>
    	 	</tr>
    	    <tr>
    			<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.approvalDate}" /></div></th>
    	        <td width="20%" align="left" valign="middle">
    	           <c:choose>
                       <c:when test="${empty KualiForm.document.protocolList[0].approvalDate}">
                            Generated on Approval
                       </c:when>
                       <c:otherwise>
    	          	        <kul:htmlControlAttribute property="document.protocolList[0].approvalDate" attributeEntry="${protocolAttributes.approvalDate}" readOnly="${readOnly}" />
    	               </c:otherwise>
    	           </c:choose>
    	        </td>
    	        <th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.lastApprovalDate}" /></div></th>
    	        <td width="20%" align="left" valign="middle">
    	           <c:choose>
                       <c:when test="${empty KualiForm.document.protocolList[0].lastApprovalDate}">
                            Generated on Renewal Approval
                       </c:when>
                       <c:otherwise>
    	          	        <kul:htmlControlAttribute property="document.protocolList[0].lastApprovalDate" attributeEntry="${protocolAttributes.lastApprovalDate}" readOnly="${readOnly}" />
    	               </c:otherwise>
    	           </c:choose>
    	        </td>
    	 	</tr>
    	    <tr>
    	       	<th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.initialSubmissionDate}"/></div></th>
    	        <td width="20%">
    	            <c:choose>
    	        	    <c:when test="${empty KualiForm.document.protocolList[0].initialSubmissionDate}">
    	                    Generated on Initial Submission
    	                </c:when>
    	                <c:otherwise>
    	                    <kul:htmlControlAttribute property="document.protocolList[0].initialSubmissionDate" attributeEntry="${protocolAttributes.initialSubmissionDate}" readOnly="${readOnly}" />
    	                </c:otherwise>    
    	            </c:choose> 
    	        </td>
    	        <th width="30%"><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.expirationDate}"/></div></th>
    	        <td width="20%"align="left" valign="middle">
    	           <c:choose>
                       <c:when test="${empty KualiForm.document.protocolList[0].expirationDate}">
                            Generated on Approval
                       </c:when>
                       <c:otherwise>
    	          	        <kul:htmlControlAttribute property="document.protocolList[0].expirationDate" attributeEntry="${protocolAttributes.expirationDate}" readOnly="${readOnly}" />
    	               </c:otherwise>
    	           </c:choose>
    	        </td>
    	  	</tr>
    	</table>
    </div>
</kul:tab>
