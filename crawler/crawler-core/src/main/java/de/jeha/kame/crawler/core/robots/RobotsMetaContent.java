package de.jeha.kame.crawler.core.robots;

/**
 * @author jenshadlich@googlemail.com
 */
public class RobotsMetaContent {
    private final boolean index;
    private final boolean follow;
    private final boolean archive;

    public RobotsMetaContent(boolean index, boolean follow, boolean archive) {
        this.index = index;
        this.follow = follow;
        this.archive = archive;
    }

    public boolean isIndex() {
        return index;
    }

    public boolean isFollow() {
        return follow;
    }

    public boolean isArchive() {
        return archive;
    }

}
