package fr.pwal.graphics.base;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.pwal.graphics.base.graphics.window.App_Component;

@SuppressWarnings("serial")
public class Application extends Canvas implements Runnable {

	private String TITLE;
	private final int WIDTH;
	private final int HEIGHT;
	private final static int FPS = 1_000 / 60;
	private final static int UPS = 1_000 / 120;

	private int current_fps, current_ups;

	private boolean fpsInLog = true;

	private JFrame window;
	private final Thread window_Thread;

	private Vector<App_Component> components;

	private boolean isRunning;

	public Application(String title, int width, int height) {
		this.TITLE = title;
		this.WIDTH = width;
		this.HEIGHT = height;

		window_Thread = new Thread(this);
		window_Thread.start();
	}

	@Override
	public void run() {
		isRunning = true;
		components = new Vector<>();
		window = new JFrame(TITLE);
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.setResizable(false);
		window.setSize(WIDTH, HEIGHT);
		window.getContentPane().add(this);
		window.addWindowListener(new WindowAdapter() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}

			@Override
			public void windowClosing(WindowEvent e) {
				int answer = JOptionPane.showConfirmDialog(window, "Are you sure ?", "Window closed detection !",
						JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION)
					System.exit(0);
				else
					return;
			}

			@Override
			public void windowClosed(WindowEvent e) {

			}

			@Override
			public void windowActivated(WindowEvent e) {

			}
		});

		window.setVisible(true);
		long timer = System.currentTimeMillis();
		long start_time_FPS = System.currentTimeMillis();
		long start_time_UPS = System.currentTimeMillis();
		int tempFPS = 0, tempUPS = 0;
		while (isRunning) {
			long current_time = System.currentTimeMillis();

			if (current_time >= start_time_FPS + FPS) {
				render();
				tempFPS++;
			}
			if (current_time >= start_time_UPS + UPS) {
				update();
				tempUPS++;
			}
			if (System.currentTimeMillis() > timer + 1000) {
				timer = System.currentTimeMillis();
				current_fps = tempFPS;
				current_ups = tempUPS;
				tempUPS = tempFPS = 0;
			}

		}
	}

	public void render() {
		// System.out.println("Render");
		Graphics g = getGraphics();
		for (Iterator<App_Component> iterator = components.iterator(); iterator.hasNext();) {
			App_Component app_Component = (App_Component) iterator.next();
			app_Component.draw(g);
		}
	}

	public void update() {
		// System.out.println("Update");
		if (fpsInLog) {
			// System.out.println(TITLE + " - UPS:" + getUPS() + " | FPS:" +
			// getFPS());
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	public int getWidth() {
		return this.WIDTH;
	}

	public int getHeight() {
		return this.HEIGHT;
	}

	public String getTitle() {
		return this.TITLE;
	}

	public int getFPS() {
		return current_fps;
	}

	public int getUPS() {
		return current_ups;
	}

	public void setTitle(String title) {
		this.TITLE = title;
	}

	public void add(App_Component component) {
		components.add(component);
	}
}
