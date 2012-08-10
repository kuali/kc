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

<channel:portalChannelTop channelTitle="Pre-Submission Compliance" />
<div class="body">
  <p>
    <strong>Conflict of Interest</strong>
  </p>
  <table border="0" cellpadding="2" cellspacing="0">
    <tr>
      <td nowrap class="disabled-text">Disclosure</td>
      <td>
        <img src="static/images/pixel_clear.gif" alt="add" width="16" height="16" border="0" align="absmiddle">
     	<portal:portalLink displayTitle="false" title="Disclosure" 
		    url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.coi.CoiDisclosure&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" >
	      <img src="static/images/searchicon.gif" alt="lookup" width="16" border="0" align="absmiddle">
        </portal:portalLink>
      </td>
    </tr>

    <tr>
      <td nowrap class="disabled-text">Event Disclosures</td>
      <td>
        <img src="static/images/pixel_clear.gif" alt="add" width="16" height="16" border="0" align="absmiddle">
     	<portal:portalLink displayTitle="false" title="Event disclosures" 
		      url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.coi.CoiEventDisclosure&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" >
          <img src="static/images/searchicon.gif" alt="lookup" width="16" border="0" align="absmiddle">
        </portal:portalLink>
      </td>
      
    </tr>
    
    <tr>
      <td nowrap class="disabled-text">Non Project Event Disclosures</td>
      <td>
        <img src="static/images/pixel_clear.gif" alt="add" width="16" height="16" border="0" align="absmiddle">
     	<portal:portalLink displayTitle="false" title="Non project event disclosures" 
		      url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.coi.CoiNonProjectEventDisclosure&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" >
          <img src="static/images/searchicon.gif" alt="lookup" width="16" border="0" align="absmiddle">
        </portal:portalLink>
      </td>     
    </tr>
    
    <tr>
      <td nowrap class="disabled-text">All My Disclosure Reviews</td>
      <td>
        <img src="static/images/pixel_clear.gif" alt="add" width="16" height="16" border="0" align="absmiddle">
     	<portal:portalLink displayTitle="false" title="My Disclosure Reviews" 
		      url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.coi.CoiDisclosureReviews&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" >
          <img src="static/images/searchicon.gif" alt="lookup" width="16" border="0" align="absmiddle">
        </portal:portalLink>
      </td>
      
    </tr>
    
    <tr>
      <td nowrap class="disabled-text">Submitted Disclosures</td>
      <td>
        <img src="static/images/pixel_clear.gif" alt="add" width="16" height="16" border="0" align="absmiddle">
     	<portal:portalLink displayTitle="false" title="Submitted disclosures" 
		      url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.coi.CoiSubmittedDisclosure&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" >
          <img src="static/images/searchicon.gif" alt="lookup" width="16" border="0" align="absmiddle">
        </portal:portalLink>
      </td>
      
    </tr>
    
    
    <tr>
      <td nowrap class="disabled-text">Annual Event Disclosures</td>
      <td>
        <img src="static/images/pixel_clear.gif" alt="add" width="16" height="16" border="0" align="absmiddle">
     	<portal:portalLink displayTitle="false" title="Annual event disclosures" 
		      url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.coi.CoiAnnualEventDisclosure&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" >
          <img src="static/images/searchicon.gif" alt="lookup" width="16" border="0" align="absmiddle">
        </portal:portalLink>
      </td>
      
    </tr>
    
     <tr>
      <td nowrap class="disabled-text">Undisclosed Events</td>
      <td>
        <img src="static/images/pixel_clear.gif" alt="add" width="16" height="16" border="0" align="absmiddle">
     	<portal:portalLink displayTitle="false" title="Undisclosed Events" 
		      url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=search&businessObjectClassName=org.kuali.kra.coi.CoiDisclosureUndisclosedEvents&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" >
          <img src="static/images/searchicon.gif" alt="lookup" width="16" border="0" align="absmiddle">
        </portal:portalLink>
      </td>
      
    </tr>   
  </table>
  <p>
    <strong>Protocols</strong>
  </p>
  <table border="0" cellpadding="2" cellspacing="0">
   <tr>
    <td nowrap class="disabled-text">Animals</td>
    <td>
      <portal:portalLink displayTitle="false" title="IACUC" url="${ConfigProperties.application.url}/iacucProtocolProtocol.do?methodToCall=docHandler&command=initiate&docTypeName=IacucProtocolDocument">
      	<img src="static/images/add.png" alt="add" width="16" height="16" border="0" align="absmiddle">
      </portal:portalLink>
      <portal:portalLink displayTitle="false" title="IACUC" 
		   url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.iacuc.IacucProtocol&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" >
      	<img src="static/images/searchicon.gif" alt="lookup" width="16" border="0" align="absmiddle">
      </portal:portalLink>
    </td>
  </tr>
  <tr>
    <td nowrap class="disabled-text">Biosafety</td>
    <td>
      <img src="static/images/add1.png" alt="add" width="16" height="16" border="0" align="absmiddle">
      <img src="static/images/searchicon1.gif" alt="lookup" width="16" height="16" align="absmiddle">
    </td>
  </tr>
  <tr>
    <td nowrap class="disabled-text">Chemical Safety</td>
    <td>
      <img src="static/images/add1.png" alt="add" width="16" height="16" border="0" align="absmiddle">
      <img src="static/images/searchicon1.gif" alt="lookup" width="16" height="16" align="absmiddle">
    </td>
  </tr>
  <tr>
    <td nowrap class="disabled-text">Human Participants</td>
    <td>
      <portal:portalLink displayTitle="false" title="Protocol" url="${ConfigProperties.application.url}/protocolProtocol.do?methodToCall=docHandler&command=initiate&docTypeName=ProtocolDocument"><img src="static/images/add.png" alt="add" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>
      <portal:portalLink displayTitle="false" title="Protocol" 
		   url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.Protocol&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" >
            <img src="static/images/searchicon.gif" alt="lookup" width="16" border="0" align="absmiddle"></portal:portalLink>
    </td>
  </tr>
  <tr>
    <td nowrap class="disabled-text">Radiation Safety</td>
    <td>
      <img src="static/images/add1.png" alt="add" width="16" height="16" border="0" align="absmiddle">
      <img src="static/images/searchicon1.gif" alt="lookup" width="16" height="16" align="absmiddle">
    </td>
  </tr>
  </table>
</div>
<channel:portalChannelBottom />
