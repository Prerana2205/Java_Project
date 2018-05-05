public class Item {
    public int ItemId;
    public String Description;
    public double Price;
    public int Quantity;
    public int Threshold;

    public Item(String line) {
        String[] fields = line.split(" ");

        ItemId = Integer.parseInt(fields[0]);
        Description = fields[1];
        Price = Double.parseDouble(fields[2]);
        Quantity = Integer.parseInt(fields[3]);
        Threshold = Integer.parseInt(fields[4]);
    }
}
