<?xml version="1.0" encoding="UTF-8"?>
<!-- $Revision:   1.11  $ -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:SF424_Short="http://apply.grants.gov/forms/SF424_Short-V1.0">
	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="/">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:header="http://apply.grants.gov/system/Header-V1.0" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0">
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
										<fo:inline font-size="6px" font-weight="bold">Received Date:  <xsl:value-of select="translate(/*/*/footer:ReceivedDateTime, 'T', ' ')"/> Time Zone: GMT-5
                              </fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<!--Data components-->
					<!--Block below is for the field named CFDAProgramTitle with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="8.484848484848484px" hyphenate="true" language="en" keep-together="always" top="105.45454545454545px" height="23.03030303030303px" width="560.6060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:CFDAProgramTitle) or //SF424_Short:SF424_Short/SF424_Short:CFDAProgramTitle = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:CFDAProgramTitle"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named CFDANumber with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.090909090909092px" hyphenate="true" language="en" keep-together="always" top="79.3939393939394px" height="13.333333333333334px" width="124.84848484848486px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:CFDANumber) or //SF424_Short:SF424_Short/SF424_Short:CFDANumber = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:CFDANumber"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named FundingOpportunityNumber with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.090909090909092px" hyphenate="true" language="en" keep-together="always" top="158.1818181818182px" height="13.333333333333334px" width="323.6363636363636px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:FundingOpportunityNumber) or //SF424_Short:SF424_Short/SF424_Short:FundingOpportunityNumber = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:FundingOpportunityNumber"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named FundingOpportunityTitle with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.090909090909092px" hyphenate="true" language="en" keep-together="always" top="183.03030303030303px" height="46.06060606060606px" width="560.6060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:FundingOpportunityTitle) or //SF424_Short:SF424_Short/SF424_Short:FundingOpportunityTitle = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:FundingOpportunityTitle"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ApplicantStreet1 with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.090909090909092px" hyphenate="true" language="en" keep-together="always" top="294.54545454545456px" height="23.03030303030303px" width="221.81818181818184px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:Address/globLib:Street1) or //SF424_Short:SF424_Short/SF424_Short:Address/globLib:Street1 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:Address/globLib:Street1"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectTitle with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.090909090909092px" hyphenate="true" language="en" keep-together="always" top="543.030303030303px" height="33.333333333333336px" width="558.1818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectTitle) or //SF424_Short:SF424_Short/SF424_Short:ProjectTitle = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ProjectTitle"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named AgencyName with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.696969696969697px" hyphenate="true" language="en" keep-together="always" top="51.515151515151516px" height="13.333333333333334px" width="478.7878787878788px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:AgencyName) or //SF424_Short:SF424_Short/SF424_Short:AgencyName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:AgencyName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named OrganizationName with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.696969696969697px" hyphenate="true" language="en" keep-together="always" top="255.75757575757578px" height="13.333333333333334px" width="478.7878787878788px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:OrganizationName) or //SF424_Short:SF424_Short/SF424_Short:OrganizationName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:OrganizationName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ApplicantCity with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.696969696969697px" hyphenate="true" language="en" keep-together="always" top="329.0909090909091px" height="13.333333333333334px" width="280px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:Address/globLib:City) or //SF424_Short:SF424_Short/SF424_Short:Address/globLib:City = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:Address/globLib:City"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the popup named ApplicantState with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.696969696969697px" hyphenate="true" language="en" keep-together="always" top="353.93939393939394px" height="13.333333333333334px" width="206.66666666666669px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:Address/globLib:State) or //SF424_Short:SF424_Short/SF424_Short:Address/globLib:State = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:Address/globLib:State"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the popup named ApplicantTypeCode1 with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="10.303030303030303px" hyphenate="true" language="en" keep-together="always" top="431.51515151515156px" height="13.333333333333334px" width="269.0909090909091px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ApplicantTypeCode1) or //SF424_Short:SF424_Short/SF424_Short:ApplicantTypeCode1 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ApplicantTypeCode1"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the popup named ApplicantTypeCode2 with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="10.303030303030303px" hyphenate="true" language="en" keep-together="always" top="456.3636363636364px" height="13.333333333333334px" width="269.0909090909091px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ApplicantTypeCode2) or //SF424_Short:SF424_Short/SF424_Short:ApplicantTypeCode2 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ApplicantTypeCode2"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the popup named ApplicantTypeCode3 with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="10.303030303030303px" hyphenate="true" language="en" keep-together="always" top="480.6060606060606px" height="13.333333333333334px" width="269.0909090909091px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ApplicantTypeCode3) or //SF424_Short:SF424_Short/SF424_Short:ApplicantTypeCode3 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ApplicantTypeCode3"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ApplicantTypeOtherSpecify with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="10.303030303030303px" hyphenate="true" language="en" keep-together="always" top="504.8484848484849px" height="13.333333333333334px" width="240px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ApplicantTypeOtherSpecify) or //SF424_Short:SF424_Short/SF424_Short:ApplicantTypeOtherSpecify = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ApplicantTypeOtherSpecify"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectDescription with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="10.303030303030303px" hyphenate="true" language="en" keep-together="always" top="586.6666666666667px" height="149.0909090909091px" width="558.1818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectDescription) or //SF424_Short:SF424_Short/SF424_Short:ProjectDescription = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ProjectDescription"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the popup named ApplicantCountry with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="10.90909090909091px" hyphenate="true" language="en" keep-together="always" top="378.7878787878788px" height="13.333333333333334px" width="246.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:Address/globLib:Country) or //SF424_Short:SF424_Short/SF424_Short:Address/globLib:Country = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:Address/globLib:Country"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ApplicantWebAddress with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="35.75757575757576px" hyphenate="true" language="en" keep-together="always" top="406.06060606060606px" height="13.333333333333334px" width="406.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ApplicantWebAddress) or //SF424_Short:SF424_Short/SF424_Short:ApplicantWebAddress = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ApplicantWebAddress"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectStartDate with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="143.03030303030303px" hyphenate="true" language="en" keep-together="always" top="735.7575757575758px" height="13.333333333333334px" width="86.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectStartDate) or //SF424_Short:SF424_Short/SF424_Short:ProjectStartDate = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//SF424_Short:SF424_Short/SF424_Short:ProjectStartDate"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectEndDate with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="283.03030303030306px" hyphenate="true" language="en" keep-together="always" top="735.7575757575758px" height="13.333333333333334px" width="86.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectEndDate) or //SF424_Short:SF424_Short/SF424_Short:ProjectEndDate = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//SF424_Short:SF424_Short/SF424_Short:ProjectEndDate"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ApplicantStreet2 with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="303.6363636363636px" hyphenate="true" language="en" keep-together="always" top="294.54545454545456px" height="23.03030303030303px" width="221.81818181818184px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:Address/globLib:Street2) or //SF424_Short:SF424_Short/SF424_Short:Address/globLib:Street2 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:Address/globLib:Street2"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ApplicantCounty with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="303.6363636363636px" hyphenate="true" language="en" keep-together="always" top="329.0909090909091px" height="13.333333333333334px" width="236.36363636363637px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:Address/globLib:County) or //SF424_Short:SF424_Short/SF424_Short:Address/globLib:County = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:Address/globLib:County"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ApplicantProvince with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="303.6363636363636px" hyphenate="true" language="en" keep-together="always" top="353.93939393939394px" height="13.333333333333334px" width="242.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:Address/globLib:Province) or //SF424_Short:SF424_Short/SF424_Short:Address/globLib:Province = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:Address/globLib:Province"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ApplicantZipCode with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="304.8484848484849px" hyphenate="true" language="en" keep-together="always" top="378.7878787878788px" height="13.333333333333334px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:Address/globLib:ZipPostalCode) or //SF424_Short:SF424_Short/SF424_Short:Address/globLib:ZipPostalCode = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:Address/globLib:ZipPostalCode"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named EmployerTaxpayerIdentificationNumber with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="305.4545454545455px" hyphenate="true" language="en" keep-together="always" top="431.51515151515156px" height="13.333333333333334px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:EmployerTaxpayerIdentificationNumber) or //SF424_Short:SF424_Short/SF424_Short:EmployerTaxpayerIdentificationNumber = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:EmployerTaxpayerIdentificationNumber"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named DUNSNumber with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="305.4545454545455px" hyphenate="true" language="en" keep-together="always" top="456.969696969697px" height="13.333333333333334px" width="110.30303030303031px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:DUNSNumber) or //SF424_Short:SF424_Short/SF424_Short:DUNSNumber = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:DUNSNumber"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named CongressionalDistrictApplicant with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="305.4545454545455px" hyphenate="true" language="en" keep-together="always" top="483.03030303030306px" height="13.333333333333334px" width="52.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:CongressionalDistrictApplicant) or //SF424_Short:SF424_Short/SF424_Short:CongressionalDistrictApplicant = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:CongressionalDistrictApplicant"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Write labels-->
					<!--Block below is for the label named -->
					<!--Block below is for the label named OpportunityIDTitle_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="8.484848484848484px" hyphenate="true" language="en" keep-together="always" top="170.9090909090909px" height="13.333333333333334px" width="31.515151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* TITLE:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d38574e2351-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="8.484848484848484px" hyphenate="true" language="en" keep-together="always" top="230.3030303030303px" height="13.333333333333334px" width="121.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">5. APPLICANT INFORMATION</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicantStreet1_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="8.484848484848484px" hyphenate="true" language="en" keep-together="always" top="282.42424242424244px" height="13.333333333333334px" width="41.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Street1:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named CFDANumber_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.090909090909092px" hyphenate="true" language="en" keep-together="always" top="66.06060606060606px" height="14.545454545454547px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">2. CATALOG OF FEDERAL DOMESTIC ASSISTANCE NUMBER:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named OpportunityID_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.090909090909092px" hyphenate="true" language="en" keep-together="always" top="144.84848484848484px" height="13.333333333333334px" width="154.54545454545456px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">* 4. FUNDING OPPORTUNITY NUMBER:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named OrganizationName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.090909090909092px" hyphenate="true" language="en" keep-together="always" top="244.24242424242425px" height="13.333333333333334px" width="115.15151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* a. Legal Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d38574e2565-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.090909090909092px" hyphenate="true" language="en" keep-together="always" top="269.0909090909091px" height="13.333333333333334px" width="90.90909090909092px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">b. Address:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectTitle_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.090909090909092px" hyphenate="true" language="en" keep-together="always" top="532.1212121212121px" height="13.333333333333334px" width="80.60606060606061px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* a. Project Title:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL8-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.090909090909092px" hyphenate="true" language="en" keep-together="always" top="735.7575757575758px" height="13.333333333333334px" width="80.60606060606061px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">c. Proposed Project:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL7-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.696969696969697px" hyphenate="true" language="en" keep-together="always" top="23.636363636363637px" height="12.727272727272728px" width="387.2727272727273px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">APPLICATION FOR FEDERAL DOMESTIC ASSISTANCE - Short Organizational</fo:block>
					</fo:block-container>
					<!--Block below is for the label named AgencyName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.696969696969697px" hyphenate="true" language="en" keep-together="always" top="38.18181818181819px" height="13.333333333333334px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">* 1. NAME OF FEDERAL AGENCY:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named CFDATitle_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.696969696969697px" hyphenate="true" language="en" keep-together="always" top="92.72727272727273px" height="13.333333333333334px" width="90.90909090909092px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">CFDA TITLE:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL5-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.696969696969697px" hyphenate="true" language="en" keep-together="always" top="129.69696969696972px" height="12.121212121212121px" width="95.75757575757576px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">* 3. DATE RECEIVED:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicantCity_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.696969696969697px" hyphenate="true" language="en" keep-together="always" top="317.5757575757576px" height="13.333333333333334px" width="25.454545454545457px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* City:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicantState_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.696969696969697px" hyphenate="true" language="en" keep-together="always" top="343.03030303030306px" height="13.333333333333334px" width="52.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* State:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectDescription_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="9.696969696969697px" hyphenate="true" language="en" keep-together="always" top="575.7575757575758px" height="13.333333333333334px" width="107.87878787878789px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* b. Project Description:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicantTypeCode1_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="10.303030303030303px" hyphenate="true" language="en" keep-together="always" top="420px" height="13.333333333333334px" width="213.93939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* d. Type of Applicant:  Select Applicant Type Code(s): </fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicantTypeCode2_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="10.303030303030303px" hyphenate="true" language="en" keep-together="always" top="444.24242424242425px" height="13.333333333333334px" width="68.48484848484848px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Type of Applicant:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="10.303030303030303px" hyphenate="true" language="en" keep-together="always" top="469.0909090909091px" height="13.333333333333334px" width="80px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Type of Applicant:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicantTypeOtherSpecify_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="10.303030303030303px" hyphenate="true" language="en" keep-together="always" top="493.33333333333337px" height="13.333333333333334px" width="86.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Other (specify):</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="10.303030303030303px" hyphenate="true" language="en" keep-together="always" top="518.7878787878789px" height="13.333333333333334px" width="138.1818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">6. PROJECT INFORMATION</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicantCountry_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="10.90909090909091px" hyphenate="true" language="en" keep-together="always" top="367.2727272727273px" height="13.333333333333334px" width="65.45454545454545px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Country:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicantWebAddress_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="10.90909090909091px" hyphenate="true" language="en" keep-together="always" top="393.93939393939394px" height="13.333333333333334px" width="68.48484848484848px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">c. Web Address:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL6-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="10.90909090909091px" hyphenate="true" language="en" keep-together="always" top="404.8484848484849px" height="14.545454545454547px" width="24.84848484848485px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">http://</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectStartDate_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="90.30303030303031px" hyphenate="true" language="en" keep-together="always" top="735.7575757575758px" height="13.333333333333334px" width="52.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Start Date:</fo:block>
					</fo:block-container>
					<!--<fo:block-container background-color="transparent" border-style="none" position="absolute" left="92.72727272727273px" hyphenate="true" language="en" keep-together="always" top="129.69696969696972px" height="13.333333333333334px" width="126.06060606060606px">
                  <fo:block background-color="transparent" color="#000000" font-size="6pt" font-style="normal" font-family="Helvetica" font-weight="normal">Completed Upon Submission to Grants.gov</fo:block>
               </fo:block-container>-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="92.72727272727273px" hyphenate="true" language="en" keep-together="always" top="129.69696969696972px" height="13.333333333333334px" width="126.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="6pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:DateReceived) or //SF424_Short:SF424_Short/SF424_Short:DateReceived = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//SF424_Short:SF424_Short/SF424_Short:DateReceived"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectEndDate_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="230.3030303030303px" hyphenate="true" language="en" keep-together="always" top="735.7575757575758px" height="13.333333333333334px" width="52.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* End Date:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL4-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="240px" hyphenate="true" language="en" keep-together="always" top="129.69696969696972px" height="13.333333333333334px" width="87.87878787878789px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">SYSTEM USE ONLY</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicantCounty_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="303.6363636363636px" hyphenate="true" language="en" keep-together="always" top="317.5757575757576px" height="13.333333333333334px" width="30.90909090909091px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">County:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicantProvince_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="303.6363636363636px" hyphenate="true" language="en" keep-together="always" top="343.03030303030306px" height="13.333333333333334px" width="38.18181818181819px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Province:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicantStreet2_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="304.24242424242425px" hyphenate="true" language="en" keep-together="always" top="283.03030303030306px" height="13.333333333333334px" width="41.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Street2:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicantZipCode_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="304.8484848484849px" hyphenate="true" language="en" keep-together="always" top="367.2727272727273px" height="13.333333333333334px" width="69.0909090909091px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Zip/Postal Code:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named EmployerId_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="305.4545454545455px" hyphenate="true" language="en" keep-together="always" top="420px" height="13.333333333333334px" width="202.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* e. Employer/Taxpayer Identification Number (EIN/TIN):</fo:block>
					</fo:block-container>
					<!--Block below is for the label named DUNSID_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="305.4545454545455px" hyphenate="true" language="en" keep-together="always" top="444.8484848484849px" height="13.333333333333334px" width="187.87878787878788px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* f. Organizational DUNS:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named CongressionalDistrict_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="305.4545454545455px" hyphenate="true" language="en" keep-together="always" top="470.90909090909093px" height="13.333333333333334px" width="163.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* g. Congressional District of  Applicant:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named omb_number-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="476.969696969697px" hyphenate="true" language="en" keep-together="always" top="3.6363636363636367px" height="12.121212121212121px" width="105.45454545454545px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 4040-0003</fo:block>
					</fo:block-container>
					<!--Block below is for the label named expiration_date-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="469.21212121212125px" hyphenate="true" language="en" keep-together="always" top="11.515151515151516px" height="12.121212121212121px" width="105.45454545454545px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Expiration Date: 01/31/2007</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d38574e1495-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="490.72727272727275px" hyphenate="true" language="en" keep-together="always" top="23.03030303030303px" height="13.333333333333334px" width="76.36363636363637px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Version 01</fo:block>
					</fo:block-container>
					<!--Draw lines-->
					<fo:block-container background-color="black" border-style="none" position="absolute" left="8.484848484848484px" top="21.81818181818182px" width="561.6060606060606px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="9.090909090909092px" top="37.57575757575758px" width="560.6060606060606px" height="0.2121212121212122px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="8.484848484848484px" top="64.84848484848486px" width="560.6060606060606px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="8.484848484848484px" top="229.0909090909091px" width="560.6060606060606px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="9.090909090909092px" top="243.63636363636365px" width="560.6060606060606px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="8.484848484848484px" top="316.3636363636364px" width="560.6060606060606px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="9.090909090909092px" top="268.4848484848485px" width="560.6060606060606px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="9.090909090909092px" top="341.8181818181818px" width="560.6060606060606px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="9.090909090909092px" top="366.6666666666667px" width="560.6060606060606px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="8.484848484848484px" top="392.72727272727275px" width="560.6060606060606px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="9.090909090909092px" top="419.39393939393943px" width="560.6060606060606px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="304.8484848484849px" top="444.8484848484849px" width="264.8484848484849px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="304.8484848484849px" top="470.90909090909093px" width="264.8484848484849px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="9.090909090909092px" top="518.1818181818182px" width="560.6060606060606px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="9.090909090909092px" top="575.7575757575758px" width="560.6060606060606px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="8.484848484848484px" top="735.1515151515152px" width="560.6060606060606px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="9.090909090909092px" top="749.0909090909091px" width="560.6060606060606px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="9.090909090909092px" top="532.1212121212121px" width="560.6060606060606px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="7.878787878787879px" top="23.03030303030303px" width="0.2121212121212122px" height="727.2727272727274px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="570.4848484848485px" top="23.636363636363637px" width="0.2121212121212122px" height="724.8484848484849px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="9.090909090909092px" top="128.4848484848485px" width="560.6060606060606px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="8.484848484848484px" top="143.63636363636365px" width="560.6060606060606px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="7.878787878787879px" top="281.8181818181818px" width="560.6060606060606px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="303.03030303030306px" top="283.6363636363636px" width="0.2121212121212122px" height="110.30303030303031px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="304.24242424242425px" top="421.21212121212125px" width="0.2121212121212122px" height="97.57575757575758px">
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
										<fo:inline font-size="6px" font-weight="bold">Received Date:  <xsl:value-of select="translate(/*/*/footer:ReceivedDateTime, 'T', ' ')"/> Time Zone: GMT-5
                              </fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<!--Data components-->
					<!--Block below is for the combobox named ProjectDirectorPrefixName with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="130.3030303030303px" height="13.333333333333334px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Name/globLib:PrefixName) or //SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Name/globLib:PrefixName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Name/globLib:PrefixName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectDirectorLastName with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="169.0909090909091px" height="23.03030303030303px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Name/globLib:LastName) or //SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Name/globLib:LastName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Name/globLib:LastName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectDirectorTitle with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="207.8787878787879px" height="23.03030303030303px" width="183.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Title) or //SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Title = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Title"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectDirectorPhoneNumber with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="248.4848484848485px" height="13.333333333333334px" width="202.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Phone) or //SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Phone = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Phone"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectDirectorStreet1 with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="278.1818181818182px" height="23.03030303030303px" width="221.81818181818184px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:Street1) or //SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:Street1 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:Street1"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectDirectorCity with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="316.969696969697px" height="13.333333333333334px" width="279.39393939393943px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:City) or //SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:City = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:City"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the popup named ProjectDirectorState with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="346.6666666666667px" height="13.333333333333334px" width="242.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:State) or //SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:State = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:State"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the popup named ProjectDirectorCountry with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="376.3636363636364px" height="13.333333333333334px" width="247.27272727272728px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:Country) or //SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:Country = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:Country"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the check named SameAsProjectDirector with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="435.1515151515152px" height="13.333333333333334px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal"/>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.242424242424242px" hyphenate="true" language="en" keep-together="always" top="433.1515151515152px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:SameAsProjectDirector) or //SF424_Short:SF424_Short/SF424_Short:SameAsProjectDirector = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="checkbox">
										<xsl:with-param name="value" select="//SF424_Short:SF424_Short/SF424_Short:SameAsProjectDirector"/>
										<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="432.1212121212121px" height="13.333333333333334px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal"/>
					</fo:block-container>
					<!--Block below is for the combobox named ContactPersonPrefixName with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="486.06060606060606px" height="13.333333333333334px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Name/globLib:PrefixName) or //SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Name/globLib:PrefixName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Name/globLib:PrefixName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ContactPersonLastName with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="525.4545454545455px" height="23.03030303030303px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Name/globLib:LastName) or //SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Name/globLib:LastName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Name/globLib:LastName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ContactPersonTitle with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="564.8484848484849px" height="23.03030303030303px" width="178.1818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Title) or //SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Title = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Title"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ContactPersonPhoneNumber with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="606.6666666666667px" height="13.333333333333334px" width="202.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Phone) or //SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Phone = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Phone"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ContactPersonStreet1 with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="636.969696969697px" height="23.03030303030303px" width="221.81818181818184px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:Street1) or //SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:Street1 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:Street1"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ContactPersonCity with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="675.7575757575758px" height="13.333333333333334px" width="280px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:City) or //SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:City = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:City"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the popup named ContactPersonState with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="703.6363636363636px" height="13.333333333333334px" width="246.66666666666669px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:State) or //SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:State = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:State"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the popup named ContactPersonCountry with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="733.3333333333334px" height="13.333333333333334px" width="247.27272727272728px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:Country) or //SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:Country = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:Country"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named FIELD1 with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.848484848484849px" hyphenate="true" language="en" keep-together="always" top="30.90909090909091px" height="13.333333333333334px" width="314.54545454545456px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold"/>
					</fo:block-container>
					<!--Block below is for the field named ProjectDirectorSSN with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.848484848484849px" hyphenate="true" language="en" keep-together="always" top="76.96969696969697px" height="13.333333333333334px" width="89.6969696969697px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorSSN) or //SF424_Short:SF424_Short/SF424_Short:ProjectDirectorSSN = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorSSN"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ContactPersonFirstName with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="95.75757575757576px" hyphenate="true" language="en" keep-together="always" top="485.4545454545455px" height="23.03030303030303px" width="139.3939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Name/globLib:FirstName) or //SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Name/globLib:FirstName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Name/globLib:FirstName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectDirectorFirstName with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="116.96969696969698px" hyphenate="true" language="en" keep-together="always" top="130.3030303030303px" height="23.03030303030303px" width="144.24242424242425px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Name/globLib:FirstName) or //SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Name/globLib:FirstName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Name/globLib:FirstName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectDirectorMiddleName with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="130.3030303030303px" height="13.333333333333334px" width="202.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Name/globLib:MiddleName) or //SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Name/globLib:MiddleName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Name/globLib:MiddleName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the combobox named ProjectDirectorSuffixName with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="169.0909090909091px" height="13.333333333333334px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Name/globLib:SuffixName) or //SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Name/globLib:SuffixName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Name/globLib:SuffixName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectDirectorEmail with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="207.8787878787879px" height="23.03030303030303px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Email) or //SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Email = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Email"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectDirectorFaxNumber with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="248.4848484848485px" height="13.333333333333334px" width="202.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Fax) or //SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Fax = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Fax"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectDirectorStreet2 with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="278.1818181818182px" height="23.03030303030303px" width="221.81818181818184px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:Street2) or //SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:Street2 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:Street2"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectDirectorCounty with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="316.969696969697px" height="13.333333333333334px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:County) or //SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:County = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:County"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectDirectorProvince with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="346.06060606060606px" height="13.333333333333334px" width="242.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:Province) or //SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:Province = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:Province"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectDirectorZipCode with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="376.3636363636364px" height="13.333333333333334px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:ZipPostalCode) or //SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:ZipPostalCode = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ProjectDirectorGroup/globLib:Address/globLib:ZipPostalCode"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ContactPersonMiddleName with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="485.4545454545455px" height="13.333333333333334px" width="202.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Name/globLib:MiddleName) or //SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Name/globLib:MiddleName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Name/globLib:MiddleName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the combobox named ContactPersonSuffixName with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="525.4545454545455px" height="13.333333333333334px" width="82.42424242424242px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Name/globLib:SuffixName) or //SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Name/globLib:SuffixName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Name/globLib:SuffixName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ContactPersonEmail with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="564.8484848484849px" height="23.03030303030303px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Email) or //SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Email = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Email"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ContactPersonFaxNumber with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="606.6666666666667px" height="13.333333333333334px" width="202.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Fax) or //SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Fax = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Fax"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ContactPersonStreet2 with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="636.969696969697px" height="23.03030303030303px" width="221.81818181818184px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:Street2) or //SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:Street2 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:Street2"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ContactPersonCounty with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="675.7575757575758px" height="13.333333333333334px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:County) or //SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:County = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:County"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ContactPersonProvince with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="703.6363636363636px" height="13.333333333333334px" width="242.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:Province) or //SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:Province = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:Province"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ContactPersonZipCode with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="733.3333333333334px" height="13.333333333333334px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:ZipPostalCode) or //SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:ZipPostalCode = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ContactPersonGroup/globLib:Address/globLib:ZipPostalCode"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ContactPersonSSN with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="299.39393939393943px" hyphenate="true" language="en" keep-together="always" top="420px" height="13.333333333333334px" width="90.90909090909092px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ContactPersonSSN) or //SF424_Short:SF424_Short/SF424_Short:ContactPersonSSN = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:ContactPersonSSN"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named FIELD2 with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="522.4242424242425px" hyphenate="true" language="en" keep-together="always" top="29.090909090909093px" height="13.333333333333334px" width="52.72727272727273px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"/>
					</fo:block-container>
					<!--Write labels-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="30.636363636363637px" height="12.727272727272728px" width="387.2727272727273px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">APPLICATION FOR FEDERAL DOMESTIC ASSISTANCE - Short Organizational</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="495.72727272727275px" hyphenate="true" language="en" keep-together="always" top="30.03030303030303px" height="13.333333333333334px" width="76.36363636363637px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Version 01</fo:block>
					</fo:block-container>
					<!--Block below is for the label named -->
					<!--Block below is for the label named LABEL5-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="47.87878787878788px" height="13.333333333333334px" width="93.93939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">7. PROJECT DIRECTOR</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectDirectorSSN_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="63.03030303030303px" height="13.333333333333334px" width="151.51515151515153px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Social Security Number (SSN) - Optional:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="94.54545454545455px" height="12.121212121212121px" width="667.8787878787879px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Disclosure of SSN is voluntary.  Please see the application package instructions for the agency's authority and routine uses of the data.</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectDirectorPrefixName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="116.36363636363637px" height="13.333333333333334px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Prefix:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectDirectorLastName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="155.15151515151516px" height="13.333333333333334px" width="51.515151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Last Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectDirectorTitle_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="196.96969696969697px" height="13.333333333333334px" width="60.60606060606061px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Title: </fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectDirectorPhoneNumber_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="234.54545454545456px" height="13.333333333333334px" width="78.18181818181819px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Telephone Number:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectDirectorStreet1_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="264.24242424242425px" height="13.333333333333334px" width="66.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Street1:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectDirectorCity_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="303.03030303030306px" height="13.333333333333334px" width="46.66666666666667px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* City:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectDirectorState_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="332.72727272727275px" height="13.333333333333334px" width="51.515151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* State:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectDirectorCountry_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="362.42424242424244px" height="13.333333333333334px" width="43.63636363636364px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Country:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="391.51515151515156px" height="15.151515151515152px" width="223.63636363636365px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">8. PRIMARY CONTACT/GRANTS ADMINISTRATOR</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ContactPersonPrefixName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="472.1212121212121px" height="13.333333333333334px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Prefix:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ContactPersonLastName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="511.51515151515156px" height="13.333333333333334px" width="86.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Last Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ContactPersonTitle_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="550.909090909091px" height="13.333333333333334px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Title: </fo:block>
					</fo:block-container>
					<!--Block below is for the label named ContactPersonPhoneNumber_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="592.7272727272727px" height="13.333333333333334px" width="139.3939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Telephone Number:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ContactPersonStreet1_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="623.0303030303031px" height="13.333333333333334px" width="66.66666666666667px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Street1:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ContactPersonCity_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="661.8181818181819px" height="13.333333333333334px" width="47.27272727272727px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* City:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ContactPersonState_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="689.6969696969697px" height="13.333333333333334px" width="52.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* State:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ContactPersonCountry_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="719.3939393939394px" height="13.333333333333334px" width="66.66666666666667px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Country:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named SameAsProjectDirector_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="24.84848484848485px" hyphenate="true" language="en" keep-together="always" top="435.1515151515152px" height="13.333333333333334px" width="229.0909090909091px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Same as Project Director (skip to item 9):</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="15.27272727272727px" hyphenate="true" language="en" keep-together="always" top="435.1212121212121px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274F;</fo:inline>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ContactPersonFirstName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="95.75757575757576px" hyphenate="true" language="en" keep-together="always" top="471.51515151515156px" height="13.333333333333334px" width="90.90909090909092px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* First Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectDirectorFirstName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="116.96969696969698px" hyphenate="true" language="en" keep-together="always" top="116.36363636363637px" height="13.333333333333334px" width="90.90909090909092px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* First Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectDirectorMiddleName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="116.36363636363637px" height="13.333333333333334px" width="100.60606060606061px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Middle Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectDirectorSuffixName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="155.15151515151516px" height="13.333333333333334px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Suffix:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectDirectorEmail_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="193.93939393939394px" height="13.333333333333334px" width="61.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Email:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectDirectorFaxNumber_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="234.54545454545456px" height="13.333333333333334px" width="53.939393939393945px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Fax Number:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectDirectorStreet2_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="264.24242424242425px" height="13.333333333333334px" width="32.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Street2:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectDirectorCounty_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="303.6363636363636px" height="13.333333333333334px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">County:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectDirectorProvince_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="332.72727272727275px" height="13.333333333333334px" width="76.36363636363637px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Province:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ProjectDirectorZipCode_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="362.42424242424244px" height="13.333333333333334px" width="70.9090909090909px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Zip/Postal Code:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ContactPersonSSN_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="407.2727272727273px" height="13.333333333333334px" width="154.54545454545456px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Social Security Number (SSN) - Optional:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ContactPersonMiddleName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="471.51515151515156px" height="13.333333333333334px" width="100.60606060606061px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Middle Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ContactPersonSuffixName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="511.51515151515156px" height="13.333333333333334px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Suffix:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ContactPersonEmail_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="550.909090909091px" height="13.333333333333334px" width="52.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Email:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ContactPersonFaxNumber_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="592.7272727272727px" height="13.333333333333334px" width="90.90909090909092px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Fax Number:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ContactPersonStreet2_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="623.0303030303031px" height="13.333333333333334px" width="66.66666666666667px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Street2:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ContactPersonCounty_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="661.8181818181819px" height="13.333333333333334px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">County:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ContactPersonProvince_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="689.6969696969697px" height="13.333333333333334px" width="76.36363636363637px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Province:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ContactPersonZipCode_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="296.969696969697px" hyphenate="true" language="en" keep-together="always" top="719.3939393939394px" height="13.333333333333334px" width="129.69696969696972px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Zip/Postal Code:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL3-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="299.39393939393943px" hyphenate="true" language="en" keep-together="always" top="434.54545454545456px" height="27.87878787878788px" width="259.39393939393943px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Disclosure of SSN is voluntary.  Please see the application package instructions for the agency's authority and routine uses of the data.</fo:block>
					</fo:block-container>
					<!--Block below is for the label named omb_number-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="483.03030303030306px" hyphenate="true" language="en" keep-together="always" top="6.666666666666667px" height="12.121212121212121px" width="105.45454545454545px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 4040-0003</fo:block>
					</fo:block-container>
					<!--Block below is for the label named expiration_date-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="475.7727272727273px" hyphenate="true" language="en" keep-together="always" top="14.545454545454547px" height="12.121212121212121px" width="105.45454545454545px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Expiration Date: 01/31/2007</fo:block>
					</fo:block-container>
					<!--Draw lines-->
					<fo:block-container background-color="black" border-style="none" position="absolute" left="4.848484848484849px" top="390.3030303030303px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="3.6363636363636367px" top="406.6666666666667px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="4.242424242424242px" top="469.69696969696975px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="295.75757575757575px" top="114.54545454545455px" width="0.2121212121212122px" height="276.969696969697px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="296.3636363636364px" top="407.8787878787879px" width="0.2121212121212122px" height="340px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="4.848484848484849px" top="508.4848484848485px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="3.6363636363636367px" top="549.0909090909091px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="3.0303030303030303px" top="587.2727272727273px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="3.0303030303030303px" top="620.6060606060606px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="4.848484848484849px" top="660px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="4.848484848484849px" top="687.8787878787879px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="4.848484848484849px" top="717.5757575757576px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="3.0303030303030303px" top="747.6666666666667px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="3.0303030303030303px" top="29.090909090909093px" width="0.2121212121212122px" height="717.5757575757576px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="575.7575757575758px" top="29.090909090909093px" width="0.2121212121212122px" height="718.1818181818182px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="3.6363636363636367px" top="113.93939393939395px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="3.6363636363636367px" top="45.45454545454546px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="4.848484848484849px" top="28.484848484848488px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="4.242424242424242px" top="153.33333333333334px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="3.6363636363636367px" top="192.12121212121212px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="4.848484848484849px" top="231.51515151515153px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="3.6363636363636367px" top="262.42424242424244px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="4.242424242424242px" top="301.21212121212125px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="4.848484848484849px" top="330.3030303030303px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="3.6363636363636367px" top="360.6060606060606px" width="571.5151515151515px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>                                        
					<fo:block-container background-color="black" border-style="none" position="absolute" left="3.6363636363636367px" top="61.81818181818182px" width="571.5151515151515px" height="0.2121212121212122px">
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
										<fo:inline font-size="6px" font-weight="bold">Received Date:  <xsl:value-of select="translate(/*/*/footer:ReceivedDateTime, 'T', ' ')"/> Time Zone: GMT-5
                              </fo:inline>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<!--Data components-->
					<!--Block below is for the field named ApplicationCertification_Required with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="93.33333333333334px" height="13.93939393939394px" width="40.60606060606061px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"/>
					</fo:block-container>
					<!--Block below is for the combobox named AuthorizedRepresentativePrefixName with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="154.54545454545456px" height="13.333333333333334px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentative/globLib:PrefixName) or //SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentative/globLib:PrefixName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentative/globLib:PrefixName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named AuthorizedRepresentativeLastName with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="193.93939393939394px" height="23.03030303030303px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentative/globLib:LastName) or //SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentative/globLib:LastName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentative/globLib:LastName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named AuthorizedRepresentativeTitle with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="233.33333333333334px" height="23.03030303030303px" width="183.03030303030303px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentativeTitle) or //SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentativeTitle = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentativeTitle"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named AuthorizedRepresentativePhoneNumber with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="272.72727272727275px" height="13.333333333333334px" width="202.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentativePhoneNumber) or //SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentativePhoneNumber = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentativePhoneNumber"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named FIELD1 with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.848484848484849px" hyphenate="true" language="en" keep-together="always" top="40.60606060606061px" height="13.333333333333334px" width="309.0909090909091px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold"/>
					</fo:block-container>
					<!--Block below is for the check named ApplicationCertification with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="47.27272727272727px" hyphenate="true" language="en" keep-together="always" top="93.33333333333334px" height="12.121212121212121px" width="18.181818181818183px">
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
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="47.27272727272727px" hyphenate="true" language="en" keep-together="always" top="90.30303030303031px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:ApplicationCertification) or //SF424_Short:SF424_Short/SF424_Short:ApplicationCertification = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="checkbox">
										<xsl:with-param name="value" select="//SF424_Short:SF424_Short/SF424_Short:ApplicationCertification"/>
										<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named AuthorizedRepresentativeFirstName with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="101.81818181818183px" hyphenate="true" language="en" keep-together="always" top="154.54545454545456px" height="23.03030303030303px" width="144.24242424242425px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentative/globLib:FirstName) or //SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentative/globLib:FirstName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentative/globLib:FirstName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named AuthorizedRepresentativeMiddleName with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="300px" hyphenate="true" language="en" keep-together="always" top="154.54545454545456px" height="13.333333333333334px" width="202.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentative/globLib:MiddleName) or //SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentative/globLib:MiddleName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentative/globLib:MiddleName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the combobox named AuthorizedRepresentativeSuffixName with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="300px" hyphenate="true" language="en" keep-together="always" top="193.93939393939394px" height="13.333333333333334px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentative/globLib:SuffixName) or //SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentative/globLib:SuffixName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentative/globLib:SuffixName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named AuthorizedRepresentativeEmail with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="300px" hyphenate="true" language="en" keep-together="always" top="233.33333333333334px" height="23.03030303030303px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentativeEmail) or //SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentativeEmail = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentativeEmail"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named AuthorizedRepresentativeFaxNumber with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="300px" hyphenate="true" language="en" keep-together="always" top="272.72727272727275px" height="13.333333333333334px" width="202.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentativeFaxNumber) or //SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentativeFaxNumber = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentativeFaxNumber"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named FIELD2 with FieldID -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="519.3939393939394px" hyphenate="true" language="en" keep-together="always" top="39.3939393939394px" height="13.333333333333334px" width="57.57575757575758px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"/>
					</fo:block-container>
					<!--Write labels-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="3.6363636363636367px" hyphenate="true" language="en" keep-together="always" top="40.636363636363637px" height="12.727272727272728px" width="387.2727272727273px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">APPLICATION FOR FEDERAL DOMESTIC ASSISTANCE - Short Organizational</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="495.72727272727275px" hyphenate="true" language="en" keep-together="always" top="40.636363636363637px" height="13.333333333333334px" width="76.36363636363637px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Version 01</fo:block>
					</fo:block-container>
					<!--Block below is for the label named -->
					<!--Block below is for the label named LABEL1-->
					<!--<fo:block-container background-color="transparent" border-style="none" position="absolute" left="3.6363636363636367px" hyphenate="true" language="en" keep-together="always" top="319.5757575757576px" height="12.121212121212121px" width="115.15151515151516px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Previous Edition Usable</fo:block>
               </fo:block-container> -->
					<!--Block below is for the label named LABEL2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="3.6363636363636367px" hyphenate="true" language="en" keep-together="always" top="319.5757575757576px" height="12.121212121212121px" width="132.12121212121212px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Authorized for Local Reproduction</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL5-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="55.75757575757576px" height="34.54545454545455px" width="572.7272727272727px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">9. * By signing this application, I certify (1) to the statements contained in the list of certifications** and (2) that the statements herein are true, complete and accurate to the best of my knowledge.  I also provide the required assurances** and agree to comply with any resulting terms if I accept an award.  I am aware that any false, fictitious, or fraudulent statements or claims may subject me to criminal, civil, or administrative penalties (U.S. Code, Title 218, Section 1001)</fo:block>
					</fo:block-container>
					<!--Block below is for the label named ApplicationCertification_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="93.33333333333334px" height="13.93939393939394px" width="42.42424242424243px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">** I Agree</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d38574e9413-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="108.48484848484848px" height="13.333333333333334px" width="570.3030303030304px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">** The list of certifications and assurances, or an internet site where you may obtain this list, is contained in the announcement or agency specific instructions.</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d38574e9520-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="124.84848484848486px" height="13.333333333333334px" width="202.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">AUTHORIZED REPRESENTATIVE</fo:block>
					</fo:block-container>
					<!--Block below is for the label named AuthorizedRepresentativePrefixName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="140.60606060606062px" height="13.333333333333334px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Prefix:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named AuthorizedRepresentativeLastName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="180px" height="13.333333333333334px" width="86.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Last Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named AuthorizedRepresentativeTitle_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="219.3939393939394px" height="13.333333333333334px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Title: </fo:block>
					</fo:block-container>
					<!--Block below is for the label named AuthorizedRepresentativePhoneNumber_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="258.7878787878788px" height="13.333333333333334px" width="139.3939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Telephone Number:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named AuthorizedRepresentativeSignature1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="288.4848484848485px" height="13.333333333333334px" width="151.51515151515153px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Signature of Authorized Representative:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named AuthorizedRepresentativeSignature-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="4.242424242424242px" hyphenate="true" language="en" keep-together="always" top="300.6060606060606px" height="13.333333333333334px" width="126.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="6pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentativeSignature) or //SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentativeSignature = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentativeSignature"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the label named AuthorizedRepresentativeFirstName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="101.81818181818183px" hyphenate="true" language="en" keep-together="always" top="140.60606060606062px" height="13.333333333333334px" width="90.90909090909092px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* First Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named AuthorizedRepresentativeMiddleName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="300px" hyphenate="true" language="en" keep-together="always" top="140.60606060606062px" height="13.333333333333334px" width="100.60606060606061px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Middle Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named AuthorizedRepresentativeSuffixName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="300px" hyphenate="true" language="en" keep-together="always" top="180px" height="13.333333333333334px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Suffix:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named AuthorizedRepresentativeEmail_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="300px" hyphenate="true" language="en" keep-together="always" top="219.3939393939394px" height="13.333333333333334px" width="52.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Email:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named AuthorizedRepresentativeFaxNumber_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="300px" hyphenate="true" language="en" keep-together="always" top="258.7878787878788px" height="13.333333333333334px" width="90.90909090909092px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Fax Number:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named AuthorizedRepresentativeDateSigned1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="300px" hyphenate="true" language="en" keep-together="always" top="288.4848484848485px" height="13.333333333333334px" width="101.81818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Date Signed:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named AuthorizedRepresentativeDateSigned-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="300px" hyphenate="true" language="en" keep-together="always" top="302.42424242424244px" height="13.333333333333334px" width="126.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="6pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentativeDateSigned) or //SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentativeDateSigned = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="formatDate">
										<xsl:with-param name="value" select="//SF424_Short:SF424_Short/SF424_Short:AuthorizedRepresentativeDateSigned"/>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL6-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="350.90909090909093px" hyphenate="true" language="en" keep-together="always" top="319.1818181818182px" height="12.121212121212121px" width="226.66666666666669px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Standard Form 424 Organization Short  (04-2005)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="350.90909090909093px" hyphenate="true" language="en" keep-together="always" top="330.1818181818182px" height="12.121212121212121px" width="226.66666666666669px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Prescribed by OMB Circular A-102</fo:block>
					</fo:block-container>
					<!--Block below is for the label named omb_number-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="484.24242424242425px" hyphenate="true" language="en" keep-together="always" top="18.78787878787879px" height="12.121212121212121px" width="105.45454545454545px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 4040-0003</fo:block>
					</fo:block-container>
					<!--Block below is for the label named expiration_date-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="476.4848484848485px" hyphenate="true" language="en" keep-together="always" top="26.060606060606062px" height="12.121212121212121px" width="105.45454545454545px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Expiration Date: 01/31/2007</fo:block>
					</fo:block-container>
					<!--Draw lines-->
					<fo:block-container background-color="black" border-style="none" position="absolute" left="298.1818181818182px" top="138.78787878787878px" width="0.2121212121212122px" height="178.7878787878788px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="2.4242424242424243px" top="178.1818181818182px" width="575.7575757575758px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="2.4242424242424243px" top="217.5757575757576px" width="575.7575757575758px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="2.4242424242424243px" top="256.969696969697px" width="575.7575757575758px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="2.4242424242424243px" top="286.6666666666667px" width="575.7575757575758px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="3.6363636363636367px" top="317.3636363636364px" width="574.5454545454546px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="2.4242424242424243px" top="53.939393939393945px" width="575.7575757575758px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="3.6363636363636367px" top="38.78787878787879px" width="573.3333333333334px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="2.4242424242424243px" top="91.51515151515152px" width="575.7575757575758px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="2.4242424242424243px" top="123.03030303030303px" width="575.7575757575758px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="2.4242424242424243px" top="138.78787878787878px" width="575.7575757575758px" height="0.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="2.4242424242424243px" top="38.78787878787879px" width="0.2121212121212122px" height="279.39393939393943px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="577.969696969697px" top="39.3939393939394px" width="0.2121212121212122px" height="278.1818181818182px">
                                            <fo:block/>
                                        </fo:block-container>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	<xsl:template name="radioButton">
		<xsl:param name="value"/>
		<xsl:param name="schemaChoice">Yes</xsl:param>
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
		<xsl:param name="schemaChoice">Yes</xsl:param>
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
