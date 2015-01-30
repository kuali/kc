<%--
   - Kuali Coeus, a comprehensive research administration system for higher education.
   - 
   - Copyright 2005-2015 Kuali, Inc.
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
<c:set var="disclosureAttributes" value="${DataDictionary.CoiDisclosure.attributes}" />
<c:set var="readOnly" value="${KualiForm.document.viewOnly}" />

	<div class="tab-container" align="center">
		<h3> 
			<span class="subhead-left">Review Status</span>
            <span class="subhead-right"><kul:help parameterNamespace="KC-COIDISCLOSURE" parameterDetailType="Document" parameterName="coiAdministratorActionHelp" altText="help"/></span>
 		</h3>
        <table class="tab" cellpadding="0" cellspacing="0" summary=""> 
            <tbody>
                <tr>               
					<th style="width: 150px">
                		<div align="right">
                			<kul:htmlAttributeLabel attributeEntry="${disclosureAttributes.reviewStatusCode}" /></div>       
                		</div>
                	</th>
                	<td style="width: 150px">
                        <kul:htmlControlAttribute property="disclosureActionHelper.coiDisclosure.reviewStatusCode" 
                                                  attributeEntry="${disclosureAttributes.reviewStatusCode}" readOnly="${readOnly}"/>
                	</td>
	            </tr>               
                <tr>
					<td align="center" colspan="4">
						<div align="center">
							<c:if test="${!readOnly}">
							<html:image property="methodToCall.updateDisclosureReviewStatus.anchor${tabKey}"
							            src='${ConfigProperties.kra.externalizable.images.url}tinybutton-submit.gif' styleClass="tinybutton"/>
							</c:if>
						</div>
	                </td>
                </tr>
            </tbody>
        </table>
   </div>
