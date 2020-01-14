import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.ArrayList;
import java.awt.Image.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.HashMap;

public class Settings implements ActionListener{
	private Timer timer = new Timer(1000/60, this);
	private SettingsPanel settingsPanel = new SettingsPanel();
	private static HashMap<String, Object> settingsMap = new HashMap<>();
	//{gray, red, green, brown}
	private static Color [] boardColors = {new Color(79, 76, 69), new Color(79,8,17), new Color(3,150,8), new Color(145,84,17)};
	private static BufferedReader reader;
	private static PrintWriter writer;
	@Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == timer) {
            settingsPanel.repaint();
        }
    }

	public static int getPortNumber() {
		loadSettings();
		return Integer.parseInt((String)settingsMap.get("port"));
	}

	public static Color getBoardColor() {
		loadSettings();
		return boardColors[Integer.parseInt((String)settingsMap.get("color"))];
	}

	public static boolean isDark() {
		loadSettings();
		return Boolean.parseBoolean((String)settingsMap.get("dark"));
	}

	public static boolean filterOn() {
		loadSettings();
		return Boolean.parseBoolean((String)settingsMap.get("filter"));
	}

	private static void loadSettings() {
		reader = Utility.getReader("settings.txt");
		for(int i = 0; i < 4; i++) {
			String [] line = Utility.readLine(reader).trim().split(" : ");
			settingsMap.put(line[0], line[1]);
		}
	}

	public static void setDefaultSettings() {
		settingsMap.clear();
		settingsMap.put("port", 7777);
		settingsMap.put("color", boardColors[0]);
		settingsMap.put("dark", true);
		settingsMap.put("filter", true);
		writer = Utility.getWriter("settings.txt");
		writer.print("port : 7777");
		writer.println("color : gray");
		writer.println("dark : true");
		writer.println("filter : true");
	}

    public JPanel getsettingsPanel() {
        return settingsPanel;
    }

    public Settings(){
		settingsPanel.setPreferredSize(Utility.panelDimensions);
	}

    private class SettingsPanel extends JPanel implements ActionListener{

		 private JButton backButton = new JButton("BACK");
		 private JButton saveButton = new JButton("SAVE");
		 private JTextField portField = new JTextField();
		 private JRadioButton boardDarkGreyButt = new JRadioButton("Dark Grey");
		 private JRadioButton boardRedButt = new JRadioButton("Red");
		 private JRadioButton boardGreenButt = new JRadioButton("Green");
		 private JRadioButton boardBrownButt = new JRadioButton("Brown");
		 private JRadioButton UIDarkButt = new JRadioButton("Dark");
		 private JRadioButton UILightButt = new JRadioButton("Light");
		 private JRadioButton ProfanityNoButt = new JRadioButton("Yes");
		 private JRadioButton ProfanityYesButt = new JRadioButton("No");
		 private String strfileName = "Assets/AboutPanel.png";
		 private JLabel portLabel = new JLabel("Change port number(Enter number above 1000): ");
		 private JLabel boardcolorLabel = new JLabel("Change Board Color: ");
		 private JLabel UIlabel = new JLabel("Change UI Style:");
		 private JLabel profLabel = new JLabel("Profanity Filter(for chat)");
		 private ButtonGroup Boardcolortg = new ButtonGroup();
		 private ButtonGroup UItg = new ButtonGroup();
		 private ButtonGroup Proftg = new ButtonGroup();



		public void actionPerformed(ActionEvent evt) {
			if(evt.getSource()== backButton){
                    Utility.changePanel(new MainMenu().getMenuPanel());
			}else if(evt.getSource()== saveButton){
			}
		}

		public SettingsPanel(){
			super(null); //transfers constructor from JPanel
            backButton.setSize(116, 45);
            backButton.setLocation(30, 28);
            backButton.addActionListener(this);
            Utility.setButtonStyle(backButton, 12);
            add(backButton);


			saveButton.setSize(100, 25);
			saveButton.setLocation(800, 100);
			saveButton.addActionListener(this);
			Utility.setButtonStyle(saveButton, 12);
			add(saveButton);

			portField.setSize(150,25);
			portField.setLocation(650,100);
			add(portField);

			portLabel.setSize(300,25);
			portLabel.setLocation(300,100);
			add(portLabel);

			boardcolorLabel.setSize(300,25);
			boardcolorLabel.setLocation(300,150);
			add(boardcolorLabel);

			boardDarkGreyButt.setSize(100,25);
			boardDarkGreyButt.setLocation(300,200);
			add(boardDarkGreyButt);

			boardRedButt.setSize(50,25);
			boardRedButt.setLocation(400,200);
			add(boardRedButt);

			boardGreenButt.setSize(75,25);
			boardGreenButt.setLocation(460,200);
			add(boardGreenButt);

			boardBrownButt.setSize(100,25);
			boardBrownButt.setLocation(540,200);
			add(boardBrownButt);

			Boardcolortg.add(boardDarkGreyButt);
			Boardcolortg.add(boardRedButt);
			Boardcolortg.add(boardGreenButt);
			Boardcolortg.add(boardBrownButt);

			UIlabel.setSize(300,25);
			UIlabel.setLocation(300,250);
			add(UIlabel);

			UIDarkButt.setSize(100,25);
			UIDarkButt.setLocation(475,250);
			add(UIDarkButt);

			UILightButt.setSize(100,25);
			UILightButt.setLocation(400,250);
			add(UILightButt);

			UItg.add(UIDarkButt);
			UItg.add(UILightButt);

			profLabel.setSize(300,25);
			profLabel.setLocation(300,300);
			add(profLabel);

			ProfanityNoButt.setSize(100,25);
			ProfanityNoButt.setLocation(475,300);
			add(ProfanityNoButt);

			ProfanityYesButt.setSize(100,25);
			ProfanityYesButt.setLocation(575,300);
			add(ProfanityYesButt);

			Proftg.add(ProfanityNoButt);
			Proftg.add(ProfanityYesButt);
		}
	}
}
