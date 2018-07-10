package fr.eql.autom.dictionnaire;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import fr.eql.autom.dictionnaire.exceptions.CategorieNonSupporteeException;
import fr.eql.autom.dictionnaire.exceptions.ProprieteDupliqueeException;
import fr.eql.autom.dictionnaire.exceptions.ProprieteObligatoireIndefinieException;
import fr.eql.autom.modele.proprietes.Categorie;
import fr.eql.autom.modele.proprietes.Genre;
import fr.eql.autom.modele.proprietes.IPropriete;

public class DictionnaireTest {

	@Test
	public void tpJava() throws CategorieNonSupporteeException {
		
		String identite = "Moucher";
		Categorie categorie = Categorie.VERBE;
		Dictionnaire dict = new Dictionnaire();
		boolean resultat = dict.ajouterEntree(identite, categorie);
		assertEquals("Message Résultat Test", true, resultat);
	}
	
	
	@Test(expected=Exception.class, timeout=1000)
	public void tpJava1() throws CategorieNonSupporteeException {
		
		String identite = "DUBUISSON";
		Categorie categorie = Categorie.NOM;
		Dictionnaire dict = new Dictionnaire();
		boolean resultat = dict.ajouterEntree(identite, categorie);
		assertNotEquals("Retour d'exception", true, resultat);
	}
	
	@Test
	public void tpJava2() throws CategorieNonSupporteeException {
		
		String identite1 ="Dejavue";
		Categorie categorie1 = Categorie.ADJ;
		String identite2 ="Dejavue";
		Categorie categorie2 = Categorie.ADJ;
		Dictionnaire dict = new Dictionnaire();
		dict.ajouterEntree(identite1, categorie1);
		boolean resultat = dict.ajouterEntree(identite2, categorie2);
		assertNotEquals("Retour sur double entrée", true, resultat);
		
	}
		
	
	@Test
	public void tpJava3() {
		
		String identite ="Mot sans genre";
		Categorie categorie = Categorie.VERBE;
		Genre genre = null;
		Dictionnaire dict = new Dictionnaire();
		boolean resultat = dict.ajouterEntree(identite, categorie, genre);
		assertEquals("entrée sans genre", true, resultat);
	}
	
	@Test
	public void tpJava4() {
		
		String identite ="Nominale avec genre";
		Categorie categorie = Categorie.NOM;
		Genre genre = Genre.F;
		Dictionnaire dict = new Dictionnaire();
		boolean resultat = dict.ajouterEntree(identite, categorie, genre);
		assertEquals("entrée avec genre", true, resultat);
	}
	
	@Test
	public void tpJava5() {
		
		String identite1 ="doublette";
		Categorie categorie1 = Categorie.NOM;
		Genre genre1 = Genre.F;
		String identite2 ="doublette";
		Categorie categorie2 = Categorie.NOM;
		Genre genre2 = Genre.F;
		Dictionnaire dict = new Dictionnaire();
		dict.ajouterEntree(identite1, categorie1, genre1);
		boolean resultat = dict.ajouterEntree(identite2, categorie2, genre2);
		assertNotEquals("doublette sur id, cat et genre", true, resultat);
	}
	
	@Test
	public void tpJava6()  throws ProprieteDupliqueeException, ProprieteObligatoireIndefinieException {
		
		Dictionnaire dict = new Dictionnaire();
		String identite ="Avec des propertizzzz";
		Categorie cat = Categorie.VERBE;
		Set<IPropriete> properties = new HashSet<IPropriete>();
		properties.add(cat);
		boolean resultat = dict.ajouterEntree(identite, properties);
		assertEquals("message", true, resultat);
	}
		//pas terminé



// CORRECTION CORRECTION CORRECTION CORRECTION CORRECTION CORRECTION CORRECTION CORRECTION CORRECTION CORRECTION

	@Test
	public void question1() throws CategorieNonSupporteeException {
		
		Dictionnaire dico = new Dictionnaire();
		dico.ajouterEntree("lire", Categorie.VERBE);
		boolean resultat = dico.ajouterEntree("lire", Categorie.VERBE);
		
		assertTrue("entrée créée", resultat); //ou assertEquals("entrée créée", true, resultat);
		
		
		// BONUS pour vérifier si l'on a bien mit une entrée dans la "MAP" (voir les entrées)
		// --> assertEquals("entrée bien créée dans la map", 1, dico.entrées.size());
		// pour lire la map on ajoutera : Entree entree = dico.entrees.get("lire");
		// String identite = entree.getIdentite();
		// String texteDeLaCategorie = entree.getCategorie().getValeur();
		//assertEquals("entree creee avec la bonne identite", true, identite);
		//assertEquals("entree creee avec la bonne categorie", "VERBE", texteDeLaCategorie);
	}
	
	
	@Test(expected = CategorieNonSupporteeException.class)
	public void question2() throws CategorieNonSupporteeException {
		
		Dictionnaire dico = new Dictionnaire();
		dico.ajouterEntree("voiture", Categorie.NOM);	 
	}
	
	@Test 
	public void question3() throws CategorieNonSupporteeException {
		
		Dictionnaire dico = new Dictionnaire();
		boolean resultat = dico.ajouterEntree("lire", Categorie.VERBE);
		assertEquals("entrée créee pour la premiere fois", true, resultat);
		
		boolean resultat2 = dico.ajouterEntree("lire", Categorie.VERBE);
		assertEquals("entrée créée pour le seconde fois", false, resultat2);
	}

	
	@Test
	public void question4() {
		Dictionnaire dico = new Dictionnaire();
		
		boolean resultat = dico.ajouterEntree("lire", Categorie.VERBE, Genre.M);
		assertEquals("entrée créée", true, resultat);
	}
	
	@Test
	public void question5() {
		Dictionnaire dico= new Dictionnaire();
		
		boolean resultat = dico.ajouterEntree("voiture", Categorie.NOM, Genre.F);
		assertEquals("entrée créee", true, resultat);
		
		//bonus assertEquals("entrée effectivement dans la map", 1, dico.entrees.size(1));
		//Entree entree = dico.entrees.get("voiture");
		//EntreeNominale entreeNominale = (EntreeNominale)entree; --> CASTER : Traiter une entrée comme une entrée nominale (voir fichier des entrées)
		//String identite = entreeNominale.getIDentite();
		//String texteDeLaCategorie = entreeNominale.getCategorie().getValeur();
		//String genre = entreeNominale.getGenre().getValeur();
	}
}