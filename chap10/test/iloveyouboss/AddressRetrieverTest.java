package iloveyouboss;

import java.io.*;
import org.json.simple.parser.*;
import org.junit.*;
import org.junit.jupiter.api.Test;
import util.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AddressRetrieverTest {
    @Test
    public void answersAppropriateAddressForValidCoordinates()
            throws IOException, ParseException {
        // mock 인스턴스
        Http http = mock(Http.class);
        when(http.get(contains("lat=38.000000&lon=-104.000000"))) // 테스트 기대사항 설정
                .thenReturn( // 기대사항이 충족되었을 때 처리
                "{\"address\":{"
                + "\"house_number\":\"324\","
                + "\"road\":\"North Tejon Street\","
                + "\"city\":\"Colorado Springs\","
                + "\"state\":\"Colorado\","
                + "\"postcode\":\"80903\","
                + "\"country_code\":\"us\"}"
                + "}");
        AddressRetriever retriever = new AddressRetriever(http);

        Address address = retriever.retrieve(38.0,-104.0);

        assertEquals(address.houseNumber, ("324"));
        assertEquals(address.road, ("North Tejon Street"));
        assertEquals(address.city, ("Colorado Springs"));
        assertEquals(address.state, ("Colorado"));
        assertEquals(address.zip, ("80903"));
    }
}