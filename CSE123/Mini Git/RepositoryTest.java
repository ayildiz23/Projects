// Ataberk Yildiz
// Brett Wortzman
// CSE 123
// P1: Mini-Git
//
// This is a class that tests the methods in Repository

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class RepositoryTest {
    private Repository repo1;
    private Repository repo2;

    /**
     * NOTE: The following test suite assumes that getRepoHead(), commit(), and size()
     *       are implemented correctly.
     */

    @BeforeEach
    public void setUp() {
        repo1 = new Repository("repo1");
        repo2 = new Repository("repo2");
        Repository.Commit.resetIds();
    }

    @Test
    @DisplayName("Test getHistory()")
    public void getHistory() {
        // Initialize commit messages
        String[] commitMessages = new String[]{"Initial commit.", "Updated method documentation.",
                                                "Removed unnecessary object creation."};

        // Commit the commit messages to repo1
        for (int i = 0; i < commitMessages.length; i++) {
            String commitMessage = commitMessages[i];
            repo1.commit(commitMessage);

            // Assert that the current commit id is at the repository's head
            // We know our ids increment from 0, meaning we can just use i as our id
            assertEquals("" + i, repo1.getRepoHead());
        }

        assertEquals(repo1.getRepoSize(), commitMessages.length);

        // This is the method we are testing for. First, we'll obtain the 2 most recent commits
        // that have been made to repo1.
        String repositoryHistory = repo1.getHistory(2);
        String[] commits = repositoryHistory.split("\n");

        // Verify that getHistory() only returned 2 commits.
        assertEquals(commits.length, 2);

        // Verify that the 2 commits have the correct commit message and commit id
        for (int i = 0; i < commits.length; i++) {
            String commit = commits[i];

            // Old commit messages/ids are on the left and the more recent commit messages/ids are
            // on the right so need to traverse from right to left to ensure that 
            // getHistory() returned the 2 most recent commits.
            int backwardsIndex = (commitMessages.length - 1) - i;
            String commitMessage = commitMessages[backwardsIndex];

            assertTrue(commit.contains(commitMessage));
            assertTrue(commit.contains("" + backwardsIndex));
        }
    }

    @Test
    @DisplayName("Test drop() (empty case)")
    public void testDropEmpty() {
        assertFalse(repo1.drop("123"));
    }

    @Test
    @DisplayName("Test drop() (front case)")
    public void testDropFront() {
        assertEquals(repo1.getRepoSize(), 0);
        // Initialize commit messages
        String[] commitMessages = new String[]{"First commit.", "Added unit tests."};

        // Commit to repo1 - ID = "0"
        repo1.commit(commitMessages[0]);

        // Commit to repo2 - ID = "1"
        repo2.commit(commitMessages[1]);

        // Assert that repo1 successfully dropped "0"
        assertTrue(repo1.drop("0"));
        assertEquals(repo1.getRepoSize(), 0);
        
        // Assert that repo2 does not drop "0" but drops "1"
        // (Note that the commit ID increments regardless of the repository!)
        assertFalse(repo2.drop("0"));
        assertTrue(repo2.drop("1"));
        assertEquals(repo2.getRepoSize(), 0);
    }

    /* TODO - Add additional unit tests */
    @Test
    @DisplayName("Test Constructor Excpetions")
    public void testConstructorExceptions() {

        // Assert that an IllegalArgumentException is thrown 
        // when null or "" is passed into the Constructor
        assertThrows(IllegalArgumentException.class, () -> {Repository r = new Repository(null);},
            "null passed into constructor does not throw exception");
        assertThrows(IllegalArgumentException.class, () -> {Repository r = new Repository("");},
            "\"\" passed into constructor does not throw exception");
    }

    @Test
    @DisplayName("Test Constructor")
    public void testConstructor() {
        assertEquals(null, repo1.getRepoHead(), 
        "getRepoHead does not return null directly after constructor");
    }

    @Test
    @DisplayName("Test Contains")
    public void containsTarget() {
        repo1.commit("Hello");
        assertTrue(repo1.contains("0"));
    }

    @Test
    @DisplayName("Test Size")
    public void repoSize() {
        repo1.commit("Hello");
        repo1.commit("Hello");
        assertEquals(2, repo1.getRepoSize());
    }

    @Test
    @DisplayName("Test Synchronize Second Empty")
    public void synchronizeSecondEmpty() {
        repo1.commit("0");
        repo1.commit("1");
        String result = repo1.toString();
        repo1.synchronize(repo2);
        assertEquals(result, repo1.toString(), 
            "Synchronize fails when second repository is empty");
    }

    @Test
    @DisplayName("Test Synchronize First Empty")
    public void synchronizeFirstEmpty() {
        repo2.commit("0");
        repo2.commit("1");
        String result = repo2.getHistory(2);
        repo1.synchronize(repo2);
        assertEquals(result, repo1.getHistory(2), 
            "Synchronize fails when First repository is empty");
    }

    @Test
    @DisplayName("Test Synchronize General")
    public void synchronizeGeneral() throws InterruptedException {
        repo2.commit("0");
        Thread.sleep(1);
        repo2.commit("1");
        Thread.sleep(1);
        repo1.commit("2");
        Thread.sleep(1);
        repo1.commit("3");
        Thread.sleep(1);
        repo2.commit("4");
        Thread.sleep(1);
        repo1.synchronize(repo2);
        for (int i = repo1.getRepoSize() - 1; i > -1; i--) {
            // Assert that the ids line up in a chronological order.
            
            assertEquals("" + i, repo1.getRepoHead(), "Synchronize fails with a general case");
            repo1.drop("" + i);
        }
    }
}
