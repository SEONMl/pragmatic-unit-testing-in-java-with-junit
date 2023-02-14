package util;


import java.io.*;
import java.net.*;

import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;
import static util.ContainsMatches.*;

public class SearchTest {
    private static final String A_TITLE = "1";
    private InputStream stream;

    @BeforeEach
    public void turnOffLogging() {
        Search.LOGGER.setLevel(Level.OFF);
    }

    @AfterEach
    public void closeResources() throws IOException {
        stream.close();
    }

    @Test
    public void returnsMatchesShowingContextWhenSearchStringInContent() {
        stream = streamOn("There are certain queer times and occasions "
                + "in this strange mixed affair we call life when a man "
                + "takes this whole universe for a vast practical joke, "
                + "though the wit thereof he but dimly discerns, and more "
                + "than suspects that the joke is at nobody's expense but "
                + "his own.");
        Search search = new Search(stream, "practical joke", A_TITLE);
        Search.LOGGER.setLevel(Level.OFF);
        search.setSurroundingCharacterCount(10);

        search.execute();

        assertEquals(search.getMatches(), containsMatches(new Match[]
                {new Match(A_TITLE, "practical joke",
                        "or a vast practical joke, though t")}));
    }

    @Test
    public void noMatchesReturnedWhenSearchStringNotInContent()
            throws MalformedURLException, IOException {
        URLConnection connection =
                new URL("http://bit.ly/15sYPA7").openConnection();
        stream = connection.getInputStream();
        Search search = new Search(stream, "smelt", A_TITLE);

        search.execute();

        assertTrue(search.getMatches().isEmpty());
    }

    private InputStream streamOn(String pageContent) {
        return new ByteArrayInputStream(pageContent.getBytes());
    }
}