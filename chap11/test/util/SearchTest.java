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
            List<Match> matches = search.getMatches();
            assertTrue(matches.size() >= 1);
            Match match = matches.get(0);
            assertEquals(match.searchString, ("practical joke"));
            assertEquals(match.surroundingContext,
                    ("or a vast practical joke, though t"));
            stream.close();

            // negative
            URLConnection connection =
                    new URL("http://bit.ly/15sYPA7").openConnection();
            InputStream inputStream = connection.getInputStream();
            search = new Search(inputStream, "smelt", "http://bit.ly/15sYPA7");
            search.execute();
            assertEquals(search.getMatches().size(), 0);
            stream.close();
    }
}