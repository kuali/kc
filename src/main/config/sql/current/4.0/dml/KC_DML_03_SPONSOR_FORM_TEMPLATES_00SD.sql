DELETE FROM SPONSOR_FORM_TEMPLATES WHERE SPONSOR_FORM_ID = 1 AND PAGE_NUMBER = 5
/
commit
/
INSERT INTO SPONSOR_FORM_TEMPLATES (SPONSOR_FORM_TEMPLATE_ID,SPONSOR_FORM_ID,PAGE_NUMBER,PAGE_DESCRIPTION,FILE_NAME,CONTENT_TYPE,FORM_TEMPLATE,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) values (SEQ_SPONSOR_FORM_TEMPLATES.NEXTVAL,(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2),5,'Budget Summary Period 4','Budget Summary Period 4.xslt','text/xml',EMPTY_CLOB(),'admin',SYSDATE,SYS_GUID(),1)
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:n1="http://era.nih.gov/Projectmgmt/SBIR/CGAP/nihspecific.namespace" xmlns:n2="http://era.nih.gov/Projectmgmt/SBIR/CGAP/phs398.namespace" xmlns:n3="http://era.nih.gov/Projectmgmt/SBIR/CGAP/researchandrelated.namespace">
    <xsl:variable name="fo:layout-master-set">
        <fo:layout-master-set>
            <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.4in" margin-right="0.3in">
                <fo:region-body margin-top="0.2in" margin-bottom="0.2in" />
                <fo:region-after extent="0.2in" />
            </fo:simple-page-master>
        </fo:layout-master-set>
    </xsl:variable>
    <xsl:template match="/">
        <fo:root>
            <xsl:copy-of select="$fo:layout-master-set" />
            <fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
                <fo:static-content flow-name="xsl-region-after" display-align="after">
                    <fo:block>
                        <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                            <fo:table-column column-width="4.5in" />
                            <fo:table-column column-width="4in" />
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" height="4pt" number-columns-spanned="2" text-align="center" width="4.5in" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="left" width="4.5in" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" text-align="center" width="4in" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                        <fo:block>
                                            <fo:inline font-weight="bold">*SIGNATURES REQUIRED ONLY FOR REVISED BUDGET (GPG III.C)</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                </fo:static-content>
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="7pt" line-height="7pt">
                        <fo:table start-indent="((8.5in - 0.4in - 0.3in) - (((8.5in - 0.4in - 0.3in) * 100) div 100) ) div 2" end-indent="((8.5in - 0.4in - 0.3in) - (((8.5in - 0.4in - 0.3in) * 100) div 100) ) div 2" padding="0" text-align="center" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                            <fo:table-column column-width="4in" />
                            <fo:table-column column-width=".5in" />
                            <fo:table-column column-width=".5in" />
                            <fo:table-column column-width=".5in" />
                            <fo:table-column column-width=".5in" />
                            <fo:table-column column-width="1in" />
                   ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '         <fo:table-column column-width="1in" />
                            <fo:table-body>
                                <fo:table-row height=".05in">
                                    <fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" height="3pt" width="4in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" height="3pt" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" height="3pt" text-align="left" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" height="3pt" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" height="3pt" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" height="3pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" height="3pt" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" number-columns-spanned="2" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
               ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '                             <fo:inline font-size="9pt" font-weight="bold">SUMMARY PROPOSAL BUDGET</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" text-align="left" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell border-before-style="none" border-end-style="none" border-start-style="none" number-columns-spanned="4" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell border-before-style="none" border-end-style="none" border-start-style="none" font-size="9pt" font-weight="bold" number-columns-spanned="2" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>Budget Period 4</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-before-style="none" border-end-style="none" border-start-style="none" text-align="left" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell background-color="gray" border-before-width="thick" border-start-width="thick" number-columns-spanned="4" text-align="center" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="9pt" font-weight="bold">FOR SPONSOR&#160; USE ONLY</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row height=".15in">
                                    <fo:table-cell display-align="before" number-columns-spanned="3" number-rows-spanned="2" text-align="left" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">ORGANIZATION&#160;&#160; </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="n1:ResearchCoverPage">
                                                    <xsl:for-each select="ApplicantOrganization">
                                                        <xsl:for-each select="OrganizationName">
                                                            <fo:inline font-size="9pt">
                                                                <xsl:apply-templates />
                                 ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '                           </fo:inline>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="gray" border-start-width="thick" display-align="before" number-columns-spanned="2" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">PROPOSAL NO.</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="gray" number-columns-spanned="2" text-align="center" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">DURATION (MONTHS)</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row height=".15in">
                                    <fo:table-cell background-color="gray" border-start-width="thick" display-align="before" number-columns-spanned="2" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell background-color="gray" text-align="center" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">Proposed</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="gray" text-align="center" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">Granted</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell number-columns-spanned="3" text-align="left" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">PRINCIPAL INVESTIGATOR/PROJECT DIRECTOR&#160; </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="n1:ResearchCoverPage">
                     ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '                                   <xsl:for-each select="n1:ProgramDirectorPrincipalInvestigator">
                                                            <xsl:for-each select="Name">
                                                                <xsl:for-each select="LastName">
                                                                    <fo:inline font-size="7pt">
                                                                        <xsl:apply-templates />
                                                                    </fo:inline>
                                                                </xsl:for-each>
                                                                <fo:inline font-size="7pt">, </fo:inline>
                                                                <xsl:for-each select="FirstName">
                                                                    <fo:inline font-size="7pt">
                                                                        <xsl:apply-templates />
                                                                    </fo:inline>
                                                                </xsl:for-each>
                                                                <fo:inline font-size="7pt">&#160;</fo:inline>
                                                                <xsl:for-each select="MiddleName">
                                                                    <fo:inline font-size="7pt">
                                                                        <xsl:apply-templates />
                                                                    </fo:inline>
                                                                </xsl:for-each>
                                                                <fo:inline font-size="7pt">&#160;</fo:inline>
                                                                <xsl:for-each select="NameSuffix">
                                                                    <fo:inline font-size="7pt">
                                                                        <xsl:apply-templates />
                                                                    </fo:inline>
                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="gray" border-start-width="thick" display-align="before" number-columns-spanned="2" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">AWARD NO.</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="gray" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell background-color="gray" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := 'border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell number-columns-spanned="2" number-rows-spanned="2" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                <fo:block>
                                                    <fo:inline font-size="7pt">A. S</fo:inline>
                                                    <fo:inline font-size="7pt">ENIOR</fo:inline>
                                                    <fo:inline font-size="7pt"> PERSONNEL: PI/PD, Co-PIs, Faculty and Other Senior Associates</fo:inline>
                                                </fo:block>
                                            </fo:block>
                                            <fo:inline font-size="7pt">List each separately with name and </fo:inline>
                                            <fo:inline font-size="7pt">title. (A.7. Show number in brackets)</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell number-columns-spanned="3" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                <fo:block>
                                                    <fo:inline font-size="7pt">Sponsor-Fun</fo:inline>
                                                    <fo:inline font-size="7pt">ded</fo:inline>
                                                </fo:block>
                                            </fo:block>
                                            <fo:inline font-size="7pt">Person-months</fo:inline>
                                            <fo:inline font-size="7pt"></fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell number-rows-spanned="2" text-align="center" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                <fo:block>
                                                    <fo:inline font-size="7pt">Funds</fo:inline>
                                                </fo:block>
                                            </fo:block>
                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                <fo:block>
                                                    <fo:inline font-size="7pt">Requested by</fo:inline>
                                                </fo:block>
                                            </fo:block>
                                            <fo:inline font-size="7pt">Proposer</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                              ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '      <fo:table-cell number-rows-spanned="2" text-align="center" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                <fo:block>
                                                    <fo:inline font-size="7pt">Funds</fo:inline>
                                                </fo:block>
                                            </fo:block>
                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                <fo:block>
                                                    <fo:inline font-size="7pt">Granted by Sponsor</fo:inline>
                                                </fo:block>
                                            </fo:block>
                                            <fo:inline font-size="7pt">(If</fo:inline>
                                            <fo:inline font-size="7pt"> Different)</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">CA</fo:inline>
                                            <fo:inline font-size="7pt">L</fo:inline>
                                            <fo:inline font-size="7pt"></fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">ACAD</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">SUMR</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="6pt" number-columns-spanned="2" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">1. </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '
                                                            <xsl:for-each select="NSFSeniorPersonnel">
                                                                <xsl:if test="Rownumber=0">
                                                                    <xsl:for-each select="FullName">
                                                                        <fo:inline font-size="7pt">
                                                                            <xsl:apply-templates />
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="NSFSeniorPersonnel">
                                                                <xsl:if test="Rownumber=0">
                                                                    <xsl:for-each select="CalendarMonthsFunded">
                                                                        <fo:inline font-size="7pt">
                                                                            <xsl:apply-templates />
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="NSFSeniorPersonnel">
                                                                <xsl:if test="Rownumber=0">
                                                                    <xs';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := 'l:for-each select="AcademicMonthsFunded">
                                                                        <fo:inline font-size="7pt">
                                                                            <xsl:apply-templates />
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="NSFSeniorPersonnel">
                                                                <xsl:if test="Rownumber=0">
                                                                    <xsl:for-each select="SummerMonthsFunded">
                                                                        <fo:inline font-size="7pt">
                                                                            <xsl:apply-templates />
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="NSFSeniorPersonnel">
                                                                    <xsl:if test="Rownumber=0">
                                                                        <xsl:for-each select="FundsRequested">$<xsl:value-of select="format-number(., ''#,###,###,##0.00'')" />
                                                           ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '             </xsl:for-each>
                                                                    </xsl:if>
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="6pt" number-columns-spanned="2" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">2. </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="NSFSeniorPersonnel">
                                                                <xsl:if test="Rownumber=1">
                                                                    <xsl:for-each select="FullName">
                                                                        <fo:inline font-size="7pt">
                                                                            <xsl:apply-templates />
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="NSFSeniorPersonnel">
                                                                <xsl:if test="Rownumber=1">
                                                                    <xsl:for-ea';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := 'ch select="CalendarMonthsFunded">
                                                                        <fo:inline font-size="7pt">
                                                                            <xsl:apply-templates />
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="NSFSeniorPersonnel">
                                                                <xsl:if test="Rownumber=1">
                                                                    <xsl:for-each select="AcademicMonthsFunded">
                                                                        <fo:inline font-size="7pt">
                                                                            <xsl:apply-templates />
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="NSFSeniorPersonnel">
                                                                <xsl:if test="Rownumber=1">
                                                                    <xsl:for-each select="SummerMonthsFunded">
                                                                        <fo:inline font-size="7pt">
                                                                            <xsl:apply-templates />
                       ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '                                                 </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="NSFSeniorPersonnel">
                                                                    <xsl:if test="Rownumber=1">
                                                                        <xsl:for-each select="FundsRequested">$<xsl:value-of select="format-number(., ''#,###,###,##0.00'')" />
                                                                        </xsl:for-each>
                                                                    </xsl:if>
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="6pt" number-columns-spanned="2" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">3. </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="NSFSeniorPersonnel">
                                                                <xsl:if test="Rownumber=2">
                                                                    <xsl:fo';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := 'r-each select="FullName">
                                                                        <fo:inline font-size="7pt">
                                                                            <xsl:apply-templates />
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="NSFSeniorPersonnel">
                                                                <xsl:if test="Rownumber=2">
                                                                    <xsl:for-each select="CalendarMonthsFunded">
                                                                        <fo:inline font-size="7pt">
                                                                            <xsl:apply-templates />
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="NSFSeniorPersonnel">
                                                                <xsl:if test="Rownumber=2">
                                                                    <xsl:for-each select="AcademicMonthsFunded">
                                                                        <fo:inline font-size="7pt">
                                                                            <xsl:apply-templates />
                             ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '                                           </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="NSFSeniorPersonnel">
                                                                <xsl:if test="Rownumber=2">
                                                                    <xsl:for-each select="SummerMonthsFunded">
                                                                        <fo:inline font-size="7pt">
                                                                            <xsl:apply-templates />
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="NSFSeniorPersonnel">
                                                                    <xsl:if test="Rownumber=2">
                                                                        <xsl:for-each select="FundsRequested">$<xsl:value-of select="format-number(., ''#,###,###,##0.00'')" />
                                                                        </xsl:for-each>
                                                                    </xsl:if>
                                                                </xsl:for-each>
                                                            </xsl:if>
             ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '                                           </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="6pt" number-columns-spanned="2" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">4. </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="NSFSeniorPersonnel">
                                                                <xsl:if test="Rownumber=3">
                                                                    <xsl:for-each select="FullName">
                                                                        <fo:inline font-size="7pt">
                                                                            <xsl:apply-templates />
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="NSFSeniorPersonnel">
                                                                <xsl:if test="Rownumber=3">
                                                                    <xsl:for-each select="CalendarMonthsFunded">
                                                                        <fo:inline font-size="7pt">
                                                                            <xsl:apply-templates />
                                     ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '                                   </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="NSFSeniorPersonnel">
                                                                <xsl:if test="Rownumber=3">
                                                                    <xsl:for-each select="AcademicMonthsFunded">
                                                                        <fo:inline font-size="7pt">
                                                                            <xsl:apply-templates />
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="NSFSeniorPersonnel">
                                                                <xsl:if test="Rownumber=3">
                                                                    <xsl:for-each select="SummerMonthsFunded">
                                                                        <fo:inline font-size="7pt">
                                                                            <xsl:apply-templates />
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                   ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '         </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="NSFSeniorPersonnel">
                                                                    <xsl:if test="Rownumber=3">
                                                                        <xsl:for-each select="FundsRequested">$<xsl:value-of select="format-number(., ''#,###,###,##0.00'')" />
                                                                        </xsl:for-each>
                                                                    </xsl:if>
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="6pt" number-columns-spanned="2" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">5. </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="NSFSeniorPersonnel">
                                                                <xsl:if test="Rownumber=4">
                                                                    <xsl:for-each select="FullName">
                                                                        <fo:inline font-size="7pt">
                                                                            <xsl:apply-templates />
                                             ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '                           </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="NSFSeniorPersonnel">
                                                                <xsl:if test="Rownumber=4">
                                                                    <xsl:for-each select="CalendarMonthsFunded">
                                                                        <fo:inline font-size="7pt">
                                                                            <xsl:apply-templates />
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="NSFSeniorPersonnel">
                                                                <xsl:if test="Rownumber=4">
                                                                    <xsl:for-each select="AcademicMonthsFunded">
                                                                        <fo:inline font-size="7pt">
                                                                            <xsl:apply-templates />
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                         ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '   </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="NSFSeniorPersonnel">
                                                                <xsl:if test="Rownumber=4">
                                                                    <xsl:for-each select="SummerMonthsFunded">
                                                                        <fo:inline font-size="7pt">
                                                                            <xsl:apply-templates />
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="NSFSeniorPersonnel">
                                                                    <xsl:if test="Rownumber=4">
                                                                        <xsl:for-each select="FundsRequested">$<xsl:value-of select="format-number(., ''#,###,###,##0.00'')" />
                                                                        </xsl:for-each>
                                                                    </xsl:if>
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                      ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '                  </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="6pt" number-columns-spanned="2" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">6. ( </fo:inline>
                                            <xsl:if test="n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFTotalSeniorPersonnel &gt;5">
                                                <fo:inline font-size="7pt">
                                                    <xsl:value-of select="(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFTotalSeniorPersonnel)-5" />
                                                </fo:inline>
                                            </xsl:if>
                                            <fo:inline font-size="7pt"> ) OTHERS (LIST INDIVIDUALLY ON BUDGET EXPLANATION PAGE)</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:value-of select="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFSeniorPersonnel[Rownumber&gt;4]/CalendarMonthsFunded)" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:value-of select="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFSeniorPersonnel[Rownumber&gt;4]/AcademicMonthsFunded)" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:value-of select="sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFSeniorPersonnel[Rownumber&gt;4]/SummerMonthsFunded)" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>$<xsl:value-of select="format-number(sum(n1:ResearchAndRe';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := 'latedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFSeniorPersonnel[Rownumber&gt;4]/FundsRequested), ''#,###,###,##0.00'')" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="6pt" number-columns-spanned="2" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">7. ( </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID =4">
                                                                <xsl:for-each select="NSFTotalSeniorPersonnel">
                                                                    <xsl:apply-templates />
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                            <fo:inline font-size="7pt"> )&#160; TOTAL SENIOR PERSONNEL (1-6)</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:value-of select="format-number(sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFSeniorPersonnel/CalendarMonthsFunded ), ''#0.#'')" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:value-of select="format-number(sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFSeniorPersonnel/AcademicMonthsFunded ), ''#0.#'')" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="center" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                 ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '       <fo:block>
                                            <xsl:value-of select="format-number(sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFSeniorPersonnel/SummerMonthsFunded ), ''#0.#'')" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>$<xsl:value-of select="format-number(sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFSeniorPersonnel/FundsRequested  ), ''#,###,###,##0.00'')" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="6pt" number-columns-spanned="2" text-align="left" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">B. OTHER PERSONNEL (SHOW NUMBERS IN BRACKETS)</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="gray" number-columns-spanned="5" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="6pt" number-columns-spanned="2" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">1. ( </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="NSFOtherPersonnel">
                                                                    <xsl:for-each select="PostDocCount">
                                                                        <xsl:apply-templates />
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                 ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '   </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                            <fo:inline font-size="7pt"> )&#160; POSTDOCTORAL</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell number-columns-spanned="2" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="NSFOtherPersonnel">
                                                                    <xsl:for-each select="PostDocFunds">$<xsl:value-of select="format-number(., ''#,###,###,##0.00'')" />
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="6pt" number-columns-spanned="2" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">2. ( </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                     ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '                       <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="NSFOtherPersonnel">
                                                                    <xsl:for-each select="OtherProfCount">
                                                                        <xsl:apply-templates />
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                            <fo:inline font-size="7pt"> )&#160;&#160; OTHER PROFESSIONS (TECHNICIAN, PROGRAMMER, ETC.)</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="left" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell number-columns-spanned="2" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="NSFOtherPersonnel">
                                                                    <xsl:for-each select="OtherProfFunds">$<xsl:value-of select="format-number(., ''#,###,###,##0.00'')" />
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:ta';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := 'ble-cell padding-start="6pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">3. ( </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="NSFOtherPersonnel">
                                                                    <xsl:for-each select="GradCount">
                                                                        <xsl:apply-templates />
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                            <fo:inline font-size="7pt"> ) GRADUATE STUDENTS</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="NSFOtherPersonnel">
                                                                    <xsl:for-each select="GradFunds">$<xsl:value-of select="format-number(., ''#,###,###,##0.00'')" />
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-st';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := 'art="6pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">4. ( </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="NSFOtherPersonnel">
                                                                    <xsl:for-each select="UnderGradCount">
                                                                        <xsl:apply-templates />
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                            <fo:inline font-size="7pt"> ) UNDERGRADUATE STUDENTS</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="NSFOtherPersonnel">
                                                                    <xsl:for-each select="UnderGradFunds">$<xsl:value-of select="format-number(., ''#,###,###,##0.00'')" />
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start=';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '"6pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">5. ( </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="NSFOtherPersonnel">
                                                                    <xsl:for-each select="ClericalCount">
                                                                        <xsl:apply-templates />
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                            <fo:inline font-size="7pt"> ) SECRETARIAL - CLERICAL (IF CHARGED DIRECTLY)</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="NSFOtherPersonnel">
                                                                    <xsl:for-each select="ClericalFunds">$<xsl:value-of select="format-number(., ''#,###,###,##0.00'')" />
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cel';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := 'l padding-start="6pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">6. ( </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="NSFOtherPersonnel">
                                                                    <xsl:for-each select="OtherCount">
                                                                        <xsl:apply-templates />
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                            <fo:inline font-size="7pt"> ) OTHER&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; (PERSO</fo:inline>
                                            <fo:inline font-size="7pt">NNEL LAB ALLOCATION:&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="NSFOtherPersonnel">
                                                                    <xsl:for-each select="OtherLAFunds">$<xsl:value-of select="format-number(., ''#,###,###,##0.00'')" />
                                                                    </xsl:for-each>
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>&#160;<fo:inline font-size="7pt"> )</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>$<xsl:value-of select="format-number(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/OtherFunds +  n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/OtherLAFunds, ''#,###,###,##0.00'')" />
                             ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '           </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="6pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">TOTAL SALARIES AND WAGES (A + B)</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>$<xsl:value-of select="format-number(sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFSeniorPersonnel/FundsRequested  ) +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/OtherFunds +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/OtherLAFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/PostDocFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/OtherProfFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/GradFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/UnderGradFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/ClericalFunds, ''##,###,###,##0.00'')" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="6pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">C. FRINGE BEN</fo:inline>
                                            <fo:inline font-size="7pt">EFITS (IF </fo:inline>
                                            <fo:inline font-size="7pt">CHARGED AS DIRECT COSTS)</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := ' select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="SalarySubtotals">
                                                                <xsl:for-each select="FringeBenefits">$<xsl:value-of select="format-number(., ''#,###,###,##0.00'')" />
                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="6pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">TOTAL</fo:inline>
                                            <fo:inline font-size="7pt"> SALARIES,</fo:inline>
                                            <fo:inline font-size="7pt"> WAGES, AND FRING</fo:inline>
                                            <fo:inline font-size="7pt">E BENEFITS (A+B+C)</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>$<xsl:value-of select="format-number(sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFSeniorPersonnel/FundsRequested  ) +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/OtherFunds +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/OtherLAFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/PostDocFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/OtherProfFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/GradFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/UnderGradFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/ClericalFunds +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/SalarySubtotals/FringeBenefits, ''##,###,###,##0.00'')" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-a';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := 'lign="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell border-after-style="none" padding-start="6pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                <fo:block>
                                                    <fo:inline font-size="7pt">D. EQUIPMENT (LIST ITEM AND DOLLAR AMOUN</fo:inline>
                                                    <fo:inline font-size="7pt">T FOR EACH ITEM EXCEEDING $5000.)&#160;&#160; </fo:inline>
                                                    <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                        <fo:inline font-size="7pt">
                                                            <xsl:for-each select="BudgetSummary">
                                                                <xsl:for-each select="BudgetPeriod">
                                                                    <xsl:if test="BudgetPeriodID=4">
                                                                        <xsl:for-each select="n3:EquipmentCosts">
																			<xsl:for-each select="Description">																			
																		        <xsl:apply-templates />
                                                                            </xsl:for-each>
																			<xsl:for-each select="Cost">
																				<fo:inline>&#160;$<xsl:value-of select="format-number(., ''#,###,###,##0.00'')"/>;&#160;&#160;</fo:inline>
																			</xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:if>
                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                        </fo:inline>
                                                    </xsl:for-each>
                                                </fo:block>
                                            </fo:block>
                                            <fo:inline font-size="7pt"></fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="gray" number-columns-spanned="2" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell border-before-style="none" padding-start="6pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">TO</fo:inline>
                                            <fo:inline font-size="7pt">TAL EQ</fo:inline>
                                            <fo:inline';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := ' font-size="7pt">UIPMENT</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="EquipmentTotal">$<xsl:value-of select="format-number(., ''#,###,###,##0.00'')" />
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell font-size="7pt" padding-start="6pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">E. TRAVEL&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; 1. DOMESTIC (INCL. CANADA, MEXICO AND U.S. POSSESSIONS)&#160;&#160; </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="n3:TravelCosts">
                                                                <xsl:if test="Type =&apos;Domestic&apos;">
                                                                    <xsl:for-each select="Description">
                                                                        <xsl:apply-templates />
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
          ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '                          <fo:table-cell text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="n3:TravelCosts">
                                                                    <xsl:if test="Type=&apos;Domestic&apos;">
                                                                        <xsl:for-each select="Cost">$<xsl:value-of select="format-number(., ''#,###,###,##0.00'')" />
                                                                        </xsl:for-each>
                                                                    </xsl:if>
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell font-size="7pt" padding-start=".9in" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">2. FOREIGN&#160;&#160;&#160; </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="n3:TravelCosts">
                                                                <xsl:if test="Type =&apos;Foreign&apos;">
                                                                    <xsl:for-each select="Description">
                                                                        <xsl:apply-templates />
                                                                    </xsl:for-each>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="n3:TravelCosts">
                                                                    <xsl:if test="Type=&apos;Foreign&apos;">
                                                                        <xsl:for-each select="Cost">$<xsl:value-of select="format-number(., ''#,###,###,##0.00'')" />
                                                                        </xsl:for-each>
                                                                    </xsl:if>
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell font-size="7pt" padding-start="6pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">&#160; PARITICIPANT SUPPORT&#160; </fo:inline>
                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                <fo:block font-size="7pt">
                                                    <fo:inline font-size="7pt">&#160;&#160;&#160;&#160; 1. STIPENDS&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; $</fo:inline>
                                                    <xsl:value-of select="format-number(sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:ParticipantPatientCosts[Type=&apos;Stipends&apos;]/Cost ), ''#,###,###,##0.00'')" />
                                                </fo:block>
                                            </fo:block>
                                            <fo:inline font-size="7pt">&#160;&#160;&#160;&#160; 2. TRAVEL&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; $</fo:inline>
                                            <xsl:value-of select="format-number(sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:ParticipantPat';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := 'ientCosts[Type=&apos;Travel&apos;]/Cost ), ''#,###,###,##0.00'')" />
                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">

                                                <fo:block line-height="6pt">
                                                    <fo:inline font-size="7pt">&#160;&#160;&#160;&#160; 3. SUBSISTENCE&#160;&#160;&#160;&#160;&#160; $</fo:inline>
                                                    <xsl:value-of select="format-number(sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:ParticipantPatientCosts[Type=&apos;Subsistence&apos;]/Cost ), ''#,###,###,##0.00'')" />
                                                </fo:block>
                                            </fo:block>
                                            <fo:inline font-size="7pt">&#160;&#160;&#160;&#160; 4. OTHER&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; $</fo:inline>
                                            <xsl:value-of select="format-number(sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:ParticipantPatientCosts[Type=&apos;Other&apos;]/Cost ), ''#,###,###,##0.00'')" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="gray" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell background-color="gray" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="6pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">TOTAL NUMBER OF PARTICIPANTS(&#160;&#160; )&#160;&#160;&#160; TOTAL PARTICIPANT COSTS</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="BudgetSummary">
                                                    <xsl:for-each select="BudgetPeriod">
                                                        <xsl:if test="BudgetPeriodID=4">
                                                            <xsl:for-each select="ParticipantPatientTotal">$<xsl:value-of select="format-number(., ''#,###,###,##0.00'')" />
                                                            </xsl:for-each>
                                                        </xsl:if>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                     ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '       </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="6pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">G. OTHER DIRECT COSTS</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="gray" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell background-color="gray" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell font-size="7pt" padding-start="9pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">1. MATERIALS AND SUPPLIES</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>$<xsl:value-of select="format-number(sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost ), ''#,###,###,##0.00'')" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="9pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inli';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := 'ne font-size="7pt">2. PUBLICATION/DOCUMENTATION/DISSEMINATION</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>$<xsl:value-of select="format-number(sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost ), ''#,###,###,##0.00'')" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="9pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">3. CONSULTANT SERVICES</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>$<xsl:value-of select="format-number(sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost ), ''#,###,###,##0.00'')" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="9pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">4. COMPUTER SERVICES</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>$<xsl:value-of select="format-number(sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost ), ''#,###,###,##0.00'')" />
                                        </fo:block>
           ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '                         </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="9pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">5. SUBAWARDS</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>$<xsl:value-of select="format-number(sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost ), ''#,###,###,##0.00'')" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="9pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">6. OTHER&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; (NON-PERSONNEL LAB ALLOCATION:&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="n3:OtherDirectCosts">
                                                                    <xsl:if test="starts-with( Description, &apos;LA&apos; )">
                                                                        <xsl:for-each select="Cost">$<xsl:value-of select="format-number(., ''#,###,###,##0.00'')" />
                                                                        </xsl:for-each>
                                                                    </xsl:if>
                                                                </xsl:for-each>
                                                            </xsl:i';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := 'f>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                            <fo:inline font-size="7pt"> )</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>$<xsl:value-of select="format-number(sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost), ''#,###,###,##0.00'')" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="9pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">TOTAL O</fo:inline>
                                            <fo:inline font-size="7pt">T</fo:inline>
                                            <fo:inline font-size="7pt">HER DIRECT COSTS</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>$<xsl:value-of select="format-number(sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost), ''#,###,###,##0.00'')" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
   ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '                             <fo:table-row>
                                    <fo:table-cell padding-start="6pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">H. T</fo:inline>
                                            <fo:inline font-size="7pt">OTAL DI</fo:inline>
                                            <fo:inline font-size="7pt">RE</fo:inline>
                                            <fo:inline font-size="7pt">CT </fo:inline>
                                            <fo:inline font-size="7pt">COSTS (A THROUGH G)</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>$<xsl:value-of select="format-number(sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFSeniorPersonnel/FundsRequested  ) +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/OtherFunds +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/OtherLAFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/PostDocFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/OtherProfFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/GradFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/UnderGradFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/ClericalFunds +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/SalarySubtotals/FringeBenefits +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/EquipmentTotal +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/TravelTotal +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/ParticipantPatientTotal +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost ) + sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost), ''##,###,###,##0.00'')" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '
                                    <fo:table-cell border-after-style="none" padding-start="6pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">I. IND</fo:inline>
                                            <fo:inline font-size="7pt">IRECT COSTS (F &amp; A) (SPECIFY RATE AND BASE)</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="gray" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell background-color="gray" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell border-before-style="none" padding-start="6pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">T</fo:inline>
                                            <fo:inline font-size="7pt">OTAL INDIR</fo:inline>
                                            <fo:inline font-size="7pt">ECT CO</fo:inline>
                                            <fo:inline font-size="7pt">STS (F</fo:inline>
                                            <fo:inline font-size="7pt"> &amp; A)</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="IndirectCostsTotal">$<xsl:value-of select="format-number(., ''#,###,###,##0.00'')" />
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-en';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := 'd="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="6pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">J. TOTAL DIRECT AN</fo:inline>
                                            <fo:inline font-size="7pt">D INDIRECT COSTS (H + I)</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>$<xsl:value-of select="format-number(sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFSeniorPersonnel/FundsRequested  ) +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/OtherFunds +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/OtherLAFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/PostDocFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/OtherProfFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/GradFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/UnderGradFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/ClericalFunds +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/SalarySubtotals/FringeBenefits +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/EquipmentTotal +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/TravelTotal +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/ParticipantPatientTotal +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost ) +
 sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost ) +
 sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost ) +
 sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost ) +
 sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost ) +
 sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost) +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/IndirectCostsTotal, ''##,###,###,##0.00'')" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                               ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '         <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="6pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">K. RE</fo:inline>
                                            <fo:inline font-size="7pt">SIDUAL FUNDS (IF FOR FURTHER SUPPORT OF CURRENT PROJECT SEE GPG II.D.7.j.)</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="6pt" number-columns-spanned="5" width=".5in" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">L. AMOUNT OF THIS REQUEST (J MINUS K)</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell font-size="7pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>$<xsl:value-of select="format-number(sum( n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFSeniorPersonnel/FundsRequested  ) +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/OtherFunds +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/OtherLAFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/PostDocFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/OtherProfFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/GradFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/UnderGradFunds +
