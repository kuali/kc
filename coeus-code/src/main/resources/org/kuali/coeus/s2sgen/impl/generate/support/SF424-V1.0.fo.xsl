<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xmlns:footer="http://apply.grants.gov/system/Footer-V1.0"
xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:SF424="http://apply.grants.gov/forms/SF424-V1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <xsl:variable name="fo:layout-master-set">
        <fo:layout-master-set>
            <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.5in" margin-right="0.5in">
                <fo:region-body margin-top="0.3in" margin-bottom="0.4in" font-family="Helvetica,Times,Courier" font-size="14pt" />
                <fo:region-after extent=".4in"/>
            </fo:simple-page-master>
        </fo:layout-master-set>
    </xsl:variable>
    <xsl:template match="SF424:GrantApplication">
        <fo:root>
            <xsl:copy-of select="$fo:layout-master-set" />
            <fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
            
            <fo:static-content flow-name="xsl-region-after">
                <fo:block>
																						<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
																						</fo:inline>
																					</fo:block>
                </fo:static-content>

            
            
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="8pt">
                    <fo:table text-align="right" width="100%">
                    <fo:table-column column-width="proportional-column-width(100)" />
																					<fo:table-body>
																						<fo:table-row>
																							<fo:table-cell>
																								<fo:block font-size="6pt" text-align="right">Version 9/03</fo:block>
																							</fo:table-cell>
																						</fo:table-row>
																					</fo:table-body>
																				</fo:table>
                            <fo:table padding="0" text-align="center" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                <fo:table-column column-width="proportional-column-width(25)" />
                                <fo:table-column column-width="proportional-column-width(40)" />
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" text-align="left" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block>
                                                <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:block font-size="7pt" padding-after="1pt" padding-before="1pt" padding-end="0pt" padding-start="0pt">
                                                        <fo:inline color="black" font-size="12pt" font-weight="bold">APPLICATION FOR </fo:inline>
                                                    </fo:block>
                                                </fo:block>
                                                <fo:inline color="black" font-size="12pt" font-weight="bold">FEDERAL ASSISTANCE</fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                      
                                        <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="0pt" display-align="after" text-align="left" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column column-width="proportional-column-width(100)" />
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="1pt" number-columns-spanned="3" width="100%" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="proportional-column-width(50)" />
                                                                        <fo:table-column column-width="proportional-column-width(50)" />
                                                                        <fo:table-column />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="after" number-columns-spanned="3" width="50%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt" font-weight="bold">&#160; 1. TYPE OF SUBMISSION:</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="50%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">&#160;&#160; Application</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="50%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">&#160;&#160; Pre-application</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" height="0pt" width="50%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>&#160;&#160;&#160;&#160;&#160; <xsl:for-each select="SF424:SubmissionTypeCode">
                                                                                            <fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
                                                                                                <fo:inline>
                                                                                                    <xsl:choose>
                                                                                                        <xsl:when test=".='AC'">
                                                                                                            <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                                        </xsl:when>
                                                                                                        <xsl:when test=".=''">
                                                                                                            <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                                        </xsl:when>
                                                                                                        <xsl:otherwise>
                                                                                                            <fo:inline text-decoration="underline" color="black">
                                                                                                                <fo:leader leader-length="8pt" leader-pattern="rule" />
                                                                                                            </fo:inline>
                                                                                                        </xsl:otherwise>
                                                                                                    </xsl:choose>
                                                                                                </fo:inline>
                                                                                            </fo:inline>
                                                                                        </xsl:for-each>
                                                                                        <fo:inline font-size="8pt">&#160;</fo:inline>
                                                                                        <fo:inline font-size="8pt" font-weight="bold">Construction</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="50%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>&#160;&#160;&#160;&#160;&#160;&#160; <xsl:for-each select="SF424:SubmissionTypeCode">
                                                                                            <fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
                                                                                                <fo:inline>
                                                                                                    <xsl:choose>
                                                                                                        <xsl:when test=".='PC'">
                                                                                                            <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                                        </xsl:when>
                                                                                                        <xsl:when test=".=''">
                                                                                                            <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                                        </xsl:when>
                                                                                                        <xsl:otherwise>
                                                                                                            <fo:inline text-decoration="underline" color="black">
                                                                                                                <fo:leader leader-length="8pt" leader-pattern="rule" />
                                                                                                            </fo:inline>
                                                                                                        </xsl:otherwise>
                                                                                                    </xsl:choose>
                                                                                                </fo:inline>
                                                                                            </fo:inline>
                                                                                        </xsl:for-each>
                                                                                        <fo:inline font-size="8pt">&#160;</fo:inline>
                                                                                        <fo:inline font-size="8pt" font-weight="bold">Construction</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" height="0pt" width="50%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>&#160;&#160;&#160;&#160;&#160; <xsl:for-each select="SF424:SubmissionTypeCode">
                                                                                            <fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
                                                                                                <fo:inline>
                                                                                                    <xsl:choose>
                                                                                                        <xsl:when test=".='AN'">
                                                                                                            <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                                        </xsl:when>
                                                                                                        <xsl:when test=".=''">
                                                                                                            <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                                        </xsl:when>
                                                                                                        <xsl:otherwise>
                                                                                                            <fo:inline text-decoration="underline" color="black">
                                                                                                                <fo:leader leader-length="8pt" leader-pattern="rule" />
                                                                                                            </fo:inline>
                                                                                                        </xsl:otherwise>
                                                                                                    </xsl:choose>
                                                                                                </fo:inline>
                                                                                            </fo:inline>
                                                                                        </xsl:for-each>
                                                                                        <fo:inline font-size="8pt">&#160;</fo:inline>
                                                                                        <fo:inline font-size="8pt" font-weight="bold">Non-Construction</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="50%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>&#160;&#160;&#160;&#160;&#160;&#160; <xsl:for-each select="SF424:SubmissionTypeCode">
                                                                                            <fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
                                                                                                <fo:inline>
                                                                                                    <xsl:choose>
                                                                                                        <xsl:when test=".='PN'">
                                                                                                            <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                                        </xsl:when>
                                                                                                        <xsl:when test=".=''">
                                                                                                            <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                                        </xsl:when>
                                                                                                        <xsl:otherwise>
                                                                                                            <fo:inline text-decoration="underline" color="black">
                                                                                                                <fo:leader leader-length="8pt" leader-pattern="rule" />
                                                                                                            </fo:inline>
                                                                                                        </xsl:otherwise>
                                                                                                    </xsl:choose>
                                                                                                </fo:inline>
                                                                                            </fo:inline>
                                                                                        </xsl:for-each>
                                                                                        <fo:inline font-size="8pt">&#160;</fo:inline>
                                                                                        <fo:inline font-size="8pt" font-weight="bold">Non-Construction</fo:inline>
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
                                            </fo:block>
                                        </fo:table-cell>
                                          <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" height="0pt" text-align="left" width="60%" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block>
                                                <fo:table padding="0" text-align="left" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column column-width="proportional-column-width(30)" />
                                                    <fo:table-column column-width="proportional-column-width(20)" />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="7pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="0pt">
                                                                            <fo:inline color="black" font-size="8pt" font-weight="bold">&#160; 2. DATE SUBMITTED</fo:inline>
                                                                        </fo:block>
                                                                    </fo:block>&#160;&#160; <xsl:for-each select="SF424:SubmittedDate">
                                                                        <fo:inline font-size="8pt" font-style="normal">
                                                                              <xsl:value-of select="format-number(substring(.,6,2), '00')"/>
									       <xsl:text>-</xsl:text>
									                 <xsl:value-of select="format-number(substring(.,9,2), '00')"/>
									       <xsl:text>-</xsl:text>
									                 <xsl:value-of select="format-number(substring(.,1,4), '0000')"/>
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" number-columns-spanned="2" width="20%" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt">
                                                                            <fo:inline font-size="8pt">&#160; Applicant Identifier</fo:inline>
                                                                        </fo:block>
                                                                    </fo:block>&#160;&#160; <xsl:for-each select="SF424:SubmittingOrganization">
                                                                        <xsl:for-each select="SF424:OrganizationIdentifyingInformation">
                                                                            <xsl:for-each select="SF424:ApplicantID">
                                                                                <fo:inline font-size="8pt" font-style="normal">
                                                                                    <xsl:apply-templates />
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt">
                                                                            <fo:inline font-size="8pt" font-weight="bold">&#160; 3. DATE RECEIVED BY STATE</fo:inline>
                                                                        </fo:block>
                                                                    </fo:block>&#160;&#160; <xsl:for-each select="SF424:StateReceivedDate">
                                                                        <fo:inline font-size="8pt" font-style="normal">
                                                                           <xsl:value-of select="format-number(substring(.,6,2), '00')"/>
									       <xsl:text>-</xsl:text>
									                 <xsl:value-of select="format-number(substring(.,9,2), '00')"/>
									       <xsl:text>-</xsl:text>
									                 <xsl:value-of select="format-number(substring(.,1,4), '0000')"/>
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" number-columns-spanned="2" width="20%" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt">
                                                                            <fo:inline font-size="8pt">&#160; State Application Identifier</fo:inline>
                                                                        </fo:block>
                                                                    </fo:block>&#160;&#160; <xsl:for-each select="SF424:StateID">
                                                                        <fo:inline font-size="8pt" font-style="normal">
                                                                            <xsl:apply-templates />
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row width="100%">
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="30%" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt">
                                                                            <fo:inline font-size="8pt" font-weight="bold">&#160; 4. DATE RECEIVED BY FEDERAL AGENCY</fo:inline>
                                                                        </fo:block>
                                                                    </fo:block>&#160;&#160; <xsl:for-each select="SF424:AgencyReceivedDate">
                                                                        <fo:inline font-size="8pt" font-style="normal">
                                                                        <xsl:value-of select="format-number(substring(.,6,2), '00')"/>
									       <xsl:text>-</xsl:text>
									                 <xsl:value-of select="format-number(substring(.,9,2), '00')"/>
									       <xsl:text>-</xsl:text>
									                 <xsl:value-of select="format-number(substring(.,1,4), '0000')"/>
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" number-columns-spanned="2" width="20%" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="7pt" padding-before="1pt">
                                                                            <fo:inline font-size="8pt">&#160; Federal Identifier</fo:inline>
                                                                        </fo:block>
                                                                    </fo:block>&#160;&#160; <xsl:for-each select="SF424:FederalID">
                                                                        <fo:inline font-size="8pt" font-style="normal" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt">
                                                                            <xsl:apply-templates />
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="0pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" top="0pt" display-align="before" number-columns-spanned="2" text-align="left" width="100%" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block>
                                                <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:table-column column-width="175pt" />
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-column />
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" number-columns-spanned="5" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:inline color="black" font-size="8pt" font-weight="bold" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt">&#160; 5. APPLICANT INFORMATION</fo:inline>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8pt" padding-after="1pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" display-align="before" number-columns-spanned="2" number-rows-spanned="2" width="175pt" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:inline color="black" font-size="8pt">&#160; * Legal Name:&#160; </fo:inline>
                                                                    <xsl:for-each select="SF424:SubmittingOrganization">
                                                                        <xsl:for-each select="SF424:OrganizationIdentifyingInformation">
                                                                            <xsl:for-each select="SF424:Organization">
                                                                                <xsl:for-each select="SF424:OrganizationName">
                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                        <xsl:apply-templates />
                                                                                    </fo:inline>
                                                                                </xsl:for-each>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8pt" padding-after="1pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" number-columns-spanned="3" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:inline color="black" font-size="8pt" font-weight="bold">&#160; Organizational Unit</fo:inline>
                                                                    <fo:inline color="black" font-size="8pt" font-weight="bold">:</fo:inline>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8pt" padding-after="1pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" number-columns-spanned="3" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:inline color="black" font-size="8pt">&#160; Department: </fo:inline>
                                                                    <xsl:for-each select="SF424:SubmittingOrganization">
                                                                        <xsl:for-each select="SF424:OrganizationIdentifyingInformation">
                                                                            <xsl:for-each select="SF424:Organization">
                                                                                <xsl:for-each select="SF424:DepartmentName">
                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                        <xsl:apply-templates />
                                                                                    </fo:inline>
                                                                                </xsl:for-each>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" display-align="before" number-columns-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:inline color="black" font-size="8pt">&#160; * Organizational DUNS: </fo:inline>
                                                                    <xsl:for-each select="SF424:SubmittingOrganization">
                                                                        <xsl:for-each select="SF424:OrganizationIdentifyingInformation">
                                                                            <xsl:for-each select="SF424:Organization">
                                                                                <xsl:for-each select="SF424:DUNSID">
                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                        <xsl:apply-templates />
                                                                                    </fo:inline>
                                                                                </xsl:for-each>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8pt" padding-after="1pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" number-columns-spanned="3" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:inline color="black" font-size="8pt">&#160; Division: </fo:inline>
                                                                    <xsl:for-each select="SF424:SubmittingOrganization">
                                                                        <xsl:for-each select="SF424:OrganizationIdentifyingInformation">
                                                                            <xsl:for-each select="SF424:Organization">
                                                                                <xsl:for-each select="SF424:DivisionName">
                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                        <xsl:apply-templates />
                                                                                    </fo:inline>
                                                                                </xsl:for-each>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" display-align="after" number-columns-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:inline color="black" font-size="8pt" font-weight="bold">&#160; Address</fo:inline>
                                                                    <fo:inline color="black" font-size="8pt" font-weight="bold">:</fo:inline>
                                                                    <fo:inline color="black" font-size="8pt" font-weight="bold"></fo:inline>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" display-align="before" number-columns-spanned="3" number-rows-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:inline color="black" font-size="8pt" font-weight="bold">&#160; Name and telephone number of person to be contacted on matters involving this application (give area code)</fo:inline>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                      
                                                        <fo:table-row>
                                                          
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" display-align="before" number-columns-spanned="2" number-rows-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:inline color="black" font-size="8pt">&#160;&#160;* Street 1: </fo:inline>
                                                                    <xsl:for-each select="SF424:SubmittingOrganization">
                                                                        <xsl:for-each select="SF424:Address">
                                                                            <xsl:for-each select="SF424:Street1">
                                                                                <fo:inline font-size="8pt" font-style="normal">
                                                                                    <xsl:apply-templates />
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                                <fo:block>
                                                                    <fo:inline color="black" font-size="8pt">&#160;&#160;Street 2: </fo:inline>
                                                                    <xsl:for-each select="SF424:SubmittingOrganization">
                                                                        <xsl:for-each select="SF424:Address">
                                                                            <xsl:for-each select="SF424:Street2">
                                                                                <fo:inline font-size="8pt" font-style="normal">
                                                                                    <xsl:apply-templates />
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                                 
                                                     </fo:table-row>
                                                   
                                                      
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" number-columns-spanned="3" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="proportional-column-width(25)" />
                                                                        <fo:table-column column-width="proportional-column-width(75)" />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="1pt" display-align="before" width="25%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline color="black" font-size="8pt">&#160; Prefix: </fo:inline>
                                                                                        <xsl:for-each select="SF424:Individual">
                                                                                            <xsl:for-each select="SF424:Contact">
                                                                                                <xsl:for-each select="SF424:NamePrefix">
                                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                                        <xsl:apply-templates />
                                                                                                    </fo:inline>
                                                                                                </xsl:for-each>
                                                                                            </xsl:for-each>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-start-color="black" font-size="8pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="1pt" display-align="before" number-rows-spanned="2" width="75%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline color="black" font-size="8pt">&#160; * First Name: </fo:inline>
                                                                                        <xsl:for-each select="SF424:Individual">
                                                                                            <xsl:for-each select="SF424:Contact">
                                                                                                <xsl:for-each select="SF424:GivenName1">
                                                                                                    <fo:inline font-size="8pt" font-style="normal">
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
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" display-align="before" number-columns-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:inline color="black" font-size="8pt">&#160; * City: </fo:inline>
                                                                    <xsl:for-each select="SF424:SubmittingOrganization">
                                                                        <xsl:for-each select="SF424:Address">
                                                                            <xsl:for-each select="SF424:City">
                                                                                <fo:inline font-size="8pt" font-style="normal">
                                                                                    <xsl:apply-templates />
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" display-align="before" number-columns-spanned="3" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:inline color="black" font-size="8pt">&#160; Middle Name: </fo:inline>
                                                                    <xsl:for-each select="SF424:Individual">
                                                                        <xsl:for-each select="SF424:Contact">
                                                                            <xsl:for-each select="SF424:GivenName2">
                                                                                <fo:inline font-size="8pt" font-style="normal">
                                                                                    <xsl:apply-templates />
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" display-align="before" number-columns-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:inline color="black" font-size="8pt">&#160; County: </fo:inline>
                                                                    <xsl:for-each select="SF424:SubmittingOrganization">
                                                                        <xsl:for-each select="SF424:Address">
                                                                            <xsl:for-each select="SF424:County">
                                                                                <fo:inline font-size="8pt" font-style="normal">
                                                                                    <xsl:apply-templates />
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" display-align="before" number-columns-spanned="3" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:inline color="black" font-size="8pt">&#160; * Last Name: </fo:inline>
                                                                    <xsl:for-each select="SF424:Individual">
                                                                        <xsl:for-each select="SF424:Contact">
                                                                            <xsl:for-each select="SF424:FamilyName">
                                                                                <fo:inline font-size="8pt" font-style="normal">
                                                                                    <xsl:apply-templates />
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="1pt" display-align="before" number-columns-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="proportional-column-width(40)" />
                                                                        <fo:table-column column-width="proportional-column-width(60)" />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="0pt" display-align="before" width="40%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline color="black" font-size="8pt">&#160; * State: </fo:inline>
                                                                                        <xsl:for-each select="SF424:SubmittingOrganization">
                                                                                            <xsl:for-each select="SF424:Address">
                                                                                                <xsl:for-each select="SF424:StateCode">
                                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                                        <xsl:apply-templates />
                                                                                                    </fo:inline>
                                                                                                </xsl:for-each>
                                                                                            </xsl:for-each>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-start-color="black" font-size="8pt" padding-after="0pt" padding-before="0pt" padding-end="0pt" padding-start="1pt" display-align="before" number-rows-spanned="2" width="60%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline color="black" font-size="8pt">&#160; * Zip Code: </fo:inline>
                                                                                        <xsl:for-each select="SF424:SubmittingOrganization">
                                                                                            <xsl:for-each select="SF424:Address">
                                                                                                <xsl:for-each select="SF424:ZipCode">
                                                                                                    <fo:inline font-size="8pt" font-style="normal">
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
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" display-align="before" number-columns-spanned="3" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:inline color="black" font-size="8pt">&#160; Suffix: </fo:inline>
                                                                    <xsl:for-each select="SF424:Individual">
                                                                        <xsl:for-each select="SF424:Contact">
                                                                            <xsl:for-each select="SF424:NameSuffix">
                                                                                <fo:inline font-size="8pt" font-style="normal">
                                                                                    <xsl:apply-templates />
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" display-align="before" number-columns-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:inline color="black" font-size="8pt">&#160; * Country: </fo:inline>
                                                                    <xsl:for-each select="SF424:SubmittingOrganization">
                                                                        <xsl:for-each select="SF424:Address">
                                                                            <xsl:for-each select="SF424:Country">
                                                                                <fo:inline font-size="8pt" font-style="normal">
                                                                                    <xsl:apply-templates />
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell hyphenate="true" font-size="8pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" display-align="before" number-columns-spanned="3" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:inline color="black" font-size="8pt">&#160; Email: </fo:inline>
                                                                    <xsl:for-each select="SF424:Individual">
                                                                        <xsl:for-each select="SF424:Contact">
                                                                            <xsl:for-each select="SF424:ElectronicMailAddress">
                                                                                <fo:inline font-size="8pt" font-style="normal">
                                                                                    <xsl:apply-templates />
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" number-columns-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="8pt" padding-before="1pt">
                                                                            <fo:inline font-size="8pt" font-weight="bold">&#160; 6. * EMPLOYER IDENTIFICATION NUMBER </fo:inline>
                                                                            <fo:inline font-size="8pt">(</fo:inline>
                                                                            <fo:inline font-size="8pt" font-style="italic">EIN</fo:inline>
                                                                            <fo:inline font-size="8pt">)</fo:inline>
                                                                            <fo:inline font-size="8pt" font-weight="bold">:</fo:inline>
                                                                        </fo:block>
                                                                    </fo:block>&#160;&#160;&#160;&#160;&#160;&#160; <xsl:for-each select="SF424:SubmittingOrganization">
                                                                        <xsl:for-each select="SF424:OrganizationIdentifyingInformation">
                                                                            <xsl:for-each select="SF424:Organization">
                                                                                <xsl:for-each select="SF424:EmployerID">
                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                        <xsl:apply-templates />
                                                                                    </fo:inline>
                                                                                </xsl:for-each>
                                                                            </xsl:for-each>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="0pt" padding-end="1pt" padding-start="1pt" display-align="before" number-columns-spanned="3" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="proportional-column-width(50)" />
                                                                        <fo:table-column column-width="proportional-column-width(50)" />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="8pt" padding-before="0pt" display-align="before" width="50%" padding-start="3pt" padding-end="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                            <fo:block font-size="8pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt">
                                                                                                <fo:inline font-size="8pt">* Phone Number (give area code)</fo:inline>
                                                                                            </fo:block>
                                                                                        </fo:block>&#160; <xsl:for-each select="SF424:Individual">
                                                                                            <xsl:for-each select="SF424:Contact">
                                                                                                <xsl:for-each select="SF424:TelephoneNumber">
                                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                                        <xsl:apply-templates />
                                                                                                    </fo:inline>
                                                                                                </xsl:for-each>
                                                                                            </xsl:for-each>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-start-color="black" font-size="8pt" padding-before="0pt" display-align="before" width="50%" padding-start="3pt" padding-end="3pt" padding-after="3pt" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                            <fo:block font-size="8pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt">
                                                                                                <fo:inline font-size="8pt">Fax Number (give area code)</fo:inline>
                                                                                            </fo:block>
                                                                                        </fo:block>&#160; <xsl:for-each select="SF424:Individual">
                                                                                            <xsl:for-each select="SF424:Contact">
                                                                                                <xsl:for-each select="SF424:FaxNumber">
                                                                                                    <fo:inline font-size="8pt" font-style="normal">
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
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" number-columns-spanned="2" number-rows-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="7pt" padding-before="1pt">
                                                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                <fo:block font-size="8pt" padding-before="1pt">
                                                                                    <fo:inline font-size="8pt" font-weight="bold">&#160; 8. TYPE OF APPLICATION:</fo:inline>
                                                                                </fo:block>
                                                                            </fo:block>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; <xsl:for-each select="SF424:ApplicationTypeCode">
                                                                                <fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
                                                                                    <fo:inline>
                                                                                        <xsl:choose>
                                                                                            <xsl:when test=".='N'">
                                                                                                <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                            </xsl:when>
                                                                                            <xsl:when test=".=''">
                                                                                                <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                            </xsl:when>
                                                                                            <xsl:otherwise>
                                                                                                <fo:inline text-decoration="underline" color="black">
                                                                                                    <fo:leader leader-length="8pt" leader-pattern="rule" />
                                                                                                </fo:inline>
                                                                                            </xsl:otherwise>
                                                                                        </xsl:choose>
                                                                                    </fo:inline>
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                            <fo:inline font-size="8pt"> New</fo:inline>&#160;&#160;&#160;&#160; <xsl:for-each select="SF424:ApplicationTypeCode">
                                                                                <fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
                                                                                    <fo:inline>
                                                                                        <xsl:choose>
                                                                                            <xsl:when test=".='C'">
                                                                                                <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                            </xsl:when>
                                                                                            <xsl:when test=".=''">
                                                                                                <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                            </xsl:when>
                                                                                            <xsl:otherwise>
                                                                                                <fo:inline text-decoration="underline" color="black">
                                                                                                    <fo:leader leader-length="8pt" leader-pattern="rule" />
                                                                                                </fo:inline>
                                                                                            </xsl:otherwise>
                                                                                        </xsl:choose>
                                                                                    </fo:inline>
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                            <fo:inline font-size="8pt"> Continuation&#160; </fo:inline>&#160;&#160; <xsl:for-each select="SF424:ApplicationTypeCode">
                                                                                <fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
                                                                                    <fo:inline>
                                                                                        <xsl:choose>
                                                                                            <xsl:when test=".='R'">
                                                                                                <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                            </xsl:when>
                                                                                            <xsl:when test=".=''">
                                                                                                <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                            </xsl:when>
                                                                                            <xsl:otherwise>
                                                                                                <fo:inline text-decoration="underline" color="black">
                                                                                                    <fo:leader leader-length="8pt" leader-pattern="rule" />
                                                                                                </fo:inline>
                                                                                            </xsl:otherwise>
                                                                                        </xsl:choose>
                                                                                    </fo:inline>
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                            <fo:inline font-size="8pt"> Revision</fo:inline>
                                                                        </fo:block>
                                                                    </fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="8pt" padding-before="1pt">
                                                                            <fo:inline font-size="8pt">&#160; If Revision, enter appropriate letter(s) in box(es)&#160;&#160;&#160; </fo:inline>
                                                                            <xsl:for-each select="SF424:Revision">
                                                                                <xsl:for-each select="SF424:RevisionCode1">
                                                                                    <fo:inline padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" line-height="18pt" text-decoration="underline" color="black">
                                                                                        <fo:inline font-size="8pt"><xsl:value-of select="substring(.,1,7)" /></fo:inline>
                                                                                    </fo:inline>
                                                                                </xsl:for-each>&#160;&#160;&#160;&#160; </xsl:for-each>
                                                                            <xsl:for-each select="SF424:Revision">
                                                                                <xsl:for-each select="SF424:RevisionCode2">
                                                                                    <fo:inline padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" line-height="18pt" text-decoration="underline" color="black">
                                                                                        <fo:inline font-size="8pt"><xsl:value-of select="substring(.,1,7)" /></fo:inline>
                                                                                    </fo:inline>
                                                                                </xsl:for-each>
                                                                            </xsl:for-each>
                                                                        </fo:block>
                                                                    </fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="8pt" padding-before="1pt">
                                                                            <fo:inline font-size="8pt">&#160; Other (specify)&#160; </fo:inline>
                                                                            <xsl:for-each select="SF424:Revision">
                                                                                <xsl:for-each select="SF424:RevisionOtherExplanation">
                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                        <xsl:apply-templates />
                                                                                    </fo:inline>
                                                                                </xsl:for-each>
                                                                            </xsl:for-each>
                                                                        </fo:block>
                                                                    </fo:block>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="4pt" padding-end="0pt" padding-start="0pt" display-align="before" number-columns-spanned="3" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:inline font-size="8pt" font-weight="bold">&#160; 7. * TYPE OF APPLICANT:</fo:inline>
                                                                    <fo:inline font-size="8pt">&#160; </fo:inline>
                                                                    <xsl:for-each select="SF424:SubmittingOrganization">
                                                                        <fo:inline font-size="8pt">
                                                                            <xsl:for-each select="SF424:OrganizationIdentifyingInformation">
                                                                                <xsl:for-each select="SF424:ApplicantTypeCode">
                                                                                    <xsl:apply-templates />
                                                                                </xsl:for-each>
                                                                            </xsl:for-each>
                                                                        </fo:inline>
                                                                    </xsl:for-each>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="8pt" padding-before="1pt">
                                                                            <fo:inline font-size="8pt">&#160; Other (specify)&#160; </fo:inline>
                                                                            <xsl:for-each select="SF424:SubmittingOrganization">
                                                                                <xsl:for-each select="SF424:OrganizationIdentifyingInformation">
                                                                                    <xsl:for-each select="SF424:ApplicantTypeCodeOtherExplanation">
                                                                                        <fo:inline font-size="8pt" font-style="normal">
                                                                                            <xsl:apply-templates />
                                                                                        </fo:inline>
                                                                                    </xsl:for-each>
                                                                                </xsl:for-each>
                                                                            </xsl:for-each>
                                                                        </fo:block>
                                                                    </fo:block>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" number-columns-spanned="3" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="8pt" padding-before="1pt">
                                                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                <fo:block font-size="8pt" padding-before="1pt">
                                                                                    <fo:inline font-size="8pt" font-weight="bold">&#160; 9. * NAME OF FEDERAL AGENCY:</fo:inline>
                                                                                </fo:block>
                                                                            </fo:block>
                                                                            <fo:inline font-size="8pt" font-weight="bold">&#160;&#160;&#160; </fo:inline>
                                                                            <xsl:for-each select="SF424:AgencyName">
                                                                                <fo:inline font-size="8pt">
                                                                                    <xsl:apply-templates />
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                        </fo:block>
                                                                    </fo:block>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" number-columns-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="7pt" padding-before="1pt">
                                                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                <fo:block font-size="8pt" padding-before="1pt">
                                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                        <fo:block font-size="7pt" padding-before="1pt">
                                                                                            <fo:inline font-size="8pt" font-weight="bold">&#160; 10. CATALOG OF FEDERAL DOMESTIC ASSISTANCE NUMBER:</fo:inline>
                                                                                        </fo:block>
                                                                                    </fo:block>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; <xsl:for-each select="SF424:CFDANumber">
                                                                                        <fo:inline font-size="8pt" font-style="normal">
                                                                                            <xsl:apply-templates />
                                                                                        </fo:inline>
                                                                                    </xsl:for-each>
                                                                                </fo:block>
                                                                            </fo:block>
                                                                            <fo:inline font-size="8pt">&#160;&#160; TITLE:&#160; </fo:inline>
                                                                            <xsl:for-each select="SF424:ActivityTitle">
                                                                                <fo:inline font-size="8pt" font-style="normal">
                                                                                    <xsl:apply-templates />
                                                                                </fo:inline>
                                                                            </xsl:for-each>
                                                                        </fo:block>
                                                                    </fo:block>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" number-columns-spanned="3" number-rows-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="8pt" padding-before="1pt">
                                                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                <fo:block font-size="8pt" padding-before="1pt">
                                                                                    <fo:inline font-size="8pt" font-weight="bold">&#160; 11. * DESCRIPTIVE TITLE OF APPLICANT&apos;S PROJECT:</fo:inline>
                                                                                </fo:block>
                                                                            </fo:block>&#160;&#160;&#160; <xsl:for-each select="SF424:Project">
                                                                                <xsl:for-each select="SF424:ProjectTitle">
                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                        <xsl:apply-templates />
                                                                                    </fo:inline>
                                                                                </xsl:for-each>
                                                                            </xsl:for-each>
                                                                        </fo:block>
                                                                    </fo:block>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" number-columns-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="8pt" padding-before="1pt">
                                                                            <fo:inline font-size="8pt" font-weight="bold">&#160; 12. * AREAS AFFECTED BY PROJECT</fo:inline>
                                                                            <fo:inline font-size="8pt"> (</fo:inline>
                                                                            <fo:inline font-size="8pt" font-style="italic">Cities, Counties, States, etc.</fo:inline>
                                                                            <fo:inline font-size="8pt">):</fo:inline>
                                                                        </fo:block>
                                                                    </fo:block>
                                                                    <fo:inline font-size="8pt">&#160;&#160;&#160;&#160;&#160;&#160; </fo:inline>
                                                                    <xsl:for-each select="SF424:Project">
                                                                        <xsl:for-each select="SF424:Location">
                                                                            <fo:inline font-size="8pt" font-style="normal">
                                                                                <xsl:apply-templates />
                                                                            </fo:inline>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" number-columns-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="8pt" padding-before="1pt">
                                                                            <fo:inline font-size="8pt" font-weight="bold">&#160; 13. * PROPOSED PROJECT</fo:inline>
                                                                        </fo:block>
                                                                    </fo:block>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" number-columns-spanned="3" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="8pt" padding-before="1pt">
                                                                            <fo:inline font-size="8pt" font-weight="bold">&#160; 14. * CONGRESSIONAL DISTRICTS OF:</fo:inline>
                                                                        </fo:block>
                                                                    </fo:block>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="1pt" display-align="before" number-columns-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="125pt" />
                                                                        <fo:table-column />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="8pt" padding-before="1pt" width="125pt" padding-start="3pt" padding-end="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">* Start Date:&#160; </fo:inline>
                                                                                        <xsl:for-each select="SF424:Project">
                                                                                            <xsl:for-each select="SF424:ProposedStartDate">
                                                                                                <fo:inline font-size="8pt" font-style="normal">
                                                                                                    <xsl:call-template name="formatDate">
                                                                                                       <xsl:with-param name="value" select="."/>
                                                                                                    </xsl:call-template>
                                                                                                </fo:inline>
                                                                                            </xsl:for-each>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-start-color="black" font-size="8pt" padding-before="1pt" padding-start="3pt" padding-end="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt"> * Ending Date:&#160; </fo:inline>
                                                                                        <xsl:for-each select="SF424:Project">
                                                                                            <xsl:for-each select="SF424:ProposedEndDate">
                                                                                                <fo:inline font-size="8pt" font-style="normal">
                                                                                                    <xsl:call-template name="formatDate">
                                                                                                       <xsl:with-param name="value" select="."/>
                                                                                                    </xsl:call-template>
                                                                                                </fo:inline>
                                                                                            </xsl:for-each>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="1pt" display-align="before" number-columns-spanned="3" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="1pt">
                                                                        <fo:table-column column-width="135pt" />
                                                                        <fo:table-column />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="8pt" padding-before="1pt" width="135pt" padding-start="1pt" padding-end="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">* a. Applicant&#160; </fo:inline>
                                                                                        <xsl:for-each select="SF424:SubmittingOrganization">
                                                                                            <xsl:for-each select="SF424:CongressionalDistrict">
                                                                                                <fo:inline font-size="8pt" font-style="normal">
                                                                                                    <xsl:apply-templates />
                                                                                                </fo:inline>
                                                                                            </xsl:for-each>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-start-color="black" font-size="8pt" padding-before="1pt" padding-start="1pt" padding-end="1pt" padding-after="1pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">* b. Project&#160; </fo:inline>
                                                                                        <xsl:for-each select="SF424:Project">
                                                                                            <xsl:for-each select="SF424:CongressionalDistrict">
                                                                                                <fo:inline font-size="8pt" font-style="normal">
                                                                                                    <xsl:apply-templates />
                                                                                                </fo:inline>
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
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="after" number-columns-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="8pt" padding-before="1pt">
                                                                            <fo:inline font-size="8pt" font-weight="bold">&#160; 15. * ESTIMATED FUNDING:</fo:inline>
                                                                        </fo:block>
                                                                    </fo:block>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="after" number-columns-spanned="3" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="8pt" padding-before="1pt">
                                                                            <fo:inline font-size="8pt" font-weight="bold">&#160; 16. IS APPLICATION SUBJECT TO REVIEW BY STATE EXECUTIVE ORDER 12372 PROCESS?</fo:inline>
                                                                        </fo:block>
                                                                    </fo:block>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="1pt" display-align="before" number-columns-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="proportional-column-width(40)" />
                                                                        <fo:table-column column-width="proportional-column-width(75)" />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="10pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="0pt" display-align="before" width="40%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">&#160; * a. Federal</fo:inline>
                                                                                        <fo:inline font-size="8pt"></fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-start-color="black" font-size="10pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" display-align="before" number-rows-spanned="2" text-align="left" width="75%" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                     <fo:table font-size="8pt" padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                            <fo:table-column />
                                                                                                            <fo:table-column />
                                                                                                            <fo:table-body>
                                                                                                                <fo:table-row>
                      <fo:table-cell padding-start="1pt" text-align="left" padding-end="3pt" padding-before="0pt" padding-after="0pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                                        <fo:block>
                                                                                                                            <fo:inline font-size="8pt">$</fo:inline>
                                                                                                                        </fo:block>
                                                                                                                    </fo:table-cell>
           <fo:table-cell padding-start="1pt" text-align="right" padding-end="3pt" padding-before="0pt" padding-after="0pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                                        <fo:block>
                                                                                                                            <xsl:for-each select="SF424:Budget">
																 
									
									<xsl:for-each select="SF424:FederalEstimatedAmount">
									                                                                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                                                                      <xsl:value-of select="format-number(., '#,##0.00')" />
                                                                                                                                    </fo:inline>
                                                                                                                                </xsl:for-each>
                                                                                                                            </xsl:for-each>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </fo:block>
                                                                                                                    </fo:table-cell>
                                                                                                                </fo:table-row>
                                                                                                            </fo:table-body>
                                                                                                        </fo:table>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="1pt" display-align="before" number-columns-spanned="3" number-rows-spanned="6" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:table font-size="8pt" padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="30pt" />
                                                                        <fo:table-column column-width="15pt" />
                                                                        <fo:table-column />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="8pt" padding-before="1pt" width="30pt" padding-start="3pt" padding-end="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">a. Yes</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell font-size="7pt" padding-before="1pt" width="15pt" padding-start="3pt" padding-end="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="SF424:StateReviewCode">
                                                                                            <fo:inline font-size="8pt">
                                                                                                <fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
                                                                                                    <fo:inline>
                                                                                                        <xsl:choose>
                                                                                                            <xsl:when test=".='Yes'">
                                                                                                                <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                                            </xsl:when>
                                                                                                            <xsl:when test=".=''">
                                                                                                                <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                                            </xsl:when>
                                                                                                            <xsl:otherwise>
                                                                                                                <fo:inline text-decoration="underline" color="black">
                                                                                                                    <fo:leader leader-length="8pt" leader-pattern="rule" />
                                                                                                                </fo:inline>
                                                                                                            </xsl:otherwise>
                                                                                                        </xsl:choose>
                                                                                                    </fo:inline>
                                                                                                </fo:inline>
                                                                                            </fo:inline>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell font-size="8pt" padding-before="1pt" padding-start="3pt" padding-end="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">THIS PREAPPLICATION / APPLICATION WAS MADE AVAILABLE TO THE STATE EXECUTIVE ORDER 12372 PROCESS FOR REVIEW ON</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="7pt" padding-before="1pt" width="30pt" padding-start="3pt" padding-end="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block />
                                                                                </fo:table-cell>
                                                                                <fo:table-cell font-size="7pt" padding-before="1pt" width="15pt" padding-start="3pt" padding-end="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block />
                                                                                </fo:table-cell>
                                                                                <fo:table-cell font-size="8pt" padding-before="1pt" padding-start="3pt" padding-end="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">DATE:&#160; </fo:inline>
                                                                                        <xsl:for-each select="SF424:StateReviewDate">
                                                                                            <fo:inline font-size="8pt" font-style="normal">
                                                                                                 <xsl:call-template name="formatDate">
                                                                                                     <xsl:with-param name="value" select="."/>
                                                                                                 </xsl:call-template>
                                                                                            </fo:inline>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="8pt" padding-before="1pt" width="30pt" padding-start="3pt" padding-end="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">b. No</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell font-size="7pt" padding-before="1pt" width="15pt" padding-start="3pt" padding-end="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="SF424:StateReviewCode">
                                                                                            <fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
                                                                                                <fo:inline>
                                                                                                    <xsl:choose>
                                                                                                        <xsl:when test=".='Not Covered'">
                                                                                                            <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                                        </xsl:when>
                                                                                                        <xsl:when test=".=''">
                                                                                                            <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                                        </xsl:when>
                                                                                                        <xsl:otherwise>
                                                                                                            <fo:inline text-decoration="underline" color="black">
                                                                                                                <fo:leader leader-length="8pt" leader-pattern="rule" />
                                                                                                            </fo:inline>
                                                                                                        </xsl:otherwise>
                                                                                                    </xsl:choose>
                                                                                                </fo:inline>
                                                                                            </fo:inline>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell font-size="8pt" padding-before="1pt" padding-start="3pt" padding-end="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">PROGRAM IS NOT COVERED BY E.O. 12372</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="7pt" padding-before="1pt" width="30pt" padding-start="3pt" padding-end="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block />
                                                                                </fo:table-cell>
                                                                                <fo:table-cell font-size="7pt" padding-before="1pt" width="15pt" padding-start="3pt" padding-end="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <xsl:for-each select="SF424:StateReviewCode">
                                                                                            <fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
                                                                                                <fo:inline>
                                                                                                    <xsl:choose>
                                                                                                        <xsl:when test=".='Not Reviewed'">
                                                                                                            <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                                        </xsl:when>
                                                                                                        <xsl:when test=".=''">
                                                                                                            <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                                        </xsl:when>
                                                                                                        <xsl:otherwise>
                                                                                                            <fo:inline text-decoration="underline" color="black">
                                                                                                                <fo:leader leader-length="8pt" leader-pattern="rule" />
                                                                                                            </fo:inline>
                                                                                                        </xsl:otherwise>
                                                                                                    </xsl:choose>
                                                                                                </fo:inline>
                                                                                            </fo:inline>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell font-size="8pt" padding-before="1pt" padding-start="3pt" padding-end="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">OR PROGRAM HAS NOT BEEN SELECTED BY STATE FOR REVIEW</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="1pt" display-align="before" number-columns-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="proportional-column-width(40)" />
                                                                        <fo:table-column column-width="proportional-column-width(75)" />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="10pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="40%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">&#160; * b. Applicant</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-start-color="black" font-size="10pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" display-align="before" number-rows-spanned="2" text-align="left" width="75%" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                     <fo:table font-size="8pt" padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                            <fo:table-column />
                                                                                                            <fo:table-column />
                                                                                                            <fo:table-body>
                                                                                                                <fo:table-row>
                      <fo:table-cell padding-start="1pt" text-align="left" padding-end="3pt" padding-before="0pt" padding-after="0pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                                        <fo:block>
                                                                                                                            <fo:inline font-size="8pt">$</fo:inline>
                                                                                                                        </fo:block>
                                                                                                                    </fo:table-cell>
           <fo:table-cell padding-start="1pt" text-align="right" padding-end="3pt" padding-before="0pt" padding-after="0pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                                        <fo:block>
                                                                                                                            <xsl:for-each select="SF424:Budget">
																 <xsl:for-each select="SF424:ApplicantEstimatedAmount">
                                                                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                                                                         <xsl:value-of select="format-number(., '#,##0.00')" />
                                                                                                                                    </fo:inline>
                                                                                                                                </xsl:for-each>
                                                                                                                            </xsl:for-each>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </fo:block>
                                                                                                                    </fo:table-cell>
                                                                                                                </fo:table-row>
                                                                                                            </fo:table-body>
                                                                                                        </fo:table>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="1pt" display-align="before" number-columns-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="proportional-column-width(40)" />
                                                                        <fo:table-column column-width="proportional-column-width(75)" />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="10pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="40%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">&#160; * c. State</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                 <fo:table-cell border-start-color="black" font-size="10pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" display-align="before" number-rows-spanned="2" text-align="left" width="75%" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                     <fo:table font-size="8pt" padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                            <fo:table-column />
                                                                                                            <fo:table-column />
                                                                                                            <fo:table-body>
                                                                                                                <fo:table-row>
                      <fo:table-cell padding-start="1pt" text-align="left" padding-end="3pt" padding-before="0pt" padding-after="0pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                                        <fo:block>
                                                                                                                            <fo:inline font-size="8pt">$</fo:inline>
                                                                                                                        </fo:block>
                                                                                                                    </fo:table-cell>
           <fo:table-cell padding-start="1pt" text-align="right" padding-end="3pt" padding-before="0pt" padding-after="0pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                                        <fo:block>
                                                                                                                            <xsl:for-each select="SF424:Budget">
																 <xsl:for-each select="SF424:StateEstimatedAmount">
                                                                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                                                                         <xsl:value-of select="format-number(., '#,##0.00')" />
                                                                                                                                    </fo:inline>
                                                                                                                                </xsl:for-each>
                                                                                                                            </xsl:for-each>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </fo:block>
                                                                                                                    </fo:table-cell>
                                                                                                                </fo:table-row>
                                                                                                            </fo:table-body>
                                                                                                        </fo:table>
                                                                                    </fo:block>
                                                                                </fo:table-cell>

                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="1pt" display-align="before" number-columns-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="proportional-column-width(40)" />
                                                                        <fo:table-column column-width="proportional-column-width(75)" />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="40%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">&#160; * d. Local</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                 <fo:table-cell border-start-color="black" font-size="10pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" display-align="before" number-rows-spanned="2" text-align="left" width="75%" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                     <fo:table font-size="8pt" padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                            <fo:table-column />
                                                                                                            <fo:table-column />
                                                                                                            <fo:table-body>
                                                                                                                <fo:table-row>
                      <fo:table-cell padding-start="1pt" text-align="left" padding-end="3pt" padding-before="0pt" padding-after="0pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                                        <fo:block>
                                                                                                                            <fo:inline font-size="8pt">$</fo:inline>
                                                                                                                        </fo:block>
                                                                                                                    </fo:table-cell>
           <fo:table-cell padding-start="1pt" text-align="right" padding-end="3pt" padding-before="0pt" padding-after="0pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                                        <fo:block>
                                                                                                                            <xsl:for-each select="SF424:Budget">
																 <xsl:for-each select="SF424:LocalEstimatedAmount">
                                                                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                                                                         <xsl:value-of select="format-number(., '#,##0.00')" />
                                                                                                                                    </fo:inline>
                                                                                                                                </xsl:for-each>
                                                                                                                            </xsl:for-each>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </fo:block>
                                                                                                                    </fo:table-cell>
                                                                                                                </fo:table-row>
                                                                                                            </fo:table-body>
                                                                                                        </fo:table>
                                                                                    </fo:block>
                                                                                </fo:table-cell>

                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="1pt" display-align="before" number-columns-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="proportional-column-width(40)" />
                                                                        <fo:table-column column-width="proportional-column-width(75)" />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="10pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="40%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">&#160; * e. Other</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
 <fo:table-cell border-start-color="black" font-size="10pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" display-align="before" number-rows-spanned="2" text-align="left" width="75%" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                     <fo:table font-size="8pt" padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                            <fo:table-column />
                                                                                                            <fo:table-column />
                                                                                                            <fo:table-body>
                                                                                                                <fo:table-row>
                      <fo:table-cell padding-start="1pt" text-align="left" padding-end="3pt" padding-before="0pt" padding-after="0pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                                        <fo:block>
                                                                                                                            <fo:inline font-size="8pt">$</fo:inline>
                                                                                                                        </fo:block>
                                                                                                                    </fo:table-cell>
           <fo:table-cell padding-start="1pt" text-align="right" padding-end="3pt" padding-before="0pt" padding-after="0pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                                        <fo:block>
                                                                                                                            <xsl:for-each select="SF424:Budget">
																 <xsl:for-each select="SF424:OtherEstimatedAmount">
                                                                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                                                                         <xsl:value-of select="format-number(., '#,##0.00')" />
                                                                                                                                    </fo:inline>
                                                                                                                                </xsl:for-each>
                                                                                                                            </xsl:for-each>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </fo:block>
                                                                                                                    </fo:table-cell>
                                                                                                                </fo:table-row>
                                                                                                            </fo:table-body>
                                                                                                        </fo:table>
                                                                                    </fo:block>
                                                                                </fo:table-cell>

                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="1pt" display-align="before" number-columns-spanned="2" number-rows-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="proportional-column-width(40)" />
                                                                        <fo:table-column column-width="proportional-column-width(75)" />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="10pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="40%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">&#160; * f. Program Income</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
 <fo:table-cell border-start-color="black" font-size="10pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" display-align="before" number-rows-spanned="2" text-align="left" width="75%" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                     <fo:table font-size="8pt" padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                            <fo:table-column />
                                                                                                            <fo:table-column />
                                                                                                            <fo:table-body>
                                                                                                                <fo:table-row>
                      <fo:table-cell padding-start="1pt" text-align="left" padding-end="3pt" padding-before="0pt" padding-after="0pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                                        <fo:block>
                                                                                                                            <fo:inline font-size="8pt">$</fo:inline>
                                                                                                                        </fo:block>
                                                                                                                    </fo:table-cell>
           <fo:table-cell padding-start="1pt" text-align="right" padding-end="3pt" padding-before="0pt" padding-after="0pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                                        <fo:block>
                                                                                                                            <xsl:for-each select="SF424:Budget">
																 <xsl:for-each select="SF424:ProgramIncomeEstimatedAmount">
                                                                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                                                                         <xsl:value-of select="format-number(., '#,##0.00')" />
                                                                                                                                    </fo:inline>
                                                                                                                                </xsl:for-each>
                                                                                                                            </xsl:for-each>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </fo:block>
                                                                                                                    </fo:table-cell>
                                                                                                                </fo:table-row>
                                                                                                            </fo:table-body>
                                                                                                        </fo:table>
                                                                                    </fo:block>
                                                                                </fo:table-cell>

                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" number-columns-spanned="3" number-rows-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="8pt" padding-before="1pt">
                                                                            <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                <fo:block>
                                                                                    <fo:inline font-size="8pt" font-weight="bold">&#160; 17. IS THE APPLICANT DELINQUENT ON ANY FEDERAL DEBT?</fo:inline>
                                                                                </fo:block>
                                                                            </fo:block>&#160;&#160;&#160; </fo:block>
                                                                    </fo:block>&#160;&#160; <xsl:for-each select="SF424:SubmittingOrganization">
                                                                        <xsl:for-each select="SF424:DelinquentFederalDebtIndicator">
                                                                            <fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
                                                                                <fo:inline>
                                                                                    <xsl:choose>
                                                                                        <xsl:when test=".='Y'">
                                                                                            <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                        </xsl:when>
                                                                                        <xsl:when test=".=''">
                                                                                            <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                        </xsl:when>
                                                                                        <xsl:otherwise>
                                                                                            <fo:inline text-decoration="underline" color="black">
                                                                                                <fo:leader leader-length="8pt" leader-pattern="rule" />
                                                                                            </fo:inline>
                                                                                        </xsl:otherwise>
                                                                                    </xsl:choose>
                                                                                </fo:inline>
                                                                            </fo:inline>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                    <fo:inline font-size="8pt"> Yes If &quot;Yes&quot; attach an explanation.</fo:inline>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; <xsl:for-each select="SF424:SubmittingOrganization">
                                                                        <xsl:for-each select="SF424:DelinquentFederalDebtIndicator">
                                                                            <fo:inline padding-before="-3pt" padding-after="-2pt" text-decoration="underline" color="black">
                                                                                <fo:inline>
                                                                                    <xsl:choose>
                                                                                        <xsl:when test=".='N'">
                                                                                            <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                        </xsl:when>
                                                                                        <xsl:when test=".=''">
                                                                                            <fo:inline white-space-collapse="false" font-family="ZapfDingbats" font-size="10pt" padding-start="1pt" padding-end="1pt">&#x2714;</fo:inline>
                                                                                        </xsl:when>
                                                                                        <xsl:otherwise>
                                                                                            <fo:inline text-decoration="underline" color="black">
                                                                                                <fo:leader leader-length="8pt" leader-pattern="rule" />
                                                                                            </fo:inline>
                                                                                        </xsl:otherwise>
                                                                                    </xsl:choose>
                                                                                </fo:inline>
                                                                            </fo:inline>
                                                                        </xsl:for-each>
                                                                    </xsl:for-each>
                                                                    <fo:inline font-size="8pt"> No</fo:inline>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="8pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="1pt" display-align="before" number-columns-spanned="2" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:table height="8pt" padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="proportional-column-width(40)" />
                                                                        <fo:table-column column-width="proportional-column-width(75)" />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="10pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="40%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">&#160; g. TOTAL</fo:inline>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
 <fo:table-cell border-start-color="black" font-size="10pt" padding-after="0pt" padding-before="2pt" padding-end="0pt" padding-start="1pt" display-align="before" number-rows-spanned="2" text-align="left" width="75%" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                     <fo:table font-size="8pt" padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                                                            <fo:table-column />
                                                                                                            <fo:table-column />
                                                                                                            <fo:table-body>
                                                                                                                <fo:table-row>
                      <fo:table-cell padding-start="1pt" text-align="left" padding-end="3pt" padding-before="0pt" padding-after="0pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                                                                                                                        <fo:block>
                                                                                                                            <fo:inline font-size="8pt">$</fo:inline>
                                                                                                                        </fo:block>
                                                                                                                    </fo:table-cell>
           <fo:table-cell padding-start="1pt" text-align="right" padding-end="3pt" padding-before="0pt" padding-after="0pt" display-align="center" border-style="solid" border-width="0pt" border-color="white">
                                                                                                                        <fo:block>
                                                                                                                            <xsl:for-each select="SF424:Budget">
																 <xsl:for-each select="SF424:TotalEstimatedAmount">
                                                                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                                                                         <xsl:value-of select="format-number(., '#,##0.00')" />
                                                                                                                                    </fo:inline>
                                                                                                                                </xsl:for-each>
                                                                                                                            </xsl:for-each>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160; </fo:block>
                                                                                                                    </fo:table-cell>
                                                                                                                </fo:table-row>
                                                                                                            </fo:table-body>
                                                                                                        </fo:table>
                                                                                    </fo:block>
                                                                                </fo:table-cell>

                                                                            </fo:table-row>
                                                                        </fo:table-body>
                                                                    </fo:table>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" number-columns-spanned="5" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="8pt" padding-before="1pt">
                                                                            <fo:inline font-size="8pt" font-weight="bold">&#160; 18. * TO THE BEST OF MY KNOWLEDGE AND BELIEF, ALL DATA IN THIS APPLICATION/PREAPPLICATION ARE TRUE AND CORRECT.  THE DOCUMENT HAS BEEN DULY AUTHORIZED BY THE GOVERNING BODY OF THE APPLICANT AND THE APPLICANT WILL COMPLY WITH THE ATTACHED ASSURANCES.</fo:inline>
                                                                        </fo:block>
                                                                    </fo:block>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" number-columns-spanned="5" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:block font-size="7pt" padding-before="1pt">
                                                                            <fo:inline font-size="8pt">&#160; a. Authorized Representative</fo:inline>
                                                                        </fo:block>
                                                                    </fo:block>
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="1pt" display-align="before" number-columns-spanned="5" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="proportional-column-width(20)" />
                                                                        <fo:table-column column-width="proportional-column-width(40)" />
                                                                        <fo:table-column column-width="proportional-column-width(40)" />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="20%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">&#160; Prefix:&#160; </fo:inline>
                                                                                        <xsl:for-each select="SF424:Individual">
                                                                                            <xsl:for-each select="SF424:AuthorizedRepresentative">
                                                                                                <xsl:for-each select="SF424:NamePrefix">
                                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                                        <xsl:apply-templates />
                                                                                                    </fo:inline>
                                                                                                </xsl:for-each>
                                                                                            </xsl:for-each>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-start-color="black" font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="40%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">&#160; * First Name:&#160; </fo:inline>
                                                                                        <xsl:for-each select="SF424:Individual">
                                                                                            <xsl:for-each select="SF424:AuthorizedRepresentative">
                                                                                                <xsl:for-each select="SF424:GivenName1">
                                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                                        <xsl:apply-templates />
                                                                                                    </fo:inline>
                                                                                                </xsl:for-each>
                                                                                            </xsl:for-each>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-start-color="black" font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="40%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">&#160; Middle Name:&#160; </fo:inline>
                                                                                        <xsl:for-each select="SF424:Individual">
                                                                                            <xsl:for-each select="SF424:AuthorizedRepresentative">
                                                                                                <xsl:for-each select="SF424:GivenName2">
                                                                                                    <fo:inline font-size="8pt" font-style="normal">
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
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="1pt" number-columns-spanned="5" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="proportional-column-width(55)" />
                                                                        <fo:table-column column-width="proportional-column-width(45)" />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="55%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">&#160; * Last Name:&#160; </fo:inline>
                                                                                        <xsl:for-each select="SF424:Individual">
                                                                                            <xsl:for-each select="SF424:AuthorizedRepresentative">
                                                                                                <xsl:for-each select="SF424:FamilyName">
                                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                                        <xsl:apply-templates />
                                                                                                    </fo:inline>
                                                                                                </xsl:for-each>
                                                                                            </xsl:for-each>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-start-color="black" font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" number-rows-spanned="2" width="45%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">&#160; Suffix:&#160; </fo:inline>
                                                                                        <xsl:for-each select="SF424:Individual">
                                                                                            <xsl:for-each select="SF424:AuthorizedRepresentative">
                                                                                                <xsl:for-each select="SF424:NameSuffix">
                                                                                                    <fo:inline font-size="8pt" font-style="normal">
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
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="1pt" number-columns-spanned="5" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="proportional-column-width(55)" />
                                                                        <fo:table-column column-width="proportional-column-width(45)" />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="55%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">&#160; * b. Title:&#160; </fo:inline>
                                                                                        <xsl:for-each select="SF424:Individual">
                                                                                            <xsl:for-each select="SF424:AuthorizedRepresentative">
                                                                                                <xsl:for-each select="SF424:RepresentativeTitle">
                                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                                        <xsl:apply-templates />
                                                                                                    </fo:inline>
                                                                                                </xsl:for-each>
                                                                                            </xsl:for-each>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-start-color="black" font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="45%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">&#160; * c. Telephone Number (give area code):&#160; </fo:inline>
                                                                                        <xsl:for-each select="SF424:Individual">
                                                                                            <xsl:for-each select="SF424:AuthorizedRepresentative">
                                                                                                <xsl:for-each select="SF424:TelephoneNumber">
                                                                                                    <fo:inline font-size="8pt" font-style="normal">
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
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="1pt" number-columns-spanned="5" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="proportional-column-width(55)" />
                                                                        <fo:table-column column-width="proportional-column-width(45)" />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="7pt" hyphenate="true" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="55%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">&#160; * Email:&#160; </fo:inline>
                                                                                        <xsl:for-each select="SF424:Individual">
                                                                                            <xsl:for-each select="SF424:AuthorizedRepresentative">
                                                                                                <xsl:for-each select="SF424:ElectronicMailAddress">
                                                                                                    <fo:inline font-size="8pt" font-style="normal">
                                                                                                        <xsl:apply-templates />
                                                                                                    </fo:inline>
                                                                                                </xsl:for-each>
                                                                                            </xsl:for-each>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-start-color="black" font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="45%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">&#160; Fax Number (give area code):&#160; </fo:inline>
                                                                                        <xsl:for-each select="SF424:Individual">
                                                                                            <xsl:for-each select="SF424:AuthorizedRepresentative">
                                                                                                <xsl:for-each select="SF424:FaxNumber">
                                                                                                    <fo:inline font-size="8pt" font-style="normal">
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
                                                                </fo:block>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                        <fo:table-row>
                                                            <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="1pt" padding-start="1pt" number-columns-spanned="5" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="black">
                                                                <fo:block>
                                                                    <fo:table padding="0" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                                                        <fo:table-column column-width="proportional-column-width(55)" />
                                                                        <fo:table-column column-width="proportional-column-width(45)" />
                                                                        <fo:table-body>
                                                                            <fo:table-row>
                                                                                <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="55%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">&#160; d. Signature of Authorized Representative: &#160; </fo:inline>
                                                                                        <xsl:for-each select="SF424:AuthorizedRepresentativeSignature">
																							<fo:inline font-size="8pt" font-style="normal">
																								<xsl:apply-templates />
																							</fo:inline>
                                                                                        </xsl:for-each>
                                                                                    </fo:block>
                                                                                </fo:table-cell>
                                                                                <fo:table-cell border-start-color="black" font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" width="45%" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                                                                    <fo:block>
                                                                                        <fo:inline font-size="8pt">&#160; e. Date Signed:&#160; </fo:inline>
                                                                                        <xsl:for-each select="SF424:SignedDate">
                                                                                            <fo:inline font-size="8pt" font-style="normal">
                                                                                                <xsl:value-of select="format-number(substring(.,6,2), '00')"/>
