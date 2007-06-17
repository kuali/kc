<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<tr>
  <td><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
  <td> 
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t" align=center>
	  <tr>
		<td class="thnormal" colspan=2 align=center height=30><strong>Route Node Instances</strong></td>
	  </tr>
	  	  
	  <c:choose> 	
	  <c:when test="${empty DocumentOperationForm.routeNodeInstances}">
	    <tr><td class="datacell" colspan=2 align=center height=15>None</td></tr>
	  </c:when>	  		 
	  <c:otherwise>
	  <logic-el:iterate id="routeNodeInstance" name="DocumentOperationForm" property="routeNodeInstances" indexId="ctr">
	  <html-el:hidden property="routeNodeInstance[${ctr}].routeNodeInstanceId" />  
	  <html-el:hidden property="routeNodeInstance[${ctr}].documentId" />
	  <html-el:hidden property="routeNodeInstance[${ctr}].lockVerNbr" />  
	  <tr>
	    <td width="33%" class="headercell3-b-l" align=right><b> Route Node Instance ID: </b><c:out value="${routeNodeInstance.routeNodeInstanceId}" /> </td>
	    <td width="66%" class="headercell3-b-l">
	      <html-el:radio property="routeNodeInstanceOp[${ctr}].value" value="add"/>Add &nbsp;&nbsp;
	      <html-el:radio property="routeNodeInstanceOp[${ctr}].value" value="update"/>Update &nbsp;&nbsp;
	      <html-el:radio property="routeNodeInstanceOp[${ctr}].value" value="delete"/>Delete&nbsp;&nbsp;
	      <html-el:radio property="routeNodeInstanceOp[${ctr}].value" value="noop"/>No Operation&nbsp;&nbsp;
	      <html-el:hidden property="routeNodeInstanceOp[${ctr}].index" />
	    </td>
	  </tr>
	   <tr>
  	    <td width="33%" align=right class="thnormal">Active Indicator:</td>
  	    <td width="66%" class="datacell"><html-el:text name="DocumentOperationForm" property="routeNodeInstance[${ctr}].active" /></td>
  	  </tr>  	 
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Complete Indicator:</td>
  	    <td width="66%" class="datacell"><html-el:text name="DocumentOperationForm" property="routeNodeInstance[${ctr}].complete" /></td>
  	  </tr>  
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Initial Indicator:</td>
  	    <td width="66%" class="datacell"><html-el:text name="DocumentOperationForm" property="routeNodeInstance[${ctr}].initial"/></td>
  	  </tr> 
  	  <!-- 
  	  <tr>
  	    <td width="33%" align=right class="thnormal">Route Node States:</td>
  	    <td width="66%" class="datacell">  	       
  	       <c:choose> 	
	       <c:when test="${empty routeNodeInstance.state}">
	    		None
	  	   </c:when>	  	
	  	   <c:otherwise>
  	       <logic-el:iterate id="routeNodeState" name="DocumentOperationForm" property="routeNodeInstance[${ctr}].state" indexId="ctr1">
  	         <html-el:hidden property="routeNodeInstance[${ctr}].state[${ctr1}].nodeStateId"/>
  	         <html-el:hidden property="routeNodeInstance[${ctr}].state[${ctr1}].lockVerNbr"/>
  	         Route Node State: <c:out value="${routeNodeState.nodeStateId}"/><br>
  	         Key: &nbsp;&nbsp;&nbsp;&nbsp;<html-el:text property="routeNodeInstance[${ctr}].state[${ctr1}].key"/><br>
  	         Value: &nbsp;&nbsp;<html-el:text property="routeNodeInstance[${ctr}].state[${ctr1}].value"/><br>
  	       </logic-el:iterate>
  	       </c:otherwise>
  	       </c:choose>
  	    </td>
  	  </tr>
	 -->
	  </logic-el:iterate>
	  </c:otherwise>
      </c:choose>
	</table>
  </td>
  <td width="20" height="30">&nbsp;</td>
</tr>




