// $Id$
/*
 * Copyright (C) 2010, 2011 sk89q <http://www.sk89q.com>
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not,
 * see <http://www.gnu.org/licenses/>.
 */

package ru.spliterash.musicbox.events;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockRedstoneEvent;

/**
 * A variation of the redstone event with a source block.
 */
public class SourcedBlockRedstoneEvent extends BlockRedstoneEvent {

    protected final Block source;
    private static final HandlerList handlers = new HandlerList();

    public SourcedBlockRedstoneEvent(Block source, Block block, int old, int n) {

        super(block, old, n);
        this.source = source;
    }

    public Block getSource() {

        return source;
    }

    public boolean hasChanged() {

        return getOldCurrent() != getNewCurrent();
    }

    public boolean isMinor() {

        return !hasChanged() || wasOn() == isOn();
    }

    public boolean isOn() {

        return getNewCurrent() > 0;
    }

    public boolean wasOn() {

        return getOldCurrent() > 0;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}