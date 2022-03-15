package model;

import java.awt.Rectangle;

import javax.swing.JPanel;

import java.awt.*;

public abstract class Entity extends Rectangle {
  public abstract void draw(Graphics2D g, JPanel observer);
}