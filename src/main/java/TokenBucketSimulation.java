import java.util.LinkedList;

public class TokenBucketSimulation {
	private final TokenBucket bucket;
	private final LinkedList<Long> arrivalTimes = new LinkedList<>();
	private final TokenBucketSimulationConfiguration configuration;
	private final PoissonRandomizer randomizer;
	private double now = 0;
	private double nextRefill = 0;
	private double nextArrival = 0;
	private double nextDeparture = Long.MAX_VALUE;
	private long waiting = 0;

	private long arrivalPackets = 0, droppedPackets = 0, transmittedPackets = 0;

	public TokenBucketSimulation(TokenBucketSimulationConfiguration configuration) {
		this.bucket = new TokenBucket(configuration.getBucketCapacity());
		this.configuration = configuration;
		this.randomizer = new PoissonRandomizer(configuration.getPacketArrivalRate());
	}

	public void generateArrivals() {
		long t = 0;
		while (t < configuration.getEndTime()) {
			t += randomizer.next();
			arrivalTimes.add(t);
		}
		randomizer.showDistribution();
	}

	public void run() {
		while (now < configuration.getEndTime()) {
			if (nextRefill < nextArrival && nextRefill < nextDeparture) {
				now = nextRefill;
				nextRefill += 1/configuration.getBucketRefillRate();

				bucket.refill(configuration.getBucketRefillAmount());
				System.out.printf("Refill bucket (nextRefill=%f)", nextRefill);
				System.out.println();
			} else if (nextArrival < nextDeparture) {
				now = nextArrival;
				nextArrival += 1d/randomizer.next();

				if (waiting == 0) {
					nextDeparture = now + configuration.getPacketSize()/configuration.getServiceRate();
				}
				arrivalPackets++;
				waiting++;
				System.out.print("Arrival packet");
				System.out.printf("(waiting=%d, tokens=%d, nextArrival=%f, nextDeparture=%f)", waiting, bucket.getKept(), nextArrival, nextDeparture);
				System.out.println();
			} else {
				now = nextDeparture;

				if (bucket.consume(configuration.getPacketSize())) {
					transmittedPackets++;
				} else {
					droppedPackets++;
				}
				waiting--;

				if (waiting > 0) {
					nextDeparture = now + configuration.getPacketSize()/configuration.getServiceRate();
				} else {
					nextDeparture = Long.MAX_VALUE;
				}

				System.out.print("Departure packet");
				System.out.printf("(waiting=%d, tokens=%d, nextDeparture=%f)", waiting, bucket.getKept(), nextDeparture);
				System.out.println();
			}
		}
	}

	public long getDroppedPackets() {
		return droppedPackets;
	}

	public long getTransmittedPackets() {
		return transmittedPackets;
	}

	public long getArrivalPackets() {
		return arrivalPackets;
	}

	public double getTransmittedPacketsRatio() {
		return ((double) getTransmittedPackets()) / ((double) getArrivalPackets());
	}
}
