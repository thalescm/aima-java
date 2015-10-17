package aima.core.environment.map;

import aima.core.environment.map.mapfiles.MapReader;
import aima.core.environment.map.mapfiles.MapReader.MapReaderCallback;

public class FileMap {
	
	public FileMap(final ExtendableMap map, int position, MapReader reader) {
		map.clear();
		reader.readFile(0, new MapReaderCallback() {
			
			@Override
			public void onNewDistance(String locationName, int distanceToReferencePosition, int bearingDirections) {
//				System.out.println("Location: " + locationName + " { " + distanceToReferencePosition + ", " + bearingDirections + " }");
				map.setDistAndDirToRefLocation(locationName, distanceToReferencePosition, bearingDirections);
			}
			
			@Override
			public void onMapName(String mapName) {
//				System.out.println("Map name: " + mapName);
			}
			
			@Override
			public void onDirectionalLink(String originName, String destinyName, double distance) {
//				System.out.println("Route from " + originName + " to " + destinyName + " and back: " + distance);
				map.addBidirectionalLink(originName, destinyName, distance);
			}
		});
	}
}
