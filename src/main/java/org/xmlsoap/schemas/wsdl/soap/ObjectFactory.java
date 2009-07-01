
package org.xmlsoap.schemas.wsdl.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.xmlsoap.schemas.wsdl.soap package. 
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

    private final static QName _Body_QNAME = new QName("http://schemas.xmlsoap.org/wsdl/soap/", "body");
    private final static QName _Binding_QNAME = new QName("http://schemas.xmlsoap.org/wsdl/soap/", "binding");
    private final static QName _Header_QNAME = new QName("http://schemas.xmlsoap.org/wsdl/soap/", "header");
    private final static QName _Fault_QNAME = new QName("http://schemas.xmlsoap.org/wsdl/soap/", "fault");
    private final static QName _Operation_QNAME = new QName("http://schemas.xmlsoap.org/wsdl/soap/", "operation");
    private final static QName _Address_QNAME = new QName("http://schemas.xmlsoap.org/wsdl/soap/", "address");
    private final static QName _Headerfault_QNAME = new QName("http://schemas.xmlsoap.org/wsdl/soap/", "headerfault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.xmlsoap.schemas.wsdl.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TAddress }
     * 
     */
    public TAddress createTAddress() {
        return new TAddress();
    }

    /**
     * Create an instance of {@link TOperation }
     * 
     */
    public TOperation createTOperation() {
        return new TOperation();
    }

    /**
     * Create an instance of {@link TBinding }
     * 
     */
    public TBinding createTBinding() {
        return new TBinding();
    }

    /**
     * Create an instance of {@link THeader }
     * 
     */
    public THeader createTHeader() {
        return new THeader();
    }

    /**
     * Create an instance of {@link THeaderFault }
     * 
     */
    public THeaderFault createTHeaderFault() {
        return new THeaderFault();
    }

    /**
     * Create an instance of {@link TBody }
     * 
     */
    public TBody createTBody() {
        return new TBody();
    }

    /**
     * Create an instance of {@link TFault }
     * 
     */
    public TFault createTFault() {
        return new TFault();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TBody }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.xmlsoap.org/wsdl/soap/", name = "body")
    public JAXBElement<TBody> createBody(TBody value) {
        return new JAXBElement<TBody>(_Body_QNAME, TBody.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TBinding }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.xmlsoap.org/wsdl/soap/", name = "binding")
    public JAXBElement<TBinding> createBinding(TBinding value) {
        return new JAXBElement<TBinding>(_Binding_QNAME, TBinding.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link THeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.xmlsoap.org/wsdl/soap/", name = "header")
    public JAXBElement<THeader> createHeader(THeader value) {
        return new JAXBElement<THeader>(_Header_QNAME, THeader.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.xmlsoap.org/wsdl/soap/", name = "fault")
    public JAXBElement<TFault> createFault(TFault value) {
        return new JAXBElement<TFault>(_Fault_QNAME, TFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TOperation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.xmlsoap.org/wsdl/soap/", name = "operation")
    public JAXBElement<TOperation> createOperation(TOperation value) {
        return new JAXBElement<TOperation>(_Operation_QNAME, TOperation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.xmlsoap.org/wsdl/soap/", name = "address")
    public JAXBElement<TAddress> createAddress(TAddress value) {
        return new JAXBElement<TAddress>(_Address_QNAME, TAddress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link THeaderFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.xmlsoap.org/wsdl/soap/", name = "headerfault")
    public JAXBElement<THeaderFault> createHeaderfault(THeaderFault value) {
        return new JAXBElement<THeaderFault>(_Headerfault_QNAME, THeaderFault.class, null, value);
    }

}
