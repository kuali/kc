DELIMITER /
delete from PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE=
  (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Agenda Report')  
  AND  COMMITTEE_ID =  'DEFAULT'
/
commit
/
INSERT INTO SEQ_PROTO_CORRESP_TEMPL VALUES(NULL)
/
INSERT INTO PROTO_CORRESP_TEMPL(PROTO_CORRESP_TEMPL_ID,PROTO_CORRESP_TYPE_CODE,COMMITTEE_ID,FILE_NAME,CORRESPONDENCE_TEMPLATE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) values ((SELECT (MAX(ID)) FROM SEQ_PROTO_CORRESP_TEMPL),(SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Agenda Report'),'DEFAULT','9-AgendaReport.xslt',
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
                                            <xsl:if test="string-length(  n1:Schedule/n1:PreviousSchedule/n1:ScheduleMasterData/n1:MeetingDate ) \&gt; 0">
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
                                                            <xsl:if test="string-length(.) \&gt; 0">
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
                                            <xsl:if test="string-length(n1:Schedule/n1:NextSchedule/n1:ScheduleMasterData/n1:ScheduledDate) \&gt; 0">
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
                                                            <xsl:if test="string-length( . ) \&gt;0">
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
                                            <xsl:if test="string-length( n1:Schedule/n1:NextSchedule/n1:ScheduleMasterData/n1:Place  ) \&gt; 0">
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
                                                            <xsl:if test="string-length( . )  \&gt;0">
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
																<xsl:if test="n1:ProtocolNumber">
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
																	</xsl:if>
                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                        </fo:block>
                                                    </fo:block>
                                                    <xsl:for-each select="n1:ProtocolSummary">
                                                        <xsl:for-each select="n1:ProtocolMasterData"/>
                                                    </xsl:for-each>
													<fo:block>
													  <xsl:for-each select="n1:ProtocolSummary">
                                                       <xsl:for-each select="n1:Investigator">
													   <xsl:if test="n1:Person">
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
                                                                      
                                                                                <xsl:for-each select="n1:Person">
                                                                                    <xsl:for-each select="n1:Fullname">
                                                                                        <xsl:if test="../../n1:PI_flag  = \&apos;true\&apos;">
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
                                                                            
                                                                    </fo:block>
                                                                </fo:table-cell>
                                                            </fo:table-row>
                                                        </fo:table-body>
                                                    </fo:table>
													</xsl:if>
													</xsl:for-each>
													</xsl:for-each>
													</fo:block>
                                                </fo:block>
                                            </fo:block>
											<fo:block>
											 <xsl:for-each select="n1:SubmissionDetails">
                                              <xsl:for-each select="n1:ProtocolReviewer">
											  <xsl:if test="n1:ReviewerTypeCode=1">
											 
											
                                            <fo:inline-container>
													<fo:block>
														<xsl:text>&#x200B;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
													 <fo:table-column column-width="70"/>
                                                      <fo:table-column column-width="110"/>
                                                       <fo:table-column column-width="111"/>
                                                       <fo:table-column column-width="109"/>
													
													<fo:table-body start-indent="0pt">
														
															<fo:table-row>
																<fo:table-cell padding="0" display-align="center">
																	<fo:block text-align="left">
																		<fo:inline font-weight="bold">
																			<xsl:text>Primary Reviewers</xsl:text>
																		</fo:inline>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell padding="0" display-align="center">
																	<fo:block text-align="left">
																		
																				
																					<xsl:for-each select="n1:Person">
																						<xsl:for-each select="n1:Fullname">
																							<fo:inline>
																								<xsl:text>: </xsl:text>
																							</fo:inline>
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
																					</xsl:for-each>
																				
																		
																			
																	</fo:block>
																</fo:table-cell>
															</fo:table-row>
																												
													</fo:table-body>
												</fo:table>
                                                 </xsl:if>
											</xsl:for-each>
											</xsl:for-each>
											</fo:block> 
											<fo:block>
											 <xsl:for-each select="n1:SubmissionDetails">
                                              <xsl:for-each select="n1:ProtocolReviewer">
											  <xsl:if test="n1:ReviewerTypeCode=2">
											 
											
                                            <fo:inline-container>
													<fo:block>
														<xsl:text>&#x200B;</xsl:text>
													</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%" border-spacing="2pt">
													 <fo:table-column column-width="70"/>
                                                      <fo:table-column column-width="110"/>
                                                       <fo:table-column column-width="111"/>
                                                       <fo:table-column column-width="109"/>
													
													<fo:table-body start-indent="0pt">
														
															<fo:table-row>
																<fo:table-cell padding="0" display-align="center">
																	<fo:block text-align="left">
																		<fo:inline font-weight="bold">
																			<xsl:text>Secondary Reviewers</xsl:text>
																		</fo:inline>
																	</fo:block>
																</fo:table-cell>
																<fo:table-cell padding="0" display-align="center">
																	<fo:block text-align="left">
																		
																				
																					<xsl:for-each select="n1:Person">
																						<xsl:for-each select="n1:Fullname">
																							<fo:inline>
																								<xsl:text>: </xsl:text>
																							</fo:inline>
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
																					</xsl:for-each>
																				
																		
																			
																	</fo:block>
																</fo:table-cell>
															</fo:table-row>
																												
													</fo:table-body>
												</fo:table>
                                                 </xsl:if>
											</xsl:for-each>
											</xsl:for-each>
											</fo:block> 
																 
                                            
											
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
</xsl:stylesheet>','admin',NOW(),UUID(),1)
/
commit
/
DELIMITER ;
