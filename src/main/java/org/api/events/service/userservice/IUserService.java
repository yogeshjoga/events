package org.api.events.service.userservice;

import org.api.events.dto.RelativeResponceDto;
import org.api.events.dto.SignUpDTO;

public interface IUserService {

    RelativeResponceDto signUp(SignUpDTO dto);
}
