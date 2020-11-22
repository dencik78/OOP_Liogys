package Backend;

public class item {
    private int id;
    private String title;
    private String price;
    private String description;
    private String images;


    public item(int id,String name,String price,String description,String images){
        this.id = id;
        this.title = name;
        this.price = price;
        this.description = description;
        this.images = images;

    }
    public item(String name,String price,String description,String images){

        this.title = name;
        this.price = price;
        this.description = description;
        this.images = images;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImages() {
        return images;
    }
}
