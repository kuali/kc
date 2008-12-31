<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.Protocol.attributes}" />
<c:set var="textAreaFieldName" value="document.title" />
<c:set var="action" value="protocol" />
<c:set var="nonEmpFlag" value="false" />
<c:set var="className" value="org.kuali.kra.protocol.document.ProtocolDocument" />

<kul:tab tabTitle="Required Fields for Saving Document" defaultOpen="true" tabErrorKey="document.protocol.protocolTypeCode*,principalInvestigator*,document.protocol.principalInvestigatorName*,document.protocol.title*,document.protocol.principalInvestigatorId*,document.protocol.leadUnitNumber*, document.currentAwardNumber*,document.continuedFrom,document.sponsorCode*,document.ProtocolTypeCode*,document.requestedStartDateInitial*,document.ownedByUnit*,document.requestedEndDateInitial*,document.activityTypeCode*,document.title" auditCluster="requiredFieldsAuditErrors" tabAuditKey="document.title" useRiceAuditMode="true">
	<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Required Fields for Saving Document</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.protocol.bo.ProtocolType" altText="help"/></span>
        </h3>
		
		<table cellpadding=4 cellspacing=0 summary="">
            <tr>
            	<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.protocolTypeCode}" /></div></th>
                <td align="left" valign="top">
                      <kra:kraControlAttribute property="document.protocol.protocolTypeCode" readOnly="${readOnly}" attributeEntry="${protocolAttributes.protocolTypeCode}" />
                </td>
				
				<th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.principalInvestigatorId}" /></div></th>
                <td align="left" valign="top">
                <div id="principalInvestigator.div" property="principalInvestigator" >
                               	
						<c:if test="${empty KualiForm.document.protocol.protocolId}">          					
							<label> Employee Search</label>
							<label>
							<kul:lookup boClassName="org.kuali.kra.bo.Person" 
	                         fieldConversions="personId:document.protocol.personId,fullName:document.protocol.principalInvestigatorName,homeUnitRef.unitNumber:document.protocol.lookupUnitNumber" 
	                         /></label>
	                        <kul:directInquiry boClassName="org.kuali.kra.bo.Person" 
	                         inquiryParameters="document.protocol.principalInvestigatorId:personId,document.protocol.principalInvestigatorName:fullName" 
	                         anchor="${tabKey}" />
	                        <br>
							<label>Non-employee Search</label> 
							<label>
							<kul:lookup boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" 
	                         fieldConversions="rolodexId:document.protocol.rolodexId,unit.unitNumber:document.protocol.lookupUnitNumber,fullName:document.protocol.principalInvestigatorName"  
	                         />
	                        </label>
	                        <kul:directInquiry boClassName="org.kuali.kra.bo.NonOrganizationalRolodex" 
	                         inquiryParameters="document.protocol.principalInvestigatorId:rolodexId" 
	                         anchor="${tabKey}" />						
							<br/>
							</label>
						</c:if>
					<br>
									
					<div id="principalInvestigatorName.div" >
                        <c:if test="${!empty KualiForm.document.protocol.principalInvestigatorId}">
            				<c:choose>
							    <c:when test="${empty KualiForm.document.protocol.principalInvestigatorName}">
	                    			<span style='color: red;'>not found</span><br>
	               				</c:when>
	                  			<c:otherwise>
										<c:out value="${KualiForm.document.protocol.principalInvestigatorName}" /><br>
								</c:otherwise>  
							</c:choose>                        
                        </c:if>
					</div>
                </td>
				</div>


            </tr>
            <tr>
                <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.title}" /></div></th>
                <td align="left" valign="top">
                	<kul:htmlControlAttribute property="document.protocol.title" attributeEntry="${protocolAttributes.title}" />
                    <kra:expandedTextArea textAreaFieldName="${textAreaFieldName}" action="${action}" textAreaLabel="${protocolAttributes.title.label}" />
                </td>
                
                <th><div align="center"><kul:htmlAttributeLabel attributeEntry="${protocolAttributes.leadUnitNumber}" /></div></th>            
                <td align="left" valign="top">
                    <c:if test="${empty KualiForm.document.protocol.protocolId}">
                    	<kul:htmlControlAttribute property="document.protocol.leadUnitNumber" 
						 attributeEntry="${protocolAttributes.leadUnitNumber}"  
						 onblur="loadUnitNameTo('document.protocol.leadUnitNumber','document.protocol.leadUnitName');"/> 
						 						                  
	                    <kul:lookup boClassName="org.kuali.kra.bo.Unit" 
	                     fieldConversions="unitNumber:document.protocol.leadUnitNumber,unitName:document.protocol.leadUnitName" />
                    
	                    <kul:directInquiry boClassName="org.kuali.kra.bo.Unit" 
	                     inquiryParameters="document.protocol.leadUnitNumber:unitNumber" 
	                     anchor="${tabKey}" />
                    </label>
                    <br>
                    </c:if>
                    
                  					
				
                    <div id="document.protocol.leadUnitName.div" align="left">                    
                        <c:if test="${!empty KualiForm.document.protocol.leadUnitNumber}">
            				<c:choose>
							<c:when test="${empty KualiForm.document.protocol.leadUnitName}">
	                    		<span style='color: red;'>not found</span><br>
	               			</c:when>
	                  		<c:otherwise>
								<c:out value="${KualiForm.document.protocol.leadUnitName}" /> 
							</c:otherwise>  
							</c:choose>                        
                        </c:if>
					</div>
					<c:if test="${!empty KualiForm.document.protocol.protocolId && !empty KualiForm.document.protocol.leadUnitNumber}">
                       - ${KualiForm.document.protocol.leadUnit.unitNumber}
                    </c:if>
				</td>
                
                
				
                
				
				
            </tr>
		</table>
	</div>	
</kul:tab>		