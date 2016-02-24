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

<%-- <c:set var="readOnly" value="${KualiForm.readOnly}"  scope="request"/> --%>
<c:set var="committeeScheduleAttributes" value="${DataDictionary.CommitteeSchedule.attributes}" />
<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />
<c:set var="admin" value="${KualiForm.meetingHelper.admin}" />

<kul:tab defaultOpen="false" tabTitle="Correspondence"
    tabErrorKey="document.committee*">
        <script type="text/javascript">
		   var $j = jQuery.noConflict();
		</script>
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
                           <c:if test="${admin}">               
                        
                            <kul:htmlControlAttribute property="meetingHelper.correspondences[${status.index}].regenerateFlag" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}"
                                                      styleClass="regenerateclass" readOnly="false" />
                           </c:if>                           
                        </div>
                    </td>
                    <td align="left" valign="middle">
                        <div align="left"> 
                            <c:choose>
                                  <c:when test="${correspondence.finalFlag}">
                                     Yes (<fmt:formatDate value="${correspondence.finalFlagTimestamp}" pattern="MM/dd/yyyy KK:mm a" /> )
                                  </c:when>
                                  <c:otherwise>
                                      No
                                  </c:otherwise>
                                     
                            </c:choose>
                        </div>

                    </td>
						<td>
							<div align="center">&nbsp;					
              				<c:if test="${admin or correspondence.finalFlag}">               
	                            <html:image property="methodToCall.viewCorrespondence.line${status.index}.anchor${currentTabIndex}"
	                                src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
	                                alt="View Correspondence" onclick="excludeSubmitRestriction = true;"/>
               				</c:if>  
							</div>
		                </td>
	            </tr>
        	</c:forEach>
              <c:if test="${admin}">               
                <tr>
                    <td colspan="6">
                            <div align="center">&nbsp;   
                                    <html:image property="methodToCall.regenerateCorrespondence.line${status.index}.anchor${currentTabIndex}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-regenerate.gif' styleClass="tinybutton"
                                        alt="Regenerate Correspondence" title="Regenerate Correspondence" onclick="excludeSubmitRestriction = true;"/>
                                  <html:image src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif" title="Select All" alt="Select All" styleClass="tinybutton" onclick="$j('.regenerateclass').attr('checked', true);return false;" />
                                  <html:image src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif" title="Select None" alt="Select None" styleClass="tinybutton" onclick="$j('.regenerateclass').attr('checked', false);return false;" />
                            </div>
                    </td>
                </tr>    
               </c:if>  
       </table> 	
</div>

</kul:tab>
