package supermarket.pricing.kata.tarification;

import java.math.BigDecimal;

import lombok.NonNull;

/**
 * Classe qui modélise la tarification des produits ayant une remise sur le prix
 *
 * @author Maher AMRI
 *
 */
public class TarificationRemiseSurPrix implements Tarification {

	/**
	 * quantité de produit soumise à la remise
	 */
	private Integer quantiteEnRemise;

	/**
	 * montant de la remise
	 */
	private BigDecimal prixRemise;

	public TarificationRemiseSurPrix(final @NonNull Integer quantiteEnRemise, final @NonNull BigDecimal prixRemise) {
		this.quantiteEnRemise = quantiteEnRemise;
		this.prixRemise = prixRemise;
	}

	/**
	 * @see supermarket.pricing.kata.tarification.Tarification#calculerTarification(java.lang.Integer,
	 *      java.math.BigDecimal)
	 */
	@Override
	public BigDecimal calculerTarification(final @NonNull Integer quantite, final @NonNull BigDecimal prix) {
		return calculerRemise(quantite)
				.add(prix.multiply(new BigDecimal(quantite).remainder(new BigDecimal(quantiteEnRemise))));
	}

	/**
	 * Calculer le montant de remise pour le client
	 *
	 * @param quantite
	 *            {@link Integer} la quantité de produits sélécionnés
	 * @return {@link BigDecimal} le montant de la remise
	 */
	private BigDecimal calculerRemise(final Integer quantite) {
		return quantite < quantiteEnRemise ? BigDecimal.ZERO
				: prixRemise.multiply(new BigDecimal(quantite).divideToIntegralValue(new BigDecimal(quantiteEnRemise)));

	}

}
