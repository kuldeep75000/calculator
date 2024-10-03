import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BirthdayReminder {
    private ArrayList<Person> people;
    private Scanner scanner;

    public BirthdayReminder() {
        people = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addPerson() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the birthday (dd-MM-yyyy): ");
        String birthday = scanner.nextLine();

        people.add(new Person(name, birthday));
        System.out.println("Person added successfully.");
    }

    public void viewBirthdays() {
        if (people.isEmpty()) {
            System.out.println("No birthdays to show.");
            return;
        }

        for (Person person : people) {
            System.out.println(person);
        }
    }

    public void checkTodayBirthdays() {
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        boolean found = false;
        for (Person person : people) {
            if (person.getBirthday().equals(today)) {
                System.out.println("Today is " + person.getName() + "'s birthday!");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No birthdays today.");
        }
    }

    public void menu() {
        while (true) {
            System.out.println("\n--- Birthday Reminder ---");
            System.out.println("1. Add a person");
            System.out.println("2. View all birthdays");
            System.out.println("3. Check today's birthdays");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    addPerson();
                    break;
                case 2:
                    viewBirthdays();
                    break;
                case 3:
                    checkTodayBirthdays();
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        BirthdayReminder reminder = new BirthdayReminder();
        reminder.menu();
    }
}
