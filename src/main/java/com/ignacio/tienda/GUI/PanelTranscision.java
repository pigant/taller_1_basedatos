/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignacio.tienda.GUI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author ignacio
 */
public class PanelTranscision extends JPanel {

	private boolean enTransicion = false;
	JPanel panelNuevo;
	JPanel panelViejo;

	BufferedImage bi;
	BufferedImage bo;

	int porAvanzar;
	int x1, x2;

	@Override
	public void paint(Graphics g) {
		if (enTransicion) {
			g.drawImage(bi, x1, 0, null);
			g.drawImage(bo, x2, 0, null);
		} else {
			super.paint(g); //To change body of generated methods, choose Tools | Templates.
		}
	}

	public void animar(JPanel nuevo) {
		if (enTransicion) {
			return;
		}
		panelViejo = panelNuevo;
		panelNuevo = nuevo;
		this.removeAll();
		if (panelViejo != null) {
			int w = panelViejo.getWidth();
			int h = panelViejo.getHeight();
			porAvanzar = -w;
			bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bi.createGraphics();
			panelViejo.paint(g);
			w = panelNuevo.getWidth();
			h = panelNuevo.getHeight();
			bo = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			g = bo.createGraphics();
			panelNuevo.paint(g);
			enTransicion = true;
			//xxx
			x1 = 0;
			x2 = -porAvanzar;
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (x1 > porAvanzar) {
						x1 -= 10;
						x2 -= 10;
						repaint();
						try {
							Thread.sleep(3);
						} catch (InterruptedException ex) {
							break;
						}
					}
					enTransicion = false;
					add(panelNuevo);
					
				}
			}).start();
		}else {
			add(panelNuevo);
			repaint();
		}
	}

}
