<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="negotiationAttributes" value="${DataDictionary.Negotiation.attributes}" />
<c:set var="negotiationUnassociatedDetailAttributes" value="${DataDictionary.NegotiationUnassociatedDetail.attributes}" />
<c:set var="action" value="negotiationNegotiation" />
<c:set var="className" value="org.kuali.kra.negotiations.document.NegotiationDocument" />
<c:set var="readOnly" value="${not KualiForm.editingMode['modify']}"/>
<c:set var="medusaLink" value="${KualiForm.methodToCall eq 'medusa'}"/>
<script type='text/javascript' src='dwr/interface/KraPersonService.js'></script>

<kul:tab tabTitle="Negotiation" defaultOpen="${!medusaLink}" 
					tabErrorKey="document.negotiationList[0].negotiation*,document.negotiationList[0].negotiator*,document.negotiationList[0].anticipatedAwardDate,document.negotiationList[0].documentFolder,document.negotiationList[0].associatedDocumentId,document.negotiationList[0].unAssociatedDetail*" 
					auditCluster="requiredFieldsAuditErrors" tabAuditKey="document.title" useRiceAuditMode="true">
					
	<div class="tab-container" align="center">
    	<h3>
    	    <c:choose><c:when test="${empty KualiForm.document.negotiationList[0].negotiationId}">
    		<span class="subhead-left">New Negotiation</span>
    		</c:when><c:otherwise>
    		<span class="subhead-left">Negotiation ${KualiForm.document.negotiationList[0].negotiationId}</span>
    		</c:otherwise></c:choose>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.negotiations.bo.Negotiation" altText="help"/></span>
        </h3>
		
		<table cellpadding="4" cellspacing="0" summary="">
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${negotiationAttributes.negotiationStatusId}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.negotiationList[0].negotiationStatusId" 
                		attributeEntry="${negotiationAttributes.negotiationStatusId}" readOnly="${readOnly}"
                		onchange="manageStatusEndDate(true);"/>
                </td>
                <th><div align="right">Negotiation Dates:</div></th>
                <td align="left" valign="middle">
                	Start: <kul:htmlControlAttribute property="document.negotiationList[0].negotiationStartDate" attributeEntry="${negotiationAttributes.negotiationStartDate}" readOnly="${readOnly}"/>
                	End: <kul:htmlControlAttribute property="document.negotiationList[0].negotiationEndDate" attributeEntry="${negotiationAttributes.negotiationEndDate}" readOnly="${readOnly}"/>
                </td>
            </tr>
            <script language="javascript">
            	<!--
            	${KualiForm.statusRelatedJavascript}
            	-->
            </script>
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${negotiationAttributes.negotiatorPersonId}" /></div></th>
                <td>
                	<c:if test="${!readOnly}">
	                    <html:text property="document.negotiationList[0].negotiatorUserName" 
							onblur="loadContactPersonName('document.negotiationList[0].negotiatorUserName',
										'negotiator.fullName',
										'na',
										'na',
										'na',
										'na');"
	                    	readonly="${readOnly}"/>
						<kul:checkErrors keyMatch="document.negotiationList[0].negotiatorUserName" auditMatch="document.negotiationList[0].negotiatorUserName"/>
					</c:if>  
            		<c:if test="${hasErrors}">
	 					<kul:fieldShowErrorIcon />
  					</c:if>                    	
                    <c:if test="${!readOnly}">
                        ${kfunc:registerEditableProperty(KualiForm, "document.negotiationList[0].negotiatorPersonId")}
	                	<kul:lookup boClassName="org.kuali.kra.bo.KcPerson" 
	                                fieldConversions="userName:document.negotiationList[0].negotiatorUserName" />
                    </c:if>
                    <br/><span id="negotiator.fullName"><c:out value="${KualiForm.document.negotiationList[0].negotiator.fullName}"/>&nbsp;</span>
                </td>
                <th><div align="right">Negotiation Age in Days:</div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.negotiationList[0].negotiationAge" attributeEntry="${negotiationAttributes.negotiationAge}" readOnly="true"/>
                </td>
            </tr>
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${negotiationAttributes.negotiationAgreementTypeId}" /></div></th>
                <td>
                	<kul:htmlControlAttribute property="document.negotiationList[0].negotiationAgreementTypeId" attributeEntry="${negotiationAttributes.negotiationAgreementTypeId}" readOnly="${readOnly}"/>
                </td>
                <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${negotiationAttributes.anticipatedAwardDate}" /></div></th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.negotiationList[0].anticipatedAwardDate" attributeEntry="${negotiationAttributes.anticipatedAwardDate}" readOnly="${readOnly}"/>
                </td>
            </tr>  
            <tr>
		        <th><div align="right"><kul:htmlAttributeLabel attributeEntry="${negotiationAttributes.documentFolder}" /></div></th>
                <td colspan="3">
                	<kul:htmlControlAttribute property="document.negotiationList[0].documentFolder" attributeEntry="${negotiationAttributes.documentFolder}" readOnly="${readOnly}"/>
                </td>
            </tr>  
            
		</table>
		<h3>Negotiation Attributes:</h3>
		<table cellpadding="4" cellspacing="0" summary="">
            <tr>
		        <th><div align="right">
		        	<kul:htmlAttributeLabel attributeEntry="${negotiationAttributes.negotiationAssociationTypeId}" />
		        </div></th>
                <td>
                
                	<kul:htmlControlAttribute property="document.negotiationList[0].negotiationAssociationTypeId" 
                		attributeEntry="${negotiationAttributes.negotiationAssociationTypeId}" readOnly="${readOnly}"
                		onchange="getElementsByName('methodToCall.changeAssociation')[0].click();"/>                		
                		<html:image property="methodToCall.changeAssociation"
					src='${ConfigProperties.kra.externalizable.images.url}tinybutton-add1.gif' styleClass="tinybutton" style="display:none"/>
					
                </td>
                <th>
                	<div align="right">
                		<c:choose>
                			<c:when test="${KualiForm.displayAward}">
					        	Award: 
					      	</c:when>
					      	<c:when test="${KualiForm.displaySubAward}">
					        	Sub Award: 
					      	</c:when>
					      	<c:when test="${KualiForm.displayProposalLog}">
					        	Proposal Log: 
					      	</c:when>
					      	<c:when test="${KualiForm.displayInstitutionalProposal}">
					        	Institutional Proposal: 
					      	</c:when>
					      	<c:when test="${KualiForm.displayUnAssociatedDetail}">
					        	<kul:htmlAttributeLabel attributeEntry="${negotiationAttributes.associatedDocumentId}" /> 
					      	</c:when>
					      	<c:otherwise>
					        	<kul:htmlAttributeLabel attributeEntry="${negotiationAttributes.associatedDocumentId}" />
					      	</c:otherwise>
					    </c:choose>
                	</div>
                </th>
                <td align="left" valign="middle">
                	<kul:htmlControlAttribute property="document.negotiationList[0].associatedDocumentId" attributeEntry="${negotiationAttributes.associatedDocumentId}" readOnly="true"/>
                	<c:if test="${!readOnly}">
                		${kfunc:registerEditableProperty(KualiForm, "document.negotiationList[0].associatedDocumentId")}
                		<c:if test="${KualiForm.dispayChangeAssociatedDocumentWarning }">
                			${KualiForm.dispayChangeAssociatedDocumentWarningMessage }
                		</c:if>
                		
		                	<c:choose>
	                			<c:when test="${KualiForm.displayAward}">
	                				<kul:lookup boClassName="org.kuali.kra.award.home.Award" 
	                					fieldConversions="awardNumber:document.negotiationList[0].associatedDocumentId" />
						      	</c:when>
						      	<c:when test="${KualiForm.displaySubAward}">
						      		<kul:lookup boClassName="org.kuali.kra.subaward.bo.SubAward" 
						        		fieldConversions="subAwardId:document.negotiationList[0].associatedDocumentId" /> 
						      	</c:when>
						      	<c:when test="${KualiForm.displayProposalLog}">
						        	<kul:lookup boClassName="org.kuali.kra.institutionalproposal.proposallog.ProposalLog" 
						        		fieldConversions="proposalNumber:document.negotiationList[0].associatedDocumentId" />  
						      	</c:when>
						      	<c:when test="${KualiForm.displayInstitutionalProposal}">
						        	<kul:lookup boClassName="org.kuali.kra.institutionalproposal.home.InstitutionalProposal" 
						        		fieldConversions="proposalNumber:document.negotiationList[0].associatedDocumentId" /> 
						      	</c:when>
							</c:choose>
						<c:if test="${KualiForm.dispayChangeAssociatedDocumentWarning }">
							</div>
						</c:if>
					</c:if>
                </td>
            </tr>
            <c:if test="${KualiForm.displayUnAssociatedDetail}">
            	<tr>
            		<th>
            			<div align="right">
            				<kul:htmlAttributeLabel attributeEntry="${negotiationUnassociatedDetailAttributes.title}" />
            			</div>
            		</th>
                	<td>
                		<kul:htmlControlAttribute property="document.negotiationList[0].unAssociatedDetail.title" 
                			attributeEntry="${negotiationUnassociatedDetailAttributes.title}" readOnly="${readOnly}"/>
                	</td>
                	<th>
                		<div align="right">
            				<kul:htmlAttributeLabel attributeEntry="${negotiationUnassociatedDetailAttributes.leadUnitNumber}" />
            			</div>
                	</th>
                	<td>
                		<kul:htmlControlAttribute property="document.negotiationList[0].unAssociatedDetail.leadUnitNumber" 
                			attributeEntry="${negotiationUnassociatedDetailAttributes.leadUnitNumber}" readOnly="${readOnly}"/>
                		<c:if test="${!readOnly}">
	                		${kfunc:registerEditableProperty(KualiForm, "document.negotiationList[0].unAssociatedDetail.leadUnitNumber")}
	                		<kul:lookup boClassName="org.kuali.kra.bo.Unit" 
						        		fieldConversions="unitNumber:document.negotiationList[0].unAssociatedDetail.leadUnitNumber" />
					    </c:if> 
                	</td>
            	</tr>
            	
            	<tr>
            		<th>
                		<div align="right">
            				<kul:htmlAttributeLabel attributeEntry="${negotiationUnassociatedDetailAttributes.piPersonId}" />
            			</div>
                	</th>
                	<td>
                		<c:if test="${!readOnly}">
	                		<html:text property="document.negotiationList[0].unAssociatedDetail.PIEmployee.userName" 
								onblur="loadContactPersonName('document.negotiationList[0].unAssociatedDetail.PIEmployee.userName',
											'PIEmployee.fullName',
											'na',
											'na',
											'na',
											'document.negotiationList[0].unAssociatedDetail.piPersonId');"
		                    	readonly="${readOnly}"/>
	                    
	                        ${kfunc:registerEditableProperty(KualiForm, "document.negotiationList[0].unAssociatedDetail.piPersonId")}
		                    <html:hidden property="document.negotiationList[0].unAssociatedDetail.piPersonId" styleId="document.negotiationList[0].unAssociatedDetail.piPersonId"/>
		                	<kul:lookup boClassName="org.kuali.kra.bo.KcPerson" 
		                                fieldConversions="personId:document.negotiationList[0].unAssociatedDetail.piPersonId" />
	                    </c:if>
	                    <br/><span id="PIEmployee.fullName"><c:out value="${KualiForm.document.negotiationList[0].unAssociatedDetail.PIEmployee.fullName}"/></span>
	                </td>
	                <th>
	                	<div align="right">
            				<kul:htmlAttributeLabel attributeEntry="${negotiationUnassociatedDetailAttributes.piRolodexId}" />
            			</div>
	                </th>
	                <td>
	                	<kul:htmlControlAttribute property="document.negotiationList[0].unAssociatedDetail.piRolodexId" 
                			attributeEntry="${negotiationUnassociatedDetailAttributes.piRolodexId}" readOnly="${readOnly}"/>
                		
                		
                		<c:if test="${!readOnly}">
	                		${kfunc:registerEditableProperty(KualiForm, "document.negotiationList[0].unAssociatedDetail.piRolodexId")}
	                		<kul:lookup boClassName="org.kuali.kra.bo.Rolodex" 
						        		fieldConversions="rolodexId:document.negotiationList[0].unAssociatedDetail.piRolodexId" />
					    </c:if> 
					    <Br/>
					    <c:out value="${KualiForm.document.negotiationList[0].unAssociatedDetail.PINonEmployee.organization}"/>
	                </td>
            	</tr>
            	
            	<tr>
            		<th>
	                	<div align="right">
            				<kul:htmlAttributeLabel attributeEntry="${negotiationUnassociatedDetailAttributes.contactAdminPersonId}" />
            			</div>
	                </th>
	                <td>
	                	<c:if test="${!readOnly}">
		                	<html:text property="document.negotiationList[0].unAssociatedDetail.contactAdmin.userName" 
								onblur="loadContactPersonName('document.negotiationList[0].unAssociatedDetail.contactAdmin.userName',
											'PINonEmployee.fullName',
											'na',
											'na',
											'na',
											'document.negotiationList[0].unAssociatedDetail.contactAdminPersonId');"
		                    	readonly="${readOnly}"/>
	                    
	                        ${kfunc:registerEditableProperty(KualiForm, "document.negotiationList[0].unAssociatedDetail.contactAdminPersonId")}
		                    <html:hidden property="document.negotiationList[0].unAssociatedDetail.contactAdminPersonId" styleId="document.negotiationList[0].unAssociatedDetail.contactAdminPersonId"/>
		                	<kul:lookup boClassName="org.kuali.kra.bo.KcPerson" 
		                                fieldConversions="personId:document.negotiationList[0].unAssociatedDetail.contactAdminPersonId" />
	                    </c:if>
	                    <br/><span id="contactAdmin.fullName"><c:out value="${KualiForm.document.negotiationList[0].unAssociatedDetail.contactAdmin.fullName}"/></span>
	                </td>
	                <th>
	                	<c:if test="${KualiForm.negotiationAssociatedDetailBean.displayOSPAdministrators}">
	                		<div align="right">OSP Administrators:</div>
	                	</c:if>
	                </th>
	                <td>
	                	<c:if test="${KualiForm.negotiationAssociatedDetailBean.displayOSPAdministrators}">
	                		<c:forEach items="${KualiForm.negotiationAssociatedDetailBean.ospAdministrators}" var="current">
            					<c:out value="${current.fullName}"/>
            					<Br/>
            				</c:forEach>
	                	</c:if>
	                </td>
            	</tr>    	
            	<tr>
            		<th>
            			<div align="right">
            				<kul:htmlAttributeLabel attributeEntry="${negotiationUnassociatedDetailAttributes.sponsorCode}" />
            			</div>
            		</th>
                	<td>
                		<kul:htmlControlAttribute property="document.negotiationList[0].unAssociatedDetail.sponsorCode" 
                			attributeEntry="${negotiationUnassociatedDetailAttributes.sponsorCode}" readOnly="${readOnly}"/>
                		<c:if test="${!readOnly}">
	                		${kfunc:registerEditableProperty(KualiForm, "document.negotiationList[0].unAssociatedDetail.sponsorCode")}
	                		<kul:lookup boClassName="org.kuali.kra.bo.Sponsor" 
						        		fieldConversions="sponsorCode:document.negotiationList[0].unAssociatedDetail.sponsorCode" />
					    </c:if>
					    <Br/>
					    <c:out value="${KualiForm.document.negotiationList[0].unAssociatedDetail.sponsor.sponsorName}"/>
                	</td>
                	<th>
                		<div align="right">
            				<kul:htmlAttributeLabel attributeEntry="${negotiationUnassociatedDetailAttributes.primeSponsorCode}" />
            			</div>
                	</th>
                	<td>
                		<kul:htmlControlAttribute property="document.negotiationList[0].unAssociatedDetail.primeSponsorCode" 
                			attributeEntry="${negotiationUnassociatedDetailAttributes.primeSponsorCode}" readOnly="${readOnly}"/>
                		<c:if test="${!readOnly}">
	                		${kfunc:registerEditableProperty(KualiForm, "document.negotiationList[0].unAssociatedDetail.primeSponsorCode")}
	                		<kul:lookup boClassName="org.kuali.kra.bo.Sponsor" 
						        		fieldConversions="sponsorCode:document.negotiationList[0].unAssociatedDetail.primeSponsorCode" />
					    </c:if> 
					    <Br/>
					    <c:out value="${KualiForm.document.negotiationList[0].unAssociatedDetail.primeSponsor.sponsorName}"/>
                	</td>
            	</tr>
            	
            	<tr>
            		<th>
            			<div align="right">
            				<kul:htmlAttributeLabel attributeEntry="${negotiationUnassociatedDetailAttributes.sponsorAwardNumber}" />
            			</div>
            		</th>
                	<td>
                		<kul:htmlControlAttribute property="document.negotiationList[0].unAssociatedDetail.sponsorAwardNumber" 
                			attributeEntry="${negotiationUnassociatedDetailAttributes.sponsorAwardNumber}" readOnly="${readOnly}"/>
                	</td>
                	<th>
                		<div align="right">
            				<kul:htmlAttributeLabel attributeEntry="${negotiationUnassociatedDetailAttributes.subAwardOrganizationId}" />
            			</div>
                	</th>
                	<td>
                		<kul:htmlControlAttribute property="document.negotiationList[0].unAssociatedDetail.subAwardOrganizationId" 
                			attributeEntry="${negotiationUnassociatedDetailAttributes.subAwardOrganizationId}" readOnly="${readOnly}"/>
                		<c:if test="${!readOnly}">
	                		${kfunc:registerEditableProperty(KualiForm, "document.negotiationList[0].unAssociatedDetail.subAwardOrganizationId")}
	                		<kul:lookup boClassName="org.kuali.kra.bo.Organization" 
						        		fieldConversions="organizationId:document.negotiationList[0].unAssociatedDetail.subAwardOrganizationId" />
					    </c:if> 
					    <Br/>
					    <c:out value="${KualiForm.document.negotiationList[0].unAssociatedDetail.subAwardOrganization.organizationName}"/>
                	</td>
            	</tr>
            	
            </c:if>
            
            
            <c:if test="${KualiForm.dispayAssociatedDetailPanel}">
            	<tr>
            		<th>
            			<div align="right">
            				<kul:htmlAttributeLabel attributeEntry="${negotiationUnassociatedDetailAttributes.title}" />
            			</div>
            		</th>
                	<td>
                		<c:out value="${KualiForm.negotiationAssociatedDetailBean.title}"/>
                	</td>
                	<th>
                		<div align="right">
            				<kul:htmlAttributeLabel attributeEntry="${negotiationUnassociatedDetailAttributes.leadUnitNumber}" />
            			</div>
                	</th>
                	<td>
                		<c:out value="${KualiForm.negotiationAssociatedDetailBean.leadUnit}"/> 
                	</td>
            	</tr>
            	
            	<tr>
            		<th>
                		<div align="right">
            				<kul:htmlAttributeLabel attributeEntry="${negotiationUnassociatedDetailAttributes.piPersonId}" />
            			</div>
                	</th>
                	<td>
                		<c:out value="${KualiForm.negotiationAssociatedDetailBean.piEmployee}"/>
	                </td>
	                <th>
	                	<div align="right">
            				<kul:htmlAttributeLabel attributeEntry="${negotiationUnassociatedDetailAttributes.piRolodexId}" />
            			</div>
	                </th>
	                <td>
	                	<c:out value="${KualiForm.negotiationAssociatedDetailBean.piNonEmployee}"/>
	                </td>
            	</tr>
            	
            	<tr>
            		<th>
	                	<div align="right">
            				<kul:htmlAttributeLabel attributeEntry="${negotiationUnassociatedDetailAttributes.contactAdminPersonId}" />
            			</div>
	                </th>
	                <td>
	                	<c:out value="${KualiForm.negotiationAssociatedDetailBean.adminPerson}"/>
	                </td>
	                <th>
	                	<c:if test="${KualiForm.negotiationAssociatedDetailBean.displayOSPAdministrators}">
	                		<div align="right">OSP Administrators:</div>
	                	</c:if>
	                </th>
	                <td>
	                	<c:if test="${KualiForm.negotiationAssociatedDetailBean.displayOSPAdministrators}">
	                		<c:forEach items="${KualiForm.negotiationAssociatedDetailBean.ospAdministrators}" var="current">
            					<c:out value="${current.fullName}"/>
            					<Br/>
            				</c:forEach>
	                	</c:if>
	                </td>
            	</tr>	            	
            	<tr>
            		<th>
            			<div align="right">
            				<kul:htmlAttributeLabel attributeEntry="${negotiationUnassociatedDetailAttributes.sponsorCode}" />
            			</div>
            		</th>
                	<td>
                		<c:out value="${KualiForm.negotiationAssociatedDetailBean.sponsor}"/>
                	</td>
                	<th>
                		<div align="right">
            				<kul:htmlAttributeLabel attributeEntry="${negotiationUnassociatedDetailAttributes.primeSponsorCode}" />
            			</div>
                	</th>
                	<td>
                		<c:out value="${KualiForm.negotiationAssociatedDetailBean.primeSponsor}"/>
                	</td>
            	</tr>
            	
            	<tr>
            		<th>
            			<div align="right">
            				<kul:htmlAttributeLabel attributeEntry="${negotiationUnassociatedDetailAttributes.sponsorAwardNumber}" />
            			</div>
            		</th>
                	<td>
                		<c:out value="${KualiForm.negotiationAssociatedDetailBean.sponsorAward}"/>
                	</td>
                	<th>
                		<div align="right">
            				<kul:htmlAttributeLabel attributeEntry="${negotiationUnassociatedDetailAttributes.subAwardOrganizationId}" />
            			</div>
                	</th>
                	<td>
                		<c:out value="${KualiForm.negotiationAssociatedDetailBean.subAwardOrganization}"/>
                	</td>
            	</tr>
            	
            	<tr>
            		<th>
            			<div align="right">
            				<kul:htmlAttributeLabel attributeEntry="${negotiationUnassociatedDetailAttributes.negotiableProposalTypeCode}" />
            			</div>
            		</th>
                	<td>
                		<c:out value="${KualiForm.negotiationAssociatedDetailBean.proposalType}"/>
                	</td>
                	<th>
                	</th>
                	<td>
                	</td>
            	</tr>
            </c:if>
		</table>
	</div>	
</kul:tab>