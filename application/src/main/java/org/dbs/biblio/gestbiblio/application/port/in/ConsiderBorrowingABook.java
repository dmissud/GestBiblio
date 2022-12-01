package org.dbs.biblio.gestbiblio.application.port.in;

import lombok.Getter;
import org.dbs.biblio.gestbiblio.application.common.SelfValidating;

import javax.validation.constraints.Pattern;

public interface ConsiderBorrowingABook {
    void considerBorrowingABook(CreateBorrowCmd createBorrowCmd);


    @Getter
    class CreateBorrowCmd extends SelfValidating<CreateBorrowCmd> {
        @Pattern(regexp = "^(AD\\d{5})$")
        private final String idMember;

        @Pattern(regexp = "^(EX\\d{5})$")
        private final String idCopy;

        public CreateBorrowCmd(String idMember, String idCopy) {
            this.idMember = idMember;
            this.idCopy = idCopy;
            this.validateSelf();
        }
    }
}
