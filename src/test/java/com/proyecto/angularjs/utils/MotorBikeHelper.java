package com.proyecto.angularjs.utils;

import java.util.LinkedList;
import java.util.List;

import com.proyecto.angularjs.models.MotorBike;

public class MotorBikeHelper {

	public static List<MotorBike> createMotorBikeList(int howMany) {
        List<MotorBike> list = new LinkedList<>();
        for (int i = 0; i < howMany; i++) {
            list.add(new MotorBike("Yamaha " + i, "y843 " + i, 0.0,0.0,false));
        }

		return list;
	}

	public static MotorBike createMotorBike() {
		MotorBike motorBike = new MotorBike("Yamaha 1", "y843 1",0.0,0.0,false);
		return motorBike;
	}

}