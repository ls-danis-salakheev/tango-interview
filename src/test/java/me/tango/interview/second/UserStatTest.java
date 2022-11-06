package me.tango.interview.second;

import me.tango.interview.TangoInterviewApplicationTests;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.TestInstance.*;

@TestInstance(Lifecycle.PER_CLASS)
class UserStatTest extends TangoInterviewApplicationTests {

    @Autowired
    UserStat userStat;

    ExecutorService executorService;

    @BeforeAll
    void init() {
        executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() - 1,
                runnable -> new Thread(runnable, "Request-#")
        );
    }

    @Test
    void testHitCounting(@Autowired Map<String, Long> hitsPerUser) throws InterruptedException {
        int hitsCount = 1_000_000;
        String userId = "user123";
        IntStream.range(0, hitsCount)
                .forEach(hit -> executorService
                        .execute(() -> userStat.onUserCall(userId)));

        int timeout = 30;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        if (!executorService.awaitTermination(timeout, timeUnit)) {
            List<Runnable> tasks = executorService.shutdownNow();
            if (tasks.size() > 0) {
                String message = "%d tasks haven't executed in a given time (%d %s) to terminate";
                throw new RuntimeException(message.formatted(tasks.size(), timeout, timeUnit));
            }
        }
        assertNotEquals(0L, hitsPerUser.get(userId));
        assertEquals(hitsCount, hitsPerUser.get(userId));

    }

    @AfterAll
    void close() {
        executorService.shutdown();
    }
}