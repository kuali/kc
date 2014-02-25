<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<xsl:variable name="documentNumber">
			<xsl:value-of select="Protocol/ProtocolMasterData/DocumentNumber" />
		</xsl:variable>

        Funding source {FUNDING_TYPE} {ACTION} 
		 protocol 
		<a title="" target="_self"
			href="../kew/DocHandler.do?command=displayDocSearchView$amp;docId={$documentNumber}">
			<xsl:value-of select="Protocol/ProtocolMasterData/ProtocolNumber" />.
		</a>
	</xsl:template>
</xsl:stylesheet>