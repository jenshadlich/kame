package de.jeha.kame.crawler.core.robots;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author jenshadlich@googlemail.com
 */
public class RobotsMetaContent {

    private final boolean metaRobotsSet;
    private final boolean robotsTagSet;
    private final boolean index;
    private final boolean follow;
    private final boolean archive;

    public RobotsMetaContent(boolean metaRobotsSet, boolean robotsTagSet, boolean index, boolean follow, boolean archive) {
        this.metaRobotsSet = metaRobotsSet;
        this.robotsTagSet = robotsTagSet;
        this.index = index;
        this.follow = follow;
        this.archive = archive;
    }

    public boolean isMetaRobotsSet() {
        return metaRobotsSet;
    }

    public boolean isRobotsTagSet() {
        return robotsTagSet;
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

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
