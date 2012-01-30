<%--
 Copyright 2005-2010 The Kuali Foundation
 
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
<script type='text/javascript' src='dwr/interface/RolodexService.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>

<c:set var="subAwardContactAttributes" value="${DataDictionary.SubAwardContact.attributes}" />
<c:set var="action" value="subAwardHome" />
<c:set var="subAwardContacts" value="${KualiForm.document.subAwardList[0].subAwardContactsList}"/>
<c:set var="newSubAwardContact" value="${KualiForm.newSubAwardContact}" />


<kul:tab tabTitle="Contacts"  transparentBackground="false" defaultOpen="true" tabErrorKey="newSubAwardContact.contactTypeCode*,newSubAwardContact.rolodex.firstName*" auditCluster="requiredFieldsAuditErrors" tabAuditKey="" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left"> Contacts </span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.bo.SubAwardContact" altText="help"/></span>
        </h3>
        
   <table id="subAward-contacts-table" cellpadding="0" cellspacing="0">
        <tr>
		  <th scope="row" width="5%">&nbsp;</th>
		  <th width="15%">* Person or Organization</th>
		  <th width="20%">* Project Role</div></th>
		  <th width="15%">Office Phone</th>
		  <th width="15%">Email</th>
		  <th width="15%"><div align="center">Actions</div></th>				
				
         <c:if test="${!readOnly}">
	     <tr>
				<th class="infoline" scope="row">Add</th>
				<td nowrap class="grid" class="infoline">
                    Non-employee ID:                      
					<kul:htmlControlAttribute property="newSubAwardContact.rolodex.fullName" 
      								attributeEntry="${subAwardContactAttributes.rolodexName}" 
      							    onblur="loadRolodexInfo('newSubAwardContact.rolodex.fullName',
	                               							'org.fullName.div',
	                	        				  			'org.phoneNumber',
           	        							  			'org.emailAddress',
           	        							  			'rolodexId');"
           	        							  			readOnly="${readOnly}"/>  
           	        							  			
  					<c:if test="${!readOnly}">
  					
  						<kul:lookup boClassName="org.kuali.kra.bo.Rolodex" fieldConversions="rolodexId:newSubAwardContact.rolodexId" 
  									anchor="${tabKey}" lookupParameters="newSubAwardContact.rolodexId:rolodexId"/>
  					</c:if>
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
							<c:out
								value="${KualiForm.newSubAwardContact.rolodex.organization}" />
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
						            styleClass="tinybutton"/>
						</c:if>
	                </div>
	             </td>	  			  
		  </tr>
		  
		  
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
						<kul:directInquiry boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" inquiryParameters="subAward_contact.identifier_${status.index}:rolodexId" anchor="${tabKey}" />
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
