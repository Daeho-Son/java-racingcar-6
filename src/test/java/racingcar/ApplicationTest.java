package racingcar;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {

    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 전진_정지() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );

        assertRandomNumberInRangeTest(
            () -> {
                run("woni,pobi", "1");
                assertThat(output()).contains("woni : -", "pobi : ", "최종 우승자 : woni");
            },
            MOVING_FORWARD, STOP
        );
    }

    @Test
    void 이동_횟수에_대한_예외_처리_숫자가_아님() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi","woni", "a"))
                .isInstanceOf(IllegalArgumentException.class)
        );

        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi","woni", "!"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 정상적인_이름과_이동_횟수() {
        assertSimpleTest(() ->
            assertThatCode(() -> runException("pobi,javaj", "1"))
                .doesNotThrowAnyException()
        );
    }

    @Test
    void 이름에_대한_예외_처리_null() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException(null, "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 이름에_대한_예외_처리_empty() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("", "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 이름에_대한_예외_처리_6_자리_이상() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );

        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobiii,javaj", "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );

        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobiii,javaji", "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
