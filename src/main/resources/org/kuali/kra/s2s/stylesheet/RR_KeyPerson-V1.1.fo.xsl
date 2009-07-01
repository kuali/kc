<?xml version="1.0" encoding="UTF-8"?>
<!-- $Revision:   1.7  $ -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:RR_KeyPerson="http://apply.grants.gov/forms/RR_KeyPerson-V1.1" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:header="http://devapply.row.com/system/Header-V1.0">
   <xsl:variable name="fo:layout-master-set">
      <fo:layout-master-set>
         <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.5in" margin-right="0.5in">
            <fo:region-body margin-top="0.5in" margin-bottom="0.5in" font-family="Helvetica,Times,Courier" font-size="14pt"/>
            <fo:region-after extent=".5in"/>
         </fo:simple-page-master>
      </fo:layout-master-set>
   </xsl:variable>
   <xsl:template match="RR_KeyPerson:RR_KeyPerson">
      <fo:root>
         <xsl:copy-of select="$fo:layout-master-set"/>
         <fo:page-sequence master-reference="default-page" initial-page-number="1" format="1">
            <fo:static-content flow-name="xsl-region-after">
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
                              <fo:inline font-size="6px" font-weight="bold">Tracking Number: <xsl:value-of select="/*/*/footer:Grants_govTrackingNumber"/>
                              </fo:inline>
                           </fo:block>
                        </fo:table-cell>
                        <fo:table-cell hyphenate="true" language="en" line-height="9pt"
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
            <fo:flow flow-name="xsl-region-body" font-family="Helvetica,Times,Courier">
               <!--title-->
               <fo:block text-align="center" font-size="15pt" font-weight="200">RESEARCH &amp; RELATED Senior/Key Person Profile
               </fo:block>
               <fo:block>&#160;</fo:block>
               <!--==========PDPI============================================================================-->
             
          
  
               <fo:block font-size="8pt">
               
                  <!--table for PDPI information-->
                  <fo:table width="100%" border-style="solid" border-width="1pt" border-top-width="1.5pt" border-color="black" border-bottom-width="1.5pt">
                     <fo:table-column/>
                     <fo:table-body>
                     
                        <!--row1, header-->
                        <fo:table-row>
                           <fo:table-cell padding-before="3pt" padding-after="3pt">
                              <fo:block text-align="center">
                                 <fo:inline font-weight="bold">PROFILE - Project Director/Principal Investigator</fo:inline>
                              </fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                        <!--row2, name titles-->
                        <fo:table-row>
                           <fo:table-cell line-height="10pt" border-top-style="solid" border-width="1.5pt" border-top-color="black">
                              <fo:block text-align="center">
                                 <fo:leader leader-pattern="space"/>
                                 <fo:table width="100%">
                                    <fo:table-column column-width="proportional-column-width(10)"/>
                                    <fo:table-column column-width="proportional-column-width(25)"/>
                                    <fo:table-column column-width="proportional-column-width(20)"/>
                                    <fo:table-column column-width="proportional-column-width(35)"/>
                                    <fo:table-column column-width="proportional-column-width(10)"/>
                                    <fo:table-body>
                                       <fo:table-row>
                                          <fo:table-cell>
                                             <fo:block>Prefix</fo:block>
                                          </fo:table-cell>
                                          <fo:table-cell>
                                             <fo:block>* First Name</fo:block>
                                          </fo:table-cell>
                                          <fo:table-cell>
                                             <fo:block>Middle Name</fo:block>
                                          </fo:table-cell>
                                          <fo:table-cell>
                                             <fo:block>* Last Name</fo:block>
                                          </fo:table-cell>
                                          <fo:table-cell>
                                             <fo:block>Suffix</fo:block>
                                          </fo:table-cell>
                                       </fo:table-row>
                                    </fo:table-body>
                                 </fo:table>
                              </fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                        <!--for-each loops to PDPI-->
                        <!--row3, name values-->
                        <xsl:for-each select="RR_KeyPerson:PDPI/RR_KeyPerson:Profile">
                           <xsl:for-each select="RR_KeyPerson:Name">
                              <fo:table-row>
                                 <fo:table-cell line-height="10pt" padding-before="3pt">
                                    <fo:block text-align="center">
                                       <fo:table width="100%">
                                          <fo:table-column column-width="proportional-column-width(10)"/>
                                          <fo:table-column column-width="proportional-column-width(25)"/>
                                          <fo:table-column column-width="proportional-column-width(20)"/>
                                          <fo:table-column column-width="proportional-column-width(35)"/>
                                          <fo:table-column column-width="proportional-column-width(10)"/>
                                          <fo:table-body>
                                             <fo:table-row>
                                                <fo:table-cell hyphenate="true">
                                                   <fo:block>
                                                      <xsl:value-of select="./globLib:PrefixName"/>
                                                   </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell hyphenate="true">
                                                   <fo:block>
                                                      <xsl:value-of select="./globLib:FirstName"/>
                                                   </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell hyphenate="true">
                                                   <fo:block>
                                                      <xsl:value-of select="./globLib:MiddleName"/>
                                                   </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell hyphenate="true">
                                                   <fo:block>
                                                      <xsl:value-of select="./globLib:LastName"/>
                                                   </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell hyphenate="true">
                                                   <fo:block>
                                                      <xsl:value-of select="./globLib:SuffixName"/>
                                                   </fo:block>
                                                </fo:table-cell>
                                             </fo:table-row>
                                          </fo:table-body>
                                       </fo:table>
                                    </fo:block>
                                 </fo:table-cell>
                              </fo:table-row>
                           </xsl:for-each>
                           <!--row4-->
                           <fo:table-row>
                              <fo:table-cell line-height="10pt">
                                 <fo:block>
                                    <fo:leader leader-pattern="space"/>
                                    <fo:table width="100%">
                                       <fo:table-column column-width="proportional-column-width(50)"/>
                                       <fo:table-column column-width="proportional-column-width(50)"/>
                                       <fo:table-body>
                                          <fo:table-row>
                                             <fo:table-cell hyphenate="true" language="en" padding-start="4pt">
                                                <fo:block>Position/Title:&#160;<xsl:value-of select="RR_KeyPerson:Title"/>
                                                </fo:block>
                                             </fo:table-cell>
                                             <fo:table-cell hyphenate="true">
                                                <fo:block>Department:&#160;<xsl:value-of select="RR_KeyPerson:DepartmentName"/>
                                                </fo:block>
                                             </fo:table-cell>
                                          </fo:table-row>
                                       </fo:table-body>
                                    </fo:table>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <!--row5-->
                           <fo:table-row>
                              <fo:table-cell line-height="10pt">
                                 <fo:block>
                                    <fo:leader leader-pattern="space"/>
                                    <fo:table width="100%">
                                       <fo:table-column column-width="proportional-column-width(50)"/>
                                       <fo:table-column column-width="proportional-column-width(50)"/>
                                       <fo:table-body>
                                          <fo:table-row>
                                             <fo:table-cell hyphenate="true" padding-start="4pt">
                                                <fo:block>Organization Name:&#160;<xsl:value-of select="RR_KeyPerson:OrganizationName"/>
                                                </fo:block>
                                             </fo:table-cell>
                                             <fo:table-cell hyphenate="true">
                                                <fo:block>Division:&#160;<xsl:value-of select="RR_KeyPerson:DivisionName"/>
                                                </fo:block>
                                             </fo:table-cell>
                                          </fo:table-row>
                                       </fo:table-body>
                                    </fo:table>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <!--address for-each loop-->
                           <xsl:for-each select="RR_KeyPerson:Address">
                              <!--row6-->
                              <fo:table-row>
                                 <fo:table-cell line-height="10pt">
                                    <fo:block>
                                       <fo:leader leader-pattern="space"/>
                                       <fo:table width="100%">
                                          <fo:table-column column-width="proportional-column-width(50)"/>
                                          <fo:table-column column-width="proportional-column-width(50)"/>
                                          <fo:table-body>
                                             <fo:table-row>
                                                <fo:table-cell hyphenate="true" padding-start="4pt">
                                                   <fo:block>* Street1:&#160;<xsl:value-of select="globLib:Street1"/>
                                                   </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell hyphenate="true">
                                                   <fo:block>Street2:&#160;<xsl:value-of select="globLib:Street2"/>
                                                   </fo:block>
                                                </fo:table-cell>
                                             </fo:table-row>
                                          </fo:table-body>
                                       </fo:table>
                                    </fo:block>
                                 </fo:table-cell>
                              </fo:table-row>
                              <!--row7 (5 columns)-->
                              <fo:table-row>
                                 <fo:table-cell line-height="10pt" padding-after="3pt">
                                    <fo:block>
                                       <fo:leader leader-pattern="space"/>
                                       <fo:table width="100%">
                                          <fo:table-column column-width="proportional-column-width(28)"/>
                                          <fo:table-column column-width="proportional-column-width(27)"/>
                                          <fo:table-column column-width="proportional-column-width(15)"/>
                                          <fo:table-column column-width="proportional-column-width(15)"/>
                                          <fo:table-column column-width="proportional-column-width(15)"/>
                                          <fo:table-body>
                                             <fo:table-row>
                                                <fo:table-cell hyphenate="true" padding-start="4pt">
                                                   <fo:block>* City:&#160;<xsl:value-of select="globLib:City"/>
                                                   </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell hyphenate="true">
                                                   <fo:block>County:&#160;<xsl:value-of select="globLib:County"/>
                                                   </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell hyphenate="true">
                                                   <fo:block>* State:&#160;<xsl:value-of select="globLib:State"/>
                                                   </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell hyphenate="true">
                                                   <fo:block>Province:&#160;<xsl:value-of select="globLib:Province"/>
                                                   </fo:block>
                                                </fo:table-cell>
                                             </fo:table-row>
                                          </fo:table-body>
                                       </fo:table>
                                    </fo:block>
                                 </fo:table-cell>
                              </fo:table-row>
                              <fo:table-row>
                                 <fo:table-cell line-height="10pt" padding-after="3pt">
                                    <fo:block>
                                       <fo:leader leader-pattern="space"/>
                                       <fo:table width="100%">
                                          <fo:table-column column-width="proportional-column-width(28)"/>
                                          <fo:table-column column-width="proportional-column-width(27)"/>
                                          <fo:table-column column-width="proportional-column-width(15)"/>
                                          <fo:table-column column-width="proportional-column-width(15)"/>
                                          <fo:table-column column-width="proportional-column-width(15)"/>
                                          <fo:table-body>
                                             <fo:table-row>
                                                <fo:table-cell hyphenate="true" padding-start="4pt">
                                                   <fo:block>* Country:&#160;<xsl:value-of select="globLib:Country"/>
                                                   </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell hyphenate="true">
                                                   <fo:block>* Zip / Postal Code:&#160;<xsl:value-of select="globLib:ZipPostalCode"/>
                                                   </fo:block>
                                                </fo:table-cell>
                                             </fo:table-row>
                                          </fo:table-body>
                                       </fo:table>
                                    </fo:block>
                                 </fo:table-cell>
                              </fo:table-row>                              
                              <!--end Address loop-->
                           </xsl:for-each>
                           <!--row phone, etc. names-->
                           <fo:table-row>
                              <fo:table-cell line-height="10pt" border-top-style="solid" border-top-color="black">
                                 <fo:block text-align="center">
                                    <fo:leader leader-pattern="space"/>
                                    <fo:table width="100%">
                                       <fo:table-column column-width="proportional-column-width(34)"/>
                                       <fo:table-column column-width="proportional-column-width(32)"/>
                                       <fo:table-column column-width="proportional-column-width(32)"/>
                                       <fo:table-body>
                                          <fo:table-row>
                                             <fo:table-cell>
                                                <fo:block>*Phone Number</fo:block>
                                             </fo:table-cell>
                                             <fo:table-cell>
                                                <fo:block>Fax Number</fo:block>
                                             </fo:table-cell>
                                             <fo:table-cell>
                                                <fo:block>* E-Mail</fo:block>
                                             </fo:table-cell>
                                          </fo:table-row>
                                       </fo:table-body>
                                    </fo:table>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <!--row phone, etc. values-->
                           <fo:table-row>
                              <fo:table-cell line-height="10pt" padding-before="3pt" padding-after="5pt">
                                 <fo:block text-align="center">
                                    <fo:table width="100%">
                                       <fo:table-column column-width="proportional-column-width(34)"/>
                                       <fo:table-column column-width="proportional-column-width(32)"/>
                                       <fo:table-column column-width="proportional-column-width(32)"/>
                                       <fo:table-body>
                                          <fo:table-row>
                                             <fo:table-cell hyphenate="true">
                                                <fo:block>
                                                   <xsl:value-of select="RR_KeyPerson:Phone"/>
                                                </fo:block>
                                             </fo:table-cell>
                                             <fo:table-cell hyphenate="true">
                                                <fo:block>
                                                   <xsl:value-of select="RR_KeyPerson:Fax"/>
                                                </fo:block>
                                             </fo:table-cell>
                                             <fo:table-cell hyphenate="true">
                                                <fo:block>
                                                   <xsl:value-of select="RR_KeyPerson:Email"/>
                                                </fo:block>
                                             </fo:table-cell>
                                          </fo:table-row>
                                       </fo:table-body>
                                    </fo:table>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <!--row, credentials-->
                           <fo:table-row>
                              <fo:table-cell padding-start="4pt" padding-before="3pt" padding-after="3pt" line-height="8pt" border-top-style="solid" border-top-color="black" border-top-width="1.5pt">
                                 <fo:block>Credential, e.g., agency login:&#160;<xsl:value-of select="RR_KeyPerson:Credential"/>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <!--row, project role, project category-->
                           <fo:table-row>
                              <fo:table-cell padding-start="4pt" padding-before="3pt" padding-after="3pt" line-height="8pt" border-top-style="solid" border-top-color="black" border-top-width="1.5pt">
                                 <fo:block font-weight="bold">
                                    <fo:table width="100%">
                                       <fo:table-column column-width="proportional-column-width(45)"/>
                                       <fo:table-column column-width="proportional-column-width(55)"/>
                                       <fo:table-body>
                                          <fo:table-row>
                                             <fo:table-cell hyphenate="true" padding-start="4pt">
                                                <fo:block>* Project Role:&#160;<fo:inline font-weight="100">
                                                      <xsl:value-of select="RR_KeyPerson:ProjectRole"/>
                                                   </fo:inline>
                                                </fo:block>
                                             </fo:table-cell>
                                             <fo:table-cell hyphenate="true">
                                                <fo:block>Other Project Role Category:&#160;<fo:inline font-weight="100">
                                                      <xsl:value-of select="RR_KeyPerson:OtherProjectRoleCategory"/>
                                                   </fo:inline>
                                                </fo:block>
                                             </fo:table-cell>
                                          </fo:table-row>
                                       </fo:table-body>
                                    </fo:table>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           
                           
                           
                           <fo:table-row>
                             <fo:table-cell padding-start="4pt" padding-before="3pt" padding-after="3pt" line-height="8pt" border-top-style="solid" border-top-color="black" border-top-width="1.5pt">
                                 <fo:block>
                                    <fo:table width="100%">
                     <fo:table-column column-width="proportional-column-width(45)"/>
                     <fo:table-column column-width="proportional-column-width(28)"/>
                     <fo:table-column column-width="proportional-column-width(27)"/>
                     <fo:table-body>
                        <fo:table-row>
                           <fo:table-cell padding-after="4pt">
                              <fo:block>&#160;</fo:block>
                           </fo:table-cell>
                           <fo:table-cell text-align="center">
                              <fo:block>File Name</fo:block>
                           </fo:table-cell>
                           <fo:table-cell text-align="center">
                              <fo:block>Mime Type</fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                           <fo:table-cell font-weight="bold" padding-after="6pt">
                              <fo:block>*Attach Biographical Sketch</fo:block>
                           </fo:table-cell>
                           <fo:table-cell text-align="center" hyphenate="true">
                              <fo:block>          
                                 <xsl:value-of select="RR_KeyPerson:BioSketchsAttached/RR_KeyPerson:BioSketchAttached/att:FileName"/>
                              </fo:block>
                           </fo:table-cell>
                           <fo:table-cell text-align="center">
                              <fo:block>
                                 <xsl:value-of select="RR_KeyPerson:BioSketchsAttached/RR_KeyPerson:BioSketchAttached/att:MimeType"/>
                              </fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                           <fo:table-cell font-weight="bold" padding-after="6pt">
                              <fo:block>Attach Current &#38; Pending Support</fo:block>
                           </fo:table-cell>
                           <fo:table-cell text-align="center" hyphenate="true">
                              <fo:block>
                                 <xsl:value-of select="RR_KeyPerson:SupportsAttached/RR_KeyPerson:SupportAttached/att:FileName"/>
                              </fo:block>
                           </fo:table-cell>
                           <fo:table-cell text-align="center">
                              <fo:block>
                                 <xsl:value-of select="RR_KeyPerson:SupportsAttached/RR_KeyPerson:SupportAttached/att:MimeType"/>
                              </fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                         </fo:table-body>
                                    </fo:table>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>


                           
                           
                           
                           <!-- end PDPI loop-->
                        </xsl:for-each>
                     </fo:table-body>
                  </fo:table>
                  <!--space after tables-->
                  <fo:block>&#160;</fo:block>
                  <fo:block>&#160;</fo:block>
               </fo:block>
               <!--========== End of PDPI========================================================================-->
               <xsl:for-each select="RR_KeyPerson:KeyPerson[1]/RR_KeyPerson:Profile">
                  <xsl:call-template name="KPTemplate">
                  <xsl:with-param name="number">1</xsl:with-param>
                  </xsl:call-template>                  
               </xsl:for-each>
               <xsl:for-each select="RR_KeyPerson:KeyPerson[2]/RR_KeyPerson:Profile">
                 <fo:block break-after="page">
                     <xsl:text>&#xA;</xsl:text>
                  </fo:block>
                  <xsl:call-template name="KPTemplate">
                  <xsl:with-param name="number">2</xsl:with-param>
                  </xsl:call-template>               </xsl:for-each>
               <xsl:for-each select="RR_KeyPerson:KeyPerson[3]/RR_KeyPerson:Profile">
                
                  <xsl:call-template name="KPTemplate">
                  <xsl:with-param name="number">3</xsl:with-param>
                  </xsl:call-template>               </xsl:for-each>
               <xsl:for-each select="RR_KeyPerson:KeyPerson[4]/RR_KeyPerson:Profile">
                 <fo:block break-after="page">
                     <xsl:text>&#xA;</xsl:text>
                  </fo:block>
                  <xsl:call-template name="KPTemplate">
                  <xsl:with-param name="number">4</xsl:with-param>
                  </xsl:call-template>               </xsl:for-each>
               <xsl:for-each select="RR_KeyPerson:KeyPerson[5]/RR_KeyPerson:Profile">
                  <xsl:call-template name="KPTemplate">
                  <xsl:with-param name="number">5</xsl:with-param>
                  </xsl:call-template>               </xsl:for-each>
               <xsl:for-each select="RR_KeyPerson:KeyPerson[6]/RR_KeyPerson:Profile">
                  <fo:block break-after="page">
                     <xsl:text>&#xA;</xsl:text>
                  </fo:block>
                  <xsl:call-template name="KPTemplate">
                  <xsl:with-param name="number">6</xsl:with-param>
                  </xsl:call-template>               </xsl:for-each>
               <xsl:for-each select="RR_KeyPerson:KeyPerson[7]/RR_KeyPerson:Profile">
                  <xsl:call-template name="KPTemplate">
                  <xsl:with-param name="number">7</xsl:with-param>
                  </xsl:call-template>               </xsl:for-each>
               <!--attachments at the end-->
               <fo:block font-size="8pt">
                  <fo:table width="6in">
                     <fo:table-column column-width="proportional-column-width(55)"/>
                     <fo:table-column column-width="proportional-column-width(28)"/>
                     <fo:table-column column-width="proportional-column-width(27)"/>
                     <fo:table-body>
                        <fo:table-row>
                           <fo:table-cell padding-after="4pt">
                              <fo:block>&#160;</fo:block>
                           </fo:table-cell>
                           <fo:table-cell text-align="center">
                              <fo:block>File Name</fo:block>
                           </fo:table-cell>
                           <fo:table-cell text-align="center">
                              <fo:block>Mime Type</fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                           <fo:table-cell font-weight="bold" padding-after="6pt">
                              <fo:block>ADDITIONAL SENIOR/KEY PERSON PROFILE(S)</fo:block>
                           </fo:table-cell>
                           <fo:table-cell text-align="center" hyphenate="true">
                              <fo:block>
                                 <xsl:value-of select="RR_KeyPerson:AdditionalProfilesAttached/RR_KeyPerson:AdditionalProfileAttached/att:FileName"/>
                              </fo:block>
                           </fo:table-cell>
                           <fo:table-cell text-align="center">
                              <fo:block>
                                 <xsl:value-of select="RR_KeyPerson:AdditionalProfilesAttached/RR_KeyPerson:AdditionalProfileAttached/att:MimeType"/>
                              </fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                           <fo:table-cell font-weight="bold" padding-after="6pt">
                              <fo:block>Additional Biographical Sketch(es) (Senior/Key Person)</fo:block>
                           </fo:table-cell>
                           <fo:table-cell text-align="center" hyphenate="true">
                              <fo:block>
                                 <xsl:value-of select="RR_KeyPerson:BioSketchsAttached/RR_KeyPerson:BioSketchAttached/att:FileName"/>
                              </fo:block>
                           </fo:table-cell>
                           <fo:table-cell text-align="center">
                              <fo:block>
                                 <xsl:value-of select="RR_KeyPerson:BioSketchsAttached/RR_KeyPerson:BioSketchAttached/att:MimeType"/>
                              </fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                           <fo:table-cell font-weight="bold" padding-after="6pt">
                              <fo:block>Additional Current and Pending Support(s)</fo:block>
                           </fo:table-cell>
                           <fo:table-cell text-align="center" hyphenate="true">
                              <fo:block>
                                 <xsl:value-of select="RR_KeyPerson:SupportsAttached/RR_KeyPerson:SupportAttached/att:FileName"/>
                              </fo:block>
                           </fo:table-cell>
                           <fo:table-cell text-align="center">
                              <fo:block>
                                 <xsl:value-of select="RR_KeyPerson:SupportsAttached/RR_KeyPerson:SupportAttached/att:MimeType"/>
                              </fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                     </fo:table-body>
                  </fo:table>
               </fo:block>
               <!-- end attachments-->
            </fo:flow>
         </fo:page-sequence>
      </fo:root>
   </xsl:template>
   <!--========================================= KP Template ====================================-->
   <xsl:template name="KPTemplate">
   <xsl:param name="number"/>
     
  

      <fo:block font-size="8pt">
         <!--table for Senior/Key person  information-->
         <fo:table width="100%" border-style="solid" border-width="1pt" border-top-width="1.5pt" border-color="black" border-bottom-width="1.5pt">
            <fo:table-column/>
            <fo:table-body>
               <!--row1, header-->
               <fo:table-row>
                  <fo:table-cell padding-before="2pt" padding-after="2pt">
                     <fo:block text-align="center">
                        <fo:inline font-weight="bold">PROFILE - Senior/Key Person</fo:inline><fo:inline text-decoration="underline" font-weight="bold" color="black">&#160;<xsl:value-of select="$number"/></fo:inline>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <!--row2, name titles-->
               <fo:table-row>
                  <fo:table-cell line-height="10pt" border-top-style="solid" border-width="1.5pt" border-top-color="black">
                     <fo:block text-align="center">
                        <fo:leader leader-pattern="space"/>
                        <fo:table width="100%">
                           <fo:table-column column-width="proportional-column-width(10)"/>
                           <fo:table-column column-width="proportional-column-width(25)"/>
                           <fo:table-column column-width="proportional-column-width(20)"/>
                           <fo:table-column column-width="proportional-column-width(35)"/>
                           <fo:table-column column-width="proportional-column-width(10)"/>
                           <fo:table-body>
                              <fo:table-row>
                                 <fo:table-cell>
                                    <fo:block>Prefix</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block>* First Name</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block>Middle Name</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block>* Last Name</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block>Suffix</fo:block>
                                 </fo:table-cell>
                              </fo:table-row>
                           </fo:table-body>
                        </fo:table>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <!--row3, name values-->
               <xsl:for-each select="RR_KeyPerson:Name">
                  <fo:table-row>
                     <fo:table-cell line-height="10pt" padding-before="3pt">
                        <fo:block text-align="center">
                           <fo:table width="100%">
                              <fo:table-column column-width="proportional-column-width(10)"/>
                              <fo:table-column column-width="proportional-column-width(25)"/>
                              <fo:table-column column-width="proportional-column-width(20)"/>
                              <fo:table-column column-width="proportional-column-width(35)"/>
                              <fo:table-column column-width="proportional-column-width(10)"/>
                              <fo:table-body>
                                 <fo:table-row>
                                    <fo:table-cell hyphenate="true">
                                       <fo:block>
                                          <xsl:value-of select="./globLib:PrefixName"/>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true">
                                       <fo:block>
                                          <xsl:value-of select="./globLib:FirstName"/>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true">
                                       <fo:block>
                                          <xsl:value-of select="./globLib:MiddleName"/>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true">
                                       <fo:block>
                                          <xsl:value-of select="./globLib:LastName"/>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true">
                                       <fo:block>
                                          <xsl:value-of select="./globLib:SuffixName"/>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                              </fo:table-body>
                           </fo:table>
                        </fo:block>
                     </fo:table-cell>
                  </fo:table-row>
               </xsl:for-each>
               <!--row4-->
               <fo:table-row>
                  <fo:table-cell line-height="10pt">
                     <fo:block>
                        <fo:leader leader-pattern="space"/>
                        <fo:table width="100%">
                           <fo:table-column column-width="proportional-column-width(50)"/>
                           <fo:table-column column-width="proportional-column-width(50)"/>
                           <fo:table-body>
                              <fo:table-row>
                                             <fo:table-cell hyphenate="true" language="en" padding-start="4pt">
                                    <fo:block>Position/Title:&#160;<xsl:value-of select="RR_KeyPerson:Title"/>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell hyphenate="true">
                                    <fo:block>Department:&#160;<xsl:value-of select="RR_KeyPerson:DepartmentName"/>
                                    </fo:block>
                                 </fo:table-cell>
                              </fo:table-row>
                           </fo:table-body>
                        </fo:table>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <!--row5-->
               <fo:table-row>
                  <fo:table-cell line-height="10pt">
                     <fo:block>
                        <fo:leader leader-pattern="space"/>
                        <fo:table width="100%">
                           <fo:table-column column-width="proportional-column-width(50)"/>
                           <fo:table-column column-width="proportional-column-width(50)"/>
                           <fo:table-body>
                              <fo:table-row>
                                 <fo:table-cell hyphenate="true" padding-start="4pt">
                                    <fo:block>Organization Name:&#160;<xsl:value-of select="RR_KeyPerson:OrganizationName"/>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell hyphenate="true">
                                    <fo:block>Division:&#160;<xsl:value-of select="RR_KeyPerson:DivisionName"/>
                                    </fo:block>
                                 </fo:table-cell>
                              </fo:table-row>
                           </fo:table-body>
                        </fo:table>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <!--address for-each loop-->
               <xsl:for-each select="RR_KeyPerson:Address">
                  <!--row6-->
                  <fo:table-row>
                     <fo:table-cell line-height="10pt">
                        <fo:block>
                           <fo:leader leader-pattern="space"/>
                           <fo:table width="100%">
                              <fo:table-column column-width="proportional-column-width(50)"/>
                              <fo:table-column column-width="proportional-column-width(50)"/>
                              <fo:table-body>
                                 <fo:table-row>
                                    <fo:table-cell hyphenate="true" padding-start="4pt">
                                       <fo:block>* Street1:&#160;<xsl:value-of select="globLib:Street1"/>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true">
                                       <fo:block>Street2:&#160;<xsl:value-of select="globLib:Street2"/>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                              </fo:table-body>
                           </fo:table>
                        </fo:block>
                     </fo:table-cell>
                  </fo:table-row>
                  <!--row7 (5 columns)-->
                  <fo:table-row>
                     <fo:table-cell line-height="10pt" padding-after="3pt">
                        <fo:block>
                           <fo:leader leader-pattern="space"/>
                           <fo:table width="100%">
                              <fo:table-column column-width="proportional-column-width(28)"/>
                              <fo:table-column column-width="proportional-column-width(27)"/>
                              <fo:table-column column-width="proportional-column-width(15)"/>
                              <fo:table-column column-width="proportional-column-width(15)"/>
                              <fo:table-column column-width="proportional-column-width(15)"/>
                              <fo:table-body>
                                 <fo:table-row>
                                    <fo:table-cell hyphenate="true" padding-start="4pt">
                                       <fo:block>* City:&#160;<xsl:value-of select="globLib:City"/>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true">
                                       <fo:block>County:&#160;<xsl:value-of select="globLib:County"/>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true">
                                       <fo:block>* State:&#160;<xsl:value-of select="globLib:State"/>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true">
                                       <fo:block>Province:&#160;<xsl:value-of select="globLib:Province"/>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                              </fo:table-body>
                           </fo:table>
                        </fo:block>
                     </fo:table-cell>
                  </fo:table-row>
                  <fo:table-row>
                     <fo:table-cell line-height="10pt" padding-after="3pt">
                        <fo:block>
                           <fo:leader leader-pattern="space"/>
                           <fo:table width="100%">
                              <fo:table-column column-width="proportional-column-width(28)"/>
                              <fo:table-column column-width="proportional-column-width(27)"/>
                              <fo:table-column column-width="proportional-column-width(15)"/>
                              <fo:table-column column-width="proportional-column-width(15)"/>
                              <fo:table-column column-width="proportional-column-width(15)"/>
                              <fo:table-body>
                                 <fo:table-row>
                                    <fo:table-cell hyphenate="true" padding-start="4pt">
                                       <fo:block>* Country:&#160;<xsl:value-of select="globLib:Country"/>
                                       </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true">
                                       <fo:block>* Zip / Postal Code:&#160;<xsl:value-of select="globLib:ZipPostalCode"/>
                                       </fo:block>
                                    </fo:table-cell>
                                 </fo:table-row>
                              </fo:table-body>
                           </fo:table>
                        </fo:block>
                     </fo:table-cell>
                  </fo:table-row>                  
                  <!--end Address loop-->
               </xsl:for-each>
               <!--row phone, etc. names-->
               <fo:table-row>
                  <fo:table-cell line-height="10pt" border-top-style="solid" border-top-color="black">
                     <fo:block text-align="center">
                        <fo:leader leader-pattern="space"/>
                        <fo:table width="100%">
                           <fo:table-column column-width="proportional-column-width(34)"/>
                           <fo:table-column column-width="proportional-column-width(32)"/>
                           <fo:table-column column-width="proportional-column-width(32)"/>
                           <fo:table-body>
                              <fo:table-row>
                                 <fo:table-cell>
                                    <fo:block>*Phone Number</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block>Fax Number</fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell>
                                    <fo:block>* E-Mail</fo:block>
                                 </fo:table-cell>
                              </fo:table-row>
                           </fo:table-body>
                        </fo:table>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <!--row phone, etc. values-->
               <fo:table-row>
                  <fo:table-cell line-height="10pt" padding-before="3pt" padding-after="5pt">
                     <fo:block text-align="center">
                        <fo:table width="100%">
                           <fo:table-column column-width="proportional-column-width(34)"/>
                           <fo:table-column column-width="proportional-column-width(32)"/>
                           <fo:table-column column-width="proportional-column-width(32)"/>
                           <fo:table-body>
                              <fo:table-row>
                                 <fo:table-cell hyphenate="true">
                                    <fo:block>
                                       <xsl:value-of select="RR_KeyPerson:Phone"/>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell hyphenate="true">
                                    <fo:block>
                                       <xsl:value-of select="RR_KeyPerson:Fax"/>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell hyphenate="true">
                                    <fo:block>
                                       <xsl:value-of select="RR_KeyPerson:Email"/>
                                    </fo:block>
                                 </fo:table-cell>
                              </fo:table-row>
                           </fo:table-body>
                        </fo:table>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <!--row, credentials-->
               <fo:table-row>
                  <fo:table-cell padding-start="4pt" padding-before="3pt" padding-after="3pt" line-height="8pt" border-top-style="solid" border-top-color="black" border-top-width="1.5pt">
                     <fo:block>Credential, e.g., agency login:&#160;<xsl:value-of select="RR_KeyPerson:Credential"/>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               <!--row, project role, project category-->
               <fo:table-row>
                  <fo:table-cell padding-start="4pt" padding-before="3pt" padding-after="3pt" line-height="8pt" border-top-style="solid" border-top-color="black" border-top-width="1.5pt">
                     <fo:block font-weight="bold">
                        <fo:table width="100%">
                           <fo:table-column column-width="proportional-column-width(45)"/>
                           <fo:table-column column-width="proportional-column-width(55)"/>
                           <fo:table-body>
                              <fo:table-row>
                                 <fo:table-cell hyphenate="true" padding-start="4pt">
                                    <fo:block>* Project Role:&#160;<fo:inline font-weight="100">
                                          <xsl:value-of select="RR_KeyPerson:ProjectRole"/>
                                       </fo:inline>
                                    </fo:block>
                                 </fo:table-cell>
                                 <fo:table-cell hyphenate="true">
                                    <fo:block>Other Project Role Category:&#160;<fo:inline font-weight="100">
                                          <xsl:value-of select="RR_KeyPerson:OtherProjectRoleCategory"/>
                                       </fo:inline>
                                    </fo:block>
                                 </fo:table-cell>
                              </fo:table-row>
                           </fo:table-body>
                        </fo:table>
                     </fo:block>
                  </fo:table-cell>
               </fo:table-row>
               
               
                  <fo:table-row>
                             <fo:table-cell padding-start="4pt" padding-before="3pt" padding-after="3pt" line-height="8pt" border-top-style="solid" border-top-color="black" border-top-width="1.5pt">
                                 <fo:block>
                                    <fo:table width="100%">
                     <fo:table-column column-width="proportional-column-width(45)"/>
                     <fo:table-column column-width="proportional-column-width(28)"/>
                     <fo:table-column column-width="proportional-column-width(27)"/>
                     <fo:table-body>
                        <fo:table-row>
                           <fo:table-cell padding-after="4pt">
                              <fo:block>&#160;</fo:block>
                           </fo:table-cell>
                           <fo:table-cell text-align="center">
                              <fo:block>File Name</fo:block>
                           </fo:table-cell>
                           <fo:table-cell text-align="center">
                              <fo:block>Mime Type</fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                           <fo:table-cell font-weight="bold" padding-after="6pt">
                              <fo:block>*Attach Biographical Sketch</fo:block>
                           </fo:table-cell>
                           <fo:table-cell text-align="center" hyphenate="true">
                              <fo:block>          
                                 <xsl:value-of select="RR_KeyPerson:BioSketchsAttached/RR_KeyPerson:BioSketchAttached/att:FileName"/>
                              </fo:block>
                           </fo:table-cell>
                           <fo:table-cell text-align="center">
                              <fo:block>
                                 <xsl:value-of select="RR_KeyPerson:BioSketchsAttached/RR_KeyPerson:BioSketchAttached/att:MimeType"/>
                              </fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                           <fo:table-cell font-weight="bold" padding-after="6pt">
                              <fo:block>Attach Current &#38; Pending Support</fo:block>
                           </fo:table-cell>
                           <fo:table-cell text-align="center" hyphenate="true">
                              <fo:block>
                                 <xsl:value-of select="RR_KeyPerson:SupportsAttached/RR_KeyPerson:SupportAttached/att:FileName"/>
                              </fo:block>
                           </fo:table-cell>
                           <fo:table-cell text-align="center">
                              <fo:block>
                                 <xsl:value-of select="RR_KeyPerson:SupportsAttached/RR_KeyPerson:SupportAttached/att:MimeType"/>
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
         <!--space after tables-->
         <fo:block>&#160;</fo:block>
        


      
      </fo:block>
   </xsl:template>
   <!--========= End Key Person Template =============================================================-->
</xsl:stylesheet>
