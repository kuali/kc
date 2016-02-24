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

<c:set var="kraAttributeReferenceDummyAttributes" value="${DataDictionary.KraAttributeReferenceDummy.attributes}" />

<div id="workarea">
    <kul:tab tabTitle="Print" 
             tabErrorKey="committeeHelper.reportType"
             auditCluster="requiredFieldsAuditErrors"  
             defaultOpen="false"
             useCurrentTabIndexAsKey="true"  
             transparentBackground="false">
        <div class="tab-container" align="center">
            <h3>
                <span class="subhead-left">Print</span>
            </h3>
            
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <td><div align="left">Roster</div></td> 
                    <td>
                        <div align="center">
                            <kul:htmlControlAttribute property="committeeHelper.printRooster" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}"
                                                      readOnly="false" styleClass="printCorOptions"/>
                        </div>
                    </td>
                </tr>     
                <tr>
                    <td><div align="left">Future Scheduled Meetings</div></td> 
                    <td>
                        <div align="center">
                            <kul:htmlControlAttribute property="committeeHelper.printFutureScheduledMeeting" 
                                                      attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}" 
                                                      readOnly="false" styleClass="printCorOptions"/>
                        </div>
                    </td>
                </tr>
				<c:if
					test="${fn:length(KualiForm.committeeHelper.correspondencesToPrint) > 0}">
					<c:forEach var="corPrintOption"
						items="${KualiForm.committeeHelper.correspondencesToPrint}"
						varStatus="status">
						<tr>
							<td>
								<div align="left">
									${KualiForm.committeeHelper.correspondencesToPrint[status.index].label}
								</div>
							</td>
							<td>
								<div align="center">
									<kul:htmlControlAttribute
										property="committeeHelper.correspondencesToPrint[${status.index}].selected"
										attributeEntry="${kraAttributeReferenceDummyAttributes.checkBox}"
										readOnly="false" styleClass="printCorOptions" />
								</div>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td class="infoline">
							<div align="center">&nbsp;</div>
						</td>
						<td>
							<div align="center">
								<html:image
									property="methodToCall.selectAllProtocolPrint.anchor${tabKey}"
									src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectall.gif"
									title="Select All" alt="Select All" styleClass="tinybutton"
									onclick="$j('.printCorOptions').attr('checked', true);return false;" />
								<html:image
									property="methodToCall.deselectAllProtocolPrint.anchor${tabKey}"
									src="${ConfigProperties.kra.externalizable.images.url}tinybutton-selectnone.gif"
									title="Select None" alt="Select None" styleClass="tinybutton"
									onclick="$j('.printCorOptions').attr('checked', false);return false;" />
							</div>
						</td>
					</tr>
				</c:if>
				<tr>
                    <td class="infoline"><div align="left">&nbsp;</div></td> 
                    <td class="infoline">
                        <div align="center">                    
                            <html:image property="methodToCall.printCommitteeDocument"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-printsel.gif' 
                                        styleClass="tinybutton"
                                        onclick="excludeSubmitRestriction = true;" />                         
                        </div>
                    </td>
                </tr>     
            </table>
        </div> 
    </kul:tab>
    <kul:panelFooter />
</div>
