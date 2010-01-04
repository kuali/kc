<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%@ attribute name="answerHeaderIndex" required="true" %>
                <!-- ELEMENT HEAD: Label Here -->
        <h3>
            <span class="subhead-left">Questionnaire Update Status: Update Required </span>
 	        <span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.questionnaire.answer.AnswerHeader" altText="help"/></span>
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
                                            <input type="radio" class="nobord" name="questionnaireHelper.answerHeaders[${answerHeaderIndex}].updateOption" value="0" checked="checked" />
                                        </td>
                                        <td style="border:none; vertical-align:top; background:none;">
                                            Copy the answers from the old version of questionnaire to the new one.  Only questions that have been changed in the new version will have to be answered.
                                        </td>
                                        <td style="border:none; background:none; text-align:right; width:20px; vertical-align:top;">
                                            <input type="radio" class="nobord" name="questionnaireHelper.answerHeaders[${answerHeaderIndex}].updateOption" value="1" />
                                        </td>
                                        <td style="border:none; vertical-align:top; background:none;">
                                            Do not copy answers from previous version of the questionnaire.  Every question in the new version of the questionnaire will have to be answered.
                                        </td>
                                    </tr>
                                </table>
                                
                            </td>
                            <td class="content_questionnaire">
						<div align=center>&nbsp;					
							<html:image property="methodToCall.updateAnswerToNewVersion.line${answerHeaderIndex}.anchor${currentTabIndex}"
										src='${ConfigProperties.kra.externalizable.images.url}tinybutton-update.gif' styleClass="tinybutton"/>
						</div>
                            </td>
                       </tr>
                    </table>
                </div>
