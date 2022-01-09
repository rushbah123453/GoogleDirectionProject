package com.maps.direction;

import java.util.ArrayList;
import java.util.List;

/**
 * Methods to encode and decode a polyline with Google polyline encoding/decoding scheme.
 * @see <a href="https://developers.google.com/maps/documentation/utilities/polylinealgorithm">Google polyline algorithm</a>
 */
public class PolylineEncoder {

    public static List<LatLng> decode(final String encodedPath) {

        int len = encodedPath.length();

        final List<LatLng> path = new ArrayList<>(len / 2);
        int index = 0;
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int result = 1;
            int shift = 0;
            int b;
            do {
                b = encodedPath.charAt(index++) - 63 - 1;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lat += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            result = 1;
            shift = 0;
            do {
                b = encodedPath.charAt(index++) - 63 - 1;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lng += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            path.add(new LatLng(lat * 1e-5, lng * 1e-5));
        }

        return path;
    }

    public void getDirections(double sourceLat,double sourceLon,double targetLat,double targetLng) {
      JSONParser res=new JSONParser();
      String polyline=res.getPolyLine("https://maps.googleapis.com/maps/api/directions/json?destination="+targetLat+","+targetLng+"&origin="+sourceLat+","+sourceLon+"&key=AIzaSyAEQvKUVouPDENLkQlCF6AAap1Ze-6zMos");
       List<LatLng> latLngList= decode(polyline);
      // sourceLat=latLngList.get(0).lat;
     //  sourceLon=latLngList.get(0).lng;
       GpsLocations gpsLocations=new GpsLocations();
       for (int i=0;i<latLngList.size();i++){
           // System.out.println(latLngList.get(i));
             targetLat=latLngList.get(i).lat;
             targetLng=latLngList.get(i).lng;
            gpsLocations.getLatLngPoints(sourceLat,sourceLon,targetLat,targetLng);
           sourceLat=targetLat;
           sourceLon=targetLng;
        }
    }
}








