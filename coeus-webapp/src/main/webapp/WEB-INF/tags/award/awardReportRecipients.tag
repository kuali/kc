<%--
 Copyright 2005-2014 The Kuali Foundation
 
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
<%@ attribute name="innerTabParent" description="Inner Tab Parent Name" required="true" %>
<%@ attribute name="index" description="Index" required="true" %>

<c:set var="awardReportTermRecipientAttributes" value="${DataDictionary.AwardReportTermRecipient.attributes}" />
    
<kul:innerTab parentTab="${innerTabParent}" tabItemCount="${fn:length(KualiForm.document.award.awardReportTermItems[index].awardReportTermRecipients)}" defaultOpen="false" tabTitle="Recipients" useCurrentTabIndexAsKey="true" tabErrorKey="awardReportsBean.newAwardReportTermRecipient[${index}]*,document.awardList[0].awardReportTermItems[${index}].awardReportTermRecipients*" >
	<table border="0" cellpadding="0" cellspacing="0" summary="">
		<tr>			
			<th width="6%"><div align="center">&nbsp;</div></th>
			<th width="47%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermRecipientAttributes.contactId}" noColon="true" /></div></th>			
			<th width="24%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermRecipientAttributes.rolodexId}" noColon="true" /></div></th>					          		
			<th width="19%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermRecipientAttributes.numberOfCopies}" noColon="true" /></div></th>
	       	<kul:htmlAttributeHeaderCell literalLabel="Action" scope="col" />
	    </tr>
	    
	    <c:if test="${!readOnly}">
	    <tbody class="addline">
	    <tr>
		    <th width="5%" class="infoline">
			    <c:out value="Add:" />
		    </th>
		    <td width="5%" valign="middle" class="infoline">
		    <div align="center">
		    	${KualiForm.valueFinderResultDoNotCache}
	            <kul:htmlControlAttribute property="awardReportsBean.newAwardReportTermRecipient[${index}].contactId" attributeEntry="${awardReportTermRecipientAttributes.contactId}" />
	            ${KualiForm.valueFinderResultCache}
	        </div>    
	        </td>	        
	        <td width="5%" valign="middle" class="infoline">
	        <div align="center">
	        	<kul:htmlControlAttribute property="awardReportsBean.newAwardReportTermRecipient[${index}].rolodexId" attributeEntry="${awardReportTermRecipientAttributes.rolodexId}" />
	        	<c:choose><c:when test="${not empty KualiForm.awardReportsBean.newAwardReportTermRecipients[index].rolodex.fullName}">
	        	  <c:out value="${KualiForm.awardReportsBean.newAwardReportTermRecipients[index].rolodex.fullName}"/>
	        	</c:when><c:otherwise>
	        	  <c:out value="${KualiForm.awardReportsBean.newAwardReportTermRecipients[index].rolodex.organization}" />
	        	</c:otherwise></c:choose>
	        	<kul:lookup boClassName="org.kuali.kra.bo.Rolodex" fieldConversions="rolodexId:awardReportsBean.newAwardReportTermRecipient[${index}].rolodexId" anchor="${tabKey}" lookupParameters="awardReportsBean.newAwardReportTermRecipient[${index}].rolodexId:rolodexId" />
	        	<c:if test="${not empty KualiForm.awardReportsBean.newAwardReportTermRecipients[index].rolodexId}" >
	            	<html:image property="methodToCall.clearRolodex.line1.awardReportTerm${index}.anchor${currentTabIndex}"
	                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-clear1.gif' styleClass="tinybutton"/>
	            </c:if>
	        </div>
	        </td>					                
	        <td width="5%" valign="middle" class="infoline">
	        <div align="center">
	        	<kul:htmlControlAttribute property="awardReportsBean.newAwardReportTermRecipient[${index}].numberOfCopies" attributeEntry="${awardReportTermRecipientAttributes.numberOfCopies}" />
	        </div>
	        </td>
	        <td class="infoline">
		    <div align="center">
			    <html:image property="methodToCall.addRecipient.awardReportTerm${index}.anchor${tabKey}"
	                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton addButton"/>
	        </div>
	        </td>
	    </tr>      
	    </tbody>         
	    </c:if>
	    		            
	    <c:forEach var="awardReportTermRecipient" items="${KualiForm.document.award.awardReportTermItems[index].awardReportTermRecipients}" varStatus="status">					            
	    <tr>
	        <th width="5%" class="infoline" >
		        <c:out value="${status.index+1}" />
	        </th>
	        <td width="5%" valign="middle">
	        <div align="center">	            
	            <c:out value="${KualiForm.document.awardList[0].awardReportTermItems[index].awardReportTermRecipients[status.index].contactType.description}" />	            
	        </div>
	        </td>	        
	        <td width="5%" valign="middle">
	        <div align="center">
	            <input type="hidden" name="document.awardList[0].awardReportTermItems[${index}].awardReportTermRecipients[${status.index}].rolodexId" value="${KualiForm.document.awardList[0].awardReportTermItems[index].awardReportTermRecipients[status.index].rolodexId}"/>
	        	<c:choose><c:when test="${not empty KualiForm.document.awardList[0].awardReportTermItems[index].awardReportTermRecipients[status.index].rolodex.fullName}">
	        	  <c:out value="${KualiForm.document.awardList[0].awardReportTermItems[index].awardReportTermRecipients[status.index].rolodex.fullName}"/>
	        	</c:when><c:otherwise>
	        	  <c:out value="${KualiForm.document.awardList[0].awardReportTermItems[index].awardReportTermRecipients[status.index].rolodex.organization}" />
	        	</c:otherwise></c:choose>	            
	            <kul:directInquiry boClassName="org.kuali.kra.bo.Rolodex" inquiryParameters="document.awardList[0].awardReportTermItems[${index}].awardReportTermRecipients[${status.index}].rolodexId:rolodexId" anchor="${tabKey}" />
	        </div>
	        </td>					                
	        <td width="5%" valign="middle">
	        <div align="center">
	            <kul:htmlControlAttribute property="document.awardList[0].awardReportTermItems[${index}].awardReportTermRecipients[${status.index}].numberOfCopies" attributeEntry="${awardReportTermRecipientAttributes.numberOfCopies}" />
	        </div>
	        </td>
	        <td valign="middle" >
	        <div align="center">        
	           <c:if test="${!readOnly}">	 
	       	    <html:image property="methodToCall.deleteRecipient.line${status.index}.awardReportTerm${index}.anchor${currentTabIndex}"
	                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
				<c:if test="${KualiForm.syncMode}">
					<html:image property="methodToCall.syncRecipient.line${status.index}.awardReportTerm${index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-sync.gif' alt="sync" styleClass="tinybutton" disabled="${readOnly}"/>
				</c:if>	                
	           </c:if>
	           <c:if test="${readOnly}">&nbsp;</c:if>
	        </div>
	        </td>
	    </tr>
	    </c:forEach>					          	
    </table>	
</kul:innerTab>
