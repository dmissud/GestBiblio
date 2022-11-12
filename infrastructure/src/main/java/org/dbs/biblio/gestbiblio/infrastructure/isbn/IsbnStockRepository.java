package org.dbs.biblio.gestbiblio.infrastructure.isbn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class IsbnStockRepository {
    private final IsbnEntityJpa isbnEntityJpa;

    @Autowired
    public IsbnStockRepository(IsbnEntityJpa isbnEntityJpa) {
        this.isbnEntityJpa = isbnEntityJpa;
    }

    public long sizeOfStock() {
        return this.isbnEntityJpa.count();
    }

    public Set<IsbnEntity> findIsbnsByPage(int pos, int sizeOfPage) {
        Pageable pageRequest = PageRequest.of(pos, sizeOfPage);
        return this.isbnEntityJpa.findAll(pageRequest).toSet();
    }

    public IsbnEntity findIsbnsByPosition(int position) {
        Pageable pageRequest = PageRequest.of(position, 1);
        List<IsbnEntity> isbnEntities = isbnEntityJpa.findAll(pageRequest).toList();
        return isbnEntities.get(0);
    }
}
