package org.dbs.biblio.gestbiblio.infrastructure.config;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "biblio_entity")
public class BiblioEntity {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    private String code;
    private Integer lastCopyBookIdentifiant;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getLastCopyBookIdentifiant() {
        return lastCopyBookIdentifiant;
    }

    public void setLastCopyBookIdentifiant(Integer lastCopyBookIdentifiant) {
        this.lastCopyBookIdentifiant = lastCopyBookIdentifiant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BiblioEntity that)) return false;

        if (!getName().equals(that.getName())) return false;
        if (!getCode().equals(that.getCode())) return false;
        return getLastCopyBookIdentifiant().equals(that.getLastCopyBookIdentifiant());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getCode().hashCode();
        result = 31 * result + getLastCopyBookIdentifiant().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BiblioEntity{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", lastCopyBookIdentifiant=" + lastCopyBookIdentifiant +
                '}';
    }
}
