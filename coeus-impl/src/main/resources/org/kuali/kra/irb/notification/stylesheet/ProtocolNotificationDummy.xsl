<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<xsl:for-each select="protocols/protocol">
<xsl:variable name="documentNumber"><xsl:value-of select="documentNumber"/></xsl:variable>

The IRB protocol number <a title="" target="_self" href="../kew/DocHandler.do?command=displayDocSearchView&amp;docId={$documentNumber}"> <xsl:value-of select="protocolNumber"/></a> , Principal Investigator <xsl:value-of select="pi/firstName"/><xsl:text> </xsl:text>
<xsl:value-of select="pi/lastName"/> has been <br/>
 withdrawn from review by 
 <xsl:for-each select="protocolReviewers/reviewer">
<xsl:variable name="i"><xsl:value-of select="idx"/></xsl:variable>
 <xsl:choose>
  <xsl:when test="$i = 0">
 <xsl:value-of select="fullName"/>
  </xsl:when>
  <xsl:otherwise>
  <xsl:text>, </xsl:text>
 <xsl:value-of select="fullName"/>
  </xsl:otherwise>
</xsl:choose>
 </xsl:for-each>
  .<br/>
 The protocol may now be modified and resubmitted.  
</xsl:for-each>
</xsl:template>
</xsl:stylesheet>
