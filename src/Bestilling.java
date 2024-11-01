import java.time.LocalDateTime;
import java.util.List;

// Klasse til at repræsentere en bestilling
public class Bestilling {
    private final List<Pizza> pizza; // Liste overn pizzaer i bestillingen
    private final LocalDateTime afhentningstidspunkt; // Tidspunkt for afhentning
    private final String kundeNavn; // Kundens navn

    // // Konstruktør til at initialisere en bestilling med pizzaer, afhentningstidspunkt og kundens navn
    public Bestilling(List<Pizza> flerePizzaer, LocalDateTime etAfhentningstidspunkt, String etKundeNavn) {
        pizza = flerePizzaer;
        afhentningstidspunkt = etAfhentningstidspunkt;
        kundeNavn = etKundeNavn;
        System.out.println("Bestilling oprettet med afhentningstidspunkt: " + afhentningstidspunkt);
    }
    // Getter-metode til at hente liste over pizzaer
    public List<Pizza> getPizza() {
        return pizza;
    }

    // Getter-metode til at hente afhentningstidspunkt
    public LocalDateTime getAfhentningstidspunkt() {
        return afhentningstidspunkt;
    }

    // Getter-metode til at hente kundens navn
    public String getKundeNavn() {
        return kundeNavn;
    }

    // Beregn samlet pris for bestillingen
    public double getTotalPris() {
        double total = 0;
        for (Pizza pizza : pizza) {
            total += pizza.getPris(); // Tilføjer prisen for hver pizza-totalen
        }
        return total;
    }

    // Override toString-metoden for at give en tekstrepræsentation af bestillingen
    @Override
    public String toString() {
        return "Kunde: " + kundeNavn + ", Pizzaer: " + pizza + ", Afhentning: " + afhentningstidspunkt + ", Samlet pris: " + getTotalPris() + " kr";
    }
}