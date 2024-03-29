package ra.implement;

import ra.config.InputMethods;
import ra.design.ICategories;
import ra.entity.Categories;

import java.util.ArrayList;
import java.util.List;

import static ra.implement.ProductImplement.productList;

public class CategoriesImplement implements ICategories {
    static List<Categories> categoriesList = new ArrayList<>();

    @Override
    public void displayAll() {
        System.out.println("Danh sách danh mục");
        for (Categories categories : categoriesList) {
            categories.displayData();
        }
    }

    @Override
    public void addNewElement() {
        System.out.println("Nhập số danh mục cần nhập thông tin:");
        int numberOfCategories = InputMethods.getInteger();
        for (int i = 0; i < numberOfCategories; i++) {
            System.out.println("Nhập thông tin danh mục thứ "+(i+1));
            Categories categories = new Categories();
            categories.inputData(true, categoriesList);
            categoriesList.add(categories);
        }
    }

    @Override
    public void updateElement() {
        System.out.println("Nhập vào mã danh mục cần cập nhật:");
        int updateId = InputMethods.getInteger();
        Categories update = findById(updateId);
        if (update == null){
            System.err.println("Không tìm thấy mã danh mục");
            return;
        }
        System.out.println("Thông tin cũ của danh mục");
        update.displayData();
        System.out.println("Nhập thông tin mới");
        update.inputData(false, categoriesList);
        System.out.println("Cập nhật thông tin thành công");
    }

    @Override
    public void deleteElement() {
        System.out.println("Nhập mã danh mục muốn xóa");
        int deleteId = InputMethods.getInteger();
        Categories delete = findById(deleteId);
        if (delete == null){
            System.err.println("Không tìm thấy mã danh mục");
            return;
        }
        if (productList.stream().anyMatch(product -> product.getCatalogId() == deleteId)){
            System.err.println("Danh mục có sản phẩm, không thể xóa");
            return;
        }
        categoriesList.remove(delete);
        System.out.println("Xóa danh mục thành công");

    }

    @Override
    public Categories findById(Integer id) {
        for (Categories categories : categoriesList) {
            if (categories.getCatalogId() == id){
                return categories;
            }
        }
        return null;
    }

    @Override
    public void updateCategorieStatus() {
        System.out.println("Nhập vào mã danh mục muốn cập nhật trạng thái");
        int catalogId = InputMethods.getInteger();
        Categories updateStatus = findById(catalogId);
        if (updateStatus == null){
            System.err.println("Không tìm thấy mã danh mục");
            return;
        }
        updateStatus.setCatalogStatus(!updateStatus.isCatalogStatus());
        System.out.println("Cập nhật trạng thái thành công");


    }
}
