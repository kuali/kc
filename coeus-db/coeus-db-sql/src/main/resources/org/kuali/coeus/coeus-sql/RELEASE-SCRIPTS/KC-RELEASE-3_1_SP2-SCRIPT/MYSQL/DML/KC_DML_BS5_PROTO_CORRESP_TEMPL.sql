delete from PROTO_CORRESP_TEMPL where PROTO_CORRESP_TYPE_CODE =  10 and COMMITTEE_ID='DEFAULT';
delete from PROTO_CORRESP_TEMPL where PROTO_CORRESP_TYPE_CODE =  9 and COMMITTEE_ID='DEFAULT';
insert into SEQ_PROTO_CORRESP_TEMPL values (null);
insert into PROTO_CORRESP_TEMPL (PROTO_CORRESP_TEMPL_ID,PROTO_CORRESP_TYPE_CODE,COMMITTEE_ID,FILE_NAME,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID,CORRESPONDENCE_TEMPLATE) select max(ID), 10,'DEFAULT','10-ScheduleMinutes.xslt',now(),'admin',uuid(),
'<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:n1="http://irb.mit.edu/irbnamespace">
    <xsl:key name="MinuteType" match="n1:Schedule/n1:Minutes" use="n1:EntrySortCode"/>
    <xsl:key name="ActionType" match="n1:Schedule/n1:Minutes" use="n1:ProtocolNumber"/>
    <xsl:key name="ReviewType" match="n1:Schedule/n1:ProtocolSubmission/n1:SubmissionDetails" use="n1:ProtocolReviewTypeCode"/>
    <xsl:variable name="XML" select="/"/>
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
                            <xsl:attribute name="src">url(''<xsl:text disable-output-escaping="yes">images/couhes_byline2.gif</xsl:text>'')</xsl:attribute>
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
                        <!-- start the editing business -->
                        
                        <fo:inline font-weight="bold">
                            <xsl:text>Other Business</xsl:text>
                        </fo:inline>
                        <fo:inline-container>
                            <fo:block>
                                <xsl:text>&#x2029;</xsl:text>
                            </fo:block>
                        </fo:inline-container>
                        <xsl:if test="$XML/n1:Schedule/n1:OtherBusiness">
                            <fo:table table-layout="fixed" width="100%" border-spacing="2pt">
                                <fo:table-column column-width="proportional-column-width(1)"/>
                                <fo:table-column column-width="proportional-column-width(2)"/>
                                <fo:table-body start-indent="0pt">
                                    <xsl:for-each select="$XML">
                                        <xsl:for-each select="n1:Schedule">
                                            <xsl:for-each select="n1:OtherBusiness">
                                                <fo:table-row>
                                                    <fo:table-cell padding="5pt" display-align="center">
                                                        <fo:block>
                                                            <xsl:for-each select="n1:ActionItemCodeDesc">
                                                                <xsl:variable name="value-of-template">
                                                                    <xsl:apply-templates/>
                                                                </xsl:variable>
                                                                <xsl:choose>
                                                                    <xsl:when test="contains(string($value-of-template),''&#x2029;'')">
                                                                        <fo:block>
                                                                            <xsl:copy-of select="$value-of-template"/>
                                                                        </fo:block>
                                                                    </xsl:when>
                                                                    <xsl:otherwise>
                                                                        <fo:inline>
                                                                            <xsl:copy-of select="$value-of-template"/>
                                                                        </fo:inline>
                                                                    </xsl:otherwise>
                                                                </xsl:choose>
                                                            </xsl:for-each>
                                                            <fo:inline>
                                                                <xsl:text>:</xsl:text>
                                                            </fo:inline>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell padding="2pt" display-align="center">
                                                        <fo:block>
                                                            <xsl:for-each select="n1:ActionItemDesc">
                                                                <xsl:variable name="value-of-template">
                                                                    <xsl:apply-templates/>
                                                                </xsl:variable>
                                                                <xsl:choose>
                                                                    <xsl:when test="contains(string($value-of-template),''&#x2029;'')">
                                                                        <fo:block>
                                                                            <xsl:copy-of select="$value-of-template"/>
                                                                        </fo:block>
                                                                    </xsl:when>
                                                                    <xsl:otherwise>
                                                                        <fo:inline>
                                                                            <xsl:copy-of select="$value-of-template"/>
                                                                        </fo:inline>
                                                                    </xsl:otherwise>
                                                                </xsl:choose>
                                                            </xsl:for-each>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>
                                            </xsl:for-each>
                                        </xsl:for-each>
                                    </xsl:for-each>
                                </fo:table-body>
                            </fo:table>
                        </xsl:if>                       
                        <!-- Other Business Section ends -->    
                        
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
</xsl:stylesheet>
' FROM SEQ_PROTO_CORRESP_TEMPL;

