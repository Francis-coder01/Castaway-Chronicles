package castaway_chronicles;

import org.junit.jupiter.api.*;

import java.io.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ResourceManagerTest {
    private File newFile;
    private File newDir;
    private ResourceManager resourceManager;
    @BeforeEach
    void init() {
        resourceManager = ResourceManager.getInstance();
        assertNotNull(resourceManager);
        newFile = new File("src/main/resources/Testing/resourceTest.txt");
        newDir = new File("src/main/resources/Testing/resourceDir");

        try {
            boolean success = newFile.createNewFile();
            assertTrue(success);
            success = newDir.mkdir();
            assertTrue(success);
            success = new File(newDir.getPath() + "/file1.txt").createNewFile();
            assertTrue(success);
            success = new File(newDir.getPath() + "/file2.txt").createNewFile();
            assertTrue(success);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        resourceManager.setPath("Testing/resourceTest.txt");
        resourceManager.writeToFile("lorem ipsem\ntesting\nresource\nmanager\n");
    }

    @AfterEach
    void tearDown() {
        boolean success =  newFile.delete();
        assertTrue(success);
        resourceManager.setPath("Testing/resourceDir");
        resourceManager.deleteResourceFileContent();
        success = newDir.delete();
        assertTrue(success);
    }

    @Test
    void existsStaticResourceFile(){
        resourceManager.setPath("Testing/staticResource.txt");
        assertTrue(resourceManager.existsStaticResourceFile());

        resourceManager.setPath("Testing/staticResource2.txt");
        assertFalse(resourceManager.existsStaticResourceFile());
    }

    @Test
    void readStaticResourceFile() {
        resourceManager.setPath("Testing/staticResource.txt");
        assertEquals(List.of("lorem ipsem","testing","resource","manager"), resourceManager.readCurrentTimeResourceFile());
    }

    @Test
    void failToReadStaticResourceFile() {
        resourceManager.setPath("Testing/resourceTest2.txt");
        assertThrows(RuntimeException.class,resourceManager::readStaticResourceFile);
    }
    @Test
    void readCurrentTimeResourceFile() {
        assertEquals(List.of("lorem ipsem","testing","resource","manager"),resourceManager.readCurrentTimeResourceFile());

        resourceManager.writeToFile("changed");

        assertEquals(List.of("lorem ipsem","testing","resource","manager","changed"),resourceManager.readCurrentTimeResourceFile());
    }

    @Test
    void failToReadCurrentTimeResourceFile() {
        resourceManager.setPath("Testing/resourceTest2");
        assertThrows(RuntimeException.class,resourceManager::readCurrentTimeResourceFile);
    }
    @Test
    void failToWriteToResourceFile() throws IOException {
        resourceManager.setPath("Testing/resourceTest2.png");
        File file = resourceManager.getFile();
        boolean success = file.createNewFile();
        assertTrue(success);
        assertThrows(Exception.class,()->{
            boolean successFullReadOnly = file.setReadOnly();
            assertTrue(successFullReadOnly);
            resourceManager.writeToFile("test");
        });
        success = file.delete();
        assertTrue(success);
    }
    @Test
    void countFiles() {
        resourceManager.setPath("Testing/testDir");

        assertEquals(2,resourceManager.countFiles());
    }
}
