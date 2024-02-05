package org.example.migrate;

import org.flywaydb.core.Flyway;

public class MigrationClass {
    public static void main(String[] args) {
        migrate();
    }
    public static void migrate(){
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:file:./src/main/resources/test", "sa", null)
                .locations("filesystem:src/main/resources/db.migration")
                .baselineOnMigrate(true)
                .cleanDisabled(false)
                .load();
        flyway.migrate();
    }
}
