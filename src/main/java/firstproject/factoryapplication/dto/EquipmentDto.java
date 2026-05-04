package firstproject.factoryapplication.dto;

import firstproject.factoryapplication.model.enums.EquipmentStatus;
import firstproject.factoryapplication.model.enums.EquipmentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EquipmentDto {
    private Long id;
    private double locationX;
    private double locationY;

    @Positive(message = "Capacity must be positive")
    private int capacity;

    @NotNull
    private EquipmentStatus status;

    @NotNull
    private EquipmentType type;
}
