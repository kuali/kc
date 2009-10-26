<?xml version="1.0" encoding="UTF-8"?>
<!-- $Revision:   1.5  $ -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:NASA_SeniorKeyPersonSupplementalDataSheet="http://apply.grants.gov/forms/NASA_SenKPSupDtSht-V1.0">
	<xsl:output method="xml" indent="yes"/>
	<xsl:variable name="fo:layout-master-set">
		<fo:layout-master-set>
			<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.5in" margin-right="0.5in">
				<fo:region-body margin-top="0.5in" margin-bottom="0.5in" font-family="Helvetica,Times,Courier" font-size="14pt"/>
				<fo:region-after extent=".5in"/>
			</fo:simple-page-master>
		</fo:layout-master-set>
	</xsl:variable>
	<xsl:template match="NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet">
		<fo:root>
			<xsl:copy-of select="$fo:layout-master-set"/>
			<fo:page-sequence master-reference="default-page" format="1" initial-page-number="1">
				<fo:static-content flow-name="xsl-region-after">
					<fo:block>
						<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber" /></fo:inline>
					</fo:block>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body" font-family="Helvetica,Times,Courier">
					<!--Block below is for the combobox named Prefix with FieldID 2-1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="76.96969696969697px" hyphenate="true" language="en" keep-together="always" top="123.63636363636364px" height="13.333333333333334px" width="86.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonName/globLib:PrefixName) or //NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonName/globLib:PrefixName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonName/globLib:PrefixName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named FirstName with FieldID 2-2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="76.96969696969697px" hyphenate="true" language="en" keep-together="always" top="136.96969696969697px" height="13.333333333333334px" width="280px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonName/globLib:FirstName) or //NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonName/globLib:FirstName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonName/globLib:FirstName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named MiddleName with FieldID 2-3-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="76.96969696969697px" hyphenate="true" language="en" keep-together="always" top="150.3030303030303px" height="13.333333333333334px" width="201.81818181818184px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonName/globLib:MiddleName) or //NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonName/globLib:MiddleName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonName/globLib:MiddleName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named LastName with FieldID 2-4-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="76.96969696969697px" hyphenate="true" language="en" keep-together="always" top="163.63636363636365px" height="13.333333333333334px" width="478.7878787878788px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonName/globLib:LastName) or //NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonName/globLib:LastName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonName/globLib:LastName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the combobox named Suffix with FieldID 2-5-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="76.96969696969697px" hyphenate="true" language="en" keep-together="always" top="176.96969696969697px" height="13.333333333333334px" width="86.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonName/globLib:SuffixName) or //NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonName/globLib:SuffixName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonName/globLib:SuffixName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named StatementofCommitment0 with FieldID 3-0-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="123.63636363636364px" hyphenate="true" language="en" keep-together="always" top="351.51515151515156px" height="12.121212121212121px" width="390.666666666667px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:StatementofCommitment/att:AttachedFile/att:FileName) or //NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:StatementofCommitment/att:AttachedFile/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:StatementofCommitment/att:AttachedFile/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named BudgetDetails0 with FieldID 3-1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="123.63636363636364px" hyphenate="true" language="en" keep-together="always" top="365.4545454545455px" height="12.121212121212121px" width="390.909090909091px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:BudgetDetails/att:AttachedFile/att:FileName) or //NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:BudgetDetails/att:AttachedFile/att:FileName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:BudgetDetails/att:AttachedFile/att:FileName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the popup named FederalAgency with FieldID 2-9-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="169.0909090909091px" hyphenate="true" language="en" keep-together="always" top="263.03030303030306px" height="13.333333333333334px" width="367.2727272727273px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:FederalAgency) or //NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:FederalAgency = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:FederalAgency"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named FederalAgencyDollar with FieldID 2-10-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="194.54545454545456px" hyphenate="true" language="en" keep-together="always" top="281.8181818181818px" height="12.121212121212121px" width="115.15151515151516px">
						<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:FederalAgencyDollar) or //NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:FederalAgencyDollar = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="format-number(//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:FederalAgencyDollar, '#,##0.00')"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the popup named NASACo_Itype with FieldID 2-7-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="336.969696969697px" hyphenate="true" language="en" keep-together="always" top="200.60606060606062px" height="13.333333333333334px" width="197.5757575757576px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:NASACo-Itype) or //NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:NASACo-Itype = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:NASACo-Itype"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the radio named USGovernmentParticipation_Yes with FieldID 2-8-1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="411.51515151515156px" hyphenate="true" language="en" keep-together="always" top="236.96969696969697px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:USGovernmentParticipation"/>
										<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the radio named InternationalParticipation_Yes with FieldID 2-11-1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="416.969696969697px" hyphenate="true" language="en" keep-together="always" top="306.06060606060606px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:InternationalParticipation"/>
										<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the radio named USGovernmentParticipation_No with FieldID 2-8-2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="520px" hyphenate="true" language="en" keep-together="always" top="236.96969696969697px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:USGovernmentParticipation"/>
										<xsl:with-param name="schemaChoice">N: No</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the radio named InternationalParticipation_No with FieldID 2-11-2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="534.5454545454546px" hyphenate="true" language="en" keep-together="always" top="306.06060606060606px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="boolean(0)">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[1]/NASA_SeniorKeyPersonSupplementalDataSheet:InternationalParticipation"/>
										<xsl:with-param name="schemaChoice">N: No</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Write labels-->
					<!--Block below is for the label named -->
					<!--Block below is for the label named FirstName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="136.96969696969697px" height="13.333333333333334px" width="54.54545454545455px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* First Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LastName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="163.63636363636365px" height="13.333333333333334px" width="52.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Last Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named USGovernmentParticipation_Lbl-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="236.96969696969697px" height="13.333333333333334px" width="303.03030303030306px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Is this person participating in this project as an employee of the U.S. Government?</fo:block>
					</fo:block-container>
					<!--Block below is for the label named InternationalParticipation_Lbl-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="306.06060606060606px" height="13.333333333333334px" width="301.21212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Is this person participating in this project as an employee of a foreign organization?</fo:block>
					</fo:block-container>
					<!--Block below is for the label named FederalAgency_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="18.181818181818183px" hyphenate="true" language="en" keep-together="always" top="263.03030303030306px" height="13.333333333333334px" width="150.9090909090909px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* If yes, select U.S. Government agency:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named StatementofCommitment_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.212121212121215px" hyphenate="true" language="en" keep-together="always" top="351.51515151515156px" height="13.333333333333334px" width="102.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Statement of Commitment </fo:block>
					</fo:block-container>
					<!--Block below is for the label named BudgetDetails_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.212121212121215px" hyphenate="true" language="en" keep-together="always" top="365.4545454545455px" height="13.333333333333334px" width="58.78787878787879px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Budget Details </fo:block>
					</fo:block-container>
					<!--Block below is for the label named SeniorKeyPerson-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="23.03030303030303px" hyphenate="true" language="en" keep-together="always" top="101.21212121212122px" height="13.333333333333334px" width="98.18181818181819px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Senior/Key Person Name</fo:block>
					</fo:block-container>
					<!--Block below is for the label named Prefix_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="23.03030303030303px" hyphenate="true" language="en" keep-together="always" top="123.63636363636364px" height="13.333333333333334px" width="27.87878787878788px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Prefix:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named MiddleName_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="23.03030303030303px" hyphenate="true" language="en" keep-together="always" top="150.3030303030303px" height="13.333333333333334px" width="55.75757575757576px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Middle Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named Suffix_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="23.03030303030303px" hyphenate="true" language="en" keep-together="always" top="176.96969696969697px" height="13.333333333333334px" width="30.303030303030305px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Suffix:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named NASACo_Itype_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="23.03030303030303px" hyphenate="true" language="en" keep-together="always" top="200.60606060606062px" height="13.333333333333334px" width="299.39393939393943px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">If this Senior/Key Person's role is Co-PI/PD, please choose the type of NASA Co-I:  </fo:block>
					</fo:block-container>
					<!--Block below is for the label named FederalAgencyDollar_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="23.03030303030303px" hyphenate="true" language="en" keep-together="always" top="281.8181818181818px" height="13.333333333333334px" width="171.51515151515153px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">If yes, enter total dollar amount requested ($) :   </fo:block>
					</fo:block-container>
					<!--Block below is for the label named title-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="124.24242424242425px" hyphenate="true" language="en" keep-together="always" top="48.484848484848484px" height="20px" width="334.54545454545456px">
						<fo:block background-color="transparent" color="#000000" font-size="14pt" font-style="normal" font-family="Helvetica" font-weight="normal">NASA - Senior/Key Person Supplemental Data Sheet</fo:block>
					</fo:block-container>
					<!--Block below is for the label named InternationalParticipation_Yes_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="321.8181818181818px" hyphenate="true" language="en" keep-together="always" top="306.06060606060606px" height="13.333333333333334px" width="95.15151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Foreign Organization Yes</fo:block>
					</fo:block-container>
					<!--Block below is for the label named USGovernmentParticipation_Yes_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="331.51515151515156px" hyphenate="true" language="en" keep-together="always" top="236.96969696969697px" height="13.333333333333334px" width="80.60606060606061px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">U.S. Government Yes</fo:block>
					</fo:block-container>
					<!--Block below is for the label named USGovernmentParticipation_No_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="443.03030303030306px" hyphenate="true" language="en" keep-together="always" top="236.96969696969697px" height="13.333333333333334px" width="76.96969696969697px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">U.S. Government No</fo:block>
					</fo:block-container>
					<!--Block below is for the label named InternationalParticipation_No_LBL-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="445.4545454545455px" hyphenate="true" language="en" keep-together="always" top="306.06060606060606px" height="13.333333333333334px" width="89.6969696969697px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Foreign Organization No</fo:block>
					</fo:block-container>
					<!--Block below is for the label named d1e2115-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="469.69696969696975px" hyphenate="true" language="en" keep-together="always" top="12.121212121212121px" height="13.333333333333334px" width="95.75757575757576px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number:  2700-0085 </fo:block>
					</fo:block-container>
					<!--Block below is for the label named omb_number-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="469.69696969696975px" hyphenate="true" language="en" keep-together="always" top="24.242424242424242px" height="13.333333333333334px" width="95.75757575757576px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number:  2700-0087 </fo:block>
					</fo:block-container>
					<!--Draw lines-->
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.90909090909091px" top="92.72727272727273px" width="555.1515151515151px" height="0.6060606060606061px">
                        <fo:block/>
                   </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.90909090909091px" top="336.969696969697px" width="555.1515151515151px" height="0.6060606060606061px">
                        <fo:block/>
                   </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.90909090909091px" top="389.69696969696975px" width="555.1515151515151px" height="0.6060606060606061px">
                        <fo:block/>
                   </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="10.90909090909091px" top="92.12121212121212px" width="0.6060606060606061px" height="298.7878787878788px">
                        <fo:block/>
                   </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="565.4545454545455px" top="92.72727272727273px" width="0.6060606060606061px" height="298.7878787878788px">
                        <fo:block/>
                   </fo:block-container>
					<!--Extract Att-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="23.03030303030303px" hyphenate="true" language="en" keep-together="always" top="446.66666666666669px" height="12.121212121212121px" width="52.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Important:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LabelAtt01-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="23.03030303030303px" hyphenate="true" language="en" keep-together="always" top="501.21212121212122px" height="13.333333333333334px" width="125.15151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">1) Please attach Attachment 1</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LabelAtt02-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="23.03030303030303px" hyphenate="true" language="en" keep-together="always" top="516.36363636363637px" height="13.333333333333334px" width="125.15151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">2) Please attach Attachment 2</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LabelAtt03-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="23.03030303030303px" hyphenate="true" language="en" keep-together="always" top="531.51515151515153px" height="13.333333333333334px" width="125.15151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">3) Please attach Attachment 3</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LabelAtt04-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="23.03030303030303px" hyphenate="true" language="en" keep-together="always" top="546.66666666666669px" height="13.333333333333334px" width="125.15151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">4) Please attach Attachment 4</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="61.81818181818182px" hyphenate="true" language="en" keep-together="always" top="446.66666666666669px" height="12.121212121212121px" width="789.0909090909091px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Please attach your NASA - Senior/Key Person Supplemental Data Sheet Attachment file(s). </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="61.81818181818182px" hyphenate="true" language="en" keep-together="always" top="456.66666666666669px" height="12.121212121212121px" width="789.0909090909091px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Please remember that any files you attach must be a Pure Edge document.</fo:block>
					</fo:block-container>
					<!-- 4 Extracted Attachments -->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="140.60606060606062px" hyphenate="true" language="en" keep-together="always" top="501.21212121212122px" height="13.333333333333334px" width="256.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Attachment1) or //NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Attachment1 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Attachment1"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="140.60606060606062px" hyphenate="true" language="en" keep-together="always" top="516.36363636363637px" height="13.333333333333334px" width="256.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Attachment2) or  //NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Attachment2 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Attachment2"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="140.60606060606062px" hyphenate="true" language="en" keep-together="always" top="531.51515151515153px" height="13.333333333333334px" width="256.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Attachment3) or  //NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Attachment3 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Attachment3"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="140.60606060606062px" hyphenate="true" language="en" keep-together="always" top="546.66666666666669px" height="13.333333333333334px" width="256.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Attachment4) or  //NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Attachment4 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheet/NASA_SeniorKeyPersonSupplementalDataSheet:Attachment4"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--for array pages-->
					<xsl:for-each select="./NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[2]">
						<fo:block break-after="page">
							<xsl:text>&#xA;</xsl:text>
						</fo:block>
						<xsl:call-template name="KPTemplate"/>
					</xsl:for-each>
					<xsl:for-each select="./NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[3]">
						<fo:block break-after="page">
							<xsl:text>&#xA;</xsl:text>
						</fo:block>
						<xsl:call-template name="KPTemplate"/>
					</xsl:for-each>
					<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[4]">
						<fo:block break-after="page">
							<xsl:text>&#xA;</xsl:text>
						</fo:block>
						<xsl:call-template name="KPTemplate"/>
					</xsl:for-each>
					<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[5]">
						<fo:block break-after="page">
							<xsl:text>&#xA;</xsl:text>
						</fo:block>
						<xsl:call-template name="KPTemplate"/>
					</xsl:for-each>
					<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[6]">
						<fo:block break-after="page">
							<xsl:text>&#xA;</xsl:text>
						</fo:block>
						<xsl:call-template name="KPTemplate"/>
					</xsl:for-each>
					<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[7]">
						<fo:block break-after="page">
							<xsl:text>&#xA;</xsl:text>
						</fo:block>
						<xsl:call-template name="KPTemplate"/>
					</xsl:for-each>
					<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person[8]">
						<fo:block break-after="page">
							<xsl:text>&#xA;</xsl:text>
						</fo:block>
						<xsl:call-template name="KPTemplate"/>
					</xsl:for-each>
					<!-- Extracted Att -->
					<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonAttachment/NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheetAtt[1]/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person">
						<fo:block break-after="page">
							<xsl:text>&#xA;</xsl:text>
						</fo:block>
						<xsl:call-template name="KPTemplate"/>
					</xsl:for-each>
					<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonAttachment/NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheetAtt[2]/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person">
						<fo:block break-after="page">
							<xsl:text>&#xA;</xsl:text>
						</fo:block>
						<xsl:call-template name="KPTemplate"/>
					</xsl:for-each>
					<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonAttachment/NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheetAtt[3]/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person">
						<fo:block break-after="page">
							<xsl:text>&#xA;</xsl:text>
						</fo:block>
						<xsl:call-template name="KPTemplate"/>
					</xsl:for-each>
					<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonAttachment/NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheetAtt[4]/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person">
						<fo:block break-after="page">
							<xsl:text>&#xA;</xsl:text>
						</fo:block>
						<xsl:call-template name="KPTemplate"/>
					</xsl:for-each>
					<!-- attachment information -->
					<fo:block break-after="page">
						<xsl:text>&#xA;</xsl:text>
					</fo:block>
					<xsl:call-template name="attInfo"/>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
	
	<!-- KeyPerson Template -->
	<xsl:template name="KPTemplate">
		<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonName">
			<fo:block-container background-color="transparent" border-style="none" position="absolute" left="76.96969696969697px" hyphenate="true" language="en" keep-together="always" top="123.63636363636364px" height="13.333333333333334px" width="86.06060606060606px">
				<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
					<xsl:choose>
						<xsl:when test="not(./globLib:PrefixName) or ./globLib:PrefixName = ''">
							<fo:inline color="#FFFFFF">&#160;</fo:inline>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="./globLib:PrefixName"/>
						</xsl:otherwise>
					</xsl:choose>
				</fo:block>
			</fo:block-container>
			<!--Block below is for the field named FirstName with FieldID 2-2-->
			<fo:block-container background-color="transparent" border-style="none" position="absolute" left="76.96969696969697px" hyphenate="true" language="en" keep-together="always" top="136.96969696969697px" height="13.333333333333334px" width="280px">
				<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
					<xsl:choose>
						<xsl:when test="not(./globLib:FirstName) or ./globLib:FirstName = ''">
							<fo:inline color="#FFFFFF">&#160;</fo:inline>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="./globLib:FirstName"/>
						</xsl:otherwise>
					</xsl:choose>
				</fo:block>
			</fo:block-container>
			<!--Block below is for the field named MiddleName with FieldID 2-3-->
			<fo:block-container background-color="transparent" border-style="none" position="absolute" left="76.96969696969697px" hyphenate="true" language="en" keep-together="always" top="150.3030303030303px" height="13.333333333333334px" width="201.81818181818184px">
				<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
					<xsl:choose>
						<xsl:when test="not(./globLib:MiddleName) or ./globLib:MiddleName = ''">
							<fo:inline color="#FFFFFF">&#160;</fo:inline>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="./globLib:MiddleName"/>
						</xsl:otherwise>
					</xsl:choose>
				</fo:block>
			</fo:block-container>
			<!--Block below is for the field named LastName with FieldID 2-4-->
			<fo:block-container background-color="transparent" border-style="none" position="absolute" left="76.96969696969697px" hyphenate="true" language="en" keep-together="always" top="163.63636363636365px" height="13.333333333333334px" width="478.7878787878788px">
				<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
					<xsl:choose>
						<xsl:when test="not(./globLib:LastName) or ./globLib:LastName = ''">
							<fo:inline color="#FFFFFF">&#160;</fo:inline>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="./globLib:LastName"/>
						</xsl:otherwise>
					</xsl:choose>
				</fo:block>
			</fo:block-container>
			<!--Block below is for the combobox named Suffix with FieldID 2-5-->
			<fo:block-container background-color="transparent" border-style="none" position="absolute" left="76.96969696969697px" hyphenate="true" language="en" keep-together="always" top="176.96969696969697px" height="13.333333333333334px" width="86.06060606060606px">
				<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
					<xsl:choose>
						<xsl:when test="not(./globLib:SuffixName) or ./globLib:SuffixName = ''">
							<fo:inline color="#FFFFFF">&#160;</fo:inline>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="./globLib:SuffixName"/>
						</xsl:otherwise>
					</xsl:choose>
				</fo:block>
			</fo:block-container>
		</xsl:for-each>
		<xsl:if test="not(./NASA_SeniorKeyPersonSupplementalDataSheet:StatementofCommitment)">
			<fo:block-container background-color="transparent" border-style="none" position="absolute" left="123.63636363636364px" hyphenate="true" language="en" keep-together="always" top="351.51515151515156px" height="12.121212121212121px" width="390.666666666667px">
				<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
					<fo:inline color="#FFFFFF">&#160;</fo:inline>
				</fo:block>
			</fo:block-container>
		</xsl:if>
		<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:StatementofCommitment">
			<!--Block below is for the field named StatementofCommitment0 with FieldID 3-0-->
			<fo:block-container background-color="transparent" border-style="none" position="absolute" left="123.63636363636364px" hyphenate="true" language="en" keep-together="always" top="351.51515151515156px" height="12.121212121212121px" width="390.666666666667px">
				<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
					<xsl:choose>
						<xsl:when test="./att:AttachedFile/att:FileName = ''">
							<fo:inline color="#FFFFFF">&#160;</fo:inline>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="./att:AttachedFile/att:FileName"/>
						</xsl:otherwise>
					</xsl:choose>
				</fo:block>
			</fo:block-container>
		</xsl:for-each>
		<xsl:if test="not(./NASA_SeniorKeyPersonSupplementalDataSheet:BudgetDetails)">
			<fo:block-container background-color="transparent" border-style="none" position="absolute" left="123.63636363636364px" hyphenate="true" language="en" keep-together="always" top="365.4545454545455px" height="12.121212121212121px" width="390.909090909091px">
				<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
					<fo:inline color="#FFFFFF">&#160;</fo:inline>
				</fo:block>
			</fo:block-container>
		</xsl:if>
		<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:BudgetDetails">
			<fo:block-container background-color="transparent" border-style="none" position="absolute" left="123.63636363636364px" hyphenate="true" language="en" keep-together="always" top="365.4545454545455px" height="12.121212121212121px" width="390.909090909091px">
				<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
					<xsl:choose>
						<xsl:when test="./att:AttachedFile/att:FileName = ''">
							<fo:inline color="#FFFFFF">&#160;</fo:inline>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="./att:AttachedFile/att:FileName"/>
						</xsl:otherwise>
					</xsl:choose>
				</fo:block>
			</fo:block-container>
		</xsl:for-each>
		<xsl:if test="not(./NASA_SeniorKeyPersonSupplementalDataSheet:FederalAgency)">
			<fo:block-container background-color="transparent" border-style="none" position="absolute" left="169.0909090909091px" hyphenate="true" language="en" keep-together="always" top="263.03030303030306px" height="13.333333333333334px" width="367.2727272727273px">
				<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
					<fo:inline color="#FFFFFF">&#160;</fo:inline>
				</fo:block>
			</fo:block-container>
		</xsl:if>
		<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:FederalAgency">
			<fo:block-container background-color="transparent" border-style="none" position="absolute" left="169.0909090909091px" hyphenate="true" language="en" keep-together="always" top="263.03030303030306px" height="13.333333333333334px" width="367.2727272727273px">
				<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
					<xsl:choose>
						<xsl:when test=". = ''">
							<fo:inline color="#FFFFFF">&#160;</fo:inline>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="./."/>
						</xsl:otherwise>
					</xsl:choose>
				</fo:block>
			</fo:block-container>
		</xsl:for-each>
		<xsl:if test="not(./NASA_SeniorKeyPersonSupplementalDataSheet:FederalAgencyDollar)">
			<fo:block-container background-color="transparent" border-style="none" position="absolute" left="194.54545454545456px" hyphenate="true" language="en" keep-together="always" top="281.8181818181818px" height="12.121212121212121px" width="115.15151515151516px">
				<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
					<fo:inline color="#FFFFFF">&#160;</fo:inline>
				</fo:block>
			</fo:block-container>
		</xsl:if>
		<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:FederalAgencyDollar">
			<fo:block-container background-color="transparent" border-style="none" position="absolute" left="194.54545454545456px" hyphenate="true" language="en" keep-together="always" top="281.8181818181818px" height="12.121212121212121px" width="115.15151515151516px">
				<fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
					<xsl:choose>
						<xsl:when test="not(.) or . = ''">
							<fo:inline color="#FFFFFF">&#160;</fo:inline>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="format-number(., '#,##0.00')"/>
						</xsl:otherwise>
					</xsl:choose>
				</fo:block>
			</fo:block-container>
		</xsl:for-each>
		<xsl:if test="not(./NASA_SeniorKeyPersonSupplementalDataSheet:NASACo-Itype)">
			<fo:block-container background-color="transparent" border-style="none" position="absolute" left="336.969696969697px" hyphenate="true" language="en" keep-together="always" top="200.60606060606062px" height="13.333333333333334px" width="197.5757575757576px">
				<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
					<fo:inline color="#FFFFFF">&#160;</fo:inline>
				</fo:block>
			</fo:block-container>
		</xsl:if>
		<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:NASACo-Itype">
			<fo:block-container background-color="transparent" border-style="none" position="absolute" left="336.969696969697px" hyphenate="true" language="en" keep-together="always" top="200.60606060606062px" height="13.333333333333334px" width="197.5757575757576px">
				<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
					<xsl:choose>
						<xsl:when test="not(.) or . = ''">
							<fo:inline color="#FFFFFF">&#160;</fo:inline>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="."/>
						</xsl:otherwise>
					</xsl:choose>
				</fo:block>
			</fo:block-container>
		</xsl:for-each>
		<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:USGovernmentParticipation">
			<fo:block-container background-color="transparent" border-style="none" position="absolute" left="411.51515151515156px" hyphenate="true" language="en" keep-together="always" top="236.96969696969697px" height="12.121212121212121px" width="18.181818181818183px">
				<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
					<xsl:choose>
						<xsl:when test="boolean(0)">
							<fo:inline color="#FFFFFF">&#160;</fo:inline>
						</xsl:when>
						<xsl:otherwise>
							<xsl:call-template name="radioButton">
								<xsl:with-param name="value" select="."/>
								<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
							</xsl:call-template>
						</xsl:otherwise>
					</xsl:choose>
				</fo:block>
			</fo:block-container>
			<fo:block-container background-color="transparent" border-style="none" position="absolute" left="520px" hyphenate="true" language="en" keep-together="always" top="236.96969696969697px" height="12.121212121212121px" width="18.181818181818183px">
				<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
					<xsl:choose>
						<xsl:when test="boolean(0)">
							<fo:inline color="#FFFFFF">&#160;</fo:inline>
						</xsl:when>
						<xsl:otherwise>
							<xsl:call-template name="radioButton">
								<xsl:with-param name="value" select="."/>
								<xsl:with-param name="schemaChoice">N: No</xsl:with-param>
							</xsl:call-template>
						</xsl:otherwise>
					</xsl:choose>
				</fo:block>
			</fo:block-container>
		</xsl:for-each>
		<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:InternationalParticipation">
			<fo:block-container background-color="transparent" border-style="none" position="absolute" left="416.969696969697px" hyphenate="true" language="en" keep-together="always" top="306.06060606060606px" height="12.121212121212121px" width="18.181818181818183px">
				<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
					<xsl:choose>
						<xsl:when test="boolean(0)">
							<fo:inline color="#FFFFFF">&#160;</fo:inline>
						</xsl:when>
						<xsl:otherwise>
							<xsl:call-template name="radioButton">
								<xsl:with-param name="value" select="."/>
								<xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
							</xsl:call-template>
						</xsl:otherwise>
					</xsl:choose>
				</fo:block>
			</fo:block-container>
			<!--Block below is for the radio named InternationalParticipation_No with FieldID 2-11-2-->
			<fo:block-container background-color="transparent" border-style="none" position="absolute" left="534.5454545454546px" hyphenate="true" language="en" keep-together="always" top="306.06060606060606px" height="12.121212121212121px" width="18.181818181818183px">
				<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
					<xsl:choose>
						<xsl:when test="boolean(0)">
							<fo:inline color="#FFFFFF">&#160;</fo:inline>
						</xsl:when>
						<xsl:otherwise>
							<xsl:call-template name="radioButton">
								<xsl:with-param name="value" select="."/>
								<xsl:with-param name="schemaChoice">N: No</xsl:with-param>
							</xsl:call-template>
						</xsl:otherwise>
					</xsl:choose>
				</fo:block>
			</fo:block-container>
		</xsl:for-each>
		<!--Write labels-->
		<!--Block below is for the label named -->
		<!--Block below is for the label named FirstName_LBL-->
		<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="136.96969696969697px" height="13.333333333333334px" width="54.54545454545455px">
			<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* First Name:</fo:block>
		</fo:block-container>
		<!--Block below is for the label named LastName_LBL-->
		<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="163.63636363636365px" height="13.333333333333334px" width="52.121212121212125px">
			<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Last Name:</fo:block>
		</fo:block-container>
		<!--Block below is for the label named USGovernmentParticipation_Lbl-->
		<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="236.96969696969697px" height="13.333333333333334px" width="303.03030303030306px">
			<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Is this person participating in this project as an employee of the U.S. Government?</fo:block>
		</fo:block-container>
		<!--Block below is for the label named InternationalParticipation_Lbl-->
		<fo:block-container background-color="transparent" border-style="none" position="absolute" left="16.363636363636363px" hyphenate="true" language="en" keep-together="always" top="306.06060606060606px" height="13.333333333333334px" width="301.21212121212125px">
			<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Is this person participating in this project as an employee of a foreign organization?</fo:block>
		</fo:block-container>
		<!--Block below is for the label named FederalAgency_LBL-->
		<fo:block-container background-color="transparent" border-style="none" position="absolute" left="18.181818181818183px" hyphenate="true" language="en" keep-together="always" top="263.03030303030306px" height="13.333333333333334px" width="150.9090909090909px">
			<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* If yes, select U.S. Government agency:</fo:block>
		</fo:block-container>
		<!--Block below is for the label named StatementofCommitment_LBL-->
		<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.212121212121215px" hyphenate="true" language="en" keep-together="always" top="351.51515151515156px" height="13.333333333333334px" width="102.42424242424244px">
			<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Statement of Commitment </fo:block>
		</fo:block-container>
		<!--Block below is for the label named BudgetDetails_LBL-->
		<fo:block-container background-color="transparent" border-style="none" position="absolute" left="21.212121212121215px" hyphenate="true" language="en" keep-together="always" top="365.4545454545455px" height="13.333333333333334px" width="58.78787878787879px">
			<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Budget Details </fo:block>
		</fo:block-container>
		<!--Block below is for the label named SeniorKeyPerson-->
		<fo:block-container background-color="transparent" border-style="none" position="absolute" left="23.03030303030303px" hyphenate="true" language="en" keep-together="always" top="101.21212121212122px" height="13.333333333333334px" width="98.18181818181819px">
			<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Senior/Key Person Name</fo:block>
		</fo:block-container>
		<!--Block below is for the label named Prefix_LBL-->
		<fo:block-container background-color="transparent" border-style="none" position="absolute" left="23.03030303030303px" hyphenate="true" language="en" keep-together="always" top="123.63636363636364px" height="13.333333333333334px" width="27.87878787878788px">
			<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Prefix:</fo:block>
		</fo:block-container>
		<!--Block below is for the label named MiddleName_LBL-->
		<fo:block-container background-color="transparent" border-style="none" position="absolute" left="23.03030303030303px" hyphenate="true" language="en" keep-together="always" top="150.3030303030303px" height="13.333333333333334px" width="55.75757575757576px">
			<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Middle Name:</fo:block>
		</fo:block-container>
		<!--Block below is for the label named Suffix_LBL-->
		<fo:block-container background-color="transparent" border-style="none" position="absolute" left="23.03030303030303px" hyphenate="true" language="en" keep-together="always" top="176.96969696969697px" height="13.333333333333334px" width="30.303030303030305px">
			<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Suffix:</fo:block>
		</fo:block-container>
		<!--Block below is for the label named NASACo_Itype_LBL-->
		<fo:block-container background-color="transparent" border-style="none" position="absolute" left="23.03030303030303px" hyphenate="true" language="en" keep-together="always" top="200.60606060606062px" height="13.333333333333334px" width="299.39393939393943px">
			<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">If this Senior/Key Person's role is Co-PI/PD, please choose the type of NASA Co-I:  </fo:block>
		</fo:block-container>
		<!--Block below is for the label named FederalAgencyDollar_LBL-->
		<fo:block-container background-color="transparent" border-style="none" position="absolute" left="23.03030303030303px" hyphenate="true" language="en" keep-together="always" top="281.8181818181818px" height="13.333333333333334px" width="171.51515151515153px">
			<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">If yes, enter total dollar amount requested ($) :   </fo:block>
		</fo:block-container>
		<!--Block below is for the label named title-->
		<fo:block-container background-color="transparent" border-style="none" position="absolute" left="124.24242424242425px" hyphenate="true" language="en" keep-together="always" top="48.484848484848484px" height="20px" width="334.54545454545456px">
			<fo:block background-color="transparent" color="#000000" font-size="14pt" font-style="normal" font-family="Helvetica" font-weight="normal">NASA - Senior/Key Person Supplemental Data Sheet</fo:block>
		</fo:block-container>
		<!--Block below is for the label named InternationalParticipation_Yes_LBL-->
		<fo:block-container background-color="transparent" border-style="none" position="absolute" left="321.8181818181818px" hyphenate="true" language="en" keep-together="always" top="306.06060606060606px" height="13.333333333333334px" width="95.15151515151516px">
			<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Foreign Organization Yes</fo:block>
		</fo:block-container>
		<!--Block below is for the label named USGovernmentParticipation_Yes_LBL-->
		<fo:block-container background-color="transparent" border-style="none" position="absolute" left="331.51515151515156px" hyphenate="true" language="en" keep-together="always" top="236.96969696969697px" height="13.333333333333334px" width="80.60606060606061px">
			<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">U.S. Government Yes</fo:block>
		</fo:block-container>
		<!--Block below is for the label named USGovernmentParticipation_No_LBL-->
		<fo:block-container background-color="transparent" border-style="none" position="absolute" left="443.03030303030306px" hyphenate="true" language="en" keep-together="always" top="236.96969696969697px" height="13.333333333333334px" width="76.96969696969697px">
			<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">U.S. Government No</fo:block>
		</fo:block-container>
		<!--Block below is for the label named InternationalParticipation_No_LBL-->
		<fo:block-container background-color="transparent" border-style="none" position="absolute" left="445.4545454545455px" hyphenate="true" language="en" keep-together="always" top="306.06060606060606px" height="13.333333333333334px" width="89.6969696969697px">
			<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Foreign Organization No</fo:block>
		</fo:block-container>
		<!--Block below is for the label named d1e2115-->
		<fo:block-container background-color="transparent" border-style="none" position="absolute" left="469.69696969696975px" hyphenate="true" language="en" keep-together="always" top="12.121212121212121px" height="13.333333333333334px" width="95.75757575757576px">
			<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number:  2700-0085 </fo:block>
		</fo:block-container>
		<!--Block below is for the label named omb_number-->
		<fo:block-container background-color="transparent" border-style="none" position="absolute" left="469.69696969696975px" hyphenate="true" language="en" keep-together="always" top="24.242424242424242px" height="13.333333333333334px" width="95.75757575757576px">
			<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number:  2700-0087 </fo:block>
		</fo:block-container>
		<!--Draw lines-->
		<fo:block-container background-color="black" border-style="none" position="absolute" left="10.90909090909091px" top="92.72727272727273px" width="555.1515151515151px" height="0.6060606060606061px">
            <fo:block/>
        </fo:block-container>
		<fo:block-container background-color="black" border-style="none" position="absolute" left="10.90909090909091px" top="336.969696969697px" width="555.1515151515151px" height="0.6060606060606061px">
            <fo:block/>
        </fo:block-container>
		<fo:block-container background-color="black" border-style="none" position="absolute" left="10.90909090909091px" top="389.69696969696975px" width="555.1515151515151px" height="0.6060606060606061px">
            <fo:block/>
        </fo:block-container>
		<fo:block-container background-color="black" border-style="none" position="absolute" left="10.90909090909091px" top="92.12121212121212px" width="0.6060606060606061px" height="298.7878787878788px">
            <fo:block/>
        </fo:block-container>
		<fo:block-container background-color="black" border-style="none" position="absolute" left="565.4545454545455px" top="92.72727272727273px" width="0.6060606060606061px" height="298.7878787878788px">
            <fo:block/>
        </fo:block-container>
	</xsl:template>
	
