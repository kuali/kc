<?xml version="1.0" encoding="UTF-8"?>
<!-- edited with XMLSPY v2004 rel. 3 U (http://www.xmlspy.com) by jenlu (Massachusetts Institute of Technology) -->
<!-- $Revision:   1.5  $ -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:PHS398_CoverPageSupplement="http://apply.grants.gov/forms/PHS398_CoverPageSupplement-V1.0" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V1.0" xmlns:header="http://apply.grants.gov/system/Header-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema">
	<xsl:output method="xml" indent="yes"/>
	<xsl:template match="/">
		<fo:root>
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
					<fo:block-container background-color="black" border-style="none" position="absolute" left="23.03030303030303px" top="83.03030303030303px" width="546.6666666666667px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="21.81818181818182px" top="724.2424242424242px" width="546.6666666666667px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="21.212121212121215px" top="83.03030303030303px" width="1.2121212121212122px" height="642.4242424242425px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="567.8787878787879px" top="83.63636363636364px" width="1.2121212121212122px" height="642.4242424242425px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="22.424242424242426px" top="203.63636363636365px" width="546.6666666666667px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="21.81818181818182px" top="556.3636363636364px" width="546.6666666666667px" height="0.6060606060606061px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="21.81818181818182px" top="280.6060606060606px" width="546.6666666666667px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="22.424242424242426px" top="373.33333333333337px" width="546.6666666666667px" height="1.2121212121212122px">
                                            <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="470.1212121212121px" top="60.6060606060606px" height="12.121212121212121px" width="100.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 0925-0001</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="470.1212121212121px" top="70.6060606060606px" height="12.121212121212121px" width="100.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Expiration Date: 9/30/2007</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="276.969696969697px" top="125.45454545454547px" height="13.333333333333334px" width="284.8484848484849px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:PDPIName/globLib:FirstName) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:PDPIName/globLib:FirstName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:PDPIName/globLib:FirstName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.03030303030303px" top="138.78787878787878px" height="13.333333333333334px" width="207.27272727272728px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:PDPIName/globLib:MiddleName) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:PDPIName/globLib:MiddleName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:PDPIName/globLib:MiddleName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.03030303030303px" top="152.12121212121212px" height="13.333333333333334px" width="478.7878787878788px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:PDPIName/globLib:LastName) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:PDPIName/globLib:LastName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:PDPIName/globLib:LastName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="30.90909090909091px" top="216.36363636363637px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"/>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="82.42424242424242px" top="243.63636363636365px" height="13.333333333333334px" width="126.06060606060606px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:Degrees[1]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:Degrees[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:Degrees[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="241.21212121212122px" top="243.63636363636365px" height="13.333333333333334px" width="126.06060606060606px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:Degrees[2]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:Degrees[2] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:Degrees[2]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="418.7878787878788px" top="243.63636363636365px" height="13.333333333333334px" width="126.06060606060606px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:Degrees[3]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:Degrees[3] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:Degrees[3]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="277.57575757575756px" top="441.21212121212125px" height="13.333333333333334px" width="284.8484848484849px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactName/globLib:FirstName) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactName/globLib:FirstName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactName/globLib:FirstName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.63636363636364px" top="454.54545454545456px" height="13.333333333333334px" width="207.27272727272728px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactName/globLib:MiddleName) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactName/globLib:MiddleName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactName/globLib:MiddleName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.63636363636364px" top="468.4848484848485px" height="13.333333333333334px" width="478.7878787878788px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactName/globLib:LastName) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactName/globLib:LastName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactName/globLib:LastName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="353.93939393939394px" top="510.90909090909093px" height="13.333333333333334px" width="207.27272727272728px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactFax) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactFax = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactFax"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="57.57575757575758px" top="525.4545454545455px" height="13.333333333333334px" width="478.7878787878788px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactEmail) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactEmail = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactEmail"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="89.0909090909091px" top="510.90909090909093px" height="13.333333333333334px" width="207.27272727272728px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactPhone) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactPhone = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactPhone"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="57.57575757575758px" top="580px" height="13.333333333333334px" width="367.2727272727273px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactTitle) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactTitle = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactTitle"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="70.30303030303031px" top="612.7272727272727px" height="13.333333333333334px" width="440px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:Street1) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:Street1 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:Street1"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="70.30303030303031px" top="626.0606060606061px" height="13.333333333333334px" width="440px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:Street2) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:Street2 = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:Street2"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="70.30303030303031px" top="639.3939393939394px" height="13.333333333333334px" width="284.8484848484849px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:City) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:City = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:City"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="70.30303030303031px" top="652.7272727272727px" height="13.333333333333334px" width="246.06060606060606px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:County) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:County = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:County"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="70.30303030303031px" top="680.6060606060606px" height="13.333333333333334px" width="246.06060606060606px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:ZipCode) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:ZipCode = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:ZipCode"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="164.24242424242425px" top="27.272727272727273px" height="41.21212121212121px" width="237.5757575757576px">
						<fo:block background-color="transparent" color="#000000" text-align="center" font-size="14pt" font-style="normal" font-family="Helvetica" font-weight="bold">PHS 398 Cover Page Supplement</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="26.060606060606062px" top="94.54545454545455px" height="12.121212121212121px" width="255.75757575757578px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">1. Project Director / Principal Investigator (PD/PI)</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="25.454545454545457px" top="580px" height="12.121212121212121px" width="42.42424242424243px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Title:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="25.454545454545457px" top="612.7272727272727px" height="12.121212121212121px" width="52.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Street1:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="25.454545454545457px" top="639.3939393939394px" height="12.121212121212121px" width="37.57575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* City:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="332.1212121212121px" top="680.6060606060606px" height="12.121212121212121px" width="52.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Country:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="30.90909090909091px" top="626.0606060606061px" height="12.121212121212121px" width="42.42424242424243px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Street2:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="30.90909090909091px" top="652.7272727272727px" height="12.121212121212121px" width="37.57575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">County:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="25.454545454545457px" top="666.6666666666667px" height="12.121212121212121px" width="42.42424242424243px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* State:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="25.454545454545457px" top="680.6060606060606px" height="13.333333333333334px" width="45.45454545454546px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Zip Code:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="31.515151515151516px" top="125.45454545454547px" height="12.121212121212121px" width="37.57575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Prefix:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="227.27272727272728px" top="125.45454545454547px" height="12.121212121212121px" width="66.66666666666667px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* First Name:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="31.515151515151516px" top="138.78787878787878px" height="12.121212121212121px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Middle Name:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="25.454545454545457px" top="151.51515151515153px" height="12.121212121212121px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Last Name:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="31.515151515151516px" top="164.24242424242425px" height="12.121212121212121px" width="37.57575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Suffix:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="26.060606060606062px" top="287.8787878787879px" height="12.121212121212121px" width="120.06060606060606px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">2. Human Subjects</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="31.515151515151516px" top="216.36363636363637px" height="13.333333333333334px" width="71.51515151515152px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* New Investigator?</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="155.75757575757578px" top="216.36363636363637px" height="12.121212121212121px" width="13.333333333333334px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">No</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="31.515151515151516px" top="243.63636363636365px" height="12.121212121212121px" width="42.42424242424243px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Degrees:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="203.63636363636365px" top="216.36363636363637px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Yes</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="31.515151515151516px" top="316.3636363636364px" height="12.121212121212121px" width="76.36363636363637px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Clinical Trial?</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="216.36363636363637px" top="316.3636363636364px" height="12.121212121212121px" width="13.333333333333334px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">No</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="260.6060606060606px" top="316.3636363636364px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Yes</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="31.515151515151516px" top="343.6363636363637px" height="12.121212121212121px" width="185px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Agency-Defined Phase III Clinical Trial?</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="216.36363636363637px" top="343.6363636363637px" height="12.121212121212121px" width="13.333333333333334px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">No</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="260.6060606060606px" top="343.6363636363637px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Yes</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="227.27272727272728px" top="441.21212121212125px" height="12.121212121212121px" width="66.66666666666667px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* First Name:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="31.515151515151516px" top="454.54545454545456px" height="12.121212121212121px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Middle Name:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="25.454545454545457px" top="469.0909090909091px" height="12.121212121212121px" width="61.81818181818182px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Last Name:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="31.515151515151516px" top="482.42424242424244px" height="12.121212121212121px" width="37.57575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Suffix:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="26.060606060606062px" top="393.33333333333337px" height="12.121212121212121px" width="163.63636363636365px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">3. Applicant Organization Contact</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="31.515151515151516px" top="424.8484848484849px" height="12.121212121212121px" width="294.54545454545456px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Person to be contacted on matters involving this application</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="31.515151515151516px" top="441.21212121212125px" height="12.121212121212121px" width="37.57575757575758px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Prefix:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="25.454545454545457px" top="510.90909090909093px" height="12.121212121212121px" width="76.36363636363637px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Phone Number:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="304.8484848484849px" top="510.90909090909093px" height="12.121212121212121px" width="56.969696969696976px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Fax Number:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="30.90909090909091px" top="525.4545454545455px" height="12.121212121212121px" width="32.72727272727273px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Email:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="146.66666666666669px" top="216.36363636363637px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:isNewInvestigator) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:isNewInvestigator = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:isNewInvestigator"/>
										<xsl:with-param name="schemaChoice">No</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="195.15151515151516px" top="216.36363636363637px" height="13.333333333333334px" width="8.484848484848484px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:isNewInvestigator) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:isNewInvestigator = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:isNewInvestigator"/>
										<xsl:with-param name="schemaChoice">Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="242.42424242424244px" top="216.36363636363637px" height="12.121212121212121px" width="13.333333333333334px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal"/>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="207.8787878787879px" top="316.3636363636364px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ClinicalTrial/PHS398_CoverPageSupplement:isClinicalTrial) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ClinicalTrial/PHS398_CoverPageSupplement:isClinicalTrial = ''">
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value">No</xsl:with-param>
										<xsl:with-param name="schemaChoice">Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ClinicalTrial/PHS398_CoverPageSupplement:isClinicalTrial"/>
										<xsl:with-param name="schemaChoice">No</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="252.72727272727275px" top="316.3636363636364px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ClinicalTrial/PHS398_CoverPageSupplement:isClinicalTrial) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ClinicalTrial/PHS398_CoverPageSupplement:isClinicalTrial = ''">
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value">No</xsl:with-param>
										<xsl:with-param name="schemaChoice">Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ClinicalTrial/PHS398_CoverPageSupplement:isClinicalTrial"/>
										<xsl:with-param name="schemaChoice">Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="293.93939393939394px" top="316.3636363636364px" height="12.121212121212121px" width="13.333333333333334px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal"/>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="207.8787878787879px" top="343.6363636363637px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ClinicalTrial/PHS398_CoverPageSupplement:isPhaseIIIClinicalTrial) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ClinicalTrial/PHS398_CoverPageSupplement:isPhaseIIIClinicalTrial = ''">
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value">No</xsl:with-param>
										<xsl:with-param name="schemaChoice">Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ClinicalTrial/PHS398_CoverPageSupplement:isPhaseIIIClinicalTrial"/>
										<xsl:with-param name="schemaChoice">No</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="252.72727272727275px" top="343.6363636363637px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ClinicalTrial/PHS398_CoverPageSupplement:isPhaseIIIClinicalTrial) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ClinicalTrial/PHS398_CoverPageSupplement:isPhaseIIIClinicalTrial = ''">
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value">No</xsl:with-param>
										<xsl:with-param name="schemaChoice">Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ClinicalTrial/PHS398_CoverPageSupplement:isPhaseIIIClinicalTrial"/>
										<xsl:with-param name="schemaChoice">Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="293.93939393939394px" top="343.6363636363637px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal"/>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.03030303030303px" top="125.45454545454547px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:PDPIName/globLib:PrefixName) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:PDPIName/globLib:PrefixName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:PDPIName/globLib:PrefixName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.03030303030303px" top="166.06060606060606px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:PDPIName/globLib:SuffixName) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:PDPIName/globLib:SuffixName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:PDPI/PHS398_CoverPageSupplement:PDPIName/globLib:SuffixName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.63636363636364px" top="441.21212121212125px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactName/globLib:PrefixName) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactName/globLib:PrefixName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactName/globLib:PrefixName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.63636363636364px" top="481.21212121212125px" height="13.333333333333334px" width="81.21212121212122px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactName/globLib:SuffixName) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactName/globLib:SuffixName = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactName/globLib:SuffixName"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="70.30303030303031px" top="666.6666666666667px" height="13.333333333333334px" width="246.06060606060606px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:State) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:State = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:State"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="370.90909090909093px" top="680.6060606060606px" height="13.333333333333334px" width="41.21212121212121px">
						<fo:block padding-left="2px" background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:Country) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:Country = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:ContactPersonInfo/PHS398_CoverPageSupplement:ContactAddress/globLib:Country"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<!-- </fo:flow>
         </fo:page-sequence>
         <fo:page-sequence master-reference="default-page" format="1" initial-page-number="2">
            <fo:static-content flow-name="xsl-region-after">
               <fo:block>
                  <fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
                  </fo:inline>
               </fo:block>
            </fo:static-content>
            <fo:flow flow-name="xsl-region-body"> -->
					<fo:block break-after="page">
						<xsl:text>&#xA;</xsl:text>
					</fo:block>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="169.69696969696972px" hyphenate="true" language="en" keep-together="always" top="27.272727272727273px" height="20.606060606060606px" width="237.5757575757576px">
						<fo:block background-color="transparent" color="#000000" text-align="center" font-size="14pt" font-style="normal" font-family="Helvetica" font-weight="bold">PHS 398 Cover Page Supplement</fo:block>
					</fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="18.181818181818183px" top="76.42424242424243px" width="546.6666666666667px" height="1.2121212121212122px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="19.393939393939394px" top="525.4545454545455px" width="546.6666666666667px" height="1.2121212121212122px">
                                        <fo:block/>    
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="18.78787878787879px" top="76.42424242424243px" width="1.2121212121212122px" height="450.8484848484849px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="black" border-style="none" position="absolute" left="564.8484848484849px" top="76.42424242424243px" width="1.2121212121212122px" height="450.8484848484849px">
                                        <fo:block/>
                                        </fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="470.1212121212121px" top="54.6060606060606px" height="12.121212121212121px" width="100.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 0925-0001</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="470.1212121212121px" top="64.6060606060606px" height="12.121212121212121px" width="100.121212121212125px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Expiration Date: 9/30/2007</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="46.66666666666667px" top="40.45454545454545px" height="13.333333333333334px" width="233.93939393939397px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"/>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="211px" height="12.121212121212121px" width="60.60606060606061px" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[1]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[1] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[1]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="226px" height="12.121212121212121px" width="60.60606060606061px" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[2]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[2] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[2]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="241px" height="12.121212121212121px" width="60.60606060606061px" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[3]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[3] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[3]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="256.5px" height="12.121212121212121px" width="60.60606060606061px" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[4]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[4] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[4]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="272px" height="12.121212121212121px" width="60.60606060606061px" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[5]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[5] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[5]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="287px" height="12.121212121212121px" width="60.60606060606061px" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[6]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[6] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[6]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="302px" height="12.121212121212121px" width="60.60606060606061px" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[7]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[7] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[7]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="317px" height="12.121212121212121px" width="60.60606060606061px" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[8]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[8] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[8]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="332px" height="12.121212121212121px" width="60.60606060606061px" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[9]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[9] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[9]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="347px" height="12.121212121212121px" width="60.60606060606061px" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[10]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[10] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[10]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="362px" height="12.121212121212121px" width="60.60606060606061px" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[11]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[11] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[11]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="377px" height="12.121212121212121px" width="60.60606060606061px" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[12]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[12] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[12]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="392px" height="12.121212121212121px" width="60.60606060606061px" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[13]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[13] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[13]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="407px" height="12.121212121212121px" width="60.60606060606061px" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[14]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[14] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[14]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="422px" height="12.121212121212121px" width="60.60606060606061px" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[15]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[15] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[15]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="437px" height="12.121212121212121px" width="60.60606060606061px" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[16]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[16] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[16]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="452px" height="12.121212121212121px" width="60.60606060606061px" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[17]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[17] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[17]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="467px" height="12.121212121212121px" width="60.60606060606061px" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[18]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[18] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[18]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="482px" height="12.121212121212121px" width="60.60606060606061px" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[19]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[19] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[19]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="497px" height="12.121212121212121px" width="60.60606060606061px" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[20]) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[20] = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:CellLines[20]"/>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="46.06060606060606px" top="86.18181818181819px" height="12.121212121212121px" width="160.24242424242425px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">4. Human Embryonic Stem Cells</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="46.66666666666667px" top="105.45454545454545px" height="12.121212121212121px" width="309.0909090909091px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Does the proposed project involve human embryonic stem cells?</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="341.21212121212125px" top="105.45454545454545px" height="12.121212121212121px" width="13.333333333333334px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">No</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="382.42424242424244px" top="105.45454545454545px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Yes</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="139.3939393939394px" height="12.121212121212121px" width="503.03030303030306px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">If the proposed project involves human embryonic stem cells, list below the registration number of the </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="150.9090909090909px" height="13.333333333333334px" width="300.4848484848485px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">specific cell line(s) from the following list: http://stemcells.nih.gov/registry/index.asp</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="340.6060606060606px" top="150.9090909090909px" height="12.121212121212121px" width="105.45454545454545px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">.  Or, if a specific </fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="45.45454545454546px" top="161.21212121212122px" height="12.121212121212121px" width="602.03030303030306px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">stem cell line cannot be referenced at this time, please check the box indicating that one from the &#xD;
