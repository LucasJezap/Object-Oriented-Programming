import org.junit.Test;

import static org.junit.Assert.*;

public class ActionsParserTest {

    @Test
    public void parse() {
        String[] arr = {"d+", "d-", "t+", "t+", "t-", "t-", "d-", "d+"};
        Action[] arr2 = {Action.DAY_LATER, Action.DAY_EARLIER, Action.TIME_LATER, Action.TIME_LATER,
                Action.TIME_EARLIER, Action.TIME_EARLIER, Action.DAY_EARLIER, Action.DAY_LATER};
        assertArrayEquals(arr2, new ActionsParser().parse(arr));
    }
}