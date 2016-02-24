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

<div align="center">
    <table cellpadding="0" cellspacing="0" summary="">
        <tr>
            <td>
                <kul:innerTab parentTab="Print Forms" tabTitle="Print Notice" defaultOpen="false" tabErrorKey="">
                    <div class="innerTab-container" align="center" >
                        <table align="right" cellpadding="0" cellspacing="0" summary="">
                            <tbody>
                                <tr>
                                    <td width="10%">&nbsp;</td>
                                    <th>Proposal Notice</th>
                                    <td width="10%">
                                        <div align="center">
                                            <html:image src="${ConfigProperties.kra.externalizable.images.url}tinybutton-print.gif" styleClass="globalbuttons"
                                            property="methodToCall.printProposalSummary" alt="Print Proposal Notice" onclick="excludeSubmitRestriction=true" />
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </kul:innerTab>
            </td>
        </tr>
    </table>
</div>
