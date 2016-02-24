<?xml version="1.0" encoding="UTF-8"?>
<!--
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
 -->
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<xsl:variable name="documentNumber">
			<xsl:value-of select="Protocol/ProtocolMasterData/DocumentNumber" />
		</xsl:variable>

		The IRB protocol number
		<a title="" target="_self"
			href="../kew/DocHandler.do?command=displayDocSearchView$amp;docId={$documentNumber}">
			<xsl:value-of select="Protocol/ProtocolMasterData/ProtocolNumber" />
		</a>

		, Principal Investigator
		<xsl:for-each select="Protocol/Investigator[PI_flag = 'true']">
			<xsl:value-of select="Person/Firstname" />
			<xsl:text> </xsl:text>
			<xsl:value-of select="Person/LastName" />
		</xsl:for-each>
		has had the action "Request To Close" performed on it.
		<br />
		The action was executed by
		<xsl:value-of select="Protocol/user/firstName" />
		<xsl:text> </xsl:text>
		<xsl:value-of select="Protocol/user/lastName" />
		. Additional information and further actions can be accessed through
		the Kuali Coeus system.

	</xsl:template>
</xsl:stylesheet>