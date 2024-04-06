package org.example.kukathonbackend.domain.recommend.presentation;

public enum InformationEnum {
    one("2시간동안 독서 114페이지를 했네! 2시간이면 영단어 100개를 외울 수 있대"),
    two("1시간동안 영단어 50개를 외웠네! 1시간이면 드라마 1편을 볼 수 있대!"),
    three("1시간동안 드라마 1편을 봤네! 1시간이면 독서 100 페이지를 할 수 있대!"),
    four("1시간동안 휴식을 했네! 1시간이면 우주가 54,000km 팽창한대!"),
    five("1시간동안 영어공부를 했네! 1시간이면 별이 14,400,000개 탄생한대!"),
    SIX("1시간동안 영화를 봤네! 1시간이면 심장이 126,000번 뛰어대!"),
    NINE("1시간동안 프로그래밍을 했네! 1시간이면 뇌세포 2개가 만들어져대!");

    private final String information;

    InformationEnum(String information) {
        this.information = information;
    }

    public String getInformation() {
        return information;
    }
    public static InformationEnum getRandomInformation() {
        return values()[(int) (Math.random() * values().length)];
    }
}
