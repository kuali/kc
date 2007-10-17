<td width="165px" valign="top">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" cols="1" summary="">
	<tbody>
		<tr>
			<td><img src="images/topleftcorner.gif" height="9" width="6"></td>
			<td class="topborder" width="100%"><img	src="images/transparent_002.gif" height="1" width="1"></td>
			<td><img src="images/toprightcorner.gif" height="9" width="6"></td>
		</tr>
		<tr>
			<td class="headerleftborder"><img
				src="images/transparent_002.gif" alt="" width="6" height="1"></td>
			<td class="background-semidark" nowrap="nowrap"><span
				class="channel-title">Menu</span></td>
			<td class="headerrightborder"><img
				src="images/transparent_002.gif" alt="" width="6" height="1"></td>

		</tr>
		<tr>
			<td><img src="images/headerbottomleft.gif" alt="" width="6"
				height="8"></td>
			<td class="headerbottomborder"><img
				src="images/transparent_002.gif" alt="" width="1" height="1"></td>
			<td><img src="images/headerbottomright.gif" alt="" width="6"
				height="8"></td>
		</tr>
		<tr>
			<td><img src="images/channeltopleft.gif" alt="" width="6"
				height="7"></td>
			<td class="channeltopborder"><img
				src="images/transparent_002.gif" alt="" width="1" height="7"></td>

			<td><img src="images/channeltopright.gif" alt="" width="6"
				height="7"></td>
		</tr>
		<tr>
			<td class="channelleftborder"><img
				src="images/transparent_002.gif" alt="" width="6" height="1"></td>
			<td class="background-content">
			<table width="100%" border="0" cellpadding="3" cellspacing="0"
				summary="">
				<tbody>
					<tr>
						<td width="100%"><a href="HomePage.form" >My Notifications</a></td>

						<td><img src="images/transparent_002.gif" height="1"
							width="1"></td>
					</tr>
					<tr>
						<td width="100%"><a href="NotificationsSent.form" >My Sent Notifications</a></td>

						<td><img src="images/transparent_002.gif" height="1"
							width="1"></td>
					</tr>
                    <tr>
						<td width="100%"><a href="Search.form" >Search</a></td>

						<td><img src="images/transparent_002.gif" height="1"
							width="1"></td>
					</tr>
               		<tr>
                        <td width="100%"><a href="LookupUsers.form" >Look Up Users</a></td>

                        <td><img src="images/transparent_002.gif" height="1"
                           width="1"></td>
                    </tr>
                    <tr>
                        <td width="100%"><a href="LookupWorkgroups.form" >Look Up Workgroups</a></td>

                        <td><img src="images/transparent_002.gif" height="1"
                           width="1"></td>
                    </tr>
                    <tr>
                    <td><img src="images/transparent_002.gif" height="1" width="1"></td>
                    <tr>
                    <tr>
                    <td><img src="images/transparent_002.gif" height="1" width="1"></td>
                    <tr>
                    <tr>
                    <td><img src="images/transparent_002.gif" height="1" width="1"></td>
                    <tr>
                    <tr>
                        <td width="100%"><b>My Preferences<b></td>
                        <td><img src="images/transparent_002.gif" height="1" width="1"></td>
                    </tr>
                    <tr>
                        <td width="100%"><img src="images/transparent_002.gif" height="1"
                           width="4"><a href="DisplayUserPreferences.form" >Channel Subscriptions</a></td>

                        <td><img src="images/transparent_002.gif" height="1"
                           width="1"></td>
                    </tr>
                    <tr>
                        <td width="100%"><img src="images/transparent_002.gif" height="1"
                           width="4"><a href="DisplayDelivererConfigurationForm.form" >Delivery Types</a></td>

                        <td><img src="images/transparent_002.gif" height="1"
                           width="1"></td>
                    </tr>
                    <tr>
                        <td width="100%"><img src="images/transparent_002.gif" height="1"
                           width="4"><a href="DisplayActionListPreferences.form" >Action List Preferences</a></td>

                        <td><img src="images/transparent_002.gif" height="1"
                           width="1"></td>
                    </tr>
                    					 
                    <tr>
                       <td><img src="images/transparent_002.gif" height="1" width="1"></td>
                    <tr>
                    <tr>
						<td><img src="images/transparent_002.gif" height="1" width="1"></td>
                    <tr>
                    <tr>
						<td><img src="images/transparent_002.gif" height="1" width="1"></td>
                    <tr>
                    <tr>
						<td width="100%"><b>Send Notifications<b></td>
                    	<td><img src="images/transparent_002.gif" height="1" width="1"></td>
                    </tr>
                    <tr>
                       <td width="100%"><img src="images/transparent_002.gif" height="1"
                           width="4"><a href="SendSimpleNotificationMessage.form" >Simple Notification</a></td>
                       <td><img src="images/transparent_002.gif" height="1" width="1"></td>
                    </tr>
                    <tr>
                       <td width="100%"><img src="images/transparent_002.gif" height="1"
                           width="4"><a href="SendEventNotificationMessage.form" >Event Notification</a></td>
                       <td><img src="images/transparent_002.gif" height="1" width="1"></td>
                    </tr>
                    <tr>
						<td><img src="images/transparent_002.gif" height="1" width="1"></td>
                    <tr>
                    <tr>
						<td><img src="images/transparent_002.gif" height="1" width="1"></td>
                    <tr>
                    <tr>
						<td><img src="images/transparent_002.gif" height="1" width="1"></td>
                    <tr>
					<tr>

                    <c:if test="${requestScope['userIsAdmin']}">
                        <tr>
    						<td width="100%"><b>Administration<b></td>
                        	<td><img src="images/transparent_002.gif" height="1" width="1"></td>
                        </tr>
    
                        <tr>
                            <td width="100%"><img src="images/transparent_002.gif" height="1"
                               width="4"><a href="ContentTypeManager.form" >Manage Content Types</a></td>
    
                            <td><img src="images/transparent_002.gif" height="1"
                               width="1"></td>
                        </tr>
                    </c:if>
				</tbody>
			</table>
			</td>
			<td class="channelrightborder"><img
				src="images/transparent_002.gif" alt="" width="1" height="1"></td>
		</tr>
		<tr>
			<td><img src="images/bottomleftcorner.gif" alt="" width="6"
				height="6"></td>

			<td class="bottomborder"><img
				src="images/transparent_002.gif" alt="" width="1" height="1"></td>
			<td><img src="images/bottomrightcorner.gif" alt="" width="6"
				height="6"></td>
		</tr>
	</tbody>
</table>
</td>