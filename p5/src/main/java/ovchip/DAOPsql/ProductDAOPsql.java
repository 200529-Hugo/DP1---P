package ovchip.DAOPsql;

import ovchip.dao.ProductDAO;
import ovchip.domain.OVChipkaart;
import ovchip.domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOPsql implements ProductDAO {
    // Attributes
    private Connection conn;

    // Constructor
    public ProductDAOPsql(Connection conn) {
        this.conn = conn;
    }

    // Save a Product to the database
    @Override
    public boolean save(Product product) {
        String query = "INSERT INTO product (product_nummer, naam, beschrijving, prijs) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, product.getProductNummer());
            pst.setString(2, product.getNaam());
            pst.setString(3, product.getBeschrijving());
            pst.setDouble(4, product.getPrijs());
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update a Product in the database
    @Override
    public boolean update(Product product) {
        String query = "UPDATE product SET naam = ?, beschrijving = ?, prijs = ? WHERE product_nummer = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, product.getNaam());
            pst.setString(2, product.getBeschrijving());
            pst.setDouble(3, product.getPrijs());
            pst.setInt(4, product.getProductNummer());
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Add a Product to an OVChipkaart in the database
    @Override
    public boolean addOVChipkaart(Product product, OVChipkaart ovChipkaart, String status) {
        String query = "INSERT INTO ov_chipkaart_product (kaart_nummer, product_nummer, status, last_update) VALUES (?, ?, ?, CURRENT_DATE)";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, ovChipkaart.getId());
            pst.setInt(2, product.getProductNummer());
            pst.setString(3, status);
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete a Product from the database
    @Override
    public boolean delete(Product product) {
        deleteFromManyToMany(product);

        String query = "DELETE FROM product WHERE product_nummer = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, product.getProductNummer());
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Find a Product by an OVChipkaart
    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) {
        String query = "SELECT product.product_nummer, product.naam, product.beschrijving, product.prijs FROM product JOIN ov_chipkaart_product ON product.product_nummer = ov_chipkaart_product.product_nummer WHERE ov_chipkaart_product.kaart_nummer = ?";
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, ovChipkaart.getId());
            var rs = pst.executeQuery();
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_nummer"),
                        rs.getString("naam"),
                        rs.getString("beschrijving"),
                        rs.getDouble("prijs")
                );
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    // Find all Products
    @Override
    public List<Product> findAll() {
        String query = "SELECT product_nummer, naam, beschrijving, prijs FROM product";
        List<Product> products = new ArrayList<>();

        try (Statement st = conn.createStatement()) {
            var rs = st.executeQuery(query);
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_nummer"),
                        rs.getString("naam"),
                        rs.getString("beschrijving"),
                        rs.getDouble("prijs")
                );
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    // Cascade delete from many-to-many table
    public void deleteFromManyToMany(Product product) {
        String query = "DELETE FROM ov_chipkaart_product WHERE product_nummer = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, product.getProductNummer());
            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
