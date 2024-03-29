package ra.entity;

import ra.config.InputMethods;

import java.util.List;
import java.util.Scanner;

public class Categories {
    private int catalogId;
    private String catalogName;
    private String descriptions;
    private boolean catalogStatus;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, String descriptions, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public void inputData(boolean isAdd, List<Categories> categoriesList) {
        if (isAdd) {
            this.catalogId = inputCatalogId(categoriesList);
        }
        this.catalogName = inputCatalogName(categoriesList);
        this.descriptions = inputDescriptions();
        this.catalogStatus = inputCatalogStatus();
    }

    public int inputCatalogId(List<Categories> categoriesList) {
        if (categoriesList.isEmpty()) {
            return 1;
        } else {
            int max = categoriesList.get(0).getCatalogId();
            for (int i = 1; i < categoriesList.size(); i++) {
                if (categoriesList.get(i).getCatalogId() > max) {
                    max = categoriesList.get(i).getCatalogId();
                }
            }
            return max + 1;
        }
    }

    public String inputCatalogName(List<Categories> categoriesList) {
        System.out.println("Nhập tên danh mục");
        do {
            String catalogName = InputMethods.getString();
            if (catalogName.length() <= 50) {
                boolean isExist = true;
                for (int i = 0; i < categoriesList.size(); i++) {
                    if (categoriesList.get(i).getCatalogName().equals(catalogName)) {
                        isExist = false;
                        break;
                    }
                }
                if (!isExist) {
                    System.err.println("Tên danh mục đã tồn tại, vui lòng nhập lại!");
                } else {
                    return catalogName;
                }
            }else{
                System.err.println("Tên danh mục tối đa 50 ký tự , vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputDescriptions(){
        System.out.println("Nhập mô tả danh mục");
        return InputMethods.getString();
    }

    public boolean inputCatalogStatus(){
        System.out.println("Nhập vào trạng thái danh mục");
        do {
            String status = InputMethods.getString();
            if (status.equals("true") || status.equals("false")) {
                return Boolean.parseBoolean(status);
            } else {
                System.err.println("Trạng thái danh mục chỉ nhận true hoặc false, vui lòng nhập lại");
            }
        } while (true);
    }

    public void displayData() {
        System.out.printf("Mã DM: %d - Tên DM: %s - Mô tả: %s - Trạng thái: %s\n",
                this.catalogId, this.catalogName, this.descriptions, this.catalogStatus ? "Hoạt động" : "Không hoạt động");
    }
}

