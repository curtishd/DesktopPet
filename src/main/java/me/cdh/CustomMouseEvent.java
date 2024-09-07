package me.cdh;

import java.awt.event.MouseEvent;

@FunctionalInterface
public interface CustomMouseEvent {
    void event(MouseEvent e);
}
