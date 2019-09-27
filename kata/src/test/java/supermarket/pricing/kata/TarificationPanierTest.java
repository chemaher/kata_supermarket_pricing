package supermarket.pricing.kata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import supermarket.pricing.kata.domaine.Produit;
import supermarket.pricing.kata.entrepot.ProduitEntrepot;
import supermarket.pricing.kata.exception.ProduitNonExistantException;
import supermarket.pricing.kata.services.ServiceTarification;
import supermarket.pricing.kata.tarification.TarificationRemiseSurPrix;
import supermarket.pricing.kata.tarification.TarificationRemiseSurQuantite;
import supermarket.pricing.kata.tarification.TarificationSimple;
import supermarket.pricing.kata.tarification.TarificationUnitaire;

@TestMethodOrder(OrderAnnotation.class)
public class TarificationPanierTest {

	private static final BigDecimal PRIX_CHOCOLAT_REMISE = new BigDecimal(5);

	private static final BigDecimal PRIX_BISSON_REMISE = new BigDecimal(2);

	private static final BigDecimal PRIX_500G_PISTACHE = new BigDecimal(30);

	private static final BigDecimal PRIX_NORMAL_TROIS_PIZZAS = new BigDecimal(9);

	private static final ProduitEntrepot produitEntrepot = ProduitEntrepot.getInstance();

	private final ServiceTarification serviceTarification = new ServiceTarification();

	@DisplayName("Récupérer les produits à partir du fichier csv")
	@ParameterizedTest
	@Order(1)
	@CsvFileSource(resources = "/produits.csv", numLinesToSkip = 1)
	public void importerProduitsTest(@AggregateWith(ProduitAggregateur.class) final Produit produit) {
		assertNotNull(produit);
		produitEntrepot.ajouterProduit(produit);
		assertTrue(MapUtils.isNotEmpty(produitEntrepot.getProduits()));
	}

	@DisplayName("Tester la tarification simple")

	@ParameterizedTest

	@ValueSource(longs = { 3 })

	@Order(2)
	public void tarificationSimpleTest(final Long produitID) {
		final Map<Long, Integer> panier = new HashMap<>();

		final Long produit = produitEntrepot.getProduits().entrySet().stream()
				.filter(e -> e.getValue().getTarification() instanceof TarificationSimple)
				.map(e -> e.getValue().getId()).findFirst().orElse(null);
		assertEquals(produitID, produit);
		panier.put(produitID, 3);
		assertEquals(PRIX_NORMAL_TROIS_PIZZAS, serviceTarification.scannerPanier(panier));
	}

	@DisplayName("Tester la tarification Unitaire")

	@ParameterizedTest

	@ValueSource(longs = { 1 })

	@Order(3)
	public void tarificationUnitaireTest(final Long produitID) {
		final Map<Long, Integer> panier = new HashMap<>();

		final Long produit = produitEntrepot.getProduits().entrySet().stream()
				.filter(e -> e.getValue().getTarification() instanceof TarificationUnitaire)
				.map(e -> e.getValue().getId()).findFirst().orElse(null);
		assertEquals(produitID, produit);
		panier.put(produitID, 500);
		assertEquals(PRIX_500G_PISTACHE, serviceTarification.scannerPanier(panier));
	}

	@DisplayName("Tester la tarification de remise sur quantité")

	@ParameterizedTest

	@ValueSource(longs = { 2 })

	@Order(4)
	public void tarificationParQuantiteTest(final Long produitID) {
		final Map<Long, Integer> panier = new HashMap<>();

		final Long produit = produitEntrepot.getProduits().entrySet().stream()
				.filter(e -> e.getValue().getTarification() instanceof TarificationRemiseSurQuantite)
				.map(e -> e.getValue().getId()).findFirst().orElse(null);
		assertEquals(produitID, produit);
		panier.put(produitID, 3);
		assertEquals(PRIX_BISSON_REMISE, serviceTarification.scannerPanier(panier));
	}

	@DisplayName("Tester la tarification de remise sur prix")
	@ParameterizedTest
	@ValueSource(longs = { 4 })
	@Order(5)
	public void tarificationParPrixTest(final Long produitID) {
		final Map<Long, Integer> panier = new HashMap<>();

		final Long produit = produitEntrepot.getProduits().entrySet().stream()
				.filter(e -> e.getValue().getTarification() instanceof TarificationRemiseSurPrix)
				.map(e -> e.getValue().getId()).findFirst().orElse(null);
		assertEquals(produitID, produit);
		panier.put(produitID, 4);
		assertEquals(PRIX_CHOCOLAT_REMISE, serviceTarification.scannerPanier(panier));
	}

	@Test
	void produitNonExistantExceptionTest() {
		final Map<Long, Integer> panier = new HashMap<>();
		panier.put(55L, 4);
		assertThrows(ProduitNonExistantException.class, () -> serviceTarification.scannerPanier(panier));
	}

}
