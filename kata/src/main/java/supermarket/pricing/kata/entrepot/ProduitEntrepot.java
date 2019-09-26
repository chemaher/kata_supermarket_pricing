package supermarket.pricing.kata.entrepot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.NonNull;
import supermarket.pricing.kata.domaine.Produit;

/**
 *
 * @author Maher AMRI
 *
 */
public final class ProduitEntrepot {

	private final Map<Long, Produit> produits;

	private static class ProduitEntrepotSingleton {
		private static final ProduitEntrepot instance = new ProduitEntrepot();
	}

	public static ProduitEntrepot getInstance() {
		return ProduitEntrepotSingleton.instance;
	}

	private ProduitEntrepot() {
		this.produits = new HashMap<>();
	}

	public Map<Long, Produit> getProduits() {
		return produits;
	}

	public Optional<Produit> recupererProduitParId(final @NonNull Long id) {
		return Optional.ofNullable(produits.get(id));
	}

	public void ajouterProduit(final @NonNull Produit produit) {
		produits.put(produit.getId(), produit);
	}
}
