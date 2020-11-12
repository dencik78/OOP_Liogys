package Backend;

public class item {
    private int id;
    private String name;
    private double price;
    private String description;
    private String images;


    public item(int id,String name,double price,String description,String images){
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.images = images;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImages() {
        return images;
    }
}
