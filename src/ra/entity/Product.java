package ra.entity;

import ra.config.InputMethods;

import java.util.*;

public class Product {
    private String productId;
    private String productName;
    private float price;
    private String description;
    private Date created;
    private int catalogId;
    private ProductStatus status;

    public enum ProductStatus {
        ACTIVE("đang bán"),
        BLOCK("hết hàng"),
        INACTIVE("không bán");

        private String status;

        private ProductStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }
    public Product(String productId, String productName, float price, String description, Date created, int catalogId, ProductStatus status) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.created = created;
        this.catalogId = catalogId;
        this.status = status;
    }

    public Product() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }


    public void inputData(List<Product> productList,List<Categories> categoriesList){
        this.productId = inputProductId(productList);
        this.productName = inputProductName(productList);
        this.price = inputProductPrice();
        this.description = inputDescription();
        this.created = inputCreated();
        this.catalogId = inputCatalogId(categoriesList);
        this.status = inputStatus();
    }

    private ProductStatus inputStatus() {
        do {
            System.out.println("Nhập trạng thái cho sản phẩm(Active/Block/Inactive:");
            String inputStatus = InputMethods.getString();
            if (inputStatus.equalsIgnoreCase("ACTIVE")) {
                return ProductStatus.ACTIVE;
            } else if (inputStatus.equalsIgnoreCase("INACTIVE")) {
                return ProductStatus.INACTIVE;
            } else if (inputStatus.equalsIgnoreCase("BLOCK")) {
                return ProductStatus.BLOCK;
            } else {
                System.err.println("Trạng thái nhập vào không đúng, mời nhập lại.");
            }
        }
        while (true);
    }

    public String inputProductId(List<Product> productList){
        System.out.println("Nhập vào mã sản phẩm");
        String regex = "^[CSA]\\d{3}$";
        do {
            String productId = InputMethods.getString();
            if (productId.matches(regex)){
                boolean isExist = false;
                for (int i = 0; i < productList.size(); i++) {
                    if (productList.get(i).getProductId().equals(productId)){
                        isExist = true;
                        break;
                    }
                }
                if (isExist){
                    System.err.println("Mã danh mục đã tồn tại, vui lòng nhập mã khác");
                }else {
                    return productId;
                }
            }else {
                System.err.println("Mã sản phẩm không đúng định dạng , vui lòng nhập lại!");
            }
        }while (true);
    }

    public String inputProductName(List<Product> productList){
        System.out.println("Nhập vào tên sản phẩm:");
        do {
            String productName = InputMethods.getString();
            if (productName.length() >= 10 && productName.length() <= 50) {
                boolean isExist = false;
                for (int i = 0; i < productList.size(); i++) {
                    if (productList.get(i).getProductName().equals(productName)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại");
                } else {
                    return productName;
                }
            } else {
                System.err.println("Tên sản phẩm có từ 10-50 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public float inputProductPrice() {
        System.out.println("Nhập vào giá sản phẩm:");
        do {
            float price = InputMethods.getFloat();
            if (price > 0) {
                return price;
            } else {
                System.err.println("Giá sản phẩm phải lớn hơn 0, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputDescription(){
        System.out.println("Nhập vào mô tả sản phẩm");
        return InputMethods.getString();
    }

    public Date inputCreated(){
        System.out.println("Nhập vào ngày nhập sản phẩm");
        return InputMethods.getDate();
    }

    public int inputCatalogId(List<Categories> categoriesList) {
        System.out.println("Chọn danh mục của sản phẩm:");
        for (int i = 0; i < categoriesList.size(); i++) {
            if (categoriesList.get(i).isCatalogStatus()) {
                System.out.printf("%d.%s\n", i + 1, categoriesList.get(i).getCatalogName());
            }
        }
        System.out.print("Lựa chọn của bạn: ");
        int choice = InputMethods.getInteger();
        return categoriesList.get(choice - 1).getCatalogId();
    }
    public void displayData() {
        System.out.printf("Mã sản phẩm: %s - Tên sản phẩm: %s - Giá: %f\n", this.productId, this.productName, this.price);
        System.out.printf("Mô tả: %s - Ngày nhập: %s\n", this.description, this.created.toString());
        System.out.printf("Danh mục: %d - Trạng thái: %s\n", this.catalogId, this.status.getStatus());
    }
}
