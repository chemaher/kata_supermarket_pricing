package supermarket.pricing.kata.tarification;

import java.math.BigDecimal;

import lombok.NonNull;

/**
 * Classe qui modélise la tarification des produits ayant une remise sur la
 * quantité achetée
 * 
 * @author Maher AMRI
 *
 */
public class TarificationRemiseSurQuantite implements Tarification {

	/**
	 * quantité de produit en promotion
	 */
	private int quantiteAchete;
	/**
	 * quantité à payer
	 */
	private int quantiteAPayer;

	public TarificationRemiseSurQuantite(final @NonNull Integer quantiteAchete,
			final @NonNull Integer quantiteEnRemise) {
		this.quantiteAchete = quantiteAchete;
		this.quantiteAPayer = quantiteEnRemise;
	}

	/**
	 * @see supermarket.pricing.kata.tarification.Tarification#calculerTarification(java.lang.Integer,
	 *      java.math.BigDecimal)
	 */
	@Override
	public BigDecimal calculerTarification(final Integer quantite, final BigDecimal prix) {
		return quantite < quantiteAchete ? prix.multiply(new BigDecimal(quantite))
				: prix.multiply(new BigDecimal(quantiteAPayer))
						.multiply(new BigDecimal(quantite).divideToIntegralValue(new BigDecimal(quantiteAchete)));
	}

}
