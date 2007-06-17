<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<script language="JavaScript" src="scripts/documentoperation-common.js"></script>

<tr>
  <td><img src="images/pixel_clear.gif" alt="" width="20" height="20"></td>
  <td> 
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="bord-r-t" align=center>
	  <tr>
		<td class="thnormal" colspan=2 align=center height=30><strong>Branch States</strong></td>
	  </tr>
	  <c:set var="branchStateIndex" value="0"/> 
	  <c:choose> 	
	  <c:when test="${empty DocumentOperationForm.branches}">
	     <tr><td class="datacell" colspan=2 align=center height=15>None</td></tr>
	  </c:when>	  		 
	  <c:otherwise>
	     <html-el:hidden property="branchStatesDelete" value=""/>
	  	 <logic-el:iterate id="branch" name="DocumentOperationForm" property="branches" indexId="ctr">
	  		<tr>
	    	  <td width="33%" class="headercell3-b-l" align=right><b> Branch ID: </b><c:out value="${branch.branchId}" /> </td>
	    	  <td width="66%" class="headercell3-b-l">
	      		<html-el:radio property="branchOp[${ctr}].value" value="update"/>Update &nbsp;&nbsp;
	      		<html-el:radio property="branchOp[${ctr}].value" value="noop"/>No Operation&nbsp;&nbsp;
	      		<html-el:hidden property="branchOp[${ctr}].index" />
	    	  </td>
	  		</tr>
	  		<tr>
        		<td width="33%" align=right class="thnormal">Branch Name:</td>
        		<td width="66%" class="datacell"><html-el:text property="branche[${ctr}].name"/></td>
      		</tr>
	  		<c:choose>
	  		<c:when test="${empty branch.branchState}">
	     	<tr>
  	    		<td width="33%" align=right class="thnormal">Branch States:</td>
  	        	<td width="66%" class="datacell">None</td>
         	</tr>  	 
     	 	</c:when>
     	 	<c:otherwise>
        <logic-el:iterate id="branchState" name="DocumentOperationForm" property="branche[${ctr}].branchState" indexId="ctr1">
        <tr>
        <c:set var="branchStateIndex" value="${branchStateIndex + 1}"/>	
           <td width="33%" align=right class="thnormal">Branch State ID: <c:out value="${branchState.branchStateId}"/>
           <td width="66%" class="datacell">
              Key: &nbsp;&nbsp;&nbsp;&nbsp;<html-el:text property="branche[${ctr}].docBranchState[${ctr1}].key"/>&nbsp;&nbsp;&nbsp;&nbsp; <html-el:button property="branchStateDeleteOp[${branchStateIndex}].value" value="delete ${branchState.branchStateId}" onclick="changeBranchValue(this,${ctr},${ctr1})"/><br>
  	          Value: &nbsp;&nbsp;<html-el:text property="branche[${ctr}].docBranchState[${ctr1}].value"/><br>
           </td>
        </tr>
        </logic-el:iterate>
      </c:otherwise>
     	 	</c:choose>
	  	</logic-el:iterate>
	  </c:otherwise>
	  </c:choose>	  
	  
	</table>
  </td>
  <td width="20" height="30">&nbsp;</td>
</tr>




