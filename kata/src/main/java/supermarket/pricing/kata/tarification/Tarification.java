package supermarket.pricing.kata.tarification;

import java.math.BigDecimal;

/**
 * Interface qui définit les standards de tarification de chaque produit
 * existant dans le magasin
 *
 * @author Maher AMRI
 *
 */
public interface Tarification {

	/**
	 * Calculer la tarification simple du produit sélectionné
	 *
	 * @param quantite
	 *            {@link Integer} la quantité du produit sélectionné
	 * @param prix
	 *            {@link BigDecimal} le prix du produit sélectionné
	 * @return {@link BigDecimal} la tarification du produit sélectionné
	 *
	 */
	public default BigDecimal calculerTarification(final Integer quantite, final BigDecimal prix) {
		return prix.multiply(new BigDecimal(quantite));
	}

}
