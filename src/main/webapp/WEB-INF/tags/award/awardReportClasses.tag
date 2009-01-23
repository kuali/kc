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

<jsp:useBean id="paramMap1" class="java.util.HashMap"/>
<jsp:useBean id="paramMap2" class="java.util.HashMap"/>
<jsp:useBean id="paramMap3" class="java.util.HashMap"/>
<c:set target="${paramMap1}" property="reportClassCode" value="${reportClassKey}" />
<c:set target="${paramMap2}" property="reportClassCode" value="${reportClassKey}" />


<c:set var="tabErrorKeyString" value=""  />
<c:set var="tabItemCount" value="0"  />
<c:forEach var="awardReportTerm" items="${KualiForm.document.award.awardReportTerms}" varStatus="status">        		 
    <c:if test="${awardReportTerm.reportClassCode == reportClassKey }" >
    		<c:set var="tabItemCount" value="${tabItemCount+1}"  />
        	<c:choose>
        	    <c:when test="${empty tabErrorKeyString}" >	            	
                    <c:set var="tabErrorKeyString" value="document.awardList[0].awardReportTerms[${status.index}].reportCode,document.awardList[0].awardReportTerms[${status.index}].frequencyCode,document.awardList[0].awardReportTerms[${status.index}].frequencyBaseCode,document.awardList[0].awardReportTerms[${status.index}].ospDistributionCode,document.awardList[0].awardReportTerms[${status.index}].dueDate"  />
                </c:when>
                <c:otherwise >
                     <c:set var="tabErrorKeyString" value="${tabErrorKeyString},document.awardList[0].awardReportTerms[${status.index}].reportCode,document.awardList[0].awardReportTerms[${status.index}].frequencyCode,document.awardList[0].awardReportTerms[${status.index}].frequencyBaseCode,document.awardList[0].awardReportTerms[${status.index}].ospDistributionCode,document.awardList[0].awardReportTerms[${status.index}].dueDate"  />
                 </c:otherwise>
             </c:choose>
    </c:if>
</c:forEach>
	                        
