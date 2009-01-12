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
<%@ attribute name="frequencyCode" description="Frequency Code" required="true" %>
<%@ attribute name="frequencyBaseCode" description="Frequency Base Code" required="true" %>
<%@ attribute name="ospDistributionCode" description="OSP Distribution Code" required="true" %>
<%@ attribute name="dueDate" description="Due Date" required="true" %>

<c:set var="awardReportTermAttributes" value="${DataDictionary.AwardReportTerm.attributes}" />

<c:set var="tabErrorKeyStringRecipient" value=""  />
<c:set var="recipientSize" value="0" />
    <c:forEach var="awardReportTermForCount" items="${KualiForm.document.award.awardReportTerm}" varStatus="count">
           <c:if test="${awardReportTermForCount.reportClassCode == reportClassKey}">           									            
               <c:if test="${awardReportTermForCount.reportCode == reportCode
                             && awardReportTermForCount.frequencyCode == frequencyCode
                             && awardReportTermForCount.frequencyBaseCode == frequencyBaseCode
                             && awardReportTermForCount.ospDistributionCode == ospDistributionCode
                             && awardReportTermForCount.dueDate == dueDate}">
                   <c:set var="recipientSize" value="${recipientSize + 1}" />                       
                   <c:choose>
                       <c:when test="${empty tabErrorKeyStringRecipient}" >	   
                        <c:set var="tabErrorKeyStringRecipient" value="document.awardList[0].awardReportTerm[${count.index}].contactTypeCode,document.awardList[0].awardReportTerm[${count.index}].rolodexId"  />
                   </c:when>
                   <c:otherwise>
                       <c:set var="tabErrorKeyStringRecipient" value="${tabErrorKeyStringRecipient},document.awardList[0].awardReportTerm[${count.index}].contactTypeCode,document.awardList[0].awardReportTerm[${count.index}].rolodexId"  />	                       
                   </c:otherwise>
               </c:choose>
                   
               </c:if>
           </c:if>
    </c:forEach>    
<kul:innerTab parentTab="${innerTabParent}" defaultOpen="false" tabTitle="Recipients" useCurrentTabIndexAsKey="true" tabErrorKey="newAwardReportTermRecipient[${index}]*,${tabErrorKeyStringRecipient}" >
	<table cellpadding="0" cellspacing="0" summary="">
	<tr>
		<th width="10%"><div align="center">&nbsp;</div></th>
		<th width="45%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermAttributes.contactTypeCode}" noColon="true" /></div></th>
		<th width="23%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermAttributes.rolodexId}" noColon="true" /></div></th>					          		
		<th width="18%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermAttributes.numberOfCopies}" noColon="true" /></div></th>
       		<kul:htmlAttributeHeaderCell literalLabel="Action" scope="col"/>
    </tr>
    <tr>
        <input type="hidden" name="newAwardReportTermRecipient[${index}].reportClassCode" value="${reportClassKey}">
        <input type="hidden" name="newAwardReportTermRecipient[${index}].reportCode" value="${reportCode}">
        <input type="hidden" name="newAwardReportTermRecipient[${index}].frequencyCode" value="${frequencyCode}">
        <input type="hidden" name="newAwardReportTermRecipient[${index}].frequencyBaseCode" value="${frequencyBaseCode}">
        <input type="hidden" name="newAwardReportTermRecipient[${index}].ospDistributionCode" value="${ospDistributionCode}">
        <input type="hidden" name="newAwardReportTermRecipient[${index}].dueDate" value="${dueDate}">
	    <th width="5%" class="infoline">
		    <c:out value="Add:" />
	    </th>    
        <td width="5%" valign="middle" class="infoline">
        <div align="center">
            <kul:htmlControlAttribute property="newAwardReportTermRecipient[${index}].contactTypeCode" attributeEntry="${awardReportTermAttributes.contactTypeCode}" />
        </div>
        </td>
        <td width="5%" valign="middle" class="infoline">
        <div align="center">
        	<kul:htmlControlAttribute property="newAwardReportTermRecipient[${index}].rolodexId" attributeEntry="${awardReportTermAttributes.rolodexId}" />
        	<kul:lookup boClassName="org.kuali.kra.bo.Rolodex" fieldConversions="rolodexId:newAwardReportTermRecipient[${index}].rolodexId" anchor="${tabKey}" lookupParameters="newAwardReportTermRecipient[${index}].rolodexId:rolodexId" />
        </div>
        </td>					                
        <td width="5%" valign="middle" class="infoline">
        <div align="center">
        	<kul:htmlControlAttribute property="newAwardReportTermRecipient[${index}].numberOfCopies" attributeEntry="${awardReportTermAttributes.numberOfCopies}" />
        </div>
        </td>
        <td class="infoline">
	    <div align="center">
		    <html:image property="methodToCall.addRecipient.reportClass${reportClassKey}.reportCode${reportCode}.recipientIndex${index}.anchor${tabKey}"
                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
        </div>
        </td>
    </tr>               
                       
       <c:set var="counterRecipient" value="0" />					            
       <c:forEach var="awardReportTermAgain" items="${KualiForm.document.award.awardReportTerm}" varStatus="status1">					            
           <c:if test="${awardReportTermAgain.reportClassCode == reportClassKey}">
               <c:if test="${awardReportTermAgain.reportCode == reportCode
                             && awardReportTermAgain.frequencyCode == frequencyCode
                             && awardReportTermAgain.frequencyBaseCode == frequencyBaseCode
                             && awardReportTermAgain.ospDistributionCode == ospDistributionCode
                             && awardReportTermAgain.dueDate == dueDate}">
                       <c:set var="counterRecipient" value="${counterRecipient + 1}" />
    <tr>
        <th width="5%" class="infoline" >
	        <c:out value="${counterRecipient}" />
        </th>
        <td width="5%" valign="middle">
        <div align="center">
            <kul:htmlControlAttribute property="document.awardList[0].awardReportTerm[${status1.index}].contactTypeCode" attributeEntry="${awardReportTermAttributes.contactTypeCode}" />
        </div>
        </td>
        <td width="5%" valign="middle">
        <div align="center">
            <kul:htmlControlAttribute property="document.awardList[0].awardReportTerm[${status1.index}].rolodexId" attributeEntry="${awardReportTermAttributes.rolodexId}" />
        </div>
        </td>					                
        <td width="5%" valign="middle">
        <div align="center">
            <kul:htmlControlAttribute property="document.awardList[0].awardReportTerm[${status1.index}].numberOfCopies" attributeEntry="${awardReportTermAttributes.numberOfCopies}" />
        </div>
        </td>
        <td valign="middle" >
        <div align="center">        	 
       	    <html:image property="methodToCall.deleteRecipient.line${status1.index}.recipientSize${recipientSize}.anchor${currentTabIndex}"
                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
        </div>
        </td>
    </tr>          
               </c:if>
           </c:if>
       </c:forEach>					          	
    </table>	
</kul:innerTab>