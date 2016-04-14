package fr.pwal.graphics.base;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.pwal.graphics.base.control.Keyboard;
import fr.pwal.graphics.base.graphics.window.App_Component;
import fr.pwal.level.LevelChain;

@SuppressWarnings("serial")
public class Application extends Canvas implements Runnable, MouseListener {

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

	private LevelChain levelChain;

	public Application(String title, int width, int height, float scale, LevelChain levelChain) {
		this.TITLE = title;
		this.WIDTH = width;
		this.HEIGHT = height;
		this.SCALE = scale;
		this.levelChain = levelChain;
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
		this.addMouseListener(this);
		window.getContentPane().add(this);
		this.addKeyListener(new Keyboard(this.levelChain.getPlayers()));
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
		g.clearRect(0, 0, getWidth(), getHeight());
		//		for (Iterator<App_Component> iterator = components.iterator(); iterator.hasNext();) {
		//			App_Component app_Component = (App_Component) iterator.next();
		//			app_Component.drawIG(g, SCALE);
		//			app_Component.drawHUD(g);
		//		}
		this.levelChain.render_IG(g, SCALE);
		this.levelChain.render_HUD(g);
		getGraphics().drawImage(img, 0, 0, null);

	}

	public void update() {
		this.levelChain.update();
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

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
