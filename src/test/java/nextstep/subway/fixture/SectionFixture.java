package nextstep.subway.fixture;

import nextstep.subway.domain.Line;
import nextstep.subway.domain.Section;
import nextstep.subway.domain.Station;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static nextstep.subway.fixture.FieldFixture.노선_간_거리;
import static nextstep.subway.fixture.FieldFixture.노선_상행_종점역_ID;
import static nextstep.subway.fixture.FieldFixture.노선_하행_종점역_ID;
import static nextstep.subway.fixture.FieldFixture.식별자_아이디;
import static nextstep.subway.fixture.StationFixture.강남역;
import static nextstep.subway.fixture.StationFixture.교대역;
import static nextstep.subway.fixture.StationFixture.남부터미널역;
import static nextstep.subway.fixture.StationFixture.삼성역;
import static nextstep.subway.fixture.StationFixture.선릉역;
import static nextstep.subway.fixture.StationFixture.신사역;
import static nextstep.subway.fixture.StationFixture.양재역;
import static nextstep.subway.fixture.StationFixture.역삼역;
import static nextstep.subway.fixture.StationFixture.정자역;

public enum SectionFixture {

    양재_정자_구간(10, 양재역, 정자역),
    강남_양재_구간(8, 강남역, 양재역),
    강남_삼성_구간(20, 강남역, 삼성역),
    강남_역삼_구간(4, 강남역, 역삼역),
    역삼_삼성_구간(4, 역삼역, 삼성역),
    역삼_선릉_구간(6, 역삼역, 선릉역),
    신사_강남_구간(7, 신사역, 강남역),
    교대_강남_구간(10, 교대역, 강남역),
    교대_남부터미널_구간(2, 교대역, 남부터미널역),
    남부터미널_양재_구간(3, 남부터미널역, 양재역),

    강남_역삼_구간_비정상_거리(100, 강남역, 역삼역),
    ;

    private final int distance;
    private final StationFixture upStation;
    private final StationFixture downStation;

    SectionFixture(int distance, StationFixture upStation, StationFixture downStation) {
        this.distance = distance;
        this.upStation = upStation;
        this.downStation = downStation;
    }

    public int 구간_거리() {
        return distance;
    }

    public StationFixture 상행역() {
        return upStation;
    }

    public StationFixture 하행역() {
        return downStation;
    }

    public Map<String, String> 요청_데이터_생성(Long 상행역_id, Long 하행역_id) {
        Map<String, String> params = new HashMap<>();
        params.put(노선_상행_종점역_ID.필드명(), String.valueOf(상행역_id));
        params.put(노선_하행_종점역_ID.필드명(), String.valueOf(하행역_id));
        params.put(노선_간_거리.필드명(), String.valueOf(구간_거리()));
        return params;
    }

    public Section 엔티티_생성(Line 호선) {
        return new Section(호선, 상행역().엔티티_생성(), 하행역().엔티티_생성(), 구간_거리());
    }

    public Section 엔티티_생성(Long id, Line 호선) {
        Section section = new Section(호선, 상행역().엔티티_생성(), 하행역().엔티티_생성(), 구간_거리());
        ReflectionTestUtils.setField(section, 식별자_아이디.필드명(), id);
        return section;
    }

    public Section 엔티티_생성(Line 호선, Station 상행역, Station 하행역) {
        return new Section(호선, 상행역, 하행역, 구간_거리());
    }
}
