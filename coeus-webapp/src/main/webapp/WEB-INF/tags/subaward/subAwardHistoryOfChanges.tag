<%--
 Copyright 2005-2013 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.opensource.org/licenses/ecl1.php
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="htmlFormAction" required="false" %>
<%@ attribute name="renderMultipart" required="false" %>
<c:set var="subAwardAmountInfoAttributes" value="${DataDictionary.SubAwardAmountInfo.attributes}" />
<c:set var="subAwardAttributes" value="${DataDictionary.SubAward.attributes}" />
<c:set var="action" value="subAwardFinancial" />

<c:set var="newSubAwardAmountInfo" value="${KualiForm.newSubAwardAmountInfo}" />

<kul:tab tabTitle="History of Changes" defaultOpen="true" alwaysOpen="true" transparentBackground="true" tabErrorKey="subAwardAmountInfoErrors,newSubAwardAmountInfo.effectiveDate*,newSubAwardAmountInfo.obligatedChange*,newSubAwardAmountInfo.anticipatedChange*,newSubAwardAmountInfo.comments*,document.subAwardList[0].subAwardAmountInfoList*,document.subAwardList[0].totalAnticipatedAmount*,document.subAwardList[0].totalObligatedAmount*" auditCluster="subawardFinancialdAuditErrors" tabAuditKey="document.subAwardList[0].totalAnticipatedAmount*,document.subAwardList[0].totalObligatedAmount*" useRiceAuditMode="true">
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
            </table>
    	<h3>
    		<span class="subhead-left">History of Changes</span>
            <div align="right"><kul:help parameterNamespace="KC-SUBAWARD" parameterDetailType="Document" parameterName="subAwardHistoryOfChangesHelpUrl" altText="help"/></div>
        </h3>
         
   <table cellpadding=0 cellspacing=0 summary="">
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
    
    				<th class="infoline" rowspan="2">
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
   				 				
   				<td class="infoline" rowspan="2"><div align="center">
   					<c:if test="${readOnly!='true'}">
						<html:image property="methodToCall.addAmountInfo.anchor${tabKey}" 
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
						            styleClass="tinybutton"/>
					</c:if>
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
   			
   			<c:forEach var="newSubAwardAmountInfo" items="${KualiForm.document.subAwardList[0].subAwardAmountInfoList}" varStatus="status">
		             <tr>
						<th width="9%" class="infoline" rowspan="2">
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
					<td width="10%" valign="middle" rowspan="2">
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
						<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardAmountInfoAttributes.comments}" /></div></th>
                			<td colspan="3">
                      			<kul:htmlControlAttribute property="document.subAwardList[0].subAwardAmountInfoList[${status.index}].comments"  attributeEntry="${subAwardAmountInfoAttributes.comments}" />
                			</td>
           		   </tr>
           		   
	        	</c:forEach>
        </table>
    </div>
</kul:tab>