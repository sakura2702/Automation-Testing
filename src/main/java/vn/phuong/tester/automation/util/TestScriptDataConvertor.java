/*
 * Copyright (c) 2015 Nguyen Thi Phuong
 */
package vn.phuong.tester.automation.util;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.Reader;

/**
 * TestScriptDataManager - This class used to convert the Object to Json and then store it into database
 *
 * @author phuong.nguyenthi
 */
public class TestScriptDataConvertor<T> {


  private final JsonFactory jsonFactory = new JsonFactory();


  public T loadFrom(String data, Class<T> clazz) throws IOException {
    ObjectMapper om = initMapper();
    return om.readValue(data, clazz);
  }


  public T loadFrom(Reader reader, Class<T> clazz) throws IOException {
    ObjectMapper om = initMapper();
    return om.readValue(reader, clazz);
  }



  public String saveFrom(T object) throws IOException {
    ObjectMapper om = initMapper();
    return om.writeValueAsString(object);
  }


  private ObjectMapper initMapper() {
    ObjectMapper om = new ObjectMapper(jsonFactory);
    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    return om;
  }
}
