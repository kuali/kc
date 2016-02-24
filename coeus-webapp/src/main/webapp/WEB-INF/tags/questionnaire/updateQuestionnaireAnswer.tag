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
<%@ attribute name="answerHeaderIndex" required="true" %>
<%@ attribute name="bean" required="true" type="org.kuali.coeus.common.questionnaire.framework.core.QuestionnaireHelperBase" %>
<%@ attribute name="property" required="true" %>

                <!-- ELEMENT HEAD: Label Here -->
<h3>
    <span class="subhead-left">Questionnaire Update Status: Update Required </span>
 	<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.coeus.common.questionnaire.framework.question.Question" altText="help"/></span>
</h3>
        
        <!-- CONTENT: Label Here -->
<div>
    <table class="content_table">  
        <tr>
            <td class="content_questionnaire" colspan="2">
            	<b>A newer version of the questionnaire has been published.  An update is required.</b>
            </td>
        </tr>
        <tr>
            <td class="content_questionnaire">
                    	
                <table style="border:none; background:none;" cellpadding="0" cellspacing="0">
                    <tr>
                        <td style="border:none; background:none; text-align:right; width:20px; vertical-align:top;">
                            <c:set var="tempProp" value="${property}.answerHeaders[${answerHeaderIndex}].notUpdated"/>
                            ${kfunc:registerEditableProperty(KualiForm, tempProp)}
                            <input type="hidden" name="${tempProp}" id="${tempProp}" value = "TRUE" />
                            
                            <c:set var="prop" value="${property}.answerHeaders[${answerHeaderIndex}].updateOption"/>
                            ${kfunc:registerEditableProperty(KualiForm, prop)}
                            <input type="radio" class="nobord" name="${property}.answerHeaders[${answerHeaderIndex}].updateOption" value="0" checked="checked" />
                        </td>
                        <td style="border:none; vertical-align:top; background:none;">
                            Copy the answers from the old version of questionnaire to the new one.  Only questions that have been changed in the new version will have to be answered.
                        </td>
                        <td style="border:none; background:none; text-align:right; width:20px; vertical-align:top;">
                            
                            <c:set var="prop" value="${property}.answerHeaders[${answerHeaderIndex}].updateOption"/>
                            ${kfunc:registerEditableProperty(KualiForm, prop)}
                            <input type="radio" class="nobord" name="${property}.answerHeaders[${answerHeaderIndex}].updateOption" value="1" />
                        </td>
                        <td style="border:none; vertical-align:top; background:none;">
                            Do not copy answers from previous version of the questionnaire.  Every question in the new version of the questionnaire will have to be answered.
                        </td>
                    </tr>
                </table>
                                
            </td>
            <td class="content_questionnaire">
			    <div align=center>&nbsp;			
					<html:image property="methodToCall.updateAnswerToNewVersion.${property}.line${answerHeaderIndex}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-update.gif' styleClass="tinybutton"/>
				</div>
            </td>
        </tr>
    </table>
</div>
