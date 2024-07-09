package com.flab.skilltrademarket.domain.term;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public enum TermType {
    ESSENTIAL_IS_OVER_FOURTEEN("만 14세 이상(필수)"),
    ESSENTIAL_TERMS_OF_SERVICE("이용약관(필수)"),
    ESSENTIAL_PERSONAL_INFO_COLLECT("개인정보 수집 및 이용동의(필수)"),
    OPTIONAL_PERSONAL_INFO_MARKETING("개인정보 마케팅 활용 동의(선택)"),
    OPTIONAL_THIRD_PARTY_SHARE("제 3자 정보 제공 동의(선택)");

    private String description;

    public static final List<TermType> CHECK_ESSENTIAL_LIST = Arrays.asList(ESSENTIAL_IS_OVER_FOURTEEN, ESSENTIAL_TERMS_OF_SERVICE, ESSENTIAL_PERSONAL_INFO_COLLECT);
    public static final List<TermType> CHECK_OPTION_LIST = Arrays.asList(OPTIONAL_PERSONAL_INFO_MARKETING, OPTIONAL_THIRD_PARTY_SHARE);
    public static final List<TermType> SIGN_ALL_LIST = Stream.concat(CHECK_ESSENTIAL_LIST.stream(), CHECK_OPTION_LIST.stream())
            .collect(Collectors.toList());

    public static boolean isEssentialType(TermType checkType){
        return CHECK_ESSENTIAL_LIST.contains(checkType);
    }

    TermType(String description) {
        this.description = description;
    }

    @JsonCreator
    public static TermType from(String description) {
        for (TermType termType : TermType.values()) {
            if (termType.getDescription().equals(description)) {
                return termType;
            }
        }
        return null;
    }
    @JsonValue
    public String getDescription() {
        return description;
    }
}
