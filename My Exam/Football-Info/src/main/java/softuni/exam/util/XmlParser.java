package softuni.exam.util;

import javax.xml.bind.JAXBException;

public interface XmlParser {
    <O> O parseXml(String filePath, Class<O> obj) throws JAXBException;
}
