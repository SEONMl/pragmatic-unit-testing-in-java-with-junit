package util;


import java.io.*;
import java.net.*;
import java.util.*;
import org.junit.*;
import org.junit.jupiter.api.Test;

import java.util.logging.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
import static util.ContainsMatches.containsMatches;

public class SearchTest {
    private static final String A_TITLE = "1";
    @Test
    public void testSearch() throws  IOException {
        InputStream stream =
                streamOn("There are certain queer times and occasions "
                        + "in this strange mixed affair we call life when a man "
                        + "takes this whole universe for a vast practical joke, "
                        + "though the wit thereof he but dimly discerns, and more "
                        + "than suspects that the joke is at nobody's expense but "
                        + "his own.");
            // search
            Search search = new Search(stream, "practical joke", A_TITLE);
            Search.LOGGER.setLevel(Level.OFF);
            search.setSurroundingCharacterCount(10);
            search.execute();
            assertFalse(search.errored());
            assertEquals(search.getMatches(), containsMatches(new Match[] {
                    new Match(A_TITLE, "practical joke","or a vast practical joke, though t") }));

            stream.close();

            // negative
            URLConnection connection =
                    new URL("http://bit.ly/15sYPA7").openConnection();
            InputStream inputStream = connection.getInputStream();
            search = new Search(inputStream, "smelt", A_TITLE);
            search.execute();
            assertTrue(search.getMatches().isEmpty());
            stream.close();
    }

    private InputStream streamOn(String pageContent) {
        return new ByteArrayInputStream(pageContent.getBytes());
    }
}