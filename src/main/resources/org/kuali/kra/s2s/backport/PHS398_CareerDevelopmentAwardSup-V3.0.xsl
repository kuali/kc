<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:PHS398_CareerDevelopmentAwardSup_3_0="http://apply.grants.gov/forms/PHS398_CareerDevelopmentAwardSup_3_0-V3.0"
	xmlns:att="http://apply.grants.gov/system/Attachments-V1.0"
	xmlns:codes="http://apply.grants.gov/system/UniversalCodes-V2.0"
	xmlns:fn="http://www.w3.org/2005/xpath-functions"
	xmlns:glob="http://apply.grants.gov/system/Global-V1.0"
	xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
	xmlns:footer="http://apply.grants.gov/system/Footer-V1.0"
	xmlns:header="http://apply.grants.gov/system/Header-V1.0"
	xmlns:xdt="http://www.w3.org/2005/xpath-datatypes" xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:output version="1.0" method="xml" encoding="UTF-8" indent="no"/>
	<xsl:param name="SV_OutputFormat" select="'PDF'"/>
	<xsl:variable name="XML" select="/"/>
	<xsl:variable name="fo:layout-master-set">
		<fo:layout-master-set>
			<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in"
				margin-left="0.6in" margin-right="0.6in">
				<fo:region-body margin-top="0.79in" margin-bottom="0.79in"/>
				<fo:region-before extent="0.79in"/>
					<fo:region-after region-name="region-after-all"
						extent="0.3in" />
			</fo:simple-page-master>
		</fo:layout-master-set>
	</xsl:variable>
	<xsl:template match="/">
		<fo:root>
			<xsl:copy-of select="$fo:layout-master-set"/>
			<fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
				<xsl:call-template name="headerall"/>
            <fo:static-content flow-name="region-after-all">
               <fo:table width="100%"
                  space-before.optimum="0pt"
                  space-after.optimum="0pt"
                  table-layout="fixed">
                  <fo:table-column column-width="proportional-column-width(1)"/>
                  <fo:table-column column-width="proportional-column-width(1)"/>
                  <fo:table-body>
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en"
                         padding-start="0pt"
                         padding-end="0pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="0pt"
                         border-color="white">
                           <fo:block>
                              <fo:inline font-size="8px">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
                              </fo:inline>
                           </fo:block>
                        </fo:table-cell>
                        <fo:table-cell hyphenate="true" language="en" line-height="10pt"
                         padding-start="0pt"
                         padding-end="0pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="right"
                         border-style="solid"
                         border-width="0pt"
                         border-color="white">
                           <fo:block><fo:inline font-size="8px">Funding Opportunity Number: <xsl:value-of select="/*/*/header:OpportunityID"/></fo:inline>
                                     <fo:inline font-size="8px">.       Received Date: <xsl:value-of select="/*/*/footer:ReceivedDateTime"/></fo:inline></fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                  </fo:table-body>
               </fo:table>
            </fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<fo:block>
						<xsl:for-each select="$XML">
							<fo:inline-container>
								<fo:block>
									<xsl:text>&#x2029;</xsl:text>
								</fo:block>
							</fo:inline-container>
							<fo:table font-family="arialuni" table-layout="fixed"
								width="100%">
								<fo:table-column column-width="proportional-column-width(1)"/>
								<fo:table-body start-indent="0pt">
									<fo:table-row>
										<fo:table-cell border="solid 1pt black" padding="1pt"
											display-align="center">
											<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%"
												border-spacing="2pt">
												<fo:table-column column-width="40%"/>
												<fo:table-column column-width="60%"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline font-family="arialuni" font-size="10px" font-weight="bold">
												<xsl:text>Introduction</xsl:text>
												</fo:inline>
												<fo:block/>
												<fo:inline font-size="9px">
												<xsl:text>1. Introduction to Application</xsl:text>
												</fo:inline>
												<fo:block/>
												<fo:inline font-size="8px">
												<xsl:text>(RESUBMISSION)</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table font-family="arialuni" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:IntroductionToApplication">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:attFile">
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									
									<fo:table-row>
										<fo:table-cell border="solid 1pt black" padding="1pt" display-align="center">
											<fo:block font-family="arialuni">
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%"
												border-spacing="2pt">
												<fo:table-column column-width="40%"/>
												<fo:table-column column-width="60%"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell number-columns-spanned="2"
												padding="2pt" display-align="center">
												<fo:block>
												<fo:inline font-family="arialuni" font-size="10px" font-weight="bold">
												<xsl:text>Candidate Section</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												</fo:table-row>
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>2. Candidate Information and Goals for Career Development</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table font-family="arialuni" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CandidateInformationAndGoals">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:attFile">
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									
									<fo:table-row>
										<fo:table-cell border="solid 1pt black" padding="1pt"
											display-align="center">
											<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%"
												border-spacing="2pt">
												<fo:table-column column-width="40%"/>
												<fo:table-column column-width="60%"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell number-columns-spanned="2"
												padding="2pt" display-align="center">
												<fo:block>
												<fo:inline font-family="arialuni" font-size="10px" font-weight="bold">
												<xsl:text>Research Plan Section</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												</fo:table-row>
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>3. Specific Aims</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table font-family="arialuni" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:SpecificAims">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:attFile">
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>4. Research Strategy*</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table font-family="arialuni" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:ResearchStrategy">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:attFile">
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>5. Progress Report Publication List</xsl:text>
												</fo:inline>
												<fo:block/>
												<fo:inline font-size="8px">
												<xsl:text>(for RENEWAL applications only)</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table font-family="arialuni" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:ProgressReportPublicationList">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:attFile">
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>6. Training in the Responsible Conduct of Research </xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table font-family="arialuni" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:ResponsibleConductOfResearch">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:attFile">
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									
									<fo:table-row>
										<fo:table-cell border="solid 1pt black" padding="1pt" display-align="center">
											<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%"
												border-spacing="2pt">
												<fo:table-column column-width="40%"/>
												<fo:table-column column-width="60%"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell number-columns-spanned="2"
												padding="2pt" display-align="center">
												<fo:block>
												<fo:inline font-family="arialuni" font-size="10px" font-weight="bold">
												<xsl:text>Other Candidate Information Section</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												</fo:table-row>
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>7. Candidate&apos;s Plan to Provide Mentoring</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table font-family="arialuni" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:MentoringPlan">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:attFile">
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>									
									
									
									<fo:table-row>
										<fo:table-cell border="solid 1pt black" padding="1pt"
											display-align="center">
											<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%"
												border-spacing="2pt">
												<fo:table-column column-width="40%"/>
												<fo:table-column column-width="60%"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell number-columns-spanned="2"
												padding="2pt" display-align="center">
												<fo:block>
												<fo:inline font-family="arialuni" font-size="10px" font-weight="bold">
												<xsl:text>Mentor, Co-Mentor, Consultant, Collaborators Section</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												</fo:table-row>
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>8. Plans and Statements of Mentor and</xsl:text>
												</fo:inline>
												<fo:block/>
												<fo:inline font-size="9px">
												<xsl:text>Co-Mentor(s)</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table font-family="arialuni" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:StatementsOfSupport">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:attFile">
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>9. Letters of Support from Collaborators,</xsl:text>  
												</fo:inline>
												<fo:block/>
												<fo:inline font-size="9px">
												<xsl:text>Contributors, and Consultants</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table font-family="arialuni" font-size="9pt"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:LettersOfSupport">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:attFile">
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									
									<fo:table-row>
										<fo:table-cell border="solid 1pt black" padding="1pt"
											display-align="center">
											<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%"
												border-spacing="2pt">
												<fo:table-column column-width="40%"/>
												<fo:table-column column-width="60%"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell number-columns-spanned="2"
												padding="2pt" display-align="center">
												<fo:block>
												<fo:inline font-family="arialuni" font-size="10px" font-weight="bold">
												<xsl:text>Environment and Institutional Commitment to Candidate Section</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												</fo:table-row>
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>10. Description of Institutional Environment</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table font-family="arialuni" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:InsitutionalEnvironment">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:attFile">
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>11. Institutional Commitment to Candidate&apos;s</xsl:text>
												</fo:inline>
												<fo:block/>
												<fo:inline font-size="9px">
												<xsl:text> Research Career Development</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table font-family="arialuni" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:InstitutionalCommitment">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:attFile">
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									
																		
									
									<fo:table-row>
										<fo:table-cell border="solid 1pt black" padding="1pt"
											display-align="center">
											<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%"
												border-spacing="2pt">
												<fo:table-column column-width="40%"/>
												<fo:table-column column-width="60%"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell number-columns-spanned="2"
												padding="2pt" display-align="center">
												<fo:block>
												<fo:inline font-family="arialuni" font-size="10px" font-weight="bold">
												<xsl:text>Human Subject Section</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												</fo:table-row>
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>12. Protection of Human Subjects</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table font-family="arialuni" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:ProtectionOfHumanSubjects">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:attFile">
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>13. Data Safety Monitoring Plan</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table font-family="arialuni" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:DataSafetyMonitoringPlan">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:attFile">
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>14. Inclusion of Women and Minorities</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table font-family="arialuni" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:InclusionOfWomenAndMinorities">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:attFile">
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>15. Inclusion of Children</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table font-family="arialuni" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:InclusionOfChildren">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:attFile">
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									
									<fo:table-row>
										<fo:table-cell border="solid 1pt black" padding="1pt"
											display-align="center">
											<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%"
												border-spacing="2pt">
												<fo:table-column column-width="40%"/>
												<fo:table-column column-width="60%"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell number-columns-spanned="2"
												padding="2pt" display-align="center">
												<fo:block>
												<fo:inline font-family="arialuni" font-size="10px" font-weight="bold">
												<xsl:text>Other Research Plan Section</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												</fo:table-row>
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>16. Vertebrate Animals</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table font-family="arialuni" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:VertebrateAnimals">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:attFile">
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>17. Select Agent Research</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table font-family="arialuni" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:SelectAgentResearch">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:attFile">
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>19. Consortium/Contractual Arrangements</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table font-family="arialuni" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:ConsortiumContractualArrangements">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:attFile">
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>19. Resource Sharing</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table font-family="arialuni" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:ResourceSharingPlans">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:attFile">
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>20. Authentication of Key Biological and/or Chemical Resources </xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table font-family="arialuni" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:KeyBiologicalAndOrChemicalResources">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:attFile">
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									
									<fo:table-row keep-together.within-page="always">
										<fo:table-cell border="solid 1pt black" padding="1pt"
											display-align="center">
											<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%"
												border-spacing="2pt">
												<fo:table-column column-width="40%"/>
												<fo:table-column column-width="60%"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell number-columns-spanned="2"
												padding="2pt" display-align="center">
												<fo:block>
												<fo:inline font-family="arialuni" font-size="10px" font-weight="bold">
												<xsl:text>Appendix</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												</fo:table-row>
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="before">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>21. Appendix</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												<fo:table-cell padding="1pt"
												display-align="before">
												<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<xsl:if
												test="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0/PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments/PHS398_CareerDevelopmentAwardSup_3_0:Appendix/att:AttachedFile">
												<fo:table font-family="arialuni" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_3_0:Appendix">
												<xsl:for-each select="att:AttachedFile">
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<xsl:for-each select="att:FileName">
												<xsl:variable name="value-of-template">
												<xsl:apply-templates/>
												</xsl:variable>
												<xsl:choose>
												<xsl:when
												test="contains(string($value-of-template),'&#x2029;')">
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
												</xsl:for-each>
												</fo:table-body>
												</fo:table>
												</xsl:if>
												</fo:block>
												</fo:table-cell>
												</fo:table-row>
												</fo:table-body>
												</fo:table>
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
									
									<fo:table-row keep-together.within-page="always">
										<fo:table-cell border="solid 1pt black" padding="1pt"
											display-align="center">
											<fo:block>
												<fo:inline-container>
												<fo:block>
												<xsl:text>&#x2029;</xsl:text>
												</fo:block>
												</fo:inline-container>
												<fo:table table-layout="fixed" width="100%"
												border-spacing="2pt">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell number-columns-spanned="4"
												padding="2pt" display-align="center">
												<fo:block>
												<fo:inline font-family="arialuni" font-weight="bold" font-size="10px">
												<xsl:text>Citizenship*:</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												</fo:table-row>
												
												<fo:table-row font-size="8pt">
												<fo:table-cell number-columns-spanned="4"
												padding="2pt" display-align="before">
												<fo:block>
												<fo:inline font-family="arialuni" font-size="9px">
												<xsl:text>U.S. Citizen or Non-Citizen National?*</xsl:text>
												</fo:inline>
												</fo:block>
												
												<fo:block>
												<!-- Below block is to get checkbox image -->
												<fo:block-container background-color="transparent"
													border-style="none" position="absolute" left="166.363636363636363px"
													hyphenate="true" language="en" keep-together="always"
													height="18.333333333333334px" width="15.303030303030303px">
													<fo:block background-color="transparent" color="#000000"
														font-size="8pt" font-style="normal" font-family="ZapfDingbats"
														font-weight="normal">
														<xsl:choose>
															<xsl:when test="boolean(0)">
																<fo:inline color="#FFFFFF">&#160;</fo:inline>
															</xsl:when>
															<xsl:otherwise>
																<fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;
																</fo:inline>
															</xsl:otherwise>
														</xsl:choose>
													</fo:block>
												</fo:block-container>
												<fo:block-container background-color="transparent"
													border-style="none" position="absolute" left="166.363636363636363px"
													hyphenate="true" language="en" keep-together="always" 
													height="18.333333333333334px" width="15.303030303030303px">
													<fo:block background-color="transparent" color="#000000"
														font-size="8pt" font-style="normal" font-family="ZapfDingbats"
														font-weight="normal">
														<xsl:for-each
																select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
																<xsl:for-each
																select="PHS398_CareerDevelopmentAwardSup_3_0:CitizenshipIndicator">
																<xsl:choose>
																<xsl:when
																test="not(//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0/PHS398_CareerDevelopmentAwardSup_3_0:CitizenshipIndicator) or //PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0/PHS398_CareerDevelopmentAwardSup_3_0:CitizenshipIndicator = ''">
																	<fo:inline color="#FFFFFF">&#160;</fo:inline>
																</xsl:when>
																<xsl:otherwise>
																	<xsl:call-template name="checkbox">
																	<xsl:with-param name="value"
																	select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0/PHS398_CareerDevelopmentAwardSup_3_0:CitizenshipIndicator" />
																	<xsl:with-param name="schemaChoice">
																		Y: Yes
																	</xsl:with-param>
																</xsl:call-template>
																</xsl:otherwise>
																		</xsl:choose>
																</xsl:for-each>
																</xsl:for-each>
														</fo:block>
												</fo:block-container>
												<!-- end -->
												
												<fo:block-container background-color="transparent"
													border-style="none" position="absolute" left="176.363636363636363px"
													hyphenate="true" language="en" keep-together="always" 
													height="18.333333333333334px" width="15.303030303030303px">
													<fo:block>
													<fo:inline font-family="arialuni" font-size="9px">
														<xsl:text>Yes</xsl:text>
													</fo:inline>
												</fo:block>
												</fo:block-container>
												
												</fo:block>
												
												<fo:block>
												<!-- Below block is to get checkbox image for No -->
												<fo:block-container background-color="transparent"
													border-style="none" position="absolute" left="196.363636363636363px"
													hyphenate="true" language="en" keep-together="always"
													height="18.333333333333334px" width="15.303030303030303px">
													<fo:block background-color="transparent" color="#000000"
														font-size="8pt" font-style="normal" font-family="ZapfDingbats"
														font-weight="normal">

														<xsl:choose>
															<xsl:when test="boolean(0)">
																<fo:inline color="#FFFFFF">&#160;</fo:inline>
															</xsl:when>
															<xsl:otherwise>
																<fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;
																</fo:inline>
															</xsl:otherwise>
														</xsl:choose>
													</fo:block>
												</fo:block-container>
												<fo:block-container background-color="transparent"
													border-style="none" position="absolute" left="196.363636363636363px"
													hyphenate="true" language="en" keep-together="always" 
													height="18.333333333333334px" width="15.303030303030303px">
													<fo:block background-color="transparent" color="#000000"
														font-size="8pt" font-style="normal" font-family="ZapfDingbats"
														font-weight="normal">
														<xsl:for-each
																select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
																<xsl:for-each
																select="PHS398_CareerDevelopmentAwardSup_3_0:CitizenshipIndicator">
																<xsl:choose>
																<xsl:when
																test="not(//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0/PHS398_CareerDevelopmentAwardSup_3_0:CitizenshipIndicator) or //PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0/PHS398_CareerDevelopmentAwardSup_3_0:CitizenshipIndicator = 'N:No'">
																	<fo:inline color="#FFFFFF">&#160;</fo:inline>
																</xsl:when>
																<xsl:otherwise>
																	<xsl:call-template name="checkboxN">
																	<xsl:with-param name="value"
																	select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0/PHS398_CareerDevelopmentAwardSup_3_0:CitizenshipIndicator" />
																	<xsl:with-param name="schemaChoice">N: No</xsl:with-param>
																</xsl:call-template>
																</xsl:otherwise>
																		</xsl:choose>
																</xsl:for-each>
																</xsl:for-each>
														</fo:block>
												</fo:block-container>
												<!-- end -->
											<fo:block-container background-color="transparent"
													border-style="none" position="absolute" left="206.363636363636363px"
													hyphenate="true" language="en" keep-together="always" 
													height="18.333333333333334px" width="15.303030303030303px">
													<fo:block>
												<fo:inline font-family="arialuni" font-size="9px">
												<xsl:text>No</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:block-container>
												
												</fo:block>
												</fo:table-cell>
												</fo:table-row>
												
												<fo:table-row>
												<fo:table-cell number-columns-spanned="4"
												padding="2pt" display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>If no, select most appropriate Non-U.S. Citizen option</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												</fo:table-row>
												
												
												<fo:table-row font-size="8pt">
												<fo:table-cell number-columns-spanned="4"
												padding="2pt" display-align="before">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline> 
												</fo:block>
												
												<fo:block>
												<!-- Below block is to get checkbox image -->
												<fo:block-container background-color="transparent"
													border-style="none" position="absolute" left="10.36px"
													hyphenate="true" language="en" keep-together="always" 
													height="28.33px" width="15.30px">
													<fo:block background-color="transparent" color="#000000"
														font-size="10pt" font-style="normal" font-family="ZapfDingbats"
														font-weight="normal">
														<xsl:choose>
															<xsl:when test="boolean(0)">
																<fo:inline color="#FFFFFF">&#160;</fo:inline>
															</xsl:when>
															<xsl:otherwise>
																<fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;
																</fo:inline>
															</xsl:otherwise>
														</xsl:choose>
													</fo:block>
												</fo:block-container>
												<fo:block-container background-color="transparent"
													border-style="none" position="absolute" left="10.36px"
													hyphenate="true" language="en" keep-together="always"  
													height="28.33px" width="15.30px">
													<fo:block background-color="transparent" color="#000000"
														font-size="10pt" font-style="normal" font-family="ZapfDingbats"
														font-weight="normal">
														<xsl:for-each
																select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
																<xsl:for-each
																select="PHS398_CareerDevelopmentAwardSup_3_0:isNonUSCitizenship">
																<xsl:choose>
																<xsl:when
																test="string(.)='Permanent U.S. Resident Visa'">
																<xsl:call-template name="checkbox">
																	<xsl:with-param name="value"
																					select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0/PHS398_CareerDevelopmentAwardSup_3_0:isNonUSCitizenship" />
																	<xsl:with-param name="schemaChoice">Permanent U.S. Resident Visa</xsl:with-param>
																</xsl:call-template>
																</xsl:when>
																<xsl:otherwise>
																	<xsl:call-template name="checkboxN">
																	<xsl:with-param name="value"
																					select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0/PHS398_CareerDevelopmentAwardSup_3_0:isNonUSCitizenship" />
																	<xsl:with-param name="schemaChoice">N: No</xsl:with-param>
																</xsl:call-template>
																</xsl:otherwise> 
																</xsl:choose>
																</xsl:for-each>
																</xsl:for-each>
														</fo:block>
												</fo:block-container>
												<!-- end -->
												<fo:block-container background-color="transparent"
													border-style="none" position="absolute" left="20.36px"
													hyphenate="true" language="en" keep-together="always" 
													height="18.33px" width="15.30px">
												<fo:block font-size="8pt">
												<fo:inline font-family="arialuni" font-size="9px">
												<xsl:text>&#160;With a Permanent U.S. Resident Visa</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:block-container>
												</fo:block>

												</fo:table-cell>
												</fo:table-row>

												
												<fo:table-row font-size="8pt">
												<fo:table-cell number-columns-spanned="4"
												padding="2pt" display-align="before">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												</fo:block>

												<fo:block>
												<!-- Below block is to get checkbox image -->
												<fo:block-container background-color="transparent"
													border-style="none" position="absolute" left="10.36px"
													hyphenate="true" language="en" keep-together="always"
													height="28.33px" width="15.30px">
													<fo:block background-color="transparent" color="#000000"
														font-size="11pt" font-style="normal" font-family="ZapfDingbats"
														font-weight="normal">
														<xsl:choose>
															<xsl:when test="boolean(0)">
																<fo:inline color="#FFFFFF">&#160;</fo:inline>
															</xsl:when>
															<xsl:otherwise>
																<fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;
																</fo:inline>
															</xsl:otherwise>
														</xsl:choose>
													</fo:block>
												</fo:block-container>
												<fo:block-container background-color="transparent"
													border-style="none" position="absolute" left="10.36px"
													hyphenate="true" language="en" keep-together="always" 
													height="28.33px" width="15.30px">
													<fo:block background-color="transparent" color="#000000"
														font-size="11pt" font-style="normal" font-family="ZapfDingbats"
														font-weight="normal">
														<xsl:for-each
																select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
																<xsl:for-each
																select="PHS398_CareerDevelopmentAwardSup_3_0:isNonUSCitizenship">
																<xsl:choose>
																<xsl:when
																test="string(.)='Temporary U.S. Visa'">
																<xsl:call-template name="checkbox">
																	<xsl:with-param name="value"
																					select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0/PHS398_CareerDevelopmentAwardSup_3_0:isNonUSCitizenship" />
																	<xsl:with-param name="schemaChoice">Temporary U.S. Visa</xsl:with-param>
																</xsl:call-template>
																</xsl:when>
															<xsl:otherwise>
																	<xsl:call-template name="checkboxN">
																	<xsl:with-param name="value"
																					select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0/PHS398_CareerDevelopmentAwardSup_3_0:isNonUSCitizenship" />
																	<xsl:with-param name="schemaChoice">N: No</xsl:with-param>
																</xsl:call-template>
																</xsl:otherwise> 
																		</xsl:choose>
																</xsl:for-each>
																</xsl:for-each>
														</fo:block>
												</fo:block-container>
												
												<fo:block-container background-color="transparent"
													border-style="none" position="absolute" left="20.36px"
													hyphenate="true" language="en" keep-together="always" 
													height="28.33px" width="15.30px">
												<fo:block font-size="8pt">
												<fo:inline font-family="arialuni" font-size="9px">
												<xsl:text>&#160;With a Temporary U.S. Visa</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:block-container>
												</fo:block>

												</fo:table-cell>
												</fo:table-row>
												
												<fo:table-row font-size="8pt">
												<fo:table-cell number-columns-spanned="4"
												padding="2pt" display-align="before">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												</fo:block>

												<fo:block>
												<!-- Below block is to get checkbox image -->
												<fo:block-container background-color="transparent"
													border-style="none" position="absolute" left="10.36px"
													hyphenate="true" language="en" keep-together="always"
													height="18.33px" width="15.30px">
													<fo:block background-color="transparent" color="#000000"
														font-size="10pt" font-style="normal" font-family="ZapfDingbats"
														font-weight="normal">
														<xsl:choose>
															<xsl:when test="boolean(0)">
																<fo:inline color="#FFFFFF">&#160;</fo:inline>
															</xsl:when>
															<xsl:otherwise>
																<fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;
																</fo:inline>
															</xsl:otherwise>
														</xsl:choose>
													</fo:block>
												</fo:block-container>
												<fo:block-container background-color="transparent"
													border-style="none" position="absolute" left="10.36px"
													hyphenate="true" language="en" keep-together="always" 
													height="18.33px" width="15.30px">
													<fo:block background-color="transparent" color="#000000"
														font-size="10pt" font-style="normal" font-family="ZapfDingbats"
														font-weight="normal">
														<xsl:for-each
																select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
																<xsl:for-each
																select="PHS398_CareerDevelopmentAwardSup_3_0:isNonUSCitizenship">
																<xsl:choose>
																<xsl:when
																test="string(.)='Not Residing in the U.S.'">
																<xsl:call-template name="checkbox">
																	<xsl:with-param name="value"
																					select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0/PHS398_CareerDevelopmentAwardSup_3_0:isNonUSCitizenship" />
																	<xsl:with-param name="schemaChoice">Not Residing in the U.S.</xsl:with-param>
																</xsl:call-template>
																</xsl:when>
																<xsl:otherwise>
																	<xsl:call-template name="checkboxN">
																	<xsl:with-param name="value"
																					select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0/PHS398_CareerDevelopmentAwardSup_3_0:isNonUSCitizenship" />
																	<xsl:with-param name="schemaChoice">N: No</xsl:with-param>
																</xsl:call-template>
																</xsl:otherwise> 
																</xsl:choose>
																</xsl:for-each>
																</xsl:for-each>
														</fo:block>
												</fo:block-container>
												
												<fo:block-container background-color="transparent"
													border-style="none" position="absolute" left="20.36px"
													hyphenate="true" language="en" keep-together="always" 
													height="18.33px" width="15.30px">
												<fo:block>
												<fo:inline font-family="arialuni" font-size="9px">
												<xsl:text>&#160;Not Residing in the U.S.</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:block-container>
												</fo:block>
		
												</fo:table-cell>
												</fo:table-row>
												
												<fo:table-row space-before="3mm">
												<fo:table-cell number-columns-spanned="4"
												padding="2pt" display-align="center">
												<fo:block>
												<fo:inline font-size="9px">
												<xsl:text>If with a temporary U.S. visa who has applied for permanent resident status and expect to hold a permanent resident</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												</fo:table-row>
												
												<fo:table-row font-size="8pt"> 
												<fo:table-cell number-columns-spanned="4"
												padding="2pt" display-align="before" margin-bottom="0.9in"> 
												<fo:block> 
												<fo:inline font-size="9px">
												<xsl:text>visa by the earliest possible start date of the award, also check here:</xsl:text>
												</fo:inline>
													
												<!-- Below block is to get checkbox image -->
												<fo:block-container background-color="transparent"
													border-style="none" position="absolute" left="306.363636363636363px"
													hyphenate="true" language="en" keep-together="always"
													height="18.333333333333334px" width="15.303030303030303px">
													<fo:block background-color="transparent" color="#000000"
														font-size="8pt" font-style="normal" font-family="ZapfDingbats"
														font-weight="normal">
														<xsl:choose>
															<xsl:when test="boolean(0)">
																<fo:inline color="#FFFFFF">&#160;</fo:inline>
															</xsl:when>
															<xsl:otherwise>
																<fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;
																</fo:inline>
															</xsl:otherwise>
														</xsl:choose>
													</fo:block>
												</fo:block-container>
												<fo:block-container background-color="transparent"
													border-style="none" position="absolute" left="306.363636363636363px"
													hyphenate="true" language="en" keep-together="always" 
													height="18.333333333333334px" width="15.303030303030303px">
													<fo:block background-color="transparent" color="#000000"
														font-size="8pt" font-style="normal" font-family="ZapfDingbats"
														font-weight="normal">
														<xsl:for-each
																select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0">
																<xsl:for-each
																select="PHS398_CareerDevelopmentAwardSup_3_0:PermanentResidentByAwardIndicator">
																<xsl:choose>
																<xsl:when
																test="not(//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0/PHS398_CareerDevelopmentAwardSup_3_0:PermanentResidentByAwardIndicator) or //PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0/PHS398_CareerDevelopmentAwardSup_3_0:PermanentResidentByAwardIndicator = ''">
																	<fo:inline color="#FFFFFF">&#160;</fo:inline>
																</xsl:when>
																<xsl:otherwise>
																	<xsl:call-template name="checkbox">
																	<xsl:with-param name="value"
																					select="//PHS398_CareerDevelopmentAwardSup_3_0:PHS398_CareerDevelopmentAwardSup_3_0/PHS398_CareerDevelopmentAwardSup_3_0:PermanentResidentByAwardIndicator" />
																	<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
																</xsl:call-template>
																</xsl:otherwise>
																		</xsl:choose>
																</xsl:for-each>
																</xsl:for-each>
														</fo:block>
												</fo:block-container>
												<!-- end -->
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
						</xsl:for-each>
					</fo:block>
					<fo:block id="SV_RefID_PageTotal"/>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	<xsl:template name="headerall">
		<fo:static-content flow-name="xsl-region-before">
			<fo:block>
				<fo:inline-container>
					<fo:block>
						<xsl:text>&#x2029;</xsl:text>
					</fo:block>
				</fo:inline-container>
				<fo:table font-family="arialuni" table-layout="fixed" width="100%"
					border-spacing="2pt">
					<fo:table-column column-width="proportional-column-width(1)"/>
					<fo:table-column column-width="proportional-column-width(1)"/>
					<fo:table-body start-indent="0pt">
						<fo:table-row>
							<fo:table-cell padding="0" number-columns-spanned="2" height="20"
								display-align="center">
								<fo:block/>
							</fo:table-cell>
						</fo:table-row>
						<fo:table-row>
							<fo:table-cell padding="0"
								number-columns-spanned="2" text-align="center" display-align="center">
								<fo:block>
									<fo:inline font-weight="bold" font-size="12px">
										<xsl:text>PHS 398 Career Development Award Supplemental Form</xsl:text>
									</fo:inline>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>

						<fo:table-row>
							<fo:table-cell padding="0" number-columns-spanned="2" text-align="right"
								display-align="center">
								<fo:block font-size="6px">
									<fo:inline>
										<xsl:text>OMB Number: 0925-0001</xsl:text>
									</fo:inline>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
					    <fo:table-row>
                        <fo:table-cell font-size="smaller" padding="0" number-columns-spanned="2" text-align="right" display-align="center">
                                <fo:block>
                                    <fo:inline font-family="arialuni" font-size="6px">
                                        <xsl:text>Expiration Date: 10/31/2018</xsl:text>
                                    </fo:inline>
                                </fo:block>
                        </fo:table-cell>
                        </fo:table-row>
						<fo:table-row>
                            <fo:table-cell padding="0" number-columns-spanned="2" height="30" display-align="center">
                                <fo:block>
                                    <fo:inline>
                                        <xsl:text>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;</xsl:text>
                                    </fo:inline>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
					</fo:table-body>
				</fo:table>
			</fo:block>
		</fo:static-content>
	</xsl:template>
	<xsl:template name="double-backslash">
		<xsl:param name="text"/>
		<xsl:param name="text-length"/>
		<xsl:variable name="text-after-bs" select="substring-after($text, '\')"/>
		<xsl:variable name="text-after-bs-length" select="string-length($text-after-bs)"/>
		<xsl:choose>
			<xsl:when test="$text-after-bs-length = 0">
				<xsl:choose>
					<xsl:when test="substring($text, $text-length) = '\'">
						<xsl:value-of select="concat(substring($text,1,$text-length - 1), '\\')"/>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="$text"/>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of
					select="concat(substring($text,1,$text-length - $text-after-bs-length - 1), '\\')"/>
				<xsl:call-template name="double-backslash">
					<xsl:with-param name="text" select="$text-after-bs"/>
					<xsl:with-param name="text-length" select="$text-after-bs-length"/>
				</xsl:call-template>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="checkbox">
		<xsl:param name="value" />
		<xsl:param name="schemaChoice">Y: Yes</xsl:param>
		<xsl:if test="normalize-space($value) = normalize-space($schemaChoice)">
			<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format"
				xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
				xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
				xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats"
				font-size="8pt">&#x2714;</fo:inline>
		</xsl:if>
	</xsl:template>
		<xsl:template name="checkboxN">
		<xsl:param name="value" />
		<xsl:param name="schemaChoice">N: No</xsl:param>
		<xsl:if test="normalize-space($value) = normalize-space($schemaChoice)">
			<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format"
				xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema"
				xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
				xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats"
				font-size="8pt">&#x2714;</fo:inline>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>
