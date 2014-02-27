 <%--
 Copyright 2005-2014 The Kuali Foundation

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
<%@ attribute name="answerHeaderIndex" required="true" %>
<%@ attribute name="bean" required="true" type="org.kuali.kra.questionnaire.QuestionnaireHelperBase" %>
<%@ attribute name="property" required="true" %>

                <!-- ELEMENT HEAD: Label Here -->
<h3>
    <span class="subhead-left">Questionnaire Update Status: Update Required </span>
 	<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.questionnaire.question.Question" altText="help"/></span>
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
