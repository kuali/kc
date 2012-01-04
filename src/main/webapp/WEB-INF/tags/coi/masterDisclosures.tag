<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>

<kul:tab defaultOpen="false" tabTitle="Disclosures"
    tabErrorKey="document.committee*">

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Disclosures </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.coi.CoiDisclosureStatus" altText="help"/> </span>
    </h3>
    
        <table id="protocolSubmitted-wrap-table" cellpadding=0 cellspacing=0 class="datatable" summary="Protocol Submitted">
        
        	<%-- Header --%>

        	<tr>
        		<th width="4%" />
                 <th><div align="center">Event</div></th> 
                 <th><div align="center">Disposition Status</div></th> 
                 <th><div align="center">Approval Date</div></th> 
                 <th><div align="center">Project Name</div></th> 
                 <th><div align="center">Project Number </div></th> 
                 <th><div align="center">Disclosure Status </div></th> 
                 <th><div align="center">Sequence</div></th> 
				 <th><div align="center">Actions </div> </th>
			</tr>
			<%-- Header --%>

			<%-- Existing data --%>
			                        <c:set var="idx" value="1"/>
        	                        <c:forEach var="disclProjectBean" items="${KualiForm.disclosureHelper.masterDisclosureBean.allProjects}" varStatus="status">
        	                        <%--  <c:if test = "${protocolSubmission.protocol.active}" > --%>
	                                    <tr>
					                       <th class="infoline" align="center">
						                       <c:out value="${idx}" />
			                                   <c:set var="idx" value="${idx+1}"/>
					                       </th>
	                                       <td align="left" valign="middle">
	                                           <div align="left"> ${disclProjectBean.coiDisclosure.coiDisclosureEventType.description} </div>
					                       </td>
	                                       <td align="left" valign="middle">
	                	                       <div align="left"> ${disclProjectBean.coiDisclosure.coiDispositionStatus.description}</div>
	                                       </td>
	                                       <td align="left" valign="middle">
	                                           <div align="left"> ${disclProjectBean.coiDisclosure.updateTimestamp} </div>
					                       </td>
	                                       <td align="left" valign="middle">
	                                           <div align="left"> ${disclProjectBean.projectName} </div>
					                       </td>
	                                       <td align="left" valign="middle">
	                                           <div align="left"> ${disclProjectBean.projectId} </div>
					                       </td>
	                                       <td align="left" valign="middle">
	                                           <div align="left"> ${disclProjectBean.coiDisclosure.coiDisclosureStatus.description} </div>
					                       </td>
					                       </td>
	                                       <td align="left" valign="middle">
	                                           <div align="left"> ${disclProjectBean.coiDisclosure.sequenceNumber} </div>
					                       </td>

                                          	<td> 
                                          		<div align="center">
                                          		<a href="${pageContext.request.contextPath}/coiDisclosure.do?command=masterDisclosure&docId=${disclProjectBean.coiDisclosure.coiDisclosureDocument.documentNumber}" target="_blank" >
                                          			<img alt="View Protocol" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif" styleClass="tinybutton" />
                                          		</a>
                                          		</div>
		                                    </td>
	                                   </tr>
	                              <%--   </c:if>  --%>
        	                       </c:forEach>
			<%-- Existing data --%>
       </table>
    </div>

</kul:tab>