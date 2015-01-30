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

INSERT INTO PROTO_CORRESP_TEMPL (PROTO_CORRESP_TEMPL_ID,PROTO_CORRESP_TYPE_CODE,COMMITTEE_ID,FILE_NAME,CORRESPONDENCE_TEMPLATE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
    VALUES (SEQ_PROTO_CORRESP_TEMPL.NEXTVAL,(SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice'),'DEFAULT','DEFAULT-26-ClosureNotice.xsl',EMPTY_CLOB(),'admin',SYSDATE,SYS_GUID(),1)
/
DECLARE    data CLOB; buffer VARCHAR2(32000);
BEGIN
SELECT CORRESPONDENCE_TEMPLATE INTO data FROM PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE = (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice') AND COMMITTEE_ID = 'DEFAULT' FOR UPDATE;
buffer := '<?xml version="1.0" encoding="UTF-8"?>
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
                            <xsl:attribute name="src">url(''<xsl:text disable-output-escaping="yes">/export/home/www/https/tomcat5.0';
    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(32000);
BEGIN
SELECT CORRESPONDENCE_TEMPLATE INTO data FROM PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE = (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice') AND COMMITTEE_ID = 'DEFAULT' FOR UPDATE;
buffer := '.25/webapps/coeus/images/couhes_byline2.gif</xsl:text>'')</xsl:attribute>
                        </fo:external-graphic>
                        <fo:block color="black" space-before.optimum="-8pt">
                            <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="1pt" />
                        </fo:block>
                        <fo:block>
                            <fo:leader leader-pattern="space" />
                        </fo:block>
                        <xsl:choose>
                            <xsl:when test="n1:Correspondence/n1:Protocol/n1:ProtocolMasterData/n1:ProtocolStatusCode =300">
                                <xsl:for-each select="n1:Correspondence">
                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                        <fo:block>
                                            <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                <fo:table-column column-width="127pt" />
                                                <fo:table-column column-width="451pt" />
                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="127pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                            <fo:block>
                                                                <fo:inline font-size="10pt" font-weight="bold">To:</fo:inline>
                                                                <fo:inline font-size="10pt">&#160;&#160;&#160;&#160;&#160; </fo:inline>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="451pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
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
                                                                                </xsl:';
    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(32000);
BEGIN
SELECT CORRESPONDENCE_TEMPLATE INTO data FROM PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE = (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice') AND COMMITTEE_ID = 'DEFAULT' FOR UPDATE;
buffer := 'for-each>
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
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="127pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                            <fo:block>
                                                                <fo:inline font-size="10pt" font-weight="bold">From:</fo:inline>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="451pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
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
                                                                               ';
    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(32000);
BEGIN
SELECT CORRESPONDENCE_TEMPLATE INTO data FROM PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE = (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice') AND COMMITTEE_ID = 'DEFAULT' FOR UPDATE;
buffer := '         <fo:inline font-size="10pt">&#160;</fo:inline>
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
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" height="19pt" width="127pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                            <fo:block>
                                                                <fo:inline font-size="10pt" font-weight="bold">Date:</fo:inline>
                                                            </fo:block>
                                                        </fo:table-cell>
             ';
    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(32000);
BEGIN
SELECT CORRESPONDENCE_TEMPLATE INTO data FROM PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE = (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice') AND COMMITTEE_ID = 'DEFAULT' FOR UPDATE;
buffer := '                                           <fo:table-cell border-style="solid" border-width="1pt" border-color="white" height="19pt" width="451pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
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
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="127pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                            <fo:block>
                                                                <fo:inline font-size="10pt" font-weight="bold">Committee Action:</fo:inline>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="451pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                            <fo:block>
                                                                <fo:inline font-size="10pt" font-weight="bold">Protocol Administratively Closed</fo:inline>
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
                                                        <fo:table-column column-width="461pt" />
                                                        <fo:table-body>
                                                            <fo:table-row>
                                           ';
    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(32000);
BEGIN
SELECT CORRESPONDENCE_TEMPLATE INTO data FROM PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE = (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice') AND COMMITTEE_ID = 'DEFAULT' FOR UPDATE;
buffer := '                     <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="127pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                                    <fo:block>
                                                                        <fo:inline font-size="10pt" font-weight="bold">Expiration Date: </fo:inline>
                                                                        <fo:inline font-size="10pt">&#160;&#160;&#160;&#160; </fo:inline>
                                                                    </fo:block>
                                                                </fo:table-cell>
                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="461pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                                    <fo:block>
                                                                        <xsl:for-each select="n1:Protocol">
                                                                            <xsl:for-each select="n1:ProtocolMasterData">
                                                                                <xsl:for-each select="n1:ExpirationDate">
                                                                                    <xsl:value-of select="format-number(number(substring(string(.), 6, 2)), ''00'')" />
                                                                                    <xsl:text>/</xsl:text>
                                                                                    <xsl:value-of select="format-number(number(substring(string(.), 9, 2)), ''00'')" />
                                                                                    <xsl:text>/</xsl:text>
                                                                                    <xsl:value-of select="format-number(number(substring(string(.), 1, 4)), ''0000'')" />
                                                                                </xsl:for-each>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </fo:block>
                                                                </fo:table-cell>
                                                            </fo:table-row>
                                                            <fo:table-row>
                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="127pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                    <fo:block>
                                                                        <fo:inline font-size="10pt" font-weight="bold">IRB Protocol #: </fo:inline>
                                                                        <fo:inline font-size="10pt">&#160;&#160;&#160; </fo:inline>
                                                                    </fo:block>
                                                                </fo:table-cell>
                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="461pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                                    <fo:block>
                                                                        <xsl:for';
    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(32000);
BEGIN
SELECT CORRESPONDENCE_TEMPLATE INTO data FROM PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE = (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice') AND COMMITTEE_ID = 'DEFAULT' FOR UPDATE;
buffer := '-each select="n1:Protocol">
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
                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="127pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                    <fo:block>
                                                                        <fo:inline font-size="10pt" font-weight="bold">Study Title: </fo:inline>
                                                                        <fo:inline font-size="10pt">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </fo:inline>
                                                                    </fo:block>
                                                                </fo:table-cell>
                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="461pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
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
                       ';
    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(32000);
BEGIN
SELECT CORRESPONDENCE_TEMPLATE INTO data FROM PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE = (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice') AND COMMITTEE_ID = 'DEFAULT' FOR UPDATE;
buffer := '                     </fo:block>
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
                                                            <fo:inline font-size="10pt">This is to notify you that the above protocol has been ADMINISTRATIVELY CLOSED effective </fo:inline>
                                                            <xsl:for-each select="n1:Protocol">
                                                                <fo:inline font-size="10pt">
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
                                                                </fo:inline>
                                                            </xsl:for-each>
                                                            <fo:inline font-size="10pt"> due to your failure to return the required Continuing Review Questionnaire.&#160;&#160;&#160; Data collected after the expiration date is considered unapproved research and cannot be included with data collected during an approved period.&#160; Furthermore, data collected after termination of approval cannot be reported or published as research data.</fo:inline>
                                                            <fo:block>
                                                                <fo:leader leader-pattern="space" />
                                                            </fo:block>
                                                            <fo:block>
                                                                <fo:leader leader-pattern="space" />
                                                            </fo:block>
                                                            <fo:inline font-size="10pt">In accordance with';
    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(32000);
BEGIN
SELECT CORRESPONDENCE_TEMPLATE INTO data FROM PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE = (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice') AND COMMITTEE_ID = 'DEFAULT' FOR UPDATE;
buffer := ' MIT Policy and Federal Regulations, you may NOT continue any further research efforts covered by this protocol.&#160; If you wish to gather further research information, you must submit a new proposal for review by the Committee on the Use of Humans as Experimental Subjects.</fo:inline>
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
                                                <fo:leader';
    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(32000);
BEGIN
SELECT CORRESPONDENCE_TEMPLATE INTO data FROM PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE = (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice') AND COMMITTEE_ID = 'DEFAULT' FOR UPDATE;
buffer := ' leader-pattern="space" />
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
                                      ';
    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(32000);
BEGIN
SELECT CORRESPONDENCE_TEMPLATE INTO data FROM PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE = (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice') AND COMMITTEE_ID = 'DEFAULT' FOR UPDATE;
buffer := '                                      <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="452pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
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
                            </xsl:when>
                            <xsl:otherwise>
                                <fo:block>
                                    <fo:leader leader-pattern="space" />
                                </fo:block>
                                <xsl:for-each select="n1:Correspondence">
          ';
    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(32000);
BEGIN
SELECT CORRESPONDENCE_TEMPLATE INTO data FROM PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE = (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice') AND COMMITTEE_ID = 'DEFAULT' FOR UPDATE;
buffer := '                          <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                        <fo:block>
                                            <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                <fo:table-column column-width="127pt" />
                                                <fo:table-column column-width="451pt" />
                                                <fo:table-body>
                                                    <fo:table-row>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="127pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                            <fo:block>
                                                                <fo:inline font-size="10pt" font-weight="bold">To:</fo:inline>
                                                                <fo:inline font-size="10pt">&#160;&#160;&#160;&#160;&#160; </fo:inline>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="451pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
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
                                                                        ';
    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(32000);
BEGIN
SELECT CORRESPONDENCE_TEMPLATE INTO data FROM PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE = (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice') AND COMMITTEE_ID = 'DEFAULT' FOR UPDATE;
buffer := '                    </fo:inline>
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
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="127pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                            <fo:block>
                                                                <fo:inline font-size="10pt" font-weight="bold">From:</fo:inline>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="451pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                            <fo:block>
                                                                <fo:inline font-size="10pt">Leigh Firn, Chair </fo:inline>
                                                                <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                    <fo:block>COUHES</fo:block>
                                                                </fo:block>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" height="19pt" width="127pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                            <fo:block>
                                                                <fo:inline font-size="10pt" font-weight="bold">Date:</fo:inline>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" height="19pt" width="451pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                            <fo:block>
                                                                <xsl:for-each select="n1:CurrentDate">
                                                                    <fo:inline font-size="10pt">
                                                                        <xsl:value-of select="format-number(number(substring(string(.), 6, 2)), ''00'')" />
                                                                    ';
    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(32000);
BEGIN
SELECT CORRESPONDENCE_TEMPLATE INTO data FROM PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE = (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice') AND COMMITTEE_ID = 'DEFAULT' FOR UPDATE;
buffer := '    <xsl:text>/</xsl:text>
                                                                        <xsl:value-of select="format-number(number(substring(string(.), 9, 2)), ''00'')" />
                                                                        <xsl:text>/</xsl:text>
                                                                        <xsl:value-of select="format-number(number(substring(string(.), 1, 4)), ''0000'')" />
                                                                    </fo:inline>
                                                                </xsl:for-each>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                    </fo:table-row>
                                                    <fo:table-row>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="127pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                            <fo:block>
                                                                <fo:inline font-size="10pt" font-weight="bold">Committee Action:</fo:inline>
                                                            </fo:block>
                                                        </fo:table-cell>
                                                        <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="451pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                            <fo:block>
                                                                <fo:inline font-size="10pt" font-weight="bold">Protocol Administratively Closed</fo:inline>
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
                                                        <fo:table-column column-width="461pt" />
                                                        <fo:table-body>
                                                            <fo:table-row>
                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="127pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                                    <fo:block />
                                                                </fo:table-cell>
                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="461pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                                    <fo:block />
          ';
    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(32000);
BEGIN
SELECT CORRESPONDENCE_TEMPLATE INTO data FROM PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE = (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice') AND COMMITTEE_ID = 'DEFAULT' FOR UPDATE;
buffer := '                                                      </fo:table-cell>
                                                            </fo:table-row>
                                                            <fo:table-row>
                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="127pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                    <fo:block>
                                                                        <fo:inline font-size="10pt" font-weight="bold">IRB Protocol #: </fo:inline>
                                                                        <fo:inline font-size="10pt">&#160;&#160;&#160; </fo:inline>
                                                                    </fo:block>
                                                                </fo:table-cell>
                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="461pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
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
                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" width="127pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start">
                                                                    <fo:block>
                                                                        <fo:inline font-size="10pt" font-weight="bold">Study Title: </fo:inline>
                                                                        <fo:inline font-size="10pt">&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </fo:inline>
                                                                    </fo:block>
                                                                </fo:table-cell>
                                                                <fo:table-cell border-style="solid" border-width="1pt" border-color="white" display-align="before" width="461pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start">
                                                                    <fo:block>
                                                                        <xsl:for-each select="n1:Protocol">
                                                       ';
    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(32000);
BEGIN
SELECT CORRESPONDENCE_TEMPLATE INTO data FROM PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE = (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice') AND COMMITTEE_ID = 'DEFAULT' FOR UPDATE;
buffer := '                     <xsl:for-each select="n1:ProtocolMasterData">
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
                                                        <fo:block>
                                                            <fo:inline font-size="10pt">This is to notify you that the above protocol has been ADMINISTRATIVELY CLOSED effective </fo:inline>
                                                            <xsl:for-each select="n1:CurrentDate">
                                                                <fo:inline font-size="10pt">
                                                                    <fo:inline font-size="10pt">
                                                                        <xsl:value-of select="format-number(number(substring(string(.), 6, 2)), ''00'')" />
                                                                        <xsl:text>/</xsl:text>
                                                                        <xsl:value-of select="format-number(number(substring(string(.), 9, 2)), ''00'')" />
                                                                        <xsl:text>/</xsl:text>
                                                                        <xsl:value-of select="format-number(number(substring(string(.), 1, 4)), ''0000'')" />
                                                                    </fo:inline>
                                                                </fo:inline>
                                                            </xsl:for-each>
                                                            <fo:inline font-size="10pt">&#160; as per your request.&#160;&#160; Data collected after the closed date is considered unapproved research and cannot be included with data collected during an approved period.&#160; Furthermore, data collected after termination of approval cannot be reported or published as research data.</fo:inline>
                                                            <fo:block>
                                                                <fo:leader leade';
    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(32000);
BEGIN
SELECT CORRESPONDENCE_TEMPLATE INTO data FROM PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE = (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice') AND COMMITTEE_ID = 'DEFAULT' FOR UPDATE;
buffer := 'r-pattern="space" />
                                                            </fo:block>
                                                            <fo:block>
                                                                <fo:leader leader-pattern="space" />
                                                            </fo:block>
                                                            <fo:inline font-size="10pt">In accordance with MIT Policy and Federal Regulations, you may NOT continue any further research efforts covered by this protocol.
If you wish to gather further research information, you must submit a new proposal for review by the Committee on the Use of Humans as Experimental Subjects.</fo:inline>
                                                            <fo:block>
                                                                <xsl:text>&#xA;</xsl:text>
                                                            </fo:block>
                                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                <fo:block>
                                                                    <fo:inline font-size="10pt">You should retain a copy of this letter for your records.</fo:inline>
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
                                                                    </fo:bl';
    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(32000);
BEGIN
SELECT CORRESPONDENCE_TEMPLATE INTO data FROM PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE = (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice') AND COMMITTEE_ID = 'DEFAULT' FOR UPDATE;
buffer := 'ock>
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
                                                                                            ';
    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(32000);
BEGIN
SELECT CORRESPONDENCE_TEMPLATE INTO data FROM PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE = (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice') AND COMMITTEE_ID = 'DEFAULT' FOR UPDATE;
buffer := '    </fo:table-row>
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
           ';
    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(32000);
BEGIN
SELECT CORRESPONDENCE_TEMPLATE INTO data FROM PROTO_CORRESP_TEMPL WHERE PROTO_CORRESP_TYPE_CODE = (SELECT PROTO_CORRESP_TYPE_CODE FROM PROTO_CORRESP_TYPE WHERE DESCRIPTION = 'Closure Notice') AND COMMITTEE_ID = 'DEFAULT' FOR UPDATE;
buffer := '                                                                                 </fo:table-body>
                                                                                        </fo:table>
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
                                </xsl:for-each>
                                <fo:block>
                                    <xsl:text>&#xA;</xsl:text>
                                </fo:block>
                            </xsl:otherwise>
                        </xsl:choose>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
';
    DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
