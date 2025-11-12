package frontiere;

import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	private String questionDeVente(String[] nomVendeursPotentiels) {
		StringBuilder questionVendeur = new StringBuilder("Chez quel commerçant voulez-vous acheter des fleurs ?\n");
		for (int i = 0; i < nomVendeursPotentiels.length; i++) {
			questionVendeur.append((i + 1));
			questionVendeur.append(" - ");
			questionVendeur.append(nomVendeursPotentiels[i] + "\n");
		}
		System.out.print(questionVendeur.toString());

		int choixVendeur = -1;

		do {
			choixVendeur = Clavier.entrerEntier(questionVendeur.toString());
			if (choixVendeur < 1 || choixVendeur > nomVendeursPotentiels.length) {
				System.out.println("Veuillez choisir un des vendeurs proposés.");
			}
		} while (choixVendeur < 1 || choixVendeur > nomVendeursPotentiels.length);

		String nomVendeur = nomVendeursPotentiels[choixVendeur - 1];

		return nomVendeur;
	}

	private void effectuerTransaction(String nomAcheteur, String produit, String nomVendeur) {
		System.out.println(nomAcheteur + " se déplace jusqu'à l'étal du vendeur " + nomVendeur);
		System.out.println("Bonjour " + nomAcheteur);
		int nbProduit = Clavier.entrerEntier("Combien de " + produit + " voulez-vous acheter ?");
		int nbVendu = controlAcheterProduit.acheterProduit(nomVendeur, nbProduit);

		if (nbVendu == 0) {
			System.out.println(
					nomAcheteur + " veut acheter " + nbProduit + " " + produit + " malheureusement il n'y en a plus");
		} else if (nbVendu < nbProduit) {
			System.out.println(nomAcheteur + " veut acheter " + nbProduit + " " + produit + " malheureusement "
					+ nomVendeur + " n'en a plus que " + nbVendu + ". " + nomAcheteur + " achète tout le stock de "
					+ nomVendeur);
		} else {
			System.out.println(nomAcheteur + " achète " + nbProduit + " " + produit + " à " + nomVendeur);
		}
	}

	public void acheterProduit(String nomAcheteur) {
		boolean nomConnu = controlAcheterProduit.verifierIdentite(nomAcheteur);

		if (!nomConnu) {
			System.out.println("Je suis désolé " + nomAcheteur
					+ " mais il faut être un habitant de notre village pour commercer ici.");
			return;
		}

		String produit = Clavier.entrerChaine("Quel produit voulez-vous acheter ?");
		String[] nomVendeursPotentiels = controlAcheterProduit.rechercherVendeursProduit(produit);

		if (nomVendeursPotentiels.length == 0) {
			System.out.println("Désolé personne ne vend ce produit au marché\n");
			return;
		}

		String nomVendeur = questionDeVente(nomVendeursPotentiels);

		effectuerTransaction(nomAcheteur, produit, nomVendeur);

	}
}