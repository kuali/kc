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
<%@ attribute name="htmlFormAction" required="false" %>
<%@ attribute name="renderMultipart" required="false" %>
<c:set var="subAwardAmountInfoAttributes" value="${DataDictionary.SubAwardAmountInfo.attributes}" />
<c:set var="subAwardAttributes" value="${DataDictionary.SubAward.attributes}" />
<c:set var="action" value="subAwardFinancial" />

<c:set var="newSubAwardAmountInfo" value="${KualiForm.newSubAwardAmountInfo}" />

<kul:tab tabTitle="History of Changes" defaultOpen="true" alwaysOpen="true" transparentBackground="true" tabErrorKey="subAwardAmountInfoErrors,newSubAwardAmountInfo.periodofPerformanceStartDate,newSubAwardAmountInfo.effectiveDate*,newSubAwardAmountInfo.obligatedChange*,newSubAwardAmountInfo.anticipatedChange*,newSubAwardAmountInfo.comments*,document.subAwardList[0].subAwardAmountInfoList*,document.subAwardList[0].modificationId,document.subAwardList[0].totalAnticipatedAmount*,document.subAwardList[0].totalObligatedAmount*" auditCluster="subawardFinancialdAuditErrors" tabAuditKey="document.subAwardList[0].totalAnticipatedAmount*,document.subAwardList[0].totalObligatedAmount*" useRiceAuditMode="true">
	<div class="tab-container" align="center">
	<h3>
    	<span class="subhead-left"></span>
    	<span class="subhead-right"></span>
    </h3>
	<table cellpadding=0 cellspacing=0 summary="">
   				<tr>
				<th><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.totalObligatedAmount}" /></th>
                <td colspan="2">
                
                     <kul:htmlControlAttribute property="document.subAwardList[0].totalObligatedAmount" disabled="true" attributeEntry="${subAwardAttributes.totalObligatedAmount}" />           
                </td>
				<th><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.totalAnticipatedAmount}" /></th>
                <td colspan="2">
                      <kul:htmlControlAttribute property="document.subAwardList[0].totalAnticipatedAmount" disabled="true" attributeEntry="${subAwardAttributes.totalAnticipatedAmount}" />           
                </td>
            </tr>    
            
            <tr>
				<th><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.totalAmountReleased}" /></th>
                <td colspan="2">
                        <kul:htmlControlAttribute property="document.subAwardList[0].totalAmountReleased" disabled="true" attributeEntry="${subAwardAttributes.totalAmountReleased}" />           
                </td>
				<th><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.totalAvailableAmount}" /></th>
                <td colspan="2">
                      <kul:htmlControlAttribute property="document.subAwardList[0].totalAvailableAmount" disabled="true" attributeEntry="${subAwardAttributes.totalAvailableAmount}" />           
                </td>
            </tr>
            
            <tr>
				<th><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.modificationEffectiveDate}" /></th>
                <td colspan="2">
                
                     <kul:htmlControlAttribute property="document.subAwardList[0].modificationEffectiveDate" disabled="true" attributeEntry="${subAwardAttributes.modificationEffectiveDate}" datePicker="false"/>           
                </td>
				<th><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.modificationId}" /></th>
                <td colspan="2">
                      <kul:htmlControlAttribute property="document.subAwardList[0].modificationId" disabled="true" attributeEntry="${subAwardAttributes.modificationId}" />           
                </td>
            </tr>    
            
            <tr>
				<th><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.performanceStartDate}" /></th>
                <td colspan="2">
                
                     <kul:htmlControlAttribute property="document.subAwardList[0].performanceStartDate" disabled="true" attributeEntry="${subAwardAttributes.performanceStartDate}" datePicker="false"/>           
                </td>
				<th><kul:htmlAttributeLabel attributeEntry="${subAwardAttributes.performanceEnddate}" /></th>
                <td colspan="2">
                      <kul:htmlControlAttribute property="document.subAwardList[0].performanceEnddate" disabled="true" attributeEntry="${subAwardAttributes.performanceEnddate}" datePicker="false"/>           
                </td>
            </tr> 
            
            </table>
    	<h3>
    		<span class="subhead-left">History of Changes</span>
            <div align="right"><kul:help parameterNamespace="KC-SUBAWARD" parameterDetailType="Document" parameterName="subAwardHistoryOfChangesHelpUrl" altText="help"/></div>
        </h3>
         
   <table cellpadding=0 cellspacing=0 summary="">
       		<tbody class="addline">            
            <tr>
              <th><div align="left">&nbsp;</div></th> 
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountInfoAttributes.effectiveDate}" /></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountInfoAttributes.obligatedChange}" /></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountInfoAttributes.anticipatedChange}" /></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountInfoAttributes.fileName}" /></div></th>
                <%-- <c:if test="${canModify}">  --%>
              	    <kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col"/>
          	    <%-- </c:if> --%>
            </tr>
              <c:if test="${readOnly!='true'}">
            <tr>
    
    				<th class="infoline" rowspan="4">
						Add:
					</th>
					
     			 
     			<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardAmountInfo.effectiveDate" readOnly="${readOnly}" attributeEntry="${subAwardAmountInfoAttributes.effectiveDate}" datePicker="true"/>           
   					</div> 
   				</td>
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardAmountInfo.obligatedChange" readOnly="${readOnly}" attributeEntry="${subAwardAmountInfoAttributes.obligatedChange}" />           
   					</div> 
   				</td>
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardAmountInfo.anticipatedChange" readOnly="${readOnly}" attributeEntry="${subAwardAmountInfoAttributes.anticipatedChange}" />         
   					</div> 
   				</td>
   				
   				   <td class="infoline">
   				   <c:if test="${readOnly!='true'}">
                	<html:file property="newSubAwardAmountInfo.newFile"  />
                	</c:if>
                </td>
   				 				
   				<td class="infoline" rowspan="4"><div align="center">
   					<c:if test="${readOnly!='true'}">
						<html:image property="methodToCall.addAmountInfo.anchor${tabKey}" 
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
						            styleClass="tinybutton"/>
					</c:if>
	                </div>
	            </td>   				
   			</tr> 
   			
   			<tr>
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountInfoAttributes.modificationEffectiveDate}" /></div></th>
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountInfoAttributes.modificationID}" /></div></th>
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountInfoAttributes.periodofPerformanceStartDate}" /></div></th>
            <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountInfoAttributes.periodofPerformanceEndDate}" /></div></th>                          
            </tr>
            
            <tr>
            <td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardAmountInfo.modificationEffectiveDate" readOnly="${readOnly}" attributeEntry="${subAwardAmountInfoAttributes.modificationEffectiveDate}" datePicker="true"/>           
   					</div> 
   				</td>
   				
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardAmountInfo.modificationID" readOnly="${readOnly}" attributeEntry="${subAwardAmountInfoAttributes.modificationID}" />           
   					</div> 
   				</td>
   				
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardAmountInfo.periodofPerformanceStartDate" readOnly="${readOnly}" attributeEntry="${subAwardAmountInfoAttributes.periodofPerformanceStartDate}" datePicker="true"/>           
   					</div> 
   				</td>
   				
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardAmountInfo.periodofPerformanceEndDate" readOnly="${readOnly}" attributeEntry="${subAwardAmountInfoAttributes.periodofPerformanceEndDate}" datePicker="true"/>           
   					</div> 
   				</td> 
   				</tr>
   				
        	<tr>				
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountInfoAttributes.comments}" /></div></th>
                <td colspan="3">
                      <kul:htmlControlAttribute property="newSubAwardAmountInfo.comments" readOnly="${readOnly}" attributeEntry="${subAwardAmountInfoAttributes.comments}" />
                </td>
            </tr>     
   			</c:if>
   			</tbody>
   			<c:forEach var="newSubAwardAmountInfo" items="${KualiForm.document.subAwardList[0].subAwardAmountInfoList}" varStatus="status">
		             <tr>
						<th width="9%" class="infoline" rowspan="3">
							<c:out value="${status.index+1}" />
						</th>	                
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountInfoList[${status.index}].effectiveDate" attributeEntry="${subAwardAmountInfoAttributes.effectiveDate}"  datePicker="true" />
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountInfoList[${status.index}].obligatedChange" attributeEntry="${subAwardAmountInfoAttributes.obligatedChange}"  />                		
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountInfoList[${status.index}].anticipatedChange" attributeEntry="${subAwardAmountInfoAttributes.anticipatedChange}"   />
						</div>
					
						<td width="9%" valign="middle">

						<div align="center"></div>
						<div id="replaceDiv${status.index}" style="display: block;">
						 <c:if test="${newSubAwardAmountInfo.fileName!=null}">
							<kra:fileicon attachment="${newSubAwardAmountInfo}" />
							</c:if>
							<kul:htmlControlAttribute
								property="document.subAwardList[0].subAwardAmountInfoList[${status.index}].fileName"
								readOnly="true"
								attributeEntry="${subAwardAmountInfoAttributes.fileName}" />
						</div>
						<div id="fileDiv${status.index}" valign="middle"
							style="display: none;">
							<html:file
								property="document.subAwardList[0].subAwardAmountInfoList[${status.index}].newFile" />
							<html:image
								property="methodToCall.replaceHistoryOfChangesAttachment.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif'
								styleClass="tinybutton" />
						</div></td>
					<td width="10%" valign="middle" rowspan="3">
					<div align="center">Attachment Actions :
					</div><br></br>
						<div align="center">
						  <c:if test="${newSubAwardAmountInfo.fileName!=null}">
                                    <html:image
                                        styleId="downloadHistoryOfChangesAttachment.line${status.index}"
                                        property="methodToCall.downloadHistoryOfChangesAttachment.line${status.index}.anchor${currentTabIndex}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif'
                                        styleClass="tinybutton"
                                        onclick="javascript: openNewWindow('${action}','downloadHistoryOfChangesAttachment','${status.index}',${KualiForm.formKey},'${KualiForm.document.sessionDocument}'); return false" />
                            </c:if>
							<c:if test="${!readOnly}">
								<html:image
									styleId="replaceHistoryOfChangesAttachment.line${status.index}"
									onclick="javascript: showHide('fileDiv${status.index}','replaceDiv${status.index}') ; return false"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-replace.gif'
									styleClass="tinybutton"
									property="methodToCall.replaceNarrativeAttachment.line${status.index}.anchor${currentTabIndex};return false" />
								<c:if test="${newSubAwardAmountInfo.fileName!=null}">
									<html:image
										property="methodToCall.deleteAmountInfo.line${status.index}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif'
										styleClass="tinybutton" />
								</c:if>
							</c:if>
							<c:if test="${readOnly}">&nbsp;</c:if>
						</div></td>	
		            </tr>
		            <tr>
		            <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountInfoList[${status.index}].modificationEffectiveDate" attributeEntry="${subAwardAmountInfoAttributes.modificationEffectiveDate}"  datePicker="true" />
						</div>
						</td>
						<td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountInfoList[${status.index}].modificationID" attributeEntry="${subAwardAmountInfoAttributes.modificationID}"/>
						</div>
						</td>
						<td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountInfoList[${status.index}].periodofPerformanceStartDate" attributeEntry="${subAwardAmountInfoAttributes.periodofPerformanceStartDate}"  datePicker="true" />
						</div>
						</td>
						<td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountInfoList[${status.index}].periodofPerformanceEndDate" attributeEntry="${subAwardAmountInfoAttributes.periodofPerformanceEndDate}"  datePicker="true" />
						</div>
						</td>
		            </tr>
		            <tr>		            			
						<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountInfoAttributes.comments}" /></div></th>
                			<td colspan="3">
                      			<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountInfoList[${status.index}].comments"  attributeEntry="${subAwardAmountInfoAttributes.comments}" />
                			</td>
           		   </tr>
           		   
	        	</c:forEach>
        </table>
    </div>
</kul:tab>
