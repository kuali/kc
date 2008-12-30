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
<%@ attribute name="reportClassKey" description="Report Class Key" required="true" %>
<%@ attribute name="reportCode" description="Report Code" required="true" %>

<c:set var="awardReportTermsAttributes" value="${DataDictionary.AwardReportTerms.attributes}" />

<kul:innerTab parentTab="${innerTabParent}" defaultOpen="false" tabTitle="Recipients" useCurrentTabIndexAsKey="true">
	<table cellpadding="0" cellspacing="0" summary="">
	<tr>
		<th width="10%"><div align="center">&nbsp;</div></th>
		<th width="45%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.contactTypeCode}" noColon="true" /></div></th>
		<th width="23%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.rolodexId}" noColon="true" /></div></th>					          		
		<th width="18%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.numberOfCopies}" noColon="true" /></div></th>
       		<kul:htmlAttributeHeaderCell literalLabel="Action" scope="col"/>
    </tr>
    <tr>
	    <th width="5%" class="infoline">
		    <c:out value="Add:" />
	    </th>    
        <td width="5%" valign="middle" class="infoline">
        <div align="center">
            <kul:htmlControlAttribute property="newAwardReportTermsRecipients[${index}].contactTypeCode" attributeEntry="${awardReportTermsAttributes.contactTypeCode}" />
        </div>
        </td>
        <td width="5%" valign="middle" class="infoline">
        <div align="center">
        	<kul:htmlControlAttribute property="newAwardReportTermsRecipients[${index}].rolodexId" attributeEntry="${awardReportTermsAttributes.rolodexId}" />
        </div>
        </td>					                
        <td width="5%" valign="middle" class="infoline">
        <div align="center">
        	<kul:htmlControlAttribute property="newAwardReportTermsRecipients[${index}].numberOfCopies" attributeEntry="${awardReportTermsAttributes.numberOfCopies}" />
        </div>
        </td>
        <td class="infoline">
	    <div align="center">
		    <html:image property="methodToCall.addRecipients.reportClass${reportClassKey}.reportCode${reportCode}.recipientIndex${index}.anchor${tabKey}"
                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
        </div>
        </td>
    </tr>
       <c:set var="counterRecipients" value="0" />					            
       <c:forEach var="awardReportTermsAgain" items="${KualiForm.document.award.awardReportTerms}" varStatus="status1">					            
           <c:if test="${awardReportTermsAgain.reportClassCode == reportClassKey}">					            
               <c:if test="${awardReportTermsAgain.reportCode == reportCode}">					            
                   <c:if test="${not empty awardReportTermsAgain.contactTypeCode}">
                       <c:set var="counterRecipients" value="${counterRecipients + 1}" />					            					            
    <tr>
        <th width="5%" class="infoline" >
	        <c:out value="${counterRecipients}" />
        </th>
        <td width="5%" valign="middle">
        <div align="center">
            <kul:htmlControlAttribute property="document.award.awardReportTerms[${status1.index}].contactTypeCode" attributeEntry="${awardReportTermsAttributes.contactTypeCode}" />
        </div>
        </td>
        <td width="5%" valign="middle">
        <div align="center">
            <kul:htmlControlAttribute property="document.award.awardReportTerms[${status1.index}].rolodexId" attributeEntry="${awardReportTermsAttributes.rolodexId}" />
        </div>
        </td>					                
        <td width="5%" valign="middle">
        <div align="center">
            <kul:htmlControlAttribute property="document.award.awardReportTerms[${status1.index}].numberOfCopies" attributeEntry="${awardReportTermsAttributes.numberOfCopies}" />
        </div>
        </td>
        <td valign="middle" >
        <div align="center">
       	    <html:image property="methodToCall.deleteAwardReportTerms.line${status1.index}.anchor${currentTabIndex}"
                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
        </div>
        </td>
    </tr>
                   </c:if>
               </c:if>
           </c:if>
       </c:forEach>					          	
    </table>	
</kul:innerTab>