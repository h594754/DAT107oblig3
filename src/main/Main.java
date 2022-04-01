package main;

import java.time.LocalDate;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import dao.AnsattDAO;
import dao.AvdelingDAO;
import dao.ProsjektDAO;
import dao.ProsjektdeltakelseDAO;
import entity.Ansatt;
import entity.Avdeling;

public class Main {

	public static void main(String[] args) {
		boolean programferdig = false;

		while (!programferdig) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Meny: ");
			System.out.println("Trykk 0 for � avslutte");
			System.out.println("Trykk 1 for � s�ke etter en ansatt med brukernavn eller ID");
			System.out.println("Trykk 2 for � skrive ut alle ansatte");
			System.out.println("Trykk 3 for � oppdatere en ansatt");
			System.out.println("Trykk 4 for � legge til en ansatt");
			System.out.println("Trykk 5 for � s�ke etter en avdeling etter ID");
			System.out.println("Trykk 6 for � skrive ut alle p� en avdeling");
			System.out.println("Trykk 7 for � legge til en ny avdeling");
			System.out.println("Trykk 8 for � legge til et nytt prosjekt");
			System.out.println("Trykk 9 for � registrere prosjektdeltakelse");
			System.out.println("Trykk 10 for � f� utskrift med info om prosjektet");
			System.out.println("Trykk 11 for � f� utskrift med info om prosjektdeltakelse");

			switch (sc.nextLine()) {

			case "0": {
				// 0 for � avslutte
				System.out.println("Velkommen tilbake ved en senere anledning!");
				programferdig = true;
				break;

			}

			case "1": {
				// 1 for � s�ke etter en ansatt med ID eller brukernavn
				System.out.println("S�k opp en ansatt med ID eller brukernavn: ");
				Ansatt funnet = finnAnsatt(sc);
				if (funnet == null) {
					System.out.println("Den ansatte finnes ikke i v�rt system. Returnerer null.");
				}
				System.out.println(funnet);
				break;
			}

			case "2": {
				// 2 for � liste ut alle ansatte
				AnsattDAO ansatt = new AnsattDAO();
				ansatt.listAlleAnsatte();
				break;
			}

			case "3": {
				// 3 for � oppdatere en ansatt
				System.out.println("S�k opp ansatt p� id eller brukernavn");
				AnsattDAO ansatt = new AnsattDAO();
				Ansatt funnet = finnAnsatt(sc);
				if (funnet == null) {
					System.out.println("Den ansatte finnes ikke i v�rt system. Returnerer null.");
					break;
				}

				System.out.println("Hva �nsker du � gj�re med den ansatte?");
				System.out.println("Trykk 1 for � endre l�nn: ");
				System.out.println("Trykk 2 for � endre stilling: ");
				System.out.println("Trykk 3 for � endre b�de l�nn og stilling: ");
				System.out.println("Trykk 4 for � endre avdeling: ");

				switch (sc.nextLine()) {
				case "1": {
					System.out.println("Oppgi m�nedsl�nn");
					funnet.setManedslonn(Integer.valueOf(sc.nextLine()));
					ansatt.oppdaterAnsatt(funnet);
					break;
				}

				case "2": {
					System.out.println("Oppgi en stilling");
					funnet.setStilling(sc.nextLine());
					ansatt.oppdaterAnsatt(funnet);
				}

				case "3": {
					System.out.println("Oppgi en m�nedsl�nn");
					funnet.setManedslonn(Integer.valueOf(sc.nextLine()));
					System.out.println("Oppgi en stilling");
					funnet.setStilling(sc.nextLine());
					ansatt.oppdaterAnsatt(funnet);
					break;
				}

				case "4": {

					if (!ansattErSjef(funnet)) {
						System.out.println("Oppgi en avdelingsid:");
						int nyAvdeling = Integer.valueOf(sc.nextLine());

						oppdaterAvdelingAnsatt(funnet, nyAvdeling);
					} else {
						System.out.println(
								"Kan ikke oppdatere avdeling fordi den ansatte du s�kte opp er sjef hos en annen avdeling");
					}
					break;
				}
				}
				break;
			}

			case "4": {
				// Legge til en ny ansatt
				// ID er en serial s� den blir oppdatert automatisk

				System.out.println("Oppgi et brukernavn p� maks 4 bokstaver: ");
				String brukernavn = sc.nextLine();
				System.out.println("Oppgi et fornavn: ");
				String fornavn = sc.nextLine();
				System.out.println("Oppgi et etternavn: ");
				String etternavn = sc.nextLine();
				System.out.println("Oppgi en ansettelsesdato i formen YYYY-MM-DD");
				LocalDate ansattDato = LocalDate.parse(sc.nextLine());
				System.out.println("Oppgi en stilling: ");
				String stilling = sc.nextLine();
				System.out.println("Oppgi en m�nedsl�nn:");
				int mndlonn = Integer.valueOf(sc.nextLine());
				System.out.println("Oppi en avdeling: ");
				int avdeling = Integer.valueOf(sc.nextLine());

				AnsattDAO ansatt = new AnsattDAO();

				ansatt.leggTilAnsatt(brukernavn, fornavn, etternavn, ansattDato, stilling, mndlonn, avdeling);
				ansatt.listAlleAnsatte();
				break;
			}

			case "5": {
				// S�k etter en avdeling p� id
				System.out.println("Oppgi en avdelingsid: ");
				Avdeling funnet = finnAvdeling(sc);

				if (funnet == null) {
					System.out.println("Fant ikke den ansatte");
				}

				System.out.println(funnet);
				break;
			}

			case "6": {
				// Skriv ut alle hos en avdeling
				System.out.println("Oppgi en avdelingsid: ");
				skrivAlleAvdeling(sc);
				break;
			}
			
			case "7": {
				// Legge til en ny avdeling
				
				System.out.println("Oppgi id eller brukernavn p� sjef i ny avdeling");
				Ansatt funnet = finnAnsatt(sc);
				
				if(funnet == null) {
					System.out.println("Fant ikke den ansatte");
					break; 
				}
				
				if(!ansattErSjef(funnet)) {
					System.out.println("Oppgi navn p� ny avdeling: ");
					String avdelingsNavn = sc.nextLine();
					leggTilAvdeling(avdelingsNavn, funnet.getAnsattId());
				}
				else {
					System.out.println("Avdelingen kunne ikke oppdateres, pr�v igjen.");
				}
				break;
			}
			case"8" : {
				System.out.println("Oppgi �nsket navn p� prosjektet: ");
				String prosjektnavn = sc.nextLine();
				System.out.println("Oppgi en kort beskrivelse av prosjektet");
				String prosjektbeskrivelse = sc.nextLine();
				
				ProsjektDAO p = new ProsjektDAO();
				p.leggTilProsjekt(prosjektnavn, prosjektbeskrivelse);
				
				break;
			}
			
			case"9": {
				System.out.println("Oppgi ansattden din");
				int ansattid = Integer.valueOf(sc.nextLine());
				System.out.println("Oppgi id til prosjekt du har deltatt i");
				int prosjektid = Integer.valueOf(sc.nextLine());
				System.out.println("Oppgi antall timer du har jobbet p� prosjektet i hele timer");
				int antalltimer = Integer.valueOf(sc.nextLine());
				System.out.println("Oppgi rolle du hadde i prosjektet");
				String rolle = sc.nextLine();
				
				ProsjektdeltakelseDAO prod = new ProsjektdeltakelseDAO();
				prod.registrereProsjektdeltakelse(null, null, antalltimer, rolle);
				
				break;
			}	
			case"10": {
				ProsjektDAO p = new ProsjektDAO(); 
				p.listAlleProsjekt();
				break;
			}
			
			case"11": {
				ProsjektdeltakelseDAO pdel = new ProsjektdeltakelseDAO();
				pdel.listProsjektdeltakelse();
				break;
			}
		}
	}
}

	public static void leggTilAvdeling(String navn, int id) {
		try {
			AnsattDAO ansatt = new AnsattDAO(); 
			Ansatt a = ansatt.finnAnsattMedId(id);
			if(!ansattErSjef(a) && a != null) {
				Avdeling avd = new Avdeling(navn, id);
				AvdelingDAO avdeling = new AvdelingDAO(); 
				avdeling.leggTilAvdeling(avd);
				
				a.setStilling("Sjef");
				ansatt.oppdaterAnsatt(a);
				
				a.setAvdeling(avd.getAvdelingId());
				avdeling.oppdaterAvdelingAnsatt(a);
				
				System.out.println("Ansatt " + a.getAnsattId() + ", " + a.getBrukernavn() + " har blitt oppgit som sjef i den nye avdelingen" + 
				navn);
			} 
			
		}catch (NullPointerException e) {
				e.printStackTrace();		
		}
		
	}

	private static void skrivAlleAvdeling(Scanner sc) {
		AvdelingDAO avdeling = new AvdelingDAO();
		String avdelingNavn = finnAvdeling(sc).getAvdelingsnavn();

		List<Ansatt> ansatte = avdeling.alleAnsatteAvdeling(avdelingNavn);
		Iterator<Ansatt> iter = ansatte.iterator();

		while (iter.hasNext()) {
			Ansatt a = iter.next();
			if (ansattErSjef(a)) {
				System.out.println("*" + a + "*");

			} else {
				System.out.println(a);
			}
		}

	}

	public static Avdeling finnAvdeling(Scanner sc) {
		String input = sc.nextLine();

		AvdelingDAO avdeling = new AvdelingDAO();
		int id = Integer.parseInt(input);
		return avdeling.finnAvdelingMedID(id);
	}

	public static Avdeling finnAvdeling(int sc) {
		AvdelingDAO avd = new AvdelingDAO();
		return avd.finnAvdelingMedID(sc);
	}

	public static void oppdaterAvdelingAnsatt(Ansatt a, int nyAvdeling) {
		if (!ansattErSjef(a)) {

			int avdelingsId = finnAvdeling(nyAvdeling).getAvdelingId();

			a.setAvdeling(avdelingsId);
			AvdelingDAO avd = new AvdelingDAO();

			avd.oppdaterAvdelingAnsatt(a);
			System.out.println("Ansatt med id" + a.getAnsattId() + ", " + a.getBrukernavn()
					+ "har f�tt et nytt avdelingsnummer: " + avd.finnAvdelingMedID(nyAvdeling));
		} else {
			System.out.println("Dette kunne ikke utf�res fordi brukeren du s�kte opp er sjef hos en annen avdeling");
		}

	}

	private static boolean ansattErSjef(Ansatt a) {
		boolean finnSjef = false;
		AvdelingDAO avdeling = new AvdelingDAO();

		Avdeling avd = avdeling.finnAvdelingMedID(a.getAvdeling());

		if (avd.getAvdelingssjef() == a.getAnsattId() && avd != null) {
			finnSjef = true;
		}

		return finnSjef;
	}

	public static Ansatt finnAnsatt(Scanner sc) {
		String input = sc.nextLine();
		AnsattDAO ansatt = new AnsattDAO();

		try {
			int id = Integer.parseInt(input);
			return ansatt.finnAnsattMedId(id);
		} catch (NumberFormatException e) {
			return ansatt.finnAnsattMedBrukernavn(input);
		}
	}
	
	
}
