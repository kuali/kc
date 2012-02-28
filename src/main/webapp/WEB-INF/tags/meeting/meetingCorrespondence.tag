<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="committeeScheduleAttributes" value="${DataDictionary.CommitteeSchedule.attributes}" />
<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />
<c:set var="irbAdmin" value="${KualiForm.meetingHelper.irbAdmin}" />

<kul:tab defaultOpen="false" tabTitle="Correspondence"
    tabErrorKey="document.committee*">

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Correspondence </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.irb.correspondence.ProtocolCorrespondence" altText="help"/> </span>
    </h3>
         <table id="viewCorrespondence-table" cellpadding=0 cellspacing=0 class="datatable" summary="View Agenda Docs">
        
        	<tr>
        		<kul:htmlAttributeHeaderCell literalLabel="Protocol Number" scope="col" />
        		<kul:htmlAttributeHeaderCell literalLabel="Correspondence" scope="col" />
                <kul:htmlAttributeHeaderCell literalLabel="Date Created" scope="col" />
                <kul:htmlAttributeHeaderCell literalLabel="Regenerate" scope="col" />
                <kul:htmlAttributeHeaderCell literalLabel="Final" scope="col" />
				<kul:htmlAttributeHeaderCell literalLabel="Actions" scope="col" />
			</tr>
        	<c:forEach var="correspondence" items="${KualiForm.meetingHelper.correspondences}" varStatus="status">
	             <tr>
					<th class="infoline" align="right">
						<c:out value="${correspondence.protocolNumber}" />
					</th>
	                <td align="left" valign="middle">
	                    <div align="left"><c:out value="${correspondence.protocolCorrespondenceType.description}" /></div>
					</td>
	                <td align="left" valign="middle">
	                    <div align="left"><fmt:formatDate value="${correspondence.createTimestamp}" pattern="MM/dd/yyyy KK:mm a" /> </div>
					</td>
                    <td>
                        <div align="center">
                           <c:if test="${irbAdmin}">               
                        
                            <kul:htmlControlAttribute property="meetingHelper.correspondences[${status.index}].regenerateFlag" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}"
                                                      styleClass="regenerateclass" readOnly="false" />
                           </c:if>                           
                        </div>
                    </td>
                    <td align="left" valign="middle">
                        <div align="left">${correspondence.finalFlag ? "Yes" : "No"}</div>
                    </td>
						<td>
							<div align="center">&nbsp;					
                                    <html:image property="methodToCall.viewCorrespondence.line${status.index}.anchor${currentTabIndex}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
                                        alt="View Correspondence" onclick="excludeSubmitRestriction = true;"/>
							</div>
		                </td>
	            </tr>
        	</c:forEach>
              <c:if test="${irbAdmin}">               
                <tr>
                    <td colspan="6">
                            <div align="center">&nbsp;   
                                    <html:image property="methodToCall.regenerateCorrespondence.line${status.index}.anchor${currentTabIndex}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-regenerate.gif' styleClass="tinybutton"
                                        alt="Regenerate Correspondence" title="Regenerate Correspondence" onclick="excludeSubmitRestriction = true;"/>
                                  <html:image property="methodToCall.selectAllProtocolPrint.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif" title="Select All" alt="Select All" styleClass="tinybutton" onclick="$('.regenerateclass').attr('checked', true);return false;" />
                                  <html:image property="methodToCall.deselectAllProtocolPrint.anchor${tabKey}" src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif" title="Select None" alt="Select None" styleClass="tinybutton" onclick="$('.regenerateclass').attr('checked', false);return false;" />
                            </div>
                    </td>
                </tr>    
               </c:if>  
       </table> 	
</div>

</kul:tab>