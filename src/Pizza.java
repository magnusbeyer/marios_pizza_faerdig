// Klasse til at repræsentere en pizza
public class Pizza {
    private String navn; // Navn på pizza
    private String ingredienser; // Ingrendienser på pizzaen
    private double pris; // Prisen på pizzaen
    private int nummer;  // Nummer på pizzaen

    // Default konstruktør
    public Pizza()
    {}

    // Konstruktør til at initialisere en pizza med navn, ingrendienser og pris
    public Pizza(String etNavn, String flereIngredienser, double enPris) {
        navn = etNavn;
        ingredienser = flereIngredienser;
        pris = enPris;
    }

    // Setter-metode til at sætte nummeret på pizzaen
    public void setNummer(int etNummer) {
        nummer = etNummer;
    }

    // Getter-metode til at hente navnet på pizzaen
    public String getNavn() {
        return navn;
    }

    // Getter-metode til at hente ingredienserne på pizzaen
    public String getIngredienser() {
        return ingredienser;
    }

    // Getter-metode til at hente prisen på pizzaen
    public double getPris() {
        return pris;
    }

    // Override toString-metoden for at give en tekstrepræsentation af pizzaen
    @Override
    public String toString() {
        return nummer + " - " + navn + " - " + ingredienser + " - " + pris + " kr";
    }
}