n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/NSFOtherPersonnel/ClericalFunds +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/SalarySubtotals/FringeBenefits +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/EquipmentTotal +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/TravelTotal +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/ParticipantPatientTotal +
sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Materials and Supplies&apos;]/Cost ) +
 sum(n1:Res';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := 'earchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Publication Costs&apos;]/Cost ) +
 sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Consultant Costs&apos;]/Cost ) +
 sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Computer Services&apos;]/Cost ) +
 sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Subcontract&apos;]/Cost ) +
 sum(n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/n3:OtherDirectCosts[Type=&apos;Other Direct Costs&apos;]/Cost) +
 n1:ResearchAndRelatedProject/BudgetSummary/BudgetPeriod[BudgetPeriodID=4]/IndirectCostsTotal, ''##,###,###,##0.00'')" />
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell padding-start="6pt" display-align="before" width="4in" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">M. COST SHARING: PROPOSED LEVEL&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="BudgetSummary">
                                                        <xsl:for-each select="BudgetPeriod">
                                                            <xsl:if test="BudgetPeriodID=4">
                                                                <xsl:for-each select="NSFCostSharingAmount">$<fo:inline font-size="7pt" />
                                                                    <xsl:value-of select="format-number(., ''#,###,###,##0.00'')" />
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                            <fo:inline font-size="7pt">&#160; </fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell number-columns-spanned="6" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">AGREED LEVEL IF DIFFERENT: $</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell number-rows-spanned="2" width="4in" padding-start="3pt" padding-end="3pt" padding-bef';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := 'ore="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>

                                            <fo:inline font-size="7pt">PI/PD TYPED NAME AND SIGNATURE*&#160; </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="n1:ResearchCoverPage">
                                                    <xsl:for-each select="n1:ProgramDirectorPrincipalInvestigator">
                                                        <xsl:for-each select="Name">
                                                            <xsl:for-each select="LastName">
                                                                <fo:inline font-size="7pt">
                                                                    <xsl:apply-templates />
                                                                </fo:inline>
                                                            </xsl:for-each>
                                                            <fo:inline font-size="7pt">,&#160; </fo:inline>
                                                            <xsl:for-each select="FirstName">
                                                                <fo:inline font-size="7pt">
                                                                    <xsl:apply-templates />
                                                                </fo:inline>
                                                            </xsl:for-each>
                                                            <fo:inline font-size="7pt">&#160;</fo:inline>
                                                            <xsl:for-each select="MiddleName">
                                                                <fo:inline font-size="7pt">
                                                                    <xsl:apply-templates />
                                                                </fo:inline>
                                                            </xsl:for-each>
                                                            <fo:inline font-size="7pt">&#160;</fo:inline>
                                                            <xsl:for-each select="NameSuffix">
                                                                <fo:inline font-size="7pt">
                                                                    <xsl:apply-templates />
                                                                </fo:inline>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                        <fo:inline font-size="7pt">&#160;&#160;&#160; </fo:inline>
                                                        <xsl:for-each select="DirectorInvestigatorSignature">
                                                            <xsl:if test="SignatureAuthentication  != unknown">
                                                                <xsl:for-each select="SignatureAuthentication">
                                                                    <fo:inline font-size="7pt">
                                                                        <xsl:apply-templates />
                                                                    </fo:inline>
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
   ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '                                         <fo:inline font-size="7pt">&#160;&#160;&#160;&#160; </fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell number-columns-spanned="2" number-rows-spanned="2" text-align="left" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">DATE&#160;&#160; </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="n1:ResearchCoverPage">
                                                        <xsl:for-each select="n1:ProgramDirectorPrincipalInvestigator">
                                                            <xsl:for-each select="DirectorInvestigatorSignature">
                                                                <xsl:for-each select="SignatureDate">
                                                                    <fo:inline font-size="7pt" />
                                                                    <xsl:value-of select="format-number(substring(., 6, 2), ''00'')" />
                                                                    <xsl:text>/</xsl:text>
                                                                    <xsl:value-of select="format-number(substring(., 9, 2), ''00'')" />
                                                                    <xsl:text>/</xsl:text>
                                                                    <xsl:value-of select="format-number(substring(., 1, 4), ''0000'')" />
                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                            <fo:inline font-size="7pt"></fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="gray" number-columns-spanned="4" text-align="center" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">FOR SPONSOR&#160; USE ONLY</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell background-color="gray" number-columns-spanned="4" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">INDIRECT COST RATE VERIFICATION</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row>
                                    <fo:table-cell display-align="before" w';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := 'idth="4in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">OR</fo:inline>
                                            <fo:inline font-size="7pt">G. REP.</fo:inline>
                                            <fo:inline font-size="7pt"> TY</fo:inline>
                                            <fo:inline font-size="7pt">PED NAME AND </fo:inline>
                                            <fo:inline font-size="7pt">SIGNATURE*&#160; </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <xsl:for-each select="n1:ResearchCoverPage">
                                                    <xsl:for-each select="AuthorizedOrganizationalRepresentative">
                                                        <xsl:for-each select="Name">
                                                            <xsl:for-each select="LastName">
                                                                <fo:inline font-size="7pt">
                                                                    <xsl:apply-templates />
                                                                </fo:inline>
                                                            </xsl:for-each>
                                                            <fo:inline font-size="7pt">, </fo:inline>
                                                            <xsl:for-each select="FirstName">
                                                                <fo:inline font-size="7pt">
                                                                    <xsl:apply-templates />
                                                                </fo:inline>
                                                            </xsl:for-each>
                                                            <fo:inline font-size="7pt">&#160;</fo:inline>
                                                            <xsl:for-each select="MiddleName">
                                                                <fo:inline font-size="7pt">
                                                                    <xsl:apply-templates />
                                                                </fo:inline>
                                                            </xsl:for-each>
                                                            <fo:inline font-size="7pt">&#160;</fo:inline>
                                                            <xsl:for-each select="NameSuffix">
                                                                <fo:inline font-size="7pt">
                                                                    <xsl:apply-templates />
                                                                </fo:inline>
                                                            </xsl:for-each>
                                                            <fo:inline font-size="7pt">&#160;</fo:inline>
                                                            <fo:block>
                                                                <fo:leader leader-pattern="space" />
                                                            </fo:block>
                                                        </xsl:for-each>
                                                        <fo:inline font-size="7pt">&#160;&#160;&#160;&#160; </fo:inline>
                                                        <xsl:for-each select="OrganizationalOfficialSignature">
                                                            <xsl:if test="SignatureAuthentication !=unknown">
                                                                <xsl:for-each se';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := 'lect="SignatureAuthentication">
                                                                    <fo:inline font-size="7pt">
                                                                        <xsl:apply-templates />
                                                                    </fo:inline>
                                                                </xsl:for-each>
                                                            </xsl:if>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell number-columns-spanned="2" text-align="left" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">DATE&#160;&#160; </fo:inline>
                                            <xsl:for-each select="n1:ResearchAndRelatedProject">
                                                <fo:inline font-size="7pt">
                                                    <xsl:for-each select="n1:ResearchCoverPage">
                                                        <xsl:for-each select="AuthorizedOrganizationalRepresentative">
                                                            <xsl:for-each select="OrganizationalOfficialSignature">
                                                                <xsl:for-each select="SignatureDate">
                                                                    <fo:inline font-size="7pt" />
                                                                    <xsl:value-of select="format-number(substring(., 6, 2), ''00'')" />
                                                                    <xsl:text>/</xsl:text>
                                                                    <xsl:value-of select="format-number(substring(., 9, 2), ''00'')" />
                                                                    <xsl:text>/</xsl:text>
                                                                    <xsl:value-of select="format-number(substring(., 1, 4), ''0000'')" />
                                                                </xsl:for-each>
                                                            </xsl:for-each>
                                                        </xsl:for-each>
                                                    </xsl:for-each>
                                                </fo:inline>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="gray" number-columns-spanned="2" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">Date Checked</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="gray" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                   ';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := '                         <fo:inline font-size="7pt">Date </fo:inline>
                                            <fo:inline font-size="7pt">of Rate Sheet</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="gray" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block>
                                            <fo:inline font-size="7pt">Initials</fo:inline>
                                            <fo:inline font-size="7pt">-ORG</fo:inline>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <fo:table-row height=".05in">
                                    <fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" height="1pt" width="4in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" height="1pt" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" height="1pt" text-align="left" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" height="1pt" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" height="1pt" width=".5in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" height="1pt" text-align="right" width="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                    <fo:table-cell border-after-style="none" border-before-style="none" border-end-style="none" border-start-style="none" height="1pt" widt';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
DECLARE    data CLOB; buffer VARCHAR2(30000);
BEGIN
SELECT FORM_TEMPLATE INTO data FROM SPONSOR_FORM_TEMPLATES
WHERE
SPONSOR_FORM_ID =(SELECT SPONSOR_FORM_ID FROM SPONSOR_FORMS WHERE SPONSOR_CODE='009800' and PACKAGE_NUMBER=2)  AND PAGE_NUMBER = 5 FOR UPDATE;
buffer := 'h="1in" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                        <fo:block />
                                    </fo:table-cell>
                                </fo:table-row>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>';
DBMS_LOB.writeappend(data,LENGTH(buffer),buffer);
END;
/
commit
/