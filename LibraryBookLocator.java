import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private int publicationYear;
    private String location;

    public Book(String title, String author, int publicationYear, String location) {
        this.title = title.toLowerCase();
        this.author = author.toLowerCase();
        this.publicationYear = publicationYear;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public String getLocation() {
        return location;
    }

    public void display() {
        System.out.println("Title: " + capitalize(title));
        System.out.println("Author: " + capitalize(author));
        System.out.println("Publication Year: " + publicationYear);
        System.out.println("Location: " + location);
        System.out.println("----------------------------");
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
}

public class LibraryBookLocator {
    private static List<Book> books = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n===== Library Book Locator =====");
            System.out.println("1. Add a Book");
            System.out.println("2. Search for a Book");
            System.out.println("3. View All Books");
            System.out.println("4. Delete a Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = getIntInput();

            switch (choice) {
                case 1 -> addBook();
                case 2 -> searchBook();
                case 3 -> viewAllBooks();
                case 4 -> deleteBook();
                case 5 -> System.out.println("Exiting program. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine().trim();
        System.out.print("Enter publication year: ");
        int year = getIntInput();
        System.out.print("Enter book location (e.g., Shelf A, Rack 2): ");
        String location = scanner.nextLine().trim();

        Book book = new Book(title, author, year, location);
        books.add(book);
        System.out.println("Book added successfully!");
    }

    private static void searchBook() {
        System.out.print("Enter title or author to search: ");
        String query = scanner.nextLine().toLowerCase();
        boolean found = false;

        for (Book book : books) {
            if (book.getTitle().contains(query) || book.getAuthor().contains(query)) {
                book.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    private static void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }

        System.out.println("\nList of all books:");
        for (Book book : books) {
            book.display();
        }
    }

    private static void deleteBook() {
        System.out.print("Enter title or author to delete: ");
        String query = scanner.nextLine().toLowerCase();
        Iterator<Book> iterator = books.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getTitle().contains(query) || book.getAuthor().contains(query)) {
                book.display();
                System.out.print("Do you want to delete this book? (yes/no): ");
                String confirm = scanner.nextLine().trim().toLowerCase();
                if (confirm.equals("yes")) {
                    iterator.remove();
                    System.out.println("Book deleted successfully!");
                    found = true;
                    break; // delete only first matching, remove this break if you want to delete multiple
                }
            }
        }

        if (!found) {
            System.out.println("No matching book found to delete.");
        }
    }

    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}
