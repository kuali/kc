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
		      url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.coi.CoiDisclosureUndisclosedEvents&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" >
          <img src="static/images/searchicon.gif" alt="lookup" width="16" border="0" align="absmiddle">
        </portal:portalLink>
      </td>
      
    </tr> 

	<tr>
      <td nowrap class="disabled-text">Open and In Progress Disclosures</td>
      <td>
        <img src="static/images/pixel_clear.gif" alt="add" width="16" height="16" border="0" align="absmiddle">
     	<portal:portalLink displayTitle="false" title="Open and In Progress Disclosures" 
		      url="${ConfigProperties.application.url}/coiSearchOpen.do?methodToCall=openCustomSearch&docFormKey=88888888" >
          <img src="static/images/searchicon.gif" alt="lookup" width="16" border="0" align="absmiddle">
        </portal:portalLink>
      </td>
      
    </tr>
    <tr>
      <td nowrap class="disabled-text">Person Search</td>
      <td>
        <img src="static/images/pixel_clear.gif" alt="add" width="16" height="16" border="0" align="absmiddle">
     	<portal:portalLink displayTitle="false" title="Person Search" 
		      url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.coeus.common.framework.person.KcPerson&docFormKey=88888888&includeCustomActionUrls=false&returnLocation=${ConfigProperties.application.url}/coiPersonSearch.do&hideReturnLink=false&showMaintenanceLinks=false&refreshCaller=CoiDisclosurePersonSearch" >
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
    <td style="padding-left: 10px"/>
    <td>
      <portal:portalLink displayTitle="false" title="IACUC" url="${ConfigProperties.application.url}/iacucProtocolProtocol.do?methodToCall=docHandler&command=initiate&docTypeName=IacucProtocolDocument">
      	<img src="static/images/add.png" alt="add" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>
      <portal:portalLink displayTitle="false" title="IACUC" 
		   url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.iacuc.IacucProtocol&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" >
      	<img src="static/images/searchicon.gif" alt="lookup" width="16" border="0" align="absmiddle">
      </portal:portalLink>
    </td>
  </tr>
  <tr>
    <td nowrap class="disabled-text">Human Participants</td>
    <td style="padding-left: 10px"/>
    <td>
      <portal:portalLink displayTitle="false" title="Protocol" url="${ConfigProperties.application.url}/protocolProtocol.do?methodToCall=docHandler&command=initiate&docTypeName=ProtocolDocument"><img src="static/images/add.png" alt="add" width="16" height="16" border="0" align="absmiddle"></portal:portalLink>
      <portal:portalLink displayTitle="false" title="Protocol" 
		   url="${ConfigProperties.application.url}/kr/lookup.do?methodToCall=start&businessObjectClassName=org.kuali.kra.irb.Protocol&docFormKey=88888888&returnLocation=${ConfigProperties.application.url}/portal.do&hideReturnLink=true" >
            <img src="static/images/searchicon.gif" alt="lookup" width="16" border="0" align="absmiddle"></portal:portalLink>
    </td>
  </tr>
  </table>
</div>
<channel:portalChannelBottom />
