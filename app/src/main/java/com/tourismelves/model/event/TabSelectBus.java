package com.tourismelves.model.event;

/**
 *tab页
 */

public class TabSelectBus {
    private int mainTabIndex;

    public TabSelectBus(int mainTabIndex) {
        this.mainTabIndex = mainTabIndex;
    }

    public int getMainTabIndex() {
        return mainTabIndex;
    }
}
