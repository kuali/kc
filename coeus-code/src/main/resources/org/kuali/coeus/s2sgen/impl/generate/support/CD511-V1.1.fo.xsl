<?xml version="1.0" encoding="UTF-8"?>
<!-- $Revision:   1.3  $ -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:CD511="http://apply.grants.gov/forms/CD511-V1.1">
	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="/">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.2in" margin-right="0.2in">
					<fo:region-body margin-top="0.2in" margin-bottom="0.4in"/>
					<fo:region-after extent="0.4in"/>
				</fo:simple-page-master>
			</fo:layout-master-set>
			<fo:page-sequence master-reference="default-page" format="1" initial-page-number="1">
				<fo:static-content flow-name="xsl-region-after">
					<fo:block>
						<fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
						</fo:inline>
					</fo:block>
				</fo:static-content>
				<fo:flow flow-name="xsl-region-body">
					<!--Data components-->
					<!--Block below is for the field named OrganizationName-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="3.0303030303030303px" hyphenate="true" language="en" keep-together="always" top="581.8181818181819px" height="13.333333333333334px" width="478.7878787878788px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//CD511:CD511/CD511:OrganizationName) or //CD511:CD511/CD511:OrganizationName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//CD511:CD511/CD511:OrganizationName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named AwardNumber-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="3.0303030303030303px" hyphenate="true" language="en" keep-together="always" top="609.6969696969697px" height="13.333333333333334px" width="202.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//CD511:CD511/CD511:AwardNumber) or //CD511:CD511/CD511:AwardNumber = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//CD511:CD511/CD511:AwardNumber"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the combobox named Prefix1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="3.0303030303030303px" hyphenate="true" language="en" keep-together="always" top="649.0909090909091px" height="13.333333333333334px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="6.5pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//CD511:CD511/CD511:ContactName/globLib:PrefixName) or //CD511:CD511/CD511:ContactName/globLib:PrefixName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//CD511:CD511/CD511:ContactName/globLib:PrefixName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="3.0303030303030303px" hyphenate="true" language="en" keep-together="always" top="649.0909090909091px" height="13.333333333333334px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid"/>
					</fo:block-container>
					<!--Block below is for the field named FamilyName-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="3.0303030303030303px" hyphenate="true" language="en" keep-together="always" top="676.969696969697px" height="13.333333333333334px" width="478.7878787878788px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//CD511:CD511/CD511:ContactName/globLib:LastName) or //CD511:CD511/CD511:ContactName/globLib:LastName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//CD511:CD511/CD511:ContactName/globLib:LastName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named PersonTitle-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="35.151515151515156px" hyphenate="true" language="en" keep-together="always" top="690.909090909091px" height="13.333333333333334px" width="367.2727272727273px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//CD511:CD511/CD511:Title) or //CD511:CD511/CD511:Title = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//CD511:CD511/CD511:Title"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named GivenName1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="75.75757575757576px" hyphenate="true" language="en" keep-together="always" top="649.6969696969697px" height="13.333333333333334px" width="280px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//CD511:CD511/CD511:ContactName/globLib:FirstName) or //CD511:CD511/CD511:ContactName/globLib:FirstName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//CD511:CD511/CD511:ContactName/globLib:FirstName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named ProjectName-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="314.54545454545456px" hyphenate="true" language="en" keep-together="always" top="609.6969696969697px" height="23.03030303030303px" width="241.21212121212122px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//CD511:CD511/CD511:ProjectName) or //CD511:CD511/CD511:ProjectName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//CD511:CD511/CD511:ProjectName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the field named GivenName2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="355.75757575757575px" hyphenate="true" language="en" keep-together="always" top="649.0909090909091px" height="13.333333333333334px" width="202.42424242424244px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//CD511:CD511/CD511:ContactName/globLib:MiddleName) or //CD511:CD511/CD511:ContactName/globLib:MiddleName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//CD511:CD511/CD511:ContactName/globLib:MiddleName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Block below is for the combobox named SuffixName-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="482.42424242424244px" hyphenate="true" language="en" keep-together="always" top="676.969696969697px" height="13.333333333333334px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="6.5pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//CD511:CD511/CD511:ContactName/globLib:SuffixName) or //CD511:CD511/CD511:ContactName/globLib:SuffixName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//CD511:CD511/CD511:ContactName/globLib:SuffixName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!--Write labels-->
					<!--Block below is for the label named -->
					<!--Block below is for the label named LABEL2-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="3.0303030303030303px" hyphenate="true" language="en" keep-together="always" top="7.878787878787879px" height="12.727272727272728px" width="31.515151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">FORM</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL4-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="3.0303030303030303px" hyphenate="true" language="en" keep-together="always" top="21.212121212121215px" height="13.333333333333334px" width="86.66666666666667px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">(REV 1-05)</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL6-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="3.0303030303030303px" hyphenate="true" language="en" keep-together="always" top="56.36363636363637px" height="36.96969696969697px" width="573.939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Applicants should also review the instructions for certification included in the regulations before completing this form. Signature on this form provides for compliance with certification requirements under 15 CFR Part 28, 'New Restrictions on Lobbying.' The certifications shall be treated as a material representation of fact upon which reliance will be placed when the Department of Commerce determines to award the covered transaction, grant, or cooperative agreement.</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL18-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="3.0303030303030303px" hyphenate="true" language="en" keep-together="always" top="567.8787878787879px" height="13.333333333333334px" width="186.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* NAME OF APPLICANT</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL19-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="3.0303030303030303px" hyphenate="true" language="en" keep-together="always" top="595.7575757575758px" height="13.333333333333334px" width="84.24242424242425px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* AWARD NUMBER</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL21-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="3.0303030303030303px" hyphenate="true" language="en" keep-together="always" top="635.1515151515152px" height="13.333333333333334px" width="56.36363636363637px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Prefix:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL23-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="3.0303030303030303px" hyphenate="true" language="en" keep-together="always" top="663.0303030303031px" height="13.333333333333334px" width="155.75757575757578px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Last Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL25-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="3.0303030303030303px" hyphenate="true" language="en" keep-together="always" top="690.909090909091px" height="13.333333333333334px" width="31.515151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Title:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL26-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="3.0303030303030303px" hyphenate="true" language="en" keep-together="always" top="706.0606060606061px" height="13.333333333333334px" width="246.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* SIGNATURE:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named Signature-->
					
					
					
					
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="3.0303030303030303px" hyphenate="true" language="en" keep-together="always" top="720px" height="27.272727272727273px" width="258.7878787878788px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">


