package eu.deyanix.tokenbucket;

import eu.deyanix.tokenbucket.randomizer.ExponentialRandomizer;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Main {
	public static void histogram() {
		int length = 1_000_000;
		List<Double> values = new ArrayList<>(length);
		ExponentialRandomizer randomizer = new ExponentialRandomizer(0.25);
		for (int i = 0; i < length; i++) {
			values.add(randomizer.next());
		}

		double min = Math.floor(Collections.min(values));
		double step = 0.5;

		values.stream()
				.map((val) -> (int) Math.floor((val - min) / step))
				.map((val) -> ((2*val + 1) / 2d) * step)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.forEach((bar, occurs) -> System.out.printf("%f;%d\n\r", bar, occurs));
	}

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 500; j++) {
				TokenBucketSimulationConfiguration configuration = new TokenBucketSimulationConfiguration()
						.setSimulationEndTime(100)
						.setBucketCapacity(100 + j*10)
						.setBucketRefillRate(48000 + i*50)
						.setPacketArrivalRate(500)
						.setPacketSize(100);
				TokenBucketSimulation simulation = new TokenBucketSimulation(configuration);
				simulation.run();
				if (simulation.getTransmittedPacketsRatio() >= 0.9) {
					System.out.printf("%f;%f;%d", simulation.getTransmittedPacketsRatio(), configuration.getBucketRefillRate(), configuration.getBucketCapacity());
					System.out.println();
					break;
				}
			}
		}
	}
}