<kul:innerTab parentTab="Report Classes" tabItemCount="${tabItemCount}" defaultOpen="false" tabTitle="${reportClassLabel}" tabErrorKey="newAwardReportTerm[${index}]*,${tabErrorKeyString}" >
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
                <html:select property="newAwardReportTerm[${index}].reportCode" tabindex="0" 
                onchange="javascript: loadFrequencyCode('${reportClassKey}', 'newAwardReportTerm[${index}].reportCode','newAwardReportTerm[${index}].frequencyCode');return false" >                                              
                <c:forEach items="${krafn:getOptionList('org.kuali.kra.award.lookup.keyvalue.ReportCodeValuesFinder', paramMap1)}" var="option">
	                <c:choose>                    	
	                	<c:when test="${KualiForm.newAwardReportTerm[index].reportCode == option.key}">
	                        <option value="${option.key}" selected>${option.label}</option>
	                    </c:when>
	                    <c:otherwise>
	                        <c:out value="${option.label}"/>
	                        <option value="${option.key}">${option.label}</option>
	                    </c:otherwise>
	                </c:choose>         
	            </c:forEach>
	            </html:select>
	            <html:image property="methodToCall.refreshPulldownOptions" styleClass="tinybutton" 
	            	src='${ConfigProperties.kra.externalizable.images.url}arrow_refresh.png'/>
            </div>
			</td>
			<c:set target="${paramMap2}" property="reportCode" value="${KualiForm.newAwardReportTerm[index].reportCode}" />
            <td width="5%" valign="middle" class="infoline">
            <div align="center">                
                <html:select property="newAwardReportTerm[${index}].frequencyCode" tabindex="0" 
                onchange="javascript: loadFrequencyBaseCode('newAwardReportTerm[${index}].frequencyCode','newAwardReportTerm[${index}].frequencyBaseCode');return false" >                
                <c:forEach items="${krafn:getOptionList('org.kuali.kra.award.lookup.keyvalue.FrequencyCodeValuesFinder', paramMap2)}" var="option">
	                <c:choose>                    	
	                	<c:when test="${KualiForm.newAwardReportTerm[index].frequencyCode == option.key}">
	                        <option value="${option.key}" selected>${option.label}</option>
	                    </c:when>
	                    <c:otherwise>
	                        <c:out value="${option.label}"/>
	                        <option value="${option.key}">${option.label}</option>
	                    </c:otherwise>
	                </c:choose>         
	            </c:forEach>
	            </html:select>
	            <html:image property="methodToCall.refreshPulldownOptions" styleClass="tinybutton" 
	            	src='${ConfigProperties.kra.externalizable.images.url}arrow_refresh.png'/>
            </div>
			</td>
            <td width="5%" valign="middle" class="infoline">
            <c:set target="${paramMap3}" property="frequencyCode" value="${KualiForm.newAwardReportTerm[index].frequencyCode}" />
            <div align="center">
                <html:select property="newAwardReportTerm[${index}].frequencyBaseCode" tabindex="0" >                
                <c:forEach items="${krafn:getOptionList('org.kuali.kra.award.lookup.keyvalue.FrequencyBaseCodeValuesFinder', paramMap3)}" var="option">
	                <c:choose>                    	
	                	<c:when test="${KualiForm.newAwardReportTerm[index].frequencyBaseCode == option.key}">
	                        <option value="${option.key}" selected>${option.label}</option>
	                    </c:when>
	                    <c:otherwise>
	                        <c:out value="${option.label}"/>
	                        <option value="${option.key}">${option.label}</option>
	                    </c:otherwise>
	                </c:choose>         
	            </c:forEach>
	            </html:select>
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
        	                                            
        <c:forEach var="awardReportTerm" items="${KualiForm.document.award.awardReportTerms}" varStatus="status">
	        <c:if test="${awardReportTerm.reportClassCode == reportClassKey }" >
	        <c:set var="counterReport" value="${counterReport + 1}" />
	    <tr>
		    <th width="5%" class="infoline" rowspan="2">
			    <c:out value="${counterReport}" />
			</th>			                
	        <td width="5%" valign="middle">	        
			<div align="center">
				<kul:checkErrors keyMatch="document.awardList[0].awardReportTerms[${status.index}].reportCode" auditMatch="${property}"/>				
				<c:if test="${hasErrors==true}" >
    				<c:set var="textStyle" value="background-color:#FFD5D5"/>
  				</c:if>                
                <html:select property="document.awardList[0].awardReportTerms[${status.index}].reportCode" tabindex="0" style="${textStyle}" 
                             onchange="javascript: loadFrequencyCode('${reportClassKey}', 'document.awardList[0].awardReportTerms[${status.index}].reportCode','document.awardList[0].awardReportTerms[${status.index}].frequencyCode');return false">                                             
                <c:forEach items="${krafn:getOptionList('org.kuali.kra.award.lookup.keyvalue.ReportCodeValuesFinder', paramMap1)}" var="option">                	
	                <c:choose>                    	
	                	<c:when test="${KualiForm.document.awardList[0].awardReportTerms[status.index].reportCode == option.key}">
	                        <option value="${option.key}" selected>${option.label}</option>
	                    </c:when>
	                    <c:otherwise>
	                        <c:out value="${option.label}"/>
	                        <option value="${option.key}">${option.label}</option>
	                    </c:otherwise>
	                </c:choose>                    
	            </c:forEach>
	            </html:select>
	            <html:image property="methodToCall.refreshPulldownOptions" styleClass="tinybutton" 
	            	src='${ConfigProperties.kra.externalizable.images.url}arrow_refresh.png'/>
	            <c:set var="textStyle" value=""/>
			</div>
			</td>
			<c:set target="${paramMap2}" property="reportCode" value="${KualiForm.document.awardList[0].awardReportTerms[status.index].reportCode}" />			
	        <td width="5%" valign="middle">
			<div align="center">
				<kul:checkErrors keyMatch="document.awardList[0].awardReportTerms[${status.index}].frequencyCode" auditMatch="${property}"/>				
				<c:if test="${hasErrors==true}" >
    				<c:set var="textStyle" value="background-color:#FFD5D5"/>
  				</c:if> 
                <html:select property="document.awardList[0].awardReportTerms[${status.index}].frequencyCode" tabindex="0" style="${textStyle}" 
                	onchange="javascript: loadFrequencyBaseCode('document.awardList[0].awardReportTerms[${status.index}].frequencyCode','document.awardList[0].awardReportTerms[${status.index}].frequencyBaseCode');return false">                
                <c:forEach items="${krafn:getOptionList('org.kuali.kra.award.lookup.keyvalue.FrequencyCodeValuesFinder', paramMap2)}" var="option">                	
	                <c:choose>                    	
	                	<c:when test="${KualiForm.document.awardList[0].awardReportTerms[status.index].frequencyCode == option.key}">
	                        <option value="${option.key}" selected>${option.label}</option>
	                    </c:when>
	                    <c:otherwise>
	                        <c:out value="${option.label}"/>
	                        <option value="${option.key}">${option.label}</option>
	                    </c:otherwise>
	                </c:choose>                    
	            </c:forEach>
	            </html:select>
	            <html:image property="methodToCall.refreshPulldownOptions" styleClass="tinybutton" 
	            	src='${ConfigProperties.kra.externalizable.images.url}arrow_refresh.png'/>
	            <c:set var="textStyle" value=""/>
			</div>
			</td>
			<c:set target="${paramMap3}" property="frequencyCode" value="${KualiForm.document.awardList[0].awardReportTerms[status.index].frequencyCode}" />
	        <td width="5%" valign="middle">
			<div align="center">
				<kul:checkErrors keyMatch="document.awardList[0].awardReportTerms[${status.index}].frequencyBaseCode" auditMatch="${property}"/>				
				<c:if test="${hasErrors==true}" >
    				<c:set var="textStyle" value="background-color:#FFD5D5"/>
  				</c:if>                 
                <html:select property="document.awardList[0].awardReportTerms[${status.index}].frequencyBaseCode" tabindex="0" style="${textStyle}" >                
                <c:forEach items="${krafn:getOptionList('org.kuali.kra.award.lookup.keyvalue.FrequencyBaseCodeValuesFinder', paramMap3)}" var="option">
	                <c:choose>                    	
	                	<c:when test="${KualiForm.document.awardList[0].awardReportTerms[status.index].frequencyBaseCode == option.key}">
	                        <option value="${option.key}" selected>${option.label}</option>
	                    </c:when>
	                    <c:otherwise>
	                        <c:out value="${option.label}"/>
	                        <option value="${option.key}">${option.label}</option>
	                    </c:otherwise>
	                </c:choose>         
	            </c:forEach>
	            </html:select>
	            <c:set var="textStyle" value=""/>
			</div>
			</td>
	        <td width="13%" valign="middle">
			<div align="center">
                <kul:htmlControlAttribute property="document.awardList[0].awardReportTerms[${status.index}].ospDistributionCode" attributeEntry="${awardReportTermAttributes.ospDistributionCode}" />
			</div>
			</td>	                
	        <td width="10%" valign="middle">
			<div align="center">
                <kul:htmlControlAttribute property="document.awardList[0].awardReportTerms[${status.index}].dueDate" attributeEntry="${awardReportTermAttributes.dueDate}" datePicker="true" />
			</div>
			</td>
			</td>
			<td valign="middle">
			<div align="center">
			    <html:image property="methodToCall.deleteAwardReportTerm.line${status.index}.anchor${currentTabIndex}"
			        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
			</div>            
			</td>
	    </tr>
	    <tr>
	        <td colspan="6">
	            <kra-a:awardReportRecipients innerTabParent="${reportClassLabel}" index="${status.index}" />	    
	        </td>
	    </tr>
	    	</c:if>                   	
	    </c:forEach> 
    </table>
</kul:innerTab>	