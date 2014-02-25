delimiter /
INSERT INTO SEQ_PROTO_CORRESP_TEMPL VALUES (null)
/
INSERT INTO PROTO_CORRESP_TEMPL (PROTO_CORRESP_TEMPL_ID,PROTO_CORRESP_TYPE_CODE,COMMITTEE_ID,FILE_NAME,CORRESPONDENCE_TEMPLATE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
    VALUES ((SELECT MAX(ID) FROM SEQ_PROTO_CORRESP_TEMPL),(SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Termination Notice'),'DEFAULT','DEFAULT-8-TerminationNotice.xsl',
'<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:n1="http://irb.mit.edu/irbnamespace" xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xsl:variable name="fo:layout-master-set">
        <fo:layout-master-set>
            <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.6in" margin-right="1.0in">
                 <fo:region-body margin-top="0.79in" margin-bottom="0.79in" />
               <fo:region-before extent="0.79in" />
            </fo:simple-page-master>
        </fo:layout-master-set>
    </xsl:variable>
    <xsl:output version="1.0" encoding="UTF-8" indent="no" omit-xml-declaration="no" media-type="text/html" />
    <xsl:template match="/">
        <fo:root>
            <xsl:copy-of select="$fo:layout-master-set" />
            <fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block>
                        <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                            <fo:table-column />
                            <fo:table-column column-width="150pt" />
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell padding-bottom="0pt" padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" height="30pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell font-size="inherited-property-value(&apos;font-size&apos;) - 2pt" padding-bottom="0pt" padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" text-align="left" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell font-size="inherited-property-value(&apos;font-size&apos;) - 2pt" padding-bottom="0pt" padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" text-align="right" width="150pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-bottom="0pt" padding-left="0pt" padding-right="0pt" padding-top="0pt" border-style="solid" border-width="1pt" border-color="white" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                </fo:static-content>
                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <fo:external-graphic space-before.optimum="4pt" space-after.optimum="4pt">
                            <xsl:attribute name="src">url(''<xsl:text disable-output-escaping="yes">/export/home/www/https/tomcat5.0.25/webapps/coeus/images/couhes_byline2.gif</xsl:text>'')</xsl:attribute>
                        </fo:external-graphic>
                        <fo:block color="black" space-before.optimum="-8pt">
                            <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="1pt" />
                        </fo:block>
                        <fo:block>
                            <fo:leader leader-pattern="space" />
                        </fo:block>
                        <xsl:for-each select="n1:Correspondence">
                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                <fo:block>
                                    <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                        <fo:table-column column-width="proportional-column-width(25)" />
                                        <fo:table-column column-width="proportional-column-width(75)" />
                                        <fo:table-body>
                                            <fo:table-row>
                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="25%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                    <fo:block>
                                                        <fo:inline font-size="10pt" font-weight="bold">To:</fo:inline>
                                                        <fo:inline font-size="10pt">&#160;&#160;&#160;&#160;&#160; </fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="75%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                    <fo:block>
                                                        <xsl:for-each select="n1:Protocol">
                                                            <xsl:for-each select="n1:Investigator">
                                                                <xsl:if test="n1:PI_flag = &apos;true&apos;">
                                                                    <xsl:for-each select="n1:Person">
                                                                        <xsl:for-each select="n1:Firstname">
                                                                            <fo:inline font-size="10pt">
                                                                                <xsl:apply-templates />
                                                                            </fo:inline>
                                                                        </xsl:for-each>
                                                                        <fo:inline font-size="10pt">&#160;</fo:inline>
                                                                        <xsl:for-each select="n1:LastName">
                                                                            <fo:inline font-size="10pt">
                                                                                <xsl:apply-templates />
                                                                            </fo:inline>
                                                                        </xsl:for-each>
                                                                        <fo:block white-space-collapse="false" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                            <fo:block>
                                                                                <xsl:for-each select="n1:OfficeLocation">
                                                                                    <fo:inline font-size="10pt">
                                                                                        <xsl:apply-templates />
                                                                                    </fo:inline>
                                                                                </xsl:for-each>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                            <fo:table-row>
                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="25%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                    <fo:block>
                                                        <fo:inline font-size="10pt" font-weight="bold">From:</fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="75%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                    <fo:block>
                                                        <xsl:for-each select="n1:Protocol">
                                                            <xsl:for-each select="n1:Submissions">
                                                                <xsl:if test="n1:CurrentSubmissionFlag =&apos;No&apos;">
                                                                    <xsl:for-each select="n1:CommitteeMember">
                                                                        <xsl:if test="n1:CommitteeMemberRole/n1:MemberRoleDesc = &apos;Chair&apos;">
                                                                            <xsl:for-each select="n1:Person">
                                                                                <xsl:for-each select="n1:Firstname">
                                                                                    <fo:inline font-size="10pt">
                                                                                        <xsl:apply-templates />
                                                                                    </fo:inline>
                                                                                </xsl:for-each>
                                                                                <fo:inline font-size="10pt">&#160;</fo:inline>
                                                                                <xsl:for-each select="n1:LastName">
                                                                                    <fo:inline font-size="10pt">
                                                                                        <xsl:apply-templates />
                                                                                    </fo:inline>
                                                                                </xsl:for-each>
                                                                            </xsl:for-each>
                                                                        </xsl:if>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                        <fo:inline font-size="10pt">, Chair </fo:inline>
                                                        <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                            <fo:block>
                                                                <xsl:for-each select="n1:Protocol">
                                                                    <xsl:for-each select="n1:Submissions">
                                                                        <xsl:for-each select="n1:CommitteeMasterData">
                                                                            <xsl:for-each select="n1:CommitteeName">
                                                                                <xsl:if test="../../n1:CurrentSubmissionFlag =&apos;No&apos;">
                                                                                    <fo:inline font-size="10pt">
                                                                                        <xsl:apply-templates />
                                                                                    </fo:inline>
                                                                                </xsl:if>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </fo:block>
                                                        </fo:block>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                            <fo:table-row>
                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" height="19pt" width="25%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                    <fo:block>
                                                        <fo:inline font-size="10pt" font-weight="bold">Date:</fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" height="19pt" width="75%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                    <fo:block>
                                                        <xsl:for-each select="n1:CurrentDate">
                                                            <fo:inline font-size="10pt">
                                                                <xsl:value-of select="format-number(number(substring(string(.), 6, 2)), ''00'')" />
                                                                <xsl:text>/</xsl:text>
                                                                <xsl:value-of select="format-number(number(substring(string(.), 9, 2)), ''00'')" />
                                                                <xsl:text>/</xsl:text>
                                                                <xsl:value-of select="format-number(number(substring(string(.), 1, 4)), ''0000'')" />
                                                            </fo:inline>
                                                        </xsl:for-each>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                            <fo:table-row>
                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="25%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                    <fo:block>
                                                        <fo:inline font-size="10pt" font-weight="bold">Committee Action:</fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="75%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                    <fo:block>
                                                        <fo:inline font-size="10pt" font-weight="bold">Protocol Termination</fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                        </fo:table-body>
                                    </fo:table>
                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                        <fo:block text-align="justify">
                                            <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                <fo:table-column column-width="proportional-column-width(25)" />
                                                <fo:table-column column-width="proportional-column-width(75)" />
                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="25%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                            <fo:block>
                                                                <fo:inline font-size="10pt" font-weight="bold">IRB Protocol #: </fo:inline>
                                                                <fo:inline font-size="10pt">&#160;&#160;&#160; </fo:inline>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="75%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                            <fo:block>
                                                                <xsl:for-each select="n1:Protocol">
                                                                    <xsl:for-each select="n1:ProtocolMasterData">
                                                                        <xsl:for-each select="n1:ProtocolNumber">
                                                                            <fo:inline font-size="10pt">
                                                                                <xsl:apply-templates />
                                                                            </fo:inline>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="25%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                            <fo:block>
                                                                <fo:inline font-size="10pt" font-weight="bold">Study Title: </fo:inline>
                                                                <fo:inline font-size="10pt">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </fo:inline>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="75%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                            <fo:block>
                                                                <xsl:for-each select="n1:Protocol">
                                                                    <xsl:for-each select="n1:ProtocolMasterData">
                                                                        <xsl:for-each select="n1:ProtocolTitle">
                                                                            <fo:inline font-size="10pt">
                                                                                <xsl:apply-templates />
                                                                            </fo:inline>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                </fo:table-body>
                                            </fo:table>
                                            <fo:block>
                                                <xsl:text>&#xA;</xsl:text>
                                            </fo:block>
                                        </fo:block>
                                    </fo:block>
                                </fo:block>
                            </fo:block>
                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                <fo:block>
                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                        <fo:block>
                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                <fo:block />
                                            </fo:block>
                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                <fo:block />
                                            </fo:block>
                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                <fo:block>
                                                    <fo:inline font-size="10pt">This is to notify you that the above protocol has been TERMINATED effective </fo:inline>
                                                    <xsl:for-each select="n1:CurrentDate">
                                                        <fo:inline font-size="10pt">
                                                            <xsl:value-of select="format-number(number(substring(string(.), 6, 2)), ''00'')" />
                                                            <xsl:text>/</xsl:text>
                                                            <xsl:value-of select="format-number(number(substring(string(.), 9, 2)), ''00'')" />
                                                            <xsl:text>/</xsl:text>
                                                            <xsl:value-of select="format-number(number(substring(string(.), 1, 4)), ''0000'')" />
                                                        </fo:inline>
                                                    </xsl:for-each>
                                                    <fo:inline font-size="10pt">.&#160; Data collected after the expiration date is considered unapproved research and cannot be included with data collected during an approved period.&#160; Furthermore, data collected after termination of approval cannot be reported or published as research data.</fo:inline>
                                                    <fo:block>
                                                        <fo:leader leader-pattern="space" />
                                                    </fo:block>
                                                    <fo:block>
                                                        <fo:leader leader-pattern="space" />
                                                    </fo:block>
                                                    <fo:inline font-size="10pt">In accordance with MIT Policy and Federal Regulations, you may NOT continue any further research efforts covered by this protocol.&#160;
If you wish to gather further research information, you must submit a new proposal for review by the Committee on the Use of Humans as Experimental Subjects.</fo:inline>
                                                    <fo:block>
                                                        <xsl:text>&#xA;</xsl:text>
                                                    </fo:block>
                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                        <fo:block>
                                                            <fo:inline font-size="10pt">You should retain a copy of this letter for your records.</fo:inline>
                                                        </fo:block>
                                                    </fo:block>
                                                </fo:block>
                                            </fo:block>
                                        </fo:block>
                                    </fo:block>
                                </fo:block>
                            </fo:block>
                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                <fo:block>
                                    <fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>
                                    <fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>
                                    <fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>
                                    <fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>
                                    <fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>
                                    <fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>
                                    <fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>
                                    <fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>
                                    <fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>
                                    <fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>
                                    <fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>
                                    <fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>
                                    <fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>
                                    <fo:block>
                                        <xsl:text>&#xA;</xsl:text>
                                    </fo:block>
                                    <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                        <fo:table-column column-width="3pt" />
                                        <fo:table-column />
                                        <fo:table-body>
                                            <fo:table-row>
                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="3pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                    <fo:block />
                                                </fo:table-cell>
                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" text-align="left" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center">
                                                    <fo:block>
                                                        <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                            <fo:table-column column-width="46pt" />
                                                            <fo:table-column column-width="452pt" />
                                                            <fo:table-body>
                                                                <fo:table-row>
                                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="46pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                        <fo:block>
                                                                            <fo:inline font-size="10pt">cc</fo:inline>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                        <fo:block>
                                                                            <fo:inline font-size="10pt">Tom Duff</fo:inline>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                </fo:table-row>
                                                                <fo:table-row>
                                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="46pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                        <fo:block />
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="452pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                                        <fo:block>
                                                                            <xsl:for-each select="n1:Protocol">
                                                                                <xsl:for-each select="n1:Correspondent">
                                                                                    <xsl:for-each select="n1:Person">
                                                                                        <xsl:for-each select="n1:Fullname">
                                                                                            <xsl:if test="../../n1:TypeOfCorrespondent = &apos;CRC&apos;">
                                                                                                <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                    <fo:block>
                                                                                                        <fo:inline font-size="10pt">
                                                                                                            <xsl:apply-templates />
                                                                                                        </fo:inline>
                                                                                                    </fo:block>
                                                                                                </fo:block>
                                                                                            </xsl:if>
                                                                                        </xsl:for-each>
                                                                                    </xsl:for-each>
                                                                                </xsl:for-each>
                                                                            </xsl:for-each>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                </fo:table-row>
                                                            </fo:table-body>
                                                        </fo:table>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                        </fo:table-body>
                                    </fo:table>
                                    <fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>
                                    <fo:inline font-size="10pt">
</fo:inline>
                                </fo:block>
                            </fo:block>
                        </xsl:for-each>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>','admin',NOW(),UUID(),1)
/
delimiter ;
