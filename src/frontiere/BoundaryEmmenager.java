package frontiere;

import controleur.ControlEmmenager;

public class BoundaryEmmenager {
	private ControlEmmenager controlEmmenager;

	public BoundaryEmmenager(ControlEmmenager controlEmmenager) {
		this.controlEmmenager = controlEmmenager;
	}

	public void emmenager(String nomVisiteur) {
		if (controlEmmenager.isHabitant(nomVisiteur)) {
			System.out.println("Mais vous êtes déjà un habitant du village !");
		} else {
			StringBuilder question = new StringBuilder();
			question.append("Êtes-vous :\n");
			question.append("1 - un druide.\n");
			question.append("2 - un gaulois.\n");
			int choixUtilisateur = -1;
			do {
				choixUtilisateur = Clavier.entrerEntier(question.toString());
				switch (choixUtilisateur) {
				case 1:
					emmenagerDruide(nomVisiteur);
					break;

				case 2:
					StringBuilder chaine = new StringBuilder();
					chaine.append("Bienvenue villageois ");
					chaine.append(nomVisiteur);
					chaine.append("\n");
					chaine.append("Quelle est votre force ? \n");

					int force = Clavier.entrerEntier(chaine.toString());
					controlEmmenager.ajouterGaulois(nomVisiteur, force);

					break;

				default:
					System.out.println("Vous devez choisir le chiffre 1 ou 2 !");
					break;
				}
			} while (choixUtilisateur != 1 && choixUtilisateur != 2);
		}
	}

	private void emmenagerDruide(String nomVisiteur) {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Bienvenue druide ");
		chaine.append(nomVisiteur);
		chaine.append("\n");
		chaine.append("Quelle est votre force ?\n");
		int force = Clavier.entrerEntier(chaine.toString());

		int effetPotionMax = 0;
		int effetPotionMin = 1;

		while (effetPotionMax < effetPotionMin) {
			chaine = new StringBuilder();
			chaine.append("Quelle est la force de potion la plus faible que vous produisez ? \n");
			effetPotionMin = Clavier.entrerEntier(chaine.toString());

			chaine = new StringBuilder();
			chaine.append("Quelle est la force de potion la plus forte que vous produisez ? \n");
			effetPotionMax = Clavier.entrerEntier(chaine.toString());

			if (effetPotionMax < effetPotionMin) {
				chaine = new StringBuilder();
				chaine.append("Attention Druide, vous vous êtes trompé entre le minimum et le maximum \n");
				System.out.print(chaine.toString());
			}
		}

		controlEmmenager.ajouterDruide(nomVisiteur, force, effetPotionMin, effetPotionMax);
	}
}
