 <%--
 Copyright 2005-2013 The Kuali Foundation

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

<html>
<kul:page lookup="true" 
          docTitle="Add Sponsor Hierarchy" 
          transactionalDocument="false" 
          htmlFormAction="sponsorLookup">

	<input type="hidden" id="mapKey"
		name="mapKey" 
		value="${SponsorHierarchyForm.mapKey}" />

	<input type="hidden" id="methodToCall"
		name="methodToCall" value="${SponsorHierarchyForm.methodToCall}"/>
    <input type="hidden" id="anchor"
        name="anchor" value="topOfForm"/>

 		<!--  <label>Sponsor Code Search</label> -->
   		<label>
   			<html:image styleId="lookupBtn" tabindex="1000000" property="methodToCall.performLookup.(!!org.kuali.kra.bo.Sponsor!!).(:;newSponsors[0];:).((%true%)).anchor"
	           src="${ConfigProperties.kr.externalizable.images.url}searchicon.gif" border="0" alt="Multiple Value Search on " title="Multiple Value Search on " />
   		
         	</label><br>
         	
         		<input type="hidden" id="selectedSponsors" name="selectedSponsors" value="${SponsorHierarchyForm.selectedSponsors}" />
         	
         	<!-- <p><a href="javascript:returnSponsor();window.close();"><b>return data</b></a> <a href="javascript:window.close()">Close</a></p> -->
</kul:page>
            
<script type="text/javascript">
  
     function returnSponsor() {
            var sponsors = document.getElementById("selectedSponsors").value
            var mapKey = document.getElementById("mapKey").value
           // alert("return sponsor "+mapKey+sponsors)
            window.opener.returnSponsor(sponsors, mapKey);
     
     }
     var lookupBtn=document.getElementById("lookupBtn");
     if (document.getElementById("methodToCall").value != "refresh") {
         var mapKey = document.getElementById("mapKey").value
        // alert("not refres "+mapKey)
        lookupBtn.click();
     } else {
       //  alert("refresh")
        returnSponsor();
        window.close();
     }
</script>

</html>
