package org.api.events.service.userservice;

import org.api.events.dto.RelativeResponceDto;
import org.api.events.dto.SignUpDTO;
import org.api.events.models.User;

import java.util.UUID;

public interface IUserService {
    RelativeResponceDto signUp(SignUpDTO dto);
    User getUserByEmail(String email);
    User getUserById(UUID userId);

    User loginUser(String email, String password);
}
