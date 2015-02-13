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
    VALUES ((SELECT MAX(ID) FROM SEQ_PROTO_CORRESP_TEMPL),(SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Schedule Minutes'),'DEFAULT','DEFAULT-10-ScheduleMinutes.xsl',
'<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:n1="http://irb.mit.edu/irbnamespace">
    <xsl:key name="MinuteType" match="n1:Schedule/n1:Minutes" use="n1:EntrySortCode"/>
    <xsl:key name="ActionType" match="n1:Schedule/n1:Minutes" use="n1:ProtocolNumber"/>
    <xsl:key name="ReviewType" match="n1:Schedule/n1:ProtocolSubmission/n1:SubmissionDetails" use="n1:ProtocolReviewTypeCode"/>
    <xsl:variable name="fo:layout-master-set">
        <fo:layout-master-set>
            <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.6in" margin-right="0.6in" font-size="12pt">
                <fo:region-body margin-top="0.79in" margin-bottom="0.79in"/>
            </fo:simple-page-master>
        </fo:layout-master-set>
    </xsl:variable>
    <xsl:template match="/">
        <fo:root>
            <xsl:copy-of select="$fo:layout-master-set"/>
            <fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="10pt">
                        <fo:external-graphic space-before.optimum="4pt" space-after.optimum="4pt">
                            <xsl:attribute name="src">url(''<xsl:text disable-output-escaping="yes">http://localhost:8080/Coeus40/images/couhes_byline2.gif</xsl:text>'')</xsl:attribute>
                        </fo:external-graphic>
                        <fo:block color="black" space-before.optimum="-8pt">
                            <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="1pt"/>
                        </fo:block>
                        <fo:block>
                            <fo:leader leader-pattern="space"/>
                        </fo:block>
                        <fo:block font-weight="bold" font-size="12pt">
                            <fo:inline font-weight="bold"> Committe Minutes for </fo:inline>
                            <xsl:for-each select="n1:Schedule">
                                <xsl:for-each select="n1:ScheduleMasterData">
                                    <xsl:for-each select="n1:CommitteeName">
                                        <xsl:apply-templates/>
                                    </xsl:for-each>
                                </xsl:for-each>
                            </xsl:for-each>
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
                                                                <fo:block>
                                                                    <xsl:text>&#xA;</xsl:text>
                                                                </fo:block>
                                                                <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                    <fo:table-column column-width="113pt"/>
                                                                    <fo:table-column/>
                                                                    <fo:table-body>
                                                                        <fo:table-row>
                                                                            <fo:table-cell width="113pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>Meeting Date:</fo:block>
                                                                            </fo:table-cell>
                                                                            <fo:table-cell padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <xsl:for-each select="n1:Schedule/n1:ScheduleMasterData">
                                                                                        <xsl:for-each select="n1:MeetingDate">
                                                                                            <xsl:value-of select="format-number(substring(., 6, 2), ''00'')"/>
                                                                                            <xsl:text>/</xsl:text>
                                                                                            <xsl:value-of select="format-number(substring(., 9, 2), ''00'')"/>
                                                                                            <xsl:text>/</xsl:text>
                                                                                            <xsl:value-of select="format-number(substring(., 1, 4), ''0000'')"/>
                                                                                     </xsl:for-each>
                                                                                    </xsl:for-each>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                        </fo:table-row>
                                                                        <fo:table-row>
                                                                            <fo:table-cell width="113pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>Meeting Time:</fo:block>
                                                                            </fo:table-cell>
                                                                            <fo:table-cell padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <xsl:for-each select="n1:Schedule/n1:ScheduleMasterData">
                                                                                        <xsl:for-each select="n1:StartTime">
                                                                                            <xsl:value-of select="format-number(substring(substring-before(., '':''), string-length(substring-before(., '':'')) - 1), ''00'')"/>
                                                                                            <xsl:text>:</xsl:text>
                                                                                            <xsl:value-of select="format-number(substring-before(substring-after(., '':''), '':''), ''00'')"/>
                                                                                        </xsl:for-each>
                                                                                    </xsl:for-each>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                        </fo:table-row>
                                                                        <fo:table-row>
                                                                            <fo:table-cell width="113pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>Location:</fo:block>
                                                                            </fo:table-cell>
                                                                            <fo:table-cell padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                <fo:block>
                                                                                    <xsl:for-each select="n1:Schedule/n1:ScheduleMasterData">
                                                                                        <xsl:for-each select="n1:Place">
                                                                                            <xsl:apply-templates/>
                                                                                        </xsl:for-each>
                                                                                    </xsl:for-each>
                                                                                </fo:block>
                                                                            </fo:table-cell>
                                                                        </fo:table-row>
                                                                    </fo:table-body>
                                                                </fo:table>
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
                        <fo:block>
                            <fo:leader leader-pattern="space"/>
                        </fo:block>


            <fo:inline font-weight="bold">
                Members Present </fo:inline>
                <xsl:for-each select="n1:Schedule">
                            <xsl:for-each select="n1:Attendents">
                                <xsl:if test="n1:PresentFlag =&apos;true&apos; and  n1:AlternateFlag =&apos;false&apos; and  n1:GuestFlag =&apos;false&apos;">
                                    <fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm" start-indent="2mm" space-before.optimum="4pt" space-after.optimum="4pt">
                                        <xsl:for-each select="n1:AttendentName">
                                            <fo:list-item>
                                                <fo:list-item-label end-indent="label-end()">
                                                    <fo:block font-family="Courier" font-size="15pt" line-height="14pt" padding-before="2pt">&#x2022;</fo:block>
                                                </fo:list-item-label>
                                                <fo:list-item-body start-indent="body-start()">
                                                    <fo:block>
                                                        <xsl:apply-templates />
                                                    </fo:block>
                                                </fo:list-item-body>
                                            </fo:list-item>
                                        </xsl:for-each>
                                    </fo:list-block>
                                </xsl:if>
                            </xsl:for-each>
                        </xsl:for-each>
                        <xsl:for-each select="n1:Schedule">
                            <xsl:for-each select="n1:Attendents">
                                <xsl:choose>
                                    <xsl:when test="n1:AlternateFlag =&apos;true&apos; and  n1:PresentFlag =&apos;true&apos; and  n1:GuestFlag =&apos;false&apos;"><fo:inline font-weight="bold">
Alternates</fo:inline><fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm" start-indent="2mm" space-before.optimum="4pt" space-after.optimum="4pt">
                                            <xsl:for-each select="n1:AttendentName">
                                                <fo:list-item>
                                                    <fo:list-item-label end-indent="label-end()">
                                                        <fo:block font-family="Courier" font-size="15pt" line-height="14pt" padding-before="2pt">&#x2022;</fo:block>
                                                    </fo:list-item-label>
                                                    <fo:list-item-body start-indent="body-start()">
                                                        <fo:block>
                                                            <xsl:apply-templates />

                                                        </fo:block>
                                                    </fo:list-item-body>
                                                </fo:list-item>
                                            </xsl:for-each>
                                        </fo:list-block>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        <xsl:if test="n1:GuestFlag =&apos;true&apos; and  n1:PresentFlag =&apos;true&apos; and  n1:AlternateFlag =&apos;false&apos;"><fo:inline font-weight="bold">
Guests</fo:inline><fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm" start-indent="2mm" space-before.optimum="4pt" space-after.optimum="4pt">
                                                <xsl:for-each select="n1:AttendentName">
                                                    <fo:list-item>
                                                        <fo:list-item-label end-indent="label-end()">
                                                            <fo:block font-family="Courier" font-size="15pt" line-height="14pt" padding-before="2pt">&#x2022;</fo:block>
                                                        </fo:list-item-label>
                                                        <fo:list-item-body start-indent="body-start()">
                                                            <fo:block>
                                                                <xsl:apply-templates />
                                                            </fo:block>
                                                        </fo:list-item-body>
                                                    </fo:list-item>
                                                </xsl:for-each>
                                            </fo:list-block>
                                        </xsl:if>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </xsl:for-each>
                            <xsl:if test="n1:Attendents/n1:PresentFlag =&apos;false&apos;"><fo:inline font-weight="bold">
Absentees</fo:inline></xsl:if>
                            <xsl:for-each select="n1:Attendents">
                                <xsl:if test="n1:GuestFlag =&apos;false&apos; and  n1:AlternateFlag =&apos;false&apos; and  n1:PresentFlag =&apos;false&apos;">
                                    <fo:list-block provisional-distance-between-starts="7mm" provisional-label-separation="2mm" start-indent="2mm" space-before.optimum="4pt" space-after.optimum="4pt">
                                        <xsl:for-each select="n1:AttendentName">
                                            <fo:list-item>
                                                <fo:list-item-label end-indent="label-end()">
                                                    <fo:block font-family="Courier" font-size="15pt" line-height="14pt" padding-before="2pt">&#x2022;</fo:block>
                                                </fo:list-item-label>
                                                <fo:list-item-body start-indent="body-start()">
                                                    <fo:block>
                                                        <xsl:apply-templates />
                                                    </fo:block>
                                                </fo:list-item-body>
                                            </fo:list-item>
                                        </xsl:for-each>
                                    </fo:list-block>
                                </xsl:if>
                            </xsl:for-each>
                        </xsl:for-each>



                        <fo:block>
                            <fo:leader leader-pattern="space"/>
                        </fo:block>
                        <xsl:for-each select="n1:Schedule/n1:Minutes[generate-id(.)=generate-id(key(''MinuteType'',n1:EntrySortCode)[1])]">
                            <fo:block>
                                <fo:leader leader-pattern="space"/>
                            </fo:block>
                            <fo:inline font-weight="bold">
                                <xsl:value-of select="n1:EntryTypeDesc"/>
                            </fo:inline>
                            <fo:block>
                                <fo:leader leader-pattern="space"/>
                            </fo:block>
                            <xsl:for-each select="key(''MinuteType'',n1:EntrySortCode)">
                                <xsl:sort select="n1:Schedule/n1:Minutes/n1:ProtocolNumber"/>
                                <xsl:variable name="lastActionType" select="n1:ProtocolNumber"/>
                                <xsl:if test="not(preceding-sibling::n1:Minutes[n1:ProtocolNumber=$lastActionType])">
                                    <fo:block space-after="5pt">
                                        <fo:inline font-style="italic" text-decoration="underline">
                                            <xsl:value-of select="n1:ProtocolNumber"/>
                                        </fo:inline>
                                    </fo:block>
                                    <xsl:for-each select="n1:Minutes[n1:ProtocolNumber=$lastActionType]">
                                        <xsl:value-of select="n1:MinuteEntry"/>
                                    </xsl:for-each>
                                </xsl:if>
                                <fo:block space-after="7pt" white-space-collapse="false" linefeed-treatment="preserve" white-space-treatment="preserve">
                                    <fo:block>
                                        <xsl:value-of select="n1:MinuteEntry"/>
                                    </fo:block>
                                </fo:block>
                            </xsl:for-each>
                        </xsl:for-each>
                        <fo:block text-align="left" space-before.optimum="1pt" space-after.optimum="2pt">
                            <fo:block>
                                <fo:inline font-weight="bold" font-size="12pt">Protocols Submitted</fo:inline>
                            </fo:block>
                        </fo:block>
                        <fo:block>
                            <fo:leader leader-pattern="space"/>
                        </fo:block>
                        <xsl:for-each select="n1:Schedule/n1:ProtocolSubmission/n1:SubmissionDetails[generate-id(.)=generate-id(key (''ReviewType'',n1:ProtocolReviewTypeCode)[1])]">
                            <xsl:sort select="n1:ProtocolReviewTypeCode"/>
                            <xsl:sort select="n1:SubmissionTypeCode"/>
                            <fo:inline font-weight="bold">
                                <xsl:value-of select="concat(''Review Type: '',n1:ProtocolReviewTypeDesc)"/>
                            </fo:inline>
                            <fo:block>
                                <fo:leader leader-pattern="space"/>
                            </fo:block>
                         <fo:block start-indent="1em" end-indent="1em" text-align="left">
                                <xsl:for-each select="key(''ReviewType'',n1:ProtocolReviewTypeCode)">

                                <!-- case 893 - remove Withdrawn protocols  -->
                                 <xsl:if test="n1:SubmissionStatusCode != 210">

                                    <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                        <fo:table-column/>
                                        <fo:table-body>
                                            <fo:table-row>
                                                <fo:table-cell display-align="before" width="70pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <fo:inline font-weight="bold">
                                                            <xsl:value-of select="concat(''Submission Type: '',n1:SubmissionTypeDesc)"/>
                                                        </fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                        </fo:table-body>
                                    </fo:table>
                                    <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                        <fo:table-column column-width="106pt"/>
                                        <fo:table-column column-width="97pt"/>
                                        <fo:table-column column-width="119pt"/>
                                        <fo:table-column column-width="151pt"/>
                                        <fo:table-body>
                                            <fo:table-row>
                                                <fo:table-cell display-align="before" width="106pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <fo:inline font-weight="bold">Protocol #</fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell display-align="before" width="97pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <xsl:value-of select="n1:ProtocolNumber"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell display-align="before" width="119pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <fo:inline font-weight="bold">Submission Status</fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell display-align="before" width="151pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <xsl:value-of select="n1:SubmissionStatusDesc"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                        </fo:table-body>
                                    </fo:table>
                                    <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                        <fo:table-column column-width="109pt"/>
                                        <fo:table-column/>
                                        <fo:table-body>
                                            <fo:table-row>
                                                <fo:table-cell display-align="before" width="109pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <fo:inline font-weight="bold">Title</fo:inline>:</fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <xsl:value-of select="../n1:ProtocolSummary/n1:ProtocolMasterData/n1:ProtocolTitle"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                            <fo:table-row>
                                                <fo:table-cell display-align="before" width="109pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <fo:inline font-weight="bold">PI:</fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <xsl:value-of select="../n1:ProtocolSummary/n1:Investigator/n1:Person/n1:Fullname[../../n1:PI_flag=&apos;true&apos; ]"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                            <!-- change on 7-15 - remove expiration date
                                            <fo:table-row>

                                                <fo:table-cell display-align="before" width="109pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <fo:inline font-weight="bold">Expiration Dt:</fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>


                                                <fo:table-cell display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <xsl:if test="../n1:ProtocolSummary/n1:ProtocolMasterData/n1:ExpirationDate  != ''null''">
                                                            <xsl:value-of select="format-number(substring(../n1:ProtocolSummary/n1:ProtocolMasterData/n1:ExpirationDate, 6, 2), ''00'')"/>
                                                            <xsl:text>/</xsl:text>
                                                            <xsl:value-of select="format-number(substring(../n1:ProtocolSummary/n1:ProtocolMasterData/n1:ExpirationDate, 9, 2), ''00'')"/>
                                                            <xsl:text>/</xsl:text>
                                                            <xsl:value-of select="format-number(substring(../n1:ProtocolSummary/n1:ProtocolMasterData/n1:ExpirationDate, 1, 4), ''0000'')"/>
                                                        </xsl:if>
                                                    </fo:block>
                                                </fo:table-cell>


                                            </fo:table-row>
                                              -->
                                        </fo:table-body>
                                    </fo:table>
                                    <xsl:if test="count(../n1:Minutes/n1:MinuteEntry) > 0">
                                        <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                            <fo:table-column column-width="200pt"/>
                                            <fo:table-column/>
                                            <fo:table-body>
                                                <fo:table-row>
                                                    <fo:table-cell display-align="before" width="200pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                        <fo:block>
                                                            <fo:inline font-weight="bold">Minute Entries</fo:inline>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>
                                            </fo:table-body>
                                        </fo:table>
                                        <xsl:for-each select="../n1:Minutes/n1:MinuteEntry">
                                            <fo:block space-after="7pt" white-space-collapse="false" linefeed-treatment="preserve" white-space-treatment="preserve">
                                                <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column column-width="30pt"/>
                                                    <fo:table-column column-width="393pt"/>
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell display-align="before" width="30pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                                            <fo:block> </fo:block>

                                                        </fo:table-cell>
                                                            <fo:table-cell display-align="before" width="393pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                <fo:block>
                                                                    <xsl:value-of select="."/>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </xsl:for-each>
                                    </xsl:if>

                                    <!--added july 14 to hide -->
                                    <xsl:if test="n1:VotingComments != ''null'' or n1:YesVote > 0 or n1:NoVote > 0">


                                    <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                        <fo:table-column column-width="109pt"/>
                                        <fo:table-column column-width="47pt"/>
                                        <fo:table-column/>
                                        <fo:table-column/>
                                        <fo:table-column/>
                                        <fo:table-column/>
                                        <fo:table-body>
                                            <fo:table-row>
                                                <fo:table-cell display-align="before" width="109pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <fo:inline font-weight="bold">Yes Votes:</fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell display-align="before" width="47pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <xsl:value-of select="n1:YesVote"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <fo:inline font-weight="bold">No votes:</fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <xsl:value-of select="n1:NoVote"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <fo:inline font-weight="bold">Abstainers:</fo:inline>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell display-align="before" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block>
                                                        <xsl:value-of select="n1:AbstainerCount"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                            <fo:table-row>
                                                <fo:table-cell display-align="before" width="109pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                                    <fo:block>
                                                    <xsl:if test="n1:VotingComments != ''null''">
                                                        <fo:block>
                                                            <fo:inline font-weight="bold">Voting Comments:</fo:inline>
                                                        </fo:block>
                                                    </xsl:if>
                                                                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell display-align="before" number-columns-spanned="5" width="66pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                    <fo:block space-after="1pt" white-space-collapse="false" linefeed-treatment="preserve" white-space-treatment="preserve">
                                                        <xsl:if test="n1:VotingComments != ''null''">
                                                            <xsl:value-of select="n1:VotingComments"/>
                                                        </xsl:if>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                        </fo:table-body>
                                    </fo:table>
                                    <!-- added july 14 -->
                                    </xsl:if>

                                    <fo:block>
                                        <fo:leader leader-pattern="rule" leader-length="80%"/>
                                    </fo:block>
                                    <fo:block>
                                        <fo:leader leader-pattern="space"/>
                                    </fo:block>
                                    <!-- case 893 - remove Withdrawn protocols  -->
                                 </xsl:if>
                                </xsl:for-each>
                            </fo:block>
                        </xsl:for-each>
                        <fo:block>
                            <xsl:text>&#xA;</xsl:text>
                        </fo:block>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>','admin',NOW(),UUID(),1)
/
delimiter ;
