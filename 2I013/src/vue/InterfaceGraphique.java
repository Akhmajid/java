package vue;

import geometrie.Vecteur;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import radar.RadarClassique;
import radar.RadarDijkstra;
import radar.RadarParabolique;
import radar.RadarPrudent;
import selecteur.SelecteurArrivee;
import selecteur.SelecteurPoint;
import selecteur.SelecteurRadar;
import simulation.Simulation;
import strategie.Strategy;
import strategie.StrategyArrivee;
import strategie.StrategyParabolique;
import strategie.StrategyPilot;
import strategie.StrategyPoint;
import strategie.StrategyPrudente;
import strategie.StrategyRadar;
import strategie.StrategySelecteur;
import strategie.StrategySelecteurImpl;
import strategie.StrategyToutDroit;
import voiture.Voiture;
import voiture.VoitureFactory;
import jeu.Jeu;
import controleur.Controleur;
import mvc_swing.CircuitObserveur;
import mvc_swing.IHMSwing;
import mvc_swing.ObstaclesObserveur;
import mvc_swing.PointsObserveur;
import mvc_swing.PointsObserveur2;
import mvc_swing.RadarObserveur;
import mvc_swing.SimulationObserveur;
import mvc_swing.TrajectoireObserveur;
import mvc_swing.VoitureObserveur;

public class InterfaceGraphique extends JPanel {
	private IHMSwing ihm;
	private Controleur controleur;
	private Jeu jeu;
	private ArrayList<JCheckBox> listeStrategy;
	private ArrayList<JCheckBox> listeObserveurs;
	
	private ArrayList<Vecteur> listePoints;
	private ArrayList<Vecteur> listeObstacles;
	
	private JSlider sliderFaisseaux;
	private JSlider sliderAngle;
	
	private JButton boutonDemarrer;
	private JButton boutonPlayPause;
	private JButton boutonArret;
	private JButton boutonPoint;
	private JButton boutonPiloter;
	private JButton boutonObstacles;
	
