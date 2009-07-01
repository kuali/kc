
package gov.grants.apply.webservices.applicantintegrationservices_v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the gov.grants.apply.webservices.applicantintegrationservices_v1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SubmissionTitle_QNAME = new QName("http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", "SubmissionTitle");
    private final static QName _CompetitionID_QNAME = new QName("http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", "CompetitionID");
    private final static QName _ClosingDate_QNAME = new QName("http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", "ClosingDate");
    private final static QName _OfferingAgency_QNAME = new QName("http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", "OfferingAgency");
    private final static QName _SubmitterName_QNAME = new QName("http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", "SubmitterName");
    private final static QName _OpportunityID_QNAME = new QName("http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", "OpportunityID");
    private final static QName _OpeningDate_QNAME = new QName("http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", "OpeningDate");
    private final static QName _AgencyName_QNAME = new QName("http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", "AgencyName");
    private final static QName _CFDADescription_QNAME = new QName("http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", "CFDADescription");
    private final static QName _GrantsGovTrackingNumber_QNAME = new QName("http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", "Grants_govTrackingNumber");
    private final static QName _ActivityTitle_QNAME = new QName("http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", "ActivityTitle");
    private final static QName _StatusDateTime_QNAME = new QName("http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", "StatusDateTime");
    private final static QName _ReceivedDateTime_QNAME = new QName("http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", "ReceivedDateTime");
    private final static QName _CFDANumber_QNAME = new QName("http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", "CFDANumber");
    private final static QName _OpportunityTitle_QNAME = new QName("http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", "OpportunityTitle");
    private final static QName _AgencyTrackingNumber_QNAME = new QName("http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", "AgencyTrackingNumber");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gov.grants.apply.webservices.applicantintegrationservices_v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetOpportunityListRequest }
     * 
     */
    public GetOpportunityListRequest createGetOpportunityListRequest() {
        return new GetOpportunityListRequest();
    }

    /**
     * Create an instance of {@link GetApplicationListResponse }
     * 
     */
    public GetApplicationListResponse createGetApplicationListResponse() {
        return new GetApplicationListResponse();
    }

    /**
     * Create an instance of {@link GetApplicationListRequest.ApplicationFilter }
     * 
     */
    public GetApplicationListRequest.ApplicationFilter createGetApplicationListRequestApplicationFilter() {
        return new GetApplicationListRequest.ApplicationFilter();
    }

    /**
     * Create an instance of {@link GetApplicationStatusDetailRequest }
     * 
     */
    public GetApplicationStatusDetailRequest createGetApplicationStatusDetailRequest() {
        return new GetApplicationStatusDetailRequest();
    }

    /**
     * Create an instance of {@link ApplicationInformationType }
     * 
     */
    public ApplicationInformationType createApplicationInformationType() {
        return new ApplicationInformationType();
    }

    /**
     * Create an instance of {@link SubmitApplicationRequest }
     * 
     */
    public SubmitApplicationRequest createSubmitApplicationRequest() {
        return new SubmitApplicationRequest();
    }

    /**
     * Create an instance of {@link GetApplicationListRequest }
     * 
     */
    public GetApplicationListRequest createGetApplicationListRequest() {
        return new GetApplicationListRequest();
    }

    /**
     * Create an instance of {@link GetOpportunitiesWithInfoRequest }
     * 
     */
    public GetOpportunitiesWithInfoRequest createGetOpportunitiesWithInfoRequest() {
        return new GetOpportunitiesWithInfoRequest();
    }

    /**
     * Create an instance of {@link OpportunityInformationType }
     * 
     */
    public OpportunityInformationType createOpportunityInformationType() {
        return new OpportunityInformationType();
    }

    /**
     * Create an instance of {@link SubmitApplicationResponse }
     * 
     */
    public SubmitApplicationResponse createSubmitApplicationResponse() {
        return new SubmitApplicationResponse();
    }

    /**
     * Create an instance of {@link GetApplicationStatusDetailResponse }
     * 
     */
    public GetApplicationStatusDetailResponse createGetApplicationStatusDetailResponse() {
        return new GetApplicationStatusDetailResponse();
    }

    /**
     * Create an instance of {@link GetOpportunityListResponse }
     * 
     */
    public GetOpportunityListResponse createGetOpportunityListResponse() {
        return new GetOpportunityListResponse();
    }

    /**
     * Create an instance of {@link GetOpportunitiesWithInfoResponse }
     * 
     */
    public GetOpportunitiesWithInfoResponse createGetOpportunitiesWithInfoResponse() {
        return new GetOpportunitiesWithInfoResponse();
    }

    /**
     * Create an instance of {@link OpportunityWithInfoType }
     * 
     */
    public OpportunityWithInfoType createOpportunityWithInfoType() {
        return new OpportunityWithInfoType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", name = "SubmissionTitle")
    public JAXBElement<String> createSubmissionTitle(String value) {
        return new JAXBElement<String>(_SubmissionTitle_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", name = "CompetitionID")
    public JAXBElement<String> createCompetitionID(String value) {
        return new JAXBElement<String>(_CompetitionID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", name = "ClosingDate")
    public JAXBElement<XMLGregorianCalendar> createClosingDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ClosingDate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", name = "OfferingAgency")
    public JAXBElement<String> createOfferingAgency(String value) {
        return new JAXBElement<String>(_OfferingAgency_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", name = "SubmitterName")
    public JAXBElement<String> createSubmitterName(String value) {
        return new JAXBElement<String>(_SubmitterName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", name = "OpportunityID")
    public JAXBElement<String> createOpportunityID(String value) {
        return new JAXBElement<String>(_OpportunityID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", name = "OpeningDate")
    public JAXBElement<XMLGregorianCalendar> createOpeningDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_OpeningDate_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", name = "AgencyName")
    public JAXBElement<String> createAgencyName(String value) {
        return new JAXBElement<String>(_AgencyName_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", name = "CFDADescription")
    public JAXBElement<String> createCFDADescription(String value) {
        return new JAXBElement<String>(_CFDADescription_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", name = "Grants_govTrackingNumber")
    public JAXBElement<String> createGrantsGovTrackingNumber(String value) {
        return new JAXBElement<String>(_GrantsGovTrackingNumber_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", name = "ActivityTitle")
    public JAXBElement<String> createActivityTitle(String value) {
        return new JAXBElement<String>(_ActivityTitle_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", name = "StatusDateTime")
    public JAXBElement<XMLGregorianCalendar> createStatusDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_StatusDateTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", name = "ReceivedDateTime")
    public JAXBElement<XMLGregorianCalendar> createReceivedDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_ReceivedDateTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", name = "CFDANumber")
    public JAXBElement<String> createCFDANumber(String value) {
        return new JAXBElement<String>(_CFDANumber_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", name = "OpportunityTitle")
    public JAXBElement<String> createOpportunityTitle(String value) {
        return new JAXBElement<String>(_OpportunityTitle_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://apply.grants.gov/WebServices/ApplicantIntegrationServices-V1.0", name = "AgencyTrackingNumber")
    public JAXBElement<String> createAgencyTrackingNumber(String value) {
        return new JAXBElement<String>(_AgencyTrackingNumber_QNAME, String.class, null, value);
    }

}
