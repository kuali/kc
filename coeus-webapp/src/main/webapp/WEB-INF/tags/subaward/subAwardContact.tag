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
<script type='text/javascript' src='dwr/interface/RolodexService.js'></script>
<!-- KRACOEUS-5477 - the engine can only be included once or it causes errors to the user -->
<script type='text/javascript' src='dwr/util.js'></script>

<c:set var="subAwardContactAttributes" value="${DataDictionary.SubAwardContact.attributes}" />
<c:set var="action" value="subAwardHome" />
<c:set var="subAwardContacts" value="${KualiForm.document.subAwardList[0].subAwardContactsList}"/>
<c:set var="newSubAwardContact" value="${KualiForm.newSubAwardContact}" />


<kul:tab tabTitle="Contacts"  transparentBackground="false" defaultOpen="${KualiForm.document.subAwardList[0].defaultOpen}" tabErrorKey="newSubAwardContact.contactTypeCode*,newSubAwardContact.rolodex.fullName*" auditCluster="contactsAuditErrors" tabAuditKey="newSubAwardContact.rolodex.fullName*" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> Contacts </span>
    	    <div align="right"><kul:help parameterNamespace="KC-SUBAWARD" parameterDetailType="Document" parameterName="subAwardContactHelpUrl" altText="help"/></div>
      </h3>
        
   <table id="subAward-contacts-table" cellpadding="0" cellspacing="0">
        <tbody class="addline">            
        <tr>
		  <th scope="row" width="5%">&nbsp;</th>
		  <th width="15%">* Person or Organization</th>
		  <th width="20%">* Project Role</div></th>
		  <th width="15%">Office Phone</th>
		  <th width="15%">Email</th>
		  <th width="15%"><div align="center">Actions</div></th>				
		</tr>		
         <c:if test="${!readOnly}">
	     <tr>
				<th class="infoline" scope="row">Add</th>
				<td nowrap class="grid" class="infoline">
                    Non-employee ID:                      
					<kul:htmlControlAttribute property="newSubAwardContact.rolodex.rolodexId" 
      								attributeEntry="${subAwardContactAttributes.rolodexName}" 
      							    onblur="loadRolodexInfo('newSubAwardContact.rolodex.rolodexId',
	                               							'org.fullName.div',
	                	        				  			'org.phoneNumber',
           	        							  			'org.emailAddress',
           	        							  			'rolodexId');"
           	        							  			readOnly="${readOnly}"/>  
           	        							  			
  					<c:if test="${!readOnly}">					
  						<kul:lookup boClassName="org.kuali.coeus.common.framework.rolodex.Rolodex" fieldConversions="rolodexId:newSubAwardContact.rolodexId" 
  									anchor="${tabKey}" lookupParameters="newSubAwardContact.rolodexId:rolodexId"/>
  					</c:if>
  					
  		            <c:if test="${readOnly}">
					     <html:hidden styleId ="org.fullName" property="newSubAwardContact.rolodex.fullName" />
				    </c:if>
				
				    ${kfunc:registerEditableProperty(KualiForm, "newSubAwardContact.rolodexId")}
				    <html:hidden styleId ="rolodexId" property="newSubAwardContact.rolodexId" />	
	              
	               <div id="org.fullName.div">&nbsp; <c:if
					test="${!empty KualiForm.newSubAwardContact.rolodex}">
					<c:choose>
						<c:when
							test="${empty KualiForm.newSubAwardContact.rolodex}">
							<span style='color: red;'>not found</span>
						</c:when>
						<c:otherwise>
							 <c:choose>
	                		    <c:when test="${empty KualiForm.newSubAwardContact.rolodex.fullName}">
	                		       ${KualiForm.newSubAwardContact.rolodex.organization}&nbsp;
	                		    </c:when>
	                		    <c:otherwise>
	                	           ${KualiForm.newSubAwardContact.rolodex.fullName}&nbsp;
	                		    </c:otherwise>
	                		</c:choose>
						
							<%--
							 <c:out value="${KualiForm.newSubAwardContact.rolodex.organization}" />
							 --%>
						</c:otherwise>
					</c:choose>
				</c:if></div>
		    </td>
				  
				<td class="infoline" style="font-size: 80%">
	        		<div align="center">
		        		<kul:htmlControlAttribute property="newSubAwardContact.contactTypeCode" 
	                									attributeEntry="${subAwardContactAttributes.contactTypeCode}" />
					</div>
	        	</td>
	        	<td id="org.phoneNumber" class="infoline">
	        	  <div align="center">
	        		<c:out value="${newSubAwardContact.rolodex.phoneNumber}" />
	        	  </div>
	        	</td>
	        	<td id="org.emailAddress" class="infoline">
	        	  <div align="center">
	        		<c:out value="${newSubAwardContact.rolodex.emailAddress}" />
	        	  </div>
	        	</td>
				<td class="infoline" rowspan="1"><div align="center">
   						<c:if test="${readOnly!='true'}">
						<html:image property="methodToCall.addContacts.anchor${tabKey}" 
						            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' 
						            styleClass="tinybutton addButton"/>
						</c:if>
	                </div>
	             </td>	  			  
		  </tr>
		  </c:if>  
		  </tbody>
		  <c:forEach var="subAwardContacts" items="${KualiForm.document.subAwardList[0].subAwardContactsList}" varStatus="status">		  
		              <tr>
						<th width="5%" class="infoline" rowspan="2">
							<c:out value="${status.index+1}" />
							<input type="hidden" name="subAward_contact.identifier_${status.index}" value="${subAwardContacts.rolodexId}" />
						</th>	                
		                <td width="9%" valign="middle">
						<div align="center">     	                		
	                		<c:choose>
	                		    <c:when test="${empty subAwardContacts.rolodex.fullName}">
	                		       ${subAwardContacts.rolodex.organization}&nbsp;
	                		    </c:when>
	                		    <c:otherwise>
	                	           ${subAwardContacts.rolodex.fullName}&nbsp;
	                		    </c:otherwise>
	                		</c:choose>	                		
						<kul:directInquiry boClassName="org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex" inquiryParameters="subAward_contact.identifier_${status.index}:rolodexId" anchor="${tabKey}" />
						</div>
						</td>
		                <td width="9%" valign="middle">
						<div align="center">
	                		<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].contactTypeCode" attributeEntry="${subAwardContactAttributes.contactTypeCode}"  datePicker="true"/>                		
						</div>
						</td>	
						<td width="9%" valign="middle">
						<div align="center">
							<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].rolodex.phoneNumber" attributeEntry="${subAwardContactAttributes.phoneNumber}" readOnly="true" />				
						</div>
						</td>
						<td width="9%" valign="middle">
						<div align="center">
							<kul:htmlControlAttribute property="document.subAwardList[0].subAwardContactsList[${status.index}].rolodex.emailAddress" attributeEntry="${subAwardContactAttributes.emailAddress}" readOnly="true" />				
						</div>
						</td>               
						<td width="10%" valign="middle" rowspan="1">
						<div align="center">
						  <c:if test="${!readOnly}">
	                		<html:image property="methodToCall.deleteContact.line${status.index}.anchor${currentTabIndex}"
								src='${ConfigProperties.kra.externalizable.images.url}tinybutton-delete1.gif' styleClass="tinybutton"/>
						  </c:if>
						  <c:if test="${readOnly}">&nbsp;</c:if>
						</div>
						</td>	
		            </tr>		           	            
		            <tr>
       </tr>     
	 </c:forEach> 
		   </table>
  </div>
</kul:tab>
