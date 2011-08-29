<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="coiDisclosureDocumentAttributes" value="${DataDictionary.CoiDisclosureDocument.attributes}" />
<c:set var="coiDisclosureAttributes" value="${DataDictionary.CoiDisclosure.attributes}" />
<c:set var="action" value="coiDisclosure" />
<c:set var="nonEmpFlag" value="false" />
<c:set var="readOnly" value="${!KualiForm.coiDisclosureHelper.modifyReporter}" scope = "request"/>

<kul:tab tabTitle="Reporter" defaultOpen="true" tabErrorKey="document.coiDisclosureList[0].principalInvestigatorId,document.coiDisclosureList[0].protocolTypeCode,document.coiDisclosureList[0].title,document.coiDisclosureList[0].leadUnitNumber,document.protocolHelper.personId,document.coiDisclosureList[0].protocolTypeCode*,principalInvestigator*,protocolHelper.principalInvestigator*,document.coiDisclosureList[0].title*,protocolHelper.leadUnitNumber*,document.ProtocolTypeCode*,document.activityTypeCode*,document.title" >
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Required Fields for Saving Document</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.irb.coiDisclosure.coiDisclosureType" altText="help"/></span>
        </h3>
		
		<table cellpadding=4 cellspacing=0 summary="">
            <tr>
            	<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${coiDisclosureAttributes.coiDisclosureTypeCode}" /></div></th>
                <td align="left" valign="center">
                    <kul:htmlControlAttribute property="document.coiDisclosureList[0].coiDisclosureTypeCode" readOnly="${readOnly}" attributeEntry="${coiDisclosureAttributes.coiDisclosureTypeCode}" />
                </td>
				
				<th><div align="right"><kul:htmlAttributeLabel attributeEntry="${coiDisclosureAttributes.principalInvestigatorId}" /></div></th>
                <td align="left" valign="top">
                <div id="principalInvestigator.div" property="principalInvestigator" >
                        <c:if test="${empty KualiForm.coiDisclosureHelper.principalInvestigatorName}">                                                 
                            
                            ${kfunc:registerEditableProperty(KualiForm, "coiDisclosureHelper.principalInvestigatorName")}
                            <input type="hidden" name="coiDisclosureHelper.principalInvestigatorName" value=""/>
                                          
                        </c:if>
                        <c:if test="${empty KualiForm.coiDisclosureHelper.personId}">
                        
                        	${kfunc:registerEditableProperty(KualiForm, "coiDisclosureHelper.personId")}       					                	
                	    	<input type="hidden" name="coiDisclosureHelper.personId" value=""/>
                	    	
                	    </c:if>       
                	    <c:if test="${empty KualiForm.coiDisclosureHelper.rolodexId}">
                	    	
                	    	${kfunc:registerEditableProperty(KualiForm, "coiDisclosureHelper.rolodexId")}  				                	
                            <input type="hidden" name="coiDisclosureHelper.rolodexId" value=""/>
                                
                	    </c:if>   	
                	    <c:if test="${empty KualiForm.coiDisclosureHelper.principalInvestigatorId}">
                	    
                	    	${kfunc:registerEditableProperty(KualiForm, "coiDisclosureHelper.principalInvestigatorId")}     					                	
                            <input type="hidden" name="coiDisclosureHelper.principalInvestigatorId" value=""/>
                            
                            ${kfunc:registerEditableProperty(KualiForm, "document.coiDisclosureList[0].principalInvestigatorId")}               
                            <input type="hidden" name="document.coiDisclosureList[0].principalInvestigatorId" value=""/>
                                          
                	    </c:if>   	
                	    <table width="100%" border="0" style="border: medium none ;">
                	    <tbody>
						<c:if test="${empty KualiForm.document.coiDisclosureList[0].coiDisclosureId}">  
						<tr>
						<td style="border: medium none ;">        					
							<label> Employee Search</label>
						</td>
						<td width="40" valign="middle" style="border: medium none ;">
							<label>
							<kul:lookup boClassName="org.kuali.kra.bo.KcPerson" 
	                         fieldConversions="personId:coiDisclosureHelper.personId,fullName:coiDisclosureHelper.principalInvestigatorName,unit.unitNumber:coiDisclosureHelper.lookupUnitNumber,unit.unitName:coiDisclosureHelper.lookupUnitName" 
	                         /></label>
                         </td>
                         </tr>
                        <tr>
                        <td style="border: medium none ;">   
							<label>Non-employee Search</label> 
						</td>
            	        <kul:checkErrors keyMatch="document.coiDisclosureList[0].principalInvestigatorId" auditMatch="document.coiDisclosureList[0].principalInvestigatorId"/>
                        <td width="40" valign="middle" style="border: medium none ;">	
							<label>
							<kul:lookup boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" 
	                         fieldConversions="rolodexId:coiDisclosureHelper.rolodexId,unit.unitNumber:coiDisclosureHelper.lookupUnitNumber,unit.unitName:coiDisclosureHelper.lookupUnitName,fullName:coiDisclosureHelper.principalInvestigatorName"  
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
                        <c:if test="${!empty KualiForm.coiDisclosureHelper.principalInvestigatorId}">
                        
                        ${kfunc:registerEditableProperty(KualiForm, "document.coiDisclosureList[0].principalInvestigatorId")}
                        <input type="hidden" name="document.coiDisclosureList[0].principalInvestigatorId" value="${KualiForm.coiDisclosureHelper.principalInvestigatorId}"/>
                                      
            				<c:choose>
							    <c:when test="${empty KualiForm.coiDisclosureHelper.principalInvestigatorName}">
	                    			<span style='color: red;'>not found</span><br>
	               				</c:when>
	                  			<c:otherwise>
										<c:out value="${KualiForm.coiDisclosureHelper.principalInvestigatorName}" />
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
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${coiDisclosureAttributes.title}" /></div></th>
                <td align="left" valign="top">
                	<kul:htmlControlAttribute property="document.coiDisclosureList[0].title" attributeEntry="${coiDisclosureAttributes.title}" readOnly="${readOnly}" />
                </td>
                
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${coiDisclosureAttributes.leadUnitNumber}" /></div></th>            
                <td align="left" valign="center">
                
                <table width="100%" border="0" style="border: medium none ;">
                <tbody>
                <tr>
                <td style="border: medium none ;">   
                        
                    <c:if test="${empty KualiForm.document.coiDisclosureList[0].coiDisclosureId}">
                    	<kul:htmlControlAttribute property="coiDisclosureHelper.leadUnitNumber" 
						 attributeEntry="${coiDisclosureAttributes.leadUnitNumber}"  
						 onblur="ajaxLoad('getUnitName','coiDisclosureHelper.leadUnitNumber', 'coiDisclosureHelper.leadUnitName');"/> 
						<%--   onblur="loadUnitNameTo('coiDisclosureHelper.leadUnitNumber','coiDisclosureHelper.leadUnitName');"/> --%>
				
				</td>
                <td width="40" valign="middle" style="border: medium none ;">
            	        <kul:checkErrors keyMatch="document.coiDisclosureList[0].leadUnitNumber" auditMatch="document.coiDisclosureList[0].leadUnitNumber"/>
                        		 						                  
	                    <kul:lookup boClassName="org.kuali.kra.bo.Unit" 
	                     fieldConversions="unitNumber:coiDisclosureHelper.leadUnitNumber,unitName:coiDisclosureHelper.leadUnitName" />
                    
	                    <kul:directInquiry boClassName="org.kuali.kra.bo.Unit" 
	                     inquiryParameters="coiDisclosureHelper.leadUnitNumber:unitNumber" 
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
				
                    <div id="coiDisclosureHelper.leadUnitName.div" align="left">         
                        <c:if test="${!empty KualiForm.coiDisclosureHelper.leadUnitNumber}">
                            <c:if test="${!empty KualiForm.document.coiDisclosureList[0].coiDisclosureId}">
                                ${KualiForm.document.coiDisclosureList[0].leadUnit.unitName}
	                            <br/>
	                            - ${KualiForm.document.coiDisclosureList[0].leadUnit.unitNumber}
                            </c:if>
                            
            				<c:choose>
								<c:when test="${empty KualiForm.coiDisclosureHelper.leadUnitName}">
		                    		<span style='color: red;'>not found</span><br>
		               			</c:when>
		                  		<c:otherwise>
		                  		   <kul:htmlControlAttribute property="coiDisclosureHelper.leadUnitName"
	                                                         attributeEntry="${coiDisclosureAttributes.leadUnitName}" 
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
		</table>
	</div>	
</kul:tab>		