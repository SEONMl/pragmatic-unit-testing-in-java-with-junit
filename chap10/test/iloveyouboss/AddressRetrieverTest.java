package iloveyouboss;

import java.io.*;
import org.json.simple.parser.*;
import org.junit.*;
import org.junit.jupiter.api.Test;
import util.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressRetrieverTest {
    @Test
    public void answersAppropriateAddressForValidCoordinates()
            throws IOException, ParseException {
        // stub: 테스트 용도로 하드 코딩한 값을 반환하는 구현체
        Http http = (String url) ->
                "{\"address\":{"
                        + "\"house_number\":\"324\","
                        + "\"road\":\"North Tejon Street\","
                        + "\"city\":\"Colorado Springs\","
                        + "\"state\":\"Colorado\","
                        + "\"postcode\":\"80903\","
                        + "\"country_code\":\"us\"}"
                        + "}";
        AddressRetriever retriever = new AddressRetriever(http);

        Address address = retriever.retrieve(38.0,-104.0);

        assertEquals(address.houseNumber, ("324"));
        assertEquals(address.road, ("North Tejon Street"));
        assertEquals(address.city, ("Colorado Springs"));
        assertEquals(address.state, ("Colorado"));
        assertEquals(address.zip, ("80903"));
    }

    @Test
    public void returnsAppropriateAddressForValidCoordinates()
            throws IOException, ParseException {
        Http http = new Http() {
            @Override
            public String get(String url) throws IOException {
                return "{\"address\":{"
                        + "\"house_number\":\"324\","
                        + "\"road\":\"North Tejon Street\","
                        // ...
                        + "\"city\":\"Colorado Springs\","
                        + "\"state\":\"Colorado\","
                        + "\"postcode\":\"80903\","
                        + "\"country_code\":\"us\"}"
                        + "}";
            }};
        AddressRetriever retriever = new AddressRetriever(http);

        Address address = retriever.retrieve(38.0,-104.0);

        assertEquals(address.houseNumber, ("324"));
        assertEquals(address.road, ("North Tejon Street"));
        assertEquals(address.city, ("Colorado Springs"));
        assertEquals(address.state, ("Colorado"));
        assertEquals(address.zip, ("80903"));
    }
}