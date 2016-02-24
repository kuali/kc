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

<%@ attribute name="protocolDocumentAttributes" required="true" type="java.util.Map" %>
<%@ attribute name="protocolAttributes" required="true" type="java.util.Map" %>
<%@ attribute name="action" required="true" %>
<%@ attribute name="className" required="true" %>
<%@ attribute name="displayLayStatementsRow" required="false" type="java.lang.Boolean" %>
<%@ attribute name="showProjectType" required="false" type="java.lang.Boolean" %>

<c:set var="textAreaFieldName" value="document.protocolList[0].title" />
<c:set var="nonEmpFlag" value="false" />
<c:set var="readOnly" value="${!KualiForm.protocolHelper.modifyGeneralInfo}" />


<kul:tab tabTitle="Required Fields for Saving Document" defaultOpen="true" tabErrorKey="document.protocolList[0].principalInvestigatorId,document.protocolList[0].protocolTypeCode,document.protocolList[0].title,document.protocolList[0].leadUnitNumber,document.protocolHelper.personId,document.protocolList[0].protocolTypeCode*,principalInvestigator*,protocolHelper.principalInvestigator*,document.protocolList[0].title*,protocolHelper.leadUnitNumber*,document.ProtocolTypeCode*,document.activityTypeCode*,document.title,document.protocolList[0].layStatement*" >
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Required Fields for Saving Document</span>
    		<span class="subhead-right"><kul:help parameterNamespace="KC-IACUC" parameterDetailType="Document" parameterName="protocolRequiredFieldsHelpUrl" altText="help"/></span>
        </h3>
		
		<table cellpadding=4 cellspacing=0 summary="">
            <tr>
            	<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.protocolTypeCode}" /></div></th>
                <td align="left" valign="center">
                    <kul:htmlControlAttribute property="document.protocolList[0].protocolTypeCode" readOnly="${readOnly}" attributeEntry="${protocolAttributes.protocolTypeCode}" />
                </td>
				
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.principalInvestigatorId}" /></div></th>
                <td align="left" valign="top">
                <div id="principalInvestigator.div" property="principalInvestigator" >
                        <c:if test="${empty KualiForm.protocolHelper.principalInvestigatorName}">                                                 
                            
                            ${kfunc:registerEditableProperty(KualiForm, "protocolHelper.principalInvestigatorName")}
                            <input type="hidden" name="protocolHelper.principalInvestigatorName" value=""/>
                                          
                        </c:if>
                        <c:if test="${empty KualiForm.protocolHelper.personId}">
                        
                        	${kfunc:registerEditableProperty(KualiForm, "protocolHelper.personId")}       					                	
                	    	<input type="hidden" name="protocolHelper.personId" value=""/>
                	    	
                	    </c:if>       
                	    <c:if test="${empty KualiForm.protocolHelper.rolodexId}">
                	    	
                	    	${kfunc:registerEditableProperty(KualiForm, "protocolHelper.rolodexId")}  				                	
                            <input type="hidden" name="protocolHelper.rolodexId" value=""/>
                                
                	    </c:if>   	
                	    <c:if test="${empty KualiForm.protocolHelper.principalInvestigatorId}">
                	    
                	    	${kfunc:registerEditableProperty(KualiForm, "protocolHelper.principalInvestigatorId")}     					                	
                            <input type="hidden" name="protocolHelper.principalInvestigatorId" value=""/>
                            
                            ${kfunc:registerEditableProperty(KualiForm, "document.protocolList[0].principalInvestigatorId")}               
                            <input type="hidden" name="document.protocolList[0].principalInvestigatorId" value=""/>
                                          
                	    </c:if>   	
                	    <table width="100%" border="0" style="border: medium none ;">
                	    <tbody>
						<c:if test="${empty KualiForm.document.protocolList[0].protocolId}">  
						<tr>
						<td style="border: medium none ;">        					
							<label> Employee Search</label>
						</td>
						<td width="40" valign="middle" style="border: medium none ;">
							<label>
							<kul:lookup boClassName="org.kuali.coeus.common.framework.person.KcPerson" 
	                         fieldConversions="personId:protocolHelper.personId,fullName:protocolHelper.principalInvestigatorName,unit.unitNumber:protocolHelper.lookupUnitNumber,unit.unitName:protocolHelper.lookupUnitName" 
	                         /></label>
                         </td>
                         </tr>
                        <tr>
                        <td style="border: medium none ;">   
							<label>Non-employee Search</label> 
						</td>
            	        <kul:checkErrors keyMatch="document.protocolList[0].principalInvestigatorId" auditMatch="document.protocolList[0].principalInvestigatorId"/>
                        <td width="40" valign="middle" style="border: medium none ;">	
							<label>
							<kul:lookup boClassName="org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex"
	                         fieldConversions="rolodexId:protocolHelper.rolodexId,unit.unitNumber:protocolHelper.lookupUnitNumber,unit.unitName:protocolHelper.lookupUnitName,fullName:protocolHelper.principalInvestigatorName"  
	                         />   
							</label>
	               			<c:if test="${hasErrors}">
                    	 		<kul:fieldShowErrorIcon />
                            </c:if>
						 </td>
                         </tr>	
						</c:if>

							
				    <tr>
                    <td style="border: medium none ;">  		
					<div id="principalInvestigatorName.div" >
					
                        <c:if test="${!empty KualiForm.protocolHelper.principalInvestigatorId}">
                        
                        ${kfunc:registerEditableProperty(KualiForm, "document.protocolList[0].principalInvestigatorId")}
                        <input type="hidden" name="document.protocolList[0].principalInvestigatorId" value="${KualiForm.protocolHelper.principalInvestigatorId}"/>
                                      
            				<c:choose>
							    <c:when test="${empty KualiForm.protocolHelper.principalInvestigatorName}">
	                    			<span style='color: red;'>not found</span><br>
	               				</c:when>
	                  			<c:otherwise>
										<c:out value="${KualiForm.protocolHelper.principalInvestigatorName}" />
								<br>
								</c:otherwise>  
							</c:choose>                        
                        </c:if>
                        
					</td>
                    </tr>  
                    </tbody>
                    </table>
                    </div>
                </td>
				</div>


            </tr>
            <tr>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.title}" /></div></th>
                <td align="left" valign="top">
                	<kul:htmlControlAttribute property="document.protocolList[0].title" attributeEntry="${protocolAttributes.title}" readOnly="${readOnly}" />
                </td>
                
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.leadUnitNumber}" /></div></th>            
                <td align="left" valign="center">
                
                <table width="100%" border="0" style="border: medium none ;">
                <tbody>
                <tr>
                <td style="border: medium none ;">   
                        
                    <c:if test="${empty KualiForm.document.protocolList[0].protocolId}">
                    	<kul:htmlControlAttribute property="protocolHelper.leadUnitNumber" 
						 attributeEntry="${protocolAttributes.leadUnitNumber}"  
						 onblur="ajaxLoad('getUnitName','protocolHelper.leadUnitNumber', 'protocolHelper.leadUnitName');"/> 

				</td>
                <td width="40" valign="middle" style="border: medium none ;">
            	        <kul:checkErrors keyMatch="document.protocolList[0].leadUnitNumber" auditMatch="document.protocolList[0].leadUnitNumber"/>
                        		 						                  
	                    <kul:lookup boClassName="org.kuali.coeus.common.framework.unit.Unit" 
	                     fieldConversions="unitNumber:protocolHelper.leadUnitNumber,unitName:protocolHelper.leadUnitName" />
                    
	                    <kul:directInquiry boClassName="org.kuali.coeus.common.framework.unit.Unit" 
	                     inquiryParameters="protocolHelper.leadUnitNumber:unitNumber" 
	                     anchor="${tabKey}" />
                    </label>
	               	<c:if test="${hasErrors}">
                    	 <kul:fieldShowErrorIcon />
                    </c:if>
                    <br>
                    </c:if>
                 
                 </td>
                 </tr>   
                 <tr>
                 <td style="border: medium none ;">  					
				
                    <div id="protocolHelper.leadUnitName.div" align="left">         
                        <c:if test="${!empty KualiForm.protocolHelper.leadUnitNumber}">
                            <c:if test="${!empty KualiForm.document.protocolList[0].protocolId}">
                                ${KualiForm.document.protocolList[0].leadUnit.unitName}
	                            <br/>
	                            - ${KualiForm.document.protocolList[0].leadUnit.unitNumber}
                            </c:if>
                            
            				<c:choose>
								<c:when test="${empty KualiForm.protocolHelper.leadUnitName}">
		                    		<span style='color: red;'>not found</span><br>
		               			</c:when>
		                  		<c:otherwise>
		                  		   <kul:htmlControlAttribute property="protocolHelper.leadUnitName"
	                                                         attributeEntry="${protocolAttributes.leadUnitName}" 
	                                                         readOnly="true" />       
	                         	</c:otherwise>  
							</c:choose>                        
                        </c:if>
					</div>
					
                    
                    </td>
                    </tr>  
                    </tbody>
                    </table>
                    
				</td>				
            </tr>
            
            <c:if test="${showProjectType}">
	            <tr>
	                <th>
	                	<div align="right">
		                    <kul:htmlAttributeLabel attributeEntry="${protocolAttributes.protocolProjectTypeCode}" />									
	                  	</div>
	                </th>
	                
	                <td width="26%">
	           				<kul:htmlControlAttribute property="document.protocolList[0].protocolProjectTypeCode" attributeEntry="${protocolAttributes.protocolProjectTypeCode}"  readOnly="${readOnly}" />
	                </td>
	                
					<th width="20%" class="infoline"><div align="center">&nbsp;</th>
					<th width="20%" class="infoline"><div align="center">&nbsp;</th>
	            </tr>
         	</c:if>   
            
         <c:if test="${displayLayStatementsRow}">
            <tr>
            	<th>
            		<div align="right">
            			<kul:htmlAttributeLabel attributeEntry="${protocolAttributes.layStatement1}" />
            		</div>
            	</th>
            	<td align="left" valign="top">
                	<kul:htmlControlAttribute property="document.protocolList[0].layStatement1" attributeEntry="${protocolAttributes.layStatement1}" readOnly="${readOnly}" />
                </td>
                
                <th>
            		<div align="right">
            			<kul:htmlAttributeLabel attributeEntry="${protocolAttributes.layStatement2}" />
            		</div>
            	</th>
            	<td align="left" valign="top">
                	<kul:htmlControlAttribute property="document.protocolList[0].layStatement2" attributeEntry="${protocolAttributes.layStatement2}" readOnly="${readOnly}" />
                </td>       
            </tr>
         </c:if>  
		</table>
	</div>	
</kul:tab>		
