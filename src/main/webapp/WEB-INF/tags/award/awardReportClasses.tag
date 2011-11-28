<%--
 Copyright 2005-2010 The Kuali Foundation
 
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
<script src="scripts/jquery/jquery.js"></script>
<script>
  //have jquery give up $ so dwr code used within this tag can have it
  $.noConflict();
</script>
<%@ attribute name="index" description="Index" required="true" %>
<%@ attribute name="reportClassKey" description="Report Class Key" required="true" %>
<%@ attribute name="reportClassLabel" description="Report Class Key" required="true" %>
<%@ attribute name="reportCodeLabel" description="This string will be displayed above the report code select box" required="true" %>
<%@ attribute name="defaultOpenForTab" description="Default Open For Tab" required="false" %>
<%@ attribute name="noShowHideButton" description="Do not show show hide button in the following inner tab" required="false" %>

<c:set var="awardReportTermAttributes" value="${DataDictionary.AwardReportTerm.attributes}" />

<jsp:useBean id="paramMap1" class="java.util.HashMap"/>
<jsp:useBean id="paramMap2" class="java.util.HashMap"/>
<jsp:useBean id="paramMap3" class="java.util.HashMap"/>
<c:set target="${paramMap1}" property="reportClassCode" value="${reportClassKey}" />
<c:set target="${paramMap2}" property="reportClassCode" value="${reportClassKey}" />

<%-- The logic in the updateBaseDateDisplay function is duplicated in ReportTrackingServiceImpl.calculateBaseDate.
If you update one, please update the other. --%>
<script>
jQuery().ready(function() {
	jQuery("select[name$='frequencyBaseCode']").each(function(i) {updateBaseDateDisplay(jQuery(this))});
});
function updateBaseDateDisplay(selectBox) {
	var defaultBaseDate = "MM/DD/YYYY";
	var baseDate = defaultBaseDate;
	if (jQuery(selectBox).val() == 1) {
		baseDate = "<fmt:formatDate value='${KualiForm.document.award.awardExecutionDate}' pattern='MM/dd/yyyy' />";
	} else if (jQuery(selectBox).val() == 2) {
		baseDate = "<fmt:formatDate value='${KualiForm.document.award.awardEffectiveDate}' pattern='MM/dd/yyyy' />";
	} else if (jQuery(selectBox).val() == 3) {
		baseDate = "<fmt:formatDate value='${KualiForm.document.award.lastAwardAmountInfo.obligationExpirationDate}' pattern='MM/dd/yyyy' />";
	} else if (jQuery(selectBox).val() == 4) {
		baseDate = "<fmt:formatDate value='${KualiForm.document.award.lastAwardAmountInfo.finalExpirationDate}' pattern='MM/dd/yyyy' />";
	} else if (jQuery(selectBox).val() == 5) {
		baseDate = "<fmt:formatDate value='${KualiForm.document.award.lastAwardAmountInfo.currentFundEffectiveDate}' pattern='MM/dd/yyyy' />";
	} else if (jQuery(selectBox).val() == 6) {
		baseDate = "As Required";
	}
	if (baseDate.length < 1) {
		baseDate = "Unavailable";
	}
	jQuery(selectBox).parent().find("div.baseDateDisplay span").html(baseDate);
}
</script>

<c:set var="tabErrorKeyString" value=""  />
<c:set var="tabItemCount" value="0"  />
<c:forEach var="awardReportTerm" items="${KualiForm.document.award.awardReportTermItems}" varStatus="status">        		 
    <c:if test="${awardReportTerm.reportClassCode == reportClassKey }" >
    		<c:set var="tabItemCount" value="${tabItemCount+1}"  />
        	<c:choose>
        	    <c:when test="${empty tabErrorKeyString}" >	            	
                    <c:set var="tabErrorKeyString" value="document.awardList[0].awardReportTermItems[${status.index}].reportCode,document.awardList[0].awardReportTermItems[${status.index}].frequencyCode,document.awardList[0].awardReportTermItems[${status.index}].frequencyBaseCode,document.awardList[0].awardReportTermItems[${status.index}].ospDistributionCode,document.awardList[0].awardReportTermItems[${status.index}].dueDate"  />
                </c:when>
                <c:otherwise >
                     <c:set var="tabErrorKeyString" value="${tabErrorKeyString},document.awardList[0].awardReportTermItems[${status.index}].reportCode,document.awardList[0].awardReportTermItems[${status.index}].frequencyCode,document.awardList[0].awardReportTermItems[${status.index}].frequencyBaseCode,document.awardList[0].awardReportTermItems[${status.index}].ospDistributionCode,document.awardList[0].awardReportTermItems[${status.index}].dueDate"  />
                 </c:otherwise>
             </c:choose>
    </c:if>
