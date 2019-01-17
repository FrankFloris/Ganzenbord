import java.util.Random;
import java.util.Scanner;

public class Ganzenbord {
	
	Random random = new Random();
	private int bordpositie = 0;
	private int ogen = 0;
	
	public static void main(String[] args) {
		Ganzenbord spel = new Ganzenbord();
		spel.run();

		
	}

	private void run() {
		System.out.println("Welkom bij ganzenbord speler, je staat momenteel aan start. voer een g in om met de dobbelsteen te gooien.");
		maakKeuze();
		
	}
	
	public void maakKeuze() {
		Scanner deScanner = new Scanner(System.in);
		String keuze = deScanner.nextLine();
		if (keuze.equals("g")) {
			gooiDobbelsteen();
			zetStapjes();
		}
		else {
			System.out.println("Als je niet 'g' invuld kan je niet gooien, probeer het nog een keer.");
			maakKeuze();
		}
	}
	
	private void zetStapjes() {
		checkVakje();
		while (bordpositie < 63) {
		maakKeuze();
		}

	}

	private void checkVakje() {
		if ((bordpositie+ogen == 10) || (bordpositie+ogen == 20) || (bordpositie+ogen == 30) || (bordpositie+ogen == 40) || (bordpositie+ogen == 50) || (bordpositie+ogen == 60) ) {
			System.out.println("Wow! Je staat op vakje " + (bordpositie+ogen) + "! Je zet nogmaals zoveel stapjes als je net hebt gegooid!");
			bordpositie += ogen;
			zetStapjes();
		}
		else if (bordpositie+ogen == 23) {
			System.out.println("Helaas, je bent op vakje 23 in de gevangenis beland, ga niet naar start en ontvang geen 200 euro. GAME OVER");
			System.exit(1);
		}
		else if (bordpositie+ogen == 63) {
			System.out.println("GEFELICITEERD!!!! Je hebt gewonnen!");
			System.exit(1);
		}
		else if (bordpositie+ogen > 63) {
			bordpositie += ogen;
			int strafStapjes = (bordpositie - 63);
			System.out.println("Je hebt teveel gegooid. Voor straf moet je vanaf de '63' " + strafStapjes + " stapje(s) terug doen");
			bordpositie = (63 - strafStapjes);
			System.out.println("Je staat nu op vakje " + bordpositie);
		}
		else if ((bordpositie+ogen == 25) || (bordpositie+ogen == 45)) {
			bordpositie += ogen;
			System.out.println("Je staat nu op vakje " + bordpositie);
			bordpositie = 0;
			System.out.println("Helaas, je bent in de val gelopen. Ga terug naar start. Je staat weer op vakje " + bordpositie);
		}
		else {
			bordpositie += ogen;
			System.out.println("Je staat nu op vakje " + bordpositie);
		}
		
	}

	public void gooiDobbelsteen() {
		ogen = random.nextInt(6)+1;
		//bordpositie += ogen;
		System.out.println("Je hebt " + ogen + " gegooid.");
	}
	

}



//- begin bij start
//- Kijk waar de speler staat
//- geef de speler de optie om te gooien met g
//- generate random value 1-6
//- kijk waar de speler terecht komt
//- voer actie uit als de speler op een speciaal vakje komt
// - bordpositie hoger dan 63 moet terug