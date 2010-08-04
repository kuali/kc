<%--
 Copyright 2005-2010 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
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
