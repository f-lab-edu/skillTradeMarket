package com.flab.skilltrademarket.domain.phone;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PhoneAuthEntity {
        private int code;
        private boolean verification;

        public PhoneAuthEntity(int code) {
            this.code = code;
            this.verification = false;
        }

        public boolean isMatchCode(int code) {
            if (this.code != code) {
                return false;
            }
            this.verification = true;
            return true;
        }

        public boolean isVerification() {
            return verification;
        }
}
