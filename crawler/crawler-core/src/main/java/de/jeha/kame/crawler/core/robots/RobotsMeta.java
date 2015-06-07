package de.jeha.kame.crawler.core.robots;

/**
 * @author jenshadlich@googlemail.com
 */
public class RobotsMeta {
    private final boolean index;
    private final boolean follow;

    public RobotsMeta(boolean index, boolean follow) {
        this.index = index;
        this.follow = follow;
    }

    public boolean isIndex() {
        return index;
    }

    public boolean isFollow() {
        return follow;
    }

}
