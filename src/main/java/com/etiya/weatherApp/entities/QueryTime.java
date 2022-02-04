package com.etiya.weatherApp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "query_times")
public class QueryTime {
    private String id;

    private long milliTime;

    private int crudStatus;//1-getall 2-save 3-update 4-delete

    private User user;
}
