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

<c:set var="awardReportTermsAttributes" value="${DataDictionary.AwardReportTerms.attributes}" />

<kul:innerTab parentTab="Report Classes" defaultOpen="false" tabTitle="${reportClassLabel}" tabErrorKey="newAwardReportTerms[${index}]*" >
    <table border="0" cellpadding="0" cellspacing="0" summary="">
        <tr>
            <th width="6%"><div align="center">&nbsp;</div></th>          		
          	<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.reportCode}" noColon="true" /></div></th>
          	<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.frequencyCode}" noColon="true" /></div></th>
          	<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.frequencyBaseCode}" noColon="true" /></div></th>
          	<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.ospDistributionCode}" noColon="true" /></div></th>
          	<th width="5%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermsAttributes.dueDate}" noColon="true" /></div></th>
          	<kul:htmlAttributeHeaderCell literalLabel="Action" scope="col"/>
        </tr>
        <tr>
		    <th width="6%" class="infoline">
			    <c:out value="Add:" />
			</th>
            <td width="5%" valign="middle" class="infoline">
            <div align="center">
                <kul:htmlControlAttribute property="newAwardReportTerms[${index}].reportCode" attributeEntry="${awardReportTermsAttributes.reportCode}" />
            </div>
			</td>
            <td width="5%" valign="middle" class="infoline">
            <div align="center">
                <kul:htmlControlAttribute property="newAwardReportTerms[${index}].frequencyCode" attributeEntry="${awardReportTermsAttributes.frequencyCode}" />
            </div>
			</td>
            <td width="5%" valign="middle" class="infoline">
            <div align="center">
                <kul:htmlControlAttribute property="newAwardReportTerms[${index}].frequencyBaseCode" attributeEntry="${awardReportTermsAttributes.frequencyBaseCode}" />
            </div>
			</td>
            <td width="5%" valign="middle" class="infoline">
            <div align="center">
                <kul:htmlControlAttribute property="newAwardReportTerms[${index}].ospDistributionCode" attributeEntry="${awardReportTermsAttributes.ospDistributionCode}" />
            </div>
			</td>                
            <td width="13%" valign="middle" class="infoline">
            <div align="center">
                <kul:htmlControlAttribute property="newAwardReportTerms[${index}].dueDate" attributeEntry="${awardReportTermsAttributes.dueDate}" datePicker="true" />
            </div>
			</td>                
			<td class="infoline">
			<div align="center">
			    <html:image property="methodToCall.addAwardReportTerms.reportClass${reportClassKey}.reportClassIndex${index}.anchor${tabKey}"
			        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
			</div>
            </td>
        </tr>
        
        <c:set var="reportCodeForComparison" value=""  />
        <c:set var="counterReport" value="0" />
        <c:forEach var="reportCode" items="${KualiForm.document.reportCodes}" varStatus="reportCodeIndex">	                                            
            <c:forEach var="awardReportTerms" items="${KualiForm.document.award.awardReportTerms}" varStatus="status">        		 
	            <c:if test="${awardReportTerms.reportClassCode == reportClassKey }" >
	                <c:if test="${reportCode.key == awardReportTerms.reportCode}" >
	                    <c:if test="${reportCodeForComparison != awardReportTerms.reportCode}" >
	                        <c:set var="reportCodeForComparison" value="${awardReportTerms.reportCode}" />
	                        <c:set var="counterReport" value="${counterReport + 1}" />	              
	    <tr>
		    <th width="5%" class="infoline" rowspan="2">
			    <c:out value="${counterReport}" />
			</th>			                
	        <td width="5%" valign="middle">
			<div align="center">
                <kul:htmlControlAttribute property="document.award.awardReportTerms[${status.index}].reportCode" attributeEntry="${awardReportTermsAttributes.reportCode}" />
			</div>
			</td>
	        <td width="5%" valign="middle">
			<div align="center">
                <kul:htmlControlAttribute property="document.award.awardReportTerms[${status.index}].frequencyCode" attributeEntry="${awardReportTermsAttributes.frequencyCode}" />
			</div>
			</td>
	        <td width="5%" valign="middle">
			<div align="center">
                <kul:htmlControlAttribute property="document.award.awardReportTerms[${status.index}].frequencyBaseCode" attributeEntry="${awardReportTermsAttributes.frequencyBaseCode}" />
			</div>
			</td>
	        <td width="13%" valign="middle">
			<div align="center">
                <kul:htmlControlAttribute property="document.award.awardReportTerms[${status.index}].ospDistributionCode" attributeEntry="${awardReportTermsAttributes.ospDistributionCode}" />
			</div>
			</td>	                
	        <td width="10%" valign="middle">
			<div align="center">
                <kul:htmlControlAttribute property="document.award.awardReportTerms[${status.index}].dueDate" attributeEntry="${awardReportTermsAttributes.dueDate}" datePicker="true" />
			</div>
			</td>
			</td>
			<td valign="middle">
			<div align="center">
			    <html:image property="methodToCall.deleteAwardReportTerms.line${status.index}.anchor${currentTabIndex}"
			        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
			</div>            
			</td>
	    </tr>
	    <tr>
	        <td colspan="9">
	            <kra-a:awardReportRecipients innerTabParent="${reportClassLabel}" index="${status.index}" reportClassKey="${reportClassKey}" reportCode="${awardReportTerms.reportCode}" />	            	    
	        </td>
	    </tr>
	                    </c:if>	            
	                </c:if>
	            </c:if>
        	</c:forEach>        	
	    </c:forEach> 
    </table>
</kul:innerTab>	