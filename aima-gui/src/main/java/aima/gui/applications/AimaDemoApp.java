package aima.gui.applications;

import aima.core.environment.map.mapfiles.MapReader;
import aima.core.environment.map.mapfiles.MapReader.MapReaderCallback;
import aima.gui.applications.search.csp.MapColoringApp;
import aima.gui.applications.search.games.ConnectFourApp;
import aima.gui.applications.search.games.EightPuzzleApp;
import aima.gui.applications.search.games.NQueensApp;
import aima.gui.applications.search.games.TicTacToeApp;
import aima.gui.applications.search.map.RouteFindingAgentApp;
import aima.gui.applications.vacuum.VacuumApp;
import aima.gui.demo.agent.TrivialVacuumDemo;
import aima.gui.demo.learning.LearningDemo;
import aima.gui.demo.logic.DPLLDemo;
import aima.gui.demo.logic.FolDemo;
import aima.gui.demo.logic.PLFCEntailsDemo;
import aima.gui.demo.logic.PLResolutionDemo;
import aima.gui.demo.logic.TTEntailsDemo;
import aima.gui.demo.logic.WalkSatDemo;
import aima.gui.demo.probability.ProbabilityDemo;
import aima.gui.demo.search.EightPuzzleDemo;
import aima.gui.demo.search.MapColoringCSPDemo;
import aima.gui.demo.search.NQueensDemo;
import aima.gui.demo.search.NondeterministicVacuumEnvironmentDemo;
import aima.gui.demo.search.TicTacToeDemo;

/**
 * The all-in-one demo application. Shows everything within one frame.
 * 
 * @author Ruediger Lunde
 */
public class AimaDemoApp {

	/** Registers agent applications and console program demos. */
	public static void registerDemos(AimaDemoFrame frame) {
		frame.addApp(VacuumApp.class);
		frame.addApp(RouteFindingAgentApp.class);
		frame.addApp(EightPuzzleApp.class);
		frame.addApp(NQueensApp.class);
		frame.addApp(TicTacToeApp.class);
		frame.addApp(ConnectFourApp.class);
		frame.addApp(MapColoringApp.class);

		frame.addDemo(TrivialVacuumDemo.class);
		
		frame.addDemo(EightPuzzleDemo.class);
		frame.addDemo(TicTacToeDemo.class);
		frame.addDemo(NQueensDemo.class);
		frame.addDemo(MapColoringCSPDemo.class);
		frame.addDemo(NondeterministicVacuumEnvironmentDemo.class);

		frame.addDemo(TTEntailsDemo.class);
		frame.addDemo(PLFCEntailsDemo.class);
		frame.addDemo(PLResolutionDemo.class);
		frame.addDemo(DPLLDemo.class);
		frame.addDemo(WalkSatDemo.class);
		frame.addDemo(FolDemo.class);

		frame.addDemo(ProbabilityDemo.class);

		frame.addDemo(LearningDemo.class);
	}

	/** Starts the demo. */
	public static void main(String[] args) {
		AimaDemoFrame frame = new AimaDemoFrame();
		registerDemos(frame);
		frame.setSize(800, 600);
		frame.setVisible(true);
		
		MapReader reader = new MapReader();
		reader.readFile(0, new MapReaderCallback() {
			
			@Override
			public void onNewDistance(String locationName, int distanceToReferencePosition, int bearingDirections) {
				System.out.println("Location: " + locationName + " { " + distanceToReferencePosition + ", " + bearingDirections + " }");
			}
			
			@Override
			public void onMapName(String mapName) {
				System.out.println("Map name: " + mapName);
			}
			
			@Override
			public void onDirectionalLink(String originName, String destinyName, int distance) {
				System.out.println("Route from " + originName + " to " + destinyName + ": " + distance);
			}
		});
	}
}
