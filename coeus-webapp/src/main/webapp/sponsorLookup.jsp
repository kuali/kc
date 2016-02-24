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


   		<label>
   			<html:image styleId="lookupBtn" tabindex="1000000" property="methodToCall.performLookup.(!!org.kuali.coeus.common.framework.sponsor.Sponsor!!).(:;newSponsors[0];:).((%true%)).anchor"
	           src="${ConfigProperties.kr.externalizable.images.url}searchicon.gif" border="0" alt="Multiple Value Search on " title="Multiple Value Search on " />
   		
         	</label><br>
         	
         		<input type="hidden" id="selectedSponsors" name="selectedSponsors" value="${SponsorHierarchyForm.selectedSponsors}" />
         	
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
