package iloveyouboss;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class ProfileMatcher {
    private Map<String, Profile> profiles = new HashMap<>();
    private static final int DEFAULT_POOL_SIZE = 4;

    public void add(Profile profile) {
        profiles.put(profile.getId(), profile);
    }



    public void findMatchingProfiles(
            Criteria criteria, MatchListener listener) {
        ExecutorService executor =
                Executors.newFixedThreadPool(DEFAULT_POOL_SIZE);

        List<MatchSet> matchSets = profiles.values().stream()
                .map(profile -> profile.getMatchSet(criteria))
                .collect(Collectors.toList());
        for (MatchSet set: matchSets) {
            Runnable runnable = () -> {
                if (set.matches())
                    listener.foundMatch(profiles.get(set.getProfileId()), set);
            };
            executor.execute(runnable);
        }
        executor.shutdown();
    }
}