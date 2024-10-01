import java.util.ArrayList;

public abstract class Policy {
    public abstract boolean allocateMemory(Process process, ArrayList<Partition> partitions);

    public void compaction(ArrayList<Partition> partitions) {
        ArrayList<Partition> p2 = new ArrayList<>();
        int fragmentSize = 0;
        for (int i = 0; i < partitions.size(); i++) {
            if (partitions.get(i).isFree())
                fragmentSize += partitions.get(i).getSize();
            else
                p2.add(partitions.get(i));
        }
        if (fragmentSize > 0)
            p2.add(new Partition("Partition " + String.valueOf(Partition.ID), fragmentSize, true));
        partitions.clear();
        partitions.addAll(p2);
    }
}
