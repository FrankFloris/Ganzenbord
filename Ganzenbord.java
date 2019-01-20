import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Ganzenbord {
	
	Random random = new Random();
	private static int bordpositie = 0;
	private static int ogen = 0;
	private static int spelers = 0;
	static ArrayList<Speler> speler = new ArrayList<>();
	static Scanner spelerScan = new Scanner(System.in);
	static String naaminput;
	
	public static void main(String[] args) {
		Ganzenbord spel = new Ganzenbord();
		spel.setupGame();
        for(Speler speler : speler) {
            System.out.println(speler.getNaam() + " speelt vandaag Ganzenbord!");
        }
        System.out.println("Lets Play!" + "\n" +
        		"Druk op 'g' om te gooien" + "\n");
        while (bordpositie != 63) {
        	for(Speler speler : speler) {
        		System.out.println("\n" + speler.getNaam() + " is aan de beurt");
        		bordpositie = speler.getBordPositie();
        		spel.maakKeuze();
        		
        		if (speler.getWachtRij() > 0) {
        			System.out.println("Helaas mocht je deze ronde niet spelen " + speler.getNaam() + "\n" + "Jouw beurt is voorbij en je geeft de dobbelsteen door");
        		}
        		else if ((bordpositie+ogen == 10) || (bordpositie+ogen == 20) || (bordpositie+ogen == 30) || (bordpositie+ogen == 40) || (bordpositie+ogen == 50) || (bordpositie+ogen == 60) ) {
        			System.out.println("Wow! " + speler.getNaam() + " gaat naar vakje " + (bordpositie+ogen) + " en zet nogmaals zoveel stapjes als net gegooid!" + "\n" + speler.getNaam() + " gaat helemaal naar vakje "+ (bordpositie+ogen+ogen) + "!");
        			bordpositie += ogen+ogen;
        		}
        		else if (bordpositie+ogen == 23) {
        			System.out.println("Helaas, " + speler.getNaam() + " is op vakje 23 in de gevangenis beland." + "\n" +  "GAME OVER " + speler.getNaam());
        			speler.setWachtRij(1000);
//        			System.exit(1);
        		}
        		else if (bordpositie+ogen == 63) {
        			System.out.println("GEFELICITEERD!!!! Je hebt gewonnen " + speler.getNaam() + "!!!!");
        			System.exit(1);
        		}
        		else if (bordpositie+ogen > 63) {
        			bordpositie += ogen;
        			int strafStapjes = (bordpositie - 63);
        			System.out.println(speler.getNaam() + ", je hebt teveel gegooid. Voor straf moet je vanaf de '63' " + strafStapjes + " stapje(s) terug doen");
        			bordpositie = (63 - strafStapjes);
        			System.out.println(speler.getNaam() + " staat nu op vakje " + bordpositie);
        		}
        		else if ((bordpositie+ogen == 25) || (bordpositie+ogen == 45)) {
        			bordpositie += ogen;
        			System.out.println("Je staat nu op vakje " + bordpositie);
        			bordpositie = 0;
        			System.out.println("Helaas, " + speler.getNaam() + ", je bent in de val gelopen. Ga terug naar start. Je staat weer op vakje " + bordpositie);
        		}
        		else {
        			bordpositie += ogen;
        			System.out.println(speler.getNaam() + " staat nu op vakje " + bordpositie);
        		}
        		speler.setBordPositie(bordpositie);
        }
        }

        
	}

	private void setupGame() {
		System.out.println("Welkom bij ganzenbord! Met hoeveel spelers wil je spelen? Vul minimaal 1 en maximaal 4 in.");
		selecteerSpelers();
		maakSpelers();
	}
	
	private static void maakSpelers() {
		Scanner spelerScan = new Scanner(System.in);
		switch (spelers) {
		case 4: 
			setSpelerVier();
		case 3: 
			setSpelerDrie();
		case 2: 
			setSpelerTwee();
		case 1: 
			setSpelerEen();
//			spelerScan.close();
		}
	}

	private static void setSpelerEen() {
		naaminput = spelerScan.nextLine();
		Speler speler1 = new Speler();
		speler1.setNaam(naaminput);
		speler1.setBordPositie(0);
		speler.add(speler1);
		System.out.println("Welkom speler " + speler1.getNaam() + ". Je staat op vakje " + speler1.getBordPositie());
	}
	
	private static void setSpelerTwee() {
		naaminput = spelerScan.nextLine();
		Speler speler2 = new Speler();
		speler2.setNaam(naaminput);
		speler2.setBordPositie(0);
		speler.add(speler2);
		System.out.println("Welkom speler " + speler2.getNaam());
	}
	
	private static void setSpelerDrie() {
		naaminput = spelerScan.nextLine();
		Speler speler3 = new Speler();
		speler3.setNaam(naaminput);
		speler3.setBordPositie(0);
		speler.add(speler3);
		System.out.println("Welkom speler " + speler3.getNaam());
	}
	
	private static void setSpelerVier() {
		naaminput = spelerScan.nextLine();
		Speler speler4 = new Speler();
		speler4.setNaam(naaminput);
		speler4.setBordPositie(0);
		speler.add(speler4);
		System.out.println("Welkom speler " + speler4.getNaam());
	}

	private void selecteerSpelers() {
		Scanner spelerSelect = new Scanner(System.in);
		if (spelerSelect.hasNextInt()) {
			int select = spelerSelect.nextInt();
			if (select > 0 && select < 5 ) {
				spelers = select;
				System.out.println("Je hebt gekozen voor " + spelers + " speler(s)");
				System.out.println("Vul nu de naam in voor iedere speler");
			}
			else {
				System.out.println("Kijk Sesamstraat, leer tellen!");
				selecteerSpelers();
			}
		}
		else {
			System.out.println("Dat is geen cijfer! Kies een nummer tussen de 1 en 4");
			selecteerSpelers();
		}
//		spelerSelect.close();
		
	}

	public void maakKeuze() {
		Scanner deScanner = new Scanner(System.in);
		String keuze = deScanner.nextLine();
		if (keuze.equals("g")) {
			gooiDobbelsteen();
		}
		else {
			System.out.println("Als je niet 'g' invuld kan je niet gooien, probeer het nog een keer.");
			maakKeuze();
		}
	}

	public void gooiDobbelsteen() {
		ogen = random.nextInt(6)+1;
		System.out.println("Je hebt " + ogen + " gegooid.");
	}
}


class Speler {
	private String Naam;
	private int bordPositie = 0;
	private int wachtRij = 0;
	void speler(String spelernaam, int positie){
		setNaam(spelernaam);
		setBordPositie(positie);
	}
	public String getNaam() {
		return Naam;
	}
	public void setNaam(String naam) {
		Naam = naam;
	}
	public int getBordPositie() {
		return bordPositie;
	}
	public void setBordPositie(int bordPositie) {
		this.bordPositie = bordPositie;
	}
	public int getWachtRij() {
		return wachtRij;
	}
	public void setWachtRij(int wachtRij) {
		this.wachtRij = wachtRij;
	}
}