	public InterfaceGraphique(Controleur controleur){
		this.controleur=controleur;
		ihm=new IHMSwing();
		listePoints = new ArrayList<Vecteur>();
		listeObstacles = new ArrayList<Vecteur>();
		try {
		      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		    }
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		//Combo box pour les circuit:
		String[] nomCircuit={"1_safe.trk","2_safe.trk","3_safe.trk","4_safe.trk","5_safe.trk","6_safe.trk","7_safe.trk","8_safe.trk","labyperso.trk","labymod.trk","aufeu.trk","bond.trk","perso.trk","t2009.trk","t260_safe.trk"};
		JComboBox comboBoxCircuit= new JComboBox(nomCircuit);
		comboBoxCircuit.addItemListener(controleur);
		comboBoxCircuit.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		JPanel circuitPanel = new JPanel();
		JLabel label= new JLabel("Choix du circuit: ");
		circuitPanel.add(label);
		circuitPanel.add(comboBoxCircuit);
		
		
		//JPanel pour le radar:
		JPanel radarPanel= new JPanel();
		
		// JSlider nombre de faisseaux :
		sliderFaisseaux = new JSlider (JSlider.HORIZONTAL,5,140,30);
		sliderFaisseaux.setMinorTickSpacing(5);
		sliderFaisseaux.setMajorTickSpacing(25);
		sliderFaisseaux.setPaintLabels(true);
		sliderFaisseaux.setPaintTicks(true);
		sliderFaisseaux.addChangeListener(controleur);
		
		JPanel faisseauxPanel=new JPanel();
		faisseauxPanel.add(new JLabel("Nombre de faisseaux du radar: "));
		faisseauxPanel.add(sliderFaisseaux);
		
		//JSlider angle du radar:
		sliderAngle = new JSlider (JSlider.HORIZONTAL,5,25,10);
		sliderAngle.setMinorTickSpacing(1);
		sliderAngle.setMajorTickSpacing(5);
		sliderAngle.setPaintLabels(true);
		sliderAngle.setPaintTicks(true);
		
		JPanel anglePanel=new JPanel();
		anglePanel.add(new JLabel("Champ du radar: "));
		anglePanel.add(sliderAngle);
		
		radarPanel.setLayout(new BorderLayout());
		radarPanel.add(faisseauxPanel, BorderLayout.NORTH);
		radarPanel.add(anglePanel, BorderLayout.SOUTH);
		
		//JPanel Choix des stratégies:
		JPanel strategyPanel=new JPanel();
		strategyPanel.setLayout(new BorderLayout());
		listeStrategy = new ArrayList<JCheckBox>();
		
		JPanel box =new JPanel();
		box.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		box.setLayout(new GridLayout(3,2));
		JCheckBox radar= new JCheckBox("Radar");
		JCheckBox dijkstra= new JCheckBox("Dijkstra");
		JCheckBox points= new JCheckBox("Points");
		JCheckBox arrivee= new JCheckBox("Arrivée");
		JCheckBox pilot= new JCheckBox("Piloter");
		JCheckBox prudent= new JCheckBox("Prudent");
		JCheckBox parabolique= new JCheckBox("Parabolique");
		JCheckBox anticipe= new JCheckBox("Anticipée");
		
			//cochage par défault:
		dijkstra.setSelected(true);
		
		listeStrategy.add(pilot);
		listeStrategy.add(arrivee);
		listeStrategy.add(points);
		listeStrategy.add(prudent);
		listeStrategy.add(parabolique);
		listeStrategy.add(dijkstra);
		listeStrategy.add(radar);
		
		
		for (JCheckBox strategy : listeStrategy){
			box.add(strategy);
		}
		strategyPanel.add(new JLabel("Stratégies: "));
		strategyPanel.add(box);
		
		//JPanel Choix des observeurs:
		JPanel observeurPanel=new JPanel();
		observeurPanel.setLayout(new BorderLayout());
		listeObserveurs = new ArrayList<JCheckBox>();
		
		JPanel box2 =new JPanel();
		box2.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		box2.setLayout(new GridLayout(3,2));
		JCheckBox rad= new JCheckBox("Radar");
		JCheckBox voiture= new JCheckBox("Voiture");
		JCheckBox trajectoire= new JCheckBox("Trajectoire");
		
		
			//cochage par défault:
		voiture.setSelected(true);
		
		listeObserveurs.add(rad);
		listeObserveurs.add(trajectoire);
		listeObserveurs.add(voiture);
		
		for (JCheckBox strategy : listeObserveurs){
			box2.add(strategy);
		}
		observeurPanel.add(new JLabel("Affichages: "));
		observeurPanel.add(box2);
		
		//Panel des JCheckBox:
		
		JPanel checkboxPanel= new JPanel();
		checkboxPanel.setLayout(new BorderLayout());
		checkboxPanel.add(observeurPanel, BorderLayout.NORTH);
		checkboxPanel.add(strategyPanel, BorderLayout.SOUTH);
		
		// boutons :
		
		JPanel boutonPanel = new JPanel();
		boutonPanel.setLayout(new GridLayout(5,2));
		
		JButton boutonDemarrer = new JButton ("  Démarrer",new ImageIcon ("feuVert.png"));
		boutonDemarrer.addActionListener(controleur);
		
		JButton boutonPlayPause= new JButton("  Play/Pause",new ImageIcon ("PlayPause.png"));
		boutonPlayPause.addActionListener(controleur);
		
		JButton boutonArret= new JButton("  Arrêter",new ImageIcon ("feuRouge.png"));
		boutonArret.addActionListener(controleur);
		
		JButton boutonPoint = new JButton ("Ajouter Point",new ImageIcon ("pluspoint.png"));
		boutonPoint.addActionListener(controleur);
		
		JButton boutonSupPoint = new JButton ("  Supprimer Points",new ImageIcon ("corbeille.png"));
		boutonSupPoint.addActionListener(controleur);
		
		
		JButton boutonPiloter = new JButton("  Piloter",new ImageIcon ("jeu.png"));
		boutonPiloter.addActionListener(controleur);
		
		JButton boutonObstacles = new JButton("  Ajouter Obstacle", new ImageIcon ("obs.png"));
		boutonObstacles.addActionListener(controleur);
		
		JButton boutonSupObstacles = new JButton("  Supprimer Obstacles",new ImageIcon ("corbeille.png"));
		boutonSupObstacles.addActionListener(controleur);
		
		//boutonDemarrer.setEnabled(false);
		//boutonPlayPause.setEnabled(false);
		//boutonArret.setEnabled(false);
		//boutonPoint.setEnabled(false);
		
		boutonPanel.add(boutonDemarrer);
		boutonPanel.add(boutonPlayPause);
		boutonPanel.add(boutonArret);
		boutonPanel.add(boutonPiloter);
		
		boutonPanel.add(boutonPoint);
		boutonPanel.add(boutonObstacles);
		
		boutonPanel.add(boutonSupPoint);
		boutonPanel.add(boutonSupObstacles);
		
		
		//Panel principal:
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel pan =new JPanel();
		pan.setLayout(new BorderLayout());
		pan.add(circuitPanel, BorderLayout.NORTH);
		pan.add(boutonPanel, BorderLayout.SOUTH);
		
		panel.add(pan, BorderLayout.NORTH);
		panel.add(checkboxPanel, BorderLayout.CENTER);
		panel.add(radarPanel,BorderLayout.SOUTH);
		
		panel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		
		this.setLayout(new BorderLayout());
		this.add(ihm, BorderLayout.CENTER);
		this.add(panel, BorderLayout.EAST);
		add(ihm);
		controleur.setPanel(this);
		ihm.setPreferredSize(new Dimension(768, 1024));
	}
	
