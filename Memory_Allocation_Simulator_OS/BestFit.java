import java.util.ArrayList;

public class BestFit extends Policy {
    @Override
    public boolean allocateMemory(Process process, ArrayList<Partition> partitions) {
        int idx = -1;
        int minimumFragmentation = Integer.MAX_VALUE;
        for (int i = 0; i < partitions.size(); i++) {
            int fragmentation = partitions.get(i).getSize() - process.getSize();
            if (partitions.get(i).getSize() >= process.getSize() && fragmentation < minimumFragmentation
                    && partitions.get(i).isFree()) {
                minimumFragmentation = fragmentation;
                idx = i;
            }
        }
        if (idx != -1) {
            if (partitions.get(idx).getSize() - process.getSize() > 0)
                partitions.add(idx + 1, new Partition("Partition " + String.valueOf(Partition.ID), partitions.get(idx).getSize() - process.getSize(), true));
            partitions.get(idx).setFree(false);
            partitions.get(idx).setSize(process.getSize());
            partitions.get(idx).setProcess(process);
            return true;
        }
        return false;
    }
}
