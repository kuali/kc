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
<!DOCTYPE html>
<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css"
<c:set var="notificationAttributes" value="${DataDictionary.KcNotification.attributes}" />
<c:set var="notification" value="${KualiForm.disclosureHelper.viewNotification}" />
<html>
   <head>
		<link rel="stylesheet" href="css/jquery/new_kuali.css" type="text/css" />
		<link rel="stylesheet" href="css/jquery/kuali-stylesheet.css"
			type="text/css" />
		<script>var jsContextPath = "${pageContext.request.contextPath}";</script>
		<title>Disclosure Notification</title>
	</head>

	<body>
	    <table border="1" style="padding: 0px; border-collapse: collapse;">
            <tbody>
                <tr>
                    <td style="background-color: rgb(195, 195, 195); font-weight: bold;" colspan="5">Notification Details:</td>
		        </tr>
			    <tr>
		            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Date Created</td>
          			<td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Recipients</td>
		            <td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Subject</td>
           			<td class="infoline fineprint sequencetd" style="font-weight: bold; text-align: center; color: rgb(51, 51, 51);">Message</td>
        		</tr>
				<tr>
	    			<td>
		   				<div align="center">
		    				<fmt:formatDate value="${notification.createTimestamp}" pattern="MM/dd/yyyy KK:mm a" /> 
						</div>
					</td>    
        			<td align="center" valign="middle">
			    		<div align="center">
				    		${notification.recipients}
	           			</div>
		   	    	</td>
					<td align="left" valign="middle">
           				<div align="left"> 
               				${notification.subject}
		            	</div>
    		    	</td>
					<td align="center" valign="middle">
           				<div align="left">
		               		${notification.message}
						</div>
					</td>
	    		</tr>
	    	</tbody>
		</table>
		<kul:csrf />
	</body>
</html>
