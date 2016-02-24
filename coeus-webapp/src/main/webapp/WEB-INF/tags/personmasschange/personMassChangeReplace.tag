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

<c:set var="attributes" value="${DataDictionary.PersonMassChange.attributes}" />

<kul:tab tabTitle="Replace Person" defaultOpen="true" tabErrorKey="document.personMassChange.replaceeFullName,document.personMassChange.replacerFullName">
    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left">Replace the Person...</span>
        </h3>
        
        <table id="person-mass-change-replacee-table" cellpadding="0" cellspacing="0" summary="">
            <tr>
                <td align="left" valign="middle"><div align="center">
                    <table cellpadding="0" cellspacing="0" summary="" border="0" style="border: medium none;">
                        <tbody>
                            <tr>
                                <td width="200" style="border: medium none;">
                                    Employee Search
                                </td>
                                <td style="border: medium none;">
                                    <kul:lookup boClassName="org.kuali.coeus.common.framework.person.KcPerson" 
                                                fieldConversions="personId:personMassChangeHomeHelper.replaceePersonId" />
                                </td>
                            </tr>
                            <tr>
                                <td width="200" style="border: medium none;">
                                    Non-Employee Search
                                </td>
                                <td style="border: medium none;">
                                    <kul:lookup boClassName="org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex"
                                                fieldConversions="rolodexId:personMassChangeHomeHelper.replaceeRolodexId" />
                                </td>
                            </tr>
                         </tbody>
                    </table>
                    <div id="personMassChangeReplaceeFullName" align="left">
                        <kul:htmlControlAttribute property="document.personMassChange.replaceeFullName" 
                                                  attributeEntry="${attributes.replaceeFullName}" 
                                                  readOnly="true" />
                    </div>
                </div></td>
            </tr>
        </table>
    </div>
    
    <div class="tab-container" align="center">
        <h3>
            <span class="subhead-left">With...</span>
        </h3>
        
        <table id="person-mass-change-replacee-table" cellpadding="0" cellspacing="0" summary="">
            <tr>
                <td align="left" valign="middle"><div align="center">
                    <table cellpadding="0" cellspacing="0" summary="" border="0" style="border: medium none;">
                        <tbody>
                            <tr>
                                <td width="200" style="border: medium none;">
                                    Employee Search
                                </td>
                                <td style="border: medium none;">
                                    <kul:lookup boClassName="org.kuali.coeus.common.framework.person.KcPerson" 
                                                fieldConversions="personId:personMassChangeHomeHelper.replacerPersonId" />
                                </td>
                            </tr>
                            <tr>
                                <td width="200" style="border: medium none;">
                                    Non-Employee Search
                                </td>
                                <td style="border: medium none;">
                                    <kul:lookup boClassName="org.kuali.coeus.common.framework.rolodex.NonOrganizationalRolodex"
                                                fieldConversions="rolodexId:personMassChangeHomeHelper.replacerRolodexId" />
                                </td>
                            </tr>
                         </tbody>
                    </table>
                    <div id="personMassChangeReplacerFullName" align="left">
                        <kul:htmlControlAttribute property="document.personMassChange.replacerFullName" 
                                                  attributeEntry="${attributes.replacerFullName}" 
                                                  readOnly="true" />
                    </div>
                </div></td>
            </tr>
        </table>
    </div>
    
</kul:tab>
