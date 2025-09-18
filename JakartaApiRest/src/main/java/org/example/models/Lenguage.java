package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Lenguage {

    @ColumnJDBC(name = "LenguageId")
    private int lenguageId;

    @ColumnJDBC(name = "Name")
    private int name;

    @ColumnJDBC(name = "LastUpdateAt")
    private Date lastUpdateAt;

    @Override
    public String toString() {
        return "Lenguage{" +
                "lenguageId=" + lenguageId +
                ", name=" + name +
                ", lastUpdate=" + lastUpdateAt +
                '}';
    }
}
