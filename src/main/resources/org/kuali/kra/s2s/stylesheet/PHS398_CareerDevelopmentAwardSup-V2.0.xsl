<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:PHS398_CareerDevelopmentAwardSup_2_0="http://apply.grants.gov/forms/PHS398_CareerDevelopmentAwardSup_2_0-V2.0"
	xmlns:att="http://apply.grants.gov/system/Attachments-V1.0"
	xmlns:codes="http://apply.grants.gov/system/UniversalCodes-V2.0"
	xmlns:fn="http://www.w3.org/2005/xpath-functions"
	xmlns:glob="http://apply.grants.gov/system/Global-V1.0"
	xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
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
			</fo:simple-page-master>
		</fo:layout-master-set>
	</xsl:variable>
	<xsl:template match="/">
		<fo:root>
			<xsl:copy-of select="$fo:layout-master-set"/>
			<fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
				<xsl:call-template name="headerall"/>
				<fo:flow flow-name="xsl-region-body">
					<fo:block>
						<xsl:for-each select="$XML">
							<fo:inline-container>
								<fo:block>
									<xsl:text>&#x2029;</xsl:text>
								</fo:block>
							</fo:inline-container>
							<fo:table font-family="Helvetica" font-size="9px" table-layout="fixed"
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
												<fo:block font-family="Helvetica">
												<fo:inline font-size="10px" font-weight="bold">
												<xsl:text>Introduction (if applicable)</xsl:text>
												</fo:inline>
												<fo:block/>
												<fo:inline>
												<xsl:text>1. Introduction to Application</xsl:text>
												</fo:inline>
												<fo:block/>
												<fo:inline font-size="8px">
												<xsl:text>(for RESUBMISSION applications only)</xsl:text>
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
												<fo:table font-family="Georgia" font-size="9px"
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
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:IntroductionToApplication">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:attFile">
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
												<fo:inline font-size="10px" font-weight="bold">
												<xsl:text>Candidate Information</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												</fo:table-row>
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>2. Candidate&apos;s Background</xsl:text>
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
												<fo:table font-family="Georgia" font-size="9px"
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
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CandidateBackground">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:attFile">
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
												<fo:inline>
												<xsl:text>3. Career Goals and Objectives</xsl:text>
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
												<fo:table font-family="Georgia" font-size="9px"
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
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerGoalsAndObjectives">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:attFile">
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
												<fo:inline>
												<xsl:text>4. Career Development/Training</xsl:text>
												</fo:inline>
												<fo:block/>
												<fo:inline>
												<xsl:text>Activities During Award Period</xsl:text>
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
												<fo:table font-family="Georgia" font-size="9px"
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
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAndTrainingActivities">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:attFile">
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
												<fo:inline>
												<xsl:text>5. Training in the Responsible Conduct of Research</xsl:text>
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
												<fo:table font-family="Georgia" font-size="9px"
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
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:ResponsibleConductOfResearch">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:attFile">
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
												<fo:inline>
												<xsl:text>6.Candidate&apos;s Plan to Provide Mentoring</xsl:text>
												</fo:inline>
												<fo:block/>
												<fo:inline font-size="8px">
												<xsl:text>(as applicable)</xsl:text>
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
												<fo:table font-family="Georgia" font-size="9px"
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
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:MentoringPlan">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:attFile">
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
												<fo:inline font-size="10px" font-weight="bold">
												<xsl:text>Statements of Support</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												</fo:table-row>
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>7. Plans and Statements of Mentor and</xsl:text>
												</fo:inline>
												<fo:block/>
												<fo:inline>
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
												<fo:table font-family="Georgia" font-size="9px"
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
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:StatementsOfSupport">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:attFile">
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
												<fo:inline>
												<xsl:text>8. Letters of Support from Collaborators,</xsl:text>
												</fo:inline>
												<fo:block/>
												<fo:inline>
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
												<fo:table font-family="Verdana" font-size="9pt"
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
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:StatementsOfSupport">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:attFile">
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
												<fo:inline font-size="10px" font-weight="bold">
												<xsl:text>Environment and Institutional Commitment to Candidate</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												</fo:table-row>
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>9. Description of Institutional Environment</xsl:text>
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
												<fo:table font-family="Georgia" font-size="9px"
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
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:InsitutionalEnvironment">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:attFile">
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
												<fo:inline>
												<xsl:text>10. Institutional Commitment to Candidate&apos;s</xsl:text>
												</fo:inline>
												<fo:block/>
												<fo:inline>
												<xsl:text>Research Career Development</xsl:text>
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
												<fo:table font-family="Georgia" font-size="9px"
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
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:InstitutionalCommitment">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:attFile">
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
												<fo:inline font-size="10px" font-weight="bold">
												<xsl:text>Research Plan</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												</fo:table-row>
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>11. Specific Aims</xsl:text>
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
												<fo:table font-family="Georgia" font-size="9px"
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
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:SpecificAims">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:attFile">
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
												<fo:inline>
												<xsl:text>12. Research Strategy*</xsl:text>
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
												<fo:table font-family="Georgia" font-size="9px"
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
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:ResearchStrategy">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:attFile">
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
												<fo:inline>
												<xsl:text>13. Progress Report Publication List</xsl:text>
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
												<fo:table font-family="Georgia" font-size="9px"
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
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:ProgressReportPublicationList">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:attFile">
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
												<fo:inline font-size="10px" font-weight="bold">
												<xsl:text>Human Subject Sections</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												</fo:table-row>
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>14. Protection of Human Subjects</xsl:text>
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
												<fo:table font-family="Georgia" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<fo:table-row>
												<fo:table-cell padding="1pt" height="20"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>&#160;</xsl:text>
												</fo:inline>
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:ProtectionOfHumanSubjects">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:attFile">
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
												<fo:inline>
												<xsl:text>15. Inclusion of Women and Minorities</xsl:text>
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
												<fo:table font-family="Georgia" font-size="9px"
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
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:InclusionOfWomenAndMinorities">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:attFile">
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
												<fo:inline>
												<xsl:text>16. Inclusion of Children</xsl:text>
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
												<fo:table font-family="Georgia" font-size="9px"
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
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:InclusionOfChildren">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:attFile">
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
												<fo:inline font-size="10px" font-weight="bold">
												<xsl:text>Other Research Plan Sections</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												</fo:table-row>
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="center">
												<fo:block>
												<fo:inline>
												<xsl:text>17. Vertebrate Animals</xsl:text>
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
												<fo:table font-family="Georgia" font-size="9px"
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
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:VertebrateAnimals">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:attFile">
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
												<fo:inline>
												<xsl:text>18. Select Agent Research</xsl:text>
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
												<fo:table font-family="Georgia" font-size="9px"
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
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:SelectAgentResearch">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:attFile">
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
												<fo:inline>
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
												<fo:table font-family="Georgia" font-size="9px"
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
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:ConsortiumContractualArrangements">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:attFile">
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
												<fo:inline>
												<xsl:text>20. Resource Sharing Plan(s)</xsl:text>
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
												<fo:table font-family="Georgia" font-size="9px"
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
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:ResourceSharingPlans">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:attFile">
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
												<fo:inline font-size="10px" font-weight="bold">
												<xsl:text>Appendix (if applicable)</xsl:text>
												</fo:inline>
												</fo:block>
												</fo:table-cell>
												</fo:table-row>
												<fo:table-row>
												<fo:table-cell padding="1pt"
												display-align="before">
												<fo:block>
												<fo:inline>
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
												test="PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0/PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments/PHS398_CareerDevelopmentAwardSup_2_0:Appendix/att:AttachedFile">
												<fo:table font-family="Georgia" font-size="9px"
												table-layout="fixed" width="100%">
												<fo:table-column
												column-width="proportional-column-width(1)"/>
												<fo:table-body start-indent="0pt">
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:CareerDevelopmentAwardAttachments">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:Appendix">
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
												<fo:inline font-weight="bold" font-size="10px">
												<xsl:text>Citizenship*:</xsl:text>
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
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:Citizenship">
												<xsl:choose>
												<xsl:when
												test="string(.)='U.S. Citizen or noncitizen national'">
												<fo:inline font-family="ZapfDingbats"
												font-size="9px">&#x25cf;</fo:inline>
												</xsl:when>
												<xsl:otherwise>
												<fo:inline>
												<fo:leader leader-length="10pt"
												leader-pattern="space"/>
												</fo:inline>
												</xsl:otherwise>
												</xsl:choose>
												</xsl:for-each>
												</xsl:for-each>
												<fo:inline>
												<xsl:text>&#160;&#160;&#160; U.S. Citizen or noncitizen national </xsl:text>
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
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:Citizenship">
												<xsl:choose>
												<xsl:when
												test="string(.)='Non-U.S. Citizen with temporary visa'">
												<fo:inline font-family="ZapfDingbats"
												font-size="9px">&#x25cf;</fo:inline>
												</xsl:when>
												<xsl:otherwise>
												<fo:inline>
												<fo:leader leader-length="10pt"
												leader-pattern="space"/>
												</fo:inline>
												</xsl:otherwise>
												</xsl:choose>
												</xsl:for-each>
												</xsl:for-each>
												<fo:inline>
												<xsl:text>&#160;&#160;&#160; Non-U.S. Citizen with temporary U.S. visa</xsl:text>
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
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:Citizenship">
												<xsl:choose>
												<xsl:when
												test="string(.)='Permanent Resident of U.S.'">
												<fo:inline font-family="ZapfDingbats"
												font-size="9px">&#x25cf;</fo:inline>
												</xsl:when>
												<xsl:otherwise>
												<fo:inline>
												<fo:leader leader-length="10pt"
												leader-pattern="space"/>
												</fo:inline>
												</xsl:otherwise>
												</xsl:choose>
												</xsl:for-each>
												</xsl:for-each>
												<fo:inline>
												<xsl:text>&#160;&#160;&#160; Permanent Resident of U.S. (If a permanent resident of the U.S., a notarized statement must be provided by the time of award)</xsl:text>
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
												<xsl:for-each
												select="//PHS398_CareerDevelopmentAwardSup_2_0:PHS398_CareerDevelopmentAwardSup_2_0">
												<xsl:for-each
												select="PHS398_CareerDevelopmentAwardSup_2_0:Citizenship">
												<xsl:choose>
												<xsl:when
												test="string(.)='Permanent Resident of U.S. Pending'">
												<fo:inline font-family="ZapfDingbats"
												font-size="9px">&#x25cf;</fo:inline>
												</xsl:when>
												<xsl:otherwise>
												<fo:inline>
												<fo:leader leader-length="10pt"
												leader-pattern="space"/>
												</fo:inline>
												</xsl:otherwise>
												</xsl:choose>
												</xsl:for-each>
												</xsl:for-each>
												<fo:inline>
												<xsl:text>&#160;&#160;&#160; Permanent Resident of U.S.  Pending</xsl:text>
												</fo:inline>
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
				<fo:table font-family="Helvetica" table-layout="fixed" width="100%"
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
							<fo:table-cell padding="0"
								number-columns-spanned="2" text-align="left" display-align="center">
								<fo:block>
									<fo:inline font-size="8px" color="white">
										<xsl:text>Please attach applicable sections, below</xsl:text>
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
</xsl:stylesheet>
