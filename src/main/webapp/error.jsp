<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>
<%
	java.lang.Throwable ex = (java.lang.Throwable) request.getAttribute("org.apache.struts.action.EXCEPTION");
	java.io.StringWriter stackTraceStr = new java.io.StringWriter();
	java.io.PrintWriter newWriter = new java.io.PrintWriter(stackTraceStr);
	ex.printStackTrace(newWriter);
	String formattedExceptionTrace = stackTraceStr.toString().replaceAll("\tat", "<br/>\tat");
	pageContext.setAttribute("ex", ex);
	pageContext.setAttribute("exceptionTrace", formattedExceptionTrace);
	ex.printStackTrace(System.out);
	System.out.println("---------------------------------------------------------------------------");
	System.out.println(stackTraceStr.toString());
%>

<kul:page htmlFormAction="exception" docTitle="Error Page" transactionalDocument="true" showTabButtons="true">
   <br/>
		<div class="msg-excol">
		  <div class="left-errmsg">
			<table width="100%" border=0 cellspacing=0 cellpadding=0 align="center">
				<tr width="100%">
					<td align="center" class="error"><html:errors/></td>
				</tr>
				<tr width="100%">
					<td align="center" class="error"><c:out value="${ex}" /></td>  
				</tr>
			</table>
			</div>
		</div> 
		
		<br/><br/>
		<c:set var="action" value="exception" />
		
		<div id="workarea">  
		
			<kul:tab tabTitle="Exception Trace" defaultOpen="false" transparentBackground="true" tabErrorKey="">
			<div class="tab-container" align="center">
				<table cellpadding="0" cellspacing="0" width="100%" class="datatable" title="View Exception Trace" 
				summary="View Exception Trace">
					<tr>
						<td><c:out value="${exceptionTrace}" escapeXml="false" /></td>
					</tr>
				</table>
			</div>
			</kul:tab>
		
			<kul:panelFooter />
		</div>

</kul:page>   
