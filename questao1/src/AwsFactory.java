public class AwsFactory implements CloudFactory {

    @Override
    public VirtualMachine createVirtualMachine() {
        return new AwsVirtualMachine();
    }

    @Override
    public Storage createStorage(double gigabytes) {
        return new AwsStorage(gigabytes);
    }

    @Override
    public MessageQueue createMessageQueue() {
        return new AwsMessageQueue();
    }

    @Override
    public String getProviderName() {
        return "AWS";
    }

    @Override
    public double getVirtualMachineHourlyCost() {
        return 0.10;
    }

    @Override
    public double getStorageCostPerGb() {
        return 0.023;
    }

    @Override
    public double getQueueMonthlyCost() {
        return 0.40;
    }
}