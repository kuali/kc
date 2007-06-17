<%@ taglib uri="../../tld/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="../../tld/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="../../tld/struts-logic-el.tld" prefix="logic-el"%>
<%@ taglib uri="../../tld/c.tld" prefix="c" %>
<%@ taglib uri="../../tld/fmt.tld" prefix="fmt" %>
<%@ taglib uri="../../tld/displaytag.tld" prefix="display-el" %>

<html>
  <head>
    <title>Route Queue Summary</title>
    <link href="css/screen.css" rel="stylesheet" type="text/css">
    <script language="JavaScript" src="scripts/en-common.js"></script>
    <script language="JavaScript" src="scripts/routequeue-common.js"></script>
  </head>
  
  <body>
  
    <html-el:form action="/RouteQueue.do">
    <html-el:hidden name="RouteQueueForm" property="methodToCall" />
    <html-el:hidden name="RouteQueueForm" property="routeQueueId" />
    
    <table width="100%" border=0 cellpadding=0 cellspacing=0 class="headercell1">
      <tr>
        <td><img src="images/wf-logo.gif" alt="OneStart Workflow" width=150 height=21 hspace=5 vspace=5></td>
        <td width="90%">
          <a href="RouteQueue.do?methodToCall=start">Route Queue</a>&nbsp;|&nbsp;<a href="RouteQueue.do?methodToCall=addNew">Queue new Document</a>
        </td>
      </tr>
    </table>

    <table width="100%" border=0 cellpadding=0 cellspacing=0>
      <tr>
        <td>
		  <table width="100%" border=0 cellpadding=0 cellspacing=0 bgcolor="#FFFFFF">
            <tr>
              <td width="20"><img src="images-channelglobal/pixel_clear.gif" alt="" width="1" height="1"><img src="images/pixel_clear.gif" alt="" width=20 height=20></td>
	          <td>
	            <table width="100%" border=0 cellspacing=0 cellpadding=0>
	              <tr>
	                <td height=30><strong>Route Queue Summary</strong></td>
	              </tr>
	            </table>
	          </td>
	          <td width="20"><img src="images/pixel_clear.gif" alt="" width=20 height=20></td>
	        </tr>
	      </table>
	    </td>
	  </tr>
	  
	  <tr>
	    <td>
		  <jsp:include page="../WorkflowMessages.jsp" flush="true" />
	    </td>
	  </tr>
	
	  <tr>
	    <td>    
          <table width="100%" border=0 cellspacing=0 cellpadding=0>
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
            <tr>
              <td width=20><img src="images/pixel_clear.gif" alt="" width=20 height=20></td>
              <td>
                <table width="100%" border=0 cellspacing=0 cellpadding=0 class="bord-r-t">
                  <tr>
                    <td>
                      <jsp:include page="../routequeue/RouteQueueDisplay.jsp" flush="true" />
                    </td>
                  </tr>     
	              <tr>
		            <td class="thnormal" colspan="3" align="center">
                      <a href="javascript: submitQueueId('copy', '<c:out value="${RouteQueueForm.routeQueue.routeQueueId}" />')">
                         <img src="images/buttonsmall_copytonew.gif" />
                      </a>
		            </td>
	              </tr>
                </table>
      		  </td>
      		  <td width=20><img src="images/pixel_clear.gif" alt="" width=20 height=20></td>
    		</tr>
    	  </table>
    	</td>
      </tr>  
    </table>
    </html-el:form>

    <jsp:include page="../BackdoorMessage.jsp" flush="true"/>  
  
  </body>
</html>
