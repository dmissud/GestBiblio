package org.dbs.biblio.gestbiblio.port.out;

import org.dbs.biblio.gestbiblio.domain.Member;

public interface MemberRepository {
    Member findMemberByIdent(String identifiant);
}