</c:forEach>

<c:if test="${empty defaultOpenForTab}">
	<c:set var="defaultOpenForTab" value="false" />
</c:if>

<c:if test="${empty noShowHideButton}">
	<c:set var="noShowHideButton" value="false" />
</c:if>
	                        
<kul:innerTab parentTab="Report Classes" tabItemCount="${tabItemCount}" defaultOpen="${defaultOpenForTab}" tabTitle="${reportClassLabel}" tabErrorKey="awardReportsBean.newAwardReportTerms[${index}]*,${tabErrorKeyString}" noShowHideButton="${noShowHideButton}" >
    <table border="0" cellpadding="0" cellspacing="0" summary="">
        <tr>
            <th width="6%"><div align="center">&nbsp;</div></th>          	
            <!-- The label for the first row is read from ${reportCodeLabel}, all others are htmlAttributeLabels -->	
          	<th width="18%"><div align="center">${reportCodeLabel}</div></th>
          	<th width="18%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermAttributes.frequencyCode}" noColon="true" /></div></th>
          	<th width="18%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermAttributes.frequencyBaseCode}" noColon="true" /></div></th>
          	<th width="18%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermAttributes.ospDistributionCode}" noColon="true" /></div></th>
          	<th width="18%"><div align="center"><kul:htmlAttributeLabel attributeEntry="${awardReportTermAttributes.dueDate}" noColon="true" /></div></th>
          	<kul:htmlAttributeHeaderCell literalLabel="Action" scope="col"/>
        </tr>
        <c:if test="${!readOnly}">
        <tr>        	
		    <th width="6%" class="infoline">
			    <c:out value="Add:" />
			</th>
            <td nowrap width="5%" valign="middle" class="infoline">
            
            <div align="center">                        
                <html:select property="awardReportsBean.newAwardReportTerms[${index}].reportCode" tabindex="0" 
                onchange="javascript: loadFrequencyCode('${reportClassKey}', 'awardReportsBean.newAwardReportTerms[${index}].reportCode','awardReportsBean.newAwardReportTerms[${index}].frequencyCode');return false" >                                              
                <c:forEach items="${krafn:getOptionList('org.kuali.kra.award.lookup.keyvalue.ReportCodeValuesFinder', paramMap1)}" var="option">
	                <c:choose>                    	
	                	<c:when test="${KualiForm.awardReportsBean.newAwardReportTerms[index].reportCode == option.key}">
	                        <option value="${option.key}" selected>${option.label}</option>
	                    </c:when>
	                    <c:otherwise>
	                        <c:out value="${option.label}"/>
	                        <option value="${option.key}">${option.label}</option>
	                    </c:otherwise>
	                </c:choose>         
	            </c:forEach>
	            </html:select>
                <kul:checkErrors keyMatch="awardReportsBean.newAwardReportTerms[${index}].reportCode" auditMatch="awardReportsBean.newAwardReportTerms[${index}].reportCode"/>
                <c:if test="${hasErrors}">
                    <kul:fieldShowErrorIcon />
                </c:if>
	            <html:image property="methodToCall.refreshPulldownOptions" styleClass="tinybutton" 
	            	src='${ConfigProperties.kra.externalizable.images.url}arrow_refresh.png'/>
            </div>
			</td>
			<c:set target="${paramMap2}" property="reportCode" value="${KualiForm.awardReportsBean.newAwardReportTerms[index].reportCode}" />
            <td nowrap width="5%" valign="middle" class="infoline">
            <div align="center">                
                <html:select property="awardReportsBean.newAwardReportTerms[${index}].frequencyCode" tabindex="0" 
                onchange="javascript: loadFrequencyBaseCode('awardReportsBean.newAwardReportTerms[${index}].frequencyCode','awardReportsBean.newAwardReportTerms[${index}].frequencyBaseCode');return false" >                
                <c:forEach items="${krafn:getOptionList('org.kuali.kra.award.lookup.keyvalue.FrequencyCodeValuesFinder', paramMap2)}" var="option">
	                <c:choose>                    	
	                	<c:when test="${KualiForm.awardReportsBean.newAwardReportTerms[index].frequencyCode == option.key}">
	                        <option value="${option.key}" selected>${option.label}</option>
	                    </c:when>
	                    <c:otherwise>
	                        <c:out value="${option.label}"/>
	                        <option value="${option.key}">${option.label}</option>
	                    </c:otherwise>
	                </c:choose>         
	            </c:forEach>
	            </html:select>
                <kul:checkErrors keyMatch="awardReportsBean.newAwardReportTerms[${index}].frequencyCode" auditMatch="awardReportsBean.newAwardReportTerms[${index}].frequencyCode"/>
                <c:if test="${hasErrors}">
                    <kul:fieldShowErrorIcon />
                </c:if>
	            <html:image property="methodToCall.refreshPulldownOptions" styleClass="tinybutton" 
	            	src='${ConfigProperties.kra.externalizable.images.url}arrow_refresh.png'/>	            	
            </div>
			</td>
            <td nowrap width="5%" valign="middle" class="infoline">
            <c:set target="${paramMap3}" property="frequencyCode" value="${KualiForm.awardReportsBean.newAwardReportTerms[index].frequencyCode}" />
            <div align="center">
                <html:select property="awardReportsBean.newAwardReportTerms[${index}].frequencyBaseCode" tabindex="0" onchange="updateBaseDateDisplay(this);">                
                <c:forEach items="${krafn:getOptionList('org.kuali.kra.award.lookup.keyvalue.FrequencyBaseCodeValuesFinder', paramMap3)}" var="option">
	                <c:choose>                    	
	                	<c:when test="${KualiForm.awardReportsBean.newAwardReportTerms[index].frequencyBaseCode == option.key}">
	                        <option value="${option.key}" selected>${option.label}</option>
	                    </c:when>
	                    <c:otherwise>
	                        <c:out value="${option.label}"/>
	                        <option value="${option.key}">${option.label}</option>
	                    </c:otherwise>
	                </c:choose>         
	            </c:forEach>
	            </html:select>
                <kul:checkErrors keyMatch="awardReportsBean.newAwardReportTerms[${index}].frequencyBaseCode" auditMatch="awardReportsBean.newAwardReportTerms[${index}].frequencyBaseCode"/>
                <c:if test="${hasErrors}">
                    <kul:fieldShowErrorIcon />
                </c:if>
				<div class="baseDateDisplay fineprint">Base Date: <span>MM/DD/YYYY</span></div>                
            </div>
			</td>
            <td nowrap width="5%" valign="middle" class="infoline">
            <div align="center">
                <kul:htmlControlAttribute property="awardReportsBean.newAwardReportTerms[${index}].ospDistributionCode" attributeEntry="${awardReportTermAttributes.ospDistributionCode}" />
            </div>
			</td>                
            <td nowrap width="13%" valign="middle" class="infoline">
            <div align="center">
                <kul:htmlControlAttribute property="awardReportsBean.newAwardReportTerms[${index}].dueDate" attributeEntry="${awardReportTermAttributes.dueDate}"  />
            </div>
			</td>                
			<td class="infoline">
			<div align="center">
			    <html:image property="methodToCall.addAwardReportTerm.reportClass${reportClassKey}.reportClassIndex${index}.anchor${tabKey}"
			        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton"/>
			</div>
            </td>
        </tr>  
        </c:if>      
        	                                            
        <c:forEach var="awardReportTerm" items="${KualiForm.document.award.awardReportTermItems}" varStatus="status">
	        <c:if test="${awardReportTerm.reportClassCode == reportClassKey }" >
	        <c:set var="counterReport" value="${counterReport + 1}" />
	    <tr>
		    <th width="5%" class="infoline" rowspan="2">
			    <c:out value="${counterReport}" />
			</th>			                
	        <td nowrap width="5%" valign="middle">	        
			<div align="center">
				<kul:checkErrors keyMatch="document.awardList[0].awardReportTermItems[${status.index}].reportCode" auditMatch="${property}"/>				
				<c:if test="${hasErrors==true}" >
    				<c:set var="textStyle" value="background-color:#FFD5D5"/>
  				</c:if>                
                <html:select property="document.awardList[0].awardReportTermItems[${status.index}].reportCode" tabindex="0" style="${textStyle}" 
                             onchange="javascript: loadFrequencyCode('${reportClassKey}', 'document.awardList[0].awardReportTermItems[${status.index}].reportCode','document.awardList[0].awardReportTermItems[${status.index}].frequencyCode');return false" disabled="${readOnly}">                                             
                <c:forEach items="${krafn:getOptionList('org.kuali.kra.award.lookup.keyvalue.ReportCodeValuesFinder', paramMap1)}" var="option">                	
	                <c:choose>                    	
	                	<c:when test="${KualiForm.document.awardList[0].awardReportTermItems[status.index].reportCode == option.key}">
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
			<c:set target="${paramMap2}" property="reportCode" value="${KualiForm.document.awardList[0].awardReportTermItems[status.index].reportCode}" />			
	        <td nowrap width="5%" valign="middle">
			<div align="center">
				<kul:checkErrors keyMatch="document.awardList[0].awardReportTermItems[${status.index}].frequencyCode" auditMatch="${property}"/>				
				<c:if test="${hasErrors==true}" >
    				<c:set var="textStyle" value="background-color:#FFD5D5"/>
  				</c:if> 
                <html:select property="document.awardList[0].awardReportTermItems[${status.index}].frequencyCode" tabindex="0" style="${textStyle}" 
                	onchange="javascript: loadFrequencyBaseCode('document.awardList[0].awardReportTermItems[${status.index}].frequencyCode','document.awardList[0].awardReportTermItems[${status.index}].frequencyBaseCode');return false" disabled="${readOnly}">                
                <c:forEach items="${krafn:getOptionList('org.kuali.kra.award.lookup.keyvalue.FrequencyCodeValuesFinder', paramMap2)}" var="option">                	
	                <c:choose>                    	
	                	<c:when test="${KualiForm.document.awardList[0].awardReportTermItems[status.index].frequencyCode == option.key}">
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
			<c:set target="${paramMap3}" property="frequencyCode" value="${KualiForm.document.awardList[0].awardReportTermItems[status.index].frequencyCode}" />
	        <td nowrap width="5%" valign="middle">
			<div align="center">
				<kul:checkErrors keyMatch="document.awardList[0].awardReportTermItems[${status.index}].frequencyBaseCode" auditMatch="${property}"/>				
				<c:if test="${hasErrors==true}" >
    				<c:set var="textStyle" value="background-color:#FFD5D5"/>
  				</c:if>                 
                <html:select property="document.awardList[0].awardReportTermItems[${status.index}].frequencyBaseCode" tabindex="0" style="${textStyle}" disabled="${readOnly}" onchange="updateBaseDateDisplay(this);">                
                <c:forEach items="${krafn:getOptionList('org.kuali.kra.award.lookup.keyvalue.FrequencyBaseCodeValuesFinder', paramMap3)}" var="option">
	                <c:choose>                    	
	                	<c:when test="${KualiForm.document.awardList[0].awardReportTermItems[status.index].frequencyBaseCode == option.key}">
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
	            <div class="baseDateDisplay fineprint">Base Date: <span>MM/DD/YYYY</span></div>
			</div>
			</td>
	        <td nowrap width="13%" valign="middle">
			<div align="center">
                <kul:htmlControlAttribute property="document.awardList[0].awardReportTermItems[${status.index}].ospDistributionCode" attributeEntry="${awardReportTermAttributes.ospDistributionCode}" />
			</div>
			</td>	                
	        <td nowrap width="10%" valign="middle">
			<div align="center">
                <kul:htmlControlAttribute property="document.awardList[0].awardReportTermItems[${status.index}].dueDate" attributeEntry="${awardReportTermAttributes.dueDate}"  />
			</div>
			</td>
			</td>
			<td valign="middle">
			<div align="center">
			 <c:if test="${!readOnly}">
			    <html:image property="methodToCall.deleteAwardReportTerm.line${status.index}.anchor${currentTabIndex}"
			        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
				<c:if test="${KualiForm.syncMode}">
					<html:image property="methodToCall.syncAwardReportTerm.line${status.index}.anchor${currentTabIndex}"
						src='${ConfigProperties.kra.externalizable.images.url}tinybutton-sync.gif' alt="sync" styleClass="tinybutton" disabled="${readOnly}"/>
				</c:if>
			 </c:if>
			 <c:if test="${readOnly}">&nbsp;</c:if>
			</div>            
			</td>
	    </tr>
	    <tr>
	        <td colspan="6">
	            ${KualiForm.valueFinderResultCache}
	            <kra-a:awardReportRecipients innerTabParent="${reportClassLabel}" index="${status.index}" />
	        	${KualiForm.valueFinderResultDoNotCache}
	            <c:if test="${KualiForm.reportClassForPaymentsAndInvoices.reportClassCode != reportClassKey}" >
	            	<kra-a:awardReporting innerTabParent="${reportClassLabel}" index="${status.index}" />
	            	<kra-a:reportTracking innerTabParent="${reportClassLabel}" index="${status.index}" />
	            </c:if>		    
	        </td>
	    </tr>
	    	</c:if>                   	
	    </c:forEach> 
    </table>
</kul:innerTab>	
