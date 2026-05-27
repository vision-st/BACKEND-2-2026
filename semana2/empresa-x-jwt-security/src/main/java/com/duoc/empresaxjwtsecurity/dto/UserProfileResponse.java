package com.duoc.empresaxjwtsecurity.dto;

import java.util.List;

public record UserProfileResponse(String username, List<String> roles, String authModel) {
}