	public void launch(){
		jeu=controleur.getJeu();
		
		Voiture v = VoitureFactory.buildVoiture(jeu.getCircuit());
		jeu.setVoiture(v);
		
		
		double [] thetas= RadarClassique.generateThetas(sliderFaisseaux.getValue(), (sliderAngle.getValue())/10.0);
		
		StrategySelecteur s= new StrategySelecteurImpl();
		jeu.setStrategy(s);
		
		
		
		
		
		Simulation simu= new Simulation(jeu.getCircuit(),jeu.getVoiture(),s);
		simu.add(ihm);
		jeu.setSimulation(simu);
		ihm.init();
		
		 
		ihm.add(new CircuitObserveur(jeu.getCircuit()));
		//ihm.add(new DijkstraObserveur(c));
		//ihm.add(new TrajectoireObserveur(simu));
		//ihm.add(new RadarObserveur(v,r));
		//ihm.add(new VoitureObserveur(v));
		ihm.add(new PointsObserveur2(listePoints));
		ihm.add(new ObstaclesObserveur(listeObstacles));
		
		//Ajout des Strategies en fonction du JcheckBox des strategies:
		for(JCheckBox checkbox : this.getListeStrategy()){
			String c = checkbox.getText();
			if (checkbox.isSelected()){
				if (c.compareTo("Pilot")==0){
					//RadarClassique r=new RadarClassique( jeu.getVoiture(),jeu.getCircuit(),thetas);
					//jeu.setRadar(r);
					s.add(new StrategyPilot(jeu.getVoiture()), new SelecteurRadar());
				}
				if (c.compareTo("Arrivée")==0){
					s.add(new StrategyArrivee(),new SelecteurArrivee(jeu.getVoiture(),jeu.getCircuit()));
				}
				if (c.compareTo("Point")==0){
					StrategyPoint sp = new StrategyPoint(jeu.getVoiture(),listePoints);
					s.add(new StrategyPoint(jeu.getVoiture(), listePoints), new SelecteurPoint(jeu.getVoiture(), jeu.getCircuit(), sp));
					
					ihm.add(new PointsObserveur(sp));
				}
				if (c.compareTo("Prudent")==0){
					RadarDijkstra r= new RadarDijkstra (jeu.getVoiture(),jeu.getCircuit(),thetas);
					double []t ={-Math.PI/2,0,Math.PI/2};
					RadarPrudent rp= new RadarPrudent(jeu.getVoiture(),jeu.getCircuit(),t);
					jeu.setRadar(r);
					s.add(new StrategyPrudente(r,rp),new SelecteurRadar());
				}
				if (c.compareTo("Parabolique")==0){
					RadarDijkstra r= new RadarDijkstra (jeu.getVoiture(),jeu.getCircuit(),thetas);
					RadarParabolique rp= new RadarParabolique(jeu.getVoiture(),jeu.getCircuit(),thetas);
					jeu.setRadar(r);
					s.add(new StrategyParabolique(r,rp),new SelecteurRadar());
				}
				if (c.compareTo("Dijkstra")==0){
					RadarDijkstra r= new RadarDijkstra (jeu.getVoiture(),jeu.getCircuit(),thetas);
					jeu.setRadar(r);
					s.add(new StrategyRadar(r),new SelecteurRadar());
				}
				
				if (c.compareTo("Radar")==0){
					RadarClassique r=new RadarClassique( jeu.getVoiture(),jeu.getCircuit(),thetas);
					jeu.setRadar(r);
					s.add(new StrategyRadar(r), new SelecteurRadar());
				}
				
			}
		}
		//Ajout des vues en fonction du JCheckBox des vues:
		for(JCheckBox checkbox : this.getListeObserveurs()){
			if (checkbox.isSelected()){
				if (checkbox.getText().compareTo("Radar")==0){
					ihm.add(new RadarObserveur(jeu.getVoiture(), jeu.getRadar()));
				}
				if (checkbox.getText().compareTo("Trajectoire")==0){
					ihm.add(new TrajectoireObserveur(jeu.getSimulation()));
				}
				if (checkbox.getText().compareTo("Voiture")==0){
					ihm.add(new VoitureObserveur(jeu.getVoiture()));
				}
			}
		}
		//ihm.add(new VoitureObserveur(jeu.getVoiture()));
		ihm.add(new SimulationObserveur(simu,jeu.getVoiture()));
		
		Thread t = new Thread(){
			public void run(){
				jeu.play();
			}
		};
		t.start();
	}
	
	
	public ArrayList<JCheckBox> getListeStrategy(){
		return listeStrategy;
	}
	
	public ArrayList<JCheckBox> getListeObserveurs(){
		return listeObserveurs;
	}
	
	public void clearListePoints(){
		listePoints.clear();
	}
	public void clearListeObstacles(){
		listeObstacles.clear();
	}
	public IHMSwing getIhm(){
		return ihm;
	}
	public ArrayList<Vecteur> getListePoints(){
		return listePoints;
	}
	public ArrayList<Vecteur> getListeObstacles(){
		return listeObstacles;
	}
	
	public JButton getBoutonDemarrer(){
		return boutonDemarrer;
	}
	public JButton getBoutonPlayPause(){
		return boutonPlayPause;
	}
	public JButton getBoutonArret(){
		return boutonArret;
	}
	public JButton getBoutonPoints(){
		return boutonPoint;
	}
}
