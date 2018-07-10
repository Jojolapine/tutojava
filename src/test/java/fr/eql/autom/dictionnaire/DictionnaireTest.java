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
		assertEquals("Message R�sultat Test", true, resultat);
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
		assertNotEquals("Retour sur double entr�e", true, resultat);
		
	}
		
	
	@Test
	public void tpJava3() {
		
		String identite ="Mot sans genre";
		Categorie categorie = Categorie.VERBE;
		Genre genre = null;
		Dictionnaire dict = new Dictionnaire();
		boolean resultat = dict.ajouterEntree(identite, categorie, genre);
		assertEquals("entr�e sans genre", true, resultat);
	}
	
	@Test
	public void tpJava4() {
		
		String identite ="Nominale avec genre";
		Categorie categorie = Categorie.NOM;
		Genre genre = Genre.F;
		Dictionnaire dict = new Dictionnaire();
		boolean resultat = dict.ajouterEntree(identite, categorie, genre);
		assertEquals("entr�e avec genre", true, resultat);
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
		//pas termin�



// CORRECTION CORRECTION CORRECTION CORRECTION CORRECTION CORRECTION CORRECTION CORRECTION CORRECTION CORRECTION

	@Test
	public void question1() throws CategorieNonSupporteeException {
		
		Dictionnaire dico = new Dictionnaire();
		dico.ajouterEntree("lire", Categorie.VERBE);
		boolean resultat = dico.ajouterEntree("lire", Categorie.VERBE);
		
		assertTrue("entr�e cr��e", resultat); //ou assertEquals("entr�e cr��e", true, resultat);
		
		
		// BONUS pour v�rifier si l'on a bien mit une entr�e dans la "MAP" (voir les entr�es)
		// --> assertEquals("entr�e bien cr��e dans la map", 1, dico.entr�es.size());
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
		assertEquals("entr�e cr�ee pour la premiere fois", true, resultat);
		
		boolean resultat2 = dico.ajouterEntree("lire", Categorie.VERBE);
		assertEquals("entr�e cr��e pour le seconde fois", false, resultat2);
	}

	
	@Test
	public void question4() {
		Dictionnaire dico = new Dictionnaire();
		
		boolean resultat = dico.ajouterEntree("lire", Categorie.VERBE, Genre.M);
		assertEquals("entr�e cr��e", true, resultat);
	}
	
	@Test
	public void question5() {
		Dictionnaire dico= new Dictionnaire();
		
		boolean resultat = dico.ajouterEntree("voiture", Categorie.NOM, Genre.F);
		assertEquals("entr�e cr�ee", true, resultat);
		
		//bonus assertEquals("entr�e effectivement dans la map", 1, dico.entrees.size(1));
		//Entree entree = dico.entrees.get("voiture");
		//EntreeNominale entreeNominale = (EntreeNominale)entree; --> CASTER : Traiter une entr�e comme une entr�e nominale (voir fichier des entr�es)
		//String identite = entreeNominale.getIDentite();
		//String texteDeLaCategorie = entreeNominale.getCategorie().getValeur();
		//String genre = entreeNominale.getGenre().getValeur();
	}
}