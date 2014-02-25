<?xml version="1.0" encoding="UTF-8"?>
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
		has had the action "IRB Acknowledgement" performed on it.
		<br />
		The action was executed by
		<xsl:value-of select="Protocol/user/firstName" />
		<xsl:text> </xsl:text>
		<xsl:value-of select="Protocol/user/lastName" />
		. Additional information and further actions can be accessed through
		the Kuali Coeus system.

	</xsl:template>
</xsl:stylesheet>