<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="Letter" page-height="11in" page-width="8.5in">
                    <fo:region-body region-name="only_region" margin="1in" background-color="#CCCCCC"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="Letter">
                <fo:flow flow-name="only_region">
                    <fo:block text-align="center">Committee - Roster</fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet> 