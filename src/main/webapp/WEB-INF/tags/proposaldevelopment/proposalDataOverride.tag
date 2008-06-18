<%--
 Copyright 2007 The Kuali Foundation.
 
 Licensed under the Educational Community License, Version 1.0 (the "License");
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

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.ProposalDevelopmentDocument.attributes}" />
<c:set var="proposalChangedDataAttributes" value="${DataDictionary.ProposalChangedData.attributes}" />
<c:set var="proposalColumnsToAlterAttributes" value="${DataDictionary.ProposalColumnsToAlter.attributes}" />
<c:set var="action" value="proposalDevelopmentActions" />

 <kul:tab tabTitle="Proposal Data Override" defaultOpen="${openFlag}" tabErrorKey="" >
         
	<div class="tab-container" align="center">
    	<div class="h2-container">
    		<span class="subhead-left"><h2>Proposal Data Override</h2></span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.proposaldevelopment.bo.ProposalChangedData" altText="help"/></span>
        </div>
        
        <kra:section permission="alterProposalData">   
		 <table cellpadding="0" cellspacing="0" summary="">
			<input type="hidden" name="document.proposalNumber" value="${KualiForm.document.proposalNumber}" />
			<input type="hidden" name="newProposalChangedData.editableColumn.lookupReturn" />
			<input type="hidden" name="newProposalChangedData.editableColumn.dataType" />
			<input type="hidden" name="newProposalChangedData.editableColumn.hasLookup" />
			<input type="hidden" name="newProposalChangedData.editableColumn.lookupArgument" />
			<input type="hidden" name="newProposalChangedData.editableColumn.lookupWindow" />
			<input type="hidden" name="newProposalChangedData.editableColumn.columnName" /> 
			<input type="hidden" name="imageUrl" value="${ConfigProperties.kr.externalizable.images.url}" /> 
			<input type="hidden" name="tabIndex" value="${KualiForm.nextArbitrarilyHighIndex}" />
		    <c:set var="textAreaFieldName" value="newProposalChangedData.comments" />
			
			<tr>
				<th align="right" valign="middle"><kul:htmlAttributeLabel attributeEntry="${proposalChangedDataAttributes.columnName}" noColon="false" /></th>
				<td align="left" valign="middle">
					<kul:htmlControlAttribute property="newProposalChangedData.columnName" attributeEntry="${proposalChangedDataAttributes.columnName}" onchange="updateOtherFields(this, updateOtherFields_Callback);" styleClass="fixed-size-select"/>
				</td>
			</tr>
		       	<tr>
		                <th align="right" valign="middle"><kul:htmlAttributeLabel attributeEntry="${proposalChangedDataAttributes.oldDisplayValue}" noColon="false" /></th>
		                <td align="left" valign="middle">
					<kul:htmlControlAttribute property="newProposalChangedData.oldDisplayValue" attributeEntry="${proposalChangedDataAttributes.oldDisplayValue}" />
				</td>
		        </tr>
		       	<tr>
		        	<th align="right" valign="middle"><kul:htmlAttributeLabel attributeEntry="${proposalChangedDataAttributes.displayValue}" noColon="false" /></th>
		        	<td align="left" valign="middle">
					<kul:htmlControlAttribute property="newProposalChangedData.displayValue" attributeEntry="${proposalChangedDataAttributes.displayValue}" />
				</td>
			</tr>
			<tr>
				<th align="right" valign="middle"><kul:htmlAttributeLabel attributeEntry="${proposalChangedDataAttributes.changedValue}" noColon="false" /></th>
				<td align="left" valign="middle">
					<div nowrap>
						<kul:htmlControlAttribute property="newProposalChangedData.changedValue" attributeEntry="${proposalChangedDataAttributes.changedValue}" />
							<div id="changedValueExtraBody" >
			                   		<c:if test="${not empty KualiForm.newProposalChangedData.editableColumn.lookupArgument and KualiForm.newProposalChangedData.editableColumn.lookupArgument != 'null'}">
										<kul:lookup boClassName="${KualiForm.newProposalChangedData.editableColumn.lookupArgument}" fieldConversions="${KualiForm.newProposalChangedData.editableColumn.lookupReturn}:newProposalChangedData.changedValue," anchor="${tabKey}"/>
									</c:if>
									<c:if test="${KualiForm.newProposalChangedData.editableColumn.dataType == 'DATE'}">
						                <img src="${ConfigProperties.kr.externalizable.images.url}cal.gif" id="newProposalChangedData.changedValue_datepicker" style="cursor: pointer;"
						                     title="Date selector" alt="Date selector"
						                     onmouseover="this.style.backgroundColor='red';" onmouseout="this.style.backgroundColor='transparent';" />
						                     <script type="text/javascript">
							                  Calendar.setup(
							                          {
							                            inputField : "newProposalChangedData.changedValue", // ID of the input field
							                            ifFormat : "%m/%d/%Y", // the date format
							                            button : "newProposalChangedData.changedValue_datepicker" // ID of the button
							                          }
							                  );
							               </script>	 
									</c:if>
			                  </div>
			                  
			                  <c:if test="${fn:length(ErrorPropertyList) > 0}" >
				                  <c:forEach items="${ErrorPropertyList}" var="key">
				                  	<c:if test="${fn:startsWith(key, 'newProposalChangedData.')}" >
		              					<c:set var="overrideDataError" value="true"/>
		              				</c:if>
		              			  </c:forEach>
	              			  </c:if>
	              			  
	 						  <div id="calendarDiv" style="display:none;">
								  <c:if test="${empty KualiForm.newProposalChangedData.editableColumn.dataType or overrideDataError == 'true'}">
											<img src="${ConfigProperties.kr.externalizable.images.url}cal.gif" id="newProposalChangedData.changedValue_datepicker" style="cursor: pointer;"
								                     title="Date selector" alt="Date selector"
								                     onmouseover="this.style.backgroundColor='red';" onmouseout="this.style.backgroundColor='transparent';" />
								 </c:if>
							 </div>
					</div>
				</td>
			</tr>
			<tr>
		                <th align="right" valign="middle"><kul:htmlAttributeLabel attributeEntry="${proposalChangedDataAttributes.comments}"noColon="true" /></th>
		                <td align="left" valign="middle">
					<kul:htmlControlAttribute property="newProposalChangedData.comments" attributeEntry="${proposalChangedDataAttributes.comments}" />
			               	<kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${proposalChangedDataAttributes.comments.label}" />
				</td>
		        </tr>
			<tr>
				<td align="center" colspan="2">
				<div align="center">
					<html:image property="methodToCall.addProposalChangedData.anchor${tabKey}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-edit1.gif' />
				</div>
		                </td>
			</tr>
		 </table>
        </kra:section>
        
       
        <c:if test="${fn:length(KualiForm.document.proposalChangeHistory) > 0}">
	        <br>
	        <div class="h2-container">
	    		<span class="subhead-left"><h2>Proposal Change History</h2></span>
	    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.proposaldevelopment.bo.ProposalChangedData" altText="help"/></span>
	        </div>
	        
		    <table cellpadding=0 cellspacing=0 summary="">
		     	<tr>
	                  <th>Original Value</th>
	                  <th>New Value</th>
	                  <th>Change Date</th>
	                  <th>Changed By</th>
	                  <th>Explanation</th>
	            </tr>
	                
	        	<c:forEach var="changedDataListByColumn" items="${KualiForm.document.proposalChangeHistory}" varStatus="status">
	        		<tr><td colspan="5" class="tab-subhead" >${status.current.key}</td></tr>
	
		        	<c:forEach var="proposalChangedData" items="${status.current.value}" varStatus="innerStatus">
			        	<tr>
			        		<td align="left" valign="middle" class="infoline"> 
				                <div align="center">
								<c:out value="${proposalChangedData.oldDisplayValue}" />
				                &nbsp;</div>
							</td>
			                <td align="left" valign="middle" class="infoline">
				                <div align="center"><c:out value="${proposalChangedData.displayValue}" />&nbsp;</div>
			                </td>
			                <td align="left" valign="middle" class="infoline">
			                	<div align="center">
			                		<fmt:formatDate value="${proposalChangedData.updateTimestamp}" type="both" dateStyle="short" timeStyle="short" />
			                	&nbsp;</div>
			                </td>
			                <td align="left" valign="middle" class="infoline">
			                	<div align="center"><c:out value="${proposalChangedData.updateUser}" />&nbsp;</div>
			                </td>
			                <td align="left" valign="middle" class="infoline">
			                	<div align="center"><c:out value="${proposalChangedData.comments}" />&nbsp;</div>
			                </td>
							 <td align="left" valign="middle" class="infoline">
			                	<div align="left">&nbsp</div>
			                </td>	
		   				</tr>
	        		</c:forEach>
	        	</c:forEach>
	      	   </table>
      	   
      	</c:if>

    </div> 
</kul:tab>

