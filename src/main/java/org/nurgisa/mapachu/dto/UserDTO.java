package org.nurgisa.mapachu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.nurgisa.mapachu.model.User;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    private LocalDateTime createdAt;

    public static UserDTO fromEntity(User u) {
        return UserDTO.builder()
                .id(u.getId())
                .username(u.getUsername())
                .createdAt(u.getCreatedAt())
                .build();
    }

    public User toEntity() {
        User u = new User();
        u.setUsername(this.username);
        return u;
    }

}
