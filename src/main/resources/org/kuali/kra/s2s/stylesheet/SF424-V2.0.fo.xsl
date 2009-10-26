<?xml version="1.0" encoding="UTF-8"?>
<!-- $Revision:   1.11  $ -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:SF424="http://apply.grants.gov/forms/SF424-V2.0">
	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="/">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:header="http://apply.grants.gov/system/Header-V1.0" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.2in" margin-right="0.2in">
					<fo:region-body margin-top="0.2in" margin-bottom="0.4in"/>
					<fo:region-after extent="0.4in"/>
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="default-page" format="1" initial-page-number="1">
				<fo:static-content flow-name="xsl-region-after">
					<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="center" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Funding Opportunity Number: <xsl:value-of select="/*/*/header:OpportunityID"/>
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Received Date: <xsl:value-of select="translate(/*/*/footer:ReceivedDateTime, 'T', ' ')"/> Time Zone: GMT-5
                              </fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<!--Data components-->
					<!--Block below is for the field named SubmissionTypeRadioRequiredField with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="15.151515151515152px" hyphenate="true" language="en" keep-together="always" top="59.3939393939394px" height="15.757575757575758px" width="96.36363636363637px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"/>
					</fo:block-container>
					<!--Block below is for the radio named SubmissionType_Preapplication with FieldID 1-1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="76.96969696969697px" height="13.333333333333334px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SF424:SF424/SF424:SubmissionType"/>
										<xsl:with-param name="schemaChoice">Preapplication</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the radio named SubmissionType_Application with FieldID 1-2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="93.33333333333334px" height="13.333333333333334px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SF424:SF424/SF424:SubmissionType"/>
										<xsl:with-param name="schemaChoice">Application</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the radio named SubmissionType_ChangedApplication with FieldID 1-3-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="109.6969696969697px" height="13.333333333333334px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SF424:SF424/SF424:SubmissionType"/>
										<xsl:with-param name="schemaChoice">Changed/Corrected Application</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named FederalEntityIdentifier with FieldID 5-A-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="183.63636363636365px" height="13.333333333333334px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:FederalEntityIdentifier) or //SF424:SF424/SF424:FederalEntityIdentifier = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:FederalEntityIdentifier"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named EmployerTaxpayerIdentificationNumber with FieldID 8-b-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="303.03030303030306px" height="13.333333333333334px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:EmployerTaxpayerIdentificationNumber) or //SF424:SF424/SF424:EmployerTaxpayerIdentificationNumber = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:EmployerTaxpayerIdentificationNumber"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named DepartmentName with FieldID 8-e1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="516.3636363636364px" height="13.333333333333334px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:DepartmentName) or //SF424:SF424/SF424:DepartmentName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:DepartmentName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named OrganizationAffiliation with FieldID 8-f7-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="664.2424242424242px" height="13.333333333333334px" width="478.7878787878788px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:OrganizationAffiliation) or //SF424:SF424/SF424:OrganizationAffiliation = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:OrganizationAffiliation"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named Title with FieldID 8-f6-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="41.81818181818182px" hyphenate="true" language="en" keep-together="always" top="627.2727272727273px" height="13.333333333333334px" width="357.5757575757576px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:Title) or //SF424:SF424/SF424:Title = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:Title"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named Email with FieldID 8-f10-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="55.151515151515156px" hyphenate="true" language="en" keep-together="always" top="705.4545454545455px" height="13.333333333333334px" width="478.7878787878788px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:Email) or //SF424:SF424/SF424:Email = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:Email"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the combobox named Prefix with FieldID 8-f1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="71.51515151515152px" hyphenate="true" language="en" keep-together="always" top="557.5757575757576px" height="13.333333333333334px" width="86.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:ContactPerson/globLib:PrefixName) or //SF424:SF424/SF424:ContactPerson/globLib:PrefixName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:ContactPerson/globLib:PrefixName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named MiddleName with FieldID 8-f3-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="71.51515151515152px" hyphenate="true" language="en" keep-together="always" top="573.939393939394px" height="13.333333333333334px" width="202.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:ContactPerson/globLib:MiddleName) or //SF424:SF424/SF424:ContactPerson/globLib:MiddleName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:ContactPerson/globLib:MiddleName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named LastName with FieldID 8-f4-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="71.51515151515152px" hyphenate="true" language="en" keep-together="always" top="590.3030303030304px" height="13.333333333333334px" width="478.7878787878788px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:ContactPerson/globLib:LastName) or //SF424:SF424/SF424:ContactPerson/globLib:LastName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:ContactPerson/globLib:LastName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the combobox named Suffix with FieldID 8-f5-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="71.51515151515152px" hyphenate="true" language="en" keep-together="always" top="606.6666666666667px" height="13.333333333333334px" width="86.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:ContactPerson/globLib:SuffixName) or //SF424:SF424/SF424:ContactPerson/globLib:SuffixName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:ContactPerson/globLib:SuffixName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named OrganizationName with FieldID 8-a-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="81.81818181818183px" hyphenate="true" language="en" keep-together="always" top="266.06060606060606px" height="13.333333333333334px" width="478.7878787878788px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:OrganizationName) or //SF424:SF424/SF424:OrganizationName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:OrganizationName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named Street1 with FieldID 8-d1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="92.12121212121212px" hyphenate="true" language="en" keep-together="always" top="344.24242424242425px" height="13.333333333333334px" width="440px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:Applicant/globLib:Street1) or //SF424:SF424/SF424:Applicant/globLib:Street1 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:Applicant/globLib:Street1"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named Street2 with FieldID 8-d2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="92.12121212121212px" hyphenate="true" language="en" keep-together="always" top="360.6060606060606px" height="13.333333333333334px" width="440px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:Applicant/globLib:Street2) or //SF424:SF424/SF424:Applicant/globLib:Street2 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:Applicant/globLib:Street2"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named City with FieldID 8-d3-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="92.12121212121212px" hyphenate="true" language="en" keep-together="always" top="376.969696969697px" height="13.333333333333334px" width="280px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:Applicant/globLib:City) or //SF424:SF424/SF424:Applicant/globLib:City = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:Applicant/globLib:City"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named County with FieldID 8-d4-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="92.12121212121212px" hyphenate="true" language="en" keep-together="always" top="393.33333333333337px" height="13.333333333333334px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:Applicant/globLib:County) or //SF424:SF424/SF424:Applicant/globLib:County = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:Applicant/globLib:County"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the popup named State with FieldID 8-d5-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="92.12121212121212px" hyphenate="true" language="en" keep-together="always" top="409.69696969696975px" height="13.333333333333334px" width="440px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:Applicant/globLib:State) or //SF424:SF424/SF424:Applicant/globLib:State = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:Applicant/globLib:State"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named Province with FieldID 8-d6-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="92.12121212121212px" hyphenate="true" language="en" keep-together="always" top="426.06060606060606px" height="13.333333333333334px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:Applicant/globLib:Province) or //SF424:SF424/SF424:Applicant/globLib:Province = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:Applicant/globLib:Province"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the popup named Country with FieldID 8-d7-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="92.12121212121212px" hyphenate="true" language="en" keep-together="always" top="442.42424242424244px" height="13.333333333333334px" width="391.51515151515156px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:Applicant/globLib:Country) or //SF424:SF424/SF424:Applicant/globLib:Country = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:Applicant/globLib:Country"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ZipCode with FieldID 8-d8-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="92.12121212121212px" hyphenate="true" language="en" keep-together="always" top="458.7878787878788px" height="13.333333333333334px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:Applicant/globLib:ZipPostalCode) or //SF424:SF424/SF424:Applicant/globLib:ZipPostalCode = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:Applicant/globLib:ZipPostalCode"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named PhoneNumber with FieldID 8-f8-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="96.96969696969697px" hyphenate="true" language="en" keep-together="always" top="684.8484848484849px" height="13.333333333333334px" width="202.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:PhoneNumber) or //SF424:SF424/SF424:PhoneNumber = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:PhoneNumber"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named StateReceiveDate with FieldID 6-1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="129.69696969696972px" hyphenate="true" language="en" keep-together="always" top="224.84848484848487px" height="13.333333333333334px" width="52.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:StateReceiveDate) or //SF424:SF424/SF424:StateReceiveDate = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//SF424:SF424/SF424:StateReceiveDate"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ApplicationTypeRadioRequiredField with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="155.75757575757578px" hyphenate="true" language="en" keep-together="always" top="59.3939393939394px" height="16.363636363636363px" width="90.90909090909092px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"/>
					</fo:block-container>
					<!--Block below is for the radio named ApplicationType_New with FieldID 2-1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="156.96969696969697px" hyphenate="true" language="en" keep-together="always" top="76.96969696969697px" height="13.333333333333334px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SF424:SF424/SF424:ApplicationType"/>
										<xsl:with-param name="schemaChoice">New</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the radio named ApplicationType_Continuation with FieldID 2-2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="156.96969696969697px" hyphenate="true" language="en" keep-together="always" top="93.33333333333334px" height="13.333333333333334px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SF424:SF424/SF424:ApplicationType"/>
										<xsl:with-param name="schemaChoice">Continuation</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the radio named ApplicationType_Revision with FieldID 2-3-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="156.96969696969697px" hyphenate="true" language="en" keep-together="always" top="109.6969696969697px" height="13.333333333333334px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SF424:SF424/SF424:ApplicationType"/>
										<xsl:with-param name="schemaChoice">Revision</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ApplicantID with FieldID 4-0-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="156.96969696969697px" hyphenate="true" language="en" keep-together="always" top="146.66666666666669px" height="13.333333333333334px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:ApplicantID) or //SF424:SF424/SF424:ApplicantID = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:ApplicantID"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the popup named RevisionType with FieldID 2-10-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="253.93939393939397px" hyphenate="true" language="en" keep-together="always" top="76.36363636363637px" height="13.333333333333334px" width="156.96969696969697px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:RevisionType) or //SF424:SF424/SF424:RevisionType = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:RevisionType"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named RevisionOtherSpecify with FieldID 2-12-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="253.93939393939397px" hyphenate="true" language="en" keep-together="always" top="108.48484848484848px" height="13.333333333333334px" width="173.33333333333334px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:RevisionOtherSpecify) or //SF424:SF424/SF424:RevisionOtherSpecify = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:RevisionOtherSpecify"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named FederalAwardIdentifier with FieldID 5-B-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="263.6363636363636px" hyphenate="true" language="en" keep-together="always" top="183.63636363636365px" height="13.333333333333334px" width="202.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:FederalAwardIdentifier) or //SF424:SF424/SF424:FederalAwardIdentifier = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:FederalAwardIdentifier"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named DUNSNumber with FieldID 8-c-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="263.6363636363636px" hyphenate="true" language="en" keep-together="always" top="303.03030303030306px" height="13.333333333333334px" width="110.30303030303031px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:DUNSNumber) or //SF424:SF424/SF424:DUNSNumber = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:DUNSNumber"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named DivisionName with FieldID 8-e2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="263.6363636363636px" hyphenate="true" language="en" keep-together="always" top="516.3636363636364px" height="13.333333333333334px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:DivisionName) or //SF424:SF424/SF424:DivisionName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:DivisionName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named FirstName with FieldID 8-f2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="270.3030303030303px" hyphenate="true" language="en" keep-together="always" top="557.5757575757576px" height="13.333333333333334px" width="280px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:ContactPerson/globLib:FirstName) or //SF424:SF424/SF424:ContactPerson/globLib:FirstName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:ContactPerson/globLib:FirstName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named StateApplicationID with FieldID 7-0-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="297.57575757575756px" hyphenate="true" language="en" keep-together="always" top="224.84848484848487px" height="13.333333333333334px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:StateApplicationID) or //SF424:SF424/SF424:StateApplicationID = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:StateApplicationID"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named Fax with FieldID 8-f9-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="357.5757575757576px" hyphenate="true" language="en" keep-together="always" top="684.8484848484849px" height="13.333333333333334px" width="202.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:Fax) or //SF424:SF424/SF424:Fax = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:Fax"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Write labels-->
					<!--Block below is for the label named -->
					<!--Block below is for the label named d1e1647-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="37.57575757575758px" height="15.757575757575758px" width="276.3636363636364px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">Application for Federal Assistance SF-424</fo:block>
					</fo:block-container>
					<!--Block below is for the label named SubmissionType_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="60.60606060606061px" height="13.333333333333334px" width="93.33333333333334px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* 1. Type of Submission:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL4-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="130.3030303030303px" height="13.93939393939394px" width="76.96969696969697px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* 3. Date Received:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named DateReceived-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="147.27272727272728px" height="11.515151515151516px" width="127.27272727272728px">
						<fo:block background-color="transparent" color="#000000" font-size="6pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:DateReceived) or //SF424:SF424/SF424:DateReceived = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//SF424:SF424/SF424:DateReceived"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the label named FederalEntityIdentifier_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="167.27272727272728px" height="13.333333333333334px" width="101.81818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">5a. Federal Entity Identifier:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d1e4295-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="204.24242424242425px" height="13.333333333333334px" width="76.36363636363637px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">State Use Only:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named StateReceiveDate_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="224.84848484848487px" height="13.333333333333334px" width="110.90909090909092px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">6. Date Received by State:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d1e4640-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="245.45454545454547px" height="13.333333333333334px" width="124.84848484848486px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">8. APPLICANT INFORMATION:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named OrganizationName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="266.06060606060606px" height="13.333333333333334px" width="63.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* a. Legal Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named EmployerTaxpayerIdentificationNumber_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="286.6666666666667px" height="13.333333333333334px" width="250.3939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* b. Employer/Taxpayer Identification Number (EIN/TIN):</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d1e5100-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="323.6363636363636px" height="13.333333333333334px" width="51.515151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">d. Address:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named Street1_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="344.24242424242425px" height="13.333333333333334px" width="39.3939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Street1:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named City_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="376.969696969697px" height="13.333333333333334px" width="26.666666666666668px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* City:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named State_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="409.69696969696975px" height="13.333333333333334px" width="40px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* State:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named Country_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="442.42424242424244px" height="13.333333333333334px" width="40.60606060606061px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Country:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ZipCode_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="458.7878787878788px" height="13.333333333333334px" width="73.93939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Zip / Postal Code:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d1e6250-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="479.39393939393943px" height="13.333333333333334px" width="97.57575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">e. Organizational Unit:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named DepartmentName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="500px" height="13.333333333333334px" width="72.12121212121212px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Department Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d1e6595-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="536.969696969697px" height="13.333333333333334px" width="376.969696969697px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">f. Name and contact information of person to be contacted on matters involving this application:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named Prefix_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="557.5757575757576px" height="13.333333333333334px" width="28.484848484848488px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Prefix:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named MiddleName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="573.939393939394px" height="13.333333333333334px" width="52.72727272727273px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Middle Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LastName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="590.3030303030304px" height="13.333333333333334px" width="50.909090909090914px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Last Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named Suffix_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="606.6666666666667px" height="13.333333333333334px" width="29.6969696969697px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Suffix:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named Title_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="627.2727272727273px" height="13.333333333333334px" width="23.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Title:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named OrganizationAffiliation_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="647.8787878787879px" height="13.333333333333334px" width="92.72727272727273px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Organizational Affiliation:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named PhoneNumber_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="684.8484848484849px" height="13.333333333333334px" width="78.18181818181819px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Telephone Number:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named Email_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="705.4545454545455px" height="13.333333333333334px" width="36.36363636363637px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Email:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named Street2_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" keep-together="always" top="360.6060606060606px" height="13.333333333333334px" width="37.57575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Street2:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named County_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" keep-together="always" top="393.33333333333337px" height="13.333333333333334px" width="32.72727272727273px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">County:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named Province_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.81818181818182px" hyphenate="true" language="en" keep-together="always" top="426.06060606060606px" height="13.333333333333334px" width="37.57575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Province:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named SubmissionType_Preapplication_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="27.272727272727273px" hyphenate="true" language="en" keep-together="always" top="76.96969696969697px" height="13.333333333333334px" width="54.54545454545455px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Preapplication</fo:block>
					</fo:block-container>
					<!--Block below is for the label named SubmissionType_Application_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="27.272727272727273px" hyphenate="true" language="en" keep-together="always" top="93.33333333333334px" height="13.333333333333334px" width="48.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Application</fo:block>
					</fo:block-container>
					<!--Block below is for the label named SubmissionType_ChangedApplication_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="27.272727272727273px" hyphenate="true" language="en" keep-together="always" top="109.6969696969697px" height="13.333333333333334px" width="118.18181818181819px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Changed/Corrected Application</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicationType_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="156.96969696969697px" hyphenate="true" language="en" keep-together="always" top="60.60606060606061px" height="13.333333333333334px" width="87.27272727272728px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* 2. Type of Application:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicantID_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="156.96969696969697px" hyphenate="true" language="en" keep-together="always" top="130.3030303030303px" height="13.333333333333334px" width="81.81818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">4. Applicant Identifier:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicationType_New_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="167.87878787878788px" hyphenate="true" language="en" keep-together="always" top="76.96969696969697px" height="13.333333333333334px" width="23.636363636363637px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">New</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicationType_Continuation_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="167.87878787878788px" hyphenate="true" language="en" keep-together="always" top="93.33333333333334px" height="13.333333333333334px" width="54.54545454545455px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Continuation</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicationType_Revision_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="167.87878787878788px" hyphenate="true" language="en" keep-together="always" top="109.6969696969697px" height="13.333333333333334px" width="37.57575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Revision</fo:block>
					</fo:block-container>
					<!--Block below is for the label named StateApplicationID_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="187.87878787878788px" hyphenate="true" language="en" keep-together="always" top="224.84848484848487px" height="13.333333333333334px" width="107.27272727272728px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">7. State Application Identifier:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named FirstName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="214.54545454545456px" hyphenate="true" language="en" keep-together="always" top="557.5757575757576px" height="13.333333333333334px" width="52.72727272727273px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* First Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d1e2800-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="253.93939393939397px" hyphenate="true" language="en" keep-together="always" top="60.60606060606061px" height="12.727272727272728px" width="144.24242424242425px">
						<fo:block background-color="transparent" color="#000000" font-size="7pt" font-style="normal" font-family="Helvetica" font-weight="normal">* If Revision, select appropriate letter(s):</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL5-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="253.93939393939397px" hyphenate="true" language="en" keep-together="always" top="92.72727272727273px" height="12.727272727272728px" width="72.12121212121212px">
						<fo:block background-color="transparent" color="#000000" font-size="7pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Other (Specify)</fo:block>
					</fo:block-container>
					<!--Block below is for the label named FederalAwardIdentifier_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="263.6363636363636px" hyphenate="true" language="en" keep-together="always" top="167.27272727272728px" height="13.333333333333334px" width="109.6969696969697px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* 5b. Federal Award Identifier:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named DUNSNumber_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="263.6363636363636px" hyphenate="true" language="en" keep-together="always" top="286.6666666666667px" height="13.333333333333334px" width="101.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* c. Organizational DUNS:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named DivisionName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="263.6363636363636px" hyphenate="true" language="en" keep-together="always" top="500px" height="13.333333333333334px" width="56.969696969696976px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Division Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named Fax_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="301.8181818181818px" hyphenate="true" language="en" keep-together="always" top="684.8484848484849px" height="13.333333333333334px" width="53.333333333333336px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Fax Number:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL3-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="456.3636363636364px" hyphenate="true" language="en" keep-together="always" top="17.575757575757578px" height="13.333333333333334px" width="105.45454545454545px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Expiration Date: 07/31/2006</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="463.6363636363637px" hyphenate="true" language="en" keep-together="always" top="5.454545454545455px" height="13.333333333333334px" width="98.18181818181819px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 4040-0004</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="507.2727272727273px" hyphenate="true" language="en" keep-together="always" top="37.57575757575758px" height="15.757575757575758px" width="54.54545454545455px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="normal">Version 02</fo:block>
					</fo:block-container>
					<!--Draw lines-->
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="33.333333333333336px" width="1.2121212121212122px" height="689.0909090909091px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="152.72727272727275px" top="56.36363636363637px" width="1.2121212121212122px" height="70.9090909090909px">
                                            <fo:block/> 
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="260px" top="163.03030303030303px" width="1.2121212121212122px" height="38.18181818181819px">
                                          <fo:block/>  
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="184.24242424242425px" top="220.60606060606062px" width="1.2121212121212122px" height="21.212121212121215px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="260px" top="283.03030303030306px" width="1.2121212121212122px" height="36.36363636363637px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="260px" top="496.969696969697px" width="1.2121212121212122px" height="36.36363636363637px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="564.8484848484849px" top="33.93939393939394px" width="1.2121212121212122px" height="689.0909090909091px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="33.333333333333336px" width="553.3333333333334px" height="1.2121212121212122px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="56.36363636363637px" width="553.3333333333334px" height="1.2121212121212122px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="126.06060606060606px" width="553.3333333333334px" height="1.2121212121212122px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="163.03030303030303px" width="553.3333333333334px" height="1.2121212121212122px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="200px" width="553.3333333333334px" height="1.2121212121212122px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="220.60606060606062px" width="553.3333333333334px" height="1.2121212121212122px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="261.8181818181818px" width="553.3333333333334px" height="1.2121212121212122px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="282.42424242424244px" width="553.3333333333334px" height="1.2121212121212122px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="319.39393939393943px" width="553.3333333333334px" height="1.2121212121212122px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="340px" width="553.3333333333334px" height="1.2121212121212122px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="475.1515151515152px" width="553.3333333333334px" height="1.2121212121212122px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="495.7575757575758px" width="553.3333333333334px" height="1.2121212121212122px">
                                         <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="532.7272727272727px" width="553.3333333333334px" height="1.2121212121212122px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="553.3333333333334px" width="553.3333333333334px" height="1.2121212121212122px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="623.0303030303031px" width="553.3333333333334px" height="1.2121212121212122px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="643.6363636363636px" width="553.3333333333334px" height="1.2121212121212122px">
                                          <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="680.6060606060606px" width="553.3333333333334px" height="1.2121212121212122px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="701.2121212121212px" width="553.3333333333334px" height="1.2121212121212122px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="721.8181818181819px" width="553.3333333333334px" height="1.2121212121212122px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="241.21212121212122px" width="553.3333333333334px" height="1.2121212121212122px">
                                        <fo:block/>
                                        </fo:block-container>
				</fo:flow>
			</fo:page-sequence>
			<fo:page-sequence master-reference="default-page" format="1" initial-page-number="2">
				<fo:static-content flow-name="xsl-region-after">
					<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="center" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Funding Opportunity Number: <xsl:value-of select="/*/*/header:OpportunityID"/>
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Received Date: <xsl:value-of select="translate(/*/*/footer:ReceivedDateTime, 'T', ' ')"/> Time Zone: GMT-5
                              </fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<!--Data components-->
					<!--Block below is for the popup named ApplicantTypeCode1 with FieldID 9-0-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="76.96969696969697px" height="13.333333333333334px" width="498.1818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:ApplicantTypeCode1) or //SF424:SF424/SF424:ApplicantTypeCode1 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:ApplicantTypeCode1"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the popup named ApplicantTypeCode2 with FieldID 9-1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="109.6969696969697px" height="13.333333333333334px" width="498.1818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:ApplicantTypeCode2) or //SF424:SF424/SF424:ApplicantTypeCode2 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:ApplicantTypeCode2"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the popup named ApplicantTypeCode3 with FieldID 9-2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="142.42424242424244px" height="13.333333333333334px" width="498.1818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:ApplicantTypeCode3) or //SF424:SF424/SF424:ApplicantTypeCode3 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:ApplicantTypeCode3"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ApplicantTypeOtherSpecify with FieldID 9-3-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="175.15151515151516px" height="13.333333333333334px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:ApplicantTypeOtherSpecify) or //SF424:SF424/SF424:ApplicantTypeOtherSpecify = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:ApplicantTypeOtherSpecify"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named AgencyName with FieldID 10-0-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="212.12121212121212px" height="13.333333333333334px" width="478.7878787878788px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:AgencyName) or //SF424:SF424/SF424:AgencyName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:AgencyName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named CFDANumber with FieldID 11-0-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="249.0909090909091px" height="13.333333333333334px" width="124.84848484848486px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:CFDANumber) or //SF424:SF424/SF424:CFDANumber = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:CFDANumber"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named CFDAProgramTitle with FieldID 11-1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="281.8181818181818px" height="23.03030303030303px" width="488.4848484848485px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:CFDAProgramTitle) or //SF424:SF424/SF424:CFDAProgramTitle = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:CFDAProgramTitle"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named FundingOpportunityNumber with FieldID 12-0-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="328.4848484848485px" height="13.333333333333334px" width="318.7878787878788px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:FundingOpportunityNumber) or //SF424:SF424/SF424:FundingOpportunityNumber = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:FundingOpportunityNumber"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named FundingOpportunityTitle with FieldID 12-1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="361.21212121212125px" height="52.121212121212125px" width="488.4848484848485px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:FundingOpportunityTitle) or //SF424:SF424/SF424:FundingOpportunityTitle = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:FundingOpportunityTitle"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named CompetitionIdentificationNumber with FieldID 13-0-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="436.969696969697px" height="13.333333333333334px" width="318.7878787878788px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:CompetitionIdentificationNumber) or //SF424:SF424/SF424:CompetitionIdentificationNumber = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:CompetitionIdentificationNumber"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named CompetitionIdentificationTitle with FieldID 13-1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="469.69696969696975px" height="52.121212121212125px" width="488.4848484848485px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:CompetitionIdentificationTitle) or //SF424:SF424/SF424:CompetitionIdentificationTitle = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:CompetitionIdentificationTitle"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named AffectedAreas with FieldID 14-0-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="545.4545454545455px" height="52.121212121212125px" width="488.4848484848485px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:AffectedAreas) or //SF424:SF424/SF424:AffectedAreas = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:AffectedAreas"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectTitle with FieldID 15-0-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="621.2121212121212px" height="42.42424242424243px" width="488.4848484848485px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:ProjectTitle) or //SF424:SF424/SF424:ProjectTitle = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:ProjectTitle"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Write labels-->
					<!--Block below is for the label named -->
					<!--Block below is for the label named d1e1647-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="37.57575757575758px" height="15.757575757575758px" width="276.3636363636364px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">Application for Federal Assistance SF-424</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicantTypeCode1_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="60.60606060606061px" height="13.333333333333334px" width="195.75757575757578px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">9. Type of Applicant 1: Select Applicant Type:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicantTypeCode2_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="93.33333333333334px" height="13.333333333333334px" width="192.12121212121212px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Type of Applicant 2: Select Applicant Type:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicantTypeCode3_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="126.06060606060606px" height="13.333333333333334px" width="190.3030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Type of Applicant 3: Select Applicant Type:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicantTypeOtherSpecify_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="158.7878787878788px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Other (specify):</fo:block>
					</fo:block-container>
					<!--Block below is for the label named AgencyName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="195.75757575757578px" height="13.333333333333334px" width="136.36363636363637px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">* 10. Name of Federal Agency:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named CFDANumber_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="232.72727272727275px" height="13.333333333333334px" width="207.27272727272728px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">11. Catalog of Federal Domestic Assistance Number:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named CFDAProgramTitle_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="265.4545454545455px" height="13.333333333333334px" width="90.90909090909092px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">CFDA Title:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named FundingOpportunityNumber_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="312.1212121212121px" height="13.333333333333334px" width="173.33333333333334px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">* 12. Funding Opportunity Number:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named FundingOpportunityTitle_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="344.8484848484849px" height="13.333333333333334px" width="30.90909090909091px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Title:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named CompetitionIdentificationNumber_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="420.6060606060606px" height="13.333333333333334px" width="184.24242424242425px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">13. Competition Identification Number:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named CompetitionIdentificationTitle_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="453.33333333333337px" height="13.333333333333334px" width="36.96969696969697px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Title:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named AffectedAreas_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="529.0909090909091px" height="13.333333333333334px" width="234.54545454545456px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">14. Areas Affected by Project (Cities, Counties, States, etc.):</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectTitle_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="604.8484848484849px" height="13.333333333333334px" width="195.75757575757578px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">* 15. Descriptive Title of Applicant's Project:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL4-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="670.909090909091px" height="13.333333333333334px" width="240.60606060606062px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Attach supporting documents as specified in agency instructions.</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL3-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="456.3636363636364px" hyphenate="true" language="en" keep-together="always" top="17.575757575757578px" height="13.333333333333334px" width="105.45454545454545px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Expiration Date: 07/31/2006</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="463.6363636363637px" hyphenate="true" language="en" keep-together="always" top="5.454545454545455px" height="13.333333333333334px" width="98.18181818181819px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 4040-0004</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="507.2727272727273px" hyphenate="true" language="en" keep-together="always" top="37.57575757575758px" height="15.757575757575758px" width="54.54545454545455px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="normal">Version 02</fo:block>
					</fo:block-container>
					<!--Draw lines-->
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="33.333333333333336px" width="1.2121212121212122px" height="671.5151515151515px">
                                            <fo:block/>
                                         </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="564.8484848484849px" top="33.333333333333336px" width="1.2121212121212122px" height="671.5151515151515px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="33.333333333333336px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="56.36363636363637px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="191.51515151515153px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="228.4848484848485px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="307.8787878787879px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="416.3636363636364px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                         </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="524.8484848484849px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="600.6060606060606px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="703.6363636363636px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="666.6666666666667px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
				</fo:flow>
			</fo:page-sequence>
			<fo:page-sequence master-reference="default-page" format="1" initial-page-number="3">
				<fo:static-content flow-name="xsl-region-after">
					<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="center" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Funding Opportunity Number: <xsl:value-of select="/*/*/header:OpportunityID"/>
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Received Date: <xsl:value-of select="translate(/*/*/footer:ReceivedDateTime, 'T', ' ')"/> Time Zone: GMT-5
                              </fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<!--Data components-->
					<!--Block below is for the field named StateReviewRadioRequiredField with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="15.151515151515152px" hyphenate="true" language="en" keep-together="always" top="309.6969696969697px" height="16.363636363636363px" width="336.969696969697px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"/>
					</fo:block-container>
					<!--Block below is for the field named DelinquentFederalDebtRadioRequiredField with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="15.151515151515152px" hyphenate="true" language="en" keep-together="always" top="378.7878787878788px" height="16.96969696969697px" width="218.1818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"/>
					</fo:block-container>
					<!--Block below is for the field named AdditionalCongressionalDistricts0 with FieldID 16-c-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="113.93939393939395px" height="12.121212121212121px" width="328.4848484848485px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:AdditionalCongressionalDistricts/att:FileName) or //SF424:SF424/SF424:AdditionalCongressionalDistricts/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:AdditionalCongressionalDistricts/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the radio named Available with FieldID 19-a-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="327.2727272727273px" height="13.333333333333334px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SF424:SF424/SF424:StateReview"/>
										<xsl:with-param name="schemaChoice">a. This application was made available to the State under the Executive Order 12372 Process for review on</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the radio named StateReview_NotSelected with FieldID 19-b-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="343.6363636363637px" height="13.333333333333334px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SF424:SF424/SF424:StateReview"/>
										<xsl:with-param name="schemaChoice">b. Program is subject to E.O. 12372 but has not been selected by the State for review.</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the radio named StateReview_NotCovered with FieldID 19-c-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="360px" height="13.333333333333334px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SF424:SF424/SF424:StateReview"/>
										<xsl:with-param name="schemaChoice">c. Program is not covered by E.O. 12372.</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the radio named DelinquentFederalDebt_Yes with FieldID 20-1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="396.969696969697px" height="13.333333333333334px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SF424:SF424/SF424:DelinquentFederalDebt"/>
										<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the check named CertificationAgree with FieldID 21-1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="463.03030303030306px" height="13.333333333333334px" width="10.303030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<fo:inline font-family="ZapfDingbats" font-size="8pt">&#x274F;</fo:inline>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="460px" height="13.333333333333334px" width="10.303030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:CertificationAgree) or //SF424:SF424/SF424:CertificationAgree = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="checkbox">
										<xsl:with-param name="value" select="//SF424:SF424/SF424:CertificationAgree"/>
										<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named SubmissionTypeRadioRequiredField with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="27.272727272727273px" hyphenate="true" language="en" keep-together="always" top="461.81818181818187px" height="15.757575757575758px" width="49.6969696969697px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"/>
					</fo:block-container>
					<!--Block below is for the field named AuthorizedRepresentativeTitle with FieldID 22-6-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="50.303030303030305px" hyphenate="true" language="en" keep-together="always" top="600px" height="13.333333333333334px" width="357.5757575757576px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:AuthorizedRepresentativeTitle) or //SF424:SF424/SF424:AuthorizedRepresentativeTitle = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:AuthorizedRepresentativeTitle"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named AuthorizedRepresentativeEmail with FieldID 22-8-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="56.36363636363637px" hyphenate="true" language="en" keep-together="always" top="641.2121212121212px" height="13.333333333333334px" width="478.7878787878788px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:AuthorizedRepresentativeEmail) or //SF424:SF424/SF424:AuthorizedRepresentativeEmail = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:AuthorizedRepresentativeEmail"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the combobox named Prefix with FieldID 22-1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="74.54545454545455px" hyphenate="true" language="en" keep-together="always" top="530.3030303030304px" height="13.333333333333334px" width="86.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:AuthorizedRepresentative/globLib:PrefixName) or //SF424:SF424/SF424:AuthorizedRepresentative/globLib:PrefixName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:AuthorizedRepresentative/globLib:PrefixName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named MiddleName with FieldID 22-3-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="74.54545454545455px" hyphenate="true" language="en" keep-together="always" top="546.6666666666667px" height="13.333333333333334px" width="202.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:AuthorizedRepresentative/globLib:MiddleName) or //SF424:SF424/SF424:AuthorizedRepresentative/globLib:MiddleName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:AuthorizedRepresentative/globLib:MiddleName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named LastName with FieldID 22-4-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="74.54545454545455px" hyphenate="true" language="en" keep-together="always" top="563.0303030303031px" height="13.333333333333334px" width="478.7878787878788px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:AuthorizedRepresentative/globLib:LastName) or //SF424:SF424/SF424:AuthorizedRepresentative/globLib:LastName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:AuthorizedRepresentative/globLib:LastName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the combobox named Suffix with FieldID 22-5-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="74.54545454545455px" hyphenate="true" language="en" keep-together="always" top="579.3939393939394px" height="13.333333333333334px" width="86.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:AuthorizedRepresentative/globLib:SuffixName) or //SF424:SF424/SF424:AuthorizedRepresentative/globLib:SuffixName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:AuthorizedRepresentative/globLib:SuffixName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectStartDate with FieldID 17-a-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="76.36363636363637px" hyphenate="true" language="en" keep-together="always" top="150.9090909090909px" height="13.333333333333334px" width="52.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:ProjectStartDate) or //SF424:SF424/SF424:ProjectStartDate = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//SF424:SF424/SF424:ProjectStartDate"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named CongressionalDistrictApplicant with FieldID 16-a-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="77.57575757575758px" hyphenate="true" language="en" keep-together="always" top="76.96969696969697px" height="13.333333333333334px" width="52.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:CongressionalDistrictApplicant) or //SF424:SF424/SF424:CongressionalDistrictApplicant = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:CongressionalDistrictApplicant"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the radio named DelinquentFederalDebt_No with FieldID 20-2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="81.81818181818183px" hyphenate="true" language="en" keep-together="always" top="396.969696969697px" height="13.333333333333334px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//SF424:SF424/SF424:DelinquentFederalDebt"/>
										<xsl:with-param name="schemaChoice">N: No</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named FederalEstimatedFunding with FieldID 18-a-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="95.75757575757576px" hyphenate="true" language="en" keep-together="always" top="192.12121212121212px" height="12.121212121212121px" width="115.15151515151516px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:FederalEstimatedFunding) or //SF424:SF424/SF424:FederalEstimatedFunding = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//SF424:SF424/SF424:FederalEstimatedFunding, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ApplicantEstimatedFunding with FieldID 18-b-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="95.75757575757576px" hyphenate="true" language="en" keep-together="always" top="208.4848484848485px" height="12.121212121212121px" width="115.15151515151516px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:ApplicantEstimatedFunding) or //SF424:SF424/SF424:ApplicantEstimatedFunding = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//SF424:SF424/SF424:ApplicantEstimatedFunding, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named StateEstimatedFunding with FieldID 18-c-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="95.75757575757576px" hyphenate="true" language="en" keep-together="always" top="224.84848484848487px" height="12.121212121212121px" width="115.15151515151516px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:StateEstimatedFunding) or //SF424:SF424/SF424:StateEstimatedFunding = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//SF424:SF424/SF424:StateEstimatedFunding, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named LocalEstimatedFunding with FieldID 18-d-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="95.75757575757576px" hyphenate="true" language="en" keep-together="always" top="241.21212121212122px" height="12.121212121212121px" width="115.15151515151516px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:LocalEstimatedFunding) or //SF424:SF424/SF424:LocalEstimatedFunding = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//SF424:SF424/SF424:LocalEstimatedFunding, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named OtherEstimatedFunding with FieldID 18-e-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="95.75757575757576px" hyphenate="true" language="en" keep-together="always" top="257.57575757575756px" height="12.121212121212121px" width="115.15151515151516px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:OtherEstimatedFunding) or //SF424:SF424/SF424:OtherEstimatedFunding = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//SF424:SF424/SF424:OtherEstimatedFunding, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProgramIncomeEstimatedFunding with FieldID 18-f-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="95.75757575757576px" hyphenate="true" language="en" keep-together="always" top="273.93939393939394px" height="12.121212121212121px" width="115.15151515151516px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:ProgramIncomeEstimatedFunding) or //SF424:SF424/SF424:ProgramIncomeEstimatedFunding = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//SF424:SF424/SF424:ProgramIncomeEstimatedFunding, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named TotalEstimatedFunding with FieldID 18-g-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="95.75757575757576px" hyphenate="true" language="en" keep-together="always" top="290.3030303030303px" height="12.121212121212121px" width="115.15151515151516px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:TotalEstimatedFunding) or //SF424:SF424/SF424:TotalEstimatedFunding = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//SF424:SF424/SF424:TotalEstimatedFunding, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named AuthorizedRepresentativePhoneNumber with FieldID 22-7-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="96.96969696969697px" hyphenate="true" language="en" keep-together="always" top="620.6060606060606px" height="13.333333333333334px" width="202.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:AuthorizedRepresentativePhoneNumber) or //SF424:SF424/SF424:AuthorizedRepresentativePhoneNumber = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:AuthorizedRepresentativePhoneNumber"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named FirstName with FieldID 22-2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="269.0909090909091px" hyphenate="true" language="en" keep-together="always" top="530.3030303030304px" height="13.333333333333334px" width="280px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:AuthorizedRepresentative/globLib:FirstName) or //SF424:SF424/SF424:AuthorizedRepresentative/globLib:FirstName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:AuthorizedRepresentative/globLib:FirstName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named StateReviewAvailableDate with FieldID 19-a1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="414.54545454545456px" hyphenate="true" language="en" keep-together="always" top="327.2727272727273px" height="13.333333333333334px" width="52.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:StateReviewAvailableDate) or //SF424:SF424/SF424:StateReviewAvailableDate = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//SF424:SF424/SF424:StateReviewAvailableDate"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named AuthorizedRepresentativeFax with FieldID 22-9-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="354.54545454545456px" hyphenate="true" language="en" keep-together="always" top="620.6060606060606px" height="13.333333333333334px" width="202.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:AuthorizedRepresentativeFax) or //SF424:SF424/SF424:AuthorizedRepresentativeFax = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:AuthorizedRepresentativeFax"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectEndDate with FieldID 17-b-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="401.21212121212125px" hyphenate="true" language="en" keep-together="always" top="150.9090909090909px" height="13.333333333333334px" width="52.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:ProjectEndDate) or //SF424:SF424/SF424:ProjectEndDate = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//SF424:SF424/SF424:ProjectEndDate"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named CongressionalDistrictProgramProject with FieldID 16-b-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="401.8181818181818px" hyphenate="true" language="en" keep-together="always" top="76.96969696969697px" height="13.333333333333334px" width="52.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:CongressionalDistrictProgramProject) or //SF424:SF424/SF424:CongressionalDistrictProgramProject = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:CongressionalDistrictProgramProject"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Write labels-->
					<!--Block below is for the label named -->
					<!--Block below is for the label named d1e1647-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="37.57575757575758px" height="15.757575757575758px" width="276.3636363636364px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">Application for Federal Assistance SF-424</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d1e9700-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="60.60606060606061px" height="13.333333333333334px" width="128.4848484848485px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">16. Congressional Districts Of:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named CongressionalDistrictApplicant_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="76.96969696969697px" height="13.333333333333334px" width="58.78787878787879px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* a. Applicant</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL6-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="97.57575757575758px" height="13.333333333333334px" width="328.4848484848485px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Attach an additional list of Program/Project Congressional Districts if needed.</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d1e10160-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="134.54545454545456px" height="13.333333333333334px" width="99.54545454545455px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">17. Proposed Project:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectStartDate_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="150.9090909090909px" height="13.333333333333334px" width="57.57575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* a. Start Date:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d1e10505-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="171.51515151515153px" height="13.333333333333334px" width="124.24242424242425px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">18. Estimated Funding ($):</fo:block>
					</fo:block-container>
					<!--Block below is for the label named FederalEstimatedFunding_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="192.12121212121212px" height="13.333333333333334px" width="51.515151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* a. Federal</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicantEstimatedFunding_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="208.4848484848485px" height="13.333333333333334px" width="56.36363636363637px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* b. Applicant</fo:block>
					</fo:block-container>
					<!--Block below is for the label named StateEstimatedFunding_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="224.84848484848487px" height="13.333333333333334px" width="47.87878787878788px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* c. State</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LocalEstimatedFunding_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="241.21212121212122px" height="13.333333333333334px" width="43.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* d. Local</fo:block>
					</fo:block-container>
					<!--Block below is for the label named OtherEstimatedFunding_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="257.57575757575756px" height="13.333333333333334px" width="46.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* e. Other</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProgramIncomeEstimatedFunding_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="273.93939393939394px" height="13.333333333333334px" width="76.96969696969697px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* f.  Program Income</fo:block>
					</fo:block-container>
					<!--Block below is for the label named TotalEstimatedFunding_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="290.3030303030303px" height="13.333333333333334px" width="48.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* g. TOTAL</fo:block>
					</fo:block-container>
					<!--Block below is for the label named StateReview_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="310.90909090909093px" height="13.333333333333334px" width="333.93939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">* 19. Is Application Subject to Review By State Under Executive Order 12372 Process?</fo:block>
					</fo:block-container>
					<!--Block below is for the label named DelinquentFederalDebt_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="380.6060606060606px" height="13.333333333333334px" width="375.15151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">* 20. Is the Applicant Delinquent On Any Federal Debt? (If "Yes", provide explanation.)</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d1e12345-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="417.5757575757576px" height="42.42424242424243px" width="484.8484848484849px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">21. *By signing this application, I certify (1) to the statements contained in the list of certifications** and (2) that the statements herein are true, complete and accurate to the best of my knowledge. I also provide the required assurances** and agree to comply with any resulting terms if I accept an award. I am aware that any false, fictitious, or fraudulent statements or claims may subject me to criminal, civil, or administrative penalties. (U.S. Code, Title 218, Section 1001)</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d1e12575-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="479.39393939393943px" height="23.03030303030303px" width="484.8484848484849px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">** The list of certifications and assurances, or an internet site where you may obtain this list, is contained in the announcement or agency specific instructions.</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d1e12690-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="509.69696969696975px" height="13.333333333333334px" width="118.18181818181819px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">Authorized Representative:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named Prefix_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="530.3030303030304px" height="13.333333333333334px" width="30.303030303030305px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Prefix:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named MiddleName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="546.6666666666667px" height="13.333333333333334px" width="55.75757575757576px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Middle Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LastName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="563.0303030303031px" height="13.333333333333334px" width="54.54545454545455px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Last Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named Suffix_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="579.3939393939394px" height="13.333333333333334px" width="33.333333333333336px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Suffix:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named AuthorizedRepresentativeTitle_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="600px" height="13.333333333333334px" width="31.515151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Title:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named AuthorizedRepresentativePhoneNumber_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="620.6060606060606px" height="13.333333333333334px" width="78.18181818181819px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Telephone Number:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named AuthorizedRepresentativeEmail_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="641.2121212121212px" height="13.333333333333334px" width="37.57575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Email:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL4-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="661.8181818181819px" height="13.333333333333334px" width="150.9090909090909px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Signature of Authorized Representative:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d1e14185-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="682.4242424242425px" height="13.333333333333334px" width="123.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Authorized for Local Reproduction</fo:block>
					</fo:block-container>
					<!--Block below is for the label named Available_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="27.272727272727273px" hyphenate="true" language="en" keep-together="always" top="327.2727272727273px" height="13.333333333333334px" width="384.8484848484849px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">a. This application was made available to the State under the Executive Order 12372 Process for review on</fo:block>
					</fo:block-container>
					<!--Block below is for the label named StateReview_NotSelected_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="27.272727272727273px" hyphenate="true" language="en" keep-together="always" top="343.6363636363637px" height="13.333333333333334px" width="324.8484848484849px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">b. Program is subject to E.O. 12372 but has not been selected by the State for review.</fo:block>
					</fo:block-container>
					<!--Block below is for the label named StateReview_NotCovered_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="27.272727272727273px" hyphenate="true" language="en" keep-together="always" top="360px" height="13.333333333333334px" width="324.8484848484849px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">c. Program is not covered by E.O. 12372.</fo:block>
					</fo:block-container>
					<!--Block below is for the label named DelinquentFederalDebt_Yes_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="27.272727272727273px" hyphenate="true" language="en" keep-together="always" top="396.969696969697px" height="13.333333333333334px" width="29.6969696969697px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Yes</fo:block>
					</fo:block-container>
					<!--Block below is for the label named CertificationAgree_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="29.090909090909093px" hyphenate="true" language="en" keep-together="always" top="463.03030303030306px" height="13.333333333333334px" width="45.45454545454546px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">** I AGREE</fo:block>
					</fo:block-container>
					<!--Block below is for the label named DelinquentFederalDebt_No_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="92.72727272727273px" hyphenate="true" language="en" keep-together="always" top="396.969696969697px" height="13.333333333333334px" width="26.060606060606062px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">No</fo:block>
					</fo:block-container>
					<!--Block below is for the label named AORSignature-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="169.69696969696972px" hyphenate="true" language="en" keep-together="always" top="661.8181818181819px" height="11.515151515151516px" width="130.9090909090909px">
						<fo:block background-color="transparent" color="#000000" font-size="6pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:AORSignature) or //SF424:SF424/SF424:AORSignature = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:AORSignature"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the label named FirstName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="214.54545454545456px" hyphenate="true" language="en" keep-together="always" top="530.3030303030304px" height="13.333333333333334px" width="52.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* First Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named CongressionalDistrictProgramProject_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="327.27272727272728px" hyphenate="true" language="en" keep-together="always" top="76.96969696969697px" height="13.333333333333334px" width="151.51515151515153px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* b. Program/Project:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named AuthorizedRepresentativeFax_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="301.8181818181818px" hyphenate="true" language="en" keep-together="always" top="620.6060606060606px" height="13.333333333333334px" width="50.303030303030305px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Fax Number:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL5-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="308.4848484848485px" hyphenate="true" language="en" keep-together="always" top="661.8181818181819px" height="13.333333333333334px" width="56.969696969696976px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Date Signed:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectEndDate_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="344.8484848484849px" hyphenate="true" language="en" keep-together="always" top="150.9090909090909px" height="13.333333333333334px" width="53.939393939393945px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* b. End Date:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named DateSigned-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="367.8787878787879px" hyphenate="true" language="en" keep-together="always" top="661.8181818181819px" height="11.515151515151516px" width="130.9090909090909px">
						<fo:block background-color="transparent" color="#000000" font-size="6pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:DateSigned) or //SF424:SF424/SF424:DateSigned = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//SF424:SF424/SF424:DateSigned"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL7-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="469.0909090909091px" hyphenate="true" language="en" keep-together="always" top="327.2727272727273px" height="13.333333333333334px" width="12.727272727272728px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">.</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d1e14300-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="412.1212121212121px" hyphenate="true" language="en" keep-together="always" top="682.4242424242425px" height="13.333333333333334px" width="149.69696969696972px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Standard Form 424 (Revised 10/2005)</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d1e14415-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="424.8484848484849px" hyphenate="true" language="en" keep-together="always" top="694.5454545454546px" height="13.333333333333334px" width="136.96969696969697px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Prescribed by OMB Circular A-102</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL3-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="456.3636363636364px" hyphenate="true" language="en" keep-together="always" top="17.575757575757578px" height="13.333333333333334px" width="105.45454545454545px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Expiration Date: 07/31/2006</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="463.6363636363637px" hyphenate="true" language="en" keep-together="always" top="5.454545454545455px" height="13.333333333333334px" width="98.18181818181819px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 4040-0004</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="507.2727272727273px" hyphenate="true" language="en" keep-together="always" top="37.57575757575758px" height="15.757575757575758px" width="54.54545454545455px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="normal">Version 02</fo:block>
					</fo:block-container>
					<!--Draw lines-->
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="33.333333333333336px" width="1.2121212121212122px" height="645.4545454545455px">
                                            <fo:block/>
                                         </fo:block-container>   
					<fo:block-container background-color="black" border-style="none" position="absolute" left="564.8484848484849px" top="33.333333333333336px" width="1.2121212121212122px" height="645.4545454545455px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="33.333333333333336px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>                                        
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="167.27272727272728px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>    
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="187.87878787878788px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="306.6666666666667px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="376.3636363636364px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="413.33333333333337px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="505.4545454545455px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="526.0606060606061px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="595.7575757575758px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="616.3636363636364px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="636.969696969697px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="657.5757575757576px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="678.1818181818182px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="56.36363636363637px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="93.33333333333334px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="130.3030303030303px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
				</fo:flow>
			</fo:page-sequence>
			<fo:page-sequence master-reference="default-page" format="1" initial-page-number="4">
				<fo:static-content flow-name="xsl-region-after">
					<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="center" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Funding Opportunity Number: <xsl:value-of select="/*/*/header:OpportunityID"/>
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Received Date: <xsl:value-of select="translate(/*/*/footer:ReceivedDateTime, 'T', ' ')"/> Time Zone: GMT-5
                              </fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<!--Data components-->
					<!--Block below is for the field named DelinquentFederalDebtExplanation with FieldID 20-1b-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="103.03030303030303px" height="633.939393939394px" width="488.4848484848485px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424:SF424/SF424:DelinquentFederalDebtExplanation) or //SF424:SF424/SF424:DelinquentFederalDebtExplanation = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424:SF424/SF424:DelinquentFederalDebtExplanation"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Write labels-->
					<!--Block below is for the label named -->
					<!--Block below is for the label named d1e1647-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="37.57575757575758px" height="15.757575757575758px" width="276.3636363636364px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">Application for Federal Assistance SF-424</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL4-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="60.60606060606061px" height="13.333333333333334px" width="203.63636363636365px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">* Applicant Federal Debt Delinquency Explanation</fo:block>
					</fo:block-container>
					<!--Block below is for the label named DelinquentFederalDebtExplanation_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="76.96969696969697px" height="23.03030303030303px" width="484.8484848484849px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">The following field should contain an explanation if the Applicant organization is delinquent on any Federal Debt. Maximum number of characters that can be entered is 4,000.  Try and avoid extra spaces and carriage returns to maximize the availability of space.</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL3-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="456.3636363636364px" hyphenate="true" language="en" keep-together="always" top="17.575757575757578px" height="13.333333333333334px" width="105.45454545454545px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Expiration Date: 07/31/2006</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="463.6363636363637px" hyphenate="true" language="en" keep-together="always" top="5.454545454545455px" height="13.333333333333334px" width="98.18181818181819px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 4040-0004</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="507.2727272727273px" hyphenate="true" language="en" keep-together="always" top="37.57575757575758px" height="15.757575757575758px" width="54.54545454545455px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="normal">Version 02</fo:block>
					</fo:block-container>
					<!--Draw lines-->
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="33.333333333333336px" width="1.2121212121212122px" height="707.8787878787879px">
                                            <fo:block/>
                                        </fo:block-container>
                                        
					<fo:block-container background-color="black" border-style="none" position="absolute" left="564.8484848484849px" top="33.333333333333336px" width="1.2121212121212122px" height="707.8787878787879px">
                                            <fo:block/>
                                         </fo:block-container>
                                                                              
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="33.333333333333336px" width="553.3333333333334px" height="1.2121212121212122px">
                                          <fo:block/>
                                        </fo:block-container>
                                        
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="56.36363636363637px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="12.727272727272728px" top="740px" width="553.3333333333334px" height="1.2121212121212122px">
                                            <fo:block/>
                                         </fo:block-container>
				</fo:flow>
			</fo:page-sequence>
			<fo:page-sequence master-reference="default-page" format="1" initial-page-number="5">
				<fo:static-content flow-name="xsl-region-after">
					<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="center" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Funding Opportunity Number: <xsl:value-of select="/*/*/header:OpportunityID"/>
										</fo:inline>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="right" border-style="solid" border-width="0pt" border-color="white">
									<fo:block>
										<fo:inline font-size="6px" font-weight="bold">Received Date: <xsl:value-of select="translate(/*/*/footer:ReceivedDateTime, 'T', ' ')"/> Time Zone: GMT-5
                              </fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-column column-width="proportional-column-width(1)"/>
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="center" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<fo:block font-size="14pt" text-decoration="underline">Attachments</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">AdditionalCongressionalDistricts</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//SF424:SF424/SF424:AdditionalCongressionalDistricts/att:FileName"/>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt">
										<xsl:value-of select="//SF424:SF424/SF424:AdditionalCongressionalDistricts/att:MimeType"/>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
									<xsl:call-template name="addBlankLines">
										<xsl:with-param name="numLines">1</xsl:with-param>
									</xsl:call-template>
									<fo:block font-size="10pt">AdditionalProjectTitle</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">File Name</fo:block>
								</fo:table-cell>
								<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
									<fo:block font-size="8pt" font-weight="bold">Mime Type</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<xsl:for-each select="//SF424:SF424/SF424:AdditionalProjectTitle/att:AttachedFile">
								<fo:table-row>
									<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
										<fo:block font-size="8pt">
											<xsl:value-of select="att:FileName"/>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
										<fo:block font-size="8pt">
											<xsl:value-of select="att:MimeType"/>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
							</xsl:for-each>
						</fo:table-body>
					</fo:table>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	<xsl:template name="radioButton">
		<xsl:param name="value"/>
		<xsl:param name="schemaChoice">Y: Yes</xsl:param>
		<xsl:choose>
			<xsl:when test="$value = $schemaChoice">
				<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="8pt">&#x25cf;</fo:inline>
			</xsl:when>
			<xsl:otherwise>
				<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="8pt">&#x274d;</fo:inline>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="checkbox">
		<xsl:param name="value"/>
		<xsl:param name="schemaChoice">Y: Yes</xsl:param>
		<xsl:if test="$value = $schemaChoice">
			<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="8pt">&#x2714;</fo:inline>
		</xsl:if>
	</xsl:template>
	<xsl:template name="formatDate">
		<xsl:param name="value"/>
		<xsl:if test="$value != ''">
			<xsl:value-of select="format-number(substring($value,6,2), '00')"/>
			<xsl:text>/</xsl:text>
			<xsl:value-of select="format-number(substring($value,9,2), '00')"/>
			<xsl:text>/</xsl:text>
			<xsl:value-of select="format-number(substring($value,1,4), '0000')"/>
		</xsl:if>
	</xsl:template>
	<xsl:template name="addBlankLines">
		<xsl:param name="numLines"/>
		<xsl:if test="string($numLines) != ''">
			<xsl:if test="$numLines &gt; 0">
				<fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0">
					<fo:leader leader-pattern="space"/>
				</fo:block>
				<xsl:call-template name="addBlankLines">
					<xsl:with-param name="numLines" select="$numLines - 1"/>
				</xsl:call-template>
			</xsl:if>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>
