<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:n1="http://irb.mit.edu/irbnamespace" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xsl:output version="1.0" method="xml" encoding="UTF-8" indent="no" />
    <xsl:variable name="XML1" select="/" />
    <xsl:variable name="fo:layout-master-set">
        <fo:layout-master-set>
            <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.6in" margin-right="0.6in">
                <fo:region-body margin-top="0.79in" margin-bottom="0.79in" />
            </fo:simple-page-master>
        </fo:layout-master-set>
    </xsl:variable>
    <xsl:template match="/">
        <xsl:variable name="maxwidth" select="7.30000"/>
        <fo:root>
            <xsl:copy-of select="$fo:layout-master-set" />
            <fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <xsl:for-each select="$XML1">
                            <xsl:variable name="tablewidth0" select="$maxwidth * 1.00000" />
                            <xsl:variable name="sumcolumnwidths0" select="0.04167" />
                            <xsl:variable name="defaultcolumns0" select="1" />
                            <xsl:variable name="defaultcolumnwidth0">
                                <xsl:choose>
                                    <xsl:when test="$defaultcolumns0 &gt; 0">
                                        <xsl:value-of select="($tablewidth0 - $sumcolumnwidths0) div $defaultcolumns0" />
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="0.000" />
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:variable>
                            <xsl:variable name="columnwidth0_0" select="$defaultcolumnwidth0" />
                            <fo:table table-layout="fixed" width="{$tablewidth0}in" border-collapse="separate" border-separation="0.04167in" font-size="14pt" font-weight="bold" color="black" display-align="center">
                                <fo:table-column column-width="{$columnwidth0_0}in" />
                                <fo:table-body font-size="14pt" font-weight="bold">
                                    <fo:table-row font-size="14pt" font-weight="bold">
                                        <fo:table-cell font-size="14pt" font-weight="bold" text-align="center" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <fo:inline font-size="14pt" font-weight="bold">
                                                    <xsl:text>Review Comments</xsl:text>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="14pt" font-weight="bold" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt" />
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                            <xsl:variable name="tablewidth1" select="$maxwidth * 1.00000" />
                            <xsl:variable name="sumcolumnwidths1" select="0.20833 + 0.04167 + 0.20833 + 0.04167 + 0.20833 + 0.04167 + 0.41667 + 0.04167" />
                            <xsl:variable name="factor1">
                                <xsl:choose>
                                    <xsl:when test="$sumcolumnwidths1 &gt; 0.00000 and $sumcolumnwidths1 &gt; $tablewidth1">
                                        <xsl:value-of select="$tablewidth1 div $sumcolumnwidths1" />
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="1.000" />
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:variable>
                            <xsl:variable name="columnwidth1_0" select="0.20833 * $factor1" />
                            <xsl:variable name="columnwidth1_1" select="0.20833 * $factor1" />
                            <xsl:variable name="columnwidth1_2" select="0.20833 * $factor1" />
                            <xsl:variable name="columnwidth1_3" select="0.41667 * $factor1" />
                            <fo:table table-layout="fixed" width="100%" border-collapse="separate" border-separation="0.04167in" font-size="9pt" color="black" display-align="center">
                                <fo:table-column column-width="20%" />
                                <fo:table-column column-width="30%" />
                                <fo:table-column column-width="20%" />
                                <fo:table-column column-width="30%" />
                                <fo:table-body font-size="9pt">
                                    <fo:table-row font-size="9pt">
                                        <fo:table-cell font-size="9pt" padding="2pt" text-align="left" display-align="before">
                                            <fo:block>
                                                <fo:inline font-weight="bold">
                                                    <xsl:text>Protocol Number:</xsl:text>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="9pt" padding="0" display-align="center">
                                            <fo:block>
                                                <xsl:for-each select="n1:Protocol">
                                                    <xsl:for-each select="n1:ProtocolMasterData">
                                                        <xsl:for-each select="n1:ProtocolNumber">
                                                            <fo:inline>
                                                                <xsl:apply-templates>
                                                                    <xsl:with-param name="maxwidth" select="$columnwidth1_1 - 0.02083 - 0.02083" />
                                                                </xsl:apply-templates>
                                                            </fo:inline>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="9pt" padding="2pt" text-align="left" display-align="before">
                                            <fo:block>
                                                <fo:inline font-weight="bold">
                                                    <xsl:text>Sequence Number:</xsl:text>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <xsl:for-each select="n1:Protocol">
                                                    <xsl:for-each select="n1:ProtocolMasterData">
                                                        <xsl:for-each select="n1:SequenceNumber">
                                                            <fo:inline>
                                                                <xsl:apply-templates>
                                                                    <xsl:with-param name="maxwidth" select="$columnwidth1_3 - 0.02083 - 0.02083" />
                                                                </xsl:apply-templates>
                                                            </fo:inline>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="9pt" padding="2pt" text-align="left" display-align="before">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <fo:inline font-weight="bold">
                                                    <xsl:text>Principal Investigator:</xsl:text>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell number-columns-spanned="3" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <xsl:for-each select="n1:Protocol">
                                                    <xsl:for-each select="n1:Investigator">
                                                        <xsl:for-each select="n1:Person">
                                                            <xsl:for-each select="n1:Fullname">
                                                                <fo:inline>
                                                                    <xsl:apply-templates>
                                                                        <xsl:with-param name="maxwidth" select="$columnwidth1_1 + $columnwidth1_2 + $columnwidth1_3 - 0.02083 - 0.02083" />
                                                                    </xsl:apply-templates>
                                                                </fo:inline>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="9pt" padding="2pt" text-align="left" display-align="before">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <fo:inline font-weight="bold">
                                                    <xsl:text>Title:</xsl:text>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell number-columns-spanned="3" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block>
                                                <xsl:for-each select="n1:Protocol">
                                                    <xsl:for-each select="n1:ProtocolMasterData">
                                                        <xsl:for-each select="n1:ProtocolTitle">
                                                            <fo:inline>
                                                                <xsl:apply-templates>
                                                                    <xsl:with-param name="maxwidth" select="$columnwidth1_1 + $columnwidth1_2 + $columnwidth1_3 - 0.02083 - 0.02083" />
                                                                </xsl:apply-templates>
                                                            </fo:inline>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="9pt" padding="2pt" text-align="left" display-align="before">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <fo:inline font-weight="bold">
                                                    <xsl:text>Committee Id:</xsl:text>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="9pt" padding="0" display-align="center">
                                            <fo:block>
                                                <xsl:for-each select="n1:Protocol">
                                                    <xsl:for-each select="n1:Submissions">
                                                        <xsl:for-each select="n1:CommitteeMasterData">
                                                            <xsl:for-each select="n1:CommitteeId">
                                                                <fo:inline>
                                                                    <xsl:apply-templates>
                                                                        <xsl:with-param name="maxwidth" select="$columnwidth1_1 - 0.02083 - 0.02083" />
                                                                    </xsl:apply-templates>
                                                                </fo:inline>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="9pt" padding="2pt" text-align="left" display-align="before">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <fo:inline font-weight="bold">
                                                    <xsl:text>Committee Name:</xsl:text>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="9pt" padding="0" display-align="center">
                                            <fo:block>
                                                <xsl:for-each select="n1:Protocol">
                                                    <xsl:for-each select="n1:Submissions">
                                                        <xsl:for-each select="n1:CommitteeMasterData">
                                                            <xsl:for-each select="n1:CommitteeName">
                                                                <fo:inline>
                                                                    <xsl:apply-templates>
                                                                        <xsl:with-param name="maxwidth" select="$columnwidth1_3 - 0.02083 - 0.02083" />
                                                                    </xsl:apply-templates>
                                                                </fo:inline>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="9pt" padding="2pt" text-align="left" display-align="before">
                                            <fo:block>
                                                <fo:inline font-weight="bold">
                                                    <xsl:text>Schedule Id:</xsl:text>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="9pt" padding="0" display-align="center">
                                            <fo:block>
                                                <xsl:for-each select="n1:Protocol">
                                                    <xsl:for-each select="n1:Submissions">
                                                        <xsl:for-each select="n1:ScheduleMasterData">
                                                            <xsl:for-each select="n1:ScheduleId">
                                                                <fo:inline>
                                                                    <xsl:apply-templates>
                                                                        <xsl:with-param name="maxwidth" select="$columnwidth1_1 - 0.02083 - 0.02083" />
                                                                    </xsl:apply-templates>
                                                                </fo:inline>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="9pt" padding="2pt" text-align="left" display-align="before">
                                            <fo:block>
                                                <fo:inline font-weight="bold">
                                                    <xsl:text>Schedule Date:</xsl:text>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="9pt" padding="0" display-align="center">
                                            <fo:block>
                                                <xsl:for-each select="n1:Protocol">
                                                    <xsl:for-each select="n1:Submissions">
                                                        <xsl:for-each select="n1:ScheduleMasterData">
                                                            <xsl:for-each select="n1:ScheduledDate">
                                                                <xsl:if test="string-length(  .  )   &gt; 0">
                                                                    <fo:inline>
                                                                        <xsl:value-of select="format-number(number(substring(string(.), 6, 2)), '00')" />
                                                                        <xsl:text>/</xsl:text>
                                                                        <xsl:value-of select="format-number(number(substring(string(.), 9, 2)), '00')" />
                                                                        <xsl:text>/</xsl:text>
                                                                        <xsl:value-of select="format-number(number(substring(string(string(.)), 1, 4)), '0000')" />
                                                                    </fo:inline>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="9pt" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt" />
                                        </fo:table-cell>
                                        <fo:table-cell padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt" />
                                        </fo:table-cell>
                                        <fo:table-cell padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt" />
                                        </fo:table-cell>
                                        <fo:table-cell padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt" />
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                            <xsl:variable name="tablewidth2" select="$maxwidth * 1.00000" />
                            <xsl:variable name="sumcolumnwidths2" select="$tablewidth2 * 0.05000 + 0.04167 + 0.04167" />
                            <xsl:variable name="factor2">
                                <xsl:choose>
                                    <xsl:when test="$sumcolumnwidths2 &gt; 0.00000 and $sumcolumnwidths2 &gt; $tablewidth2">
                                        <xsl:value-of select="$tablewidth2 div $sumcolumnwidths2" />
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="1.000" />
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:variable>
                            <xsl:variable name="defaultcolumns2" select="1" />
                            <xsl:variable name="defaultcolumnwidth2">
                                <xsl:choose>
                                    <xsl:when test="$factor2 &lt; 1.000">
                                        <xsl:value-of select="0.000" />
                                    </xsl:when>
                                    <xsl:when test="$defaultcolumns2 &gt; 0">
                                        <xsl:value-of select="($tablewidth2 - $sumcolumnwidths2) div $defaultcolumns2" />
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="0.000" />
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:variable>
                            <xsl:variable name="columnwidth2_0" select="$tablewidth2 * 0.05000 * $factor2" />
                            <xsl:variable name="columnwidth2_1" select="$defaultcolumnwidth2" />
                            <fo:table table-layout="fixed" width="{$tablewidth2}in" border-collapse="separate" border-separation="0.04167in" font-size="9pt" text-align="justify" color="black" display-align="center">
                                <fo:table-column column-width="{$columnwidth2_0}in" />
                                <fo:table-column column-width="{$columnwidth2_1}in" />
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell number-columns-spanned="2" text-align="left" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <fo:inline font-weight="bold">
                                                    <xsl:text>Review Comments:</xsl:text>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                            <xsl:for-each select="n1:Protocol">
                                <xsl:for-each select="n1:Submissions">
                                    <xsl:for-each select="n1:Minutes">
                                        <xsl:choose>
                                            <xsl:when test="n1:PrivateCommentFlag = &apos;true&apos;">
                                                <xsl:variable name="tablewidth3" select="$maxwidth * 1.00000" />
                                                <xsl:variable name="sumcolumnwidths3" select="$tablewidth3 * 0.02000 + 0.04167 + $tablewidth3 * 0.95000 + 0.04167" />
                                                <xsl:variable name="factor3">
                                                    <xsl:choose>
                                                        <xsl:when test="$sumcolumnwidths3 &gt; 0.00000 and $sumcolumnwidths3 &gt; $tablewidth3">
                                                            <xsl:value-of select="$tablewidth3 div $sumcolumnwidths3" />
                                                        </xsl:when>
                                                        <xsl:otherwise>
                                                            <xsl:value-of select="1.000" />
                                                        </xsl:otherwise>
                                                    </xsl:choose>
                                                </xsl:variable>
                                                <xsl:variable name="columnwidth3_0" select="$tablewidth3 * 0.02000 * $factor3" />
                                                <xsl:variable name="columnwidth3_1" select="$tablewidth3 * 0.95000 * $factor3" />
                                                <fo:table table-layout="fixed" width="{$tablewidth3}in" border-collapse="separate" border-separation="0.04167in" font-size="9pt" color="black" display-align="center">
                                                    <fo:table-column column-width="{$columnwidth3_0}in" />
                                                    <fo:table-column column-width="{$columnwidth3_1}in" />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-weight="bold" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                                                <fo:block padding-top="1pt" padding-bottom="1pt">
                                                                    <fo:inline>
                                                                        <xsl:text>#</xsl:text>
                                                                    </fo:inline>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                                                <fo:block padding-top="1pt" padding-bottom="1pt">
                                                                    <xsl:for-each select="n1:MinuteEntry">
                                                                        <fo:inline>
                                                                            <xsl:apply-templates>
                                                                                <xsl:with-param name="maxwidth" select="$columnwidth3_1 - 0.02083 - 0.02083" />
                                                                            </xsl:apply-templates>
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </xsl:when>
                                            <xsl:when test="n1:PrivateCommentFlag = &apos;false&apos;">
                                                <xsl:variable name="tablewidth4" select="$maxwidth * 1.00000" />
                                                <xsl:variable name="sumcolumnwidths4" select="$tablewidth4 * 0.02000 + 0.04167 + $tablewidth4 * 0.98000 + 0.04167" />
                                                <xsl:variable name="factor4">
                                                    <xsl:choose>
                                                        <xsl:when test="$sumcolumnwidths4 &gt; 0.00000 and $sumcolumnwidths4 &gt; $tablewidth4">
                                                            <xsl:value-of select="$tablewidth4 div $sumcolumnwidths4" />
                                                        </xsl:when>
                                                        <xsl:otherwise>
                                                            <xsl:value-of select="1.000" />
                                                        </xsl:otherwise>
                                                    </xsl:choose>
                                                </xsl:variable>
                                                <xsl:variable name="columnwidth4_0" select="$tablewidth4 * 0.02000 * $factor4" />
                                                <xsl:variable name="columnwidth4_1" select="$tablewidth4 * 0.98000 * $factor4" />
                                                <fo:table table-layout="fixed" width="{$tablewidth4}in" border-collapse="separate" border-separation="0.04167in" font-size="9pt" color="black" display-align="center">
                                                    <fo:table-column column-width="{$columnwidth4_0}in" />
                                                    <fo:table-column column-width="{$columnwidth4_1}in" />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-weight="bold" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                                                <fo:block padding-top="1pt" padding-bottom="1pt">
                                                                    <fo:inline>
                                                                        <xsl:text>*</xsl:text>
                                                                    </fo:inline>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                                                <fo:block padding-top="1pt" padding-bottom="1pt">
                                                                    <xsl:for-each select="n1:MinuteEntry">
                                                                        <fo:inline>
                                                                            <xsl:apply-templates>
                                                                                <xsl:with-param name="maxwidth" select="$columnwidth4_1 - 0.02083 - 0.02083" />
                                                                            </xsl:apply-templates>
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </xsl:when>
                                        </xsl:choose>
                                    </xsl:for-each>
                                </xsl:for-each>
                            </xsl:for-each>
                            <fo:block>
                                <fo:leader leader-pattern="space" />
                            </fo:block>
                            <xsl:variable name="tablewidth5" select="$maxwidth * 1.00000" />
                            <xsl:variable name="sumcolumnwidths5" select="$tablewidth5 * 0.50000 + 0.04167 + 0.04167 + $tablewidth5 * 0.80000 + 0.04167 + 0.04167" />
                            <xsl:variable name="factor5">
                                <xsl:choose>
                                    <xsl:when test="$sumcolumnwidths5 &gt; 0.00000 and $sumcolumnwidths5 &gt; $tablewidth5">
                                        <xsl:value-of select="$tablewidth5 div $sumcolumnwidths5" />
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="1.000" />
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:variable>
                            <xsl:variable name="defaultcolumns5" select="1 + 1" />
                            <xsl:variable name="defaultcolumnwidth5">
                                <xsl:choose>
                                    <xsl:when test="$factor5 &lt; 1.000">
                                        <xsl:value-of select="0.000" />
                                    </xsl:when>
                                    <xsl:when test="$defaultcolumns5 &gt; 0">
                                        <xsl:value-of select="($tablewidth5 - $sumcolumnwidths5) div $defaultcolumns5" />
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="0.000" />
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:variable>
                            <xsl:variable name="columnwidth5_0" select="$tablewidth5 * 0.50000 * $factor5" />
                            <xsl:variable name="columnwidth5_1" select="$defaultcolumnwidth5" />
                            <xsl:variable name="columnwidth5_2" select="$tablewidth5 * 0.80000 * $factor5" />
                            <xsl:variable name="columnwidth5_3" select="$defaultcolumnwidth5" />
                            <fo:table table-layout="fixed" width="{$tablewidth5}in" border-collapse="separate" border-separation="0.04167in" font-size="9pt" color="black" display-align="center">
                                <fo:table-column column-width="{$columnwidth5_0}in" />
                                <fo:table-column column-width="{$columnwidth5_1}in" />
                                <fo:table-column column-width="{$columnwidth5_2}in" />
                                <fo:table-column column-width="{$columnwidth5_3}in" />
                                <fo:table-body display-align="before">
                                    <fo:table-row display-align="before">
                                        <fo:table-cell font-weight="normal" display-align="after" number-columns-spanned="2" text-align="left" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <fo:inline font-weight="bold">
                                                    <xsl:text>#</xsl:text>
                                                </fo:inline>
                                                <fo:inline>
                                                    <xsl:text>&#160;&#160;&#160;&#160; Private Comment</xsl:text>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell display-align="center" number-columns-spanned="2" text-align="justify" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt" />
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row display-align="before">
                                        <fo:table-cell font-weight="normal" display-align="after" number-columns-spanned="2" text-align="left" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <fo:inline>
                                                    <xsl:text>*&#160;&#160;&#160;&#160; Non-Private Comment</xsl:text>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell display-align="center" number-columns-spanned="2" text-align="justify" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt" />
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </xsl:for-each>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
