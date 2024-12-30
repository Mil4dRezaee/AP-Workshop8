import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Command {

    private final List<String> titlesList = new ArrayList<>();

    private Command() {
        populateTitlesList();
    }

    private static final Command command = new Command();

    public static Command getInstance() {
        return command;
    }

    public void add(Note note) {
        String fileName = "assets/" + note.getTitle() + ".ser";
        File file = new File(fileName);

        if (!isTitleUnique(note.getTitle())) {
            System.out.println("Duplicate title is not allowed!");
            return;
        }

        if (!Files.exists(Paths.get("assets/"))) {
            try {
                Files.createDirectories(Paths.get("assets/"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(note);
            titlesList.add(note.getTitle());
            System.out.println("Note added successfully...");
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    public void remove(int index) {
        String title = titlesList.get(index);
        String fileName = "assets/" + title + ".ser";
        File file = new File(fileName);
        if (file.delete()) {
            titlesList.remove(index);
            System.out.println("Note removed successfully...");
        } else {
            System.out.println("Failed to delete the note.");
        }
    }

    public void export(int index) {
        if (!Files.exists(Paths.get("export/"))) {
            try {
                Files.createDirectories(Paths.get("export/"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        String title = titlesList.get(index);
        String fileName = "export/" + title + ".txt";
        File file = new File(fileName);

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(showSelectedNoteContent(index));
            System.out.println("Note exported successfully...");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showNoteTitles() {
        populateTitlesList();
        String directoryName = "assets/";

        int index = 0;
        System.out.println("Note titles:");
        for (String title : titlesList) {
            try {
                Path path = Paths.get(directoryName + title + ".ser");
                BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
                String creationDate = String.valueOf(attributes.creationTime());
                System.out.println(index + "\t" + title + "\t\t\t" + creationDate);
                index++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String showSelectedNoteContent(int index) {
        Note note;
        String title = titlesList.get(index);
        String fileName = "assets/" + title + ".ser";

        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            note = (Note) objectInputStream.readObject();
            return "----\t" + note.getTitle() + "\t----\n" + note.getContent();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    private void populateTitlesList() {
        titlesList.clear();
        String directoryName = "assets/";
        File directory = new File(directoryName);
        String[] fileList = directory.list();
        if (fileList != null) {
            for (String fileName : fileList) {
                if (fileName.endsWith(".ser")) {
                    titlesList.add(fileName.replaceAll("\\.ser$", ""));
                }
            }
        }
    }

    private boolean isTitleUnique(String title) {
        return !titlesList.contains(title);
    }

    public void exit() {
        System.exit(0);
    }
}