package firstproject.factoryapplication.dto;

import firstproject.factoryapplication.model.enums.EmployeeStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {
    private Long id;

    @NotBlank(message = "Position cannot be blank")
    private String position;

    @Positive(message = "Salary must be positive")
    private double salary;

    @NotNull(message = "Status cannot be null")
    private EmployeeStatus status;

    private double locationX;
    private double locationY;
}
