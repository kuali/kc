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
<%@ attribute name="index" description="Index" required="true" %>
<%@ attribute name="reportClassKey" description="Report Class Key" required="true" %>
<%@ attribute name="reportClassLabel" description="Report Class Key" required="true" %>

<c:set var="awardReportTermAttributes" value="${DataDictionary.AwardReportTerm.attributes}" />

<c:set var="tabErrorKeyString" value=""  />
<c:set var="reportCodeForComparison" value=""  />
<c:forEach var="reportCode" items="${KualiForm.document.reportCodes}" varStatus="reportCodeIndex">	                                            
    <c:forEach var="awardReportTerm" items="${KualiForm.document.award.awardReportTerm}" varStatus="status">        		 
	    <c:if test="${awardReportTerm.reportClassCode == reportClassKey }" >
	        <c:if test="${reportCode.key == awardReportTerm.reportCode}" >
	            <c:if test="${reportCodeForComparison != awardReportTerm.reportCode}" >
	            	<c:choose>
	            	    <c:when test="${empty tabErrorKeyString}" >	            	
	                        <c:set var="tabErrorKeyString" value="document.awardList[0].awardReportTerm[${status.index}].reportCode,document.awardList[0].awardReportTerm[${status.index}].frequencyCode,document.awardList[0].awardReportTerm[${status.index}].frequencyBaseCode,document.awardList[0].awardReportTerm[${status.index}].ospDistributionCode,document.awardList[0].awardReportTerm[${status.index}].dueDate"  />
	                    </c:when>
	                    <c:otherwise >
	                        <c:set var="tabErrorKeyString" value="${tabErrorKeyString},document.awardList[0].awardReportTerm[${status.index}].reportCode,document.awardList[0].awardReportTerm[${status.index}].frequencyCode,document.awardList[0].awardReportTerm[${status.index}].frequencyBaseCode,document.awardList[0].awardReportTerm[${status.index}].ospDistributionCode,document.awardList[0].awardReportTerm[${status.index}].dueDate"  />
	                    </c:otherwise>
	                </c:choose>    	                    
	                <c:set var="reportCodeForComparison" value="${awardReportTerm.reportCode}" />
	            </c:if>
	        </c:if>
	    </c:if>
	</c:forEach>
</c:forEach>                
	                        
