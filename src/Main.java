import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Opret et menukortet
        Menukort menu = new Menukort();

        // Menukortet med alle pizzaer.
        menu.addPizza(new Pizza("Vesuvio", "Tomatsauce, ost, skinke og oregano", 57));
        menu.addPizza(new Pizza("Amerikaner", "Tomatsauce, ost, oksefars og oregano", 53));
        menu.addPizza(new Pizza("Cacciatore", "Tomatsauce, ost, pepperoni og oregano", 57));
        menu.addPizza(new Pizza("Carbona", "Tomatsauce, ost, kødsauce, spaghetti, cocktailpølser og oregano", 63.0));
        menu.addPizza(new Pizza("Dennis", "Tomatsauce, ost, skinke, pepperoni, cocktailpølser og oregano", 65.0));
        menu.addPizza(new Pizza("Bertil", "Tomatsauce, ost, bacon og oregano", 57));
        menu.addPizza(new Pizza("Silvia", "Tomatsauce, ost, pepperoni, rød peber, løg, oliven og oregano", 61));
        menu.addPizza(new Pizza("Victoria", "Tomatsauce, ost, skinke, ananas, champignon, løg og oregano", 61.0));
        menu.addPizza(new Pizza("Toronfo", "Tomatsauce, ost, skinke, bacon, kebab, chili og oregano", 61.0));
        menu.addPizza(new Pizza("Capricciosa", "Tomatsauce, ost, skinke, champignon og oregano", 61.0));
        menu.addPizza(new Pizza("Hawai", "Tomatsauce, ost, skinke, ananas og oregano", 61.0));
        menu.addPizza(new Pizza("Le Blissola", "Tomatsauce, ost, skinke, rejer og oregano", 61.0));
        menu.addPizza(new Pizza("Venezia", "Tomatsauce, ost, skinke, bacon og oregano", 61.0));
        menu.addPizza(new Pizza("Mafia", "Tomatsauce, ost, pepperoni, bacon, løg og oregano", 61.0));
        menu.addPizza(new Pizza("København Speciale", "Tomatsauce, ost, lammekød, trøffel og pesto", 75.0));
        menu.addPizza(new Pizza("Brøndby Speciale", "Tomatsauce, ost, cocktailpølser og oregano", 61.0));
        menu.addPizza(new Pizza("NY KNICKS", "Tomatsauce, ost, pastrami, lammekød og oregano", 75.0));
        menu.addPizza(new Pizza("Ishøj Speciale", "Tomatsauce, ost, bacon, pepperoni og gorgonzola", 61.0));
        menu.addPizza(new Pizza("Roma", "Tomatsauce, kødsauce, champignon", 65.0));
        menu.addPizza(new Pizza("Pep Med Dress", "Tomatsauce, ost, pepperoni og dressing", 60.0));
        menu.addPizza(new Pizza("Quattro Formaggi", "Mascarpone, bøffelmozzarella, gorgonzola, parmesan og port salut", 75.0));
        menu.addPizza(new Pizza("Al Filetto", "Tomatsauce, ost, løg, hakket oksekød, majs, jalapenos og peberfrugt", 75.0));
        menu.addPizza(new Pizza("Ajax", "Tomatsauce, ost, løg, hakket oksekød og champignon", 75.0));
        menu.addPizza(new Pizza("Tagliatelle Alla Boscaiola", "Tomatsauce, ost, karl johan svampe og skinke", 65.0));
        menu.addPizza(new Pizza("Salami", "Tomatsauce, ost, stærk salami, rucola og parmesan", 65.0));
        menu.addPizza(new Pizza("Patate", "mascarpone, kartoffel og bacon", 75.0));
        menu.addPizza(new Pizza("Everton", "Tomatsauce, ost, champignon, grøn peber og kylling", 65.0));
        menu.addPizza(new Pizza("Mama Mia", "Tomatsauce, ost, kebab, gorgonzola og champignon", 65.0));
        menu.addPizza(new Pizza("Scrum Speciale", "Tomatsauce, ost, pepperoni, skinke, bacon, dressing og chili", 65.0));
        menu.addPizza(new Pizza("NÆVER FOREVER", "Tomatsauce, ost, æg, kød strimler, bacon, chili, paprika og oliven", 65.0));
        menu.addPizza(new Pizza("Rødovre", "ansjoser, løg, bacon, rucola og parmesan ", 65.0));

        // Udskriv menukortet
        menu.printMenu();

        // Opret en bestillingsliste
        Bestillingsliste bestillingsListe = new Bestillingsliste();

        // Læs eksisterende bestillinger fra filen
        bestillingsListe.readBestillingerFromFile("bestillinger.txt");

        Scanner scanner = new Scanner(System.in);

        String createNewOrder;
        do {
            System.out.println("Opret en ny bestilling!");
            System.out.println("Indtast kundens navn:");
            String kundeNavn = scanner.nextLine();

            List<Pizza> pizzaOrder = new ArrayList<>();
            String orderMore;

            do {
                System.out.println("Indtast nummeret på den pizza du vil bestille:");
                int pizzaNummer = scanner.nextInt();
                scanner.nextLine(); // Forbruger newline efter nummer

                try {
                    Pizza selectedPizza = menu.getPizza(pizzaNummer - 1); // Justér til 0-indeksering
                    pizzaOrder.add(selectedPizza);
                    System.out.println("Tilføjet: " + selectedPizza.getNavn());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Ugyldigt pizzanummer. Prøv igen.");
                }

                System.out.println("Vil du bestille en til pizza? (ja/nej):");
                orderMore = scanner.nextLine();
            } while (orderMore.equalsIgnoreCase("ja"));

            // Indtast afhentningstidspunkt
            LocalDateTime afhentningstidspunkt;
            while (true) {
                System.out.println("Indtast afhentningstidspunkt (format: ÅÅÅÅ-MM-DD HH:MM):");
                String afhentningstidspunktStr = scanner.nextLine();
                afhentningstidspunkt = LocalDateTime.parse(afhentningstidspunktStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                if (afhentningstidspunkt.isAfter(LocalDateTime.now())) {
                    break;
                } else {
                    System.out.println("Afhentningstidspunktet kan ikke være i fortiden. Prøv igen.");
                }
            }

            // Tilføj bestilling til listen
            Bestilling newBestilling = new Bestilling(pizzaOrder, afhentningstidspunkt, kundeNavn);
            bestillingsListe.addBestilling(newBestilling);

            System.out.println("Vil du oprette en ny bestilling? (ja/nej):");
            createNewOrder = scanner.nextLine();
        } while (createNewOrder.equalsIgnoreCase("ja"));

        // Sortér bestillingerne efter afhentningstidspunkt
        bestillingsListe.sortBestillinger();

        // Gem alle bestillinger (inklusive tidligere og nye) til filen
        bestillingsListe.saveBestillingerToFile("bestillinger.txt");

        // Luk scanner
        scanner.close();
    }
}