<?xml version="1.0" encoding="UTF-8"?>
<!-- $Revision:   1.7  $ -->
<xsl:stylesheet version="1.0"
 xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:fo="http://www.w3.org/1999/XSL/Format"
 xmlns:RR_SF424="http://apply.grants.gov/forms/RR_SF424-V1.1"
 xmlns:footer="http://apply.grants.gov/system/Footer-V1.0"
 xmlns:att="http://apply.grants.gov/system/Attachments-V1.0"
 xmlns:glob="http://apply.grants.gov/system/Global-V1.0"
 xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0"
 xmlns:header="http://apply.grants.gov/system/Header-V1.0"
 xmlns:xs="http://www.w3.org/2001/XMLSchema">

   <xsl:template match="RR_SF424:RR_SF424">
      <fo:root>

         <fo:layout-master-set>
            <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.2in" margin-right="0.2in">
               <fo:region-body margin-top="0.2in" margin-bottom="0.4in" />
               <fo:region-after extent=".4in"/>
            </fo:simple-page-master>
         </fo:layout-master-set>

         <fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">

            <!-- ===================================== -->
            <!-- Default Footer                        -->
            <!-- ===================================== -->
            <fo:static-content flow-name="xsl-region-after">
               <fo:table width="100%"
                  space-before.optimum="0pt"
                  space-after.optimum="0pt"
                  table-layout="fixed">
                  <fo:table-column column-width="proportional-column-width(1)"/>
                  <fo:table-column column-width="proportional-column-width(1)"/>
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
                              <fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
                              </fo:inline>
                           </fo:block>
                        </fo:table-cell>
                        <fo:table-cell hyphenate="true" language="en"
                         padding-start="0pt"
                         padding-end="0pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="center"
                         border-style="solid"
                         border-width="0pt"
                         border-color="white">
                           <fo:block>
                              <fo:inline font-size="6px" font-weight="bold">Funding Opportunity Number: <xsl:value-of select="/*/*/header:OpportunityID"/>
                              </fo:inline>
                           </fo:block>
                        </fo:table-cell>
                        <fo:table-cell hyphenate="true" language="en"
                         padding-start="0pt"
                         padding-end="0pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="right"
                         border-style="solid"
                         border-width="0pt"
                         border-color="white">
                           <fo:block>
                              <fo:inline font-size="6px" font-weight="bold">Received Date: <xsl:value-of select="translate(/*/*/footer:ReceivedDateTime, 'T', ' ')"/> Time Zone: GMT-5
                              </fo:inline>
                           </fo:block>
                        </fo:table-cell>
                        <fo:table-cell hyphenate="true" language="en"
                         line-height="9pt"
                         padding-start="0pt"
                         padding-end="0pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="right"
                         border-style="solid"
                         border-width="0pt"
                         border-color="white">
                           <fo:block><fo:inline font-size="6px" font-weight="bold">OMB Number: 4040-0001</fo:inline></fo:block>
                           <fo:block><fo:inline font-size="6px" font-weight="bold">Expiration Date: 04/30/2008</fo:inline></fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                  </fo:table-body>
               </fo:table>
            </fo:static-content>

            <fo:flow flow-name="xsl-region-body">
               <!-- ===================================== -->
               <!-- First Page                            -->
               <!-- ===================================== -->
               <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt" table-layout="fixed">
                  <fo:table-column column-width="proportional-column-width(10)"/>
                  <fo:table-column column-width="proportional-column-width(4)"/>
                  <fo:table-column column-width="proportional-column-width(6)"/>
                  <fo:table-column column-width="proportional-column-width(10)"/>
                  <fo:table-body>
                     <fo:table-row>
                        <!-- ============================ -->
                        <!-- Title                        -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         number-rows-spanned="2"
                         text-align="left"
                         padding-start="0pt"
                         padding-end="3pt"
                         padding-before="0pt"
                         padding-after="1pt"
                         display-align="before"
                         border-style="solid"
                         border-width="0pt"
                         border-color="white">
                           <fo:block font-size="9pt">APPLICATION FOR FEDERAL ASSISTANCE</fo:block>
                           <fo:block font-size="19pt" font-weight="bold">SF 424 (R&amp;R)</fo:block>
                        </fo:table-cell>
                        <!-- ============================ -->
                        <!-- Date Submitted               -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="2"
                         text-align="left"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="3pt"
                         padding-after="1pt"
                         display-align="before"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt" font-weight="bold">2. DATE SUBMITTED</fo:block>
                           <fo:block font-size="8pt" text-indent="10pt">
                              <xsl:call-template name="formatDate">
                                 <xsl:with-param name="value" select="RR_SF424:SubmittedDate"/>
                              </xsl:call-template>
                           </fo:block>
                        </fo:table-cell>
                        <!-- ============================ -->
                        <!-- Applicant Identifier         -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         text-align="left"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="3pt"
                         padding-after="1pt"
                         display-align="before"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt" font-weight="bold">Applicant Identifier</fo:block>
                           <fo:block font-size="8pt" text-indent="10pt"><xsl:value-of select="RR_SF424:ApplicantID"/></fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <!-- ============================ -->
                        <!-- Date Received By State       -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="2"
                         number-rows-spanned="2"
                         text-align="left"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="3pt"
                         padding-after="1pt"
                         display-align="before"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt" font-weight="bold">3. DATE RECEIVED BY STATE</fo:block>
                           <fo:block font-size="8pt" text-indent="10pt">
                              <xsl:call-template name="formatDate">
                                 <xsl:with-param name="value" select="RR_SF424:StateReceivedDate"/>
                              </xsl:call-template>
                           </fo:block>
                        </fo:table-cell>
                        <!-- ============================ -->
                        <!-- State Appliction Identifier  -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         number-rows-spanned="2"
                         text-align="left"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="3pt"
                         padding-after="1pt"
                         display-align="before"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt" font-weight="bold">State Application Identifier</fo:block>
                           <fo:block font-size="8pt" text-indent="10pt"><xsl:value-of select="RR_SF424:StateID"/></fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <!-- ============================ -->
                        <!-- Submission Type Heading      -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         text-align="left"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="3pt"
                         padding-after="1pt"
                         display-align="before"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt" font-weight="bold">1. * TYPE OF SUBMISSION</fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <!-- ============================ -->
                        <!-- Submission Type Values       -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         text-align="left"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="3pt"
                         padding-after="1pt"
                         display-align="before"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block/>
                           <fo:table width="100%"
                              space-before.optimum="0pt"
                              space-after.optimum="0pt"
                              table-layout="fixed">
                              <fo:table-column column-width="proportional-column-width(1)"/>
                              <fo:table-column column-width="proportional-column-width(3)"/>
                              <fo:table-column column-width="proportional-column-width(3)"/>
                              <fo:table-column column-width="proportional-column-width(3)"/>
                              <fo:table-body>
                                 <fo:table-row>
                                    <fo:table-cell hyphenate="true" language="en"
                                     number-columns-spanned="2"
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="0pt"
                                     padding-after="0pt"
                                     display-align="before"
                                     text-align="start"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt">
                                          <xsl:choose>
                                             <xsl:when test="RR_SF424:SubmissionTypeCode = 'Preapplication'">
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">Y: Yes</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:when>
                                             <xsl:otherwise>
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">N: No</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:otherwise>
                                          </xsl:choose>
                                          <fo:inline> Pre-application</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en"
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="0pt"
                                     padding-after="0pt"
                                     display-align="before"
                                     text-align="start"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt">
                                          <xsl:choose>
                                             <xsl:when test="RR_SF424:SubmissionTypeCode = 'Application'">
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">Y: Yes</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:when>
                                             <xsl:otherwise>
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">N: No</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:otherwise>
                                          </xsl:choose>
                                          <fo:inline> Application</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <!-- JIRA COEUSDEV-248 START-->
                                    <!--fo:table-cell hyphenate="true" language="en"
                                     number-rows-spanned="2"
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="0pt"
                                     padding-after="0pt"
                                     display-align="before"
                                     text-align="start"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block>
                                          <fo:leader leader-pattern="space"/>
                                       </fo:block>
                                    </fo:table-cell-->
                                    <!-- JIRA COEUSDEV-248 END-->
                                 </fo:table-row>
                                 <fo:table-row>
                                    <fo:table-cell hyphenate="true" language="en"
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="0pt"
                                     padding-after="0pt"
                                     display-align="before"
                                     text-align="start"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block>
                                          <fo:leader leader-pattern="space"/>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="false" language="en"
                                     number-columns-spanned="3"
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="0pt"
                                     padding-after="0pt"
                                     display-align="before"
                                     text-align="start"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt">
                                          <xsl:choose>
                                             <xsl:when test="RR_SF424:SubmissionTypeCode = 'Change/Corrected Application'">
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">Y: Yes</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:when>
                                             <xsl:otherwise>
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">N: No</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:otherwise>
                                          </xsl:choose>
                                          <fo:inline> Changed/Corrected Application</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                              </fo:table-body>
                           </fo:table>
                        </fo:table-cell>
                        <!-- ============================ -->
                        <!--Federal Identifier    -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="3"
                         text-align="left"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="3pt"
                         padding-after="1pt"
                         display-align="before"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-weight="bold" font-size="8pt">4. Federal Identifier</fo:block>
                         <fo:block font-size="8pt" text-indent="10pt"><xsl:value-of select="RR_SF424:FederalID"/></fo:block>
                        </fo:table-cell>
                    
                     </fo:table-row>
                     <fo:table-row>
                        <!-- ============================ -->
                        <!-- Applicant Information        -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="4"
                         text-align="left"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="3pt"
                         padding-after="1pt"
                         display-align="before"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block/>
                           <fo:table width="100%"
                              space-before.optimum="0pt"
                              space-after.optimum="0pt"
                              table-layout="fixed">
                              <fo:table-column column-width="proportional-column-width(1)"/>
                              <fo:table-column column-width="proportional-column-width(2)"/>
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
                                       <fo:block font-weight="bold" font-size="8pt">5. APPLICANT INFORMATION</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en"
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="1pt"
                                     padding-after="1pt"
                                     display-align="before"
                                     text-align="right"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt">
                                          <fo:inline font-weight="bold">* Organizational DUNS:</fo:inline>
                                          <fo:inline text-indent="10pt"><xsl:value-of select="RR_SF424:ApplicantInfo/RR_SF424:OrganizationInfo/globLib:DUNSID"/></fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                                 <fo:table-row>
                                    <fo:table-cell hyphenate="true" language="en"
                                     number-columns-spanned="2"
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="1pt"
                                     padding-after="1pt"
                                     display-align="before"
                                     text-align="start"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt">* Legal Name: <xsl:value-of select="RR_SF424:ApplicantInfo/RR_SF424:OrganizationInfo/globLib:OrganizationName"/></fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                                 <xsl:call-template name="printAddressFormatted">
                                    <xsl:with-param name="department"><xsl:value-of select="RR_SF424:ApplicantInfo/RR_SF424:OrganizationInfo/globLib:DepartmentName"/></xsl:with-param>
                                    <xsl:with-param name="division"><xsl:value-of select="RR_SF424:ApplicantInfo/RR_SF424:OrganizationInfo/globLib:DivisionName"/></xsl:with-param>
                                    <xsl:with-param name="street1"><xsl:value-of select="RR_SF424:ApplicantInfo/RR_SF424:OrganizationInfo/globLib:Address/globLib:Street1"/></xsl:with-param>
                                    <xsl:with-param name="street2"><xsl:value-of select="RR_SF424:ApplicantInfo/RR_SF424:OrganizationInfo/globLib:Address/globLib:Street2"/></xsl:with-param>
                                    <xsl:with-param name="city"><xsl:value-of select="RR_SF424:ApplicantInfo/RR_SF424:OrganizationInfo/globLib:Address/globLib:City"/></xsl:with-param>
                                    <xsl:with-param name="county"><xsl:value-of select="RR_SF424:ApplicantInfo/RR_SF424:OrganizationInfo/globLib:Address/globLib:County"/></xsl:with-param>
                                    <xsl:with-param name="state"><xsl:value-of select="RR_SF424:ApplicantInfo/RR_SF424:OrganizationInfo/globLib:Address/globLib:State"/></xsl:with-param>
                                    <xsl:with-param name="province"><xsl:value-of select="RR_SF424:ApplicantInfo/RR_SF424:OrganizationInfo/globLib:Address/globLib:Province"/></xsl:with-param>
                                    <xsl:with-param name="country"><xsl:value-of select="RR_SF424:ApplicantInfo/RR_SF424:OrganizationInfo/globLib:Address/globLib:Country"/></xsl:with-param>
                                    <xsl:with-param name="zip"><xsl:value-of select="RR_SF424:ApplicantInfo/RR_SF424:OrganizationInfo/globLib:Address/globLib:ZipPostalCode"/></xsl:with-param>
                                 </xsl:call-template>
                              </fo:table-body>
                           </fo:table>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="4"
                         text-align="left"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="3pt"
                         padding-after="1pt"
                         display-align="before"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt">Person to be contacted on matters involving this application</fo:block>
                           <xsl:call-template name="printNameFormatted">
                              <xsl:with-param name="prefix"><xsl:value-of select="RR_SF424:ApplicantInfo/RR_SF424:ContactPersonInfo/RR_SF424:Name/globLib:PrefixName"/></xsl:with-param>
                              <xsl:with-param name="first"><xsl:value-of select="RR_SF424:ApplicantInfo/RR_SF424:ContactPersonInfo/RR_SF424:Name/globLib:FirstName"/></xsl:with-param>
                              <xsl:with-param name="middle"><xsl:value-of select="RR_SF424:ApplicantInfo/RR_SF424:ContactPersonInfo/RR_SF424:Name/globLib:MiddleName"/></xsl:with-param>
                              <xsl:with-param name="last"><xsl:value-of select="RR_SF424:ApplicantInfo/RR_SF424:ContactPersonInfo/RR_SF424:Name/globLib:LastName"/></xsl:with-param>
                              <xsl:with-param name="suffix"><xsl:value-of select="RR_SF424:ApplicantInfo/RR_SF424:ContactPersonInfo/RR_SF424:Name/globLib:SuffixName"/></xsl:with-param>
                           </xsl:call-template>
                           <xsl:call-template name="printContactInfo2">
                              <xsl:with-param name="phone"><xsl:value-of select="RR_SF424:ApplicantInfo/RR_SF424:ContactPersonInfo/RR_SF424:Phone"/></xsl:with-param>
                              <xsl:with-param name="fax"><xsl:value-of select="RR_SF424:ApplicantInfo/RR_SF424:ContactPersonInfo/RR_SF424:Fax"/></xsl:with-param>
                              <xsl:with-param name="email"><xsl:value-of select="RR_SF424:ApplicantInfo/RR_SF424:ContactPersonInfo/RR_SF424:Email"/></xsl:with-param>
                           </xsl:call-template>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <!-- ============================ -->
                        <!-- Employer Identification      -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="2"
                         number-rows-spanned="2"
                         text-align="left"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="3pt"
                         padding-after="1pt"
                         display-align="before"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt">
                              <fo:inline font-weight="bold">6. * EMPLOYER IDENTIFICATION NUMBER</fo:inline>
                              <fo:inline font-style="italic"> (EIN) or (TIN)</fo:inline>
                              <fo:inline>:</fo:inline>
                           </fo:block>
                           <fo:block font-size="8pt" text-indent="10pt"><xsl:value-of select="RR_SF424:EmployerID"/></fo:block>
                        </fo:table-cell>
                        <!-- ============================ -->
                        <!-- Applicant Type               -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="2"
                         text-align="left"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="3pt"
                         padding-after="1pt"
                         display-align="before"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt" font-weight="bold">7. * TYPE OF APPLICANT</fo:block>
                           <fo:block font-size="8pt" text-indent="10pt"><xsl:value-of select="RR_SF424:ApplicantType/RR_SF424:ApplicantTypeCode"/></fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <!-- ==================================== -->
                        <!-- Applicant Type (Other and Small Biz) -->
                        <!-- ==================================== -->
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="2"
                         number-rows-spanned="2"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt">Other (Specify): <xsl:value-of select="RR_SF424:ApplicantType/RR_SF424:ApplicantTypeCodeOtherExplanation"/></fo:block>
                           <fo:table width="100%"
                              space-before.optimum="0pt"
                              space-after.optimum="0pt"
                              table-layout="fixed">
                              <fo:table-column column-width="proportional-column-width(1)"/>
                              <fo:table-column column-width="proportional-column-width(2)"/>
                              <fo:table-body>
                                 <fo:table-row>
                                    <fo:table-cell
                                     number-columns-spanned="2"
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="1pt"
                                     padding-after="1pt"
                                     display-align="before"
                                     text-align="center"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt" font-weight="bold" text-align="center">Small Business Organization Type</fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                                 <fo:table-row>
                                    <fo:table-cell
                                     padding-start="3pt"
                                     padding-end="3pt"
                                     padding-before="1pt"
                                     padding-after="1pt"
                                     display-align="before"
                                     text-align="left"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt">
                                          <xsl:call-template name="checkbox">
                                             <xsl:with-param name="value" select="RR_SF424:ApplicantType/RR_SF424:SmallBusinessOrganizationType/RR_SF424:isWomenOwned"/>
                                          </xsl:call-template>
                                          <fo:inline> Women Owned</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell
                                     padding-start="3pt"
                                     padding-end="3pt"
                                     padding-before="1pt"
                                     padding-after="1pt"
                                     display-align="before"
                                     text-align="right"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt">
                                          <xsl:call-template name="checkbox">
                                             <xsl:with-param name="value" select="RR_SF424:ApplicantType/RR_SF424:SmallBusinessOrganizationType/RR_SF424:isSociallyEconomicallyDisadvantaged"/>
                                          </xsl:call-template>
                                          <fo:inline> Socially and Economically Disadvantaged</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                              </fo:table-body>
                           </fo:table>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <!-- ============================ -->
                        <!-- Application Type             -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="2"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block/>
                           <fo:table width="100%"
                              space-before.optimum="0pt"
                              space-after.optimum="0pt"
                              table-layout="fixed">
                              <fo:table-column column-width="proportional-column-width(3)"/>
                              <fo:table-column column-width="proportional-column-width(2)"/>
                              <fo:table-column column-width="proportional-column-width(3)"/>
                              <fo:table-column column-width="proportional-column-width(2)"/>
                              <fo:table-body>
                                 <fo:table-row>
                                    <fo:table-cell
                                     number-columns-spanned="2"
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="1pt"
                                     padding-after="1pt"
                                     display-align="before"
                                     text-align="left"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt" font-weight="bold">8. * TYPE OF APPLICATION:</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell
                                     number-columns-spanned="2"
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="1pt"
                                     padding-after="1pt"
                                     display-align="before"
                                     text-align="left"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt">
                                          <xsl:choose>
                                             <xsl:when test="RR_SF424:ApplicationType/RR_SF424:ApplicationTypeCode = 'New'">
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">Y: Yes</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:when>
                                             <xsl:otherwise>
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">N: No</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:otherwise>
                                          </xsl:choose>
                                          <fo:inline> New</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                                 <fo:table-row>
                                    <fo:table-cell
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="3pt"
                                     padding-after="1pt"
                                     display-align="before"
                                     text-align="left"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt">
                                          <xsl:choose>
                                             <xsl:when test="RR_SF424:ApplicationType/RR_SF424:ApplicationTypeCode = 'Resubmission'">
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">Y: Yes</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:when>
                                             <xsl:otherwise>
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">N: No</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:otherwise>
                                          </xsl:choose>
                                          <fo:inline> Resubmission</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="3pt"
                                     padding-after="1pt"
                                     display-align="before"
                                     text-align="left"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt">
                                          <xsl:choose>
                                             <xsl:when test="RR_SF424:ApplicationType/RR_SF424:ApplicationTypeCode = 'Renewal'">
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">Y: Yes</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:when>
                                             <xsl:otherwise>
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">N: No</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:otherwise>
                                          </xsl:choose>
                                          <fo:inline> Renewal</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="3pt"
                                     padding-after="1pt"
                                     display-align="before"
                                     text-align="left"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt">
                                          <xsl:choose>
                                             <xsl:when test="RR_SF424:ApplicationType/RR_SF424:ApplicationTypeCode = 'Continuation'">
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">Y: Yes</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:when>
                                             <xsl:otherwise>
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">N: No</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:otherwise>
                                          </xsl:choose>
                                          <fo:inline> Continuation</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="3pt"
                                     padding-after="1pt"
                                     display-align="before"
                                     text-align="left"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt">
                                          <xsl:choose>
                                             <xsl:when test="RR_SF424:ApplicationType/RR_SF424:ApplicationTypeCode = 'Revision'">
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">Y: Yes</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:when>
                                             <xsl:otherwise>
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">N: No</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:otherwise>
                                          </xsl:choose>
                                          <fo:inline> Revision</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                              </fo:table-body>
                           </fo:table>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <!-- ============================ -->
                        <!-- Revision                     -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="2"
                         number-rows-spanned="2"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt">If Revision, mark appropriate box(es).</fo:block>
                           <fo:table width="100%"
                              space-before.optimum="3pt"
                              space-after.optimum="3pt"
                              table-layout="fixed">
                              <fo:table-column column-width="proportional-column-width(1)"/>
                              <fo:table-column column-width="proportional-column-width(1)"/>
                              <fo:table-column column-width="proportional-column-width(1)"/>
                              <fo:table-body>
                                 <fo:table-row>
                                    <fo:table-cell
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="1pt"
                                     padding-after="1pt"
                                     display-align="before"
                                     text-align="left"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt">
                                          <xsl:choose>
                                             <xsl:when test="RR_SF424:ApplicationType/RR_SF424:RevisionCode = 'A' or RR_SF424:ApplicationType/RR_SF424:RevisionCode = 'AC' or RR_SF424:ApplicationType/RR_SF424:RevisionCode = 'AD'">
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">Y: Yes</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:when>
                                             <xsl:otherwise>
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">N: No</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:otherwise>
                                          </xsl:choose>
                                          <fo:inline> A. Increase Award</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="1pt"
                                     padding-after="1pt"
                                     display-align="before"
                                     text-align="left"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt">
                                          <xsl:choose>
                                             <xsl:when test="RR_SF424:ApplicationType/RR_SF424:RevisionCode = 'B' or RR_SF424:ApplicationType/RR_SF424:RevisionCode = 'BC' or RR_SF424:ApplicationType/RR_SF424:RevisionCode = 'BD'">
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">Y: Yes</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:when>
                                             <xsl:otherwise>
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">N: No</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:otherwise>
                                          </xsl:choose>
                                          <fo:inline> B. Decrease Award</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="1pt"
                                     padding-after="1pt"
                                     display-align="before"
                                     text-align="left"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt">
                                          <xsl:choose>
                                             <xsl:when test="RR_SF424:ApplicationType/RR_SF424:RevisionCode = 'C' or RR_SF424:ApplicationType/RR_SF424:RevisionCode = 'AC' or RR_SF424:ApplicationType/RR_SF424:RevisionCode = 'BC'">
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">Y: Yes</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:when>
                                             <xsl:otherwise>
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">N: No</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:otherwise>
                                          </xsl:choose>
                                          <fo:inline> C. Increase Duration</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                                 <fo:table-row>
                                    <fo:table-cell
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="1pt"
                                     padding-after="1pt"
                                     display-align="before"
                                     text-align="left"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt">
                                          <xsl:choose>
                                             <xsl:when test="RR_SF424:ApplicationType/RR_SF424:RevisionCode = 'D' or RR_SF424:ApplicationType/RR_SF424:RevisionCode = 'AD' or RR_SF424:ApplicationType/RR_SF424:RevisionCode = 'BD'">
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">Y: Yes</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:when>
                                             <xsl:otherwise>
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">N: No</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:otherwise>
                                          </xsl:choose>
                                          <fo:inline> D. Decrease Duration</fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell
                                     number-columns-spanned="2"
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="1pt"
                                     padding-after="1pt"
                                     display-align="before"
                                     text-align="left"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt">
                                          <xsl:choose>
                                             <xsl:when test="RR_SF424:ApplicationType/RR_SF424:RevisionCode = 'E'">
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">Y: Yes</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:when>
                                             <xsl:otherwise>
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">N: No</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:otherwise>
                                          </xsl:choose>
                                          <fo:inline> E. Other </fo:inline>
                                          <fo:inline font-style="italic">(specify)</fo:inline>
                                          <fo:inline>: <xsl:value-of select="RR_SF424:ApplicationType/RR_SF424:RevisionCodeOtherExplanation"/></fo:inline>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                              </fo:table-body>
                           </fo:table>
                        </fo:table-cell>
                        <!-- ============================ -->
                        <!-- Federal Agency Name          -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="2"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt" font-weight="bold">9. * NAME OF FEDERAL AGENCY:</fo:block>
                           <fo:block font-size="8pt" text-indent="10pt"><xsl:value-of select="RR_SF424:FederalAgencyName"/></fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <!-- ============================ -->
                        <!-- CFDA Number                  -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="2"
                         number-rows-spanned="2"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt" font-weight="bold">10. CATALOG OF FEDERAL DOMESTIC ASSISTANCE NUMBER:</fo:block>
                           <fo:block font-size="8pt" text-indent="20pt"><xsl:value-of select="RR_SF424:CFDANumber"/></fo:block>
                           <fo:block font-size="8pt">TITLE: <xsl:value-of select="RR_SF424:ActivityTitle"/></fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <!-- ============================ -->
                        <!-- Submitted To Other Agencies  -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="2"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt">
                              <fo:inline>* Is this application being submitted to other agencies? </fo:inline>
                              <xsl:call-template name="yes_no_radio">
                                 <xsl:with-param name="value" select="RR_SF424:ApplicationType/RR_SF424:isOtherAgencySubmission"/>
                              </xsl:call-template>
                           </fo:block>
                           <fo:block font-size="8pt">What other Agencies? <xsl:value-of select="RR_SF424:ApplicationType/RR_SF424:OtherAgencySubmissionExplanation"/></fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <!-- ============================ -->
                        <!-- Project Title                -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="4"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt" font-weight="bold">11. * DESCRIPTIVE TITLE OF APPLICANT'S PROJECT:</fo:block>
                           <fo:block font-size="8pt"><xsl:value-of select="RR_SF424:ProjectTitle"/></fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <!-- ============================ -->
                        <!-- Project Affected Areas       -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="4"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt">
                              <fo:inline font-weight="bold">12. * AREAS AFFECTED BY PROJECT </fo:inline>
                              <fo:inline font-style="italic">(cities, counties, states, etc.)</fo:inline>
                           </fo:block>
                           <fo:block font-size="8pt"><xsl:value-of select="RR_SF424:Location"/></fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <!-- ============================ -->
                        <!-- Project Dates                -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="2"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt" font-weight="bold">13. PROPOSED PROJECT:</fo:block>
                           <fo:block font-size="8pt">
                              <fo:table width="90%"
                                 space-before.optimum="0pt"
                                 space-after.optimum="0pt"
                                 table-layout="fixed">
                                 <fo:table-column column-width="proportional-column-width(1)"/>
                                 <fo:table-column column-width="proportional-column-width(1)"/>
                                 <fo:table-body>
                                    <fo:table-row>
                                       <fo:table-cell
                                        padding-start="0pt"
                                        padding-end="0pt"
                                        padding-before="1pt"
                                        padding-after="1pt"
                                        display-align="before"
                                        text-align="left"
                                        border-style="solid"
                                        border-width="0pt"
                                        border-color="white">
                                          <fo:block font-size="8pt">* Start Date</fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell
                                        padding-start="0pt"
                                        padding-end="0pt"
                                        padding-before="1pt"
                                        padding-after="1pt"
                                        display-align="before"
                                        text-align="left"
                                        border-style="solid"
                                        border-width="0pt"
                                        border-color="white">
                                          <fo:block font-size="8pt">* Ending Date</fo:block>
                                       </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                       <fo:table-cell
                                        padding-start="0pt"
                                        padding-end="0pt"
                                        padding-before="1pt"
                                        padding-after="1pt"
                                        display-align="before"
                                        text-align="left"
                                        border-style="solid"
                                        border-width="0pt"
                                        border-color="white">
                                          <fo:block font-size="8pt">
                                             <xsl:call-template name="formatDate">
                                                <xsl:with-param name="value" select="RR_SF424:ProposedProjectPeriod/RR_SF424:ProposedStartDate"/>
                                             </xsl:call-template>
                                          </fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell
                                        padding-start="0pt"
                                        padding-end="0pt"
                                        padding-before="1pt"
                                        padding-after="1pt"
                                        display-align="before"
                                        text-align="left"
                                        border-style="solid"
                                        border-width="0pt"
                                        border-color="white">
                                          <fo:block font-size="8pt">
                                             <xsl:call-template name="formatDate">
                                                <xsl:with-param name="value" select="RR_SF424:ProposedProjectPeriod/RR_SF424:ProposedEndDate"/>
                                             </xsl:call-template>
                                          </fo:block>
                                       </fo:table-cell>
                                    </fo:table-row>
                                 </fo:table-body>
                              </fo:table>
                           </fo:block>
                        </fo:table-cell>
                        <!-- ============================ -->
                        <!-- Congressional Districts      -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="2"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt" font-weight="bold">14. CONGRESSIONAL DISTRICTS OF:</fo:block>
                           <fo:block font-size="8pt">
                              <fo:table width="100%"
                                 space-before.optimum="0pt"
                                 space-after.optimum="0pt"
                                 table-layout="fixed">
                                 <fo:table-column column-width="proportional-column-width(1)"/>
                                 <fo:table-column column-width="proportional-column-width(1)"/>
                                 <fo:table-body>
                                    <fo:table-row>
                                       <fo:table-cell
                                        padding-start="0pt"
                                        padding-end="0pt"
                                        padding-before="1pt"
                                        padding-after="1pt"
                                        display-align="before"
                                        text-align="left"
                                        border-style="solid"
                                        border-width="0pt"
                                        border-color="white">
                                          <fo:block font-size="8pt">a. * Applicant</fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell
                                        padding-start="0pt"
                                        padding-end="0pt"
                                        padding-before="1pt"
                                        padding-after="1pt"
                                        display-align="before"
                                        text-align="left"
                                        border-style="solid"
                                        border-width="0pt"
                                        border-color="white">
                                          <fo:block font-size="8pt">b. * Project</fo:block>
                                       </fo:table-cell>
                                    </fo:table-row>
                                    <fo:table-row>
                                       <fo:table-cell
                                        padding-start="0pt"
                                        padding-end="0pt"
                                        padding-before="1pt"
                                        padding-after="1pt"
                                        display-align="before"
                                        text-align="left"
                                        border-style="solid"
                                        border-width="0pt"
                                        border-color="white">
                                          <fo:block font-size="8pt"><xsl:value-of select="RR_SF424:CongressionalDistrict/RR_SF424:ApplicantCongressionalDistrict"/></fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell
                                        padding-start="0pt"
                                        padding-end="0pt"
                                        padding-before="1pt"
                                        padding-after="1pt"
                                        display-align="before"
                                        text-align="left"
                                        border-style="solid"
                                        border-width="0pt"
                                        border-color="white">
                                          <fo:block font-size="8pt"><xsl:value-of select="RR_SF424:CongressionalDistrict/RR_SF424:ProjectCongressionalDistrict"/></fo:block>
                                       </fo:table-cell>
                                    </fo:table-row>
                                 </fo:table-body>
                              </fo:table>
                           </fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <!-- ============================ -->
                        <!-- Project Director Information -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="4"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt" font-weight="bold">15. PROJECT DIRECTOR/PRINCIPAL INVESTIGATOR CONTACT INFORMATION</fo:block>
                           <xsl:call-template name="printNameFormatted">
                              <xsl:with-param name="prefix"><xsl:value-of select="RR_SF424:PDPIContactInfo/RR_SF424:Name/globLib:PrefixName"/></xsl:with-param>
                              <xsl:with-param name="first"><xsl:value-of select="RR_SF424:PDPIContactInfo/RR_SF424:Name/globLib:FirstName"/></xsl:with-param>
                              <xsl:with-param name="middle"><xsl:value-of select="RR_SF424:PDPIContactInfo/RR_SF424:Name/globLib:MiddleName"/></xsl:with-param>
                              <xsl:with-param name="last"><xsl:value-of select="RR_SF424:PDPIContactInfo/RR_SF424:Name/globLib:LastName"/></xsl:with-param>
                              <xsl:with-param name="suffix"><xsl:value-of select="RR_SF424:PDPIContactInfo/RR_SF424:Name/globLib:SuffixName"/></xsl:with-param>
                           </xsl:call-template>
                           <fo:table width="100%"
                              space-before.optimum="0pt"
                              space-after.optimum="0pt"
                              table-layout="fixed">
                              <fo:table-column column-width="proportional-column-width(1)"/>
                              <fo:table-column column-width="proportional-column-width(2)"/>
                              <fo:table-body>
                                 <fo:table-row>
                                    <fo:table-cell hyphenate="true" language="en"
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="1pt"
                                     padding-after="1pt"
                                     display-align="before"
                                     text-align="start"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt">
                                          Position/Title: <xsl:value-of select="RR_SF424:PDPIContactInfo/RR_SF424:Title"/>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en"
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="1pt"
                                     padding-after="1pt"
                                     display-align="before"
                                     text-align="start"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt">
                                          * Organization Name: <xsl:value-of select="RR_SF424:PDPIContactInfo/RR_SF424:OrganizationName"/>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                                 <xsl:call-template name="printAddressFormatted">
                                    <xsl:with-param name="department"><xsl:value-of select="RR_SF424:PDPIContactInfo/RR_SF424:DepartmentName"/></xsl:with-param>
                                    <xsl:with-param name="division"><xsl:value-of select="RR_SF424:PDPIContactInfo/RR_SF424:DivisionName"/></xsl:with-param>
                                    <xsl:with-param name="street1"><xsl:value-of select="RR_SF424:PDPIContactInfo/RR_SF424:Address/globLib:Street1"/></xsl:with-param>
                                    <xsl:with-param name="street2"><xsl:value-of select="RR_SF424:PDPIContactInfo/RR_SF424:Address/globLib:Street2"/></xsl:with-param>
                                    <xsl:with-param name="city"><xsl:value-of select="RR_SF424:PDPIContactInfo/RR_SF424:Address/globLib:City"/></xsl:with-param>
                                    <xsl:with-param name="county"><xsl:value-of select="RR_SF424:PDPIContactInfo/RR_SF424:Address/globLib:County"/></xsl:with-param>
                                    <xsl:with-param name="state"><xsl:value-of select="RR_SF424:PDPIContactInfo/RR_SF424:Address/globLib:State"/></xsl:with-param>
                                    <xsl:with-param name="province"><xsl:value-of select="RR_SF424:PDPIContactInfo/RR_SF424:Address/globLib:Province"/></xsl:with-param>
                                    <xsl:with-param name="country"><xsl:value-of select="RR_SF424:PDPIContactInfo/RR_SF424:Address/globLib:Country"/></xsl:with-param>
                                    <xsl:with-param name="zip"><xsl:value-of select="RR_SF424:PDPIContactInfo/RR_SF424:Address/globLib:ZipPostalCode"/></xsl:with-param>
                                 </xsl:call-template>
                              </fo:table-body>
                           </fo:table>
                           <xsl:call-template name="printContactInfo">
                              <xsl:with-param name="phone"><xsl:value-of select="RR_SF424:PDPIContactInfo/RR_SF424:Phone"/></xsl:with-param>
                              <xsl:with-param name="fax"><xsl:value-of select="RR_SF424:PDPIContactInfo/RR_SF424:Fax"/></xsl:with-param>
                              <xsl:with-param name="email"><xsl:value-of select="RR_SF424:PDPIContactInfo/RR_SF424:Email"/></xsl:with-param>
                           </xsl:call-template>
                        </fo:table-cell>
                     </fo:table-row>
                  </fo:table-body>
               </fo:table>
               <!-- ============================ -->
               <!-- Next Page                    -->
               <!-- ============================ -->
               <fo:block break-after="page">
                  <xsl:text>&#xA;</xsl:text>
               </fo:block>
               <fo:table width="100%"
                  space-before.optimum="0pt"
                  space-after.optimum="0pt"
                  table-layout="fixed">
                  <fo:table-column column-width="proportional-column-width(7)"/>
                  <fo:table-column column-width="proportional-column-width(3)"/>
                  <fo:table-body>
                     <!-- ============================ -->
                     <!-- Heading                      -->
                     <!-- ============================ -->
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
                              <fo:inline font-size="19pt" font-weight="bold">SF 424 (R&amp;R) </fo:inline>
                              <fo:inline font-size="9pt">APPLICATION FOR FEDERAL ASSISTANCE</fo:inline>
                           </fo:block>
                        </fo:table-cell>
                        <fo:table-cell hyphenate="true" language="en"
                         padding-start="0pt"
                         padding-end="0pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="right"
                         border-style="solid"
                         border-width="0pt"
                         border-color="white">
                           <fo:block font-size="19pt" font-weight="bold">Page 2</fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                  </fo:table-body>
               </fo:table>
               <fo:table width="100%"
                  space-before.optimum="0pt"
                  space-after.optimum="0pt"
                  table-layout="fixed">
                  <fo:table-column column-width="proportional-column-width(4)"/>
                  <fo:table-column column-width="proportional-column-width(6)"/>
                  <fo:table-body>
                     <fo:table-row>
                        <!-- ============================ -->
                        <!-- Estimated Project Funding    -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         padding-start="3pt"
                         padding-end="0pt"
                         padding-before="1pt"
                         padding-after="0pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt" font-weight="bold">16. ESTIMATED PROJECT FUNDING</fo:block>
                           <fo:block>
                              <fo:table width="100%"
                                 space-before.optimum="0pt"
                                 space-after.optimum="0pt"
                                 table-layout="fixed">
                                 <fo:table-column column-width="proportional-column-width(3)"/>
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
                                          <fo:block font-size="8pt"><fo:leader leader-pattern="space"/></fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell hyphenate="true" language="en"
                                        padding-start="3pt"
                                        padding-end="3pt"
                                        padding-before="3pt"
                                        padding-after="3pt"
                                        display-align="before"
                                        text-align="right">
                                          <fo:block font-size="8pt"><fo:leader leader-pattern="space"/></fo:block>
                                       </fo:table-cell>
                                    </fo:table-row>
                                 </fo:table-body>
                              </fo:table>
                           </fo:block>
                        </fo:table-cell>
                        <!-- ============================ -->
                        <!-- Application Review By State  -->
                        <!-- ============================ -->
                        <fo:table-cell hyphenate="true" language="en"
                         number-rows-spanned="2"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block/>
                           <fo:table width="80%"
                              space-before.optimum="0pt"
                              space-after.optimum="0pt"
                              table-layout="fixed">
                              <fo:table-column column-width="proportional-column-width(4)"/>
                              <fo:table-column column-width="proportional-column-width(96)"/>
                              <fo:table-body>
                                 <fo:table-row>
                                    <fo:table-cell hyphenate="true" language="en"
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="0pt"
                                     padding-after="0pt"
                                     display-align="before"
                                     text-align="left"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt" font-weight="bold">17.</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en"
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="0pt"
                                     padding-after="0pt"
                                     display-align="before"
                                     text-align="left"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt" font-weight="bold">* IS APPLICATION SUBJECT TO REVIEW BY STATE EXECUTIVE ORDER 12372 PROCESS?</fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                              </fo:table-body>
                           </fo:table>
                           <fo:table width="100%"
                              space-before.optimum="0pt"
                              space-after.optimum="0pt"
                              table-layout="fixed">
                              <fo:table-column column-width="proportional-column-width(3)"/>
                              <fo:table-column column-width="proportional-column-width(10)"/>
                              <fo:table-column column-width="proportional-column-width(4)"/>
                              <fo:table-column column-width="proportional-column-width(83)"/>
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
                                       <fo:block font-size="8pt">a.</fo:block>
                                    </fo:table-cell>
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
                                       <fo:block font-size="8pt">YES</fo:block>
                                    </fo:table-cell>
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
                                          <xsl:call-template name="checkbox">
                                             <xsl:with-param name="value" select="RR_SF424:StateReview/RR_SF424:StateReviewCodeType"/>
                                          </xsl:call-template>
                                       </fo:block>
                                    </fo:table-cell>
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
                                       <fo:block font-size="8pt">THIS PREAPPLICATION/APPLICATION WAS MADE AVAILABLE TO THE STATE EXECUTIVE ORDER 12372 PROCESS FOR REVIEW ON:</fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
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
                                          <fo:leader leader-pattern="space"/>
                                       </fo:block>
                                    </fo:table-cell>
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
                                       <fo:block font-size="8pt">DATE:</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en"
                                     number-columns-spanned="2"
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="1pt"
                                     padding-after="1pt"
                                     display-align="before"
                                     text-align="left"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt">
                                          <xsl:call-template name="formatDate">
                                             <xsl:with-param name="value" select="RR_SF424:StateReview/RR_SF424:StateReviewDate"/>
                                          </xsl:call-template>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
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
                                       <fo:block font-size="8pt">b.</fo:block>
                                    </fo:table-cell>
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
                                       <fo:block font-size="8pt">NO</fo:block>
                                    </fo:table-cell>
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
                                          <xsl:choose>
                                             <xsl:when test="RR_SF424:StateReview/RR_SF424:StateReviewCodeType = 'Program is not covered by E.O. 12372'">
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">Y: Yes</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:when>
                                             <xsl:otherwise>
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">N: No</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:otherwise>
                                          </xsl:choose>
                                       </fo:block>
                                    </fo:table-cell>
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
                                       <fo:block font-size="8pt">PROGRAM IS NOT COVERED BY E.O. 12372; OR</fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
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
                                       <fo:block font-size="8pt"><fo:leader leader-pattern="space"/></fo:block>
                                    </fo:table-cell>
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
                                       <fo:block font-size="8pt"><fo:leader leader-pattern="space"/></fo:block>
                                    </fo:table-cell>
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
                                          <xsl:choose>
                                             <xsl:when test="RR_SF424:StateReview/RR_SF424:StateReviewCodeType = 'Program has not been selected by state for review'">
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">Y: Yes</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:when>
                                             <xsl:otherwise>
                                                <xsl:call-template name="checkbox">
                                                   <xsl:with-param name="value">N: No</xsl:with-param>
                                                </xsl:call-template>
                                             </xsl:otherwise>
                                          </xsl:choose>
                                       </fo:block>
                                    </fo:table-cell>
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
                                       <fo:block font-size="8pt">PROGRAM HAS NOT BEEN SELECTED BY STATE FOR REVIEW</fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                              </fo:table-body>
                           </fo:table>
                        </fo:table-cell>
                     </fo:table-row>
                     <!-- ============================ -->
                     <!-- Funding values               -->
                     <!-- ============================ -->
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block/>
                           <fo:table width="100%"
                              space-before.optimum="0pt"
                              space-after.optimum="0pt"
                              table-layout="fixed">
                              <fo:table-column column-width="proportional-column-width(65)"/>
                              <fo:table-column column-width="proportional-column-width(35)"/>
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
                                       <fo:block font-size="8pt">a. * Total Estimated Project Funding</fo:block>
                                    </fo:table-cell>
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
                                       <fo:block font-size="8pt">
                                          <xsl:value-of select="format-number(RR_SF424:EstimatedProjectFunding/RR_SF424:TotalEstimatedAmount, '$#,##0.00')"/>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
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
                                       <fo:block font-size="8pt">b. * Total Federal &amp; Non-Federal Funds</fo:block>
                                    </fo:table-cell>
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
                                       <fo:block font-size="8pt">
                                          <xsl:value-of select="format-number(RR_SF424:EstimatedProjectFunding/RR_SF424:TotalfedNonfedrequested, '$#,##0.00')"/>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
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
                                       <fo:block font-size="8pt">c. * Estimated Program Income</fo:block>
                                    </fo:table-cell>
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
                                       <fo:block font-size="8pt">
                                          <xsl:value-of select="format-number(RR_SF424:EstimatedProjectFunding/RR_SF424:EstimatedProgramIncome, '$#,##0.00')"/>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>

                              </fo:table-body>
                           </fo:table>
                        </fo:table-cell>
                     </fo:table-row>
                     <!-- ============================ -->
                     <!-- Agreement                    -->
                     <!-- ============================ -->
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="2"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:table width="80%"
                              space-before.optimum="0pt"
                              space-after.optimum="0pt"
                              table-layout="fixed">
                              <fo:table-column column-width="proportional-column-width(25)"/>
                              <fo:table-column column-width="proportional-column-width(975)"/>
                              <fo:table-body>
                                 <fo:table-row>
                                    <fo:table-cell hyphenate="true" language="en"
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="0pt"
                                     padding-after="0pt"
                                     display-align="before"
                                     text-align="left"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt" font-weight="bold">18.</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en"
                                     padding-start="0pt"
                                     padding-end="0pt"
                                     padding-before="0pt"
                                     padding-after="0pt"
                                     display-align="before"
                                     text-align="left"
                                     border-style="solid"
                                     border-width="0pt"
                                     border-color="white">
                                       <fo:block font-size="8pt" font-weight="bold">By signing this application, I certify (1) to the statements contained in the list of certifications* and (2) that the statements herein are true, complete and accurate to the best of my knowledge. I also provide the required assurances * and agree to comply with any resulting terms if I accept an award. I am aware that any false, fictitious, or fraudulent statements or claims may subject me to criminal, civil, or administrative penalties. (U.S. Code, Title 18, Section 1001)</fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                              </fo:table-body>
                           </fo:table>
                           <fo:block>
                              <fo:table width="100%"
                                 space-before.optimum="0pt"
                                 space-after.optimum="0pt"
                                 table-layout="fixed">
                                 <fo:table-column column-width="proportional-column-width(1)"/>
                                 <fo:table-column column-width="proportional-column-width(7)"/>
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
                                          <fo:block font-size="8pt"><fo:leader leader-pattern="space"/></fo:block>
                                       </fo:table-cell>
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
                                          <fo:block font-size="8pt">
                                             <xsl:call-template name="checkbox">
                                                <xsl:with-param name="value" select="RR_SF424:TrustAgree"/>
                                             </xsl:call-template>
                                             <fo:inline> * I agree</fo:inline>
                                          </fo:block>
                                       </fo:table-cell>
                                    </fo:table-row>
                                 </fo:table-body>
                              </fo:table>
                           </fo:block>
                           <fo:block>
                              <fo:table width="100%"
                                 space-before.optimum="0pt"
                                 space-after.optimum="0pt"
                                 table-layout="fixed">
                                 <fo:table-column column-width="proportional-column-width(25)"/>
                                 <fo:table-column column-width="proportional-column-width(975)"/>
                                 <fo:table-body>
                                    <fo:table-row>
                                       <fo:table-cell hyphenate="true" language="en"
                                        padding-start="0pt"
                                        padding-end="0pt"
                                        padding-before="0pt"
                                        padding-after="0pt"
                                        display-align="before"
                                        text-align="left"
                                        border-style="solid"
                                        border-width="0pt"
                                        border-color="white">
                                          <fo:block font-size="8pt" font-weight="bold"><fo:leader leader-pattern="space"/></fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell hyphenate="true" language="en"
                                        padding-start="0pt"
                                        padding-end="0pt"
                                        padding-before="0pt"
                                        padding-after="0pt"
                                        display-align="before"
                                        text-align="left"
                                        border-style="solid"
                                        border-width="0pt"
                                        border-color="white">
                                          <fo:block font-size="6pt" font-style="italic">* The list of certifications and assurances, or an Internet site where you may obtain this list, is contained in the announcement or agency specific instructions.</fo:block>
                                       </fo:table-cell>
                                    </fo:table-row>
                                 </fo:table-body>
                              </fo:table>
                           </fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <!-- ============================ -->
                     <!-- AOR Information              -->
                     <!-- ============================ -->
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="2"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt" font-weight="bold">19. Authorized Representative</fo:block>
                           <fo:block font-size="8pt">
                              <xsl:call-template name="printNameFormatted">
                                 <xsl:with-param name="prefix"><xsl:value-of select="RR_SF424:AORInfo/RR_SF424:Name/globLib:PrefixName"/></xsl:with-param>
                                 <xsl:with-param name="first"><xsl:value-of select="RR_SF424:AORInfo/RR_SF424:Name/globLib:FirstName"/></xsl:with-param>
                                 <xsl:with-param name="middle"><xsl:value-of select="RR_SF424:AORInfo/RR_SF424:Name/globLib:MiddleName"/></xsl:with-param>
                                 <xsl:with-param name="last"><xsl:value-of select="RR_SF424:AORInfo/RR_SF424:Name/globLib:LastName"/></xsl:with-param>
                                 <xsl:with-param name="suffix"><xsl:value-of select="RR_SF424:AORInfo/RR_SF424:Name/globLib:SuffixName"/></xsl:with-param>
                              </xsl:call-template>
                           </fo:block>
                           <fo:block font-size="8pt">
                              <fo:table width="100%"
                                 space-before.optimum="0pt"
                                 space-after.optimum="0pt"
                                 table-layout="fixed">
                                 <fo:table-column column-width="proportional-column-width(1)"/>
                                 <fo:table-column column-width="proportional-column-width(2)"/>
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
                                          <fo:block font-size="8pt">* Position/Title: <xsl:value-of select="RR_SF424:AORInfo/RR_SF424:Title"/></fo:block>
                                       </fo:table-cell>
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
                                          <fo:block font-size="8pt">* Organization Name: <xsl:value-of select="RR_SF424:AORInfo/RR_SF424:OrganizationName"/></fo:block>
                                       </fo:table-cell>
                                    </fo:table-row>
                                    <xsl:call-template name="printAddressFormatted">
                                       <xsl:with-param name="department"><xsl:value-of select="RR_SF424:AORInfo/RR_SF424:DepartmentName"/></xsl:with-param>
                                       <xsl:with-param name="division"><xsl:value-of select="RR_SF424:AORInfo/RR_SF424:DivisionName"/></xsl:with-param>
                                       <xsl:with-param name="street1"><xsl:value-of select="RR_SF424:AORInfo/RR_SF424:Address/globLib:Street1"/></xsl:with-param>
                                       <xsl:with-param name="street2"><xsl:value-of select="RR_SF424:AORInfo/RR_SF424:Address/globLib:Street2"/></xsl:with-param>
                                       <xsl:with-param name="city"><xsl:value-of select="RR_SF424:AORInfo/RR_SF424:Address/globLib:City"/></xsl:with-param>
                                       <xsl:with-param name="county"><xsl:value-of select="RR_SF424:AORInfo/RR_SF424:Address/globLib:County"/></xsl:with-param>
                                       <xsl:with-param name="state"><xsl:value-of select="RR_SF424:AORInfo/RR_SF424:Address/globLib:State"/></xsl:with-param>
                                       <xsl:with-param name="province"><xsl:value-of select="RR_SF424:AORInfo/RR_SF424:Address/globLib:Province"/></xsl:with-param>
                                       <xsl:with-param name="country"><xsl:value-of select="RR_SF424:AORInfo/RR_SF424:Address/globLib:Country"/></xsl:with-param>
                                       <xsl:with-param name="zip"><xsl:value-of select="RR_SF424:AORInfo/RR_SF424:Address/globLib:ZipPostalCode"/></xsl:with-param>
                                    </xsl:call-template>
                                 </fo:table-body>
                              </fo:table>
                           </fo:block>
                           <fo:block font-size="8pt">
                              <xsl:call-template name="printContactInfo">
                                 <xsl:with-param name="phone"><xsl:value-of select="RR_SF424:AORInfo/RR_SF424:Phone"/></xsl:with-param>
                                 <xsl:with-param name="fax"><xsl:value-of select="RR_SF424:AORInfo/RR_SF424:Fax"/></xsl:with-param>
                                 <xsl:with-param name="email"><xsl:value-of select="RR_SF424:AORInfo/RR_SF424:Email"/></xsl:with-param>
                              </xsl:call-template>
                           </fo:block>
                           <fo:block font-size="8pt">
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
                                        text-align="center"
                                        border-style="solid"
                                        border-width="0pt"
                                        border-color="white">
                                          <fo:block font-size="8pt"><fo:leader leader-pattern="space"/></fo:block>
                                          <fo:block font-size="8pt" font-weight="bold">* Signature of Authorized Representative</fo:block>
                                          <fo:block font-size="8pt"><fo:leader leader-pattern="space"/></fo:block>
                                          <fo:block font-size="8pt"><xsl:value-of select="RR_SF424:AOR_Signature"/></fo:block>
                                          <fo:block font-size="8pt"><fo:leader leader-pattern="rule" leader-length="90%"/></fo:block>
                                       </fo:table-cell>
                                       <fo:table-cell hyphenate="true" language="en"
                                        padding-start="0pt"
                                        padding-end="0pt"
                                        padding-before="1pt"
                                        padding-after="1pt"
                                        display-align="before"
                                        text-align="center"
                                        border-style="solid"
                                        border-width="0pt"
                                        border-color="white">
                                          <fo:block font-size="8pt"><fo:leader leader-pattern="space"/></fo:block>
                                          <fo:block font-size="8pt" font-weight="bold">* Date Signed</fo:block>
                                          <fo:block font-size="8pt"><fo:leader leader-pattern="space"/></fo:block>
                                          <fo:block font-size="8pt">
                                             <xsl:call-template name="formatDate">
                                                <xsl:with-param name="value" select="RR_SF424:AOR_SignedDate"/>
                                             </xsl:call-template>
                                          </fo:block>
                                          <fo:block font-size="8pt"><fo:leader leader-pattern="rule" leader-length="90%"/></fo:block>
                                       </fo:table-cell>
                                    </fo:table-row>
                                 </fo:table-body>
                              </fo:table>
                           </fo:block>
                           <fo:block>
                              <fo:leader leader-pattern="space"/>
                           </fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="2"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt">
                              <fo:inline font-weight="bold">20. Pre-application</fo:inline>
                              <fo:inline>&#160;&#160;&#160;File Name: <xsl:value-of select="RR_SF424:PreApplicationAttachment/att:FileName"/></fo:inline>
                              <fo:inline>&#160;&#160;&#160;Mime Type: <xsl:value-of select="RR_SF424:PreApplicationAttachment/att:MimeType"/></fo:inline>
                           </fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     
                     
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="2"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt">
                              <fo:inline font-weight="bold">21.  Attach an additional list of Project Congressional Districts if needed.</fo:inline>
                           </fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     
                     <fo:table-row>
                        <fo:table-cell hyphenate="true" language="en"
                         number-columns-spanned="2"
                         padding-start="3pt"
                         padding-end="3pt"
                         padding-before="1pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="left"
                         border-style="solid"
                         border-width="1pt"
                         border-color="black">
                           <fo:block font-size="8pt">
                              <fo:inline>&#160;&#160;&#160;File Name: <xsl:value-of select="RR_SF424:AdditionalCongressionalDistricts/att:FileName"/></fo:inline>
                              <fo:inline>&#160;&#160;&#160;Mime Type: <xsl:value-of select="RR_SF424:AdditionalCongressionalDistricts/att:MimeType"/></fo:inline>
                           </fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                     
                     
                     
                     
                  </fo:table-body>
               </fo:table>
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
         <xsl:text>/</xsl:text>
         <xsl:value-of select="format-number(substring($value,9,2), '00')"/>
         <xsl:text>/</xsl:text>
         <xsl:value-of select="format-number(substring($value,1,4), '0000')"/>
      </xsl:if>
   </xsl:template>

   <!-- ============================================= -->
   <!-- PRINT ADDRESS                                 -->
   <!-- Prints address in the usual format            -->
   <!-- ============================================= -->
   <xsl:template name="printAddress">
      <xsl:param name="street1"/>
      <xsl:param name="street2"/>
      <xsl:param name="city"/>
      <xsl:param name="state"/>
      <xsl:param name="province"/>
      <xsl:param name="country"/>
      <xsl:param name="zip"/>
      <fo:block line-height="9pt">
         <fo:block font-size="8pt">
            <xsl:value-of select="$street1"/>
         </fo:block>
         <fo:block font-size="8pt">
             <xsl:value-of select="$street2"/>
         </fo:block>
         <fo:block font-size="8pt">
             <xsl:value-of select="$city"/>,&#160;
             <xsl:value-of select="$state"/>&#160;
             <xsl:value-of select="$province"/>&#160;
             <xsl:value-of select="$country"/>
             <xsl:value-of select="$zip"/>&#160;
         </fo:block>
      </fo:block>
   </xsl:template>

   <!-- ============================================= -->
   <!-- PRINT ADDRESS FORMATTED                       -->
   <!-- Prints address in the format used in XFD      -->
   <!-- ============================================= -->
   <xsl:template name="printAddressFormatted">
      <xsl:param name="department"/>
      <xsl:param name="division"/>
      <xsl:param name="street1"/>
      <xsl:param name="street2"/>
      <xsl:param name="city"/>
      <xsl:param name="county"/>
      <xsl:param name="state"/>
      <xsl:param name="province"/>
      <xsl:param name="country"/>
      <xsl:param name="zip"/>
      <fo:table-row>
         <fo:table-cell hyphenate="true" language="en"
          padding-start="0pt"
          padding-end="0pt"
          padding-before="1pt"
          padding-after="1pt"
          display-align="before"
          text-align="start"
          border-style="solid"
          border-width="0pt"
          border-color="white">
            <fo:block font-size="8pt">
               Department: <xsl:value-of select="$department"/>
            </fo:block>
         </fo:table-cell>
         <fo:table-cell hyphenate="true" language="en"
          padding-start="0pt"
          padding-end="0pt"
          padding-before="1pt"
          padding-after="1pt"
          display-align="before"
          text-align="start"
          border-style="solid"
          border-width="0pt"
          border-color="white">
            <fo:block font-size="8pt">
               Division: <xsl:value-of select="$division"/>
            </fo:block>
         </fo:table-cell>
      </fo:table-row>
      <fo:table-row>
         <fo:table-cell hyphenate="true" language="en"
          padding-start="0pt"
          padding-end="0pt"
          padding-before="1pt"
          padding-after="1pt"
          display-align="before"
          text-align="start"
          border-style="solid"
          border-width="0pt"
          border-color="white">
            <fo:block font-size="8pt">
              * Street1: <xsl:value-of select="$street1"/>
            </fo:block>
         </fo:table-cell>
         <fo:table-cell hyphenate="true" language="en"
          padding-start="0pt"
          padding-end="0pt"
          padding-before="1pt"
          padding-after="1pt"
          display-align="before"
          text-align="start"
          border-style="solid"
          border-width="0pt"
          border-color="white">
            <fo:block font-size="8pt">
               Street2: <xsl:value-of select="$street2"/>
            </fo:block>
         </fo:table-cell>
      </fo:table-row>
      <fo:table-row>
         <fo:table-cell hyphenate="true" language="en"
          number-columns-spanned="2"
          padding-start="0pt"
          padding-end="0pt"
          padding-before="1pt"
          padding-after="1pt"
          display-align="before"
          text-align="start"
          border-style="solid"
          border-width="0pt"
          border-color="white">
            <fo:block/>
            <fo:table width="100%"
               space-before.optimum="0pt"
               space-after.optimum="0pt"
               table-layout="fixed">
               <fo:table-column column-width="proportional-column-width(2)"/>
               <fo:table-column column-width="proportional-column-width(2)"/>
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
                        <fo:block font-size="8pt">* City: <xsl:value-of select="$city"/></fo:block>
                     </fo:table-cell>
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
                        <fo:block font-size="8pt">County: <xsl:value-of select="$county"/></fo:block>
                     </fo:table-cell>
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
                        <fo:block font-size="8pt">* State: <xsl:value-of select="$state"/></fo:block>
                     </fo:table-cell>
                  </fo:table-row>
               </fo:table-body>
            </fo:table>
         </fo:table-cell>
      </fo:table-row>
      <fo:table-row>
         <fo:table-cell hyphenate="true" language="en"
          number-columns-spanned="2"
          padding-start="0pt"
          padding-end="0pt"
          padding-before="1pt"
          padding-after="1pt"
          display-align="before"
          text-align="start"
          border-style="solid"
          border-width="0pt"
          border-color="white">
            <fo:block/>
            <fo:table width="100%"
               space-before.optimum="0pt"
               space-after.optimum="0pt"
               table-layout="fixed">
               <fo:table-column column-width="proportional-column-width(2)"/>
               <fo:table-column column-width="proportional-column-width(2)"/>
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
                        <fo:block font-size="8pt">Province: <xsl:value-of select="$province"/></fo:block>
                     </fo:table-cell>
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
                        <fo:block font-size="8pt">* Country: <xsl:value-of select="$country"/></fo:block>
                     </fo:table-cell>
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
                        <fo:block font-size="8pt">* ZIP / Postal Code: <xsl:value-of select="$zip"/></fo:block>
                     </fo:table-cell>
                  </fo:table-row>
               </fo:table-body>
            </fo:table>
         </fo:table-cell>
      </fo:table-row>      
   </xsl:template>

   <!-- ============================================= -->
   <!-- PRINT NAME                                    -->
   <!-- Prints name in the usual format               -->
   <!-- ============================================= -->
   <xsl:template name="printName">
      <xsl:param name="prefix"/>
      <xsl:param name="first"/>
      <xsl:param name="middle"/>
      <xsl:param name="last"/>
      <xsl:param name="suffix"/>
      <xsl:if test="$prefix != ''">
         <xsl:value-of select="$prefix"/>&#160;
      </xsl:if>
      <xsl:value-of select="$first"/>&#160;
      <xsl:if test="$middle != ''">
         <xsl:value-of select="$middle"/>&#160;
      </xsl:if>
      <xsl:value-of select="$last"/>&#160;
      <xsl:if test="$suffix != ''">
         <xsl:value-of select="$suffix"/>&#160;
      </xsl:if>
   </xsl:template>

   <!-- ============================================= -->
   <!-- PRINT NAME FORMATTED                          -->
   <!-- Prints name in the same format as the XFD     -->
   <!-- ============================================= -->
   <xsl:template name="printNameFormatted">
      <xsl:param name="prefix"/>
      <xsl:param name="first"/>
      <xsl:param name="middle"/>
      <xsl:param name="last"/>
      <xsl:param name="suffix"/>
      <fo:table width="100%"
         space-before.optimum="0pt"
         space-after.optimum="0pt"
         table-layout="fixed">
         <fo:table-column column-width="proportional-column-width(1)"/>
         <fo:table-column column-width="proportional-column-width(3)"/>
         <fo:table-column column-width="proportional-column-width(2)"/>
         <fo:table-column column-width="proportional-column-width(3)"/>
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
                  <fo:block font-size="8pt">Prefix:</fo:block>
               </fo:table-cell>
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
                  <fo:block font-size="8pt">* First Name:</fo:block>
               </fo:table-cell>
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
                  <fo:block font-size="8pt">Middle Name:</fo:block>
               </fo:table-cell>
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
                  <fo:block font-size="8pt">* Last Name:</fo:block>
               </fo:table-cell>
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
                  <fo:block font-size="8pt">Suffix:</fo:block>
               </fo:table-cell>
            </fo:table-row>
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
                  <fo:block font-size="8pt"><xsl:value-of select="$prefix"/></fo:block>
               </fo:table-cell>
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
                  <fo:block font-size="8pt"><xsl:value-of select="$first"/></fo:block>
               </fo:table-cell>
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
                  <fo:block font-size="8pt"><xsl:value-of select="$middle"/></fo:block>
               </fo:table-cell>
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
                  <fo:block font-size="8pt"><xsl:value-of select="$last"/></fo:block>
               </fo:table-cell>
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
                  <fo:block font-size="8pt"><xsl:value-of select="$suffix"/></fo:block>
               </fo:table-cell>
            </fo:table-row>
         </fo:table-body>
      </fo:table>
   </xsl:template>

   <!-- ============================================= -->
   <!-- PRINT CONTACT INFO                            -->
   <!-- Prints phone, fax, and email information.     -->
   <!-- ============================================= -->
   <xsl:template name="printContactInfo">
      <xsl:param name="phone"/>
      <xsl:param name="fax"/>
      <xsl:param name="email"/>
      <fo:table width="100%"
       space-before.optimum="0pt"
       space-after.optimum="0pt"
       table-layout="fixed">
         <fo:table-column column-width="proportional-column-width(1)"/>
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
                  <fo:block font-size="8pt">* Phone Number: <xsl:value-of select="$phone"/></fo:block>
               </fo:table-cell>
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
                  <fo:block font-size="8pt"> Fax Number: <xsl:value-of select="$fax"/></fo:block>
               </fo:table-cell>
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
                  <fo:block font-size="8pt">* Email: <xsl:value-of select="$email"/></fo:block>
               </fo:table-cell>
            </fo:table-row>
         </fo:table-body>
      </fo:table>
   </xsl:template>
 <!-- ============================================= -->
   <!-- PRINT CONTACT INFO               WITHOUT *             -->
   <!-- Prints phone, fax, and email information.     -->
   <!-- ============================================= -->
   <xsl:template name="printContactInfo2">
      <xsl:param name="phone"/>
      <xsl:param name="fax"/>
      <xsl:param name="email"/>
      <fo:table width="100%"
       space-before.optimum="0pt"
       space-after.optimum="0pt"
       table-layout="fixed">
         <fo:table-column column-width="proportional-column-width(1)"/>
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
                  <fo:block font-size="8pt">* Phone Number: <xsl:value-of select="$phone"/></fo:block>
               </fo:table-cell>
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
                  <fo:block font-size="8pt"> Fax Number: <xsl:value-of select="$fax"/></fo:block>
               </fo:table-cell>
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
                  <fo:block font-size="8pt"> Email: <xsl:value-of select="$email"/></fo:block>
               </fo:table-cell>
            </fo:table-row>
         </fo:table-body>
      </fo:table>
   </xsl:template>


   <!-- ============================================= -->
   <!-- ADD BLANK LINES                               -->
   <!-- Adds blank lines to fill up page space.       -->
   <!-- ============================================= -->
   <xsl:template name="addBlankLines">
      <xsl:param name="numLines"/>
      <xsl:if test="$numLines &gt; 0">
         <fo:block>
            <fo:leader leader-pattern="space"/>
         </fo:block>
         <xsl:call-template name="addBlankLines">
            <xsl:with-param name="numLines" select="$numLines - 1"/>
         </xsl:call-template>
      </xsl:if>
   </xsl:template>

   <!-- ============================================= -->
   <!-- CHECKBOX                                      -->
   <!-- Print out a checkbox according to value.      -->
   <!-- ============================================= -->
   <xsl:template name="checkbox">
      <xsl:param name="value"/>
      <xsl:param name="check">Y: Yes</xsl:param>
      <xsl:choose>
         <xsl:when test="$value = $check">
            <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
         </xsl:when>
         <xsl:otherwise>
            <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
         </xsl:otherwise>
      </xsl:choose>
   </xsl:template>

   <!-- ============================================= -->
   <!-- YES NO RADIO                                  -->
   <!-- Print out a radio button according to value.  -->
   <!-- ============================================= -->
   <xsl:template name="yes_no_radio">
      <xsl:param name="value"/>
      <xsl:choose>
         <xsl:when test="$value = 'N: No'">
            <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
            <fo:inline font-size="8pt">&#160;Yes&#160;&#160;</fo:inline>
            <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
            <fo:inline font-size="8pt">&#160;No&#160;&#160;</fo:inline>
         </xsl:when>
         <xsl:when test="$value = 'Y: Yes'">
            <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x25cf;</fo:inline>
            <fo:inline font-size="8pt">&#160;Yes&#160;&#160;</fo:inline>
            <fo:inline font-family="ZapfDingbats" font-size="10pt">&#x274d;</fo:inline>
            <fo:inline font-size="8pt">&#160;No&#160;&#160;</fo:inline>
         </xsl:when>
<!-- Use this if production box doesn't have ZapfDingbats font.
         <xsl:when test="$value = 'No'">
            <fo:inline font-family="Courier" font-size="10pt">&#x2022;</fo:inline>
            <fo:inline font-size="8pt">&#160;No&#160;&#160;</fo:inline>
            <fo:inline font-family="Courier" font-size="10pt">&#160;</fo:inline>
            <fo:inline font-size="8pt">&#160;Yes</fo:inline>
         </xsl:when>
         <xsl:when test="$value = 'Yes'">
            <fo:inline font-family="Courier" font-size="10pt">&#160;</fo:inline>
            <fo:inline font-size="8pt">&#160;No&#160;&#160;</fo:inline>
            <fo:inline font-family="Courier" font-size="10pt">&#x2022;</fo:inline>
            <fo:inline font-size="8pt">&#160;Yes</fo:inline>
          </xsl:when>
-->
      </xsl:choose>
   </xsl:template>

</xsl:stylesheet> 
