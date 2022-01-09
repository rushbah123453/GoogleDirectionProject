package com.maps.direction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DirectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DirectionApplication.class, args);
		PolylineEncoder polylineEncoder=new PolylineEncoder();
		Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
		System.out.print("Enter Source lat - ");
		double sourceLat= sc.nextDouble();
		System.out.print("Enter Source Lon - ");
		double sourceLon= sc.nextDouble();

		System.out.print("Enter Target lat - ");
		double targetLat= sc.nextDouble();
		System.out.print("Enter Target Lon - ");
		double targetLon= sc.nextDouble();
		polylineEncoder.getDirections(sourceLat,sourceLon,targetLat,targetLon);
	}

}
