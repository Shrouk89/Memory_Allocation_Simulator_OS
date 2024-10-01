import java.util.ArrayList;

public class FirstFit extends Policy {
    @Override
    public boolean allocateMemory(Process process, ArrayList<Partition> partitions) {
        for (int i = 0; i < partitions.size(); i++) {
            if (partitions.get(i).getSize() >= process.getSize() && partitions.get(i).isFree()) {
                Partition p = new Partition("Partition " + String.valueOf(Partition.ID),
                        partitions.get(i).getSize() - process.getSize(), true);
                if (p.getSize() > 0)
                    partitions.add(i + 1, p);
                partitions.get(i).setFree(false);
                partitions.get(i).setSize(process.getSize());
                partitions.get(i).setProcess(process);
                return true;
            }
        }
        return false;
    }
}