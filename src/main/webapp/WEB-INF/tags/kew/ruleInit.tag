<%--
 Copyright 2007-2008 The Kuali Foundation

 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.opensource.org/licenses/ecl2.php

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<kul:tabTop tabTitle="Create New Rule" defaultOpen="true">
    <div class="tab-container" align=center >
      <h3>Create New Rule</h3>
      <table cellpadding="0" cellspacing="0" class="datatable" summary="Parent Rule Section">
      <tr>
        <th align=right valign=middle class="bord-l-b">*Document Type: </th>
        <td align=left valign=middle class="datacell">
            <kul:htmlControlAttribute attributeEntry="${docTypeAttributes.name}" property="documentTypeName" readOnly="true" />
            <kul:lookup boClassName="org.kuali.rice.kew.doctype.bo.DocumentType" fieldConversions="name:documentTypeName"/>
        </td>
      </tr>
      <tr>
        <th align=right valign=middle class="bord-l-b">*Rule Template: </th>
        <td align=left valign=middle class="datacell">
            <kul:htmlControlAttribute attributeEntry="${ruleTemplateAttributes.name}" property="ruleTemplateName" readOnly="true" />
            <kul:lookup boClassName="org.kuali.rice.kew.rule.bo.RuleTemplate" fieldConversions="name:ruleTemplateName"/>
        </td>
      </tr>
      </table>
    </div>

</kul:tabTop>
