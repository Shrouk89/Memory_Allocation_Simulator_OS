public class Partition {
    private String partitionName;
    private int size;
    private boolean isFree = true;
    private Process process;
    public static int ID = 0;

    public Partition(String partitionName, int size, boolean isFree) {
        this.partitionName = partitionName;
        this.size = size;
        this.isFree = isFree;
        ID++;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public String getPartitionName() {
        return partitionName;
    }

    public void setPartitionName(String partitionName) {
        this.partitionName = partitionName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
