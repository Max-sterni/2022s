package computer;

public class HardDisk extends Component {

    public enum disk_type_t {
        HDD, SSD
    }

    private disk_type_t disk_type;
    private int storage_capacity;
    private double data_write_rate;

    public HardDisk(String name, int price, disk_type_t disk_type, int storage_capacity, double data_write_rate) {
        super(name, price);
        this.disk_type = disk_type;
        this.storage_capacity = storage_capacity;
        this.data_write_rate = data_write_rate;
    }

    public void printInfo() {
        String disk_type_string = "";
        switch (disk_type) {
            case HDD:
                disk_type_string = "HDD";
                break;
            case SSD:
                disk_type_string = "SSD";
                break;
        }
        System.out.println("Hard Disk: " + name + " : " + disk_type_string + " Price: " + price + " Storage: "
                + storage_capacity + " Data Write Rate: " + data_write_rate);
    }

    public double estimateDataTransferDuration(double dataToTransferInGB) {
        return dataToTransferInGB / data_write_rate;
    }

}
