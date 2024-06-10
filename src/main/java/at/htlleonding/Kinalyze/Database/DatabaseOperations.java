package at.htlleonding.Kinalyze.Database;

import at.htlleonding.Kinalyze.Entity.KAnalysis;
import at.htlleonding.Kinalyze.Entity.KAnalysisDetail;
import at.htlleonding.Kinalyze.Entity.KCode;
import at.htlleonding.Kinalyze.Entity.KSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DatabaseOperations {

    public static int addKCode(byte[] data, String filename, String fileending) {
        DatabaseConnection database = DatabaseConnection.getInstance();

        String query = "INSERT INTO k_code (c_data, c_filename, c_fileending) VALUES (?, ?, ?)";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setBytes(1, data);
            stmt.setString(2, filename);
            stmt.setString(3, fileending);
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

            database.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static KCode getKCode(int c_id) {
        DatabaseConnection database = DatabaseConnection.getInstance();

        String query = "SELECT * FROM k_code WHERE c_id = ?";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, c_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                byte[] data = rs.getBytes("c_data");
                String filename = rs.getString("c_filename");
                String fileending = rs.getString("c_fileending");
                return new KCode(c_id, data, filename, fileending);
            }

            database.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int addKSession(Timestamp date, int c_id) {
        DatabaseConnection database = DatabaseConnection.getInstance();
        String query = "INSERT INTO k_session (s_date, c_id) VALUES (?, ?)";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, date);
            stmt.setInt(2, c_id);
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

            database.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static KSession getKSession(int s_id) {
        DatabaseConnection database = DatabaseConnection.getInstance();
        String query = "SELECT * FROM k_session WHERE s_id = ?";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, s_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Timestamp date = rs.getTimestamp("s_date");
                int c_id = rs.getInt("c_id");
                return new KSession(s_id, date, c_id);
            }

            database.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int addKAnalysis(String type, String result, int s_id) {
        DatabaseConnection database = DatabaseConnection.getInstance();
        String query = "INSERT INTO k_analysis (a_type, a_result, s_id) VALUES (?, ?, ?)";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, type);
            stmt.setString(2, result);
            stmt.setInt(3, s_id);
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

            database.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static KAnalysis getKAnalysis(int a_id) {
        DatabaseConnection database = DatabaseConnection.getInstance();
        String query = "SELECT * FROM k_analysis WHERE a_id = ?";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, a_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String type = rs.getString("a_type");
                String result = rs.getString("a_result");
                int s_id = rs.getInt("s_id");
                return new KAnalysis(a_id, type, result, s_id);
            }

            database.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int addKAnalysisDetail(int row, String content, int a_id) {
        DatabaseConnection database = DatabaseConnection.getInstance();
        String query = "INSERT INTO k_analysis_detail (ad_row, ad_content, a_id) VALUES (?, ?, ?)";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, row);
            stmt.setString(2, content);
            stmt.setInt(3, a_id);
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

            database.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static KAnalysisDetail getKAnalysisDetail(int ad_id) {
        DatabaseConnection database = DatabaseConnection.getInstance();
        String query = "SELECT * FROM k_analysis_detail WHERE ad_id = ?";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ad_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int row = rs.getInt("ad_row");
                String content = rs.getString("ad_content");
                int a_id = rs.getInt("a_id");
                return new KAnalysisDetail(ad_id, row, content, a_id);
            }

            database.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
