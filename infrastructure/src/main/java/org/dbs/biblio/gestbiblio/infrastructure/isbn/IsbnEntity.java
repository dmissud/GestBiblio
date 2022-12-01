package org.dbs.biblio.gestbiblio.infrastructure.isbn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
public class IsbnEntity {
    @Id
    @Pattern(regexp = "^(978\\d{10})$")
    @Column(nullable = false)
    private String isbn13;

    private String titre;

    private String auteur;

    private String editeur;

    private String date;

    private String langue;

    private String prix;

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IsbnEntity that)) return false;

        if (getIsbn13() != null ? !getIsbn13().equals(that.getIsbn13()) : that.getIsbn13() != null) return false;
        if (getTitre() != null ? !getTitre().equals(that.getTitre()) : that.getTitre() != null) return false;
        if (getAuteur() != null ? !getAuteur().equals(that.getAuteur()) : that.getAuteur() != null) return false;
        if (getEditeur() != null ? !getEditeur().equals(that.getEditeur()) : that.getEditeur() != null) return false;
        if (getDate() != null ? !getDate().equals(that.getDate()) : that.getDate() != null) return false;
        if (getLangue() != null ? !getLangue().equals(that.getLangue()) : that.getLangue() != null) return false;
        return getPrix() != null ? getPrix().equals(that.getPrix()) : that.getPrix() == null;
    }

    @Override
    public int hashCode() {
        int result = getIsbn13() != null ? getIsbn13().hashCode() : 0;
        result = 31 * result + (getTitre() != null ? getTitre().hashCode() : 0);
        result = 31 * result + (getAuteur() != null ? getAuteur().hashCode() : 0);
        result = 31 * result + (getEditeur() != null ? getEditeur().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getLangue() != null ? getLangue().hashCode() : 0);
        result = 31 * result + (getPrix() != null ? getPrix().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "IsbnEntity{" +
                "isbn13='" + isbn13 + '\'' +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", editeur='" + editeur + '\'' +
                ", date='" + date + '\'' +
                ", langue='" + langue + '\'' +
                ", prix='" + prix + '\'' +
                '}';
    }
}
