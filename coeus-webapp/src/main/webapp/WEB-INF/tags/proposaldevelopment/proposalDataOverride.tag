<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 The Kuali Foundation
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

<c:set var="proposalDevelopmentAttributes" value="${DataDictionary.DevelopmentProposal.attributes}" />
<c:set var="proposalChangedDataAttributes" value="${DataDictionary.ProposalChangedData.attributes}" />
<c:set var="proposalColumnsToAlterAttributes" value="${DataDictionary.ProposalColumnsToAlter.attributes}" />
<c:set var="action" value="proposalDevelopmentActions" />

     <%-- Need to register the lookup methodToCall that can be generated by the js --%>
     <c:forEach items="${KualiForm.proposalDataOverrideMethodToCalls}" var = "mtc" varStatus = "status">
           	${kfunc:registerEditableProperty(KualiForm, mtc)}
	 </c:forEach>
	        
 
 <kul:tab tabTitle="Proposal Data Override" defaultOpen="${openFlag}" tabErrorKey="newProposalChangedData.*">
         
	<div class="tab-container" align="center">
	 <kra:section permission="alterProposalData">  
    	<h3>
    		<span class="subhead-left">Proposal Data Override</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.coeus.propdev.impl.editable.ProposalChangedData" altText="help"/></span>
        </h3>
        
        
		 <table cellpadding="0" cellspacing="0" summary="">
			<input type="hidden" name="document.developmentProposalList[0].proposalNumber" id="document.developmentProposalList[0].proposalNumber" value="${KualiForm.document.developmentProposalList[0].proposalNumber}" />
			<input type="hidden" name="newProposalChangedData.editableColumn.lookupReturn" id="newProposalChangedData.editableColumn.lookupReturn"  value="${KualiForm.newProposalChangedData.editableColumn.lookupReturn}" />
			<input type="hidden" name="newProposalChangedData.editableColumn.dataType" id="newProposalChangedData.editableColumn.dataType" value="${KualiForm.newProposalChangedData.editableColumn.dataType}" />
			<input type="hidden" name="newProposalChangedData.editableColumn.hasLookup" id="newProposalChangedData.editableColumn.hasLookup" value="${KualiForm.newProposalChangedData.editableColumn.hasLookup}" />
			<input type="hidden" name="newProposalChangedData.editableColumn.lookupClass" id="newProposalChangedData.editableColumn.lookupClass" value="${KualiForm.newProposalChangedData.editableColumn.lookupClass}" />
			<input type="hidden" name="newProposalChangedData.editableColumn.lookupPkReturn" id="newProposalChangedData.editableColumn.lookupPkReturn"  value="${KualiForm.newProposalChangedData.editableColumn.lookupPkReturn}" />
			<input type="hidden" name="newProposalChangedData.editableColumn.columnName" id="newProposalChangedData.editableColumn.columnName" value="${KualiForm.newProposalChangedData.editableColumn.columnName}" />
			<input type="hidden" name="imageUrl" id="imageUrl"  value="${ConfigProperties.kr.externalizable.images.url}" /> 
			<input type="hidden" name="tabIndex" id="tabIndex" value="${KualiForm.nextArbitrarilyHighIndex}" />
		    <c:set var="textAreaFieldName" value="newProposalChangedData.comments" />
			
			<tr>
				<th align="right" valign="middle"><kul:htmlAttributeLabel attributeEntry="${proposalChangedDataAttributes.columnName}" noColon="false" /></th>
				<td align="left" valign="middle">
					<kul:htmlControlAttribute readOnly="false" property="newProposalChangedData.columnName" attributeEntry="${proposalChangedDataAttributes.columnName}" onchange="updateOtherFields(this, 'document.developmentProposalList[0].proposalNumber', updateOtherFields_Callback);" styleClass="fixed-size-select"/>
				</td>
			</tr>
		       	<tr>
		                <th align="right" valign="middle"><kul:htmlAttributeLabel attributeEntry="${proposalChangedDataAttributes.oldDisplayValue}" noColon="false" /></th>
		                <td align="left" valign="middle">
					<kul:htmlControlAttribute readOnly="false" property="newProposalChangedData.oldDisplayValue" attributeEntry="${proposalChangedDataAttributes.oldDisplayValue}" />
				</td>
		        </tr>
		       	<tr>
		        	<th align="right" valign="middle"><kul:htmlAttributeLabel attributeEntry="${proposalChangedDataAttributes.displayValue}" noColon="false" /></th>
		        	<td align="left" valign="middle">
					<kul:htmlControlAttribute readOnly="false" property="newProposalChangedData.displayValue" attributeEntry="${proposalChangedDataAttributes.displayValue}" />
				</td>
			</tr>
			<tr>
				<th align="right" valign="middle"><kul:htmlAttributeLabel attributeEntry="${proposalChangedDataAttributes.changedValue}" noColon="false" /></th>
				<td align="left" valign="middle">
					<div nowrap>
						<kul:htmlControlAttribute readOnly="false" property="newProposalChangedData.changedValue" attributeEntry="${proposalChangedDataAttributes.changedValue}" />
							<div id="changedValueExtraBody" >
			                   		<c:if test="${not empty KualiForm.newProposalChangedData.editableColumn.lookupClass and KualiForm.newProposalChangedData.editableColumn.lookupClass != 'null'}">
				                   		<c:if test="${not empty KualiForm.newProposalChangedData.editableColumn.lookupPkReturn and KualiForm.newProposalChangedData.editableColumn.lookupPkReturn != 'null'}">
				                   		<c:if test="${not empty KualiForm.newProposalChangedData.editableColumn.lookupReturn and KualiForm.newProposalChangedData.editableColumn.lookupReturn != 'null'}">
											<kul:lookup boClassName="${KualiForm.newProposalChangedData.editableColumn.lookupClass}" fieldConversions="${KualiForm.newProposalChangedData.editableColumn.lookupPkReturn}:newProposalChangedData.changedValue,${KualiForm.newProposalChangedData.editableColumn.lookupReturn}:newProposalChangedData.displayValue" anchor="${tabKey}"/>
										</c:if>
										</c:if>
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
					</div>
				</td>
			</tr>
			<tr>
		         <th align="right" valign="middle"><kul:htmlAttributeLabel attributeEntry="${proposalChangedDataAttributes.comments}" noColon="false" /></th>
		         <td align="left" valign="middle">
					<kul:htmlControlAttribute readOnly="false" property="newProposalChangedData.comments" attributeEntry="${proposalChangedDataAttributes.comments}" />
				</td>
		    </tr>
			<tr>
				<td align="center" colspan="2">
				<div align="center">
					<html:image property="methodToCall.addProposalChangedData.anchor${tabKey}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-edit1.gif' styleClass="tinybutton"/>
				</div>
		                </td>
			</tr>
		 </table>
        </kra:section>
        
       
        <c:if test="${fn:length(KualiForm.document.developmentProposalList[0].proposalChangeHistory) > 0}">
	        <br>
	        <h3>
	    		<span class="subhead-left">Proposal Change History</span>
	    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.coeus.propdev.impl.editable.ProposalChangedData" altText="help"/></span>
	        </h3>
	        
		    <table cellpadding=0 cellspacing=0 summary="">
		     	<tr>
	                  <th>Original Value</th>
	                  <th>New Value</th>
	                  <th>Change Date</th>
	                  <th>Changed By</th>
	                  <th>Explanation</th>
	            </tr>
	            
	                
	        	<c:forEach var="changedDataListByColumn" items="${KualiForm.document.developmentProposalList[0].proposalChangeHistory}" varStatus="status">
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
			                	<div align="left">&nbsp;</div>
			                </td>	
		   				</tr>
	        		</c:forEach>
	        	</c:forEach>
	      	   </table>
      	   
      	</c:if>

    </div> 
</kul:tab>

