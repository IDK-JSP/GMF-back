package com.fmg.gmf_core.daos;

import com.fmg.gmf_core.entitys.Measurement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MeasurementDao {
    private final JdbcTemplate jdbcTemplate;

    public MeasurementDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Measurement> measurementRowMapper = (rs, _) -> new Measurement(
            rs.getInt("id_measurement"),
            rs.getString("name"),
            rs.getString("type")
    );
}
