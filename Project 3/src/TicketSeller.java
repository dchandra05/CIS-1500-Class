import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Ticket {
    private int ticketNumber;
    private String purchaserName;
    private int verificationCode;

    public Ticket(int ticketNumber) {
        this.ticketNumber = ticketNumber;
        this.purchaserName = "";
        this.verificationCode = 0;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public String getPurchaserName() {
        return purchaserName;
    }

    public int getVerificationCode() {
        return verificationCode;
    }

    public boolean isAvailable() {
        return purchaserName.equals("") && verificationCode == 0;
    }

    public void purchase(String name, int code) {
        this.purchaserName = name;
        this.verificationCode = code;
    }

    @Override
    public String toString() {
        return ticketNumber + "," + purchaserName + "," + verificationCode;
    }

    public static Ticket fromString(String line) {
        String[] parts = line.split(",");
        Ticket ticket = new Ticket(Integer.parseInt(parts[0]));
        ticket.purchaserName = parts[1];
        ticket.verificationCode = Integer.parseInt(parts[2]);
        return ticket;
    }
}

public class TicketSeller {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Manage Shows");
            System.out.println("2. Sell Tickets");
            System.out.println("3. Print Show Report");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> manageShows();
                case 2 -> sellTickets();
                case 3 -> printShowReport();
                case 4 -> {
                    System.out.println("Exiting program.");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void manageShows() {
        System.out.print("Enter the show name: ");
        String showName = scanner.nextLine();
        System.out.print("Enter the number of tickets for this show: ");
        int numTickets = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Ticket> tickets = new ArrayList<>();
        for (int i = 1; i <= numTickets; i++) {
            tickets.add(new Ticket(i));
        }

        saveTicketsToFile(showName, tickets);
        System.out.println("Show and tickets saved.");
    }

    private static void sellTickets() {
        System.out.print("Enter the show name: ");
        String showName = scanner.nextLine();

        ArrayList<Ticket> tickets = loadTicketsFromFile(showName);
        if (tickets == null) {
            System.out.println("Show not found.");
            return;
        }

        while (true) {
            System.out.print("Enter desired ticket number: ");
            int ticketNumber = scanner.nextInt();
            scanner.nextLine();

            Ticket ticket = findTicket(tickets, ticketNumber);
            if (ticket == null) {
                System.out.println("Ticket not found.");
            } else if (!ticket.isAvailable()) {
                System.out.println("Ticket is already sold. Choose another ticket.");
            } else {
                System.out.print("Enter your name: ");
                String name = scanner.nextLine();
                int verificationCode = random.nextInt(900000) + 100000;
                ticket.purchase(name, verificationCode);
                System.out.println("Ticket purchased successfully with code: " + verificationCode);
                saveTicketsToFile(showName, tickets);
                break;
            }
        }
    }

    private static void printShowReport() {
        System.out.print("Enter the show name: ");
        String showName = scanner.nextLine();

        ArrayList<Ticket> tickets = loadTicketsFromFile(showName);
        if (tickets == null) {
            System.out.println("Show not found.");
            return;
        }

        System.out.println("\n--- Ticket Report for Show: " + showName + " ---");
        for (Ticket ticket : tickets) {
            System.out.printf("Ticket #%d - Purchaser: %s, Code: %d%n",
                    ticket.getTicketNumber(),
                    ticket.getPurchaserName().isEmpty() ? "Available" : ticket.getPurchaserName(),
                    ticket.getVerificationCode());
        }
    }

    private static Ticket findTicket(ArrayList<Ticket> tickets, int ticketNumber) {
        for (Ticket ticket : tickets) {
            if (ticket.getTicketNumber() == ticketNumber) {
                return ticket;
            }
        }
        return null;
    }

    private static void saveTicketsToFile(String showName, ArrayList<Ticket> tickets) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(showName + ".txt"))) {
            for (Ticket ticket : tickets) {
                writer.println(ticket);
            }
        } catch (IOException e) {
            System.out.println("Error saving tickets: " + e.getMessage());
        }
    }

    private static ArrayList<Ticket> loadTicketsFromFile(String showName) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        File file = new File(showName + ".txt");

        if (!file.exists()) {
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tickets.add(Ticket.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading tickets: " + e.getMessage());
        }
        return tickets;
    }
}
