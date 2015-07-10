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
<div class="tab-container" align="center" id="multichoice-container">
    <h3>
        <span class="subhead-left"> Multi-Choice </span>
    </h3>

    <table id="multiChoice-table" width="100%" cellpadding="0" cellspacing="0" class="datatable">
        <tr>
            <th align="center" valign="middle">
                &nbsp;
            </th>
            <th align="center" valign="middle">
                <kul:htmlAttributeLabel attributeEntry="${DataDictionary.QuestionMultiChoice.attributes.prompt}" useShortLabel="true" noColon="true" />
            </th>
            <th align="center" valign="middle">
                <kul:htmlAttributeLabel attributeEntry="${DataDictionary.QuestionMultiChoice.attributes.description}" useShortLabel="true" noColon="true" />
            </th>
        </tr>

        <c:if test="${!KualiForm.readOnly}">
            ${kfunc:registerEditableProperty(KualiForm, "document.newMaintainableObject.businessObject.questionMultiChoices")}
            <tr>
                <th class="infoline">
                    <c:out value="Add:" />
                </th>
                <td align="left" valign="middle" class="infoline">
                    <div align="left">
                        <kul:htmlControlAttribute property="newQuestionMultiChoice.prompt" attributeEntry="${DataDictionary.QuestionMultiChoice.attributes.prompt}" />
                    </div>
                </td>
                <td align="left" valign="middle" class="infoline">
                    <div align="left">
                        <kul:htmlControlAttribute property="newQuestionMultiChoice.description" attributeEntry="${DataDictionary.QuestionMultiChoice.attributes.description}" />
                    </div>
                </td>
                <td class="infoline">
                    <div align=center>&nbsp;
                        <input type="image" id="addMultiChoice" name="addMultiChoice" alt="Add a Multi-Choice" title="Add a Multi-Choice" src="static/images/tinybutton-add1.gif" class="tinybutton">
                    </div>
                </td>
            </tr>
        </c:if>

        <tr class="multiChoiceTemplate" style="display: none;">
            <td align="left" valign="middle">
                &nbsp;
            </td>
            <td align="left" valign="middle">
                <div align="left">
                    <input type="text" name="%PROMPT_NAME%" value="%PROMPT%" maxlength="${DataDictionary.QuestionMultiChoice.attributes.prompt.maxLength}" size="${DataDictionary.QuestionMultiChoice.attributes.prompt.control.size}"/>
                </div>
            </td>

            <td align="left" valign="middle">
                <div align="left">
                    <input type="text" name="%DESCRIPTION_NAME%" value="%DESCRIPTION%" maxlength="${DataDictionary.QuestionMultiChoice.attributes.description.maxLength}" size="${DataDictionary.QuestionMultiChoice.attributes.description.control.size}"/>
                </div>
            </td>

            <td style="text-align: center;">
                <c:if test="${!KualiForm.readOnly}">
                    <input type="image" name="deleteMultiChoice" title="Delete Multi-Choice" src="static/images/tinybutton-delete1.gif" class="tinybutton deleteMultiChoice"/>
                </c:if>
            </td>
        </tr>
        <c:forEach items="${KualiForm.document.newMaintainableObject.businessObject.questionMultiChoices}" var="choice" varStatus="status">
            <tr>
                <td align="left" valign="middle">
                    &nbsp;
                </td>
                <td align="left" valign="middle">
                    <div align="left">
                        <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.questionMultiChoices[${status.index}].prompt" attributeEntry="${DataDictionary.QuestionMultiChoice.attributes.prompt}" />
                    </div>
                </td>
                <td align="left" valign="middle">
                    <div align="left">
                        <kul:htmlControlAttribute property="document.newMaintainableObject.businessObject.questionMultiChoices[${status.index}].description" attributeEntry="${DataDictionary.QuestionMultiChoice.attributes.description}" />
                    </div>
                </td>
                <td style="text-align: center;">
                    <c:if test="${!KualiForm.readOnly}">
                        <input type="image" name="deleteMultiChoice" title="Delete Multi-Choice" src="static/images/tinybutton-delete1.gif" class="tinybutton deleteMultiChoice"/>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
