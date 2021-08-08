package dmit2015.rcharitra.assignment05.config;
import javax.annotation.sql.DataSourceDefinition;
import javax.annotation.sql.DataSourceDefinitions;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;


@DataSourceDefinitions({
// @DataSourceDefinition(
// name="java:app/datasources/mssqlDS",
// className="com.microsoft.sqlserver.jdbc.SQLServerDataSource",
// url="jdbc:sqlserver://localhost;databaseName=DMIT2015DB",
// user="user2015",
// password="Password2015"),
        @DataSourceDefinition(
                name="java:app/datasources/oracleUser2015DS",
                className="oracle.jdbc.pool.OracleDataSource",
                url="jdbc:oracle:thin:@localhost:11521/xepdb1",
                user="user2015",
                password="Password2015"),
})

@ApplicationScoped
@FacesConfig
public class ApplicationConfig {
}