insert into SEQ_PROTO_CORRESP_TEMPL values (null);
insert into PROTO_CORRESP_TEMPL (PROTO_CORRESP_TEMPL_ID,PROTO_CORRESP_TYPE_CODE,COMMITTEE_ID,FILE_NAME,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID,CORRESPONDENCE_TEMPLATE) select max(ID), 9,'DEFAULT','9-AgendaReport.xslt',now(),'admin',uuid(),
'<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fn="http://www.w3.org/2005/xpath-functions" xmlns:n1="http://irb.mit.edu/irbnamespace" xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output version="1.0" method="xml" encoding="UTF-8" indent="no"/>
    <xsl:param name="SV_OutputFormat" select="''PDF''"/>
    <xsl:variable name="XML" select="/"/>
    <xsl:variable name="fo:layout-master-set">
        <fo:layout-master-set>
            <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.6in" margin-right="0.6in">
                <fo:region-body margin-top="0.79in" margin-bottom="0.79in"/>
            </fo:simple-page-master>
        </fo:layout-master-set>
    </xsl:variable>
    <xsl:template match="/">
        <fo:root>
            <xsl:copy-of select="$fo:layout-master-set"/>
            <fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <xsl:for-each select="$XML">
                            <fo:inline-container>
                                <fo:block>
                                    <xsl:text>&#x2029;</xsl:text>
                                </fo:block>
                            </fo:inline-container>
                            <fo:block text-align="center" margin="0pt">
                                <fo:block>
                                    <fo:inline font-size="large">
                                        <xsl:text>Massachusetts Institute of Technology</xsl:text>
                                    </fo:inline>
                                </fo:block>
                            </fo:block>
                            <fo:inline-container>
                                <fo:block>
                                    <xsl:text>&#x2029;</xsl:text>
                                </fo:block>
                            </fo:inline-container>
                            <fo:block text-align="center" margin="0pt">
                                <fo:block>
                                    <fo:inline font-size="large" font-weight="bold">
                                        <xsl:text>Agenda</xsl:text>
                                    </fo:inline>
                                </fo:block>
                            </fo:block>
                            <xsl:for-each select="n1:Schedule">
                                <xsl:for-each select="n1:ScheduleMasterData">
                                    <xsl:for-each select="n1:CommitteeName">
                                        <fo:inline-container>
                                            <fo:block>
                                                <xsl:text>&#x2029;</xsl:text>
                                            </fo:block>
                                        </fo:inline-container>
                                        <fo:block text-align="center" margin="0pt">
                                            <fo:block>
                                                <xsl:variable name="value-of-template">
                                                    <xsl:apply-templates/>
                                                </xsl:variable>
                                                <xsl:choose>
                                                    <xsl:when test="contains(string($value-of-template),''&#x2029;'')">
                                                        <fo:block padding-bottom="2px" padding-top="2px">
                                                            <xsl:copy-of select="$value-of-template"/>
                                                        </fo:block>
                                                    </xsl:when>
                                                    <xsl:otherwise>
                                                        <fo:inline padding-bottom="2px" padding-top="2px">
                                                            <xsl:copy-of select="$value-of-template"/>
                                                        </fo:inline>
                                                    </xsl:otherwise>
                                                </xsl:choose>
                                            </fo:block>
                                        </fo:block>
                                    </xsl:for-each>
                                </xsl:for-each>
                            </xsl:for-each>
                            <xsl:for-each select="n1:Schedule">
                                <xsl:for-each select="n1:ScheduleMasterData">
                                    <xsl:for-each select="n1:ScheduledTime">
                                        <fo:inline-container>
                                            <fo:block>
                                                <xsl:text>&#x2029;</xsl:text>
                                            </fo:block>
                                        </fo:inline-container>
                                        <fo:block text-align="center" margin="0pt">
                                            <fo:block>
                                                <fo:inline margin-bottom=" ">
                                                    <xsl:value-of select="format-number(number(substring(string(string(.)), 1, 2)), ''00'')"/>
                                                    <xsl:text>:</xsl:text>
                                                    <xsl:value-of select="format-number(number(substring(string(string(.)), 4, 2)), ''00'')"/>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:block>
                                    </xsl:for-each>
                                </xsl:for-each>
                            </xsl:for-each>
                            <xsl:for-each select="n1:Schedule">
                                <xsl:for-each select="n1:ScheduleMasterData">
                                    <xsl:for-each select="n1:Place">
                                        <fo:inline-container>
                                            <fo:block>
                                                <xsl:text>&#x2029;</xsl:text>
                                            </fo:block>
                                        </fo:inline-container>
                                        <fo:block text-align="center" margin="0pt">
                                            <fo:block>
                                                <xsl:variable name="value-of-template">
                                                    <xsl:apply-templates/>
                                                </xsl:variable>
                                                <xsl:choose>
                                                    <xsl:when test="contains(string($value-of-template),''&#x2029;'')">
                                                        <fo:block>
                                                            <xsl:copy-of select="$value-of-template"/>
                                                        </fo:block>
                                                    </xsl:when>
                                                    <xsl:otherwise>
                                                        <fo:inline>
                                                            <xsl:copy-of select="$value-of-template"/>
                                                        </fo:inline>
                                                    </xsl:otherwise>
                                                </xsl:choose>
                                            </fo:block>
                                        </fo:block>
                                    </xsl:for-each>
                                </xsl:for-each>
                            </xsl:for-each>
                            <fo:block/>
                            <fo:block/>
                            <fo:inline font-weight="bold" text-decoration="underline">
                                <xsl:text>AGENDA ITEMS</xsl:text>
                            </fo:inline>
                            <fo:block/>
                            <fo:inline-container>
                                <fo:block>
                                    <xsl:text>&#x2029;</xsl:text>
                                </fo:block>
                            </fo:inline-container>
                            <fo:list-block margin-bottom="0" margin-top="0" provisional-distance-between-starts="7mm" provisional-label-separation="2mm">
                                <fo:list-item>
                                    <fo:list-item-label end-indent="label-end()" text-align="right">
                                        <fo:block font-family="Courier">&#x2022;</fo:block>
                                    </fo:list-item-label>
                                    <fo:list-item-body start-indent="body-start()">
                                        <fo:block>
                                            <xsl:if test="string-length(  n1:Schedule/n1:PreviousSchedule/n1:ScheduleMasterData/n1:MeetingDate ) &gt; 0">
                                                <fo:inline>
                                                    <xsl:text>Review the minutes of the meeting held on</xsl:text>
                                                </fo:inline>
                                            </xsl:if>
                                            <fo:inline>
                                                <xsl:text>&#160;</xsl:text>
                                            </fo:inline>
                                            <xsl:for-each select="n1:Schedule">
                                                <xsl:for-each select="n1:PreviousSchedule">
                                                    <xsl:for-each select="n1:ScheduleMasterData">
                                                        <xsl:for-each select="n1:MeetingDate">
                                                            <xsl:if test="string-length(.) &gt; 0">
                                                                <fo:inline>
                                                                    <xsl:value-of select="format-number(number(substring(string(string(.)), 6, 2)), ''00'')"/>
                                                                    <xsl:text>/</xsl:text>
                                                                    <xsl:value-of select="format-number(number(substring(string(string(.)), 9, 2)), ''00'')"/>
                                                                    <xsl:text>/</xsl:text>
                                                                    <xsl:value-of select="format-number(number(substring(string(string(string(.))), 1, 4)), ''0000'')"/>
                                                                </fo:inline>
                                                                <fo:inline>
                                                                    <xsl:text>.</xsl:text>
                                                                </fo:inline>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:list-item-body>
                                </fo:list-item>
                                <fo:list-item>
                                    <fo:list-item-label end-indent="label-end()" text-align="right">
                                        <fo:block font-family="Courier">&#x2022;</fo:block>
                                    </fo:list-item-label>
                                    <fo:list-item-body start-indent="body-start()">
                                        <fo:block>
                                            <xsl:if test="string-length(n1:Schedule/n1:NextSchedule/n1:ScheduleMasterData/n1:ScheduledDate) &gt; 0">
                                                <fo:inline>
                                                    <xsl:text>The next meeting will be held on</xsl:text>
                                                </fo:inline>
                                            </xsl:if>
                                            <fo:inline>
                                                <xsl:text>&#160;</xsl:text>
                                            </fo:inline>
                                            <xsl:for-each select="n1:Schedule">
                                                <xsl:for-each select="n1:NextSchedule">
                                                    <xsl:for-each select="n1:ScheduleMasterData">
                                                        <xsl:for-each select="n1:ScheduledDate">
                                                            <xsl:if test="string-length( . ) &gt;0">
                                                                <fo:inline>
                                                                    <xsl:value-of select="format-number(number(substring(string(string(.)), 6, 2)), ''00'')"/>
                                                                    <xsl:text>/</xsl:text>
                                                                    <xsl:value-of select="format-number(number(substring(string(string(.)), 9, 2)), ''00'')"/>
                                                                    <xsl:text>/</xsl:text>
                                                                    <xsl:value-of select="format-number(number(substring(string(string(string(.))), 1, 4)), ''0000'')"/>
                                                                </fo:inline>
                                                                <fo:inline>
                                                                    <xsl:text>.</xsl:text>
                                                                </fo:inline>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                            <fo:inline>
                                                <xsl:text>&#160; </xsl:text>
                                            </fo:inline>
                                            <xsl:if test="string-length( n1:Schedule/n1:NextSchedule/n1:ScheduleMasterData/n1:Place  ) &gt; 0">
                                                <fo:inline>
                                                    <xsl:text>It is scheduled to be held in&#160; at</xsl:text>
                                                </fo:inline>
                                            </xsl:if>
                                            <fo:inline>
                                                <xsl:text>&#160; </xsl:text>
                                            </fo:inline>
                                            <xsl:for-each select="n1:Schedule">
                                                <xsl:for-each select="n1:NextSchedule">
                                                    <xsl:for-each select="n1:ScheduleMasterData">
                                                        <xsl:for-each select="n1:Place">
                                                            <xsl:if test="string-length( . )  &gt;0">
                                                                <xsl:variable name="value-of-template">
                                                                    <xsl:apply-templates/>
                                                                </xsl:variable>
                                                                <xsl:choose>
                                                                    <xsl:when test="contains(string($value-of-template),''&#x2029;'')">
                                                                        <fo:block>
                                                                            <xsl:copy-of select="$value-of-template"/>
                                                                        </fo:block>
                                                                    </xsl:when>
                                                                    <xsl:otherwise>
                                                                        <fo:inline>
                                                                            <xsl:copy-of select="$value-of-template"/>
                                                                        </fo:inline>
                                                                    </xsl:otherwise>
                                                                </xsl:choose>
                                                                <fo:inline>
                                                                    <xsl:text>.</xsl:text>
                                                                </fo:inline>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:list-item-body>
                                </fo:list-item>
                            </fo:list-block>
                            <!-- change on other action start -->
                            
                            <fo:inline-container>
                            <fo:block>
                                <xsl:text>&#x2029;</xsl:text>
                            </fo:block>
                            </fo:inline-container>
                            
                            <fo:inline font-weight="bold" text-decoration="underline">
                                <xsl:text>OTHER ACTIONS</xsl:text>
                            </fo:inline>                            
                            <fo:inline-container>
                                <fo:block>
                                    <xsl:text>&#x2029;</xsl:text>
                                </fo:block>
                            </fo:inline-container>                  
                            <xsl:if test="$XML/n1:Schedule/n1:OtherBusiness">
                                <fo:table table-layout="fixed" width="100%" border-spacing="2pt">
                                    <fo:table-column column-width="proportional-column-width(1)"/>
                                    <fo:table-column column-width="proportional-column-width(1)"/>
                                    <fo:table-header start-indent="0pt">
                                        <fo:table-row>
                                            <fo:table-cell padding="2pt" display-align="center">
                                                <fo:block>
                                                    <fo:inline font-weight="bold">
                                                        <xsl:text>Action Type</xsl:text>
                                                    </fo:inline>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding="2pt" display-align="center">
                                                <fo:block>
                                                    <fo:inline font-weight="bold">
                                                        <xsl:text>Description</xsl:text>
                                                    </fo:inline>
                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-header>
                                    <fo:table-body start-indent="0pt">
                                        <xsl:for-each select="$XML">
                                            <xsl:for-each select="n1:Schedule">
                                                <xsl:for-each select="n1:OtherBusiness">
                                                    <fo:table-row>
                                                        <fo:table-cell padding="2pt" display-align="center">
                                                            <fo:block>
                                                                <xsl:for-each select="n1:ActionItemCodeDesc">
                                                                    <xsl:variable name="value-of-template">
                                                                        <xsl:apply-templates/>
                                                                    </xsl:variable>
                                                                    <xsl:choose>
                                                                        <xsl:when test="contains(string($value-of-template),''&#x2029;'')">
                                                                            <fo:block>
                                                                                <xsl:copy-of select="$value-of-template"/>
                                                                            </fo:block>
                                                                        </xsl:when>
                                                                        <xsl:otherwise>
                                                                            <fo:inline>
                                                                                <xsl:copy-of select="$value-of-template"/>
                                                                            </fo:inline>
                                                                        </xsl:otherwise>
                                                                    </xsl:choose>
                                                                </xsl:for-each>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="2pt" display-align="center">
                                                            <fo:block>
                                                                <xsl:for-each select="n1:ActionItemDesc">
                                                                    <xsl:variable name="value-of-template">
                                                                        <xsl:apply-templates/>
                                                                    </xsl:variable>
                                                                    <xsl:choose>
                                                                        <xsl:when test="contains(string($value-of-template),''&#x2029;'')">
                                                                            <fo:block>
                                                                                <xsl:copy-of select="$value-of-template"/>
                                                                            </fo:block>
                                                                        </xsl:when>
                                                                        <xsl:otherwise>
                                                                            <fo:inline>
                                                                                <xsl:copy-of select="$value-of-template"/>
                                                                            </fo:inline>
                                                                        </xsl:otherwise>
                                                                    </xsl:choose>
                                                                </xsl:for-each>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </xsl:for-each>
                                    </fo:table-body>
                                </fo:table>
                            </xsl:if>
                            <fo:inline-container>
                                <fo:block>
                                    <xsl:text>&#x2029;</xsl:text>
                                </fo:block>
                            </fo:inline-container>                          
                            <!--Other Business Changes End -->
                            <fo:block/>
                            <fo:inline-container>
                                <fo:block>
                                    <xsl:text>&#x2029;</xsl:text>
                                </fo:block>
                            </fo:inline-container>
                            <fo:inline font-weight="bold" text-decoration="underline">
                                <xsl:text>PROTOCOLS SUBMITTED FOR REVIEW</xsl:text>
                            </fo:inline>                            
                            <fo:inline-container>
                                <fo:block>
                                    <xsl:text>&#x2029;</xsl:text>
                                </fo:block>
                            </fo:inline-container>
                            <fo:inline>
                                <fo:block>
                                    <xsl:text>&#x2029;</xsl:text>
                                </fo:block>
                            </fo:inline>
                        <fo:block/>
                            <fo:inline-container>
                                <fo:block>
                                    <xsl:text>&#x2029;</xsl:text>
                                </fo:block>
                            </fo:inline-container>
                            <fo:block margin="0pt">
                                <fo:block>
                                    <xsl:for-each select="n1:Schedule">
                                        <xsl:for-each select="n1:ProtocolSubmission">
                                            <xsl:for-each select="n1:SubmissionDetails">
                                                <fo:block/>
                                                <xsl:for-each select="n1:SubmissionTypeDesc">
                                                    <xsl:variable name="value-of-template">
                                                        <xsl:apply-templates/>
                                                    </xsl:variable>
                                                    <xsl:choose>
                                                        <xsl:when test="contains(string($value-of-template),''&#x2029;'')">
                                                            <fo:block font-weight="bold">
                                                                <xsl:copy-of select="$value-of-template"/>
                                                            </fo:block>
                                                        </xsl:when>
                                                        <xsl:otherwise>
                                                            <fo:inline font-weight="bold">
                                                                <xsl:copy-of select="$value-of-template"/>
                                                            </fo:inline>
                                                        </xsl:otherwise>
                                                    </xsl:choose>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                            <fo:block/>
                                            <fo:inline-container>
                                                <fo:block>
                                                    <xsl:text>&#x2029;</xsl:text>
                                                </fo:block>
                                            </fo:inline-container>
                                            <fo:block margin="0pt">
                                                <fo:block>
                                                    <fo:inline-container>
                                                        <fo:block>
                                                            <xsl:text>&#x2029;</xsl:text>
                                                        </fo:block>
                                                    </fo:inline-container>
                                                        <fo:block margin="0pt">
                                                        <fo:block>
                                                            <xsl:for-each select="n1:ProtocolSummary">
                                                                <xsl:for-each select="n1:ProtocolMasterData">
                                                                    <fo:inline>
                                                                        <xsl:text>&#160;</xsl:text>
                                                                    </fo:inline>
                                                                    <fo:inline-container>
                                                                        <fo:block>
                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                        </fo:block>
                                                                    </fo:inline-container>
                                                                    <fo:table table-layout="fixed" width="100%" border-spacing="2pt">
                                                                        <fo:table-column column-width="70"/>
                                                                        <fo:table-column column-width="110"/>
                                                                        <fo:table-column column-width="111"/>
                                                                        <fo:table-column column-width="109"/>
                                                                        <fo:table-column column-width="109"/>
                                                                        <fo:table-body start-indent="0pt">
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="2pt" display-align="before">
                                                                                    <fo:block>
                                                                                        <fo:inline font-weight="bold">
                                                                                            <xsl:text>Protocol #: </xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="2pt" display-align="before">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="n1:ProtocolNumber">
                                                                                            <xsl:variable name="value-of-template">
                                                                                                <xsl:apply-templates/>
                                                                                            </xsl:variable>
                                                                                            <xsl:choose>
                                                                                                <xsl:when test="contains(string($value-of-template),''&#x2029;'')">
                                                                                                    <fo:block>
                                                                                                        <xsl:copy-of select="$value-of-template"/>
                                                                                                    </fo:block>
                                                                                                </xsl:when>
                                                                                                <xsl:otherwise>
                                                                                                    <fo:inline>
                                                                                                        <xsl:copy-of select="$value-of-template"/>
                                                                                                    </fo:inline>
                                                                                                </xsl:otherwise>
                                                                                            </xsl:choose>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="2pt" display-align="before">
                                                                                    <fo:block>
                                                                                        <fo:inline font-weight="bold">
                                                                                            <xsl:text>Expiration Date:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="2pt" text-align="left" display-align="before">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="n1:ExpirationDate">
                                                                                            <fo:inline>
                                                                                                <xsl:value-of select="format-number(number(substring(string(string(.)), 6, 2)), ''00'')"/>
                                                                                                <xsl:text>/</xsl:text>
                                                                                                <xsl:value-of select="format-number(number(substring(string(string(.)), 9, 2)), ''00'')"/>
                                                                                                <xsl:text>/</xsl:text>
                                                                                                <xsl:value-of select="format-number(number(substring(string(string(string(.))), 1, 4)), ''0000'')"/>
                                                                                            </fo:inline>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell padding="2pt" text-align="left" display-align="before">
                                                                                    <fo:block/>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell padding="2pt" display-align="before">
                                                                                    <fo:block>
                                                                                        <fo:inline font-weight="bold">
                                                                                            <xsl:text>Title:</xsl:text>
                                                                                        </fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell number-columns-spanned="4" padding="2pt" text-align="left" display-align="before">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="n1:ProtocolTitle">
                                                                                            <xsl:variable name="value-of-template">
                                                                                                <xsl:apply-templates/>
                                                                                            </xsl:variable>
                                                                                            <xsl:choose>
                                                                                                <xsl:when test="contains(string($value-of-template),''&#x2029;'')">
                                                                                                    <fo:block>
                                                                                                        <xsl:copy-of select="$value-of-template"/>
                                                                                                    </fo:block>
                                                                                                </xsl:when>
                                                                                                <xsl:otherwise>
                                                                                                    <fo:inline>
                                                                                                        <xsl:copy-of select="$value-of-template"/>
                                                                                                    </fo:inline>
                                                                                                </xsl:otherwise>
                                                                                            </xsl:choose>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                        </fo:block>
                                                    </fo:block>
                                                    <xsl:for-each select="n1:ProtocolSummary">
                                                        <xsl:for-each select="n1:ProtocolMasterData"/>
                                                    </xsl:for-each>
                                                    <fo:inline-container>
                                                        <fo:block>
                                                            <xsl:text>&#x2029;</xsl:text>
                                                        </fo:block>
                                                    </fo:inline-container>
                                                    <fo:table table-layout="fixed" width="100%" border-spacing="2pt">
                                                        <fo:table-column column-width="70"/>
                                                        <fo:table-column column-width="187"/>
                                                        <fo:table-body start-indent="0pt">
                                                            <fo:table-row>
                                                                <fo:table-cell padding="2pt" display-align="before">
                                                                    <fo:block>
                                                                        <fo:inline font-weight="bold">
                                                                            <xsl:text>PI:</xsl:text>
                                                                        </fo:inline>
                                                                    </fo:block>
                                                                </fo:table-cell>
                                                                <fo:table-cell padding="2pt" display-align="before">
                                                                    <fo:block>
                                                                        <xsl:for-each select="n1:ProtocolSummary">
                                                                            <xsl:for-each select="n1:Investigator">
                                                                                <xsl:for-each select="n1:Person">
                                                                                    <xsl:for-each select="n1:Fullname">
                                                                                        <xsl:if test="../../n1:PI_flag  = &apos;true&apos;">
                                                                                            <xsl:variable name="value-of-template">
                                                                                                <xsl:apply-templates/>
                                                                                            </xsl:variable>
                                                                                            <xsl:choose>
                                                                                                <xsl:when test="contains(string($value-of-template),''&#x2029;'')">
                                                                                                    <fo:block>
                                                                                                        <xsl:copy-of select="$value-of-template"/>
                                                                                                    </fo:block>
                                                                                                </xsl:when>
                                                                                                <xsl:otherwise>
                                                                                                    <fo:inline>
                                                                                                        <xsl:copy-of select="$value-of-template"/>
                                                                                                    </fo:inline>
                                                                                                </xsl:otherwise>
                                                                                            </xsl:choose>
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
                                            </fo:block>                                         
                                            <fo:inline-container>
                                                <fo:block>
                                                    <xsl:text>&#x2029;</xsl:text>
                                                </fo:block>
                                            </fo:inline-container>
                                            <fo:table table-layout="fixed" width="100%" border-spacing="0">
                                                <fo:table-column column-width="70"/>
                                                <fo:table-column column-width="141"/>
                                                <fo:table-column column-width="113"/>
                                                <fo:table-column column-width="100"/>
                                                <fo:table-body start-indent="0pt">
                                                    <fo:table-row>
                                                        <fo:table-cell padding="0" display-align="before">
                                                            <fo:block>
                                                                <fo:inline font-weight="bold">
                                                                    <xsl:text>Primary Reviewers:</xsl:text>
                                                                </fo:inline>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="0" display-align="before">
                                                            <fo:block>
                                                                <xsl:for-each select="n1:SubmissionDetails">
                                                                    <xsl:for-each select="n1:ProtocolReviewer">
                                                                        <xsl:for-each select="n1:Person">
                                                                            <xsl:for-each select="n1:Fullname">
                                                                                <xsl:if test="../../n1:ReviewerTypeCode = 1">
                                                                                    <fo:block>
                                                                                        <fo:leader leader-pattern="space"/>
                                                                                    </fo:block>
                                                                                    <fo:inline-container>
                                                                                        <fo:block>
                                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                                        </fo:block>
                                                                                    </fo:inline-container>
                                                                                    <fo:block margin="0pt">
                                                                                        <fo:block>
                                                                                            <xsl:variable name="value-of-template">
                                                                                                <xsl:apply-templates/>
                                                                                            </xsl:variable>
                                                                                            <xsl:choose>
                                                                                                <xsl:when test="contains(string($value-of-template),''&#x2029;'')">
                                                                                                    <fo:block>
                                                                                                        <xsl:copy-of select="$value-of-template"/>
                                                                                                    </fo:block>
                                                                                                </xsl:when>
                                                                                                <xsl:otherwise>
                                                                                                    <fo:inline>
                                                                                                        <xsl:copy-of select="$value-of-template"/>
                                                                                                    </fo:inline>
                                                                                                </xsl:otherwise>
                                                                                            </xsl:choose>
                                                                                        </fo:block>
                                                                                    </fo:block>
                                                                                </xsl:if>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="0" display-align="before">
                                                            <fo:block>
                                                                <fo:inline font-weight="bold">
                                                                    <xsl:text>Secondary Reviewers:</xsl:text>
                                                                </fo:inline>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell padding="0" display-align="before">
                                                            <fo:block>
                                                                <xsl:for-each select="n1:SubmissionDetails">
                                                                    <xsl:for-each select="n1:ProtocolReviewer">
                                                                        <xsl:for-each select="n1:Person">
                                                                            <xsl:for-each select="n1:Fullname">
                                                                                <xsl:if test="../../n1:ReviewerTypeCode =2">
                                                                                    <fo:block>
                                                                                        <fo:leader leader-pattern="space"/>
                                                                                    </fo:block>
                                                                                    <fo:inline-container>
                                                                                        <fo:block>
                                                                                            <xsl:text>&#x2029;</xsl:text>
                                                                                        </fo:block>
                                                                                    </fo:inline-container>
                                                                                    <fo:block margin="0pt">
                                                                                        <fo:block>
                                                                                            <xsl:variable name="value-of-template">
                                                                                                <xsl:apply-templates/>
                                                                                            </xsl:variable>
                                                                                            <xsl:choose>
                                                                                                <xsl:when test="contains(string($value-of-template),''&#x2029;'')">
                                                                                                    <fo:block>
                                                                                                        <xsl:copy-of select="$value-of-template"/>
                                                                                                    </fo:block>
                                                                                                </xsl:when>
                                                                                                <xsl:otherwise>
                                                                                                    <fo:inline>
                                                                                                        <xsl:copy-of select="$value-of-template"/>
                                                                                                    </fo:inline>
                                                                                                </xsl:otherwise>
                                                                                            </xsl:choose>
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
                                        </xsl:for-each>
                                    </xsl:for-each>
                                </fo:block>
                            </fo:block>
                        </xsl:for-each>
                    </fo:block>
                    <fo:block id="SV_RefID_PageTotal"/>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
    <xsl:template match="n1:ProtocolSubmission">
        <fo:block/>
        <fo:block/>
    </xsl:template>
    <xsl:template match="n1:ProtocolSummary">
        <fo:block/>
    </xsl:template>
    <xsl:template match="n1:ResearchArea">
        <xsl:variable name="value-of-template">
            <xsl:apply-templates/>
        </xsl:variable>
        <xsl:choose>
            <xsl:when test="contains(string($value-of-template),''&#x2029;'')">
                <fo:block>
                    <xsl:copy-of select="$value-of-template"/>
                </fo:block>
            </xsl:when>
            <xsl:otherwise>
                <fo:inline>
                    <xsl:copy-of select="$value-of-template"/>
                </fo:inline>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
	<xsl:template name="double-backslash">
		<xsl:param name="text"/>
		<xsl:param name="text-length"/>
		<xsl:variable name="text-after-bs" select="substring-after($text, \'\\\')"/>
		<xsl:variable name="text-after-bs-length" select="string-length($text-after-bs)"/>
		<xsl:choose>
			<xsl:when test="$text-after-bs-length = 0">
				<xsl:choose>
					<xsl:when test="substring($text, $text-length) = \'\\\'">
						<xsl:value-of select="concat(substring($text,1,$text-length - 1), \'\\\\\')"/>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="$text"/>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="concat(substring($text,1,$text-length - $text-after-bs-length - 1), \'\\\\\')"/>
				<xsl:call-template name="double-backslash">
					<xsl:with-param name="text" select="$text-after-bs"/>
					<xsl:with-param name="text-length" select="$text-after-bs-length"/>
				</xsl:call-template>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>' FROM SEQ_PROTO_CORRESP_TEMPL;

commit;