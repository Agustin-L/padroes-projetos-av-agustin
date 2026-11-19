public interface CloudFactory {
    VirtualMachine createVirtualMachine();
    Storage createStorage(double gigabytes);
    MessageQueue createMessageQueue();

    String getProviderName();

    double getVirtualMachineHourlyCost();
    double getStorageCostPerGb();
    double getQueueMonthlyCost();
}