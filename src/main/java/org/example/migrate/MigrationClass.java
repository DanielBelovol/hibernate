package org.example.migrate;

import org.flywaydb.core.Flyway;

public class MigrationClass {
    public void migrate(){
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:h2:~/test", "sa", "")
                .locations("filesystem:src/main/resources/db.migration")
                .baselineOnMigrate(true)
                .cleanDisabled(false)
                .load();
        flyway.migrate();
    }
}
