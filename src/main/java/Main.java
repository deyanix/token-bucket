import java.util.Random;

public class Main {
	private static final Random RANDOM = new Random(12213542552L);

	public static void main(String[] args) {
		TokenBucketSimulationConfiguration configuration = new TokenBucketSimulationConfiguration()
				.setBucketCapacity(100)
				.setBucketRefillRate(500)
				.setPacketArrivalRate(500)
				.setServiceRate(1000);
		TokenBucketSimulation simulation = new TokenBucketSimulation(configuration);
		simulation.run();
		System.out.printf("%f", simulation.getTransmittedPacketsRatio());

//		for (int i = 1; i < 400; i++) {
//			TokenBucketSimulationConfiguration configuration = new TokenBucketSimulationConfiguration()
//					.setBucketCapacity(i)
//					.setPacketArrivalRate(500);
//			TokenBucketSimulation simulation = new TokenBucketSimulation(configuration);
//			simulation.run();
//			if (simulation.getTransmittedPacketsRatio() >= 0.9) {
//				System.out.printf("A %d %f", i, simulation.getTransmittedPacketsRatio());
//				System.out.println();
//				break;
//			}
//		}
//
//		for (int i = 1; i < 400; i++) {
//			TokenBucketSimulationConfiguration configuration = new TokenBucketSimulationConfiguration()
//					.setBucketCapacity(i)
//					.setPacketArrivalRate(400);
//			TokenBucketSimulation simulation = new TokenBucketSimulation(configuration);
//			simulation.run();
//			if (simulation.getTransmittedPacketsRatio() >= 0.9) {
//				System.out.printf("B %d %f", i, simulation.getTransmittedPacketsRatio());
//				System.out.println();
//				break;
//			}
//		}

	}
}
