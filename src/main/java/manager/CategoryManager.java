package manager;

import db.DBConnectionProvider;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void save(Category category) {
        String sql = "INSERT INTO category(name) VALUES(?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, category.getName());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                category.setId(generatedKeys.getInt(1));
            }
            System.out.println("Category inserted into DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Category getById(int id) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("Select * from category where id = " + id);
            if (resultSet.next()) {
                return getCategoryFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> getAll() {
        List<Category> categoryList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from category");
            while (resultSet.next()) {
                categoryList.add(getCategoryFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }


    public void edit(Category category) {
        String sql = "UPDATE category SET name = '%s' WHERE id = %d";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(String.format(sql, category.getName(), category.getId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeById(int categoryId) {
        String sql = "DELETE FROM category WHERE id = " + categoryId;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Category getCategoryFromResultSet(ResultSet resultSet)
            throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("name"));
        return category;

    }
}


