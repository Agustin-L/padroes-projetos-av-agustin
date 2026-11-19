public class AzureFactory implements CloudFactory {

    @Override
    public VirtualMachine createVirtualMachine() {
        return new AzureVirtualMachine();
    }

    @Override
    public Storage createStorage(double gigabytes) {
        return new AzureStorage(gigabytes);
    }

    @Override
    public MessageQueue createMessageQueue() {
        return new AzureMessageQueue();
    }

    @Override
    public String getProviderName() {
        return "Azure";
    }

    @Override
    public double getVirtualMachineHourlyCost() {
        return 0.12;
    }

    @Override
    public double getStorageCostPerGb() {
        return 0.02;
    }

    @Override
    public double getQueueMonthlyCost() {
        return 0.50;
    }
}