<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="disclProjectBean" required="true" type="org.kuali.kra.coi.disclosure.CoiDisclosureProjectBean" description="A List of active or inactive FE" %>
<%@ attribute name="projectDivNamePrefix" required="true" description="name for project related div" %>
<%@ attribute name="idx" required="true" description="detail index" %>
<c:set var="coiDiscDetailAttributes" value="${DataDictionary.CoiDiscDetail.attributes}" />
              
              
                                  
            <%-- New data --%>
            
            <%-- Existing data --%>

             <div class="financialentity">
                 <div class="h2-container" style="height:20px; vertical-align:middle; background-color:#999; border-top:none; border-bottom:none; color:#FFF; font-weight:bold;">
    		        <span class="subhead-left">
                     <a href="#" id ="${projectDivNamePrefix}Control${idx}" class="financialEntitySubpanel"><img src='kr/images/tinybutton-hide.gif' alt='show/hide panel' width='45' height='15' border='0' align='absmiddle'></a> 
    		        Financial Entities </span>
    		        <span style="float: right;text-align: right;"><kul:help businessObjectClassName="org.kuali.kra.coi.CoiDiscDetail" altText="help"/></span>
                </div>

                 <div  id="${projectDivNamePrefix}Details${idx}" style="margin-bottom:15px;">
                 <div  id="${projectDivNamePrefix}Content${idx}" class="financialEntitySubpanelContent" style="margin-bottom:15px;">
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
					    ${disclosureDetail.coiEntityStatusCode.description}
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
