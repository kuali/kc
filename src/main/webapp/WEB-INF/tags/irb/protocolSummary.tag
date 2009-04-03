<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.Protocol.attributes}" />
<c:set var="action" value="protocolActions" />
<c:set var="protocol" value="${KualiForm.document.protocolList[0]}" />
<c:set var="protocolPersonAttributes" value="${DataDictionary.ProtocolPerson.attributes}" />
<c:set var="protocolUnitAttributes" value="${DataDictionary.ProtocolUnit.attributes}" />
<c:set var="protocolResearchAreaAttributes" value="${DataDictionary.ProtocolResearchArea.attributes}" />
<c:set var="researchAreaAttributes" value="${DataDictionary.ResearchArea.attributes}" />

    	<kul:innerTab parentTab="Summary, History, & Print" defaultOpen="false" tabTitle="View Summary (Notified Committee dd/mm/yyyy)">
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <th style="text-align:right; width:135px;">
                        Protocol Number:
                    </th>
                    <td>
                       ${protocol.protocolNumber}
                    </td>
                    <th style="text-align:right">
                        Application Date:
                    </th>
                    <td>
                    	<fmt:formatDate value="${protocol.applicationDate}" pattern="MM/dd/yyyy" />                        
                    </td>
                    <th rowspan="5">
                        <a href="#"><img src="../images/tinybutton-previous3.gif" alt="close" width="70" height="15" border="0" style="padding:2px;" /></a><br />
                        <a href="#"><img src="../images/tinybutton-next3.gif" alt="close" width="70" height="15" border="0" style="padding:2px;" /></a>
                    </th>
                </tr>
                <tr>
                    <th style="text-align:right">
                        Approval Date:
                    </th>
                    <td>
                    	<fmt:formatDate value="${protocol.approvalDate}" pattern="MM/dd/yyyy" />                       
                    </td>
                    <th style="text-align:right">
                        Expiration Date:
                    </th>
                    <td>
                    	<fmt:formatDate value="${protocol.expirationDate}" pattern="MM/dd/yyyy" />                          
                    </td>
                </tr>
                <tr>
                    <th style="text-align:right">
                        Status:
                    </th>
                    <td>
                        ${protocol.protocolStatus.description}
                    </td>
                    <th style="text-align:right">&nbsp;
                        Type:
                    </th>
                    <td>
                    	${protocol.protocolType.description}
                    </td>
                </tr>
                <tr>
                    <th style="text-align:right; height:50px;">
                        Title:
                    </th>
                    <td colspan="3" style="text-align:left; vertical-align:top;">
                        ${protocol.title}
                    </td>

                </tr>
            </table>
    		
            <!-- Investigators -->
                <table  cellpadding="0" cellspacing="0"  summary="">
                    <tr>
                        <td class="tab-subhead" colspan="4">
                           Personnel
                      </td>
                    </tr>
                        <tr>
                            <th>Name</th>
                            <th>Role</th>
                            <th>Affiliation </th>
                            <th>Unit(s)</th>
                        </tr>   		
			        	<c:forEach var="protocolPerson" items="${protocol.protocolPersons}" varStatus="status">
				             <tr>
			                          <td><kul:htmlControlAttribute property="document.protocolList[0].protocolPersons[${status.index}].personName" 
				                									readOnly="true"	attributeEntry="${protocolPersonAttributes.personName}"  /></td>
			                          <td><kul:htmlControlAttribute property="document.protocolList[0].protocolPersons[${status.index}].protocolPersonRole.description" 
				                									readOnly="true"	attributeEntry="${protocolPersonAttributes.protocolPersonRole.description}"  /></td>
			                          <td><kul:htmlControlAttribute property="document.protocolList[0].protocolPersons[${status.index}].affiliationType.description" 
				                									readOnly="true"	attributeEntry="${protocolPersonAttributes.affiliationType.description}"  /></td>
			                          <td> 
								        	<c:forEach var="protocolUnit" items="${protocolPerson.protocolUnits}" varStatus="it">						        
								            	<kul:htmlControlAttribute property="document.protocolList[0].protocolPersons[${status.index}].protocolUnits[${it.index}].protocolUnitsId" 
									                							readOnly="true"	attributeEntry="${protocolUnitAttributes.protocolUnitsId}"  />	
									        &nbsp;:&nbsp;	
									        <kul:htmlControlAttribute property="document.protocolList[0].protocolPersons[${status.index}].protocolUnits[${it.index}].unitName" 
									                							readOnly="true"	attributeEntry="${protocolUnitAttributes.unitName}"  />	        										                 
								        	</c:forEach>                                                    
			                          </td>
				            </tr>     
			        	</c:forEach>
    			</table>
    			
	          <!-- Areas of Research -->
              <table  cellpadding="0" cellspacing="0"  summary="">
                    <tr>
                        <td class="tab-subhead" >Areas of Research</td>
                    </tr>
			        	<c:forEach var="protocolPerson" items="${protocol.protocolResearchAreas}" varStatus="status">
				             <tr>
			                     <td>
			                     	<kul:htmlControlAttribute property="document.protocolList[0].protocolResearchAreas[${status.index}].researchAreas.researchAreaCode" 
				                									readOnly="true"	attributeEntry="${researchAreaAttributes.researchAreas.researchAreaCode}"  />
				                	:&nbsp;
				                	<kul:htmlControlAttribute property="document.protocolList[0].protocolResearchAreas[${status.index}].researchAreas.description" 
				                									readOnly="true"	attributeEntry="${researchAreaAttributes.researchAreas.description}"  />
				                 </td>
				             </tr>     
			        	</c:forEach>
			  </table>
    			
    	</kul:innerTab>

