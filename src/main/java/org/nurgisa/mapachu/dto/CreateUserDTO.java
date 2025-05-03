package org.nurgisa.mapachu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.nurgisa.mapachu.model.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDTO {
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    public User toEntity() {
        return User.builder()
                .username(this.username)
                .build();
    }
}