<%@ include file="/WEB-INF/jsp/kraTldHeader.jsp"%>

<c:set var="protocolDocumentAttributes" value="${DataDictionary.ProtocolDocument.attributes}" />
<c:set var="protocolAttributes" value="${DataDictionary.Protocol.attributes}" />
<c:set var="action" value="protocolActions" />

<kul:tab tabTitle="Summary, History, & Print" defaultOpen="false" tabErrorKey="">
	<div class="tab-container" align="left">
    	<kul:innerTab parentTab="Summary, History, & Print" defaultOpen="false" tabTitle="View Summary (Notified Committee 11/14/2008)">
            <table cellpadding="0" cellspacing="0">
                <tr>

                    <th style="text-align:right; width:135px;">
                        Protocol Number:
                    </th>
                    <td>
                        0809000039
                    </td>
                    <th style="text-align:right">
                        Application Date:
                    </th>
                    <td>

                        09/16/2002
                    </td>
                    <th rowspan="5">
                        <a href="#"><img src="../images/tinybutton-previous3.gif" alt="close" width="70" height="15" border="0" style="padding:2px;" /></a><br />
                        <a href="#"><img src="../images/tinybutton-next3.gif" alt="close" width="70" height="15" border="0" style="padding:2px;" /></a>
                    </th>
                </tr>
                <tr>
                    <th style="text-align:right">

                        Approval Date:
                    </th>
                    <td>
                        09/23/2008
                    </td>
                    <th style="text-align:right">
                        Expiration Date:
                    </th>
                    <td>
                        09/22/2009
                    </td>

                </tr>
                <tr>
                    <th style="text-align:right">
                        Status:
                    </th>
                    <td>
                        Active - Open to Enrollment
                    </td>
                    <th style="text-align:right">&nbsp;
                        Type:
                    </th>

                    <td>
                    	Standard
                    </td>
                </tr>
                <tr>
                    <th style="text-align:right; height:50px;">
                        Title:
                    </th>
                    <td colspan="3" style="text-align:left; vertical-align:top;">
                        My cool title to make provision for the use of parts of bodies of deceased starfish 
                        for therapeutic purposes and for purposes of cosmic education and research.
                    </td>

                </tr>
            </table>
    		
    		
    	</kul:innerTab>
    </div>	    
</kul:tab>
