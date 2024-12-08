package hexlet.code.repository;

import hexlet.code.model.UrlCheck;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CheckRepository extends BaseRepository {
    public static void save(UrlCheck urlCheck) throws SQLException {
        var sql = "INSERT INTO url_checks (url_id, status_code, h1, title, description, created_at) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, urlCheck.getUrlId());
            preparedStatement.setInt(2, urlCheck.getStatusCode());
            preparedStatement.setString(3, urlCheck.getH1());
            preparedStatement.setString(4, urlCheck.getTitle());
            preparedStatement.setString(5, urlCheck.getDescription());
            preparedStatement.setTimestamp(6, urlCheck.getCreatedAt());
            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                urlCheck.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("The database have not returned an id after saving");
            }
        }
    }

    public static List<UrlCheck> getUrlChecks(Long urlId) throws SQLException {
        var sql = "SELECT * FROM url_checks "
                + "WHERE url_id = ?";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, urlId);
            var resultSet = preparedStatement.executeQuery();
            var urlChecks = new LinkedList<UrlCheck>();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var statusCode = resultSet.getInt("status_code");
                var h1 = resultSet.getString("h1");
                var title = resultSet.getString("title");
                var description = resultSet.getString("description");
                var createdAt = resultSet.getTimestamp("created_at");
                var check = new UrlCheck(id, urlId, statusCode, h1, title, description, createdAt);
                urlChecks.addFirst(check);
            }
            return urlChecks;
        }
    }

//    public static List<UrlCheck> getUrlChecks() throws SQLException {
//        var sql = "SELECT * FROM url_checks";
//        try (var conn = dataSource.getConnection();
//             var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//            var resultSet = preparedStatement.executeQuery();
//            var urlChecks = new LinkedList<UrlCheck>();
//            while (resultSet.next()) {
//                var id = resultSet.getLong("id");
//                var urlId = resultSet.getLong("url_id");
//                var statusCode = resultSet.getInt("status_code");
//                var h1 = resultSet.getString("h1");
//                var title = resultSet.getString("title");
//                var description = resultSet.getString("description");
//                var createdAt = resultSet.getTimestamp("created_at");
//                var check = new UrlCheck(id, urlId, statusCode, h1, title, description, createdAt);
//                urlChecks.addFirst(check);
//            }
//            return urlChecks;
//        }
//    }

    public static Map<Long, UrlCheck> findLatestChecks() throws SQLException {
        var sql = "SELECT DISTINCT ON (url_id) * from url_checks order by url_id DESC, id DESC";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            var result = new HashMap<Long, UrlCheck>();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var urlId = resultSet.getLong("url_id");
                var statusCode = resultSet.getInt("status_code");
                var title = resultSet.getString("title");
                var h1 = resultSet.getString("h1");
                var description = resultSet.getString("description");
                var createdAt = resultSet.getTimestamp("created_at");
                var check = new UrlCheck(id, urlId, statusCode, h1, title, description, createdAt);
//                var check = new UrlCheck(statusCode, title, h1, description);
//                check.setId(id);
//                check.setUrlId(urlId);
//                check.setCreatedAt(createdAt);
                result.put(urlId, check);
            }
            return result;
        }
    }

    public static Optional<UrlCheck> getLastCheck(Long urlId) throws SQLException {
        var sql = "SELECT * FROM url_checks "
                + "WHERE url_id = ? "
                + "ORDER BY created_at DESC "
                + "LIMIT 1";
        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, urlId);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                var id = resultSet.getLong("id");
                var statusCode = resultSet.getInt("status_code");
                var h1 = resultSet.getString("h1");
                var title = resultSet.getString("title");
                var description = resultSet.getString("description");
                var createdAt = resultSet.getTimestamp("created_at");
                return Optional.of(new UrlCheck(id, urlId, statusCode, h1, title, description, createdAt));
            }
            return Optional.empty();
        }
    }
}
