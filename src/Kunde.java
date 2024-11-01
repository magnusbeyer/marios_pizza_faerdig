// Vi nåede ikke at implementere vores kunde-klasse i vores projektopgave, men vi ville have brugt kunde-klassen
// - til at kunne bruges til at repræsentere kunder i et system, hvor vi ville have brug for at gemme og håndtere kundedata.

public class Kunde
{
    // Attributter
    private String navn; // Kundens navn
    private String telefonNummer; // Kunden telefonnummer
    private double rabat; // Kundens rabat

    // Default konstruktør
    public Kunde()
    {}

    // Defualt konstruktør til at initialisere en kunde med navn, telefonnummer og rabat
    public Kunde(String etNavn, String etTelefonNummer, double enRabat)
    {
        navn = etNavn;
        telefonNummer = etTelefonNummer;
        rabat = enRabat;
    }

    // Set-metode til attributten navn
    public void setNavn(String etNavn)
    {
        navn = etNavn;
    }

    // Get-metode til atrributten navn
    public String getNavn()
    {
        return navn;
    }

    // Set-metode til attributten telefonNummer
    public void setTelefonNummer(String etTelefonNummer)
    {
        telefonNummer = etTelefonNummer;
    }

    // Get-metode til attributten telefonNummer
    public String getTelefonNummer()
    {
        return telefonNummer;
    }

    // Set-metode til attributten rabat
    public void setRabat(double enRabat)
    {
        rabat = enRabat;
    }

    // Get-metode til attributten rabat
    public double getRabat()
    {
        return rabat;
    }
}