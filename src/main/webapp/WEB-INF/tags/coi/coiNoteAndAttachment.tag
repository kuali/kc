
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

<script src="scripts/jquery/jquery.js"></script>
<script type="text/javascript">
   var $j = jQuery.noConflict();
</script>
 <SCRIPT type="text/javascript">
var kualiForm = document.forms['KualiForm'];
var kualiElements = kualiForm.elements;

</SCRIPT>
<kul:tab tabTitle="Notes & Attachments" tabItemCount="${tabItemCount}" defaultOpen="false" tabErrorKey="*">
<div class="tab-container" align="center">
    	<h3>
    		<span class="subhead-left">Notes & Attachments</span>
    		<span class="subhead-right"><kul:help businessObjectClassName="org.kuali.kra.coi.notesandattachments.notepad.CoiDisclosureNotepad" altText="help"/></span>
        </h3>
<kra-coi:coiAttachments />

<kra-coi:coiNotes />

</div>
</kul:tab>


