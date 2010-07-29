<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<xsl:for-each select="protocols/protocol">
			<xsl:variable name="documentNumber">
				<xsl:value-of select="documentNumber" />
			</xsl:variable>

			The IRB protocol number
			<a title="" target="_self"
				href="../kew/DocHandler.do?command=displayDocSearchView&amp;docId={$documentNumber}">
				<xsl:value-of select="protocolNumber" />
			</a>
			, Principal Investigator
			<xsl:value-of select="pi/firstName" />
			<xsl:text> </xsl:text>
			<xsl:value-of select="pi/lastName" />
			is notifying IRB.
			<br />
			There is change to this protocol.
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>
