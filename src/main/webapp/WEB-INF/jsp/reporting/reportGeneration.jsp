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
 <c:set var="KualiForm" value="${ReportGenerationForm}" scope="request"/>   
<kul:page  docTitle="Birt Reporting"  transactionalDocument="false" 
htmlFormAction="reporting">
<c:set var="custReportDetailsAttributes" value="${DataDictionary.CustReportDetails.attributes}" />
<c:set var="custRptDefaultParmsAttributes" value="${DataDictionary.CustRptDefaultParms.attributes}" />

<script type="text/javascript" src="scripts/jquery/jquery.tablesorter.js"></script> 
        <script type="text/javascript" language="javascript" src="scripts/kuali_application.js"></script>
        <script type="text/javascript" language="javascript" src="scripts/core.js"></script>
		<script language="JavaScript" type="text/javascript" src="dwr/util.js"></script>

	<kul:tabTop tabTitle="Generate Report" defaultOpen="true" tabErrorKey="custReportDetails.reportLabelDisplay">
		 <div class="tab-container" align="center">
			<h3>
				<span class="subhead-left">Generate Report</span>
			</h3>
			 <table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th align="right" valign="center" width="44%"><kul:htmlAttributeLabel
							attributeEntry="${custReportDetailsAttributes.reportLabelDisplay}" />
					</th>
					<td><kul:htmlControlAttribute
							property="custReportDetails.reportLabelDisplay"
							attributeEntry="${custReportDetailsAttributes.reportLabelDisplay}"
							onchange="generateInputParams(this)" />
					</td>
				</tr>
			</table>
		</div>
	</kul:tabTop>
	<kul:tab tabTitle="" defaultOpen="true" tabErrorKey="reportParameterList[0].inputParameterText">
	 <div class="tab-container" align="center">
		 <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<c:if test="${not empty KualiForm.reportParameterList}">
				<tr><div style="border:0px; border-style: solid; 
						border-radius: 3px;  background-color: #E4E4E4;"><h3 align="left" style="background-color: #6B6B6B;border-bottom: 1px solid #666666;border-top: 1px solid #666666;color: #FFFFFF;font-size: 100%;font-weight: bold;height: 18px;margin: 0px;padding: 2px 0px 0px 6px;text-align: left;"><c:out value="${KualiForm.reportName}"/></h3></div></tr>
						<c:forEach var="reportParams" items="${KualiForm.reportParameterList}" varStatus="status">
							<tr>
							<c:if test="${KualiForm.reportParameterList[status.index].controlType == 1}">
								<th align="right" valign="middle"><c:out
										value="${KualiForm.reportParameterList[status.index].promptText}:" />
								</th>
								
									<td align="left"><html:text
											property="reportParameterList[${status.index}].inputParameterText"
											size="20" maxlength="20" styleId = "reportParameterList[${status.index}].inputParameterText"/>
											<c:if test ="${KualiForm.reportParameterList[status.index].dataType == 5}">
											
											<img src="${ConfigProperties.kr.externalizable.images.url}cal.gif" id="reportParameterList[${status.index}].inputParameterText_datepicker" style="cursor: pointer;"
						                     title="Date selector" alt="Date selector"
						                     onmouseover="this.style.backgroundColor='red';" onmouseout="this.style.backgroundColor='transparent';" />
						                     <script type="text/javascript">
							                  Calendar.setup(
							                          {
							                            inputField : "reportParameterList[${status.index}].inputParameterText", // ID of the input field
							                            ifFormat : "%m/%d/%Y", // the date format
							                            button : "reportParameterList[${status.index}].inputParameterText_datepicker" // ID of the button
							                          }
							                  );
							                
							               </script>
							               </c:if>
									</td>
								</c:if>
							</tr>
						</c:forEach>
						</c:if>	
						<c:if test="${not empty KualiForm.reportId}">
					<tr>
						<th align="right" valign="center" width="44%"><c:out
								value="Report Format" />
						</th>
						<td><html:select property="reportFormat">
								<html:option value="PDF" />
								<html:option value="HTML" />
								<html:option value="EXCEL" />
							</html:select></td>
					</tr>
					</c:if>
		</table>
		</div>
	</kul:tab>
	<kul:panelFooter />
	<br><br>
		<div align="center">		
			<c:if test="${not empty KualiForm.reportId}">						
				<html:image
						property="methodToCall.printReport.KualiForm.reportName"
						src='${ConfigProperties.kra.externalizable.images.url}buttonsmall_report.gif' onclick="excludeSubmitRestriction=true" />
				<html:image src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_cancel.gif" styleClass="globalbuttons" 
								title="cancel" alt="cancel" onclick="resetBirtSelectDiv();"/>
			</c:if>		
			<html:image property="methodToCall.close" src="${ConfigProperties.kr.externalizable.images.url}buttonsmall_close.gif" 
								styleClass="globalbuttons" title="close" alt="close" />
		</div>												
    </kul:page>
