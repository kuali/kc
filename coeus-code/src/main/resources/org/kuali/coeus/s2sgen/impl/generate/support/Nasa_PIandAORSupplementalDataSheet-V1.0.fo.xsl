<?xml version="1.0" encoding="UTF-8"?><!-- $Revision:   1.4  $ -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:Nasa_PIandAORSupplementalDataSheet="http://apply.grants.gov/forms/Nasa_PIandAORSupplementalDataSheet-V1.0">
   <xsl:output method="xml" indent="yes"/>
   <xsl:template match="/">
      <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0">
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
            <fo:flow flow-name="xsl-region-body"><!--Data components--><!--Block below is for the field named FIELD2 with FieldID -->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="24.242424242424242px" hyphenate="true" language="en" keep-together="always" top="481.21212121212125px" height="13.333333333333334px" width="300.6060606060606px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"/>
               </fo:block-container><!--Block below is for the field named FIELD1 with FieldID -->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="25.454545454545457px" hyphenate="true" language="en" keep-together="always" top="369.69696969696975px" height="13.333333333333334px" width="300px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal"/>
               </fo:block-container><!--Block below is for the radio named USGovernmentParticipation_YES with FieldID 3-6-1-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="25.454545454545457px" hyphenate="true" language="en" keep-together="always" top="383.6363636363637px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:USGovernmentParticipation"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the radio named InternationalParticipation_YES with FieldID 3-9-1-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="25.454545454545457px" hyphenate="true" language="en" keep-together="always" top="495.1515151515152px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:InternationalParticipation"/>
                              <xsl:with-param name="schemaChoice">Y: Yes</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the radio named USGovernmentParticipation_NO with FieldID 3-6-2-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="72.12121212121212px" hyphenate="true" language="en" keep-together="always" top="383.6363636363637px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:USGovernmentParticipation"/>
                              <xsl:with-param name="schemaChoice">N: No</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the radio named InternationalParticipation_NO with FieldID 3-9-2-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="75.15151515151516px" hyphenate="true" language="en" keep-together="always" top="495.1515151515152px" height="12.121212121212121px" width="18.181818181818183px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="ZapfDingbats" font-weight="normal">
                     <xsl:choose>
                        <xsl:when test="boolean(0)">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:call-template name="radioButton">
                              <xsl:with-param name="value" select="//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:InternationalParticipation"/>
                              <xsl:with-param name="schemaChoice">N: No</xsl:with-param>
                           </xsl:call-template>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the combobox named AOR_Prefix with FieldID 2-1-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.63636363636364px" hyphenate="true" language="en" keep-together="always" top="134.54545454545456px" height="13.333333333333334px" width="86.06060606060606px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:AuthorizedRepresentativeName/Nasa_PIandAORSupplementalDataSheet:AORName/globLib:PrefixName) or //Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:AuthorizedRepresentativeName/Nasa_PIandAORSupplementalDataSheet:AORName/globLib:PrefixName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:AuthorizedRepresentativeName/Nasa_PIandAORSupplementalDataSheet:AORName/globLib:PrefixName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the field named AOR_FirstName with FieldID 2-2-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.63636363636364px" hyphenate="true" language="en" keep-together="always" top="147.87878787878788px" height="13.333333333333334px" width="280px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:AuthorizedRepresentativeName/Nasa_PIandAORSupplementalDataSheet:AORName/globLib:FirstName) or //Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:AuthorizedRepresentativeName/Nasa_PIandAORSupplementalDataSheet:AORName/globLib:FirstName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:AuthorizedRepresentativeName/Nasa_PIandAORSupplementalDataSheet:AORName/globLib:FirstName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the field named AOR_MiddleName with FieldID 2-3-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.63636363636364px" hyphenate="true" language="en" keep-together="always" top="161.21212121212122px" height="13.333333333333334px" width="202.42424242424244px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:AuthorizedRepresentativeName/Nasa_PIandAORSupplementalDataSheet:AORName/globLib:MiddleName) or //Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:AuthorizedRepresentativeName/Nasa_PIandAORSupplementalDataSheet:AORName/globLib:MiddleName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:AuthorizedRepresentativeName/Nasa_PIandAORSupplementalDataSheet:AORName/globLib:MiddleName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the field named AOR_LastName with FieldID 2-4-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.63636363636364px" hyphenate="true" language="en" keep-together="always" top="174.54545454545456px" height="13.333333333333334px" width="478.7878787878788px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:AuthorizedRepresentativeName/Nasa_PIandAORSupplementalDataSheet:AORName/globLib:LastName) or //Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:AuthorizedRepresentativeName/Nasa_PIandAORSupplementalDataSheet:AORName/globLib:LastName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:AuthorizedRepresentativeName/Nasa_PIandAORSupplementalDataSheet:AORName/globLib:LastName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the combobox named AOR_Suffix with FieldID 2-5-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.63636363636364px" hyphenate="true" language="en" keep-together="always" top="187.87878787878788px" height="13.333333333333334px" width="86.06060606060606px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:AuthorizedRepresentativeName/Nasa_PIandAORSupplementalDataSheet:AORName/globLib:SuffixName) or //Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:AuthorizedRepresentativeName/Nasa_PIandAORSupplementalDataSheet:AORName/globLib:SuffixName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:AuthorizedRepresentativeName/Nasa_PIandAORSupplementalDataSheet:AORName/globLib:SuffixName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the combobox named PDPI_Prefix with FieldID 3-1-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.63636363636364px" hyphenate="true" language="en" keep-together="always" top="286.06060606060606px" height="13.333333333333334px" width="86.06060606060606px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:PDPIName/globLib:PrefixName) or //Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:PDPIName/globLib:PrefixName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:PDPIName/globLib:PrefixName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the field named PDPI_FirstName with FieldID 3-2-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.63636363636364px" hyphenate="true" language="en" keep-together="always" top="299.39393939393943px" height="13.333333333333334px" width="280px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:PDPIName/globLib:FirstName) or //Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:PDPIName/globLib:FirstName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:PDPIName/globLib:FirstName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the field named PDPI_MiddleName with FieldID 3-3-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.63636363636364px" hyphenate="true" language="en" keep-together="always" top="312.72727272727275px" height="13.333333333333334px" width="202.42424242424244px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:PDPIName/globLib:MiddleName) or //Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:PDPIName/globLib:MiddleName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:PDPIName/globLib:MiddleName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the field named PDPI_LastName with FieldID 3-4-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.63636363636364px" hyphenate="true" language="en" keep-together="always" top="326.06060606060606px" height="13.333333333333334px" width="478.7878787878788px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:PDPIName/globLib:LastName) or //Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:PDPIName/globLib:LastName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:PDPIName/globLib:LastName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the combobox named PDPI_Suffix with FieldID 3-5-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.63636363636364px" hyphenate="true" language="en" keep-together="always" top="339.39393939393943px" height="13.333333333333334px" width="86.06060606060606px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:PDPIName/globLib:SuffixName) or //Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:PDPIName/globLib:SuffixName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:PDPIName/globLib:SuffixName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the field named UserName with FieldID 2-6-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="115.15151515151516px" hyphenate="true" language="en" keep-together="always" top="216.36363636363637px" height="26.333333333333334px" width="440.7878787878788px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:AuthorizedRepresentativeName/Nasa_PIandAORSupplementalDataSheet:UserName) or //Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:AuthorizedRepresentativeName/Nasa_PIandAORSupplementalDataSheet:UserName = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:AuthorizedRepresentativeName/Nasa_PIandAORSupplementalDataSheet:UserName"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the popup named FederalAgency with FieldID 3-7-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="175.75757575757578px" hyphenate="true" language="en" keep-together="always" top="415.7575757575758px" height="13.333333333333334px" width="304.24242424242425px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:FederalAgency) or //Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:FederalAgency = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:FederalAgency"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Block below is for the field named FederalAgencyDollar with FieldID 3-8-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="192.60606060606062px" hyphenate="true" language="en" keep-together="always" top="447.2727272727273px" height="13.333333333333334px" width="142.42424242424244px">
                  <fo:block background-color="transparent" color="#000000" text-align="end" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal" border-top-style="solid" border-bottom-style="solid" border-start-style="solid" border-end-style="solid">
                     <xsl:choose>
                        <xsl:when test="not(//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:FederalAgencyDollar) or //Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:FederalAgencyDollar = ''">
                           <fo:inline color="#FFFFFF">&#160;</fo:inline>
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="format-number(//Nasa_PIandAORSupplementalDataSheet:Nasa_PIandAORSupplementalDataSheet/Nasa_PIandAORSupplementalDataSheet:PrincipalInvestigatorName/Nasa_PIandAORSupplementalDataSheet:FederalAgencyDollar, '#,##0.00')"/>
                        </xsl:otherwise>
                     </xsl:choose>
                  </fo:block>
               </fo:block-container><!--Write labels--><!--Block below is for the label named --><!--Block below is for the label named AOR-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="24.84848484848485px" hyphenate="true" language="en" keep-together="always" top="109.6969696969697px" height="13.333333333333334px" width="126.06060606060606px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Authorized Representative Name</fo:block>
               </fo:block-container><!--Block below is for the label named AOR_FirstName_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="24.84848484848485px" hyphenate="true" language="en" keep-together="always" top="147.87878787878788px" height="13.333333333333334px" width="50.303030303030305px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* First Name:</fo:block>
               </fo:block-container><!--Block below is for the label named AOR_LastName_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="24.84848484848485px" hyphenate="true" language="en" keep-together="always" top="174.54545454545456px" height="13.333333333333334px" width="51.515151515151516px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Last Name:</fo:block>
               </fo:block-container><!--Block below is for the label named PDPI-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="24.84848484848485px" hyphenate="true" language="en" keep-together="always" top="262.42424242424244px" height="13.333333333333334px" width="106.66666666666667px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Principal Investigator Name</fo:block>
               </fo:block-container><!--Block below is for the label named PDPI_FirstName_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="24.84848484848485px" hyphenate="true" language="en" keep-together="always" top="299.39393939393943px" height="13.333333333333334px" width="53.333333333333336px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* First Name:</fo:block>
               </fo:block-container><!--Block below is for the label named PDPI_LastName_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="24.84848484848485px" hyphenate="true" language="en" keep-together="always" top="326.06060606060606px" height="13.333333333333334px" width="52.72727272727273px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Last Name:</fo:block>
               </fo:block-container><!--Block below is for the label named FederalAgency_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="24.84848484848485px" hyphenate="true" language="en" keep-together="always" top="415.7575757575758px" height="13.333333333333334px" width="151.51515151515153px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* If yes, Select U.S. Government agency:</fo:block>
               </fo:block-container><!--Block below is for the label named FederalAgencyDollar_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="24.84848484848485px" hyphenate="true" language="en" keep-together="always" top="447.2727272727273px" height="13.333333333333334px" width="165.75757575757578px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">If yes, enter total dollar amount requested ($): </fo:block>
               </fo:block-container><!--Block below is for the label named InternationalParticipation-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="24.84848484848485px" hyphenate="true" language="en" keep-together="always" top="480px" height="13.333333333333334px" width="302.42424242424244px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Is this person participating in this project as an employee of a foreign organization?</fo:block>
               </fo:block-container><!--Block below is for the label named USGovernmentParticipation-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="25.454545454545457px" hyphenate="true" language="en" keep-together="always" top="369.69696969696975px" height="13.333333333333334px" width="300px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">* Is this person participating in this project as an employee of the U.S. Government?</fo:block>
               </fo:block-container><!--Block below is for the label named AOR_Prefix_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="30.90909090909091px" hyphenate="true" language="en" keep-together="always" top="134.54545454545456px" height="13.333333333333334px" width="28.484848484848488px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Prefix:</fo:block>
               </fo:block-container><!--Block below is for the label named AOR_MiddleName_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="30.90909090909091px" hyphenate="true" language="en" keep-together="always" top="161.21212121212122px" height="13.333333333333334px" width="52.121212121212125px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Middle Name:</fo:block>
               </fo:block-container><!--Block below is for the label named AOR_Suffix_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="30.90909090909091px" hyphenate="true" language="en" keep-together="always" top="187.87878787878788px" height="13.333333333333334px" width="61.81818181818182px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Suffix:</fo:block>
               </fo:block-container><!--Block below is for the label named UserName_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="30.90909090909091px" hyphenate="true" language="en" keep-together="always" top="216.36363636363637px" height="13.333333333333334px" width="84.24242424242425px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">NSPIRES Username:</fo:block>
               </fo:block-container><!--Block below is for the label named PDPI_Prefix_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="30.90909090909091px" hyphenate="true" language="en" keep-together="always" top="286.06060606060606px" height="13.333333333333334px" width="29.6969696969697px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Prefix:</fo:block>
               </fo:block-container><!--Block below is for the label named PDPI_MiddleName_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="30.90909090909091px" hyphenate="true" language="en" keep-together="always" top="312.72727272727275px" height="13.333333333333334px" width="52.121212121212125px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Middle Name:</fo:block>
               </fo:block-container><!--Block below is for the label named PDPI_Suffix_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="30.90909090909091px" hyphenate="true" language="en" keep-together="always" top="339.39393939393943px" height="13.333333333333334px" width="28.484848484848488px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Suffix:</fo:block>
               </fo:block-container><!--Block below is for the label named USGovernmentParticipation_YES_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="32.72727272727273px" hyphenate="true" language="en" keep-together="always" top="383.6363636363637px" height="13.333333333333334px" width="27.87878787878788px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Yes</fo:block>
               </fo:block-container><!--Block below is for the label named InternationalParticipation_YES_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="33.333333333333336px" hyphenate="true" language="en" keep-together="always" top="495.1515151515152px" height="13.333333333333334px" width="27.87878787878788px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">Yes</fo:block>
               </fo:block-container><!--Block below is for the label named USGovernmentParticipation_NO_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="80.60606060606061px" hyphenate="true" language="en" keep-together="always" top="383.6363636363637px" height="13.333333333333334px" width="23.03030303030303px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">No</fo:block>
               </fo:block-container>

