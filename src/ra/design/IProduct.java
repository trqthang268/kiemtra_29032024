package ra.design;

import ra.entity.Product;

public interface IProduct extends IGenericDesign<Product,String>{
    void sortByPrice();
    void searchProductByName();
    void searchProductInRange();
}
