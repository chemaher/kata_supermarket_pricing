package supermarket.pricing.kata;

import java.math.BigDecimal;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import supermarket.pricing.kata.domaine.Produit;
import supermarket.pricing.kata.tarification.Tarification;
import supermarket.pricing.kata.tarification.TarificationRemiseSurPrix;
import supermarket.pricing.kata.tarification.TarificationRemiseSurQuantite;
import supermarket.pricing.kata.tarification.TarificationSimple;
import supermarket.pricing.kata.tarification.TarificationUnitaire;

public class ProduitAggregateur implements ArgumentsAggregator {

	@Override
	public Produit aggregateArguments(final ArgumentsAccessor arguments, final ParameterContext context) {

		final Produit produit = new Produit();
		produit.setId(arguments.getLong(0));
		produit.setNom(arguments.getString(1));
		produit.setPrix(new BigDecimal(arguments.getLong(2)));
		produit.setUnite(arguments.getLong(3));
		produit.setDevise(arguments.getString(7));

		final String typeTarification = arguments.getString(4);

		switch (typeTarification) {
		case "SIMPLE":
			final Tarification tarificationSimple = new TarificationSimple();
			produit.setTarification(tarificationSimple);
			break;
		case "PAR_UNITE":
			final Tarification tarificationParUnite = new TarificationUnitaire();
			produit.setTarification(tarificationParUnite);
			break;
		case "REMISE_SUR_QUANTITE":
			final Tarification tarificationParQuantite = new TarificationRemiseSurQuantite();
			produit.setTarification(tarificationParQuantite);
			break;
		case "REMISE_SUR_PRIX":
			final Tarification tarificationParPrix = new TarificationRemiseSurPrix();
			produit.setTarification(tarificationParPrix);
			break;
		default:
			break;
		}
		return produit;
	}

}
