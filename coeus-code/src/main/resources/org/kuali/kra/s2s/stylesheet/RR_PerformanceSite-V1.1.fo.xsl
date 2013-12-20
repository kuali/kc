<?xml version="1.0" encoding="UTF-8"?>
<!-- $Revision:   1.3  $ -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:RR_PerformanceSite="http://apply.grants.gov/forms/RR_PerformanceSite-V1.1" xmlns:codes="http://apply.grants.gov/system/UniversalCodes-V1.0" xmlns:glob="http://apply.grants.gov/system/Global-V1.0" xmlns:globLib="http://apply.grants.gov/system/GlobalLibrary-V2.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:footer="http://apply.grants.gov/system/Footer-V1.0" xmlns:att="http://apply.grants.gov/system/Attachments-V1.0" xmlns:header="http://devapply.row.com/system/Header-V1.0">
   <xsl:variable name="fo:layout-master-set">
      <fo:layout-master-set>
         <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.6in" margin-right="0.6in">
            <fo:region-body margin-top="0.5in" margin-bottom="0.5in"/>
            <fo:region-after extent=".4in"/>
         </fo:simple-page-master>
      </fo:layout-master-set>
   </xsl:variable>
   <xsl:template match="RR_PerformanceSite:RR_PerformanceSite">
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
            <fo:flow flow-name="xsl-region-body">
               <fo:block>
                  <fo:table font-size="12pt" font-weight="bold" width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                     <fo:table-column/>
                     <fo:table-body>
                        <fo:table-row>
                           <fo:table-cell text-align="center" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" border-style="solid" border-width="1pt" border-color="white">
                              <fo:block font-size="12pt" font-weight="bold">
                                 RESEARCH &amp; RELATED Project/Performance Site Location(s)
                              </fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                     </fo:table-body>
                  </fo:table>
                  <fo:block>
                     <fo:leader leader-pattern="space"/>
                  </fo:block>
                  <fo:inline font-size="8pt" font-weight="bold">&#160;</fo:inline>
                  <xsl:for-each select="RR_PerformanceSite:PrimarySite">
                     <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                        <fo:table-column/>
                        <fo:table-column/>
                        <fo:table-column/>
                        <fo:table-column/>
                        <fo:table-column/>
                        <fo:table-body>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="10pt">Project/Performance Site Primary Location</fo:inline>
                                 </fo:block>
                              </fo:table-cell>
                              <fo:table-cell hyphenate="true" language="en" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block/>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="9pt" number-columns-spanned="5" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="8pt">Organization Name: </fo:inline>
                                    <xsl:for-each select="RR_PerformanceSite:OrganizationName">
                                       <fo:inline font-size="8pt">
                                          <xsl:apply-templates/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="9pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="8pt">* Street1: </fo:inline>
                                    <xsl:for-each select="RR_PerformanceSite:Address">
                                       <xsl:for-each select="globLib:Street1">
                                          <fo:inline font-size="8pt">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                              <fo:table-cell hyphenate="true" language="en" line-height="9pt" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="8pt">Street2: </fo:inline>
                                    <xsl:for-each select="RR_PerformanceSite:Address">
                                       <xsl:for-each select="globLib:Street2">
                                          <fo:inline font-size="8pt">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="8pt">* City: </fo:inline>
                                    <xsl:for-each select="RR_PerformanceSite:Address">
                                       <xsl:for-each select="globLib:City">
                                          <fo:inline font-size="8pt">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                              <fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="8pt">County: </fo:inline>
                                    <xsl:for-each select="RR_PerformanceSite:Address">
                                       <xsl:for-each select="globLib:County">
                                          <fo:inline font-size="8pt">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                              <fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="8pt">* State: </fo:inline>
                                    <xsl:for-each select="RR_PerformanceSite:Address">
                                       <xsl:for-each select="globLib:State">
                                          <fo:inline font-size="8pt">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>                              
                              <fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="8pt">Province: </fo:inline>
                                    <xsl:for-each select="RR_PerformanceSite:Address">
                                       <xsl:for-each select="globLib:Province">
                                          <fo:inline font-size="8pt">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                              <fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="8pt">* Country: </fo:inline>
                                    <xsl:for-each select="RR_PerformanceSite:Address">
                                       <xsl:for-each select="globLib:Country">
                                          <fo:inline font-size="8pt">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                    <fo:inline font-size="8pt">&#160;</fo:inline>
                                 </fo:block>
                              </fo:table-cell>
                             <fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="8pt">* Zip / Postal Code: </fo:inline>
                                    <xsl:for-each select="RR_PerformanceSite:Address">
                                       <xsl:for-each select="globLib:ZipPostalCode">
                                          <fo:inline font-size="8pt">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
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
                  <xsl:for-each select="RR_PerformanceSite:OtherSite">
                     <fo:block>
                        <xsl:text>&#xA;</xsl:text>
                     </fo:block>
                     <fo:table width="100%" space-before.optimum="1pt" space-after.optimum="2pt">
                        <fo:table-column/>
                        <fo:table-column/>
                        <fo:table-column/>
                        <fo:table-column/>
                        <fo:table-column/>
                        <fo:table-body>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="9pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="10pt">Project/Performance Site Location <xsl:value-of select="position()"/></fo:inline>
                                 </fo:block>
                              </fo:table-cell>
                              <fo:table-cell hyphenate="true" language="en" line-height="9pt" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block/>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="9pt" number-columns-spanned="5" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="8pt">Organization Name: </fo:inline>
                                    <xsl:for-each select="RR_PerformanceSite:OrganizationName">
                                       <fo:inline font-size="8pt">
                                          <xsl:apply-templates/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="9pt" number-columns-spanned="2" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="8pt">* Street1: </fo:inline>
                                    <xsl:for-each select="RR_PerformanceSite:Address">
                                       <xsl:for-each select="globLib:Street1">
                                          <fo:inline font-size="8pt">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                              <fo:table-cell hyphenate="true" language="en" line-height="9pt" number-columns-spanned="3" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="8pt">Street2: </fo:inline>
                                    <xsl:for-each select="RR_PerformanceSite:Address">
                                       <xsl:for-each select="globLib:Street2">
                                          <fo:inline font-size="8pt">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>
                              <fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="8pt">* City: </fo:inline>
                                    <xsl:for-each select="RR_PerformanceSite:Address">
                                       <xsl:for-each select="globLib:City">
                                          <fo:inline font-size="8pt">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                              <fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="8pt">County: </fo:inline>
                                    <xsl:for-each select="RR_PerformanceSite:Address">
                                       <xsl:for-each select="globLib:County">
                                          <fo:inline font-size="8pt">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                              <fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="8pt">* State: </fo:inline>
                                    <xsl:for-each select="RR_PerformanceSite:Address">
                                       <xsl:for-each select="globLib:State">
                                          <fo:inline font-size="8pt">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                           </fo:table-row>
                           <fo:table-row>                              
                              <fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="8pt">Province: </fo:inline>
                                    <xsl:for-each select="RR_PerformanceSite:Address">
                                       <xsl:for-each select="globLib:Province">
                                          <fo:inline font-size="8pt">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                              <fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="8pt">* Country: </fo:inline>
                                    <xsl:for-each select="RR_PerformanceSite:Address">
                                       <xsl:for-each select="globLib:Country">
                                          <fo:inline font-size="8pt">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
                                    </xsl:for-each>
                                 </fo:block>
                              </fo:table-cell>
                              <fo:table-cell hyphenate="true" language="en" line-height="9pt" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                                 <fo:block>
                                    <fo:inline font-size="8pt">* Zip / Postal Code: </fo:inline>
                                    <xsl:for-each select="RR_PerformanceSite:Address">
                                       <xsl:for-each select="globLib:ZipPostalCode">
                                          <fo:inline font-size="8pt">
                                             <xsl:apply-templates/>
                                          </fo:inline>
                                       </xsl:for-each>
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
                                 <fo:inline font-size="8pt">File Name</fo:inline>
                              </fo:block>
                           </fo:table-cell>
                           <fo:table-cell hyphenate="true" language="en" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                              <fo:block>
                                 <fo:inline font-size="8pt">Mime Type</fo:inline>
                              </fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                           <fo:table-cell hyphenate="true" language="en" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                              <fo:block>
                                 <fo:inline font-size="8pt" font-weight="bold">Additional Location(s)</fo:inline>
                              </fo:block>
                           </fo:table-cell>
                           <fo:table-cell hyphenate="true" language="en" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                              <fo:block>
                                 <xsl:for-each select="RR_PerformanceSite:AttachedFile">
                                    <xsl:for-each select="att:FileName">
                                       <fo:inline font-size="8pt">
                                          <xsl:apply-templates/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </xsl:for-each>
                              </fo:block>
                           </fo:table-cell>
                           <fo:table-cell hyphenate="true" language="en" padding-start="3pt" padding-end="3pt" padding-before="2pt" padding-after="2pt" display-align="center" text-align="start" border-style="solid" border-width="1pt" border-color="white">
                              <fo:block>
                                 <xsl:for-each select="RR_PerformanceSite:AttachedFile">
                                    <xsl:for-each select="att:MimeType">
                                       <fo:inline font-size="8pt">
                                          <xsl:apply-templates/>
                                       </fo:inline>
                                    </xsl:for-each>
                                 </xsl:for-each>
                              </fo:block>
                           </fo:table-cell>
                        </fo:table-row>
                     </fo:table-body>
                  </fo:table>
                  <!--fo:block>
                     <fo:leader leader-pattern="space"/>
                  </fo:block>
                  <fo:block>
                     <fo:leader leader-pattern="space"/>
                  </fo:block>
                  <fo:block>
                     <xsl:text>&#xA;</xsl:text>
                  </fo:block-->
               </fo:block>
            </fo:flow>
         </fo:page-sequence>
      </fo:root>
   </xsl:template>
</xsl:stylesheet>
