import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("1-\tAdd");
            System.out.println("2-\tRemove");
            System.out.println("3-\tNotes");
            System.out.println("4-\tExport");
            System.out.println("5-\tExit");

            int input = scanner.nextInt();
            scanner.nextLine(); // clear the buffer

            switch (input) {
                case 1:
                    System.out.println("Enter the title for note: ");
                    String title = scanner.nextLine();
                    System.out.println("Type the content of the note (end with #):");
                    String content = inputText();
                    Note note = new Note();
                    note.setFields(title, content);
                    Command.getInstance().add(note);
                    break;

                case 2:
                    Command.getInstance().showNoteTitles();
                    Command.getInstance().remove(scanner.nextInt());
                    break;

                case 3:
                    Command.getInstance().showNoteTitles();
                    System.out.println(Command.getInstance().showSelectedNoteContent(scanner.nextInt()));
                    break;

                case 4:
                    Command.getInstance().showNoteTitles();
                    Command.getInstance().export(scanner.nextInt());
                    break;

                case 5:
                    Command.getInstance().exit();

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static String inputText() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder content = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equals("#")) {
            content.append(line).append("\n");
        }
        return content.toString();
    }
}