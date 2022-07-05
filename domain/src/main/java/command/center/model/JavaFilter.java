package command.center.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class JavaFilter {
    String campaignname;
    LocalDate startDate;
    LocalDate endDate;
}
