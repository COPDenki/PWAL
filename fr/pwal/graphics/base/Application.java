package fr.pwal.graphics.base;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.pwal.base.physic.AABB;
import fr.pwal.base.physic.BlockEffect;
import fr.pwal.graphics.base.control.Keyboard;
import fr.pwal.graphics.base.graphics.window.App_Component;
import fr.pwal.level.Level;
import fr.pwal.level.Player;

@SuppressWarnings("serial")
public class Application extends Canvas implements Runnable {

	private String TITLE;
	private final int WIDTH;
	private final int HEIGHT;
	private final float SCALE;
	private final static int FPS = 1_000 / 60;
	private final static int UPS = 1_000 / 120;

	private int current_fps, current_ups;

	private JFrame window;
	private final Thread window_Thread;
	private BufferedImage img;

	private Vector<App_Component> components;

	private boolean isRunning;

	private Level level;

	public Application(String title, int width, int height, float scale, Level level) {
		this.TITLE = title;
		this.WIDTH = width;
		this.HEIGHT = height;
		this.SCALE = scale;
		this.level = level;
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
		this.addKeyListener(new Keyboard(this.level.getPlayers()));
		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int answer = JOptionPane.showConfirmDialog(window, "Are you sure ?", "Window closed detection !",
						JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION)
					System.exit(0);
				else
					return;
			}
		});

		add(level);
		window.setVisible(true);
		long timer = System.currentTimeMillis();
		long start_time_FPS = System.currentTimeMillis();
		long start_time_UPS = System.currentTimeMillis();
		int tempFPS = 0, tempUPS = 0;
		while (isRunning) {
			long current_time = System.currentTimeMillis();

			if (current_time >= start_time_UPS + UPS) {
				update();
				tempUPS++;
			}

			if (current_time >= start_time_FPS + FPS) {
				render();
				tempFPS++;
			}
			if (System.currentTimeMillis() > timer + 1000) {
				timer += 1000;
				current_fps = tempFPS;
				current_ups = tempUPS;
				tempUPS = 0;
				tempFPS = 0;
			}

		}
	}

	public void render() {
		if (img == null) {
			img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		}
		Graphics g = img.getGraphics();
//		for (Iterator<App_Component> iterator = components.iterator(); iterator.hasNext();) {
//			App_Component app_Component = (App_Component) iterator.next();
//			app_Component.drawIG(g, SCALE);
//			app_Component.drawHUD(g);
//		}
		this.level.render(g, SCALE);
		getGraphics().drawImage(img, 0, 0, null);

	}

	public void update() {
		this.level.update();
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
