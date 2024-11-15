package com.mycompany.model.query;

import org.springframework.data.domain.PageRequest;

public record CustomerQuery(PageRequest pageRequest) {}
