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
    @Test
    public void testSearch() throws  IOException {
            String pageContent = "There are certain queer times and occasions "
                    + "in this strange mixed affair we call life when a man "
                    + "takes this whole universe for a vast practical joke, "
                    + "though the wit thereof he but dimly discerns, and more "
                    + "than suspects that the joke is at nobody's expense but "
                    + "his own.";
            byte[] bytes = pageContent.getBytes();
            ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
            // search
            Search search = new Search(stream, "practical joke", "1");
            Search.LOGGER.setLevel(Level.OFF);
            search.setSurroundingCharacterCount(10);
            search.execute();
            assertFalse(search.errored());
            assertEquals(search.getMatches(), containsMatches(new Match[] {
                    new Match("1", "practical joke","or a vast practical joke, though t") }));

            stream.close();

            // negative
            URLConnection connection =
                    new URL("http://bit.ly/15sYPA7").openConnection();
            InputStream inputStream = connection.getInputStream();
            search = new Search(inputStream, "smelt", "http://bit.ly/15sYPA7");
            search.execute();
            assertTrue(search.getMatches().isEmpty());
            stream.close();
    }
}