import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Klasse til at håndtere en liste af bestillinger
public class Bestillingsliste {
    private final List<Bestilling> bestillinger; // Liste til at gemme bestillinger

    // Konstruktører til at initialisere listen over bestillinger
    public Bestillingsliste() {
        bestillinger = new ArrayList<>();
    }

    // Metode til at tilføje en ny bestilling til listen
    public void addBestilling(Bestilling bestilling) {
        bestillinger.add(bestilling);
    }

    // Metode til at sortere bestillinger efter afhentningstidspunkt
    public void sortBestillinger() {
        bestillinger.sort(Comparator.comparing(Bestilling::getAfhentningstidspunkt));
    }

    // Metode til at læse bestillinger fra en fil
    public void readBestillingerFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            while ((line = reader.readLine()) != null) {
                try {
                    System.out.println("Behandler linje: " + line); // Fejlfindingsudskrift
                    String[] parts = line.split("; ");
                    if (parts.length < 4) {
                        System.out.println("Ugyldig linjeformat: " + line);
                        continue; // Spring over denne linje, hvis den ikke har nok dele
                    }
                    System.out.println("Parts: " + Arrays.toString(parts)); // Fejlfindingsudskrift

                    // Split og valider kunde navn
                    String[] kundeNavnParts = parts[0].split(": ");
                    if (kundeNavnParts.length < 2) {
                        System.out.println("Ugyldigt kundeformat: " + parts[0]);
                        continue; // Spring over denne linje, hvis kundeformatet er ugyldigt
                    }
                    String kundeNavn = kundeNavnParts[1];
                    System.out.println("Kunde: " + kundeNavn); // Fejlfindingsudskrift

                    String[] afhentningstidspunktParts = parts[2].split(": ");
                    if (afhentningstidspunktParts.length < 2) {
                        System.out.println("Ugyldigt afhentningstidspunktformat: " + parts[2]);
                        continue; // Spring over denne linje, hvis afhentningstidspunktformatet er ugyldigt
                    }
                    String afhentningstidspunktStr = afhentningstidspunktParts[1];
                    LocalDateTime afhentningstidspunkt = LocalDateTime.parse(afhentningstidspunktStr, formatter);
                    System.out.println("Afhentningstidspunkt: " + afhentningstidspunkt); // Fejlfindingsudskrift

                    // Parsing pizzaer fra filen
                    String[] pizzaParts = parts[1].split(": ")[1].split(";");
                    List<Pizza> pizzas = new ArrayList<>();
                    for (String pizzaPart : pizzaParts) {
                        String[] pizzaDetails = pizzaPart.split("-");
                        if (pizzaDetails.length < 3) {
                            System.out.println("Ugyldig pizzaformat: " + pizzaPart);
                            continue; // Spring over denne pizza, hvis den ikke har nok dele
                        }
                        String navn = pizzaDetails[0].trim();
                        String ingredienser = pizzaDetails[1].trim();
                        double pris = Double.parseDouble(pizzaDetails[2].trim().replace(" kr", ""));
                        pizzas.add(new Pizza(navn, ingredienser, pris));
                    }

                    // Opret et ny bestilling og tilføj den til listen
                    Bestilling bestilling = new Bestilling(pizzas, afhentningstidspunkt, kundeNavn);
                    bestillinger.add(bestilling);
                } catch (Exception e) {
                    System.out.println("Fejl ved behandling af linje: " + line);
                    e.printStackTrace();
                }
            }
            sortBestillinger(); // Sørg for at sortere efter, at alle bestillinger er læst ind
        } catch (IOException e) {
            System.out.println("Der opstod en fejl under læsning af bestillinger fra fil: " + e.getMessage());
        }
    }

    // Metode til at gemme bestillinger til en fil
    public void saveBestillingerToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) { // Append-mode
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            for (Bestilling bestilling : bestillinger) {
                StringBuilder sb = new StringBuilder();
                sb.append("Kunde: ").append(bestilling.getKundeNavn()).append("; ");
                sb.append("Pizzaer: ");
                double totalPris = 0;
                for (Pizza pizza : bestilling.getPizza()) {
                    sb.append(pizza.getNavn()).append(" - ").append(pizza.getIngredienser()).append(" - ").append(pizza.getPris()).append(" kr; ");
                    totalPris += pizza.getPris();
                }
                sb.append("Afhentningstidspunkt: ").append(bestilling.getAfhentningstidspunkt().format(formatter)).append("; ");
                sb.append("Samlet pris: ").append(totalPris).append(" kr");
                writer.write(sb.toString());
                writer.newLine();
            }
            System.out.println("Bestillinger gemt til fil: " + filename);
        } catch (IOException e) {
            System.out.println("Der opstod en fejl under gemning af bestillinger til fil: " + e.getMessage());
        }
    }
}