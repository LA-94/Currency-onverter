package parser;

import exception.ParserException;

import java.io.File;

public interface Parser {
    Object getObject(File file, Class<?> c) throws ParserException;
    void saveObject(File file, Object o) throws ParserException;
}
