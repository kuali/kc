<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2016 Kuali, Inc.
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

<c:set var="subAwardCloseoutAttributes" value="${DataDictionary.SubAwardCloseout.attributes}" />
<c:set var="action" value="subAwardCloseout" />
<c:set var="subAwardCloseouts" value="${KualiForm.document.subAwardList[0].subAwardCloseoutList}"/>
<c:set var="newSubAwardCloseout" value="${KualiForm.newSubAwardCloseout}" />

<script language="javascript" src="dwr/interface/SubAwardService.js"></script>

<kul:tab tabTitle="Closeout"  transparentBackground="false" defaultOpen="${KualiForm.document.subAwardList[0].defaultOpen}" tabErrorKey="newSubAwardCloseout.closeoutTypeCode*,newSubAwardCloseout.dateRequested*,newSubAwardCloseout.dateFollowup*,newSubAwardCloseout.dateReceived*,newSubAwardCloseout.comments*,document.subAwardList[0].subAwardCloseoutList*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> Closeout </span>
   	        <div align="right"><kul:help parameterNamespace="KC-SUBAWARD" parameterDetailType="Document" parameterName="subAwardCloseOutHelpUrl" altText="help"/></div>
        </h3>
        
        <table cellpadding=0 cellspacing=0 summary="">
       		<tbody class="addline">            
            <tr>
              <th><div align="left">&nbsp;</div></th> 
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardCloseoutAttributes.closeoutTypeCode}" /></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardCloseoutAttributes.dateRequested}" /></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardCloseoutAttributes.dateFollowup}" /></div></th>
               <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${subAwardCloseoutAttributes.dateReceived}" /></div></th>
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
     					<kul:htmlControlAttribute property="newSubAwardCloseout.closeoutTypeCode" readOnly="${readOnly}" attributeEntry="${subAwardCloseoutAttributes.closeoutTypeCode}" />           
   					</div> 
   				</td>
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardCloseout.dateRequested" readOnly="${readOnly}" 
     						attributeEntry="${subAwardCloseoutAttributes.dateRequested}" datePicker="true" 
     						onchange="defaultFollowupField(this, document.getElementsByName('newSubAwardCloseout.dateFollowup')[0])" 
     						onblur=  "defaultFollowupField(this, document.getElementsByName('newSubAwardCloseout.dateFollowup')[0])" />           
   					</div> 
   				</td>
   				<script language="javascript">
   				<!--
   					String.prototype.trim=function(){return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');};
   					function defaultFollowupField(dateRequestedField, dateFollowupField) {
   						var dateRequestedFieldValue = dateRequestedField.value.trim();
   						var dateFollowupFieldValue = dateFollowupField.value.trim();
   						if (dateRequestedFieldValue != '' && dateFollowupFieldValue == '') {
   							var dwrReply = {
   									callback:function(data) {
   										if ( data != null ) {				
   											dateFollowupField.value = data;
   										}
   									},
   									errorHandler:function( errorMessage ) {	
   										window.status = errorMessage;
   										fullNameElement.innerHTML = wrapError( "not found" );
   										rolodexIdElement.innerHTML = wrapError( "not found" );
   									}
   							};
   							SubAwardService.getCalculatedFollowupDateForAjaxCall(dateRequestedFieldValue, dwrReply);
   						}
   					}
   				-->
   				</script>
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardCloseout.dateFollowup" readOnly="${readOnly}" attributeEntry="${subAwardCloseoutAttributes.dateFollowup}" datePicker="true" />         
   					</div> 
   				</td>
   				<td><div align="center">
     					<kul:htmlControlAttribute property="newSubAwardCloseout.dateReceived" readOnly="${readOnly}" attributeEntry="${subAwardCloseoutAttributes.dateReceived}" datePicker="true" />         
   					</div> 
   				</td>   				
   				<td class="infoline" rowspan="2"><div align="center">
   					<c:if test="${readOnly!='true'}">
						<html:image property="methodToCall.addCloseouts.anchor${tabKey}" 
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
						            styleClass="tinybutton addButton"/>
					</c:if>
	                </div>
	            </td>   				
   			</tr> 
        	<tr>				
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardCloseoutAttributes.comments}" /></div></th>
                <td colspan="3">
                      <kul:htmlControlAttribute property="newSubAwardCloseout.comments" readOnly="${readOnly}" attributeEntry="${subAwardCloseoutAttributes.comments}" />
                </td>
            </tr>     
   			</c:if>
   			</tbody>
   			<c:forEach var="subAwardCloseouts" items="${KualiForm.document.subAwardList[0].subAwardCloseoutList}" varStatus="status">
		             <tr>
						<th width="5%" class="infoline" rowspan="2">
							<c:out value="${status.index+1}" />
						</th>	                
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardCloseoutList[${status.index}].closeoutTypeCode" attributeEntry="${subAwardCloseoutAttributes.closeoutTypeCode}" styleClass="amount"/>
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardCloseoutList[${status.index}].dateRequested" 
	                		attributeEntry="${subAwardCloseoutAttributes.dateRequested}"  datePicker="true"
	                		onchange="defaultFollowupField(this, document.getElementsByName('document.subAwardList[0].subAwardCloseoutList[${status.index}].dateFollowup')[0])" 
     						onblur=  "defaultFollowupField(this, document.getElementsByName('document.subAwardList[0].subAwardCloseoutList[${status.index}].dateFollowup')[0])" />                		
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardCloseoutList[${status.index}].dateFollowup" attributeEntry="${subAwardCloseoutAttributes.dateFollowup}"  datePicker="true"/>
						</div>
						</td>
						<td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardCloseoutList[${status.index}].dateReceived" attributeEntry="${subAwardCloseoutAttributes.dateReceived}"  datePicker="true"/>
						</div>
						</td>		               
						<td width="10%" valign="middle" rowspan="2">
						<div align="center">
						  <c:if test="${!readOnly}">
	                		<html:image property="methodToCall.deleteCloseout.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						  </c:if>
						  <c:if test="${readOnly}">&nbsp;</c:if>
						</div>
						</td>	
		            </tr>
		            
		            <tr>		            			
						<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${subAwardCloseoutAttributes.comments}" /></div></th>
                			<td colspan="3">
                      			<kul:htmlControlAttribute property="document.subAwardList[0].subAwardCloseoutList[${status.index}].comments" readOnly="${readOnly}" attributeEntry="${subAwardCloseoutAttributes.comments}" />
                			</td>
           		   </tr>
	        	</c:forEach>
        </table>
    </div>
</kul:tab>
