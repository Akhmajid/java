package jeu;

import radar.Radar;
import simulation.Simulation;
import strategie.Strategy;
import terrain.Circuit;
import voiture.Voiture;
import voiture.VoitureException;
import mvc.UpdateEventListener;
import mvc.UpdateEventSender;

public class Jeu implements UpdateEventSender {

	private Voiture v;
	private Circuit c;
	private Strategy s;
	private Radar r;
	private Simulation simu;

	public Jeu() {
		super();
		this.v = null;
		this.c = null;
		this.s = null;
		this.r = null;
		this.simu = null;
	}

	public Jeu(Voiture v, Circuit c, Strategy s, Radar r,
			Simulation simu) {
		super();
		this.v = v;
		this.c = c;
		this.s = s;
		this.r = r;
		this.simu = simu;
	}

	public Voiture getVoiture() {
		return v;
	}

	public void setVoiture(Voiture v) {
		this.v = v;
	}

	public Circuit getCircuit() {
		return c;
	}

	public void setCircuit(Circuit c) {
		this.c = c;
	}

	public Strategy getStrategy() {
		return s;
	}

	public void setStrategy(Strategy s) {
		this.s = s;
	}

	public Radar getRadar() {
		return r;
	}

	public void setRadar(Radar r) {
		this.r = r;
	}

	public Simulation getSimulation() {
		return simu;
	}

	public void setSimulation(Simulation simu) {
		this.simu = simu;
	}

	
	public void add(UpdateEventListener listener) {
		simu.add(listener);
	}

	
	public void update() {
		simu.update();
	}

	public void play() {
		Thread t = new Thread() {
			public void run() {
				try {
					simu.run();
				} catch (VoitureException e) {
					e.printStackTrace();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		};
		t.start();
	}
	public void interrupt(){
		if ((simu!=null)&& (simu.getPlay()==true)){
			simu.pause();
		}
	}
	
	

	

}
