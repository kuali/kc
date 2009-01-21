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
<%@ attribute name="innerTabParent" description="Inner Tab Parent Name" required="true" %>
<%@ attribute name="index" description="Index" required="true" %>

<c:set var="awardReportTermRecipientAttributes" value="${DataDictionary.AwardReportTermRecipient.attributes}" />

    
<kul:innerTab parentTab="${innerTabParent}" defaultOpen="false" tabTitle="Recipients" useCurrentTabIndexAsKey="true" tabErrorKey="newAwardReportTermRecipient[${index}]*,document.awardList[0].awardReportTerms[${index}].awardReportTermRecipients*" >
	<table border="0" cellpadding="0" cellspacing="0" summary="">
		<tr>			
			<th width="6%"><div align="center">&nbsp;</div></th>
			<th width="45%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermRecipientAttributes.contactTypeCode}" noColon="true" /></div></th>
			<th width="23%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermRecipientAttributes.rolodexId}" noColon="true" /></div></th>					          		
			<th width="18%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermRecipientAttributes.numberOfCopies}" noColon="true" /></div></th>
	       	<kul:htmlAttributeHeaderCell literalLabel="Action" scope="col" />
	    </tr>
	    <tr>
		    <th width="5%" class="infoline">
			    <c:out value="Add:" />
		    </th>    
	        <td width="5%" valign="middle" class="infoline">
	        <div align="center">
	            <kul:htmlControlAttribute property="newAwardReportTermRecipient[${index}].contactTypeCode" attributeEntry="${awardReportTermRecipientAttributes.contactTypeCode}" />
	        </div>
	        </td>
	        <td width="5%" valign="middle" class="infoline">
	        <div align="center">
	        	<kul:htmlControlAttribute property="newAwardReportTermRecipient[${index}].rolodexId" attributeEntry="${awardReportTermRecipientAttributes.rolodexId}" />
	        	<kul:lookup boClassName="org.kuali.kra.bo.Rolodex" fieldConversions="rolodexId:newAwardReportTermRecipient[${index}].rolodexId" anchor="${tabKey}" lookupParameters="newAwardReportTermRecipient[${index}].rolodexId:rolodexId" />
	        </div>
	        </td>					                
	        <td width="5%" valign="middle" class="infoline">
	        <div align="center">
	        	<kul:htmlControlAttribute property="newAwardReportTermRecipient[${index}].numberOfCopies" attributeEntry="${awardReportTermRecipientAttributes.numberOfCopies}" />
	        </div>
	        </td>
	        <td class="infoline">
		    <div align="center">
			    <html:image property="methodToCall.addRecipient.awardReportTerm${index}.anchor${tabKey}"
	                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
	        </div>
	        </td>
	    </tr>               
						            
	    <c:forEach var="awardReportTermRecipient" items="${KualiForm.document.award.awardReportTerms[index].awardReportTermRecipients}" varStatus="status">					            
	    <tr>
	        <th width="5%" class="infoline" >
		        <c:out value="${status.index}" />
	        </th>
	        <td width="5%" valign="middle">
	        <div align="center">
	            <kul:htmlControlAttribute property="document.awardList[0].awardReportTerms[${index}].awardReportTermRecipients[${status.index}].contactTypeCode" attributeEntry="${awardReportTermRecipientAttributes.contactTypeCode}" />
	        </div>
	        </td>
	        <td width="5%" valign="middle">
	        <div align="center">
	            <kul:htmlControlAttribute property="document.awardList[0].awardReportTerms[${index}].awardReportTermRecipients[${status.index}].rolodexId" attributeEntry="${awardReportTermRecipientAttributes.rolodexId}" />
	        </div>
	        </td>					                
	        <td width="5%" valign="middle">
	        <div align="center">
	            <kul:htmlControlAttribute property="document.awardList[0].awardReportTerms[${index}].awardReportTermRecipients[${status.index}].numberOfCopies" attributeEntry="${awardReportTermRecipientAttributes.numberOfCopies}" />
	        </div>
	        </td>
	        <td valign="middle" >
	        <div align="center">        	 
	       	    <html:image property="methodToCall.deleteRecipient.line${status.index}.awardReportTerm${index}.anchor${currentTabIndex}"
	                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
	        </div>
	        </td>
	    </tr>
	    </c:forEach>					          	
    </table>	
</kul:innerTab>