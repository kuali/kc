 <%--
 Copyright 2006-2009 The Kuali Foundation

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

<kul:page lookup="true" 
          docTitle="Search Research Areas" 
          transactionalDocument="false" 
          htmlFormAction="researchAreas">


	<input type="hidden" id="methodToCall"
		name="methodToCall" value="${ResearchAreasForm.methodToCall}"/>
    <!--  fake this "formcomplete" otherwise, there will be a popup that showed not finished loading -->
	<html:hidden property="formComplete" value="true"/>	

 		<!--  <label>Sponsor Code Search</label> -->
   		<label>
   			<input type="image" tabindex="1000000" name="methodToCall.performLookup.(!!org.kuali.kra.bo.ResearchArea!!).(((researchAreaCode:researchAreaCode))).((%false%)).anchor" id = "lookupBtn" 
	   src="/kra-dev/kr/static/images/searchicon.gif" border="0" class="tinybutton" valign="middle" alt="Search on " title="Search on " />
   		
         	</label><br>
         	
         		<input type="hidden" id="researchAreaCode" name="researchAreaCode" value="${ResearchAreasForm.researchAreaCode}" />
         	
         	<!-- <p><a href="javascript:returnSponsor();window.close();"><b>return data</b></a> <a href="javascript:window.close()">Close</a></p> -->
         	
          	<script type="text/javascript">
          	  
          	     function returnResearchArea() {
          	            var researchAreaCode = document.getElementById("researchAreaCode").value
          	            //alert("return "+researchAreaCode)
          	         	window.opener.returnResearchArea(researchAreaCode);
          	     
          	     }
                 var lookupBtn=document.getElementById("lookupBtn");
                 //alert("refresh "+document.getElementById("methodToCall").value)
                 if (document.getElementById("methodToCall").value != "refresh") {
                 	lookupBtn.click();
                 } else {
                 	returnResearchArea();
                 	window.close();
                 }
            </script>
</kul:page>
