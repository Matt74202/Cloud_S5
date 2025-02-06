package model;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Analyse {
    int id;
    TypeAnalyse type;
    Crypto crypto;
    Date date_min;
    Date date_max;
}
