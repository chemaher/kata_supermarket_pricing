package supermarket.pricing.kata.tarification;

import java.math.BigDecimal;

import lombok.NonNull;

/**
 * Classe qui modélise la tarification des produits par unité
 *
 * @author Maher AMRI
 *
 */
public class TarificationUnitaire implements Tarification {

	private int unite;

	public TarificationUnitaire(final @NonNull Integer unite) {
		this.unite = unite;
	}

	/**
	 * @see supermarket.pricing.kata.tarification.Tarification#calculerTarification(java.lang.Integer,
	 *      java.math.BigDecimal)
	 */
	@Override
	public BigDecimal calculerTarification(final Integer quantite, final BigDecimal prix) {
		return new BigDecimal(quantite).divideToIntegralValue(new BigDecimal(unite)).multiply(prix);
	}

}
