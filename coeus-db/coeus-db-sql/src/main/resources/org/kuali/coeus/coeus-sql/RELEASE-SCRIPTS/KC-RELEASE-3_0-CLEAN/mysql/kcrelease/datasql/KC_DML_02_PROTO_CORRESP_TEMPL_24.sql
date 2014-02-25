delimiter /
INSERT INTO SEQ_PROTO_CORRESP_TEMPL VALUES (null)
/
INSERT INTO PROTO_CORRESP_TEMPL (PROTO_CORRESP_TEMPL_ID,PROTO_CORRESP_TYPE_CODE,COMMITTEE_ID,FILE_NAME,CORRESPONDENCE_TEMPLATE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
    VALUES ((SELECT MAX(ID) FROM SEQ_PROTO_CORRESP_TEMPL),(SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Reminder to IRB Notification #2'),'DEFAULT','DEFAULT-24-ReminderToIrbNotification#2.xsl',
'<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:n1="http://irb.mit.edu/irbnamespace" xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xsl:variable name="fo:layout-master-set">
        <fo:layout-master-set>
            <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.8in" margin-right="0.8in">
                <fo:region-body margin-top="0.45in" margin-bottom="0.45in" />
                <fo:region-before extent="0.79in" />
            </fo:simple-page-master>
        </fo:layout-master-set>
    </xsl:variable>
    <xsl:output version="1.0" encoding="UTF-8" indent="no" omit-xml-declaration="no" media-type="text/html" />
    <xsl:template match="/">
        <fo:root>
            <xsl:copy-of select="$fo:layout-master-set" />
            <fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <xsl:for-each select="n1:RenewalReminder">
                            <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                <fo:table-column column-width="43pt" />
                                <fo:table-column column-width="281pt" />
                                <fo:table-column column-width="100pt" />
                                <fo:table-column column-width="99pt" />
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell line-height="10pt" border-style="solid" border-width="1pt" border-color="white" display-align="before" height="15pt" number-columns-spanned="4" text-align="right" width="43pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt">
                                            <fo:block>
                                                <fo:external-graphic space-before.optimum="4pt" space-after.optimum="4pt">
                                                    <xsl:attribute name="src">url(''<xsl:text disable-output-escaping="yes">/export/home/www/https/tomcat5.0.25/webapps/coeus/images/couhes_byline2.gif</xsl:text>'')</xsl:attribute>
                                                </fo:external-graphic>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell line-height="10pt" border-style="solid" border-width="1pt" border-color="white" display-align="before" height="15pt" text-align="right" width="43pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt">
                                            <fo:block>
                                                <fo:inline font-weight="bold">To:</fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell line-height="10pt" border-style="solid" border-width="1pt" border-color="white" display-align="before" height="15pt" width="281pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                            <fo:block>
                                                <xsl:for-each select="n1:Protocol">
                                                    <xsl:for-each select="n1:Investigator">
                                                        <xsl:for-each select="n1:Person">
                                                            <xsl:for-each select="n1:Fullname">
                                                                <xsl:if test="../../n1:PI_flag =&apos;true&apos;">
                                                                    <fo:inline font-size="10pt">
                                                                        <xsl:apply-templates />
                                                                    </fo:inline>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell line-height="10pt" border-style="solid" border-width="1pt" border-color="white" display-align="before" height="15pt" text-align="right" width="100pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt">
                                            <fo:block>
                                                <fo:inline font-weight="bold">Date:</fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell line-height="10pt" border-style="solid" border-width="1pt" border-color="white" height="15pt" width="99pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
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
                                        <fo:table-cell line-height="10pt" border-style="solid" border-width="1pt" border-color="white" display-align="before" height="15pt" text-align="right" width="43pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt">
                                            <fo:block />
                                        </fo:table-cell>
                                        <fo:table-cell line-height="10pt" border-style="solid" border-width="1pt" border-color="white" display-align="before" height="15pt" width="281pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                            <fo:block>
                                                <xsl:for-each select="n1:Protocol">
                                                    <xsl:for-each select="n1:Investigator">
                                                        <xsl:for-each select="n1:Person">
                                                            <xsl:for-each select="n1:OfficeLocation">
                                                                <xsl:if test="../../n1:PI_flag =&apos;true&apos;">
                                                                    <xsl:apply-templates />
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell line-height="10pt" border-style="solid" border-width="1pt" border-color="white" display-align="before" height="15pt" text-align="right" width="100pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt">
                                            <fo:block />
                                        </fo:table-cell>
                                        <fo:table-cell line-height="10pt" border-style="solid" border-width="1pt" border-color="white" display-align="before" height="15pt" width="99pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                            <fo:block />
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell line-height="10pt" border-style="solid" border-width="1pt" border-color="white" display-align="before" height="15pt" text-align="right" width="43pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt">
                                            <fo:block>
                                                <fo:inline font-weight="bold">From:</fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell line-height="10pt" border-style="solid" border-width="1pt" border-color="white" display-align="before" height="15pt" width="281pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                            <fo:block>
                                                <xsl:for-each select="n1:CommitteeMasterData">
                                                    <xsl:for-each select="n1:CommitteeName">
                                                        <fo:inline font-size="10pt">
                                                            <xsl:apply-templates />
                                                        </fo:inline>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell line-height="10pt" border-style="solid" border-width="1pt" border-color="white" display-align="before" height="15pt" text-align="right" width="100pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt">
                                            <fo:block>
                                                <fo:inline font-weight="bold">Action Date:</fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell line-height="10pt" border-style="solid" border-width="1pt" border-color="white" display-align="before" height="15pt" width="99pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                            <fo:block>
                                                <xsl:for-each select="n1:Protocol">
                                                    <xsl:for-each select="n1:ProtocolMasterData">
                                                        <xsl:for-each select="n1:ExpirationDate">
                                                            <fo:inline font-size="10pt">
                                                                <xsl:value-of select="format-number(number(substring(string(.), 6, 2)), ''00'')" />
                                                                <xsl:text>/</xsl:text>
                                                                <xsl:value-of select="format-number(number(substring(string(.), 9, 2)), ''00'')" />
                                                                <xsl:text>/</xsl:text>
                                                                <xsl:value-of select="format-number(number(substring(string(.), 1, 4)), ''0000'')" />
                                                            </fo:inline>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                            <fo:table font-size="10pt" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                 <fo:table-column column-width="43pt" />
                                <fo:table-column column-width="281pt" />
                               <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" text-align="right" width="43pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt">
                                            <fo:block>
                                                <fo:inline font-weight="bold">Re:</fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="281pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                            <fo:block>Protocol #: <xsl:for-each select="n1:Protocol">
                                                    <xsl:for-each select="n1:ProtocolMasterData">
                                                        <xsl:for-each select="n1:ProtocolNumber">
                                                            <xsl:apply-templates />
                                                        </xsl:for-each>: <xsl:for-each select="n1:ProtocolTitle">
                                                            <xsl:apply-templates />
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                            <fo:inline font-size="10pt">This letter serves as an IRB notification reminder by the </fo:inline>
                            <xsl:for-each select="n1:CommitteeMasterData">
                                <xsl:for-each select="n1:CommitteeName">
                                    <fo:inline font-size="10pt">
                                        <xsl:apply-templates />
                                    </fo:inline>
                                </xsl:for-each>
                            </xsl:for-each>
                            <fo:inline font-size="10pt">.&#160; Recently a protocol was returned to you with a request for revision.&#160;  It is the primary responsibility of the Principal Investigator to ensure that the protocol is resubmitted with the necessary changes.&#160; </fo:inline>
                            <fo:block>
                                <fo:leader leader-pattern="space" />
                            </fo:block>
                            <fo:inline font-size="10pt">P</fo:inline>
                            <fo:inline font-size="10pt">lease note that the level of scrutiny given to the resubmitted protocol is the same as that of any new protocol.&#160;  All requests for approval must be reviewed at a convened IRB meeting, except for those protocols that meet the criteria for expedited review.&#160;</fo:inline>
                             <fo:block>
                                <xsl:text>&#xA;</xsl:text>
                            </fo:block>
                        </xsl:for-each>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
','admin',NOW(),UUID(),1)
/
delimiter ;
