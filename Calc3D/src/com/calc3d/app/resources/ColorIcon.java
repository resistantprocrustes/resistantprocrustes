package com.calc3d.app.resources;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

 class ColorIcon implements Icon {

 private Color color;
  public ColorIcon(Color color) {
	  this.color=color;
  }

  @Override
public int getIconHeight() {
    return 20;
  }

  @Override
public int getIconWidth() {
    return 20;
  }

  @Override
public void paintIcon(Component c, Graphics g, int x, int y) {
    g.setColor(color);
    g.fillRect(2, 2, 16, 16);
    g.setColor(Color.black);
    g.drawRect(2, 2, 16, 16);
  }
}