package aima.core.environment.map.mapfiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidParameterException;

public class MapReader {
	
	public interface MapReaderCallback {
		void onMapName(String mapName);
		void onNewDistance(String locationName, int distanceToReferencePosition, int bearingDirections);
		void onDirectionalLink(String originName, String destinyName, double distance);
	}
	
	public static enum Map {
		ROMANIA("PartOfRomania.txt");
		
		private final String value;
		Map(String value) {
			this.value = value;
		}
		
		public String getName() {
			return this.value;
		}
	};
	
	public int getNumberOfMaps() {
		return Map.values().length;
	}
	
	private String getNameOfMapFile(int position) {
		if (position < 0 || position > getNumberOfMaps()) {
			throw new NullPointerException();
		}
		
		return Map.values()[position].getName();
	}

	public void readFile(int position, MapReaderCallback callback) {
		if (callback == null) {
			throw new InvalidParameterException();
		}
		
		URL url = getClass().getResource(getNameOfMapFile(position));
		BufferedReader br = null;
		
		
		int currentLine = 0;
		int cityCounter = 0;
		int numberOfcities = 0;
		int routesCounter = 0;
		int numberOfRoutes = 0;
		try {
			
			String sCurrentLine;
			br = new BufferedReader(new FileReader(url.getPath()));
			while ((sCurrentLine = br.readLine()) != null) {
				
				if (currentLine == 0) {
					callback.onMapName(sCurrentLine);
				} else if (currentLine == 1) {
					numberOfcities = Integer.valueOf(sCurrentLine);
				} else if (currentLine - 2 < numberOfcities) {
					String[] params = sCurrentLine.split(" ");
					callback.onNewDistance(params[0], Integer.valueOf(params[1]), Integer.valueOf(params[2]));
					cityCounter++;
				} else if (currentLine - 2 == numberOfcities) {
					numberOfRoutes = Integer.valueOf(sCurrentLine);
				} else {
					String[] params = sCurrentLine.split(" ");
					callback.onDirectionalLink(params[0], params[1], Double.valueOf(params[2]));
					routesCounter++;
				}
				
				currentLine++;
			}
			
			if (numberOfRoutes != routesCounter) {
				throw new IllegalArgumentException("Number of routes provided and actual routes provided differs. Please fix file: " + getNameOfMapFile(position));
			} else if (numberOfcities != cityCounter) {
				throw new IllegalArgumentException("Number of cities provided and actual cities provided differs. Please fix file: " + getNameOfMapFile(position));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

}
