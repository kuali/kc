<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="masterDisclosureProjects" required="true" type="java.util.List" description="A List of active or inactive FE" %>
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
<kul:tab defaultOpen="false" tabTitle="Proposals" auditCluster="financialEntityDiscAuditErrors" tabAuditKey="${auditErrorKey}" useRiceAuditMode="true"
    tabErrorKey="disclosureHelper.newCoiDisclProject.*" >
	<div class="tab-container" align="center">
              
              
                                  
            <%-- New data --%>
            
            <%-- Existing data --%>

        	<c:forEach var="disclProjectBean" items="${masterDisclosureProjects}" varStatus="status">
                     <kra-coi:proposalHeader disclProject="${disclProjectBean.disclosureProject}" />                    
             <div class="financialentity">
                <h3>
    		        <span class="subhead-left">
                     <a href="#" id ="masterProposalFEControl${status.index}" class="financialEntitySubpanel"><img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'></a> 
    		        Financial Entities </span>
    		        <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.CoiDiscDetail" altText="help"/></span>
                </h3>

                 <div  id="masterProposalFEDetails${status.index}" style="margin-bottom:15px;">
                 <div  id="masterProposalFEContent${status.index}" class="financialEntitySubpanelContent" style="margin-bottom:15px;">
                    <table class=tab cellpadding="0" cellspacing="0" summary="">
          	<tr>
          		<kul:htmlAttributeHeaderCell literalLabel="Review" scope="col" /> 
          		<kul:htmlAttributeHeaderCell attributeEntry="${coiDiscDetailAttributes.personFinIntDisclosureId}" scope="col" />
          		<th rowspan="1" colspan="1" scope="col">${KualiForm.disclosureHelper.conflictHeaderLabel}</th>
          		<kul:htmlAttributeHeaderCell attributeEntry="${coiDiscDetailAttributes.comments}" scope="col" />
          	</tr> 
        	    <c:forEach var="disclosureDetail" items="${disclProjectBean.projectDiscDetails}" varStatus="festatus">
	             <tr>
					  <td>
						<div align=center>&nbsp;
							<c:if test="${KualiForm.disclosureHelper.canViewDisclosureFeHistory}">		
                                <a class="disclosureFeView" id="viewEntitySummary${festatus.index}" title="${disclosureDetail.personFinIntDisclosure.entityName} Summary" href="${pageContext.request.contextPath}/financialEntityEditList.do?methodToCall=viewFinancialEntity&status=activecoi&index=${disclosureDetail.personFinIntDisclosure.personFinIntDisclosureId}" scrolling="no" noresize>
						 	        <html:image src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton" title="View Entity"/>
                    	        </a>   
                    	    </c:if>         
							<c:if test="${KualiForm.disclosureHelper.canEditDisclosureFinancialEntity}">		
							    <html:image property="methodToCall.editFinancialEntity.line${festatus.index}.anchor${currentTabIndex}"
									src='${ConfigProperties.kra.externalizable.images.url}tinybutton-edit1.gif' styleClass="tinybutton"/>
                    	     </c:if>
							<c:if test="${KualiForm.disclosureHelper.canViewDisclosureFeHistory}">		
						        <a class="disclosureFeHistory" id="history${festatus.index}" title="${disclosureDetail.personFinIntDisclosure.entityName} History" href="${pageContext.request.contextPath}/financialEntityEditList.do?methodToCall=showFinancialEntityHistory&status=activecoi&index=${disclosureDetail.personFinIntDisclosure.personFinIntDisclosureId}">
							        <html:image property="methodToCall.historyFinancialEntity.line${festatus.index}.anchor${currentTabIndex}"
									    src='${ConfigProperties.kra.externalizable.images.url}tinybutton-history.gif' styleClass="tinybutton"/>
                    	         </a>
                    	     </c:if>
						</div>
		              </td>
                  <td align="left" valign="middle">
					<div align="left">
					    ${disclosureDetail.personFinIntDisclosure.entityName}
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
					    ${disclosureDetail.entityStatusCode}
					</div>
				  </td>
                  <td align="left" valign="middle">
					<div align="left">
					    ${disclosureDetail.comments}
					</div>
				  </td>
	            </tr>
	            </c:forEach>
	            </table>
	           </div> 
	           </div>
	           </div>
        	</c:forEach> 
            <%-- Existing data --%>
       </div>
</kul:tab>