<xsl:text>-</xsl:text>
<xsl:value-of select="format-number(substring(.,9,2), '00')"/>
<xsl:text>-</xsl:text>
 <xsl:value-of select="format-number(substring(.,1,4), '0000')"/>                                                                                            </fo:inline>
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
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt" display-align="before" text-align="left" width="25%" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block>
                                                <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:block font-size="7pt" padding-before="1pt">
                                                        <fo:inline font-size="7pt">Previous Edition Usable</fo:inline>
                                                        <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                            <fo:block font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt">
                                                                <fo:inline font-size="7pt">Authorized for Local Reproduction</fo:inline>
                                                            </fo:block>
                                                        </fo:block>
                                                    </fo:block>
                                                </fo:block>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell font-size="7pt" padding-before="1pt" display-align="before" text-align="right" width="40%" padding-start="3pt" padding-end="3pt" padding-after="3pt" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block>
                                                <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                    <fo:block font-size="7pt" padding-before="1pt">
                                                        <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                            <fo:block font-size="7pt" padding-before="1pt">
                                                                <fo:inline font-size="8pt">Standard Form 424 (Rev. x-xx)</fo:inline>
                                                                <fo:block space-before.optimum="1pt" space-after.optimum="2pt">
                                                                    <fo:block font-size="7pt" padding-after="0pt" padding-before="1pt" padding-end="0pt" padding-start="0pt">
                                                                        <fo:inline font-size="8pt">Prescribed by OMB Circular A-102</fo:inline>
                                                                    </fo:block>
                                                                </fo:block>
                                                            </fo:block>
                                                        </fo:block>
                                                    </fo:block>
                                                </fo:block>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
 				</fo:block>                           
                            
                            
                            <xsl:if test="SF424:SubmittingOrganization/SF424:DelinquentFederalDebtIndicator='Y'">                       
                            <fo:block break-after="page">
                                <xsl:text>&#xA;</xsl:text>
                            </fo:block>
                            <fo:leader leader-pattern="space" />     
                            <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                                <fo:table-column />
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell font-size="8pt" padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block>
                                                <fo:inline font-weight="bold">DELINQUENT FEDERAL DEBT</fo:inline>
                                                <fo:inline>&#160;</fo:inline>
                                                <fo:inline font-weight="bold">EXPLANATION</fo:inline>
                                            </fo:block>
                                        </fo:table-cell>
                                            </fo:table-row>
                                            <fo:table-row>
                                        <fo:table-cell>
																																									<fo:block text-align="center"><fo:inline font-weight="bold" font-size="8pt">The following field should contain an explanation if the Applicant organization is delinquent on any Federal Debt.  </fo:inline></fo:block>
																																								</fo:table-cell>
																		</fo:table-row>
																		<fo:table-row>
                                        <fo:table-cell>
																																									<fo:block text-align="center"><fo:inline font-size="8pt">Maximum number of characters that can be entered is 4,000.  Try and avoid extra spaces and carriage returns to maximize the availability of space.  </fo:inline></fo:block>
																																								</fo:table-cell>
																		

                                    </fo:table-row>
                                    <fo:table-row>
                                        <fo:table-cell padding-start="3pt" padding-end="3pt" padding-before="3pt" padding-after="3pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                            <fo:block white-space-collapse="false" space-before.optimum="1pt" space-after.optimum="2pt">
                                                <xsl:for-each select="SF424:SubmittingOrganization">
                                                    <xsl:for-each select="SF424:DelinquentFederalDebtExplanation">
                                                        <xsl:apply-templates />
                                                    </xsl:for-each>
                                                </xsl:for-each>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </fo:table-body>
                               </fo:table>
                            </xsl:if>
                            
                    
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

   <!-- ============================================= -->
   <!-- FORMAT DATE                                   -->
   <!-- Writes XSD:date style text into to mm-dd-yyyy -->
   <!-- ============================================= -->
   <xsl:template name="formatDate">
      <xsl:param name="value"/>
      <xsl:if test="$value != ''">
         <xsl:value-of select="format-number(substring($value,6,2), '00')"/>
         <xsl:text>-</xsl:text>
         <xsl:value-of select="format-number(substring($value,9,2), '00')"/>
         <xsl:text>-</xsl:text>
         <xsl:value-of select="format-number(substring($value,1,4), '0000')"/>
      </xsl:if>
   </xsl:template>
</xsl:stylesheet>
