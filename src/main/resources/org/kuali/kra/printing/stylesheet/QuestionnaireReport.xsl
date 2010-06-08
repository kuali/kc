<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema">
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
        <xsl:variable name="maxwidth" select="7.30000" />
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
                            <fo:table table-layout="fixed" width="{$tablewidth0}in" border-collapse="separate" border-separation="0.04167in" color="black" display-align="center">
                                <fo:table-column column-width="{$columnwidth0_0}in" />
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell text-align="center" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <fo:inline font-size="14pt" font-weight="bold">
                                                    <xsl:text>Questionnaire</xsl:text>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                            <xsl:variable name="tablewidth1" select="$maxwidth * 1.00000" />
                            <xsl:variable name="sumcolumnwidths1" select="0.20833 + 0.04167 + 0.31250 + 0.04167 + 0.26042 + 0.04167 + 0.26042 + 0.04167" />
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
                            <xsl:variable name="columnwidth1_1" select="0.31250 * $factor1" />
                            <xsl:variable name="columnwidth1_2" select="0.26042 * $factor1" />
                            <xsl:variable name="columnwidth1_3" select="0.26042 * $factor1" />
                            <fo:table table-layout="fixed" width="{$tablewidth1}in" border-collapse="separate" border-separation="0.04167in" font-size="9pt" color="black" display-align="center">
                                <fo:table-column column-width="{$columnwidth1_0}in" />
                                <fo:table-column column-width="{$columnwidth1_1}in" />
                                <fo:table-column column-width="{$columnwidth1_2}in" />
                                <fo:table-column column-width="{$columnwidth1_3}in" />
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell font-size="9pt" font-weight="bold" display-align="before" text-align="justify" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <fo:inline>
                                                    <xsl:text>Questionnaire Name:</xsl:text>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell number-columns-spanned="3" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <xsl:for-each select="Questionnaire">
                                                    <xsl:for-each select="QuestionnaireName">
                                                        <fo:inline>
                                                            <xsl:apply-templates>
                                                                <xsl:with-param name="maxwidth" select="$columnwidth1_1 + $columnwidth1_2 + $columnwidth1_3 - 0.02083 - 0.02083" />
                                                            </xsl:apply-templates>
                                                        </fo:inline>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="9pt" font-weight="bold" display-align="before" text-align="justify" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <fo:inline>
                                                    <xsl:text>Description:</xsl:text>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell number-columns-spanned="3" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <xsl:for-each select="Questionnaire">
                                                    <xsl:for-each select="QuestionnaireDesc">
                                                        <fo:inline>
                                                            <xsl:apply-templates>
                                                                <xsl:with-param name="maxwidth" select="$columnwidth1_1 + $columnwidth1_2 + $columnwidth1_3 - 0.02083 - 0.02083" />
                                                            </xsl:apply-templates>
                                                        </fo:inline>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="9pt" font-weight="bold" display-align="before" text-align="justify" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <fo:inline>
                                                    <xsl:text>Module:</xsl:text>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <xsl:for-each select="Questionnaire">
                                                    <xsl:for-each select="ModuleUsage">
                                                        <xsl:for-each select="ModuleInfo">
                                                            <xsl:for-each select="ModuleDesc">
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
                                        <fo:table-cell font-size="9pt" font-weight="bold" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <fo:inline>
                                                    <xsl:text>Sub Module:</xsl:text>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <xsl:for-each select="Questionnaire">
                                                    <xsl:for-each select="ModuleUsage">
                                                        <xsl:for-each select="ModuleInfo">
                                                            <xsl:for-each select="SubModuleDesc">
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
                                </fo:table-body>
                            </fo:table>
                            <xsl:choose>
                                <xsl:when test="/Questionnaire/ProtocolInfo">
                                    <xsl:variable name="tablewidth2" select="$maxwidth * 1.00000" />
                                    <xsl:variable name="sumcolumnwidths2" select="0.20833 + 0.04167 + 0.31250 + 0.04167 + 0.26042 + 0.04167 + 0.26042 + 0.04167" />
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
                                    <xsl:variable name="columnwidth2_0" select="0.20833 * $factor2" />
                                    <xsl:variable name="columnwidth2_1" select="0.31250 * $factor2" />
                                    <xsl:variable name="columnwidth2_2" select="0.26042 * $factor2" />
                                    <xsl:variable name="columnwidth2_3" select="0.26042 * $factor2" />
                                    <fo:table table-layout="fixed" width="{$tablewidth2}in" border-collapse="separate" border-separation="0.04167in" font-size="9pt" color="black" display-align="center">
                                        <fo:table-column column-width="{$columnwidth2_0}in" />
                                        <fo:table-column column-width="{$columnwidth2_1}in" />
                                        <fo:table-column column-width="{$columnwidth2_2}in" />
                                        <fo:table-column column-width="{$columnwidth2_3}in" />
                                        <fo:table-body>
                                            <fo:table-row>
                                                <fo:table-cell font-weight="bold" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                                    <fo:block padding-top="1pt" padding-bottom="1pt">
                                                        <fo:inline>
                                                            <xsl:text>Protocol Number:</xsl:text>
                                                        </fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                                    <fo:block padding-top="1pt" padding-bottom="1pt">
                                                        <xsl:for-each select="Questionnaire">
                                                            <xsl:for-each select="AnswerHeader">
                                                                <xsl:for-each select="ModuleKey">
                                                                    <fo:inline>
                                                                        <xsl:apply-templates>
                                                                            <xsl:with-param name="maxwidth" select="$columnwidth2_1 - 0.02083 - 0.02083" />
                                                                        </xsl:apply-templates>
                                                                    </fo:inline>
                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell font-weight="bold" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                                    <fo:block padding-top="1pt" padding-bottom="1pt">
                                                        <fo:inline>
                                                            <xsl:text>Sequence Number:</xsl:text>
                                                        </fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                                    <fo:block padding-top="1pt" padding-bottom="1pt">
                                                        <xsl:for-each select="Questionnaire">
                                                            <xsl:for-each select="AnswerHeader">
                                                                <xsl:for-each select="SubModuleKey">
                                                                    <fo:inline>
                                                                        <xsl:apply-templates>
                                                                            <xsl:with-param name="maxwidth" select="$columnwidth2_3 - 0.02083 - 0.02083" />
                                                                        </xsl:apply-templates>
                                                                    </fo:inline>
                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                            <fo:table-row>
                                                <fo:table-cell font-weight="bold" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                                    <fo:block padding-top="1pt" padding-bottom="1pt">
                                                        <fo:inline>
                                                            <xsl:text>Title:</xsl:text>
                                                        </fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                                    <fo:block padding-top="1pt" padding-bottom="1pt">
                                                        <xsl:for-each select="Questionnaire">
                                                            <xsl:for-each select="ProtocolInfo">
                                                                <xsl:for-each select="Title">
                                                                    <fo:inline>
                                                                        <xsl:apply-templates>
                                                                            <xsl:with-param name="maxwidth" select="$columnwidth2_1 - 0.02083 - 0.02083" />
                                                                        </xsl:apply-templates>
                                                                    </fo:inline>
                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell font-weight="bold" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                                    <fo:block padding-top="1pt" padding-bottom="1pt">
                                                        <fo:inline>
                                                            <xsl:text>Principal Investigator:</xsl:text>
                                                        </fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                                    <fo:block padding-top="1pt" padding-bottom="1pt">
                                                        <xsl:for-each select="Questionnaire">
                                                            <xsl:for-each select="ProtocolInfo">
                                                                <xsl:for-each select="Investigator">
                                                                    <xsl:for-each select="Fullname">
                                                                        <fo:inline>
                                                                            <xsl:apply-templates>
                                                                                <xsl:with-param name="maxwidth" select="$columnwidth2_3 - 0.02083 - 0.02083" />
                                                                            </xsl:apply-templates>
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                        </fo:table-body>
                                    </fo:table>
                                </xsl:when>
                                <xsl:when test="/Questionnaire/ProposalInfo">
                                    <xsl:variable name="tablewidth3" select="$maxwidth * 1.00000" />
                                    <xsl:variable name="sumcolumnwidths3" select="0.20833 + 0.04167 + 0.31250 + 0.04167 + 0.26042 + 0.04167 + 0.26042 + 0.04167" />
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
                                    <xsl:variable name="columnwidth3_0" select="0.20833 * $factor3" />
                                    <xsl:variable name="columnwidth3_1" select="0.31250 * $factor3" />
                                    <xsl:variable name="columnwidth3_2" select="0.26042 * $factor3" />
                                    <xsl:variable name="columnwidth3_3" select="0.26042 * $factor3" />
                                    <fo:table table-layout="fixed" width="{$tablewidth3}in" border-collapse="separate" border-separation="0.04167in" font-size="9pt" color="black" display-align="center">
                                        <fo:table-column column-width="{$columnwidth3_0}in" />
                                        <fo:table-column column-width="{$columnwidth3_1}in" />
                                        <fo:table-column column-width="{$columnwidth3_2}in" />
                                        <fo:table-column column-width="{$columnwidth3_3}in" />
                                        <fo:table-body>
                                            <fo:table-row>
                                                <fo:table-cell font-weight="bold" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                                    <fo:block padding-top="1pt" padding-bottom="1pt">
                                                        <fo:inline>
                                                            <xsl:text>Proposal Number:</xsl:text>
                                                        </fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                                    <fo:block padding-top="1pt" padding-bottom="1pt">
                                                        <xsl:for-each select="Questionnaire">
                                                            <xsl:for-each select="AnswerHeader">
                                                                <xsl:for-each select="ModuleKey">
                                                                    <fo:inline>
                                                                        <xsl:apply-templates>
                                                                            <xsl:with-param name="maxwidth" select="$columnwidth3_1 - 0.02083 - 0.02083" />
                                                                        </xsl:apply-templates>
                                                                    </fo:inline>
                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell font-weight="bold" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                                    <fo:block padding-top="1pt" padding-bottom="1pt" />
                                                </fo:table-cell>
                                                <fo:table-cell padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                                    <fo:block padding-top="1pt" padding-bottom="1pt" />
                                                </fo:table-cell>
                                            </fo:table-row>
                                            <fo:table-row>
                                                <fo:table-cell font-weight="bold" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                                    <fo:block padding-top="1pt" padding-bottom="1pt">
                                                        <fo:inline>
                                                            <xsl:text>Title:</xsl:text>
                                                        </fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                                    <fo:block padding-top="1pt" padding-bottom="1pt">
                                                        <xsl:for-each select="Questionnaire">
                                                            <xsl:for-each select="ProposalInfo">
                                                                <xsl:for-each select="Title">
                                                                    <fo:inline>
                                                                        <xsl:apply-templates>
                                                                            <xsl:with-param name="maxwidth" select="$columnwidth3_1 - 0.02083 - 0.02083" />
                                                                        </xsl:apply-templates>
                                                                    </fo:inline>
                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell font-weight="bold" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                                    <fo:block padding-top="1pt" padding-bottom="1pt">
                                                        <fo:inline>
                                                            <xsl:text>Principal Investigator:</xsl:text>
                                                        </fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                                    <fo:block padding-top="1pt" padding-bottom="1pt">
                                                        <xsl:for-each select="Questionnaire">
                                                            <xsl:for-each select="ProposalInfo">
                                                                <xsl:for-each select="Investigator">
                                                                    <xsl:for-each select="Fullname">
                                                                        <fo:inline>
                                                                            <xsl:apply-templates>
                                                                                <xsl:with-param name="maxwidth" select="$columnwidth3_3 - 0.02083 - 0.02083" />
                                                                            </xsl:apply-templates>
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                        </fo:table-body>
                                    </fo:table>
                                </xsl:when>
                            </xsl:choose>
                            <fo:block>
                                <xsl:text>&#xA;</xsl:text>
                            </fo:block>
                            <xsl:variable name="tablewidth4" select="$maxwidth * 1.00000" />
                            <xsl:variable name="sumcolumnwidths4" select="0.04167 + 0.04167" />
                            <xsl:variable name="defaultcolumns4" select="1 + 1" />
                            <xsl:variable name="defaultcolumnwidth4">
                                <xsl:choose>
                                    <xsl:when test="$defaultcolumns4 &gt; 0">
                                        <xsl:value-of select="($tablewidth4 - $sumcolumnwidths4) div $defaultcolumns4" />
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:value-of select="0.000" />
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:variable>
                            <xsl:variable name="columnwidth4_0" select="$defaultcolumnwidth4" />
                            <xsl:variable name="columnwidth4_1" select="$defaultcolumnwidth4" />
                            <fo:table table-layout="fixed" width="{$tablewidth4}in" border-collapse="separate" border-separation="0.04167in" color="black" display-align="center">
                                <fo:table-column column-width="{$columnwidth4_0}in" />
                                <fo:table-column column-width="{$columnwidth4_1}in" />
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell font-size="9pt" number-columns-spanned="2" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <xsl:for-each select="Questionnaire">
                                                    <xsl:for-each select="Questions">
                                                        <xsl:if test="../UserOption/UserOptionsInfo/PrintAnswers  = &apos;No&apos;">
                                                            <xsl:for-each select="QuestionInfo">
                                                                <xsl:if test="not(  Question  =  preceding-sibling::QuestionInfo[1]/Question )">
                                                                    <fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm" start-indent="2mm" space-before.optimum="4pt" space-after.optimum="4pt">
                                                                        <fo:list-item>
                                                                            <fo:list-item-label end-indent="label-end()">
                                                                                <fo:block text-align="right" font-family="Courier" font-size="15pt" line-height="14pt" padding-before="2pt">&#x2022;</fo:block>
                                                                            </fo:list-item-label>
                                                                            <fo:list-item-body start-indent="body-start()">
                                                                                <fo:block />
                                                                            </fo:list-item-body>
                                                                        </fo:list-item>
                                                                    </fo:list-block>
                                                                    <fo:inline>
                                                                        <xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
                                                                    </fo:inline>
                                                                    <xsl:for-each select="Question">
                                                                        <fo:inline>
                                                                            <xsl:apply-templates>
                                                                                <xsl:with-param name="maxwidth" select="$columnwidth4_0 + $columnwidth4_1 - 0.02083 - 0.02083" />
                                                                            </xsl:apply-templates>
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="9pt" height="0.94792in" number-columns-spanned="2" padding-top="0.02083in" padding-bottom="0.02083in" padding-left="0.02083in" padding-right="0.02083in">
                                            <fo:block padding-top="1pt" padding-bottom="1pt">
                                                <xsl:choose>
                                                    <xsl:when test="/Questionnaire/UserOption/UserOptionsInfo/PrintAnsweredQuestionsOnly = &apos;Yes&apos;">
                                                        <xsl:for-each select="Questionnaire">
                                                            <xsl:for-each select="Questions">
                                                                <xsl:for-each select="QuestionInfo">
                                                                    <xsl:if test="count(   AnswerInfo   ) &gt; 0">
                                                                        <fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm" start-indent="2mm" space-before.optimum="4pt" space-after.optimum="4pt">
                                                                            <fo:list-item>
                                                                                <fo:list-item-label end-indent="label-end()">
                                                                                    <fo:block text-align="right" font-family="Courier" font-size="15pt" line-height="14pt" padding-before="2pt">&#x2022;</fo:block>
                                                                                </fo:list-item-label>
                                                                                <fo:list-item-body start-indent="body-start()">
                                                                                    <fo:block />
                                                                                </fo:list-item-body>
                                                                            </fo:list-item>
                                                                        </fo:list-block>
                                                                        <fo:inline>
                                                                            <xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
                                                                        </fo:inline>
                                                                        <xsl:for-each select="Question">
                                                                            <fo:inline>
                                                                                <xsl:apply-templates>
                                                                                    <xsl:with-param name="maxwidth" select="$columnwidth4_0 + $columnwidth4_1 - 0.02083 - 0.02083" />
                                                                                </xsl:apply-templates>
                                                                            </fo:inline>
                                                                        </xsl:for-each>
                                                                        <xsl:for-each select="AnswerInfo">
                                                                            <xsl:if test="string-length(  Answer  )  &gt; 0">
                                                                                <fo:block>
                                                                                    <fo:leader leader-pattern="space" />
                                                                                </fo:block>
                                                                                <fo:inline>
                                                                                    <xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
                                                                                </fo:inline>
                                                                                <xsl:for-each select="Answer">
                                                                                    <fo:inline>
                                                                                        <xsl:apply-templates>
                                                                                            <xsl:with-param name="maxwidth" select="$columnwidth4_0 + $columnwidth4_1 - 0.02083 - 0.02083" />
                                                                                        </xsl:apply-templates>
                                                                                    </fo:inline>
                                                                                </xsl:for-each>
                                                                            </xsl:if>
                                                                        </xsl:for-each>
                                                                    </xsl:if>
                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </xsl:when>
                                                    <xsl:when test="/Questionnaire/UserOption/UserOptionsInfo/PrintAnsweredQuestionsOnly = &apos;No&apos;">
                                                        <xsl:for-each select="Questionnaire">
                                                            <xsl:for-each select="Questions">
                                                                <xsl:for-each select="QuestionInfo">
                                                                    <fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm" start-indent="2mm" space-before.optimum="4pt" space-after.optimum="4pt">
                                                                        <fo:list-item>
                                                                            <fo:list-item-label end-indent="label-end()">
                                                                                <fo:block text-align="right" font-family="Courier" font-size="15pt" line-height="14pt" padding-before="2pt">&#x2022;</fo:block>
                                                                            </fo:list-item-label>
                                                                            <fo:list-item-body start-indent="body-start()">
                                                                                <fo:block />
                                                                            </fo:list-item-body>
                                                                        </fo:list-item>
                                                                    </fo:list-block>
                                                                    <fo:inline>
                                                                        <xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
                                                                    </fo:inline>
                                                                    <xsl:for-each select="Question">
                                                                        <fo:inline>
                                                                            <xsl:apply-templates>
                                                                                <xsl:with-param name="maxwidth" select="$columnwidth4_0 + $columnwidth4_1 - 0.02083 - 0.02083" />
                                                                            </xsl:apply-templates>
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                    <xsl:if test="count(  AnswerInfo  )  &gt; 0">
                                                                        <xsl:for-each select="AnswerInfo">
                                                                            <fo:block>
                                                                                <fo:leader leader-pattern="space" />
                                                                            </fo:block>
                                                                            <fo:inline>
                                                                                <xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </xsl:text>
                                                                            </fo:inline>
                                                                            <xsl:for-each select="Answer">
                                                                                <fo:inline>
                                                                                    <xsl:apply-templates>
                                                                                        <xsl:with-param name="maxwidth" select="$columnwidth4_0 + $columnwidth4_1 - 0.02083 - 0.02083" />
                                                                                    </xsl:apply-templates>
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:if>
                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </xsl:when>
                                                </xsl:choose>
                                            </fo:block>
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
