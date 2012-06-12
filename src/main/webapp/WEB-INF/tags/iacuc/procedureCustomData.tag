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

<kul:innerTab tabTitle="Custom Data:" parentTab="${parentTabName}" defaultOpen="false" tabErrorKey="" useCurrentTabIndexAsKey="true">
	<div class="innerTab-container" align="left">
        <table class=tab cellpadding="0" cellspacing="0" summary="">
        </table>
    </div>
   	<table id="included-locations-table" cellpadding=0 cellspacing=0 summary="">
        	<tr>
        		<td colspan="4" class="infoline">
				<div align="center">
					<html:image property="methodToCall.updateIacucProtocolStudyGroup.anchor${tabKey}"
					src="${ConfigProperties.kra.externalizable.images.url}tinybutton-update.gif" styleClass="tinybutton"/>
				</div>
			</td>
        	</tr>
   	</table>
</kul:innerTab>
