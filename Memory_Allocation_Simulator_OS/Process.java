public class Process {
    private String processName;
    private int size;
    public Process(String processName, int size) {
        this.processName = processName;
        this.size = size;
    }

    public String getProcessName() {
        return processName;
    }
    public void setProcessName(String processName) {
        this.processName = processName;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
}
