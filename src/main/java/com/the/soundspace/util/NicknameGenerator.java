package com.the.soundspace.util;


import java.util.Random;


public class NicknameGenerator {
    private static final String[] NOUNS = {"꽃", "나무", "바다", "하늘", "별", "빛", "음악", "사랑", "행복", "꿈", "모래", "강", "바람", "햇살", "눈", "비", "숲", "감성", "평화", "세상"};
    private static final String[] ADJECTIVES = {"화려한", "창의적인", "열정적인", "자유로운", "활기찬", "격렬한", "쾌활한", "우아한", "현명한", "우아한", "정신없는", "신비로운", "풍부한", "안정된", "다채로운", "유쾌한", "진실한", "영리한", "열정적인", "차분한"};

    public static String generate() {
        var rand = new Random();
        var adjectiveIndex = rand.nextInt(ADJECTIVES.length);
        var nounIndex = rand.nextInt(NOUNS.length);
        var nickname = String.join(" ", ADJECTIVES[adjectiveIndex], NOUNS[nounIndex]);
        var hashtag = rand.nextInt(1000, 10000);
        return nickname + hashtag;
    }
}