registry will be used:</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="46.06060606060606px" top="190px" height="12.121212121212121px" width="66.66666666666667px">
						<fo:block background-color="transparent" color="#000000" font-size="10pt" font-style="normal" font-family="Helvetica" font-weight="bold">Cell Line(s):</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="137.5757575757576px" top="192px" height="13.333333333333334px" width="325.4545454545455px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Specific stem cell line cannot be referenced at this time.  One from the registry will be used.</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="333.33333333333337px" top="105.45454545454545px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:isHumanStemCellsInvolved) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:isHumanStemCellsInvolved = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:isHumanStemCellsInvolved"/>
										<xsl:with-param name="schemaChoice">No</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="373.93939393939394px" top="105.45454545454545px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:isHumanStemCellsInvolved) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:isHumanStemCellsInvolved = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="radioButton">
										<xsl:with-param name="value" select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:isHumanStemCellsInvolved"/>
										<xsl:with-param name="schemaChoice">Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="421.8181818181818px" top="106.06060606060606px" height="12.121212121212121px" width="13.333333333333334px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal"/>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="123.63636363636364px" top="192px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:StemCellsIndicator) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:StemCellsIndicator = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274F;</fo:inline>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
					</fo:block-container>
					<fo:block-container background-color="transparent" border-style="none" position="absolute" left="123.63636363636364px" top="192.3939393939394px" height="12.121212121212121px" width="18.181818181818183px">
						<fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
							<xsl:choose>
								<xsl:when test="not(//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:StemCellsIndicator) or //PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:StemCellsIndicator = ''">
									<fo:inline color="#FFFFFF">&#160;</fo:inline>
								</xsl:when>
								<xsl:otherwise>
									<xsl:call-template name="checkbox">
										<xsl:with-param name="value" select="//PHS398_CoverPageSupplement:PHS398_CoverPageSupplement/PHS398_CoverPageSupplement:StemCells/PHS398_CoverPageSupplement:StemCellsIndicator"/>
										<xsl:with-param name="schemaChoice">Yes</xsl:with-param>
									</xsl:call-template>
								</xsl:otherwise>
							</xsl:choose>
						</fo:block>
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
				<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V1.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
			</xsl:when>
			<xsl:otherwise>
				<fo:inline xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V1.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
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
