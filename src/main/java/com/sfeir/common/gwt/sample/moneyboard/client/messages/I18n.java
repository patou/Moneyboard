package com.sfeir.common.gwt.sample.moneyboard.client.messages;

import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.LocalizableResource.Generate;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.safehtml.shared.SafeHtml;

/**
 * Interface pour la traduction des textes de l'application
 * 
 */
@DefaultLocale("fr")
@Generate(format={"com.google.gwt.i18n.rebind.format.PropertiesFormat"},locales={"en"})
public interface I18n extends Messages {
	@DefaultMessage("E-mail")
	String login();
	
	@DefaultMessage("Mot de passe")
	String password();
	
	@DefaultMessage("Veuillez vous connectez ou vous inscrire pour utiliser Moneyboard")
	String applicationAuthentificationInfo();
	
	@DefaultMessage("Annuler")
	String cancel();
	
	@DefaultMessage("Valider")
	String submit();
	
	@DefaultMessage("S''inscrire")
	String register();
	
	@DefaultMessage("Mot de passe oublié")
	String lostPassword();

	@DefaultMessage("Plus de critères")
	String moreCriteria();

	@DefaultMessage("OU")
	String or();

	@DefaultMessage("Recherche")
	String search();

	@DefaultMessage("N° de client ou de contrat :")
	String numeroClientLabel();
	@DefaultMessage("Nom :")
	String lastName();
	@DefaultMessage("Prénom :")
	String firstName();
	@DefaultMessage("Né(e) le :")
	String dateDeNaissanceLabel();
	@DefaultMessage("Code postal de naissance :")
	String codePostalNaissanceLabel();
	@DefaultMessage("Ville de naissance :")
	String villeNaissanceLabel();
	@DefaultMessage("Rue (voie) :")
	String rueLabel();
	@DefaultMessage("Code postal :")
	String codePostalLabel();
	@DefaultMessage("Ville :")
	String villeLabel();
	@DefaultMessage("Pays :")
	String paysLabel();
	
	@DefaultMessage("Téléphone :")
	String phone();
	
	@DefaultMessage("E-mail ou mot de passe incorrect.")
	String errorLoginOrPassword();
	
	@DefaultMessage("{0}<br/>{1} {2}")
	SafeHtml addressColumn(String rue, String codePostal, String ville);

	@DefaultMessage("Né(e) le {0} à {1}")
	SafeHtml naissanceColumn(String naissance, String naissanceVille);

	@DefaultMessage("Chargement en cours ...")
	String loadingText();
	
	@DefaultMessage("Sélectionnez une banque proche de chez vous, ou entrez votre code banque en dessous")
	String selectBank();
	@DefaultMessage("Entrez-votre RIB")
	String enterRib();
	@DefaultMessage("Entrez un nom pour votre compte bancaire (ie: Compte courant LCL)")
	String accountName();
	
	@DefaultMessage("Ajouter un autre compte")
	String createNewAccount();
	
	@DefaultMessage("Votre numéro RIB n''est pas valide, vérifiez ce que vous avez saisis")
	String ribNotValid();
	
	@DefaultMessage("Importer vos opérations banquaires")
	String importOperation();
	
	@DefaultMessage("Utilisateur actif :")
	String userActif();
	
	@DefaultMessage("Deconnexion")
	String logout();
	@DefaultMessage("Tableau de bord")
	String dashboard();
	@DefaultMessage("Transactions")
	String transactions();
	@DefaultMessage("Analyse")
	String analysis();
	
	@DefaultMessage("Montant du compte (actuellement) :")
	String currentAmount();
	
	@DefaultMessage("Filtrer entre ")
	String filterBeetween();
	
	@DefaultMessage("à")
	String a();
	
	@DefaultMessage("Selectionner tous les comptes")
	String selectAllAccount();
	
	@DefaultMessage("Importer un fichier de transaction au format QIF")
	String importFileDescription();
	
	@DefaultMessage("Vous n''avez pas de transactions pour ce compte.")
	String noTransactionsForThisAccount();
	
	@DefaultMessage("Entrez une note personnel")
	String enterPersonnalNote();
	
	@DefaultMessage("Recette")
	String recette();
	
	@DefaultMessage("Dépense")
	String depense();
	
	@DefaultMessage("Solde")
	String solde();
	
	@DefaultMessage("Evolution du solde")
	String soldeEvolution();
	
	@DefaultMessage("Synthèse")
	String synthese();
	
	@DefaultMessage("Mes comptes")
	String myAccount();
	
	//
	
	@DefaultMessage("Balance Recette/Dépense")
	String balanceRecetteDepense();
	
	@DefaultMessage("Répartition des dépense par catégories")
	String repartitionDepenseCategory();

	@DefaultMessage("Répartition des recette par catégories")
	String repartitionRecetteCategory();

	@DefaultMessage("Répartition géographique")
	String repartitionGeo();
}
