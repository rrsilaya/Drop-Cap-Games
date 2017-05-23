package pvs.screens;

import pvs.objects.ObjectRendered;
import pvs.objects.Sprite;
import pvs.objects.Button;
import pvs.objects.Text;
import pvs.objects.MainFrame;
import pvs.objects.DragAndDrop;
import pvs.University;
import pvs.essentials.*;

import java.util.ArrayList;
import java.lang.String;
import java.lang.Integer;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import java.io.File;

public class Gameplay extends Background {
	private BufferedImage background;
	private ObjectRendered image;
	private Container container;
	private MainFrame frame;
	private University university;
	private ArrayList<JPanel> lines;
	private ArrayList<String> renderedStudents;

	private boolean mouseDragged;
	private int mouseX, mouseY;

	public Gameplay(Container container, MainFrame frame) {
		super("Assets/UI/Gameplay/Background.jpg");

		// Back-end Integration
		this.university = new University(1);
		this.container = container;
		this.frame = frame;

		/*
		Talker waterThrower1 = new Talker();
		Talker waterThrower2 = new Talker();
		Talker waterThrower3 = new Talker();
		Talker waterThrower4 = new Talker();
		Talker waterThrower5 = new Talker();

		this.university.hireProfessor(0, 0, waterThrower1);
		this.university.hireProfessor(0, 1, waterThrower2);
		this.university.hireProfessor(0, 2, waterThrower3);
		this.university.hireProfessor(0, 3, waterThrower4);
		this.university.hireProfessor(0, 4, waterThrower5);
		*/


		// Element Layers
		this.lines = new ArrayList<JPanel>();
		for(int i = 0; i < 5; i++) {
			this.lines.add(new JPanel());
			this.lines.get(i).setSize(1000, 600);
			this.lines.get(i).setOpaque(false);
			this.add(this.lines.get(i), i);
		}

		// UUID Holder
		this.renderedStudents = new ArrayList<String>();

		// Instantiate Menu Button
		Button menu_btn = new Button(850, 15, "Assets/UI/Gameplay/Menu.png");
		this.renderObject(menu_btn);
		menu_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu menu = new MainMenu(container, frame);
				container.removeAll();
				container.add(menu, BorderLayout.CENTER);

				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});

		this.repaint();
	}

	private void renderStash() {
		Button tita_card = new Button(130, -12, "Assets/UI/Gameplay/Cards/Tita.png");
		Button talker_card = new Button(214, -12, "Assets/UI/Gameplay/Cards/Talker.png");
		Button waterThrower_card = new Button(298, -12, "Assets/UI/Gameplay/Cards/WaterThrower.png");
		Button coffeeMaker_card = new Button(382, -12, "Assets/UI/Gameplay/Cards/CoffeeMaker.png");

		this.renderObject(tita_card);
		this.renderObject(talker_card);
		this.renderObject(waterThrower_card);
		this.renderObject(coffeeMaker_card);
		this.add(new Text(40, 55, 5000));

		ObjectRendered tita_Image = new ObjectRendered(mouseX, mouseY, "Assets/Professors/Tita.png");
		ObjectRendered talker_Image = new ObjectRendered(mouseX, mouseY, "Assets/Professors/Talker.png");
		ObjectRendered waterThrower_Image = new ObjectRendered(mouseX, mouseY, "Assets/Professors/WaterThrower.png");
		ObjectRendered coffeeMaker_Image = new ObjectRendered(mouseX, mouseY, "Assets/Professors/CoffeeMaker.png");

		/* Mouse Listeners */
			// Tita
		tita_card.addMouseListener(new DragAndDrop(tita_Image, this.getGameplay(), 1));
		tita_card.addMouseMotionListener(new DragAndDrop(tita_Image, this.getGameplay(), 1));

		// Talker

		talker_card.addMouseListener(new DragAndDrop(talker_Image, this.getGameplay(), 2));
		talker_card.addMouseMotionListener(new DragAndDrop(talker_Image, this.getGameplay(), 2));

		// WaterThrower
		waterThrower_card.addMouseListener(new DragAndDrop(waterThrower_Image, this.getGameplay(), 3));
		waterThrower_card.addMouseMotionListener(new DragAndDrop(waterThrower_Image, this.getGameplay(), 3));

		// CoffeeMaker
		coffeeMaker_card.addMouseListener(new DragAndDrop(coffeeMaker_Image, this.getGameplay(), 4));
		coffeeMaker_card.addMouseMotionListener(new DragAndDrop(coffeeMaker_Image, this.getGameplay(), 4));
	}

	private void renderKwatro() {
		ArrayList<ObjectRendered> kwatro = new ArrayList<ObjectRendered>();
		String path = "Assets/UI/Gameplay/Kwatro.png";

		for(int i = 1; i <= 5; i++) {
			kwatro.add(new ObjectRendered(10, 100 * i, path));
			this.renderObject(kwatro.get(i - 1));
		}
	}

	private void renderProfessors() {
		ArrayList<Professor> professors = this.university.getProfessors();

		for(int i = 0; i < professors.size(); i++) {
			JPanel pane = this.lines.get(this.lines.size() - 1 - professors.get(i).getArrY());

			pane.add(professors.get(i));
		}
	}

	private void renderStudents() {
		ArrayList<Student> students = this.university.getStudents();

		for(int i = 0; i < students.size(); i++) {
			this.lines.get(this.lines.size() - 1 - students.get(i).getArrY()).add(students.get(i));
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(this.background, 0, 0, null);

		this.renderProfessors();
		this.renderStash();
		this.renderKwatro();
		this.renderStudents();
	}

	public void mouseDragging(ObjectRendered object, boolean mouseDragged, int mouseX, int mouseY, int value){
		if(mouseDragged){
			object.setCoordinates(mouseX, mouseY);
			object.repaint();
			this.add(object);
			this.repaint();
		}else{
			this.remove(object);
			if((mouseY /100) + 1 > 4){
				mouseY = 300;
			}

			if(mouseY < 0){
				switch (value){
					case 1:
						this.university.hireProfessor(mouseX/ 113, 0, new Tita());
						break;
					case 2:
						this.university.hireProfessor(mouseX/ 113, 0, new Talker());
						break;
					case 3:
						this.university.hireProfessor(mouseX/ 113, 0, new WaterThrower());
						break;
					case 4:
						this.university.hireProfessor(mouseX/ 113, 0 , new CoffeeMaker());
						break;
				}
			}else{
				switch (value){
					case 1:
						this.university.hireProfessor(mouseX/ 113, (mouseY / 100) + 1, new Tita());
						break;
					case 2:
						this.university.hireProfessor(mouseX/ 113, (mouseY / 100) + 1, new Talker());
						break;
					case 3:
						this.university.hireProfessor(mouseX/ 113, (mouseY / 100) + 1, new WaterThrower());
						break;
					case 4:
						this.university.hireProfessor(mouseX/ 113, (mouseY / 100) + 1 , new CoffeeMaker());
						break;
				}
			}

			this.repaint();
		}
	}

	public Gameplay getGameplay(){
		return this;
	}
}
