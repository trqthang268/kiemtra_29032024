package ra.implement;

import ra.config.InputMethods;
import ra.design.IProduct;
import ra.entity.Categories;
import ra.entity.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ra.implement.CategoriesImplement.categoriesList;

public class ProductImplement implements IProduct {
    static List<Product> productList = new ArrayList<>();

    @Override
    public void displayAll() {
        for (Product product : productList) {
            product.displayData();
        }
    }

    @Override
    public void addNewElement() {
        System.out.println("Nhập số sản phẩm cần nhập thông tin");
        int numberOfProduct = InputMethods.getInteger();
        for (int i = 0; i < numberOfProduct; i++) {
            System.out.println("Nhập thông tin danh mục thứ "+(i+1));
            Product product = new Product();
            product.inputData(productList,categoriesList);
            productList.add(product);
        }
    }

    @Override
    public void updateElement() {
        System.out.println("Nhập mã sản phẩm muốn sửa đổi");
        String updateId = InputMethods.getString();
        Product update = findById(updateId);
        if (update == null){
            System.err.println("Sản phẩm không tồn tại");
            return;
        }
        System.out.println("Thông tin cũ :");
        update.displayData();
        System.out.println("Nhập thông tin mới");
        update.inputData(productList,categoriesList);
        System.out.println("Cập nhật thông tin thành công");
    }

    @Override
    public void deleteElement() {
        System.out.println("Nhập mã danh mục muốn xóa");
        String deleteId = InputMethods.getString();
        Product delete = findById(deleteId);
        if (delete == null){
            System.err.println("Không tìm thấy mã danh mục");
            return;
        }
        productList.remove(delete);
        System.out.println("Xóa danh mục thành công");

    }

    @Override
    public Product findById(String id) {
        for (Product product : productList) {
            if (product.getProductId().equals(id)){
                return product;
            }
        }
        return null;
    }

    @Override
    public void sortByPrice() {
        System.out.println("Lựa chọn thứ tự sắp xếp");
        System.out.println("1. Sắp xếp giá tăng dần");
        System.out.println("2. Sắp xếp giá giảm dần");
        int choice = InputMethods.getInteger();
        switch (choice){
            case 1:
                productList.sort((o1, o2) -> (int) (o1.getPrice() - o2.getPrice()));
                displayAll();
                break;
            case 2:
                productList.sort((o1, o2) -> (int) (o2.getPrice() - o1.getPrice()));
                displayAll();
                break;
            default:
        }
    }

    @Override
    public void searchProductByName() {
        System.out.println("Nhập tên sản phẩm cần tìm: ");
        String inputName = InputMethods.getString();
        if (productList.stream().anyMatch(product -> product.getProductName().equals(inputName))){
            productList.stream().filter(product -> product.getProductName().equals(inputName)).forEach(Product::displayData);
        } else {
            System.err.println("Không có sản phẩm nào trùng tên");
        }
    }

    @Override
    public void searchProductInRange() {
        System.out.println("Nhập giá tiền tối thiểu");
        float fromPrice = InputMethods.getFloat();
        System.out.println("Nhập giá tiền tối đa");
        float toPrice = InputMethods.getFloat();
        System.out.printf("Sản phẩm có giá trị trong khoảng %f - %f\n",fromPrice,fromPrice);
        boolean isExist = true;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getPrice() >= fromPrice && productList.get(i).getPrice()<=toPrice){
                productList.get(i).displayData();
                isExist = false;
            }
        }
        if (isExist){
            System.out.println("Không có sản phẩm nào trong khoảng giá đó");
        }

    }
}
