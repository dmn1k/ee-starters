package com.github.dmn1k.dbdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class DatabaseRule implements TestRule {
    private final EntityManager entityManager;

    public DatabaseRule() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("todos-test");
        entityManager = emFactory.createEntityManager();
    }
    
    
    @Override
    public Statement apply(Statement stmnt, Description d) {
        return new Statement(){
            @Override
            public void evaluate() throws Throwable {
                setupDbSchema();
                
                EntityTransaction txn = entityManager.getTransaction();
                txn.begin();
                stmnt.evaluate();
                txn.commit();
            }
        };
   
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    
    private void setupDbSchema() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "sa");
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));

            Liquibase liquibase = new Liquibase("changelogs/migrate.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update("");
        } catch (SQLException | LiquibaseException ex) {
            throw new RuntimeException(ex);
        }
    }

}
