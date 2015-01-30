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
    VALUES ((SELECT MAX(ID) FROM SEQ_PROTO_CORRESP_TEMPL),(SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Substantive Revisions Required Letter'),'DEFAULT','DEFAULT-4-SubstantiveRevisionsRequiredLetter.xsl',
'<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:n1="http://irb.mit.edu/irbnamespace" xmlns:xs="http://www.w3.org/2001/XMLSchema">
    <xsl:variable name="fo:layout-master-set">
        <fo:layout-master-set>
            <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.6in" margin-right="0.6in">
                 <fo:region-body margin-top="0.79in" margin-bottom="0.79in" />
               <fo:region-before extent="0.79in" />
            </fo:simple-page-master>
        </fo:layout-master-set>
    </xsl:variable>
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
                                    <fo:table-cell padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" height="30pt" number-columns-spanned="2" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell font-size="inherited-property-value(&apos;font-size&apos;) - 2pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="left" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell font-size="inherited-property-value(&apos;font-size&apos;) - 2pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="right" width="150pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" number-columns-spanned="2" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
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
                                        <fo:table-column column-width="proportional-column-width(20)" />
                                        <fo:table-column column-width="proportional-column-width(80)" />
                                        <fo:table-body>
                                            <fo:table-row>
                                                <fo:table-cell display-align="before" width="20%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <fo:inline font-size="10pt" font-weight="bold">To:</fo:inline>
                                                        <fo:inline font-size="10pt">&#160;&#160;&#160;&#160;&#160; </fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell width="80%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
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
                                                <fo:table-cell display-align="before" width="20%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <fo:inline font-size="10pt" font-weight="bold">From:</fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell width="80%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <xsl:for-each select="n1:Protocol">
                                                            <xsl:for-each select="n1:Submissions">
                                                                <xsl:for-each select="n1:CommitteeMember">
                                                                    <xsl:if test="n1:CommitteeMemberRole/n1:MemberRoleDesc = &apos;Chair&apos;">
                                                                        <xsl:for-each select="n1:Person">
                                                                            <xsl:for-each select="n1:Firstname">
                                                                                <xsl:if test="../../../n1:CurrentSubmissionFlag =&apos;No&apos;">
                                                                                    <fo:inline font-size="10pt">
                                                                                        <xsl:apply-templates />
                                                                                    </fo:inline>
                                                                                </xsl:if>
                                                                            </xsl:for-each>
                                                                            <fo:inline font-size="10pt">&#160;</fo:inline>
                                                                            <xsl:for-each select="n1:LastName">
                                                                                <xsl:if test="../../../n1:CurrentSubmissionFlag =&apos;No&apos;">
                                                                                    <fo:inline font-size="10pt">
                                                                                        <xsl:apply-templates />
                                                                                    </fo:inline>
                                                                                </xsl:if>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:if>
                                                                </xsl:for-each>
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
                                                <fo:table-cell display-align="before" width="20%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <fo:inline font-size="10pt" font-weight="bold">Date:</fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell width="80%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <xsl:for-each select="n1:CurrentDate">
                                                            <fo:inline font-size="10pt" />
                                                            <xsl:value-of select="format-number(substring(., 6, 2), ''00'')" />
                                                            <xsl:text>/</xsl:text>
                                                            <xsl:value-of select="format-number(substring(., 9, 2), ''00'')" />
                                                            <xsl:text>/</xsl:text>
                                                            <xsl:value-of select="format-number(substring(., 1, 4), ''0000'')" />
                                                        </xsl:for-each>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                            <fo:table-row>
                                                <fo:table-cell display-align="before" width="20%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <fo:inline font-size="10pt" font-weight="bold">Committee Action:</fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell width="80%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <fo:inline font-size="10pt" font-weight="bold">Substantive Revisions Required</fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                        </fo:table-body>
                                    </fo:table>
                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                        <fo:block text-align="justify">
                                            <fo:block>
                                                <xsl:text>&#xA;</xsl:text>
                                            </fo:block>
                                            <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                <fo:table-column column-width="127pt" />
                                                <fo:table-column column-width="proportional-column-width(80)" />
                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell display-align="before" width="127pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                            <fo:block>
                                                                <fo:inline font-size="10pt" font-weight="bold">IRB Action Date: </fo:inline>
                                                                <fo:inline font-size="10pt">&#160;&#160;&#160;&#160; </fo:inline>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell display-align="before" width="80%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                            <fo:block>
                                                                <xsl:for-each select="n1:Protocol">
                                                                    <xsl:for-each select="n1:Submissions">
                                                                        <xsl:for-each select="n1:SubmissionDetails">
                                                                            <xsl:for-each select="n1:ActionType">
                                                                                <xsl:for-each select="n1:ActionDate">
                                                                                    <xsl:if test="../../../n1:CurrentSubmissionFlag =&apos;No&apos;">
                                                                                        <xsl:value-of select="format-number(substring(., 6, 2), ''00'')" />
                                                                                        <xsl:text>/</xsl:text>
                                                                                        <xsl:value-of select="format-number(substring(., 9, 2), ''00'')" />
                                                                                        <xsl:text>/</xsl:text>
                                                                                        <xsl:value-of select="format-number(substring(., 1, 4), ''0000'')" />
                                                                                    </xsl:if>
                                                                                </xsl:for-each>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell width="20%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                            <fo:block>
                                                                <fo:inline font-size="10pt" font-weight="bold">IRB Protocol #:</fo:inline>
                                                                <fo:inline font-size="10pt">&#160;&#160;&#160; </fo:inline>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell display-align="before" width="80%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
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
                                                        <fo:table-cell width="127pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                            <fo:block>
                                                                <fo:inline font-size="10pt" font-weight="bold">Study Title:</fo:inline>
                                                                <fo:inline font-size="10pt">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </fo:inline>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell display-align="before" width="80%" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
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
                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                        <fo:block>
                                                            <fo:inline font-size="10pt">At its meeting on </fo:inline>
                                                            <xsl:for-each select="n1:Protocol">
                                                                <xsl:for-each select="n1:Submissions">
                                                                    <xsl:for-each select="n1:ScheduleMasterData">
                                                                        <xsl:for-each select="n1:MeetingDate">
                                                                            <xsl:if test="../../n1:CurrentSubmissionFlag =&apos;No&apos;">
                                                                                <fo:inline font-size="10pt" />
                                                                                <xsl:value-of select="format-number(substring(., 6, 2), ''00'')" />
                                                                                <xsl:text>/</xsl:text>
                                                                                <xsl:value-of select="format-number(substring(., 9, 2), ''00'')" />
                                                                                <xsl:text>/</xsl:text>
                                                                                <xsl:value-of select="format-number(substring(., 1, 4), ''0000'')" />
                                                                            </xsl:if>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                            <fo:inline font-size="10pt">, the Committee On the Use of Humans as Experimental Subjects</fo:inline>
                                                            <fo:inline font-size="10pt"> reviewed the above mentioned protocol and determined that substantive revisions are required. These revisions are noted below.&#160; If you agree with all of the committee&apos;s revisions, incorporate them in a revised protocol and/or consent form and submit it to the </fo:inline>
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
                                                            <fo:inline font-size="10pt"> for expeditious review.</fo:inline>
                                                        </fo:block>
                                                    </fo:block>
                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                        <fo:block>
                                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block>
                                                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                <fo:block>
                                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:block>
                                                                                            <fo:inline font-size="10pt">If you disagree with the committee&apos;s recommendations, you may do the following:&#160; Please justify to the </fo:inline>
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
                                                                                            <fo:inline font-size="10pt"> why the revisions should not be incorporated. </fo:inline>
                                                                                            <fo:block>
                                                                                                <fo:leader leader-pattern="space" />
                                                                                            </fo:block>
                                                                                            <fo:block>
                                                                                                <xsl:text>&#xA;</xsl:text>
                                                                                            </fo:block>
                                                                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                <fo:block>
                                                                                                    <fo:block color="black" space-before.optimum="-8pt">
                                                                                                        <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="1pt" />
                                                                                                    </fo:block>
                                                                                                    <fo:inline font-size="10pt" font-weight="bold">Requested Revisions:</fo:inline>
                                                                                                </fo:block>
                                                                                            </fo:block>
                                                                                        </fo:block>
                                                                                    </fo:block>
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
                                                            <xsl:for-each select="n1:Protocol">
                                                                <xsl:for-each select="n1:Submissions">
                                                                    <xsl:for-each select="n1:Minutes">
                                                                        <xsl:for-each select="n1:MinuteEntry">
                                                                            <fo:block white-space-collapse="false" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                <fo:block>
                                                                                    <xsl:if test="../n1:PrivateCommentFlag = &quot;false&quot; and  ../../n1:CurrentSubmissionFlag =&apos;No&apos;">
                                                                                        <fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm" start-indent="2mm" space-before.optimum="4pt" space-after.optimum="4pt">
                                                                                            <fo:list-item>
                                                                                                <fo:list-item-label end-indent="label-end()">
                                                                                                    <fo:block font-family="Courier" font-size="15pt" line-height="14pt" padding-before="2pt">&#x2022;</fo:block>
                                                                                                </fo:list-item-label>
                                                                                                <fo:list-item-body start-indent="body-start()">
                                                                                                    <fo:block>
                                                                                                        <fo:inline font-size="10pt">
                                                                                                            <xsl:apply-templates />
                                                                                                        </fo:inline>
                                                                                                    </fo:block>
                                                                                                </fo:list-item-body>
                                                                                            </fo:list-item>
                                                                                        </fo:list-block>
                                                                                    </xsl:if>
                                                                                </fo:block>
                                                                            </fo:block>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </xsl:for-each>
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
                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                        <fo:block>
                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                <fo:block>
                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                        <fo:block>
                                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block>
                                                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                <fo:block>
                                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:block>
                                                                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                <fo:block>
                                                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                        <fo:block>
                                                                                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                                <fo:block>
                                                                                                                    <fo:block>
                                                                                                                        <fo:leader leader-pattern="space" />
                                                                                                                    </fo:block>
                                                                                                                    <fo:block>
                                                                                                                        <xsl:text>&#xA;</xsl:text>
                                                                                                                    </fo:block>
                                                                                                                    <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                                        <fo:table-column column-width="31pt" />
                                                                                                                        <fo:table-column />
                                                                                                                        <fo:table-body>
                                                                                                                            <fo:table-row>
                                                                                                                                <fo:table-cell display-align="before" width="31pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                                                    <fo:block />
                                                                                                                                </fo:table-cell>
                                                                                                                                <fo:table-cell padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                                                    <fo:block />
                                                                                                                                </fo:table-cell>
                                                                                                                            </fo:table-row>
                                                                                                                        </fo:table-body>
                                                                                                                    </fo:table>
                                                                                                                    <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                                        <fo:table-column column-width="47pt" />
                                                                                                                        <fo:table-column column-width="452pt" />
                                                                                                                        <fo:table-body>
                                                                                                                            <fo:table-row>
                                                                                                                                <fo:table-cell width="47pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                                                    <fo:block>
                                                                                                                                        <fo:inline font-size="10pt">cc</fo:inline>
                                                                                                                                    </fo:block>
                                                                                                                                </fo:table-cell>
                                                                                                                                <fo:table-cell padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                                                    <fo:block>
                                                                                                                                        <fo:inline font-size="10pt">Tom Duff</fo:inline>
                                                                                                                                    </fo:block>
                                                                                                                                </fo:table-cell>
                                                                                                                            </fo:table-row>
                                                                                                                            <fo:table-row>
                                                                                                                                <fo:table-cell height="32pt" width="47pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                                                    <fo:block />
                                                                                                                                </fo:table-cell>
                                                                                                                                <fo:table-cell display-align="before" height="32pt" width="452pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                                                    <fo:block>
                                                                                                                                        <fo:block white-space-collapse="false" space-before.optimum="1pt" space-after.optimum="2pt">
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
                                                                                                                                        </fo:block>
                                                                                                                                    </fo:block>
                                                                                                                                </fo:table-cell>
                                                                                                                            </fo:table-row>
                                                                                                                        </fo:table-body>
                                                                                                                    </fo:table>
                                                                                                                </fo:block>
                                                                                                            </fo:block>
                                                                                                            <fo:block white-space-collapse="false" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                                <fo:block>
                                                                                                                    <fo:block>
                                                                                                                        <xsl:text>&#xA;</xsl:text>
                                                                                                                    </fo:block>
                                                                                                                </fo:block>
                                                                                                            </fo:block>
                                                                                                        </fo:block>
                                                                                                    </fo:block>
                                                                                                    <fo:block white-space-collapse="false" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                        <fo:block>
                                                                                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                                <fo:block>
                                                                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                                        <fo:block>
                                                                                                                            <fo:inline font-size="10pt">
</fo:inline>
                                                                                                                        </fo:block>
                                                                                                                    </fo:block>
                                                                                                                </fo:block>
                                                                                                            </fo:block>
                                                                                                        </fo:block>
                                                                                                    </fo:block>
                                                                                                </fo:block>
                                                                                            </fo:block>
                                                                                        </fo:block>
                                                                                    </fo:block>
                                                                                </fo:block>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:block>
                                                                </fo:block>
                                                            </fo:block>
                                                        </fo:block>
                                                    </fo:block>
                                                </fo:block>
                                            </fo:block>
                                        </fo:block>
                                    </fo:block>
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
