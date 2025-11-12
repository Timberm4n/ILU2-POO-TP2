package controleur;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur, Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}

	public boolean verifierIdentite(String nomVendeur) {
		boolean identite = controlVerifierIdentite.verifierIdentite(nomVendeur);
		return identite;
	}

	public String[] rechercherVendeursProduit(String produit) {
		String[] etatMarche = village.donnerEtatMarche();
		int nombreVendeurs = 0;

		for (int i = 0; i < etatMarche.length; i += 3) {
			if (etatMarche[i + 2].equals(produit)) {
				nombreVendeurs++;
			}
		}
		String[] nomsVendeurs = new String[nombreVendeurs];
		int index = 0;

		for (int i = 0; i < etatMarche.length; i += 3) {
			if (etatMarche[i + 2].equals(produit)) {
				nomsVendeurs[index] = etatMarche[i];
				index++;
			}
		}
		return nomsVendeurs;
	}

	public Gaulois[] getVendeurProduit(String produit) {
		Gaulois[] vendeur = village.rechercherVendeursProduit(produit);
		return vendeur;
	}

	public int acheterProduit(String nomVendeur, int quantite) {
		Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(nomVendeur);
		if (etal != null) {
			return etal.acheterProduit(quantite);
		} else {
			return 0;
		}
	}
}