public class TokenBucketSimulationConfiguration {
	private long bucketCapacity = 105L;
	private double bucketRefillRate = 5d;
	private long bucketRefillAmount = 1L;
	private double packetArrivalRate = 500d;
	private long packetSize = 100L;
	private double simulationEndTime = 100d;
	private double serviceRate = 100d;

	public long getBucketCapacity() {
		return bucketCapacity;
	}

	public TokenBucketSimulationConfiguration setBucketCapacity(long bucketCapacity) {
		this.bucketCapacity = bucketCapacity;
		return this;
	}

	public double getBucketRefillRate() {
		return bucketRefillRate;
	}

	public TokenBucketSimulationConfiguration setBucketRefillRate(double bucketRefillRate) {
		this.bucketRefillRate = bucketRefillRate;
		return this;
	}

	public long getBucketRefillAmount() {
		return bucketRefillAmount;
	}

	public TokenBucketSimulationConfiguration setBucketRefillAmount(long bucketRefillAmount) {
		this.bucketRefillAmount = bucketRefillAmount;
		return this;
	}

	public double getPacketArrivalRate() {
		return packetArrivalRate;
	}

	public TokenBucketSimulationConfiguration setPacketArrivalRate(double packetArrivalRate) {
		this.packetArrivalRate = packetArrivalRate;
		return this;
	}

	public double getEndTime() {
		return simulationEndTime;
	}

	public TokenBucketSimulationConfiguration setSimulationEndTime(double simulationEndTime) {
		this.simulationEndTime = simulationEndTime;
		return this;
	}

	public double getServiceRate() {
		return serviceRate;
	}

	public TokenBucketSimulationConfiguration setServiceRate(double serviceRate) {
		this.serviceRate = serviceRate;
		return this;
	}

	public long getPacketSize() {
		return packetSize;
	}

	public TokenBucketSimulationConfiguration setPacketSize(long packetSize) {
		this.packetSize = packetSize;
		return this;
	}
}
