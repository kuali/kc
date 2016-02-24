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
<c:set var="protocolCorrespondenceAttributes" value="${DataDictionary.IacucProtocolCorrespondence.attributes}" />

<kul:tabTop defaultOpen="false" tabTitle="Correspondence"
    tabErrorKey="document.committee*">

<div class="tab-container" align="center">
    <h3>
        <span class="subhead-left"> Correspondence </span>
        <span class="subhead-right"> <kul:help businessObjectClassName="org.kuali.kra.iacuc.correspondence.IacucProtocolCorrespondence" altText="help"/> </span>
    </h3>
         <table id="Correspondence-table" cellpadding=0 cellspacing=0 class="datatable" summary="Set correspondence">
        
            <tr>
                <kul:htmlAttributeHeaderCell literalLabel="Protocol Number" scope="col" />
                <kul:htmlAttributeHeaderCell literalLabel="Correspondence" scope="col" />
                <kul:htmlAttributeHeaderCell literalLabel="Final" scope="col" />
                <kul:htmlAttributeHeaderCell literalLabel="Action" scope="col" />
            </tr>
                 <tr>
                    <th class="infoline" align="right">
                        <c:out value="${KualiForm.actionHelper.protocolCorrespondence.protocolNumber}" />
                    </th>
                    <td align="left" valign="middle">
                        <div align="left"><c:out value="${KualiForm.actionHelper.protocolCorrespondence.protocolCorrespondenceType.description}" /></div>
                    </td>
                    <td align="left" valign="middle" class="infoline">
                        <div align="center">
                        <kul:htmlControlAttribute property="actionHelper.protocolCorrespondence.finalFlag" attributeEntry="${protocolCorrespondenceAttributes.finalFlag}" readOnly="false" />
                        </div>
                    </td>
                        <td>
                            <div align="center">&nbsp;                  
                                    <html:image property="methodToCall.viewCorrespondence.line${KualiForm.actionHelper.protocolCorrespondence.id}.anchor${currentTabIndex}"
                                        src='${ConfigProperties.kra.externalizable.images.url}tinybutton-view.gif' styleClass="tinybutton"
                                        alt="View Agenda" onclick="excludeSubmitRestriction = true;"/>
                            </div>
                        </td>
                </tr>
       </table>     
</div>

</kul:tabTop>
