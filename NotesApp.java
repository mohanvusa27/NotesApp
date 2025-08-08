import java.io.*;
import java.util.Scanner;

public class NotesApp {

    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to Notes App!");

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Write a new note");
            System.out.println("2. Read all notes");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    writeNote(scanner);
                    break;
                case "2":
                    readNotes();
                    break;
                case "3":
                    running = false;
                    System.out.println("Exiting Notes App. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }

    private static void writeNote(Scanner scanner) {
        System.out.println("\nEnter your note (type 'END' on a new line to finish):");
        StringBuilder note = new StringBuilder();
        String line;
        
        while (!(line = scanner.nextLine()).equalsIgnoreCase("END")) {
            note.append(line).append(System.lineSeparator());
        }

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) { // append = true
            writer.write(note.toString());
            writer.write("----\n"); // separator
            System.out.println("Note saved successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the note.");
            e.printStackTrace();
        }
    }

    private static void readNotes() {
        System.out.println("\nYour Notes:");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean isEmpty = true;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                isEmpty = false;
            }

            if (isEmpty) {
                System.out.println("(No notes found.)");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No notes found. Write your first one!");
        } catch (IOException e) {
            System.out.println("An error occurred while reading notes.");
            e.printStackTrace();
        }
    }
}