<kul:innerTab parentTab="Report Classes" defaultOpen="false" tabTitle="${reportClassLabel}" tabErrorKey="newAwardReportTerm[${index}]*,${tabErrorKeyString}" >
    <table border="0" cellpadding="0" cellspacing="0" summary="">
        <tr>
            <th width="6%"><div align="center">&nbsp;</div></th>          		
          	<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermAttributes.reportCode}" noColon="true" /></div></th>
          	<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermAttributes.frequencyCode}" noColon="true" /></div></th>
          	<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermAttributes.frequencyBaseCode}" noColon="true" /></div></th>
          	<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermAttributes.ospDistributionCode}" noColon="true" /></div></th>
          	<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermAttributes.dueDate}" noColon="true" /></div></th>
          	<kul:htmlAttributeHeaderCell literalLabel="Action" scope="col"/>
        </tr>
        <tr>
		    <th width="6%" class="infoline">
			    <c:out value="Add:" />
			</th>
            <td width="5%" valign="middle" class="infoline">
            <div align="center">
                <kul:htmlControlAttribute property="newAwardReportTerm[${index}].reportCode" attributeEntry="${awardReportTermAttributes.reportCode}" />
            </div>
			</td>
            <td width="5%" valign="middle" class="infoline">
            <div align="center">
                <kul:htmlControlAttribute property="newAwardReportTerm[${index}].frequencyCode" attributeEntry="${awardReportTermAttributes.frequencyCode}" />
            </div>
			</td>
            <td width="5%" valign="middle" class="infoline">
            <div align="center">
                <kul:htmlControlAttribute property="newAwardReportTerm[${index}].frequencyBaseCode" attributeEntry="${awardReportTermAttributes.frequencyBaseCode}" />
            </div>
			</td>
            <td width="5%" valign="middle" class="infoline">
            <div align="center">
                <kul:htmlControlAttribute property="newAwardReportTerm[${index}].ospDistributionCode" attributeEntry="${awardReportTermAttributes.ospDistributionCode}" />
            </div>
			</td>                
            <td width="13%" valign="middle" class="infoline">
            <div align="center">
                <kul:htmlControlAttribute property="newAwardReportTerm[${index}].dueDate" attributeEntry="${awardReportTermAttributes.dueDate}" datePicker="true" />
            </div>
			</td>                
			<td class="infoline">
			<div align="center">
			    <html:image property="methodToCall.addAwardReportTerm.reportClass${reportClassKey}.reportClassIndex${index}.anchor${tabKey}"
			        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
			</div>
            </td>
        </tr>
        
        <c:set var="reportCodeForComparison" value=""  />
        <c:set var="frequencyCodeForComparison" value=""  />
        <c:set var="frequencyBaseCodeForComparison" value=""  />
        <c:set var="distributionCodeForComparison" value=""  />
        <c:set var="dueDateForComparison" value=""  />
        <c:set var="counterReport" value="0" />        
        <c:forEach var="reportCode" items="${KualiForm.document.reportCodes}" varStatus="reportCodeIndex">	                                            
            <c:forEach var="awardReportTerm" items="${KualiForm.document.award.awardReportTerm}" varStatus="status">        		 
	            <c:if test="${awardReportTerm.reportClassCode == reportClassKey }" >
	                <c:if test="${reportCode.key == awardReportTerm.reportCode}" >	                	 
	                    <c:if test="${reportCodeForComparison != awardReportTerm.reportCode
	                                   ||  frequencyCodeForComparison != awardReportTerm.frequencyCode
	                                   ||  frequencyBaseCodeForComparison != awardReportTerm.frequencyBaseCode
	                                   ||  distributionCodeForComparison != awardReportTerm.ospDistributionCode
	                                   ||  dueDateForComparison != awardReportTerm.dueDate}" >	                                   
	                        <c:set var="reportCodeForComparison" value="${awardReportTerm.reportCode}" />
	                        <c:set var="frequencyCodeForComparison" value="${awardReportTerm.frequencyCode}" />
	                        <c:set var="frequencyBaseCodeForComparison" value="${awardReportTerm.frequencyBaseCode}" />
	                        <c:set var="distributionCodeForComparison" value="${awardReportTerm.ospDistributionCode}" />
	                        <c:set var="dueDateForComparison" value="${awardReportTerm.dueDate}" />
	                        <c:set var="counterReport" value="${counterReport + 1}" />	              
	    <tr>
		    <th width="5%" class="infoline" rowspan="2">
			    <c:out value="${counterReport}" />
			</th>			                
	        <td width="5%" valign="middle">        
			<div align="center">
                <kul:htmlControlAttribute property="document.awardList[0].awardReportTerm[${status.index}].reportCode" attributeEntry="${awardReportTermAttributes.reportCode}" />
			</div>
			</td>
	        <td width="5%" valign="middle">
			<div align="center">
                <kul:htmlControlAttribute property="document.awardList[0].awardReportTerm[${status.index}].frequencyCode" attributeEntry="${awardReportTermAttributes.frequencyCode}" />
			</div>
			</td>
	        <td width="5%" valign="middle">
			<div align="center">
                <kul:htmlControlAttribute property="document.awardList[0].awardReportTerm[${status.index}].frequencyBaseCode" attributeEntry="${awardReportTermAttributes.frequencyBaseCode}" />
			</div>
			</td>
	        <td width="13%" valign="middle">
			<div align="center">
                <kul:htmlControlAttribute property="document.awardList[0].awardReportTerm[${status.index}].ospDistributionCode" attributeEntry="${awardReportTermAttributes.ospDistributionCode}" />
			</div>
			</td>	                
	        <td width="10%" valign="middle">
			<div align="center">
                <kul:htmlControlAttribute property="document.awardList[0].awardReportTerm[${status.index}].dueDate" attributeEntry="${awardReportTermAttributes.dueDate}" datePicker="true" />
			</div>
			</td>
			</td>
			<td valign="middle">
			<div align="center">
			    <html:image property="methodToCall.deleteawardReportTerm.line${status.index}.anchor${currentTabIndex}"
			        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
			</div>            
			</td>
	    </tr>
	    <tr>
	        <td colspan="9">
	            <kra-a:awardReportRecipients innerTabParent="${reportClassLabel}" index="${status.index}" 
	                reportClassKey="${reportClassKey}" reportCode="${awardReportTerm.reportCode}" 
	                frequencyCode="${awardReportTerm.frequencyCode}" frequencyBaseCode="${awardReportTerm.frequencyBaseCode}" 
	                ospDistributionCode="${awardReportTerm.ospDistributionCode}" dueDate="${awardReportTerm.dueDate}"/>	            	    
	        </td>
	    </tr>
	                    </c:if>	            
	                </c:if>
	            </c:if>
        	</c:forEach>        	
	    </c:forEach> 
    </table>
</kul:innerTab>	