<!-- attachment information -->
	<xsl:template name="attInfo">
		<fo:table width="100%" space-before.optimum="0pt" space-after.optimum="0pt" table-layout="fixed">
			<fo:table-column column-width="proportional-column-width(1)"/>
			<fo:table-column column-width="proportional-column-width(1)"/>
			<fo:table-body>
				<fo:table-row>
					<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="center" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
						<fo:block font-size="14pt" text-decoration="underline">Attachments</fo:block>
					</fo:table-cell>
				</fo:table-row>
				<!--Statement Of Commitment-->
				<fo:table-row>
					<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
						<xsl:call-template name="addBlankLines">
							<xsl:with-param name="numLines">1</xsl:with-param>
						</xsl:call-template>
						<fo:block font-size="10pt">Statement of Commitment</fo:block>
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
				<!--main form StatementofCommitment-->
				<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person">
					<fo:table-row>
						<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
							<fo:block font-size="8pt">
								<xsl:value-of select="./NASA_SeniorKeyPersonSupplementalDataSheet:StatementofCommitment/att:AttachedFile/att:FileName"/>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
							<fo:block font-size="8pt">
								<xsl:value-of select="./NASA_SeniorKeyPersonSupplementalDataSheet:StatementofCommitment/att:AttachedFile/att:MimeType"/>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</xsl:for-each>
				<!--each extracted StatementofCommitment-->
				<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonAttachment/NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheetAtt/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person">
					<fo:table-row>
						<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
							<fo:block font-size="8pt">
								<xsl:value-of select="./NASA_SeniorKeyPersonSupplementalDataSheet:StatementofCommitment/att:AttachedFile/att:FileName"/>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
							<fo:block font-size="8pt">
								<xsl:value-of select="./NASA_SeniorKeyPersonSupplementalDataSheet:StatementofCommitment/att:AttachedFile/att:MimeType"/>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</xsl:for-each>
				<!-- Budget Details -->
				<fo:table-row>
					<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white" number-columns-spanned="2">
						<xsl:call-template name="addBlankLines">
							<xsl:with-param name="numLines">1</xsl:with-param>
						</xsl:call-template>
						<fo:block font-size="10pt">Budget Details</fo:block>
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
				<!--main form BudgetDetails -->
				<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person">
					<fo:table-row>
						<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
							<fo:block font-size="8pt">
								<xsl:value-of select="./NASA_SeniorKeyPersonSupplementalDataSheet:BudgetDetails/att:AttachedFile/att:FileName"/>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
							<fo:block font-size="8pt">
								<xsl:value-of select="./NASA_SeniorKeyPersonSupplementalDataSheet:BudgetDetails/att:AttachedFile/att:MimeType"/>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</xsl:for-each>
				<!--each extracted  BudgetDetails-->
				<xsl:for-each select="NASA_SeniorKeyPersonSupplementalDataSheet:SeniorKeyPersonAttachment/NASA_SeniorKeyPersonSupplementalDataSheet:NASA_SeniorKeyPersonSupplementalDataSheetAtt/NASA_SeniorKeyPersonSupplementalDataSheet:Senior_Key_Person">
					<fo:table-row>
						<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
							<fo:block font-size="8pt">
								<xsl:value-of select="./NASA_SeniorKeyPersonSupplementalDataSheet:BudgetDetails/att:AttachedFile/att:FileName"/>
							</fo:block>
						</fo:table-cell>
						<fo:table-cell hyphenate="true" language="en" padding-start="0pt" padding-end="0pt" padding-before="1pt" padding-after="1pt" display-align="before" text-align="left" border-style="solid" border-width="0pt" border-color="white">
							<fo:block font-size="8pt">
								<xsl:value-of select="./NASA_SeniorKeyPersonSupplementalDataSheet:BudgetDetails/att:AttachedFile/att:MimeType"/>
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</xsl:for-each>
			</fo:table-body>
		</fo:table>
	</xsl:template>
	
<!-- General Template -->
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
