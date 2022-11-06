package me.tango.interview.second;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * This class implements hit counting logic per user and thread safe.
 * The storage's based on concurrent hashmap, which be injected into the bean on application startup.
 *
 * @see class with tests {@link UserStatTest}
 */
@Component
public class UserStat {

    private final Map<String, Long> hitsPerUser;

    public UserStat(Map<String, Long> hitsPerUser) {
        this.hitsPerUser = hitsPerUser;
    }

    /**
     * Listens to hits and update its count by {@code userId} in inner storing structure.
     * The call invocation is performed atomically using the node synchronization of concurrent hashmap.
     *
     * @param userId - id of the current user
     */
    public void onUserCall(String userId) {
        hitsPerUser.merge(userId, 1L, Long::sum);
    }
}
