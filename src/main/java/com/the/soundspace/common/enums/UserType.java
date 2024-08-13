package com.the.soundspace.common.enums;

public enum UserType {
    CORPORATE_MEMBER("기업회원"), // 기업회원
    REGULAR_MEMBER("일반회원");    // 일반회원

    private final String koreanName;

    UserType(String koreanName) {
        this.koreanName = koreanName;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public String getEnglishName() {
        return name(); // Enum의 이름을 반환 (CORPORATE_MEMBER, REGULAR_MEMBER)
    }

    public static UserType fromKoreanName(String koreanName) {
        for (UserType type : UserType.values()) {
            if (type.getKoreanName().equals(koreanName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("해당하는 회원 유형이 없습니다: " + koreanName);
    }
}
