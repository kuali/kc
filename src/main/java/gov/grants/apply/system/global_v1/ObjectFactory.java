
package gov.grants.apply.system.global_v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the gov.grants.apply.system.global_v1 package. 
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

    private final static QName _FormVersionIdentifier_QNAME = new QName("http://apply.grants.gov/system/Global-V1.0", "FormVersionIdentifier");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: gov.grants.apply.system.global_v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HashValue }
     * 
     */
    public HashValue createHashValue() {
        return new HashValue();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://apply.grants.gov/system/Global-V1.0", name = "FormVersionIdentifier")
    public JAXBElement<String> createFormVersionIdentifier(String value) {
        return new JAXBElement<String>(_FormVersionIdentifier_QNAME, String.class, null, value);
    }

}