<xsl:choose>
								<xsl:when test="not(//CD511:CD511/CD511:Signature) or //CD511:CD511/CD511:Signature = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//CD511:CD511/CD511:Signature"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					
					
					
					
					
					
					
					<!--Block below is for the label named LABEL17-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="3.6363636363636367px" hyphenate="true" language="en" keep-together="always" top="529.0909090909091px" height="36.36363636363637px" width="570.3030303030304px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">As the duly authorized representative of the applicant, I hereby certify that the applicant will comply with the above applicable certification.</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL7-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="5.454545454545455px" hyphenate="true" language="en" keep-together="always" top="106.66666666666667px" height="18.181818181818183px" width="179.3939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">LOBBYING</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL8-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="5.454545454545455px" hyphenate="true" language="en" keep-together="always" top="126.06060606060606px" height="58.18181818181819px" width="266.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">As required by Section 1352, Title 31 of the U.S. Code, and implemented at 15 CFR Part 28, for persons entering into a grant, cooperative agreement or contract over $100,000 or a loan or loan guarantee over $150,000 as defined at 15 CFR Part 28, Sections 28.105 and 28.110, the applicant certifies that to the best of his or her knowledge and belief, that:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL9-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="5.454545454545455px" hyphenate="true" language="en" keep-together="always" top="192.72727272727275px" height="82.42424242424242px" width="266.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">(1) No Federal appropriated funds have been paid or will be paid, by or on behalf of the undersigned, to any person for influencing or attempting to influence an officer or employee of any agency, a Member of Congress in connection with the awarding of any Federal contract, the making of any Federal grant, the making of any Federal loan, the entering into of any cooperative agreement, and the extension, continuation, renewal, amendment, or modification of any Federal contract, grant, loan, or cooperative agreement.</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL10-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="5.454545454545455px" hyphenate="true" language="en" keep-together="always" top="278.7878787878788px" height="76.96969696969697px" width="266.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">(2) If any funds other than Federal appropriated funds have been paid or will be paid to any person for influencing or attempting to influence an officer or employee of any agency, a Member of Congress, an officer or employee of Congress, or an employee of a member of Congress in connection with this Federal contract, grant, loan, or cooperative agreement, the undersigned shall complete and submit Standard Form-LLL, 'Disclosure Form to Report Lobbying.' in accordance with its instructions.</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL11-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="5.454545454545455px" hyphenate="true" language="en" keep-together="always" top="359.39393939393943px" height="67.87878787878788px" width="266.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">(3) The undersigned shall require that the language of this certification be included in the award documents for all subawards at all tiers (including subcontracts, subgrants, and contracts under grants, loans, and cooperative agreements) and that all subrecipients shall certify and disclose accordingly.</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL12-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="5.454545454545455px" hyphenate="true" language="en" keep-together="always" top="433.93939393939394px" height="94.54545454545455px" width="266.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">This certification is a material representation of fact upon which reliance was placed when this transaction was made or entered into. Submission of this certification is a prerequisite for making or entering into this transaction imposed by section 1352, title 31, U.S. Code. Any person who fails to file the required certification shall be subject to a civil penalty of not less than $10,000 and not more than $100,000 for each such failure occurring on or before October 23, 1996, and of not less than $11,000 and not more than $110,000 for each such failure occurring after October 23, 1996.</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL3-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="35.151515151515156px" hyphenate="true" language="en" keep-together="always" top="7.878787878787879px" height="12.727272727272728px" width="64.24242424242425px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">CD-511</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL15-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="75.15151515151516px" hyphenate="true" language="en" keep-together="always" top="635.1515151515152px" height="13.333333333333334px" width="155.75757575757578px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* First Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL1-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="209.69696969696972px" hyphenate="true" language="en" keep-together="always" top="10.90909090909091px" height="29.6969696969697px" width="164.24242424242425px">
						<fo:block background-color="transparent" color="#000000" text-align="center" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">CERTIFICATION REGARDING LOBBYING </fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL13-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="308.4848484848485px" hyphenate="true" language="en" keep-together="always" top="103.63636363636364px" height="18.181818181818183px" width="227.27272727272728px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="bold">Statement for Loan Guarantees and Loan Insurance</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL14-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="308.4848484848485px" hyphenate="true" language="en" keep-together="always" top="125.45454545454547px" height="32.121212121212125px" width="266.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">The undersigned states, to the best of his or her knowledge and belief, that: </fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL28-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="308.4848484848485px" hyphenate="true" language="en" keep-together="always" top="165.45454545454547px" height="72.12121212121212px" width="266.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">In any funds have been paid or will be paid to any person for influencing or attempting to influence an officer or employee of any agency, a Member of Congress, an officer or employee of Congress, or an employee of a Member of Congress in connection with this commitment providing for the United States to insure or guarantee a loan, the undersigned shall complete and submit Standard Form-LLL, 'Disclosure Form to Report Lobbying,' in accordance with its instructions.</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL16-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="308.4848484848485px" hyphenate="true" language="en" keep-together="always" top="244.24242424242425px" height="76.96969696969697px" width="266.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Submission of this statement is a prerequisite for making or entering into this transaction imposed by section 1352, title 31, U.S. Code. Any person who fails to file the required statement shall be subject to a civil penalty of not less than $10,000 and not more than $100,000 for each such failure occurring on or before October 23, 1996, and of not less than $11,000 and not more than $110,000 for each such failure occurring after October 23, 1996.</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL20-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="314.54545454545456px" hyphenate="true" language="en" keep-together="always" top="595.7575757575758px" height="13.333333333333334px" width="193.93939393939394px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* PROJECT NAME</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL22-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="355.75757575757575px" hyphenate="true" language="en" keep-together="always" top="635.1515151515152px" height="13.333333333333334px" width="115.15151515151516px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Middle Name:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL27-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="395.1515151515152px" hyphenate="true" language="en" keep-together="always" top="706.0606060606061px" height="13.333333333333334px" width="156.96969696969697px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* DATE:</fo:block>
					</fo:block-container>
					<!--Block below is for the label named SubmittedDate-->

					
					
					               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="395.1515151515152px" hyphenate="true" language="en" keep-together="always" top="720px" height="27.272727272727273px" width="166.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//CD511:CD511/CD511:SubmittedDate) or //CD511:CD511/CD511:SubmittedDate = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                          <xsl:call-template name="formatDate">
					<xsl:with-param name="value" select="//CD511:CD511/CD511:SubmittedDate"/>
				</xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container>
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					<!--Block below is for the label named LABEL5-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="430.3030303030303px" hyphenate="true" language="en" keep-together="always" top="7.878787878787879px" height="12.121212121212121px" width="144.84848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">U.S. DEPARTMENT OF COMMERCE</fo:block>
					</fo:block-container>
					<!--Block below is for the label named LABEL24-->
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="482.42424242424244px" hyphenate="true" language="en" keep-together="always" top="663.0303030303031px" height="12.121212121212121px" width="56.36363636363637px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Suffix:</fo:block>
					</fo:block-container>
					<!--Draw lines-->
					<fo:block-container background-color="black" border-style="none" position="absolute" left="1.8181818181818183px" top="51.515151515151516px" width="576.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="1.8181818181818183px" top="95.15151515151516px" width="576.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="1.8181818181818183px" top="6.666666666666667px" width="579.3939393939394px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="1.8181818181818183px" top="6.666666666666667px" width="0.6060606060606061px" height="747.2727272727274px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="577.5757575757576px" top="6.666666666666667px" width="0.6060606060606061px" height="747.2727272727274px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="1.8181818181818183px" top="566.6666666666667px" width="576.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="1.8181818181818183px" top="633.3333333333334px" width="576.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>    
					<fo:block-container background-color="black" border-style="none" position="absolute" left="1.8181818181818183px" top="704.8484848484849px" width="576.3636363636364px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>    
					<fo:block-container background-color="black" border-style="none" position="absolute" left="1.8181818181818183px" top="753.3333333333334px" width="576.3636363636364px" height="0.6060606060606061px">
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
				<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V1.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats">&#x25cf;</fo:inline>
			</xsl:when>
			<xsl:otherwise>
				<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V1.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats">&#x274d;</fo:inline>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="checkbox">
		<xsl:param name="value"/>
		<xsl:param name="schemaChoice">Yes</xsl:param>
		<xsl:if test="$value = $schemaChoice">
			<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V1.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="11pt">&#x2714;</fo:inline>
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
				<fo:block xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V1.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0">
					<fo:leader leader-pattern="space"/>
				</fo:block>
				<xsl:call-template name="addBlankLines">
					<xsl:with-param name="numLines" select="$numLines - 1"/>
				</xsl:call-template>
			</xsl:if>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>
