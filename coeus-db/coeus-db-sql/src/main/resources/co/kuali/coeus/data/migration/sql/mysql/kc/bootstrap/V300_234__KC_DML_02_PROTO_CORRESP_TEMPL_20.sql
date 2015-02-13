--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

delimiter /
INSERT INTO SEQ_PROTO_CORRESP_TEMPL VALUES (null)
/
INSERT INTO PROTO_CORRESP_TEMPL (PROTO_CORRESP_TEMPL_ID,PROTO_CORRESP_TYPE_CODE,COMMITTEE_ID,FILE_NAME,CORRESPONDENCE_TEMPLATE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
    VALUES ((SELECT MAX(ID) FROM SEQ_PROTO_CORRESP_TEMPL),(SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Renewal Reminder Letter #1'),'DEFAULT','DEFAULT-20-RenewalReminderLetter#1.xsl',
'<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:n1="http://irb.mit.edu/irbnamespace" xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xsl:variable name="fo:layout-master-set">
        <fo:layout-master-set>
            <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.8in" margin-right="0.8in">
                <fo:region-body margin-top="0.45in" margin-bottom="0.45in" />
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
                                                <fo:inline font-weight="bold">Expiration Date:</fo:inline>
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
                                <fo:table-column column-width="proportional-column-width(9)" />
                                <fo:table-column column-width="proportional-column-width(91)" />
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" text-align="right" width="9%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt">
                                            <fo:block>
                                                <fo:inline font-weight="bold">Re:</fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="91%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
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
                            <fo:inline font-size="10pt">.&#160; It is the primary responsibility of the Principal Investigator to ensure that the re-approval status for expiring protocols is achieved.&#160; All protocols must be re-approved annually by the IRB unless shorter intervals have been specified.&#160; </fo:inline>
                            <fo:block>
                                <fo:leader leader-pattern="space" />
                            </fo:block>
                            <fo:inline font-size="10pt">P</fo:inline>
                            <fo:inline font-size="10pt">lease note that the level of scrutiny given to the continuing review process is the same as that of any new protocol.&#160; All requests for re-approval must be reviewed at a convened IRB meeting, except for those protocols that meet the criteria for expedited review.</fo:inline>
                            <fo:block>
                                <fo:leader leader-pattern="space" />
                            </fo:block>
                            <fo:inline font-size="10pt">Please submit the following documents prior to the next COUHES meeting that is scheduled to meet before your expiration date:</fo:inline>
                            <fo:block>
                                <fo:leader leader-pattern="space" />
                            </fo:block>
                            <fo:block>
                                <xsl:text>&#xA;</xsl:text>
                            </fo:block>
                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                <fo:block>
                                    <fo:inline font-size="10pt">1) The original copy of the Continuing Review Questionnaire (CRQ).</fo:inline>
                                </fo:block>
                            </fo:block>
                            <fo:inline font-size="10pt">2) Two (2) copies of each consent form(s) used in the study (without the validation stamp to allow for revalidation).&#160; COUHES requires that MIT consent forms follow the template on the web site.&#160; </fo:inline>
                            <fo:inline font-size="10pt" font-weight="bold">Note: template updated in March, 2008.&#160; The &quot;Emergency Care and Compensation for Injury&quot; required language has changed.</fo:inline>
                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                <fo:block>
                                    <fo:inline font-size="10pt">3) A current protocol summary, inclusive of all amendments and revisions, which will serve as an IRB file copy.</fo:inline>
                                    <fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>
                                    <fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>
                                    <fo:inline font-size="10pt">Please note that you can obtain a copy of the Continuing Review Questionnaire through our web site : http://web.mit.edu/committees/couhes/forms.shtml.</fo:inline>
                                    <fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>
                                    <fo:inline font-size="10pt">As of July 1, 2003, all personnel involved in Human Subjects Research must complete the Human Subjects training course.&#160; It is the responsibility of the PI to make sure that all personnel associated with this study have completed the human subjects training course (see the COUHES web site for a link to the training).&#160; </fo:inline>
                                    <fo:inline font-size="10pt" font-weight="bold">Human subjects training must be updated every 3 years.&#160; Training must be current for all study personnel before renewal can be approved.</fo:inline>
                                    <fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>
                                    <fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>
                                    <fo:inline font-size="10pt">It is a violation of Massachusetts Institute of Technology policy and federal regulations to continue research activities after the approval period has expired.&#160; If the IRB has not reviewed and re-approved this research by its current expiration date, all enrollment, research activities and intervention on previously enrolled subjects must stop.&#160; If you believe that the health and welfare of the subjects will be jeopardized if the study treatment is discontinued, you may submit a written request to the IRB to continue treatment activities with currently enrolled subjects.</fo:inline>
                                    <fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>&#160;<fo:block>
                                        <fo:leader leader-pattern="space" />
                                    </fo:block>
                                    <fo:inline font-size="10pt">Your assistance and cooperation in ensuring that the above-mentioned protocol is received at the COUHES office in time for re-approval evaluation is greatly appreciated.</fo:inline>
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
