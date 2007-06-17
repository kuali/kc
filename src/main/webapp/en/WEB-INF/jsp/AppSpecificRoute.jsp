<%@ taglib uri="../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../tld/c.tld" prefix="c" %>
<%@ taglib uri="../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../tld/displaytag.tld" prefix="display-el" %>

<tr>
  <td class="datacell"> 
    <table width="100%" cellpadding=0 cellspacing=0>
      <tr>
        <td colspan=3> &nbsp;</td>
      </tr>
       
      <tr>
        <td width="10%">&nbsp; </td>
        <td> 
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
            <tr>
              <td class="thnormal" colspan=2 align=center height=15><strong>Adhoc Route: </strong></td>
            </tr>

  		    <tr>
    		  <td class="thnormal" width="45%" align=right valign=top>*Recipient Type:</td>
			  <td class="datacell" width="55%">
				<html-el:radio property="appSpecificRouteRecipientType" value="person" onclick="javascript:appSpecificRouteRecipientLookup();"/>person
			    <html-el:radio property="appSpecificRouteRecipientType" value="workgroup" onclick="javascript:appSpecificRouteRecipientLookup();"/>workgroup
				<html-el:hidden property="appSpecificRouteRecipient.type" /> 
			  </td>
       		</tr>
  
  		    <tr>
			  <td class="thnormal" align="right">*Network ID or Workgroup Name: </td>
			  <td class="datacell">
				<span><html-el:text property="appSpecificRouteRecipient.id" value="${ActionForm.appSpecificRouteRecipient.id}"/></span>
				
				<c:if test="${ActionForm.appSpecificRouteRecipientType == 'person'}">
				  <c:set var="personLookup" value="display:inline;"/>
				  <c:set var="workgroupLookup" value="display:none;"/>
				</c:if>
					
				<c:if test="${ActionForm.appSpecificRouteRecipientType == 'workgroup'}">
				  <c:set var="personLookup" value="display:none;"/>
				  <c:set var="workgroupLookup" value="display:inline;"/>
				</c:if>	
			
				<span id="personLookup" style='<c:out value="${personLookup}" />'>
                  <html-el:image property="methodToCall.performLookup" src="${resourcePath}images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'UserLookupableImplService';document.forms[0].elements['lookupType'].value = 'appSpecificPersonId';"/>
				</span>
				<span id="workgroupLookup" style='<c:out value="${workgroupLookup}" />'>
                  <html-el:image property="methodToCall.performLookup" src="${resourcePath}images/searchicon.gif" alt="search" align="absmiddle" onclick="document.forms[0].elements['lookupableImplServiceName'].value = 'WorkGroupLookupableImplService';document.forms[0].elements['lookupType'].value = 'appSpecificWorkgroupId';"/>
				</span>
			  </td>
  			 </tr>

  			 <tr>
			  <td class="thnormal" align="right">*Action Request:</td>
			  <td class="datacell">
	 		  <%-- <html-el:select property="appSpecificRouteRecipient.actionRequested"> --%>
 	 			<html-el:select property="appSpecificRouteActionRequestCd" value="${ActionForm.appSpecificRouteActionRequestCd}"> 
	    		<c:set var="actionRequestCodes" value="${ActionForm.appSpecificRouteActionRequestCds}"/>
	    		<html-el:options collection="actionRequestCodes" property="key" labelProperty="value"/>
	  			</html-el:select>
			  </td>
  			 </tr>
  
  			 <tr>
    		  <td height=30 class="thnormal" colspan=2 align=center><html-el:image property="methodToCall.routeToAppSpecificRecipient" src="${resourcePath}images/tinybutton-routerecpt.gif" alt="Route To Recipient" align="absmiddle" /></td>
  		 	 </tr>
  		 	 
			<c:if test="${!empty ActionForm.appSpecificRouteList}">
			<tr>
    		  <td colspan=2 class="datacell">
      			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t">
  			      <tr>
    		       <td height=15 class="datacell" colspan=4 align=center><strong>List of App Specific Route Recipients</strong></td>
  			      </tr> 
        		  <tr>
          			<td class="datacell"><b>Recipient Type</b></td>
          			<td class="datacell"><b>Network ID or Workgroup Name</b></td>
          			<td class="datacell"><b>Action Requested</b></td>
          			<td class="datacell"><b>Delete</b></td>
        		  </tr>  

     			  <logic-el:iterate name="ActionForm" id="recipient" property="appSpecificRouteList" indexId="ctr">
          		  <tr>
            		<td class="datacell"><c:out value="${recipient.type}" /></td>
            		<td class="datacell"><c:out value="${recipient.id}" /></td> 
            		<td class="datacell"><c:out value="${recipient.actionRequestedValue}" /></td>
            		<td class="datacell"><html-el:image src="${resourcePath}images/tinybutton-remreci.gif" align="absmiddle" property="methodToCall.removeAppSpecificRecipient" onclick="removedAppSpecificRecipient.value=${ctr}" /></td>
            		<html-el:hidden property="appSpecificRoute[${ctr}].type" /> 
            		<html-el:hidden property="appSpecificRoute[${ctr}].id" /> 
            		<html-el:hidden property="appSpecificRoute[${ctr}].actionRequested" /> 
          		  </tr>
        		  </logic-el:iterate>     
        		  <html-el:hidden property="removedAppSpecificRecipient" />  		  
      			</table>
    		  </td>
  		    </tr>	
  		  </c:if>
  		  
		  </table>
        </td>
        <td width="10%"> &nbsp; </td> 
      </tr>
   
      <tr>
        <td colspan=3> &nbsp;&nbsp; </td>
      </tr>
    </table>
  </td>
</tr>

<html-el:hidden property="appSpecificRouteRecipient.type" />
<html-el:hidden property="appSpecificRouteRecipient.actionRequested" />
<html-el:hidden property="recipientIndex" />