<!--Block below is for the label named d1e1876-->
                            <fo:block-container background-color="transparent" border-style="none" position="absolute" left="82.42424242424242px" hyphenate="true" language="en" keep-together="always" top="51.515151515151516px" height="20.57575757575758px" width="419.39393939393943px">
                  <fo:block background-color="transparent" color="#000000" font-size="14pt" font-style="normal" font-family="Helvetica" font-weight="bold">NASA - Principal Investigator and Authorized Representative </fo:block>
               </fo:block-container>
			   <fo:block-container background-color="transparent" border-style="none" position="absolute" left="190.42424242424242px" hyphenate="true" language="en" keep-together="always" top="67.515151515151516px" height="20.57575757575758px" width="200.39393939393943px">
                  <fo:block background-color="transparent" color="#000000" font-size="14pt" font-style="normal" font-family="Helvetica" font-weight="bold"> Supplemental Data Sheet</fo:block>
               </fo:block-container>

<!--Block below is for the label named InternationalParticipation_NO_LBL-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="83.03030303030303px" hyphenate="true" language="en" keep-together="always" top="495.1515151515152px" height="13.333333333333334px" width="23.03030303030303px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">No</fo:block>
               </fo:block-container><!--Block below is for the label named omb_number-->
               <fo:block-container background-color="transparent" border-style="none" position="absolute" left="460.5757575757576px" hyphenate="true" language="en" keep-together="always" top="27.87878787878788px" height="12.121212121212121px" width="100.03030303030303px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 2700-0085 </fo:block>
               </fo:block-container>
				<fo:block-container background-color="transparent" border-style="none" position="absolute" left="460.5757575757576px" hyphenate="true" language="en" keep-together="always" top="37.87878787878788px" height="12.121212121212121px" width="100.03030303030303px">
                  <fo:block background-color="transparent" color="#000000" font-size="8pt" font-style="normal" font-family="Helvetica" font-weight="normal">OMB Number: 2700-0087 </fo:block>
               </fo:block-container><!--Draw lines-->
               <fo:block-container background-color="black" border-style="none" position="absolute" left="17.575757575757578px" top="100.60606060606061px" width="551.5151515151515px" height="0.6060606060606061px">
                    <fo:block/>
               </fo:block-container>
               <fo:block-container background-color="black" border-style="none" position="absolute" left="17.575757575757578px" top="246.06060606060606px" width="551.5151515151515px" height="0.6060606060606061px">
                    <fo:block/>
               </fo:block-container>

               <fo:block-container background-color="black" border-style="none" position="absolute" left="15.757575757575758px" top="539.3939393939394px" width="551.5151515151515px" height="0.6060606060606061px">
                    <fo:block/>
               </fo:block-container>
               <fo:block-container background-color="black" border-style="none" position="absolute" left="16.96969696969697px" top="100px" width="0.6060606060606061px" height="440px">
                    <fo:block/>
               </fo:block-container>
               <fo:block-container background-color="black" border-style="none" position="absolute" left="567.8787878787879px" top="100.60606060606061px" width="0.6060606060606061px" height="440px">
                    <fo:block/>
               </fo:block-container>
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
