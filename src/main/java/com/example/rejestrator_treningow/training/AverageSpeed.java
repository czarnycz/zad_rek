package com.example.rejestrator_treningow.training;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AverageSpeed {

    public double calculateAverageOfSpeed(String timeInHHmmss, double distance) {
        String[] split = timeInHHmmss.split(" ");
        long seconds = 0;

        for (String timestr : split) {
            String[] hhmmss = timestr.split(":");
            long currentSeconds = 0;

            currentSeconds += Integer.valueOf(hhmmss[0]) * 60 * 60;
            currentSeconds += Integer.valueOf(hhmmss[1]) * 60;
            currentSeconds += Integer.valueOf(hhmmss[2]);

            seconds += currentSeconds;
        }
        return (distance / seconds * 3600);


    }

}
