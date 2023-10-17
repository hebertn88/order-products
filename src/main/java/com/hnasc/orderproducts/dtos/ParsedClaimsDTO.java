package com.hnasc.orderproducts.dtos;

import java.util.Date;

public record ParsedClaimsDTO(String sub, String iss, Date exp) {
}
