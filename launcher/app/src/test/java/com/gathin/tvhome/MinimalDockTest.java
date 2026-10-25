package com.gathin.tvhome;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** minimal_dock narrows the grid to a centred block of small tiles. */
public class MinimalDockTest {
    private static final float DENSITY = 2f;   // 1080p TV, 320 dpi
    /** The grid on a 1920 px screen: the content column's 56dp padding taken off each side. */
    private static final int GRID = 1920 - 2 * 56 * 2;

    @Test public void fourColumnsTakeHalfOfAnEightColumnRowCentred() {
        int side = AppAdapter.minimalSidePaddingPx(GRID, 4, DENSITY);
        int inner = GRID - 2 * side;
        int eightColumnCell = (GRID - 2 * 10 * 2) / 8;
        assertEquals(4 * eightColumnCell, inner, 1);
    }

    @Test public void tilesComeOutAtTheEightColumnSize() {
        int side = AppAdapter.minimalSidePaddingPx(GRID, 4, DENSITY);
        int small = AppAdapter.tileWidthPx(GRID - 2 * side, 4, DENSITY);
        int eight = AppAdapter.tileWidthPx(GRID - 2 * 10 * 2, 8, DENSITY);
        assertEquals(eight, small, 1);
    }

    @Test public void eightOrMoreColumnsKeepTheGridsOwnPadding() {
        assertEquals(20, AppAdapter.minimalSidePaddingPx(GRID, 8, DENSITY), 1);
    }

    @Test public void noWidthKeepsTheGridsOwnPadding() {
        assertEquals(20, AppAdapter.minimalSidePaddingPx(0, 4, DENSITY));
    }
}
