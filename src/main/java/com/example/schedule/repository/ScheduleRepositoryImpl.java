package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedules;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto saveScheudle(Schedules sc) {
        // INSERT Query를 직접 작성하지 않아도 된다.
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("schedules").usingGeneratedKeyColumns("id").usingColumns("title","content","user_id","update_date");
        jdbcInsert.withTableName("schedules").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title",sc.getTitle());
        parameters.put("content",sc.getContent());
        parameters.put("user_id",sc.getUserId()); //외래키로 유저테이블과 연결하기
        parameters.put("update_date",sc.getUpdate_date());

        // 저장 후 생성된 key값을 Number 타입으로 반환하는 메서드
        // executeAndReturnKey : DB에 insert 하고, AUTO_INCREMENT 또는 SERIAL 같은 자동 생성된 id 값을 반환해주는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(key.longValue(), sc.getTitle(), sc.getContent(), sc.getUserId(),sc.getCreate_date(),sc.getUpdate_date());
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules(String date, String name, Long id) {
        StringBuilder sql = new StringBuilder("SELECT * FROM schedules WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if(date != null){
            sql.append(" AND DATE(update_date) = ?");
            LocalDate localDate = LocalDate.parse(date); // 문자열을 스트링으로 받아서, 데이터 타입으로 변환해서 전달 "2025-03-26"
            params.add(java.sql.Date.valueOf(localDate)); // 이건 그냥 검색해서 알아냄.... 이렇게 쓰면 된다고...
        }
        if(name !=null){
            sql.append(" AND user_id IN (SELECT id FROM users WHERE name = ?)");
            params.add(name);
        }

        if(id !=null){
            sql.append(" AND user_id = ?");
            params.add(id);
        }

        //String sql = "SELECT * FROM schedules WHERE 1=1 AND DATE(update_date) = ? AND user_id IN (...) ORDER BY ...";
        //List<Object> params = List.of("2025-03-27", "sun");
        //
        //jdbcTemplate.query(sql, rowMapper, params.toArray());
        sql.append(" ORDER BY update_date DESC");

        return jdbcTemplate.query(sql.toString(), scheduleRowMapper(), params.toArray());
//        return jdbcTemplate.query("select * from schedules", scheduleRowMapper());
    }


    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getLong("user_id"),
                        rs.getTimestamp("create_date"),
                        rs.getTimestamp("update_date").toLocalDateTime()
                        //get update_date
                        //get user_id
                );
            }
        };
    }

    @Override
    public Schedules findScheduleById(Long id) {
        List<Schedules> result = jdbcTemplate.query("select * from schedules where id = ?", schedulesRowMapper2(), id);

        return result.stream().findAny().orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = "+id));
    }


    private RowMapper<Schedules> schedulesRowMapper2(){
        return new RowMapper<Schedules>() {
            @Override
            public Schedules mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedules(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getLong("user_id"),
                        rs.getTimestamp("create_date"),
                        rs.getTimestamp("update_date").toLocalDateTime()
                );
            }
        };
    }

    @Override
    public int update(Long id, String content) {
        return jdbcTemplate.update("update schedules set content = ?, update_date = NOW() where id = ?", content, id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("delete from schedules where id = ?", id);
    }

}
