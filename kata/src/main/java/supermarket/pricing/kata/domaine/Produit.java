package supermarket.pricing.kata.domaine;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import lombok.NonNull;
import supermarket.pricing.kata.tarification.Tarification;

/**
 * Entité Produit qui modélise chaque produit dans le supermarchet
 *
 * @author Maher AMRI
 *
 */
public class Produit {

	/**
	 * Id du produit
	 */
	private Long id;

	/**
	 * nom du produit
	 */
	private String nom;

	/**
	 * prix du produit
	 */
	private BigDecimal prix;

	/**
	 * Devise du prix du produit
	 */
	private String devise;

	/**
	 * Date d'expiration du produit
	 */
	private LocalDate dateExpiration;

	/**
	 * Le type de tarification du produit
	 */
	private Tarification tarification;

	public Long getId() {
		return id;
	}

	public void setId(final @NonNull Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(final @NonNull String nom) {
		this.nom = nom;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(final @NonNull BigDecimal prix) {
		this.prix = prix;
	}

	public String getDevise() {
		return devise;
	}

	public void setDevise(final @NonNull String devise) {
		this.devise = devise;
	}

	public Tarification getTarification() {
		return tarification;
	}

	public void setTarification(final @NonNull Tarification tarification) {
		this.tarification = tarification;
	}

	public LocalDate getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(final @NonNull LocalDate dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, nom, prix, devise, dateExpiration);
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Produit)) {
			return false;
		}
		final Produit produit = (Produit) obj;
		return Objects.equals(id, produit.getId()) && Objects.equals(nom, produit.getNom())
				&& Objects.equals(prix, produit.getPrix()) && Objects.equals(devise, produit.getDevise())
				&& Objects.equals(dateExpiration, produit.getDateExpiration());
	}

}
