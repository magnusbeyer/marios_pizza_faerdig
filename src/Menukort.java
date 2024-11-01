import java.util.ArrayList;
import java.util.List;

// Klasse til at repræsentere et menukort med pizzaer
public class Menukort {
    private List<Pizza> pizzaer; // Liste over pizzaer på menukortet
    private int nextNummer = 1;  // Tæller til næste pizza-nummer

    // Konstruktør til at initialisere menukortet
    public Menukort() {
        pizzaer = new ArrayList<>();
    }

    // Metode til at tilføje en pizza til menukortet
    public void addPizza(Pizza pizza) {
        pizza.setNummer(nextNummer);  // Tildel nummer til pizzaen
        pizzaer.add(pizza); // Tilføj pizzaen til listen
        nextNummer++;  // Øg nummer-tælleren, så næste pizza får nyt nummer
    }

    // Metode til at udskrive menukortet
    public void printMenu() {
        System.out.println("Menukort:");
        for (Pizza pizza : pizzaer) {
            System.out.println(pizza); // Udskriver hver pizza ved hjælp af toString-metoden i Pizza-klassen
        }
    }

    // Metode til at hente en pizza fra menukortet baseret på indeks
    public Pizza getPizza(int i) {
        return pizzaer.get(i);
    }
}