package org.ametiste.pg.test;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;


public class Main {



    public static void main(String[] args) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/db");


        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);



        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        MetaWrapper metaWrapper = new MetaWrapper(map);

        BeanPropertySqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource( metaWrapper );
        sqlParameterSource.registerSqlType( "metadata", Types.OTHER );

        jdbcTemplate.update("CREATE TABLE mydb.meta (metadata jsonb)",sqlParameterSource);

        jdbcTemplate.update( "INSERT INTO mydb.meta (metadata) VALUES (:metadata)", sqlParameterSource );

    }
}
