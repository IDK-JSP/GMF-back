package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.Ingredient;
import com.fmg.gmf_core.entitys.Measurement;
import com.fmg.gmf_core.helpers.GlobalHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MeasurementDao {
    private final JdbcTemplate jdbcTemplate;
    private final GlobalHelper globalHelper;

    public MeasurementDao(JdbcTemplate jdbcTemplate, GlobalHelper globalHelper) {
        this.jdbcTemplate = jdbcTemplate;
        this.globalHelper = globalHelper;
    }

    private final RowMapper<Measurement> measurementRowMapper = (rs, _) -> new Measurement(
            rs.getInt("id_measurement"),
            rs.getString("name"),
            rs.getString("type")
    );
    public List<Measurement> findAll() {
        String sql = "SELECT * FROM measurement";
        List<Measurement> measurements = jdbcTemplate.query(sql, measurementRowMapper);
        globalHelper.isEmpty(measurements, "mesure");
        return measurements;
    }
    public Measurement save(Measurement measurement) {
        globalHelper.notExist(measurementExist(measurement.getName()),"Mesure");
        String sql = "INSERT INTO measurement (name, type) VALUES (?, ?)";
        jdbcTemplate.update(sql, measurement.getName(), measurement.getType());
        return measurement;
    }

    //Utilitaire
    public Boolean measurementExist(String name){
        String checkSql = "SELECT COUNT(*) FROM measurement WHERE name = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, name);
        return count > 0;
    }
}
