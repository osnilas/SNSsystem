package app.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Exit implements Serializable {

        private SNSuser snSuser;

        private LocalDateTime LeavingDateTime;

        /**
         * @author Afonso Cunha <1190325@isep.ipp.pt>
         * @param snSuser
         * Constructor
         * Registers the exit of an SNS User.
         */

        public Exit (SNSuser snSuser) {
            this.snSuser = snSuser;
            this.LeavingDateTime = LocalDateTime.now();
        }

        public SNSuser getSnSuser() {
            return snSuser;
        }

        public LocalDateTime getLeavingDateTime() {
            return LeavingDateTime;
        }
    }
