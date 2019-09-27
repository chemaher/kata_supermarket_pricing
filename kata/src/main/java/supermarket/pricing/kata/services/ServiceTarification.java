package supermarket.pricing.kata.services;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import supermarket.pricing.kata.domaine.Produit;
import supermarket.pricing.kata.entrepot.ProduitEntrepot;
import supermarket.pricing.kata.exception.ProduitNonExistantException;
import supermarket.pricing.kata.tarification.Tarification;

/**
 * Service de scan et calcul du panier du client
 *
 * @author Maher AMRI
 *
 */
public class ServiceTarification {

	ProduitEntrepot produitEntrepot = ProduitEntrepot.getInstance();

	/**
	 * Scanner et calculer le montant total du panier du client
	 *
	 * @param panier
	 *            {@link Map} dont la clé est l'id du produit et la valeur est la
	 *            quantité sélectionnée
	 * @return {@link BigDecimal} le montant du panier du client
	 */
	public BigDecimal scannerPanier(final Map<Long, Integer> panier) {
		return panier.keySet().stream().map(id -> calculerPrixDeTarification(id, panier.get(id)))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	/**
	 * Calculer le prix de tarification pour chaque produit selectionné par le
	 * client
	 *
	 * @param productID
	 *            {@link Long} id du produit selectionné
	 * @param quantite
	 *            {@link Integer} quantité selectionné
	 * @return {@link BigDecimal} montant global à payer par le client
	 */
	private BigDecimal calculerPrixDeTarification(final Long productID, final Integer quantite) {
		final Optional<Produit> product = produitEntrepot.recupererProduitParId(productID);
		final Tarification tarification = product.map(Produit::getTarification)
				.orElseThrow(ProduitNonExistantException::new);
		final BigDecimal price = product.map(Produit::getPrix).orElseThrow(ProduitNonExistantException::new);
		return tarification.calculerTarification(quantite, price);

	}
}
