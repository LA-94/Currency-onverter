package parser.impl;

import exception.ParserException;
import parser.Parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JaxbParser implements Parser {
    @Override
    public Object getObject(File file, Class<?> c) throws ParserException {
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new ParserException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void saveObject(File file, Object o) throws ParserException {
        try {
            JAXBContext context = JAXBContext.newInstance(o.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(o, file);
        } catch (JAXBException e) {
            throw new ParserException(e.getMessage(), e.getCause());
        }
    }
}
