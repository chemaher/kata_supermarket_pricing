package supermarket.pricing.kata.services;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Service de scan et calcul du panier du client
 *
 * @author Maher AMRI
 *
 */
public class ServiceTarification {

	/**
	 * Scanner et calculer le montant total du panier du client
	 *
	 * @param panier
	 *            {@link Map} dont la clé est l'id du produit et la valeur est la
	 *            quantité sélectionnée
	 * @return {@link BigDecimal} le montant du panier du client
	 */
	public BigDecimal scannerPanier(final Map<Long, Integer> panier) {
		return BigDecimal.ZERO;
	}

}
