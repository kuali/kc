<?xml version="1.0" encoding="UTF-8"?>
<!-- $Revision:   1.4  $ -->
<xsl:stylesheet version="1.0" 
xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:fo="http://www.w3.org/1999/XSL/Format" 
xmlns:PerformanceSite_2_0="http://apply.grants.gov/forms/PerformanceSite_2_0-V2.0" 
xmlns:codes="http://apply.grants.gov/system/UniversalCodes-V1.0" 
xmlns:glob="http://apply.grants.gov/system/Global-V1.0" 
xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" 
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" 
xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" 
xmlns:header="http://apply.grants.gov/system/Header-V1.0">
   <xsl:variable name="fo:layout-master-set">
  <fo:layout-master-set>
    <fo:simple-page-master master-name="first" page-height="11in" page-width="8.5in" margin-left="0.6in" margin-right="0.6in">
      <fo:region-body margin-top="0.5in" margin-bottom="0.5in" />
      <fo:region-before region-name="region-before-first" extent=".4in" />
      <fo:region-after region-name="region-after-all" extent=".3in" />
    </fo:simple-page-master>
    
    <fo:simple-page-master master-name="rest" page-height="11in" page-width="8.5in" margin-left="0.6in" margin-right="0.6in">
      <fo:region-body margin-top="0.4in" margin-bottom="0.4in" />
      <fo:region-after region-name="region-after-all" extent=".3in" />
    </fo:simple-page-master>

    <fo:page-sequence-master master-name="all-pages">
      <fo:repeatable-page-master-alternatives>
        <fo:conditional-page-master-reference master-reference="first" page-position="first"/>
        <fo:conditional-page-master-reference master-reference="rest" page-position="rest"/>
      </fo:repeatable-page-master-alternatives>
    </fo:page-sequence-master>
  </fo:layout-master-set>
   </xsl:variable>
   <xsl:template match="PerformanceSite_2_0:PerformanceSite_2_0">
      <fo:root>
         <xsl:copy-of select="$fo:layout-master-set"/>
         <fo:page-sequence master-reference="all-pages" initial-page-number="1" format="1">
            <fo:static-content flow-name="region-before-first">
               <fo:table width="100%"
                  space-before.optimum="0pt"
                  space-after.optimum="0pt"
                  table-layout="fixed">
                  <fo:table-column column-width="proportional-column-width(1)"/>
                  <fo:table-body>
                     <fo:table-row>

                        <fo:table-cell hyphenate="true" language="en" line-height="9pt"
                         padding-start="0pt"
                         padding-end="0pt"
                         padding-before="10pt"
                         padding-after="1pt"
                         display-align="before"
                         text-align="right"
                         border-style="solid"
                         border-width="0pt"
                         border-color="white">
                           <fo:block><fo:inline font-size="6px">OMB Number: 4040-0010</fo:inline></fo:block>
                           <fo:block><fo:inline font-size="6px">Expiration Date: 06/30/2016</fo:inline></fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                  </fo:table-body>
               </fo:table>
            </fo:static-content>
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
                           <fo:block><fo:inline font-size="8px">Funding Opportunity Number: <xsl:value-of select="/*/*/header:OpportunityID"/></fo:inline>
                                     <fo:inline font-size="8px">.       Received Date: <xsl:value-of select="/*/*/footer:ReceivedDateTime"/></fo:inline></fo:block>
                        </fo:table-cell>
                     </fo:table-row>
                  </fo:table-body>
               </fo:table>
            </fo:static-content>
            <fo:flow flow-name="xsl-region-body">
               <fo:block>
                  <fo:table font-size="12pt" font-weight="bold" width="100%" space-before.optimum="1pt" space-after.optimum="2pt" table-layout="fixed">
                     <fo:table-column column-width="proportional-column-width(1)"/>
                     <fo:table-body>
                        <fo:table-row>
                           <fo:table-cell text-align="center" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                              <fo:block font-size="12pt" font-weight="bold">
                                 Project/Performance Site Location(s)
                              </fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                     </fo:table-body>
                  </fo:table>
                  <fo:block>
                     <fo:leader leader-pattern="space"/>
                  </fo:block>
                  <fo:inline font-size="9px">&#160;</fo:inline>
                  <xsl:for-each select="PerformanceSite_2_0:PrimarySite">
                     <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt" table-layout="fixed">
                        <fo:table-column column-width="1.5in"/>
                        <fo:table-column column-width="0.75in"/>
                        <fo:table-column column-width="1.0in"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-body>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="10pt" font-weight="bold">Project/Performance Site Primary Location</fo:inline>
                                 </fo:block>
                              </fo:table-cell>
                              <fo:table-cell hyphenate="true" language="en" number-columns-spanned="3" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                 
                                 <xsl:choose>
                                             <xsl:when test="PerformanceSite_2_0:Individual = 'Y: Yes'">
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
                                          
                                    <fo:inline font-size="9px">&#160;I am submitting an application as an individual, and not on behalf of a company, state, local or tribal government, academia, or other type of organization.</fo:inline>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="1" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">Organization Name: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="3" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                    <fo:block font-family="Georgia" font-size="9px">
                                    <xsl:for-each select="PerformanceSite_2_0:OrganizationName">
                                       <fo:inline font-size="10px">
                                          <xsl:apply-templates/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="1" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">Duns Number: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="3" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                    <fo:block font-family="Georgia" font-size="10px">
                                    <xsl:for-each select="PerformanceSite_2_0:DUNSNumber">
                                       <fo:inline font-size="10px">
                                          <xsl:apply-templates/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="1" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">Street1*: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="3" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block font-family="Georgia">
                                    <xsl:for-each select="PerformanceSite_2_0:Address">
                                       <xsl:for-each select="globLib:Street1">
                                          <fo:inline font-size="10px">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                             </fo:table-row>
                              
                              <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="1" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">Street2: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="3" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block font-family="Georgia">
                                    <xsl:for-each select="PerformanceSite_2_0:Address">
                                       <xsl:for-each select="globLib:Street2">
                                          <fo:inline font-size="10px">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="1" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">City*: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                     <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="3" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block font-family="Georgia">
                                    <xsl:for-each select="PerformanceSite_2_0:Address">
                                       <xsl:for-each select="globLib:City">
                                          <fo:inline font-size="10px">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="1" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">County: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="3" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block font-family="Georgia">
                                    <xsl:for-each select="PerformanceSite_2_0:Address">
                                       <xsl:for-each select="globLib:County">
                                          <fo:inline font-size="10px">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                              </fo:table-row>
                              
                              <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="1" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">State*: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="3" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block font-family="Georgia">
                                    <xsl:for-each select="PerformanceSite_2_0:Address">
                                       <xsl:for-each select="globLib:State">
                                          <fo:inline font-size="10px">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           
                           <fo:table-row>                              
                              <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="1" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">Province: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="3" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block font-family="Georgia">
                                    <xsl:for-each select="PerformanceSite_2_0:Address">
                                       <xsl:for-each select="globLib:Province">
                                          <fo:inline font-size="10px">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                              </fo:table-row>
                              
                              <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="1" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">Country*: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="3" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block font-family="Georgia">
                                    <xsl:for-each select="PerformanceSite_2_0:Address">
                                       <xsl:for-each select="globLib:Country">
                                          <fo:inline font-size="10px">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                    <fo:inline font-size="9px">&#160;</fo:inline>
                                 </fo:block>
                              </fo:table-cell>
                              </fo:table-row>
                              
                              <fo:table-row>
                             <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="1" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">Zip / Postal Code*: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="3" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block font-family="Georgia">
                                    <xsl:for-each select="PerformanceSite_2_0:Address">
                                       <xsl:for-each select="globLib:ZipPostalCode">
                                          <fo:inline font-size="10px">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                              </fo:table-row>
                              
                              <fo:table-row>                              
                              <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="3" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">Project/Performance Site Congressional District*: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="1" padding-start="2pt" padding-end="2pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="left" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block font-family="Georgia" font-size="9px">
                                       <xsl:for-each select="PerformanceSite_2_0:CongressionalDistrictProgramProject">
                                          <fo:inline font-size="10px">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                              
                           </fo:table-row>
                        </fo:table-body>
                     </fo:table>
                     
                  </xsl:for-each>
                  <fo:block color="black" space-before.optimum="-8pt">
                     <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="1.5pt"/>
                  </fo:block>
                  <xsl:for-each select="PerformanceSite_2_0:OtherSite">
                     <fo:block>
                        <xsl:text>&#xA;</xsl:text>
                     </fo:block>
                      <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt" table-layout="fixed">
                        <fo:table-column column-width="1.5in"/>
                        <fo:table-column column-width="0.75in"/>
                        <fo:table-column column-width="1.0in"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-body>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="before" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="10pt" font-weight="bold">Project/Performance Site Location <xsl:value-of select="position()"/></fo:inline>
                                 </fo:block>
                              </fo:table-cell>
                              <fo:table-cell hyphenate="true" language="en" number-columns-spanned="3" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                 
                                 <xsl:choose>
                                             <xsl:when test="PerformanceSite_2_0:Individual = 'Y: Yes'">
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
                                          
                                    <fo:inline font-size="9px">&#160;I am submitting an application as an individual, and not on behalf of a company, state, local or tribal government, academia, or other type of organization.</fo:inline>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="1" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">Organization Name: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                    <fo:block font-family="Georgia">
                                    <xsl:for-each select="PerformanceSite_2_0:OrganizationName">
                                       <fo:inline font-size="10px">
                                          <xsl:apply-templates/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="1" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">DUNS Number: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                    <fo:block font-family="Georgia">
                                    <xsl:for-each select="PerformanceSite_2_0:DUNSNumber">
                                       <fo:inline font-size="10px">
                                          <xsl:apply-templates/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="1" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">Street1*: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block font-family="Georgia">
                                    <xsl:for-each select="PerformanceSite_2_0:Address">
                                       <xsl:for-each select="globLib:Street1">
                                          <fo:inline font-size="10px">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                             </fo:table-row>
                              
                              <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="1" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">Street2: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block font-family="Georgia">
                                    <xsl:for-each select="PerformanceSite_2_0:Address">
                                       <xsl:for-each select="globLib:Street2">
                                          <fo:inline font-size="10px">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="10pt"  number-columns-spanned="1" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">City*: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                     <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="1" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block font-family="Georgia">
                                    <xsl:for-each select="PerformanceSite_2_0:Address">
                                       <xsl:for-each select="globLib:City">
                                          <fo:inline font-size="10px">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                              </fo:table-row>
                              
                              <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="1" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">County: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block font-family="Georgia">
                                    <xsl:for-each select="PerformanceSite_2_0:Address">
                                       <xsl:for-each select="globLib:County">
                                          <fo:inline font-size="10px">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                              </fo:table-row>
                              
                              <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="10pt" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">State*: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block font-family="Georgia">
                                    <xsl:for-each select="PerformanceSite_2_0:Address">
                                       <xsl:for-each select="globLib:State">
                                          <fo:inline font-size="10px">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           
                           <fo:table-row>                              
                              <fo:table-cell hyphenate="true" language="en" line-height="10pt" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">Province: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block font-family="Georgia">
                                    <xsl:for-each select="PerformanceSite_2_0:Address">
                                       <xsl:for-each select="globLib:Province">
                                          <fo:inline font-size="10px">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                              </fo:table-row>
                              
                              <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="10pt" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">Country*: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="4" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block font-family="Georgia">
                                    <xsl:for-each select="PerformanceSite_2_0:Address">
                                       <xsl:for-each select="globLib:Country">
                                          <fo:inline font-size="10px">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                    <fo:inline font-size="9px">&#160;</fo:inline>
                                 </fo:block>
                              </fo:table-cell>
                              </fo:table-row>
                              
                              <fo:table-row>
                             <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="1" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">Zip / Postal Code*: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="1" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block font-family="Georgia">
                                    <xsl:for-each select="PerformanceSite_2_0:Address">
                                       <xsl:for-each select="globLib:ZipPostalCode">
                                          <fo:inline font-size="10px">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                              </fo:table-row>
                              
                              <fo:table-row>                              
                              <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="9px">Project/Performance Site Congressional District*: </fo:inline>
                                    </fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell hyphenate="true" language="en" line-height="10pt" number-columns-spanned="1" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block font-family="Georgia">
                                       <xsl:for-each select="PerformanceSite_2_0:CongressionalDistrictProgramProject">
                                          <fo:inline font-size="10px">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                        </fo:table-body>
                     </fo:table>
                     <fo:block color="black" space-before.optimum="-8pt">
                        <fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="1.5pt"/>
                     </fo:block>
                  </xsl:for-each>
                  <fo:block>
                     <xsl:text>&#xA;</xsl:text>
                  </fo:block>
                  
                  <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                     <fo:table-column/>
                     <fo:table-column/>
                     <fo:table-column/>
                     <fo:table-body>
                        <fo:table-row>
                           <fo:table-cell hyphenate="true" language="en" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                              <fo:block/>
                           </fo:table-cell>
                           <fo:table-cell hyphenate="true" language="en" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                              <fo:block>
                                 <fo:inline font-size="9px">File Name</fo:inline>
                              </fo:block>
                           </fo:table-cell>
                           <fo:table-cell hyphenate="true" language="en" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                              <fo:block color="white">
                                 <fo:inline font-size="9px">Mime Type</fo:inline>
                              </fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                           <fo:table-cell hyphenate="true" language="en" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                              <fo:block>
                                 <fo:inline font-size="9px" font-weight="bold">Additional Location(s)</fo:inline>
                              </fo:block>
                           </fo:table-cell>
                           <fo:table-cell hyphenate="true" language="en" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                              <fo:block>
                                 <xsl:for-each select="PerformanceSite_2_0:AttachedFile">
                                    <xsl:for-each select="att:FileName">
                                       <fo:inline font-size="9px">
                                          <xsl:apply-templates/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </xsl:for-each>
                              </fo:block>
                           </fo:table-cell>
                           <fo:table-cell hyphenate="true" language="en" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                              <fo:block color="white">
                                 <xsl:for-each select="PerformanceSite_2_0:AttachedFile">
                                    <xsl:for-each select="att:MimeType">
                                       <fo:inline font-size="9px">
                                          <xsl:apply-templates/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </xsl:for-each>
                              </fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                     </fo:table-body>
                  </fo:table>
               </fo:block>
            </fo:flow>
         </fo:page-sequence>
      </fo:root>
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
   
</xsl:stylesheet>
